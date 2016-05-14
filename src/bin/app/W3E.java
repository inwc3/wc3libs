package bin.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bin.Format;
import bin.Wc3bin;
import dataTypes.app.Bounds;
import dataTypes.app.Coords2DI;
import misc.Boundable;
import misc.Id;
import misc.Size;
import misc.Raster;

/**
 * environment file for wrapping war3map.w3e
 */
public class W3E extends Raster<W3E.Tile> implements Boundable {
	public final static String GAME_PATH = "war3map.w3e";
	
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
	public Tile mergeCellVal(Tile oldVal, Tile other) {
		return other;
	}
	
	char _tileset;
	
	public char getTileset() {
		return _tileset;
	}
	
	int _customTilesetFlag = 0;
	
	public int getCustomTilesetUsedFlag() {
		return _customTilesetFlag;
	}
	
	public void setCustomTilesetFlag(int val) {
		_customTilesetFlag = val;
	}
	
	public void setTileset(char val) {
		_tileset = val;
	}
	
	Map<Integer, Id> _groundTiles = new HashMap<>();
	
	public Id getGroundTile(int index) {
		return _groundTiles.get(index);
	}
	
	public void setGroundTile(int index, Id val) {
		_groundTiles.put(index, val);
	}
	
	Map<Integer, Id> _cliffTiles = new HashMap<>();
	
	public Id getCliffTile(int index) {
		return _cliffTiles.get(index);
	}
	
	public void setCliffTile(int index, Id val) {
		_cliffTiles.put(index, val);
	}
	
	public class Tile {
		final int groundZero = 0x2000;
		final float waterZero = 89.6F;
		final int cliffHeight = 0x200;
		
		public float rawToFinalGroundHeight(float rawVal, int cliffLevel) {
			return ((rawVal - groundZero + (cliffLevel - 2) * cliffHeight) / 4);
		}
	
		public float finalGroundToRawHeight(float finalVal, int cliffLevel) {
			return (finalVal * 4 - (cliffLevel - 2) * cliffHeight + groundZero);
		}
	
		public float rawToFinalWaterHeight(float rawVal) {
			return ((rawVal - groundZero) / 4) - waterZero;
		}
	
		public float finalWaterToRawHeight(float finalVal) {
			return ((finalVal + waterZero) * 4 + groundZero);
		}
		
		short _groundHeight = 0;
		
		public short getGroundHeight() {
			return _groundHeight;
		}
		
		public void setGroundHeight(short val) {
			_groundHeight = val;
		}
		
		short _waterLevel = 0;
		
		public short getWaterLevel() {
			return _waterLevel;
		}
		
		public void setWaterLevel(short val) {
			_waterLevel = val;
		}
		
		int _boundary = 0;
		
		public int getBoundary() {
			return _boundary;
		}
		
		public void setBoundary(int val) {
			_boundary = val;
		}
		
		int _boundary2;
		
		public int getBoundary2() {
			return _boundary2;
		}
		
		public void setBoundary2(int val) {
			_boundary2 = val;
		}
		
		int _water = 0;
		
		public int getWater() {
			return _water;
		}
		
		public void setWater(int val) {
			_water = val;
		}
		
		int _blight = 0;
		
		public int getBlight() {
			return _blight;
		}
		
		public void setBlight(int val) {
			_blight = val;
		}
		
		int _ramp = 0;
		
		public int getRamp() {
			return _ramp;
		}
		
		public void setRamp(int val) {
			_ramp = val;
		}
		
		int _tex = 0;
		
		public int getTex() {
			return _tex;
		}
		
		public void setTex(int val) {
			_tex = val;
		}
		
		int _cliffTex = 0;
		
		public int getCliffTex() {
			return _cliffTex;
		}
		
		public void setCliffTex(int val) {
			_cliffTex = val;
		}
		
		int _cliffLayer = 0;
		
		public int getCliffLayer() {
			return _cliffLayer;
		}
		
		public void setCliffLayer(int val) {
			_cliffLayer = val;
		}
		
		int _texDetails = 0;
		
		public int getTexDetails() {
			return _texDetails;
		}
		
		public void setTexDetails(int val) {
			_texDetails = val;
		}
		
		int _cliff = 0;
		
		public int getCliff() {
			return _cliff;
		}
		
		public void setCliff(int val) {
			_cliff = val;
		}
		
		public void read_0x11(Stream stream) throws StreamException {
			setGroundHeight(stream.readShort());
			
			short waterLevel = stream.readShort();
			
			setWaterLevel((short) (waterLevel & 0x7FFF));
			setBoundary(waterLevel >> 15);
			
			int flags = stream.readByte();
			
			setBoundary2(flags & 0x1);
			setWater((flags >> 1) & 0x1);
			setBlight((flags >> 2) & 0x1);
			setRamp((flags >> 3) & 0x1);
			setTex(flags >> 4);
			
			setTexDetails(stream.readByte());
			
			int cliff = stream.readByte();
			
			setCliffTex(cliff & 0xF);
			setCliffLayer(cliff >> 4);
		}
		
