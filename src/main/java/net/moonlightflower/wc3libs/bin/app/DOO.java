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
import java.util.*;

/**
 * doodad placements file for wrapping war3map.doo
 */
public class DOO {
	public final static File GAME_PATH = new File("war3map.doo");

	/** The sub-version Warcraft III writes, and this library's default. */
	public final static int DEFAULT_SUB_VERSION = 0xB;

	/**
	 * Whether each doodad carries the four-byte skin id Reforged added.
	 * <p>
	 * It is a property of the whole file. {@link #AUTO} works it out on read and
	 * reproduces on write whatever was read, so a file round-trips unchanged;
	 * name it explicitly when producing a file for a particular game version.
	 */
	public enum SkinIds {
		AUTO,
		PRESENT,
		ABSENT
	}

	private int _subVersion = DEFAULT_SUB_VERSION;

	/**
	 * @return the sub-version read from the file, or the one that will be
	 *         written. Sub-version 9 predates 11 and is still in circulation;
	 *         writing 11 regardless, as this used to, relabelled such a file.
	 */
	public int getSubVersion() {
		return _subVersion;
	}

	public void setSubVersion(int val) {
		_subVersion = val;
	}

	private SkinIds _skinIds = SkinIds.AUTO;

	@Nonnull
	public SkinIds getSkinIds() {
		return _skinIds;
	}

	public void setSkinIds(@Nonnull SkinIds val) {
		_skinIds = val;
	}

	public static class Dood {
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
		
		private ObjId _skinId = null;
		
		public ObjId getSkinId() {
			return _skinId;
		}
		
