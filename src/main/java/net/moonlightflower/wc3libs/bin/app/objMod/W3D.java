package net.moonlightflower.wc3libs.bin.app.objMod;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.slk.app.doodads.DoodSLK;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

/**
 * doodad modifications file for wrapping war3map.w3d
 */
public class W3D extends ObjMod<W3D.Dood> {
	public final static File GAME_PATH = new File("war3map.w3d");
	public final static File CAMPAIGN_PATH = new File("war3campaign.w3d");

	public W3D(@Nonnull Wc3BinInputStream stream) throws IOException {
		super(stream);
	}

	public W3D(@Nonnull File file) throws Exception {
		super(file);
	}

	public W3D() {
		super();
	}

	@Nonnull
	public static W3D ofMapFile(@Nonnull File mapFile) throws IOException {
		return ofMapFile(W3D.class, mapFile);
	}

	@Override
	protected Dood createObj(@Nonnull ObjId id, @Nullable ObjId baseId) {
		return new Dood(id, baseId);
	}

	@Nonnull
	@Override
	protected Dood createObj(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
		return new Dood(stream, format);
	}

	@Nonnull
	@Override
	public W3D copy() {
		W3D other = new W3D();

		other.merge(this);

		return other;
	}

	@Override
	public Collection<File> getSLKs() {
		return Collections.singletonList(DoodSLK.GAME_PATH);
	}

	@Override
	public Collection<File> getNecessarySLKs() {
		return Collections.singletonList(DoodSLK.GAME_PATH);
	}

	@Override
	public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
		super.write(stream, format);
	}

	public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
		super.write(stream);
	}
	
	public static class Dood extends ObjMod.Obj {
		public Dood(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
			super(stream, format);
		}

		public Dood(@Nonnull ObjId id, @Nullable ObjId baseId) {
			super(id, baseId);
		}

		@Override
		public boolean isExtended() {
			return true;
		}

		@Override
		protected Dood copySpec() {
			return new Dood(getId(), getBaseId());
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
		public final static State<War3Bool> ART_ANIM_IN_FOG = new State<>("danf", War3Bool.class);
		public final static State<War3Bool> ART_CASTS_SHADOW = new State<>("dshd", War3Bool.class);
		public final static State<DataList<War3Int>> ART_COLOR_RED = new State<>("dvr1", new DataTypeInfo(DataList.class, War3Int.class)); //mult
		public final static State<DataList<War3Int>> ART_COLOR_GREEN = new State<>("dvg1", new DataTypeInfo(DataList.class, War3Int.class)); //mult
		public final static State<DataList<War3Int>> ART_COLOR_BLUE = new State<>("dvb1", new DataTypeInfo(DataList.class, War3Int.class)); //mult
		public final static State<War3Real> ART_FIXED_ROTATION = new State<>("dfxr", War3Real.class);
		public final static State<War3Bool> ART_FLOATS = new State<>("dflt", War3Bool.class);
		public final static State<War3Real> ART_PITCH_MAX = new State<>("dmap", War3Real.class);
		public final static State<War3Real> ART_ROLL_MAX = new State<>("dmar", War3Real.class);
		public final static State<War3Real> ART_SCALE_DEFAULT = new State<>("ddes", War3Real.class);
		public final static State<War3Real> ART_SELECTION_SCALE = new State<>("dsel", War3Real.class);
		public final static State<War3Bool> ART_SHOW_IN_FOG = new State<>("dshf", War3Bool.class);
		public final static State<War3Bool> ART_SHOW_ON_MINIMAP = new State<>("dsmm", War3Bool.class);
		public final static State<War3Int> ART_MINIMAP_COLOR_BLUE = new State<>("dmmb", War3Int.class);
		public final static State<War3Int> ART_MINIMAP_COLOR_GREEN = new State<>("dmmg", War3Int.class);
		public final static State<War3Int> ART_MINIMAP_COLOR_RED = new State<>("dmmr", War3Int.class);
		public final static State<War3Bool> ART_MINIMAP_USE_COLOR = new State<>("dumc", War3Bool.class);
		public final static State<Model> ART_MODEL = new State<>("dfil", Model.class);
		public final static State<War3Int> ART_VARIATIONS = new State<>("dvar", War3Int.class);
		public final static State<War3Real> ART_VISIBILITY_RAD = new State<>("dvis", War3Real.class);

		public final static State<War3Bool> EDITOR_CAN_PLACE_RANDOM_SCALE = new State<>("dcpr", War3Bool.class);
		public final static State<DoodadClass> EDITOR_CLASS = new State<>("dcat", DoodadClass.class);
		public final static State<War3Bool> EDITOR_IGNORE_MODEL_CLICK = new State<>("dimc", War3Bool.class);
		public final static State<War3Bool> EDITOR_IN_USER_LIST = new State<>("dusr", War3Bool.class);
		public final static State<War3Bool> EDITOR_ON_CLIFFS = new State<>("donc", War3Bool.class);
		public final static State<War3Bool> EDITOR_ON_WATER = new State<>("donw", War3Bool.class);
		public final static State<War3Real> EDITOR_SCALE_MAX = new State<>("dmas", War3Real.class);
		public final static State<War3Real> EDITOR_SCALE_MIN = new State<>("dmis", War3Real.class);
		public final static State<War3Bool> EDITOR_TILESET_SPECIFIC = new State<>("dtsp", War3Bool.class);
		public final static State<DataList<Tileset>> EDITOR_TILESETS = new State<>("dtil", new DataTypeInfo(DataList.class, Tileset.class));
		public final static State<War3Bool> EDITOR_USE_CLICK_HELPER = new State<>("duch", War3Bool.class);

		public final static State<PathingTex> PATH_TEX = new State<>("dptx", PathingTex.class);
		public final static State<War3Bool> PATH_WALKABLE = new State<>("dwlk", War3Bool.class);

		public final static State<SoundLabel> SOUND_LOOP = new State<>("dsnd", SoundLabel.class);

		public final static State<War3String> TEXT_NAME = new State<>("dnam", War3String.class);

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