		private void write_0x11(Stream stream) {
			stream.writeShort(getGroundHeight());
			
			short waterLevel = getWaterLevel();
			
			waterLevel |= getBoundary() << 15;
			
			stream.writeShort(waterLevel);
			
			int flags = getBoundary2();
			
			flags |= (getWater() << 1);
			flags |= (getBlight() << 2);
			flags |= (getRamp() << 3);
			flags |= (getTex() << 4);
			
			stream.writeByte(flags);
			
			stream.writeByte(getTexDetails());
			
			int cliff = getCliffTex();
			
			cliff |= (getCliffLayer() << 4);
			
			stream.writeByte(cliff);
		}
		
		public void read(Stream stream, EncodingFormat format) throws StreamException {
			switch (format.toEnum()) {
			case W3E_0x11: {
				read_0x11(stream);
			}
			}
		}
		
		public void write(Stream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case W3E_0x11: {
				write_0x11(stream);
			}
			}
		}
		
		public Tile(Stream stream, EncodingFormat format) throws StreamException {
			read(stream, format);
		}
		
		public Tile() {
		}
	}
	
	public static class EncodingFormat extends Format<EncodingFormat.Enum> {
		enum Enum {
			AUTO,
			W3E_0x11,
		}

		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat W3E_0x11 = new EncodingFormat(Enum.W3E_0x11, 0x11);

		static Map<Integer, EncodingFormat> _map = new HashMap<>();
		
		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}
		
		private EncodingFormat(Enum enumVal, int version) {
			super(enumVal, version);
			
			_map.put(version, this);
		}
	}
	
	private void read_0x11(Stream stream) throws StreamException {
		Id startToken = stream.readId();
		
		int version = stream.readInt();
		
		Wc3bin.checkFormatVer("envMaskFunc", EncodingFormat.W3E_0x11.getVersion(), version);
		
		setTileset(stream.readChar());
		
		setCustomTilesetFlag(stream.readInt());
		
		int groundTilesUsedCount = stream.readInt();
		
		for (int i = 0; i < groundTilesUsedCount; i++) {
			setGroundTile(i, stream.readId());
		}
		
		int cliffTilesUsedCount = stream.readInt();
		
		for (int i = 0; i < cliffTilesUsedCount; i++) {
			setCliffTile(i, stream.readId());
		}

		setBounds(new Bounds(new Size(stream.readInt(), stream.readInt()), new Coords2DI(stream.readFloat().intValue(), stream.readFloat().intValue())), false);
		
		int width = getWidth();
		int height = getHeight();
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				set(new Coords2DI(x, y), new Tile(stream, EncodingFormat.W3E_0x11));
			}
		}
	}

	private void write_0x11(Stream stream) {
		stream.writeId(Id.valueOf("W3E!"));
		
		stream.writeInt(EncodingFormat.W3E_0x11.getVersion());
		
		stream.writeChar(getTileset());
		
		stream.writeInt(getCustomTilesetUsedFlag());
		
		stream.writeInt(_groundTiles.size());
		
		for (Id groundTile : _groundTiles.values()) {
			stream.writeId(groundTile);
		}
		
		stream.writeInt(_cliffTiles.size());

		for (Id cliffTile : _cliffTiles.values()) {
			stream.writeId(cliffTile);
		}

		int width = getWidth();
		int height = getHeight();

		stream.writeInt(width);
		stream.writeInt(height);
		
		Coords2DI center = getCenter();
		
		stream.writeFloat(center.getX());
		stream.writeFloat(center.getY());
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				get(new Coords2DI(x, y)).write(stream, EncodingFormat.W3E_0x11);
			}
		}
	}
	
	private void read_auto(Stream stream) throws StreamException {
		Id startToken = stream.readId();
		
		int version = stream.readInt();
		
		read(stream, EncodingFormat.valueOf(version));
	}
	
	private void read(Stream stream, EncodingFormat format) throws StreamException {		
		switch (format.toEnum()) {
		case AUTO: {
			read_auto(stream);
			
			break;
		}
		case W3E_0x11: {
			read_0x11(stream);
			
			break;
		}
		}
	}
	
	private void write(Stream stream, EncodingFormat format) {
		switch (format.toEnum()) {
		case AUTO:
		case W3E_0x11: {
			write_0x11(stream);
			
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
	
	@Override
	public W3E clone() {
		W3E other = new W3E(getBounds());
		
		other.mergeCells(this);
		
		return other;
	}
	
	public W3E(Bounds bounds) {
		setBounds(bounds, false);
	}
}
