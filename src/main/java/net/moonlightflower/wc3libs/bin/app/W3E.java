package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.dataTypes.app.Bounds;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DF;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;
import net.moonlightflower.wc3libs.misc.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.lang.Math;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * environment file for wrapping war3map.w3e
 */
public class W3E extends Raster<W3E.Tile> implements Boundable {
	public final static File GAME_PATH = new File("war3map.w3e");

	public final Id START_TOKEN = Id.valueOf("W3E!");
	public final static int CELL_SIZE = 128;

	@Override
	public int getCellSize() {
		return CELL_SIZE;
	}
	
	@Override
	public void setSize(int cellsCount) {
		_cells = new Tile[cellsCount];
	}
	
	@Override
	public Tile mergeCellVal(@Nonnull Tile oldVal, @Nonnull Tile other) {
		return other;
	}
	
	private char _tileset;
	
	public char getTileset() {
		return _tileset;
	}
	
	private int _customTilesetFlag = 0;
	
	public int getCustomTilesetUsedFlag() {
		return _customTilesetFlag;
	}
	
	public void setCustomTilesetFlag(int val) {
		_customTilesetFlag = val;
	}
	
	public void setTileset(char val) {
		_tileset = val;
	}
	
	private final Map<Integer, Id> _groundTiles = new LinkedHashMap<>();
	
	public Id getGroundTile(int index) {
		return _groundTiles.get(index);
	}
	
	public void setGroundTile(int index, Id val) {
		_groundTiles.put(index, val);
	}
	
	private final Map<Integer, Id> _cliffTiles = new LinkedHashMap<>();
	
	public Id getCliffTile(int index) {
		return _cliffTiles.get(index);
	}
	
	public void setCliffTile(int index, Id val) {
		_cliffTiles.put(index, val);
	}
	
	public static class Tile implements Printable {
		public final static int GROUND_ZERO = 0x2000;
		public final static float WATER_ZERO = 89.6F;
		public final static int CLIFF_HEIGHT = 0x200;
		
		public float rawToFinalGroundHeight(float rawVal, int cliffLevel) {
			return ((rawVal - GROUND_ZERO + (cliffLevel - 2) * CLIFF_HEIGHT) / 4);
		}
	
		public float finalGroundToRawHeight(float finalVal, int cliffLevel) {
			return (finalVal * 4 - (cliffLevel - 2) * CLIFF_HEIGHT + GROUND_ZERO);
		}
	
		public float rawToFinalWaterHeight(float rawVal) {
			return ((rawVal - GROUND_ZERO) / 4) - WATER_ZERO;
		}
	
		public float finalWaterToRawHeight(float finalVal) {
			return ((finalVal + WATER_ZERO) * 4 + GROUND_ZERO);
		}
		
		private short _groundHeight = 0;
		
		public short getGroundHeight() {
			return _groundHeight;
		}
		
		public void setGroundHeight(short val) {
			_groundHeight = val;
		}
		
		private short _waterLevel = 0;
		
		public short getWaterLevel() {
			return _waterLevel;
		}
		
		public void setWaterLevel(short val) {
			_waterLevel = val;
		}
		
		private int _boundary = 0;
		
		public int getBoundary() {
			return _boundary;
		}
		
		public void setBoundary(int val) {
			_boundary = val;
		}
		
		private int _boundary2;
		
		public int getBoundary2() {
			return _boundary2;
		}
		
		public void setBoundary2(int val) {
			_boundary2 = val;
		}
		
		private int _water = 0;
		
		public int getWater() {
			return _water;
		}
		
		public void setWater(int val) {
			_water = val;
		}
		
		private int _blight = 0;
		
		public int getBlight() {
			return _blight;
		}
		
		public void setBlight(int val) {
			_blight = val;
		}
		
		private int _ramp = 0;
		
		public int getRamp() {
			return _ramp;
		}
		
		public void setRamp(int val) {
			_ramp = val;
		}
		
		private int _tex = 0;
		
		public int getTex() {
			return _tex;
		}
		
		public void setTex(int val) {
			_tex = val;
		}
		
		private int _cliffTex = 0;
		
		public int getCliffTex() {
			return _cliffTex;
		}
		
		public void setCliffTex(int val) {
			_cliffTex = val;
		}
		
		private int _cliffLayer = 0;
		
		public int getCliffLayer() {
			return _cliffLayer;
		}
		
		public void setCliffLayer(int val) {
			_cliffLayer = val;
		}
		
		private int _texDetails = 0;
		
		public int getTexDetails() {
			return _texDetails;
		}
		
		public void setTexDetails(int val) {
			_texDetails = val;
		}
		
		private int _cliff = 0;
		
		public int getCliff() {
			return _cliff;
		}
		
		public void setCliff(int val) {
			_cliff = val;
		}

		@Override
		public void print(@Nonnull Printer printer) {
			//TODO
			printer.print(getBlight());
			printer.print(getBoundary());
		}

