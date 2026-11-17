package net.moonlightflower.wc3libs.app;

import net.moonlightflower.wc3libs.app.objMerge.BundledGameData;
import net.moonlightflower.wc3libs.app.objMerge.GameData;
import net.moonlightflower.wc3libs.app.objMerge.LayeredGameData;
import net.moonlightflower.wc3libs.app.objMerge.MemoryGameData;
import net.moonlightflower.wc3libs.app.objMerge.ObjType;
import net.moonlightflower.wc3libs.app.objMerge.ProfileFile;
import net.moonlightflower.wc3libs.app.objMerge.ProfileGroup;
import net.moonlightflower.wc3libs.app.objMerge.SlkTable;
import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.bin.app.MapFlag;
import net.moonlightflower.wc3libs.bin.app.W3I;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.War3Num;
import net.moonlightflower.wc3libs.dataTypes.app.War3Int;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Moves object modifications into SLK tables and profile files, which the
 * game loads considerably faster than it applies object-modification files.
 * <p>
 * An object-modification file only records how objects differ from the game's
 * own data, while a map's SLK table <em>replaces</em> the game's table of the
 * same name. Merging therefore starts from the game's tables -- supplied as
 * {@link GameData}, usually the {@linkplain BundledGameData bundled snapshot}
 * under the map's own overrides -- and writes them back whole, with custom
 * objects added and modified base objects changed in place.
 * <p>
 * Whatever cannot be expressed that way, or not without guessing at the
 * game's behaviour, stays in a <em>residual</em> object-modification file,
 * which the game still applies on top. That is what keeps the result
 * equivalent to the input:
 * <ul>
 *   <li>A custom object is copied from its base object's rows and profile
 *   sections, then its modifications are applied to the copy. One that cannot
 *   be placed safely -- its base object is unknown, its id is taken by game
 *   data or declared by two object types, or the id is malformed -- is left in
 *   the object-modification file untouched, and so is the base object it is
 *   derived from, whose rows it will be copied from in game.</li>
 *   <li>A modified base object keeps its profile modifications in the residual
 *   file: its profile keys live in the game's own, partly localized files,
 *   and a second definition would race the first.</li>
 *   <li>A field whose SLK column does not exist -- ability levels above the
 *   table's four, for one -- or whose value cannot be written in the target
 *   format ({@code TRIGSTR_} references, quotes, line breaks) stays in the
 *   residual file.</li>
 *   <li>Doodads and skin modifications ({@code war3mapSkin.*}) are not
 *   merged; pass only the {@code war3map.*} files.</li>
 * </ul>
 * A custom object that leaves the object-modification file is described in
 * the game's profile files from then on; the strings it inherits from its
 * base object are the ones in the supplied game data, so in the bundled
 * snapshot's English.
 */
public class ObjMerger {
    private static final Logger log = LoggerFactory.getLogger(ObjMerger.class);

    /** Fields dropped from merged objects: Wurst's marker for generated objects, which the game ignores. */
    public static final Set<String> DEFAULT_IGNORED_FIELDS = Collections.singleton("wurs");

    private final GameData _gameData;
    private final Map<ObjType, ObjMod<?>> _objMods = new EnumMap<>(ObjType.class);
    private final Set<String> _ignoredFields = new LinkedHashSet<>(DEFAULT_IGNORED_FIELDS);
    private boolean _moveProfileMods = true;

    /** @param gameData the data the map plays with, its own overrides included */
    public ObjMerger(@Nonnull GameData gameData) {
        _gameData = gameData;
    }

    @Nonnull
    public GameData getGameData() {
        return _gameData;
    }

