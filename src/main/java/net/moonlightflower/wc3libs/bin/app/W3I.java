package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.Size;
import net.moonlightflower.wc3libs.port.GameVersion;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.port.Orient;
import net.moonlightflower.wc3libs.txt.app.jass.FuncDecl;
import net.moonlightflower.wc3libs.txt.app.jass.FuncImpl;
import net.moonlightflower.wc3libs.txt.app.jass.JassScript;
import net.moonlightflower.wc3libs.txt.app.jass.statement.Statement;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * info file for wrapping war3map.w3i
 */
public class W3I {
    public final static File GAME_PATH = new File("war3map.w3i");

    private int _fileVersion = 0;

    public int getFileVersion() {
        return _fileVersion;
    }

    public void setFileVersion(int fileVersion) {
        _fileVersion = fileVersion;
    }

    public static class State<T extends DataType> extends BinState<T> {
        public static State<War3Int> SAVES_AMOUNT = new State<>("savesAmount", War3Int.class, War3Int.valueOf(0));

        public State(@Nonnull String fieldIdS, @Nonnull DataTypeInfo typeInfo, @Nullable T defVal) {
            super(fieldIdS, typeInfo, defVal);
        }

        public State(@Nonnull String fieldIdS, @Nonnull DataTypeInfo typeInfo) {
            super(fieldIdS, typeInfo);
        }

        public State(@Nonnull String idString, @Nonnull Class<T> type) {
            super(idString, type);
        }

        public State(@Nonnull String idString, @Nonnull Class<T> type, @Nullable T defVal) {
            super(idString, type, defVal);
        }
    }

    private final Map<State, DataType> _vals = new LinkedHashMap<>();

    public <T extends DataType> T get(@Nonnull State<T> state) {
        return state.getVal(_vals.get(state));
    }

    public <T extends DataType> void set(@Nonnull State<T> state, T val) {
        _vals.put(state, val);
    }

    public War3Int getSavesAmount() {
        return get(State.SAVES_AMOUNT);
    }

    public void setSavesAmount(@Nonnull War3Int val) {
        set(State.SAVES_AMOUNT, val);
    }

    private int _editorVersion = 0;

    public int getEditorVersion() {
        return _editorVersion;
    }

    public void setEditorVersion(int val) {
        _editorVersion = val;
    }

    private long _gameVersion_major = 0;

    public long getGameVersion_major() {
        return _gameVersion_major;
    }

    public void setGameVersion_major(long val) {
        _gameVersion_major = val;
    }

    private long _gameVersion_minor;

    public long getGameVersion_minor() {
        return _gameVersion_minor;
    }

    public void setGameVersion_minor(long val) {
        _gameVersion_minor = val;
    }

    private long _gameVersion_rev;

    public long getGameVersion_rev() {
        return _gameVersion_rev;
    }

    public void setGameVersion_rev(long val) {
        _gameVersion_rev = val;
    }

    private long _gameVersion_build;

    public long getGameVersion_build() {
        return _gameVersion_build;
    }

    public void setGameVersion_build(long val) {
        _gameVersion_build = val;
    }

    private String _mapName;

    @Nonnull
    public String getMapName() {
        return _mapName;
    }

    public void setMapName(@Nonnull String val) {
        _mapName = val;
    }

    private String _mapAuthor;

    @Nullable
    public String getMapAuthor() {
        return _mapAuthor;
    }

    public void setMapAuthor(@Nullable String val) {
        _mapAuthor = val;
    }

    @Nullable
    private String _mapDescription;

    public String getMapDescription() {
        return _mapDescription;
    }

    public void setMapDescription(@Nullable String val) {
        _mapDescription = val;
    }

    private String _playersRecommendedAmount;

    @Nullable
    public String getPlayersRecommendedAmount() {
        return _playersRecommendedAmount;
    }

    public void setPlayersRecommendedAmount(@Nullable String val) {
        _playersRecommendedAmount = val;
    }

    private Coords2DF _cameraBounds1 = new Coords2DF(0F, 0F);
    private Coords2DF _cameraBounds2 = new Coords2DF(0F, 0F);
    private Coords2DF _cameraBounds3 = new Coords2DF(0F, 0F);
    private Coords2DF _cameraBounds4 = new Coords2DF(0F, 0F);

    @Nonnull
    public Coords2DF getCameraBounds1() {
        return _cameraBounds1;
    }

    @Nonnull
    public Coords2DF getCameraBounds2() {
        return _cameraBounds2;
    }

    @Nonnull
    public Coords2DF getCameraBounds3() {
        return _cameraBounds3;
    }

    @Nonnull
    public Coords2DF getCameraBounds4() {
        return _cameraBounds4;
    }

    public void setCameraBounds(@Nonnull Coords2DF pos1, @Nonnull Coords2DF pos2, @Nonnull Coords2DF pos3, @Nonnull Coords2DF pos4) {
        _cameraBounds1 = pos1;
        _cameraBounds2 = pos2;
        _cameraBounds3 = pos3;
        _cameraBounds4 = pos4;
    }

    private Bounds _margins = new Bounds(0, 0, 0, 0);

    @Nonnull
    public Bounds getMargins() {
        return _margins;
    }

    public void setMargins(@Nonnull Bounds val) {
        _margins = val;
    }

    private int _width = 32;
    private int _height = 32;

    //without boundaries
    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    @Nonnull
    public Bounds getWorldBounds() {
        Size size = new Size(getWidth() + getMargins().getMinX() + getMargins().getMaxX(), getHeight() + getMargins().getMinY() + getMargins().getMaxY());

        return new Bounds(size);
    }

    public void setDimensions(int width, int height) {
        _width = width;
        _height = height;
    }

    public static class Flags extends FlagsInt {
        @Override
        public DataType decode(Object val) {
            // TODO
            return null;
        }

        @Override
        public Object toSLKVal() {
            // TODO
            return null;
        }

        @Override
        public Object toTXTVal() {
            // TODO
            return null;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("");

            Collection<MapFlag> flags = MapFlag.values();

            if (!flags.isEmpty()) {
                for (MapFlag flag : flags) {
                    if (sb.length() > 0) {
                        sb.append(" ");
                    }

                    sb.append(String.format("%s=%s", flag.toString(), containsFlag(flag)));
                }
            }

            return String.format("%s [%s]", Integer.toBinaryString(toInt()), sb.toString());
        }

        protected Flags(int val) {
            super(val);
        }

        public static Flags valueOf(int val) {
            return new Flags(val);
        }
    }

    private Flags _flags = Flags.valueOf(0);

    public Flags getFlags() {
        return _flags;
    }

    public void setFlags(Flags val) {
        _flags = val;
    }

    public boolean getFlag(MapFlag flag) {
        return _flags.containsFlag(flag);
    }

    public void setFlag(MapFlag flag, boolean val) {
        _flags.setFlag(flag, val);
    }

    private Tileset _tileset;

    @Nonnull
    public Tileset getTileset() {
        return _tileset;
    }

    public void setTileset(@Nonnull Tileset val) {
        _tileset = val;
    }

    public static class LoadingScreen {
        private LoadingScreenBackground _background;

        public LoadingScreenBackground getBackground() {
            return _background;
        }

        public void setBackground(LoadingScreenBackground val) {
            _background = val;
        }

        private String _text;

        public String getText() {
            return _text;
        }

        public void setText(String val) {
            _text = val;
        }

        private String _title;

        public String getTitle() {
            return _title;
        }

        public void setTitle(String val) {
            _title = val;
        }

        private String _subtitle;

        public String getSubtitle() {
            return _subtitle;
        }

        public void setSubtitle(String val) {
            _subtitle = val;
        }

        private int _index = 0;

        public int getIndex() {
            return _index;
        }

        public void setIndex(int val) {
            _index = val;
        }

        @Override
        public String toString() {
            return String.format("background=%s text=%s title=%s subtitle=%s index=%d", getBackground(), getText(), getTitle(), getSubtitle(), getIndex());
        }

        private void set(int backgroundIndex, String customPath, String text, String title, String subtitle, int index) {
            LoadingScreenBackground background = null;

            if (backgroundIndex < 0) {
                if ((customPath != null) && !customPath.isEmpty()) {
                    background = new LoadingScreenBackground.CustomBackground(new File(customPath));
                }
            } else if (backgroundIndex > 0) {
                background = LoadingScreenBackground.PresetBackground.valueOf(backgroundIndex);
            }

            setBackground(background);
            setText(text);
            setTitle(title);
            setSubtitle(subtitle);
            setIndex(index);
        }

        private void read_0x12(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
            set(
                    stream.readInt32("campaignBackgroundIndex"),
                    null,
                    stream.readString("loadingScreenText"),
                    stream.readString("loadingScreenTitle"),
                    stream.readString("loadingScreenSubtitle"),
                    stream.readInt32("loadingScreenIndex")
            );
        }

        private void write_0x12(@Nonnull Wc3BinOutputStream stream) {
            LoadingScreenBackground background = getBackground();

            stream.writeInt32(background instanceof LoadingScreenBackground.PresetBackground ? ((LoadingScreenBackground.PresetBackground) background).getIndex
                    () : -1);
            stream.writeString(getText());
            stream.writeString(getTitle());
            stream.writeString(getSubtitle());
            stream.writeInt32(getIndex());
        }

        private void read_0x19(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            set(
                    stream.readInt32("campaignBackgroundIndex"),
                    stream.readString("loadingScreenModel"),
                    stream.readString("loadingScreenText"),
                    stream.readString("loadingScreenTitle"),
                    stream.readString("loadingScreenSubtitle"),
                    -1
            );
        }

        private void write_0x19(@Nonnull Wc3BinOutputStream stream) {
            LoadingScreenBackground background = getBackground();

            if (background != null) {
                stream.writeInt32(background instanceof LoadingScreenBackground.PresetBackground ? ((LoadingScreenBackground.PresetBackground) background)
                        .getIndex() : -1);
                stream.writeString(background instanceof LoadingScreenBackground.CustomBackground ? ((LoadingScreenBackground.CustomBackground) background)
                        .getCustomPath().toString() : null);
            } else {
                stream.writeInt32(-1);
                stream.writeString((String) null);
            }

            stream.writeString(getText());
            stream.writeString(getTitle());
            stream.writeString(getSubtitle());
        }

        private void read_0x1C(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            set(
                    stream.readInt32("campaignBackgroundIndex"),
                    stream.readString("loadingScreenModel"),
                    stream.readString("loadingScreenText"),
                    stream.readString("loadingScreenTitle"),
                    stream.readString("loadingScreenSubtitle"),
                    -1
            );
        }

        private void write_0x1C(@Nonnull Wc3BinOutputStream stream) {
            LoadingScreenBackground background = getBackground();

            if (background != null) {
                stream.writeInt32(background instanceof LoadingScreenBackground.PresetBackground ? ((LoadingScreenBackground.PresetBackground) background)
                        .getIndex() : -1);
                stream.writeString(background instanceof LoadingScreenBackground.CustomBackground ? ((LoadingScreenBackground.CustomBackground) background)
                        .getCustomPath().toString() : null);
            } else {
                stream.writeInt32(-1);
                stream.writeString((String) null);
            }

            stream.writeString(getText());
            stream.writeString(getTitle());
            stream.writeString(getSubtitle());
        }

        private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
            switch (format.toEnum()) {
                case W3I_0x1F:
                case W3I_0x1C:
                case W3I_0x19: {
                    read_0x19(stream);

                    break;
                }
                case W3I_0x12: {
                    read_0x12(stream);

                    break;
                }
            }
        }

