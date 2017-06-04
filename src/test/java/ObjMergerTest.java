import net.moonlightflower.wc3libs.app.ObjMerger;
import net.moonlightflower.wc3libs.port.Orient;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class ObjMergerTest {

    @Test
    public void testMerger() {
        try {
            ObjMerger merger = new ObjMerger();

            //File classDir = new File(Orient.getWorkingDir(), "tests\\objMergerTest");
            File inMapFile = new File(getClass().getResource("in.w3x").getFile());

            File workDir = new File(new File(getClass().getResource("").getFile()), "work");

            System.out.println(workDir);

            //merger.setWorkDir(workDir);

            System.out.println("map:" + inMapFile);
            //merger.readFromMap(inMapFile, true);

            File inDir = new File(workDir, "in");
            File outDir = new File(workDir, "out");

            //merger.exportMap(inMapFile, inDir);

            merger.addDir(inDir);

            merger.writeToDir(outDir);

            File outMapFile = new File(workDir, "merged.w3x");

            Orient.copyFile(inMapFile, outMapFile, true);
            System.out.println(outMapFile + ";" + outMapFile.exists());
            merger.writeToMap(outMapFile, outDir);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }

        //merger.addDir(dir);
    }

}
