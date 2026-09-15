package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.app.DOO;
import net.moonlightflower.wc3libs.bin.app.DOO_UNITS;
import net.moonlightflower.wc3libs.bin.app.IMP;
import net.moonlightflower.wc3libs.bin.app.MMP;
import net.moonlightflower.wc3libs.bin.app.SHD;
import net.moonlightflower.wc3libs.bin.app.W3C;
import net.moonlightflower.wc3libs.bin.app.W3E;
import net.moonlightflower.wc3libs.bin.app.W3I;
import net.moonlightflower.wc3libs.bin.app.W3R;
import net.moonlightflower.wc3libs.bin.app.WCT;
import net.moonlightflower.wc3libs.bin.app.WPM;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

/** Byte-identical cycles for every structured binary in the 3.0 editor dump. */
public class V3MapFilesTest extends Wc3LibTest {
    @DataProvider
    public Object[][] structuredFiles() {
        return new Object[][] {
            { DOO.class, "wc3data/DOO/war3map_v3.doo" },
            { DOO_UNITS.class, "wc3data/DOO_UNITS/war3mapUnits_v3.doo" },
            { MMP.class, "wc3data/MMP/war3map_v3.mmp" },
            { SHD.class, "wc3data/SHD/war3map_v3.shd" },
            { W3C.class, "wc3data/W3C/war3map_v3.w3c" },
            { W3E.class, "wc3data/W3E/war3map_v3.w3e" },
            { W3R.class, "wc3data/W3R/war3map_v3.w3r" },
            { WCT.class, "wc3data/WCT/war3map_v3.wct" },
            { WPM.class, "wc3data/WPM/war3map_v3.wpm" },
            { DOO.class, "wc3data/DOO/war3map_v3_filled.doo" },
            { DOO_UNITS.class, "wc3data/DOO_UNITS/war3mapUnits_v3_filled.doo" },
            { MMP.class, "wc3data/MMP/war3map_v3_filled.mmp" },
            { SHD.class, "wc3data/SHD/war3map_v3_filled.shd" },
            { W3C.class, "wc3data/W3C/war3map_v3_filled.w3c" },
            { W3E.class, "wc3data/W3E/war3map_v3_filled.w3e" },
            { W3I.class, "wc3data/W3I/war3map_v3_filled.w3i" },
            { W3R.class, "wc3data/W3R/war3map_v3_filled.w3r" },
            { WCT.class, "wc3data/WCT/war3map_v3_filled.wct" },
            { WPM.class, "wc3data/WPM/war3map_v3_filled.wpm" },
            { IMP.class, "wc3data/IMP/war3map_v3_filled.imp" },
        };
    }

    @Test(dataProvider = "structuredFiles")
    public void readWriteCycleV3(Class<?> format, String resource) throws Exception {
        readWriteCycle(format, getFile(resource));
    }

    @Test
    public void classifyVersion3Formats() throws Exception {
        try (Wc3BinInputStream stream = new Wc3BinInputStream(getFile("wc3data/DOO/war3map_v3.doo"))) {
            Assert.assertSame(new DOO(stream).getFormat(), DOO.EncodingFormat.DOO_0xD);
        }

        try (Wc3BinInputStream stream = new Wc3BinInputStream(getFile("wc3data/DOO_UNITS/war3mapUnits_v3.doo"))) {
            DOO_UNITS units = new DOO_UNITS(stream);
            Assert.assertSame(units.getFormat(), DOO_UNITS.EncodingFormat.DOO_0xD);
            Assert.assertTrue(units.isOpaque());
        }

        try (Wc3BinInputStream stream = new Wc3BinInputStream(getFile("wc3data/W3C/war3map_v3.w3c"))) {
            Assert.assertSame(new W3C(stream).getFormat(), W3C.EncodingFormat.W3C_0x3);
        }

        try (Wc3BinInputStream stream = new Wc3BinInputStream(getFile("wc3data/W3E/war3map_v3.w3e"))) {
            Assert.assertSame(new W3E(stream).getFormat(), W3E.EncodingFormat.W3E_0xC);
        }

        try (Wc3BinInputStream stream = new Wc3BinInputStream(getFile("wc3data/W3R/war3map_v3.w3r"))) {
            Assert.assertSame(new W3R(stream).getFormat(), W3R.EncodingFormat.W3R_0x7);
        }
    }

    @Test
    public void classifyPopulatedVersion3Records() throws Exception {
        try (Wc3BinInputStream stream = new Wc3BinInputStream(getFile("wc3data/DOO/war3map_v3_filled.doo"))) {
            DOO doodads = new DOO(stream);
            Assert.assertSame(doodads.getFormat(), DOO.EncodingFormat.DOO_0xD);
            Assert.assertEquals(doodads.getDoods().size(), 16);
            for (int i = 0; i < doodads.getDoods().size(); i++) {
                DOO.Dood doodad = doodads.getDoods().get(i);
                Assert.assertEquals(doodad.getV13Color(), -1);
                Assert.assertEquals(doodad.getV13Id(), i);
                Assert.assertEquals(doodad.getV13UnknownA(), 0);
                Assert.assertEquals(doodad.getV13UnknownB(), 0);
                Assert.assertEquals(doodad.getV13UnknownC(), 0);
            }
        }

        try (Wc3BinInputStream stream = new Wc3BinInputStream(getFile("wc3data/W3C/war3map_v3_filled.w3c"))) {
            W3C cameras = new W3C(stream);
            Assert.assertEquals(cameras.getCameras().size(), 2);
            Assert.assertEquals(cameras.getCameras().get(0).getCineName().toString(), "Camera 001");
            Assert.assertFalse(cameras.getCameras().get(0).isFreeCamera());
            Assert.assertEquals(cameras.getCameras().get(1).getCineName().toString(), "free cam");
            Assert.assertTrue(cameras.getCameras().get(1).isFreeCamera());
            Assert.assertEquals(cameras.getCameras().get(1).getCameraType(), 1);
            Assert.assertEquals(cameras.getCameras().get(1).getAbsoluteZ().toFloat(), 4096F);
        }

        try (Wc3BinInputStream stream = new Wc3BinInputStream(getFile("wc3data/DOO_UNITS/war3mapUnits_v3_filled.doo"))) {
            DOO_UNITS units = new DOO_UNITS(stream);
            Assert.assertSame(units.getFormat(), DOO_UNITS.EncodingFormat.DOO_0xD);
            Assert.assertTrue(units.isOpaque());
        }

        try (Wc3BinInputStream stream = new Wc3BinInputStream(getFile("wc3data/W3R/war3map_v3_filled.w3r"))) {
            W3R regions = new W3R(stream);
            Assert.assertEquals(regions.getRects().size(), 1);
            Assert.assertEquals(regions.getRects().get(0).getName().toString(), "TestRegion");
            Assert.assertEquals(regions.getRects().get(0).getV7UnknownA(), 1);
            Assert.assertEquals(regions.getRects().get(0).getV7UnknownB(), 1);
        }

        try (Wc3BinInputStream stream = new Wc3BinInputStream(getFile("wc3data/IMP/war3map_v3_filled.imp"))) {
            IMP imports = new IMP(stream);
            Assert.assertEquals(imports.getObjs().size(), 1);
            Assert.assertEquals(imports.getObjs().get(0).getStdFlagRaw(), 0x15);
            Assert.assertNull(imports.getObjs().get(0).getStdFlag());
            Assert.assertEquals(imports.getObjs().get(0).getPath(), "1067db77-5b50-4a5c-8b62-8008c4529a51.mdl");
        }
    }
}
