package net.moonlightflower.wc3libs.bin.app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.moonlightflower.wc3libs.bin.Bin;
import net.moonlightflower.wc3libs.bin.BinState;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.BinStream.StreamException;
import net.moonlightflower.wc3libs.bin.Format;
import net.moonlightflower.wc3libs.bin.Packed;
import net.moonlightflower.wc3libs.bin.Wc3BinStream;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.AbilId;
import net.moonlightflower.wc3libs.dataTypes.app.Bool;
import net.moonlightflower.wc3libs.dataTypes.app.Bounds;
import net.moonlightflower.wc3libs.dataTypes.app.Color;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.ItemId;
import net.moonlightflower.wc3libs.dataTypes.app.Real;
import net.moonlightflower.wc3libs.dataTypes.app.SoundLabel;
import net.moonlightflower.wc3libs.dataTypes.app.UnitId;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.dataTypes.app.WeatherId;

/**
 * gamecache file
 */
public class W3V {	
	public static class Cache extends Bin {
		private static class State<T extends DataType> extends BinState<T> {
			private static final List<State> _values = new ArrayList<>();
			
			public static List<State> values() {
				return _values;
			}
			
			/*public T get() {
				return Rect.this.get(this);
			}
			
			public void set(T val) {
				Rect.this.set(this, val);
			}*/
			
			public State(DataTypeInfo typeInfo, String idString, T defVal) {
				super(typeInfo, idString, defVal);
				
				_values.add(this);
			}
			
			public State(DataTypeInfo typeInfo, String idString) {
				this(typeInfo, idString, null);
			}

			public State(Class<T> type, String idString) {
				this(new DataTypeInfo(type), idString);
			}
			
			public State(Class<T> type, String idString, T defVal) {
				this(new DataTypeInfo(type), idString, defVal);
			}
		}

		public final static State<Wc3String> NAME = new State<>(Wc3String.class, "name");
		public final static State<Int> RESERVED = new State<>(Int.class, "reserved");

		public <T extends DataType> T get(State<T> state) {
			return state.tryCastVal(super.get(state));
		}
		
		public <T extends DataType> void set(State<T> state, T val) {
			super.set(state, val);
		}
		
		public <T extends DataType> void remove(State<T> state) {
			super.set(state, null);
		}
		
		public static class Cat extends Bin {
			private static class State<T extends DataType> extends BinState<T> {
				private static final List<State> _values = new ArrayList<>();
				
				public static List<State> values() {
					return _values;
				}
				
				/*public T get() {
					return Rect.this.get(this);
				}
				
				public void set(T val) {
					Rect.this.set(this, val);
				}*/
				
				public State(DataTypeInfo typeInfo, String idString, T defVal) {
					super(typeInfo, idString, defVal);
					
					_values.add(this);
				}
				
				public State(DataTypeInfo typeInfo, String idString) {
					this(typeInfo, idString, null);
				}

				public State(Class<T> type, String idString) {
					this(new DataTypeInfo(type), idString);
				}
				
				public State(Class<T> type, String idString, T defVal) {
					this(new DataTypeInfo(type), idString, defVal);
				}
			}
			
			public final static State<Wc3String> NAME = new State<>(Wc3String.class, "name");
			public final static State<Int> CUSTOM_VAR_TYPES = new State<>(Int.class, "customVarTypes");

			public <T extends DataType> T get(State<T> state) {
				return state.tryCastVal(super.get(state));
			}
			
			public <T extends DataType> void set(State<T> state, T val) {
				super.set(state, val);
			}
			
			public <T extends DataType> void remove(State<T> state) {
				super.set(state, null);
			}
			
			public abstract static class Entry extends Bin {
				private static class State<T extends DataType> extends BinState<T> {
					private static final List<State> _values = new ArrayList<>();
					
					public static List<State> values() {
						return _values;
					}
					
					/*public T get() {
						return Rect.this.get(this);
					}
					
					public void set(T val) {
						Rect.this.set(this, val);
					}*/
					
