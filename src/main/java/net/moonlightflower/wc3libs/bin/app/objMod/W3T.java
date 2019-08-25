package net.moonlightflower.wc3libs.bin.app.objMod;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.app.objs.ItemSLK;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

/**
 * item modifications file for wrapping war3map.w3t
 */
public class W3T extends ObjMod<W3T.Item> {
	public final static File GAME_PATH = new File("war3map.w3t");
	public final static File CAMPAIGN_PATH = new File("war3campaign.w3t");

	public W3T(@Nonnull Wc3BinInputStream stream) throws IOException {
		super(stream);
	}

	public W3T(@Nonnull File file) throws Exception {
		super(file);
	}

	public W3T() {
		super();
	}

	@Nonnull
	public static W3T ofMapFile(@Nonnull File mapFile) throws IOException {
		return ofMapFile(W3T.class, mapFile);
	}

	@Override
	protected Item createObj(@Nonnull ObjId id, @Nullable ObjId baseId) {
		return new Item(id, baseId);
	}

	@Nonnull
	@Override
	protected Item createObj(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
		return new Item(stream, format);
	}

	@Nonnull
	@Override
	public W3T copy() {
		W3T other = new W3T();

		other.merge(this);

		return other;
	}

	@Override
	public Collection<File> getSLKs() {
		return Collections.singletonList(ItemSLK.GAME_PATH);
	}

	@Override
	public Collection<File> getNecessarySLKs() {
		return Collections.singletonList(ItemSLK.GAME_PATH);
	}

	@Override
	public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
		super.write(stream, format);
	}

	public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
		super.write(stream);
	}
	
	public static class Item extends ObjMod.Obj {
		public Item(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
			super(stream, format);
		}

		public Item(@Nonnull ObjId id, @Nullable ObjId baseId) {
			super(id, baseId);
		}

		@Override
		public boolean isExtended() {
			return false;
		}

		@Override
		protected ObjMod.Obj copySpec() {
			return new Item(getId(), getBaseId());
		}

		public <T extends DataType> T get(@Nonnull State<T> state) {
            try {
                return state.tryCastVal(super.get(state.getFieldId()));
            } catch (DataTypeInfo.CastException ignored) {
            }

            return null;
        }

        public <T extends DataType> void set(@Nonnull State<T> state, T val) {
            super.set(state.getFieldId(), val);
        }

        public <T extends DataType> void remove(@Nonnull State<T> state) {
            super.remove(state.getFieldId());
        }
	}

	public static class State<T extends DataType> extends MetaState<T> {
		public final static State<DataList<AbilId>> ABIL_ABILS = new State<>("iabi", new DataTypeInfo(DataList.class, AbilId.class));

		public final static State<War3Int> ART_BUTTON_POS_X = new State<>("ubpx", War3Int.class);
		public final static State<War3Int> ART_BUTTON_POS_Y = new State<>("ubpy", War3Int.class);
		public final static State<War3Int> ART_COLOR_BLUE = new State<>("iclb", War3Int.class);
		public final static State<War3Int> ART_COLOR_GREEN = new State<>("iclg", War3Int.class);
		public final static State<War3Int> ART_COLOR_RED = new State<>("iclr", War3Int.class);
		public final static State<Icon> ART_ICON = new State<>("iico", Icon.class);
		public final static State<Model> ART_MODEL = new State<>("ifil", Model.class);
		public final static State<War3Real> ART_SCALE = new State<>("isca", War3Real.class);
		public final static State<War3Real> ART_SELECTION_SCALE = new State<>("issc", War3Real.class);

		public final static State<DefType> COMBAT_ARMOR_TYPE = new State<>("iarm", DefType.class);

		public final static State<War3Int> DATA_CHARGES = new State<>("iuse", War3Int.class);
		public final static State<ItemClass> DATA_CLASS = new State<>("icla", ItemClass.class);
		public final static State<AbilCode> DATA_COOLDOWN_ID = new State<>("icid", AbilCode.class);
		public final static State<War3Int> DATA_COST_GOLD = new State<>("igol", War3Int.class);
		public final static State<War3Int> DATA_COST_LUMBER = new State<>("ilum", War3Int.class);
		public final static State<War3Bool> DATA_DROPPABLE = new State<>("idro", War3Bool.class);
		public final static State<War3Bool> DATA_DROPPED = new State<>("idrp", War3Bool.class);
		public final static State<War3Bool> DATA_IGNORE_COOLDOWN = new State<>("iicd", War3Bool.class);
		public final static State<War3Int> DATA_LEVEL = new State<>("ilev", War3Int.class);
		public final static State<War3Int> DATA_LEVEL_OLD = new State<>("ilvo", War3Int.class);
		public final static State<War3Int> DATA_LIFE = new State<>("ihtp", War3Int.class);
		public final static State<War3Bool> DATA_MORPHABLE = new State<>("imor", War3Bool.class);
		public final static State<War3Bool> DATA_PAWNABLE = new State<>("ipaw", War3Bool.class);
		public final static State<War3Bool> DATA_PERISHABLE = new State<>("iper", War3Bool.class);
		public final static State<War3Bool> DATA_POWERUP = new State<>("ipow", War3Bool.class);
		public final static State<War3Int> DATA_PRIO = new State<>("ipri", War3Int.class);
		public final static State<War3Bool> DATA_RANDOMED = new State<>("iprn", War3Bool.class);
		public final static State<War3Bool> DATA_SELLABLE = new State<>("isel", War3Bool.class);
		public final static State<War3Int> DATA_STOCK_MAX = new State<>("isto", War3Int.class);
		public final static State<War3Int> DATA_STOCK_REGEN = new State<>("istr", War3Int.class);
		public final static State<War3Int> DATA_STOCK_INITIAL = new State<>("isst", War3Int.class);
		public final static State<War3Bool> DATA_USABLE = new State<>("iusa", War3Bool.class);

		public final static State<DataList<PathingRequire>> TECH_REQUIRES = new State<>("ureq", new DataTypeInfo(DataList.class, PathingRequire.class));
		public final static State<DataList<War3Int>> TECH_REQUIRES_COUNT = new State<>("urqa", new DataTypeInfo(DataList.class, War3Int.class));

		public final static State<War3String> TEXT_DESCRIPTION = new State<>("ides", War3String.class);
		public final static State<War3Char> TEXT_HOTKEY = new State<>("uhot", War3Char.class);
		public final static State<War3String> TEXT_NAME = new State<>("unam", War3String.class);
		public final static State<War3String> TEXT_TOOLTIP = new State<>("utip", War3String.class);
		public final static State<War3String> TEXT_TOOLTIP_UBER = new State<>("utub", War3String.class);

		public State(@Nonnull String idString, @Nonnull DataTypeInfo typeInfo, @Nullable T defVal) {
			super(idString, typeInfo, defVal);
		}

		public State(@Nonnull String idString, @Nonnull Class<T> type, @Nullable T defVal) {
			this(idString, new DataTypeInfo(type), defVal);
		}

		public State(@Nonnull String idString, @Nonnull DataTypeInfo typeInfo) {
			this(idString, typeInfo, null);
		}

		public State(@Nonnull String idString, @Nonnull Class<T> type) {
			this(idString, new DataTypeInfo(type), null);
		}
	}
}
