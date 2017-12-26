package net.moonlightflower.wc3libs.bin;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Vector;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import net.moonlightflower.wc3libs.misc.Id;

import javax.annotation.Nonnull;

public class Packed {
	public static Wc3BinOutputStream compress(@Nonnull Wc3BinInputStream inStream) throws BinStream.StreamException {
		//deflate
		Vector<byte[]> blocks = new Vector<>();
		Vector<byte[]> uncompressedBlocks = new Vector<>();
		
		long compressedSize = 0;
		long uncompressedSize = 0;
		
		while (!inStream.eof()) {
			Deflater deflater = new Deflater();

			//TODO: long?
			byte[] uncompressedBlock = new byte[(int) Math.min(1024, inStream.size() - inStream.getPos())];
			
			uncompressedBlocks.add(uncompressedBlock);
			uncompressedSize += uncompressedBlock.length;
			
			deflater.setInput(inStream.readBytes(uncompressedBlock.length));
			
			int len = deflater.deflate(uncompressedBlock);
			
			byte[] block = new byte[len];
			
			block = Arrays.copyOf(uncompressedBlock, len);
			
			blocks.add(block);
			compressedSize += block.length;
		}
		
		//write
		Wc3BinOutputStream outStream = new Wc3BinOutputStream(new ByteArrayOutputStream());
		
		String startTokenS = "Warcraft III recorded game \0x1A";
		
		outStream.writeBytes(startTokenS.getBytes(StandardCharsets.US_ASCII));
		
		outStream.writeUInt32(0x44);  //headerSize
		
		outStream.writeUInt32(compressedSize);  //compressedSize
		
		outStream.writeUInt32(0x01);  //headerVersion
		
		outStream.writeUInt32(uncompressedSize);  //uncompressedSize
		
		outStream.writeUInt32(blocks.size());
		
		outStream.writeId(Id.valueOf("W3XP"));
		
		outStream.writeUInt32(0);  //version
		
		outStream.writeUInt16(0);  //buildNum
		
		outStream.writeUInt16(0);  //flags
		
		outStream.writeUInt32(0);  //len in milliseconds
		
		CRC32 crc = new CRC32();
		
		for (int i = 0; i < outStream.size(); i++) {
			crc.update(outStream.get(i));
		}
		
		outStream.writeUInt32(crc.getValue());  //crc32
		
		int c = 0;
		
		for (byte[] block : blocks) {
			outStream.writeUInt16(block.length);
			outStream.writeUInt16(uncompressedBlocks.get(c).length);
			
			outStream.writeBytes(block);
			
			c++;
		}
		
		return outStream;
	}
	
	public static Wc3BinInputStream decompress(@Nonnull Wc3BinInputStream inStream) throws BinStream.StreamException {
		byte[] startToken = inStream.readBytes(28);
		
		long headerSize = inStream.readUInt32();
		/*
		 * 0x40 up to 1.06
		 * 0x44 else
		 */
		
		long compressedFileSize = inStream.readUInt32();
		
		long headerVersion = inStream.readUInt32();
		/*
		 * 0x00 up to 1.06
		 * 0x01 else
		 */
		
		long uncompressedFileSize = inStream.readUInt32();
		
		long blocksCount = inStream.readUInt32();
		
		long version;
		
		switch ((int) headerVersion) {
		case 0x00: {
			int unknown = inStream.readUInt16();
			version = inStream.readUInt32();
			
			break;
		}
		default: {
			Id id = inStream.readId(); //WAR3 or W3XP
			version = inStream.readUInt32();
		}
		}
		
		int buildNum = inStream.readUInt16();
		int flags = inStream.readUInt16();
		/*
		 * 0x0000 - singleplayer
		 * 0x8000 - multiplayer
		 */
		
		long len = inStream.readUInt32();
		/*
		 * milli seconds of replays, otherwise 0
		 */
		
		long crc32 = inStream.readUInt32();

		Vector<byte[]> blocks = new Vector<>();
		Vector<byte[]> uncompressedBlocks = new Vector<>();
		
		for (int i = 0; i < blocksCount; i++) {
			int compressedSize = inStream.readUInt16();
			int uncompressedSize = inStream.readUInt16();
			/*
			 * multiple of 2048, rest padded with 0x00
			 */
			
			long hash = inStream.readUInt32();

			blocks.add(inStream.readBytes(compressedSize));
			uncompressedBlocks.add(new byte[uncompressedSize]);
		}
		
		int c = 0;
		
		//inflate
		for (byte[] block : blocks) {
			Inflater inflater = new Inflater();
			
			inflater.setInput(block);
			
			try {
				inflater.inflate(uncompressedBlocks.get(c));
			} catch (DataFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			c++;
		}
		
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		
		try {
			for (byte[] block : uncompressedBlocks) {
				outStream.write(block);
			}
			
			outStream.close();
			
			return new Wc3BinInputStream(new ByteArrayInputStream(outStream.toByteArray()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
