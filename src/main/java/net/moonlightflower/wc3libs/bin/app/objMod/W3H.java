package net.moonlightflower.wc3libs.bin.app.objMod;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.slk.app.objs.BuffSLK;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

/**
 * buff modifications file for wrapping war3map.w3h
 */
public class W3H extends ObjMod<W3H.Buff> {
	public final static File GAME_PATH = new File("war3map.w3h");
	public final static File CAMPAIGN_PATH = new File("war3campaign.w3h");

	public W3H(@Nonnull Wc3BinInputStream stream) throws IOException {
		super(stream);
	}

	public W3H(@Nonnull File file) throws Exception {
		super(file);
	}

	public W3H() {
		super();
	}

	@Nonnull
	public static W3H ofMapFile(@Nonnull File mapFile) throws IOException {
		return ofMapFile(W3H.class, mapFile);
	}

	@Override
	protected Buff createObj(@Nonnull ObjId id, @Nullable ObjId baseId) {
		return new Buff(id, baseId);
	}

	@Nonnull
	@Override
	protected Buff createObj(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
		return new Buff(stream, format);
	}

	@Nonnull
	@Override
	public W3H copy() {
		W3H other = new W3H();

		other.merge(this);

		return other;
	}

	@Override
	public Collection<File> getSLKs() {
		return Collections.singletonList(BuffSLK.GAME_PATH);
	}

	@Override
	public Collection<File> getNecessarySLKs() {
		return Collections.singletonList(BuffSLK.GAME_PATH);
	}

	@Override
	public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
		super.write(stream, format);
	}

	public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
		super.write(stream);
	}
	
	public static class Buff extends ObjMod.Obj {
		public Buff(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
			super(stream, format);
		}

		public Buff(@Nonnull ObjId id, @Nullable ObjId baseId) {
			super(id, baseId);
		}

		@Override
		public boolean isExtended() {
			return false;
		}

		@Override
		protected ObjMod.Obj copySpec() {
			return new Buff(getId(), getBaseId());
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
		public final static State<SpellDetail> ART_DETAIL = new State<>("fspd", SpellDetail.class);
		public final static State<Icon> ART_ICON = new State<>("fart", Icon.class);
		public final static State<LightningId> ART_LIGHTNING = new State<>("flig", new DataTypeInfo(DataList.class, LightningId.class));
		public final static State<DataList<Model>> ART_MISSILE = new State<>("fmat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<War3Real> ART_MISSILE_ARC = new State<>("fmac", War3Real.class);
		public final static State<War3Bool> ART_MISSILE_HOMING = new State<>("fmho", War3Bool.class);
		public final static State<War3Int> ART_MISSILE_SPEED = new State<>("fmsp", War3Int.class);
		public final static State<DataList<Model>> ART_SFX = new State<>("feat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<DataList<War3String>> ART_SFX_ATTACH = new State<>("feft", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<Model>> ART_SFX_SPECIAL = new State<>("fsat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<DataList<War3String>> ART_SFX_SPECIAL_ATTACH = new State<>("fspt", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<Model>> ART_SFX_TARGET = new State<>("ftat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<War3Int> ART_SFX_TARGET_ATTACH_COUNT = new State<>("ftac", War3Int.class);
		public final static State<DataList<War3String>> ART_SFX_TARGET_ATTACH0 = new State<>("fta0", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<War3String>> ART_SFX_TARGET_ATTACH1 = new State<>("fta1", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<War3String>> ART_SFX_TARGET_ATTACH2 = new State<>("fta2", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<War3String>> ART_SFX_TARGET_ATTACH3 = new State<>("fta3", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<War3String>> ART_SFX_TARGET_ATTACH4 = new State<>("fta4", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<War3String>> ART_SFX_TARGET_ATTACH5 = new State<>("fta5", new DataTypeInfo(DataList.class, War3String.class));

		public final static State<War3Bool> DATA_IS_EFFECT = new State<>("feff", War3Bool.class);
		public final static State<UnitRace> DATA_RACE = new State<>("frac", UnitRace.class);

		public final static State<SoundLabel> SOUND_ONESHOT = new State<>("fefs", SoundLabel.class);
		public final static State<SoundLabel> SOUND_LOOP = new State<>("fefl", SoundLabel.class);

		public final static State<War3String> TEXT_EDITOR_SUFFIX = new State<>("fnsf", War3String.class);
		public final static State<War3String> TEXT_NAME_EDITOR = new State<>("fnam", War3String.class);
		public final static State<War3String> TEXT_TOOLTIP = new State<>("ftip", War3String.class);
		public final static State<War3String> TEXT_TOOLTIP_UBER = new State<>("fube", War3String.class);

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
