package wc3libs.misc;

import net.moonlightflower.wc3libs.misc.audio.WAV;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class WAVTest extends Wc3LibTest {
    @Test
    public void testWav() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file = getFile("test.wav");

        WAV wav = new WAV(file);
        Assert.assertNotNull(wav);
    }
}
