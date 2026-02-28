package wc3libs.bin;

import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.bin.app.objMod.W3A;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.app.War3Int;
import net.moonlightflower.wc3libs.dataTypes.app.War3Real;
import net.moonlightflower.wc3libs.misc.MetaFieldId;
import net.moonlightflower.wc3libs.misc.ObjId;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
}
