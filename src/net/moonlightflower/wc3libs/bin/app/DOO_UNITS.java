package net.moonlightflower.wc3libs.bin.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.moonlightflower.wc3libs.bin.Format;
import net.moonlightflower.wc3libs.bin.Wc3bin;
import net.moonlightflower.wc3libs.bin.Wc3bin.Stream;
import net.moonlightflower.wc3libs.bin.Wc3bin.StreamException;
import net.moonlightflower.wc3libs.dataTypes.app.Coords3DF;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.ObjId;

/**
 * unit and item placements file for wrapping war3mapUnits.doo
 */
public class DOO_UNITS {
	public final static String GAME_PATH = "war3mapUnits.doo";
	
	private static class Obj {
		private ObjId _typeId;
		
		public ObjId getTypeId() {
			return _typeId;
		}
		
		public void setTypeId(ObjId val) {
			_typeId = val;
		}
		
		private int _variation = 0;
		
		public int getVariation() {
			return _variation;
		}
		
		public void setVariation(int val) {
			_variation = val;
		}
		
		private Coords3DF _pos = new Coords3DF(0F, 0F, 0F);
		
		public Coords3DF getPos() {
			return _pos;
		}
		
		public void setPos(Coords3DF val) {
			_pos = val;
		}
		
		private float _angle = 0;
		
		public float getAngle() {
			return _angle;
		}
		
		public void setAngle(float val) {
			_angle = val;
		}
		
		private Coords3DF _scale = new Coords3DF(0F, 0F, 0F);
		
		public Coords3DF getScale() {
			return _scale;
		}
		
		public void setScale(Coords3DF val) {
			_scale = val;
		}
		
		private int _flags = 0;
		
		public int getFlags() {
			return _flags;
		}
		
		public void setFlags(int val) {
			_flags = val;
		}
		
		private int _ownerIndex = 0;
		
		public int getOwnerIndex() {
			return _ownerIndex;
		}
		
		public void setOwnerIndex(int val) {
			_ownerIndex = val;
		}
		
		private int _unknown = 0;
		
		public int getUnknown() {
			return _unknown;
		}
		
		public void setUnknown(int val) {
			_unknown = val;
		}
		
		private int _unknownB = 0;
		
		public int getUnknownB() {
			return _unknownB;
		}
		
		public void setUnknownB(int val) {
			_unknownB = val;
		}
		
		private int _lifePerc = 100;
		
		public int getLifePerc() {
			return _lifePerc;
		}
		
		public void setLifePerc(int val) {
			_lifePerc = val;
		}
		
		private int _manaPerc = 100;
		
		public int getManaPerc() {
			return _manaPerc;
		}
		
		public void setManaPerc(int val) {
			_manaPerc = val;
		}
		
		private int _itemTablePtr = 0;
		
		public int getItemTablePtr() {
			return _itemTablePtr;
		}
		
		public void setItemTablePtr(int val) {
			_itemTablePtr = val;
		}
		
		private class LootSet {
			private class Item {
				private ObjId _typeId;
				
				public ObjId getTypeId() {
					return _typeId;
				}
				
				public void setTypeId(ObjId val) {
					_typeId = val;
				}
				
				private int _chance = 100;
				
				public int getChance() {
					return _chance;
				}
				
				public void setChance(int val) {
					_chance = val;
				}
				
				private void read_0x8(Stream stream) throws StreamException {
					setTypeId(ObjId.valueOf(stream.readId()));
					setChance(stream.readInt());
				}
				
				private void write_0x8(Stream stream) {
					stream.writeId(getTypeId());
					stream.writeInt(getChance());
				}

				private void read(Stream stream, EncodingFormat format) throws StreamException {		
					switch (format.toEnum()) {
					case DOO_0x8: {
						read_0x8(stream);
						
						break;
					}
					}
				}
				
				private void write(Stream stream, EncodingFormat format) {
					switch (format.toEnum()) {
					case AUTO:
					case DOO_0x8: {
						write_0x8(stream);
						
						break;
					}
					}
				}
				
				public Item(Stream stream, EncodingFormat format) throws StreamException {
					read(stream, format);
				}
				
				public Item() {
				}
			}
			
			private List<Item> _items = new ArrayList<>();
			
			private void addItem(Item val) {
				_items.add(val);
			}
			
