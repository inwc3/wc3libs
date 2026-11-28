package wc3libs.app.objMerge;

import net.moonlightflower.wc3libs.app.ObjMerger;
import net.moonlightflower.wc3libs.app.objMerge.BundledGameData;
import net.moonlightflower.wc3libs.app.objMerge.GameData;
import net.moonlightflower.wc3libs.app.objMerge.LayeredGameData;
import net.moonlightflower.wc3libs.app.objMerge.MemoryGameData;
import net.moonlightflower.wc3libs.app.objMerge.ObjType;
import net.moonlightflower.wc3libs.app.objMerge.SlkTable;
import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.app.War3Int;
import net.moonlightflower.wc3libs.dataTypes.app.War3Real;
import net.moonlightflower.wc3libs.dataTypes.app.War3String;
import net.moonlightflower.wc3libs.misc.MetaFieldId;
import net.moonlightflower.wc3libs.misc.ObjId;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Random modifications of every field of every merged object type, values
 * the formats cannot hold included, must merge without changing any object.
 */
public class ObjMergerFuzzTest {
    private static final String[] STRINGS = {"abc", "a,b", "x, y z", "", "TRIGSTR_001", "q\"uote", "semi;colon", "AHbz,AHab",
        " lead", "Units\\Human\\Footman\\Footman.mdl"};

    @DataProvider
    public static Object[][] seeds() {
        return new Object[][]{{1L}, {2L}, {3L}, {4L}, {5L}, {6L}, {7L}, {8L}};
    }

    @Test(dataProvider = "seeds")
    public void randomModificationsChangeNoObject(long seed) throws Exception {
        Random random = new Random(seed);
        GameData base = BundledGameData.custom();
        Map<ObjType, ObjMod<?>> objMods = new EnumMap<>(ObjType.class);
        String[] prefixes = {"x", "y", "z", "w", "v", "u"};
        int prefix = 0;

        for (ObjType type : ObjType.values()) {
            if (!type.isMerged()) continue;

            List<EffectiveObjData.Field> fields = new ArrayList<>(EffectiveObjData.fields(base, type).values());
            List<String> baseIds = new ArrayList<>();

            for (SlkTable.Row row : SlkTable.parse(base.read(type.getPrimaryTable())).getRows()) {
                if (row.getId() != null && row.getId().length() == 4) baseIds.add(row.getId());
            }

            ObjMod<?> objMod = ObjMod.create(type.getObjModClass());
            boolean extended = type == ObjType.ABILITY || type == ObjType.UPGRADE;
            String idPrefix = prefixes[prefix++];

            for (int i = 0; i < 40; i++) {
                boolean custom = i < 30;
                String id = custom ? String.format("%s%03d", idPrefix, i) : baseIds.get(random.nextInt(baseIds.size()));
                ObjMod.Obj obj = objMod.addObj(ObjId.valueOf(id), custom ? ObjId.valueOf(baseIds.get(random.nextInt(baseIds.size()))) : null);

                for (int m = 0; m < 25; m++) {
                    EffectiveObjData.Field field = fields.get(random.nextInt(fields.size()));
                    ObjMod.ValType valType = valType(field.type());
                    DataType val = switch (valType) {
                        case INT -> War3Int.valueOf(random.nextInt(2000) - 100);
                        case REAL, UNREAL -> War3Real.valueOf(random.nextInt(4000) / 8F);
                        default -> War3String.valueOf(STRINGS[random.nextInt(STRINGS.length)]);
                    };

                    if (extended) {
                        int level = field.repeat() > 0 ? 1 + random.nextInt(6) : 0;

                        obj.addMod(new ObjMod.Obj.ExtendedMod(MetaFieldId.valueOf(field.id()), valType, val, level, field.data()));
                    } else {
                        obj.addMod(new ObjMod.Obj.Mod(MetaFieldId.valueOf(field.id()), valType, val));
                    }
                }
            }

            objMods.put(type, objMod);
        }

        ObjMerger merger = new ObjMerger(base);

        objMods.values().forEach(merger::add);

        ObjMerger.Result result = merger.merge();

        // Random values touch techtree lists and keys the base lacks, which keep whole objects behind.
        Assert.assertTrue(result.getReport().getMovedModCount() > 300, result.getReport().toString());
        Assert.assertTrue(result.getReport().getKeptModCount() > 100, "the random values should include unwritable ones");

        Map<ObjType, ObjMod<?>> residuals = new EnumMap<>(ObjType.class);

        for (ObjType type : objMods.keySet()) {
            byte[] bytes = result.getFiles().get(type.getObjModPath().toString());

            if (bytes == null) continue;

            ObjMod<?> residual = ObjMod.create(type.getObjModClass());

            try (Wc3BinInputStream stream = new Wc3BinInputStream(new ByteArrayInputStream(bytes))) {
                residual.read(stream);
            }

            residuals.put(type, residual);
        }

        ObjMergerEquivalenceTest.assertEquivalent(base, objMods, new LayeredGameData(new MemoryGameData(result.getFiles()), base),
            residuals);
    }

    private static ObjMod.ValType valType(String metaType) {
        switch (metaType) {
            case "int":
            case "bool":
                return ObjMod.ValType.INT;
            case "real":
                return ObjMod.ValType.REAL;
            case "unreal":
                return ObjMod.ValType.UNREAL;
            default:
                return ObjMod.ValType.STRING;
        }
    }
}