    /**
     * Adds a {@code war3map.*} object-modification file. Adding a second one of
     * the same type merges it into the first. The argument is copied, never
     * changed.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void add(@Nonnull ObjMod<?> objMod) {
        ObjType type = ObjType.of(objMod);

        if (type == null) throw new IllegalArgumentException("unsupported object modification type " + objMod.getClass());

        ObjMod existing = _objMods.get(type);

        if (existing == null) {
            _objMods.put(type, objMod.copy());
        } else {
            existing.merge(objMod);
        }
    }

    @Nonnull
    public Map<ObjType, ObjMod<?>> getObjMods() {
        return Collections.unmodifiableMap(_objMods);
    }

    /** Replaces the fields dropped from merged objects; see {@link #DEFAULT_IGNORED_FIELDS}. */
    public void setIgnoredFields(@Nonnull Collection<String> fieldIds) {
        _ignoredFields.clear();
        _ignoredFields.addAll(fieldIds);
    }

    /**
     * Whether a custom object's profile modifications (names, tooltips, art)
     * move into profile files along with it, which is the default. Off, they
     * stay in the residual object-modification file and only SLK fields move.
     */
    public void setMoveProfileMods(boolean moveProfileMods) {
        _moveProfileMods = moveProfileMods;
    }

    /** Merges the added modifications. The merger can be run again; nothing it holds is changed. */
    @Nonnull
    public Result merge() throws IOException {
        return new Run().exec();
    }

    /**
     * Prepares a merger for a map: the bundled game data for the map's data
     * set, overlaid with whatever tables and profile files the map already
     * ships, and the map's {@code war3map.*} object modifications.
     */
    @Nonnull
    public static ObjMerger ofMap(@Nonnull File mapFile) throws IOException {
        Map<String, byte[]> files = readMapFiles(mapFile, mapReadPaths());

        GameData base;

        byte[] w3iBytes = files.get(GameData.normalize(W3I.GAME_PATH.toString()));

        if (w3iBytes != null) {
            W3I w3i;

            try {
                w3i = new W3I(w3iBytes);
            } catch (Exception e) {
                throw new IOException("cannot read the map's info file", e);
            }

            base = BundledGameData.forDataSet(w3i.getGameDataSet(), w3i.getFlag(MapFlag.MELEE_MAP));
        } else {
            base = BundledGameData.custom();
        }

        MemoryGameData overrides = new MemoryGameData();

        for (String path : gameDataPaths()) {
            byte[] bytes = files.get(GameData.normalize(path));

            if (bytes != null) overrides.put(path, bytes);
        }

        ObjMerger merger = new ObjMerger(new LayeredGameData(overrides, base));

        for (ObjType type : ObjType.values()) {
            byte[] bytes = files.get(GameData.normalize(type.getObjModPath().toString()));

            if (bytes == null) continue;

            ObjMod objMod = ObjMod.create(type.getObjModClass());

            try (Wc3BinInputStream stream = new Wc3BinInputStream(new ByteArrayInputStream(bytes))) {
                objMod.read(stream);
            }

            merger.add(objMod);
        }

        return merger;
    }

    /** @return every game file merging reads or writes */
    @Nonnull
    public static Set<String> gameDataPaths() {
        Set<String> paths = new LinkedHashSet<>();

        for (ObjType type : ObjType.values()) {
            paths.add(type.getMetaPath());
            paths.addAll(type.getTables());
        }

        for (ProfileGroup group : ProfileGroup.values()) paths.addAll(group.getFiles());

        return paths;
    }

    @Nonnull
    private static Set<String> mapReadPaths() {
        Set<String> paths = new LinkedHashSet<>(gameDataPaths());

        paths.add(W3I.GAME_PATH.toString());

        for (ObjType type : ObjType.values()) paths.add(type.getObjModPath().toString());

        return paths;
    }

    @Nonnull
    private static Map<String, byte[]> readMapFiles(@Nonnull File mapFile, @Nonnull Collection<String> paths) throws IOException {
        if (!mapFile.isFile()) throw new IOException("no such map: " + mapFile);

        MpqPort.Out port = new JMpqPort.Out();

        for (String path : paths) port.add(new File(path));

        MpqPort.Out.Result portResult = port.commit(mapFile);

        Map<String, byte[]> files = new LinkedHashMap<>();

        for (Map.Entry<File, MpqPort.Out.Result.Segment> entry : portResult.getExports().entrySet()) {
            byte[] bytes = entry.getValue().getOutBytes();

            if (bytes != null) files.put(GameData.normalize(entry.getKey().toString()), bytes);
        }

        return files;
    }

