package net.moonlightflower.wc3libs.misc.image;

import net.moonlightflower.wc3libs.dataTypes.app.FlagsInt;
import net.moonlightflower.wc3libs.misc.Size;
import net.moonlightflower.wc3libs.misc.UnsupportedFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Iterator;

public class BLP extends Wc3RasterImg {
	private static final Logger log = LoggerFactory.getLogger(FlagsInt.class.getName());
	private static final int CONTENT_JPEG = 0;
	private static final int CONTENT_DIRECT = 1;
	private static final int MAX_DIMENSION = 65535;
	private byte[] _sourceBytes;

	private static int readAlpha(@Nonnull byte[] alphaData, int pixelIndex, int alphaBits) {
		if (alphaBits <= 0) return 0xFF;

		switch (alphaBits) {
		case 1: {
			int byteIndex = pixelIndex / 8;

			if (byteIndex >= alphaData.length) return 0xFF;

			int bit = (alphaData[byteIndex] >> (pixelIndex % 8)) & 0x1;

			return bit == 0 ? 0x00 : 0xFF;
		}
		case 4: {
			int byteIndex = pixelIndex / 2;

			if (byteIndex >= alphaData.length) return 0xFF;

			int alphaNibble = (pixelIndex % 2 == 0) ? (alphaData[byteIndex] & 0xF) : ((alphaData[byteIndex] >> 4) & 0xF);

			return (alphaNibble * 0xFF) / 0xF;
		}
		case 8:
		default:
			if (pixelIndex >= alphaData.length) return 0xFF;

			return alphaData[pixelIndex] & 0xFF;
		}
	}

	private static byte[] buildCompoundJpeg(@Nonnull byte[] headerBytes, @Nonnull byte[] jpegPayload) {
		byte[] out = new byte[headerBytes.length + jpegPayload.length];
		System.arraycopy(headerBytes, 0, out, 0, headerBytes.length);
		System.arraycopy(jpegPayload, 0, out, headerBytes.length, jpegPayload.length);
		return out;
	}

	private static int mipSizeAtLevel(int baseSize, int level) {
		int result = Math.max(1, baseSize);

		for (int i = 0; i < level; i++) {
			result = Math.max(1, result / 2);
		}

		return result;
	}

	private static int getMipmapLevelCount(int width, int height, boolean hasMipmaps) {
		if (!hasMipmaps) return 1;

		int levels = 1;
		int maxDim = Math.max(width, height);

		while ((maxDim > 1) && (levels < 16)) {
			maxDim = Math.max(1, maxDim / 2);
			levels++;
		}

		return levels;
	}

	private static int validateDimension(String fieldName, int value, int version) throws UnsupportedFormatException {
		if (value <= 0) throw new UnsupportedFormatException(String.format("%s %d is invalid", fieldName, value));
		if (value > MAX_DIMENSION) throw new UnsupportedFormatException(String.format("%s %d exceeds max %d", fieldName, value, MAX_DIMENSION));

		return value;
	}

	private static int normalizeAlphaBits(int rawAlphaBits, int contentType) {
		if (contentType == CONTENT_JPEG) {
			if ((rawAlphaBits == 0) || (rawAlphaBits == 8)) return rawAlphaBits;

			return 0;
		}

		if ((rawAlphaBits == 0) || (rawAlphaBits == 1) || (rawAlphaBits == 4) || (rawAlphaBits == 8)) return rawAlphaBits;

		return 0;
	}

	private static byte[] readPadded(@Nonnull Reader reader, int size, @Nonnull String fieldName) throws IOException {
		int available = Math.min(size, reader.remaining());

		byte[] result = new byte[size];
		byte[] readBytes = reader.read(available);
		System.arraycopy(readBytes, 0, result, 0, readBytes.length);

		return result;
	}

	private static int safeToInt(long value, @Nonnull String fieldName) throws UnsupportedFormatException {
		if ((value < 0) || (value > Integer.MAX_VALUE)) {
			throw new UnsupportedFormatException(String.format("%s %d is too large", fieldName, value));
		}

		return (int) value;
	}

	private static byte[] getMipmapChunk(@Nonnull Reader reader, int[] mipmapOffsets, int[] mipmapSizes, int mipmapLevel) {
		if ((mipmapLevel < 0) || (mipmapLevel >= 16)) return new byte[0];

		int offset = mipmapOffsets[mipmapLevel];
		int size = mipmapSizes[mipmapLevel];

		if ((offset <= 0) || (size <= 0)) {
			return new byte[0];
		}

		if (offset >= reader.size()) {
			return new byte[0];
		}

		int available = Math.min(size, reader.size() - offset);

		return reader.copy(offset, available);
	}

