package net.moonlightflower.wc3libs.bin.app.objMod;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.slk.app.objs.UpgradeSLK;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

/**
 * upgrade modifications file for wrapping war3map.w3q
 */
public class W3Q extends ObjMod<W3Q.Upgr> {
	public final static File GAME_PATH = new File("war3map.w3q");
	public final static File CAMPAIGN_PATH = new File("war3campaign.w3q");

	public W3Q(@Nonnull Wc3BinInputStream stream) throws IOException {
		super(stream);
	}

	public W3Q(@Nonnull File file) throws Exception {
		super(file);
	}

	public W3Q() {
		super();
	}

	@Nonnull
	public static W3Q ofMapFile(@Nonnull File mapFile) throws IOException {
		return ofMapFile(W3Q.class, mapFile);
	}

	@Override
	protected Upgr createObj(@Nonnull ObjId id, @Nullable ObjId baseId) {
		return new Upgr(id, baseId);
	}

	@Nonnull
	@Override
	protected Upgr createObj(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
		return new Upgr(stream, format);
	}

	@Nonnull
	@Override
	public W3Q copy() {
		W3Q other = new W3Q();

		other.merge(this);

		return other;
	}

	@Override
	public Collection<File> getSLKs() {
		return Collections.singletonList(UpgradeSLK.GAME_PATH);
	}

	@Override
	public Collection<File> getNecessarySLKs() {
		return Collections.singletonList(UpgradeSLK.GAME_PATH);
	}

	@Override
	public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
		super.write(stream, format);
	}

	public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
		super.write(stream);
	}

	public static class Upgr extends ObjMod.Obj {
		public Upgr(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
			super(stream, format);
		}

		public Upgr(@Nonnull ObjId id, @Nullable ObjId baseId) {
			super(id, baseId);
		}

		@Override
		public boolean isExtended() {
			return true;
		}

		@Override
		protected Upgr copySpec() {
			return new Upgr(getId(), getBaseId());
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
		public final static State<War3Int> ART_BUTTON_POS_X = new State<>("gbpx", War3Int.class);
		public final static State<War3Int> ART_BUTTON_POS_Y = new State<>("gbpy", War3Int.class);
		public final static State<Icon> ART_ICON = new State<>("gar1", Icon.class);

		public final static State<UpgradeClass> DATA_CLASS = new State<>("gcls", UpgradeClass.class);
		public final static State<UpgradeEffect> DATA_EFFECT1 = new State<>("gef1", UpgradeEffect.class);
		public final static State<War3Real> DATA_EFFECT1_BASE = new State<>("gba1", War3Real.class);
		public final static State<War3Real> DATA_EFFECT1_INC = new State<>("gmo1", War3Real.class);
		public final static State<War3String> DATA_EFFECT1_CODE = new State<>("gco1", War3String.class);
		public final static State<UpgradeEffect> DATA_EFFECT2 = new State<>("gef2", UpgradeEffect.class);
		public final static State<War3Real> DATA_EFFECT2_BASE = new State<>("gba2", War3Real.class);
		public final static State<War3Real> DATA_EFFECT2_INC = new State<>("gmo2", War3Real.class);
		public final static State<War3String> DATA_EFFECT2_CODE = new State<>("gco2", War3String.class);
		public final static State<UpgradeEffect> DATA_EFFECT3 = new State<>("gef3", UpgradeEffect.class);
		public final static State<War3Real> DATA_EFFECT3_BASE = new State<>("gba3", War3Real.class);
		public final static State<War3Real> DATA_EFFECT3_INC = new State<>("gmo3", War3Real.class);
		public final static State<War3String> DATA_EFFECT3_CODE = new State<>("gco3", War3String.class);
		public final static State<UpgradeEffect> DATA_EFFECT4 = new State<>("gef4", UpgradeEffect.class);
		public final static State<War3Real> DATA_EFFECT4_BASE = new State<>("gba4", War3Real.class);
		public final static State<War3Real> DATA_EFFECT4_INC = new State<>("gmo4", War3Real.class);
		public final static State<War3String> DATA_EFFECT4_CODE = new State<>("gco4", War3String.class);
		public final static State<War3Int> DATA_COSTS_GOLD = new State<>("gglb", War3Int.class);
		public final static State<War3Int> DATA_COSTS_GOLD_INC = new State<>("gglm", War3Int.class);
		public final static State<War3Int> DATA_COSTS_LUMBER = new State<>("glmb", War3Int.class);
		public final static State<War3Int> DATA_COSTS_LUMBER_INC = new State<>("glmm", War3Int.class);
		public final static State<War3Int> DATA_COSTS_TIME = new State<>("gtib", War3Int.class);
		public final static State<War3Int> DATA_COSTS_TIME_ADD = new State<>("gtim", War3Int.class);
		public final static State<War3Bool> DATA_IS_GLOBAL = new State<>("glob", War3Bool.class);
		public final static State<War3Bool> DATA_IS_INHERITED = new State<>("ginh", War3Bool.class);
		public final static State<War3Int> DATA_LEVEL_COUNT = new State<>("glvl", War3Int.class);
		public final static State<UnitRace> DATA_RACE = new State<>("grac", UnitRace.class);

		public final static State<DataList<TechId>> TECH_REQUIRES = new State<>("greq", new DataTypeInfo(DataList.class, TechId.class));
		public final static State<DataList<War3Int>> TECH_REQUIRES_LEVELS = new State<>("grqc", new DataTypeInfo(DataList.class, War3Int.class));

		public final static State<War3String> TEXT_EDITOR_SUFFIX = new State<>("gnsf", War3String.class);
		public final static State<War3Char> TEXT_HOTKEY = new State<>("ghk1", War3Char.class);
		public final static State<War3String> TEXT_NAME = new State<>("gnam", War3String.class);
		public final static State<War3String> TEXT_TOOLTIP = new State<>("gtp1", War3String.class);
		public final static State<War3String> TEXT_TOOLTIP_UBER = new State<>("gub1", War3String.class);

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