    /** What merging produced: files to write into the map, files to remove from it, and a report. */
    public static final class Result {
        private final Map<String, byte[]> _files;
        private final Set<String> _removedFiles;
        private final Report _report;

        private Result(@Nonnull Map<String, byte[]> files, @Nonnull Set<String> removedFiles, @Nonnull Report report) {
            _files = Collections.unmodifiableMap(files);
            _removedFiles = Collections.unmodifiableSet(removedFiles);
            _report = report;
        }

        /** @return game path to content, for every file to add to or replace in the map */
        @Nonnull
        public Map<String, byte[]> getFiles() {
            return _files;
        }

        /** @return game paths of object-modification files left empty, to delete from the map */
        @Nonnull
        public Set<String> getRemovedFiles() {
            return _removedFiles;
        }

        @Nonnull
        public Report getReport() {
            return _report;
        }

        /** Writes the files under {@code dir} by their game paths and deletes the removed ones there. */
        public void writeTo(@Nonnull Path dir) throws IOException {
            for (Map.Entry<String, byte[]> entry : _files.entrySet()) {
                Path file = resolve(dir, entry.getKey());

                Files.createDirectories(file.getParent());
                Files.write(file, entry.getValue());
            }

            for (String path : _removedFiles) Files.deleteIfExists(resolve(dir, path));
        }

        @Nonnull
        private static Path resolve(@Nonnull Path dir, @Nonnull String gamePath) {
            return dir.resolve(gamePath.replace('\\', '/'));
        }

        /** Applies the result to a map archive in place. */
        public void writeToMap(@Nonnull File mapFile) throws IOException {
            Path tempDir = Files.createTempDirectory("wc3libs-objmerge");

            try {
                MpqPort.In port = new JMpqPort.In();

                for (Map.Entry<String, byte[]> entry : _files.entrySet()) {
                    Path file = resolve(tempDir, entry.getKey());

                    Files.createDirectories(file.getParent());
                    Files.write(file, entry.getValue());

                    port.add(file.toFile(), new File(entry.getKey()));
                }

                for (String path : _removedFiles) port.addDel(new File(path));

                port.commit(mapFile);
            } catch (IOException e) {
                throw e;
            } catch (Exception e) {
                throw new IOException(e);
            } finally {
                try (Stream<Path> paths = Files.walk(tempDir)) {
                    paths.sorted(Comparator.reverseOrder()).forEach(path -> path.toFile().delete());
                }
            }
        }
    }

    /** Which objects moved, which stayed and why. */
    public static final class Report {
        /** An object left in its object-modification file as it was. */
        public static final class Skipped {
            private final ObjType _type;
            private final String _id;
            private final String _reason;

            private Skipped(@Nonnull ObjType type, @Nonnull String id, @Nonnull String reason) {
                _type = type;
                _id = id;
                _reason = reason;
            }

            @Nonnull
            public ObjType getType() {
                return _type;
            }

            @Nonnull
            public String getId() {
                return _id;
            }

            @Nonnull
            public String getReason() {
                return _reason;
            }

            @Override
            public String toString() {
                return _type + " " + _id + ": " + _reason;
            }
        }

        private final Map<ObjType, List<String>> _merged = new EnumMap<>(ObjType.class);
        private final List<Skipped> _skipped = new ArrayList<>();
        private int _movedMods;
        private int _keptMods;

        /** @return per type, the objects whose data now lives in SLK tables and profile files */
        @Nonnull
        public Map<ObjType, List<String>> getMerged() {
            return Collections.unmodifiableMap(_merged);
        }

        @Nonnull
        public List<Skipped> getSkipped() {
            return Collections.unmodifiableList(_skipped);
        }

        /** @return modifications now expressed in SLK tables or profile files, dropped markers included */
        public int getMovedModCount() {
            return _movedMods;
        }

