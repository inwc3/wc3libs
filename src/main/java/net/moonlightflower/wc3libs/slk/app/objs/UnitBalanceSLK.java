package net.moonlightflower.wc3libs.slk.app.objs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.AttributeType;
import net.moonlightflower.wc3libs.dataTypes.app.Bool;
import net.moonlightflower.wc3libs.dataTypes.app.DefType;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.ItemId;
import net.moonlightflower.wc3libs.dataTypes.app.PathingPrevent;
import net.moonlightflower.wc3libs.dataTypes.app.PathingRequire;
import net.moonlightflower.wc3libs.dataTypes.app.Real;
import net.moonlightflower.wc3libs.dataTypes.app.RegenType;
import net.moonlightflower.wc3libs.dataTypes.app.Tileset;
import net.moonlightflower.wc3libs.dataTypes.app.UnitClass;
import net.moonlightflower.wc3libs.dataTypes.app.UnitId;
import net.moonlightflower.wc3libs.dataTypes.app.UpgradeId;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.SLK;
import net.moonlightflower.wc3libs.slk.SLKState;
import net.moonlightflower.wc3libs.slk.app.objs.ItemSLK.Obj;

public class UnitBalanceSLK extends ObjSLK<UnitBalanceSLK, UnitId, UnitBalanceSLK.Obj> {
	public final static File GAME_USE_PATH = new File("Units\\UnitBalance.slk");
	
	public static class States {
		static public class State<T extends DataType> extends SLKState<T> {
			private static List<State> _values = new ArrayList<>();
			
			public static List<State> values() {
				return _values;
			}
			
			public State(String idString, DataTypeInfo typeInfo, T defVal) {
				super(idString, typeInfo, defVal);
				
				_values.add(this);
			}
			
			public State(String idString, DataTypeInfo typeInfo) {
				this(idString, typeInfo, null);
			}

			public State(String idString, Class<T> type) {
				this(idString, new DataTypeInfo(type));
			}
			
			public State(String idString, Class<T> type, T defVal) {
				this(idString, new DataTypeInfo(type), defVal);
			}
		}
		
		public static List<State> values() {
			return State.values();
		}
		
		public final static State<UnitId> OBJ_ID = new State<>("unitBalanceID", UnitId.class);

		public final static State<Int> DATA_ABIL_TEST = new State<>("abilTest", Int.class);
		public final static State<Int> DATA_ARMOR = new State<>("def", Int.class);
		public final static State<Int> DATA_ARMOR_REAL = new State<>("realdef", Int.class);
		public final static State<DefType> DATA_ARMOR_TYPE = new State<>("defType", DefType.class);
		public final static State<Int> DATA_ARMOR_UP = new State<>("defUp", Int.class);
		public final static State<Int> DATA_BOUNTY_GOLD_BASE = new State<>("bountyplus", Int.class);
		public final static State<Int> DATA_BOUNTY_GOLD_DICE = new State<>("bountydice", Int.class);
		public final static State<Int> DATA_BOUNTY_GOLD_DICE_SIDES = new State<>("bountysides", Int.class);
		public final static State<Int> DATA_BOUNTY_LUMBER_BASE = new State<>("lumberbountyplus", Int.class);
		public final static State<Int> DATA_BOUNTY_LUMBER_DICE = new State<>("lumberbountydice", Int.class);
		public final static State<Int> DATA_BOUNTY_LUMBER_DICE_SIDES = new State<>("lumberbountysides", Int.class);
		public final static State<Int> DATA_BUILD_TIME = new State<>("bldtm", Int.class);
		public final static State<UnitClass> DATA_CLASS = new State<>("type", UnitClass.class);
		public final static State<Real> DATA_COLLISION = new State<>("collision", Real.class);
		public final static State<Int> DATA_COSTS_GOLD = new State<>("goldcost", Int.class);
		public final static State<Int> DATA_COSTS_GOLD_REPAIR = new State<>("goldRep", Int.class);
		public final static State<Int> DATA_COSTS_LUMBER = new State<>("lumbercost", Int.class);
		public final static State<Int> DATA_COSTS_LUMBER_REPAIR = new State<>("lumberRep", Int.class);
		public final static State<Int> DATA_HERO_ATTR_AGI = new State<>("AGI", Int.class);
		public final static State<Int> DATA_HERO_ATTR_AGI_UP = new State<>("AGIplus", Int.class);
		public final static State<Int> DATA_HERO_ATTR_INT = new State<>("INT", Int.class);
		public final static State<Int> DATA_HERO_ATTR_INT_UP = new State<>("INTplus", Int.class);
		public final static State<AttributeType> DATA_HERO_ATTR_PRIMARY = new State<>("Primary", AttributeType.class);
		public final static State<Int> DATA_HERO_ATTR_STR = new State<>("STR", Int.class);
		public final static State<Int> DATA_HERO_ATTR_STR_UP = new State<>("STRplus", Int.class);
		static final public State<Bool> DATA_IS_STRUCTURE = new State<>("isbldg", Bool.class);
		public final static State<Int> DATA_LEVEL = new State<>("level", Int.class);
		public final static State<Int> DATA_LIFE = new State<>("HP", Int.class);
		public final static State<Int> DATA_LIFE_REAL = new State<>("realHP", Int.class);
		public final static State<RegenType> DATA_LIFE_REGEN_TYPE = new State<>("regenType", RegenType.class);
		public final static State<Real> DATA_LIFE_REGEN = new State<>("regenHP", Real.class);
		public final static State<Int> DATA_MANA = new State<>("manaN", Int.class);
		public final static State<Int> DATA_MANA_INITIAL = new State<>("mana0", Int.class);
		public final static State<Int> DATA_MANA_REAL = new State<>("realM", Int.class);
		public final static State<Int> DATA_MANA_REGEN = new State<>("regenMana", Int.class);
		public final static State<Bool> DATA_MOVEMENT_REPULSE = new State<>("repulse", Bool.class);
		public final static State<Int> DATA_MOVEMENT_REPULSE_GROUP = new State<>("repulseGroup", Int.class);
		public final static State<Int> DATA_MOVEMENT_REPULSE_PARAM = new State<>("repulseParam", Int.class);
		public final static State<Int> DATA_MOVEMENT_REPULSE_PRIO = new State<>("repulsePrio", Int.class);
		public final static State<Int> DATA_MOVEMENT_SPEED = new State<>("spd", Int.class);
		public final static State<Int> DATA_MOVEMENT_SPEED_MAX = new State<>("maxSpd", Int.class);
		public final static State<Int> DATA_MOVEMENT_SPEED_MIN = new State<>("minSpd", Int.class);
		static final public State<Bool> DATA_NEUTRAL_STRUCTURE_RANDOMED = new State<>("nbrandom", Bool.class);
		public final static State<Int> DATA_REPAIR_TIME = new State<>("reptm", Int.class);
		public final static State<Int> DATA_SIGHT_RANGE_DAY = new State<>("sight", Int.class);
		public final static State<Int> DATA_SIGHT_RANGE_NIGHT = new State<>("nsight", Int.class);
		public final static State<Int> DATA_STOCK_INITIAL = new State<>("stockStart", Int.class);
		public final static State<Int> DATA_STOCK_MAX = new State<>("stockMax", Int.class);
		public final static State<Int> DATA_STOCK_REGEN = new State<>("stockRegen", Int.class);
		public final static State<Int> DATA_SUPPLY_PRODUCED = new State<>("fmade", Int.class);
		public final static State<Int> DATA_SUPPLY_USED = new State<>("fused", Int.class);
		
