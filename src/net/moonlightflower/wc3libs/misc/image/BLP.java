package net.moonlightflower.wc3libs.misc.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class BLP extends Wc3Img {
	private Image _fxImg;
	
	@Override
	public Image getFXImg() {
		return _fxImg;
	}
	
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
		
		public int readInt() {			
			ByteBuffer buf = ByteBuffer.wrap(read(4));
			
			buf.order(ByteOrder.LITTLE_ENDIAN);
			
			return buf.getInt();
		}
		
		public String readChar4() {
			read(4);
			
			return null;
		}
		
		public void seek(int val) {
			_pos = val;
		}
		
		public Reader(File file) throws IOException {
			_bytes = new byte[(int) file.length()];
			
			DataInputStream dis = new DataInputStream(new FileInputStream(file));
			
			dis.readFully(_bytes);
			
			dis.close();
		}
	}
	
	private void read(File file) throws FileNotFoundException {
		try {
			Reader reader = new Reader(file);
			
			String startToken = reader.readChar4();
			
			int type = reader.readInt();
			
			int alphaState = reader.readInt();
			
			int width = reader.readInt();
			
			int height = reader.readInt();
			
			int colorInfo = reader.readInt();
			
			int messy = reader.readInt();
			
			int[] mipmapOffsets = new int[16];
			
			for (int i = 0; i < 16; i++) {
				mipmapOffsets[i] = reader.readInt();
			}
			
			int[] mipmapSizes = new int[16];

			for (int i = 0; i < 16; i++) {
				mipmapSizes[i] = reader.readInt();
			}
			
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
				
				_fxImg = new JPG(stream).getFXImg();

				break;
			}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BLP(File file) throws FileNotFoundException {
		this();
		
		read(file);
	}
	
	public BLP() {
		super();
		
		_fxImg = new WritableImage(1, 1);
	}
}