			public Item addItem() {
				Item item = new Item();
				
				addItem(item);
				
				return item;
			}
			
			private void read_0x8(Stream stream) throws StreamException {
				int itemsCount = stream.readInt();
				
				for (int i = 1; i <= itemsCount; i++) {
					addItem(new Item(stream, EncodingFormat.DOO_0x8));
				}
			}
			
			private void write_0x8(Stream stream) {
				stream.writeInt(_items.size());
				
				for (Item item : _items) {
					item.write(stream, EncodingFormat.DOO_0x8);
				}
			}
			
			private void read(Stream stream, EncodingFormat format) throws StreamException {		
				switch (format.toEnum()) {
				case DOO_0x8: {
					read_0x8(stream);
					
					break;
				}
				}
			}
			
			private void write(Stream stream, EncodingFormat format) {
				switch (format.toEnum()) {
				case AUTO:
				case DOO_0x8: {
					write_0x8(stream);
					
					break;
				}
				}
			}
			
			public LootSet(Stream stream, EncodingFormat format) throws StreamException {
				read(stream, format);
			}
			
			public LootSet() {
			}
		}
		
		private List<LootSet> _lootSets = new ArrayList<>();
		
		private void addLootSet(LootSet val) {
			_lootSets.add(val);
		}
		
		public LootSet addLootSet() {
			LootSet set = new LootSet();
			
			addLootSet(set);
			
			return set;
		}
		
		private int _resourcesAmount = 0;
		
		public int getResourcesAmount() {
			return _resourcesAmount;
		}
		
		public void setResourcesAmount(int val) {
			_resourcesAmount = val;
		}
		
		private float _targetAcquisition = 0;

		public float targetAcquisition_NORMAL() {
			return -1;
		}

		public float targetAcquisition_CAMP() {
			return -2;
		}
		
		public float getTargetAcquisition() {
			return _targetAcquisition;
		}
		
		public void setTargetAcquisition(float val) {
			_targetAcquisition = val;
		}
		
		private int _heroLevel = 1;
		
		public int getHeroLevel() {
			return _heroLevel;
		}
		
		public void setHeroLevel(int val) {
			_heroLevel = val;
		}
		
		private int _heroStr = 0;
		private int _heroAgi = 0;
		private int _heroInt = 0;
		
		public int getHeroStr() {
			return _heroStr;
		}
		
		public int getHeroAgi() {
			return _heroAgi;
		}

		public int getHeroInt() {
			return _heroInt;
		}
		
		public void setHeroAttributes(int strVal, int agiVal, int intVal) {
			_heroStr = strVal;
			_heroAgi = agiVal;
			_heroInt = intVal;
		}
		
		private class InvItem {
			private int _slot = 0;
			
			public int getSlot() {
				return _slot;
			}
			
			public void setSlot(int val) {
				_slot = val;
			}
			
			private ObjId _typeId;
			
			public ObjId getTypeId() {
				return _typeId;
			}
			
			public void setTypeId(ObjId val) {
				_typeId = val;
			}
			
			private void read_0x8(Stream stream) throws StreamException {
				setSlot(stream.readInt());
				setTypeId(ObjId.valueOf(stream.readId()));
			}
			
			private void write_0x8(Stream stream) {
				stream.writeInt(getSlot());
				stream.writeId(getTypeId());
			}

			private void read(Stream stream, EncodingFormat format) throws StreamException {		
				switch (format.toEnum()) {
				case DOO_0x8: {
					read_0x8(stream);
					
					break;
				}
				}
			}
			
			private void write(Stream stream, EncodingFormat format) {
				switch (format.toEnum()) {
				case AUTO:
				case DOO_0x8: {
					write_0x8(stream);
					
					break;
				}
				}
			}
			
			public InvItem(Stream stream, EncodingFormat format) throws StreamException {
				read(stream, format);
			}
			
			public InvItem() {
			}
		}
		
		private List<InvItem> _invItems = new ArrayList<>();
		
		private void addInvItem(InvItem val) {
			_invItems.add(val);
		}
		
		public InvItem addInvItem() {
			InvItem item = new InvItem();
			
			addInvItem(item);
			
			return item;
		}

		private class AbilMod {
			private ObjId _typeId;
			
			public ObjId getTypeId() {
				return _typeId;
			}
			
			public void setTypeId(ObjId val) {
				_typeId = val;
			}
			
