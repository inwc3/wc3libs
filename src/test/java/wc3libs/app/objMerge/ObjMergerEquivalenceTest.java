package wc3libs.app.objMerge;

import net.moonlightflower.wc3libs.app.ObjMerger;
import net.moonlightflower.wc3libs.app.objMerge.BundledGameData;
import net.moonlightflower.wc3libs.app.objMerge.GameData;
import net.moonlightflower.wc3libs.app.objMerge.LayeredGameData;
import net.moonlightflower.wc3libs.app.objMerge.MemoryGameData;
import net.moonlightflower.wc3libs.app.objMerge.ObjType;
import net.moonlightflower.wc3libs.app.objMerge.ProfileGroup;
import net.moonlightflower.wc3libs.app.objMerge.SlkTable;
import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Merging must not change what any object ends up as. For every object an
 * input modifies, every field it modifies -- and, for a custom object, every
 * field there is -- resolves to the same value before and after.
 */
public class ObjMergerEquivalenceTest {
    private static final Logger log = LoggerFactory.getLogger(ObjMergerEquivalenceTest.class);

    @DataProvider
    public static Object[][] fixtures() {
        // the second value is how many objects the fixture is expected to merge at least
        return new Object[][]{
            {"wc3data/W3U/war3map.w3u", 0},
            {"wc3data/W3U/war3mapReforged.w3u", 400},
            {"wc3data/W3U/war3map_v3_lumber_bounty.w3u", 2},
            // its abilities are all unknown to 3.0.1 and are left as they are
            {"wc3data/W3A/war3map.w3a", 0},
            {"wc3data/W3A/war3map_cfde.w3a", 700},
            {"wc3data/W3A/war3map_format3.w3a", 40},
            {"wc3data/W3A/war3map_large_v3.w3a", 900},
            {"wc3data/W3A/threeLetterId.w3a", 1400},
            {"wc3data/W3B/war3map.w3b", 0},
            {"wc3data/W3H/war3map.w3h", 0},
            {"wc3data/W3Q/war3map.w3q", 0},
            {"wc3data/W3T/war3map.w3t", 0},
        };
    }

    @Test(dataProvider = "fixtures")
    public void fixtureMergesWithoutChangingAnyObject(String fixture, int minMerged) throws Exception {
        File file = Wc3LibTest.getFile(fixture);
        String extension = fixture.substring(fixture.lastIndexOf('.') + 1);
        ObjMod<?> objMod = ObjMod.createFromInFile(new File("war3map." + extension), file);
        ObjType type = ObjType.of(objMod);
        GameData base = BundledGameData.custom();

        ObjMerger merger = new ObjMerger(base);

        merger.add(objMod);

        ObjMerger.Result result = merger.merge();

        log.info("{}: {} {}", fixture, result.getReport(), result.getReport().getSkipped().stream().limit(5).toList());

        Map<ObjType, ObjMod<?>> before = new EnumMap<>(ObjType.class);

        before.put(type, objMod);

        assertEquivalent(base, before, outputData(base, result), residuals(before, result));
        assertUntouchedBaseDataKept(base, before, result);

        Assert.assertTrue(result.getReport().getMerged().getOrDefault(type, List.of()).size() >= minMerged,
            fixture + " merged too little: " + result.getReport());
    }

    @DataProvider
    public static Object[][] maps() {
        return new Object[][]{{"in.w3x"}, {"wc3data/Map/Forest_Defense_0.18w.w3x"}};
    }

    /** A whole map, through the archive: read, merged, written back, read again. */
    @Test(dataProvider = "maps")
    public void mapMergesInPlaceWithoutChangingAnyObject(String map) throws Exception {
        Path copy = Files.createTempDirectory("wc3libs-objmerge-map").resolve("map.w3x");

        Files.copy(Wc3LibTest.getFile(map).toPath(), copy);

        Set<String> otherFiles = new LinkedHashSet<>(new JMpqPort().listFiles(copy.toFile()).stream().map(File::toString).toList());
        Map<String, byte[]> beforeFiles = readMap(copy.toFile(), otherFiles);

        ObjMerger merger = ObjMerger.ofMap(copy.toFile());
        ObjMerger.Result result = merger.merge();

        log.info("{}: {} by type {} skipped {}", map, result.getReport(), result.getReport().getMerged().entrySet().stream().map(e -> e.getKey() + "=" + e.getValue().size()).toList(), result.getReport().getSkipped());

        result.writeToMap(copy.toFile());

        ObjMerger reread = ObjMerger.ofMap(copy.toFile());

        assertEquivalent(merger.getGameData(), merger.getObjMods(), reread.getGameData(), reread.getObjMods());

        Set<String> afterPaths = new LinkedHashSet<>(otherFiles);

        afterPaths.addAll(result.getFiles().keySet());
        afterPaths.addAll(result.getRemovedFiles());

        Map<String, byte[]> afterFiles = readMap(copy.toFile(), afterPaths);

        for (Map.Entry<String, byte[]> entry : result.getFiles().entrySet()) {
            Assert.assertEquals(afterFiles.get(entry.getKey().toLowerCase()), entry.getValue(), entry.getKey() + " in the map");
        }

        for (String removed : result.getRemovedFiles()) {
            Assert.assertFalse(afterFiles.containsKey(removed.toLowerCase()), removed + " should be gone from the map");
        }

        for (Map.Entry<String, byte[]> entry : beforeFiles.entrySet()) {
            String path = entry.getKey();

            if (result.getRemovedFiles().stream().anyMatch(p -> p.equalsIgnoreCase(path))) continue;
            if (result.getFiles().keySet().stream().anyMatch(p -> p.equalsIgnoreCase(path))) continue;
            if (path.equals("(listfile)") || path.equals("(attributes)")) continue;

            Assert.assertEquals(afterFiles.get(path), entry.getValue(), path + " must not change");
        }
    }

