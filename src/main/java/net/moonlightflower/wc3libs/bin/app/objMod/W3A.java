package net.moonlightflower.wc3libs.bin.app.objMod;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.slk.app.objs.AbilSLK;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

/**
 * ability modifications file for wrapping war3map.w3a
 */
public class W3A extends ObjMod<W3A.Abil> {
	public final static File GAME_PATH = new File("war3map.w3a");
	public final static File CAMPAIGN_PATH = new File("war3campaign.w3a");

	public W3A(@Nonnull Wc3BinInputStream stream) throws IOException {
		super(stream);
	}

	public W3A(@Nonnull File file) throws Exception {
		super(file);
	}

	public W3A() {
		super();
	}

	@Nonnull
	public static W3A ofMapFile(@Nonnull File mapFile) throws IOException {
		return ofMapFile(W3A.class, mapFile);
	}

	@Override
	protected Abil createObj(@Nonnull ObjId id, @Nullable ObjId baseId) {
		return new Abil(id, baseId);
	}

	@Nonnull
	@Override
	protected Abil createObj(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
		return new Abil(stream, format);
	}

	@Nonnull
	@Override
	public W3A copy() {
		W3A other = new W3A();

		other.merge(this);

		return other;
	}

	@Override
	public Collection<File> getSLKs() {
		return Collections.singletonList(AbilSLK.GAME_PATH);
	}

	@Override
	public Collection<File> getNecessarySLKs() {
		return Collections.singletonList(AbilSLK.GAME_PATH);
	}