			private int _autoCast = 0;
			
			private final int autoCast_OFF = 0;
			private final int autoCast_ON = 1;
			
			public int getAutoCast() {
				return _autoCast;
			}
			
			public void setAutoCast(int val) {
				_autoCast = val;
			}
			
			private int _level = 1;
			
			public int getLevel() {
				return _level;
			}
			
			public void setLevel(int val) {
				_level = val;
			}
			
			private void read_0x8(Stream stream) throws StreamException {
				setTypeId(ObjId.valueOf(stream.readId()));
				setAutoCast(stream.readInt());
				setLevel(stream.readInt());
			}

			private void write_0x8(Stream stream) {
				stream.writeId(getTypeId());
				stream.writeInt(getAutoCast());
				stream.writeInt(getLevel());
			}
			
			private void read(Stream stream, EncodingFormat format) throws StreamException {		
				switch (format.toEnum()) {
				case DOO_0x8: {
					read_0x8(stream);
					
					break;
				}
				}
			}
			
			private void write(Stream stream, EncodingFormat format) {
				switch (format.toEnum()) {
				case AUTO:
				case DOO_0x8: {
					write_0x8(stream);
					
					break;
				}
				}
			}
			
			public AbilMod(Stream stream, EncodingFormat format) throws StreamException {
				read(stream, format);
			}
			
			public AbilMod() {
			}
		}
		
		private List<AbilMod> _abilMods = new ArrayList<>();
		
		private void addAbilMod(AbilMod val) {
			_abilMods.add(val);
		}
		
		public AbilMod addAbilMod() {
			AbilMod abil = new AbilMod();
			
			addAbilMod(abil);
			
			return abil;
		}
		
		private int _randFlag = 0;
		
		final int randFlag_ANY = 0;
		final int randFlag_RAND_GROUP = 1;
		final int randFlag_RAND_CUSTOM = 2;
		
		public int getRandFlag() {
			return _randFlag;
		}
		
		public void setRandFlag(int val) {
			_randFlag = val;
		}
		
		private int _randLevel = 0;
		
		public int getRandLevel() {
			return _randLevel;
		}
		
		public void setRandLevel(int val) {
			_randLevel = val;
		}
		
		int _randClass = 0;
		
		final int randClass_ANY_OR_SPECIFIC = 0;
		final int randClass_PERM = 1;
		
		public int getRandClass() {
			return _randClass;
		}
		
		public void setRandClass(int val) {
			_randClass = val;
		}
		
		private int _randGroupIndex = 0;
		
		public int getRandGroupIndex() {
			return _randGroupIndex;
		}
		
		public void setRandGroupIndex(int val) {
			_randGroupIndex = val;
		}
		
		private int _randGroupPos = 0;
		
		public int getRandGroupPos() {
			return _randGroupPos;
		}
		
		public void setRandGroupPos(int val) {
			_randGroupPos = val;
		}
		
		private class RandObj {
			private ObjId _typeId;
			
			public ObjId getTypeId() {
				return _typeId;
			}
			
			public void setTypeId(ObjId val) {
				_typeId = val;
			}
			
			private int _chance;
			
			public int getChance() {
				return _chance;
			}
			
			public void setChance(int val) {
				_chance = val;
			}

			private void read_0x8(Stream stream) throws StreamException {
				setTypeId(ObjId.valueOf(stream.readId()));
				setChance(stream.readInt());
			}
			
			private void write_0x8(Stream stream) {
				stream.writeId(getTypeId());
				stream.writeInt(getChance());
			}
			
			private void read(Stream stream, EncodingFormat format) throws StreamException {		
				switch (format.toEnum()) {
				case DOO_0x8: {
					read_0x8(stream);
					
					break;
				}
				}
			}
			
			private void write(Stream stream, EncodingFormat format) {
				switch (format.toEnum()) {
				case AUTO:
				case DOO_0x8: {
					write_0x8(stream);
					
					break;
				}
				}
			}
			
			public RandObj(Stream stream, EncodingFormat format) throws StreamException {
				read(stream, format);
			}
			
			public RandObj() {
			}
		}
		
		private List<RandObj> _randObjs = new ArrayList<>();
		
		private void addRandObj(RandObj val) {
			_randObjs.add(val);
		}
		
