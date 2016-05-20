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
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;

public class MMP {
	public static class Icon {
		enum IconType {
			GOLD_MINE(0x0),
			HOUSE(0x1),
			PLAYER_START(2);
			
			int _val;
			
			public int getVal() {
				return _val;
			}
			
			private final static Map<Integer, IconType> _map = new HashMap<>();
			
			public static IconType valueOf(int val) {
				return _map.get(val);
			}
			
			IconType(int val) {
				_val = val;
			}
			
			static {
				for (IconType iconType : values()) {
					_map.put(iconType.getVal(), iconType);
				}
			}
		}
		
		private IconType _type = IconType.GOLD_MINE;
		
		public IconType getType() {
			return _type;
		}
		
		public void setType(IconType val) {
			_type = val;
		}
		
		private Coords2DI _coords = new Coords2DI(0, 0);
		
		public Coords2DI getCoords() {
			return _coords;
		}
		
		public void setCoords(Coords2DI val) {
			_coords = val;
		}
		
		private Color _color = Color.fromBGRA(0, 0, 0, 0);
		
		public Color getColor() {
			return _color;
		}
		
		public void setColor(Color val) {
			_color = val;
		}

		public void read_0x0(Stream stream) throws StreamException {
			setType(IconType.valueOf(stream.readInt()));
			
			setCoords(new Coords2DI(stream.readInt(), stream.readInt()));
			
			setColor(Color.fromBGRA(stream.readByte(), stream.readByte(), stream.readByte(), stream.readByte()));
		}
		
		public void write_0x0(Stream stream) {
			stream.writeInt(getType().getVal());
			
			Coords2DI coords = getCoords();
			
			stream.writeInt(coords.getX());
			stream.writeInt(coords.getY());
			
			Color color = getColor();
			
			stream.writeByte(color.getBlue());
			stream.writeByte(color.getGreen());
			stream.writeByte(color.getRed());
			stream.writeByte(color.getAlpha());
		}
		
		public void read(Stream stream, EncodingFormat format) throws StreamException {
			switch (format.toEnum()) {
			case MMP_0x0: {
				read_0x0(stream);
				
				break;
			}
			}
		}
		
		public void write(Stream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case MMP_0x0: {
				write_0x0(stream);
				
				break;
			}
			}
		}
		
		public Icon(Stream stream, EncodingFormat format) throws StreamException {
			read(stream, format);
		}
		
		public Icon() {
		}
	}
	
	private List<Icon> _icons = new ArrayList<>();
	
	public Icon addIcon() {
		Icon icon = new Icon();
		
		_icons.add(icon);
		
		return icon;
	}

	private static class EncodingFormat extends Format<EncodingFormat.Enum> {
		enum Enum {
			AUTO,
			MMP_0x0,
		}

		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat MMP_0x0 = new EncodingFormat(Enum.MMP_0x0, 0x0);

		private static Map<Integer, EncodingFormat> _map = new HashMap<>();
		
		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}
		
		private EncodingFormat(Enum enumVal, int version) {
			super(enumVal, version);
			
			_map.put(version, this);
		}
	}

	private void read_0x0(Stream stream) throws StreamException {
		int unknown = stream.readInt(); //most probably format
		
		int iconsCount = stream.readInt();
		
		for (int i = 0; i < iconsCount; i++) {
			Icon icon = addIcon();
			
			icon.read(stream, EncodingFormat.MMP_0x0);
		}
	}
	
	private void write_0x0(Stream stream) {
		stream.writeInt(0x0);
		
		stream.writeInt(_icons.size());

		for (Icon icon : _icons) {
			icon.write(stream, EncodingFormat.MMP_0x0);
		}
	}
	
	private void read_auto(Stream stream) throws StreamException {
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
		case MMP_0x0: {
			read_0x0(stream);
			
			break;
		}
		}
	}
	
	private void write(Stream stream, EncodingFormat format) {
		switch (format.toEnum()) {
		case AUTO:
		case MMP_0x0: {
			write_0x0(stream);
			
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
	
	public MMP() {
	}
}