        /** @return modifications of merged objects left in the residual object-modification files */
        public int getKeptModCount() {
            return _keptMods;
        }

        @Override
        public String toString() {
            int merged = _merged.values().stream().mapToInt(List::size).sum();

            return String.format("merged %d objects (%d modifications moved, %d kept), skipped %d", merged, _movedMods,
                _keptMods, _skipped.size());
        }
    }

    /** One metadata row, as far as merging needs it. */
    private static final class MetaField {
        private final String _field;
        private final String _slk;
        private final int _index;
        private final int _repeat;
        private final int _data;
        private final boolean _appendIndex;
        private final String _type;

        private MetaField(@Nonnull SlkTable.Row row) {
            _field = nonNull(row.get("field"));
            _slk = nonNull(row.get("slk"));
            _index = intOf(row.get("index"), -1);
            _repeat = intOf(row.get("repeat"), 0);
            _data = intOf(row.get("data"), 0);
            _appendIndex = intOf(row.get("appendIndex"), 0) > 0;
            _type = nonNull(row.get("type"));
        }

        private boolean isProfile() {
            return _slk.equals("Profile");
        }

        private boolean isList() {
            return _type.endsWith("List");
        }

        /** @return the SLK column for a level, or {@code null} if the field has none */
        @Nullable
        private String column(int level) {
            String column = _field;

            if (column.equals("Data")) {
                if (_data < 1 || _data > 26) return null;

                column += (char) ('A' + _data - 1);
            }

            if (_repeat > 0) {
                if (level < 1) return null;

                column += level;
            }

            return column;
        }

        /**
         * Where a profile field's value goes for a level: the key, and the
         * element of its comma-separated value, or {@code null} for the whole
         * value. Upgrades put each level under its own key ({@code Requires},
         * {@code Requires1}, ...), other level-dependent fields use one
         * element per level.
         */
        @Nullable
        private ProfileSlot profileSlot(int level) {
            if (_repeat > 0 && level < 1) return null;

            if (_repeat > 0 && _appendIndex) return new ProfileSlot(level > 1 ? _field + (level - 1) : _field, null);
            if (_repeat > 0) return new ProfileSlot(_field, level - 1 + Math.max(_index, 0));
            if (_index >= 0) return new ProfileSlot(_field, _index);

            return new ProfileSlot(_field, null);
        }

        /** @return an id for the storage this field writes at a level, shared by fields that alias it */
        @Nonnull
        private String storage(int level) {
            if (isProfile()) {
                ProfileSlot slot = profileSlot(level);

                return slot == null ? "invalid" : slot.toString();
            }

            String column = column(level);

            return "slk|" + _slk + "|" + (column == null ? "invalid" : column.toLowerCase(java.util.Locale.ROOT));
        }

        @Nonnull
        private static String nonNull(@Nullable String s) {
            return s == null ? "" : s;
        }

        private static int intOf(@Nullable String s, int fallback) {
            if (s == null || s.isEmpty()) return fallback;

            try {
                return Integer.parseInt(s.trim());
            } catch (NumberFormatException e) {
                return fallback;
            }
        }
    }

    private static final class ProfileSlot {
        private final String _key;
        private final Integer _pos;

        private ProfileSlot(@Nonnull String key, @Nullable Integer pos) {
            _key = key;
            _pos = pos;
        }

        @Nonnull
        private String keyId() {
            return "profile|" + _key.toLowerCase(java.util.Locale.ROOT);
        }

        @Override
        public String toString() {
            return keyId() + "|" + (_pos == null ? "*" : _pos.toString());
        }
    }

    /** The storage written by modifications that stayed in the residual file, for one object. */
    private static final class KeptSlots {
        private final Set<String> _storages = new LinkedHashSet<>();
        private final Set<String> _profileKeys = new LinkedHashSet<>();

        private void add(@Nonnull MetaField meta, int level) {
            _storages.add(meta.storage(level));

            ProfileSlot slot = meta.isProfile() ? meta.profileSlot(level) : null;

            if (slot != null) _profileKeys.add(slot.keyId());
        }