		public RandObj addRandObj() {
			RandObj obj = new RandObj();
			
			addRandObj(obj);
			
			return obj;
		}
		
		private int _customColor = 0;
		
		public int getCustomColor() {
			return _customColor;
		}
		
		public void setCustomColor(int val) {
			_customColor = val;
		}
		
		private int _waygateTargetRectIndex = 0;
		
		public int getWaygateTargetRectIndex() {
			return _waygateTargetRectIndex;
		}
		
		public void setWaygateTargetRectIndex(int val) {
			_waygateTargetRectIndex = val;
		}
		
		private int _editorId = 0;
		
		public int getEditorId() {
			return _editorId;
		}
		
		public void setEditorId(int val) {
			_editorId = val;
		}

		private void read_0x8(Stream stream) throws StreamException {
			setTypeId(ObjId.valueOf(stream.readId()));
			
			setVariation(stream.readInt());
			
			setPos(new Coords3DF(stream.readFloat(), stream.readFloat(), stream.readFloat()));
			
			setAngle(stream.readFloat());
			
			setScale(new Coords3DF(stream.readFloat(), stream.readFloat(), stream.readFloat()));
			
			setFlags(stream.readByte());
			setOwnerIndex(stream.readInt());
			setUnknown(stream.readByte());
			setUnknownB(stream.readByte());
			
			setLifePerc(stream.readInt());
			setManaPerc(stream.readInt());
			
			setItemTablePtr(stream.readInt());

			int lootsCount = stream.readInt();
			
			for (int i = 0; i < lootsCount; i++) {
				addLootSet(new LootSet(stream, EncodingFormat.DOO_0x8));
			}
			
			setResourcesAmount(stream.readInt());
			
			setTargetAcquisition(stream.readFloat());
			
			setHeroLevel(stream.readInt());
			
			setHeroAttributes(stream.readInt(), stream.readInt(), stream.readInt());
			
			int invItemsCount = stream.readInt();
			
			for (int i = 0; i < invItemsCount; i++) {
				addInvItem(new InvItem(stream, EncodingFormat.DOO_0x8));
			}
			
			int abilityModsCount = stream.readInt();
			
			for (int i = 0; i < abilityModsCount; i++) {
				addAbilMod(new AbilMod(stream, EncodingFormat.DOO_0x8));
			}
			
			int randFlag = stream.readInt();
			
			setRandFlag(randFlag);
			
			switch (randFlag) {
			case randFlag_ANY: {
				int rand1 = stream.readByte() & 0xff;
				int rand2 = stream.readByte() & 0xff;
				int rand3 = stream.readByte() & 0xff;
				
				int randLevel = rand3 * 256 * 256 + rand2 * 256 + rand1;
				
				setRandLevel(randLevel);
				
				setRandClass(stream.readByte());
				
				break;
			}
			case randFlag_RAND_GROUP: {
				setRandGroupIndex(stream.readInt());
				setRandGroupPos(stream.readInt());
				
				break;
			}
			case randFlag_RAND_CUSTOM: {
				int randUnitsCount = stream.readInt();
				
				for (int i = 0; i < randUnitsCount; i++) {
					addRandObj(new RandObj(stream, EncodingFormat.DOO_0x8));
				}
				
				break;
			}
			}
			
			setCustomColor(stream.readInt());
			setWaygateTargetRectIndex(stream.readInt());
			setEditorId(stream.readInt());
		}

