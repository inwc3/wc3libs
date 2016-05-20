package net.moonlightflower.wc3libs.bin.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.moonlightflower.wc3libs.bin.Format;
import net.moonlightflower.wc3libs.bin.Wc3bin;
import net.moonlightflower.wc3libs.bin.Wc3bin.Stream;
import net.moonlightflower.wc3libs.bin.Wc3bin.StreamException;
import net.moonlightflower.wc3libs.dataTypes.app.Color;
import net.moonlightflower.wc3libs.dataTypes.app.Controller;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DF;
import net.moonlightflower.wc3libs.dataTypes.app.FlagsInt;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.TerrainFogType;
import net.moonlightflower.wc3libs.misc.Id;

/**
 * info file for wrapping war3map.w3i
 */
public class W3I {
	public final static String GAME_PATH = "war3map.w3i";
	
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
	
	public String getMapName() {
		return _mapName;
	}
	
	public void setMapName(String val) {
		_mapName = val;
	}
	
	private String _mapAuthor;
	
	public String getMapAuthor() {
		return _mapAuthor;
	}
	
	public void setMapAuthor(String val) {
		_mapAuthor = val;
	}

	private String _mapDescription;
	
	public String getMapDescription() {
		return _mapDescription;
	}
	
	public void setMapDescription(String val) {
		_mapDescription = val;
	}
	
	private String _playersRecommendedAmount;
	
	public String getPlayersRecommendedAmount() {
		return _playersRecommendedAmount;
	}
	
	public void setPlayersRecommendedAmount(String val) {
		_playersRecommendedAmount = val;
	}
	
	private Coords2DF _cameraBounds1 = new Coords2DF(0F, 0F);
	private Coords2DF _cameraBounds2 = new Coords2DF(0F, 0F);
	private Coords2DF _cameraBounds3 = new Coords2DF(0F, 0F);
	private Coords2DF _cameraBounds4 = new Coords2DF(0F, 0F);
	
	public Coords2DF getCameraBounds1() {
		return _cameraBounds1;
	}

	public Coords2DF getCameraBounds2() {
		return _cameraBounds2;
	}

	public Coords2DF getCameraBounds3() {
		return _cameraBounds3;
	}

	public Coords2DF getCameraBounds4() {
		return _cameraBounds4;
	}
	
	public void setCameraBounds(Coords2DF pos1, Coords2DF pos2, Coords2DF pos3, Coords2DF pos4) {
		_cameraBounds1 = pos1;
		_cameraBounds2 = pos2;
		_cameraBounds3 = pos3;
		_cameraBounds4 = pos4;
	}
	
	private int _marginLeft = 0;
	private int _marginRight = 0;
	private int _marginBottom = 0;
	private int _marginTop = 0;
	
	public int getMarginLeft() {
		return _marginLeft;
	}
	
	public int getMarginRight() {
		return _marginRight;
	}

	public int getMarginBottom() {
		return _marginBottom;
	}

	public int getMarginTop() {
		return _marginTop;
	}
	
