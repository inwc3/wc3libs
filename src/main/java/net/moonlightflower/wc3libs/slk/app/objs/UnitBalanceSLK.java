package net.moonlightflower.wc3libs.slk.app.objs;

import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.SLK;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class UnitBalanceSLK extends ObjSLK<UnitBalanceSLK, UnitId, UnitBalanceSLK.Obj> {
	public final static File GAME_USE_PATH = new File("Units\\UnitBalance.slk");

	public static class States {
		public static List<State> values() {
			return State.values();
		}

		public final static State<UnitId> OBJ_ID = new State<>("unitBalanceID", UnitId.class);
		public final static State<Wc3String> EDITOR_SORT = new State<>("sortBalance", Wc3String.class);
		public final static State<Wc3String> EDITOR_SORT2 = new State<>("sort2", Wc3String.class);
		public final static State<Wc3String> EDITOR_COMMENT = new State<>("comment(s)", Wc3String.class);
		public final static State<Wc3Int> DATA_LEVEL = new State<>("level", Wc3Int.class);
		public final static State<UnitClass> DATA_CLASS = new State<>("type", UnitClass.class);
		public final static State<Wc3Int> DATA_COSTS_GOLD = new State<>("goldcost", Wc3Int.class);
		public final static State<Wc3Int> DATA_COSTS_LUMBER = new State<>("lumbercost", Wc3Int.class);
		public final static State<Wc3Int> DATA_COSTS_GOLD_REPAIR = new State<>("goldRep", Wc3Int.class);
		public final static State<Wc3Int> DATA_COSTS_LUMBER_REPAIR = new State<>("lumberRep", Wc3Int.class);
		public final static State<Wc3Int> DATA_SUPPLY_PRODUCED = new State<>("fmade", Wc3Int.class);
		public final static State<Wc3Int> DATA_SUPPLY_USED = new State<>("fused", Wc3Int.class);
		public final static State<Wc3Int> DATA_BOUNTY_GOLD_DICE = new State<>("bountydice", Wc3Int.class);
		public final static State<Wc3Int> DATA_BOUNTY_GOLD_DICE_SIDES = new State<>("bountysides", Wc3Int.class);
		public final static State<Wc3Int> DATA_BOUNTY_GOLD_BASE = new State<>("bountyplus", Wc3Int.class);
		public final static State<Wc3Int> DATA_BOUNTY_LUMBER_DICE = new State<>("lumberbountydice", Wc3Int.class);
		public final static State<Wc3Int> DATA_BOUNTY_LUMBER_DICE_SIDES = new State<>("lumberbountysides", Wc3Int.class);
		public final static State<Wc3Int> DATA_BOUNTY_LUMBER_BASE = new State<>("lumberbountyplus", Wc3Int.class);
		public final static State<Wc3Int> DATA_STOCK_MAX = new State<>("stockMax", Wc3Int.class);
		public final static State<Wc3Int> DATA_STOCK_REGEN = new State<>("stockRegen", Wc3Int.class);
		public final static State<Wc3Int> DATA_STOCK_INITIAL = new State<>("stockStart", Wc3Int.class);
		public final static State<Wc3Int> DATA_LIFE = new State<>("HP", Wc3Int.class);
		public final static State<Wc3Int> DATA_LIFE_REAL = new State<>("realHP", Wc3Int.class);
		public final static State<Real> DATA_LIFE_REGEN = new State<>("regenHP", Real.class);
		public final static State<RegenType> DATA_LIFE_REGEN_TYPE = new State<>("regenType", RegenType.class);
		public final static State<Wc3Int> DATA_MANA = new State<>("manaN", Wc3Int.class);
		public final static State<Wc3Int> DATA_MANA_REAL = new State<>("realM", Wc3Int.class);
		public final static State<Wc3Int> DATA_MANA_INITIAL = new State<>("mana0", Wc3Int.class);
		public final static State<Wc3Int> DATA_MANA_REGEN = new State<>("regenMana", Wc3Int.class);
		public final static State<Wc3Int> DATA_ARMOR = new State<>("def", Wc3Int.class);
		public final static State<Wc3Int> DATA_ARMOR_UP = new State<>("defUp", Wc3Int.class);
		public final static State<Wc3Int> DATA_ARMOR_REAL = new State<>("realdef", Wc3Int.class);
		public final static State<DefType> DATA_ARMOR_TYPE = new State<>("defType", DefType.class);
		public final static State<Wc3Int> DATA_MOVEMENT_SPEED = new State<>("spd", Wc3Int.class);
		public final static State<Wc3Int> DATA_MOVEMENT_SPEED_MIN = new State<>("minSpd", Wc3Int.class);
		public final static State<Wc3Int> DATA_MOVEMENT_SPEED_MAX = new State<>("maxSpd", Wc3Int.class);
		public final static State<Wc3Int> DATA_BUILD_TIME = new State<>("bldtm", Wc3Int.class);
		public final static State<Wc3Int> DATA_REPAIR_TIME = new State<>("reptm", Wc3Int.class);
		public final static State<Wc3Int> DATA_SIGHT_RANGE_DAY = new State<>("sight", Wc3Int.class);
		public final static State<Wc3Int> DATA_SIGHT_RANGE_NIGHT = new State<>("nsight", Wc3Int.class);
		public final static State<Wc3Int> DATA_HERO_ATTR_STR = new State<>("STR", Wc3Int.class);
		public final static State<Wc3Int> DATA_HERO_ATTR_INT = new State<>("INT", Wc3Int.class);
		public final static State<Wc3Int> DATA_HERO_ATTR_AGI = new State<>("AGI", Wc3Int.class);
		public final static State<Wc3Int> DATA_HERO_ATTR_STR_UP = new State<>("STRplus", Wc3Int.class);
		public final static State<Wc3Int> DATA_HERO_ATTR_INT_UP = new State<>("INTplus", Wc3Int.class);
		public final static State<Wc3Int> DATA_HERO_ATTR_AGI_UP = new State<>("AGIplus", Wc3Int.class);
		public final static State<Wc3Int> DATA_ABIL_TEST = new State<>("abilTest", Wc3Int.class);
		public final static State<AttributeType> DATA_HERO_ATTR_PRIMARY = new State<>("Primary", AttributeType.class);
		public final static State<DataList<UpgradeId>> TECH_UPGRADES = new State<>("upgrades", new DataTypeInfo(DataList.class, UpgradeId.class));
		public final static State<DataList<Tileset>> EDITOR_TILESETS = new State<>("tilesets", new DataTypeInfo(DataList.class, Tileset.class));
		static final public State<Bool> DATA_NEUTRAL_STRUCTURE_RANDOMED = new State<>("nbrandom", Bool.class);
		static final public State<Bool> DATA_IS_STRUCTURE = new State<>("isbldg", Bool.class);
		static final public State<DataList<PathingPrevent>> PATH_PREVENT_PLACE = new State<>("preventPlace", new DataTypeInfo(DataList.class, PathingPrevent.class));
		static final public State<DataList<PathingRequire>> PATH_REQUIRE_PLACE = new State<>("requirePlace", new DataTypeInfo(DataList.class, PathingRequire.class));
		public final static State<Bool> DATA_MOVEMENT_REPULSE = new State<>("repulse", Bool.class);
		public final static State<Wc3Int> DATA_MOVEMENT_REPULSE_PARAM = new State<>("repulseParam", Wc3Int.class);
		public final static State<Wc3Int> DATA_MOVEMENT_REPULSE_GROUP = new State<>("repulseGroup", Wc3Int.class);
		public final static State<Wc3Int> DATA_MOVEMENT_REPULSE_PRIO = new State<>("repulsePrio", Wc3Int.class);
		public final static State<Real> DATA_COLLISION = new State<>("collision", Real.class);

		public final static State<Bool> EDITOR_IN_BETA = new State<>("InBeta", Bool.class);
	}

	public static class Obj extends SLK.Obj<UnitId> {
		public <T extends DataType> T get(State<T> state) {
			return state.tryCastVal(super.get(state.getFieldId()));
		}

		public <T extends DataType> void set(State<T> state, T val) {
			super.set(state.getFieldId(), val);
		}

		private void read(SLK.Obj<? extends ObjId> slkObj) {
			merge(slkObj, true);
		}

		public Obj(SLK.Obj<? extends ObjId> slkObj) {
			super(UnitId.valueOf(slkObj.getId()));

			read(slkObj);
		}

		public Obj(UnitId id) {
			super(id);

			for (State state : States.values()) {
				set(state, state.getDefVal());
			}
		}

		@Override
		public void reduce() {
			// TODO Auto-generated method stub

		}
	}

	//private Map<UnitId, Camera> _objs = new LinkedHashMap<>();

	@Nonnull
	@Override
	public Map<UnitId, Obj> getObjs() {
		return _objs;
	}

	@Override
	public void addObj(@Nonnull Obj val) {
		_objs.put(val.getId(), val);
	}

	@Nonnull
	@Override
	public Obj addObj(@Nonnull UnitId id) {
		if (_objs.containsKey(id)) return _objs.get(id);

		Obj obj = new Obj(id);

		addObj(obj);

		return obj;
	}

	@Override
	protected void read(@Nonnull SLK<?, ? extends ObjId, ? extends SLK.Obj<? extends ObjId>> slk) {
		for (Entry<? extends ObjId, ? extends SLK.Obj<? extends ObjId>> slkEntry : slk.getObjs().entrySet()) {
			ObjId id = slkEntry.getKey();
			SLK.Obj<? extends ObjId> slkObj = slkEntry.getValue();

			Obj obj = new Obj(slkObj);

			addObj(obj);
		}
	}

	@Override
	public void read(@Nonnull File file) throws IOException {
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

		for (State state : States.values()) {
			addField(state);
		}
	}

	public UnitBalanceSLK(File file) throws IOException {
		this();

		read(file);
	}

	@Nonnull
	@Override
	public Obj createObj(@Nonnull ObjId id) {
		return new Obj(UnitId.valueOf(id));
	}

	@Override
	public void merge(@Nonnull UnitBalanceSLK other, boolean overwrite) {
		for (Map.Entry<UnitId, Obj> objEntry : other.getObjs().entrySet()) {
			UnitId objId = objEntry.getKey();
			Obj otherObj = objEntry.getValue();

			Obj obj = addObj(objId);

			obj.merge(otherObj);
		}
	}
}