        /** A whole profile value and its elements overlap, so either blocks the other. */
        private boolean blocks(@Nonnull MetaField meta, int level) {
            if (_storages.contains(meta.storage(level))) return true;

            ProfileSlot slot = meta.isProfile() ? meta.profileSlot(level) : null;

            if (slot == null) return false;

            return _storages.contains(slot.keyId() + "|*") || (slot._pos == null && _profileKeys.contains(slot.keyId()));
        }
    }

    /** The state of one merge. */
    private final class Run {
        private final Map<String, SlkTable> _tables = new LinkedHashMap<>();
        private final Set<String> _changedTables = new LinkedHashSet<>();
        private final Map<ProfileGroup, Map<String, ProfileFile.Section>> _baseSections = new EnumMap<>(ProfileGroup.class);
        private final Map<ProfileGroup, Map<String, ProfileFile.Section>> _newSections = new EnumMap<>(ProfileGroup.class);
        private final Map<ProfileGroup, String> _lineSeparators = new EnumMap<>(ProfileGroup.class);
        private final Set<String> _takenIds = new LinkedHashSet<>();
        private final Map<String, Integer> _customIdUses = new LinkedHashMap<>();
        private final Map<ObjType, Set<String>> _protectedBases = new EnumMap<>(ObjType.class);
        private final Report _report = new Report();

        @Nullable
        private SlkTable table(@Nonnull String path) throws IOException {
            if (_tables.containsKey(path)) return _tables.get(path);

            byte[] bytes = _gameData.read(path);
            SlkTable table = bytes == null ? null : SlkTable.parse(bytes);

            _tables.put(path, table);

            return table;
        }

        private void loadIdsAndProfiles() throws IOException {
            for (ObjType type : ObjType.values()) {
                for (String path : type.getTables()) {
                    SlkTable table = table(path);

                    if (table == null) continue;

                    for (SlkTable.Row row : table.getRows()) {
                        if (row.getId() != null) _takenIds.add(row.getId());
                    }
                }
            }

            for (ProfileGroup group : ProfileGroup.values()) {
                Map<String, ProfileFile.Section> sections = new LinkedHashMap<>();

                for (String path : group.getFiles()) {
                    byte[] bytes = _gameData.read(path);

                    if (bytes == null) continue;

                    ProfileFile file = ProfileFile.parse(bytes);

                    if (path.equals(group.getTarget())) _lineSeparators.put(group, file.getLineSeparator());

                    for (ProfileFile.Section section : file.getSections().values()) {
                        sections.computeIfAbsent(section.getId(), ProfileFile.Section::new).putAll(section);
                    }
                }

                _baseSections.put(group, sections);
                _newSections.put(group, new LinkedHashMap<>());
                _takenIds.addAll(sections.keySet());
            }

            for (ObjMod<?> objMod : _objMods.values()) {
                for (ObjMod.Obj obj : objMod.getCustomObjs()) _customIdUses.merge(obj.getId().toString(), 1, Integer::sum);
            }
        }

        @Nonnull
        private Map<String, MetaField> meta(@Nonnull ObjType type) throws IOException {
            SlkTable table = table(type.getMetaPath());

            if (table == null) throw new IOException("game data has no " + type.getMetaPath());

            Map<String, MetaField> fields = new LinkedHashMap<>();

            for (SlkTable.Row row : table.getRows()) {
                if (row.getId() != null) fields.put(row.getId(), new MetaField(row));
            }

            return fields;
        }

