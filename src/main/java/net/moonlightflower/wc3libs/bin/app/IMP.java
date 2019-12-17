package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.Format;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * imports file for wrapping war3map.imp
 */
interface IMP_Streamable {
    void read(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException;

    void write(@Nonnull Wc3BinOutputStream stream);
}

public class IMP {
    public final static String GAME_PATH = "war3map.imp";
    public final static File CAMPAIGN_PATH = new File("war3campaign.imp");

    public static class StdFlag {
        private final static Map<Integer, StdFlag> _map = new LinkedHashMap<>();

        public final static StdFlag CUSTOM = new StdFlag(0xA, 0xD);
        public final static StdFlag STD = new StdFlag(0x0, 0x5, 0x8);

        int _val;

        public int getVal() {
            return _val;
        }

        public static StdFlag fromVal(int val) {
            return _map.get(val);
        }

        private StdFlag(int... vals) {
            for (int val : vals) {
                _val = val;

                _map.put(val, this);
            }
        }
    }

    public static class Obj implements IMP_Streamable {
        protected String _path;

        public String getPath() {
            return _path;
        }

        public void setPath(String val) {
            _path = val;
        }

        protected StdFlag _stdFlag = StdFlag.CUSTOM;

        public StdFlag getStdFlag() {
            return _stdFlag;
        }

        public void setStdFlag(StdFlag val) {
            _stdFlag = val;
        }

        private void merge(@Nonnull Obj other) {
            _path = other.getPath();
            _stdFlag = other.getStdFlag();
        }

        public void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
            Obj other = null;

            switch (format.toEnum()) {
                case IMP_0x1: {
                    other = new IMP_0x1.Obj(stream);

                    break;
                }
            }

            if (other != null) merge(other);
        }

        public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
            Obj other = null;

            switch (format.toEnum()) {
                case AUTO:
                case IMP_0x1: {
                    other = new IMP_0x1.Obj();

                    break;
                }
            }

            other.merge(this);

            other.write(stream);
        }

        @Override
        public void read(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
            read(stream, EncodingFormat.AUTO);
        }

        @Override
        public void write(@Nonnull Wc3BinOutputStream stream) {
            write(stream, EncodingFormat.AUTO);
        }

        public Obj(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
            read(stream, format);
        }

        public Obj() {
        }
    }

    protected List<Obj> _objs = new ArrayList<>();

    public List<Obj> getObjs() {
        return _objs;
    }

    public void addObj(@Nonnull Obj val) {
        _objs.add(val);
    }

    @Nonnull
    public Obj addObj() {
        Obj obj = new Obj();

        addObj(obj);

        return obj;
    }

    public void merge(@Nonnull IMP other) {
        for (Obj obj : other.getObjs()) {
            addObj(obj);
        }
    }

    public static class EncodingFormat extends Format<EncodingFormat.Enum> {
        public enum Enum {
            AUTO,
            IMP_0x1,
        }

        public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
        public final static EncodingFormat IMP_0x1 = new EncodingFormat(Enum.IMP_0x1, 0x1);

        @Nullable
        public static EncodingFormat valueOf(@Nonnull Integer version) {
            return get(EncodingFormat.class, version);
        }

        private EncodingFormat(@Nonnull Enum enumVal, int version) {
            super(enumVal, version);
        }
    }

    private void read_auto(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        int version = stream.readInt32("version");

        stream.rewind();

        read(stream, stream.getFormat(EncodingFormat.class, version));
    }

    private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
        IMP other = null;

        switch (format.toEnum()) {
            case AUTO: {
                read_auto(stream);

                break;
            }
            case IMP_0x1: {
                other = new IMP_0x1(stream);

                break;
            }
        }

        if (other != null) merge(other);
    }

    private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
        IMP other = null;

        switch (format.toEnum()) {
            case AUTO:
            case IMP_0x1: {
                other = new IMP_0x1();

                break;
            }
        }

        other.merge(this);

        other.write(stream);
    }

    private void read(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        read(stream, EncodingFormat.AUTO);
    }

    public void write(@Nonnull Wc3BinOutputStream stream) {
        write(stream, EncodingFormat.AUTO);
    }

    public void write(@Nonnull File file) throws IOException {
        Wc3BinOutputStream stream = new Wc3BinOutputStream(file);

        write(stream);

        stream.close();
    }

    public IMP(@Nonnull File file) throws IOException {
        this(new Wc3BinInputStream(file));
    }

    public IMP(Wc3BinInputStream inputStream) throws IOException {
        read(inputStream);

        inputStream.close();
    }

    public IMP() {
    }
}
