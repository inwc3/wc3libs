package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Format;
import net.moonlightflower.wc3libs.bin.Wc3BinStream;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.port.LadikMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * info file for wrapping war3campaign.w3f
 */
public class W3F {
	public final static File CAMPAIGN_PATH = new File("war3campaign.w3f");
	
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
	
	private String _name;
	
	public String getCampaignName() {
		return _name;
	}
	
	public void setCampaignName(String val) {
		_name = val;
	}

	private String _difficulty;

	public String getDifficulty() {
		return _difficulty;
	}
	
	public void setDifficulty(String val) {
		_difficulty = val;
	}
	
	private String _author;
	
	public String getCampaignAuthor() {
		return _author;
	}
	
	public void setCampaignAuthor(String val) {
		_author = val;
	}

	private String _description;
	
	public String getCampaignDescription() {
		return _description;
	}
	
	public void setCampaignDescription(String val) {
		_description = val;
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
			
			public final static Flag VAR_DIFFICULTY = new Flag(0, "varDifficulty");
			public final static Flag CONTAINS_EXPANSION_MAPS = new Flag(1, "containsExpansionMaps");
			
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
	
	private LoadingScreenBackground _campaignBackground;
	
	public LoadingScreenBackground getCampaignBackground() {
		return _campaignBackground;
	}

	public void setCampaignBackground(LoadingScreenBackground val) {
		_campaignBackground = val;
	}

	private void setCampaignBackground(int index, String customPath) {
		if (index < 0) {
			setCampaignBackground(new LoadingScreenBackground.CustomBackground(new File(customPath)));
		} else {
			setCampaignBackground(LoadingScreenBackground.PresetBackground.valueOf(index));
		}
	}

	private File _minimapPath;
	
	public File getMinimapPath() {
		return _minimapPath;
	}
	
	public void setMinimapPath(File val) {
		_minimapPath = val;
	}

	public static class AmbientSound {};

	public static class PresetAmbientSound extends AmbientSound {
		private static Map<Integer, AmbientSound> _map = new LinkedHashMap<>();
		
		public final static PresetAmbientSound TUTORIAL = new PresetAmbientSound(0, "TUTORIAL");
		public final static PresetAmbientSound HUMAN = new PresetAmbientSound(1, "HUMAN");
		public final static PresetAmbientSound ORC = new PresetAmbientSound(2, "ORC");
		public final static PresetAmbientSound UNDEAD = new PresetAmbientSound(3, "UNDEAD");
		public final static PresetAmbientSound NIGHTELF = new PresetAmbientSound(4, "NIGHTELF");
		public final static PresetAmbientSound NIGHTELF_EXPANSION = new PresetAmbientSound(5, "NIGHTELFEXP");
		public final static PresetAmbientSound HUMAN_EXPANSION = new PresetAmbientSound(6, "HUMANEXP");
		public final static PresetAmbientSound UNDEAD_EXPANSION = new PresetAmbientSound(7, "UNDEADEXP");
		
		private int _index;
		
		public int getIndex() {
			return _index;
		}
		
		private String _label;
		
		public String getLabel() {
			return _label;
		}
		
		@Override
		public String toString() {
			return _label;
		}
		
		public static AmbientSound valueOf(int index) {
			return _map.get(index);
		}
		
		private PresetAmbientSound(int index, String label) {
			_map.put(index, this);
			
			_index = index;
			_label = String.format("WESTRING_AMBIENTSOUND_%s", label);
		}
	}
	
	public static class CustomAmbientSound extends AmbientSound {
		private File _path = null;
		
		public File getCustomPath() {
			return _path;
		}
		
		@Override
		public String toString() {
			return getCustomPath().toString();
		}
		
		public CustomAmbientSound(File path) {
			_path = path;
		}
	}
	
	private AmbientSound _ambientSound;
	
	public AmbientSound getAmbientSound() {
		return _ambientSound;
	}
	
	public void setAmbientSound(AmbientSound val) {
		_ambientSound = val;
	}
	
	private void setAmbientSound(int val, String path) {
		if (val < 0) {
			setAmbientSound(new CustomAmbientSound(new File(path)));
		} else if (val > 0) {
			setAmbientSound(PresetAmbientSound.valueOf(val));
		} else {
			setAmbientSound(null);
		}
	}
	
	private TerrainFog _terrainFog;
	
	public TerrainFog getTerrainFog() {
		return _terrainFog;
	}
	
	public void setTerrainFog(TerrainFog val) {
		_terrainFog = val;
	}
	
	public static class UIRace {
		private static Map<Integer, UIRace> _map = new LinkedHashMap<>();
		
		public final static UIRace HUMAN = new UIRace(0, "HUMAN");
		public final static UIRace ORC = new UIRace(1, "ORC");
		public final static UIRace UNDEAD = new UIRace(2, "UNDEAD");
		public final static UIRace NIGHTELF = new UIRace(3, "NIGHTELF");
		
		private int _val;
		
		public int getVal() {
			return _val;
		}
		
		private String _label;
		
		public String getLabel() {
			return _label;
		}
		
		@Override
		public String toString() {
			return getLabel();
		}
		
		public static UIRace valueOf(int val) {
			return _map.get(val);
		}
		
		private UIRace(int val, String label) {
			_map.put(val, this);
			
			_val = val;
			_label = String.format("WESTRING_RACE_%s", label);
		}
	}
	
	private UIRace _uiRace;
	
	public UIRace getUIRace() {
		return _uiRace;
	}
	
	public void setUIRace(UIRace val) {
		_uiRace = val;
	}
	
	public static class MapEntry {
		private boolean _isVisible = false;
		
		public boolean isVisible() {
			return _isVisible;
		}
		
		public void setVisible(boolean val) {
			_isVisible = true;
		}
		
		private String _chapterTitle;
		
		public String getChapterTitle() {
			return _chapterTitle;
		}
		
		public void setChapterTitle(String val) {
			_chapterTitle = val;
		}
		
		private String _mapTitle;
		
		public String getMapTitle() {
			return _mapTitle;
		}
		

		public void setMapTitle(String val) {
			_mapTitle = val;
		}

		private String _mapPath;
		
		public String getMapPath() {
			return _mapPath;
		}

		public void setMapPath(String val) {
			_mapPath = val;
		}

		public void print(PrintStream outStream) {
			outStream.println(String.format("isVisible: %s", isVisible()));
			outStream.println(String.format("chapterTitle: %s", getChapterTitle()));
			outStream.println(String.format("mapTitle: %s", getMapTitle()));
			outStream.println(String.format("mapPath: %s", getMapPath()));
		}
		
		public void print() {
			print(System.out);
		}

		private void read_0x1(Wc3BinStream stream) throws BinStream.StreamException {
			setVisible((stream.readInt() > 0));
			setChapterTitle(stream.readString());
			setMapTitle(stream.readString());
			setMapPath(stream.readString());
		}
		
		private void write_0x1(Wc3BinStream stream) {
			stream.writeInt(isVisible() ? 1 : 0);
			stream.writeString(getChapterTitle());
			stream.writeString(getMapTitle());
			stream.writeString(getMapPath());
		}
		
		private void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {		
			switch (format.toEnum()) {
			case W3F_0x1:
				read_0x1(stream);
				
				break;
			}
		}
		
		private void write(Wc3BinStream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case W3F_0x1: {
				write_0x1(stream);
				
				break;
			}
			}
		}
		
		public MapEntry(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
			read(stream, format);
		}
		
		public MapEntry() {
		}
	}
	
