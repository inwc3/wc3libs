package bin.app.objMod;

import java.util.ArrayList;
import java.util.List;

import bin.MetaState;
import bin.ObjMod;
import dataTypes.app.AbilCode;
import dataTypes.app.AbilList;
import dataTypes.app.Bool;
import dataTypes.app.Char;
import dataTypes.app.DefType;
import dataTypes.app.Icon;
import dataTypes.app.Int;
import dataTypes.app.IntList;
import dataTypes.app.ItemClass;
import dataTypes.app.Model;
import dataTypes.app.Real;
import dataTypes.app.RequireList;
import misc.ObjId;
import slk.SLKState;

/**
 * item modifications file for wrapping war3map.w3t
 */
public class W3T extends ObjMod {
	public final static String GAME_PATH = "war3map.w3t";
	
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
		
		public final static State<AbilList> ABIL_ABILS = new State<>("iabi");
		
		public final static State<Int> ART_BUTTON_POS_X = new State<>("ubpx");
		public final static State<Int> ART_BUTTON_POS_Y = new State<>("ubpy");
		public final static State<Int> ART_COLOR_BLUE = new State<>("iclb");
		public final static State<Int> ART_COLOR_GREEN = new State<>("iclg");
		public final static State<Int> ART_COLOR_RED = new State<>("iclr");
		public final static State<Icon> ART_ICON = new State<>("iico");
		public final static State<Model> ART_MODEL = new State<>("ifil");
		public final static State<Real> ART_SCALE = new State<>("isca");
		public final static State<Real> ART_SELECTION_SCALE = new State<>("issc");
		
		public final static State<DefType> COMBAT_ARMOR_TYPE = new State<>("iarm");
		
		public final static State<Int> DATA_CHARGES = new State<>("iuse");
		public final static State<ItemClass> DATA_CLASS = new State<>("icla");
		public final static State<AbilCode> DATA_COOLDOWN_ID = new State<>("icid");
		public final static State<Int> DATA_COST_GOLD = new State<>("igol");
		public final static State<Int> DATA_COST_LUMBER = new State<>("ilum");
		public final static State<Bool> DATA_DROPPABLE = new State<>("idro");
		public final static State<Bool> DATA_DROPPED = new State<>("idrp");
		public final static State<Bool> DATA_IGNORE_COOLDOWN = new State<>("iicd");
		public final static State<Int> DATA_LEVEL = new State<>("ilev");
		public final static State<Int> DATA_LEVEL_OLD = new State<>("ilvo");
		public final static State<Int> DATA_LIFE = new State<>("ihtp");
		public final static State<Bool> DATA_MORPHABLE = new State<>("imor");
		public final static State<Bool> DATA_PAWNABLE = new State<>("ipaw");
		public final static State<Bool> DATA_PERISHABLE = new State<>("iper");
		public final static State<Bool> DATA_POWERUP = new State<>("ipow");
		public final static State<Int> DATA_PRIO = new State<>("ipri");
		public final static State<Bool> DATA_RANDOMED = new State<>("iprn");
		public final static State<Bool> DATA_SELLABLE = new State<>("isel");
		public final static State<Int> DATA_STOCK_MAX = new State<>("isto");
		public final static State<Int> DATA_STOCK_REGEN = new State<>("istr");
		public final static State<Int> DATA_STOCK_INITIAL = new State<>("isst");
		public final static State<Bool> DATA_USABLE = new State<>("iusa");
		
		public final static State<RequireList> TECH_REQUIRES = new State<>("ureq");
		public final static State<IntList> TECH_REQUIRES_COUNT = new State<>("urqa");

		public final static State<String> TEXT_DESCRIPTION = new State<>("ides");
		public final static State<Char> TEXT_HOTKEY = new State<>("uhot");
		public final static State<String> TEXT_NAME = new State<>("unam");
		public final static State<String> TEXT_TOOLTIP = new State<>("utip");
		public final static State<String> TEXT_TOOLTIP_UBER = new State<>("utub");
	}
	
	public static class Obj extends ObjMod.Obj {
		public Obj(ObjId id, ObjId baseId) {
			super(id, baseId);
		}
	}
}
