package app;

import net.moonlightflower.wc3libs.app.ObjMerger;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public class ObjMergerTest {
    @Test()
    public void test() throws Exception {
        ObjMerger objMerger = new ObjMerger();

        File inMap = new File("C:\\Users\\Win7F\\Downloads\\objmergerbug\\4\\Forest Defense 0.20a.w3x");

        objMerger.readFromMap(inMap, true, new File("C:\\Users\\Win7F\\Downloads\\objmergerbug\\4\\objmergertestIn"));

        objMerger.filter(objMerger.FILTER_MODDED_OR_CUSTOM);

        objMerger.writeToDir(new File("C:\\Users\\Win7F\\Downloads\\objmergerbug\\4\\objmergertestOut"));

        File outMap = new File("C:\\Users\\Win7F\\Downloads\\objmergerbug\\4\\out.w3x");

        Files.copy(inMap.toPath(), outMap.toPath(), StandardCopyOption.REPLACE_EXISTING);

        objMerger.writeToMap(outMap, new File("C:\\Users\\Win7F\\Downloads\\objmergerbug\\4\\objmergertestMapPort"));

        String s = Integer.toString(1747988548, 256);

        for (int i = 0; i < s.length(); i++) {
            System.out.println((char) (Integer.parseInt(Character.toString(s.charAt(i)), 256) + 48));
        }
    }
}
