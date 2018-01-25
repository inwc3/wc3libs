package wc3libs.misc.model;

import net.moonlightflower.wc3libs.misc.model.MDX;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.IOException;

public class MDXTest extends Wc3LibTest {
    @Test()
    public void MDXTest() throws IOException {
        MDX mdx = new MDX(getFile("wc3data/Models/PackHorse.mdx"));

        Assert.assertTrue(mdx.getVersionChunk().isPresent());
        System.out.println(mdx.getVersionChunk().get().getVersion());
    }

    @Test()
    public void readWriteCycle() throws IOException {
        readWriteCycle(MDX.class, getFile("wc3data/Models/PackHorse.mdx"));
    }
}