		public void setSkinId(ObjId val) {
			_skinId = val;
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
		
		public static class ItemSet {
            public static class Item {
				private ObjId _typeId;

				@Nonnull
				public ObjId getTypeId() {
					return _typeId;
				}
				
				public void setTypeId(@Nonnull ObjId val) {
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
			
			private final List<Item> _items = new ArrayList<>();

			@Nonnull
			public Item addItem(@Nonnull ObjId typeId, int chance) {
				Item item = new Item();
				
				_items.add(item);
				
				item.setTypeId(typeId);
				item.setChance(chance);
				
				return item;
			}
			
			private void read_0x8(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
				int itemsCount = stream.readInt32("itemsCount");

				for (int i = 0; i < itemsCount; i++) {
                    if (stream.eof()) {
                        break;
                    }
					ObjId typeId = ObjId.valueOf(stream.readId("typeId"));

                    if (stream.eof()) {
                        break;
                    }
                    int chance = stream.readInt32("chance");

                    Item item = addItem(typeId, chance);
				}
			}
			
			private void write_0x8(@Nonnull Wc3BinOutputStream stream) {
				stream.writeInt32(_items.size());
				
				for (Item item : _items) {
					stream.writeId(item.getTypeId());
					
					stream.writeInt32(item.getChance());
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

		public void read_0x8(@Nonnull Wc3BinInputStream stream, boolean withSkinId) throws BinInputStream.StreamException {
			setTypeId(ObjId.valueOf(stream.readId("typeId")));

			setVariation(stream.readInt32("variation"));

			setPos(new Coords3DF(stream.readFloat32("posX"), stream.readFloat32("posY"), stream.readFloat32("posZ")));

			setAngle(stream.readFloat32("angle"));

			setScale(new Coords3DF(stream.readFloat32("scaleX"), stream.readFloat32("scaleY"), stream.readFloat32("scaleZ")));

			// Whether a skin id is here is a property of the file, not of this
			// doodad, so it is decided once by DOO.readDoods and passed in.
			if (withSkinId) {
				setSkinId(ObjId.valueOf(stream.readId("skinId")));
			}

			setFlags(stream.readUByte("flags"));

			setLifePerc(stream.readUByte("lifePerc"));

			setItemTablePtr(stream.readInt32("itemTablePtr"));

			int itemSetsDroppedCount = stream.readInt32("itemSetsDroppedCount");

			for (int i = 0; i < itemSetsDroppedCount; i++) {
				addItemSet(new ItemSet(stream, EncodingFormat.DOO_0x8));
			}

			setEditorId(stream.readInt32("editorId"));
		}

		public void write_0x8(@Nonnull Wc3BinOutputStream stream, boolean withSkinId) {
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

			// Every doodad in a file that has skin ids needs one, so an unset
			// skin falls back to the doodad's own type, which is what the editor
			// stores for a doodad using its default skin. Writing the field only
			// where it happened to be set, as this did, produced a file whose
			// doodads were no longer a fixed distance apart.
			if (withSkinId) {
				stream.writeId(getSkinId() != null ? getSkinId() : getTypeId());
			}

			stream.writeUByte(getFlags());

			stream.writeUByte(getLifePerc());

			stream.writeInt32(getItemTablePtr());

			stream.writeInt32(_itemSets.size());

			for (ItemSet set : _itemSets) {
				set.write(stream, EncodingFormat.DOO_0x8);
			}

			stream.writeInt32(_editorId);
		}

		public void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format, boolean withSkinId) throws BinInputStream.StreamException {
			switch (format.toEnum()) {
			case DOO_0x8: {
				read_0x8(stream, withSkinId);

				break;
			}
			}
		}

		public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format, boolean withSkinId) {
			switch (format.toEnum()) {
			case AUTO:
			case DOO_0x8: {
				write_0x8(stream, withSkinId);

				break;
			}
			}
		}

		public Dood(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format, boolean withSkinId) throws BinInputStream.StreamException {
			read(stream, format, withSkinId);
		}
		
		public Dood() {
		}
	}
	
	private List<Dood> _doods = new ArrayList<>();

	public List<Dood> getDoods() {
		return new ArrayList<>(_doods);
	}

	public void addDood(@Nonnull Dood val) {
		_doods.add(val);
	}

	public void removeDood(@Nonnull Dood val) {
		_doods.remove(val);
	}

	public void clearDoods() {
		_doods.clear();
	}

	@Nonnull
	public Dood addDood() {
		Dood dood = new Dood();
		
		addDood(dood);
		
		return dood;
	}
	
	public static class SpecialDood {
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
			_typeId = ObjId.valueOf(stream.readId("typeId"));
			
			int z = stream.readInt32("z");
			int x = stream.readInt32("x");
			int y = stream.readInt32("y");
			
			_pos = new Coords3DI(x, y, z);
		}
		
		private void write_0x0(@Nonnull Wc3BinOutputStream stream) {
			stream.writeId(getTypeId());
			
			Coords3DI pos = getPos();
			
			stream.writeInt32(pos.getZ());
			stream.writeInt32(pos.getX());
			stream.writeInt32(pos.getY());
		}
		
		public void read(@Nonnull Wc3BinInputStream stream, @Nonnull Special.EncodingFormat format) throws BinInputStream.StreamException {
			switch (format.toEnum()) {
			case DOO_SPECIAL_0x0: {
				read_0x0(stream);
				
				break;
			}
			}
		}
		
		public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull Special.EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case DOO_SPECIAL_0x0: {
				write_0x0(stream);
				
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
	
	private final List<SpecialDood> _specialDoods = new ArrayList<>();

	public List<SpecialDood> getSpecialDoods() {
		return new ArrayList<>(_specialDoods);
	}

	public void addSpecialDood(@Nonnull SpecialDood val) {
		_specialDoods.add(val);
	}

	public void removeSpecialDood(@Nonnull SpecialDood val) {
		_specialDoods.remove(val);
	}

	public void clearSpecialDoods() {
		_specialDoods.clear();
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

			private final static Map<Integer, EncodingFormat> _map = new LinkedHashMap<>();

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
			int specialVersion = stream.readInt32("specialVersion");

			if (specialVersion != 0) throw new IllegalArgumentException("unknown special format " + specialVersion + " (should be 0)");

			int specialDoodsCount = stream.readInt32("specialDoodsCount");

			for (int i = 0; i < specialDoodsCount; i++) {
				_parent.addSpecialDood(new SpecialDood(stream, EncodingFormat.DOO_SPECIAL_0x0));
			}
		}
		
		private void write_0x0(@Nonnull Wc3BinOutputStream stream) {
			stream.writeInt32(EncodingFormat.DOO_SPECIAL_0x0.getVersion());

			stream.writeInt32(_parent._specialDoods.size());
			
			for (SpecialDood dood : _parent._specialDoods) {
				dood.write(stream, EncodingFormat.DOO_SPECIAL_0x0);
			}
		}
		
		private void read_auto(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			int specialVersion = stream.readInt32("specialVersion");

			stream.rewind(4);

			EncodingFormat format = EncodingFormat.valueOf(specialVersion);

			if (format == null) throw new IllegalArgumentException("unknown format " + specialVersion);

			read(stream, format);
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
	
	private void read_0x8(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		Id startToken = stream.readId("startToken");

		int version = stream.readInt32("version");

		stream.checkFormatVersion(EncodingFormat.DOO_0x8.getVersion(), version);

		_subVersion = stream.readInt32("subVersion");

		int doodsCount = stream.readInt32("doodsCount");

		readDoods(stream, doodsCount);
	}

	/**
	 * Reads the doodad array, working out first whether its entries carry a skin
	 * id.
	 * <p>
	 * The sub-version does not say: files exist with sub-version 11 and no skin
	 * ids, and others with sub-version 11 and skin ids. What does say is the
	 * arithmetic. The two layouts differ by exactly four bytes per doodad, so at
	 * most one of them can consume the array and leave the special-doodad
	 * section it is followed by intact. That is decidable, unlike the previous
	 * approach of peeking one byte and assuming a value above 7 could not be a
	 * flags byte -- which a skin id of four NULs, or one whose first character is
	 * a control code, defeats.
	 */
	private void readDoods(@Nonnull Wc3BinInputStream stream, int doodsCount) throws BinInputStream.StreamException {
		if (_skinIds != SkinIds.AUTO) {
			readDoods(stream, doodsCount, _skinIds == SkinIds.PRESENT);

			return;
		}

		long start = stream.getPos();

		boolean fitsWithout = fits(stream, start, doodsCount, false);
		boolean fitsWith = fits(stream, start, doodsCount, true);

		// Exactly one layout accounting for the file is the answer. Both fitting
		// is possible in principle, because a misparse can absorb the four-byte
		// difference into an item-set count, so that is treated as no answer at
		// all rather than as a reason to prefer one.
		if (fitsWithout != fitsWith) {
			stream.setPos(start);

			_skinIds = fitsWith ? SkinIds.PRESENT : SkinIds.ABSENT;

			readDoods(stream, doodsCount, fitsWith);

			return;
		}

		// Fall back to the old guess rather than refusing a file this library
		// used to read: a flags byte only ever uses its low three bits, so a
		// larger value is more likely to be the first character of a skin id.
		stream.setPos(start);

		boolean withSkinIds = doodsCount > 0 && peekSkinIdAt(stream, start + SKIN_ID_OFFSET);

		_skinIds = withSkinIds ? SkinIds.PRESENT : SkinIds.ABSENT;

		readDoods(stream, doodsCount, withSkinIds);
	}

	private void readDoods(@Nonnull Wc3BinInputStream stream, int doodsCount, boolean withSkinIds)
		throws BinInputStream.StreamException {
		for (int i = 0; i < doodsCount; i++) {
			addDood(new Dood(stream, EncodingFormat.DOO_0x8, withSkinIds));
		}
	}

	/** Offset of the byte that is either a skin id's first character or the flags. */
	private final static int SKIN_ID_OFFSET = 4 + 4 + 12 + 4 + 12;

	private static boolean peekSkinIdAt(@Nonnull Wc3BinInputStream stream, long pos) {
		if (pos >= stream.size()) return false;

		return (stream.get(pos) & 0xFF) > 0x07;
	}

	/**
	 * @return whether reading {@code doodsCount} doodads in the given layout
	 *         consumes the array and leaves exactly a well-formed special-doodad
	 *         section, or nothing, behind it.
	 */
	private static boolean fits(@Nonnull Wc3BinInputStream stream, long start, int doodsCount, boolean withSkinIds) {
		try {
			stream.setPos(start);

			for (int i = 0; i < doodsCount; i++) {
				new Dood(stream, EncodingFormat.DOO_0x8, withSkinIds);
			}

			long remaining = stream.size() - stream.getPos();

			// Nothing after the doodads at all is a valid, if unusual, file.
			if (remaining == 0) return true;

			if (remaining < SPECIAL_HEADER_SIZE) return false;

			int specialVersion = stream.readInt32();
			int specialCount = stream.readInt32();

			if (specialVersion != 0 || specialCount < 0) return false;

			return remaining == SPECIAL_HEADER_SIZE + (long) specialCount * SPECIAL_DOOD_SIZE;
		} catch (BinInputStream.StreamException | RuntimeException e) {
			// Running off the end of the file, or reading an absurd item-set
			// count, means this was the wrong layout.
			return false;
		}
	}

	/** The special-doodad section's own version and count. */
	private final static int SPECIAL_HEADER_SIZE = 4 + 4;

	/** A special doodad: its type and three integer coordinates. */
	private final static int SPECIAL_DOOD_SIZE = 4 + 4 + 4 + 4;

	private void write_0x8(@Nonnull Wc3BinOutputStream stream) {
		stream.writeId(Id.valueOf("W3do"));

		stream.writeInt32(EncodingFormat.DOO_0x8.getVersion());

		stream.writeInt32(_subVersion);

		stream.writeInt32(_doods.size());

		boolean withSkinIds = writesSkinIds();

		for (Dood dood : _doods) {
			dood.write(stream, EncodingFormat.DOO_0x8, withSkinIds);
		}
	}

	/**
	 * Whether the doodads will be written with a skin id.
	 * <p>
	 * A file's doodads are all one shape, so this is all-or-nothing: it is on if
	 * the caller asked for it, or if any doodad has a skin to record. Writing
	 * the field per doodad, as this used to, produced a file nothing could read.
	 */
	private boolean writesSkinIds() {
		return switch (_skinIds) {
			case PRESENT -> true;
			case ABSENT -> false;
			case AUTO -> _doods.stream().anyMatch(dood -> dood.getSkinId() != null);
		};
	}
	
	private void read_auto(@Nonnull Wc3BinInputStream stream) throws IOException {
		Id startToken = stream.readId("startToken");
		
		int version = stream.readInt32("version");
		
		stream.rewind();

		EncodingFormat format = EncodingFormat.valueOf(version);

		if (format == null) throw new IOException(String.format("unknown format %x", version));

		read(stream, format, null);
	}
	
	private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format, @Nullable Special.EncodingFormat specialFormat) throws IOException {
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
	
	private void read(@Nonnull Wc3BinInputStream stream) throws IOException {
		read(stream, EncodingFormat.AUTO, Special.EncodingFormat.AUTO);
	}
	
	public void write(@Nonnull Wc3BinOutputStream stream) {
		write(stream, EncodingFormat.AUTO, Special.EncodingFormat.AUTO);
	}

	public void write(@Nonnull File file) throws IOException {
		Wc3BinOutputStream outStream = new Wc3BinOutputStream(file);

		write(outStream);

		outStream.close();
	}

	public DOO(@Nonnull Wc3BinInputStream stream) throws Exception {
		read(stream);
	}

	public DOO(@Nonnull File file) throws IOException {
		Wc3BinInputStream inStream = new Wc3BinInputStream(file);

		read(inStream);

		inStream.close();
	}

	public DOO() {
	}
}
