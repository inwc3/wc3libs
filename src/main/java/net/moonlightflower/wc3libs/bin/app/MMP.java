package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.Format;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.app.Color;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;
import net.moonlightflower.wc3libs.misc.UnsupportedFormatException;
import net.moonlightflower.wc3libs.misc.image.BLP;
import net.moonlightflower.wc3libs.misc.image.FxImg;
import net.moonlightflower.wc3libs.misc.image.Wc3RasterImg;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MMP {
	public final static File GAME_PATH = new File("war3map.mmp");
	
	public static class Icon {
		public enum IconType {
			//GOLD_MINE(0x0, new File("UI\\MiniMap\\MiniMapIcon\\MinimapIconGold.blp")),
			GOLD_MINE(0x0, new File("UI\\MiniMap\\minimap-gold.blp")),
			//HOUSE(0x1, new File("UI\\MiniMap\\MiniMapIcon\\MinimapIconNeutralBuilding.blp")),
			HOUSE(0x1, new File("UI\\MiniMap\\minimap-neutralbuilding.blp")),
			PLAYER_START(2, new File("UI\\MiniMap\\MiniMapIcon\\MinimapIconStartLoc.blp"));
			
			private final static Map<Integer, IconType> _map = new LinkedHashMap<>();
			
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
			
			IconType(int val, @Nonnull File imgPath) {
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

		@Nonnull
		public IconType getType() {
			return _type;
		}
		
		public void setType(@Nonnull IconType val) {
			_type = val;
		}
		
		private Coords2DI _coords = new Coords2DI(0, 0);
		
		public Coords2DI getCoords() {
			return _coords;
		}
		
		public void setCoords(Coords2DI val) {
			_coords = val;
		}
		
		private Color _color = Color.fromBGRA255(0, 0, 0, 0);

		@Nullable
		public Color getColor() {
			return _color;
		}
		
		public void setColor(@Nullable Color val) {
			_color = val;
		}

		private void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			setType(IconType.valueOf(stream.readInt32()));
			
			setCoords(new Coords2DI(stream.readInt32(), stream.readInt32()));
			
			int blue = stream.readUByte();
			int green = stream.readUByte();
			int red = stream.readUByte();
			int alpha = stream.readUByte();
			
			if (blue == 0xFF && green == 0xFF && red == 0xFF && alpha == 0xFF) {
				setColor(null);
			} else {
				setColor(Color.fromBGRA255(blue, green, red, alpha));
			}
		}
		
		private void write_0x0(@Nonnull Wc3BinOutputStream stream) {
			stream.writeInt32(getType().getVal());
			
			Coords2DI coords = getCoords();
			
			stream.writeInt32(coords.getX());
			stream.writeInt32(coords.getY());
			
			Color color = getColor();
			
			if (color == null) {
				stream.writeUByte(0xFF);
				stream.writeUByte(0xFF);
				stream.writeUByte(0xFF);
				stream.writeUByte(0xFF);
			} else {		
				stream.writeUByte(color.getBlue255());
				stream.writeUByte(color.getGreen255());
				stream.writeUByte(color.getRed255());
				stream.writeUByte(color.getAlpha255());
			}
		}
		
		public void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			switch (format.toEnum()) {
			case MMP_0x0: {
				read_0x0(stream);
				
				break;
			}
			}
		}
		
		public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case MMP_0x0: {
				write_0x0(stream);
				
				break;
			}
			}
		}
		
		public Icon(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			read(stream, format);
		}
		
		public Icon() {
		}
	}
	
	private List<Icon> _icons = new ArrayList<>();

	@Nonnull
	public List<Icon> getIcons() {
		return _icons;
	}

	@Nonnull
	public Icon addIcon() {
		Icon icon = new Icon();
		
		_icons.add(icon);
		
		return icon;
	}
	
	public class IconImg {
		public int _width;
		public int _height;
		public int[] _pxBuf;
		
		public IconImg(@Nonnull InputStream inStream) throws UnsupportedFormatException {
			FxImg fxImg = new BLP(inStream).getFXImg();
			
			int width = (int) fxImg.getWidth();
			int height = (int) fxImg.getHeight();
			
			int[] pxBuf = new int[width * height];

			fxImg.toBufImg().getRGB(0, 0, width, height, pxBuf, 0, width);
			
			_width = width;
			_height = height;
			_pxBuf = pxBuf;
		}
	}

	@Nonnull
	public FxImg toFXImg() throws Exception {
		BufferedImage img = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
		
		Map<File, IconImg> iconImgMap = new LinkedHashMap<>();
		
		for (Icon icon : getIcons()) {
			File imgPath = icon.getType().getImgPath();
			
			if (imgPath == null) continue;
			
			IconImg iconImg;
			
			if (!iconImgMap.containsKey(imgPath)) {			
				MpqPort.Out.Result portResult = new JMpqPort().getGameFiles(imgPath);
				
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
				
				double alphaFactor = ((double) icon.getColor().getAlpha255()) / 0xFF;
				double redFactor = ((double) icon.getColor().getRed255()) / 0xFF;
				double greenFactor = ((double) icon.getColor().getGreen255()) / 0xFF;
				double blueFactor = ((double) icon.getColor().getBlue255()) / 0xFF;
				
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

			img.setRGB(minX, minY, width - xOff, height - yOff, pxBuf, iconImg._width * yOff + xOff, iconImg._width);
		}
		
		return new FxImg(img);
	}

	@Nonnull
	public Wc3RasterImg toImg() throws Exception {
		return new Wc3RasterImg(toFXImg());
	}

	public static class EncodingFormat extends Format<EncodingFormat.Enum> {
		public enum Enum {
			AUTO,
			MMP_0x0,
		}
		
		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat MMP_0x0 = new EncodingFormat(Enum.MMP_0x0, 0x0);

		@Nullable
		public static EncodingFormat valueOf(@Nonnull Integer version) {
			return get(EncodingFormat.class, version);
		}

		private EncodingFormat(@Nonnull Enum enumVal, int version) {
			super(enumVal, version);
		}
	}

	private void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int unknown = stream.readInt32(); //most probably format
		
		int iconsCount = stream.readInt32();
		
		for (int i = 0; i < iconsCount; i++) {
			Icon icon = addIcon();
			
			icon.read(stream, EncodingFormat.MMP_0x0);
		}
	}
	
	private void write_0x0(@Nonnull Wc3BinOutputStream stream) {
		stream.writeInt32(0x0); //most probably format
		
		stream.writeInt32(_icons.size());

		for (Icon icon : _icons) {
			icon.write(stream, EncodingFormat.MMP_0x0);
		}
	}
	
	private void read_auto(@Nonnull Wc3BinInputStream stream) throws Exception {
		int version = stream.readInt32();
		
		stream.rewind();

		EncodingFormat format = EncodingFormat.valueOf(version);

		if (format == null) throw new Exception(String.format("unknown format %x", version));

		read(stream, format);
	}
	
	private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws Exception {
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
	
	private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
		switch (format.toEnum()) {
		case AUTO:
		case MMP_0x0: {
			write_0x0(stream);
			
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

	public MMP(@Nonnull Wc3BinInputStream stream) throws Exception {
		read(stream);
	}

	public MMP() {
	}
	
	public MMP(@Nonnull File file) throws Exception {
		Wc3BinInputStream inStream = new Wc3BinInputStream(file);

		read(inStream);

		inStream.close();
	}

	@Nonnull
	public static MMP ofMapFile(@Nonnull File mapFile) throws Exception {
		MpqPort.Out port = new JMpqPort.Out();
		
		port.add(GAME_PATH);
		
		MpqPort.Out.Result portResult = port.commit(mapFile);

		if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract info file");

		MMP mmp = new MMP();

		mmp.read(new Wc3BinInputStream(portResult.getInputStream(GAME_PATH)));
		
		return mmp;
	}
}
