package wc3libs.app;

import net.moonlightflower.wc3libs.app.ObjMerger;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class ObjMergerTest {
    private Set<File> getFiles(File dir) {
        Set<File> ret = new LinkedHashSet<>();

        for (File file : dir.listFiles()) {
            if (file.isDirectory()) ret.addAll(getFiles(file)); else ret.add(file);
        }

        return ret;
    }

    private class Pair {
        File _outFile;
        File _inFile;

        private Pair(String outFile, String inFile) {
            _outFile = new File(outFile);
            _inFile = new File(inFile);
        }
    }

    Collection<Pair> pairs = new ArrayList<>();

    private void createPair(String inFileS, String outFileS) {
        pairs.add(new Pair(outFileS, inFileS));
    }

    @Test()
    public void test() throws Exception {
        ObjMerger objMerger = new ObjMerger();

        File inMap = new File("C:\\Users\\Win7F\\Downloads\\objmergerbug\\4\\Forest Defense 0.20a.w3x");

        objMerger.readFromMap(inMap, true, new File("C:\\Users\\Win7F\\Downloads\\objmergerbug\\4\\objmergertestIn"));

        objMerger.filter(objMerger.FILTER_MODDED_OR_CUSTOM);

        objMerger.writeToDir(new File("C:\\Users\\Win7F\\Downloads\\objmergerbug\\4\\objmergertestOut"), false);

        File outMap = new File("C:\\Users\\Win7F\\Downloads\\objmergerbug\\4\\out.w3x");

        Files.copy(inMap.toPath(), outMap.toPath(), StandardCopyOption.REPLACE_EXISTING);

        objMerger.writeToMap(outMap, new File("C:\\Users\\Win7F\\Downloads\\objmergerbug\\4\\objmergertestMapPort"));
    }

    @Test()
    public void bug() throws IOException {
        File temp = new File("C:\\Users\\Win7F\\Downloads\\objmergerbug\\4\\out.w3x");

        //new JMpqEditor().close();
    }
}
