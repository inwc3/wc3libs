package net.moonlightflower.wc3libs.bin.app;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.moonlightflower.wc3libs.bin.Format;
import net.moonlightflower.wc3libs.bin.Wc3bin;
import net.moonlightflower.wc3libs.dataTypes.app.Bounds;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;
import net.moonlightflower.wc3libs.misc.Raster;
import net.moonlightflower.wc3libs.misc.Size;

/**
 * shadow map file file for wrapping war3map.shd
 */
public class SHD extends Raster<Boolean> {
	public final static String GAME_PATH = "war3map.shd";
	
	public final static int CELL_SIZE = 32;
	
	@Override
	public int getCellSize() {
		return CELL_SIZE;
	}

	@Override
	public void setSize(int cellsCount) {
		_cells = new Boolean[cellsCount];
	}

	@Override
	public Boolean mergeCellVal(Boolean oldVal, Boolean other) {
		return (oldVal && other);
	}
	
	private static class EncodingFormat extends Format<EncodingFormat.Enum> {
		enum Enum {
			AUTO,
			SHD_0x0,
		}

		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat SHD_0x0 = new EncodingFormat(Enum.SHD_0x0, 0x0);

		private static Map<Integer, EncodingFormat> _map = new HashMap<>();
		
		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}
		
		private EncodingFormat(Enum enumVal, int version) {
			super(enumVal, version);
			
			_map.put(version, this);
		}
	}
	
	private void write_0x0(Stream stream) {
		for (int i = 0; i < size(); i++) {
			if (get(i)) {
				stream.writeByte((byte) 0xFF);
			} else {
				stream.writeByte((byte) 0x00);
			}
		}
	}

	private void read_0x0(Stream stream) throws StreamException {
		int size = stream.size();
		
		setSize(size, false);
		
		int pos = stream.getPos();
		
		while (pos < size) {
			Boolean val = (stream.readByte() == 0xFF);
			
			set(pos, val);
		}
	}
	
	private void read_auto(Stream stream) throws StreamException {
		read(stream, EncodingFormat.SHD_0x0);
	}
	
	private void read(Stream stream, EncodingFormat format) throws StreamException {		
		switch (format.toEnum()) {
		case AUTO: {
			read_auto(stream);
			
			break;
		}
		case SHD_0x0: {
			read_0x0(stream);
			
			break;
		}
		}
	}
	
	private void write(Stream stream, EncodingFormat format) {
		switch (format.toEnum()) {
		case AUTO:
		case SHD_0x0: {
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
	
	private BufferedImage toImg() {
		BufferedImage img = new BufferedImage(getBounds().getSize().getWidth(), getBounds().getSize().getHeight(), BufferedImage.TYPE_BYTE_BINARY);
		
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				img.setRGB(x, y, get(new Coords2DI(x, y)) ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
			}
		}
		
		return img;
	}

	@Override
	public SHD clone() {
		SHD other = new SHD(getBounds());
		
		other.mergeCells(this);
		
		return other;
	}
	
	public SHD(Bounds bounds) {
		setBounds(bounds, false);
	}
	
	public SHD(BufferedImage img) {
		this(new Bounds(new Size(img.getWidth(), img.getHeight()), new Coords2DI(0, 0)));
		
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				set(new Coords2DI(x, y), img.getRGB(x, y) != Color.BLACK.getRGB());
			}
		}
	}
}
