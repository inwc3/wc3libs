package net.moonlightflower.wc3libs.app;

import com.esotericsoftware.minlog.Log;
import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.bin.ObjMod.ObjPack;
import net.moonlightflower.wc3libs.bin.app.objMod.*;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.misc.Translator;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.port.Orient;
import net.moonlightflower.wc3libs.slk.RawMetaSLK;
import net.moonlightflower.wc3libs.slk.SLK;
import net.moonlightflower.wc3libs.slk.app.doodads.DoodSLK;
import net.moonlightflower.wc3libs.slk.app.objs.*;
import net.moonlightflower.wc3libs.txt.Profile;
import net.moonlightflower.wc3libs.txt.TXTSectionId;
import net.moonlightflower.wc3libs.txt.WTS;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.NoSuchFileException;
import java.util.*;

public class ObjMerger {
    private Map<File, SLK> _slks = new LinkedHashMap<>();

    private void addSlk(File inFile, SLK otherSlk) {
        assert (inFile != null);

        SLK slk = _slks.computeIfAbsent(inFile, SLK::createFromInFile);

        slk.merge(otherSlk);
    }

    private RawMetaSLK _metaSlk = new RawMetaSLK();

    private void addMetaSlk(RawMetaSLK slk) {
        _metaSlk.merge(slk);
    }

    private Profile _profile = new Profile();

    private void addProfile(Profile otherProfile) {
        _profile.merge(otherProfile);
    }

    private Map<File, ObjMod> _objMods = new LinkedHashMap<>();

