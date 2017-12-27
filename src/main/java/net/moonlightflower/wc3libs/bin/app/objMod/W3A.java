package net.moonlightflower.wc3libs.bin.app.objMod;

import net.moonlightflower.wc3libs.bin.MetaState;
import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * ability modifications file for wrapping war3map.w3a
 */
public class W3A extends ObjMod {
	public final static File GAME_PATH = new File("war3map.w3a");
	public final static File CAMPAIGN_PATH = new File("war3campaign.w3a");
	
	public static class States {
		public static class State<T extends DataType> extends MetaState<T> {
			private final static List<State> _values = new ArrayList<>();

			@Nonnull
			private static List<State> values() {
				return new ArrayList<>(_values);
			}
			
			public State(@Nonnull String idString, @Nonnull DataTypeInfo typeInfo, @Nullable T defVal) {
				super(idString, typeInfo, defVal);
				
				_values.add(this);
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
		
		public static List<State> values() {
			return State.values();
		}

		public final static State<DataList<Wc3String>> ART_ANIMS = new State<>("aani", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<Int> ART_BUTTON_POS_X = new State<>("abpx", Int.class);
		public final static State<Int> ART_BUTTON_POS_Y = new State<>("abpy", Int.class);
		public final static State<Int> ART_BUTTON_OFF_POS_X = new State<>("aubx", Int.class);
		public final static State<Int> ART_BUTTON_OFF_POS_Y = new State<>("auby", Int.class);
		public final static State<Int> ART_BUTTON_RESEARCH_POS_X = new State<>("arpx", Int.class);
		public final static State<Int> ART_BUTTON_RESEARCH_POS_Y = new State<>("arpy", Int.class);
		public final static State<DataList<Model>> ART_EFFECT = new State<>("aeat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<DataList<Model>> ART_EFFECT_AREA = new State<>("aaea", new DataTypeInfo(DataList.class, Model.class));
		public final static State<DataList<Model>> ART_EFFECT_CASTER = new State<>("acat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<DataList<Wc3String>> ART_EFFECT_CASTER_ATTACH = new State<>("acap", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<DataList<Wc3String>> ART_EFFECT_CASTER_ATTACH1 = new State<>("aca1", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<Int> ART_EFFECT_CASTER_ATTACH_COUNT = new State<>("acac", Int.class);
		public final static State<DataList<Model>> ART_EFFECT_SPECIAL = new State<>("asat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<DataList<Wc3String>> ART_EFFECT_SPECIAL_ATTACH = new State<>("aspt", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<DataList<Model>> ART_EFFECT_TARGET = new State<>("atat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<DataList<Wc3String>> ART_EFFECT_TARGET_ATTACH0 = new State<>("ata0", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<DataList<Wc3String>> ART_EFFECT_TARGET_ATTACH1 = new State<>("ata1", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<DataList<Wc3String>> ART_EFFECT_TARGET_ATTACH2 = new State<>("ata2", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<DataList<Wc3String>> ART_EFFECT_TARGET_ATTACH3 = new State<>("ata3", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<DataList<Wc3String>> ART_EFFECT_TARGET_ATTACH4 = new State<>("ata4", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<DataList<Wc3String>> ART_EFFECT_TARGET_ATTACH5 = new State<>("ata5", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<Int> ART_EFFECT_TARGET_ATTACH_COUNT = new State<>("atac", Int.class);
		public final static State<Icon> ART_ICON = new State<>("aart", Icon.class);
		public final static State<Icon> ART_ICON_OFF = new State<>("auar", Icon.class);
		public final static State<Icon> ART_ICON_RESEARCH = new State<>("arar", Icon.class);
		public final static State<DataList<LightningId>> ART_LIGHTNING = new State<>("alig", new DataTypeInfo(DataList.class, LightningId.class));
		public final static State<Real> ART_MISSILE_ARC = new State<>("amac", Real.class);
		public final static State<DataList<Model>> ART_MISSILE_ART = new State<>("amat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<Bool> ART_MISSILE_HOMING = new State<>("amho", Bool.class);
		public final static State<Int> ART_MISSILE_SPEED = new State<>("amsp", Int.class);
		
		public final static State<Real> DATA_AREA = new State<>("aare", Real.class); //multi
		public final static State<Wc3String> DATA_BUFFS = new State<>("abuf", Wc3String.class);
		public final static State<Real> DATA_CAST_TIME = new State<>("acas", Real.class); //multi
		public final static State<Real> DATA_COOLDOWN = new State<>("acdn", Real.class); //multi
		public final static State<Real> DATA_DURATION = new State<>("adur", Real.class); //multi
		public final static State<Real> DATA_DURATION_HERO = new State<>("ahdu", Real.class); //multi
		public final static State<DataList<Effect>> DATA_EFFECTS = new State<>("aeff", new DataTypeInfo(DataList.class, Effect.class));
		public final static State<Bool> DATA_HERO = new State<>("aher", Bool.class);
		public final static State<Bool> DATA_ITEM = new State<>("aite", Bool.class);
		public final static State<Int> DATA_LEVELS_COUNT = new State<>("alev", Int.class);
		public final static State<Int> DATA_LEVEL_REQ = new State<>("arlv", Int.class);
		public final static State<Int> DATA_LEVEL_SKIP = new State<>("alsk", Int.class);
		public final static State<Int> DATA_MANA_COST = new State<>("amcs", Int.class); //multi
		public final static State<Int> DATA_PRIO = new State<>("apri", Int.class);
		public final static State<UnitRace> DATA_RACE = new State<>("arac", UnitRace.class);
		public final static State<Real> DATA_RANGE = new State<>("aran", Real.class); //multi
		public final static State<DataList<CombatTarget>> DATA_TARGETS = new State<>("atar", new DataTypeInfo(DataList.class, CombatTarget.class)); //multi
		
		public final static State<SoundLabel> SOUND_EFFECT = new State<>("aefs", SoundLabel.class);
		public final static State<SoundLabel> SOUND_EFFECT_LOOP = new State<>("aefl", SoundLabel.class);
		
		public final static State<Bool> TECH_CHECK_DEPENDENCY = new State<>("achd", Bool.class);
		public final static State<DataList<TechId>> TECH_REQUIRES = new State<>("areq", new DataTypeInfo(DataList.class, TechId.class));
		public final static State<DataList<Int>> TECH_REQUIRES_COUNT = new State<>("arqa", new DataTypeInfo(DataList.class, Int.class));
		
		public final static State<Wc3String> TEXT_EDITOR_SUFFIX = new State<>("ansf", Wc3String.class);
		public final static State<Char> TEXT_HOTKEY = new State<>("ahky", Char.class);
		public final static State<Char> TEXT_HOTKEY_OFF = new State<>("auhk", Char.class);
		public final static State<Char> TEXT_HOTKEY_RESEARCH = new State<>("arhk", Char.class);
		public final static State<Wc3String> TEXT_NAME = new State<>("anam", Wc3String.class);
		public final static State<OrderString> TEXT_ORDER = new State<>("aord", OrderString.class);
		public final static State<OrderString> TEXT_ORDER_OFF = new State<>("aoru", OrderString.class);
		public final static State<OrderString> TEXT_ORDER_AUTO_ON = new State<>("aoro", OrderString.class);
		public final static State<OrderString> TEXT_ORDER_AUTO_OFF = new State<>("aorf", OrderString.class);
		public final static State<Wc3String> TEXT_TOOLTIP = new State<>("atp1", Wc3String.class); //multi
		public final static State<Wc3String> TEXT_TOOLTIP_UBER = new State<>("aub1", Wc3String.class); //multi
		public final static State<Wc3String> TEXT_TOOLTIP_UBER_OFF = new State<>("auu1", Wc3String.class); //multi
		public final static State<Wc3String> TEXT_TOOLTIP_OFF = new State<>("aut1", Wc3String.class); //multi
		public final static State<Wc3String> TEXT_TOOLTIP_RESEARCH = new State<>("aret", Wc3String.class);
		public final static State<Wc3String> TEXT_TOOLTIP_RESEARCH_UBER = new State<>("arut", Wc3String.class);
	}
	
	public static class Obj extends ObjMod.Obj {
		public Obj(ObjId id, ObjId baseId) {
			super(id, baseId);
		}
	}

	@Override
	public Collection<File> getSLKs() {
		return Collections.singletonList(AbilSLK.GAME_USE_PATH);
	}
	
	@Override
	public Collection<File> getNecessarySLKs() {
		return Collections.singletonList(AbilSLK.GAME_USE_PATH);
	}
	
	public W3A(@Nonnull File file) throws Exception {
		super(file, true);
	}
	
	public W3A() {
		super();
	}

	public static W3A ofMapFile(@Nonnull File mapFile) throws Exception {
		if (!mapFile.exists()) throw new IOException(String.format("file %s does not exist", mapFile));
		
		MpqPort.Out port = new JMpqPort.Out();
		
		port.add(GAME_PATH);
		
		MpqPort.Out.Result portResult = port.commit(mapFile);

		if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract w3a file");

		Wc3BinInputStream inStream = new Wc3BinInputStream(portResult.getInputStream(GAME_PATH));

		W3A w3a = new W3A();

		w3a.read(inStream, true);

		inStream.close();

		return w3a;
	}
}
