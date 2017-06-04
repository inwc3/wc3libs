package net.moonlightflower.wc3libs.misc.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import net.moonlightflower.wc3libs.bin.StdBinStream;
import net.moonlightflower.wc3libs.misc.UnsupportedFormatException;

public class TGADecoder {
	private int _height;
	private int _width;
	
	private boolean _isV2;
	private int _metaOffset;
	private int _devAreaOffset;
	
	private StdBinStream _stream;
	
	private void readFooter() throws IOException {
		String v2Sign = "TRUEVISION_XFILE.";
		
		_stream.setPos(_stream.size() - v2Sign.length() - 1);
		
		String versionSign = _stream.readString("versionSign");
		
		if (versionSign.equals(v2Sign)) {
			_stream.setPos(_stream.size() - v2Sign.length() - 9);
			
			_isV2 = true;
			
			_metaOffset = _stream.readDoubleWord("metaOffset");
			_devAreaOffset = _stream.readDoubleWord("devAreaOffset");
		}
	}
	
	private void readMeta() throws IOException {
		if (_isV2 && _metaOffset > 0) {
			int size = _stream.readWord("metaSize"); //always 495
			
			String author = _stream.readString("author"); //max 40
			String commentA = _stream.readString("commentA"); //max 80
			String commentB = _stream.readString("commentB"); //max 80
			String commentC = _stream.readString("commentC"); //max 80
			String commentD = _stream.readString("commentD"); //max 80
			
			int stampMonth = _stream.readWord("timestamp_month");
			int stampDay = _stream.readWord("timestamp_day");
			int stampYear = _stream.readWord("timestamp_year");
			int stampHour = _stream.readWord("timestamp_hour");
			int stampMin = _stream.readWord("timestamp_min");
			int stampSec = _stream.readWord("timestamp_sec");
			
			String jobName = _stream.readString("jobName"); //max 40
			int jobHour = _stream.readWord("jobHour");
			int jobMin = _stream.readWord("jobMin");
			int jobSec = _stream.readWord("jobSec");
			String softwareId = _stream.readString("softwareId"); //max 40
			
			int version = _stream.readWord("version");
			byte softwareVersion = _stream.readByte("softwareVersion");
			int backgroundColor = _stream.readWord("backgroundColor"); //0 = transparent
			int pxAspectRatioNumerator = _stream.readWord("pxAspectRatioNum");
			int pxAspectRatioDenominator = _stream.readWord("pxAspectRatioDenom");
			
			int gammaNumerator = _stream.readWord("gammaNum");
			int gammaDenominator = _stream.readWord("gammaDenom");
			
			int colorCorrectionTableOffset = _stream.readDoubleWord("colorCorrectionTableOffset"); //related to start (0 = skip)
			int thumbnailOffset = _stream.readDoubleWord("thumbnailOffset"); //related to start (0 = skip)
			int scanLineTableOffset = _stream.readDoubleWord("scanLineTableOffset"); //related to start (0 = skip)
			
			//scanLineTable
			if (scanLineTableOffset > 0) {
				_stream.setPos(scanLineTableOffset);
				
				int offsets[] = new int[_height];
				
				for (int i = 0; i < offsets.length; i++) {
					offsets[i] = _stream.readWord(String.format("scanLineOffset %d", i)); //related to start
				}
			}
			
			//thumbnail
			if (thumbnailOffset > 0) {
				_stream.setPos(thumbnailOffset);
				
				int width = _stream.readByte("thumbnailWidth");
				int height = _stream.readByte("thumbnailHeight");
				
				//img data
				//TODO
			}
			
			//colorCorrectionTable
			if (colorCorrectionTableOffset > 0) {
				_stream.setPos(colorCorrectionTableOffset);
				
				int colors[][] = new int[256][];
				
				for (int i = 0; i < 256; i++) {
					colors[i][0] = _stream.readWord(String.format("colorCorrectionAlpha", i)); //alpha
					colors[i][1] = _stream.readWord(String.format("colorCorrectionBlue", i)); //blue
					colors[i][2] = _stream.readWord(String.format("colorCorrectionGreen", i)); //green
					colors[i][3] = _stream.readWord(String.format("colorCorrectionRed", i)); //red
				}
			}
		}
	}
	