	public void setMargins(int left, int right, int bottom, int top) {
		_marginLeft = left;
		_marginRight = right;
		_marginBottom = bottom;
		_marginTop = top;
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
	
	public void setDimensions(int width, int height) {
		_width = width;
		_height = height;
	}
	
	public static class Flags extends FlagsInt {
		public static class Flag extends FlagsInt.Flag {
			public final static Flag HIDE_MINIMAP = new Flag(0);
			public final static Flag MODIFY_ALLY_PRIORITIES = new Flag(1);
			public final static Flag MELEE_MAP = new Flag(2);
			public final static Flag INITIAL_MAP_SIZE_LARGE_NERVER_MODIFIED = new Flag(3);
			public final static Flag MASKED_AREAS_PARTIALLY_VISIBLE = new Flag(4);
			public final static Flag FIXED_PLAYER_FORCE_SETTING = new Flag(5);
			public final static Flag USE_CUSTOM_FORCES = new Flag(6);
			public final static Flag USE_CUSTOM_TECHS = new Flag(7);
			public final static Flag USE_CUSTOM_ABILS = new Flag(8);
			public final static Flag USE_CUSTOM_UPGRADES = new Flag(9);
			public final static Flag MAP_PROPERTIES_WINDOW_OPENED_BEFORE = new Flag(10);
			public final static Flag SHOW_WATER_WAVES_ON_CLIFF_SHORES = new Flag(11);
			public final static Flag SHOW_WATER_WAVES_ON_ROLLING_SHORES = new Flag(12);
			public final static Flag UNKNOWN = new Flag(13);
			public final static Flag UNKNOWN_B = new Flag(14);
			public final static Flag UNKNOWN_C = new Flag(15);
			
			private Flag(int pos) {
				super(pos);
			}
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
	
	private char _tileset;
	
	public char getTileset() {
		return _tileset;
	}
	
	public void setTileset(char val) {
		_tileset = val;
	}

	private String _loadingScreenText;
	
	public String getLoadingScreenText() {
		return _loadingScreenText;
	}
	
	public void setLoadingScreenText(String val) {
		_loadingScreenText = val;
	}
	
	private String _loadingScreenTitle;
	
	public String getLoadingScreenTitle() {
		return _loadingScreenTitle;
	}
	
	public void setLoadingScreenTitle(String val) {
		_loadingScreenTitle = val;
	}
	
	private String _loadingScreenSubtitle;
	
	public String getLoadingScreenSubtitle() {
		return _loadingScreenSubtitle;
	}
	
	public void setLoadingScreenSubtitle(String val) {
		_loadingScreenSubtitle = val;
	}
	
	private int _loadingScreenIndex = 0;
	
	public int getLoadingScreenIndex() {
		return _loadingScreenIndex;
	}
	
	public void setLoadingScreenIndex(int val) {
		_loadingScreenIndex = val;
	}

	private int _campaignBackgroundIndex = 0;
	
	public int getCampaignBackgroundIndex() {
		return _campaignBackgroundIndex;
	}
	
	public void setCampaignBackgroundIndex(int val) {
		_campaignBackgroundIndex = val;
	}

	private String _prologueScreenPath;
	
	public String getPrologueScreenPath() {
		return _prologueScreenPath;
	}
	
	public void setPrologueScreenPath(String val) {
		_prologueScreenPath = val;
	}
	
	private String _prologueScreenText;
	
	public String getPrologueScreenText() {
		return _prologueScreenText;
	}
	
	public void setPrologueScreenText(String val) {
		_prologueScreenText = val;
	}
	
	private String _prologueScreenTitle;
	
	public String getPrologueScreenTitle() {
		return _prologueScreenTitle;
	}
	
	public void setPrologueScreenTitle(String val) {
		_prologueScreenTitle = val;
	}
	
	private String _prologueScreenSubtitle;
	
	public String getPrologueScreenSubtitle() {
		return _prologueScreenSubtitle;
	}
	
	public void setPrologueScreenSubtitle(String val) {
		_prologueScreenSubtitle = val;
	}
	
	private String _loadingScreenModel;
	
	public String getLoadingScreenModel() {
		return _loadingScreenModel;
	}
	
	public void setLoadingScreenModel(String val) {
		_loadingScreenModel = val;
	}
	
	//0 = standard
	private int _gameData = 0;
	
	public int getGameData() {
		return _gameData;
	}

	public void setGameData(int val) {
		_gameData = val;
	}
	
	private TerrainFogType _terrainFogType = TerrainFogType.NONE;
	
	public TerrainFogType getTerrainFogType() {
		return _terrainFogType;
	}
	
	public void setTerrainFogType(TerrainFogType val) {
		_terrainFogType = val;
	}
	
	private float _terrainFogZHeightStart = 0;
	
	public float getTerrainFogZHeightStart() {
		return _terrainFogZHeightStart;
	}
	
	public void setTerrainFogZHeightStart(float val) {
		_terrainFogZHeightStart = val;
	}

	private float _terrainFogZHeightEnd = 0;
	
	public float getTerrainFogZHeightEnd() {
		return _terrainFogZHeightEnd;
	}
	
	public void setTerrainFogZHeightEnd(float val) {
		_terrainFogZHeightEnd = val;
	}
	
	private float _terrainFogDensity = 0;
	
	public float getTerrainFogDensity() {
		return _terrainFogDensity;
	}
	
	public void setTerrainFogDensity(float val) {
		_terrainFogDensity = val;
	}
	
	private Color _terrainFogColor = Color.fromBGRA(0, 0, 0, 0);
	
	public Color getTerrainFogColor() {
		return _terrainFogColor;
	}
	
	public void setTerrainFogColor(Color val) {
		_terrainFogColor = val;
	}
	
	private Id _globalWeatherId;
	
	public Id getGlobalWeatherId() {
		return _globalWeatherId;
	}
	
	public void setGlobalWeatherId(Id val) {
		_globalWeatherId = val;
	}
	
	private String _soundEnv;
	
	public String getSoundEnv() {
		return _soundEnv;
	}
	
	public void setSoundEnv(String val) {
		_soundEnv = val;
	}

	private char _tilesetLightEnv;
	
	public char getTilesetLightEnv() {
		return _tilesetLightEnv;
	}
	
	public void setTilesetLightEnv(char val) {
		_tilesetLightEnv = val;
	}
	
	private Color _waterColor = Color.fromBGRA(255, 255, 255, 255);
	
	public Color getWaterColor() {
		return _waterColor;
	}
	
	public void setWaterColor(Color val) {
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
		
		public Controller getType() {
			return _type;
		}
		
		public void setType(Controller val) {
			_type = val;
		}
		
		public static class UnitRace extends Int {
			private static Map<Integer, UnitRace> _map = new HashMap<>();
			
			public final static UnitRace NIGHT_ELF = new UnitRace(4);
			public final static UnitRace HUMAN = new UnitRace(1);
			public final static UnitRace ORC = new UnitRace(2);
			public final static UnitRace UNDEAD = new UnitRace(3);
			
			public UnitRace(int val) {
				super(val);
				
				_map.put(val, this);
			}

			public static UnitRace valueOf(int val) {
				return _map.get(val);
			}
			
			public static UnitRace decodeStatic(Object val) {
				try {
					return valueOf(Integer.parseInt(val.toString()));
				} catch (NumberFormatException e) {
				}
				
				return null;
			}
		}
		
		private UnitRace _race = UnitRace.HUMAN;
		
		public UnitRace getRace() {
			return _race;
		}
		
		public void setRace(UnitRace val) {
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
		
		public Coords2DF getStartPos() {
			return _startPos;
		}
		
		public void setStartPos(Coords2DF val) {
			_startPos = val;
		}
		
		private FlagsInt _allyLowPrioFlags = FlagsInt.valueOf(0);
		
		public int getAllyLowPrioFlags() {
			return _allyLowPrioFlags.toInt();
		}
		
		public void setAllyLowPrioFlags(int val) {
			_allyLowPrioFlags.setVal(val);
		}
		
		public void setAllyLowPrioFlag(int index, boolean val) {
			_allyLowPrioFlags.setPos(index, val);
		}
		
		private FlagsInt _allyHighPrioFlags = FlagsInt.valueOf(0);
		
		public int getAllyHighPrioFlags() {
			return _allyHighPrioFlags.toInt();
		}
		
		public void setAllyHighPrioFlags(int val) {
			_allyHighPrioFlags.setVal(val);
		}

		public void setAllyHighPrioFlag(int index, boolean val) {
			_allyHighPrioFlags.setPos(index, val);
		}
		
		private void read_0x12(Stream stream) throws StreamException {
			setNum(stream.readInt());
			
			setType(Controller.valueOf(stream.readInt()));
			setRace(UnitRace.valueOf(stream.readInt()));
			
			setStartPosFixed(stream.readInt());
			
			setName(stream.readString());
			
			setStartPos(new Coords2DF(stream.readFloat(), stream.readFloat()));
			
			setAllyLowPrioFlags(stream.readInt());
			setAllyHighPrioFlags(stream.readInt());
		}
		
		private void write_0x12(Stream stream) {
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
		
		private void read(Stream stream, EncodingFormat format) throws StreamException {		
			switch (format.toEnum()) {
			case W3I_0x19:
			case W3I_0x12: {
				read_0x12(stream);
				
				break;
			}
			}
		}
		
		private void write(Stream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case W3I_0x19:
			case W3I_0x12: {
				write_0x12(stream);
				
				break;
			}
			}
		}
		
		public Player(Stream stream, EncodingFormat format) throws StreamException {
			read(stream, format);
		}
		
		public Player() {
		}
	}
	
	private List<Player> _players = new ArrayList<>();
	
	private void addPlayer(Player val) {
		_players.add(val);
	}
	
	public Player addPlayer() {
		Player player = new Player();
		
		addPlayer(player);
		
		return player;
	}
	
	public static class Force {
		static public class Flags extends FlagsInt {
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
			
			protected Flags(int val) {
				super(val);
			}
			
			static public Flags valueOf(int val) {
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
		
		private int _players = 0;
		
		public int getPlayers() {
			return _players;
		}
		
		public void setPlayers(int val) {
			_players = val;
		}
		
		private String _name;
		
		public String getName() {
			return _name;
		}
		
		public void setName(String val) {
			_name = val;
		}
		
		private void read_0x12(Stream stream) throws StreamException {
			setFlags(Flags.valueOf(stream.readInt()));
			
			setPlayers(stream.readInt());
			
			setName(stream.readString());
		}

		private void write_0x12(Stream stream) {
			stream.writeInt(getFlags().toInt());
			
			stream.writeInt(getPlayers());
			
			stream.writeString(getName());
		}
		
		private void read(Stream stream, EncodingFormat format) throws StreamException {		
			switch (format.toEnum()) {
			case W3I_0x19:
			case W3I_0x12: {
				read_0x12(stream);
				
				break;
			}
			}
		}
		
		private void write(Stream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case W3I_0x19:
			case W3I_0x12: {
				write_0x12(stream);
				
				break;
			}
			}
		}
		
		public Force(Stream stream, EncodingFormat format) throws StreamException {
			read(stream, format);
		}
		
		public Force() {
		}
	}
	
	private List<Force> _forces = new ArrayList<>();
	
	private void addForce(Force val) {
		_forces.add(val);
	}
	
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
		
		private void read_0x12(Stream stream) throws StreamException {
			setPlayers(stream.readInt());
			
			setId(stream.readId());
			
			setLevel(stream.readInt());
			
			setAvail(stream.readInt());
		}
		
		private void write_0x12(Stream stream) {
			stream.writeInt(getPlayers());
			
			stream.writeId(getId());
			
			stream.writeInt(getLevel());
			
			stream.writeInt(getAvail());
		}
		
		private void read(Stream stream, EncodingFormat format) throws StreamException {		
			switch (format.toEnum()) {
			case W3I_0x19:
			case W3I_0x12: {
				read_0x12(stream);
				
				break;
			}
			}
		}
		
		private void write(Stream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case W3I_0x19:
			case W3I_0x12: {
				write_0x12(stream);
				
				break;
			}
			}
		}
		
		public UpgradeMod(Stream stream, EncodingFormat format) throws StreamException {
			read(stream, format);
		}
		
		public UpgradeMod() {
		}
	}

	private List<UpgradeMod> _upgradeMods = new ArrayList<>();
	
	private void addUpgradeMod(UpgradeMod val) {
		_upgradeMods.add(val);
	}
	
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
		
		public Id getId() {
			return _id;
		}
		
		public void setId(Id val) {
			_id = val;
		}

		private void read_0x12(Stream stream) throws StreamException {
			setPlayers(stream.readInt());
			
			setId(stream.readId());
		}
		
		private void write_0x12(Stream stream) {
			stream.writeInt(getPlayers());
			
			stream.writeId(getId());
		}
		
		private void read(Stream stream, EncodingFormat format) throws StreamException {		
			switch (format.toEnum()) {
			case W3I_0x19:
			case W3I_0x12: {
				read_0x12(stream);
				
				break;
			}
			}
		}
		
		private void write(Stream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case W3I_0x19:
			case W3I_0x12: {
				write_0x12(stream);
				
				break;
			}
			}
		}
		
		public TechMod(Stream stream, EncodingFormat format) throws StreamException {
			read(stream, format);
		}
		
		public TechMod() {
		}
	}

	private List<TechMod> _techMods = new ArrayList<>();
	
	private void addTechMod(TechMod val) {
		_techMods.add(val);
	}
	
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
		
		public String getName() {
			return _name;
		}
		
		public void setName(String val) {
			_name = val;
		}
		
		enum PositionType {
			UNIT(0),
			STRUCTURE(1),
			ITEM(2);
			
			private int _val;
			
			public int getVal() {
				return _val;
			}
			
			private static Map<Integer, PositionType> _valToPositionTypeMap = new HashMap<>();
			
			PositionType(int val) {
				_val = val;
			}
			
			public static PositionType valueOf(int val) {
				return _valToPositionTypeMap.get(val);
			}
			
			static {
				_valToPositionTypeMap.put(0, UNIT);
			}
		}
		
		private Map<Integer, PositionType> _positionTypes = new HashMap<>();
		
		public PositionType getPositionType(int index) {
			return _positionTypes.get(index);
		}
		
		public void setPositionType(int index, PositionType val) {
			_positionTypes.put(index, val);
		}
		
		public static class Set {
			UnitTable _parent;
			
			private int _chance = 100;
			
			public int getChance() {
				return _chance;
			}
			
			public void setChance(int val) {
				_chance = val;
			}
			
			private Map<Integer, Id> _typeIds = new HashMap<>();
			
			public Id getTypeId(int pos) {
				return _typeIds.get(pos);
			}
			
			public void setTypeId(int pos, Id val) {
				_typeIds.put(pos, val);
			}

			private void read_0x12(Stream stream) throws StreamException {
				setChance(stream.readInt());
				
				for (int i = 0; i < _parent._positionTypes.size(); i++) {
					setTypeId(i, stream.readId());
				}
			}

			private void write_0x12(Stream stream) {
				stream.writeInt(getChance());
				
				for (int i = 0; i < _parent._positionTypes.size(); i++) {
					stream.writeId(getTypeId(i));
				}
			}
			
			private void read(Stream stream, EncodingFormat format) throws StreamException {		
				switch (format.toEnum()) {
				case W3I_0x19:
				case W3I_0x12: {
					read_0x12(stream);
					
					break;
				}
				}
			}
			
			private void write(Stream stream, EncodingFormat format) {
				switch (format.toEnum()) {
				case AUTO:
				case W3I_0x19:
				case W3I_0x12: {
					write_0x12(stream);
					
					break;
				}
				}
			}
			
			public Set(Stream stream, EncodingFormat format, UnitTable parent) throws StreamException {
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

		private void read_0x12(Stream stream) throws StreamException {
			int positionsCount = stream.readInt();
			
			for (int i = 0; i < positionsCount; i++) {
				setPositionType(i, PositionType.valueOf(stream.readInt()));
			}
			
			int setsCount = stream.readInt();
			
			for (int i = 0; i < setsCount; i++) {
				addSet(new Set(stream, EncodingFormat.W3I_0x19, this));
			}
		}
		
		private void write_0x12(Stream stream) {
			int positionsCount = _positionTypes.size();
			
			stream.writeInt(positionsCount);
			
			for (int i = 0; i < positionsCount; i++) {
				stream.writeInt(getPositionType(i).getVal());
			}
			
			for (Set set : _sets) {
				set.write(stream, EncodingFormat.W3I_0x19);
			}
		}
		
		private void read(Stream stream, EncodingFormat format) throws StreamException {		
			switch (format.toEnum()) {
			case W3I_0x19:
			case W3I_0x12: {
				read_0x12(stream);
				
				break;
			}
			}
		}
		
		private void write(Stream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case W3I_0x19:
			case W3I_0x12: {
				write_0x12(stream);
				
				break;
			}
			}
		}
		
		public UnitTable(Stream stream, EncodingFormat format) throws StreamException {
			read(stream, format);
		}
		
		public UnitTable() {
		}
	}
	
	private List<UnitTable> _unitTables = new ArrayList<>();
	
	private void addUnitTable(UnitTable val) {
		_unitTables.add(val);
	}
	
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
			
			public Id getTypeId() {
				return _id;
			}
			
			public void setTypeId(Id val) {
				_id = val;
			}

			private void read_0x19(Stream stream) throws StreamException {
				setChance(stream.readInt());
				
				setTypeId(stream.readId());
			}
			
			private void write_0x19(Stream stream) {
				stream.writeInt(getChance());
				
				stream.writeId(getTypeId());
			}
			
			private void read(Stream stream, EncodingFormat format) throws StreamException {		
				switch (format.toEnum()) {
				case W3I_0x19: {
					read_0x19(stream);
					
					break;
				}
				}
			}
			
			private void write(Stream stream, EncodingFormat format) {
				switch (format.toEnum()) {
				case AUTO:
				case W3I_0x19: {
					write_0x19(stream);
					
					break;
				}
				}
			}
			
			public Set(Stream stream, EncodingFormat format) throws StreamException {
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
		
		private void read_0x19(Stream stream) throws StreamException {
			setIndex(stream.readInt());
			
			setName(stream.readString());
			
			int setsCount = stream.readInt();
			
			for (int i = 0; i < setsCount; i++) {
				addSet(new Set(stream, EncodingFormat.W3I_0x19));
			}
		}
		
		private void write_0x19(Stream stream) {
			stream.writeInt(getIndex());
			
			stream.writeString(getName());
			
			for (Set set : _sets) {
				set.write(stream, EncodingFormat.W3I_0x19);
			}
		}
		
		private void read(Stream stream, EncodingFormat format) throws StreamException {		
			switch (format.toEnum()) {
			case W3I_0x19: {
				read_0x19(stream);
				
				break;
			}
			}
		}
		
		private void write(Stream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case W3I_0x19: {
				write_0x19(stream);
				
				break;
			}
			}
		}
		
		public ItemTable(Stream stream, EncodingFormat format) throws StreamException {
			read(stream, format);
		}
		
		public ItemTable() {
		}
	}

