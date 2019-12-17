package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * sounds file for wrapping war3map.W3S
 */
public class W3S {
	public final static File GAME_PATH = new File("war3map.W3S");

	public static class Sound extends Bin {
		public static class State<T extends DataType> extends BinState<T> {
			private final static List<State> _values = new ArrayList<>();

			public static List<State> values() {
				return new ArrayList<>(_values);
			}

			public State(@Nonnull DataTypeInfo typeInfo, @Nonnull String idString, T defVal) {
				super(idString, typeInfo, defVal);

				_values.add(this);
			}

			public State(@Nonnull DataTypeInfo typeInfo, @Nonnull String idString) {
				this(typeInfo, idString, null);
			}

			public State(@Nonnull Class<T> type, @Nonnull String idString) {
				this(new DataTypeInfo(type), idString);
			}

			public State(@Nonnull Class<T> type, @Nonnull String idString, T defVal) {
				this(new DataTypeInfo(type), idString, defVal);
			}
		}

		public enum Eax {
			DEFAULT_ON("DefaultEAXON", "Default"),
			COMBAT("CombatSoundsEAX", "combat"),
			KOTO_DRUMS("KotoDrumsEAX", "drums"),
			SPELLS("SpellsEAX", "spells"),
			MISSILES("MissilesEAX", "missiles"),
			HERO_SPEECH("HeroAcksEAX", "hero speech"),
			Doodads("DoodadsEAX", "doodads");

			private String _key;

			public String getKey() {
				return _key;
			}

			private String _description;

			public String getDescription() {
				return _description;
			}

			Eax(@Nonnull String key, @Nonnull String description) {
				_key = key;
				_description = description;
			}
		}

		public enum Flag {
			LOOPING(0x1),
			USE_3D(0x2),
			STOP_WHEN_OUT_OF_RANGE(0x4),
			MUSIC(0x8),
			UNKNOWN(0x10);

			private int _index;

			public int getIndex() {
				return _index;
			}

			Flag(int index) {
				_index = index;
			}
		}

		public enum Channel {
			GENERAL(0),
			UNIT_SELECTION(1),
			UNIT_ACKNOWLEDGEMENT(2),
			UNIT_MOVEMENT(3),
			UNIT_READY(4),
			COMBAT(5),
			ERROR(6),
			MUSIC(7),
			USER_INTERFACE(8),
			LOOPING_MOVEMENT(9),
			LOOPING_AMBIENT(10),
			ANIMATION(11),
			CONSTRUCTION(12),
			BIRTH(13),
			FIRE(14);

			private int _index;

			public int getIndex() {
				return _index;
			}

			Channel(int index) {
				_index = index;
			}
		}

		public final static State<War3String> DATA_FILE_PATH = new State<>(War3String.class, "filePath");
		public final static State<War3String> DATA_EAX = new State<>(War3String.class, "eax");
		public final static State<War3Int> DATA_FLAGS = new State<>(War3Int.class, "flags");
		public final static State<War3Int> DATA_FADE_IN = new State<>(War3Int.class, "fadeIn");
		public final static State<War3Int> DATA_FADE_OUT = new State<>(War3Int.class, "fadeOut");
		public final static State<War3Int> DATA_VOLUME = new State<>(War3Int.class, "volume");
		public final static State<War3Real> DATA_PITCH = new State<>(War3Real.class, "pitch");
		public final static State<War3Real> DATA_UNKNOWN_A = new State<>(War3Real.class, "unknownA");
		public final static State<War3Int> DATA_UNKNOWN_B = new State<>(War3Int.class, "unknownB");
		public final static State<War3Int> DATA_CHANNEL = new State<>(War3Int.class, "channel");
		public final static State<War3Real> DATA_DIST_MIN = new State<>(War3Real.class, "distMin");
		public final static State<War3Real> DATA_DIST_MAX = new State<>(War3Real.class, "distMax");
		public final static State<War3Real> DATA_DIST_CUTOFF = new State<>(War3Real.class, "cutoff");
		public final static State<War3Real> DATA_UNKNOWN_C = new State<>(War3Real.class, "unknownC");
		public final static State<War3Real> DATA_UNKNOWN_D = new State<>(War3Real.class, "unknownD");
		public final static State<War3Int> DATA_UNKNOWN_E = new State<>(War3Int.class, "unknownE");
		public final static State<War3Real> DATA_UNKNOWN_F = new State<>(War3Real.class, "unknownF");
		public final static State<War3Real> DATA_UNKNOWN_G = new State<>(War3Real.class, "unknownG");
        public final static State<War3Real> DATA_UNKNOWN_H = new State<>(War3Real.class, "unknownH");

