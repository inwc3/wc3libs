package wc3libs.txt;

import net.moonlightflower.wc3libs.txt.FDF;
import net.moonlightflower.wc3libs.txt.TXT;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.File;
import java.io.IOException;

import static org.testng.Assert.assertEquals;


public class FDFTest extends Wc3LibTest {

    @Test
    public void testRebuild() throws Exception {
        File file = getFile("fdfs/GlobalStrings.fdf");

        try {
            FDF fdf = new FDF(file);

            File rebuild = new File("out/txtdat/" + file.getName());
            rebuild.delete();

            TXT txt = fdf.toTXT();
            txt.write(rebuild);

            TXT.Section defaultSection = txt.getDefaultSection();
            TXT.Section.Field firstField = defaultSection.getFields().values().iterator().next();
            assertEquals(firstField.getVals().get(0).toString(), "Accept" );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
