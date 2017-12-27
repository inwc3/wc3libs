package wc3libs.misc.model;

import net.moonlightflower.wc3libs.bin.BinOutputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.model.MDX;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.*;

public class MDXTest extends Wc3LibTest {
    @Test()
    public void MDXTest() throws IOException {
        MDX mdx = new MDX(getFile("wc3data/Models/PackHorse.mdx"));

        System.out.println(mdx.getVersionChunks().get(0).getVersion());
    }

    @Test()
    public void readWriteCycle() throws IOException {
        readWriteCycle(MDX.class, getFile("wc3data/Models/PackHorse.mdx"));
    }
}