		public final static State<War3String> TEXT_VAR_NAME = new State<>(War3String.class, "varName", War3String.valueOf("unnamed"));

		public <T extends DataType> T get(State<T> state) {
			try {
				return state.tryCastVal(super.get(state));
			} catch (DataTypeInfo.CastException ignored) {
			}

			return null;
		}

		public <T extends DataType> void set(State<T> state, T val) {
			super.set(state, val);
		}

		public <T extends DataType> void reset(State<T> state) {
			super.set(state, state.getDefVal());
		}

		public void init() {
			for (State<?> state : State.values()) {
				set(state, state.getDefVal());
			}
		}

		public Float FLOAT_DEFAULT = ByteBuffer.wrap(new byte[]{0x4F, (byte) 0x80, 0x00, 0x00}).order(ByteOrder.LITTLE_ENDIAN).getFloat();

		private Function<Float, War3Real> FLOAT_READ_FUNC = val -> {
			if (val.equals(FLOAT_DEFAULT)) return null;

			return War3Real.valueOf(val);
		};
		private Function<War3Real, Float> FLOAT_WRITE_FUNC = val -> {
			if (val == null) return FLOAT_DEFAULT;

			return val.getVal();
		};

		public final static Integer VOLUME_DEFAULT = -1;

		private Function<Integer, War3Int> VOLUME_READ_FUNC = val -> {
			if (val.equals(VOLUME_DEFAULT)) return null;

			return War3Int.valueOf(val);
		};
		private Function<War3Int, Integer> VOLUME_WRITE_FUNC = val -> {
			if (val == null) return VOLUME_DEFAULT;

			return val.getVal();
		};

		public void read_0x1(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			set(TEXT_VAR_NAME, War3String.valueOf(stream.readString("varName")));
			set(DATA_FILE_PATH, War3String.valueOf(stream.readString("filePath")));
			set(DATA_EAX, War3String.valueOf(stream.readString("eax")));
			set(DATA_FLAGS, War3Int.valueOf(stream.readInt32("flags")));
			set(DATA_FADE_IN, War3Int.valueOf(stream.readInt32("fadeIn")));
			set(DATA_FADE_OUT, War3Int.valueOf(stream.readInt32("fadeOut")));
			set(DATA_VOLUME, VOLUME_READ_FUNC.apply(stream.readInt32("volume")));
			set(DATA_PITCH, FLOAT_READ_FUNC.apply(stream.readFloat32("pitch")));
			set(DATA_UNKNOWN_A, FLOAT_READ_FUNC.apply(stream.readFloat32("unknownA")));
			set(DATA_UNKNOWN_B, War3Int.valueOf(stream.readInt32("unknownB")));
			set(DATA_CHANNEL, War3Int.valueOf(stream.readInt32("channel")));
			set(DATA_DIST_MIN, FLOAT_READ_FUNC.apply(stream.readFloat32("distMin")));
			set(DATA_DIST_MAX, FLOAT_READ_FUNC.apply(stream.readFloat32("distMax")));
			set(DATA_DIST_CUTOFF, FLOAT_READ_FUNC.apply(stream.readFloat32("distCutoff")));
			set(DATA_UNKNOWN_C, FLOAT_READ_FUNC.apply(stream.readFloat32("unknownC")));
			set(DATA_UNKNOWN_D, FLOAT_READ_FUNC.apply(stream.readFloat32("unknownD")));
			set(DATA_UNKNOWN_E, War3Int.valueOf(stream.readInt32("unknownE")));
			set(DATA_UNKNOWN_F, FLOAT_READ_FUNC.apply(stream.readFloat32("unknownF")));
			set(DATA_UNKNOWN_G, FLOAT_READ_FUNC.apply(stream.readFloat32("unknownG")));
            set(DATA_UNKNOWN_H, FLOAT_READ_FUNC.apply(stream.readFloat32("unknownH")));
		}

