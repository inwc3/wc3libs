package wc3libs.txt;

import com.esotericsoftware.minlog.Log;
import net.moonlightflower.wc3libs.app.ProfileCleaner;
import net.moonlightflower.wc3libs.txt.Profile;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.testng.Assert.assertTrue;

public class ProfileCleanTest extends Wc3LibTest {

    @Test
    public void testClean() throws Exception {
        File file = getFile("txts/UnitStrings.txt");

        try {
            Profile profile = new Profile(file);

            File rebuild = new File("out/txtdat/" + file.getName());
            rebuild.delete();

            ProfileCleaner.clean(profile);
            profile.write(rebuild);

            String generated = new String(Files.readAllBytes(rebuild.toPath()));
            assertTrue(!generated.contains("editorsuffix"));
            assertTrue(!generated.contains("editorname"));
        } catch (IOException e) {
            Log.error(e.getMessage(), e);
        }
    }


}
