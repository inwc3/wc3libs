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
        Wc3BinInputStream inStream = new Wc3BinInputStream(getFile("wc3data/Models/PackHorse.mdx"));

        byte[] bytes = inStream.readBytes((int) inStream.size());

        inStream.rewind();

        MDX mdx = new MDX(inStream);

        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        Wc3BinOutputStream outStream = new Wc3BinOutputStream(outByteStream);

        mdx.write(outStream);

        outStream.close();

        /*FileOutputStream fp = new FileOutputStream("E:\\work\\bla.mdx");

        fp.write(outByteStream.toByteArray());

        fp.close();*/

        Assert.assertEquals(outByteStream.toByteArray(), bytes);
    }
}
