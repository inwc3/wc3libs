package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.bin.BinStream.StreamException;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;

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
			
			/*public T get() {
				return Rect.this.get(this);
			}
			
			public void set(T val) {
				Rect.this.set(this, val);
			}*/
			
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
		
		public final static State<Color> ART_COLOR = new State<>(Color.class, "color");
		public final static State<WeatherId> ART_WEATHER = new State<>(WeatherId.class, "weather");
		
		public final static State<Bounds> DATA_BOUNDS = new State<>(Bounds.class, "bounds");
		public final static State<Int> DATA_INDEX = new State<>(Int.class, "index");
		
		public final static State<SoundLabel> SOUND_LABEL = new State<>(SoundLabel.class, "sound");
		
		public final static State<Wc3String> TEXT_NAME = new State<>(Wc3String.class, "name");
		
		public Bounds getBounds() {
			return get(DATA_BOUNDS);
		}
		
		public void setBounds(Bounds val) {
			set(DATA_BOUNDS, val);
		}
		
		public Wc3String getName() {
			return get(TEXT_NAME);
		}
		
		public void setName(Wc3String val) {
			set(TEXT_NAME, val);
		}

		public <T extends DataType> T get(State<T> state) {
			return state.tryCastVal(super.get(state));
		}
		
		public <T extends DataType> void set(State<T> state, T val) {
			super.set(state, val);
		}
		
		public <T extends DataType> void remove(State<T> state) {
			super.set(state, null);
		}
		
		public void read_0x5(Wc3BinStream stream) throws BinStream.StreamException {
			float minX = stream.readFloat();
			float minY = stream.readFloat();
			float maxX = stream.readFloat();
			float maxY = stream.readFloat();
			
			set(DATA_BOUNDS, Bounds.valueOf((int) minX, (int) minY, (int) maxX, (int) maxY));
			set(TEXT_NAME, Wc3String.valueOf(stream.readString()));
			set(DATA_INDEX, stream.readWc3Int());
			
			WeatherId weatherId = WeatherId.valueOf(stream.readId());
			
			set(ART_WEATHER, !weatherId.equals(WeatherId.valueOf("0000")) ? weatherId : null);
			set(SOUND_LABEL, SoundLabel.valueOf(stream.readString()));
			set(ART_COLOR, Color.fromRGB255(stream.readUByte(), stream.readUByte(), stream.readUByte()));
			
			int endToken = stream.readUByte();
		}

		public void write_0x5(Wc3BinStream stream) {
			//Bounds bounds = getBounds();
			Bounds bounds = get(DATA_BOUNDS);
			
			stream.writeFloat(bounds.getMinX());
			stream.writeFloat(bounds.getMinY());
			stream.writeFloat(bounds.getMaxX());
			stream.writeFloat(bounds.getMaxY());
			
			//stream.writeString(_name);
			stream.writeString(get(TEXT_NAME));

			//stream.writeInt(_index);
			stream.writeInt(get(DATA_INDEX));

			//stream.writeId(_weather);
			stream.writeId(get(ART_WEATHER) != null ? get(ART_WEATHER) : WeatherId.valueOf("0000"));
			//stream.writeString(_sound);
			System.out.println("write " + get(SOUND_LABEL));
			stream.writeString(get(SOUND_LABEL));
			
			//Color color = _color;
			Color color = get(ART_COLOR);
			
			stream.writeUByte(color.getBlue());
			stream.writeUByte(color.getGreen());
			stream.writeUByte(color.getRed());
			
			stream.writeUByte(0); //endToken
		}

		public void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
			switch (format.toEnum()) {
			case W3R_0x5:
				read_0x5(stream);
				
				break;
			}
		}
		
		public void write(Wc3BinStream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case W3R_0x5:
				write_0x5(stream);
				
				break;
			}
		}
		
		public Rect(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
			this();
			
			read(stream, format);
		}
		
		public Rect() {
			set(DATA_BOUNDS, new Bounds(0, 0, 0, 0));
			System.out.println("set " + get(DATA_BOUNDS));
			set(TEXT_NAME, Wc3String.valueOf("unnamed"));
			set(DATA_INDEX, Int.valueOf(0));
			set(ART_WEATHER, null);
			set(SOUND_LABEL, SoundLabel.valueOf("sound"));
			set(ART_COLOR, Color.fromBGR255(255, 255, 255));
		}
		
		public Rect(Bounds bounds) {
			this();
			
			set(DATA_BOUNDS, bounds);
		}
		
		public Rect(int minX, int minY, int maxX, int maxY) {
			this(new Bounds(minX, minY, maxX, maxY));
		}
	}
	
	private List<Rect> _rects = new ArrayList<>();
	
	public List<Rect> getRects() {
		return new ArrayList<>(_rects);
	}
	
	private void addRect(Rect val) {
		_rects.add(val);
	}
	
	public Rect addRect() {
		Rect rect = new Rect();
		
		addRect(rect);
		
		return rect;
	}
	
	public Rect addRect(Bounds bounds) {
		Rect rect = addRect();

		rect.setBounds(bounds);

		return rect;
	}
	
	public Rect addRect(int minX, int minY, int maxX, int maxY) {
		return addRect(new Bounds(minX, minY, maxX, maxY));
	}
	
	private static class EncodingFormat extends Format<EncodingFormat.Enum> {
		enum Enum {
			AUTO,
			W3R_0x5
		}

		static Map<Integer, EncodingFormat> _map = new LinkedHashMap<>();
		
		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat W3R_0x5 = new EncodingFormat(Enum.W3R_0x5, 0x5);
		
		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}
		
		private EncodingFormat(Enum enumVal, int version) {
			super(enumVal, version);
			
			_map.put(version, this);
		}
	}

	public void read_0x5(Wc3BinStream stream) throws BinStream.StreamException {
		int format = stream.readInt();

		Wc3BinStream.checkFormatVer("rectMaskFunc", EncodingFormat.W3R_0x5.getVersion(), format);

		int rectsCount = stream.readInt();

		for (int i = 0; i < rectsCount; i++) {					
			addRect(new Rect(stream, EncodingFormat.W3R_0x5));
		}
	}

	public void write_0x5(Wc3BinStream stream) {
		stream.writeInt(EncodingFormat.W3R_0x5.getVersion());
		
		stream.writeInt(_rects.size());
		
		for (Rect rect : _rects) {
			rect.write(stream, EncodingFormat.W3R_0x5);
		}
	}
	
	private void read_auto(Wc3BinStream stream) throws BinStream.StreamException {
		int version = stream.readInt();
		
		stream.rewind();

		read(stream, EncodingFormat.valueOf(version));
	}

	private void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {		
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
	
	private void write(Wc3BinStream stream, EncodingFormat format) {
		switch (format.toEnum()) {
		case AUTO:
		case W3R_0x5: {
			write_0x5(stream);
			
			break;
		}
		}
	}
	
	private void read(Wc3BinStream stream) throws BinStream.StreamException {
		read(stream, EncodingFormat.AUTO);
	}
	
	private void write(Wc3BinStream stream) {
		write(stream, EncodingFormat.AUTO);
	}
	
	private void read(File file, EncodingFormat format) throws IOException {
		read(new Wc3BinStream(file), format);
	}
	
	public void write(File file, EncodingFormat format) throws IOException {
		write(new Wc3BinStream(file), format);
	}
	
	private void read(File file) throws IOException {
		read(file, EncodingFormat.AUTO);
	}

	public void write(File file) throws IOException {
		write(new Wc3BinStream(file));
	}
	
	public W3R() {
	}
	
	public W3R(Wc3BinStream stream) throws StreamException {
		read(stream);
	}
	
	public W3R(File file) throws IOException {
		read(file);
	}
}