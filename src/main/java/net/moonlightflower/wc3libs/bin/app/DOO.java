package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.Format;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.app.Coords3DF;
import net.moonlightflower.wc3libs.dataTypes.app.Coords3DI;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.ObjId;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * doodad placements file for wrapping war3map.doo
 */
public class DOO {
	public final static File GAME_PATH = new File("war3map.doo");
	
	private class Dood {
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
		
		private byte _lifePerc = 1;
		
		public int getLifePerc() {
			return _lifePerc;
		}
		
		public void setLifePerc(int val) {
			_lifePerc = (byte) val;
		}
		
		private int _flags = 0;
		
		public int getFlags() {
			return _flags;
		}
		
		public void setFlags(int val) {
			_flags = val;
		}
		
		private int _itemTablePtr = 0;
		
		public int getItemTablePtr() {
			return _itemTablePtr;
		}
		
		public void setItemTablePtr(int val) {
			_itemTablePtr = val;
		}
		
		private class ItemSet {
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
			}
			
			private List<Item> _items = new ArrayList<>();
			
			public Item addItem(ObjId typeId, int chance) {
				Item item = new Item();
				
				_items.add(item);
				
				item.setTypeId(typeId);
				item.setChance(chance);
				
				return item;
			}
			
			private void read_0x8(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
				int itemsCount = stream.readInt();

				for (int i = 0; i < itemsCount; i++) {
					ObjId typeId = ObjId.valueOf(stream.readId());
					
					int chance = stream.readInt();
					
					Item item = addItem(typeId, chance);
				}
			}
			
			private void write_0x8(@Nonnull Wc3BinOutputStream stream) {
				stream.writeInt(_items.size());
				
				for (Item item : _items) {
					stream.writeId(item.getTypeId());
					
					stream.writeInt(item.getChance());
				}
			}
			
			public void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
				switch (format.toEnum()) {
				case DOO_0x8: {
					read_0x8(stream);
					
					break;
				}
				}
			}
			