	@Override
	public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
		super.write(stream, format);
	}

	public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
		super.write(stream);
	}

	public static class Abil extends ObjMod.Obj {
		public Abil(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
			super(stream, format);
		}

		public Abil(@Nonnull ObjId id, @Nullable ObjId baseId) {
			super(id, baseId);
		}

		@Override
		public boolean isExtended() {
			return true;
		}

		@Override
		protected Abil copySpec() {
			return new Abil(getId(), getBaseId());
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
		public final static State<DataList<War3String>> ART_ANIMS = new State<>("aani", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<War3Int> ART_BUTTON_POS_X = new State<>("abpx", War3Int.class);
		public final static State<War3Int> ART_BUTTON_POS_Y = new State<>("abpy", War3Int.class);
		public final static State<War3Int> ART_BUTTON_OFF_POS_X = new State<>("aubx", War3Int.class);
		public final static State<War3Int> ART_BUTTON_OFF_POS_Y = new State<>("auby", War3Int.class);
		public final static State<War3Int> ART_BUTTON_RESEARCH_POS_X = new State<>("arpx", War3Int.class);
		public final static State<War3Int> ART_BUTTON_RESEARCH_POS_Y = new State<>("arpy", War3Int.class);
		public final static State<DataList<Model>> ART_EFFECT = new State<>("aeat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<DataList<Model>> ART_EFFECT_AREA = new State<>("aaea", new DataTypeInfo(DataList.class, Model.class));
		public final static State<DataList<Model>> ART_EFFECT_CASTER = new State<>("acat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<DataList<War3String>> ART_EFFECT_CASTER_ATTACH = new State<>("acap", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<War3String>> ART_EFFECT_CASTER_ATTACH1 = new State<>("aca1", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<War3Int> ART_EFFECT_CASTER_ATTACH_COUNT = new State<>("acac", War3Int.class);
		public final static State<DataList<Model>> ART_EFFECT_SPECIAL = new State<>("asat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<DataList<War3String>> ART_EFFECT_SPECIAL_ATTACH = new State<>("aspt", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<Model>> ART_EFFECT_TARGET = new State<>("atat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<DataList<War3String>> ART_EFFECT_TARGET_ATTACH0 = new State<>("ata0", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<War3String>> ART_EFFECT_TARGET_ATTACH1 = new State<>("ata1", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<War3String>> ART_EFFECT_TARGET_ATTACH2 = new State<>("ata2", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<War3String>> ART_EFFECT_TARGET_ATTACH3 = new State<>("ata3", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<War3String>> ART_EFFECT_TARGET_ATTACH4 = new State<>("ata4", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<War3String>> ART_EFFECT_TARGET_ATTACH5 = new State<>("ata5", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<War3Int> ART_EFFECT_TARGET_ATTACH_COUNT = new State<>("atac", War3Int.class);
		public final static State<Icon> ART_ICON = new State<>("aart", Icon.class);
		public final static State<Icon> ART_ICON_OFF = new State<>("auar", Icon.class);
		public final static State<Icon> ART_ICON_RESEARCH = new State<>("arar", Icon.class);
		public final static State<DataList<LightningId>> ART_LIGHTNING = new State<>("alig", new DataTypeInfo(DataList.class, LightningId.class));
		public final static State<War3Real> ART_MISSILE_ARC = new State<>("amac", War3Real.class);
		public final static State<DataList<Model>> ART_MISSILE_ART = new State<>("amat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<War3Bool> ART_MISSILE_HOMING = new State<>("amho", War3Bool.class);
		public final static State<War3Int> ART_MISSILE_SPEED = new State<>("amsp", War3Int.class);

		public final static State<War3Real> DATA_AREA = new State<>("aare", War3Real.class); //multi
		public final static State<War3String> DATA_BUFFS = new State<>("abuf", War3String.class);
		public final static State<War3Real> DATA_CAST_TIME = new State<>("acas", War3Real.class); //multi
		public final static State<War3Real> DATA_COOLDOWN = new State<>("acdn", War3Real.class); //multi
		public final static State<War3Real> DATA_DURATION = new State<>("adur", War3Real.class); //multi
		public final static State<War3Real> DATA_DURATION_HERO = new State<>("ahdu", War3Real.class); //multi
		public final static State<DataList<Effect>> DATA_EFFECTS = new State<>("aeff", new DataTypeInfo(DataList.class, Effect.class));
		public final static State<War3Bool> DATA_HERO = new State<>("aher", War3Bool.class);
		public final static State<War3Bool> DATA_ITEM = new State<>("aite", War3Bool.class);
		public final static State<War3Int> DATA_LEVELS_COUNT = new State<>("alev", War3Int.class);
		public final static State<War3Int> DATA_LEVEL_REQ = new State<>("arlv", War3Int.class);
		public final static State<War3Int> DATA_LEVEL_SKIP = new State<>("alsk", War3Int.class);
		public final static State<War3Int> DATA_MANA_COST = new State<>("amcs", War3Int.class); //multi
		public final static State<War3Int> DATA_PRIO = new State<>("apri", War3Int.class);
		public final static State<UnitRace> DATA_RACE = new State<>("arac", UnitRace.class);
		public final static State<War3Real> DATA_RANGE = new State<>("aran", War3Real.class); //multi
		public final static State<DataList<CombatTarget>> DATA_TARGETS = new State<>("atar", new DataTypeInfo(DataList.class, CombatTarget.class)); //multi

		public final static State<SoundLabel> SOUND_EFFECT = new State<>("aefs", SoundLabel.class);
		public final static State<SoundLabel> SOUND_EFFECT_LOOP = new State<>("aefl", SoundLabel.class);

		public final static State<War3Bool> TECH_CHECK_DEPENDENCY = new State<>("achd", War3Bool.class);
		public final static State<DataList<TechId>> TECH_REQUIRES = new State<>("areq", new DataTypeInfo(DataList.class, TechId.class));
		public final static State<DataList<War3Int>> TECH_REQUIRES_COUNT = new State<>("arqa", new DataTypeInfo(DataList.class, War3Int.class));

		public final static State<War3String> TEXT_EDITOR_SUFFIX = new State<>("ansf", War3String.class);
		public final static State<War3Char> TEXT_HOTKEY = new State<>("ahky", War3Char.class);
		public final static State<War3Char> TEXT_HOTKEY_OFF = new State<>("auhk", War3Char.class);
		public final static State<War3Char> TEXT_HOTKEY_RESEARCH = new State<>("arhk", War3Char.class);
		public final static State<War3String> TEXT_NAME = new State<>("anam", War3String.class);
		public final static State<OrderString> TEXT_ORDER = new State<>("aord", OrderString.class);
		public final static State<OrderString> TEXT_ORDER_OFF = new State<>("aoru", OrderString.class);
		public final static State<OrderString> TEXT_ORDER_AUTO_ON = new State<>("aoro", OrderString.class);
		public final static State<OrderString> TEXT_ORDER_AUTO_OFF = new State<>("aorf", OrderString.class);
		public final static State<War3String> TEXT_TOOLTIP = new State<>("atp1", War3String.class); //multi
		public final static State<War3String> TEXT_TOOLTIP_UBER = new State<>("aub1", War3String.class); //multi
		public final static State<War3String> TEXT_TOOLTIP_UBER_OFF = new State<>("auu1", War3String.class); //multi
		public final static State<War3String> TEXT_TOOLTIP_OFF = new State<>("aut1", War3String.class); //multi
		public final static State<War3String> TEXT_TOOLTIP_RESEARCH = new State<>("aret", War3String.class);
		public final static State<War3String> TEXT_TOOLTIP_RESEARCH_UBER = new State<>("arut", War3String.class);

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
