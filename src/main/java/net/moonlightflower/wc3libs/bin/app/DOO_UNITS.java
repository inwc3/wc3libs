package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.dataTypes.app.Coords3DF;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.ObjId;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * unit and item placements file for wrapping war3mapUnits.doo
 */
public class DOO_UNITS {
	public final static File GAME_PATH = new File("war3mapUnits.doo");
	
	public static class Obj {
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
		
		private int _unknownA = 0;
		
		public int getUnknownA() {
			return _unknownA;
		}
		
		public void setUnknownA(int val) {
			_unknownA = val;
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
		
		public static class LootSet {
            public static class Item {
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
				
				private void read_0x8(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
					setTypeId(ObjId.valueOf(stream.readId("typeId")));
					setChance(stream.readInt32("chance"));
				}
				
				private void write_0x8(@Nonnull Wc3BinOutputStream stream) {
					stream.writeId(getTypeId());
					stream.writeInt32(getChance());
				}

				private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
					switch (format.toEnum()) {
					case DOO_0x8: {
						read_0x8(stream);
						
						break;
					}
					}
				}
				
				private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
					switch (format.toEnum()) {
					case AUTO:
					case DOO_0x8: {
						write_0x8(stream);
						
						break;
					}
					}
				}
				
				public Item(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
					read(stream, format);
				}
				
				public Item() {
				}
			}
			
			private List<Item> _items = new ArrayList<>();
			
			private void addItem(@Nonnull Item val) {
				_items.add(val);
			}

			@Nonnull
			public Item addItem() {
				Item item = new Item();
				
				addItem(item);
				
				return item;
			}
			
			private void read_0x8(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
				int itemsCount = stream.readInt32("itemsCount");
				
				for (int i = 1; i <= itemsCount; i++) {
					addItem(new Item(stream, EncodingFormat.DOO_0x8));
				}
			}
			
			private void write_0x8(@Nonnull Wc3BinOutputStream stream) {
				stream.writeInt32(_items.size());
				
				for (Item item : _items) {
					item.write(stream, EncodingFormat.DOO_0x8);
				}
			}
			
			private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
				switch (format.toEnum()) {
				case DOO_0x8: {
					read_0x8(stream);
					
					break;
				}
				}
			}
			
