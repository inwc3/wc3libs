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
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * custom text triggers file for wrapping war3map.wct
 */
public class WCT {
	public final static File GAME_PATH = new File("war3map.wct");
	
	public static class Trig {
		private String _text;
		
		public String getText() {
			return _text;
		}
		
		public void setText(@Nullable String val) {
			_text = val;
		}

		public void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			int size = stream.readInt32("length");

			if (size > 0) {
				setText(new String(stream.readBytes(size, "text"), StandardCharsets.UTF_8));
			}
		}
		
		public void write_0x0(@Nonnull Wc3BinOutputStream stream) {
			byte[] textBytes = stream.stringToByteArray(_text);

			if (textBytes == null) {
				stream.writeInt32(0);
			} else {
				textBytes = Arrays.copyOf(textBytes, textBytes.length);

				stream.writeInt32(textBytes.length);
				stream.writeBytes(textBytes);
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
	
	private final List<Trig> _trigs = new ArrayList<>();

	@Nonnull
	public List<Trig> getTrigs() {
		return new ArrayList<>(_trigs);
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
	
	public void setHeadComment(@Nullable String val) {
		_headComment = val;
	}
	
	private Trig _headTrig;
	
	public void setHeadTrig(@Nullable Trig trig) {
		_headTrig = trig;
	}
	
	public static class EncodingFormat extends Format<EncodingFormat.Enum> {
		public enum Enum {
			AUTO,
			WCT_0x0,
			WCT_0x1,
		}
		
		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat WCT_0x0 = new EncodingFormat(Enum.WCT_0x0, 0x0);
		public final static EncodingFormat WCT_0x1 = new EncodingFormat(Enum.WCT_0x1, 0x1);

		@Nullable
		public static EncodingFormat valueOf(@Nonnull Integer version) {
			return get(EncodingFormat.class, version);
		}
		
		private EncodingFormat(@Nonnull Enum enumVal, int version) {
			super(enumVal, version);
		}
	}
	
	public void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int version = stream.readInt32();
		
		stream.checkFormatVersion(EncodingFormat.WCT_0x0.getVersion(), version);

		int trigsCount = stream.readInt32();

		for (int i = 1; i <= trigsCount; i++) {
			addTrig(new Trig(stream, EncodingFormat.WCT_0x0));
		}
	}
	
	public void write_0x0(@Nonnull Wc3BinOutputStream stream) {
		stream.writeInt32(EncodingFormat.WCT_0x0.getVersion());

		stream.writeInt32(_trigs.size());

		for (Trig trig : _trigs) {
			trig.write(stream, EncodingFormat.WCT_0x0);
		}
	}

	public void read_0x1(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int version = stream.readInt32("version");
		
		stream.checkFormatVersion(EncodingFormat.WCT_0x1.getVersion(), version);
		
		_headComment = stream.readString("headComment");
		
		_headTrig = new Trig(stream, EncodingFormat.WCT_0x1);

		int trigsCount = stream.readInt32("trigsCount");

		for (int i = 1; i <= trigsCount; i++) {
			addTrig(new Trig(stream, EncodingFormat.WCT_0x1));
		}
	}

	public void write_0x1(@Nonnull Wc3BinOutputStream stream) {
		stream.writeInt32(EncodingFormat.WCT_0x1.getVersion());
		
		stream.writeString(_headComment);
		
		_headTrig.write(stream, EncodingFormat.WCT_0x1);

		stream.writeInt32(_trigs.size());

		for (Trig trig : _trigs) {
			trig.write(stream, EncodingFormat.WCT_0x1);
		}
	}
	
	private void read_auto(@Nonnull Wc3BinInputStream stream) throws Exception {
		int version = stream.readInt32();
		
		stream.rewind();

		read(stream, stream.getFormat(EncodingFormat.class, version));
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

	public void write(@Nonnull Wc3BinOutputStream stream) {
		write(stream, EncodingFormat.AUTO);
	}

	public void write(@Nonnull File file) throws IOException {
		Wc3BinOutputStream outStream = new Wc3BinOutputStream(file);

		write(outStream);

		outStream.close();
	}

	public WCT(@Nonnull Wc3BinInputStream stream) throws Exception {
		read(stream);
	}

	public WCT(@Nonnull File file) throws Exception {
		Wc3BinInputStream inStream = new Wc3BinInputStream(file);

		read(inStream);

		inStream.close();
	}

	public WCT() {
	}

	@Nullable
	public static WCT ofMapFile(@Nonnull File mapFile) throws Exception {
		if (!mapFile.exists()) throw new IOException(String.format("file %s does not exist", mapFile));
		
		MpqPort.Out port = new JMpqPort.Out();
		
		port.add(GAME_PATH);
		
		MpqPort.Out.Result portResult = port.commit(mapFile);

		if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract wct file");

		InputStream inStream = portResult.getInputStream(GAME_PATH);

		if (inStream == null) return null;

		Wc3BinInputStream stream = new Wc3BinInputStream(inStream);

		WCT wct = new WCT();

		wct.read(stream);

		stream.close();

		return wct;
	}
}
