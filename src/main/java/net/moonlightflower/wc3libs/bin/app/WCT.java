package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.Format;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * custom text triggers file for wrapping war3map.wct
 */
public class WCT {
	public final static File GAME_PATH = new File("war3map.wct");
	
	public class Trig {
		private String _text;
		
		public String getText() {
			return _text;
		}
		
		public void setText(String val) {
			_text = val;
		}

		public void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			int size = stream.readInt32();
			
			if (size > 0) {
				setText(stream.readString());
			}
		}
		
		public void write_0x0(@Nonnull Wc3BinOutputStream stream) {
			int textLen = stream.stringToByteArray(getText()).length;

			if (textLen > 0) {
				stream.writeInt(textLen + 1);
				
				stream.writeString(getText());
			} else {
				stream.writeInt(0);
			}
		}
		
		public void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			switch (format.toEnum()) {
			case WCT_0x1:
			case WCT_0x0: {
				read_0x0(stream);
			}
			}
		}
		
		public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case WCT_0x1:
			case WCT_0x0: {
				write_0x0(stream);
			}
			}
		}
		
		public Trig(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			read(stream, format);
		}
		
		public Trig() {
		}
	}
	
	private List<Trig> _trigs = new ArrayList<>();

	@Nonnull
	public List<Trig> getTrigs() {
		return _trigs;
	}
	
	private void addTrig(@Nonnull Trig val) {
		_trigs.add(val);
	}

	@Nonnull
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
	
	public static class EncodingFormat extends Format<EncodingFormat.Enum> {
		public enum Enum {
			AUTO,
			WCT_0x0,
			WCT_0x1,
		}

		private static Map<Integer, EncodingFormat> _map = new LinkedHashMap<>();
		
		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat WCT_0x0 = new EncodingFormat(Enum.WCT_0x0, 0x0);
		public final static EncodingFormat WCT_0x1 = new EncodingFormat(Enum.WCT_0x1, 0x1);

		@Nullable
		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}
		
		private EncodingFormat(@Nonnull Enum enumVal, int version) {
			super(enumVal, version);
			
			_map.put(version, this);
		}
	}
	
	public void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int format = stream.readInt32();
		
		Wc3BinInputStream.checkFormatVer("wctMaskFunc", EncodingFormat.WCT_0x0.getVersion(), format);

		int trigsCount = stream.readInt32();

		for (int i = 1; i <= trigsCount; i++) {
			addTrig(new Trig(stream, EncodingFormat.WCT_0x0));
		}
	}
	
	public void write_0x0(@Nonnull Wc3BinOutputStream stream) {
		stream.writeInt(EncodingFormat.WCT_0x0.getVersion());
		
		for (Trig trig : _trigs) {
			trig.write(stream, EncodingFormat.WCT_0x0);
		}
	}

	public void read_0x1(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int version = stream.readInt32();
		
		Wc3BinInputStream.checkFormatVer("wctMaskFunc", EncodingFormat.WCT_0x1.getVersion(), version);
		
		_headComment = stream.readString();
		
		_headTrig = new Trig(stream, EncodingFormat.WCT_0x1);

		int trigsCount = stream.readInt32();

		for (int i = 1; i <= trigsCount; i++) {
			addTrig(new Trig(stream, EncodingFormat.WCT_0x1));
		}
	}

	public void write_0x1(@Nonnull Wc3BinOutputStream stream) {
		stream.writeInt(EncodingFormat.WCT_0x1.getVersion());
		
		stream.writeString(_headComment);
		
		_headTrig.write(stream, EncodingFormat.WCT_0x1);
		
		for (Trig trig : _trigs) {
			trig.write(stream, EncodingFormat.WCT_0x1);
		}
	}
	
	private void read_auto(@Nonnull Wc3BinInputStream stream) throws Exception {
		int version = stream.readInt32();
		
		stream.rewind();

		EncodingFormat format = EncodingFormat.valueOf(version);

		if (format == null) throw new Exception(String.format("unknown format %x", version));

		read(stream, format);
	}
	
	private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws Exception {
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
	
	private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
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
	
	private void read(@Nonnull Wc3BinInputStream stream) throws Exception {
		read(stream, EncodingFormat.AUTO);
	}
	
	private void write(@Nonnull Wc3BinOutputStream stream) {
		write(stream, EncodingFormat.AUTO);
	}

	private void write(@Nonnull File file) throws IOException {
		Wc3BinOutputStream outStream = new Wc3BinOutputStream(file);

		write(outStream);

		outStream.close();
	}
	
	public WCT(@Nonnull File file) throws Exception {
		Wc3BinInputStream inStream = new Wc3BinInputStream(file);

		read(inStream);

		inStream.close();
	}

	public WCT() {
	}

	public static WCT ofMapFile(File mapFile) throws Exception {
		if (!mapFile.exists()) throw new IOException(String.format("file %s does not exist", mapFile));
		
		MpqPort.Out port = new JMpqPort.Out();
		
		port.add(GAME_PATH);
		
		MpqPort.Out.Result portResult = port.commit(mapFile);

		if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract wct file");

		Wc3BinInputStream inStream = new Wc3BinInputStream(portResult.getInputStream(GAME_PATH));

		WCT wct = new WCT();

		wct.read(inStream);

		inStream.close();

		return wct;
	}
}