	private Color readColor(int bitsPerPx) throws IOException {
		int bytesPerPx = (bitsPerPx + 8 - 1) / 8;
		
		int colorData = 0;
		
		for (int j = 0; j < bytesPerPx; j++) {
			//colorData += _stream.readByte(String.format("color %d (byte %d)", i, j)) << ((bytesPerPx - j - 1) * 8);
			int add = ((_stream.readByte() & 0xFF) << ((bytesPerPx - j - 1) * 8));
			
			colorData += add;
		}
		
		double red = 0D;
		double green = 0D;
		double blue = 0D;
		double alpha = 0D;
		
		switch (bitsPerPx) {
		case 1: {
			red = colorData * 255;
			green = colorData * 255;
			blue = colorData * 255;
			alpha = 1D;
			
			break;
		}
		case 8: {
			//TODO
			
			break;
		}
		case 15: {
			red = ((float) ((colorData >> 10) & 0x1F)) / 0x1F;
			green = ((float) ((colorData >> 5) & 0x1F)) / 0x1F;
			blue = ((float) ((colorData >> 0) & 0x1F)) / 0x1F;
			alpha = 1D;
			
			break;
		}
		case 16: {
			red = ((float) ((colorData >> 10) & 0x1F)) / 0x1F;
			green = ((float) ((colorData >> 5) & 0x1F)) / 0x1F;
			blue = ((float) ((colorData >> 0) & 0x1F)) / 0x1F;
			alpha = colorData >> 15;
			
			break;
		}
		case 24: {
			red = ((double) ((colorData >> 0) & 0xFF)) / 0xFF;
			green = ((double) ((colorData >> 8) & 0xFF)) / 0xFF;
			blue = ((double) ((colorData >> 16) & 0xFF)) / 0xFF;
			alpha = 1D;

			break;
		}
		case 32: {
			red = ((float) ((colorData >> 8) & 0xFF)) / 0xFF;
			green = ((float) ((colorData >> 16) & 0xFF)) / 0xFF;
			blue = ((float) ((colorData >> 24) & 0xFF)) / 0xFF;
			alpha = ((float) ((colorData >> 0) & 0xFF)) / 0xFF;

			break;
		}
		default:
			throw new IOException("bitsPerPx " + bitsPerPx + " not supported");
		}
		
		return new Color(red, green, blue, alpha);
	}
	
