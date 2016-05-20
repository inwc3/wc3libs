package net.moonlightflower.wc3libs.bin.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.moonlightflower.wc3libs.bin.Format;
import net.moonlightflower.wc3libs.bin.Wc3bin;
import net.moonlightflower.wc3libs.bin.Wc3bin.Stream;
import net.moonlightflower.wc3libs.bin.Wc3bin.StreamException;
import net.moonlightflower.wc3libs.dataTypes.app.Bounds;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DF;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;
import net.moonlightflower.wc3libs.misc.Boundable;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.PathMap;
import net.moonlightflower.wc3libs.misc.Size;

/**
 * terrain pathing map file for wrapping war3map.wpm
 */
public class WPM implements Boundable {
	public final static String GAME_PATH = "war3map.wpm";
	
	private PathMap _pathMap;
	
	public PathMap getPathMap() {
		return _pathMap;
	}
	
	@Override
	public WPM clone() {
		PathMap pathMap = _pathMap;
		
		WPM other = new WPM(pathMap.getBounds());
		
		other.getPathMap().mergeCells(pathMap);
		
		return other;
	}
	
	private static class EncodingFormat extends Format<EncodingFormat.Enum> {
		enum Enum {
			AUTO,
			WPM_0x0,
		}

		private final static Map<Integer, EncodingFormat> _map = new HashMap<>();
		
		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat WPM_0x0 = new EncodingFormat(Enum.WPM_0x0, 0x0);
		
		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}
		
		private EncodingFormat(Enum enumVal, int version) {
			super(enumVal, version);
			
			_map.put(version, this);
		}
	}

	public void write_0x0(Stream stream) {
		stream.writeId(Id.valueOf("MP3W"));
		stream.writeInt(EncodingFormat.WPM_0x0.getVersion());
		
		PathMap pathMap = getPathMap();
		
		int width = pathMap.getWidth();
		int height = pathMap.getHeight();
		
		stream.writeInt(height);
		stream.writeInt(height);
		
		int cellsCount = width * height * 16;
		
		for (int i = 0; i < cellsCount; i++) {
			stream.writeByte(pathMap.get(i));
		}
	}

	public void read_0x0(Stream stream) throws StreamException {		
		Id startToken = stream.readId("startToken");
		int version = stream.readInt("version");
		
		Wc3bin.checkFormatVer("wpmMaskFunc", EncodingFormat.WPM_0x0.getVersion(), version);
		
		int width = stream.readInt("width");
		int height = stream.readInt("height");
		
		PathMap pathMap = getPathMap();
		
		pathMap.setBounds(new Bounds(new Size(width, height), new Coords2DI(0, 0)), false);

		int cellsCount = width * height;
		
		for (int i = 0; i < cellsCount; i++) {
			Coords2DI coords = pathMap.indexToCoords(i);

			pathMap.set(i, stream.readByte(String.format("tile%d (%d;%d)", i, coords.getX(), coords.getY())));
		}
	}
	
	private void read_auto(Stream stream) throws StreamException {
		Id startToken = stream.readId("startToken");
		int version = stream.readInt("version");
		
		stream.rewind();

		read(stream, EncodingFormat.valueOf(version));
	}
	
	private void read(Stream stream, EncodingFormat format) throws StreamException {		
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
	
	private void write(Stream stream, EncodingFormat format) {
		switch (format.toEnum()) {
		case AUTO:
		case WPM_0x0: {
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
	
	public WPM(PathMap pathMap) {
		this();
		
		_pathMap = pathMap.clone();
	}
	
	public WPM(Bounds bounds) {
		this(new PathMap(bounds));
	}

	public WPM(Wc3bin.Stream stream) throws IOException {
		this();
		
		read(stream);
	}
	
	public WPM() {
		_pathMap = new PathMap(new Bounds(new Size(0, 0), new Coords2DI(0, 0)));
	}
	
	@Override
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
	
	public void setBounds(Bounds val, boolean retainContents) {
		getPathMap().setBounds(val, retainContents);
	}
}
