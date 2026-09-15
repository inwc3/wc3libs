package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.*;

import javax.annotation.Nonnull;
import java.io.*;

/**
 * gamecache file
 */
public class W3V extends W3VUncompressed {
	public void read_0x0(Wc3BinInputStream stream) throws BinInputStream.StreamException {
        super.read_0x0(ZCompression.decompress(stream));
	}

    public void read_0xFF(Wc3BinInputStream stream) throws BinInputStream.StreamException {
        super.read_0xFF(ZCompression.decompress(stream));
    }

	public void write_0x0(Wc3BinOutputStream stream) throws BinStream.StreamException {
        Wc3BinOutputStream uncompressedStream = new Wc3BinOutputStream(new ByteArrayOutputStream());
        super.write_0x0(uncompressedStream);
        stream.writeBytes(ZCompression.compress(uncompressedStream.getBytes()));
	}

    public void write_0xFF(Wc3BinOutputStream stream) throws BinStream.StreamException {
        Wc3BinOutputStream uncompressedStream = new Wc3BinOutputStream(new ByteArrayOutputStream());
        super.write_0xFF(uncompressedStream);
        stream.writeBytes(ZCompression.compress(uncompressedStream.getBytes()));
    }

    private void write_as_defined(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        switch (_format.toEnum()) {
            case W3V_0x0: {
                write_0x0(stream);

                break;
            }
            case W3V_0xFF: {
                write_0xFF(stream);

                break;
            }
        }
    }

	private void read_as_defined(Wc3BinInputStream stream) throws BinInputStream.StreamException {
        Wc3BinInputStream uncompressedStream = ZCompression.decompress(stream);

        stream.rewind();

		int version = uncompressedStream.readInt32("version");

        EncodingFormat format = EncodingFormat.valueOf(version);

        if (format == null) throw new IllegalArgumentException("unknown format " + version);

		read(stream, format);
	}

	public void read(Wc3BinInputStream stream, EncodingFormat format) throws BinInputStream.StreamException {
		switch (format.toEnum()) {
        case AUTO:
        case AS_DEFINED: {
            read_as_defined(stream);

            break;
        }
        case W3V_0xFF:
            read_0xFF(stream);

            break;
		case W3V_0x0: {
			read_0x0(stream);

			break;
		}
		}
	}

	public void write(Wc3BinOutputStream stream, EncodingFormat format) throws BinStream.StreamException {
		switch (format.toEnum()) {
        case AS_DEFINED: {
            write_as_defined(stream);

            break;
        }
        case AUTO:
        case W3V_0xFF:
            write_0xFF(stream);

            break;
		case W3V_0x0: {
			write_0x0(stream);

			break;
		}
		}
	}

	public void read(Wc3BinInputStream stream) throws BinInputStream.StreamException {
		read(stream, EncodingFormat.AUTO);
	}

	public void write(Wc3BinOutputStream stream) throws BinOutputStream.StreamException {
		write(stream, EncodingFormat.AUTO);
	}

	public void read(File file, EncodingFormat format) throws IOException {
		read(new Wc3BinInputStream(file), format);
	}

	public void write(File file, EncodingFormat format) throws IOException {
		try (Wc3BinOutputStream stream = new Wc3BinOutputStream(file)) {
			write(stream, format);
		}
	}

	public void read(File file) throws IOException {
		read(file, EncodingFormat.AUTO);
	}

	public void write(File file) throws IOException {
		try (Wc3BinOutputStream stream = new Wc3BinOutputStream(file)) {
			write(stream);
		}
	}

    public W3V(@Nonnull Wc3BinInputStream stream) throws Exception {
        read(stream);
    }

	public W3V(File file) throws IOException {
		read(file);
	}

    public W3V() {
    }
}
