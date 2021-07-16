package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.BinState;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.bin.app.W3C;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class W3CTest extends Wc3LibTest {
    @Test()
    public void writeTest() throws IOException {
        W3C w3c = new W3C();

        w3c.addCamera();
        w3c.addCamera();

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

        Wc3BinOutputStream stream = new Wc3BinOutputStream(byteStream);

        w3c.write_0x0(stream);

        stream.close();

        W3C newW3C = new W3C();

        newW3C.read_0x0(new Wc3BinInputStream(new ByteArrayInputStream(byteStream.toByteArray())));

        Assert.assertEquals(w3c.getCameras().size(), newW3C.getCameras().size());

        for (int i = 0; i < w3c.getCameras().size(); i++) {
            W3C.Camera camera = w3c.getCameras().get(i);
            W3C.Camera newCamera = newW3C.getCameras().get(i);

            for (BinState<?> state : camera.getVals().keySet()) {
                Assert.assertEquals(newCamera.get(state), camera.get(state), state.toString());
            }
        }
    }

    @Test()
    public void readWriteCycle() throws IOException {
        readWriteCycle(W3C.class, getFile("wc3data/W3C/war3map.w3c"));
    }
}