        private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
            switch (format.toEnum()) {
                case AUTO:
                case W3I_0x1F:
                case W3I_0x1C:
                case W3I_0x19: {
                    write_0x19(stream);

                    break;
                }
                case W3I_0x12: {
                    write_0x12(stream);

                    break;
                }
            }
        }

        public LoadingScreen(LoadingScreenBackground background, String text, String title, String subtitle, int index) {
            setBackground(background);
            setText(text);
            setTitle(title);
            setSubtitle(subtitle);
            setIndex(index);
        }

        public LoadingScreen(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
            read(stream, format);
        }
    }

    private LoadingScreen _loadingScreen;

    public LoadingScreen getLoadingScreen() {
        return _loadingScreen;
    }

    public void setLoadingScreen(LoadingScreen val) {
        _loadingScreen = val;
    }

    public class PrologueScreen {
        private String _path;

        public String getPath() {
            return _path;
        }

        public void setPath(String val) {
            _path = val;
        }

        private String _text;

        public String getText() {
            return _text;
        }

        public void setText(String val) {
            _text = val;
        }

        private String _title;

        public String getTitle() {
            return _title;
        }

        public void setTitle(String val) {
            _title = val;
        }

        private String _subtitle;

        public String getSubtitle() {
            return _subtitle;
        }

        public void setSubtitle(String val) {
            _subtitle = val;
        }

        @Override
        public String toString() {
            return String.format("path=%s text=%s title=%s subtitle=%s", getPath(), getText(), getTitle(), getSubtitle());
        }

        public PrologueScreen(String path, String text, String title, String subtitle) {
            setPath(path);
            setText(text);
            setTitle(title);
            setSubtitle(subtitle);
        }
    }

    private PrologueScreen _prologueScreen;

    @Nullable
    public PrologueScreen getPrologueScreen() {
        return _prologueScreen;
    }

    public void setPrologueScreen(@Nullable PrologueScreen val) {
        _prologueScreen = val;
    }

    private String _loadingScreenModel;

    @Nullable
    public String getLoadingScreenModel() {
        return _loadingScreenModel;
    }

    public void setLoadingScreenModel(@Nullable String val) {
        _loadingScreenModel = val;
    }

    public static class GameDataSet {
        private final static Map<Integer, GameDataSet> _map = new LinkedHashMap<>();

        public final static GameDataSet STANDARD = new GameDataSet(0, "DEFAULT");
        public final static GameDataSet CUSTOM_V1 = new GameDataSet(1, "CUSTOM_V1");
        public final static GameDataSet MELEE_V1 = new GameDataSet(2, "MELEE_V1");

        private int _index;

        public int getIndex() {
            return _index;
        }

        @Nullable
        public static GameDataSet valueOf(int index) {
            return _map.getOrDefault(index, STANDARD);
        }

        private String _label;

        public String getLabel() {
            return _label;
        }

        @Override
        public String toString() {
            return _label;
        }

        protected GameDataSet(int index, String label) {
            _map.put(index, this);

            _index = index;
            _label = String.format("WESTRING_GAMEDATASET_%s", label);
        }
    }

    private GameDataSet _gameData = GameDataSet.STANDARD;

    @Nonnull
    public GameDataSet getGameDataSet() {
        return _gameData;
    }

    public static File getGameDataSetPath(@Nonnull GameDataSet dataSet, boolean isMeleeMap) {
        if (dataSet.equals(GameDataSet.CUSTOM_V1)) {
            return new File("Custom_V1");
        } else if (dataSet.equals(GameDataSet.MELEE_V1)) {
            return new File("Melee_V0");
        }

        if (isMeleeMap) return new File("Melee_V0");

        return new File("Custom_V1");
    }

    public File getGameDataSetPath() {
        return getGameDataSetPath(_gameData, getFlag(MapFlag.MELEE_MAP));
    }

    public void setGameDataSet(@Nonnull GameDataSet val) {
        _gameData = val;
    }

    private TerrainFog _terrainFog;

    @Nullable
    public TerrainFog getTerrainFog() {
        return _terrainFog;
    }

    public void setTerrainFog(@Nullable TerrainFog val) {
        _terrainFog = val;
    }

    private Id _globalWeatherId;

    @Nullable
    public Id getGlobalWeatherId() {
        return _globalWeatherId;
    }

    public void setGlobalWeatherId(@Nullable WeatherId val) {
        _globalWeatherId = val;
    }

    private SoundLabel _soundEnv;

    @Nullable
    public SoundLabel getSoundEnv() {
        return _soundEnv;
    }

    public void setSoundEnv(@Nullable SoundLabel val) {
        _soundEnv = val;
    }

    private Tileset _tilesetLightEnv;

    @Nullable
    public Tileset getTilesetLightEnv() {
        return _tilesetLightEnv;
    }

    public void setTilesetLightEnv(@Nullable Tileset val) {
        _tilesetLightEnv = val;
    }

    private Color _waterColor = Color.fromRGBA255(255, 255, 255, 255);

    @Nonnull
    public Color getWaterColor() {
        return _waterColor;
    }

    public void setWaterColor(@Nonnull Color val) {
        _waterColor = val;
    }

    public enum ScriptLang {
        JASS,
        LUA
    }

    private ScriptLang _scriptLang = ScriptLang.JASS;

    @Nonnull
    public ScriptLang getScriptLang() {
        return _scriptLang;
    }

    public void setScriptLang(@Nonnull ScriptLang val) {
        _scriptLang = val;
    }

    public enum Graphics {
        SD,
        HD,
        SD_AND_HD
    }

    private Graphics _graphics = Graphics.SD_AND_HD;

    @Nonnull
    public Graphics getGraphics() {
        return _graphics;
    }

    public void setGraphics(@Nonnull Graphics val) {
        _graphics = val;
    }

    public enum GameDataVersion {
        ROC,
        TFT
    }

    private GameDataVersion _gameDataVersion = GameDataVersion.TFT;

    @Nonnull
    public GameDataVersion getGameDataVersion() {
        return _gameDataVersion;
    }

    public void setGameDataVersion(@Nonnull GameDataVersion val) {
        _gameDataVersion = val;
    }

    public static class Player {
        private int _num = 0;

        public int getNum() {
            return _num;
        }

        public void setNum(int val) {
            _num = val;
        }

        private Controller _type = Controller.USER;

        @Nonnull
        public Controller getType() {
            return _type;
        }

        public void setType(@Nonnull Controller val) {
            _type = val;
        }

        public static class UnitRace extends War3Int {
            private final static Map<Integer, UnitRace> _map = new LinkedHashMap<>();
            private final static Map<String, UnitRace> _smap = new LinkedHashMap<>();

            public final static UnitRace NIGHT_ELF = new UnitRace(4, "NIGHT_ELF", "RACE_PREF_NIGHTELF");
            public final static UnitRace HUMAN = new UnitRace(1, "HUMAN", "RACE_PREF_HUMAN");
            public final static UnitRace ORC = new UnitRace(2, "ORC", "RACE_PREF_ORC");
            public final static UnitRace SELECTABLE = new UnitRace(0, "SELECTABLE", "RACE_PREF_RANDOM");
            public final static UnitRace UNDEAD = new UnitRace(3, "UNDEAD", "RACE_PREF_UNDEAD");

            private String _label;

            public String getLabel() {
                return _label;
            }

            private String _jassExpr;

            public String getJassExpr() {
                return _jassExpr;
            }

            @Override
            public String toString() {
                return getLabel();
            }

            public UnitRace(int val, String label, String jassExpr) {
                super(val);

                _map.put(val, this);
                _smap.put(label, this);

                _label = label;
                _jassExpr = jassExpr;
            }

            @Nullable
            public static UnitRace valueOf(@Nonnull Integer val) {
                return _map.get(val);
            }

            @Nullable
            public static UnitRace valueOf(@Nonnull String val) {
                return _smap.get(val);
            }

			/*public static UnitRace decodeStatic(Object val) {
				try {
					return valueOf(Integer.parseInt(val.toString()));
				} catch (NumberFormatException e) {
				}
				
				return null;
			}*/
        }

        private UnitRace _race = UnitRace.SELECTABLE;

        @Nonnull
        public UnitRace getRace() {
            return _race;
        }

        public void setRace(@Nonnull UnitRace val) {
            _race = val;
        }

        private int _startPosFixed = 0;

        public int getStartPosFixed() {
            return _startPosFixed;
        }

        public void setStartPosFixed(int val) {
            _startPosFixed = val;
        }

        private String _name;

        public String getName() {
            return _name;
        }

        public void setName(String val) {
            _name = val;
        }

        private Coords2DF _startPos = new Coords2DF(0F, 0F);

        @Nonnull
        public Coords2DF getStartPos() {
            return _startPos;
        }

        public void setStartPos(@Nonnull Coords2DF val) {
            _startPos = val;
        }

        public static class AllyFlags extends FlagsInt {
            private AllyFlags(int val) {
                super(val);
            }

            private AllyFlags(@Nonnull BitSet val) {
                super(val);
            }

            public static AllyFlags valueOf(int val) {
                return new AllyFlags(val);
            }

            public static AllyFlags valueOf(@Nonnull BitSet val) {
                return new AllyFlags(val);
            }

            @Override
            public DataType decode(Object val) {
                // TODO
                return null;
            }

            @Override
            public Object toSLKVal() {
                // TODO
                return null;
            }

            @Override
            public Object toTXTVal() {
                // TODO
                return null;
            }
        }

        private FlagsInt _allyLowPrioFlags = AllyFlags.valueOf(new BitSet());

        public int getAllyLowPrioFlags() {
            return _allyLowPrioFlags.toInt();
        }

        public void setAllyLowPrioFlags(int val) {
            _allyLowPrioFlags.setVal(val);
        }

        public void setAllyLowPrioFlag(int index, boolean val) {
            _allyLowPrioFlags.setPos(index, val);
        }

        @Nonnull
        public Set<Integer> getAllyLowPrioPlayerNums() {
            return _allyLowPrioFlags.getPoses();
        }

        public void removeAllyLowPrioPlayers(int... players) {
            _allyLowPrioFlags.setPoses(Arrays.stream(players).boxed().collect(Collectors.toSet()), false);
        }

        public void addAllyLowPrioPlayerNums(int... players) {
            _allyLowPrioFlags.setPoses(Arrays.stream(players).boxed().collect(Collectors.toSet()), true);
        }

        private FlagsInt _allyHighPrioFlags = AllyFlags.valueOf(0);

        public int getAllyHighPrioFlags() {
            return _allyHighPrioFlags.toInt();
        }

        public void setAllyHighPrioFlags(int val) {
            _allyHighPrioFlags.setVal(val);
        }

        public void setAllyHighPrioFlag(int index, boolean val) {
            _allyHighPrioFlags.setPos(index, val);
        }

        @Nonnull
        public Set<Integer> getAllyHighPrioPlayerNums() {
            return _allyHighPrioFlags.getPoses();
        }

        public void removeAllyHighPrioPlayers(int... players) {
            _allyHighPrioFlags.setPoses(Arrays.stream(players).boxed().collect(Collectors.toSet()), false);
        }

        public void addAllyHighPrioPlayerNums(int... players) {
            _allyHighPrioFlags.setPoses(Arrays.stream(players).boxed().collect(Collectors.toSet()), true);
        }

        public static class EnemyFlags extends FlagsInt {
            private EnemyFlags(int val) {
                super(val);
            }

            private EnemyFlags(@Nonnull BitSet val) {
                super(val);
            }

            public static EnemyFlags valueOf(int val) {
                return new EnemyFlags(val);
            }

            public static EnemyFlags valueOf(@Nonnull BitSet val) {
                return new EnemyFlags(val);
            }

            @Override
            public DataType decode(Object val) {
                // TODO
                return null;
            }

            @Override
            public Object toSLKVal() {
                // TODO
                return null;
            }

            @Override
            public Object toTXTVal() {
                // TODO
                return null;
            }
        }

        private FlagsInt _enemyLowPrioFlags = EnemyFlags.valueOf(new BitSet());

        public int getEnemyLowPrioFlags() {
            return _enemyLowPrioFlags.toInt();
        }

        public void setEnemyLowPrioFlags(int val) {
            _enemyLowPrioFlags.setVal(val);
        }

        public void setEnemyLowPrioFlag(int index, boolean val) {
            _enemyLowPrioFlags.setPos(index, val);
        }

        @Nonnull
        public Set<Integer> getEnemyLowPrioPlayerNums() {
            return _enemyLowPrioFlags.getPoses();
        }

        public void removeEnemyLowPrioPlayers(int... players) {
            _enemyLowPrioFlags.setPoses(Arrays.stream(players).boxed().collect(Collectors.toSet()), false);
        }

        public void addEnemyLowPrioPlayerNums(int... players) {
            _enemyLowPrioFlags.setPoses(Arrays.stream(players).boxed().collect(Collectors.toSet()), true);
        }

        private FlagsInt _enemyHighPrioFlags = EnemyFlags.valueOf(0);

        public int getEnemyHighPrioFlags() {
            return _enemyHighPrioFlags.toInt();
        }

        public void setEnemyHighPrioFlags(int val) {
            _enemyHighPrioFlags.setVal(val);
        }

        public void setEnemyHighPrioFlag(int index, boolean val) {
            _enemyHighPrioFlags.setPos(index, val);
        }

        @Nonnull
        public Set<Integer> getEnemyHighPrioPlayerNums() {
            return _enemyHighPrioFlags.getPoses();
        }

        public void removeEnemyHighPrioPlayers(int... players) {
            _enemyHighPrioFlags.setPoses(Arrays.stream(players).boxed().collect(Collectors.toSet()), false);
        }

        public void addEnemyHighPrioPlayerNums(int... players) {
            _enemyHighPrioFlags.setPoses(Arrays.stream(players).boxed().collect(Collectors.toSet()), true);
        }

        @Override
        public String toString() {
            String allyLowPrioFlagsS = String.format("%12s", Integer.toBinaryString(getAllyLowPrioFlags()));
            String allyHighPrioFlagsS = String.format("%12s", Integer.toBinaryString(getAllyHighPrioFlags()));

            allyLowPrioFlagsS = allyLowPrioFlagsS.substring(allyLowPrioFlagsS.length() - 12).replaceAll(" ", "0");
            allyHighPrioFlagsS = allyHighPrioFlagsS.substring(allyHighPrioFlagsS.length() - 12).replaceAll(" ", "0");

            String enemyLowPrioFlagsS = String.format("%12s", Integer.toBinaryString(getEnemyLowPrioFlags()));
            String enemyHighPrioFlagsS = String.format("%12s", Integer.toBinaryString(getEnemyHighPrioFlags()));

            enemyLowPrioFlagsS = enemyLowPrioFlagsS.substring(enemyLowPrioFlagsS.length() - 12).replaceAll(" ", "0");
            enemyHighPrioFlagsS = enemyHighPrioFlagsS.substring(enemyHighPrioFlagsS.length() - 12).replaceAll(" ", "0");

            return String.format("%s [num=%d controller=%s race=%s startPosFixed=%s startPos=%s allyLowPrioFlags=%s allyHighPrioFlags=%s enemyLowPrioFlags=%s enemyHighPrioFlags=%s]", getName(), getNum
                    (), getType(), getRace(), getStartPosFixed(), getStartPos(), allyLowPrioFlagsS, allyHighPrioFlagsS, enemyLowPrioFlagsS, enemyHighPrioFlagsS);
        }

        private void read_0x12(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
            setNum(stream.readInt32("playerNum"));

            Controller controller = Controller.valueOf(stream.readInt32("controller"));

            if (controller != null) setType(controller);

            UnitRace race = UnitRace.valueOf(stream.readInt32("race"));

            if (race != null) setRace(race);

            setStartPosFixed(stream.readInt32("startPosFixed"));

            setName(stream.readString("playerName"));

            setStartPos(new Coords2DF(stream.readFloat32("startPosX"), stream.readFloat32("startPosY")));

            setAllyLowPrioFlags(stream.readInt32("allyLowPrioFlags"));
            setAllyHighPrioFlags(stream.readInt32("allyHighPrioFlags"));
        }

        private void read_0x1F(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
            setNum(stream.readInt32("playerNum"));

            Controller controller = Controller.valueOf(stream.readInt32("controller"));

            if (controller != null) setType(controller);

            UnitRace race = UnitRace.valueOf(stream.readInt32("race"));

            if (race != null) setRace(race);

            setStartPosFixed(stream.readInt32("startPosFixed"));

            setName(stream.readString("playerName"));

            setStartPos(new Coords2DF(stream.readFloat32("startPosX"), stream.readFloat32("startPosY")));

            setAllyLowPrioFlags(stream.readInt32("allyLowPrioFlags"));
            setAllyHighPrioFlags(stream.readInt32("allyHighPrioFlags"));

            setEnemyLowPrioFlags(stream.readInt32("enemyLowPrioFlags"));
            setEnemyHighPrioFlags(stream.readInt32("enemyHighPrioFlags"));
        }

        private void write_0x12(@Nonnull Wc3BinOutputStream stream) {
            stream.writeInt32(getNum());

            stream.writeInt32(getType().getVal());
            stream.writeInt32(getRace().getVal());

            stream.writeInt32(getStartPosFixed());

            stream.writeString(getName());

            Coords2DF startPos = getStartPos();

            stream.writeFloat32(startPos.getX());
            stream.writeFloat32(startPos.getY());

            stream.writeInt32(getAllyLowPrioFlags());
            stream.writeInt32(getAllyHighPrioFlags());
        }

        private void write_0x1F(@Nonnull Wc3BinOutputStream stream) {
            stream.writeInt32(getNum());

            stream.writeInt32(getType().getVal());
            stream.writeInt32(getRace().getVal());

            stream.writeInt32(getStartPosFixed());

            stream.writeString(getName());

            Coords2DF startPos = getStartPos();

            stream.writeFloat32(startPos.getX());
            stream.writeFloat32(startPos.getY());

            stream.writeInt32(getAllyLowPrioFlags());
            stream.writeInt32(getAllyHighPrioFlags());

            stream.writeInt32(getEnemyLowPrioFlags());
            stream.writeInt32(getEnemyHighPrioFlags());
        }

        private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
            switch (format.toEnum()) {
                case W3I_0x1F:
                    read_0x1F(stream);

                    break;
                case W3I_0x1C:
                case W3I_0x19:
                case W3I_0x12: {
                    read_0x12(stream);

                    break;
                }
            }
        }

        private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
            switch (format.toEnum()) {
                case AUTO:
                case W3I_0x1F:
                    write_0x1F(stream);

                    break;
                case W3I_0x1C:
                case W3I_0x19:
                case W3I_0x12: {
                    write_0x12(stream);

                    break;
                }
            }
        }

        public Player(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
            read(stream, format);
        }

        public Player() {
        }
    }

    private List<Player> _players = new ArrayList<>();

    @Nonnull
    public List<Player> getPlayers() {
        return _players;
    }

    @Nullable
    public Player getPlayerFromNum(int num) {
        for (Player p : _players) if (p.getNum() == num) return p;

        return null;
    }

    public void addPlayer(@Nonnull Player val) {
        _players.add(val);
    }

    public static class Force {
        public static class Flags extends FlagsInt {
            @Override
            public DataType decode(Object val) {
                // TODO
                return null;
            }

            @Override
            public Object toSLKVal() {
                // TODO
                return null;
            }

            @Override
            public Object toTXTVal() {
                // TODO
                return null;
            }

            public static class Flag extends FlagsInt.Flag {
                public final static Flag ALLIED = new Flag("allied", 0);
                public final static Flag ALLIED_VICTORY = new Flag("allied_victory", 1);
                public final static Flag SHARED_VISION = new Flag("shared_vision", 2);
                public final static Flag SHARED_UNIT_CONTROL = new Flag("shared_unit_control", 4);
                public final static Flag SHARED_UNIT_CONTROL_ADVANCED = new Flag("shared_unit_control_advanced", 5);

                private Flag(@Nonnull String label, int pos) {
                    super(label, pos);
                }
            }

            @Override
            public String toString() {
                return String.format("%5s", Integer.toBinaryString(toInt())).replaceAll(" ", "0");
            }

            protected Flags(int val) {
                super(val);
            }

            public static Flags valueOf(int val) {
                return new Flags(val);
            }
        }

        private Flags _flags = Flags.valueOf(0);

        @Nonnull
        public Flags getFlags() {
            return _flags;
        }

        public void setFlags(@Nonnull Flags val) {
            _flags = val;
        }

        public boolean getFlag(@Nonnull Flags.Flag flag) {
            return _flags.containsFlag(flag);
        }

        public void setFlag(@Nonnull Flags.Flag flag, boolean val) {
            _flags.setFlag(flag, val);
        }

        private BitSet _players = new BitSet();

        @Nonnull
        public BitSet getPlayers() {
            return _players;
        }

        public void setPlayers(@Nonnull BitSet val) {
            _players = val;
        }

        public Set<Integer> getPlayerNums() {
            Set<Integer> ret = new LinkedHashSet<>();

            ret.addAll(_players.stream().boxed().collect(Collectors.toList()));

            return ret;
        }

        public Set<Integer> getPlayerNums(@Nonnull List<Player> definedPlayers) {
            Set<Integer> definedPlayerNums = definedPlayers.stream().map(Player::getNum).collect(Collectors.toSet());

            Set<Integer> ret = getPlayerNums();

            ret.removeIf(playerNum -> !definedPlayerNums.contains(playerNum));

            return ret;
        }

        public void removePlayerNums(int... players) {
            for (int player : players) {
                _players.clear(player);
            }
        }

        public void addPlayerNums(int... players) {
            for (int player : players) {
                _players.set(player);
            }
        }

        private String _name;

        @Nullable
        public String getName() {
            return _name;
        }

        public void setName(@Nullable String val) {
            _name = val;
        }

        @Override
        public String toString() {
            String playersS = String.format("%12s", _players);

            playersS = playersS.substring(playersS.length() - 12).replaceAll(" ", "0");

            return String.format("name=%s players=%s flags=%s", getName(), playersS, getFlags());
        }

        private void read_0x12(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
            setFlags(Flags.valueOf(stream.readInt32("forceFlags")));

            long playersL = stream.readUInt32("forcePlayers");

            BitSet players = BitSet.valueOf(new long[]{playersL});

            setPlayers(players);

            setName(stream.readString("forceName"));
        }

        private void write_0x12(@Nonnull Wc3BinOutputStream stream) {
            stream.writeInt32(getFlags().toInt());

            long playersL = _players.toLongArray().length != 0 ? _players.toLongArray()[0] : Long.parseLong("0", 2);

            stream.writeUInt32(playersL);

            stream.writeString(getName());
        }

        private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
            switch (format.toEnum()) {
                case W3I_0x1F:
                case W3I_0x1C:
                case W3I_0x19:
                case W3I_0x12: {
                    read_0x12(stream);

                    break;
                }
            }
        }

        private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
            switch (format.toEnum()) {
                case AUTO:
                case W3I_0x1F:
                case W3I_0x1C:
                case W3I_0x19:
                case W3I_0x12: {
                    write_0x12(stream);

                    break;
                }
            }
        }

        public Force(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
            read(stream, format);
        }

        public Force() {
        }
    }

    private final List<Force> _forces = new ArrayList<>();

    @Nonnull
    public List<Force> getForces() {
        return new ArrayList<>(_forces);
    }

    public void addForce(@Nonnull Force val) {
        _forces.add(val);
    }

    public void removeForce(@Nonnull Force force) {
        _forces.remove(force);
    }

    public void clearForces() {
        _forces.clear();
    }

    public static class UpgradeMod {
        private int _players = 0;

        public int getPlayers() {
            return _players;
        }

        public void setPlayers(int val) {
            _players = val;
        }

        private Id _id;

        public Id getId() {
            return _id;
        }

        public void setId(Id val) {
            _id = val;
        }

        private int _level = 1;

        public int getLevel() {
            return _level;
        }

        public void setLevel(int val) {
            _level = val;
        }

        private int _avail = 0;

        public int getAvail() {
            return _avail;
        }

        public void setAvail(int val) {
            _avail = val;
        }

        @Override
        public String toString() {
            return String.format("avail=%s id=%s level=%s players=%s", _avail, _id, _level, _players);
        }

        private void read_0x12(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
            setPlayers(stream.readInt32("abilPlayers"));

            setId(stream.readId("abilId"));

            setLevel(stream.readInt32("level"));

            setAvail(stream.readInt32("avail"));
        }

        private void write_0x12(@Nonnull Wc3BinOutputStream stream) {
            stream.writeInt32(getPlayers());

            stream.writeId(getId());

            stream.writeInt32(getLevel());

            stream.writeInt32(getAvail());
        }

        private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
            switch (format.toEnum()) {
                case W3I_0x1F:
                case W3I_0x1C:
                case W3I_0x19:
                case W3I_0x12: {
                    read_0x12(stream);

                    break;
                }
            }
        }

        private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
            switch (format.toEnum()) {
                case AUTO:
                case W3I_0x1F:
                case W3I_0x1C:
                case W3I_0x19:
                case W3I_0x12: {
                    write_0x12(stream);

                    break;
                }
            }
        }

        public UpgradeMod(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
            read(stream, format);
        }

        public UpgradeMod() {
        }
    }

    private final List<UpgradeMod> _upgradeMods = new ArrayList<>();

    @Nonnull
    public List<UpgradeMod> getUpgradeMods() {
        return new ArrayList<>(_upgradeMods);
    }

    public void addUpgradeMod(@Nonnull UpgradeMod val) {
        _upgradeMods.add(val);
    }

    public static class TechMod {
        private int _players = 0;

        public int getPlayers() {
            return _players;
        }

        public void setPlayers(int val) {
            _players = val;
        }

        private Id _id;

        @Nonnull
        public Id getId() {
            return _id;
        }

        public void setId(@Nonnull Id val) {
            _id = val;
        }

        @Override
        public String toString() {
            return String.format("id=%s players=%s", _id, _players);
        }

        private void read_0x12(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
            setPlayers(stream.readInt32("techPlayers"));

            setId(stream.readId("techId"));
        }

        private void write_0x12(@Nonnull Wc3BinOutputStream stream) {
            stream.writeInt32(getPlayers());

            stream.writeId(getId());
        }

        private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
            switch (format.toEnum()) {
                case W3I_0x1F:
                case W3I_0x1C:
                case W3I_0x19:
                case W3I_0x12: {
                    read_0x12(stream);

                    break;
                }
            }
        }

        private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
            switch (format.toEnum()) {
                case AUTO:
                case W3I_0x1F:
                case W3I_0x1C:
                case W3I_0x19:
                case W3I_0x12: {
                    write_0x12(stream);

                    break;
                }
            }
        }

        public TechMod(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
            read(stream, format);
        }

        public TechMod() {
        }
    }

    private List<TechMod> _techMods = new ArrayList<>();

    @Nonnull
    public List<TechMod> getTechMods() {
        return new ArrayList<>(_techMods);
    }

    public void addTechMod(@Nonnull TechMod val) {
        _techMods.add(val);
    }

    public static class UnitTable {
        private int _index = 0;

        public int getIndex() {
            return _index;
        }

        public void setIndex(int val) {
            _index = val;
        }

        private String _name;

        @Nullable
        public String getName() {
            return _name;
        }

        public void setName(@Nullable String val) {
            _name = val;
        }

        public enum PositionType {
            UNIT(0),
            STRUCTURE(1),
            ITEM(2);

            private int _val;

            public int getVal() {
                return _val;
            }

            private final static Map<Integer, PositionType> _valToPositionTypeMap = new LinkedHashMap<>();

            PositionType(int val) {
                _val = val;
            }

            @Nullable
            public static PositionType valueOf(int val) {
                return _valToPositionTypeMap.get(val);
            }

            static {
                _valToPositionTypeMap.put(0, UNIT);
                _valToPositionTypeMap.put(1, STRUCTURE);
                _valToPositionTypeMap.put(2, ITEM);
            }
        }

        private Map<Integer, PositionType> _positionTypes = new LinkedHashMap<>();

        @Nonnull
        public PositionType getPositionType(int index) {
            return _positionTypes.get(index);
        }

        public void setPositionType(int index, @Nonnull PositionType val) {
            _positionTypes.put(index, val);
        }

        public static class Set {
            private UnitTable _parent;

            private int _chance = 100;

            public int getChance() {
                return _chance;
            }

            public void setChance(int val) {
                _chance = val;
            }

            private Map<Integer, Id> _typeIds = new LinkedHashMap<>();

            public Id getTypeId(int pos) {
                return _typeIds.get(pos);
            }

            public void setTypeId(int pos, Id val) {
                _typeIds.put(pos, val);
            }

            @Override
            public String toString() {
                return String.format("chance=%s typeIds=[%s]", _chance, _typeIds.entrySet().stream().map(entry -> String.format("%s->%s", entry.getKey(), entry.getValue())).collect(Collectors.joining(" ")));
            }

            private void read_0x12(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
                setChance(stream.readInt32("chance"));

                for (int i = 0; i < _parent._positionTypes.size(); i++) {
                    setTypeId(i, stream.readId("typeId"));
                }
            }

            private void write_0x12(@Nonnull Wc3BinOutputStream stream) {
                stream.writeInt32(getChance());

                for (int i = 0; i < _parent._positionTypes.size(); i++) {
                    stream.writeId(getTypeId(i));
                }
            }

            private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
                switch (format.toEnum()) {
                    case W3I_0x1F:
                    case W3I_0x1C:
                    case W3I_0x19:
                    case W3I_0x12: {
                        read_0x12(stream);

                        break;
                    }
                }
            }

            private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
                switch (format.toEnum()) {
                    case AUTO:
                    case W3I_0x1F:
                    case W3I_0x1C:
                    case W3I_0x19:
                    case W3I_0x12: {
                        write_0x12(stream);

                        break;
                    }
                }
            }

            public Set(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format, @Nonnull UnitTable parent) throws BinInputStream.StreamException {
                _parent = parent;

                read(stream, format);
            }

            public Set(int chance) {
                _chance = chance;
            }
        }

        private List<Set> _sets = new ArrayList<>();

        @Nonnull
        public List<Set> getSets() {
            return new ArrayList<>(_sets);
        }

        public void addSet(@Nonnull Set val) {
            _sets.add(val);
        }

        @Override
        public String toString() {
            return String.format("index=%s name=%s positionTypes=[%s] sets=[%s]", _index, _name, _positionTypes.entrySet().stream().map(entry -> String.format("%s->%s", entry.getKey(), entry.getValue())).collect(Collectors.joining(" ")), _sets.stream().map(Set::toString).collect(Collectors.joining(" ")));
        }

        private void read_0x12(@Nonnull Wc3BinInputStream stream) throws Exception {
            setIndex(stream.readInt32("index"));

            setName(stream.readString("name"));

            int positionsCount = stream.readInt32("posCount");

            for (int i = 0; i < positionsCount; i++) {
                int posTypeI = stream.readInt32("posType");

                PositionType posType = PositionType.valueOf(posTypeI);

                if (posType == null) throw new Exception(String.format("unknown type %x", posTypeI));

                setPositionType(i, posType);
            }

            int setsCount = stream.readInt32("setsCount");

            for (int i = 0; i < setsCount; i++) {
                addSet(new Set(stream, EncodingFormat.W3I_0x19, this));
            }
        }

        private void write_0x12(@Nonnull Wc3BinOutputStream stream) {
            int positionsCount = _positionTypes.size();

            stream.writeInt32(positionsCount);

            for (int i = 0; i < positionsCount; i++) {
                stream.writeInt32(getPositionType(i).getVal());
            }

            for (Set set : _sets) {
                set.write(stream, EncodingFormat.W3I_0x19);
            }
        }

        private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws Exception {
            switch (format.toEnum()) {
                case W3I_0x1F:
                case W3I_0x1C:
                case W3I_0x19:
                case W3I_0x12: {
                    read_0x12(stream);

                    break;
                }
            }
        }

        private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
            switch (format.toEnum()) {
                case AUTO:
                case W3I_0x1F:
                case W3I_0x1C:
                case W3I_0x19:
                case W3I_0x12: {
                    write_0x12(stream);

                    break;
                }
            }
        }

        public UnitTable(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws Exception {
            read(stream, format);
        }

        public UnitTable(int index, @Nonnull String name) {
            _index = index;
            _name = name;
        }
    }

    private List<UnitTable> _unitTables = new ArrayList<>();

    @Nonnull
    public List<UnitTable> getUnitTables() {
        return new ArrayList<>(_unitTables);
    }

    public void addUnitTable(@Nonnull UnitTable val) {
        _unitTables.add(val);
    }

    public static class ItemTable {
        private int _index = 0;

        public int getIndex() {
            return _index;
        }

        public void setIndex(int val) {
            _index = val;
        }

        private String _name;

        public String getName() {
            return _name;
        }

        public void setName(String val) {
            _name = val;
        }

        public static class Set {
            public static class Item {
                private int _chance = 100;

                public int getChance() {
                    return _chance;
                }

                public void setChance(int val) {
                    _chance = val;
                }

                private Id _typeId;

                @Nonnull
                public Id getTypeId() {
                    return _typeId;
                }

                public void setTypeId(@Nonnull Id val) {
                    _typeId = val;
                }

                @Override
                public String toString() {
                    return String.format("chance=%s typeId=%s", _chance, _typeId);
                }

                private void read_0x19(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
                    setChance(stream.readInt32("chance"));

                    setTypeId(stream.readId("typeId"));
                }

                private void write_0x19(@Nonnull Wc3BinOutputStream stream) {
                    stream.writeInt32(getChance());

                    stream.writeId(getTypeId());
                }

                private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
                    switch (format.toEnum()) {
                        case W3I_0x1F:
                        case W3I_0x1C:
                        case W3I_0x19: {
                            read_0x19(stream);

                            break;
                        }
                    }
                }

                private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
                    switch (format.toEnum()) {
                        case AUTO:
                        case W3I_0x1F:
                        case W3I_0x1C:
                        case W3I_0x19: {
                            write_0x19(stream);

                            break;
                        }
                    }
                }

                public Item(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
                    read(stream, format);
                }

                public Item(@Nonnull Id typeId, int chance) {
                    _typeId = typeId;
                    _chance = chance;
                }
            }

            private List<Item> _items = new ArrayList<>();

            @Nonnull
            public List<Item> getItems() {
                return new ArrayList<>(_items);
            }

            public void addItem(@Nonnull Item val) {
                _items.add(val);
            }

            @Override
            public String toString() {
                return String.format("items=[%s]", _items.stream().map(Item::toString).collect(Collectors.joining(" ")));
            }

            private void read_0x19(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
                int itemsCount = stream.readInt32("itemsCount");

                for (int i = 0; i < itemsCount; i++) {
                    addItem(new Item(stream, EncodingFormat.W3I_0x19));
                }
            }

            private void write_0x19(@Nonnull Wc3BinOutputStream stream) {
                stream.writeInt32(_items.size());

                for (Item item : _items) {
                    item.write(stream, EncodingFormat.W3I_0x19);
                }
            }

            private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
                switch (format.toEnum()) {
                    case W3I_0x1F:
                    case W3I_0x1C:
                    case W3I_0x19: {
                        read_0x19(stream);

                        break;
                    }
                }
            }

            private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
                switch (format.toEnum()) {
                    case AUTO:
                    case W3I_0x1F:
                    case W3I_0x1C:
                    case W3I_0x19: {
                        write_0x19(stream);

                        break;
                    }
                }
            }

            public Set(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
                read(stream, format);
            }

            public Set() {
            }
        }

        private List<Set> _sets = new ArrayList<>();

        @Nonnull
        public List<Set> getSets() {
            return new ArrayList<>(_sets);
        }

        public void addSet(@Nonnull Set val) {
            _sets.add(val);
        }

        @Override
        public String toString() {
            return String.format("index=%s name=%s sets=[%s]", _index, _name, _sets.stream().map(Set::toString).collect(Collectors.joining(" ")));
        }

        private void read_0x19(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
            setIndex(stream.readInt32("index"));

            setName(stream.readString("name"));

            int setsCount = stream.readInt32("setsCount");

            for (int i = 0; i < setsCount; i++) {
                addSet(new Set(stream, EncodingFormat.W3I_0x19));
            }
        }

        private void write_0x19(@Nonnull Wc3BinOutputStream stream) {
            stream.writeInt32(getIndex());

            stream.writeString(getName());

            stream.writeInt32(_sets.size());

            for (Set set : _sets) {
                set.write(stream, EncodingFormat.W3I_0x19);
            }
        }

        private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
            switch (format.toEnum()) {
                case W3I_0x1F:
                case W3I_0x1C:
                case W3I_0x19: {
                    read_0x19(stream);

                    break;
                }
            }
        }

        private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
            switch (format.toEnum()) {
                case AUTO:
                case W3I_0x1F:
                case W3I_0x1C:
                case W3I_0x19: {
                    write_0x19(stream);

                    break;
                }
            }
        }

        public ItemTable(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
            read(stream, format);
        }

        public ItemTable(int index, @Nonnull String name) {
            _index = index;
            _name = name;
        }
    }

    private List<ItemTable> _itemTables = new ArrayList<>();

    @Nonnull
    public List<ItemTable> getItemTables() {
        return new ArrayList<>(_itemTables);
    }

    public void addItemTable(@Nonnull ItemTable val) {
        _itemTables.add(val);
    }

    public void print(@Nonnull PrintStream outStream) {
        outStream.println(String.format("savesAmount: %s", getSavesAmount()));
        outStream.println(String.format("editorVersion: %d", getEditorVersion()));

        outStream.println(String.format("mapName: %s", getMapName()));
        outStream.println(String.format("mapAuthor: %s", getMapAuthor()));
        outStream.println(String.format("mapDescription: %s", getMapDescription()));
        outStream.println(String.format("playersRecommended: %s", getPlayersRecommendedAmount()));

        outStream.println(String.format("camBounds: [%s,%s,%s,%s]", getCameraBounds1(), getCameraBounds2(), getCameraBounds3(), getCameraBounds4()));
        outStream.println(String.format("margins: %s", getMargins()));
        outStream.println(String.format("dimensions: [width=%d height=%d]", getWidth(), getHeight()));

        outStream.println(String.format("flags: %s", getFlags()));
        outStream.println(String.format("tileset: %s", getTileset()));

        outStream.println(String.format("loadingScreen: %s", getLoadingScreen()));

        outStream.println(String.format("gameDataSet: %s", getGameDataSet()));
        outStream.println(String.format("gameDataVersion: %s", getGameDataVersion()));
        outStream.println(String.format("scriptLang: %s", getScriptLang()));
        outStream.println(String.format("graphics: %s", getGraphics()));

        outStream.println(String.format("prologueScreen: %s", getPrologueScreen()));

        outStream.println(String.format("terrainFog: %s", getTerrainFog()));

        outStream.println(String.format("globalWeatherId: %s", getGlobalWeatherId()));
        outStream.println(String.format("soundEnv: %s", getSoundEnv()));
        outStream.println(String.format("tilesetLightEnv: %s", getTilesetLightEnv()));

        outStream.println(String.format("waterColor: %s", getWaterColor()));

        outStream.println(String.format("players: [%s]", getPlayers().stream().map(Player::toString).collect(Collectors.joining(" "))));
        outStream.println(String.format("forces: [%s]", getForces().stream().map(Force::toString).collect(Collectors.joining(" "))));
        outStream.println(String.format("upgradeMods: [%s]", getUpgradeMods().stream().map(UpgradeMod::toString).collect(Collectors.joining(" "))));
        outStream.println(String.format("techMods: [%s]", getTechMods().stream().map(TechMod::toString).collect(Collectors.joining(" "))));
        outStream.println(String.format("unitTables: [%s]", getUnitTables().stream().map(UnitTable::toString).collect(Collectors.joining(" "))));
        outStream.println(String.format("itemTables: [%s]", getItemTables().stream().map(ItemTable::toString).collect(Collectors.joining(" "))));
    }

    public void print() {
        print(System.out);
    }

    public static class EncodingFormat extends Format<EncodingFormat.Enum> {
        public enum Enum {
            AUTO,
            W3I_0x1F,
            W3I_0x1C,
            W3I_0x19,
            W3I_0x12,
        }

        public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
        public final static EncodingFormat W3I_0x1F = new EncodingFormat(Enum.W3I_0x1F, 0x1F);
        public final static EncodingFormat W3I_0x1C = new EncodingFormat(Enum.W3I_0x1C, 0x1C);
        public final static EncodingFormat W3I_0x19 = new EncodingFormat(Enum.W3I_0x19, 0x19);
        public final static EncodingFormat W3I_0x12 = new EncodingFormat(Enum.W3I_0x12, 0x12);

        @Nullable
        public static EncodingFormat valueOf(@Nonnull Integer version) {
            return get(EncodingFormat.class, version);
        }

        private EncodingFormat(@Nonnull Enum enumVal, int version) {
            super(enumVal, version);
        }
    }

    private void read_0x12(@Nonnull Wc3BinInputStream stream) throws Exception {
        _fileVersion = stream.readInt32("version");

        stream.checkFormatVersion(EncodingFormat.W3I_0x12.getVersion(), _fileVersion);

        set(State.SAVES_AMOUNT, War3Int.valueOf(stream.readInt32("savesAmount")));
        setEditorVersion(stream.readInt32("editorVersion"));
        setMapName(stream.readString("mapName"));
        setMapAuthor(stream.readString("mapAuthor"));
        setMapDescription(stream.readString("mapDescription"));
        setPlayersRecommendedAmount(stream.readString("playersRecommendedAmount"));

        setCameraBounds(new Coords2DF(stream.readFloat32("camA"), stream.readFloat32("camB")), new Coords2DF(stream.readFloat32("camC"), stream.readFloat32(
                "camD")),
                new Coords2DF(stream.readFloat32("camE"), stream.readFloat32("camF")), new Coords2DF(stream.readFloat32("camG"), stream.readFloat32("camH")));
        setMargins(new Bounds(-stream.readInt32("marginA"), stream.readInt32("marginB"), -stream.readInt32("marginC"), stream.readInt32("marginD")));

        setDimensions(stream.readInt32("width"), stream.readInt32("height"));

        setFlags(Flags.valueOf(stream.readInt32("flags")));

        setTileset(Tileset.valueOf(stream.readChar("tileset")));

        setLoadingScreen(new LoadingScreen(stream, EncodingFormat.W3I_0x12));

        setPrologueScreen(new PrologueScreen(null, stream.readString("prologueScreenText"), stream.readString("prologueScreenTitle"), stream.readString
                ("prologueScreenSubtitle")));

        int playersCount = stream.readInt32("playersCount");

        for (int i = 0; i < playersCount; i++) {
            addPlayer(new Player(stream, EncodingFormat.W3I_0x12));
        }

        int forcesCount = stream.readInt32("forcesCount");

        for (int i = 0; i < forcesCount; i++) {
            addForce(new Force(stream, EncodingFormat.W3I_0x12));
        }

        int upgradeModsCount = stream.readInt32("upgradeModsCount");

        for (int i = 0; i < upgradeModsCount; i++) {
            addUpgradeMod(new UpgradeMod(stream, EncodingFormat.W3I_0x12));
        }

        int techModsCount = stream.readInt32("techModsCount");

        for (int i = 0; i < techModsCount; i++) {
            addTechMod(new TechMod(stream, EncodingFormat.W3I_0x12));
        }

        int unitTablesCount = stream.readInt32("unitTablesCount");

        for (int i = 0; i < unitTablesCount; i++) {
            addUnitTable(new UnitTable(stream, EncodingFormat.W3I_0x12));
        }
    }

    private void write_0x12(@Nonnull Wc3BinOutputStream stream) {
        stream.writeInt32(EncodingFormat.W3I_0x12.getVersion());

        stream.writeInt32(getSavesAmount());
        stream.writeInt32(getEditorVersion());
        stream.writeString(getMapName());
        stream.writeString(getMapAuthor());
        stream.writeString(getMapDescription());
        stream.writeString(getPlayersRecommendedAmount());

        Coords2DF camBounds1 = getCameraBounds1();
        Coords2DF camBounds2 = getCameraBounds2();
        Coords2DF camBounds3 = getCameraBounds3();
        Coords2DF camBounds4 = getCameraBounds4();

        stream.writeFloat32(camBounds1.getX());
        stream.writeFloat32(camBounds1.getY());
        stream.writeFloat32(camBounds2.getX());
        stream.writeFloat32(camBounds2.getY());
        stream.writeFloat32(camBounds3.getX());
        stream.writeFloat32(camBounds3.getY());
        stream.writeFloat32(camBounds4.getX());
        stream.writeFloat32(camBounds4.getY());

        stream.writeInt32(-getMargins().getMinX());
        stream.writeInt32(getMargins().getMaxX());
        stream.writeInt32(-getMargins().getMinY());
        stream.writeInt32(getMargins().getMaxY());

        stream.writeInt32(getWidth());
        stream.writeInt32(getHeight());

        stream.writeInt32(getFlags().toInt());

        stream.writeChar(getTileset().getChar());

        getLoadingScreen().write(stream, EncodingFormat.W3I_0x12);

        PrologueScreen prologueScreen = getPrologueScreen();

        stream.writeString(prologueScreen != null ? prologueScreen.getText() : null);
        stream.writeString(prologueScreen != null ? prologueScreen.getTitle() : null);
        stream.writeString(prologueScreen != null ? prologueScreen.getSubtitle() : null);

        stream.writeInt32(_players.size());

        for (Player player : _players) {
            player.write(stream, EncodingFormat.W3I_0x12);
        }

        stream.writeInt32(_forces.size());

        for (Force force : _forces) {
            force.write(stream, EncodingFormat.W3I_0x12);
        }

        stream.writeInt32(_upgradeMods.size());

        for (UpgradeMod upgradeMod : _upgradeMods) {
            upgradeMod.write(stream, EncodingFormat.W3I_0x12);
        }

        stream.writeInt32(_techMods.size());

        for (TechMod techMod : _techMods) {
            techMod.write(stream, EncodingFormat.W3I_0x12);
        }

        stream.writeInt32(_unitTables.size());

        for (UnitTable unitTable : _unitTables) {
            unitTable.write(stream, EncodingFormat.W3I_0x12);
        }
    }

    private void read_0x19(@Nonnull Wc3BinInputStream stream) throws Exception {
        _fileVersion = stream.readInt32("version");

        stream.checkFormatVersion(EncodingFormat.W3I_0x19.getVersion(), _fileVersion);

        set(State.SAVES_AMOUNT, War3Int.valueOf(stream.readInt32("savesAmount")));
        setEditorVersion(stream.readInt32("editorVersion"));
        setMapName(stream.readString("mapName"));
        setMapAuthor(stream.readString("mapAuthor"));
        setMapDescription(stream.readString("mapDescription"));
        setPlayersRecommendedAmount(stream.readString("playersRecommendedAmount"));

        setCameraBounds(new Coords2DF(
                        stream.readFloat32("camA"),
                        stream.readFloat32("camB")),
                new Coords2DF(
                        stream.readFloat32("camC"),
                        stream.readFloat32("camD")),
                new Coords2DF(
                        stream.readFloat32("camE"),
                        stream.readFloat32("camF")),
                new Coords2DF(stream.readFloat32("camG"),
                        stream.readFloat32("camH")
                ));
        setMargins(new Bounds(-stream.readInt32("marginA"), stream.readInt32("marginB"), -stream.readInt32("marginC"), stream.readInt32("marginD")));

        setDimensions(stream.readInt32("width"), stream.readInt32("height"));

        setFlags(Flags.valueOf(stream.readInt32("flags")));

        setTileset(Tileset.valueOf(stream.readChar("tileset")));

        setLoadingScreen(new LoadingScreen(stream, EncodingFormat.W3I_0x19));

        setGameDataSet(GameDataSet.valueOf(stream.readInt32("gameDataSet")));

        setPrologueScreen(new PrologueScreen(
                stream.readString("prologueScreenPath"),
                stream.readString("prologueScreenText"),
                stream.readString("prologueScreenTitle"),
                stream.readString("prologueScreenSubtitle")
        ));

        TerrainFogType terrainFogType = TerrainFogType.valueOf(stream.readInt32("terrainFogType"));
        War3Real terrainFogZStart = stream.readReal("terrainFogZStart");
        War3Real terrainFogZEnd = stream.readReal("terrainFogZEnd");
        War3Real terrainFogDensity = stream.readReal("terrainFogDensity");
        Color terrainFogColor = Color.fromRGBA255(stream.readUByte("terrainFogRed"), stream.readUByte("terrainFogGreen"), stream.readUByte("terrainFogBlue"),
                stream.readUByte("terrainFogAlpha"));

        setTerrainFog(new TerrainFog(terrainFogType, terrainFogZStart, terrainFogZEnd, terrainFogDensity, terrainFogColor));

        setGlobalWeatherId(WeatherId.valueOf(stream.readId("globalWeatherId")));
        setSoundEnv(SoundLabel.valueOf(stream.readString("soundEnv")));
        setTilesetLightEnv(Tileset.valueOf(stream.readChar("tilesetLightEnv")));

        setWaterColor(Color.fromRGBA255(
                stream.readUByte("waterRed"),
                stream.readUByte("waterGreen"),
                stream.readUByte("waterBlue"),
                stream.readUByte("waterAlpha")
        ));

        int playersCount = stream.readInt32("playersCount");

        for (int i = 0; i < playersCount; i++) {
            addPlayer(new Player(stream, EncodingFormat.W3I_0x19));
        }

        if (stream.eof()) return;

        int forcesCount = stream.readInt32("forcesCount");

        for (int i = 0; i < forcesCount; i++) {
            addForce(new Force(stream, EncodingFormat.W3I_0x19));
        }

        if (stream.eof()) return;

        if (stream.readUByte() == 0xFF) return;

        stream.rewind(1);

        int upgradeModsCount = stream.readInt32("upgradeModsCount");

        for (int i = 0; i < upgradeModsCount; i++) {
            addUpgradeMod(new UpgradeMod(stream, EncodingFormat.W3I_0x19));
        }

        if (stream.eof()) return;

        int techModsCount = stream.readInt32("techModsCount");

        for (int i = 0; i < techModsCount; i++) {
            addTechMod(new TechMod(stream, EncodingFormat.W3I_0x19));
        }

        if (stream.eof()) return;

        int unitTablesCount = stream.readInt32("unitTablesCount");

        for (int i = 0; i < unitTablesCount; i++) {
            addUnitTable(new UnitTable(stream, EncodingFormat.W3I_0x19));
        }

        if (stream.eof()) return;

        int itemTablesCount = stream.readInt32("itemTablesCount");

        for (int i = 0; i < itemTablesCount; i++) {
            addItemTable(new ItemTable(stream, EncodingFormat.W3I_0x19));
        }
    }

    private void write_0x19(@Nonnull Wc3BinOutputStream stream) {
        stream.writeInt32(EncodingFormat.W3I_0x19.getVersion());

        stream.writeInt32(getSavesAmount());
        stream.writeInt32(getEditorVersion());
        stream.writeString(getMapName());
        stream.writeString(getMapAuthor());
        stream.writeString(getMapDescription());
        stream.writeString(getPlayersRecommendedAmount());

        Coords2DF camBounds1 = getCameraBounds1();
        Coords2DF camBounds2 = getCameraBounds2();
        Coords2DF camBounds3 = getCameraBounds3();
        Coords2DF camBounds4 = getCameraBounds4();

        stream.writeFloat32(camBounds1.getX());
        stream.writeFloat32(camBounds1.getY());
        stream.writeFloat32(camBounds2.getX());
        stream.writeFloat32(camBounds2.getY());
        stream.writeFloat32(camBounds3.getX());
        stream.writeFloat32(camBounds3.getY());
        stream.writeFloat32(camBounds4.getX());
        stream.writeFloat32(camBounds4.getY());

        stream.writeInt32(-getMargins().getMinX());
        stream.writeInt32(getMargins().getMaxX());
        stream.writeInt32(-getMargins().getMinY());
        stream.writeInt32(getMargins().getMaxY());

        stream.writeInt32(getWidth());
        stream.writeInt32(getHeight());

        stream.writeInt32(getFlags().toInt());

        stream.writeChar(getTileset().getChar());

        getLoadingScreen().write(stream, EncodingFormat.W3I_0x19);

        stream.writeInt32(getGameDataSet().getIndex());

        PrologueScreen prologueScreen = getPrologueScreen();

        stream.writeString(prologueScreen != null ? prologueScreen.getPath() : null);
        stream.writeString(prologueScreen != null ? prologueScreen.getText() : null);
        stream.writeString(prologueScreen != null ? prologueScreen.getTitle() : null);
        stream.writeString(prologueScreen != null ? prologueScreen.getSubtitle() : null);

        TerrainFog terrainFog = getTerrainFog();

        TerrainFogType terrainFogType = terrainFog != null ? terrainFog.getType() : null;

        stream.writeInt32(terrainFogType != null ? terrainFogType.getVal() : 0);
        stream.writeReal(terrainFog != null ? terrainFog.getZStart() : null);
        stream.writeReal(terrainFog != null ? terrainFog.getZEnd() : null);
        stream.writeReal(terrainFog != null ? terrainFog.getDensity() : null);

        Color terrainFogColor = (terrainFog != null) ? terrainFog.getColor() : null;

        stream.writeUByte(terrainFogColor != null ? terrainFogColor.getRed255() : 0x00);
        stream.writeUByte(terrainFogColor != null ? terrainFogColor.getGreen255() : 0x00);
        stream.writeUByte(terrainFogColor != null ? terrainFogColor.getBlue255() : 0x00);
        stream.writeUByte(terrainFogColor != null ? terrainFogColor.getAlpha255() : 0x00);

        stream.writeId(getGlobalWeatherId());
        stream.writeString(getSoundEnv());
        stream.writeChar(getTilesetLightEnv() != null ? getTilesetLightEnv().getChar() : null);

        Color waterColor = getWaterColor();

        stream.writeUByte(waterColor.getRed255());
        stream.writeUByte(waterColor.getGreen255());
        stream.writeUByte(waterColor.getBlue255());
        stream.writeUByte(waterColor.getAlpha255());

        stream.writeInt32(_players.size());

        for (Player player : _players) {
            player.write(stream, EncodingFormat.W3I_0x19);
        }

        stream.writeInt32(_forces.size());

        for (Force force : _forces) {
            force.write(stream, EncodingFormat.W3I_0x19);
        }

        stream.writeInt32(_upgradeMods.size());

        for (UpgradeMod upgradeMod : _upgradeMods) {
            upgradeMod.write(stream, EncodingFormat.W3I_0x19);
        }

        stream.writeInt32(_techMods.size());

        for (TechMod techMod : _techMods) {
            techMod.write(stream, EncodingFormat.W3I_0x19);
        }

        stream.writeInt32(_unitTables.size());

        for (UnitTable unitTable : _unitTables) {
            unitTable.write(stream, EncodingFormat.W3I_0x19);
        }

        stream.writeInt32(_itemTables.size());

        for (ItemTable itemTable : _itemTables) {
            itemTable.write(stream, EncodingFormat.W3I_0x19);
        }
    }

    private void read_0x1C(@Nonnull Wc3BinInputStream stream) throws Exception {
        _fileVersion = stream.readInt32("version");

        stream.checkFormatVersion(EncodingFormat.W3I_0x1C.getVersion(), _fileVersion);

        set(State.SAVES_AMOUNT, War3Int.valueOf(stream.readInt32("savesAmount")));
        setEditorVersion(stream.readInt32("editorVersion"));

        setGameVersion_major(stream.readUInt32("gameVersion_major"));
        setGameVersion_minor(stream.readUInt32("gameVersion_minor"));
        setGameVersion_rev(stream.readUInt32("gameVersion_rev"));
        setGameVersion_build(stream.readUInt32("gameVersion_build"));

        setMapName(stream.readString("mapName"));
        setMapAuthor(stream.readString("mapAuthor"));
        setMapDescription(stream.readString("mapDescription"));
        setPlayersRecommendedAmount(stream.readString("playersRecommendedAmount"));

        setCameraBounds(new Coords2DF(
                        stream.readFloat32("camA"),
                        stream.readFloat32("camB")),
                new Coords2DF(
                        stream.readFloat32("camC"),
                        stream.readFloat32("camD")),
                new Coords2DF(
                        stream.readFloat32("camE"),
                        stream.readFloat32("camF")),
                new Coords2DF(stream.readFloat32("camG"),
                        stream.readFloat32("camH")
                ));
        setMargins(new Bounds(-stream.readInt32("marginA"), stream.readInt32("marginB"), -stream.readInt32("marginC"), stream.readInt32("marginD")));

        setDimensions(stream.readInt32("width"), stream.readInt32("height"));

        setFlags(Flags.valueOf(stream.readInt32("flags")));

        setTileset(Tileset.valueOf(stream.readChar("tileset")));

        setLoadingScreen(new LoadingScreen(stream, EncodingFormat.W3I_0x1C));

        setGameDataSet(GameDataSet.valueOf(stream.readInt32("gameDataSet")));

        setPrologueScreen(new PrologueScreen(
                stream.readString("prologueScreenPath"),
                stream.readString("prologueScreenText"),
                stream.readString("prologueScreenTitle"),
                stream.readString("prologueScreenSubtitle")
        ));

        TerrainFogType terrainFogType = TerrainFogType.valueOf(stream.readInt32("terrainFogType"));
        War3Real terrainFogZStart = stream.readReal("terrainFogZStart");
        War3Real terrainFogZEnd = stream.readReal("terrainFogZEnd");
        War3Real terrainFogDensity = stream.readReal("terrainFogDensity");
        Color terrainFogColor = Color.fromRGBA255(stream.readUByte("terrainFogRed"), stream.readUByte("terrainFogGreen"), stream.readUByte("terrainFogBlue"),
                stream.readUByte("terrainFogAlpha"));

        setTerrainFog(new TerrainFog(terrainFogType, terrainFogZStart, terrainFogZEnd, terrainFogDensity, terrainFogColor));

        setGlobalWeatherId(WeatherId.valueOf(stream.readId("globalWeatherId")));
        setSoundEnv(SoundLabel.valueOf(stream.readString("soundEnv")));
        setTilesetLightEnv(Tileset.valueOf(stream.readChar("tilesetLightEnv")));

        setWaterColor(Color.fromRGBA255(
                stream.readUByte("waterRed"),
                stream.readUByte("waterGreen"),
                stream.readUByte("waterBlue"),
                stream.readUByte("waterAlpha")
        ));

        setScriptLang(stream.readUInt32("scriptLang") == 0 ? ScriptLang.JASS : ScriptLang.LUA);

        int playersCount = stream.readInt32("playersCount");

        for (int i = 0; i < playersCount; i++) {
            addPlayer(new Player(stream, EncodingFormat.W3I_0x1C));
        }

        if (stream.eof()) return;

        int forcesCount = stream.readInt32("forcesCount");

        for (int i = 0; i < forcesCount; i++) {
            addForce(new Force(stream, EncodingFormat.W3I_0x1C));
        }

        if (stream.eof()) return;

        if (stream.readUByte() == 0xFF) return;

        stream.rewind(1);

        int upgradeModsCount = stream.readInt32("upgradeModsCount");

        for (int i = 0; i < upgradeModsCount; i++) {
            addUpgradeMod(new UpgradeMod(stream, EncodingFormat.W3I_0x1C));
        }

        if (stream.eof()) return;

        int techModsCount = stream.readInt32("techModsCount");

        for (int i = 0; i < techModsCount; i++) {
            addTechMod(new TechMod(stream, EncodingFormat.W3I_0x1C));
        }

        if (stream.eof()) return;

        int unitTablesCount = stream.readInt32("unitTablesCount");

        for (int i = 0; i < unitTablesCount; i++) {
            addUnitTable(new UnitTable(stream, EncodingFormat.W3I_0x1C));
        }

        if (stream.eof()) return;

        int itemTablesCount = stream.readInt32("itemTablesCount");

        for (int i = 0; i < itemTablesCount; i++) {
            addItemTable(new ItemTable(stream, EncodingFormat.W3I_0x1C));
        }
    }

    private void write_0x1C(@Nonnull Wc3BinOutputStream stream) {
        stream.writeInt32(EncodingFormat.W3I_0x1C.getVersion());

        stream.writeInt32(getSavesAmount());
        stream.writeInt32(getEditorVersion());

        stream.writeUInt32(getGameVersion_major());
        stream.writeUInt32(getGameVersion_minor());
        stream.writeUInt32(getGameVersion_rev());
        stream.writeUInt32(getGameVersion_build());

        stream.writeString(getMapName());
        stream.writeString(getMapAuthor());
        stream.writeString(getMapDescription());
        stream.writeString(getPlayersRecommendedAmount());

        Coords2DF camBounds1 = getCameraBounds1();
        Coords2DF camBounds2 = getCameraBounds2();
        Coords2DF camBounds3 = getCameraBounds3();
        Coords2DF camBounds4 = getCameraBounds4();

        stream.writeFloat32(camBounds1.getX());
        stream.writeFloat32(camBounds1.getY());
        stream.writeFloat32(camBounds2.getX());
        stream.writeFloat32(camBounds2.getY());
        stream.writeFloat32(camBounds3.getX());
        stream.writeFloat32(camBounds3.getY());
        stream.writeFloat32(camBounds4.getX());
        stream.writeFloat32(camBounds4.getY());

        stream.writeInt32(-getMargins().getMinX());
        stream.writeInt32(getMargins().getMaxX());
        stream.writeInt32(-getMargins().getMinY());
        stream.writeInt32(getMargins().getMaxY());

        stream.writeInt32(getWidth());
        stream.writeInt32(getHeight());

        stream.writeInt32(getFlags().toInt());

        stream.writeChar(getTileset().getChar());

        getLoadingScreen().write(stream, EncodingFormat.W3I_0x1C);

        stream.writeInt32(getGameDataSet().getIndex());

        PrologueScreen prologueScreen = getPrologueScreen();

        stream.writeString(prologueScreen != null ? prologueScreen.getPath() : null);
        stream.writeString(prologueScreen != null ? prologueScreen.getText() : null);
        stream.writeString(prologueScreen != null ? prologueScreen.getTitle() : null);
        stream.writeString(prologueScreen != null ? prologueScreen.getSubtitle() : null);

        TerrainFog terrainFog = getTerrainFog();

        TerrainFogType terrainFogType = terrainFog != null ? terrainFog.getType() : null;

        stream.writeInt32(terrainFogType != null ? terrainFogType.getVal() : 0);
        stream.writeReal(terrainFog != null ? terrainFog.getZStart() : null);
        stream.writeReal(terrainFog != null ? terrainFog.getZEnd() : null);
        stream.writeReal(terrainFog != null ? terrainFog.getDensity() : null);

        Color terrainFogColor = (terrainFog != null) ? terrainFog.getColor() : null;

        stream.writeUByte(terrainFogColor != null ? terrainFogColor.getRed255() : 0x00);
        stream.writeUByte(terrainFogColor != null ? terrainFogColor.getGreen255() : 0x00);
        stream.writeUByte(terrainFogColor != null ? terrainFogColor.getBlue255() : 0x00);
        stream.writeUByte(terrainFogColor != null ? terrainFogColor.getAlpha255() : 0x00);

        stream.writeId(getGlobalWeatherId());
        stream.writeString(getSoundEnv());
        stream.writeChar(getTilesetLightEnv() != null ? getTilesetLightEnv().getChar() : null);

        Color waterColor = getWaterColor();

        stream.writeUByte(waterColor.getRed255());
        stream.writeUByte(waterColor.getGreen255());
        stream.writeUByte(waterColor.getBlue255());
        stream.writeUByte(waterColor.getAlpha255());

        stream.writeUInt32(getScriptLang() == ScriptLang.LUA ? 1 : 0);

        stream.writeInt32(_players.size());

        for (Player player : _players) {
            player.write(stream, EncodingFormat.W3I_0x1C);
        }

        stream.writeInt32(_forces.size());

        for (Force force : _forces) {
            force.write(stream, EncodingFormat.W3I_0x1C);
        }

        stream.writeInt32(_upgradeMods.size());

        for (UpgradeMod upgradeMod : _upgradeMods) {
            upgradeMod.write(stream, EncodingFormat.W3I_0x1C);
        }

        stream.writeInt32(_techMods.size());

        for (TechMod techMod : _techMods) {
            techMod.write(stream, EncodingFormat.W3I_0x1C);
        }

        stream.writeInt32(_unitTables.size());

        for (UnitTable unitTable : _unitTables) {
            unitTable.write(stream, EncodingFormat.W3I_0x1C);
        }

        stream.writeInt32(_itemTables.size());

        for (ItemTable itemTable : _itemTables) {
            itemTable.write(stream, EncodingFormat.W3I_0x1C);
        }
    }

    private void read_0x1F(@Nonnull Wc3BinInputStream stream) throws Exception {
        _fileVersion = stream.readInt32("version");

        stream.checkFormatVersion(EncodingFormat.W3I_0x1F.getVersion(), _fileVersion);

        set(State.SAVES_AMOUNT, War3Int.valueOf(stream.readInt32("savesAmount")));
        setEditorVersion(stream.readInt32("editorVersion"));

        setGameVersion_major(stream.readUInt32("gameVersion_major"));
        setGameVersion_minor(stream.readUInt32("gameVersion_minor"));
        setGameVersion_rev(stream.readUInt32("gameVersion_rev"));
        setGameVersion_build(stream.readUInt32("gameVersion_build"));

        setMapName(stream.readString("mapName"));
        setMapAuthor(stream.readString("mapAuthor"));
        setMapDescription(stream.readString("mapDescription"));
        setPlayersRecommendedAmount(stream.readString("playersRecommendedAmount"));

        setCameraBounds(new Coords2DF(
                stream.readFloat32("camA"),
                stream.readFloat32("camB")),
            new Coords2DF(
                stream.readFloat32("camC"),
                stream.readFloat32("camD")),
            new Coords2DF(
                stream.readFloat32("camE"),
                stream.readFloat32("camF")),
            new Coords2DF(stream.readFloat32("camG"),
                stream.readFloat32("camH")
            ));
        setMargins(new Bounds(-stream.readInt32("marginA"), stream.readInt32("marginB"), -stream.readInt32("marginC"), stream.readInt32("marginD")));

        setDimensions(stream.readInt32("width"), stream.readInt32("height"));

        setFlags(Flags.valueOf(stream.readInt32("flags")));

        setTileset(Tileset.valueOf(stream.readChar("tileset")));

        setLoadingScreen(new LoadingScreen(stream, EncodingFormat.W3I_0x1F));

        setGameDataSet(GameDataSet.valueOf(stream.readInt32("gameDataSet")));

        setPrologueScreen(new PrologueScreen(
            stream.readString("prologueScreenPath"),
            stream.readString("prologueScreenText"),
            stream.readString("prologueScreenTitle"),
            stream.readString("prologueScreenSubtitle")
        ));

        TerrainFogType terrainFogType = TerrainFogType.valueOf(stream.readInt32("terrainFogType"));
        War3Real terrainFogZStart = stream.readReal("terrainFogZStart");
        War3Real terrainFogZEnd = stream.readReal("terrainFogZEnd");
        War3Real terrainFogDensity = stream.readReal("terrainFogDensity");
        Color terrainFogColor = Color.fromRGBA255(stream.readUByte("terrainFogRed"), stream.readUByte("terrainFogGreen"), stream.readUByte("terrainFogBlue"),
            stream.readUByte("terrainFogAlpha"));

        setTerrainFog(new TerrainFog(terrainFogType, terrainFogZStart, terrainFogZEnd, terrainFogDensity, terrainFogColor));

        setGlobalWeatherId(WeatherId.valueOf(stream.readId("globalWeatherId")));
        setSoundEnv(SoundLabel.valueOf(stream.readString("soundEnv")));
        setTilesetLightEnv(Tileset.valueOf(stream.readChar("tilesetLightEnv")));

        setWaterColor(Color.fromRGBA255(
            stream.readUByte("waterRed"),
            stream.readUByte("waterGreen"),
            stream.readUByte("waterBlue"),
            stream.readUByte("waterAlpha")
        ));

        setScriptLang(stream.readUInt32("scriptLang") == 0 ? ScriptLang.JASS : ScriptLang.LUA);
        setGraphics(((Function<Long, Graphics>) val -> {
            if (val == 1L) return Graphics.SD;
            if (val == 2L) return Graphics.HD;
            if (val == 3L) return Graphics.SD_AND_HD;
            return Graphics.SD;
        }).apply(stream.readUInt32("graphics")));
        setGameDataVersion(stream.readUInt32("gameDataVersion") == 0 ? GameDataVersion.ROC : GameDataVersion.TFT);

        int playersCount = stream.readInt32("playersCount");

        for (int i = 0; i < playersCount; i++) {
            addPlayer(new Player(stream, EncodingFormat.W3I_0x1F));
        }

        if (stream.eof()) return;

        int forcesCount = stream.readInt32("forcesCount");

        for (int i = 0; i < forcesCount; i++) {
            addForce(new Force(stream, EncodingFormat.W3I_0x1F));
        }

        if (stream.eof()) return;

        if (stream.readUByte() == 0xFF) return;

        stream.rewind(1);

        int upgradeModsCount = stream.readInt32("upgradeModsCount");

        for (int i = 0; i < upgradeModsCount; i++) {
            addUpgradeMod(new UpgradeMod(stream, EncodingFormat.W3I_0x1F));
        }

        if (stream.eof()) return;

        int techModsCount = stream.readInt32("techModsCount");

        for (int i = 0; i < techModsCount; i++) {
            addTechMod(new TechMod(stream, EncodingFormat.W3I_0x1F));
        }

        if (stream.eof()) return;

        int unitTablesCount = stream.readInt32("unitTablesCount");

        for (int i = 0; i < unitTablesCount; i++) {
            addUnitTable(new UnitTable(stream, EncodingFormat.W3I_0x1F));
        }

        if (stream.eof()) return;

        int itemTablesCount = stream.readInt32("itemTablesCount");

        for (int i = 0; i < itemTablesCount; i++) {
            addItemTable(new ItemTable(stream, EncodingFormat.W3I_0x1F));
        }
    }

    private void write_0x1F(@Nonnull Wc3BinOutputStream stream) {
        stream.writeInt32(EncodingFormat.W3I_0x1F.getVersion());

        stream.writeInt32(getSavesAmount());
        stream.writeInt32(getEditorVersion());

        stream.writeUInt32(getGameVersion_major());
        stream.writeUInt32(getGameVersion_minor());
        stream.writeUInt32(getGameVersion_rev());
        stream.writeUInt32(getGameVersion_build());

        stream.writeString(getMapName());
        stream.writeString(getMapAuthor());
        stream.writeString(getMapDescription());
        stream.writeString(getPlayersRecommendedAmount());

        Coords2DF camBounds1 = getCameraBounds1();
        Coords2DF camBounds2 = getCameraBounds2();
        Coords2DF camBounds3 = getCameraBounds3();
        Coords2DF camBounds4 = getCameraBounds4();

        stream.writeFloat32(camBounds1.getX());
        stream.writeFloat32(camBounds1.getY());
        stream.writeFloat32(camBounds2.getX());
        stream.writeFloat32(camBounds2.getY());
        stream.writeFloat32(camBounds3.getX());
        stream.writeFloat32(camBounds3.getY());
        stream.writeFloat32(camBounds4.getX());
        stream.writeFloat32(camBounds4.getY());

        stream.writeInt32(-getMargins().getMinX());
        stream.writeInt32(getMargins().getMaxX());
        stream.writeInt32(-getMargins().getMinY());
        stream.writeInt32(getMargins().getMaxY());

        stream.writeInt32(getWidth());
        stream.writeInt32(getHeight());

        stream.writeInt32(getFlags().toInt());

        stream.writeChar(getTileset().getChar());

        getLoadingScreen().write(stream, EncodingFormat.W3I_0x1F);

        stream.writeInt32(getGameDataSet().getIndex());

        PrologueScreen prologueScreen = getPrologueScreen();

        stream.writeString(prologueScreen != null ? prologueScreen.getPath() : null);
        stream.writeString(prologueScreen != null ? prologueScreen.getText() : null);
        stream.writeString(prologueScreen != null ? prologueScreen.getTitle() : null);
        stream.writeString(prologueScreen != null ? prologueScreen.getSubtitle() : null);

        TerrainFog terrainFog = getTerrainFog();

        TerrainFogType terrainFogType = terrainFog != null ? terrainFog.getType() : null;

        stream.writeInt32(terrainFogType != null ? terrainFogType.getVal() : 0);
        stream.writeReal(terrainFog != null ? terrainFog.getZStart() : null);
        stream.writeReal(terrainFog != null ? terrainFog.getZEnd() : null);
        stream.writeReal(terrainFog != null ? terrainFog.getDensity() : null);

        Color terrainFogColor = (terrainFog != null) ? terrainFog.getColor() : null;

        stream.writeUByte(terrainFogColor != null ? terrainFogColor.getRed255() : 0x00);
        stream.writeUByte(terrainFogColor != null ? terrainFogColor.getGreen255() : 0x00);
        stream.writeUByte(terrainFogColor != null ? terrainFogColor.getBlue255() : 0x00);
        stream.writeUByte(terrainFogColor != null ? terrainFogColor.getAlpha255() : 0x00);

        stream.writeId(getGlobalWeatherId());
        stream.writeString(getSoundEnv());
        stream.writeChar(getTilesetLightEnv() != null ? getTilesetLightEnv().getChar() : null);

        Color waterColor = getWaterColor();

        stream.writeUByte(waterColor.getRed255());
        stream.writeUByte(waterColor.getGreen255());
        stream.writeUByte(waterColor.getBlue255());
        stream.writeUByte(waterColor.getAlpha255());

        stream.writeUInt32(getScriptLang() == ScriptLang.LUA ? 1 : 0);
        stream.writeUInt32(((Function<Graphics, Long>) val -> {
            if (val == Graphics.SD) return 1L;
            if (val == Graphics.HD) return 2L;
            if (val == Graphics.SD_AND_HD) return 3L;
            return 1L;
        }).apply(getGraphics()));
        stream.writeUInt32(getGameDataVersion() == GameDataVersion.TFT ? 1 : 0);

        stream.writeInt32(_players.size());

        for (Player player : _players) {
            player.write(stream, EncodingFormat.W3I_0x1F);
        }

        stream.writeInt32(_forces.size());

        for (Force force : _forces) {
            force.write(stream, EncodingFormat.W3I_0x1F);
        }

        stream.writeInt32(_upgradeMods.size());

        for (UpgradeMod upgradeMod : _upgradeMods) {
            upgradeMod.write(stream, EncodingFormat.W3I_0x1F);
        }

        stream.writeInt32(_techMods.size());

        for (TechMod techMod : _techMods) {
            techMod.write(stream, EncodingFormat.W3I_0x1F);
        }

        stream.writeInt32(_unitTables.size());

        for (UnitTable unitTable : _unitTables) {
            unitTable.write(stream, EncodingFormat.W3I_0x1F);
        }

        stream.writeInt32(_itemTables.size());

        for (ItemTable itemTable : _itemTables) {
            itemTable.write(stream, EncodingFormat.W3I_0x1F);
        }
    }

    private void read_auto(@Nonnull Wc3BinInputStream stream) throws Exception {
        int version = stream.readInt32("version");

        stream.rewind();

        read(stream, stream.getFormat(EncodingFormat.class, version));
    }

    private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws Exception {
        switch (format.toEnum()) {
            case AUTO:
                read_auto(stream);
                break;
            case W3I_0x12:
                read_0x12(stream);
                break;
            case W3I_0x19:
                read_0x19(stream);
                break;
            case W3I_0x1C:
                read_0x1C(stream);
                break;
            case W3I_0x1F:
                read_0x1F(stream);
                break;
        }
    }

    private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
        switch (format.toEnum()) {
            case AUTO:
                format = EncodingFormat.valueOf(_fileVersion);

                if (format == null) throw new IllegalArgumentException("no writer for " + _fileVersion);

                write(stream, format);

                break;
            case W3I_0x1F: {
                write_0x1F(stream);

                break;
            }
            case W3I_0x1C: {
                write_0x1C(stream);

                break;
            }
            case W3I_0x19: {
                write_0x19(stream);

                break;
            }
            case W3I_0x12: {
                write_0x12(stream);

                break;
            }
        }
    }

    private void read(@Nonnull Wc3BinInputStream stream) throws Exception {
        read(stream, EncodingFormat.AUTO);
    }

    public void write(@Nonnull Wc3BinOutputStream stream) {
        write(stream, EncodingFormat.AUTO);
    }

    public void write(@Nonnull File file) throws IOException {
        Wc3BinOutputStream outStream = new Wc3BinOutputStream(file);

        write(outStream);

        outStream.close();
    }

    public W3I() {
        for (State state : State.values(State.class)) {
            set(state, state.getDefVal());
        }
    }

    public W3I(@Nonnull Wc3BinInputStream stream) throws Exception {
        read(stream);
    }

    public W3I(@Nonnull byte[] bytes) throws Exception {
        read(new Wc3BinInputStream(new ByteArrayInputStream(bytes)));
    }

    public W3I(@Nonnull File file) throws Exception {
        read(new Wc3BinInputStream(file));
    }

    @Nonnull
    public static W3I ofMapFile(@Nonnull File mapFile) throws Exception {
        Orient.checkFileExists(mapFile);

        MpqPort.Out port = new JMpqPort.Out();

        port.add(GAME_PATH);

        MpqPort.Out.Result portResult = port.commit(mapFile);

        if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract info file");

        Wc3BinInputStream inStream = new Wc3BinInputStream(portResult.getInputStream(GAME_PATH));

        W3I w3i = new W3I();

        w3i.read(inStream);

        inStream.close();

        return w3i;
    }

    public FuncImpl makeInitCustomPlayerSlots() {
        FuncDecl funcDecl = new FuncDecl(false, FuncDecl.INIT_CUSTOM_PLAYER_SLOTS, new ArrayList<>(), null);

        List<Statement> stmts = new ArrayList<>();

        for (Player player : getPlayers()) {
            stmts.add(Statement.create("call SetPlayerStartLocation(Player(" + player.getNum() + ")" + ", " + player.getNum() + ")"));
            if (player.getStartPosFixed() == 1) {
                stmts.add(Statement.create("call ForcePlayerStartLocation(Player(" + player.getNum() + "), " + player.getNum() + ")"));
            }
            stmts.add(Statement.create("call SetPlayerColor(Player(" + player.getNum() + ")" + ", ConvertPlayerColor(" + player.getNum() + "))"));
            stmts.add(Statement.create("call SetPlayerRacePreference(Player(" + player.getNum() + ")" + ", " + player.getRace().getJassExpr() + ")"));
            stmts.add(Statement.create("call SetPlayerRaceSelectable(Player(" + player.getNum() + ")" + ", " + (player.getRace().equals(Player.UnitRace.SELECTABLE) || !getFlag(MapFlag.FIXED_PLAYER_FORCE_SETTING) ? "true" : "false") + ")"));
            stmts.add(Statement.create("call SetPlayerController(Player(" + player.getNum() + ")" + ", " + player.getType().getJassExpr() + ")"));
        }

        FuncImpl.Body body = new FuncImpl.Body(new ArrayList<>(), stmts);

        FuncImpl funcImpl = new FuncImpl(funcDecl, body);

        return funcImpl;
    }

    public FuncImpl makeInitCustomTeams() {
        FuncDecl funcDecl = new FuncDecl(false, FuncDecl.INIT_CUSTOM_TEAMS, new ArrayList<>(), null);

        List<Statement> stmts = new ArrayList<>();

        Map<Integer, Player> numToPlayerMap = new LinkedHashMap<>();

        for (Player player : getPlayers()) {
            numToPlayerMap.put(player.getNum(), player);
        }

        for (Force force : getForces()) {
            for (Integer playerNum : force.getPlayerNums(getPlayers())) {
                Player player = numToPlayerMap.get(playerNum);

                stmts.add(Statement.create("call SetPlayerTeam(Player(" + player.getNum() + ")" + ", " + getForces().indexOf(force) + ")"));
            }
        }

        for (Force force : getForces()) {
            for (Integer playerNum : force.getPlayerNums(getPlayers())) {
                Player player = numToPlayerMap.get(playerNum);

                if (force.getFlag(Force.Flags.Flag.ALLIED)) {
                    for (Integer playerNum2 : force.getPlayerNums(getPlayers())) {
                        if (playerNum.equals(playerNum2)) continue;

                        stmts.add(Statement.create("call SetPlayerAllianceStateAllyBJ(Player(" + player.getNum() + ")" + ", Player(" + playerNum2 + "), true)"));
                    }
                }
                if (force.getFlag(Force.Flags.Flag.SHARED_VISION)) {
                    for (Integer playerNum2 : force.getPlayerNums(getPlayers())) {
                        if (playerNum.equals(playerNum2)) continue;

                        stmts.add(Statement.create("call SetPlayerAllianceStateVisionBJ(Player(" + player.getNum() + ")" + ", Player(" + playerNum2 + "), true)"));
                    }
                }
            }
        }

        FuncImpl.Body body = new FuncImpl.Body(new ArrayList<>(), stmts);

        FuncImpl funcImpl = new FuncImpl(funcDecl, body);

        return funcImpl;
    }

    public FuncImpl makeInitAllyPriorities(@Nonnull GameVersion gameVersion) {
        FuncDecl funcDecl = new FuncDecl(false, FuncDecl.INIT_ALLY_PRIORITIES, new ArrayList<>(), null);

        List<Statement> stmts = new ArrayList<>();

        for (Player player : getPlayers()) {
            int playerNum = player.getNum();

            {
                Set<Integer> allyLowNums = player.getAllyLowPrioPlayerNums();
                Set<Integer> allyHighNums = player.getAllyHighPrioPlayerNums();

                stmts.add(Statement.create(String.format("call SetStartLocPrioCount(%d, %d)", playerNum, allyLowNums.size() + allyHighNums.size())));

                int c = 0;

                for (Player otherPlayer : getPlayers()) {
                    int otherPlayerNum = otherPlayer.getNum();

                    if (playerNum == otherPlayerNum) continue;

                    if (allyLowNums.contains(otherPlayerNum)) {
                        stmts.add(Statement.create(String.format("call SetStartLocPrio(%d, %d, %d, %s)", playerNum, c, otherPlayerNum, "MAP_LOC_PRIO_LOW")));
                    } else if (allyHighNums.contains(otherPlayerNum)) {
                        stmts.add(Statement.create(String.format("call SetStartLocPrio(%d, %d, %d, %s)", playerNum, c, otherPlayerNum, "MAP_LOC_PRIO_HIGH")));
                    } else {
                        //stmts.add(Statement.create(String.format("call SetStartLocPrio(%d, %d, %d, %s)", playerNum, c, otherPlayerNum, "MAP_LOC_PRIO_HIGH")));
                    }

                    c++;
                }
            }

            if (gameVersion.compareTo(GameVersion.VERSION_1_32) >= 0) {
                Set<Integer> enemyLowNums = player.getEnemyLowPrioPlayerNums();
                Set<Integer> enemyHighNums = player.getEnemyHighPrioPlayerNums();

                stmts.add(Statement.create(String.format("call SetEnemyStartLocPrioCount(%d, %d)", playerNum, enemyLowNums.size() + enemyHighNums.size())));

                int c = 0;

                for (Player otherPlayer : getPlayers()) {
                    int otherPlayerNum = otherPlayer.getNum();

                    if (playerNum == otherPlayerNum) continue;

                    if (enemyLowNums.contains(otherPlayerNum)) {
                        stmts.add(Statement.create(String.format("call SetEnemyStartLocPrio(%d, %d, %d, %s)", playerNum, c, otherPlayerNum, "MAP_LOC_PRIO_LOW")));
                    } else if (enemyHighNums.contains(otherPlayerNum)) {
                        stmts.add(Statement.create(String.format("call SetEnemyStartLocPrio(%d, %d, %d, %s)", playerNum, c, otherPlayerNum, "MAP_LOC_PRIO_HIGH")));
                    } else {
                        //stmts.add(Statement.create(String.format("call SetEnemyStartLocPrio(%d, %d, %d, %s)", playerNum, c, otherPlayerNum, "MAP_LOC_PRIO_HIGH")));
                    }

                    c++;
                }
            }
        }

        FuncImpl.Body body = new FuncImpl.Body(new ArrayList<>(), stmts);

        FuncImpl funcImpl = new FuncImpl(funcDecl, body);

        return funcImpl;
    }

    public FuncImpl makeConfig() {
        FuncDecl funcDecl = new FuncDecl(false, FuncDecl.CONFIG_NAME, new ArrayList<>(), null);

        List<Statement> stmts = new ArrayList<>();

        Function enquote = (Function<String, String>) s -> {
            return "\"" + s + "\"";
        };

        stmts.add(Statement.create("call SetMapName(" + enquote.apply(getMapName()) + ")"));
        stmts.add(Statement.create("call SetMapDescription(" + enquote.apply(getMapDescription()) + ")"));

        stmts.add(Statement.create("call SetPlayers(" + getPlayers().size() + ")"));
        stmts.add(Statement.create("call SetTeams(" + getForces().size() + ")"));

        stmts.add(Statement.create("call SetGamePlacement(MAP_PLACEMENT_TEAMS_TOGETHER)"));

        for (Player player : getPlayers()) {
            stmts.add(Statement.create("call DefineStartLocation(" + player.getNum() + ", " + player.getStartPos().getX() + ", " + player.getStartPos().getY() + ")"));
        }

        stmts.add(Statement.create("call " + FuncDecl.INIT_CUSTOM_PLAYER_SLOTS + "()"));
        stmts.add(Statement.create("call " + FuncDecl.INIT_CUSTOM_TEAMS + "()"));
        stmts.add(Statement.create("call " + FuncDecl.INIT_ALLY_PRIORITIES + "()"));

        FuncImpl.Body body = new FuncImpl.Body(new ArrayList<>(), stmts);

        FuncImpl funcImpl = new FuncImpl(funcDecl, body);

        return funcImpl;
    }

    public void injectConfigsInJassScript(@Nonnull JassScript jassScript, @Nonnull GameVersion gameVersion) {
        List<String> funcNames = new ArrayList<>();

        funcNames.add(FuncDecl.CONFIG_NAME);
        funcNames.add(FuncDecl.INIT_CUSTOM_PLAYER_SLOTS);
        funcNames.add(FuncDecl.INIT_CUSTOM_TEAMS);
        funcNames.add(FuncDecl.INIT_ALLY_PRIORITIES);

        List<FuncImpl> funcImplsToRemove = new ArrayList<>();

        for (FuncImpl funcImpl : jassScript.getFuncImpls()) {
            if (funcNames.contains(funcImpl.getDecl().getName())) {
                funcImplsToRemove.add(funcImpl);
            }
        }

        for (FuncImpl funcImpl : funcImplsToRemove) {
            jassScript.removeFuncImpl(funcImpl);
        }

        FuncImpl initCustomPlayerSlots = makeInitCustomPlayerSlots();
        FuncImpl initCustomTeams = makeInitCustomTeams();
        FuncImpl initAllyPriorities = makeInitAllyPriorities(gameVersion);
        FuncImpl config = makeConfig();

        jassScript.addFuncImpl(initCustomPlayerSlots);
        jassScript.addFuncImpl(initCustomTeams);
        jassScript.addFuncImpl(initAllyPriorities);
        jassScript.addFuncImpl(config);
    }

    public void removeConfigsInJassScript(@Nonnull InputStream inStream, @Nonnull StringWriter sw) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, StandardCharsets.UTF_8));

        String line;

        List<String> toBeRemovedFuncs = new ArrayList<>();

        toBeRemovedFuncs.add(FuncDecl.CONFIG_NAME);
        toBeRemovedFuncs.add(FuncDecl.INIT_CUSTOM_PLAYER_SLOTS);
        toBeRemovedFuncs.add(FuncDecl.INIT_CUSTOM_TEAMS);
        toBeRemovedFuncs.add(FuncDecl.INIT_ALLY_PRIORITIES);

        boolean first = true;
        boolean skip = false;

        while ((line = reader.readLine()) != null) {
            Pattern funcStartPattern = Pattern.compile("^\\s*function\\s+(\\w+)");

            Matcher funcStartMatcher = funcStartPattern.matcher(line);

            if (funcStartMatcher.find()) {
                String funcName = funcStartMatcher.group(1);

                if (toBeRemovedFuncs.contains(funcName)) {
                    skip = true;
                }
            }

            if (skip) {
                Pattern funcEndPattern = Pattern.compile("^\\s*endfunction");

                Matcher funcEndMatcher = funcEndPattern.matcher(line);

                if (funcEndMatcher.find()) {
                    skip = false;
                }

                continue;
            }

            if (first) {
                first = false;
            } else {
                sw.write("\n");
            }

            sw.write(line);
        }

        reader.close();
    }

    public void injectConfigsInJassScript(@Nonnull InputStream inStream, @Nonnull StringWriter sw, @Nonnull GameVersion gameVersion) throws IOException {
        removeConfigsInJassScript(inStream, sw);

        List<FuncImpl> toBeAddedFuncImpls = new ArrayList<>();

        toBeAddedFuncImpls.add(makeInitCustomPlayerSlots());
        toBeAddedFuncImpls.add(makeInitCustomTeams());
        toBeAddedFuncImpls.add(makeInitAllyPriorities(gameVersion));
        toBeAddedFuncImpls.add(makeConfig());

        for (FuncImpl funcImpl : toBeAddedFuncImpls) {
            sw.write("\n");
            funcImpl.write(sw);
        }
    }
}
