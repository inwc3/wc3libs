package wc3libs.app.objMerge;

import net.moonlightflower.wc3libs.app.objMerge.GameData;
import net.moonlightflower.wc3libs.app.objMerge.ObjType;
import net.moonlightflower.wc3libs.app.objMerge.ProfileFile;
import net.moonlightflower.wc3libs.app.objMerge.ProfileGroup;
import net.moonlightflower.wc3libs.app.objMerge.SlkTable;
import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.dataTypes.War3Num;
import net.moonlightflower.wc3libs.dataTypes.app.War3Int;
import net.moonlightflower.wc3libs.misc.ObjId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * What the game ends up with for one field of one object, given game files
 * and object modifications: the last modification of the field if there is
 * one, otherwise the value in the tables or profile files -- of the base
 * object, for a custom one.
 * <p>
 * This is written apart from the merger on purpose. Comparing its answers for
 * the input (game data + object modifications) and for the output (merged
 * files + residual object modifications) checks the merge end to end without
 * reusing the merger's own reasoning. Only plain profile keys are compared;
 * qualified ones ({@code Art:hd}) are covered by unit tests.
 */
final class EffectiveObjData {
    record Field(String id, String field, String slk, int index, int repeat, int data, boolean appendIndex, String type) {
        boolean isProfile() {
            return slk.equals("Profile");
        }
    }

    private final GameData _data;
    private final Map<ObjType, ObjMod<?>> _objMods;
    private final Map<String, SlkTable> _tables = new HashMap<>();
    private final Map<ObjType, Map<String, Field>> _fields = new HashMap<>();
    private final Map<String, Map<String, String>> _profile = new HashMap<>();

    EffectiveObjData(GameData data, Map<ObjType, ? extends ObjMod<?>> objMods) throws IOException {
        _data = data;
        _objMods = new LinkedHashMap<>(objMods);

        for (ProfileGroup group : ProfileGroup.values()) {
            for (String path : group.getFiles()) {
                byte[] bytes = data.read(path);

                if (bytes == null) continue;

                for (ProfileFile.Section section : ProfileFile.parse(bytes).getSections().values()) {
                    Map<String, String> keys = _profile.computeIfAbsent(section.getId(), k -> new HashMap<>());

                    section.getEntries().forEach((key, entry) -> {
                        if (!key.contains(":")) keys.put(key, entry.getRawValue());
                    });
                }
            }
        }
    }

    static Map<String, Field> fields(GameData data, ObjType type) throws IOException {
        SlkTable meta = SlkTable.parse(data.read(type.getMetaPath()));
        Map<String, Field> fields = new LinkedHashMap<>();

        for (SlkTable.Row row : meta.getRows()) {
            if (row.getId() == null) continue;

            fields.put(row.getId(), new Field(row.getId(), str(row.get("field")), str(row.get("slk")), num(row.get("index"), -1),
                num(row.get("repeat"), 0), num(row.get("data"), 0), num(row.get("appendIndex"), 0) > 0, str(row.get("type"))));
        }

        return fields;
    }

    private static String str(String s) {
        return s == null ? "" : s;
    }

