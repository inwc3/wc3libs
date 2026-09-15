package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.ObjId;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * gamecache file (uncompressed)
 */
public class W3VUncompressed {
    protected EncodingFormat _format;

    public EncodingFormat getFormat() {
        return _format;
    }

    public void setFormat(@Nonnull EncodingFormat format) {
        this._format = format;
    }

	public static class GameCache extends Bin {
        private String _name;

        public String getName() {
            return _name;
        }

        public void setName(String value) {
            _name = value;
        }

        private War3Int _reserved;

        public War3Int getReserved() {
            return _reserved;
        }

        public void setReserved(War3Int value) {
            _reserved = value;
        }

		public static class Category extends Bin {
            private String _name;

            public String getName() {
                return _name;
            }

            public void setName(String value) {
                _name = value;
            }

			public abstract static class Entry<T> extends Bin {
                protected String _label;

                public String getLabel() {
                    return _label;
                }

                public void setLabel(String label) {
                    _label = label;
                }

                protected T _value;

				public T getValue() {
                    return _value;
                }

                public void setValue(T value) {
                    _value = value;
                }

				public void print() {
					System.out.printf("%s: %s%n", _label, _value);
				}

                public void read_0x0(Wc3BinInputStream stream) throws BinInputStream.StreamException {
                    _label = stream.readString("label");
                    read_0x0_spec(stream);
                }

                public abstract void read_0x0_spec(Wc3BinInputStream stream) throws BinInputStream.StreamException;

                public void write_0x0(Wc3BinOutputStream stream) throws BinInputStream.StreamException {
                    stream.writeString(_label);
                    write_0x0_spec(stream);
                }

                public abstract void write_0x0_spec(Wc3BinOutputStream stream) throws BinInputStream.StreamException;

                public void read(Wc3BinInputStream stream, EncodingFormat format) throws BinInputStream.StreamException {
                    switch (format.toEnum()) {
                        case W3V_0x0:
                            read_0x0(stream);

                            break;
                    }
                }

                public void write(Wc3BinOutputStream stream, EncodingFormat format) throws BinStream.StreamException {
                    switch (format.toEnum()) {
                        case AUTO:
                        case W3V_0x0:
                            write_0x0(stream);

                            break;
                    }
                }
			}

			public static class IntEntry extends Entry<War3Int> {
				public void read_0x0_spec(Wc3BinInputStream stream) throws BinInputStream.StreamException {
					_value = stream.readWc3Int("value");
				}

				public void write_0x0_spec(Wc3BinOutputStream stream) {
					stream.writeInt32(_value);
				}

				public IntEntry(Wc3BinInputStream stream, EncodingFormat format) throws BinInputStream.StreamException {
					read(stream, format);
				}

				public IntEntry() {
				}
			}

			private final List<IntEntry> _ints = new ArrayList<>();

			public List<IntEntry> getIntEntries() {
				return new ArrayList<>(_ints);
			}

			public static class RealEntry extends Entry<War3Real> {
				public void read_0x0_spec(Wc3BinInputStream stream) throws BinInputStream.StreamException {
					_value = stream.readReal("value");
				}

				public void write_0x0_spec(Wc3BinOutputStream stream) {
					stream.writeReal(_value);
				}

				public RealEntry(Wc3BinInputStream stream, EncodingFormat format) throws BinInputStream.StreamException {
					read(stream, format);
				}

				public RealEntry() {
				}
			}

			private final List<RealEntry> _reals = new ArrayList<>();

			public List<RealEntry> getRealEntries() {
				return new ArrayList<>(_reals);
			}

			public static class BoolEntry extends Entry<War3Bool> {
                public void read_0x0_spec(Wc3BinInputStream stream) throws BinInputStream.StreamException {
					_value = War3Bool.valueOf((stream.readInt32("value")) > 0);
				}

				public void write_0x0_spec(Wc3BinOutputStream stream) {
					stream.writeInt32(_value.getVal() ? 1 : 0);
				}

				public BoolEntry(Wc3BinInputStream stream, EncodingFormat format) throws BinInputStream.StreamException {
					read(stream, format);
				}