					public State(DataTypeInfo typeInfo, String idString, T defVal) {
						super(typeInfo, idString, defVal);
						
						_values.add(this);
					}
					
					public State(DataTypeInfo typeInfo, String idString) {
						this(typeInfo, idString, null);
					}

					public State(Class<T> type, String idString) {
						this(new DataTypeInfo(type), idString);
					}
					
					public State(Class<T> type, String idString, T defVal) {
						this(new DataTypeInfo(type), idString, defVal);
					}
				}

				public final static State<Wc3String> LABEL = new State<>(Wc3String.class, "label");
				
				public <T extends DataType> T get(State<T> state) {
					return state.tryCastVal(super.get(state));
				}
				
				public <T extends DataType> void set(State<T> state, T val) {
					super.set(state, val);
				}
				
				public <T extends DataType> void remove(State<T> state) {
					super.set(state, null);
				}
				
				public void print() {
					System.out.println(String.format("%s: %s", ((Wc3String) get(LABEL)).getVal(), toString()));
				}
			}
			
			public static class IntEntry extends Entry {
				public final static State<Int> VAL = new State<>(Int.class, "val");
				
				@Override
				public String toString() {
					return Integer.toString(((Int) get(VAL)).toInt());
				}
				
				public void read_0x0(Wc3BinStream stream) throws BinStream.StreamException {
					set(LABEL, stream.readWc3String());
					set(VAL, stream.readWc3Int());
				}

				public void write_0x0(Wc3BinStream stream) {
					stream.writeString((Wc3String) get(LABEL));
					stream.writeInt((Int) get(VAL));
				}

				public void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
					switch (format.toEnum()) {
					case W3V_0x0:
						read_0x0(stream);
						
						break;
					}
				}
				
				public void write(Wc3BinStream stream, EncodingFormat format) {
					switch (format.toEnum()) {
					case AUTO:
					case W3V_0x0:
						write_0x0(stream);
						
						break;
					}
				}
				
				public IntEntry(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
					read(stream, format);
				}
				
				public IntEntry() {
				}
			}
			
			private List<IntEntry> _ints = new ArrayList<>();
			
			public List<IntEntry> getIntEntries() {
				return new ArrayList<>(_ints);
			}
			
			private void addIntEntry(IntEntry val) {
				_ints.add(val);
			}
			
			public IntEntry addIntEntry() {
				IntEntry entry = new IntEntry();

				addIntEntry(entry);

				return entry;
			}
			
			public static class RealEntry extends Entry {
				public final static State<Real> VAL = new State<>(Real.class, "val");
				
				@Override
				public String toString() {
					return Float.toString(((Real) get(VAL)).toFloat());
				}
				
				public void read_0x0(Wc3BinStream stream) throws BinStream.StreamException {
					set(LABEL, stream.readWc3String());
					set(VAL, stream.readReal());
				}

				public void write_0x0(Wc3BinStream stream) {
					stream.writeString((Wc3String) get(LABEL));
					stream.writeReal((Real) get(VAL));
				}

				public void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
					switch (format.toEnum()) {
					case W3V_0x0:
						read_0x0(stream);
						
						break;
					}
				}
				
				public void write(Wc3BinStream stream, EncodingFormat format) {
					switch (format.toEnum()) {
					case AUTO:
					case W3V_0x0:
						write_0x0(stream);
						
						break;
					}
				}
				
				public RealEntry(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
					read(stream, format);
				}
				
				public RealEntry() {
				}
			}
			
			private List<RealEntry> _reals = new ArrayList<>();
			
			public List<RealEntry> getRealEntries() {
				return new ArrayList<>(_reals);
			}
			
			private void addRealEntry(RealEntry val) {
				_reals.add(val);
			}
			
			public RealEntry addRealEntry() {
				RealEntry entry = new RealEntry();

				addRealEntry(entry);

				return entry;
			}
			
			public static class BoolEntry extends Entry {
				public final static State<Bool> VAL = new State<>(Bool.class, "val");
				
