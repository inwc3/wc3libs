package net.moonlightflower.wc3libs.bin.app.objMod;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.moonlightflower.wc3libs.bin.MetaState;
import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.app.Bool;
import net.moonlightflower.wc3libs.dataTypes.app.Char;
import net.moonlightflower.wc3libs.dataTypes.app.CombatTarget;
import net.moonlightflower.wc3libs.dataTypes.app.Effect;
import net.moonlightflower.wc3libs.dataTypes.app.Icon;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.LightningId;
import net.moonlightflower.wc3libs.dataTypes.app.Model;
import net.moonlightflower.wc3libs.dataTypes.app.OrderString;
import net.moonlightflower.wc3libs.dataTypes.app.Real;
import net.moonlightflower.wc3libs.dataTypes.app.SoundLabel;
import net.moonlightflower.wc3libs.dataTypes.app.TechId;
import net.moonlightflower.wc3libs.dataTypes.app.UnitRace;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.misc.ObjId;

/**
 * ability modifications file for wrapping war3map.w3a
 */
public class W3A extends ObjMod {
	public final static File GAME_PATH = new File("war3map.w3a");
	
	public static class States {
		public static class State<T> extends MetaState<T> {
			private static List<State> _values = new ArrayList<>();
			
			public static List<State> values() {
				return _values;
			}
			
			public State(String idString) {
				this(idString, null);
			}

			public State(String idString, T defVal) {
				super(idString, defVal);
				
				_values.add(this);
			}
		}
		
		public static List<State> values() {
			return State.values();
		}

		public final static State<DataList<Wc3String>> ART_ANIMS = new State<>("aani");
		public final static State<Int> ART_BUTTON_POS_X = new State<>("abpx");
		public final static State<Int> ART_BUTTON_POS_Y = new State<>("abpy");
		public final static State<Int> ART_BUTTON_OFF_POS_X = new State<>("aubx");
		public final static State<Int> ART_BUTTON_OFF_POS_Y = new State<>("auby");
		public final static State<Int> ART_BUTTON_RESEARCH_POS_X = new State<>("arpx");
		public final static State<Int> ART_BUTTON_RESEARCH_POS_Y = new State<>("arpy");
		public final static State<DataList<Model>> ART_EFFECT = new State<>("aeat");
		public final static State<DataList<Model>> ART_EFFECT_AREA = new State<>("aaea");
		public final static State<DataList<Model>> ART_EFFECT_CASTER = new State<>("acat");
		public final static State<DataList<Wc3String>> ART_EFFECT_CASTER_ATTACH = new State<>("acap");
		public final static State<DataList<Wc3String>> ART_EFFECT_CASTER_ATTACH1 = new State<>("aca1");
		public final static State<Int> ART_EFFECT_CASTER_ATTACH_COUNT = new State<>("acac");
		public final static State<DataList<Model>> ART_EFFECT_SPECIAL = new State<>("asat");
		public final static State<DataList<Wc3String>> ART_EFFECT_SPECIAL_ATTACH = new State<>("aspt");
		public final static State<DataList<Model>> ART_EFFECT_TARGET = new State<>("atat");
		public final static State<DataList<Wc3String>> ART_EFFECT_TARGET_ATTACH0 = new State<>("ata0");
		public final static State<DataList<Wc3String>> ART_EFFECT_TARGET_ATTACH1 = new State<>("ata1");
		public final static State<DataList<Wc3String>> ART_EFFECT_TARGET_ATTACH2 = new State<>("ata2");
		public final static State<DataList<Wc3String>> ART_EFFECT_TARGET_ATTACH3 = new State<>("ata3");
		public final static State<DataList<Wc3String>> ART_EFFECT_TARGET_ATTACH4 = new State<>("ata4");
		public final static State<DataList<Wc3String>> ART_EFFECT_TARGET_ATTACH5 = new State<>("ata5");
		public final static State<Int> ART_EFFECT_TARGET_ATTACH_COUNT = new State<>("atac");
		public final static State<Icon> ART_ICON = new State<>("aart");
		public final static State<Icon> ART_ICON_OFF = new State<>("auar");
		public final static State<Icon> ART_ICON_RESEARCH = new State<>("arar");
		public final static State<DataList<LightningId>> ART_LIGHTNING = new State<>("alig");
		public final static State<Real> ART_MISSILE_ARC = new State<>("amac");
		public final static State<DataList<Model>> ART_MISSILE_ART = new State<>("amat");
		public final static State<Bool> ART_MISSILE_HOMING = new State<>("amho");
		public final static State<Int> ART_MISSILE_SPEED = new State<>("amsp");
		