		public static class Writer extends net.moonlightflower.wc3libs.bin.Writer<EncodingFormat> {
			public Writer(@Nonnull Wc3BinOutputStream stream) {
				super(stream);
			}

			public Writer(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
				super(stream, format);
			}

			@Override
			public EncodingFormat getAutoFormat() {
				return EncodingFormat.AUTO;
			}

			private Tile _tile;

			public void exec(@Nonnull Tile tile) throws BinStream.StreamException {
				_tile = tile;

				write();
			}

			private void write() {
				switch (getFormat().toEnum()) {
					case AUTO:
					case W3E_0xB: {
						write_0xB();
					}
				}
			}

			private void write_0xB() {
				Wc3BinOutputStream stream = getStream();

				stream.writeInt16(_tile.getGroundHeight());

				short waterLevel = _tile.getWaterLevel();

				waterLevel |= _tile.getBoundary() << 15;

				stream.writeInt16(waterLevel);

				int flags = _tile.getBoundary2();

				flags |= (_tile.getWater() << 1);
				flags |= (_tile.getBlight() << 2);
				flags |= (_tile.getRamp() << 3);
				flags |= (_tile.getTex() << 4);

				stream.writeUByte(flags);

				stream.writeUByte(_tile.getTexDetails());

				int cliff = _tile.getCliffTex() << 4;

				int layer = Math.min(_tile.getCliffLayer(), 14); //15 is bad

				cliff |= layer;

				stream.writeUByte(cliff);
			}
		}

		private void write(@Nonnull Writer writer) throws BinStream.StreamException {
			writer.exec(this);
		}

		public static class Reader extends net.moonlightflower.wc3libs.bin.Reader<EncodingFormat> {
			public Reader(@Nonnull Wc3BinInputStream stream) {
				super(stream);
			}

			public Reader(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) {
				super(stream, format);
			}

			public Tile exec() throws BinStream.StreamException {
				return exec(new Tile());
			}

			private Tile _tile;

			private Tile exec(@Nonnull Tile tile) throws BinStream.StreamException {
				_tile = tile;

				read();

				return _tile;
			}

			private void read() throws BinStream.StreamException {
				read(getFormat());
			}

			private void read(@Nonnull EncodingFormat format) throws BinStream.StreamException {
				switch (format.toEnum()) {
					case W3E_0xB: {
						read_0xB();

						break;

					}
					default:
						throw new EncodingFormat.InvalidFormatException(format);
				}
			}

			private void read_0xB() throws BinInputStream.StreamException {
				Wc3BinInputStream stream = getStream();

				_tile.setGroundHeight(stream.readInt16("groundHeight"));

				short waterLevel = stream.readInt16("waterLevelAndFlag");

				_tile.setWaterLevel((short) (waterLevel & 0x7FFF));
				_tile.setBoundary(waterLevel >> 15);

				int flags = stream.readUByte("flags");

				_tile.setBoundary2(flags & 0x1);
				_tile.setWater((flags >> 1) & 0x1);
				_tile.setBlight((flags >> 2) & 0x1);
				_tile.setRamp((flags >> 3) & 0x1);
				_tile.setTex(flags >> 4);

				_tile.setTexDetails(stream.readUByte("texDetails"));

				byte cliff = stream.readByte("cliffTexAndLayer");

				_tile.setCliffTex((cliff >> 4) & 0xF);
				_tile.setCliffLayer(cliff & 0xF);
			}

			@Override
			public EncodingFormat getAutoFormat() {
				return EncodingFormat.AUTO;
			}
		}

		public Tile(@Nonnull Reader reader) throws BinStream.StreamException {
			reader.exec(this);
		}

		public Tile(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			new Reader(stream).exec();
		}
		
		public Tile() {
		}
	}
	
	public static class EncodingFormat extends Format<EncodingFormat.Enum> {
		public enum Enum {
			AUTO,
			W3E_0xB,
		}
		
		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, null);
		public final static EncodingFormat W3E_0xB = new EncodingFormat(Enum.W3E_0xB, 0xB);

		@Nullable
		public static EncodingFormat valueOf(@Nonnull Integer version) {
			return get(EncodingFormat.class, version);
		}
		