				public BoolEntry() {
				}
			}

			private final List<BoolEntry> _bools = new ArrayList<>();

			public List<BoolEntry> getBoolEntries() {
				return new ArrayList<>(_bools);
			}

			public static class UnitEntry extends Entry<UnitEntry.UnitValue> {
                public static class UnitValue {
                    private UnitId _unitId;

                    public UnitId getUnitId() {
                        return _unitId;
                    }

                    public void setUnitId(UnitId value) {
                        _unitId = value;
                    }

                    public static class InvSlot extends Bin {
                        private ItemId _itemId;

                        public ItemId getItemId() {
                            return _itemId;
                        }

                        public void setItemId(ItemId _itemId) {
                            this._itemId = _itemId;
                        }

                        private int _charges;

                        public int getCharges() {
                            return _charges;
                        }

                        public void setCharges(int _charges) {
                            this._charges = _charges;
                        }

                        public static class Flags extends FlagsInt {
                            @Override
                            public DataType decode(Object val) {
                                // TODO
                                return null;
                            }

                            @Override
                            public Object toSLKVal() {
                                // TODO
                                return null;
                            }

                            @Override
                            public Object toTXTVal() {
                                // TODO
                                return null;
                            }

                            public static class Flag extends FlagsInt.Flag {
                                private static List<Flag> _all = new ArrayList<>();

                                static int i = 0;

                                public final static Flag FLAG0 = new Flag(i++, "flag0");
                                public final static Flag FLAG1 = new Flag(i++, "flag1");
                                public final static Flag FLAG2 = new Flag(i++, "flag2");
                                public final static Flag INVULNERABLE = new Flag(i++, "invulnerable");
                                public final static Flag FLAG4 = new Flag(i++, "flag4");
                                public final static Flag FLAG5 = new Flag(i++, "flag5");
                                public final static Flag PERISHABLE = new Flag(i++, "perishable");
                                public final static Flag DROP_UPON_DEATH = new Flag(i++, "dropUponDeath");

                                public final static Flag FLAG8 = new Flag(i++, "flag8");
                                public final static Flag ACTIVELY_USED = new Flag(i++, "activelyUsed");
                                public final static Flag DROPPABLE = new Flag(i++, "droppable");
                                public final static Flag USE_AUTOMATICALLY_WHEN_ACQUIRED = new Flag(i++, "useAutomaticallyWhenAcquired");
                                public final static Flag FLAG12 = new Flag(i++, "flag12");
                                public final static Flag PAWNABLE = new Flag(i++, "pawnable");
                                public final static Flag FLAG14 = new Flag(i++, "flag14");
                                public final static Flag FLAG15 = new Flag(i++, "flag15");

                                public static Flag[] values() {
                                    Flag[] flags = new Flag[_all.size()];

                                    return _all.toArray(flags);
                                }

                                private Flag(int pos, @Nonnull String label) {
                                    super(label, pos);

                                    _all.add(this);
                                }
                            }

                            @Override
                            public String toString() {
                                StringBuilder sb = new StringBuilder("");

                                Flag[] flags = Flag.values();

                                if (flags.length > 0) {
                                    for (Flag flag : flags) {
                                        if (sb.length() > 0) {
                                            sb.append(" ");
                                        }

                                        sb.append(String.format("%s=%s", flag.toString(), containsFlag(flag)));
                                    }
                                }

                                return String.format("%s [%s]", Integer.toBinaryString(toInt()), sb.toString());
                            }

                            protected Flags(int val) {
                                super(val);
                            }

                            public static Flags valueOf(int val) {
                                return new Flags(val);
                            }
                        }

                        private Flags _flags;

                        public Flags getFlags() {
                            return _flags;
                        }

                        public void setFlags(Flags value) {
                            this._flags = value;
                        }

                        public void read_0x0(Wc3BinInputStream stream) throws BinInputStream.StreamException {
                            _itemId = ItemId.valueOf(stream.readId("itemId"));
                            _charges = stream.readInt32("charges");
                            _flags = Flags.valueOf(stream.readInt32("flags"));
                        }

