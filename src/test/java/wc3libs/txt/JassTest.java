package wc3libs.txt;

import net.moonlightflower.wc3libs.txt.Jass;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.File;

import static org.testng.Assert.assertFalse;


public class JassTest extends Wc3LibTest {

    @Test
    public void testRebuild() throws Exception {
        File file = getFile("jass/compiled.j");

        Jass jass = new Jass(file);

        assertFalse(System.err.checkError());
    }

}