	private List<MapEntry> _maps = new ArrayList<>();
	
	public List<MapEntry> getMaps() {
		return _maps;
	}
	
	private void addMap(MapEntry val) {
		_maps.add(val);
	}
	
	public MapEntry addMap() {
		MapEntry map = new MapEntry();
		
		addMap(map);
		
		return map;
	}

	public static class ListedMapEntry {
		private String _unknown;
		
		public String getUnknown() {
			return _unknown;
		}
		

		public void setUnknown(String val) {
			_unknown = val;
		}

		private String _mapPath;
		
		public String getMapPath() {
			return _mapPath;
		}

		public void setMapPath(String val) {
			_mapPath = val;
		}
		
		public void print(PrintStream outStream) {
			outStream.println(String.format("unknown: %s", getUnknown()));
			outStream.println(String.format("mapPath: %s", getMapPath()));
		}
		
		public void print() {
			print(System.out);
		}

		private void read_0x1(Wc3BinStream stream) throws BinStream.StreamException {
			setUnknown(stream.readString());
			setMapPath(stream.readString());
		}
		
		private void write_0x1(Wc3BinStream stream) {
			stream.writeString(getUnknown());
			stream.writeString(getMapPath());
		}
		
		private void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {		
			switch (format.toEnum()) {
			case W3F_0x1:
				read_0x1(stream);
				
				break;
			}
		}
		