                        public void write_0x0(Wc3BinOutputStream stream) {
                            stream.writeId(_itemId);
                            stream.writeInt32(_charges);
                            stream.writeInt32(_flags.toInt());
                        }

                        public void read(Wc3BinInputStream stream, EncodingFormat format) throws BinInputStream.StreamException {
                            switch (format.toEnum()) {
                                case W3V_0x0:
                                    read_0x0(stream);

                                    break;
                            }
                        }

                        public void write(Wc3BinOutputStream stream, EncodingFormat format) {
                            switch (format.toEnum()) {
                                case AUTO:
                                case W3V_0x0:
                                    write_0x0(stream);

                                    break;
                            }
                        }

                        public InvSlot(Wc3BinInputStream stream, EncodingFormat format) throws BinInputStream.StreamException {
                            read(stream, format);
                        }

                        public InvSlot() {
                        }
                    }

                    private final List<InvSlot> _invSlots = new ArrayList<>();

                    public List<InvSlot> getInvSlots() {
                        return new ArrayList<>(_invSlots);
                    }

                    private War3Int _experience;

                    public War3Int getExperience() {
                        return _experience;
                    }

                    public void setExperience(War3Int value) {
                        _experience = value;
                    }

                    private War3Int _levelMinusOne;

                    public War3Int getLevelMinusOne() {
                        return _levelMinusOne;
                    }

                    public void setLevelMinusOne(War3Int value) {
                        _levelMinusOne = value;
                    }

                    private War3Int _unusedSkillPts;

                    public War3Int getUnusedSkillPts() {
                        return _unusedSkillPts;
                    }

                    public void setUnusedSkillPts(War3Int value) {
                        _unusedSkillPts = value;
                    }

                    private short _heroProperNameIndex;

                    public short getHeroProperNameIndex() {
                        return _heroProperNameIndex;
                    }

                    public void setHeroProperNameIndex(short value) {
                        _heroProperNameIndex = value;
                    }

                    private short _unknownHeroProperNameIndex2;

                    public short getUnknownHeroProperNameIndex2() {
                        return _unknownHeroProperNameIndex2;
                    }

                    public void setUnknownHeroProperNameIndex2(short value) {
                        _unknownHeroProperNameIndex2 = value;
                    }

                    private int _strength;

                    public int getStrength() {
                        return _strength;
                    }

                    public void setStrength(int value) {
                        _strength = value;
                    }

                    private float _strengthPlus;

                    public float getStrengthPlus() {
                        return _strengthPlus;
                    }

                    public void setStrengthPlus(float value) {
                        _strengthPlus = value;
                    }

                    private int _agility;

                    private float _moveSpeedIncrementFromAgility;

                    public float getMoveSpeedIncrementFromAgility() {
                        return _moveSpeedIncrementFromAgility;
                    }

                    public void setMoveSpeedIncrementFromAgility(float value) {
                        this._moveSpeedIncrementFromAgility = value;
                    }

                    private float _attackSpeedIncrementFromAgility;

                    public float getAttackSpeedIncrementFromAgility() {
                        return _attackSpeedIncrementFromAgility;
                    }

                    public void setAttackSpeedIncrementFromAgility(float value) {
                        this._attackSpeedIncrementFromAgility = value;
                    }

                    public int getAgility() {
                        return _agility;
                    }

                    public void setAgility(int value) {
                        _agility = value;
                    }

                    private float _agilityPlus;

                    public float getAgilityPlus() {
                        return _agilityPlus;
                    }

                    public void setAgilityPlus(float value) {
                        _agilityPlus = value;
                    }

                    private int _intelligence;

                    public int getIntelligence() {
                        return _intelligence;
                    }

                    public void setIntelligence(int value) {
                        _intelligence = value;
                    }

                    private float _intelligencePlus;

                    public float getIntelligencePlus() {
                        return _intelligencePlus;
                    }

                    public void setInteligencePlus(float value) {
                        _intelligencePlus = value;
                    }

                    public static class HeroSkill extends Bin {
                        private AbilId _abilId;

                        public AbilId getAbilId() {
                            return _abilId;
                        }

                        public void setAbilId(AbilId value) {
                            this._abilId = value;
                        }