        /** @return why {@code obj} has to stay as it is, or {@code null} if it can be merged */
        @Nullable
        private String skipReason(@Nonnull ObjType type, @Nonnull ObjMod.Obj obj) throws IOException {
            if (!type.isMerged()) return type + " objects are not merged";

            SlkTable primary = table(type.getPrimaryTable());

            if (primary == null) return "game data has no " + type.getPrimaryTable();

            String id = obj.getId().toString();

            if (obj.getNewId() == null) {
                if (!primary.containsRow(id)) return "not an object of the game data";

                // A custom object left in the object-modification file is
                // derived from this object's rows as the map ships them, so
                // those rows must not take on this object's modifications.
                if (_protectedBases.get(type).contains(id)) return "base of a custom object that is not merged";

                return null;
            }

            if (!isPlainId(id)) return "not a plain four-character id";

            String baseId = obj.getBaseId() == null ? null : obj.getBaseId().toString();

            if (baseId == null || !primary.containsRow(baseId)) return "base object " + baseId + " is not in the game data";
            if (_takenIds.contains(id)) return "id is already used by the game data";
            if (_customIdUses.getOrDefault(id, 0) > 1) return "id is declared by more than one object type";

            return null;
        }

        private boolean isPlainId(@Nonnull String id) {
            if (id.length() != 4) return false;

            for (char c : id.toCharArray()) {
                if (c <= ' ' || c > '~' || "\"[];,=".indexOf(c) >= 0) return false;
            }

            return true;
        }

        /** Gives a custom object its base object's rows and profile sections, before anything is modified. */
        private void instantiate(@Nonnull ObjType type, @Nonnull String id, @Nonnull String baseId) throws IOException {
            for (String path : type.getTables()) {
                SlkTable table = table(path);

                if (table == null) continue;

                SlkTable.Row baseRow = table.getRow(baseId);

                if (baseRow == null) continue;

                table.copyRow(baseRow, id);
                _changedTables.add(path);
            }

            for (ProfileGroup group : ProfileGroup.values()) {
                ProfileFile.Section baseSection = _baseSections.get(group).get(baseId);

                if (baseSection != null) _newSections.get(group).put(id, baseSection.copy(id));
            }
        }

        private boolean applySlk(@Nonnull ObjType type, @Nonnull String id, @Nonnull MetaField meta,
                                 @Nonnull ObjMod.Obj.Mod mod) throws IOException {
            String path = ObjType.tableOf(meta._slk);

            if (path == null || !type.getTables().contains(path)) return false;

            SlkTable table = table(path);

            if (table == null) return false;

            String column = meta.column(levelOf(mod));

            if (column == null) return false;

            int index = table.getColumnIndex(column);

            if (index < 1) return false;

            String raw = slkCell(mod);

            if (raw == null) return false;

            SlkTable.Row row = table.getRow(id);

            if (row == null) row = table.addRow(id);

            row.setRaw(index, raw.isEmpty() ? null : raw);
            _changedTables.add(path);

            return true;
        }

        private boolean applyProfile(@Nonnull String id, @Nonnull MetaField meta, @Nonnull ObjMod.Obj.Mod mod) {
            String text = profileValue(mod);

            if (text == null) return false;

            ProfileSlot slot = meta.profileSlot(levelOf(mod));

            if (slot == null) return false;

            String key = slot._key;
            Integer pos = slot._pos;
            List<String> replacement = (pos == null && meta.isList()) ? ProfileFile.tokenize(text) : Collections.singletonList(text);
            boolean applied = false;

            for (ProfileGroup group : ProfileGroup.values()) {
                ProfileFile.Section section = _newSections.get(group).get(id);

                if (section == null || !section.hasBaseKey(key)) continue;

                List<String> tokens;

                if (pos == null) {
                    tokens = replacement;
                } else {
                    ProfileFile.Entry entry = section.get(key);

                    tokens = entry == null ? new ArrayList<>() : new ArrayList<>(ProfileFile.tokenize(entry.getRawValue()));

                    while (tokens.size() <= pos) tokens.add("");

                    tokens.set(pos, text);
                }

                section.removeQualified(key);
                section.put(key, ProfileFile.join(tokens));

                applied = true;
            }

            return applied;
        }

