package wc3libs.bin;

import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.bin.app.objMod.W3A;
import net.moonlightflower.wc3libs.bin.app.objMod.W3B;
import net.moonlightflower.wc3libs.bin.app.objMod.W3D;
import net.moonlightflower.wc3libs.bin.app.objMod.W3H;
import net.moonlightflower.wc3libs.bin.app.objMod.W3Q;
import net.moonlightflower.wc3libs.bin.app.objMod.W3T;
import net.moonlightflower.wc3libs.bin.app.objMod.W3U;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.app.War3Int;
import net.moonlightflower.wc3libs.dataTypes.app.War3Real;
import net.moonlightflower.wc3libs.dataTypes.app.War3String;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.MetaFieldId;
import net.moonlightflower.wc3libs.misc.ObjId;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ObjModCorrectnessTest {
    private static final ObjId OBJ_ID = ObjId.valueOf("AHtb");
    private static final MetaFieldId INT_FIELD = MetaFieldId.valueOf("amcs");
    private static final MetaFieldId REAL_FIELD = MetaFieldId.valueOf("aran");
    private static final MetaFieldId STRING_FIELD = MetaFieldId.valueOf("anam");

    private W3A roundTrip(W3A source) throws Exception {
        ByteArrayOutputStream outBytes = new ByteArrayOutputStream();

        try (Wc3BinOutputStream out = new Wc3BinOutputStream(outBytes)) {
            source.write(out, ObjMod.EncodingFormat.OBJ_0x3);
        }

        try (Wc3BinInputStream in = new Wc3BinInputStream(new ByteArrayInputStream(outBytes.toByteArray()))) {
            return new W3A(in);
        }
    }

    private ObjMod.Obj.Mod getOnlyMod(W3A.Abil abil, MetaFieldId fieldId) {
        List<ObjMod.Obj.Mod> mods = abil.getModsOfField(fieldId);

        Assert.assertEquals(mods.size(), 1, "expected exactly one mod for " + fieldId);

        return mods.get(0);
    }

    @Test
    public void detectedFormatRoundTripsByteIdenticallyForEverySupportedVersion() throws Exception {
        for (ObjMod.EncodingFormat format : Arrays.asList(
                ObjMod.EncodingFormat.OBJ_0x1,
                ObjMod.EncodingFormat.OBJ_0x2,
                ObjMod.EncodingFormat.OBJ_0x3)) {
            W3A source = new W3A();
            W3A.Abil abil = (W3A.Abil) source.addObj(OBJ_ID, null);
            abil.set(INT_FIELD, War3Int.valueOf(123));

            byte[] original = write(source, format);
            W3A parsed;
            try (Wc3BinInputStream in = new Wc3BinInputStream(new ByteArrayInputStream(original))) {
                parsed = new W3A(in);
            }

            Assert.assertEquals(parsed.getFormat(), format);
            Assert.assertEquals(write(parsed, parsed.getFormat()), original);
        }
    }

    @Test
    public void everyBaseAndSkinObjectFamilyRetainsV3MetadataWithDefaultWriter() throws Exception {
        List<Class<? extends ObjMod>> types = Arrays.asList(
            W3A.class, W3B.class, W3D.class, W3H.class, W3Q.class, W3T.class, W3U.class);

        for (Class<? extends ObjMod> type : types) {
            ObjMod<?> source = type.getConstructor().newInstance();
            ObjMod.Obj object = source.addObj(ObjId.valueOf("hfoo"), null);
            object.setUnknown(new int[]{101, 202});

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            try (Wc3BinOutputStream out = new Wc3BinOutputStream(bytes)) {
                source.write(out);
            }

            ObjMod<?> reparsed;
            try (Wc3BinInputStream in = new Wc3BinInputStream(new ByteArrayInputStream(bytes.toByteArray()))) {
                reparsed = type.getConstructor(Wc3BinInputStream.class).newInstance(in);
            }

            Assert.assertSame(reparsed.getFormat(), ObjMod.EncodingFormat.OBJ_0x3, type.getSimpleName());
            Assert.assertEquals(reparsed.getObj(ObjId.valueOf("hfoo")).getUnknown(),
                new int[]{101, 202}, type.getSimpleName());
        }
    }

    private byte[] write(W3A source, ObjMod.EncodingFormat format) throws Exception {
        ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
        try (Wc3BinOutputStream out = new Wc3BinOutputStream(outBytes)) {
            source.write(out, format);
        }
        return outBytes.toByteArray();
    }

    @Test
    public void setInfersIntValTypeAndRoundTrips() throws Exception {
        W3A w3a = new W3A();
        W3A.Abil abil = (W3A.Abil) w3a.addObj(OBJ_ID, null);

        abil.set(INT_FIELD, War3Int.valueOf(123));

        W3A out = roundTrip(w3a);
        W3A.Abil outAbil = out.getObj(OBJ_ID);

        Assert.assertNotNull(outAbil);

        ObjMod.Obj.Mod mod = getOnlyMod(outAbil, INT_FIELD);
        DataType val = mod.getVal();

        Assert.assertEquals(mod.getValType(), ObjMod.ValType.INT);
        Assert.assertTrue(val instanceof War3Int);
        Assert.assertEquals(((War3Int) val).toInt(), 123);
    }

    @Test
    public void explicitValTypePreservesUnreal() throws Exception {
        W3A w3a = new W3A();
        W3A.Abil abil = (W3A.Abil) w3a.addObj(OBJ_ID, null);

        abil.set(REAL_FIELD, War3Real.valueOf(1.5f), ObjMod.ValType.UNREAL);

        W3A out = roundTrip(w3a);
        W3A.Abil outAbil = out.getObj(OBJ_ID);

        Assert.assertNotNull(outAbil);

        ObjMod.Obj.Mod mod = getOnlyMod(outAbil, REAL_FIELD);
        DataType val = mod.getVal();

        Assert.assertEquals(mod.getValType(), ObjMod.ValType.UNREAL);
        Assert.assertTrue(val instanceof War3Real);
        Assert.assertEquals(((War3Real) val).toFloat(), 1.5f, 0.00001f);
    }

    @Test
    public void nullValueDefaultsToStringAndRoundTrips() throws Exception {
        W3A w3a = new W3A();
        W3A.Abil abil = (W3A.Abil) w3a.addObj(OBJ_ID, null);

        abil.set(STRING_FIELD, null);

        W3A out = roundTrip(w3a);
        W3A.Abil outAbil = out.getObj(OBJ_ID);

        Assert.assertNotNull(outAbil);

        ObjMod.Obj.Mod mod = getOnlyMod(outAbil, STRING_FIELD);

        Assert.assertEquals(mod.getValType(), ObjMod.ValType.STRING);
        Assert.assertEquals(String.valueOf(mod.getVal()), "");
    }

    @Test
    public void mergeAddsMissingFields() throws IOException {
        W3A.Abil target = new W3A.Abil(OBJ_ID, null);
        target.set(INT_FIELD, War3Int.valueOf(1));

        W3A.Abil other = new W3A.Abil(OBJ_ID, null);
        other.set(REAL_FIELD, War3Real.valueOf(2.5f));

        target.merge(other);

        Assert.assertEquals(((War3Int) target.get(INT_FIELD)).toInt(), 1);
        Assert.assertEquals(((War3Real) target.get(REAL_FIELD)).toFloat(), 2.5f, 0.00001f);
    }

    @Test
    public void copyPreservesV3MetadataWithoutAliasingMutableRecords() {
        W3A source = new W3A();
        W3A.Abil sourceAbil = (W3A.Abil) source.addObj(OBJ_ID, null);
        sourceAbil.setUnknown(new int[] { 11, 22, 33 });
        sourceAbil.set(STRING_FIELD, War3String.valueOf("source"));
        getOnlyMod(sourceAbil, STRING_FIELD).setEndToken(Id.valueOf("ABCD"));

        W3A copy = source.copy();
        W3A.Abil copyAbil = copy.getObj(OBJ_ID);

        Assert.assertEquals(copyAbil.getUnknown(), new int[] { 11, 22, 33 });
        copyAbil.setUnknown(new int[] { 44 });
        ((War3String) getOnlyMod(copyAbil, STRING_FIELD).getVal()).set_val("copy");
        getOnlyMod(copyAbil, STRING_FIELD).getEndToken().set_val("WXYZ");
        copyAbil.getId().set_val("A001");

        Assert.assertEquals(sourceAbil.getUnknown(), new int[] { 11, 22, 33 });
        Assert.assertEquals(sourceAbil.get(STRING_FIELD).toString(), "source");
        Assert.assertEquals(getOnlyMod(sourceAbil, STRING_FIELD).getEndToken().toString(), "ABCD");
        Assert.assertEquals(sourceAbil.getId().toString(), OBJ_ID.toString());
    }

    @Test
    public void mergePromotesFormatAndPreservesV3Metadata() throws Exception {
        W3A target = new W3A();
        target.setFormat(ObjMod.EncodingFormat.OBJ_0x2);

        W3A source = new W3A();
        source.setFormat(ObjMod.EncodingFormat.OBJ_0x3);
        W3A.Abil sourceAbil = (W3A.Abil) source.addObj(OBJ_ID, null);
        sourceAbil.setUnknown(new int[] { 101, 202 });
        sourceAbil.set(INT_FIELD, War3Int.valueOf(7));

        target.merge(source);

        Assert.assertSame(target.getFormat(), ObjMod.EncodingFormat.OBJ_0x3);
        W3A reparsed;
        byte[] bytes = write(target, target.getFormat());
        try (Wc3BinInputStream in = new Wc3BinInputStream(new ByteArrayInputStream(bytes))) {
            reparsed = new W3A(in);
        }

        Assert.assertEquals(reparsed.getObj(OBJ_ID).getUnknown(), new int[] { 101, 202 });
    }
}