                        private War3Int _level;

                        public void read_0x0(Wc3BinInputStream stream) throws BinInputStream.StreamException {
                            _abilId = AbilId.valueOf(ObjId.valueOf(stream.readId()));
                            _level = stream.readWc3Int();
                        }

                        public void write_0x0(Wc3BinOutputStream stream) {
                            stream.writeId(_abilId);
                            stream.writeInt32(_level);
                        }

                        public void read(Wc3BinInputStream stream, EncodingFormat format) throws BinInputStream.StreamException {
                            switch (format.toEnum()) {
                                case W3V_0x0:
                                    read_0x0(stream);

                                    break;
                            }
                        }

                        public void write(Wc3BinOutputStream stream, EncodingFormat format) {
                            switch (format.toEnum()) {
                                case AUTO:
                                case W3V_0x0:
                                    write_0x0(stream);

                                    break;
                            }
                        }

                        public HeroSkill(Wc3BinInputStream stream, EncodingFormat format) throws BinInputStream.StreamException {
                            read(stream, format);
                        }

                        public HeroSkill() {
                        }
                    }

                    private final List<HeroSkill> _heroSkills = new ArrayList<>();

                    public List<HeroSkill> getHeroSkills() {
                        return new ArrayList<>(_heroSkills);
                    }

                    private float _lifeIncrement;

                    public float getLifeIncrement() {
                        return _lifeIncrement;
                    }

                    public void setLifeIncrement(float value) {
                        _lifeIncrement = value;
                    }

                    private float _manaIncrement;

                    public float getManaIncrement() {
                        return _manaIncrement;
                    }

                    public void setManaIncrement(float value) {
                        _manaIncrement = value;
                    }

                    private float _sightRangeDay;

                    public float getSightRangeDay() {
                        return _sightRangeDay;
                    }

                    public void setSightRangeDay(float value) {
                        _sightRangeDay = value;
                    }

                    private int _unknown7;

                    public int getUnknown7() {
                        return _unknown7;
                    }

                    public void setUnknown7(int value) {
                        _unknown7 = value;
                    }

                    private int _attackIncrement1;

                    public int getAttackIncrement1() {
                        return _attackIncrement1;
                    }

                    public void setAttackIncrement1(int value) {
                        _attackIncrement1 = value;
                    }

                    private int _attackIncrement2;

                    public int getAttackIncrement2() {
                        return _attackIncrement2;
                    }

                    public void setAttackIncrement2(int value) {
                        _attackIncrement2 = value;
                    }

                    private float _armorIncrement;

                    public float getArmorIncrement() {
                        return _armorIncrement;
                    }

                    public void setArmorIncrement(float value) {
                        _armorIncrement = value;
                    }

                    private short _unknown8;

                    public short getUnknown8() {
                        return _unknown8;
                    }

                    public void setUnknown8(short value) {
                        _unknown8 = value;
                    }

                    public void read_0x0(Wc3BinInputStream stream) throws BinInputStream.StreamException {
                        _unitId = UnitId.valueOf(ObjId.valueOf(stream.readId("unitId")));

                        int invSlotsCount = stream.readInt32("invSlotsCount");

                        for (int i = 0; i < invSlotsCount; i++) {
                            _invSlots.add(new InvSlot(stream, EncodingFormat.W3V_0x0));
                        }

                        _experience = stream.readWc3Int("experience");
                        _levelMinusOne = stream.readWc3Int("levelMinusOne");
                        _unusedSkillPts = stream.readWc3Int("unusedSkillPts");
                        _heroProperNameIndex = stream.readInt16("heroProperNameIndex");
                        _unknownHeroProperNameIndex2 = stream.readInt16("unknownHeroProperNameIndex2");
                        _strength = stream.readInt32("strength");
                        _strengthPlus = stream.readFloat32("strengthPlus");
                        _agility = stream.readInt32("agility");
                        _moveSpeedIncrementFromAgility = stream.readFloat32("moveSpeedIncrementFromAgility");
                        _attackSpeedIncrementFromAgility = stream.readFloat32("attackSpeedIncrementFromAgility");
                        _agilityPlus = stream.readFloat32("agilityPlus");
                        _intelligence = stream.readInt32("intelligence");
                        _intelligencePlus = stream.readFloat32("intelligencePlus");

                        int heroSkillsCount = stream.readInt32("heroSkillsCount");

                        for (int i = 0; i < heroSkillsCount; i++) {
                            _heroSkills.add(new HeroSkill(stream, EncodingFormat.W3V_0x0));
                        }

                        _lifeIncrement = stream.readFloat32("lifeIncrement");
                        _manaIncrement = stream.readFloat32("manaIncrement");
                        _sightRangeDay = stream.readFloat32("sightRangeDay");

                        _unknown7 = stream.readInt32("unknown7");

                        _attackIncrement1 = stream.readInt32("attackIncrement1");
                        _attackIncrement2 = stream.readInt32("attackIncrement2");
                        _armorIncrement = stream.readFloat32("armorIncrement");

                        _unknown8 = stream.readInt16("unknown8");
                    }

