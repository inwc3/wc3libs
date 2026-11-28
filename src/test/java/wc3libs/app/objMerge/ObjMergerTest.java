package wc3libs.app.objMerge;

import net.moonlightflower.wc3libs.app.ObjMerger;
import net.moonlightflower.wc3libs.app.objMerge.BundledGameData;
import net.moonlightflower.wc3libs.app.objMerge.GameData;
import net.moonlightflower.wc3libs.app.objMerge.LayeredGameData;
import net.moonlightflower.wc3libs.app.objMerge.MemoryGameData;
import net.moonlightflower.wc3libs.app.objMerge.ObjType;
import net.moonlightflower.wc3libs.app.objMerge.ProfileFile;
import net.moonlightflower.wc3libs.app.objMerge.SlkTable;
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
import net.moonlightflower.wc3libs.misc.MetaFieldId;
import net.moonlightflower.wc3libs.misc.ObjId;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ObjMergerTest {
    private static final String UNIT_DATA = "Units\\UnitData.slk";
    private static final String UNIT_BALANCE = "Units\\UnitBalance.slk";
    private static final String UNIT_UI = "Units\\UnitUI.slk";
    private static final String UNIT_WEAPONS = "Units\\UnitWeapons.slk";
    private static final String UNIT_ABILITIES = "Units\\UnitAbilities.slk";
    private static final String ABILITY_DATA = "Units\\AbilityData.slk";
    private static final String UNIT_FUNC_TARGET = "Units\\CampaignUnitFunc.txt";
    private static final String UNIT_SKIN_TARGET = "Units\\UnitSkin.txt";

    // --- building object modifications ---

    static ObjMod.Obj.Mod mod(String field, int val) {
        return new ObjMod.Obj.Mod(MetaFieldId.valueOf(field), ObjMod.ValType.INT, War3Int.valueOf(val));
    }

    static ObjMod.Obj.Mod mod(String field, float val) {
        return new ObjMod.Obj.Mod(MetaFieldId.valueOf(field), ObjMod.ValType.UNREAL, War3Real.valueOf(val));
    }

    static ObjMod.Obj.Mod mod(String field, String val) {
        return new ObjMod.Obj.Mod(MetaFieldId.valueOf(field), ObjMod.ValType.STRING, War3String.valueOf(val));
    }

    static ObjMod.Obj.Mod lvl(String field, int level, int dataPt, int val) {
        return new ObjMod.Obj.ExtendedMod(MetaFieldId.valueOf(field), ObjMod.ValType.INT, War3Int.valueOf(val), level, dataPt);
    }

    static ObjMod.Obj.Mod lvl(String field, int level, int dataPt, String val) {
        return new ObjMod.Obj.ExtendedMod(MetaFieldId.valueOf(field), ObjMod.ValType.STRING, War3String.valueOf(val), level, dataPt);
    }

    static ObjMod.Obj obj(ObjMod<?> objMod, String id, String baseId, ObjMod.Obj.Mod... mods) {
        ObjMod.Obj obj = objMod.addObj(ObjId.valueOf(id), baseId == null ? null : ObjId.valueOf(baseId));

        for (ObjMod.Obj.Mod mod : mods) obj.addMod(mod);

        return obj;
    }

    // --- reading results ---

    static GameData base() throws Exception {
        return BundledGameData.custom();
    }

    static ObjMerger.Result merge(ObjMod<?>... objMods) throws Exception {
        return merge(base(), objMods);
    }

    static ObjMerger.Result merge(GameData data, ObjMod<?>... objMods) throws Exception {
        ObjMerger merger = new ObjMerger(data);

        for (ObjMod<?> objMod : objMods) merger.add(objMod);

        return merger.merge();
    }

    static SlkTable table(ObjMerger.Result result, String path) throws Exception {
        byte[] bytes = result.getFiles().get(path);

        return SlkTable.parse(bytes != null ? bytes : base().read(path));
    }

    static ProfileFile.Section section(ObjMerger.Result result, String path, String id) {
        byte[] bytes = result.getFiles().get(path);

        Assert.assertNotNull(bytes, path + " was not written");

        return ProfileFile.parse(bytes).getSection(id);
    }

    static <T extends ObjMod<?>> T residual(ObjMerger.Result result, Class<T> clazz, String path) throws Exception {
        byte[] bytes = result.getFiles().get(path);

        if (bytes == null) return null;

        T objMod = ObjMod.create(clazz);

        try (Wc3BinInputStream stream = new Wc3BinInputStream(new ByteArrayInputStream(bytes))) {
            objMod.read(stream);
        }

        return objMod;
    }

    static List<String> fieldsOf(ObjMod.Obj obj) {
        return obj.getMods().stream().map(mod -> mod.getId().toString()).toList();
    }

    static DataType valOf(ObjMod.Obj obj, String field) {
        return obj.get(MetaFieldId.valueOf(field));
    }

    // --- custom objects ---

    @Test
    public void customUnitIsACopyOfItsBaseInEveryTable() throws Exception {
        W3U w3u = new W3U();

        obj(w3u, "h000", "hfoo", mod("uhpm", 1234), mod("umvs", 300), mod("uabi", "Aatk,AHbz"));

        ObjMerger.Result result = merge(w3u);

        for (String path : Arrays.asList(UNIT_DATA, UNIT_BALANCE, UNIT_UI, UNIT_WEAPONS, UNIT_ABILITIES)) {
            SlkTable before = SlkTable.parse(base().read(path));
            SlkTable after = table(result, path);
            SlkTable.Row hfoo = before.getRow("hfoo");
            SlkTable.Row h000 = after.getRow("h000");

            Assert.assertNotNull(h000, "h000 missing from " + path);
            Assert.assertEquals(after.getColumns(), before.getColumns());

            for (int col = 1; col < before.getColumns().size(); col++) {
                String name = before.getColumns().get(col);

                if (name == null || Arrays.asList("HP", "spd", "abilList").contains(name)) continue;

                Assert.assertEquals(h000.getRaw(col), hfoo.getRaw(col), path + " column " + name);
            }
        }

        Assert.assertEquals(table(result, UNIT_BALANCE).getRow("h000").getRaw(table(result, UNIT_BALANCE).getColumnIndex("HP")), "1234");
        Assert.assertEquals(table(result, UNIT_BALANCE).getRow("h000").get("spd"), "300");
        Assert.assertEquals(table(result, UNIT_ABILITIES).getRow("h000").getRaw(table(result, UNIT_ABILITIES).getColumnIndex("abilList")),
            "\"Aatk,AHbz\"");

        Assert.assertTrue(result.getRemovedFiles().contains(W3U.GAME_PATH.toString()), "the emptied w3u is to be removed");
        Assert.assertEquals(result.getReport().getMerged().get(ObjType.UNIT), Collections.singletonList("h000"));
    }

    @Test
    public void baseRowsTheMapDidNotTouchStayAsTheyWere() throws Exception {
        W3U w3u = new W3U();

        obj(w3u, "h000", "hfoo", mod("uhpm", 1));
        obj(w3u, "hkni", null, mod("uhpm", 2));

        SlkTable before = SlkTable.parse(base().read(UNIT_BALANCE));
        SlkTable after = table(merge(w3u), UNIT_BALANCE);
        int hp = before.getColumnIndex("HP");

        Assert.assertEquals(after.getRows().size(), before.getRows().size() + 1);

        for (SlkTable.Row row : before.getRows()) {
            if (row.getId() == null) continue;

            SlkTable.Row now = after.getRow(row.getId());

            for (int col = 0; col < before.getColumns().size(); col++) {
                if (row.getId().equals("hkni") && col == hp) continue;

                Assert.assertEquals(now.getRaw(col), row.getRaw(col), row.getId() + " column " + col);
            }
        }

        Assert.assertEquals(after.getRow("hkni").getRaw(hp), "2");
    }

    @Test
    public void customUnitGetsItsBasesProfileSectionsGroupByGroup() throws Exception {
        W3U w3u = new W3U();

        obj(w3u, "h000", "hfoo", mod("unam", "Shieldbearer"), mod("ubpy", 2), mod("umdl", "units\\custom\\Shield"));

        ObjMerger.Result result = merge(w3u);

        ProfileFile.Section func = section(result, UNIT_FUNC_TARGET, "h000");
        ProfileFile.Section skin = section(result, UNIT_SKIN_TARGET, "h000");

        // humanunitfunc/strings keys land in the func target, unitskin keys in the skin target
        Assert.assertEquals(func.get("Name").getRawValue(), "Shieldbearer");
        Assert.assertEquals(func.get("Buttonpos").getRawValue(), "0,2", "only the y position changes");
        Assert.assertEquals(func.get("Specialart").getRawValue(),
            "Objects\\Spawnmodels\\Human\\HumanLargeDeathExplode\\HumanLargeDeathExplode.mdl");
        Assert.assertNull(func.get("file"));

        Assert.assertEquals(skin.get("file").getRawValue(), "units\\custom\\Shield");
        Assert.assertEquals(skin.get("Art").getRawValue(), "ReplaceableTextures\\CommandButtons\\BTNFootman.blp");
        Assert.assertNull(skin.get("Name"));

        Assert.assertTrue(result.getRemovedFiles().contains(W3U.GAME_PATH.toString()));
    }

    @Test
    public void targetFilesKeepTheirOriginalContentFirst() throws Exception {
        W3U w3u = new W3U();

        obj(w3u, "h000", "hfoo", mod("unam", "x"));

        ObjMerger.Result result = merge(w3u);

        for (String path : Arrays.asList(UNIT_FUNC_TARGET, UNIT_SKIN_TARGET)) {
            byte[] original = base().read(path);
            byte[] written = result.getFiles().get(path);

            Assert.assertEquals(Arrays.copyOf(written, original.length), original, path + " must start with its original bytes");

            ProfileFile reparsed = ProfileFile.parse(written);

            Assert.assertTrue(reparsed.getSections().keySet().containsAll(ProfileFile.parse(original).getSections().keySet()));
        }
    }

    @Test
    public void modifiedKeysDropTheirQualifiedVariants() throws Exception {
        W3U w3u = new W3U();

        obj(w3u, "h000", "hfoo", mod("usca", 2.5F));

        ProfileFile.Section skin = section(merge(w3u), UNIT_SKIN_TARGET, "h000");
        ProfileFile.Section hfoo = ProfileFile.parse(base().read(UNIT_SKIN_TARGET)).getSection("hfoo");

        Assert.assertTrue(hfoo.getEntries().keySet().stream().anyMatch(key -> key.startsWith("modelscale:")),
            "hfoo is expected to carry qualified modelScale keys");
        Assert.assertEquals(skin.get("modelScale").getRawValue(), "2.5");
        Assert.assertTrue(skin.getEntries().keySet().stream().noneMatch(key -> key.startsWith("modelscale:")));
        Assert.assertNotNull(skin.get("file"), "unrelated keys are still copied");
    }

    @Test
    public void emptiedListElementsStayInTheResidual() throws Exception {
        W3U w3u = new W3U();

        // Castle Fight's Elemental of Lightning: a Water Elemental emptied of its first missile art fired the Water
        // Elemental's missile once the empty element moved into the profile.
        obj(w3u, "h000", "hwat", mod("ua1m", ""), mod("unam", "Elemental Of Lightning"));

        ObjMerger.Result result = merge(w3u);
        ObjMod.Obj h000 = residual(result, W3U.class, W3U.GAME_PATH.toString()).getObj(ObjId.valueOf("h000"));

        Assert.assertTrue(fieldsOf(h000).contains("ua1m"), fieldsOf(h000).toString());
        Assert.assertEquals(valOf(h000, "ua1m").toString(), "");
    }

    /**
     * Castle Fight's Display Range: an ability based on Berserk (Requires=Robk) that sets no requirement asked for
     * the Berserker upgrade only once its copied profile section carried Berserk's. It stays in the residual, where
     * the game derives it from Berserk as before.
     */
    @Test
    public void customObjectsInheritingARequirementStayInTheResidual() throws Exception {
        W3A w3a = new W3A();

        obj(w3a, "A000", "Absk", mod("anam", "Check Range"));
        obj(w3a, "A001", "Absk", mod("anam", "Rage"), mod("areq", ""));

        ObjMerger.Result result = merge(w3a);
        W3A residual = residual(result, W3A.class, W3A.GAME_PATH.toString());
        ObjMod.Obj a000 = residual.getObj(ObjId.valueOf("A000"));

        Assert.assertNotNull(a000, "A000 stays in the residual");
        Assert.assertEquals(a000.getBaseId().toString(), "Absk", "as a custom object derived from Berserk");
        Assert.assertEquals(fieldsOf(a000), Collections.singletonList("anam"));
        Assert.assertFalse(table(result, ABILITY_DATA).containsRow("A000"));

        // Setting the requirement, even to nothing, changes the techtree, which no native reports: it stays too.
        Assert.assertFalse(table(result, ABILITY_DATA).containsRow("A001"));
        Assert.assertEquals(residual.getObj(ObjId.valueOf("A001")).getBaseId().toString(), "Absk");
    }

    @Test
    public void listFieldsAreWrittenElementWise() throws Exception {
        W3U w3u = new W3U();

        obj(w3u, "h000", "hfoo", mod("utub", "Strong, slow and loyal."));

        ProfileFile.Section func = section(merge(w3u), UNIT_FUNC_TARGET, "h000");

        Assert.assertEquals(ProfileFile.tokenize(func.get("Ubertip").getRawValue()),
            Collections.singletonList("Strong, slow and loyal."));
    }

    @Test
    public void profileModsCanStayInTheObjectModificationFile() throws Exception {
        W3U w3u = new W3U();

        obj(w3u, "h000", "hfoo", mod("uhpm", 5), mod("unam", "x"));

        ObjMerger merger = new ObjMerger(base());

        merger.setMoveProfileMods(false);
        merger.add(w3u);

        ObjMerger.Result result = merger.merge();
        W3U residual = residual(result, W3U.class, W3U.GAME_PATH.toString());
        ObjMod.Obj h000 = residual.getObj(ObjId.valueOf("h000"));

        Assert.assertNull(h000.getNewId(), "the residual modifies h000 as an existing object");
        Assert.assertEquals(fieldsOf(h000), Collections.singletonList("unam"));
        Assert.assertEquals(section(result, UNIT_FUNC_TARGET, "h000").get("Name").getRawValue(), "Footman");
    }

    // --- modified base objects ---

    @Test
    public void modifiedBaseObjectsKeepTheirProfileModsInTheResidual() throws Exception {
        W3U w3u = new W3U();

        obj(w3u, "hfoo", null, mod("uhpm", 999), mod("unam", "Soldier"));

        ObjMerger.Result result = merge(w3u);

        Assert.assertEquals(table(result, UNIT_BALANCE).getRow("hfoo").get("HP"), "999");
        Assert.assertFalse(result.getFiles().containsKey(UNIT_FUNC_TARGET), "no profile file is touched");
        Assert.assertFalse(result.getFiles().containsKey(UNIT_DATA), "tables without changes are not rewritten");

        ObjMod.Obj hfoo = residual(result, W3U.class, W3U.GAME_PATH.toString()).getObj(ObjId.valueOf("hfoo"));

        Assert.assertEquals(fieldsOf(hfoo), Collections.singletonList("unam"));
        Assert.assertEquals(valOf(hfoo, "unam").toString(), "Soldier");
    }

    /** A custom object is derived from its base object as the game data has it, not as the map modifies it. */
    @Test
    public void customObjectsCopyTheUnmodifiedBase() throws Exception {
        W3U w3u = new W3U();

        obj(w3u, "hfoo", null, mod("uhpm", 999), mod("udef", 9));
        obj(w3u, "h000", "hfoo", mod("udef", 1));

        SlkTable balance = table(merge(w3u), UNIT_BALANCE);
        String baseHp = SlkTable.parse(base().read(UNIT_BALANCE)).getRow("hfoo").get("HP");

        Assert.assertEquals(balance.getRow("hfoo").get("HP"), "999");
        Assert.assertEquals(balance.getRow("h000").get("HP"), baseHp);
        Assert.assertEquals(balance.getRow("h000").get("def"), "1");
        Assert.assertEquals(balance.getRow("hfoo").get("def"), "9");
    }

    // --- what stays behind ---

    /**
     * The game reads four levels of a field from a map's tables (measured on
     * the 3.0.1 client): DataA5 and DataA6 exist in its own AbilityData.slk,
     * but values a map puts there do not arrive. Levels above four stay in the
     * residual.
     */
    @Test
    public void abilityLevelsBeyondFourStayInTheResidual() throws Exception {
        W3A w3a = new W3A();

        obj(w3a, "A000", "AHbz", lvl("alev", 0, 0, 7), lvl("Hbz1", 1, 1, 11), lvl("Hbz1", 4, 1, 44), lvl("Hbz1", 5, 1, 55),
            lvl("Hbz1", 7, 1, 77), lvl("atp1", 7, 0, "Level seven"), lvl("atp1", 2, 0, "Level two"));

        ObjMerger.Result result = merge(w3a);
        SlkTable.Row row = table(result, ABILITY_DATA).getRow("A000");

        Assert.assertEquals(row.get("code"), "AHbz");
        Assert.assertEquals(row.get("levels"), "7");
        Assert.assertEquals(row.get("DataA1"), "11");
        Assert.assertEquals(row.get("DataA4"), "44");
        Assert.assertNotEquals(row.get("DataA5"), "55");

        ObjMod.Obj a000 = residual(result, W3A.class, W3A.GAME_PATH.toString()).getObj(ObjId.valueOf("A000"));

        Assert.assertNull(a000.getNewId(), "the residual modifies A000 as an existing object");
        Assert.assertEquals(fieldsOf(a000), Arrays.asList("Hbz1", "Hbz1"));
        Assert.assertEquals(((ObjMod.Obj.ExtendedMod) a000.getMods().get(0)).getLevel(), 5);
        Assert.assertEquals(((ObjMod.Obj.ExtendedMod) a000.getMods().get(1)).getLevel(), 7);

        List<String> tips = ProfileFile.tokenize(section(result, "Units\\CampaignAbilityFunc.txt", "A000").get("Tip").getRawValue());

        Assert.assertEquals(tips.get(1), "Level two");
        Assert.assertEquals(tips.get(6), "Level seven");
        Assert.assertTrue(tips.get(0).startsWith("Blizzard"), "untouched levels keep the base tooltip");
    }

    /**
     * With UseNewDefaultLevelCap (alcp) set, as every stock ability with more
     * than four levels has, the game reads levels five and six from the table
     * too (measured on the 3.0.1 client); seven and up still stay behind, since
     * columns for them crash the game. The flag counts wherever the object
     * sets it in its list.
     */
    @Test
    public void theNewLevelCapLetsLevelsFiveAndSixIntoTheTable() throws Exception {
        W3A w3a = new W3A();

        obj(w3a, "A000", "AHbz", lvl("alev", 0, 0, 7), lvl("Hbz1", 4, 1, 44), lvl("Hbz1", 5, 1, 55), lvl("Hbz1", 6, 1, 66),
            lvl("Hbz1", 7, 1, 77), lvl("alcp", 0, 0, 1));
        // A stock ability with five levels already has the flag.
        obj(w3a, "A001", "AUla", lvl("Ucs1", 5, 1, 99));

        ObjMerger.Result result = merge(w3a);
        SlkTable abilities = table(result, ABILITY_DATA);

        Assert.assertEquals(abilities.getRow("A000").get(ObjMerger.NEW_LEVEL_CAP_COLUMN), "1");
        Assert.assertEquals(abilities.getRow("A000").get("DataA4"), "44");
        Assert.assertEquals(abilities.getRow("A000").get("DataA5"), "55");
        Assert.assertEquals(abilities.getRow("A000").get("DataA6"), "66");
        Assert.assertEquals(abilities.getRow("A001").get("DataA5"), "99");

        W3A residual = residual(result, W3A.class, W3A.GAME_PATH.toString());
        ObjMod.Obj a000 = residual.getObj(ObjId.valueOf("A000"));

        Assert.assertEquals(fieldsOf(a000), Collections.singletonList("Hbz1"));
        Assert.assertEquals(((ObjMod.Obj.ExtendedMod) a000.getMods().get(0)).getLevel(), 7);
        Assert.assertNull(residual.getObj(ObjId.valueOf("A001")));
    }

    /**
     * Emptied table values stay in the residual, where "" reads as nothing:
     * a table's placeholder does not read the same everywhere (an emptied
     * targs2 written as "_" allowed a dozen target kinds), and an empty
     * UnitAbilities "auto" crashes the game on load (measured on the 3.0.1
     * client). The table keeps the base's cells.
     */
    @Test
    public void emptiedTableValuesStayInTheResidual() throws Exception {
        W3U w3u = new W3U();

        obj(w3u, "h000", "hfoo", mod("udaa", ""), mod("utyp", ""), mod("urac", ""), mod("uhpm", 5));

        ObjMerger.Result result = merge(w3u);

        Assert.assertEquals(table(result, UNIT_ABILITIES).getRow("h000").get("auto"),
            table(result, UNIT_ABILITIES).getRow("hfoo").get("auto"));
        Assert.assertEquals(table(result, UNIT_BALANCE).getRow("h000").get("HP"), "5");

        ObjMod.Obj h000 = residual(result, W3U.class, W3U.GAME_PATH.toString()).getObj(ObjId.valueOf("h000"));

        Assert.assertEquals(fieldsOf(h000), Arrays.asList("udaa", "utyp", "urac"));
    }

    /**
     * An emptied unitShadow in the skin file showed the default shadow in game,
     * where the object modification file reads "" as no shadow. Emptied
     * profile values stay in the residual; the skin keeps the base's value.
     */
    @Test
    public void emptiedProfileValuesStayInTheResidual() throws Exception {
        W3U w3u = new W3U();

        obj(w3u, "h000", "hfoo", mod("ushu", ""), mod("ushb", ""), mod("umdl", "none.mdl"));

        ObjMerger.Result result = merge(w3u);
        ProfileFile.Section skin = section(result, UNIT_SKIN_TARGET, "h000");

        Assert.assertEquals(skin.get("file").getRawValue(), "none.mdl");
        Assert.assertEquals(skin.get("unitShadow").getRawValue(), "Shadow", "the skin keeps the base's shadow");

        ObjMod.Obj h000 = residual(result, W3U.class, W3U.GAME_PATH.toString()).getObj(ObjId.valueOf("h000"));

        Assert.assertEquals(fieldsOf(h000), Arrays.asList("ushu", "ushb"));
        Assert.assertEquals(valOf(h000, "ushu").toString(), "");
    }

    /**
     * 3.x skin files repeat UnitUI columns (special, campaign, teamColor, ...).
     * A custom object gets the value in both; a base object's change stays in
     * the residual, since the game's own skin copy would contradict the table.
     */
    @Test
    public void columnsTheSkinRepeatsAgreeWithTheTable() throws Exception {
        W3U w3u = new W3U();

        obj(w3u, "h000", "hfoo", mod("uspe", 1));
        obj(w3u, "hfoo", null, mod("uspe", 1));

        ObjMerger.Result result = merge(w3u);

        Assert.assertEquals(table(result, UNIT_UI).getRow("h000").get("special"), "1");
        Assert.assertEquals(section(result, UNIT_SKIN_TARGET, "h000").get("special").getRawValue(), "1");
        Assert.assertEquals(table(result, UNIT_UI).getRow("hfoo").get("special"), base().read(UNIT_UI) == null ? null
            : SlkTable.parse(base().read(UNIT_UI)).getRow("hfoo").get("special"));

        ObjMod.Obj hfoo = residual(result, W3U.class, W3U.GAME_PATH.toString()).getObj(ObjId.valueOf("hfoo"));

        Assert.assertEquals(fieldsOf(hfoo), Collections.singletonList("uspe"));
    }

    @Test
    public void unwritableValuesStayInTheResidual() throws Exception {
        W3U w3u = new W3U();

        obj(w3u, "h000", "hfoo", mod("unam", "TRIGSTR_012"), mod("utip", "say \"hi\""), mod("uabi", "line\nbreak"),
            mod("uhpm", 5));

        ObjMerger.Result result = merge(w3u);
        ObjMod.Obj h000 = residual(result, W3U.class, W3U.GAME_PATH.toString()).getObj(ObjId.valueOf("h000"));

        Assert.assertEquals(fieldsOf(h000), Arrays.asList("unam", "utip", "uabi"));
        Assert.assertEquals(table(result, UNIT_BALANCE).getRow("h000").get("HP"), "5");
        Assert.assertEquals(section(result, UNIT_FUNC_TARGET, "h000").get("Name").getRawValue(), "Footman");
    }

    @Test
    public void unknownFieldsStayAndTheWurstMarkerIsDropped() throws Exception {
        W3U w3u = new W3U();

        obj(w3u, "h000", "hfoo", mod("wurs", 1), mod("zzzz", 3));

        ObjMod.Obj h000 = residual(merge(w3u), W3U.class, W3U.GAME_PATH.toString()).getObj(ObjId.valueOf("h000"));

        Assert.assertEquals(fieldsOf(h000), Collections.singletonList("zzzz"));
    }

    @Test
    public void unsafeObjectsAreLeftExactlyAsTheyWere() throws Exception {
        W3U w3u = new W3U();
        W3B w3b = new W3B();
        W3H w3h = new W3H();

        obj(w3u, "hkni", "hfoo", mod("uhpm", 1));      // id taken by a base unit
        obj(w3u, "h001", "zzzz", mod("uhpm", 1));      // unknown base
        obj(w3u, "zzz1", null, mod("uhpm", 1));        // not a base object
        obj(w3b, "B000", "LTlt", mod("bnam", "Gate"));  // B000 is a buff as well
        obj(w3h, "B000", "BHbd", mod("ftip", "Chill"));
        obj(w3u, "h0\"1", "hfoo", mod("uhpm", 1));     // would break the formats

        ObjMerger.Result result = merge(w3u, w3b, w3h);

        Assert.assertTrue(result.getReport().getMerged().values().stream().allMatch(List::isEmpty), result.getReport().toString());
        Assert.assertEquals(result.getReport().getSkipped().size(), 6);

        W3U residualUnits = residual(result, W3U.class, W3U.GAME_PATH.toString());

        Assert.assertEquals(residualUnits.getObj(ObjId.valueOf("hkni")).getBaseId().toString(), "hfoo");
        Assert.assertEquals(residualUnits.getObj(ObjId.valueOf("h001")).getBaseId().toString(), "zzzz");
        Assert.assertEquals(residual(result, W3H.class, W3H.GAME_PATH.toString()).getObj(ObjId.valueOf("B000")).getBaseId().toString(), "BHbd");
        Assert.assertFalse(result.getFiles().containsKey(UNIT_BALANCE));
        Assert.assertFalse(result.getFiles().containsKey(UNIT_FUNC_TARGET));
    }

    /** The game derives a custom object it still reads from the object-modification file from the map's tables. */
    @Test
    public void baseOfAnUnmergedCustomObjectIsNotModifiedInTheTables() throws Exception {
        W3U w3u = new W3U();

        obj(w3u, "hfoo", null, mod("uhpm", 999));
        obj(w3u, "h0;1", "hfoo", mod("udef", 1));

        ObjMerger.Result result = merge(w3u);
        W3U residual = residual(result, W3U.class, W3U.GAME_PATH.toString());

        Assert.assertFalse(result.getFiles().containsKey(UNIT_BALANCE));
        Assert.assertEquals(fieldsOf(residual.getObj(ObjId.valueOf("hfoo"))), Collections.singletonList("uhpm"));
        Assert.assertEquals(result.getReport().getSkipped().size(), 2);
    }

    /** Read from a map, an original object carries its id as base id; skipped, it must stay an original object. */
    @Test
    public void skippedOriginalObjectReadFromAMapStaysAnOriginalObject() throws Exception {
        W3U written = new W3U();

        obj(written, "hfoo", null, mod("uhpm", 999));
        obj(written, "h0;1", "hfoo", mod("udef", 1));

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (Wc3BinOutputStream stream = new Wc3BinOutputStream(out)) {
            written.write(stream);
        }

        W3U read = new W3U();

        try (Wc3BinInputStream stream = new Wc3BinInputStream(new ByteArrayInputStream(out.toByteArray()))) {
            read.read(stream);
        }

        Assert.assertNotNull(read.getObj(ObjId.valueOf("hfoo")).getBaseId(), "precondition: reading sets the base id");

        W3U residual = residual(merge(read), W3U.class, W3U.GAME_PATH.toString());
        ObjMod.Obj hfoo = residual.getObj(ObjId.valueOf("hfoo"));

        Assert.assertNull(hfoo.getNewId(), "hfoo must stay an original object");
        Assert.assertEquals(residual.getOrigObjs().size(), 1);
        Assert.assertEquals(residual.getCustomObjs().size(), 1);
    }

    @Test
    public void doodadsAndUnchangedFilesAreNotWritten() throws Exception {
        W3D w3d = new W3D();

        obj(w3d, "D000", "LTlt");

        ObjMerger.Result result = merge(w3d);

        Assert.assertTrue(result.getFiles().isEmpty());
        Assert.assertTrue(result.getRemovedFiles().isEmpty());
        Assert.assertEquals(result.getReport().getSkipped().size(), 1);
    }

    // --- other object types ---

    /** No native reports a techtree, so an object that changes one stays as it is, where the game derives it. */
    @Test
    public void upgradesChangingTheirRequirementsStayInTheResidual() throws Exception {
        W3Q w3q = new W3Q();

        obj(w3q, "R000", "Rhme", lvl("greq", 2, 0, "hcas,hlum"), lvl("gnam", 3, 0, "Third"), lvl("glvl", 0, 0, 3));
        obj(w3q, "R001", "Rhme", lvl("gnam", 3, 0, "Third"), lvl("glvl", 0, 0, 3));

        ObjMerger.Result result = merge(w3q);

        W3Q residual = residual(result, W3Q.class, W3Q.GAME_PATH.toString());

        // R000 changes its requirements; R001 inherits Rhme's without setting them.
        for (String id : Arrays.asList("R000", "R001")) {
            Assert.assertFalse(table(result, "Units\\UpgradeData.slk").containsRow(id), id);
            Assert.assertEquals(residual.getObj(ObjId.valueOf(id)).getBaseId().toString(), "Rhme", id);
        }
    }

    /** A table value a profile file repeats is written there as one element, quoted if it holds a comma. */
    @Test
    public void repeatedProfileValuesKeepTheirCommas() throws Exception {
        W3B w3b = new W3B();

        obj(w3b, "B002", "LTlt", mod("bnam", "Gate, North"));

        ObjMerger.Result result = merge(w3b);

        Assert.assertEquals(table(result, "Units\\DestructableData.slk").getRow("B002").get("Name"), "Gate, North");

        String raw = section(result, "Units\\DestructableSkin.txt", "B002").get("Name").getRawValue();

        Assert.assertEquals(ProfileFile.tokenize(raw), Collections.singletonList("Gate, North"), raw);
    }

    @Test
    public void itemsBuffsAndDestructablesMerge() throws Exception {
        W3T w3t = new W3T();
        W3H w3h = new W3H();
        W3B w3b = new W3B();

        obj(w3t, "I000", "ratf", mod("iabi", "AIat"), mod("unam", "Claws"));
        obj(w3h, "B001", "BHbd", mod("ftip", "Chilled"));
        obj(w3b, "B002", "LTlt", mod("bnam", "Big Tree"), mod("bhps", 5000.0F));

        ObjMerger.Result result = merge(w3t, w3h, w3b);

        Assert.assertEquals(table(result, "Units\\ItemData.slk").getRow("I000").get("abilList"), "AIat");
        Assert.assertEquals(section(result, "Units\\ItemFunc.txt", "I000").get("Name").getRawValue(), "Claws");
        Assert.assertEquals(table(result, "Units\\AbilityBuffData.slk").getRow("B001").get("code"), "BHbd");
        Assert.assertEquals(section(result, "Units\\CampaignAbilityFunc.txt", "B001").get("Bufftip").getRawValue(), "Chilled");
        Assert.assertEquals(table(result, "Units\\DestructableData.slk").getRow("B002").get("HP"), "5000");

        for (ObjType type : Arrays.asList(ObjType.ITEM, ObjType.BUFF, ObjType.DESTRUCTABLE)) {
            Assert.assertTrue(result.getRemovedFiles().contains(type.getObjModPath().toString()), type + " residual should be empty");
        }
    }

    @Test
    public void oneListElementChangesInEveryVariantOfTheKey() throws Exception {
        // 3.0.1 base data gives the Ice Troll a different attack missile in HD: Missileart and Missileart:hd
        GameData data = BundledGameData.layer(BundledGameData.LAYER_BASE);
        W3U w3u = new W3U();

        obj(w3u, "n000", "nitr", mod("ua2m", "X.mdl"));

        ProfileFile.Section n000 = section(merge(data, w3u), UNIT_FUNC_TARGET, "n000");

        Assert.assertEquals(n000.get("Missileart").getRawValue(), "Abilities\\Weapons\\Axe\\AxeMissile.mdl,X.mdl");
        Assert.assertNotNull(n000.get("Missileart:hd"), "the HD value of attack 1 must survive");
        Assert.assertEquals(n000.get("Missileart:hd").getRawValue(), "Abilities\\Weapons\\Axe_IceTroll\\Axe_IceTroll.mdl,X.mdl");
    }

    @Test
    public void aWholeValueReplacesEveryVariantOfTheKey() throws Exception {
        GameData data = BundledGameData.layer(BundledGameData.LAYER_BASE);
        W3U w3u = new W3U();

        obj(w3u, "n000", "nitr", mod("ua1m", "Y.mdl"), mod("ua2m", "Z.mdl"));

        ProfileFile.Section n000 = section(merge(data, w3u), UNIT_FUNC_TARGET, "n000");

        Assert.assertEquals(n000.get("Missileart").getRawValue(), "Y.mdl,Z.mdl");
        Assert.assertEquals(n000.get("Missileart:hd").getRawValue(), "Y.mdl,Z.mdl");
    }

    // --- game data ---

    @Test
    public void tablesTheMapShipsAreTheBase() throws Exception {
        SlkTable mapTable = SlkTable.parse(base().read(UNIT_BALANCE));

        mapTable.getRow("hfoo").setRaw(mapTable.getColumnIndex("HP"), "777");

        MemoryGameData overrides = new MemoryGameData();

        overrides.put(UNIT_BALANCE, mapTable.toBytes());

        W3U w3u = new W3U();

        obj(w3u, "h000", "hfoo", mod("udef", 3));

        SlkTable balance = table(merge(new LayeredGameData(overrides, base()), w3u), UNIT_BALANCE);

        Assert.assertEquals(balance.getRow("h000").get("HP"), "777");
        Assert.assertEquals(balance.getRow("hfoo").get("HP"), "777");
    }

    @Test
    public void meleeDataSetIsUsedWhenAsked() throws Exception {
        W3U w3u = new W3U();

        // the Altar of Kings trains one hero fewer in melee data
        obj(w3u, "h000", "halt", mod("unam", "x"));

        Assert.assertEquals(section(merge(BundledGameData.melee(), w3u), UNIT_FUNC_TARGET, "h000").get("Trains").getRawValue(),
            "Hamg,Hmkg,Hpal");
        Assert.assertEquals(section(merge(BundledGameData.custom(), w3u), UNIT_FUNC_TARGET, "h000").get("Trains").getRawValue(),
            "Hamg,Hmkg,Hpal,Hblm");
    }

    // --- the merger itself ---

    @Test
    public void inputsAreNotChangedAndMergingRepeats() throws Exception {
        W3U w3u = new W3U();

        obj(w3u, "h000", "hfoo", mod("uhpm", 5), mod("unam", "x"));

        ObjMerger merger = new ObjMerger(base());

        merger.add(w3u);

        ObjMerger.Result first = merger.merge();
        ObjMerger.Result second = merger.merge();

        Assert.assertEquals(w3u.getObj(ObjId.valueOf("h000")).getMods().size(), 2);
        Assert.assertEquals(first.getFiles().keySet(), second.getFiles().keySet());

        for (String path : first.getFiles().keySet()) {
            Assert.assertEquals(second.getFiles().get(path), first.getFiles().get(path), path);
        }
    }

    @Test
    public void addingTheSameTypeTwiceMergesThem() throws Exception {
        W3U a = new W3U();
        W3U b = new W3U();

        obj(a, "h000", "hfoo", mod("uhpm", 5));
        obj(b, "h001", "hpea", mod("uhpm", 6));

        SlkTable balance = table(merge(a, b), UNIT_BALANCE);

        Assert.assertEquals(balance.getRow("h000").get("HP"), "5");
        Assert.assertEquals(balance.getRow("h001").get("HP"), "6");
    }

    /**
     * Hbz1 and Hab1 both write DataA1. Once a write to some storage has to
     * stay in the residual file -- applied after the tables -- every later
     * write to it stays as well, or the earlier one would win in game.
     */
    @Test
    public void writesAfterAKeptWriteToTheSameStorageStayToo() throws Exception {
        W3A w3a = new W3A();

        obj(w3a, "A000", "AHbz", lvl("Hbz2", 1, 2, 7), lvl("Hbz1", 1, 1, "q\"uote"), lvl("Hab1", 1, 1, 20), lvl("Hbz2", 2, 2, 8));

        ObjMerger.Result result = merge(w3a);
        ObjMod.Obj a000 = residual(result, W3A.class, W3A.GAME_PATH.toString()).getObj(ObjId.valueOf("A000"));
        SlkTable.Row row = table(result, ABILITY_DATA).getRow("A000");

        Assert.assertEquals(fieldsOf(a000), Arrays.asList("Hbz1", "Hab1"), "Hab1 writes DataA1 after the kept Hbz1");
        Assert.assertEquals(row.get("DataA1"), SlkTable.parse(base().read(ABILITY_DATA)).getRow("AHbz").get("DataA1"));
        Assert.assertEquals(row.get("DataB1"), "7", "other storage is unaffected");
        Assert.assertEquals(row.get("DataB2"), "8");
    }

    /** The game applies modifications in file order, so copying must not regroup them by field. */
    @Test
    public void copiesKeepTheOrderOfModifications() {
        W3A w3a = new W3A();

        obj(w3a, "A000", "AHbz", lvl("Hbz1", 1, 1, 1), lvl("Hab1", 1, 1, 2), lvl("Hbz1", 2, 1, 3));

        ObjMod.Obj copy = w3a.copy().getObj(ObjId.valueOf("A000"));

        Assert.assertEquals(fieldsOf(copy), Arrays.asList("Hbz1", "Hab1", "Hbz1"));
    }

    @Test
    public void realsArePlainAndExact() {
        Assert.assertEquals(ObjMerger_realText(0.1F), "0.1");
        Assert.assertEquals(ObjMerger_realText(1.0E-5F), "0.00001");
        Assert.assertEquals(ObjMerger_realText(100F), "100");
        Assert.assertEquals(ObjMerger_realText(-2.5F), "-2.5");
        Assert.assertEquals(ObjMerger_realText(0F), "0");
        Assert.assertEquals(Float.parseFloat(ObjMerger_realText(0.3333333F)), 0.3333333F);
    }

    private static String ObjMerger_realText(float f) {
        W3U w3u = new W3U();

        obj(w3u, "h000", "hfoo", mod("usca", f));

        try {
            return section(merge(w3u), UNIT_SKIN_TARGET, "h000").get("modelScale").getRawValue();
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    static String text(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