	private List<ItemTable> _itemTables = new ArrayList<>();
	
	private void addItemTable(ItemTable val) {
		_itemTables.add(val);
	}
	
	public ItemTable addItemTable() {
		ItemTable itemTable = new ItemTable();
		
		addItemTable(itemTable);
		
		return itemTable;
	}
	
	public static class EncodingFormat extends Format<EncodingFormat.Enum> {
		enum Enum {
			AUTO,
			W3I_0x19,
			W3I_0x12,
		}

		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat W3I_0x19 = new EncodingFormat(Enum.W3I_0x19, 0x19);
		public final static EncodingFormat W3I_0x12 = new EncodingFormat(Enum.W3I_0x12, 0x12);

		private static Map<Integer, EncodingFormat> _map = new HashMap<>();
		
		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}
		
		private EncodingFormat(Enum enumVal, int version) {
			super(enumVal, version);
			
			_map.put(version, this);
		}
	}
	
	private void read_0x12(Stream stream) throws StreamException {
		int version = stream.readInt();
		
		Wc3bin.checkFormatVer("infoFileMaskFunc", EncodingFormat.W3I_0x12.getVersion(), version);
		
		setSavesAmount(stream.readInt());
		setEditorVersion(stream.readInt());
		setMapName(stream.readString());
		setMapAuthor(stream.readString());
		setMapDescription(stream.readString());
		setPlayersRecommendedAmount(stream.readString());
		
		setCameraBounds(new Coords2DF(stream.readFloat(), stream.readFloat()), new Coords2DF(stream.readFloat(), stream.readFloat()), new Coords2DF(stream.readFloat(), stream.readFloat()), new Coords2DF(stream.readFloat(), stream.readFloat()));
		setMargins(stream.readInt(), stream.readInt(), stream.readInt(), stream.readInt());
		
		setDimensions(stream.readInt(), stream.readInt());
		
		setFlags(Flags.valueOf(stream.readInt()));
		
		setTileset(stream.readChar());
		
		setCampaignBackgroundIndex(stream.readInt());
		
		setLoadingScreenText(stream.readString());
		setLoadingScreenTitle(stream.readString());
		setLoadingScreenSubtitle(stream.readString());
		setLoadingScreenIndex(stream.readInt());
		
		setPrologueScreenText(stream.readString());
		setPrologueScreenTitle(stream.readString());
		setPrologueScreenSubtitle(stream.readString());
		
		int playersCount = stream.readInt();
		
		for (int i = 0; i < playersCount; i++) {
			addPlayer(new Player(stream, EncodingFormat.W3I_0x12));
		}
		
		int forcesCount = stream.readInt();
		
		for (int i = 0; i < forcesCount; i++) {
			addForce(new Force(stream, EncodingFormat.W3I_0x12));
		}
		
		int upgradeModsCount = stream.readInt();
		
		for (int i = 0; i < upgradeModsCount; i++) {
			addUpgradeMod(new UpgradeMod(stream, EncodingFormat.W3I_0x12));
		}
		
		int techModsCount = stream.readInt();
		
		for (int i = 0; i < techModsCount; i++) {
			addTechMod(new TechMod(stream, EncodingFormat.W3I_0x12));
		}
		
		int unitTablesCount = stream.readInt();
		
		for (int i = 0; i < unitTablesCount; i++) {
			addUnitTable(new UnitTable(stream, EncodingFormat.W3I_0x12));
		}
	}

	private void write_0x12(Stream stream) {
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
		
		stream.writeInt(getMarginLeft());
		stream.writeInt(getMarginRight());
		stream.writeInt(getMarginBottom());
		stream.writeInt(getMarginTop());
		
		stream.writeInt(getWidth());
		stream.writeInt(getHeight());
		
		stream.writeInt(getFlags().toInt());
		
		stream.writeChar(getTileset());
		
		stream.writeInt(getCampaignBackgroundIndex());
		
		stream.writeString(getLoadingScreenText());
		stream.writeString(getLoadingScreenTitle());
		stream.writeString(getLoadingScreenSubtitle());
		stream.writeInt(getLoadingScreenIndex());
		
		stream.writeString(getPrologueScreenText());
		stream.writeString(getPrologueScreenTitle());
		stream.writeString(getPrologueScreenSubtitle());
		
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
	}
	
	private void read_0x19(Stream stream) throws StreamException {
		int version = stream.readInt();
		
		Wc3bin.checkFormatVer("infoFileMaskFunc", EncodingFormat.W3I_0x19.getVersion(), version);

		setSavesAmount(stream.readInt());
		setEditorVersion(stream.readInt());
		setMapName(stream.readString());
		setMapAuthor(stream.readString());
		setMapDescription(stream.readString());
		setPlayersRecommendedAmount(stream.readString());
		
		setCameraBounds(new Coords2DF(stream.readFloat(), stream.readFloat()), new Coords2DF(stream.readFloat(), stream.readFloat()), new Coords2DF(stream.readFloat(), stream.readFloat()), new Coords2DF(stream.readFloat(), stream.readFloat()));
		setMargins(stream.readInt(), stream.readInt(), stream.readInt(), stream.readInt());
		
		setDimensions(stream.readInt(), stream.readInt());
		
		setFlags(Flags.valueOf(stream.readInt()));
		
		setTileset(stream.readChar());

		setLoadingScreenIndex(stream.readInt());
		setLoadingScreenModel(stream.readString());
		setLoadingScreenText(stream.readString());
		setLoadingScreenTitle(stream.readString());
		setLoadingScreenSubtitle(stream.readString());
		
		setGameData(stream.readInt());

		setPrologueScreenPath(stream.readString());
		setPrologueScreenText(stream.readString());
		setPrologueScreenTitle(stream.readString());
		setPrologueScreenSubtitle(stream.readString());
		
		setTerrainFogType(TerrainFogType.valueOf(stream.readInt()));
		setTerrainFogZHeightStart(stream.readFloat());
		setTerrainFogZHeightEnd(stream.readFloat());
		setTerrainFogDensity(stream.readFloat());
		
		int terrainFogBlue = stream.readByte();
		int terrainFogGreen = stream.readByte();
		int terrainFogRed = stream.readByte();
		int terrainFogAlpha = stream.readByte();
		
		setTerrainFogColor(Color.fromRGBA(terrainFogRed, terrainFogGreen, terrainFogBlue, terrainFogAlpha));
		
		setGlobalWeatherId(stream.readId());
		setSoundEnv(stream.readString());
		setTilesetLightEnv(stream.readChar());
		
		int waterBlue = stream.readByte();
		int waterGreen = stream.readByte();
		int waterRed = stream.readByte();
		int waterAlpha = stream.readByte();
		
		setWaterColor(Color.fromRGBA(waterRed, waterGreen, waterBlue, waterAlpha));

		int playersCount = stream.readInt();
		
		for (int i = 0; i < playersCount; i++) {
			addPlayer(new Player(stream, EncodingFormat.W3I_0x19));
		}
		
		int forcesCount = stream.readInt();
		
		for (int i = 0; i < forcesCount; i++) {
			addForce(new Force(stream, EncodingFormat.W3I_0x19));
		}
		
		int upgradeModsCount = stream.readInt();
		
		for (int i = 0; i < upgradeModsCount; i++) {
			addUpgradeMod(new UpgradeMod(stream, EncodingFormat.W3I_0x19));
		}
		
		int techModsCount = stream.readInt();
		
		for (int i = 0; i < techModsCount; i++) {
			addTechMod(new TechMod(stream, EncodingFormat.W3I_0x19));
		}
		
		int unitTablesCount = stream.readInt();
		
		for (int i = 0; i < unitTablesCount; i++) {
			addUnitTable(new UnitTable(stream, EncodingFormat.W3I_0x19));
		}
	}

	private void write_0x19(Stream stream) {
		stream.writeInt(EncodingFormat.W3I_0x19.getVersion());
		
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
		
		stream.writeInt(getMarginLeft());
		stream.writeInt(getMarginRight());
		stream.writeInt(getMarginBottom());
		stream.writeInt(getMarginTop());
		
		stream.writeInt(getWidth());
		stream.writeInt(getHeight());
		
		stream.writeInt(getFlags().toInt());
		
		stream.writeChar(getTileset());
		
		stream.writeInt(getLoadingScreenIndex());
		stream.writeString(getLoadingScreenModel());
		stream.writeString(getLoadingScreenText());
		stream.writeString(getLoadingScreenTitle());
		stream.writeString(getLoadingScreenSubtitle());
		
		stream.writeInt(getGameData());
		
		stream.writeString(getPrologueScreenPath());
		stream.writeString(getPrologueScreenText());
		stream.writeString(getPrologueScreenTitle());
		stream.writeString(getPrologueScreenSubtitle());
		
		stream.writeInt(getTerrainFogType().getVal());
		stream.writeFloat(getTerrainFogZHeightStart());
		stream.writeFloat(getTerrainFogZHeightEnd());
		stream.writeFloat(getTerrainFogDensity());
		
		Color terrainFogColor = getTerrainFogColor();
		
		stream.writeByte(terrainFogColor.getBlue());
		stream.writeByte(terrainFogColor.getGreen());
		stream.writeByte(terrainFogColor.getRed());
		stream.writeByte(terrainFogColor.getAlpha());
		
		stream.writeId(getGlobalWeatherId());
		stream.writeString(getSoundEnv());
		stream.writeChar(getTilesetLightEnv());
		
		Color waterColor = getWaterColor();
		
		stream.writeByte(waterColor.getBlue());
		stream.writeByte(waterColor.getGreen());
		stream.writeByte(waterColor.getRed());
		stream.writeByte(waterColor.getAlpha());
		
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
	}
	
	private void read_auto(Stream stream) throws StreamException {
		Id startToken = stream.readId();
		
		int version = stream.readInt();
		
		stream.rewind();

		read(stream, EncodingFormat.valueOf(version));
	}

	private void read(Stream stream, EncodingFormat format) throws StreamException {		
		switch (format.toEnum()) {
		case AUTO: {
			read_auto(stream);
			
			break;
		}
		case W3I_0x19: {
			read_0x19(stream);
			
			break;
		}
		}
	}
	
	private void write(Stream stream, EncodingFormat format) {
		switch (format.toEnum()) {
		case AUTO:
		case W3I_0x19: {
			write_0x19(stream);
			
			break;
		}
		}
	}
	
	private void read(Stream stream) throws StreamException {
		read(stream, EncodingFormat.AUTO);
	}
	
	private void write(Stream stream) {
		write(stream, EncodingFormat.AUTO);
	}
	
	private void read(File file, EncodingFormat format) throws IOException {
		read(new Wc3bin.Stream(file), format);
	}
	
	private void write(File file, EncodingFormat format) throws IOException {
		write(new Wc3bin.Stream(file), format);
	}
	
	private void read(File file) throws IOException {
		read(file, EncodingFormat.AUTO);
	}

	private void write(File file) throws IOException {
		write(new Wc3bin.Stream(file));
	}
}