		public final static State<Wc3String> EDITOR_COMMENT = new State<>("comment(s)", Wc3String.class);
		public final static State<Bool> EDITOR_IN_BETA = new State<>("InBeta", Bool.class);
		public final static State<Wc3String> EDITOR_SORT = new State<>("sortBalance", Wc3String.class);
		public final static State<Wc3String> EDITOR_SORT2 = new State<>("sort2", Wc3String.class);
		public final static State<DataList<Tileset>> EDITOR_TILESETS = new State<>("tilesets", new DataTypeInfo(DataList.class, Tileset.class));
		
		static final public State<DataList<PathingPrevent>> PATH_PREVENT_PLACE = new State<>("preventPlace", new DataTypeInfo(DataList.class, PathingPrevent.class));
		static final public State<DataList<PathingRequire>> PATH_REQUIRE_PLACE = new State<>("requirePlace", new DataTypeInfo(DataList.class, PathingRequire.class));
		
		public final static State<DataList<UpgradeId>> TECH_UPGRADES = new State<>("upgrades", new DataTypeInfo(DataList.class, UpgradeId.class));
	}
	
	public static class Obj extends SLK.Obj<UnitId> {
		public <T extends DataType> T get(States.State<T> state) {
			return state.tryCastVal(super.get(state.getFieldId()));
		}
		
		public <T extends DataType> void set(States.State<T> state, T val) {
			super.set(state.getFieldId(), val);
		}
		
		private void read(SLK.Obj<? extends ObjId> slkObj) {
			merge(slkObj, true);
		}
		
		public Obj(SLK.Obj<? extends ObjId> slkObj) {
			this(UnitId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(UnitId id) {
			super(id);

			for (States.State state : States.values()) {
				set(state, state.getDefVal());
			}
		}

		@Override
		public void reduce() {
			// TODO Auto-generated method stub
			
		}
	}
	
	//private Map<UnitId, Obj> _objs = new HashMap<>();
	
	@Override
	public Map<UnitId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Override
	public Obj addObj(UnitId id) {
		if (_objs.containsKey(id)) return _objs.get(id);
		
		Obj obj = new Obj(id);
		
		addObj(obj);
		
		return obj;
	}
	
	@Override
	protected void read(SLK<?, ? extends ObjId, ? extends SLK.Obj<? extends ObjId>> slk) {
		for (Entry<? extends ObjId, ? extends SLK.Obj<? extends ObjId>> slkEntry : slk.getObjs().entrySet()) {
			ObjId id = slkEntry.getKey();
			SLK.Obj<? extends ObjId> slkObj = slkEntry.getValue();
			
			Obj obj = new Obj(slkObj);

			addObj(obj);
		}
	}
	
	@Override
	public void read(File file) throws IOException {
		super.read(file);
	}
	
	@Override
	public void write(File file) throws IOException {
		super.write(file);
	}
	
	public UnitBalanceSLK(SLK slk) {
		read(slk);
	}
	
	public UnitBalanceSLK() {
		super();
		
		addField(States.OBJ_ID);
		
		for (States.State state : States.values()) {
			addField(state);
		}
	}

	public UnitBalanceSLK(File file) throws IOException {
		this();
		
		read(file);
	}
	
	@Override
	public Obj createObj(ObjId id) {
		return new Obj(UnitId.valueOf(id));
	}

	@Override
	public void merge(UnitBalanceSLK other, boolean overwrite) {
		for (Map.Entry<UnitId, Obj> objEntry : other.getObjs().entrySet()) {
			UnitId objId = objEntry.getKey();
			Obj otherObj = objEntry.getValue();
			
			Obj obj = addObj(objId);
			
			obj.merge(otherObj);
		}
	}
}
