package net.moonlightflower.wc3libs.bin.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.moonlightflower.wc3libs.bin.Format;
import net.moonlightflower.wc3libs.bin.Wc3bin;
import net.moonlightflower.wc3libs.bin.Wc3bin.Stream;
import net.moonlightflower.wc3libs.bin.Wc3bin.StreamException;

/**
 * custom text triggers file for wrapping war3map.wct
 */
public class WCT {
	public final static String GAME_PATH = "war3map.wct";
	
	public class Trig {
		private String _text;
		
		public String getText() {
			return _text;
		}
		
		public void setText(String val) {
			_text = val;
		}

		public void read_0x0(Wc3bin.Stream stream) throws StreamException {
			int size = stream.readInt();
			
			if (size > 0) {
				setText(stream.readString());
			}
		}
		
		public void write_0x0(Wc3bin.Stream stream) {
			int textLen = stream.stringToByteArray(getText()).length;

			if (textLen > 0) {
				stream.writeInt(textLen + 1);
				
				stream.writeString(getText());
			} else {
				stream.writeInt(0);
			}
		}
		
		public void read(Stream stream, EncodingFormat format) throws StreamException {
			switch (format.toEnum()) {
			case WCT_0x1:
			case WCT_0x0: {
				read_0x0(stream);
			}
			}
		}
		
		public void write(Stream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case WCT_0x1:
			case WCT_0x0: {
				write_0x0(stream);
			}
			}
		}
		
		public Trig(Stream stream, EncodingFormat format) throws StreamException {
			read(stream, format);
		}
		
		public Trig() {
		}
	}
	
	private ArrayList<Trig> _trigs = new ArrayList<>();
	
	private void addTrig(Trig val) {
		_trigs.add(val);
	}
	
	public Trig addTrig() {
		Trig trig = new Trig();

		addTrig(trig);
		
		return trig;
	}
	
	private String _headComment;
	
	public void setHeadComment(String val) {
		_headComment = val;
	}
	
	private Trig _headTrig;
	
	public void setHeadTrig(Trig trig) {
		_headTrig = trig;
	}
	
	private static class EncodingFormat extends Format<EncodingFormat.Enum> {
		enum Enum {
			AUTO,
			WCT_0x0,
			WCT_0x1,
		}

		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat WCT_0x0 = new EncodingFormat(Enum.WCT_0x0, 0x0);
		public final static EncodingFormat WCT_0x1 = new EncodingFormat(Enum.WCT_0x1, 0x1);

		private static Map<Integer, EncodingFormat> _map = new HashMap<>();
		
		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}
		
		private EncodingFormat(Enum enumVal, int version) {
			super(enumVal, version);
			
			_map.put(version, this);
		}
	}
	
	public void read_0x0(Wc3bin.Stream stream) throws StreamException {
		int format = stream.readInt();
		
		Wc3bin.checkFormatVer("wctMaskFunc", EncodingFormat.WCT_0x0.getVersion(), format);

		int trigsCount = stream.readInt();

		for (int i = 1; i <= trigsCount; i++) {
			addTrig(new Trig(stream, EncodingFormat.WCT_0x0));
		}
	}
	
	public void write_0x0(Wc3bin.Stream stream) {
		stream.writeInt(EncodingFormat.WCT_0x0.getVersion());
		
		for (Trig trig : _trigs) {
			trig.write(stream, EncodingFormat.WCT_0x0);
		}
	}

	public void read_0x1(Wc3bin.Stream stream) throws StreamException {
		int version = stream.readInt();
		
		Wc3bin.checkFormatVer("wctMaskFunc", EncodingFormat.WCT_0x1.getVersion(), version);
		
		_headComment = stream.readString();
		
		_headTrig = new Trig(stream, EncodingFormat.WCT_0x1);

		int trigsCount = stream.readInt();

		for (int i = 1; i <= trigsCount; i++) {
			addTrig(new Trig(stream, EncodingFormat.WCT_0x1));
		}
	}

	public void write_0x1(Wc3bin.Stream stream) {
		stream.writeInt(EncodingFormat.WCT_0x1.getVersion());
		
		stream.writeString(_headComment);
		
		_headTrig.write(stream, EncodingFormat.WCT_0x1);
		
		for (Trig trig : _trigs) {
			trig.write(stream, EncodingFormat.WCT_0x1);
		}
	}
	
	private void read_auto(Stream stream) throws StreamException {
		int version = stream.readInt();
		
		stream.rewind();

		read(stream, EncodingFormat.valueOf(version));
	}
	
	private void read(Stream stream, EncodingFormat format) throws StreamException {		
		switch (format.toEnum()) {
		case AUTO: {
			read_auto(stream);
			
			break;
		}
		case WCT_0x1: {
			read_0x1(stream);
			
			break;
		}
		case WCT_0x0: {
			read_0x0(stream);
			
			break;
		}
		}
	}
	
	private void write(Stream stream, EncodingFormat format) {
		switch (format.toEnum()) {
		case AUTO:
		case WCT_0x1: {
			write_0x1(stream);
			
			break;
		}
		case WCT_0x0: {
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

	public WCT(Stream stream) throws StreamException {
		read(stream);
	}
	
	public WCT(File file) throws IOException {
		this(new Wc3bin.Stream(file));
	}

	public WCT() {
	}
}