                    public void write_0x0(Wc3BinOutputStream stream) {
                        stream.writeId(_unitId);

                        stream.writeInt32(_invSlots.size());

                        for (InvSlot slot : _invSlots) {
                            slot.write(stream, EncodingFormat.W3V_0x0);
                        }

                        stream.writeInt32(_experience);
                        stream.writeInt32(_levelMinusOne);
                        stream.writeInt32(_unusedSkillPts);
                        stream.writeInt16(_heroProperNameIndex);
                        stream.writeInt16(_unknownHeroProperNameIndex2);
                        stream.writeInt32(_strength);
                        stream.writeFloat32(_strengthPlus);
                        stream.writeInt32(_agility);
                        stream.writeFloat32(_moveSpeedIncrementFromAgility);
                        stream.writeFloat32(_attackSpeedIncrementFromAgility);
                        stream.writeFloat32(_agilityPlus);
                        stream.writeInt32(_intelligence);
                        stream.writeFloat32(_intelligencePlus);

                        stream.writeInt32(_heroSkills.size());

                        for (HeroSkill skill : _heroSkills) {
                            skill.write(stream, EncodingFormat.W3V_0x0);
                        }

                        stream.writeFloat32(_lifeIncrement);
                        stream.writeFloat32(_manaIncrement);
                        stream.writeFloat32(_sightRangeDay);

                        stream.writeInt32(_unknown7);

                        stream.writeInt32(_attackIncrement1);
                        stream.writeInt32(_attackIncrement2);
                        stream.writeFloat32(_armorIncrement);

                        stream.writeInt16(_unknown8);
                    }

                    public void read(Wc3BinInputStream stream, EncodingFormat format) throws BinInputStream.StreamException {
                        switch (format.toEnum()) {
                            case W3V_0x0:
                                read_0x0(stream);

                                break;
                        }
                    }

                    public void write(Wc3BinOutputStream stream, EncodingFormat format) {
                        switch (format.toEnum()) {
                            case AUTO:
                            case W3V_0x0:
                                write_0x0(stream);

                                break;
                        }
                    }

                    public UnitValue(Wc3BinInputStream stream, EncodingFormat format) throws BinInputStream.StreamException {
                        read(stream, format);
                    }

                    public UnitValue() {
                    }
                }

                public void read_0x0_spec(Wc3BinInputStream stream) throws BinInputStream.StreamException {
                    _value = new UnitValue(stream, EncodingFormat.W3V_0x0);
                }

                public void write_0x0_spec(Wc3BinOutputStream stream) {
                    _value.write_0x0(stream);
                }

                public UnitEntry(Wc3BinInputStream stream, EncodingFormat format) throws BinInputStream.StreamException {
                    read(stream, format);
                }

                public UnitEntry() {
                }
			}

			private final List<UnitEntry> _units = new ArrayList<>();

			public List<UnitEntry> getUnitEntries() {
				return new ArrayList<>(_units);
			}

			public static class StringEntry extends Entry<String> {
				public void read_0x0_spec(Wc3BinInputStream stream) throws BinInputStream.StreamException {
					_value = stream.readString();
				}

				public void write_0x0_spec(Wc3BinOutputStream stream) {
                    stream.writeString(_value);
				}

