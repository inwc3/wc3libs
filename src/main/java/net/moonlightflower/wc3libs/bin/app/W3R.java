package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * rects file for wrapping war3map.w3r
 */
public class W3R {
	public final static File GAME_PATH = new File("war3map.w3r");
	
	public static class Rect extends Bin {
		private static class State<T extends DataType> extends BinState<T> {
			private static final List<State> _values = new ArrayList<>();
			
			public static List<State> values() {
				return _values;
			}
			
			public State(DataTypeInfo typeInfo, String idString, T defVal) {
				super(typeInfo, idString, defVal);
				
				_values.add(this);
			}
			
			public State(DataTypeInfo typeInfo, String idString) {
				this(typeInfo, idString, null);
			}

			public State(Class<T> type, String idString) {
				this(new DataTypeInfo(type), idString);
			}
			
			public State(Class<T> type, String idString, T defVal) {
				this(new DataTypeInfo(type), idString, defVal);
			}
		}
		
		public final static State<Color> ART_COLOR = new State<>(Color.class, "color", Color.fromBGR255(255, 255, 255));
		public final static State<WeatherId> ART_WEATHER = new State<>(WeatherId.class, "weather", null);
		
		public final static State<Bounds> DATA_BOUNDS = new State<>(Bounds.class, "bounds", new Bounds(0, 0, 0, 0));
		public final static State<Int> DATA_INDEX = new State<>(Int.class, "index", Int.valueOf(0));
		
		public final static State<SoundLabel> SOUND_LABEL = new State<>(SoundLabel.class, "sound", SoundLabel.valueOf("sound"));
		
		public final static State<Wc3String> TEXT_NAME = new State<>(Wc3String.class, "name", Wc3String.valueOf("unnamed"));

		public <T extends DataType> T get(State<T> state) {
			return state.tryCastVal(super.get(state));
		}

		public <T extends DataType> void set(State<T> state, T val) {
			super.set(state, val);
		}

		public <T extends DataType> void reset(State<T> state) {
			super.set(state, state.getDefVal());
		}

		public void init() {
			for (State<?> state : State.values()) {
				set(state, state.getDefVal());
			}
		}

		@Nonnull()
		public Bounds getBounds() {
			return get(DATA_BOUNDS) != null ? get(DATA_BOUNDS) : DATA_BOUNDS.getDefVal();
		}
		
		public void setBounds(@Nonnull Bounds val) {
			set(DATA_BOUNDS, val);
		}

		public Color getColor() {
			return get(ART_COLOR);
		}

		public void setColor(Color val) {
			set(ART_COLOR, val);
		}

		public Int getIndex() {
			return get(DATA_INDEX);
		}

		public void setIndex(Int val) {
			set(DATA_INDEX, val);
		}

		public Wc3String getName() {
			return get(TEXT_NAME);
		}
		
		public void setName(Wc3String val) {
			set(TEXT_NAME, val);
		}

		public SoundLabel getSound() {
			return get(SOUND_LABEL);
		}

		public void setSound(SoundLabel val) {
			set(SOUND_LABEL, val);
		}

		public WeatherId getWeather() {
			return get(ART_WEATHER);
		}

		public void setWeather(WeatherId val) {
			set(ART_WEATHER, val);
		}

		public WeatherId NULL_WEATHER_ID = WeatherId.valueOf("0000");

		public void read_0x5(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			float minX = stream.readFloat8();
			float minY = stream.readFloat8();
			float maxX = stream.readFloat8();
			float maxY = stream.readFloat8();
			
			set(DATA_BOUNDS, Bounds.valueOf((int) minX, (int) minY, (int) maxX, (int) maxY));
			set(TEXT_NAME, Wc3String.valueOf(stream.readString()));
			set(DATA_INDEX, stream.readWc3Int());
			
			WeatherId weatherId = WeatherId.valueOf(stream.readId());
			
			set(ART_WEATHER, !weatherId.equals(NULL_WEATHER_ID) ? weatherId : null);
			set(SOUND_LABEL, SoundLabel.valueOf(stream.readString()));
			set(ART_COLOR, Color.fromRGB255(stream.readUByte(), stream.readUByte(), stream.readUByte()));
			
			int endToken = stream.readUByte();
		}

