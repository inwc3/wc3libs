package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * rects file for wrapping war3map.w3r
 */
public class W3R {
	public final static File GAME_PATH = new File("war3map.w3r");
	
	public static class Rect extends Bin {
		public static class State<T extends DataType> extends BinState<T> {
			private final static List<State> _values = new ArrayList<>();
			
			public static List<State> values() {
				return new ArrayList<>(_values);
			}
			
			public State(@Nonnull DataTypeInfo typeInfo, @Nonnull String fieldIdS, T defVal) {
				super(fieldIdS, typeInfo, defVal);
				
				_values.add(this);
			}
			
			public State(@Nonnull DataTypeInfo typeInfo, @Nonnull String idString) {
				this(typeInfo, idString, null);
			}

			public State(@Nonnull Class<T> type, @Nonnull String idString) {
				this(new DataTypeInfo(type), idString);
			}
			
			public State(@Nonnull Class<T> type, @Nonnull String idString, T defVal) {
				this(new DataTypeInfo(type), idString, defVal);
			}
		}
		
		public final static State<Color> ART_COLOR = new State<>(Color.class, "color", Color.fromBGR255(255, 255, 255));
		public final static State<WeatherId> ART_WEATHER = new State<>(WeatherId.class, "weather", null);
		
		public final static State<Bounds> DATA_BOUNDS = new State<>(Bounds.class, "bounds", new Bounds(0, 0, 0, 0));
		public final static State<War3Int> DATA_INDEX = new State<>(War3Int.class, "index", War3Int.valueOf(0));
		
		public final static State<SoundLabel> SOUND_LABEL = new State<>(SoundLabel.class, "sound", SoundLabel.valueOf("sound"));
		
		public final static State<War3String> TEXT_NAME = new State<>(War3String.class, "name", War3String.valueOf("unnamed"));

		public <T extends DataType> T get(State<T> state) {
			try {
				return state.tryCastVal(super.get(state));
			} catch (DataTypeInfo.CastException ignored) {
			}

			return null;
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

		@Nonnull
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

		public War3Int getIndex() {
			return get(DATA_INDEX);
		}

		public void setIndex(War3Int val) {
			set(DATA_INDEX, val);
		}

		public War3String getName() {
			return get(TEXT_NAME);
		}
		
		public void setName(War3String val) {
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

		public WeatherId NULL_WEATHER_ID = WeatherId.valueOf("\0\0\0\0");

		public void read_0x5(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			float minX = stream.readFloat32();
			float maxX = stream.readFloat32();
			float minY = stream.readFloat32();
			float maxY = stream.readFloat32();
			
			set(DATA_BOUNDS, Bounds.valueOf((int) minX, (int) maxX, (int) minY, (int) maxY));
			set(TEXT_NAME, War3String.valueOf(stream.readString()));
			set(DATA_INDEX, stream.readWc3Int());
			
			WeatherId weatherId = WeatherId.valueOf(stream.readId());

			set(ART_WEATHER, !weatherId.equals(NULL_WEATHER_ID) ? weatherId : null);
			set(SOUND_LABEL, SoundLabel.valueOf(stream.readString()));
			set(ART_COLOR, Color.fromBGR255(stream.readUByte(), stream.readUByte(), stream.readUByte()));
			
			int endToken = stream.readUByte();
		}

		public void write_0x5(@Nonnull Wc3BinOutputStream stream) {
			Bounds bounds = get(DATA_BOUNDS);
			
			stream.writeReal(War3Real.valueOf(bounds.getMinX()));
			stream.writeReal(War3Real.valueOf(bounds.getMaxX()));
			stream.writeReal(War3Real.valueOf(bounds.getMinY()));
			stream.writeReal(War3Real.valueOf(bounds.getMaxY()));

			stream.writeString(get(TEXT_NAME));
			stream.writeInt32(get(DATA_INDEX));
			stream.writeId(get(ART_WEATHER) != null ? get(ART_WEATHER) : NULL_WEATHER_ID);
			stream.writeString(get(SOUND_LABEL));

			Color color = get(ART_COLOR);
			
			stream.writeUByte(color.getBlue255());
			stream.writeUByte(color.getGreen255());
			stream.writeUByte(color.getRed255());
			
			stream.writeUByte(0xFF); //endToken
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

	@Nonnull
	public Rect addRect() {
		Rect rect = new Rect();
		
		addRect(rect);
		
		return rect;
	}

	@Nonnull
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
		
		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat W3R_0x5 = new EncodingFormat(Enum.W3R_0x5, 0x5);

		@Nullable
		public static EncodingFormat valueOf(@Nonnull Integer version) {
			return get(EncodingFormat.class, version);
		}
		
		private EncodingFormat(@Nonnull Enum enumVal, int version) {
			super(enumVal, version);
		}
	}

	public void read_0x5(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int version = stream.readInt32("version");

		stream.checkFormatVersion(EncodingFormat.W3R_0x5.getVersion(), version);

		int rectsCount = stream.readInt32("rectsCount");

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

		read(stream, stream.getFormat(EncodingFormat.class, version));
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
	
	public void write(@Nonnull Wc3BinOutputStream stream) {
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