		private EncodingFormat(@Nonnull Enum enumVal, @Nullable Integer version) {
			super(enumVal, version);
		}
	}

	public class Writer extends net.moonlightflower.wc3libs.bin.Writer<EncodingFormat> {
		@Override
		public EncodingFormat getAutoFormat() {
			return EncodingFormat.AUTO;
		}

		private void write_0xB(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
			stream.writeId(START_TOKEN);

			stream.writeInt32(EncodingFormat.W3E_0xB.getVersion());

			stream.writeChar(getTileset());

			stream.writeInt32(getCustomTilesetUsedFlag());

			stream.writeInt32(_groundTiles.size());

			for (Id groundTile : _groundTiles.values()) {
				stream.writeId(groundTile);
			}

			stream.writeInt32(_cliffTiles.size());

			for (Id cliffTile : _cliffTiles.values()) {
				stream.writeId(cliffTile);
			}

			int width = getWidth();
			int height = getHeight();

			stream.writeInt32(width);
			stream.writeInt32(height);

			Coords2DF center = getCenter();

			stream.writeFloat32(center.getX());
			stream.writeFloat32(center.getY());

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					Tile tile = get(new Coords2DI(x, y));

					tile.write(new Tile.Writer(stream, EncodingFormat.W3E_0xB));
				}
			}
		}

		public void exec() throws BinStream.StreamException {
			switch (getFormat().toEnum()) {
				case AUTO:
				case W3E_0xB: {
					write_0xB(getStream());

					break;
				}
			}
		}

		public Writer(@Nonnull Wc3BinOutputStream stream) {
			super(stream);
		}
	}

	public void write(@Nonnull Writer writer) throws BinStream.StreamException {
		writer.exec();
	}

	public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
		write(new Writer(stream));
	}

	public static class Reader extends net.moonlightflower.wc3libs.bin.Reader<EncodingFormat> {
		public Reader(@Nonnull Wc3BinInputStream stream) {
			super(stream);
		}

		@Override
		public EncodingFormat getAutoFormat() {
			return EncodingFormat.AUTO;
		}

		@Nonnull
		public W3E exec() throws IOException {
			return exec(new W3E(new Bounds(0, 0, 0, 0)));
		}

		private W3E _w3e;

		private W3E exec(@Nonnull W3E w3e) throws IOException {
			_w3e = w3e;

			read(getFormat());

			return _w3e;
		}

		@Nonnull
		private W3E read(@Nonnull EncodingFormat format) throws IOException {
			switch (format.toEnum()) {
				case AUTO: {
					return read_auto();
				}
				case W3E_0xB: {
					return read_0xB();
				}
				default:
					throw new EncodingFormat.InvalidFormatException(format);
			}
		}

		@Nonnull
		private W3E read_0xB() throws BinInputStream.StreamException {
			Wc3BinInputStream stream = getStream();

			Id startToken = stream.readId();

			int version = stream.readInt32("version");

			stream.checkFormatVersion(EncodingFormat.W3E_0xB.getVersion(), version);

			_w3e.setTileset(stream.readChar("tileset"));

			_w3e.setCustomTilesetFlag(stream.readInt32("customTilesetFlag"));

			int groundTilesUsedCount = stream.readInt32("groundTilesUsedCount");

			for (int i = 0; i < groundTilesUsedCount; i++) {
				_w3e.setGroundTile(i, stream.readId("groundTilesUsed" + i));
			}

			int cliffTilesUsedCount = stream.readInt32("cliffTileUsedCount");

			for (int i = 0; i < cliffTilesUsedCount; i++) {
				_w3e.setCliffTile(i, stream.readId("cliffTilesUsed" + i));
			}

			_w3e.setBounds(new Bounds(new Size(stream.readInt32("width"), stream.readInt32("height")), new Coords2DF(stream.readFloat32("x"), stream.readFloat32("y"))), false, false);

			int width = _w3e.getWidth();
			int height = _w3e.getHeight();

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					_w3e.set(new Coords2DI(x, y), new Tile(new Tile.Reader(stream, EncodingFormat.W3E_0xB)));
				}
			}

			return _w3e;
		}

		@Nonnull
		private W3E read_auto() throws IOException {
			Wc3BinInputStream stream = getStream();

			Id startToken = stream.readId();

			int version = stream.readInt32();

			stream.rewind();

			EncodingFormat format = getStream().getFormat(EncodingFormat.class, version);

			if (format == null) throw new EncodingFormat.InvalidFormatException(version);

			return read(format);
		}
	}

	public W3E read(@Nonnull Reader reader) throws IOException {
		return reader.exec(this);
	}

	private W3E read(@Nonnull Wc3BinInputStream stream) throws IOException {
		return read(new Reader(stream));
	}

	public void write(@Nonnull File file) throws IOException {
		Wc3BinOutputStream outStream = new Wc3BinOutputStream(file);
		
		write(outStream);
		
		outStream.close();
	}

	@Nonnull
	@Override
	public W3E clone() {
		W3E other = (getBounds() == null) ? new W3E(new Bounds(0, 0, 0, 0)) : new W3E(getBounds());
		
		other.mergeCells(this);
		
		return other;
	}

	public W3E(@Nonnull Bounds bounds) {
		super(bounds);

		setBounds(bounds, false, false);
	}

	public W3E(@Nonnull Reader reader) throws IOException {
		super(new Bounds(0, 0, 0, 0));

		read(reader);
	}

	public W3E(@Nonnull Wc3BinInputStream stream) throws IOException {
		this(new Reader(stream));
	}

	public W3E(@Nonnull File file) throws IOException {
		super(new Bounds(0, 0, 0, 0));

		Wc3BinInputStream inStream = new Wc3BinInputStream(file);
		
		read(new Reader(inStream));
		
		inStream.close();
	}
}