        private void mergeType(@Nonnull ObjType type, @Nonnull ObjMod<?> objMod, @Nonnull Map<String, byte[]> files,
                               @Nonnull Set<String> removed) throws IOException {
            if (!type.isMerged()) {
                for (ObjMod.Obj obj : objMod.getObjsList()) {
                    _report._skipped.add(new Report.Skipped(type, obj.getId().toString(), type + " objects are not merged"));
                }

                return;
            }

            Map<String, MetaField> metaFields = meta(type);
            ObjMod residual = ObjMod.create(type.getObjModClass());

            residual.setFormat(objMod.getFormat());

            List<String> merged = _report._merged.computeIfAbsent(type, k -> new ArrayList<>());

            for (ObjMod.Obj obj : objMod.getObjsList()) {
                String id = obj.getId().toString();
                String reason = skipReason(type, obj);

                if (reason != null) {
                    copyInto(residual.addObj(obj.getId(), obj.getBaseId()), obj, obj.getMods());
                    _report._skipped.add(new Report.Skipped(type, id, reason));

                    continue;
                }

                boolean custom = obj.getNewId() != null;
                List<ObjMod.Obj.Mod> kept = new ArrayList<>();
                KeptSlots keptSlots = new KeptSlots();

                for (ObjMod.Obj.Mod mod : obj.getMods()) {
                    String fieldId = mod.getId().toString();

                    if (_ignoredFields.contains(fieldId)) {
                        _report._movedMods++;

                        continue;
                    }

                    MetaField meta = metaFields.get(fieldId);
                    int level = levelOf(mod);
                    boolean moved;

                    // Several fields can address the same storage -- every
                    // ability's DataA1, or the unit and item "file" -- and the
                    // last write wins. The residual file is applied after the
                    // tables, so once a write to some storage stays behind,
                    // every later write to it has to stay behind as well.
                    if (meta == null || keptSlots.blocks(meta, level)) {
                        moved = false;
                    } else if (meta.isProfile()) {
                        moved = custom && _moveProfileMods && applyProfile(id, meta, mod);
                    } else {
                        moved = applySlk(type, id, meta, mod);
                    }

                    if (moved) {
                        _report._movedMods++;
                    } else {
                        if (meta != null) keptSlots.add(meta, level);

                        kept.add(mod);
                        _report._keptMods++;
                    }
                }

                // The object exists in the tables now, so what is left
                // modifies it as a base object.
                if (!kept.isEmpty()) copyInto(residual.addObj(obj.getId(), null), obj, kept);

                merged.add(id);
            }

            String path = type.getObjModPath().toString();

            if (residual.getObjsList().isEmpty()) {
                removed.add(path);
            } else {
                files.put(path, objModBytes(residual));
            }
        }

        @Nonnull
        private Result exec() throws IOException {
            loadIdsAndProfiles();

            for (ObjType type : ObjType.values()) _protectedBases.put(type, new LinkedHashSet<>());

            for (Map.Entry<ObjType, ObjMod<?>> entry : _objMods.entrySet()) {
                for (ObjMod.Obj obj : entry.getValue().getCustomObjs()) {
                    if (skipReason(entry.getKey(), obj) != null && obj.getBaseId() != null) {
                        _protectedBases.get(entry.getKey()).add(obj.getBaseId().toString());
                    }
                }
            }

            // Custom objects copy their base object as the game data has it,
            // so every copy is taken before any base object is modified.
            for (Map.Entry<ObjType, ObjMod<?>> entry : _objMods.entrySet()) {
                ObjType type = entry.getKey();

                for (ObjMod.Obj obj : entry.getValue().getCustomObjs()) {
                    if (skipReason(type, obj) == null) instantiate(type, obj.getId().toString(), obj.getBaseId().toString());
                }
            }

            Map<String, byte[]> files = new LinkedHashMap<>();
            Set<String> removed = new LinkedHashSet<>();

            for (Map.Entry<ObjType, ObjMod<?>> entry : _objMods.entrySet()) {
                mergeType(entry.getKey(), entry.getValue(), files, removed);
            }

            for (String path : _changedTables) files.put(path, _tables.get(path).toBytes());

            for (ProfileGroup group : ProfileGroup.values()) {
                Map<String, ProfileFile.Section> sections = _newSections.get(group);

                if (!sections.isEmpty()) files.put(group.getTarget(), profileBytes(group, sections.values()));
            }

            log.info("{}", _report);

            return new Result(files, removed, _report);
        }

