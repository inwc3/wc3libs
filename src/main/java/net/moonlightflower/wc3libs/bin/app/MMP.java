package net.moonlightflower.wc3libs.bin.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Format;
import net.moonlightflower.wc3libs.bin.Wc3BinStream;
import net.moonlightflower.wc3libs.dataTypes.app.Color;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;
import net.moonlightflower.wc3libs.misc.UnsupportedFormatException;
import net.moonlightflower.wc3libs.misc.image.BLP;
import net.moonlightflower.wc3libs.misc.image.Wc3Img;
import net.moonlightflower.wc3libs.misc.image.Wc3RasterImg;
import net.moonlightflower.wc3libs.port.LadikMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;

public class MMP {
	public final static File GAME_PATH = new File("war3map.mmp");
	
	public static class Icon {
		enum IconType {			
			//GOLD_MINE(0x0, new File("UI\\MiniMap\\MiniMapIcon\\MinimapIconGold.blp")),
			GOLD_MINE(0x0, new File("UI\\MiniMap\\minimap-gold.blp")),
			//HOUSE(0x1, new File("UI\\MiniMap\\MiniMapIcon\\MinimapIconNeutralBuilding.blp")),
			HOUSE(0x1, new File("UI\\MiniMap\\minimap-neutralbuilding.blp")),
			PLAYER_START(2, new File("UI\\MiniMap\\MiniMapIcon\\MinimapIconStartLoc.blp"));
			
			private final static Map<Integer, IconType> _map = new HashMap<>();
			
			private int _val;
			
			public int getVal() {
				return _val;
			}
			
			private File _imgPath;
			
			public File getImgPath() {
				return _imgPath;
			}
			
			public static IconType valueOf(int val) {
				return _map.get(val);
			}
			
			IconType(int val, File imgPath) {
				_val = val;
				_imgPath = imgPath;
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

		public void read_0x0(Wc3BinStream stream) throws BinStream.StreamException {
			setType(IconType.valueOf(stream.readInt()));
			
			setCoords(new Coords2DI(stream.readInt(), stream.readInt()));
			
			int blue = stream.readUByte();
			int green = stream.readUByte();
			int red = stream.readUByte();
			int alpha = stream.readUByte();
			
			if (blue == 0xFF && green == 0xFF && red == 0xFF && alpha == 0xFF) {
				setColor(null);
			} else {
				setColor(Color.fromBGRA(blue, green, red, alpha));
			}
		}
		
		public void write_0x0(Wc3BinStream stream) {
			stream.writeInt(getType().getVal());
			
			Coords2DI coords = getCoords();
			
			stream.writeInt(coords.getX());
			stream.writeInt(coords.getY());
			
			Color color = getColor();
			
			if (color == null) {
				stream.writeUByte(0xFF);
				stream.writeUByte(0xFF);
				stream.writeUByte(0xFF);
				stream.writeUByte(0xFF);
			} else {		
				stream.writeUByte(color.getBlue());
				stream.writeUByte(color.getGreen());
				stream.writeUByte(color.getRed());
				stream.writeUByte(color.getAlpha());
			}
		}
		
		public void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
			switch (format.toEnum()) {
			case MMP_0x0: {
				read_0x0(stream);
				
				break;
			}
			}
		}
		
		public void write(Wc3BinStream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case MMP_0x0: {
				write_0x0(stream);
				
				break;
			}
			}
		}
		
		public Icon(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
			read(stream, format);
		}
		
		public Icon() {
		}
	}
	
	private List<Icon> _icons = new ArrayList<>();
	
	public List<Icon> getIcons() {
		return _icons;
	}
	
	public Icon addIcon() {
		Icon icon = new Icon();
		
		_icons.add(icon);
		
		return icon;
	}
	
	private class IconImg {
		public int _width;
		public int _height;
		public int[] _pxBuf;
		
		public IconImg(InputStream inStream) throws UnsupportedFormatException {			
			javafx.scene.image.Image fxImg = new BLP(inStream).getFXImg();
			
			javafx.scene.image.PixelReader pxReader = fxImg.getPixelReader();
			
			int width = (int) fxImg.getWidth();
			int height = (int) fxImg.getHeight();
			
			int[] pxBuf = new int[width * height];
			
			pxReader.getPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), pxBuf, 0, width);
			
