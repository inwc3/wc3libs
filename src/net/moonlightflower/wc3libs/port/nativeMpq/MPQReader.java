package net.moonlightflower.wc3libs.port.nativeMpq;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import net.moonlightflower.wc3libs.bin.BinStream;

public class MPQReader {
	public class FileBinStream extends BinStream {
		private RandomAccessFile _stream;

		public byte read() throws IOException {
			return _stream.readByte();
		}
		
		private byte[] read(int len) throws IOException {
			byte[] vals = new byte[len];
			
			_stream.read(vals, 0, len);
			
			return vals;
		}
		
		private int readDoubleWord() throws IOException {
			return (_stream.readByte() & 0xFF) | ((_stream.readByte() & 0xFF) << 8) | ((_stream.readByte() & 0xFF) << 16) | ((_stream.readByte() & 0xFF) << 24);
		}
		
		public int readDoubleWord(String label) throws IOException {
			int val = readDoubleWord();
			
			log("dword", label, val);
			
			return val;
		}
		
		private int readUnsignedShort() throws IOException {
			return (_stream.readByte() & 0xFF) | ((_stream.readByte() & 0xFF) << 8);
		}
		
		public int readUnsignedShort(String label) throws IOException {
			int val = readUnsignedShort();
			
			log("ushort", label, val);
			
			return val;
		}
		
		private long readLong() throws IOException {
			return _stream.readLong();
		}
		
		public long readLong(String label) throws IOException {
			long val = readLong();
			
			log("long", label, val);
			
			return val;
		}
		
		private BigInteger readUnsignedLong() throws IOException {
			ByteBuffer buf = ByteBuffer.allocate(Long.BYTES);
			
			buf.order(ByteOrder.LITTLE_ENDIAN);
			
			buf.putLong(readLong());
			
			return new BigInteger(buf.array());
		}
		
		private BigInteger readUnsignedLong(String label) throws IOException {
			BigInteger val = readUnsignedLong();
			
			log("ulong", label, val, "%x");
			
			return val;
		}
		
		public void seek(int pos) throws IOException {
			_stream.seek(pos);
		}
		
		public void close() throws IOException {
			_stream.close();
		}
		
		public FileBinStream(File file) throws FileNotFoundException {
			_stream = new RandomAccessFile(new File("D:\\testmpq\\wow-0-17399-Win-final.mpq"), "r");
		}
	}
	
	private FileBinStream _stream;
	
	public class HashEntry {
		public int BLOCK_INDEX_EMPTY = 0xFFFFFFFF;
		public int BLOCK_INDEX_DELETED = 0xFFFFFFFE;
		
		private int _nameA;
		private int _nameB;
		
		private int _locale;
		
		private int _platform;
		
		private int _blockIndex;
		
		public HashEntry() throws IOException {
			int nameA = _stream.readDoubleWord();
			int nameB = _stream.readDoubleWord();
			
			int locale = _stream.readUnsignedShort();
			
			int platform = _stream.readUnsignedShort();
			
			int blockIndex = _stream.readDoubleWord();
		}
	}
	
	private void readHashEntry() throws IOException {
		HashEntry hashEntry = new HashEntry();
	}
	
	private void searchFile(String fileName) {
		int index = hashString(fileName, MPQ_HASH_TABLE_INDEX);
		int nameA = hashString(fileName, MPQ_HASH_NAME_A);
		int nameB = hashString(fileName, MPQ_HASH_NAME_B);
		
		long curHashIndex = _hashTablePos + (index & (_hashTableSize - 1));
		
		HashEntry curHash = null;
		
		while (true) {
			curHash = _hashes[curHasIndex];
			
			if ((curHash._nameA == nameA) && (curHash._nameB == nameB)) break;
			
			if (curHash._blockIndex == HashEntry.BLOCK_INDEX_EMPTY) {
				curHash = null;
				
				break;
			}
		}
		
		if (curHash == null) {
			//file not found
		} else {
			//file found
			int blockIndex = curHash._blockIndex;
		}
		
		return curHash;
	}
	
	private void readBlockEntry() {
		int filePos = _stream.readDoubleWord();
		
		int compressedSize = _stream.readDoubleWord();
		
		int uncompressedSize = _stream.readDoubleWord();
		
		int flags = _stream.readDoubleWord();
		
		
	}
	
	private void readBet() throws IOException {
		_stream.beginGroup("bet");
		
		int sign = _stream.readDoubleWord("sign");
		int version = _stream.readDoubleWord("version");
		int dataSize = _stream.readDoubleWord("dataSize");
		
		int tableSize = _stream.readDoubleWord("tableSize");
		int fileCount = _stream.readDoubleWord("fileCount");
		int unknown08 = _stream.readDoubleWord("unknown08");
		int tableEntrySize = _stream.readDoubleWord("tableEntrySize");
		
		_stream.beginGroup("bitIndex");
		
		int bitIndex_filePos = _stream.readDoubleWord("filePos");
		int bitIndex_fileSize = _stream.readDoubleWord("fileSize");
		int bitIndex_cmpSize = _stream.readDoubleWord("cmpSize");
		int bitIndex_flagIndex = _stream.readDoubleWord("flagIndex");
		int bitIndex_unknown = _stream.readDoubleWord("unknown");
		
		_stream.endGroup();

		_stream.beginGroup("bitCount");
		
		int bitCount_filePos = _stream.readDoubleWord("filePos");
		int bitCount_fileSize = _stream.readDoubleWord("fileSize");
		int bitCount_cmpSize = _stream.readDoubleWord("cmpSize");
		int bitCount_flagIndex = _stream.readDoubleWord("flagIndex");
		int bitCount_unknown = _stream.readDoubleWord("unknown");
		
		_stream.endGroup();
		
		int totalBetHashSize = _stream.readDoubleWord("totalBetHashSize");
		int betHashSizeExtra = _stream.readDoubleWord("betHashSizeExtra");
		int betHashSize = _stream.readDoubleWord("betHashSize");
		int betHashArraySize = _stream.readDoubleWord("betHashArraySize");
		int flagsCount = _stream.readDoubleWord("flagsCount");
		
		int[] flags = new int[flagsCount];
		
		for (int i = 0; i < flagsCount; i++) {
			flags[i] = _stream.readDoubleWord(String.format("flag%d", i));
		}
		
		_stream.endGroup();
	}
	