        @Nonnull
        private byte[] profileBytes(@Nonnull ProfileGroup group, @Nonnull Collection<ProfileFile.Section> sections)
            throws IOException {
            String nl = _lineSeparators.getOrDefault(group, "\r\n");
            byte[] base = _gameData.read(group.getTarget());
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            if (base != null) {
                out.write(base);

                String text = new String(base, StandardCharsets.UTF_8);

                if (!text.isEmpty() && !text.endsWith("\n")) out.write(nl.getBytes(StandardCharsets.UTF_8));
            }

            StringBuilder sb = new StringBuilder();

            for (ProfileFile.Section section : sections) sb.append(nl).append(section.toText(nl));

            out.write(sb.toString().getBytes(StandardCharsets.UTF_8));

            return out.toByteArray();
        }
    }

    @Nonnull
    private static byte[] objModBytes(@Nonnull ObjMod<?> objMod) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (Wc3BinOutputStream stream = new Wc3BinOutputStream(out)) {
            objMod.write(stream);
        }

        return out.toByteArray();
    }

    /** Copies modifications in their order, which decides the winner where fields share storage. */
    private static void copyInto(@Nonnull ObjMod.Obj target, @Nonnull ObjMod.Obj source, @Nonnull List<ObjMod.Obj.Mod> mods) {
        target.setUnknown(source.getUnknown());

        for (ObjMod.Obj.Mod mod : mods) target.addMod(mod.copy());
    }

    private static int levelOf(@Nonnull ObjMod.Obj.Mod mod) {
        return mod instanceof ObjMod.Obj.ExtendedMod ? ((ObjMod.Obj.ExtendedMod) mod).getLevel() : 0;
    }

    /** Text the game would not read back as written: a string reference or anything the formats cannot quote. */
    private static boolean isUnwritable(@Nonnull String s) {
        return s.contains("\"") || s.contains("\n") || s.contains("\r") || s.contains("TRIGSTR_");
    }

    /** @return the raw SLK cell, {@code ""} for an empty cell, or {@code null} if it cannot be written */
    @Nullable
    static String slkCell(@Nonnull ObjMod.Obj.Mod mod) {
        DataType val = mod.getVal();
        ObjMod.ValType valType = mod.getValType();

        if (valType == ObjMod.ValType.INT) return intText(val);
        if (valType == ObjMod.ValType.REAL || valType == ObjMod.ValType.UNREAL) return realText(val);

        String s = val == null ? "" : val.toString();

        if (isUnwritable(s) || s.contains(";")) return null;

        return s.isEmpty() ? "" : SlkTable.quote(s);
    }

    /** @return the profile value, or {@code null} if it cannot be written */
    @Nullable
    static String profileValue(@Nonnull ObjMod.Obj.Mod mod) {
        DataType val = mod.getVal();
        ObjMod.ValType valType = mod.getValType();

        if (valType == ObjMod.ValType.INT) return intText(val);
        if (valType == ObjMod.ValType.REAL || valType == ObjMod.ValType.UNREAL) return realText(val);

        String s = val == null ? "" : val.toString();

        return isUnwritable(s) ? null : s;
    }

    @Nullable
    private static String intText(@Nullable DataType val) {
        if (val instanceof War3Int) return Integer.toString(((War3Int) val).toInt());

        return null;
    }

    /** Writes a float in plain notation with the fewest digits that read back to the same float. */
    @Nullable
    static String realText(@Nullable DataType val) {
        if (!(val instanceof War3Num)) return null;

        float f = ((War3Num) val).toFloat();

        if (Float.isNaN(f) || Float.isInfinite(f)) return null;
        if (f == 0F) return "0";

        return new BigDecimal(Float.toString(f)).stripTrailingZeros().toPlainString();
    }
}
