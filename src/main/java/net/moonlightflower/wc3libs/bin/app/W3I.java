package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.Size;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.port.Orient;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * info file for wrapping war3map.w3i
 */
public class W3I {
    public final static File GAME_PATH = new File("war3map.w3i");

    /*private int _fileVersion;

    public int getFileVersion() {
        return _fileVersion;
    }

    public void setFileVersion(int fileVersion) {
        _fileVersion = fileVersion;
    }*/

    private int _savesAmount = 0;

    public int getSavesAmount() {
        return _savesAmount;
    }

    public void setSavesAmount(int val) {
        _savesAmount = val;
    }

    private int _editorVersion = 0;

    public int getEditorVersion() {
        return _editorVersion;
    }

    public void setEditorVersion(int val) {
        _editorVersion = val;
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

    public Bounds getWorldBounds() {
        Size size = new Size(getWidth() + getMargins().getMinX() + getMargins().getMaxX(), getHeight() + getMargins().getMinY() + getMargins().getMaxY());

        return new Bounds(size, new Coords2DI(0, 0));
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

        public static class Flag extends FlagsInt.Flag {
            private static List<Flag> _all = new ArrayList<>();

            public final static Flag HIDE_MINIMAP = new Flag(0, "hideMinimap");
            public final static Flag MODIFY_ALLY_PRIORITIES = new Flag(1, "modifyAllyPriorities");
            public final static Flag MELEE_MAP = new Flag(2, "meleeMap");
            public final static Flag INITIAL_MAP_SIZE_LARGE_NEVER_MODIFIED = new Flag(3, "initialMapSizeLargeNeverModified");
            public final static Flag MASKED_AREAS_PARTIALLY_VISIBLE = new Flag(4, "maskedAreasPartiallyVisible");
            public final static Flag FIXED_PLAYER_FORCE_SETTING = new Flag(5, "fixedPlayerForceSetting");
            public final static Flag USE_CUSTOM_FORCES = new Flag(6, "useCustomForces");
            public final static Flag USE_CUSTOM_TECHS = new Flag(7, "useCustomTechs");
            public final static Flag USE_CUSTOM_ABILS = new Flag(8, "useCustomAbils");
            public final static Flag USE_CUSTOM_UPGRADES = new Flag(9, "useCustomUpgrades");
            public final static Flag MAP_PROPERTIES_WINDOW_OPENED_BEFORE = new Flag(10, "mapPrioritiesWindowOpenedBefore");
            public final static Flag SHOW_WATER_WAVES_ON_CLIFF_SHORES = new Flag(11, "showWaterWavesOnCliffShores");
            public final static Flag SHOW_WATER_WAVES_ON_ROLLING_SHORES = new Flag(12, "showWaterWavesOnRollingShores");
            public final static Flag UNKNOWN = new Flag(13, "unknown");
            public final static Flag UNKNOWN_B = new Flag(14, "unknownB");
            public final static Flag UNKNOWN_C = new Flag(15, "unknownC");

            public static Flag[] values() {
                Flag[] flags = new Flag[_all.size()];

                return _all.toArray(flags);
            }

            private Flag(int pos, String label) {
                super(pos, label);

                _all.add(this);
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("");

            Flag[] flags = Flag.values();

            if (flags.length > 0) {
                for (Flag flag : flags) {
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

    public boolean getFlag(Flags.Flag flag) {
        return _flags.containsFlag(flag);
    }

    public void setFlag(Flags.Flag flag, boolean val) {
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

            stream.writeInt(background instanceof LoadingScreenBackground.PresetBackground ? ((LoadingScreenBackground.PresetBackground) background).getIndex
                    () : -1);
            stream.writeString(getText());
            stream.writeString(getTitle());
            stream.writeString(getSubtitle());
            stream.writeInt(getIndex());
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
                stream.writeInt(background instanceof LoadingScreenBackground.PresetBackground ? ((LoadingScreenBackground.PresetBackground) background)
                        .getIndex() : -1);
                stream.writeString(background instanceof LoadingScreenBackground.CustomBackground ? ((LoadingScreenBackground.CustomBackground) background)
                        .getCustomPath().toString() : null);
            } else {
                stream.writeInt(-1);
                stream.writeString((String) null);
            }

            stream.writeString(getText());
            stream.writeString(getTitle());
            stream.writeString(getSubtitle());
        }

        private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
            switch (format.toEnum()) {
                case W3I_0x12: {
                    read_0x12(stream);

                    break;
                }
                case W3I_0x19: {
                    read_0x19(stream);

                    break;
                }
            }
        }

        private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
            switch (format.toEnum()) {
                case AUTO:
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
        private static Map<Integer, GameDataSet> _map = new LinkedHashMap<>();

        public final static GameDataSet STANDARD = new GameDataSet(0, "DEFAULT");
        public final static GameDataSet CUSTOM_V1 = new GameDataSet(1, "CUSTOM_V1");
        public final static GameDataSet MELEE_V1 = new GameDataSet(2, "MELEE_V1");

        private int _index;

        public int getIndex() {
            return _index;
        }

        public static GameDataSet valueOf(int index) {
            return _map.get(index);
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

    private GameDataSet _gameData;

    @Nonnull
    public GameDataSet getGameDataSet() {
        return _gameData;
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

    public static class Player {
        private int _num = 0;

        public int getNum() {
            return _num;
        }

        public void setNum(int val) {
            _num = val;
        }

        private Controller _type = Controller.HUMAN;

        @Nonnull
        public Controller getType() {
            return _type;
        }

        public void setType(@Nonnull Controller val) {
            _type = val;
        }

        public static class UnitRace extends Int {
            private static Map<Integer, UnitRace> _map = new LinkedHashMap<>();

            public final static UnitRace NIGHT_ELF = new UnitRace(4, "NIGHT_ELF");
            public final static UnitRace HUMAN = new UnitRace(1, "HUMAN");
            public final static UnitRace ORC = new UnitRace(2, "ORC");
            public final static UnitRace SELECTABLE = new UnitRace(0, "SELECTABLE");
            public final static UnitRace UNDEAD = new UnitRace(3, "UNDEAD");

            private String _label;

            public String getLabel() {
                return _label;
            }

            @Override
            public String toString() {
                return getLabel();
            }

            public UnitRace(int val, String label) {
                super(val);

                _map.put(val, this);

                _label = label;
            }

            public static UnitRace valueOf(Integer val) {
                return _map.get(val);
            }

			/*public static UnitRace decodeStatic(Object val) {
				try {
					return valueOf(Integer.parseInt(val.toString()));
				} catch (NumberFormatException e) {
				}
				
				return null;
			}*/
        }

        private UnitRace _race = UnitRace.HUMAN;

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

            public static AllyFlags valueOf(int val) {
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

        private FlagsInt _allyLowPrioFlags = AllyFlags.valueOf(0);

        public int getAllyLowPrioFlags() {
            return _allyLowPrioFlags.toInt();
        }

        public void setAllyLowPrioFlags(int val) {
            _allyLowPrioFlags.setVal(val);
        }

        public void setAllyLowPrioFlag(int index, boolean val) {
            _allyLowPrioFlags.setPos(index, val);
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

        @Override
        public String toString() {
            String allyLowPrioFlagsS = String.format("%12s", Integer.toBinaryString(getAllyLowPrioFlags()));
            String allyHighPrioFlagsS = String.format("%12s", Integer.toBinaryString(getAllyHighPrioFlags()));

            allyLowPrioFlagsS = allyLowPrioFlagsS.substring(allyLowPrioFlagsS.length() - 12).replaceAll(" ", "0");
            allyHighPrioFlagsS = allyHighPrioFlagsS.substring(allyHighPrioFlagsS.length() - 12).replaceAll(" ", "0");

            return String.format("%s [num=%d controller=%s race=%s startPosFixed=%s startPos=%s allyLowPrioFlags=%s allyHighPrioFlags=%s]", getName(), getNum
                    (), getType(), getRace(), getStartPosFixed(), getStartPos(), allyLowPrioFlagsS, allyHighPrioFlagsS);
        }

        private void read_0x12(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
            setNum(stream.readInt32("playerNum"));

            setType(Controller.valueOf(stream.readInt32("controller")));
            setRace(UnitRace.valueOf(stream.readInt32("race")));

            setStartPosFixed(stream.readInt32("startPosFixed"));

            setName(stream.readString("playerName"));

            setStartPos(new Coords2DF(stream.readFloat8("startPosX"), stream.readFloat8("startPosY")));

            setAllyLowPrioFlags(stream.readInt32("allyLowPrioFlags"));
            setAllyHighPrioFlags(stream.readInt32("allyHighPrioFlags"));
        }

        private void write_0x12(@Nonnull Wc3BinOutputStream stream) {
            stream.writeInt(getNum());

            stream.writeInt(getType().getVal());
            stream.writeInt(getRace().getVal());

            stream.writeInt(getStartPosFixed());

            stream.writeString(getName());

            Coords2DF startPos = getStartPos();

            stream.writeFloat(startPos.getX());
            stream.writeFloat(startPos.getY());

            stream.writeInt(getAllyLowPrioFlags());
            stream.writeInt(getAllyHighPrioFlags());
        }

        private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
            switch (format.toEnum()) {
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

    public Player getPlayerFromNum(int num) {
        for (Player p : _players) if (p.getNum() == num) return p;

        return null;
    }

    private void addPlayer(@Nonnull Player val) {
        _players.add(val);
    }

    public Player addPlayer() {
        Player player = new Player();

        addPlayer(player);

        return player;
    }

    public static class Force {
        static public class Flags extends FlagsInt {
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
                public final static Flag ALLIED = new Flag(0);
                public final static Flag ALLIED_VICTORY = new Flag(1);
                public final static Flag SHARED_VISION = new Flag(2);
                public final static Flag SHARED_UNIT_CONTROL = new Flag(4);
                public final static Flag SHARED_UNIT_CONTROL_ADVANCED = new Flag(5);

                private Flag(int pos) {
                    super(pos);
                }
            }

            @Override
            public String toString() {
                return String.format("%5s", Integer.toBinaryString(toInt())).replaceAll(" ", "0");
            }

            protected Flags(int val) {
                super(val);
            }

            static public Flags valueOf(int val) {
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

        private int _players = 0;

        public int getPlayers() {
            return _players;
        }

        public void setPlayers(int val) {
            _players = val;
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
            String playersS = String.format("%12s", Integer.toBinaryString(getPlayers()));

            playersS = playersS.substring(playersS.length() - 12).replaceAll(" ", "0");

            return String.format("%s [players=%s, flags=%s]", getName(), playersS, getFlags());
        }

        private void read_0x12(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
            setFlags(Flags.valueOf(stream.readInt32("forceFlags")));

            setPlayers(stream.readInt32("forcePlayers"));

            setName(stream.readString("forceName"));
        }

        private void write_0x12(@Nonnull Wc3BinOutputStream stream) {
            stream.writeInt(getFlags().toInt());

            stream.writeInt(getPlayers());

            stream.writeString(getName());
        }

        private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
            switch (format.toEnum()) {
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

    private List<Force> _forces = new ArrayList<>();

    @Nonnull
    public List<Force> getForces() {
        return _forces;
    }

    private void addForce(@Nonnull Force val) {
        _forces.add(val);
    }

    @Nonnull
    public Force addForce() {
        Force force = new Force();

        addForce(force);

        return force;
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

        private void read_0x12(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
            setPlayers(stream.readInt32("abilPlayers"));

            setId(stream.readId("abilId"));

            setLevel(stream.readInt32("level"));

            setAvail(stream.readInt32("avail"));
        }

        private void write_0x12(@Nonnull Wc3BinOutputStream stream) {
            stream.writeInt(getPlayers());

            stream.writeId(getId());

            stream.writeInt(getLevel());

            stream.writeInt(getAvail());
        }

        private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
            switch (format.toEnum()) {
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

    private List<UpgradeMod> _upgradeMods = new ArrayList<>();

    @Nonnull
    public List<UpgradeMod> getUpgradeMods() {
        return _upgradeMods;
    }

    private void addUpgradeMod(@Nonnull UpgradeMod val) {
        _upgradeMods.add(val);
    }

    @Nonnull
    public UpgradeMod addUpgradeMod() {
        UpgradeMod upgradeMod = new UpgradeMod();

        addUpgradeMod(upgradeMod);

        return upgradeMod;
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

        private void read_0x12(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
            setPlayers(stream.readInt32("techPlayers"));

            setId(stream.readId("techId"));
        }

        private void write_0x12(Wc3BinOutputStream stream) {
            stream.writeInt(getPlayers());

            stream.writeId(getId());
        }

        private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
            switch (format.toEnum()) {
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
        return _techMods;
    }

    private void addTechMod(@Nonnull TechMod val) {
        _techMods.add(val);
    }

    @Nonnull
    public TechMod addTechMod() {
        TechMod techMod = new TechMod();

        addTechMod(techMod);

        return techMod;
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

            private static Map<Integer, PositionType> _valToPositionTypeMap = new LinkedHashMap<>();

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

            private void read_0x12(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
                setChance(stream.readInt32("chance"));

                for (int i = 0; i < _parent._positionTypes.size(); i++) {
                    setTypeId(i, stream.readId("typeId"));
                }
            }

            private void write_0x12(@Nonnull Wc3BinOutputStream stream) {
                stream.writeInt(getChance());

                for (int i = 0; i < _parent._positionTypes.size(); i++) {
                    stream.writeId(getTypeId(i));
                }
            }

            private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
                switch (format.toEnum()) {
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

            public Set() {

            }
        }

        private List<Set> _sets = new ArrayList<>();

        private void addSet(Set val) {
            _sets.add(val);
        }

        public Set addSet() {
            Set set = new Set();

            addSet(set);

            return set;
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

            stream.writeInt(positionsCount);

            for (int i = 0; i < positionsCount; i++) {
                stream.writeInt(getPositionType(i).getVal());
            }

            for (Set set : _sets) {
                set.write(stream, EncodingFormat.W3I_0x19);
            }
        }

        private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws Exception {
            switch (format.toEnum()) {
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

        public UnitTable() {
        }
    }

    private List<UnitTable> _unitTables = new ArrayList<>();

    @Nonnull
    public List<UnitTable> getUnitTables() {
        return _unitTables;
    }

    private void addUnitTable(@Nonnull UnitTable val) {
        _unitTables.add(val);
    }

    @Nonnull
    public UnitTable addUnitTable() {
        UnitTable unitTable = new UnitTable();

        addUnitTable(unitTable);

        return unitTable;
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
            private int _chance = 100;

            public int getChance() {
                return _chance;
            }

            public void setChance(int val) {
                _chance = val;
            }

            private Id _id;

            @Nonnull
            public Id getTypeId() {
                return _id;
            }

            public void setTypeId(@Nonnull Id val) {
                _id = val;
            }

            private void read_0x19(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
                setChance(stream.readInt32("chance"));

                setTypeId(stream.readId("typeId"));
            }

            private void write_0x19(@Nonnull Wc3BinOutputStream stream) {
                stream.writeInt(getChance());

                stream.writeId(getTypeId());
            }

            private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
                switch (format.toEnum()) {
                    case W3I_0x19: {
                        read_0x19(stream);

                        break;
                    }
                }
            }

            private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
                switch (format.toEnum()) {
                    case AUTO:
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

        private void addSet(@Nonnull Set val) {
            _sets.add(val);
        }

        @Nonnull
        public Set addSet() {
            Set set = new Set();

            addSet(set);

            return set;
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
            stream.writeInt(getIndex());

            stream.writeString(getName());

            for (Set set : _sets) {
                set.write(stream, EncodingFormat.W3I_0x19);
            }
        }

        private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
            switch (format.toEnum()) {
                case W3I_0x19: {
                    read_0x19(stream);

                    break;
                }
            }
        }

        private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
            switch (format.toEnum()) {
                case AUTO:
                case W3I_0x19: {
                    write_0x19(stream);

                    break;
                }
            }
        }

        public ItemTable(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
            read(stream, format);
        }

        public ItemTable() {
        }
    }

    private List<ItemTable> _itemTables = new ArrayList<>();

    @Nonnull
    public List<ItemTable> getItemTables() {
        return _itemTables;
    }

    private void addItemTable(@Nonnull ItemTable val) {
        _itemTables.add(val);
    }

    @Nonnull
    public ItemTable addItemTable() {
        ItemTable itemTable = new ItemTable();

        addItemTable(itemTable);

        return itemTable;
    }

    public void print(@Nonnull PrintStream outStream) {
        outStream.println(String.format("savesAmount: %d", getSavesAmount()));
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

        outStream.println(String.format("prologueScreen: %s", getPrologueScreen()));

        outStream.println(String.format("terrainFog: %s", getTerrainFog()));

        outStream.println(String.format("globalWeatherId: %s", getGlobalWeatherId()));
        outStream.println(String.format("soundEnv: %s", getSoundEnv()));
        outStream.println(String.format("tilesetLightEnv: %s", getTilesetLightEnv()));

        outStream.println(String.format("waterColor: %s", getWaterColor()));
    }

    public void print() {
        print(System.out);
    }

    public static class EncodingFormat extends Format<EncodingFormat.Enum> {
        public enum Enum {
            AUTO,
            W3I_0x19,
            W3I_0x12,
        }

        private static Map<Integer, EncodingFormat> _map = new LinkedHashMap<>();

        public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
        public final static EncodingFormat W3I_0x19 = new EncodingFormat(Enum.W3I_0x19, 0x19);
        public final static EncodingFormat W3I_0x12 = new EncodingFormat(Enum.W3I_0x12, 0x12);

        @Nullable
        public static EncodingFormat valueOf(int version) {
            return _map.get(version);
        }

        private EncodingFormat(@Nonnull Enum enumVal, int version) {
            super(enumVal, version);

            _map.put(version, this);
        }
    }

    private void read_0x12(@Nonnull Wc3BinInputStream stream) throws Exception {
        int version = stream.readInt32("version");

        Wc3BinInputStream.checkFormatVer("infoFileMaskFunc", EncodingFormat.W3I_0x12.getVersion(), version);

        setSavesAmount(stream.readInt32("savesAmount"));
        setEditorVersion(stream.readInt32("editorVersion"));
        setMapName(stream.readString("mapName"));
        setMapAuthor(stream.readString("mapAuthor"));
        setMapDescription(stream.readString("mapDescription"));
        setPlayersRecommendedAmount(stream.readString("playersRecommendedAmount"));

        setCameraBounds(new Coords2DF(stream.readFloat8("camA"), stream.readFloat8("camB")), new Coords2DF(stream.readFloat8("camC"), stream.readFloat8("camD")),
                new Coords2DF(stream.readFloat8("camE"), stream.readFloat8("camF")), new Coords2DF(stream.readFloat8("camG"), stream.readFloat8("camH")));
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
        stream.writeInt(EncodingFormat.W3I_0x12.getVersion());

        stream.writeInt(getSavesAmount());
        stream.writeInt(getEditorVersion());
        stream.writeString(getMapName());
        stream.writeString(getMapDescription());
        stream.writeString(getPlayersRecommendedAmount());

        Coords2DF camBounds1 = getCameraBounds1();
        Coords2DF camBounds2 = getCameraBounds2();
        Coords2DF camBounds3 = getCameraBounds3();
        Coords2DF camBounds4 = getCameraBounds4();

        stream.writeFloat(camBounds1.getX());
        stream.writeFloat(camBounds1.getY());
        stream.writeFloat(camBounds2.getX());
        stream.writeFloat(camBounds2.getY());
        stream.writeFloat(camBounds3.getX());
        stream.writeFloat(camBounds3.getY());
        stream.writeFloat(camBounds4.getX());
        stream.writeFloat(camBounds4.getY());

        stream.writeInt(-getMargins().getMinX());
        stream.writeInt(getMargins().getMaxX());
        stream.writeInt(-getMargins().getMinY());
        stream.writeInt(getMargins().getMaxY());

        stream.writeInt(getWidth());
        stream.writeInt(getHeight());

        stream.writeInt(getFlags().toInt());

        stream.writeChar(getTileset().getChar());

        getLoadingScreen().write(stream, EncodingFormat.W3I_0x12);

        PrologueScreen prologueScreen = getPrologueScreen();

        stream.writeString(prologueScreen != null ? prologueScreen.getText() : null);
        stream.writeString(prologueScreen != null ? prologueScreen.getTitle() : null);
        stream.writeString(prologueScreen != null ? prologueScreen.getSubtitle() : null);

        stream.writeInt(_players.size());

        for (Player player : _players) {
            player.write(stream, EncodingFormat.W3I_0x12);
        }

        stream.writeInt(_forces.size());

        for (Force force : _forces) {
            force.write(stream, EncodingFormat.W3I_0x12);
        }

        stream.writeInt(_upgradeMods.size());

        for (UpgradeMod upgradeMod : _upgradeMods) {
            upgradeMod.write(stream, EncodingFormat.W3I_0x12);
        }

        stream.writeInt(_techMods.size());

        for (TechMod techMod : _techMods) {
            techMod.write(stream, EncodingFormat.W3I_0x12);
        }

        stream.writeInt(_unitTables.size());

        for (UnitTable unitTable : _unitTables) {
            unitTable.write(stream, EncodingFormat.W3I_0x12);
        }

        stream.writeInt(_itemTables.size());

        for (ItemTable itemTable : _itemTables) {
            itemTable.write(stream, EncodingFormat.W3I_0x12);
        }
    }

    private void read_0x19(@Nonnull Wc3BinInputStream stream) throws Exception {
        int version = stream.readInt32("version");

        Wc3BinInputStream.checkFormatVer("infoFileMaskFunc", EncodingFormat.W3I_0x19.getVersion(), version);

        setSavesAmount(stream.readInt32("savesAmount"));
        setEditorVersion(stream.readInt32("editorVersion"));
        setMapName(stream.readString("mapName"));
        setMapAuthor(stream.readString("mapAuthor"));
        setMapDescription(stream.readString("mapDescription"));
        setPlayersRecommendedAmount(stream.readString("playersRecommendedAmount"));

        setCameraBounds(new Coords2DF(
                        stream.readFloat8("camA"),
                        stream.readFloat8("camB")),
                new Coords2DF(
                        stream.readFloat8("camC"),
                        stream.readFloat8("camD")),
                new Coords2DF(
                        stream.readFloat8("camE"),
                        stream.readFloat8("camF")),
                new Coords2DF(stream.readFloat8("camG"),
                        stream.readFloat8("camH")
                ));
        setMargins(new Bounds(-stream.readInt32("marginA"), stream.readInt32("marginB"), -stream.readInt32("marginC"), stream.readInt32("marginD")));

        setDimensions(stream.readInt32("width"), stream.readInt32("height"));

        setFlags(Flags.valueOf(stream.readInt32("flags")));

        setTileset(Tileset.valueOf(stream.readChar("tileset")));

        setLoadingScreen(new LoadingScreen(stream, EncodingFormat.W3I_0x19));

        setGameDataSet(GameDataSet.valueOf(stream.readInt32("gameData")));

        setPrologueScreen(new PrologueScreen(
                stream.readString("prologueScreenPath"),
                stream.readString("prologueScreenText"),
                stream.readString("prologueScreenTitle"),
                stream.readString("prologueScreenSubtitle")
        ));

        setTerrainFog(new TerrainFog(
                TerrainFogType.valueOf(stream.readInt32("terrainFogType")),
                stream.readReal("terrainFogZHeightStart"),
                stream.readReal("terrainFogZHeightEnd"),
                stream.readReal("terrainFogDensity"),
                Color.fromRGBA255(
                        stream.readUByte("terrainFogRed"),
                        stream.readUByte("terrainFogGreen"),
                        stream.readUByte("terrainFogBlue"),
                        stream.readUByte("terrainFogAlpha"))
        ));

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

    private void write_0x19(Wc3BinOutputStream stream) {
        stream.writeInt(EncodingFormat.W3I_0x19.getVersion());

        stream.writeInt(getSavesAmount());
        stream.writeInt(getEditorVersion());
        stream.writeString(getMapName());
        stream.writeString(getMapAuthor());
        stream.writeString(getMapDescription());
        stream.writeString(getPlayersRecommendedAmount());

        Coords2DF camBounds1 = getCameraBounds1();
        Coords2DF camBounds2 = getCameraBounds2();
        Coords2DF camBounds3 = getCameraBounds3();
        Coords2DF camBounds4 = getCameraBounds4();

        stream.writeFloat(camBounds1.getX());
        stream.writeFloat(camBounds1.getY());
        stream.writeFloat(camBounds2.getX());
        stream.writeFloat(camBounds2.getY());
        stream.writeFloat(camBounds3.getX());
        stream.writeFloat(camBounds3.getY());
        stream.writeFloat(camBounds4.getX());
        stream.writeFloat(camBounds4.getY());

        stream.writeInt(-getMargins().getMinX());
        stream.writeInt(getMargins().getMaxX());
        stream.writeInt(-getMargins().getMinY());
        stream.writeInt(getMargins().getMaxY());

        stream.writeInt(getWidth());
        stream.writeInt(getHeight());

        stream.writeInt(getFlags().toInt());

        stream.writeChar(getTileset().getChar());

        getLoadingScreen().write(stream, EncodingFormat.W3I_0x19);

        stream.writeInt(getGameDataSet().getIndex());

        PrologueScreen prologueScreen = getPrologueScreen();

        stream.writeString(prologueScreen != null ? prologueScreen.getPath() : null);
        stream.writeString(prologueScreen != null ? prologueScreen.getText() : null);
        stream.writeString(prologueScreen != null ? prologueScreen.getTitle() : null);
        stream.writeString(prologueScreen != null ? prologueScreen.getSubtitle() : null);

        TerrainFog terrainFog = getTerrainFog();

        stream.writeInt(terrainFog != null ? terrainFog.getType() : null);
        stream.writeReal(terrainFog != null ? terrainFog.getZStart() : null);
        stream.writeReal(terrainFog != null ? terrainFog.getZEnd() : null);
        stream.writeReal(terrainFog != null ? terrainFog.getDensity() : null);

        Color terrainFogColor = (terrainFog != null) ? terrainFog.getColor() : null;

        stream.writeUByte(terrainFogColor != null ? terrainFogColor.getRed() : 0x00);
        stream.writeUByte(terrainFogColor != null ? terrainFogColor.getGreen() : 0x00);
        stream.writeUByte(terrainFogColor != null ? terrainFogColor.getBlue() : 0x00);
        stream.writeUByte(terrainFogColor != null ? terrainFogColor.getAlpha() : 0x00);

        stream.writeId(getGlobalWeatherId());
        stream.writeString(getSoundEnv());
        stream.writeChar(getTilesetLightEnv() != null ? getTilesetLightEnv().getChar() : null);

        Color waterColor = getWaterColor();

        stream.writeUByte(waterColor.getRed());
        stream.writeUByte(waterColor.getGreen());
        stream.writeUByte(waterColor.getBlue());
        stream.writeUByte(waterColor.getAlpha());

        stream.writeInt(_players.size());

        for (Player player : _players) {
            player.write(stream, EncodingFormat.W3I_0x19);
        }

        stream.writeInt(_forces.size());

        for (Force force : _forces) {
            force.write(stream, EncodingFormat.W3I_0x19);
        }

        stream.writeInt(_upgradeMods.size());

        for (UpgradeMod upgradeMod : _upgradeMods) {
            upgradeMod.write(stream, EncodingFormat.W3I_0x19);
        }

        stream.writeInt(_techMods.size());

        for (TechMod techMod : _techMods) {
            techMod.write(stream, EncodingFormat.W3I_0x19);
        }

        stream.writeInt(_unitTables.size());

        for (UnitTable unitTable : _unitTables) {
            unitTable.write(stream, EncodingFormat.W3I_0x19);
        }

        stream.writeInt(0);
    }

    private void read_auto(@Nonnull Wc3BinInputStream stream) throws Exception {
        int version = stream.readInt32("version");
        System.out.println("Detected version: " + version);
        stream.rewind();

        EncodingFormat format = EncodingFormat.valueOf(version);

        if (format == null) throw new Exception(String.format("unknown format %x", version));

        read(stream, format);
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
        }
    }

    private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
        switch (format.toEnum()) {
            case AUTO:
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

    private void write(@Nonnull Wc3BinOutputStream stream) {
        write(stream, EncodingFormat.AUTO);
    }

    public void write(@Nonnull File file) throws IOException {
        Wc3BinOutputStream outStream = new Wc3BinOutputStream(file);

        write(outStream);

        outStream.close();
    }

    public W3I() {

    }

    public W3I(@Nonnull byte[] bytes) throws Exception {
        read(new Wc3BinInputStream(new ByteArrayInputStream(bytes)));
    }

    public W3I(@Nonnull File file) throws Exception {
        read(new Wc3BinInputStream(file));
    }

    public static W3I ofMapFile(@Nonnull File mapFile) throws Exception {
        Orient.checkFileExists(mapFile);

        MpqPort.Out port = new JMpqPort.Out();

        port.add(GAME_PATH);

        MpqPort.Out.Result portResult = port.commit(mapFile);

        if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract info file");

        W3I w3i = new W3I();

        w3i.read(new Wc3BinInputStream(GAME_PATH));

        return w3i;
    }
}