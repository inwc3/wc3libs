package wc3libs.port;

import net.moonlightflower.wc3libs.port.GameExeFinder;
import net.moonlightflower.wc3libs.port.NotFoundException;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.annotation.Nonnull;
import java.io.File;

public class GameExeFinderTest {
    public class TestGameExeFinder extends GameExeFinder {
        @Nonnull
        @Override
        public File find() throws NotFoundException {
            return new File("");
        }
    }

    @Test
    public void notAlreadyTriedTest() throws NotFoundException {
        Assert.assertEquals(new TestGameExeFinder().get(), new File(""));
        Assert.assertEquals(new TestGameExeFinder().get(), new File(""));
        Assert.assertEquals(new TestGameExeFinder().get(), new File(""));
    }
}