		private void write_0x8(Stream stream) {
			stream.writeId(getTypeId());
			
			stream.writeInt(getVariation());
			
			Coords3DF pos = getPos();
			
			stream.writeFloat(pos.getX());
			stream.writeFloat(pos.getY());
			stream.writeFloat(pos.getZ());
			
			stream.writeFloat(getAngle());
			
			Coords3DF scale = getScale();
			
			stream.writeFloat(scale.getX());
			stream.writeFloat(scale.getY());
			stream.writeFloat(scale.getZ());
			
			stream.writeByte(getFlags());
			stream.writeInt(getOwnerIndex());
			stream.writeByte(getUnknown());
			stream.writeByte(getUnknownB());
			
			stream.writeInt(getLifePerc());
			stream.writeInt(getManaPerc());
			
			stream.writeInt(getItemTablePtr());
			
			stream.writeInt(_lootSets.size());

			for (LootSet set : _lootSets) {
				set.write(stream, EncodingFormat.DOO_0x8);
			}
			
			stream.writeInt(getResourcesAmount());
			
			stream.writeFloat(getTargetAcquisition());
			
			stream.writeInt(getHeroLevel());
			
			stream.writeInt(getHeroStr());
			stream.writeInt(getHeroAgi());
			stream.writeInt(getHeroInt());
			
			stream.writeInt(_invItems.size());
			
			for (InvItem item : _invItems) {
				item.write(stream, EncodingFormat.DOO_0x8);
			}
			
			stream.writeInt(_abilMods.size());
			
			for (AbilMod abilMod : _abilMods) {
				abilMod.write(stream, EncodingFormat.DOO_0x8);
			}
			
			int randFlag = getRandFlag();
			
			stream.writeInt(randFlag);
			
			switch (randFlag) {
			case randFlag_ANY: {
				int randLevel = getRandLevel();
				
				byte rand1 = (byte) (randLevel % 256);
				byte rand2 = (byte) (randLevel / 256 % 256);
				byte rand3 = (byte) (randLevel / (256 * 256) % 256);

				stream.writeByte(rand1);
				stream.writeByte(rand2);
				stream.writeByte(rand3);
				
				stream.writeInt(getRandLevel());
				
				stream.writeByte(getRandClass());
				
				break;
			}
			case randFlag_RAND_GROUP: {
				stream.writeInt(getRandGroupIndex());
				stream.writeInt(getRandGroupPos());
				
				break;
			}
			case randFlag_RAND_CUSTOM: {
				stream.writeInt(_randObjs.size());
				
				for (RandObj randObj : _randObjs) {
					randObj.write(stream, EncodingFormat.DOO_0x8);
				}
				
				break;
			}
			}
			
			stream.writeInt(getCustomColor());
			stream.writeInt(getWaygateTargetRectIndex());
			stream.writeInt(getEditorId());
		}
		
		private void read(Stream stream, EncodingFormat format) throws StreamException {		
			switch (format.toEnum()) {
			case DOO_0x8: {
				read_0x8(stream);
				
				break;
			}
			}
		}
		
		private void write(Stream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case DOO_0x8: {
				write_0x8(stream);
				
				break;
			}
			}
		}
		
		public Obj(Stream stream, EncodingFormat format) throws StreamException {
			read(stream, format);
		}
		
		public Obj() {
		}
	}
	
	private List<Obj> _objs = new ArrayList<>();
	
	private void addObj(Obj val) {
		_objs.add(val);
	}
	
	public Obj addObj() {
		Obj obj = new Obj();
		
		addObj(obj);
		
		return obj;
	}

	private static class EncodingFormat extends Format<EncodingFormat.Enum> {
		enum Enum {
			AUTO,
			DOO_0x8,
		}

		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat DOO_0x8 = new EncodingFormat(Enum.DOO_0x8, 0x8);

		private static Map<Integer, EncodingFormat> _map = new HashMap<>();
		
		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}
		
		private EncodingFormat(Enum enumVal, int version) {
			super(enumVal, version);
			
			_map.put(version, this);
		}
	}
	
	private void write_0x8(Stream stream) {
		stream.writeId(Id.valueOf("W3do"));
		
		stream.writeInt(EncodingFormat.DOO_0x8.getVersion());
		
		stream.writeInt(0xB);
		
		stream.writeByte(_objs.size());
	}

	private void read_0x8(Stream stream) throws StreamException {
		Id startToken = stream.readId();
		
		int version = stream.readInt(); //0xB
		
		Wc3bin.checkFormatVer("dooUnitsMaskFunc", EncodingFormat.DOO_0x8.getVersion(), version);
		
		int subVersion = stream.readInt();
		
		int objsCount = stream.readInt();
		
		for (int i = 0; i < objsCount; i++) {
			addObj(new Obj(stream, EncodingFormat.DOO_0x8));
		}
	}

	private void read_auto(Stream stream) throws StreamException {
		Id startToken = stream.readId();
		
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
		case DOO_0x8: {
			read_0x8(stream);
			
			break;
		}
		}
	}
	
	private void write(Stream stream, EncodingFormat format) {
		switch (format.toEnum()) {
		case AUTO:
		case DOO_0x8: {
			write_0x8(stream);
			
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
	
	public DOO_UNITS() {
		
	}
}