				public void read_0x0(Wc3BinStream stream) throws BinStream.StreamException {
					set(LABEL, stream.readWc3String());
					set(VAL, Bool.valueOf(((stream.readInt() & 0xFFFFFFFF) > 0)));
				}

				public void write_0x0(Wc3BinStream stream) {
					stream.writeString((Wc3String) get(LABEL));
					stream.writeInt(((Bool) get(VAL)).getVal() ? 1 : 0);
				}

				public void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
					switch (format.toEnum()) {
					case W3V_0x0:
						read_0x0(stream);
						
						break;
					}
				}
				
				public void write(Wc3BinStream stream, EncodingFormat format) {
					switch (format.toEnum()) {
					case AUTO:
					case W3V_0x0:
						write_0x0(stream);
						
						break;
					}
				}
				
				@Override
				public String toString() {
					return Boolean.toString(((Bool) get(VAL)).getVal());
				}
				
				public BoolEntry(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
					read(stream, format);
				}
				
				public BoolEntry() {
				}
			}
			
			private List<BoolEntry> _bools = new ArrayList<>();
			
			public List<BoolEntry> getBoolEntries() {
				return new ArrayList<>(_bools);
			}
			
			private void addBoolEntry(BoolEntry val) {
				_bools.add(val);
			}
			
			public BoolEntry addBoolEntry() {
				BoolEntry entry = new BoolEntry();

				addBoolEntry(entry);

				return entry;
			}
			
			public static class UnitEntry extends Entry {
				public final static State<UnitId> ID = new State<>(UnitId.class, "id");
				public final static State<Int> EXP = new State<>(Int.class, "exp");
				public final static State<Int> LEVEL = new State<>(Int.class, "level");
				public final static State<Int> UNUSED_SKILL_PTS = new State<>(Int.class, "unusedSkillPts");
				
				public static class InvSlot extends Bin {
					private static class State<T extends DataType> extends BinState<T> {
						private static final List<State> _values = new ArrayList<>();
						
						public static List<State> values() {
							return _values;
						}
						
						/*public T get() {
							return Rect.this.get(this);
						}
						
						public void set(T val) {
							Rect.this.set(this, val);
						}*/
						
						public State(DataTypeInfo typeInfo, String idString, T defVal) {
							super(typeInfo, idString, defVal);
							
							_values.add(this);
						}
						
						public State(DataTypeInfo typeInfo, String idString) {
							this(typeInfo, idString, null);
						}

						public State(Class<T> type, String idString) {
							this(new DataTypeInfo(type), idString);
						}
						
						public State(Class<T> type, String idString, T defVal) {
							this(new DataTypeInfo(type), idString, defVal);
						}
					}
					
					public <T extends DataType> T get(State<T> state) {
						return state.tryCastVal(super.get(state));
					}
					
					public <T extends DataType> void set(State<T> state, T val) {
						super.set(state, val);
					}
					
					public <T extends DataType> void remove(State<T> state) {
						super.set(state, null);
					}
					
					public final static State<ItemId> ID = new State<>(ItemId.class, "id");
					public final static State<Int> CHARGES = new State<>(Int.class, "charges");
					public final static State<Int> UNKNOWN = new State<>(Int.class, "unknown");
					
					public void read_0x0(Wc3BinStream stream) throws BinStream.StreamException {
						set(LABEL, ItemId.valueOf(stream.readId()));
						set(CHARGES, stream.readWc3Int());
						set(UNKNOWN, stream.readWc3Int());
					}

					public void write_0x0(Wc3BinStream stream) {
						stream.writeId((ItemId) get(LABEL));
						stream.writeInt((Int) get(CHARGES));
						stream.writeInt((Int) get(UNKNOWN));
					}

					public void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
						switch (format.toEnum()) {
						case W3V_0x0:
							read_0x0(stream);
							
							break;
						}
					}
					
					public void write(Wc3BinStream stream, EncodingFormat format) {
						switch (format.toEnum()) {
						case AUTO:
						case W3V_0x0:
							write_0x0(stream);
							
							break;
						}
					}
					
					public InvSlot(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
						read(stream, format);
					}
					
					public InvSlot() {
					}
				}
				
				private List<InvSlot> _invSlots = new ArrayList<>();
				
				public List<InvSlot> getInvSlots() {
					return new ArrayList<>(_invSlots);
				}
				
				private void addInvSlot(InvSlot val) {
					_invSlots.add(val);
				}
				
				public InvSlot addInvSlot() {
					InvSlot slot = new InvSlot();

					addInvSlot(slot);

					return slot;
				}
				
				public static class HeroSkill extends Bin {
					private static class State<T extends DataType> extends BinState<T> {
						private static final List<State> _values = new ArrayList<>();
						
						public static List<State> values() {
							return _values;
						}
						
						/*public T get() {
							return Rect.this.get(this);
						}
						
						public void set(T val) {
							Rect.this.set(this, val);
						}*/
						
						public State(DataTypeInfo typeInfo, String idString, T defVal) {
							super(typeInfo, idString, defVal);
							
							_values.add(this);
						}
						
						public State(DataTypeInfo typeInfo, String idString) {
							this(typeInfo, idString, null);
						}

						public State(Class<T> type, String idString) {
							this(new DataTypeInfo(type), idString);
						}
						
						public State(Class<T> type, String idString, T defVal) {
							this(new DataTypeInfo(type), idString, defVal);
						}
					}
					
					public <T extends DataType> T get(State<T> state) {
						return state.tryCastVal(super.get(state));
					}
					
					public <T extends DataType> void set(State<T> state, T val) {
						super.set(state, val);
					}
					
					public <T extends DataType> void remove(State<T> state) {
						super.set(state, null);
					}
					
					public final static State<AbilId> ID = new State<>(AbilId.class, "id");
					public final static State<Int> LEVEL = new State<>(Int.class, "level");
					
					public void read_0x0(Wc3BinStream stream) throws BinStream.StreamException {
						set(ID, AbilId.valueOf(stream.readId()));
						set(LEVEL, stream.readWc3Int());
					}

					public void write_0x0(Wc3BinStream stream) {
						stream.writeId((AbilId) get(ID));
						stream.writeInt((Int) get(LEVEL));
					}

					public void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
						switch (format.toEnum()) {
						case W3V_0x0:
							read_0x0(stream);
							
							break;
						}
					}
					
					public void write(Wc3BinStream stream, EncodingFormat format) {
						switch (format.toEnum()) {
						case AUTO:
						case W3V_0x0:
							write_0x0(stream);
							
							break;
						}
					}
					
					public HeroSkill(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
						read(stream, format);
					}
					
					public HeroSkill() {
					}
				}
				
				private List<HeroSkill> _heroSkills = new ArrayList<>();
				
				public List<HeroSkill> getHeroSkills() {
					return new ArrayList<>(_heroSkills);
				}
				
				private void addHeroSkill(HeroSkill val) {
					_heroSkills.add(val);
				}
				
				public HeroSkill addHeroSkill() {
					HeroSkill skill = new HeroSkill();

					addHeroSkill(skill);

					return skill;
				}
				
				@Override
				public String toString() {
					return ((Wc3String) get(LABEL)).getVal();
				}
				
				public void read_0x0(Wc3BinStream stream) throws BinStream.StreamException {
					set(LABEL, stream.readWc3String());
					set(ID, UnitId.valueOf(stream.readId()));
					
					int invSlots = stream.readInt();
					
					for (int i = 0; i < invSlots; i++) {
						addInvSlot(new InvSlot(stream, EncodingFormat.W3V_0x0));
					}
					
					set(EXP, stream.readWc3Int());
					set(LEVEL, stream.readWc3Int());
					set(UNUSED_SKILL_PTS, stream.readWc3Int());
					
					for (int i = 0; i < 9; i++) {
						stream.readInt();
					}
					
					int heroSkills = stream.readInt();
					
					for (int i = 0; i < heroSkills; i++) {
						addHeroSkill(new HeroSkill(stream, EncodingFormat.W3V_0x0));
					}
					
					for (int i = 0; i < 2; i++) {
						stream.readInt();
					}
					
					stream.readFloat();
					
					for (int i = 0; i < 4; i++) {
						stream.readInt();
					}
					
					stream.readShort();
				}

				public void write_0x0(Wc3BinStream stream) {
					stream.writeString((Wc3String) get(LABEL));
					stream.writeId((UnitId) get(ID));
					
					stream.writeInt(_invSlots.size());
					
					for (InvSlot slot : _invSlots) {
						slot.write(stream, EncodingFormat.W3V_0x0);
					}
					
					stream.writeInt((Int) get(EXP));
					stream.writeInt((Int) get(LEVEL));
					stream.writeInt((Int) get(UNUSED_SKILL_PTS));
					
					for (int i = 0; i < 9; i++) {
						stream.writeInt(0);
					}
					
					stream.writeInt(_heroSkills.size());
					
					for (HeroSkill skill : _heroSkills) {
						skill.write(stream, EncodingFormat.W3V_0x0);
					}

					for (int i = 0; i < 2; i++) {
						stream.writeInt(0);
					}
					
					stream.writeFloat(0F);
					
					for (int i = 0; i < 4; i++) {
						stream.writeInt(0);
					}
					
					stream.writeShort((short) 0);
				}

				public void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
					switch (format.toEnum()) {
					case W3V_0x0:
						read_0x0(stream);
						
						break;
					}
				}
				
				public void write(Wc3BinStream stream, EncodingFormat format) {
					switch (format.toEnum()) {
					case AUTO:
					case W3V_0x0:
						write_0x0(stream);
						
						break;
					}
				}
				
				public UnitEntry(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
					read(stream, format);
				}
				
				public UnitEntry() {
				}
			}
			
			private List<UnitEntry> _units = new ArrayList<>();
			
			public List<UnitEntry> getUnitEntries() {
				return new ArrayList<>(_units);
			}
			
			private void addUnitEntry(UnitEntry val) {
				_units.add(val);
			}
			
			public UnitEntry addUnitEntry() {
				UnitEntry entry = new UnitEntry();

				addUnitEntry(entry);

				return entry;
			}
			
			public static class StringEntry extends Entry {
				public final static State<Wc3String> VAL = new State<>(Wc3String.class, "val");
				
				@Override
				public String toString() {
					return ((Wc3String) get(VAL)).getVal();
				}
				
				public void read_0x0(Wc3BinStream stream) throws BinStream.StreamException {
					set(LABEL, stream.readWc3String());
					set(VAL, stream.readWc3String());
				}

				public void write_0x0(Wc3BinStream stream) {
				}

				public void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
					switch (format.toEnum()) {
					case W3V_0x0:
						read_0x0(stream);
						
						break;
					}
				}
				
				public void write(Wc3BinStream stream, EncodingFormat format) {
					switch (format.toEnum()) {
					case AUTO:
					case W3V_0x0:
						write_0x0(stream);
						
						break;
					}
				}
				
				public StringEntry(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
					read(stream, format);
				}
				
				public StringEntry() {
				}
			}
			
			private List<StringEntry> _strings = new ArrayList<>();
			
			public List<StringEntry> getStringEntries() {
				return new ArrayList<>(_strings);
			}
			
			private void addStringEntry(StringEntry val) {
				_strings.add(val);
			}
			
			public StringEntry addStringEntry() {
				StringEntry entry = new StringEntry();

				addStringEntry(entry);

				return entry;
			}
			
			public void print() {
				System.out.println(String.format("cat: %s", get(NAME)));

				for (IntEntry entry : _ints) {
					entry.print();
				}
				for (RealEntry entry : _reals) {
					entry.print();
				}
				for (BoolEntry entry : _bools) {
					entry.print();
				}
				for (UnitEntry entry : _units) {
					entry.print();
				}
				for (StringEntry entry : _strings) {
					entry.print();
				}
			}
			
			public void read_0x0(Wc3BinStream stream) throws BinStream.StreamException {
				set(NAME, stream.readWc3String());
				
				for (int i = 0; i < 5; i++) {
					stream.readWc3Int();
				}
				
				//set(RESERVED, stream.readWc3Int());
				
				int intsCount = stream.readInt();

				for (int i = 0; i < intsCount; i++) {
					addIntEntry(new IntEntry(stream, EncodingFormat.W3V_0x0));
				}
				
				int realsCount = stream.readInt();

				for (int i = 0; i < realsCount; i++) {
					addRealEntry(new RealEntry(stream, EncodingFormat.W3V_0x0));
				}

				int boolsCount = stream.readInt();

				for (int i = 0; i < boolsCount; i++) {
					addBoolEntry(new BoolEntry(stream, EncodingFormat.W3V_0x0));
				}

				int unitsCount = stream.readInt();

				for (int i = 0; i < unitsCount; i++) {
					addUnitEntry(new UnitEntry(stream, EncodingFormat.W3V_0x0));
				}

				int stringsCount = stream.readInt();

				for (int i = 0; i < stringsCount; i++) {
					addStringEntry(new StringEntry(stream, EncodingFormat.W3V_0x0));
				}
			}

			public void write_0x0(Wc3BinStream stream) {
				stream.writeString((Wc3String) get(NAME));
				
				for (int i = 0; i < 5; i++) {
					stream.writeInt(0);
				}
				
				stream.writeInt(_ints.size());
				
				for (IntEntry entry : _ints) {
					entry.write(stream, EncodingFormat.W3V_0x0);
				}

				stream.writeInt(_reals.size());
				
				for (RealEntry entry : _reals) {
					entry.write(stream, EncodingFormat.W3V_0x0);
				}

				stream.writeInt(_bools.size());
				
				for (BoolEntry entry : _bools) {
					entry.write(stream, EncodingFormat.W3V_0x0);
				}

				stream.writeInt(_units.size());
				
				for (UnitEntry entry : _units) {
					entry.write(stream, EncodingFormat.W3V_0x0);
				}

				stream.writeInt(_strings.size());
				
				for (StringEntry entry : _strings) {
					entry.write(stream, EncodingFormat.W3V_0x0);
				}
			}

			public void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
				switch (format.toEnum()) {
				case W3V_0x0:
					read_0x0(stream);
					
					break;
				}
			}
			
			public void write(Wc3BinStream stream, EncodingFormat format) {
				switch (format.toEnum()) {
				case AUTO:
				case W3V_0x0:
					write_0x0(stream);
					
					break;
				}
			}
			
			public Cat(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
				read(stream, format);
			}
			
			public Cat() {
			}
		}
		
		private List<Cat> _cats = new ArrayList<>();
		
		public List<Cat> getCats() {
			return new ArrayList<>(_cats);
		}
		
		private void addCat(Cat val) {
			_cats.add(val);
		}
		
		public Cat addCat() {
			Cat cat = new Cat();

			addCat(cat);

			return cat;
		}
		
		public void print() {
			System.out.println(String.format("gc: %s", get(NAME)));
			
			for (Cat cat : _cats) {
				cat.print();
			}
		}
		
		public void read_0x0(Wc3BinStream stream) throws BinStream.StreamException {
			set(NAME, stream.readWc3String());
			set(RESERVED, stream.readWc3Int());
			
			int catsCount = stream.readInt();
			
			for (int i = 0; i < catsCount; i++) {
				addCat(new Cat(stream, EncodingFormat.W3V_0x0));
			}
		}

		public void write_0x0(Wc3BinStream stream) {
			stream.writeString((Wc3String) get(NAME));
			stream.writeInt((Int) get(RESERVED));

			stream.writeInt(_cats.size());
			
			for (Cat cat : _cats) {
				cat.write(stream, EncodingFormat.W3V_0x0);
			}
		}

		public void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
			switch (format.toEnum()) {
			case W3V_0x0:
				read_0x0(stream);
				
				break;
			}
		}
		
		public void write(Wc3BinStream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case W3V_0x0:
				write_0x0(stream);
				
				break;
			}
		}
		
		public Cache(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
			read(stream, format);
		}
		
		public Cache() {
		}
	}
	
	private List<Cache> _caches = new ArrayList<>();
	
	public List<Cache> getCaches() {
		return new ArrayList<>(_caches);
	}
	
	private void addCache(Cache val) {
		_caches.add(val);
	}
	
	public Cache addCache() {
		Cache cache = new Cache();

		addCache(cache);

		return cache;
	}
	
	public void print() {
		for (Cache cache : _caches) {
			cache.print();
		}
	}
	
	private static class EncodingFormat extends Format<EncodingFormat.Enum> {
		enum Enum {
			AUTO,
			W3V_0x0
		}

		static Map<Integer, EncodingFormat> _map = new HashMap<>();
		
		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat W3V_0x0 = new EncodingFormat(Enum.W3V_0x0, 0x0);
		
		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}
		
		private EncodingFormat(Enum enumVal, int version) {
			super(enumVal, version);
			
			_map.put(version, this);
		}
	}

	public void read_0x0(Wc3BinStream stream) throws BinStream.StreamException {
		stream = Packed.decompress(stream);
		
		int version = stream.readInt();

		Wc3BinStream.checkFormatVer("gamecacheMaskFunc", EncodingFormat.W3V_0x0.getVersion(), version);

		int cachesCount = stream.readInt();

		for (int i = 0; i < cachesCount; i++) {					
			addCache(new Cache(stream, EncodingFormat.W3V_0x0));
		}
	}

	public void write_0x0(Wc3BinStream stream) throws StreamException {
		Wc3BinStream outStream = stream;
		
		stream = new Wc3BinStream();
		
		stream.writeInt(EncodingFormat.W3V_0x0.getVersion());
		
		stream.writeInt(_caches.size());
		
		for (Cache cache : _caches) {
			cache.write(stream, EncodingFormat.W3V_0x0);
		}
		
		Wc3BinStream packStream = Packed.compress(stream);
		
		while (!packStream.eof()) {
			outStream.writeBytes(packStream.readBytes(Math.min(1024, packStream.size() - packStream.getPos())));
		}
	}
	
	private void read_auto(Wc3BinStream stream) throws BinStream.StreamException {
		int version = stream.readInt();
		
		stream.rewind();
		
		System.out.println(version);

		read(stream, EncodingFormat.valueOf(version));
	}

	private void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
		format = EncodingFormat.W3V_0x0;
		
		switch (format.toEnum()) {
		case AUTO: {
			read_auto(stream);
			
			break;
		}
		case W3V_0x0: {
			read_0x0(stream);
			
			break;
		}
		}
	}
	
	private void write(Wc3BinStream stream, EncodingFormat format) throws StreamException {
		switch (format.toEnum()) {
		case AUTO:
		case W3V_0x0: {
			write_0x0(stream);
			
			break;
		}
		}
	}
	
	private void read(Wc3BinStream stream) throws BinStream.StreamException {
		read(stream, EncodingFormat.AUTO);
	}
	
	private void write(Wc3BinStream stream) throws StreamException {
		write(stream, EncodingFormat.AUTO);
	}
	
	private void read(File file, EncodingFormat format) throws IOException {
		read(new Wc3BinStream(file), format);
	}
	
	public void write(File file, EncodingFormat format) throws IOException {
		write(new Wc3BinStream(file), format);
	}
	
	private void read(File file) throws IOException {
		read(file, EncodingFormat.AUTO);
	}

	public void write(File file) throws IOException {
		write(new Wc3BinStream(file));
	}
	
	public W3V() {
	}
	
	public W3V(File file) throws IOException {
		read(file);
	}
}