			_width = width;
			_height = height;
			_pxBuf = pxBuf;
		}
	}
	
	public javafx.scene.image.Image toFXImg() throws Exception {
		WritableImage img = new WritableImage(256, 256);
		
		PixelWriter pxWriter = img.getPixelWriter();
		
		Map<File, IconImg> iconImgMap = new HashMap<>();
		
		for (Icon icon : getIcons()) {
			File imgPath = icon.getType().getImgPath();
			
			if (imgPath == null) continue;
			
			IconImg iconImg = null;
			
			if (!iconImgMap.containsKey(imgPath)) {			
				MpqPort.Out.Result portResult = new LadikMpqPort().getGameFiles(imgPath);
				
				InputStream inStream = portResult.getInputStream(imgPath);
				
				iconImg = new IconImg(inStream);
				
				inStream.close();
				
				iconImgMap.put(imgPath, iconImg);
			} else {
				iconImg = iconImgMap.get(imgPath);
			}
			
			if (iconImg == null) continue;

			int[] pxBuf = iconImg._pxBuf;
			
			if (icon.getColor() != null) {
				pxBuf = Arrays.copyOf(pxBuf, pxBuf.length);
				
				double alphaFactor = ((double) icon.getColor().getAlpha()) / 0xFF;
				double redFactor = ((double) icon.getColor().getRed()) / 0xFF;
				double greenFactor = ((double) icon.getColor().getGreen()) / 0xFF;
				double blueFactor = ((double) icon.getColor().getBlue()) / 0xFF;
				
				//System.out.println(alphaFactor+";"+redFactor+";"+greenFactor+";"+blueFactor);
				//System.out.println("A " + pxBuf[0] +";"+ pxBuf[1] +";"+ pxBuf[2] +";"+ pxBuf[3]);
				
				for (int i = 0; i < pxBuf.length; i++) {
					int alpha = (int) (((pxBuf[i] >> 24) & 0xFF) * alphaFactor);
					int red = (int) (((pxBuf[i] >> 24) & 0xFF) * redFactor);
					int green = (int) (((pxBuf[i] >> 24) & 0xFF) * greenFactor);
					int blue = (int) (((pxBuf[i] >> 24) & 0xFF) * blueFactor);
					
					pxBuf[i] = (alpha << 24) + (red << 16) + (green << 8) + (blue);
				}
				
				//System.out.println("B " + pxBuf[0] +";"+ pxBuf[1] +";"+ pxBuf[2] +";"+ pxBuf[3]);
			}
			//System.out.println(icon.getCoords().getX()+";"+icon.getCoords().getY()+";"+iconImg._width+";"+iconImg._height);
			
			int minX = icon.getCoords().getX() - iconImg._width / 2;
			int minY = icon.getCoords().getY() - iconImg._height / 2;
			
			int maxX = (int) Math.min(img.getWidth() - 1, minX + iconImg._width);
			int maxY = (int) Math.min(img.getHeight() - 1, minY + iconImg._height);
			
			int width = maxX - minX;
			int height = maxY - minY;
			
			//int xOff = iconImg._width / 2;
			//int yOff = iconImg._height / 2;
			
			int xOff = Math.max(-minX, 0);
			int yOff = Math.max(-minY, 0);
			
			pxWriter.setPixels(minX, minY, width - xOff, height - yOff, PixelFormat.getIntArgbInstance(), pxBuf, iconImg._width * yOff + xOff, iconImg._width);
		};
		
		return img;
	}

	public Wc3RasterImg toImg() throws Exception {
		return new Wc3RasterImg(toFXImg());
	}

	private static class EncodingFormat extends Format<EncodingFormat.Enum> {
		enum Enum {
			AUTO,
			MMP_0x0,
		}

		private static Map<Integer, EncodingFormat> _map = new HashMap<>();
		
		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat MMP_0x0 = new EncodingFormat(Enum.MMP_0x0, 0x0);
		
		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}
		
		private EncodingFormat(Enum enumVal, int version) {
			super(enumVal, version);
			
			_map.put(version, this);
		}
	}

	private void read_0x0(Wc3BinStream stream) throws BinStream.StreamException {
		int unknown = stream.readInt(); //most probably format
		
		int iconsCount = stream.readInt();
		
		for (int i = 0; i < iconsCount; i++) {
			Icon icon = addIcon();
			
			icon.read(stream, EncodingFormat.MMP_0x0);
		}
	}
	
	private void write_0x0(Wc3BinStream stream) {
		stream.writeInt(0x0);
		
		stream.writeInt(_icons.size());

		for (Icon icon : _icons) {
			icon.write(stream, EncodingFormat.MMP_0x0);
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
		case MMP_0x0: {
			read_0x0(stream);
			
			break;
		}
		}
	}
	
	private void write(Wc3BinStream stream, EncodingFormat format) {
		switch (format.toEnum()) {
		case AUTO:
		case MMP_0x0: {
			write_0x0(stream);
			
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
	
	private void write(File file, EncodingFormat format) throws IOException {
		write(new Wc3BinStream(file), format);
	}
	
	private void read(InputStream inStream) throws Exception {
		read(new Wc3BinStream(inStream), EncodingFormat.AUTO);
	}
	
	private void read(File file) throws IOException {
		read(file, EncodingFormat.AUTO);
	}

	private void write(File file) throws IOException {
		write(new Wc3BinStream(file));
	}
	
	public MMP() {
	}
	
	public MMP(InputStream inStream) throws Exception {
		read(inStream);
	}
	
	public MMP(File inFile) throws Exception {
		InputStream inStream = new FileInputStream(inFile);
		
		read(inStream);
		
		inStream.close();
	}

	public static MMP ofMapFile(File mapFile) throws Exception {
		MpqPort.Out port = new LadikMpqPort.Out();
		
		port.add(GAME_PATH);
		
		MpqPort.Out.Result portResult = port.commit(mapFile);

		if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract info file");

		InputStream inStream = portResult.getInputStream(GAME_PATH);
		
		return new MMP(inStream);
	}
}