    private static int num(String s, int fallback) {
        try {
            return s == null || s.isEmpty() ? fallback : Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    /**
     * Where a field's value is stored at a level. Distinct fields can share
     * storage -- every ability's DataA1, the unit and the item "file" -- so a
     * modification is found by where it writes, not by its field id.
     */
    private static String storage(Field field, int level) {
        if (field.isProfile()) return "profile|" + profileKey(field, level).toLowerCase(Locale.ROOT) + "|" + profilePos(field, level);

        return "slk|" + field.slk() + "|" + column(field, level).toLowerCase(Locale.ROOT);
    }

    private static String profileKey(Field field, int level) {
        return field.repeat() > 0 && field.appendIndex() && level > 1 ? field.field() + (level - 1) : field.field();
    }

    /** @return the element index, or null for the whole value */
    private static Integer profilePos(Field field, int level) {
        if (field.repeat() > 0 && field.appendIndex()) return null;
        if (field.repeat() > 0) return Math.max(level, 1) - 1 + Math.max(field.index(), 0);

        return field.index() >= 0 ? field.index() : null;
    }

    private static String column(Field field, int level) {
        String column = field.field();

        if (column.equals("Data")) column += (char) ('A' + field.data() - 1);
        if (field.repeat() > 0) column += level;

        return column;
    }

    String value(ObjType type, String id, Field field, int level) throws IOException {
        ObjMod<?> objMod = _objMods.get(type);
        ObjMod.Obj obj = objMod == null ? null : objMod.getObj(ObjId.valueOf(id));
        String source = obj != null && obj.getNewId() != null ? obj.getBaseId().toString() : id;

        if (obj == null) return field.isProfile() ? profileValue(source, field, level) : tableValue(source, field, level);

        Map<String, Field> fields = _fields.computeIfAbsent(type, t -> {
            try {
                return fields(_data, t);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        });

        if (!field.isProfile()) {
            String target = storage(field, level);
            String last = null;

            for (ObjMod.Obj.Mod mod : obj.getMods()) {
                Field modField = fields.get(mod.getId().toString());

                if (modField != null && storage(modField, levelOf(mod)).equals(target)) last = modText(mod);
            }

            return last != null ? last : tableValue(source, field, level);
        }

        // A profile value is a list: replay every write to its key in order,
        // whole values and single elements alike, onto what the data holds.
        String key = profileKey(field, level).toLowerCase(Locale.ROOT);
        Map<String, String> section = _profile.get(source);
        String raw = section == null ? null : section.get(key);
        List<String> tokens = raw == null ? new ArrayList<>() : new ArrayList<>(ProfileFile.tokenize(raw));
        boolean written = raw != null;

        for (ObjMod.Obj.Mod mod : obj.getMods()) {
            Field modField = fields.get(mod.getId().toString());

            if (modField == null || !modField.isProfile()) continue;

            int modLevel = levelOf(mod);

            if (!profileKey(modField, modLevel).equalsIgnoreCase(key)) continue;

            Integer modPos = profilePos(modField, modLevel);

            if (modPos == null) {
                tokens = modField.type().endsWith("List") ? new ArrayList<>(ProfileFile.tokenize(modText(mod)))
                    : new ArrayList<>(List.of(modText(mod)));
            } else {
                while (tokens.size() <= modPos) tokens.add("");

                tokens.set(modPos, modText(mod));
            }

            written = true;
        }

        if (!written) return "";

        Integer pos = profilePos(field, level);

        if (pos == null) return String.join(",", tokens);

        return pos < tokens.size() ? tokens.get(pos) : "";
    }

    private static int levelOf(ObjMod.Obj.Mod mod) {
        return mod instanceof ObjMod.Obj.ExtendedMod ext ? ext.getLevel() : 0;
    }

    private static String modText(ObjMod.Obj.Mod mod) {
        if (mod.getVal() == null) return "";
        if (mod.getVal() instanceof War3Int i) return Integer.toString(i.toInt());
        if (mod.getVal() instanceof War3Num n) return Float.toString(n.toFloat());

        return mod.getVal().toString();
    }

    private String profileValue(String id, Field field, int level) {
        String key = field.field();
        Integer pos;

        if (field.repeat() > 0 && field.appendIndex()) {
            if (level > 1) key += level - 1;

            pos = null;
        } else if (field.repeat() > 0) {
            pos = Math.max(level, 1) - 1 + Math.max(field.index(), 0);
        } else {
            pos = field.index() >= 0 ? field.index() : null;
        }

        Map<String, String> section = _profile.get(id);
        String raw = section == null ? null : section.get(key.toLowerCase(Locale.ROOT));

        if (raw == null) return "";

        List<String> tokens = ProfileFile.tokenize(raw);

        if (pos == null) return String.join(",", tokens);

        return pos < tokens.size() ? tokens.get(pos) : "";
    }

    private String tableValue(String id, Field field, int level) throws IOException {
        String path = ObjType.tableOf(field.slk());

        if (path == null) return "";

        SlkTable table = _tables.computeIfAbsent(path, p -> {
            try {
                byte[] bytes = _data.read(p);

                return bytes == null ? null : SlkTable.parse(bytes);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        });

        SlkTable.Row row = table == null ? null : table.getRow(id);

        if (row == null) return "";

        String column = field.field();

        if (column.equals("Data")) column += (char) ('A' + field.data() - 1);
        if (field.repeat() > 0) column += level;

        String val = row.get(column);

        return val == null ? "" : val;
    }

    /**
     * Whether two answers mean the same to the game: equal text, equal
     * numbers, or both nothing. The game's tables spell nothing as {@code _}
     * or {@code -} in columns they never leave empty (an empty UnitAbilities
     * "auto" crashes the game), so the merger writes an emptied value that way.
     */
    static boolean same(String a, String b) {
        if (a.equals(b)) return true;
        if (isNothing(a) && isNothing(b)) return true;

        try {
            return Float.parseFloat(a) == Float.parseFloat(b);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isNothing(String s) {
        String trimmed = s.trim();

        return trimmed.isEmpty() || trimmed.equals("_") || trimmed.equals("-");
    }
}
