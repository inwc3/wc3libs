package wc3libs.misc.model;

import net.moonlightflower.wc3libs.misc.model.MDX;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.File;
import java.io.IOException;

public class MDXTest extends Wc3LibTest {
    @Test()
    public void MDXTest() throws IOException {
        MDX mdx = new MDX(getFile("wc3data/Infos/PackHorse.mdx"));

        System.out.println(mdx.getVersions().get(0));
    }
}