		public void write_0x1(@Nonnull Wc3BinOutputStream stream) {
			stream.writeString(get(TEXT_VAR_NAME));
			stream.writeString(get(DATA_FILE_PATH));
			stream.writeString(get(DATA_EAX));
			stream.writeInt32(get(DATA_FLAGS));
			stream.writeInt32(get(DATA_FADE_IN));
			stream.writeInt32(get(DATA_FADE_OUT));
			stream.writeInt32(VOLUME_WRITE_FUNC.apply(get(DATA_VOLUME)));
			stream.writeFloat32(FLOAT_WRITE_FUNC.apply(get(DATA_PITCH)));
			stream.writeFloat32(FLOAT_WRITE_FUNC.apply(get(DATA_UNKNOWN_A)));
			stream.writeInt32(get(DATA_UNKNOWN_B));
			stream.writeInt32(get(DATA_CHANNEL));
			stream.writeFloat32(FLOAT_WRITE_FUNC.apply(get(DATA_DIST_MIN)));
			stream.writeFloat32(FLOAT_WRITE_FUNC.apply(get(DATA_DIST_MAX)));
			stream.writeFloat32(FLOAT_WRITE_FUNC.apply(get(DATA_DIST_CUTOFF)));
			stream.writeFloat32(FLOAT_WRITE_FUNC.apply(get(DATA_UNKNOWN_C)));
			stream.writeFloat32(FLOAT_WRITE_FUNC.apply(get(DATA_UNKNOWN_D)));
			stream.writeInt32(get(DATA_UNKNOWN_E));
			stream.writeFloat32(FLOAT_WRITE_FUNC.apply(get(DATA_UNKNOWN_F)));
			stream.writeFloat32(FLOAT_WRITE_FUNC.apply(get(DATA_UNKNOWN_G)));
            stream.writeFloat32(FLOAT_WRITE_FUNC.apply(get(DATA_UNKNOWN_H)));
		}

		public void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			switch (format.toEnum()) {
				case W3S_0x1:
				read_0x1(stream);

				break;
			}
		}

		public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case W3S_0x1:
				write_0x1(stream);

				break;
			}
		}

		public Sound(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			this();

			read(stream, format);
		}

		public Sound() {
			init();
		}
	}

	private List<Sound> _sounds = new ArrayList<>();

	@Nonnull
	public List<Sound> getSounds() {
		return new ArrayList<>(_sounds);
	}

	private void addSound(@Nonnull Sound val) {
		_sounds.add(val);
	}

	@Nonnull
	public Sound addSound() {
		Sound sound = new Sound();

		addSound(sound);

		return sound;
	}

	public static class EncodingFormat extends Format<EncodingFormat.Enum> {
		public enum Enum {
			AUTO,
			W3S_0x1
		}

		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat W3S_0x1 = new EncodingFormat(Enum.W3S_0x1, 0x1);

		@Nullable
		public static EncodingFormat valueOf(@Nonnull Integer version) {
			return get(EncodingFormat.class, version);
		}

		private EncodingFormat(@Nonnull Enum enumVal, int version) {
			super(enumVal, version);
		}
	}

	public void read_0x1(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int version = stream.readInt32("version");

		stream.checkFormatVersion(EncodingFormat.W3S_0x1.getVersion(), version);

		int soundsCount = stream.readInt32("soundsCount");

		for (int i = 0; i < soundsCount; i++) {
			addSound(new Sound(stream, EncodingFormat.W3S_0x1));
		}
	}

	public void write_0x1(@Nonnull Wc3BinOutputStream stream) {
		stream.writeInt32(EncodingFormat.W3S_0x1.getVersion());

		stream.writeInt32(_sounds.size());

		for (Sound sound : _sounds) {
			sound.write(stream, EncodingFormat.W3S_0x1);
		}
	}

	private void read_auto(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int version = stream.readInt32();

		stream.rewind();

		read(stream, stream.getFormat(EncodingFormat.class, version));
	}

	private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
		switch (format.toEnum()) {
			case AUTO: {
				read_auto(stream);

				break;
			}
			case W3S_0x1: {
				read_0x1(stream);

				break;
			}
		}
	}

	private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
		switch (format.toEnum()) {
			case AUTO:
			case W3S_0x1: {
				write_0x1(stream);

				break;
			}
		}
	}

	private void read(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		read(stream, EncodingFormat.AUTO);
	}

	public void write(@Nonnull Wc3BinOutputStream stream) {
		write(stream, EncodingFormat.AUTO);
	}

	private void read(@Nonnull File file, @Nonnull EncodingFormat format) throws IOException {
		read(new Wc3BinInputStream(file), format);
	}

	public void write(@Nonnull File file, @Nonnull EncodingFormat format) throws IOException {
		Wc3BinOutputStream outStream = new Wc3BinOutputStream(file);

		write(outStream, format);

		outStream.close();
	}

	private void read(@Nonnull File file) throws IOException {
		read(file, EncodingFormat.AUTO);
	}

	public void write(@Nonnull File file) throws IOException {
		write(new Wc3BinOutputStream(file));
	}

	public W3S() {
	}

	public W3S(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
		read(stream);
	}

	public W3S(@Nonnull File file) throws IOException {
		read(file);
	}
}