package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.Format;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
			
			private Flag(int pos, @Nonnull String label) {
				super(label, pos);
				
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
	
	private LoadingScreenBackground _campaignBackground;

	@Nullable
	public LoadingScreenBackground getCampaignBackground() {
		return _campaignBackground;
	}

	public void setCampaignBackground(@Nullable LoadingScreenBackground val) {
		_campaignBackground = val;
	}

	private void setCampaignBackground(int index, String customPath) {
		if (index < 0) {
			setCampaignBackground(new LoadingScreenBackground.CustomBackground(new File(customPath)));
		} else if (index > 0) {
			setCampaignBackground(LoadingScreenBackground.PresetBackground.valueOf(index));
		} else {
			setCampaignBackground(null);
		}
	}

	private File _minimapPath;

	@Nullable
	public File getMinimapPath() {
		return _minimapPath;
	}
	
	public void setMinimapPath(@Nullable File val) {
		_minimapPath = val;
	}

	public static class AmbientSound {}

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
		
		private PresetAmbientSound(int index, @Nonnull String label) {
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
		
		public CustomAmbientSound(@Nonnull File path) {
			_path = path;
		}
	}
	
	private AmbientSound _ambientSound;

	@Nullable
	public AmbientSound getAmbientSound() {
		return _ambientSound;
	}
	
	public void setAmbientSound(@Nullable AmbientSound val) {
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

	@Nullable
	public TerrainFog getTerrainFog() {
		return _terrainFog;
	}
	
	public void setTerrainFog(@Nullable TerrainFog val) {
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

		@Nullable
		public static UIRace valueOf(int val) {
			return _map.get(val);
		}
		
		private UIRace(int val, @Nonnull String label) {
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

		@Nonnull
		public String getChapterTitle() {
			return _chapterTitle;
		}
		
		public void setChapterTitle(@Nonnull String val) {
			_chapterTitle = val;
		}
		
		private String _mapTitle;

		@Nonnull
		public String getMapTitle() {
			return _mapTitle;
		}

		public void setMapTitle(@Nonnull String val) {
			_mapTitle = val;
		}

		private String _mapPath;

		@Nonnull
		public String getMapPath() {
			return _mapPath;
		}

		public void setMapPath(@Nonnull String val) {
			_mapPath = val;
		}

		public void print(@Nonnull PrintStream outStream) {
			outStream.printf("isVisible: %s%n", isVisible());
			outStream.printf("chapterTitle: %s%n", getChapterTitle());
			outStream.printf("mapTitle: %s%n", getMapTitle());
			outStream.printf("mapPath: %s%n", getMapPath());
		}
		
		public void print() {
			print(System.out);
		}

		private void read_0x1(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			setVisible((stream.readInt32() > 0));
			setChapterTitle(stream.readString());
			setMapTitle(stream.readString());
			setMapPath(stream.readString());
		}
		
		private void write_0x1(@Nonnull Wc3BinOutputStream stream) {
			stream.writeInt32(isVisible() ? 1 : 0);
			stream.writeString(getChapterTitle());
			stream.writeString(getMapTitle());
			stream.writeString(getMapPath());
		}
		
		private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			switch (format.toEnum()) {
			case W3F_0x1:
				read_0x1(stream);
				
				break;
			}
		}
		
		private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case W3F_0x1: {
				write_0x1(stream);
				
				break;
			}
			}
		}
		
		public MapEntry(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			read(stream, format);
		}
		
		public MapEntry() {
		}
	}
	
	private List<MapEntry> _maps = new ArrayList<>();

	@Nonnull
	public List<MapEntry> getMaps() {
		return _maps;
	}
	
	private void addMap(@Nonnull MapEntry val) {
		_maps.add(val);
	}

	@Nonnull
	public MapEntry addMap() {
		MapEntry map = new MapEntry();
		
		addMap(map);
		
		return map;
	}

	public static class ListedMapEntry {
		private String _unknown;

		@Nullable
		public String getUnknown() {
			return _unknown;
		}

		public void setUnknown(@Nullable String val) {
			_unknown = val;
		}

		private String _mapPath;
		
		public String getMapPath() {
			return _mapPath;
		}

		public void setMapPath(String val) {
			_mapPath = val;
		}
		
		public void print(@Nonnull PrintStream outStream) {
			outStream.printf("unknown: %s%n", getUnknown());
			outStream.printf("mapPath: %s%n", getMapPath());
		}
		
		public void print() {
			print(System.out);
		}

		private void read_0x1(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			setUnknown(stream.readString());
			setMapPath(stream.readString());
		}
		
		private void write_0x1(@Nonnull Wc3BinOutputStream stream) {
			stream.writeString(getUnknown());
			stream.writeString(getMapPath());
		}
		
		private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			switch (format.toEnum()) {
			case W3F_0x1:
				read_0x1(stream);
				
				break;
			}
		}
		
		private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case W3F_0x1: {
				write_0x1(stream);
				
				break;
			}
			}
		}
		
		public ListedMapEntry(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			read(stream, format);
		}
		
		public ListedMapEntry() {
		}
	}
	
	private List<ListedMapEntry> _listedMaps = new ArrayList<>();

	@Nonnull
	public List<ListedMapEntry> getListedMaps() {
		return _listedMaps;
	}
	
	private void addListedMap(@Nonnull ListedMapEntry val) {
		_listedMaps.add(val);
	}

	@Nonnull
	public ListedMapEntry addListedMap() {
		ListedMapEntry map = new ListedMapEntry();
		
		addListedMap(map);
		
		return map;
	}
	
	public void print(@Nonnull PrintStream outStream) {
		outStream.printf("savesAmount: %d%n", getSavesAmount());
		outStream.printf("editorVersion: %d%n", getEditorVersion());
		outStream.printf("campaignName: %s%n", getCampaignName());
		outStream.printf("difficulty: %s%n", getDifficulty());
		outStream.printf("author: %s%n", getCampaignAuthor());
		outStream.printf("description: %s%n", getCampaignDescription());
		
		outStream.printf("flags: %s%n", getFlags());
		
		outStream.printf("campaignBackground: %s%n", getCampaignBackground());
		outStream.printf("minimapPath: %s%n", getMinimapPath());
		
		outStream.printf("ambientSound: %s%n", getAmbientSound());
		outStream.printf("terrainFog: %s%n", getTerrainFog());
		outStream.printf("uiRace: %s%n", getUIRace());
		
		for (int i = 0; i < _maps.size(); i++) {
			outStream.printf("mapEntry %d:%n", i);
			
			_maps.get(i).print(outStream);
		}
		
		for (int i = 0; i < _listedMaps.size(); i++) {
			outStream.printf("listedMapEntry %d:%n", i);
			
			_listedMaps.get(i).print(outStream);
		}
	}
	
	public void print() {
		print(System.out);
	}
	
	public static class EncodingFormat extends Format<EncodingFormat.Enum> {
		public enum Enum {
			AUTO,
			W3F_0x1
		}
		
		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat W3F_0x1 = new EncodingFormat(Enum.W3F_0x1, 0x1);

		@Nullable
		public static EncodingFormat valueOf(@Nonnull Integer version) {
			return get(EncodingFormat.class, version);
		}
		
		private EncodingFormat(@Nonnull Enum enumVal, int version) {
			super(enumVal, version);
		}
	}
	
	private void read_0x1(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int version = stream.readInt32("version");
		
		stream.checkFormatVersion(EncodingFormat.W3F_0x1.getVersion(), version);
		
		setSavesAmount(stream.readInt32("savesAmount"));
		setEditorVersion(stream.readInt32("editorVersion"));
		
		setCampaignName(stream.readString("campaignName"));
		setDifficulty(stream.readString("difficulty"));
		setCampaignAuthor(stream.readString("author"));
		setCampaignDescription(stream.readString("description"));

		setFlags(Flags.valueOf(stream.readInt32("flags")));
		
		setCampaignBackground(stream.readInt32("backgroundIndex"), stream.readString("backgroundCustomPath"));
		setMinimapPath(new File(stream.readString("minimapPath")));
		setAmbientSound(stream.readInt32("ambientSoundVal"), stream.readString("ambientSoundPath"));

		TerrainFogType terrainFogType = TerrainFogType.valueOf(stream.readInt32("type"));
		War3Real terrainFogZStart = stream.readReal("zStart");
		War3Real terrainFogZEnd = stream.readReal("zEnd");
		War3Real terrainFogDensity = stream.readReal("density");
		Color terrainFogColor = Color.fromRGBA255(stream.readUByte("red"), stream.readUByte("green"), stream.readUByte("blue"), stream.readUByte("alpha"));

		if (terrainFogType != null) setTerrainFog(new TerrainFog(terrainFogType, terrainFogZStart, terrainFogZEnd, terrainFogDensity, terrainFogColor));
		
		setUIRace(UIRace.valueOf(stream.readInt32("uiRace")));
		
		int mapsCount = stream.readInt32("mapsCount");
		
		for (int i = 0; i < mapsCount; i++) {
			addMap(new MapEntry(stream, EncodingFormat.W3F_0x1));
		}
		
		int listedMapsCount = stream.readInt32("listedMapsCount");
		
		for (int i = 0; i < listedMapsCount; i++) {
			addListedMap(new ListedMapEntry(stream, EncodingFormat.W3F_0x1));
		}
	}

	private void write_0x1(@Nonnull Wc3BinOutputStream stream) {
		stream.writeInt32(EncodingFormat.W3F_0x1.getVersion());
		
		stream.writeInt32(getSavesAmount());
		stream.writeInt32(getEditorVersion());
		
		stream.writeString(getCampaignName());
		stream.writeString(getDifficulty());
		stream.writeString(getCampaignAuthor());
		stream.writeString(getCampaignDescription());
		
		stream.writeInt32(getFlags().toInt());

		LoadingScreenBackground background = getCampaignBackground();

		stream.writeInt32((background instanceof LoadingScreenBackground.PresetBackground) ? ((LoadingScreenBackground.PresetBackground) background).getIndex() : -1);
		stream.writeString((background instanceof LoadingScreenBackground.CustomBackground) ? ((LoadingScreenBackground.CustomBackground) background).getCustomPath().toString() : null);
		stream.writeString(getMinimapPath() != null ? getMinimapPath().toString() : null);
		
		AmbientSound ambientSound = getAmbientSound();

		if (ambientSound != null) {
			stream.writeInt32((ambientSound instanceof PresetAmbientSound) ? ((PresetAmbientSound) ambientSound).getIndex() : -1);
			stream.writeString((ambientSound instanceof CustomAmbientSound) ? ((CustomAmbientSound) ambientSound).getCustomPath().toString() : null);
		} else {
			stream.writeInt32(0);
			stream.writeString((String) null);
		}
		
		TerrainFog terrainFog = getTerrainFog();
		
		stream.writeInt32(terrainFog != null ? terrainFog.getType() : War3Int.valueOf(0));
		stream.writeReal(terrainFog != null ? terrainFog.getZStart() : War3Real.valueOf(0F));
		stream.writeReal(terrainFog != null ? terrainFog.getZEnd() : War3Real.valueOf(0F));
		stream.writeReal(terrainFog != null ? terrainFog.getDensity() : War3Real.valueOf(0F));
		
		Color terrainFogColor = (terrainFog != null) ? terrainFog.getColor() : null;
		
		stream.writeUByte(terrainFogColor != null ? terrainFogColor.getRed255() : 0);
		stream.writeUByte(terrainFogColor != null ? terrainFogColor.getGreen255() : 0);
		stream.writeUByte(terrainFogColor != null ? terrainFogColor.getBlue255() : 0);
		stream.writeUByte(terrainFogColor != null ? terrainFogColor.getAlpha255() : 0);

		stream.writeInt32(getUIRace().getVal());
		
		stream.writeInt32(getMaps().size());
		
		for (int i = 0; i < getMaps().size(); i++) {
			getMaps().get(i).write(stream, EncodingFormat.W3F_0x1);
		}
		
		stream.writeInt32(getListedMaps().size());
		
		for (int i = 0; i < getListedMaps().size(); i++) {
			getListedMaps().get(i).write(stream, EncodingFormat.W3F_0x1);
		}
	}
	
	private void read_auto(@Nonnull Wc3BinInputStream stream) throws Exception {
		int version = stream.readInt32("version");

		stream.rewind();

		read(stream, stream.getFormat(EncodingFormat.class, version));
	}

	private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws Exception {
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
	
	private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
		switch (format.toEnum()) {
		case AUTO:
		case W3F_0x1: {
			write_0x1(stream);
			
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
	
	public W3F() {
		
	}
	
	public W3F(@Nonnull File file) throws Exception {
		Wc3BinInputStream inStream = new Wc3BinInputStream(file);

		read(inStream);

		inStream.close();
	}

	@Nonnull
	public static W3F ofCampaignFile(@Nonnull File mapFile) throws Exception {
		MpqPort.Out port = new JMpqPort.Out();
		
		port.add(CAMPAIGN_PATH);
		
		MpqPort.Out.Result portResult = port.commit(mapFile);

		if (!portResult.getExports().containsKey(CAMPAIGN_PATH)) throw new IOException("could not extract info file");

		InputStream inStream = portResult.getInputStream(CAMPAIGN_PATH);

		W3F w3f = new W3F();

		w3f.read(new Wc3BinInputStream(inStream));

		return w3f;
	}
}
