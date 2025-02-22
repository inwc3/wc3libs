package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.*;
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

    private EncodingFormat _format;

    public EncodingFormat getFormat() {
        return _format;
    }

    public void setFormat(@Nonnull EncodingFormat format) {
        this._format = format;
    }

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
            case WCT_0x80000004:
			case WCT_0x1:
			case WCT_0x0: {
				read_0x0(stream);
			}
			}
		}
		
		public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
            case WCT_0x80000004:
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

    private int _useHeader;

    public void setUseHeader(int val) {
        _useHeader = val;
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
            AS_DEFINED,
			WCT_0x0,
			WCT_0x1,
            WCT_0x80000004
		}

        private final static Map<Integer, EncodingFormat> _map = new LinkedHashMap<>();

		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
        public final static EncodingFormat AS_DEFINED = new EncodingFormat(Enum.AS_DEFINED, null);
		public final static EncodingFormat WCT_0x0 = new EncodingFormat(Enum.WCT_0x0, 0x0);
		public final static EncodingFormat WCT_0x1 = new EncodingFormat(Enum.WCT_0x1, 0x1);
        public final static EncodingFormat WCT_0x80000004 = new EncodingFormat(Enum.WCT_0x80000004, 0x80000004);

		@Nullable
		public static EncodingFormat valueOf(int version) {
            return _map.get(version);
		}
		
		private EncodingFormat(@Nonnull Enum enumVal, @Nullable Integer version) {
			super(enumVal, version);

            if (version != null) {
                _map.put(version, this);
            }
		}
	}
	
	public void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int version = stream.readInt32();
		
		stream.checkFormatVersion(EncodingFormat.WCT_0x0.getVersion(), version);

        _format = EncodingFormat.valueOf(version);

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

        _format = EncodingFormat.valueOf(version);

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

    public void read_0x80000004(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        int version = stream.readInt32("version");

        stream.checkFormatVersion(EncodingFormat.WCT_0x80000004.getVersion(), version);

        _format = EncodingFormat.valueOf(version);

        _useHeader = stream.readInt32("useHeader");

        if (_useHeader != 0) {
            _headComment = stream.readString("headComment");

            _headTrig = new Trig(stream, EncodingFormat.WCT_0x80000004);
        }

        while (!stream.eof()) {
            addTrig(new Trig(stream, EncodingFormat.WCT_0x80000004));
        }
    }

    public void write_0x80000004(@Nonnull Wc3BinOutputStream stream) {
        stream.writeInt32(EncodingFormat.WCT_0x80000004.getVersion());

        stream.writeInt32(_useHeader);

        if (_useHeader != 0) {
            stream.writeString(_headComment);

            _headTrig.write(stream, EncodingFormat.WCT_0x80000004);
        }

        for (Trig trig : _trigs) {
            trig.write(stream, EncodingFormat.WCT_0x80000004);
        }
    }

    private void write_as_defined(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        switch (_format.toEnum()) {
            case WCT_0x0: {
                write_0x0(stream);

                break;
            }
            case WCT_0x1: {
                write_0x1(stream);

                break;
            }
            case WCT_0x80000004: {
                write_0x80000004(stream);

                break;
            }
        }
    }

	private void read_as_defined(@Nonnull Wc3BinInputStream stream) throws Exception {
		int version = stream.readInt32();
		
		stream.rewind();

        EncodingFormat format = EncodingFormat.valueOf(version);

        if (format == null) throw new IllegalArgumentException("unknown format " + version);

		read(stream, format);
	}

    public void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws Exception {
		switch (format.toEnum()) {
		case AUTO:
        case AS_DEFINED: {
            read_as_defined(stream);

            break;
        }
        case WCT_0x80000004: {
            read_0x80000004(stream);

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
	
	public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
		switch (format.toEnum()) {
        case AS_DEFINED: {
            write_as_defined(stream);

            break;
        }
		case AUTO:
        case WCT_0x80000004: {
            write_0x80000004(stream);

            break;
        }
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

    public void read(@Nonnull Wc3BinInputStream stream) throws Exception {
		read(stream, EncodingFormat.AUTO);
	}

	public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
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
