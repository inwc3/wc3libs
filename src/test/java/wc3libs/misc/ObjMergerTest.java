package wc3libs.misc;

import net.moonlightflower.wc3libs.app.ObjMerger;
import net.moonlightflower.wc3libs.port.Context;
import net.moonlightflower.wc3libs.port.GameDirFinder;
import net.moonlightflower.wc3libs.port.NotFoundException;
import net.moonlightflower.wc3libs.port.Orient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class ObjMergerTest {
    private static final Logger log = LoggerFactory.getLogger(ObjMergerTest.class.getName());

    @Test()
    public void testMerger() {
        try {
            Context.getService(GameDirFinder.class).get();

            try {
                ObjMerger merger = new ObjMerger();

                //File classDir = new File(Orient.getWorkingDir(), "tests\\objMergerTest");
                File inMapFile = new File(getClass().getClassLoader().getResource("in.w3x").getFile());

                File workDir = new File(new File(getClass().getClassLoader().getResource("").getFile()), "work");

                Orient.createDir(workDir);

                log.info("workDir: " + workDir + "; exists: " + workDir.exists());
                //merger.setWorkDir(workDir);
                log.info("map: " + inMapFile + "; exists: " + inMapFile.exists());
                //merger.readFromMap(inMapFile, true);

                File inDir = new File(workDir, "in");
                File outDir = new File(workDir, "out");

                Orient.removeDir(inDir);
                Orient.removeDir(outDir);

                Orient.createDir(inDir);
                Orient.createDir(outDir);

                merger.exportMap(inMapFile, inDir);

                log.info("exported all files from input map");

                merger.addDir(inDir);

                log.info("added all exported files to the objectmerger");

                merger.writeToDir(outDir, true);

                log.info("Written to directory");

                File outMapFile = new File(workDir, "merged.w3x");

                Orient.copyFile(inMapFile, outMapFile, true);

                System.out.println("outMap:" + outMapFile + ";" + outMapFile.exists());

                merger.writeToMap(outMapFile, outDir);
            } catch (Exception e) {
                Assert.fail(e.getMessage(), e);
            }

            //merger.addDir(dir);
        } catch (NotFoundException e) {
            log.info("no game dir found, skip test", e);
        }
    }

}