	private static byte[] resizeChunk(@Nonnull byte[] src, int expectedSize, @Nonnull String label) {
		if (src.length == expectedSize) return src;

		return Arrays.copyOf(src, expectedSize);
	}

	private static class Reader {
		private byte[] _bytes;
		private int _pos;
		
		public byte[] read(int size) {
			if (size < 0) size = 0;

			int available = Math.min(size, remaining());

			byte b[] = new byte[size];
			
			for (int i = 0; i < available; i++) {
				b[i] = _bytes[_pos];
				
				_pos++;
			}
			
			return b;
		}
		
		public byte readByte() {
			return read(1)[0];
		}
		
		public int readUByte() {
			return readByte() & 0xFF;
		}
		
		public int readInt() {			
			ByteBuffer buf = ByteBuffer.wrap(read(4));
			
			buf.order(ByteOrder.LITTLE_ENDIAN);
			
			return buf.getInt();
		}
		
		public String readChar4() {
			byte[] bytes = read(4);
			
			return new String(bytes);
		}
		
		public void seek(int val) {
			_pos = Math.max(0, Math.min(val, _bytes.length));
		}

		public int remaining() {
			return _bytes.length - _pos;
		}

		public int size() {
			return _bytes.length;
		}

		public byte[] copy(int offset, int size) {
			if ((offset < 0) || (size <= 0) || (offset >= _bytes.length)) return new byte[0];

			int available = Math.min(size, _bytes.length - offset);
			byte[] result = new byte[available];
			System.arraycopy(_bytes, offset, result, 0, available);

			return result;
		}
		
		public Reader(@Nonnull InputStream inStream) throws IOException {
			_bytes = inStream.readAllBytes();
		}
	}
	