		public void write_0x5(@Nonnull Wc3BinOutputStream stream) {
			Bounds bounds = get(DATA_BOUNDS);
			
			stream.writeReal(Real.valueOf(bounds.getMinX()));
			stream.writeReal(Real.valueOf(bounds.getMinY()));
			stream.writeReal(Real.valueOf(bounds.getMaxX()));
			stream.writeReal(Real.valueOf(bounds.getMaxY()));

			stream.writeString(get(TEXT_NAME));

			stream.writeInt32(get(DATA_INDEX));

			stream.writeId(get(ART_WEATHER) != null ? get(ART_WEATHER) : NULL_WEATHER_ID);

			stream.writeString(get(SOUND_LABEL));

			Color color = get(ART_COLOR);
			
			stream.writeUByte(color.getBlue());
			stream.writeUByte(color.getGreen());
			stream.writeUByte(color.getRed());
			
			stream.writeUByte(0); //endToken
		}

		public void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			switch (format.toEnum()) {
			case W3R_0x5:
				read_0x5(stream);
				
				break;
			}
		}
		
		public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case W3R_0x5:
				write_0x5(stream);
				
				break;
			}
		}
		
		public Rect(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			this();
			
			read(stream, format);
		}
		
		public Rect() {
			init();
		}
		
		public Rect(@Nonnull Bounds bounds) {
			this();
			
			set(DATA_BOUNDS, bounds);
		}
	}
	
	private List<Rect> _rects = new ArrayList<>();

	@Nonnull
	public List<Rect> getRects() {
		return new ArrayList<>(_rects);
	}
	
	private void addRect(@Nonnull Rect val) {
		_rects.add(val);
	}
	
	public Rect addRect() {
		Rect rect = new Rect();
		
		addRect(rect);
		
		return rect;
	}
	
	public Rect addRect(@Nonnull Bounds bounds) {
		Rect rect = addRect();

		rect.setBounds(bounds);

		return rect;
	}
	
	public static class EncodingFormat extends Format<EncodingFormat.Enum> {
		public enum Enum {
			AUTO,
			W3R_0x5
		}

		private static Map<Integer, EncodingFormat> _map = new LinkedHashMap<>();
		
		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat W3R_0x5 = new EncodingFormat(Enum.W3R_0x5, 0x5);
		
		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}
		
		private EncodingFormat(@Nonnull Enum enumVal, int version) {
			super(enumVal, version);
			
			_map.put(version, this);
		}
	}

	public void read_0x5(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int format = stream.readInt32();

		Wc3BinInputStream.checkFormatVer("rectMaskFunc", EncodingFormat.W3R_0x5.getVersion(), format);

		int rectsCount = stream.readInt32();

		for (int i = 0; i < rectsCount; i++) {					
			addRect(new Rect(stream, EncodingFormat.W3R_0x5));
		}
	}

	public void write_0x5(@Nonnull Wc3BinOutputStream stream) {
		stream.writeInt32(EncodingFormat.W3R_0x5.getVersion());
		
		stream.writeInt32(_rects.size());
		
		for (Rect rect : _rects) {
			rect.write(stream, EncodingFormat.W3R_0x5);
		}
	}
	
	private void read_auto(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int version = stream.readInt32();
		
		stream.rewind();

		read(stream, EncodingFormat.valueOf(version));
	}

	private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
		switch (format.toEnum()) {
		case AUTO: {
			read_auto(stream);
			
			break;
		}
		case W3R_0x5: {
			read_0x5(stream);
			
			break;
		}
		}
	}
	
	private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
		switch (format.toEnum()) {
		case AUTO:
		case W3R_0x5: {
			write_0x5(stream);
			
			break;
		}
		}
	}
	
	private void read(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		read(stream, EncodingFormat.AUTO);
	}
	
	private void write(@Nonnull Wc3BinOutputStream stream) {
		write(stream, EncodingFormat.AUTO);
	}
	
	private void read(@Nonnull File file, @Nonnull EncodingFormat format) throws IOException {
		read(new Wc3BinInputStream(file), format);
	}
	
	public void write(@Nonnull File file, @Nonnull EncodingFormat format) throws IOException {
		Wc3BinOutputStream outStream = new Wc3BinOutputStream(file);

		write(outStream, format);

		outStream.close();
	}
	
	private void read(@Nonnull File file) throws IOException {
		read(file, EncodingFormat.AUTO);
	}

	public void write(@Nonnull File file) throws IOException {
		write(new Wc3BinOutputStream(file));
	}
	
	public W3R() {
	}
	
	public W3R(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
		read(stream);
	}
	
	public W3R(@Nonnull File file) throws IOException {
		read(file);
	}
}