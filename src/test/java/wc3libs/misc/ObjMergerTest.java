package wc3libs.misc;

import com.esotericsoftware.minlog.Log;
import net.moonlightflower.wc3libs.app.ObjMerger;
import net.moonlightflower.wc3libs.port.Orient;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class ObjMergerTest {
    @Test()
    public void testMerger() {
        try {
            ObjMerger merger = new ObjMerger();

            //File classDir = new File(Orient.getWorkingDir(), "tests\\objMergerTest");
            File inMapFile = new File(getClass().getClassLoader().getResource("in.w3x").getFile());

            File workDir = new File(new File(getClass().getClassLoader().getResource("").getFile()), "work");
            
            Orient.createDir(workDir);

            Log.info("workDir: " + workDir + "; exists: " + workDir.exists());
            //merger.setWorkDir(workDir);
            Log.info("map: " + inMapFile + "; exists: " + inMapFile.exists());
            //merger.readFromMap(inMapFile, true);

            File inDir = new File(workDir, "in");
            File outDir = new File(workDir, "out");
            
            Orient.removeDir(inDir);
            Orient.removeDir(outDir);
            
            Orient.createDir(inDir);
            Orient.createDir(outDir);
            
            merger.exportMap(inMapFile, inDir);

            Log.info("exported all files from input map");
            
            merger.addDir(inDir);

            Log.info("added all exported files to the objectmerger");
            
            merger.writeToDir(outDir, true);

            Log.info("Written to directory");

            File outMapFile = new File(workDir, "merged.w3x");

            Orient.copyFile(inMapFile, outMapFile, true);
            
            System.out.println("outMap:" + outMapFile + ";" + outMapFile.exists());
            
            merger.writeToMap(outMapFile, outDir);
        } catch (Exception e) {
            Assert.fail(e.getMessage(), e);
        }

        //merger.addDir(dir);
    }

}