		private void write(Wc3BinStream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case W3F_0x1: {
				write_0x1(stream);
				
				break;
			}
			}
		}
		
		public ListedMapEntry(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
			read(stream, format);
		}
		
		public ListedMapEntry() {
		}
	}
	
	private List<ListedMapEntry> _listedMaps = new ArrayList<>();
	
	public List<ListedMapEntry> getListedMaps() {
		return _listedMaps;
	}
	
	private void addListedMap(ListedMapEntry val) {
		_listedMaps.add(val);
	}
	
	public ListedMapEntry addListedMap() {
		ListedMapEntry map = new ListedMapEntry();
		
		addListedMap(map);
		
		return map;
	}
	
	public void print(PrintStream outStream) {
		outStream.println(String.format("savesAmount: %d", getSavesAmount()));
		outStream.println(String.format("editorVersion: %d", getEditorVersion()));
		outStream.println(String.format("campaignName: %s", getCampaignName()));
		outStream.println(String.format("difficulty: %s", getDifficulty()));
		outStream.println(String.format("author: %s", getCampaignAuthor()));
		outStream.println(String.format("description: %s", getCampaignDescription()));
		
		outStream.println(String.format("flags: %s", getFlags()));
		
		outStream.println(String.format("campaignBackground: %s", getCampaignBackground()));
		outStream.println(String.format("minimapPath: %s", getMinimapPath()));
		
		outStream.println(String.format("ambientSound: %s", getAmbientSound()));
		outStream.println(String.format("terrainFog: %s", getTerrainFog()));
		outStream.println(String.format("uiRace: %s", getUIRace()));
		
		for (int i = 0; i < _maps.size(); i++) {
			outStream.println(String.format("mapEntry %d:", i));
			
			_maps.get(i).print(outStream);
		}
		
		for (int i = 0; i < _listedMaps.size(); i++) {
			outStream.println(String.format("listedMapEntry %d:", i));
			
			_listedMaps.get(i).print(outStream);
		}
	}
	
	public void print() {
		print(System.out);
	}
	
	public static class EncodingFormat extends Format<EncodingFormat.Enum> {
		enum Enum {
			AUTO,
			W3F_0x1
		}

		private static Map<Integer, EncodingFormat> _map = new LinkedHashMap<>();
		
		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat W3F_0x1 = new EncodingFormat(Enum.W3F_0x1, 0x1);
		
		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}
		
		private EncodingFormat(Enum enumVal, int version) {
			super(enumVal, version);
			
			_map.put(version, this);
		}
	}
	
	private void read_0x1(Wc3BinStream stream) throws BinStream.StreamException {
		int version = stream.readInt("version");
		
		Wc3BinStream.checkFormatVer("infoFileMaskFunc", EncodingFormat.W3F_0x1.getVersion(), version);
		
		setSavesAmount(stream.readInt("savesAmount"));
		setEditorVersion(stream.readInt("editorVersion"));
		
		setCampaignName(stream.readString("campaignName"));
		setDifficulty(stream.readString("difficulty"));
		setCampaignAuthor(stream.readString("author"));
		setCampaignDescription(stream.readString("description"));

		setFlags(Flags.valueOf(stream.readInt()));
		
		setCampaignBackground(stream.readInt(), stream.readString());
		setMinimapPath(new File(stream.readString()));
		setAmbientSound(stream.readInt(), stream.readString());
		
		setTerrainFog(new TerrainFog(
				TerrainFogType.valueOf(stream.readInt()),
				stream.readFloat(), stream.readFloat(), 
				stream.readFloat(),
				Color.fromRGBA(stream.readUByte(), stream.readUByte(), stream.readUByte(), stream.readUByte())
		));
		
		setUIRace(UIRace.valueOf(stream.readInt()));
		
		int mapsCount = stream.readInt();
		
		for (int i = 0; i < mapsCount; i++) {
			addMap(new MapEntry(stream, EncodingFormat.W3F_0x1));
		}
		
		int listedMapsCount = stream.readInt();
		
		for (int i = 0; i < listedMapsCount; i++) {
			addListedMap(new ListedMapEntry(stream, EncodingFormat.W3F_0x1));
		}
	}

	private void write_0x1(Wc3BinStream stream) {
		stream.writeInt(EncodingFormat.W3F_0x1.getVersion());
		
		stream.writeInt(getSavesAmount());
		stream.writeInt(getEditorVersion());
		
		stream.writeString(getCampaignName());
		stream.writeString(getDifficulty());
		stream.writeString(getCampaignAuthor());
		stream.writeString(getCampaignDescription());
		
		stream.writeInt(getFlags().toInt());

		LoadingScreenBackground background = getCampaignBackground();

		stream.writeInt((background instanceof LoadingScreenBackground.PresetBackground) ? ((LoadingScreenBackground.PresetBackground) background).getIndex() : -1);
		stream.writeString((background instanceof LoadingScreenBackground.CustomBackground) ? ((LoadingScreenBackground.CustomBackground) background).getCustomPath().toString() : null);
		stream.writeString(getMinimapPath().toString());
		
		AmbientSound ambientSound = getAmbientSound();

		if (ambientSound != null) {
			stream.writeInt((ambientSound instanceof PresetAmbientSound) ? ((PresetAmbientSound) ambientSound).getIndex() : -1);
			stream.writeString((ambientSound instanceof CustomAmbientSound) ? ((CustomAmbientSound) ambientSound).getCustomPath().toString() : (String) null);
		} else {
			stream.writeInt(0);
			stream.writeString((String) null);
		}
		
		TerrainFog terrainFog = getTerrainFog();
		
		stream.writeInt(terrainFog.getType());
		stream.writeFloat(terrainFog.getZStart());
		stream.writeFloat(terrainFog.getZEnd());
		stream.writeFloat(terrainFog.getDensity());
		
		Color terrainFogColor = terrainFog.getColor();
		
		stream.writeUByte(terrainFogColor.getRed());
		stream.writeUByte(terrainFogColor.getGreen());
		stream.writeUByte(terrainFogColor.getBlue());
		stream.writeUByte(terrainFogColor.getAlpha());

		stream.writeInt(getUIRace().getVal());
		
		stream.writeInt(getMaps().size());
		
		for (int i = 0; i < getMaps().size(); i++) {
			getMaps().get(i).write(stream, EncodingFormat.W3F_0x1);
		}
		
		stream.writeInt(getListedMaps().size());
		
		for (int i = 0; i < getListedMaps().size(); i++) {
			getListedMaps().get(i).write(stream, EncodingFormat.W3F_0x1);
		}
	}
	
	private void read_auto(Wc3BinStream stream) throws Exception {
		int version = stream.readInt("version");

		stream.rewind();

		read(stream, EncodingFormat.valueOf(version));
	}

	private void read(Wc3BinStream stream, EncodingFormat format) throws Exception {
		if (format == null) throw new Exception("no format");
		
		switch (format.toEnum()) {
		case AUTO: {
			read_auto(stream);
			
			break;
		}
		case W3F_0x1: {
			read_0x1(stream);
			
			break;
		}
		}
	}
	
	private void write(Wc3BinStream stream, EncodingFormat format) {
		switch (format.toEnum()) {
		case AUTO:
		case W3F_0x1: {
			write_0x1(stream);
			
			break;
		}
		}
	}
	
	private void read(Wc3BinStream stream) throws Exception {
		read(stream, EncodingFormat.AUTO);
	}
	
	private void write(Wc3BinStream stream) {
		write(stream, EncodingFormat.AUTO);
	}
	
	private void read(File file, EncodingFormat format) throws Exception {
		read(new Wc3BinStream(file), format);
	}
	
	private void write(File file, EncodingFormat format) throws IOException {
		write(new Wc3BinStream(file), format);
	}
	
	private void read(InputStream inStream) throws Exception {
		read(new Wc3BinStream(inStream), EncodingFormat.AUTO);
	}
	
	private void read(File file) throws Exception {
		read(file, EncodingFormat.AUTO);
	}

	public void write(File file) throws IOException {
		Wc3BinStream outStream = new Wc3BinStream();
		
		write(outStream);
		
		outStream.writeTo(file);
	}
	
	public W3F() {
		
	}
	
	public W3F(InputStream inStream) throws Exception {
		read(inStream);
	}
	
	public W3F(File inFile) throws Exception {
		InputStream inStream = new FileInputStream(inFile);
		
		read(inStream);
		
		inStream.close();
	}
	
	public static W3F ofCampaignFile(File mapFile) throws Exception {
		MpqPort.Out port = new LadikMpqPort.Out();
		
		port.add(CAMPAIGN_PATH);
		
		MpqPort.Out.Result portResult = port.commit(mapFile);

		if (!portResult.getExports().containsKey(CAMPAIGN_PATH)) throw new IOException("could not extract info file");

		InputStream inStream = portResult.getInputStream(CAMPAIGN_PATH);
		
		return new W3F(inStream);
	}
}