				public StringEntry(Wc3BinInputStream stream, EncodingFormat format) throws BinInputStream.StreamException {
					read(stream, format);
				}

				public StringEntry() {
				}
			}

			private final List<StringEntry> _strings = new ArrayList<>();

			public List<StringEntry> getStringEntries() {
				return new ArrayList<>(_strings);
			}

			public void print() {
				System.out.printf("category: %s%n", _name);

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

			public void read_0x0(Wc3BinInputStream stream) throws BinInputStream.StreamException {
				_name = stream.readString("name");

				for (int i = 0; i < 5; i++) {
					stream.readWc3Int(String.format("reservedVarTypes[%d]", i));
				}

				//set(RESERVED, stream.readWc3Int());

				int intsCount = stream.readInt32("intsCount");

				for (int i = 0; i < intsCount; i++) {
					_ints.add(new IntEntry(stream, EncodingFormat.W3V_0x0));
				}

				int realsCount = stream.readInt32("realsCount");

				for (int i = 0; i < realsCount; i++) {
                    _reals.add(new RealEntry(stream, EncodingFormat.W3V_0x0));
				}

				int boolsCount = stream.readInt32("boolsCount");

				for (int i = 0; i < boolsCount; i++) {
					_bools.add(new BoolEntry(stream, EncodingFormat.W3V_0x0));
				}

				int unitsCount = stream.readInt32("unitsCount");

				for (int i = 0; i < unitsCount; i++) {
					_units.add(new UnitEntry(stream, EncodingFormat.W3V_0x0));
				}

				int stringsCount = stream.readInt32("stringsCount");

				for (int i = 0; i < stringsCount; i++) {
					_strings.add(new StringEntry(stream, EncodingFormat.W3V_0x0));
				}
			}

			public void write_0x0(Wc3BinOutputStream stream) throws BinStream.StreamException {
				stream.writeString(_name);

				for (int i = 0; i < 5; i++) {
					stream.writeInt32(0);
				}

				stream.writeInt32(_ints.size());

				for (IntEntry entry : _ints) {
					entry.write(stream, EncodingFormat.W3V_0x0);
				}

				stream.writeInt32(_reals.size());

				for (RealEntry entry : _reals) {
					entry.write(stream, EncodingFormat.W3V_0x0);
				}

				stream.writeInt32(_bools.size());

				for (BoolEntry entry : _bools) {
					entry.write(stream, EncodingFormat.W3V_0x0);
				}

				stream.writeInt32(_units.size());

				for (UnitEntry entry : _units) {
					entry.write(stream, EncodingFormat.W3V_0x0);
				}

				stream.writeInt32(_strings.size());

				for (StringEntry entry : _strings) {
					entry.write(stream, EncodingFormat.W3V_0x0);
				}
			}

			public void read(Wc3BinInputStream stream, EncodingFormat format) throws BinInputStream.StreamException {
				switch (format.toEnum()) {
                case W3V_0xFF:
				case W3V_0x0:
					read_0x0(stream);

					break;
				}
			}

			public void write(Wc3BinOutputStream stream, EncodingFormat format) throws BinStream.StreamException {
				switch (format.toEnum()) {
				case AUTO:
                case W3V_0xFF:
				case W3V_0x0:
					write_0x0(stream);

					break;
				}
			}

			public Category(Wc3BinInputStream stream, EncodingFormat format) throws BinInputStream.StreamException {
				read(stream, format);
			}

			public Category() {
			}
		}

		private final List<Category> _categories = new ArrayList<>();

		public List<Category> getCategories() {
			return new ArrayList<>(_categories);
		}

		public void print() {
			System.out.printf("%s%n", _name);

			for (Category category : _categories) {
				category.print();
			}
		}

		public void read_0x0(Wc3BinInputStream stream) throws BinInputStream.StreamException {
			_name = stream.readString("name");
			_reserved = stream.readWc3Int("reserved");

			int categoriesCount = stream.readInt32("categoriesCount");

			for (int i = 0; i < categoriesCount; i++) {
				_categories.add(new Category(stream, EncodingFormat.W3V_0x0));
			}
		}