		public final static State<Real> DATA_AREA = new State<>("aare"); //multi
		public final static State<Wc3String> DATA_BUFFS = new State<>("abuf");
		public final static State<Real> DATA_CAST_TIME = new State<>("acas"); //multi
		public final static State<Real> DATA_COOLDOWN = new State<>("acdn"); //multi
		public final static State<Real> DATA_DURATION = new State<>("adur"); //multi
		public final static State<Real> DATA_DURATION_HERO = new State<>("ahdu"); //multi
		public final static State<DataList<Effect>> DATA_EFFECTS = new State<>("aeff");
		public final static State<Bool> DATA_HERO = new State<>("aher");
		public final static State<Bool> DATA_ITEM = new State<>("aite");
		public final static State<Int> DATA_LEVELS_COUNT = new State<>("alev");
		public final static State<Int> DATA_LEVEL_REQ = new State<>("arlv");
		public final static State<Int> DATA_LEVEL_SKIP = new State<>("alsk");
		public final static State<Int> DATA_MANA_COST = new State<>("amcs"); //multi
		public final static State<Int> DATA_PRIO = new State<>("apri");
		public final static State<UnitRace> DATA_RACE = new State<>("arac");
		public final static State<Real> DATA_RANGE = new State<>("aran"); //multi
		public final static State<DataList<CombatTarget>> DATA_TARGETS = new State<>("atar"); //multi
		
		public final static State<SoundLabel> SOUND_EFFECT = new State<>("aefs");
		public final static State<SoundLabel> SOUND_EFFECT_LOOP = new State<>("aefl");
		
		public final static State<Bool> TECH_CHECK_DEPENDENCY = new State<>("achd");
		public final static State<DataList<TechId>> TECH_REQUIRES = new State<>("areq");
		public final static State<DataList<Int>> TECH_REQUIRES_COUNT = new State<>("arqa");
		
		public final static State<Wc3String> TEXT_EDITOR_SUFFIX = new State<>("ansf");
		public final static State<Char> TEXT_HOTKEY = new State<>("ahky");
		public final static State<Char> TEXT_HOTKEY_OFF = new State<>("auhk");
		public final static State<Char> TEXT_HOTKEY_RESEARCH = new State<>("arhk");
		public final static State<Wc3String> TEXT_NAME = new State<>("anam");
		public final static State<OrderString> TEXT_ORDER = new State<>("aord");
		public final static State<OrderString> TEXT_ORDER_OFF = new State<>("aoru");
		public final static State<OrderString> TEXT_ORDER_AUTO_ON = new State<>("aoro");
		public final static State<OrderString> TEXT_ORDER_AUTO_OFF = new State<>("aorf");
		public final static State<Wc3String> TEXT_TOOLTIP = new State<>("atp1"); //multi
		public final static State<Wc3String> TEXT_TOOLTIP_UBER = new State<>("aub1"); //multi
		public final static State<Wc3String> TEXT_TOOLTIP_UBER_OFF = new State<>("auu1"); //multi
		public final static State<Wc3String> TEXT_TOOLTIP_OFF = new State<>("aut1"); //multi
		public final static State<Wc3String> TEXT_TOOLTIP_RESEARCH = new State<>("aret");
		public final static State<Wc3String> TEXT_TOOLTIP_RESEARCH_UBER = new State<>("arut");
	}
	
	public static class Obj extends ObjMod.Obj {
		public Obj(ObjId id, ObjId baseId) {
			super(id, baseId);
		}
	}
}