	private BufferedImage readPriv(InputStream inStream) throws IOException, UnsupportedFormatException {
		try {
			_stream = new StdBinStream(inStream);
			
			//read footer first to know if it's a V2 TGA
			readFooter();
			
			_stream.setPos(0);
			
			//imgId
			int imgIdLen = _stream.readByte("imgIdLen");
			
			//colorMapType
			int colorMapType = _stream.readByte("colorMapType");
			/* 0 - no color map
			 * 1 - has color map
			 * 2-127 - reserved
			 * 128-255 - open for custom use
			*/
			
			//imgType
			int imgTypeEx = _stream.readByte("imgTypeEx");
			/* 0 - no img data
			 * 1 - uncompressed color-mapped
			 * 2 - uncompressed true-color
			 * 3 - uncompressed grayscale
			 * 9 - run-length encoded color-mapped
			 * 10 - run-length encoded true-color
			 * 11 - run-length encoded grayscale
			 */
			
			int imgType = imgTypeEx & 0x7;
			boolean RLE = ((imgTypeEx & 0x8) > 0); //RLE compression

			//colorMapParams
			int colorMapStart = _stream.readWord("colorMapStart");
			int colorMapLen = _stream.readWord("colorMapLen");
			int colorMapEntryLen = _stream.readByte("colorMapEntryLen");
			
			//imgParams
			int originX = _stream.readWord("originX");
			int originY = _stream.readWord("originY");
			int width = _stream.readUWord("width");
			int height = _stream.readUWord("height");
			int bitsPerPx = _stream.readByte("bitsPerPx"); //1, 8, 15, 16, 24 or 32
			int imgDescriptor = _stream.readByte("imgDescriptor");
			
			int alphaDepth = imgDescriptor & 0x7;
			int dir = (imgDescriptor >> 4) & 0x3;
			
			boolean dirLeftwards = ((dir & 0x1) > 0);
			boolean dirDownwards = ((dir & 0x2) > 0);
			
			if (imgIdLen > 0) {
				int[] imgId = new int[imgIdLen];
				
				for (int i = 0; i < imgId.length; i++) {
					imgId[i] = _stream.readByte(String.format("imgId %d", i));
				}
			}
			
			Color[] colorMap = null;
			
			if (colorMapType > 0 && colorMapLen > 0) {
				colorMap = new Color[colorMapLen];
				
				for (int i = 0; i < colorMapLen; i++) {
					colorMap[i] = readColor(bitsPerPx);
				}
			}

			Color pxColors[][] = new Color[width][height];

			//img data
			switch (imgType) {
			case 1: {
				if (RLE) {
					int x = 0;
					int y = 0;
					
					while ((x < width) && (y < height)) {
						byte header = _stream.readByte();
						
						boolean isRunLenPacket = ((header & 0x80) > 0);
						int size = (header & 0x7F) + 1;
						
						if (isRunLenPacket) {
							int colorIndex = _stream.readWord();
							
							Color color = colorMap[colorIndex];
							
							for (int i = 0; i < size; i++) {
								pxColors[x][y] = color;
								
								x++;
								
								if (x >= width) {
									x = 0;
									y++;
								}
							}
						} else {
							for (int i = 0; i < size; i++) {
								int colorIndex = _stream.readWord();
								
								pxColors[x][y] = colorMap[colorIndex];
								
								x++;
								
								if (x >= width) {
									x = 0;
									y++;
								}
							}
						}
					}
				} else {
					for (int y = 0; y < height; y++) {
						for (int x = 0; x < width; x++) {
							//int colorIndex = _stream.readWord(String.format("colorIndex", x, y));
							int colorIndex = _stream.readWord();
							
							pxColors[x][y] = colorMap[colorIndex];
						}
					}
				}
				
				break;
			}
			case 2: {
				if (RLE) {
					int x = 0;
					int y = 0;
					
					while ((x < width) && (y < height)) {
						byte header = _stream.readByte();
						
						boolean isRunLenPacket = ((header & 0x80) > 0);
						int size = (header & 0x7F) + 1;
						
						if (isRunLenPacket) {
							Color color = readColor(bitsPerPx);
							
							for (int i = 0; i < size; i++) {
								pxColors[x][y] = color;
								
								x++;
								
								if (x >= width) {
									x = 0;
									y++;
								}
							}
						} else {
							for (int i = 0; i < size; i++) {
								pxColors[x][y] = readColor(bitsPerPx);
								
								x++;
								
								if (x >= width) {
									x = 0;
									y++;
								}
							}
						}
					}
				} else {
					for (int y = 0; y < height; y++) {
						for (int x = 0; x < width; x++) {
							pxColors[x][y] = readColor(bitsPerPx);
						}
					}
				}
				
				break;
			}
			default: {
				throw new IOException("imgType " + imgType + " is not supported");
			}
			}
			
			//dev area
			if (_isV2 && _devAreaOffset > 0) {
				int devEntriesLen = _stream.readWord("devEntriesLen");
				int devId = _stream.readWord("devId"); //32768-65535 reserved
				int devOffset = _stream.readDoubleWord("devOffset"); //related to file start
				int devLen = _stream.readDoubleWord("devLen");
				
				byte devEntries[][] = new byte[devEntriesLen][];
				
				for (int i = 0; i < devEntriesLen; i++) {
					devEntries[i] = new byte[10];
					
					for (int j = 0; j < 10; j++) {
						devEntries[i][j] = (byte) _stream.readByte(String.format("devEntry %d;%d", i, j));
					}
				}
			}
			
			//meta
			readMeta();
			
			WritableImage writeImg = new WritableImage(width, height);
			BufferedImage bufImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			
			PixelWriter pxWriter = writeImg.getPixelWriter();

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					int x2 = x;
					int y2 = y;
					
					if (dirLeftwards) x2 = width - x - 1;
					if (!dirDownwards) y2 = height - y - 1;
					
					Color pxColor = pxColors[x2][y2];

					//pxColor = new Color(pxColor.getRed(), pxColor.getGreen(), pxColor.getBlue(), 1D);
			
					pxWriter.setColor(x, y, pxColor);
					bufImg.setRGB(x, y, new java.awt.Color((float) pxColor.getRed(), (float) pxColor.getGreen(), (float) pxColor.getBlue(), (float) pxColor.getOpacity()).getRGB());
				}
			}
			
			return bufImg;
		} catch (Exception e) {
			_stream.printLog();
			
			e.printStackTrace();
			
			throw e;
		}
	}
	
	private TGADecoder() {
	}
	
	public static BufferedImage read(InputStream inStream) throws IOException, UnsupportedFormatException {		
		TGADecoder decoder = new TGADecoder();
		
		BufferedImage img = decoder.readPriv(inStream);
		
		return img;
	}
}
