package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.Format;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.app.Bounds;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;
import net.moonlightflower.wc3libs.dataTypes.app.FlagsInt;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.PathMap;
import net.moonlightflower.wc3libs.misc.Raster;
import net.moonlightflower.wc3libs.misc.Size;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;

/**
 * terrain pathing map file for wrapping war3map.wpm
 */
public class WPM extends Raster<FlagsInt> {	
	public final static File GAME_PATH = new File("war3map.wpm");
	
	public final static int CELL_SIZE = 32;
	
	@Override
	public int getCellSize() {
		return CELL_SIZE;
	}

	@Override
	public void setSize(int cellsCount) {
		_cells = new FlagsInt[cellsCount];
	}
	
	private static class Flags extends FlagsInt {
		private Flags(int val) {
			super(val);
		}
		
		public static Flags valueOf(int val) {
			return new Flags(val);
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

	@Override
	public FlagsInt mergeCellVal(@Nonnull FlagsInt oldVal, @Nonnull FlagsInt other) {
		return (Flags.valueOf(oldVal.toInt() | other.toInt()));
	}
	
	private PathMap _pathMap;
	
	public PathMap getPathMap() {
		return _pathMap;
	}
	
	@Override
	public WPM clone() {
		PathMap pathMap = _pathMap;
		
		WPM other = pathMap.getBounds() != null ? new WPM(pathMap.getBounds()) : new WPM();
		
		other.getPathMap().mergeCells(pathMap);
		
		return other;
	}
	
	public static class EncodingFormat extends Format<EncodingFormat.Enum> {
		public enum Enum {
			AUTO,
			WPM_0x0,
		}
		
		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat WPM_0x0 = new EncodingFormat(Enum.WPM_0x0, 0x0);

		@Nullable
		public static EncodingFormat valueOf(@Nonnull Integer version) {
			return get(EncodingFormat.class, version);
		}
		
		private EncodingFormat(@Nonnull Enum enumVal, int version) {
			super(enumVal, version);
		}
	}

	public void write_0x0(@Nonnull Wc3BinOutputStream stream) {
		stream.writeId(Id.valueOf("MP3W"));
		stream.writeInt32(EncodingFormat.WPM_0x0.getVersion());
		
		PathMap pathMap = getPathMap();
		
		int width = pathMap.getWidth();
		int height = pathMap.getHeight();
		
		stream.writeInt32(width);
		stream.writeInt32(height);
		
		int cellsCount = width * height;

		for (int i = 0; i < cellsCount; i++) {
			stream.writeUByte(pathMap.get(i));
		}
	}

	public void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		Id startToken = stream.readId("startToken");
		int version = stream.readInt32("version");
		
		stream.checkFormatVersion(EncodingFormat.WPM_0x0.getVersion(), version);
		
		int width = stream.readInt32("width");
		int height = stream.readInt32("height");
		
		PathMap pathMap = getPathMap();
		
		pathMap.setBounds(new Bounds(new Size(width, height)), false, false);

		int cellsCount = width * height;

		for (int i = 0; i < cellsCount; i++) {
			Coords2DI coords = pathMap.indexToCoords(i);

			//pathMap.set(i, stream.readUByte(String.format("tile%d (%d;%d)", i, coords.getX(), coords.getY())));
			pathMap.set(i, stream.readUByte());
		}
	}
	
	private void read_auto(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		Id startToken = stream.readId("startToken");
		int version = stream.readInt32("version");
		
		stream.rewind();

		read(stream, stream.getFormat(EncodingFormat.class, version));
	}
	
	private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
		switch (format.toEnum()) {
		case AUTO: {
			read_auto(stream);
			
			break;
		}
		case WPM_0x0: {
			read_0x0(stream);
			
			break;
		}
		}
	}
	
	private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
		switch (format.toEnum()) {
		case AUTO:
		case WPM_0x0: {
			write_0x0(stream);
			
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

	public void write(@Nonnull File file) throws IOException {
		Wc3BinOutputStream inStream = new Wc3BinOutputStream(file);

		write(inStream);

		inStream.close();
	}
	
	public WPM(@Nonnull PathMap pathMap) {
		this();
		
		_pathMap = pathMap.clone();
	}
	
	public WPM(@Nonnull Bounds bounds) {
		this(new PathMap(bounds));
	}

	public WPM(@Nonnull Wc3BinInputStream stream) throws IOException {
		this();
		
		read(stream);
	}
	
	public WPM() {
		super(new Bounds(0, 0, 0, 0));

		_pathMap = new PathMap(new Bounds(new Size(0, 0)));
	}

	@Nonnull
	public static WPM ofMap(@Nonnull File mapFile) throws IOException {
		MpqPort.Out portOut = new JMpqPort.Out();
		
		portOut.add(GAME_PATH);
		
		MpqPort.Out.Result portResult;
		
		try {
			portResult = portOut.commit(mapFile);
		} catch (Exception e) {
			throw new IOException(String.format("could not extract %s", GAME_PATH.toString()));
		}
		
		return new WPM(new Wc3BinInputStream(portResult.getInputStream(GAME_PATH)));
	}

	/*@Override
	public Coords2DI getCenter() {
		return getPathMap().getCenter();
	}

	@Override
	public int getCenterX() {
		return getPathMap().getCenterX();
	}

	@Override
	public int getCenterY() {
		return getPathMap().getCenterY();
	}

	@Override
	public Size getSize() {
		return getPathMap().getSize();
	}

	@Override
	public int getWidth() {
		return getPathMap().getWidth();
	}

	@Override
	public int getHeight() {
		return getPathMap().getHeight();
	}
	
	public void setBounds(@Nonnull Bounds val, boolean retainContents) {
		getPathMap().setBounds(val, retainContents);
	}*/
}
