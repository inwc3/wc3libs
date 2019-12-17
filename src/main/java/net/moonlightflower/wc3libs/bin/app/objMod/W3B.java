package net.moonlightflower.wc3libs.bin.app.objMod;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.slk.app.objs.DestructableSLK;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

/**
 * destructable modifications file for wrapping war3map.w3b
 */
public class W3B extends ObjMod<W3B.Destr> {
	public final static File GAME_PATH = new File("war3map.w3b");
	public final static File CAMPAIGN_PATH = new File("war3campaign.w3b");

	public W3B(@Nonnull Wc3BinInputStream stream) throws IOException {
		super(stream);
	}

	public W3B(@Nonnull File file) throws Exception {
		super(file);
	}

	public W3B() {
		super();
	}

	@Nonnull
	public static W3B ofMapFile(@Nonnull File mapFile) throws IOException {
		return ofMapFile(W3B.class, mapFile);
	}

	@Override
	protected Destr createObj(@Nonnull ObjId id, @Nullable ObjId baseId) {
		return new Destr(id, baseId);
	}

	@Nonnull
	@Override
	protected Destr createObj(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
		return new Destr(stream, format);
	}

	@Nonnull
	@Override
	public W3B copy() {
		W3B other = new W3B();

		other.merge(this);

		return other;
	}

	@Override
	public Collection<File> getSLKs() {
		return Collections.singletonList(DestructableSLK.GAME_PATH);
	}

	@Override
	public Collection<File> getNecessarySLKs() {
		return Collections.singletonList(DestructableSLK.GAME_PATH);
	}