			private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
				switch (format.toEnum()) {
				case AUTO:
				case DOO_0x8: {
					write_0x8(stream);
					
					break;
				}
				}
			}
			
			public LootSet(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
				read(stream, format);
			}
			
			public LootSet() {
			}
		}
		
		private List<LootSet> _lootSets = new ArrayList<>();
		
		private void addLootSet(@Nonnull LootSet val) {
			_lootSets.add(val);
		}

		@Nonnull
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
		
		public class InvItem {
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
			
			private void read_0x8(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
				setSlot(stream.readInt32("slot"));
				setTypeId(ObjId.valueOf(stream.readId("typeId")));
			}
			
			private void write_0x8(@Nonnull Wc3BinOutputStream stream) {
				stream.writeInt32(getSlot());
				stream.writeId(getTypeId());
			}

			private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
				switch (format.toEnum()) {
				case DOO_0x8: {
					read_0x8(stream);
					
					break;
				}
				}
			}
			
			private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
				switch (format.toEnum()) {
				case AUTO:
				case DOO_0x8: {
					write_0x8(stream);
					
					break;
				}
				}
			}
			
			public InvItem(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
				read(stream, format);
			}
			
			public InvItem() {
			}
		}
		
		private List<InvItem> _invItems = new ArrayList<>();
		
		private void addInvItem(@Nonnull InvItem val) {
			_invItems.add(val);
		}

		@Nonnull
		public InvItem addInvItem() {
			InvItem item = new InvItem();
			
			addInvItem(item);
			
			return item;
		}

		public class AbilMod {
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
			
			private void read_0x8(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
				setTypeId(ObjId.valueOf(stream.readId("typeId")));
				setAutoCast(stream.readInt32("autoCast"));
				setLevel(stream.readInt32("level"));
			}

			private void write_0x8(@Nonnull Wc3BinOutputStream stream) {
				stream.writeId(getTypeId());
				stream.writeInt32(getAutoCast());
				stream.writeInt32(getLevel());
			}
			
			private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
				switch (format.toEnum()) {
				case DOO_0x8: {
					read_0x8(stream);
					
					break;
				}
				}
			}
			
			private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
				switch (format.toEnum()) {
				case AUTO:
				case DOO_0x8: {
					write_0x8(stream);
					
					break;
				}
				}
			}
			
			public AbilMod(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
				read(stream, format);
			}
			
			public AbilMod() {
			}
		}
		
		private List<AbilMod> _abilMods = new ArrayList<>();
		
		private void addAbilMod(@Nonnull AbilMod val) {
			_abilMods.add(val);
		}

		@Nonnull
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
		
		public class RandObj {
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

			private void read_0x8(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
				setTypeId(ObjId.valueOf(stream.readId("typeId")));
				setChance(stream.readInt32("chance"));
			}
			
			private void write_0x8(@Nonnull Wc3BinOutputStream stream) {
				stream.writeId(getTypeId());
				stream.writeInt32(getChance());
			}
			
			private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
				switch (format.toEnum()) {
				case DOO_0x8: {
					read_0x8(stream);
					
					break;
				}
				}
			}
			
			private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
				switch (format.toEnum()) {
				case AUTO:
				case DOO_0x8: {
					write_0x8(stream);
					
					break;
				}
				}
			}
			
			public RandObj(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
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

		private void read_0x8(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			setTypeId(ObjId.valueOf(stream.readId("typeId")));
			
			setVariation(stream.readInt32("variation"));
			
			setPos(new Coords3DF(stream.readFloat32("posX"), stream.readFloat32("posY"), stream.readFloat32("posZ")));
			
			setAngle(stream.readFloat32("angle"));
			
			setScale(new Coords3DF(stream.readFloat32("scaleX"), stream.readFloat32("scaleY"), stream.readFloat32("scaleZ")));
			
			setFlags(stream.readByte("flags"));
			setOwnerIndex(stream.readInt32("ownerIndex"));
			setUnknownA(stream.readByte("unknownA"));
			setUnknownB(stream.readByte("unknownB"));
			
			setLifePerc(stream.readInt32("lifePerc"));
			setManaPerc(stream.readInt32("manaPerc"));
			
			setItemTablePtr(stream.readInt32("itemTablePtr"));

			int lootsCount = stream.readInt32("lootsCount");
			
			for (int i = 0; i < lootsCount; i++) {
				addLootSet(new LootSet(stream, EncodingFormat.DOO_0x8));
			}
			
			setResourcesAmount(stream.readInt32("resourcesAmount"));
			
			setTargetAcquisition(stream.readFloat32("targetAcquisition"));
			
			setHeroLevel(stream.readInt32("heroLevel"));
			
			setHeroAttributes(stream.readInt32("heroStr"), stream.readInt32("heroAgi"), stream.readInt32("heroInt"));
			
			int invItemsCount = stream.readInt32("invItemsCount");
			
			for (int i = 0; i < invItemsCount; i++) {
				addInvItem(new InvItem(stream, EncodingFormat.DOO_0x8));
			}
			
			int abilModsCount = stream.readInt32("abilModsCount");
			
			for (int i = 0; i < abilModsCount; i++) {
				addAbilMod(new AbilMod(stream, EncodingFormat.DOO_0x8));
			}
			
			int randFlag = stream.readInt32("randFlag");
			
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
				setRandGroupIndex(stream.readInt32());
				setRandGroupPos(stream.readInt32());
				
				break;
			}
			case randFlag_RAND_CUSTOM: {
				int randUnitsCount = stream.readInt32();
				
				for (int i = 0; i < randUnitsCount; i++) {
					addRandObj(new RandObj(stream, EncodingFormat.DOO_0x8));
				}
				
				break;
			}
			}
			
			setCustomColor(stream.readInt32("customColor"));
			setWaygateTargetRectIndex(stream.readInt32("waygateTargetRectIndex"));
			setEditorId(stream.readInt32("editorId"));
		}

		private void write_0x8(@Nonnull Wc3BinOutputStream stream) {
			stream.writeId(getTypeId());
			
			stream.writeInt32(getVariation());
			
			Coords3DF pos = getPos();
			
			stream.writeFloat32(pos.getX());
			stream.writeFloat32(pos.getY());
			stream.writeFloat32(pos.getZ());
			
			stream.writeFloat32(getAngle());
			
			Coords3DF scale = getScale();
			
			stream.writeFloat32(scale.getX());
			stream.writeFloat32(scale.getY());
			stream.writeFloat32(scale.getZ());
			
			stream.writeUByte(getFlags());
			stream.writeInt32(getOwnerIndex());
			stream.writeUByte(getUnknownA());
			stream.writeUByte(getUnknownB());
			
			stream.writeInt32(getLifePerc());
			stream.writeInt32(getManaPerc());
			
			stream.writeInt32(getItemTablePtr());
			
			stream.writeInt32(_lootSets.size());

			for (LootSet set : _lootSets) {
				set.write(stream, EncodingFormat.DOO_0x8);
			}
			
			stream.writeInt32(getResourcesAmount());
			
			stream.writeFloat32(getTargetAcquisition());
			
			stream.writeInt32(getHeroLevel());
			
			stream.writeInt32(getHeroStr());
			stream.writeInt32(getHeroAgi());
			stream.writeInt32(getHeroInt());
			
			stream.writeInt32(_invItems.size());
			
			for (InvItem item : _invItems) {
				item.write(stream, EncodingFormat.DOO_0x8);
			}
			
			stream.writeInt32(_abilMods.size());
			
			for (AbilMod abilMod : _abilMods) {
				abilMod.write(stream, EncodingFormat.DOO_0x8);
			}
			
			int randFlag = getRandFlag();
			
			stream.writeInt32(randFlag);
			
			switch (randFlag) {
			case randFlag_ANY: {
				int randLevel = getRandLevel();
				
				byte rand1 = (byte) (randLevel % 256);
				byte rand2 = (byte) (randLevel / 256 % 256);
				byte rand3 = (byte) (randLevel / (256 * 256) % 256);

				stream.writeByte(rand1);
				stream.writeByte(rand2);
				stream.writeByte(rand3);
				
				stream.writeUByte(getRandClass());
				
				break;
			}
			case randFlag_RAND_GROUP: {
				stream.writeInt32(getRandGroupIndex());
				stream.writeInt32(getRandGroupPos());
				
				break;
			}
			case randFlag_RAND_CUSTOM: {
				stream.writeInt32(_randObjs.size());
				
				for (RandObj randObj : _randObjs) {
					randObj.write(stream, EncodingFormat.DOO_0x8);
				}
				
				break;
			}
			}
			
			stream.writeInt32(getCustomColor());
			stream.writeInt32(getWaygateTargetRectIndex());
			stream.writeInt32(getEditorId());
		}
		
		private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			switch (format.toEnum()) {
			case DOO_0x8: {
				read_0x8(stream);
				
				break;
			}
			}
		}
		
		private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case DOO_0x8: {
				write_0x8(stream);
				
				break;
			}
			}
		}
		
		public Obj(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			read(stream, format);
		}
		
		public Obj() {
		}
	}

	private List<Obj> _objs = new ArrayList<>();

	@Nonnull
	public List<Obj> getObjs() {
		return new ArrayList<>(_objs);
	}

	private void addObj(@Nullable Obj val) {
		_objs.add(val);
	}

	@Nonnull
	public Obj addObj() {
		Obj obj = new Obj();
		
		addObj(obj);
		
		return obj;
	}

	public static class EncodingFormat extends Format<EncodingFormat.Enum> {
		public enum Enum {
			AUTO,
			DOO_0x8,
		}

		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat DOO_0x8 = new EncodingFormat(Enum.DOO_0x8, 0x8);

		@Nullable
		public static EncodingFormat valueOf(@Nonnull Integer version) {
			return get(EncodingFormat.class, version);
		}

		private EncodingFormat(@Nonnull Enum enumVal, int version) {
			super(enumVal, version);
		}
	}
	
	private void write_0x8(@Nonnull Wc3BinOutputStream stream) {
		stream.writeId(Id.valueOf("W3do"));
		
		stream.writeInt32(EncodingFormat.DOO_0x8.getVersion());
		
		stream.writeInt32(0xB); //subVersion
		
		stream.writeInt32(_objs.size());

		for (Obj obj : _objs) {
			obj.write(stream, EncodingFormat.DOO_0x8);
		}
	}

	private void read_0x8(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		Id startToken = stream.readId("startToken");
		
		int version = stream.readInt32("version"); //0xB
		
		stream.checkFormatVersion(EncodingFormat.DOO_0x8.getVersion(), version);
		
		int subVersion = stream.readInt32("subVersion"); //usually 0xB
		
		int objsCount = stream.readInt32("objsCount");

		for (int i = 0; i < objsCount; i++) {
			addObj(new Obj(stream, EncodingFormat.DOO_0x8));
		}
	}

	private void read_auto(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		Id startToken = stream.readId("startToken");
		
		int version = stream.readInt32("version");
		
		stream.rewind();

		read(stream, stream.getFormat(EncodingFormat.class, version));
	}

	private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
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
	
	private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
		switch (format.toEnum()) {
		case AUTO:
		case DOO_0x8: {
			write_0x8(stream);
			
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
	
	private void read(@Nonnull File file) throws IOException {
		Wc3BinInputStream inStream = new Wc3BinInputStream(file);

		read(inStream, EncodingFormat.AUTO);

		inStream.close();
	}

	public void write(@Nonnull File file) throws IOException {
		Wc3BinOutputStream outStream = new Wc3BinOutputStream(file);

		write(outStream);

		outStream.close();
	}

	public DOO_UNITS(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
		read(stream);
	}

	public DOO_UNITS() {
		
	}
}
