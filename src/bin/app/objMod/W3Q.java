package bin.app.objMod;

import java.util.ArrayList;
import java.util.List;

import bin.MetaState;
import bin.ObjMod;
import dataTypes.app.Bool;
import dataTypes.app.Char;
import dataTypes.app.Icon;
import dataTypes.app.Int;
import dataTypes.app.IntList;
import dataTypes.app.Real;
import dataTypes.app.TechList;
import dataTypes.app.UnitRace;
import dataTypes.app.UpgradeClass;
import dataTypes.app.UpgradeEffect;
import misc.ObjId;
import slk.SLKState;

/**
 * upgrade modifications file for wrapping war3map.w3q
 */
public class W3Q extends ObjMod {
	public final static String GAME_PATH = "war3map.w3q";
	
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
		
		public final static State<Int> ART_BUTTON_POS_X = new State<>("gbpx");
		public final static State<Int> ART_BUTTON_POS_Y = new State<>("gbpy");
		public final static State<Icon> ART_ICON = new State<>("gar1");
		
		public final static State<UpgradeClass> DATA_CLASS = new State<>("gcls");
		public final static State<UpgradeEffect> DATA_EFFECT1 = new State<>("gef1");
		public final static State<Real> DATA_EFFECT1_BASE = new State<>("gba1");
		public final static State<Real> DATA_EFFECT1_INC = new State<>("gmo1");
		public final static State<String> DATA_EFFECT1_CODE = new State<>("gco1");
		public final static State<UpgradeEffect> DATA_EFFECT2 = new State<>("gef2");
		public final static State<Real> DATA_EFFECT2_BASE = new State<>("gba2");
		public final static State<Real> DATA_EFFECT2_INC = new State<>("gmo2");
		public final static State<String> DATA_EFFECT2_CODE = new State<>("gco2");
		public final static State<UpgradeEffect> DATA_EFFECT3 = new State<>("gef3");
		public final static State<Real> DATA_EFFECT3_BASE = new State<>("gba3");
		public final static State<Real> DATA_EFFECT3_INC = new State<>("gmo3");
		public final static State<String> DATA_EFFECT3_CODE = new State<>("gco3");
		public final static State<UpgradeEffect> DATA_EFFECT4 = new State<>("gef4");
		public final static State<Real> DATA_EFFECT4_BASE = new State<>("gba4");
		public final static State<Real> DATA_EFFECT4_INC = new State<>("gmo4");
		public final static State<String> DATA_EFFECT4_CODE = new State<>("gco4");
		public final static State<Int> DATA_COSTS_GOLD = new State<>("gglb");
		public final static State<Int> DATA_COSTS_GOLD_INC = new State<>("gglm");
		public final static State<Int> DATA_COSTS_LUMBER = new State<>("glmb");
		public final static State<Int> DATA_COSTS_LUMBER_INC = new State<>("glmm");
		public final static State<Int> DATA_COSTS_TIME = new State<>("gtib");
		public final static State<Int> DATA_COSTS_TIME_ADD = new State<>("gtim");
		public final static State<Bool> DATA_IS_GLOBAL = new State<>("glob");
		public final static State<Bool> DATA_IS_INHERITED = new State<>("ginh");
		public final static State<Int> DATA_LEVEL_COUNT = new State<>("glvl");
		public final static State<UnitRace> DATA_RACE = new State<>("grac");
		
		public final static State<TechList> TECH_REQUIRES = new State<>("greq");
		public final static State<IntList> TECH_REQUIRES_LEVELS = new State<>("grqc");
		
		public final static State<String> TEXT_EDITOR_SUFFIX = new State<>("gnsf");
		public final static State<Char> TEXT_HOTKEY = new State<>("ghk1");
		public final static State<String> TEXT_NAME = new State<>("gnam");
		public final static State<String> TEXT_TOOLTIP = new State<>("gtp1");
		public final static State<String> TEXT_TOOLTIP_UBER = new State<>("gub1");
	}
	
	public static class Obj extends ObjMod.Obj {
		public Obj(ObjId id, ObjId baseId) {
			super(id, baseId);
		}
	}
}