	@Override
	public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
		super.write(stream, format);
	}

	public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
		super.write(stream);
	}
	
	public static class Destr extends ObjMod.Obj {
		public Destr(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
			super(stream, format);
		}

		public Destr(@Nonnull ObjId id, @Nullable ObjId baseId) {
			super(id, baseId);
		}

		@Override
		public boolean isExtended() {
			return false;
		}

		@Override
		protected Destr copySpec() {
			return new Destr(getId(), getBaseId());
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
		public final static State<War3Int> ART_COLOR_RED = new State<>("bvcr", War3Int.class);
		public final static State<War3Int> ART_COLOR_GREEN = new State<>("bvcg", War3Int.class);
		public final static State<War3Int> ART_COLOR_BLUE = new State<>("bvcb", War3Int.class);
		public final static State<War3Real> ART_ELEV_RAD = new State<>("brad", War3Real.class);
		public final static State<War3Bool> ART_FAT_LINE_OF_SIGHT = new State<>("bflo", War3Bool.class);
		public final static State<War3Real> ART_FIXED_ROTATION = new State<>("bfxr", War3Real.class);
		public final static State<War3Real> ART_FLY_HEIGHT = new State<>("bflh", War3Real.class);
		public final static State<War3Real> ART_FOG_RAD = new State<>("bfra", War3Real.class);
		public final static State<War3Bool> ART_LIGHTWEIGHT = new State<>("blit", War3Bool.class);
		public final static State<War3Int> ART_MINIMAP_COLOR_BLUE = new State<>("bmmb", War3Int.class);
		public final static State<War3Int> ART_MINIMAP_COLOR_GREEN = new State<>("bmmg", War3Int.class);
		public final static State<War3Int> ART_MINIMAP_COLOR_RED = new State<>("bmmr", War3Int.class);
		public final static State<War3Bool> ART_MINIMAP_USE_COLOR = new State<>("bumm", War3Bool.class);
		public final static State<Model> ART_MODEL = new State<>("bfil", Model.class);
		public final static State<Model> ART_MODEL_PORTRAIT = new State<>("bgpm", Model.class);
		public final static State<War3Real> ART_OCCLUSION_HEIGHT = new State<>("boch", War3Real.class);
		public final static State<War3Real> ART_PITCH_MAX = new State<>("bmap", War3Real.class);
		public final static State<War3Real> ART_ROLL_MAX = new State<>("bmar", War3Real.class);
		public final static State<War3Bool> ART_SELECTABLE = new State<>("bgse", War3Bool.class);
		public final static State<War3Real> ART_SELECTION_SCALE_EDITOR = new State<>("bsel", War3Real.class);
		public final static State<War3Real> ART_SELECTION_SCALE_GAME = new State<>("bgsc", War3Real.class);
		public final static State<ShadowTex> ART_SHADOW_TEX = new State<>("bshd", ShadowTex.class);
		public final static State<War3Bool> ART_SHOW_IN_FOG = new State<>("bfvi", War3Bool.class);
		public final static State<War3Bool> ART_SHOW_ON_MINIMAP = new State<>("bsmm", War3Bool.class);
		public final static State<Tex> ART_TEX = new State<>("btxf", Tex.class);
		public final static State<War3Int> ART_TEX_ID = new State<>("btxi", War3Int.class);
		public final static State<War3Int> ART_VARIATIONS = new State<>("bvar", War3Int.class);

		public final static State<DefType> COMBAT_ARMOR_TYPE = new State<>("barm", DefType.class);
		public final static State<DataList<CombatTarget>> COMBAT_TARGETS = new State<>("btar", new DataTypeInfo(DataList.class, CombatTarget.class));

		public final static State<War3Int> DATA_BUILD_TIME = new State<>("bbut", War3Int.class);
		public final static State<War3Int> DATA_COST_GOLD_REPAIR = new State<>("breg", War3Int.class);
		public final static State<War3Int> DATA_COST_LUMBER_REPAIR = new State<>("brel", War3Int.class);
		public final static State<War3Real> DATA_LIFE = new State<>("bhps", War3Real.class);
		public final static State<War3Int> DATA_REPAIR_TIME = new State<>("bret", War3Int.class);

		public final static State<War3Bool> EDITOR_CAN_PLACE_DEAD = new State<>("bcpd", War3Bool.class);
		public final static State<War3Bool> EDITOR_CAN_PLACE_RANDOM_SCALE = new State<>("bcpr", War3Bool.class);
		public final static State<DestructableClass> EDITOR_CLASS = new State<>("bcat", DestructableClass.class);
		public final static State<War3Bool> EDITOR_IN_USER_LIST = new State<>("busr", War3Bool.class);
		public final static State<War3Bool> EDITOR_ON_CLIFFS = new State<>("bonc", War3Bool.class);
		public final static State<War3Bool> EDITOR_ON_WATER = new State<>("bonw", War3Bool.class);
		public final static State<War3Real> EDITOR_SCALE_MIN = new State<>("bmis", War3Real.class);
		public final static State<War3Real> EDITOR_SCALE_MAX = new State<>("bmas", War3Real.class);
		public final static State<War3Bool> EDITOR_TILESET_SPECIFIC = new State<>("btsp", War3Bool.class);
		public final static State<DataList<Tileset>> EDITOR_TILESETS = new State<>("btil", new DataTypeInfo(DataList.class, Tileset.class));
		public final static State<War3Bool> EDITOR_USE_CLICK_HELPER = new State<>("buch", War3Bool.class);

		public final static State<War3Int> PATH_CLIFF_LEVEL = new State<>("bclh", War3Int.class);
		public final static State<PathingTex> PATH_TEX = new State<>("bptx", PathingTex.class);
		public final static State<PathingTex> PATH_TEX_DEAD = new State<>("bptd", PathingTex.class);
		public final static State<War3Bool> PATH_WALKABLE = new State<>("bwal", War3Bool.class);

		public final static State<SoundLabel> SOUND_DEATH = new State<>("bdsn", SoundLabel.class);

		public final static State<War3String> TEXT_EDITOR_SUFFIX = new State<>("bsuf", War3String.class);
		public final static State<War3String> TEXT_NAME = new State<>("bnam", War3String.class);

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