    private void addObjMod(File inFile, ObjMod otherObjMod) {
        try {
            ObjPack pack = otherObjMod.reduce(_metaSlk);

            Map<ObjId, ObjId> baseObjIds = pack.getBaseObjIds();

            Set<ObjId> objIds = new HashSet<>();

            for (Map.Entry<File, SLK> slkEntry : pack.getSlks().entrySet()) {
                File file = slkEntry.getKey();
                SLK otherSlk = slkEntry.getValue();

                Map<ObjId, SLK.Obj> otherObjs = new LinkedHashMap<>(((Map<ObjId, SLK.Obj>) otherSlk.getObjs()));

                otherSlk.clearObjs();

                for (Map.Entry<ObjId, SLK.Obj> objEntry : otherObjs.entrySet()) {                	
                    ObjId objId = objEntry.getKey();

                    objIds.add(objId);
                    
                    SLK.Obj otherObj = objEntry.getValue();

                    SLK.Obj obj = otherSlk.addObj(objId);

                    ObjId baseId = baseObjIds.get(objId);

                    if (baseId != null) {
                        SLK.Obj baseObj = _slks.get(file).getObj(baseId);

                        obj.merge(baseObj);
                    }

                    obj.merge(otherObj);
                }

                addSlk(file, otherSlk);
            }
            
            for (File baseSLKFile : otherObjMod.getSLKs()) {
            	SLK newSLK = SLK.createFromInFile(baseSLKFile);
            	
            	for (ObjId objId : objIds) {
            		SLK reducedSLK = pack.getSlks().get(baseSLKFile);

            		if ((reducedSLK != null) && reducedSLK.containsObj(objId)) continue;
            		
            		ObjId baseId = baseObjIds.get(objId);
            		
                    if (baseId != null) {
                        SLK.Obj baseObj = _slks.get(baseSLKFile).getObj(baseId);

                        if (baseObj != null) {
                        	SLK.Obj obj = newSLK.addObj(objId);

                        	obj.merge(baseObj);
                        }
                    }
            	}
            	
            	addSlk(baseSLKFile, newSLK);
            }
            
            //
            Profile otherProfile = pack.getProfile();

            Map<TXTSectionId, Profile.Obj> otherObjs = new LinkedHashMap<>(otherProfile.getObjs());

            otherProfile.clearObjs();
            
            for (Map.Entry<TXTSectionId, Profile.Obj> objEntry : otherObjs.entrySet()) {
                ObjId objId = ObjId.valueOf(objEntry.getKey().toString());
                Profile.Obj otherObj = objEntry.getValue();

                Profile.Obj obj = otherProfile.addObj(TXTSectionId.valueOf(objId.toString()));

                ObjId baseId = baseObjIds.get(objId);

                if (baseId != null) {
                    Profile.Obj baseObj = _profile.getObj(TXTSectionId.valueOf(baseId.toString()));

                    obj.merge(baseObj);
                }
                
                obj.merge(otherObj);
            }

            //pack.getProfile().print();

            addProfile(pack.getProfile());

            ObjMod objMod = _objMods.computeIfAbsent(inFile, k -> new ObjMod());

            objMod.merge(pack.getObjMod());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isObjModFileExtended(File inFile) {
        return (inFile.equals(W3A.GAME_PATH) || inFile.equals(W3D.GAME_PATH) || inFile.equals(W3Q.GAME_PATH));
    }

    private void addFiles(Map<File, File> metaSlkFiles, Map<File, File> slkFiles, Map<File, File> profileFiles, Map<File, File> objModFiles, File wtsFile) throws Exception {
        Log.info("adding exported files to object merger");
        if (wtsFile == null) {
            System.out.println("no wts file");
        } else {
            WTS wts = new WTS(wtsFile);

            Translator translator = new Translator();

            translator.addTXT(wts.toTXT());

            _profile.setTranslator(translator);
        }

        for (Map.Entry<File, File> fileEntry : metaSlkFiles.entrySet()) {
            File outFile = fileEntry.getValue();

            RawMetaSLK metaSlk = new RawMetaSLK(outFile);

            addMetaSlk(metaSlk);
        }

        for (Map.Entry<File, File> fileEntry : slkFiles.entrySet()) {
            File inFile = fileEntry.getKey();
            File outFile = fileEntry.getValue();

            SLK slk = SLK.createFromInFile(inFile, outFile);

            addSlk(inFile, slk);
        }

        for (Map.Entry<File, File> fileEntry : profileFiles.entrySet()) {
            File inFile = fileEntry.getKey();
            File outFile = fileEntry.getValue();

            Profile profile = new Profile(outFile);

            addProfile(profile);
        }

        for (Map.Entry<File, File> fileEntry : objModFiles.entrySet()) {
            File inFile = fileEntry.getKey();
            File outFile = fileEntry.getValue();

            ObjMod objMod = ObjMod.createFromInFile(inFile, outFile);

            addObjMod(inFile, objMod);
        }
    }


    private void exportFiles(File dir, Map<File, File> fileEntries) throws IOException {
        Orient.removeDir(dir);
        Orient.createDir(dir);

        for (Map.Entry<File, File> fileEntry : fileEntries.entrySet()) {
            File inFile = fileEntry.getKey();
            File outFile = fileEntry.getValue();

            Orient.copyFile(outFile, new File(dir, inFile.toString()), true);
        }
    }

    private void exportFiles(File outDir, Map<File, File> metaSlkFiles, Map<File, File> slkFiles, Map<File, File> profileFiles, Map<File, File> objModFiles,
							 File wtsFile) throws IOException {
        Map<File, File> fileEntries = new LinkedHashMap<>();

        for (Map.Entry<File, File> fileEntry : metaSlkFiles.entrySet()) {
            File inFile = fileEntry.getKey();
            File outFile = fileEntry.getValue();

            fileEntries.put(inFile, outFile);
        }

        for (Map.Entry<File, File> fileEntry : slkFiles.entrySet()) {
            File inFile = fileEntry.getKey();
            File outFile = fileEntry.getValue();

            fileEntries.put(inFile, outFile);
        }

        for (Map.Entry<File, File> fileEntry : profileFiles.entrySet()) {
            File inFile = fileEntry.getKey();
            File outFile = fileEntry.getValue();

            fileEntries.put(inFile, outFile);
        }

        for (Map.Entry<File, File> fileEntry : objModFiles.entrySet()) {
            File inFile = fileEntry.getKey();
            File outFile = fileEntry.getValue();

            fileEntries.put(inFile, outFile);
        }

        if (wtsFile != null) {
            fileEntries.put(WTS.GAME_PATH, wtsFile);
        }

        exportFiles(outDir, fileEntries);
    }

    private final static Collection<File> _metaSlkInFiles = Arrays.asList(
            new File("Doodads\\DoodadMetaData.slk"),
            new File("Units\\AbilityBuffMetaData.slk"),
            new File("Units\\AbilityMetaData.slk"),
            new File("Units\\DestructableMetaData.slk"),
            new File("Units\\UnitMetaData.slk"),
            new File("Units\\UpgradeMetaData.slk"));

    private final static Collection<File> _slkInFiles = Arrays.asList(
            DoodSLK.GAME_USE_PATH,
            UnitAbilsSLK.GAME_USE_PATH,
            UnitBalanceSLK.GAME_USE_PATH,
            UnitDataSLK.GAME_USE_PATH,
            UnitUISLK.GAME_USE_PATH,
            UnitWeaponsSLK.GAME_USE_PATH,
            ItemSLK.GAME_USE_PATH,
            DestructableSLK.GAME_USE_PATH,
            AbilSLK.GAME_USE_PATH,
            BuffSLK.GAME_USE_PATH,
            UpgradeSLK.GAME_USE_PATH);

    private final static Collection<File> _profileInFiles = Arrays.asList(Profile.getNativePaths());

    private final static Collection<File> _objModInFiles = Arrays.asList(
            W3A.GAME_PATH,
            W3B.GAME_PATH,
            W3D.GAME_PATH,
            W3H.GAME_PATH,
            W3Q.GAME_PATH,
            W3T.GAME_PATH,
            W3U.GAME_PATH);

    public void addDir(File dir) throws Exception {
        Log.info("Adding directory of files to be merged: " + dir.getAbsolutePath());
        Map<File, File> files = new LinkedHashMap<>();

        for (File outFile : Orient.getFiles(dir)) {
            File inFile = new File(outFile.toString().substring(dir.toString().length() + 1));

            files.put(inFile, outFile);
        }

        Map<File, File> metaSlkFiles = new LinkedHashMap<>();
        Map<File, File> slkFiles = new LinkedHashMap<>();
        Map<File, File> profileFiles = new LinkedHashMap<>();
        Map<File, File> objModFiles = new LinkedHashMap<>();
        File wtsFile = null;

        for (Map.Entry<File, File> fileEntry : files.entrySet()) {
            File inFile = fileEntry.getKey();
            File outFile = fileEntry.getValue();

            if (_metaSlkInFiles.contains(inFile)) {
                metaSlkFiles.put(inFile, outFile);
            }
            if (_slkInFiles.contains(inFile)) {
                slkFiles.put(inFile, outFile);
            }
            if (_profileInFiles.contains(inFile)) {
                profileFiles.put(inFile, outFile);
            }
            if (_objModInFiles.contains(inFile)) {
                objModFiles.put(inFile, outFile);
            }
            if (WTS.GAME_PATH.equals(inFile)) {
                wtsFile = outFile;
            }
        }

        addFiles(metaSlkFiles, slkFiles, profileFiles, objModFiles, wtsFile);
    }

    private void addExports(File outDir, MpqPort.Out.Result metaSlkResult, MpqPort.Out.Result slkResult, MpqPort.Out.Result profileResult, MpqPort.Out.Result
			objModResult, MpqPort.Out.Result wtsResult) throws Exception {
        Map<File, File> metaSlkFiles = new LinkedHashMap<>();

        processSegments(metaSlkResult, metaSlkFiles);

        Map<File, File> slkFiles = new LinkedHashMap<>();

        processSegments(slkResult, slkFiles);

        Map<File, File> profileFiles = new LinkedHashMap<>();

        processSegments(profileResult, profileFiles);

        Map<File, File> objModFiles = new LinkedHashMap<>();

        processSegments(objModResult, objModFiles);

        File wtsFile = null;

        try {
            wtsFile = wtsResult.getFile(WTS.GAME_PATH);
        } catch (NoSuchFileException e) {
        }

        exportFiles(outDir, metaSlkFiles, metaSlkFiles, profileFiles, objModFiles, wtsFile);

        addFiles(metaSlkFiles, metaSlkFiles, profileFiles, objModFiles, wtsFile);
    }

    private void processSegments(MpqPort.Out.Result metaSlkResult, Map<File, File> metaSlkFiles) throws IOException {
        for (MpqPort.Out.Result.Segment segment : metaSlkResult.getExports().values()) {
            File inFile = segment.getExport().getInFile();

            try {
                File outFile = metaSlkResult.getFile(inFile);

                metaSlkFiles.put(inFile, outFile);
            } catch (NoSuchFileException e) {
            }
        }
    }

    private final static File PROFILE_OUTPUT_PATH = new File("Units\\CampaignUnitStrings.txt");

    public void writeToDir(File outDir) throws Exception {
        Orient.createDir(outDir);

        for (Map.Entry<File, SLK> slkEntry : _slks.entrySet()) {
            File inFile = slkEntry.getKey();
            SLK slk = slkEntry.getValue();
            
            File outFile = new File(outDir, inFile.toString());

            slk.write(outFile);
        }

        File emptyFile = new File(outDir, "empty.txt");

        OutputStream emptyFileStream = Orient.createFileOutputStream(emptyFile);

        //emptyFileStream.write(0x00);

        emptyFileStream.close();

        //Orient.createFile(emptyFile);

        for (File inFile : _profileInFiles) {
            File outFile = new File(outDir, inFile.toString());

            Orient.copyFile(emptyFile, outFile, true);
        }

        File profileOutFile = new File(outDir, PROFILE_OUTPUT_PATH.toString());

        _profile.write(profileOutFile);

		/*for (Map.Entry<File, ObjMod> objModEntry : _objMods.entrySet()) {
			File inFile = objModEntry.getKey();
			ObjMod objMod = objModEntry.getValue();

			File outFile = new File(outDir, inFile.toString());

			objMod.write(outFile, isObjModFileExtended(inFile));
		}*/
    }

    public void writeToMap(File mapFile, File outDir) throws Exception {
        //File outDir = _workDir;

        Orient.removeDir(outDir);
        Orient.createDir(outDir);

        MpqPort.In portIn = new JMpqPort.In();

        for (Map.Entry<File, SLK> slkEntry : _slks.entrySet()) {
            File inFile = slkEntry.getKey();
            SLK slk = slkEntry.getValue();

            File outFile = new File(outDir, inFile.toString());

            slk.write(outFile);

            portIn.add(outFile, inFile);
        }

        for (File inFile : _profileInFiles) {
            File outFile = new File(outDir, inFile.toString());

            new Profile().write(outFile);
        }

        File profileOutFile = new File(outDir, PROFILE_OUTPUT_PATH.toString());

        _profile.write(profileOutFile);

        for (File inFile : _profileInFiles) {
            File outFile = new File(outDir, inFile.toString());

            portIn.add(outFile, inFile);
        }

        for (Map.Entry<File, ObjMod> objModEntry : _objMods.entrySet()) {
            File inFile = objModEntry.getKey();
            ObjMod objMod = objModEntry.getValue();

            File outFile = new File(outDir, inFile.toString());

            objMod.write(outFile, isObjModFileExtended(inFile));

            portIn.add(outFile, inFile);
        }

        //if(true) return;
        portIn.commit(mapFile);
    }

    public void exportMap(File mapFile, boolean includeNativeMpqs, File wc3Dir, File outDir) throws Exception {
        Log.info("Exporting map: " + mapFile.getAbsolutePath());
        Vector<File> mpqFiles = new Vector<>();

        mpqFiles.add(mapFile);

        if (includeNativeMpqs) {
            if (wc3Dir == null) {
                //throw new Exception("no wc3Dir");
                mpqFiles.add(new MpqPort.ResourceFile(""));
            } else {
                mpqFiles.addAll(JMpqPort.getWc3Mpqs(wc3Dir));
            }
        }

        MpqPort.Out portOut = new JMpqPort.Out();

        for (File inFile : _metaSlkInFiles) {
            portOut.add(inFile);
        }

        Collection<File> slkFiles = new ArrayList<>();

        slkFiles.addAll(_slkInFiles);

        for (File inFile : slkFiles) {
            portOut.add(inFile);
        }

        Collection<File> profileFiles = new ArrayList<>();

        profileFiles.addAll(_profileInFiles);

        for (File inFile : profileFiles) {
            portOut.add(inFile);
        }

        for (File inFile : _objModInFiles) {
            portOut.add(inFile);
        }

        portOut.add(WTS.GAME_PATH);

        MpqPort.Out.Result portResult = portOut.commit(mpqFiles);

        Map<File, File> outFiles = new LinkedHashMap<>();


        for (Map.Entry<File, MpqPort.Out.Result.Segment> segmentEntry : portResult.getExports().entrySet()) {
            File inFile = segmentEntry.getKey();

            try {
                File outFile = portResult.getFile(inFile);

                outFiles.put(inFile, outFile);
            } catch (NoSuchFileException e) {
            }
        }
        Log.info("Extracting following files: " + Arrays.toString(outFiles.values().toArray()));

        exportFiles(outDir, outFiles);
    }

    public void exportMap(File mapFile, File outDir) throws Exception {
        exportMap(mapFile, true, MpqPort.getWc3Dir(), outDir);
    }

    public void readFromMap(File mapFile, boolean includeNativeMpqs, File wc3Dir, File outDir) throws Exception {
        //File outDir = _workDir;

        Orient.removeDir(outDir);
        Orient.createDir(outDir);

        exportMap(mapFile, includeNativeMpqs, wc3Dir, outDir);

        addDir(outDir);
    }

    public void readFromMap2(File mapFile, boolean includeNativeMpqs, File wc3Dir, File outDir) throws Exception {
        Vector<File> mpqFiles = new Vector<>();

        mpqFiles.add(mapFile);

        if (includeNativeMpqs) {
            if (wc3Dir == null) throw new Exception("no wc3Dir");

            mpqFiles.addAll(JMpqPort.getWc3Mpqs(wc3Dir));
        }

        MpqPort.Out metaSlkPortOut = new JMpqPort.Out();

        for (File inFile : _metaSlkInFiles) {
            metaSlkPortOut.add(inFile);
        }

        MpqPort.Out.Result metaSlkResult = metaSlkPortOut.commit(mpqFiles);

        MpqPort.Out slkPortOut = new JMpqPort.Out();

        Collection<File> slkFiles = new ArrayList<>();

        slkFiles.addAll(_slkInFiles);

        for (File inFile : slkFiles) {
            slkPortOut.add(inFile);
        }

        MpqPort.Out.Result slkResult = slkPortOut.commit(mpqFiles);

        Collection<File> profileFiles = new ArrayList<>();

        profileFiles.addAll(_profileInFiles);

        MpqPort.Out profilePortOut = new JMpqPort.Out();

        for (File inFile : profileFiles) {
            profilePortOut.add(inFile);
        }

        MpqPort.Out.Result profileResult = profilePortOut.commit(mpqFiles);

        MpqPort.Out objModPortOut = new JMpqPort.Out();

        for (File inFile : _objModInFiles) {
            objModPortOut.add(inFile);
        }

        MpqPort.Out.Result objModResult = objModPortOut.commit(mapFile);

        MpqPort.Out wtsPortOut = new JMpqPort.Out();

        objModPortOut.add(WTS.GAME_PATH);

        MpqPort.Out.Result wtsResult = wtsPortOut.commit(mapFile);

        addExports(outDir, metaSlkResult, slkResult, profileResult, objModResult, wtsResult);
    }

    public void readFromMap(File mapFile, boolean includeNativeMpqs, File outDir) throws Exception {
        readFromMap(mapFile, includeNativeMpqs, MpqPort.getWc3Dir(), outDir);
    }

}