	private void read(@Nonnull InputStream inStream) throws UnsupportedFormatException {
		try {
			Reader reader = new Reader(inStream);

			String startToken = reader.readChar4();
			if ((startToken.length() < 4) || !startToken.startsWith("BLP")) {
				throw new UnsupportedFormatException(String.format("invalid magic %s", startToken));
			}

			int version = (int) startToken.charAt(3) - '0';
			if ((version < 0) || (version > 2)) throw new UnsupportedFormatException(String.format("unsupported BLP version %d", version));

			if (version == 0) {
				throw new UnsupportedFormatException("BLP0 is not supported (external bXX mipmap files required)");
			}
			
			int typeRaw = reader.readInt();
			int type = typeRaw;
			/*
			 * 0 - jpeg
			 * 1 - true color
			 */
			if ((type != CONTENT_JPEG) && (type != CONTENT_DIRECT)) {
				type = CONTENT_JPEG;
			}

			boolean hasAlpha = false;
			int alphaBits = 0;
			boolean hasMipmaps = false;
			int pixmapType = 1; // BLP1 direct is palette-indexed equivalent.
			
			if (version >= 2) {
				pixmapType = reader.readUByte();

				alphaBits = normalizeAlphaBits(reader.readUByte(), type);
				/*
				 * 8 - 8 bits for alpha
				 * 4 - 4 bits for alpha, not for JPEG
				 * 1 - 1 bit for alpha, not for JPEG
				 * 0 - no alpha
				 * unknown for BGRA pixmapType
				 */
				hasAlpha = alphaBits > 0;

				byte sampleType = reader.readByte();
				hasMipmaps = reader.readUByte() != 0;
			} else {
				int alphaBitsRaw = reader.readInt();
				if (type == CONTENT_JPEG) {
					// Some BLP1 JPEG exporters write garbage in alphaBits (for example 7 instead of 8).
					// Defer alpha presence to the decoded JPEG raster and keep the raw field only for
					// diagnostics and binary round-tripping.
					alphaBits = (alphaBitsRaw == 8) ? 8 : 0;
				} else {
					int normalizedRawBits = alphaBitsRaw;
					if ((alphaBitsRaw != 0) && (alphaBitsRaw != 1) && (alphaBitsRaw != 4) && (alphaBitsRaw != 8)) {
						if ((alphaBitsRaw & 0x8) > 0) {
							normalizedRawBits = 8;
						}
					}
					alphaBits = normalizeAlphaBits(normalizedRawBits, type);
				}

				hasAlpha = alphaBits > 0;
			}
			
			int width = validateDimension("width", reader.readInt(), version);
			int height = validateDimension("height", reader.readInt(), version);
			
			if (version < 2) {
				int unknown = reader.readInt(); //?
				hasMipmaps = reader.readInt() != 0;
				/*
				 * 0 - no mipmaps, only full
				 * else - mipmaps from full to 1x1
				 */
			}
			int mipmapCount = getMipmapLevelCount(width, height, hasMipmaps);
			
			int[] mipmapOffsets = new int[16];
			int[] mipmapSizes = new int[16];
			
			if (version >= 1) {				
				for (int i = 0; i < 16; i++) {
					mipmapOffsets[i] = reader.readInt(); //offset from file start
				}

				for (int i = 0; i < 16; i++) {
					mipmapSizes[i] = reader.readInt();
				}
			}
			
			//int colorInfo = reader.readInt32();
			/*
			 * 3 - uncompressed index list + alpha list
			 * 4 - uncompressed index list + alpha list
			 * 5 - uncompressed index list
			 */
			
			//int messy = reader.readInt32();  //1 - ?

			switch (type) {
			case 0: {
				//jpeg
				
				int headerSize = reader.readInt();
				if (headerSize < 0) throw new UnsupportedFormatException(String.format("invalid jpegHeaderSize %d", headerSize));
				byte[] headerBytes = readPadded(reader, headerSize, "jpegHeaderChunk");
				
				byte[] mipmapData0 = getMipmapChunk(reader, mipmapOffsets, mipmapSizes, 0);
				if (mipmapData0.length == 0) throw new UnsupportedFormatException("missing or invalid JPEG mipmap level 0");

				byte[] jpegBytes = buildCompoundJpeg(headerBytes, mipmapData0);
				Iterator<ImageReader> imgReaders = ImageIO.getImageReadersByFormatName("jpeg");
				ImageReader imgReader = null;

				while (imgReaders.hasNext()) {
					ImageReader candidate = imgReaders.next();
					if (candidate.canReadRaster()) {
						imgReader = candidate;
						break;
					}
				}
				if (imgReader == null) throw new UnsupportedFormatException("no JPEG reader with raster support found");

				java.awt.image.Raster raster;
				try (ImageInputStream imageInputStream = new MemoryCacheImageInputStream(new ByteArrayInputStream(jpegBytes))) {
					imgReader.setInput(imageInputStream, true, true);
					raster = imgReader.readRaster(0, null);
				} finally {
					imgReader.dispose();
				}

				BufferedImage writeImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
				int rasterWidth = raster.getWidth();
				int rasterHeight = raster.getHeight();


				final int bands = raster.getNumBands();
				final boolean rasterHasAlpha = bands >= 4;
				if (version < 2) {
					hasAlpha = rasterHasAlpha;
					alphaBits = rasterHasAlpha ? 8 : 0;
				}
				final int[] px = new int[Math.max(4, bands)];

				for (int x = 0; x < width; x++) {
					for (int y = 0; y < height; y++) {
						int srcX = (width <= 1) ? 0 : (x * rasterWidth) / width;
						int srcY = (height <= 1) ? 0 : (y * rasterHeight) / height;
						srcX = Math.max(0, Math.min(rasterWidth - 1, srcX));
						srcY = Math.max(0, Math.min(rasterHeight - 1, srcY));

						raster.getPixel(srcX, srcY, px);

						int blue = px[0] & 0xFF;
						int green = bands >= 2 ? (px[1] & 0xFF) : blue;
						int red = bands >= 3 ? (px[2] & 0xFF) : green;
						int alpha = rasterHasAlpha ? (px[3] & 0xFF) : 255;

						java.awt.Color color = new java.awt.Color(red, green, blue, alpha);

						writeImg.setRGB(x, y, color.getRGB());
					}
				}

				setFXImg(new FxImg(writeImg));

				break;
			}
			case 1: {
				if ((version >= 2) && (pixmapType == 3)) {
					long pixelCountLong = (long) width * (long) height;
					int pixelCount = safeToInt(pixelCountLong, "pixelCount");
					int expectedChunkSize = pixelCount * 4;

					byte[] mipmapData0 = getMipmapChunk(reader, mipmapOffsets, mipmapSizes, 0);
					if (mipmapData0.length == 0) throw new UnsupportedFormatException("missing or invalid BGRA mipmap level 0");
					mipmapData0 = resizeChunk(mipmapData0, expectedChunkSize, "BGRA mipmap 0 payload");

					BufferedImage writeImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
					int c = 0;

					for (int y = 0; y < height; y++) {
						for (int x = 0; x < width; x++) {
							int blue = mipmapData0[c++] & 0xFF;
							int green = mipmapData0[c++] & 0xFF;
							int red = mipmapData0[c++] & 0xFF;
							int alpha = mipmapData0[c++] & 0xFF;

							writeImg.setRGB(x, y, new java.awt.Color(red, green, blue, alpha).getRGB());
						}
					}

					setFXImg(new FxImg(writeImg));

					break;
				}

				if ((version >= 2) && (pixmapType == 2)) {
					throw new UnsupportedFormatException("BLP2 compressed sample pixmapType=2 is not supported yet");
				}

				byte[] colorTableBytes = readPadded(reader, 256 * 4, "direct color table");
				byte[][] colors = new byte[256][4];

				for (int i = 0; i < 256; i++) {
					colors[i][0] = colorTableBytes[i * 4];
					colors[i][1] = colorTableBytes[i * 4 + 1];
					colors[i][2] = colorTableBytes[i * 4 + 2];
					colors[i][3] = colorTableBytes[i * 4 + 3];
				}

				long pixelCountLong = (long) width * (long) height;
				int pixelCount = safeToInt(pixelCountLong, "pixelCount");
				int alphaSize = hasAlpha ? (pixelCount * alphaBits + 7) / 8 : 0;
				int expectedChunkSize = pixelCount + alphaSize;

				byte[] mipmapData0 = getMipmapChunk(reader, mipmapOffsets, mipmapSizes, 0);
				if (mipmapData0.length == 0) throw new UnsupportedFormatException("missing or invalid direct mipmap level 0");
				mipmapData0 = resizeChunk(mipmapData0, expectedChunkSize, "direct mipmap 0 payload");

				byte[] indexList = Arrays.copyOfRange(mipmapData0, 0, pixelCount);
				byte[] alphaList = hasAlpha ? Arrays.copyOfRange(mipmapData0, pixelCount, pixelCount + alphaSize) : null;

				BufferedImage writeImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
				
				int c = 0;

				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						int colIndex = indexList[c] & 0xFF;

						double red = ((double) (colors[colIndex][2] & 0xFF)) / 0xFF;
						double green = ((double) (colors[colIndex][1] & 0xFF)) / 0xFF;
						double blue = ((double) (colors[colIndex][0] & 0xFF)) / 0xFF;
						
						double alpha = 1D;
						
						if (hasAlpha) {
							int alphaIndex = readAlpha(alphaList, c, alphaBits);
							alpha = ((double) (alphaIndex & 0xFF)) / 0xFF;
						}
						
						writeImg.setRGB(x, y, new java.awt.Color((int) (red * 255), (int) (green * 255), (int) (blue * 255), (int) (alpha * 255)).getRGB());
						//pxWriter.setColor(x, y, Color.BLACK);
						
						c++;
					}
				}

				setFXImg(new FxImg(writeImg));
				
				break;
			}
			default: {
				throw new UnsupportedFormatException(String.format("format type %d not supported", type));
			}
			}
		} catch (IOException | RuntimeException e) {
			log.error(e.getMessage(), e);
			throw new UnsupportedFormatException(String.format("failed reading BLP: %s", e.getMessage()));
		}
	}

	public void write(@Nonnull OutputStream outStream) throws IOException {
		if (_sourceBytes == null) {
			throw new UnsupportedOperationException("BLP encoding is not implemented for generated images; load from a BLP file/stream for binary roundtrip write");
		}

		outStream.write(_sourceBytes);
	}

	public void write(@Nonnull File file) throws IOException {
		try (OutputStream outStream = Files.newOutputStream(file.toPath())) {
			write(outStream);
		}
	}
	
	public BLP(@Nonnull InputStream inStream) throws UnsupportedFormatException {
		super();

		try {
			_sourceBytes = inStream.readAllBytes();
		} catch (IOException e) {
			throw new UnsupportedFormatException(String.format("failed reading BLP stream: %s", e.getMessage()));
		}

		read(new ByteArrayInputStream(_sourceBytes));
	}
	
	public BLP(@Nonnull File file) throws IOException, UnsupportedFormatException {
		super();

		_sourceBytes = Files.readAllBytes(file.toPath());

		read(new ByteArrayInputStream(_sourceBytes));
	}
	
	public BLP(@Nonnull Size size) {
		super(size);
	}
}
