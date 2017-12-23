package net.moonlightflower.wc3libs.misc.image;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import net.moonlightflower.wc3libs.misc.Size;
import net.moonlightflower.wc3libs.misc.UnsupportedFormatException;

public class BLP extends Wc3RasterImg {
	private class Reader {		
		private byte[] _bytes;
		private int _pos;
		
		public byte[] read(int size) {
			byte b[] = new byte[size];
			
			for (int i = 0; i < size; i++) {
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
			_pos = val;
		}
		
		public Reader(InputStream inStream) throws IOException {
			int val;
			List<Byte> bytes = new ArrayList<>();
			
			while ((val = inStream.read()) != -1) {
				bytes.add((byte) (val & 0xFF));
			}
			
			_bytes = new byte[bytes.size()];
			
			for (int i = 0; i < bytes.size(); i++) {
				_bytes[i] = bytes.get(i);
			}
		}
	}
	
	private void read(@Nonnull InputStream inStream) throws UnsupportedFormatException {
		try {
			Reader reader = new Reader(inStream);
			
			String startToken = reader.readChar4();

			int version = (int) startToken.charAt(3) - '0';
			
			int type = reader.readInt();
			/*
			 * 0 - jpeg
			 * 1 - true color
			 */

			boolean hasAlpha = false;
			
			if (version >= 2) {
				int pixmapType = reader.readUByte();
				/*
				 * 1 - indexed
				 * 2 - compressed sample
				 * 3 - BGRA
				 */

				if (pixmapType > 3) throw new UnsupportedFormatException(String.format("invalid pixmapType %d", pixmapType));

				byte alphaBits = reader.readByte();
				/*
				 * 8 - 8 bits for alpha
				 * 4 - 4 bits for alpha, not for JPEG
				 * 1 - 1 bit for alpha, not for JPEG
				 * 0 - no alpha
				 * unknown for BGRA pixmapType
				 */

				byte sampleType = reader.readByte();
				byte hasMipmaps = reader.readByte();
			} else {
				int alphaBits = reader.readInt();
				
				hasAlpha = ((alphaBits & 0x8) > 0);
			}
			
			int width = reader.readInt(); //max 512
			int height = reader.readInt(); //max 512

			if (width > 512) throw new UnsupportedFormatException(String.format("width %d exceeds the limit of 512", width));
			if (height > 512) throw new UnsupportedFormatException(String.format("height %d exceeds the limit of 512", height));
			
			if (version < 2) {
				int unknown = reader.readInt(); //?
				int hasMipmaps = reader.readInt();
				/*
				 * 0 - no mipmaps, only full
				 * else - mipmaps from full to 1x1
				 */
			}
			
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
				
				byte[] headerBytes = new byte[headerSize];
				
				for (int i = 0; i < headerSize; i++) {
					headerBytes[i] = reader.readByte();
				}
				
				byte[][] mipmapDatas = new byte[16][];
				
				for (int i = 0; i < 16; i++) {				
					reader.seek(mipmapOffsets[i]);

					byte[] mipmapData = new byte[mipmapSizes[i]];
					
					mipmapDatas[i] = mipmapData;//ByteBuffer.allocate();
					
					for (int j = 0; j < mipmapSizes[i]; j++) {
						//mipmapData[i].put(reader.readByte());
						mipmapData[j] = reader.readByte();
					}
				}
				
				ByteBuffer buf = ByteBuffer.allocate(headerSize + mipmapSizes[0]);
				
				buf.put(headerBytes);
				buf.put(mipmapDatas[0]);
				
				InputStream stream = new ByteArrayInputStream(buf.array());
				
				stream.reset();
				
				setFXImg(new JPG(stream).getFXImg());
				
				/*OutputStream writer = new FileOutputStream("D:\\a\\test.jpg");
				
				writer.write(buf.array());
				
				writer.close();

				JpegDecoder decoder = new JpegDecoder();
				
				BufferedImage imageRGB = null;
				
				try {
					byte[] all = new byte[headerBytes.length + mipmapDatas[0].length];
					
					for (int i = 0; i < headerBytes.length; i++) {
						all[i] = headerBytes[i];
					}
					for (int i = 0; i < mipmapDatas[0].length; i++) {
						all[headerBytes.length + i] = mipmapDatas[0][i];
					}
					
					imageRGB = decoder.read(all);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Graphics2D graphics = imageRGB.createGraphics();

				ImageIO.write(
						  imageRGB, 
						  "jpg", 
						  new File(
								  "D:\\a\\outMinimap.jpg"));
				
				//Image fxImg = new JPG(stream).getFXImg();
				System.out.println("A");
				//ImageIO.write(SwingFXUtils.fromFXImage(fxImg, null), "jpg", new File("D:\\a\\outMinimap.jpg"));
				System.out.println("B");
				setFXImg(null);
				
				graphics.dispose();*/

				break;
			}
			case 1: {
				byte[][] colors = new byte[256][4];
				
				for (int i = 0; i < 256; i++) {
					colors[i][0] = reader.readByte();
					colors[i][1] = reader.readByte();
					colors[i][2] = reader.readByte();
					colors[i][3] = reader.readByte();
					
					//System.out.println("color " + i + " -> " + colors[i][0] + ";" + colors[i][1] + ";" + colors[i][2] + ";" + colors[i][3]);
				}
				
				int curWidth = width;
				int curHeight = height;
				
				byte mipmapIndexLists[][] = new byte[16][];
				byte mipmapAlphaLists[][] = new byte[16][];
				
				for (int i = 0; i < 1; i++) {
					byte[] indexList = new byte[curWidth * curHeight];
					
					mipmapIndexLists[i] = indexList;
					
					for (int j = 0; j < indexList.length; j++) {
						indexList[j] = reader.readByte();
					}
					
					if (hasAlpha) {
						byte[] alphaList = new byte[curWidth * curHeight];
	
						mipmapAlphaLists[i] = alphaList;
						
						for (int j = 0; j < indexList.length; j++) {
							alphaList[j] = reader.readByte();
						}
					}
				}
				
				WritableImage writeImg = new WritableImage(width, height);
				
				PixelWriter pxWriter = writeImg.getPixelWriter();
				
				int c = 0;
				
				byte[] indexList = mipmapIndexLists[0];
				byte[] alphaList = mipmapAlphaLists[0];
				
				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						int colIndex = indexList[c] & 0xFF;

						double red = ((double) (colors[colIndex][2] & 0xFF)) / 0xFF;
						double green = ((double) (colors[colIndex][1] & 0xFF)) / 0xFF;
						double blue = ((double) (colors[colIndex][0] & 0xFF)) / 0xFF;
						
						double alpha = 1D;
						
						if (hasAlpha) {
							int alphaIndex = alphaList[c] & 0xFF;
						
							alpha = ((double) (alphaIndex & 0xFF)) / 0xFF;
						}
						
						pxWriter.setColor(x, y, new Color(red, green, blue, alpha));
						//pxWriter.setColor(x, y, Color.BLACK);
						
						c++;
					}
				}

				setFXImg(writeImg);
				
				break;
			}
			default: {
				throw new UnsupportedFormatException(String.format("format type %d not supported", type));
			}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BLP(@Nonnull InputStream inStream) throws UnsupportedFormatException {
		super();
		
		read(inStream);
	}
	
	public BLP(@Nonnull File file) throws IOException, UnsupportedFormatException {
		super();
		
		InputStream inStream = new FileInputStream(file);
		
		read(inStream);
		
		inStream.close();
	}
	
	public BLP(@Nonnull Size size) {
		super(size);
	}
}