    private static Map<String, byte[]> readMap(File map, Set<String> paths) throws Exception {
        MpqPort.Out port = new JMpqPort.Out();

        for (String path : paths) port.add(new File(path));

        Map<String, byte[]> files = new LinkedHashMap<>();

        port.commit(map).getExports().forEach((file, segment) -> files.put(file.toString().toLowerCase(), segment.getOutBytes()));

        return files;
    }

    private static GameData outputData(GameData base, ObjMerger.Result result) {
        return new LayeredGameData(new MemoryGameData(result.getFiles()), base);
    }

    private static Map<ObjType, ObjMod<?>> residuals(Map<ObjType, ObjMod<?>> before, ObjMerger.Result result) throws Exception {
        Map<ObjType, ObjMod<?>> after = new EnumMap<>(ObjType.class);

        for (Map.Entry<ObjType, ObjMod<?>> entry : before.entrySet()) {
            String path = entry.getKey().getObjModPath().toString();
            byte[] bytes = result.getFiles().get(path);

            if (bytes != null) {
                ObjMod<?> residual = ObjMod.create(entry.getKey().getObjModClass());

                try (Wc3BinInputStream stream = new Wc3BinInputStream(new ByteArrayInputStream(bytes))) {
                    residual.read(stream);
                }

                after.put(entry.getKey(), residual);
            } else if (!result.getRemovedFiles().contains(path)) {
                after.put(entry.getKey(), entry.getValue());
            }
        }

        return after;
    }

    static void assertEquivalent(GameData beforeData, Map<ObjType, ObjMod<?>> beforeMods, GameData afterData,
                                 Map<ObjType, ObjMod<?>> afterMods) throws Exception {
        EffectiveObjData before = new EffectiveObjData(beforeData, beforeMods);
        EffectiveObjData after = new EffectiveObjData(afterData, afterMods);
        List<String> mismatches = new ArrayList<>();
        int checked = 0;

        for (Map.Entry<ObjType, ObjMod<?>> entry : beforeMods.entrySet()) {
            ObjType type = entry.getKey();

            if (!type.isMerged()) continue;

            Map<String, EffectiveObjData.Field> fields = EffectiveObjData.fields(beforeData, type);

            for (ObjMod.Obj obj : (List<ObjMod.Obj>) entry.getValue().getObjsList()) {
                String id = obj.getId().toString();
                Set<String> probes = new LinkedHashSet<>();

                for (ObjMod.Obj.Mod mod : obj.getMods()) {
                    int level = mod instanceof ObjMod.Obj.ExtendedMod ext ? ext.getLevel() : 0;

                    probes.add(mod.getId() + "@" + level);
                }

                if (obj.getNewId() != null) {
                    for (EffectiveObjData.Field field : fields.values()) {
                        for (int level : field.repeat() > 0 ? Arrays.asList(1, 2, 3, 4) : Collections.singletonList(0)) {
                            probes.add(field.id() + "@" + level);
                        }
                    }
                }

                for (String probe : probes) {
                    String fieldId = probe.substring(0, probe.indexOf('@'));
                    int level = Integer.parseInt(probe.substring(probe.indexOf('@') + 1));
                    EffectiveObjData.Field field = fields.get(fieldId);

                    if (field == null) continue;

                    String expected = before.value(type, id, field, level);
                    String actual = after.value(type, id, field, level);

                    checked++;

                    if (!EffectiveObjData.same(expected, actual)) {
                        mismatches.add(String.format("%s %s %s(%s) level %d: expected [%s] but was [%s]", type, id, fieldId,
                            field.field(), level, expected, actual));
                    }
                }
            }
        }

        log.info("compared {} field values, {} differ", checked, mismatches.size());

        Assert.assertTrue(mismatches.isEmpty(), mismatches.size() + " values changed, first ones:\n"
            + String.join("\n", mismatches.subList(0, Math.min(25, mismatches.size()))));
    }

    /** Rows of objects the input does not modify, and profile files' original content, stay byte for byte. */
    private static void assertUntouchedBaseDataKept(GameData base, Map<ObjType, ObjMod<?>> objMods, ObjMerger.Result result)
        throws Exception {
        Set<String> modified = new LinkedHashSet<>();

        for (ObjMod<?> objMod : objMods.values()) {
            for (ObjMod.Obj obj : objMod.getObjsList()) modified.add(obj.getId().toString());
        }

        for (ObjType type : ObjType.values()) {
            for (String path : type.getTables()) {
                byte[] written = result.getFiles().get(path);

                if (written == null) continue;

                SlkTable before = SlkTable.parse(base.read(path));
                SlkTable after = SlkTable.parse(written);

                Assert.assertEquals(after.getColumns(), before.getColumns(), path);

                for (SlkTable.Row row : before.getRows()) {
                    if (row.getId() == null || modified.contains(row.getId())) continue;

                    for (int col = 0; col < before.getColumns().size(); col++) {
                        Assert.assertEquals(after.getRow(row.getId()).getRaw(col), row.getRaw(col), path + " " + row.getId() + " column " + col);
                    }
                }
            }
        }

        for (ProfileGroup group : ProfileGroup.values()) {
            byte[] written = result.getFiles().get(group.getTarget());

            if (written == null) continue;

            byte[] original = base.read(group.getTarget());

            Assert.assertEquals(Arrays.copyOf(written, original.length), original, group.getTarget());
        }
    }
}