			public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
				switch (format.toEnum()) {
				case AUTO:
				case DOO_0x8: {
					write_0x8(stream);
					
					break;
				}
				}
			}
			
			public ItemSet(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
				read(stream, format);
			}
			
			public ItemSet() {
			}
		}
		
		private List<ItemSet> _itemSets = new ArrayList<>();
		
		private void addItemSet(ItemSet val) {
			_itemSets.add(val);
		}
		
		public ItemSet addItemSet() {
			ItemSet set = new ItemSet();
			
			addItemSet(set);
			
			return set;
		}
		
		private int _editorId = 0;
				
		public int getEditorId() {
			return _editorId;
		}
		
		public void setEditorId(int val) {
			_editorId = val;
		}

		public void read_0x8(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			setTypeId(ObjId.valueOf(stream.readId()));
			
			setVariation(stream.readInt());
			
			setPos(new Coords3DF(stream.readFloat(), stream.readFloat(), stream.readFloat()));
			
			setAngle(stream.readFloat());
			
			setScale(new Coords3DF(stream.readFloat(), stream.readFloat(), stream.readFloat()));
			
			setLifePerc(stream.readUByte());
			
			setFlags(stream.readUByte());
			
			setItemTablePtr(stream.readInt());

			int itemsDroppedCount = stream.readInt();
			
			for (int i = 0; i < itemsDroppedCount; i++) {
				addItemSet(new ItemSet(stream, EncodingFormat.DOO_0x8));
			}

			setEditorId(stream.readInt());
		}
		
		public void write_0x8(@Nonnull Wc3BinOutputStream stream) {
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
			
			stream.writeUByte(getLifePerc());
			
			stream.writeUByte(getFlags());
			
			stream.writeInt(getItemTablePtr());
			
			stream.writeInt(_itemSets.size());
			
			for (ItemSet set : _itemSets) {
				set.write(stream, EncodingFormat.DOO_0x8);
			}
		}
		
		public void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			switch (format.toEnum()) {
			case DOO_0x8: {
				read_0x8(stream);
				
				break;
			}
			}
		}
		
		public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case DOO_0x8: {
				write_0x8(stream);
				
				break;
			}
			}
		}
		
		public Dood(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			read(stream, format);
		}
		
		public Dood() {
		}
	}
	
	private List<Dood> _doods = new ArrayList<>();
	
	private void addDood(Dood val) {
		_doods.add(val);
	}
	
	public Dood addDood() {
		Dood dood = new Dood();
		
		addDood(dood);
		
		return dood;
	}
	
	private class SpecialDood {
		private ObjId _typeId;

		@Nonnull
		public ObjId getTypeId() {
			return _typeId;
		}
		
		public void setTypeId(@Nonnull ObjId val) {
			_typeId = val;
		}
		
		private Coords3DI _pos = new Coords3DI(0, 0, 0);

		@Nonnull
		public Coords3DI getPos() {
			return _pos;
		}
		
		public void setPos(@Nonnull Coords3DI val) {
			_pos = val;
		}
		
		private void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			ObjId typeId = ObjId.valueOf(stream.readId());
			
			int z = stream.readInt();
			int x = stream.readInt();
			int y = stream.readInt();
			
			setPos(new Coords3DI(x, y, z));
		}
		
		private void write_0x0(@Nonnull Wc3BinOutputStream stream) {
			stream.writeId(getTypeId());
			
			Coords3DI pos = getPos();
			
			stream.writeInt(pos.getZ());
			stream.writeInt(pos.getX());
			stream.writeInt(pos.getY());
		}
		
		public void read(@Nonnull Wc3BinInputStream stream, @Nonnull Special.EncodingFormat format) throws BinInputStream.StreamException {
			switch (format.toEnum()) {
			case DOO_SPECIAL_0x0: {
				read_0x8(stream);
				
				break;
			}
			}
		}
		
		public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull Special.EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case DOO_SPECIAL_0x0: {
				write_0x8(stream);
				
				break;
			}
			}
		}
		
		public SpecialDood(@Nonnull Wc3BinInputStream stream, @Nonnull Special.EncodingFormat format) throws BinInputStream.StreamException {
			read(stream, format);
		}
		
		public SpecialDood() {
		}
	}
	
	private List<SpecialDood> _specialDoods = new ArrayList<>();
	
	private void addSpecialDood(@Nonnull SpecialDood val) {
		_specialDoods.add(val);
	}

	@Nonnull
	public SpecialDood addSpecialDood() {
		SpecialDood dood = new SpecialDood();
		
		addSpecialDood(dood);
		
		return dood;
	}

	public static class Special {
		private final DOO _parent;
		
		public static class EncodingFormat extends Format<EncodingFormat.Enum> {
			public enum Enum {
				AUTO,
				DOO_SPECIAL_0x0,
			}

			static Map<Integer, EncodingFormat> _map = new LinkedHashMap<>();

			public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
			public final static EncodingFormat DOO_SPECIAL_0x0 = new EncodingFormat(Enum.DOO_SPECIAL_0x0, 0x0);

			@Nullable
			public static EncodingFormat valueOf(int version) {
				return _map.get(version);
			}
			
			private EncodingFormat(@Nonnull Enum enumVal, int version) {
				super(enumVal, version);
				
				_map.put(version, this);
			}
		}
		
		private void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			int specialVersion = stream.readInt();
			
			int specialDoodsCount = stream.readInt();
			
			for (int i = 0; i < specialDoodsCount; i++) {
				_parent.addSpecialDood(_parent.new SpecialDood(stream, EncodingFormat.DOO_SPECIAL_0x0));
			}
		}
		
		private void write_0x0(@Nonnull Wc3BinOutputStream stream) {
			stream.writeInt(EncodingFormat.DOO_SPECIAL_0x0.getVersion());
			
			stream.writeInt(_parent._specialDoods.size());
			
			for (SpecialDood dood : _parent._specialDoods) {
				dood.write(stream, EncodingFormat.DOO_SPECIAL_0x0);
			}
		}
		
		private void read_auto(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			int specialVersion = stream.readInt();
			
			stream.rewind();

			read(stream, EncodingFormat.valueOf(specialVersion));
		}
		
		private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			switch (format.toEnum()) {
			case AUTO: {
				read_auto(stream);
				
				break;
			}
			case DOO_SPECIAL_0x0: {
				read_0x0(stream);
				
				break;
			}
			}
		}
		
		private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case DOO_SPECIAL_0x0: {
				write_0x0(stream);
				
				break;
			}
			}
		}
		
		private Special(@Nonnull DOO parent) {
			_parent = parent;
		}
	}
	
	private static class EncodingFormat extends Format<EncodingFormat.Enum> {
		public enum Enum {
			AUTO,
			DOO_0x8,
		}

		static Map<Integer, EncodingFormat> _map = new LinkedHashMap<>();

		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat DOO_0x8 = new EncodingFormat(Enum.DOO_0x8, 0x8);

		@Nullable
		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}
		
		private EncodingFormat(Enum enumVal, int version) {
			super(enumVal, version);
			
			_map.put(version, this);
		}
	}
	
	private void read_0x8(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		Id startToken = stream.readId();
		
		int version = stream.readInt();
		
		Wc3BinInputStream.checkFormatVer("dooMaskFunc", EncodingFormat.DOO_0x8.getVersion(), version);
		
		int subVersion = stream.readInt(); //0xB
		
		int doodsCount = stream.readInt();
		
		for (int i = 0; i < doodsCount; i++) {
			addDood(new Dood(stream, EncodingFormat.DOO_0x8));
		}
	}
	
	private void write_0x8(@Nonnull Wc3BinOutputStream stream) {
		stream.writeId(Id.valueOf("W3do"));
		
		stream.writeInt(EncodingFormat.DOO_0x8.getVersion());
		
		stream.writeInt(0xB);
		
		stream.writeInt(_doods.size());
		
		for (Dood dood : _doods) {
			dood.write(stream, EncodingFormat.DOO_0x8);
		}
	}
	
	private void read_auto(@Nonnull Wc3BinInputStream stream) throws Exception {
		Id startToken = stream.readId();
		
		int version = stream.readInt();
		
		stream.rewind();

		EncodingFormat format = EncodingFormat.valueOf(version);

		if (format == null) throw new Exception(String.format("unknown format %x", version));

		read(stream, format, null);
	}
	
	private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format, @Nullable Special.EncodingFormat specialFormat) throws Exception {
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
		
		if (specialFormat == null) return;
		
		new Special(this).read(stream, specialFormat);
	}
	
	private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format, @Nullable Special.EncodingFormat specialFormat) {
		switch (format.toEnum()) {
		case AUTO:
		case DOO_0x8: {
			write_0x8(stream);
			
			break;
		}
		}
		
		if (specialFormat == null) return;
		
		new Special(this).write(stream, specialFormat);
	}
	
	private void read(@Nonnull Wc3BinInputStream stream) throws Exception {
		read(stream, EncodingFormat.AUTO, Special.EncodingFormat.AUTO);
	}
	
	private void write(@Nonnull Wc3BinOutputStream stream) {
		write(stream, EncodingFormat.AUTO, Special.EncodingFormat.AUTO);
	}

	private void write(@Nonnull File file) throws IOException {
		Wc3BinOutputStream outStream = new Wc3BinOutputStream(file);

		write(outStream);

		outStream.close();
	}

	public DOO(@Nonnull File file) throws Exception {
		Wc3BinInputStream inStream = new Wc3BinInputStream(file);

		read(inStream);

		inStream.close();
	}

	public DOO() {
	}
}