		public void write_0x0(Wc3BinOutputStream stream) throws BinStream.StreamException {
			stream.writeString(_name);
			stream.writeInt32(_reserved);

			stream.writeInt32(_categories.size());

			for (Category category : _categories) {
				category.write(stream, EncodingFormat.W3V_0x0);
			}
		}

		public void read(Wc3BinInputStream stream, EncodingFormat format) throws BinInputStream.StreamException {
			switch (format.toEnum()) {
            case W3V_0xFF:
			case W3V_0x0:
				read_0x0(stream);

				break;
			}
		}

		public void write(Wc3BinOutputStream stream, EncodingFormat format) throws BinStream.StreamException {
			switch (format.toEnum()) {
			case AUTO:
            case W3V_0xFF:
			case W3V_0x0:
				write_0x0(stream);

				break;
			}
		}

		public GameCache(Wc3BinInputStream stream, EncodingFormat format) throws BinInputStream.StreamException {
			read(stream, format);
		}

		public GameCache() {
		}
	}

	protected final List<GameCache> _gameCaches = new ArrayList<>();

	public List<GameCache> getGameCaches() {
		return new ArrayList<>(_gameCaches);
	}

	public void print() {
		for (GameCache gameCache : _gameCaches) {
			gameCache.print();
		}
	}

	public static class EncodingFormat extends Format<EncodingFormat.Enum> {
		enum Enum {
			AUTO,
            AS_DEFINED,
			W3V_0x0,
            W3V_0xFF,
		}

		private static final Map<Integer, EncodingFormat> _map = new LinkedHashMap<>();

		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
        public final static EncodingFormat AS_DEFINED = new EncodingFormat(Enum.AS_DEFINED, null);
		public final static EncodingFormat W3V_0x0 = new EncodingFormat(Enum.W3V_0x0, 0);
        public final static EncodingFormat W3V_0xFF = new EncodingFormat(Enum.W3V_0xFF, 0xFF);

		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}

		private EncodingFormat(Enum enumVal, @Nullable Integer version) {
			super(enumVal, version);

            if (version != null) {
                _map.put(version, this);
            }
		}
	}

	public void read_0x0(Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int version = stream.readInt32("version");

		stream.checkFormatVersion(EncodingFormat.W3V_0x0.getVersion(), version);

        _format = EncodingFormat.valueOf(version);

		int gameCachesCount = stream.readInt32("gameCachesCount");

		for (int i = 0; i < gameCachesCount; i++) {
			_gameCaches.add(new GameCache(stream, EncodingFormat.W3V_0x0));
		}
	}

    public void read_0xFF(Wc3BinInputStream stream) throws BinInputStream.StreamException {
        int version = stream.readInt32("version");

        stream.checkFormatVersion(EncodingFormat.W3V_0xFF.getVersion(), version);

        _format = EncodingFormat.valueOf(version);

        int gameCachesCount = stream.readInt32("gameCachesCount");

        for (int i = 0; i < gameCachesCount; i++) {
            _gameCaches.add(new GameCache(stream, EncodingFormat.W3V_0xFF));
        }
    }

	public void write_0x0(Wc3BinOutputStream stream) throws BinStream.StreamException {
		stream.writeInt32(EncodingFormat.W3V_0x0.getVersion());

		stream.writeInt32(_gameCaches.size());

		for (GameCache gameCache : _gameCaches) {
			gameCache.write(stream, EncodingFormat.W3V_0x0);
		}
	}

    public void write_0xFF(Wc3BinOutputStream stream) throws BinStream.StreamException {
        stream.writeInt32(EncodingFormat.W3V_0xFF.getVersion());

        stream.writeInt32(_gameCaches.size());

        for (GameCache gameCache : _gameCaches) {
            gameCache.write(stream, EncodingFormat.W3V_0xFF);
        }
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
		int version = stream.readInt32();

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

    public W3VUncompressed(@Nonnull Wc3BinInputStream stream) throws Exception {
        read(stream);
    }

	public W3VUncompressed(File file) throws IOException {
		read(file);
	}

    public W3VUncompressed() {
    }
}