	private void readHet() throws IOException {
		_stream.beginGroup("het");
		
		int sign = _stream.readDoubleWord("sign");
		int version = _stream.readDoubleWord("version");
		int dataSize = _stream.readDoubleWord("dataSize");
		
		int tableSize = _stream.readDoubleWord("tableSize");
		int maxFileCount = _stream.readDoubleWord("maxFileCount");
		int hashTableSize = _stream.readDoubleWord("hashTableSize");
		int hashEntrySize = _stream.readDoubleWord("hashEntrySize");
		int totalIndexSize = _stream.readDoubleWord("totalIndexSize");
		int indexSizeExtra = _stream.readDoubleWord("indexSizeExtra");
		int indexSize = _stream.readDoubleWord("indexSize");
		int blockTableSize = _stream.readDoubleWord("blockTableSize");
		
		byte[] hetHashTable = _stream.read(hashTableSize);
		
		_stream.endGroup();
	}
	
	private void readHeader() throws IOException {
		byte type = _stream.read();
		
		if (type == 0x1A) {
			int headerSize = _stream.readDoubleWord("headerSize");
			
			int archiveSize = _stream.readDoubleWord("archiveSize");
			
			int formatVersion = _stream.readUnsignedShort("formatVersion");
			
			int blockSize = _stream.readUnsignedShort("blockSize");
			
			int hashTablePos = _stream.readDoubleWord("hashTablePos");
			
			int blockTablePos = _stream.readDoubleWord("blockTablePos");
			
			int hashTableSize = _stream.readDoubleWord("hashTableSize");
			
			int blockTableSize = _stream.readDoubleWord("blockTableSize");
			
			//v2
			BigInteger hiBlockTableOffset64 = _stream.readUnsignedLong("hiBlockTableOffset64");
			
			int hashTablePosHi = _stream.readUnsignedShort("hashTablePosHi");
			
			int blockTablePosHi = _stream.readUnsignedShort("blockTablePosHi");
			
			//v3
			BigInteger archiveSize64 = _stream.readUnsignedLong("archiveSize64");
			
			System.out.println(String.format("%x", archiveSize64));
			
			BigInteger betTablePos64 = _stream.readUnsignedLong("betTablePos64");
			
			BigInteger hetTablePos64 = _stream.readUnsignedLong("hetTablePos64");
			
			//v4
			BigInteger hashTableSize64 = _stream.readUnsignedLong("hashTableSize64");
			
			BigInteger blockTableSize64 = _stream.readUnsignedLong("blockTableSize64");
			
			BigInteger hiBlockTableSize64 = _stream.readUnsignedLong("hiBlockTableSize64");
			
			BigInteger hetTableSize64 = _stream.readUnsignedLong("hetTableSize64");
			
			BigInteger betTableSize64 = _stream.readUnsignedLong("betTableSize64");
			
			int rawChunkSize = _stream.readDoubleWord("rawChunkSize");
			
			/*
			 *     // Array of MD5's
    unsigned char MD5_BlockTable[MD5_DIGEST_SIZE];      // MD5 of the block table before decryption
    unsigned char MD5_HashTable[MD5_DIGEST_SIZE];       // MD5 of the hash table before decryption
    unsigned char MD5_HiBlockTable[MD5_DIGEST_SIZE];    // MD5 of the hi-block table
    unsigned char MD5_BetTable[MD5_DIGEST_SIZE];        // MD5 of the BET table before decryption
    unsigned char MD5_HetTable[MD5_DIGEST_SIZE];        // MD5 of the HET table before decryption
    unsigned char MD5_MpqHeader[MD5_DIGEST_SIZE];       // MD5 of the MPQ header from signature to (including) MD5_HetTable

			 * */
			
			int md5Size = 128 / 8;
			
			_stream.beginGroup("md5");

			byte[] md5BlockTable = _stream.read(md5Size);
			byte[] md5HashTable = _stream.read(md5Size);
			byte[] md5HiBlockTable = _stream.read(md5Size);
			byte[] md5BetTable = _stream.read(md5Size);
			byte[] md5HetTable = _stream.read(md5Size);
			byte[] md5MpqHeader = _stream.read(md5Size);

			_stream.endGroup();
			
			_stream.printLog();
		}
	}
	
	public MPQReader(InputStream inStream) throws IOException {
		//_stream = new RandomAccessFile(new File("D:\\testmpq\\war3.mpq"), "r");
		_stream = new FileBinStream(new File("D:\\testmpq\\wow-0-17399-Win-final.mpq"));
		
		for (int i = 0; ; i++) {
			_stream.seek(0x200 * i);
			
			byte[] b = _stream.read(3);
			
			if (new String(b).equals("MPQ")) {
				System.out.println(String.format("header at %x", 0x200*i));
				
				break;
			}
		}
		
		readHeader();
		
		_stream.close();
	}
}
