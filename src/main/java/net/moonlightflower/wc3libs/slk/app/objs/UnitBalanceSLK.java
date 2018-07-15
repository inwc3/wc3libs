package net.moonlightflower.wc3libs.slk.app.objs;

import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.SLK;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class UnitBalanceSLK extends ObjSLK<UnitBalanceSLK, UnitId, UnitBalanceSLK.Obj> {
	public final static File GAME_PATH = new File("Units\\UnitBalance.slk");

	public static class State<T extends DataType> extends ObjSLK.State<T> {
		public final static State<UnitId> OBJ_ID = new State<>("unitBalanceID", UnitId.class);
		public final static State<War3String> EDITOR_SORT = new State<>("sortBalance", War3String.class);
		public final static State<War3String> EDITOR_SORT2 = new State<>("sort2", War3String.class);
		public final static State<War3String> EDITOR_COMMENT = new State<>("comment(s)", War3String.class);
		public final static State<War3Int> DATA_LEVEL = new State<>("level", War3Int.class);
		public final static State<UnitClass> DATA_CLASS = new State<>("type", UnitClass.class);
		public final static State<War3Int> DATA_COSTS_GOLD = new State<>("goldcost", War3Int.class);
		public final static State<War3Int> DATA_COSTS_LUMBER = new State<>("lumbercost", War3Int.class);
		public final static State<War3Int> DATA_COSTS_GOLD_REPAIR = new State<>("goldRep", War3Int.class);
		public final static State<War3Int> DATA_COSTS_LUMBER_REPAIR = new State<>("lumberRep", War3Int.class);
		public final static State<War3Int> DATA_SUPPLY_PRODUCED = new State<>("fmade", War3Int.class);
		public final static State<War3Int> DATA_SUPPLY_USED = new State<>("fused", War3Int.class);
		public final static State<War3Int> DATA_BOUNTY_GOLD_DICE = new State<>("bountydice", War3Int.class);
		public final static State<War3Int> DATA_BOUNTY_GOLD_DICE_SIDES = new State<>("bountysides", War3Int.class);
		public final static State<War3Int> DATA_BOUNTY_GOLD_BASE = new State<>("bountyplus", War3Int.class);
		public final static State<War3Int> DATA_BOUNTY_LUMBER_DICE = new State<>("lumberbountydice", War3Int.class);
		public final static State<War3Int> DATA_BOUNTY_LUMBER_DICE_SIDES = new State<>("lumberbountysides", War3Int.class);
		public final static State<War3Int> DATA_BOUNTY_LUMBER_BASE = new State<>("lumberbountyplus", War3Int.class);
		public final static State<War3Int> DATA_STOCK_MAX = new State<>("stockMax", War3Int.class);
		public final static State<War3Int> DATA_STOCK_REGEN = new State<>("stockRegen", War3Int.class);
		public final static State<War3Int> DATA_STOCK_INITIAL = new State<>("stockStart", War3Int.class);
		public final static State<War3Int> DATA_LIFE = new State<>("HP", War3Int.class);
		public final static State<War3Int> DATA_LIFE_REAL = new State<>("realHP", War3Int.class);
		public final static State<War3Real> DATA_LIFE_REGEN = new State<>("regenHP", War3Real.class);
		public final static State<RegenType> DATA_LIFE_REGEN_TYPE = new State<>("regenType", RegenType.class);
		public final static State<War3Int> DATA_MANA = new State<>("manaN", War3Int.class);
		public final static State<War3Int> DATA_MANA_REAL = new State<>("realM", War3Int.class);
		public final static State<War3Int> DATA_MANA_INITIAL = new State<>("mana0", War3Int.class);
		public final static State<War3Int> DATA_MANA_REGEN = new State<>("regenMana", War3Int.class);
		public final static State<War3Int> DATA_ARMOR = new State<>("def", War3Int.class);
		public final static State<War3Int> DATA_ARMOR_UP = new State<>("defUp", War3Int.class);
		public final static State<War3Int> DATA_ARMOR_REAL = new State<>("realdef", War3Int.class);
		public final static State<DefType> DATA_ARMOR_TYPE = new State<>("defType", DefType.class);
		public final static State<War3Int> DATA_MOVEMENT_SPEED = new State<>("spd", War3Int.class);
		public final static State<War3Int> DATA_MOVEMENT_SPEED_MIN = new State<>("minSpd", War3Int.class);
		public final static State<War3Int> DATA_MOVEMENT_SPEED_MAX = new State<>("maxSpd", War3Int.class);
		public final static State<War3Int> DATA_BUILD_TIME = new State<>("bldtm", War3Int.class);
		public final static State<War3Int> DATA_REPAIR_TIME = new State<>("reptm", War3Int.class);
		public final static State<War3Int> DATA_SIGHT_RANGE_DAY = new State<>("sight", War3Int.class);
		public final static State<War3Int> DATA_SIGHT_RANGE_NIGHT = new State<>("nsight", War3Int.class);
		public final static State<War3Int> DATA_HERO_ATTR_STR = new State<>("STR", War3Int.class);
		public final static State<War3Int> DATA_HERO_ATTR_INT = new State<>("INT", War3Int.class);
		public final static State<War3Int> DATA_HERO_ATTR_AGI = new State<>("AGI", War3Int.class);
		public final static State<War3Int> DATA_HERO_ATTR_STR_UP = new State<>("STRplus", War3Int.class);
		public final static State<War3Int> DATA_HERO_ATTR_INT_UP = new State<>("INTplus", War3Int.class);
		public final static State<War3Int> DATA_HERO_ATTR_AGI_UP = new State<>("AGIplus", War3Int.class);
		public final static State<War3Int> DATA_ABIL_TEST = new State<>("abilTest", War3Int.class);
		public final static State<AttributeType> DATA_HERO_ATTR_PRIMARY = new State<>("Primary", AttributeType.class);
		public final static State<DataList<UpgradeId>> TECH_UPGRADES = new State<>("upgrades", new DataTypeInfo(DataList.class, UpgradeId.class));
		public final static State<DataList<Tileset>> EDITOR_TILESETS = new State<>("tilesets", new DataTypeInfo(DataList.class, Tileset.class));
		static final public State<War3Bool> DATA_NEUTRAL_STRUCTURE_RANDOMED = new State<>("nbrandom", War3Bool.class);
		static final public State<War3Bool> DATA_IS_STRUCTURE = new State<>("isbldg", War3Bool.class);
		static final public State<DataList<PathingPrevent>> PATH_PREVENT_PLACE = new State<>("preventPlace", new DataTypeInfo(DataList.class, PathingPrevent.class));
		static final public State<DataList<PathingRequire>> PATH_REQUIRE_PLACE = new State<>("requirePlace", new DataTypeInfo(DataList.class, PathingRequire.class));
		public final static State<War3Bool> DATA_MOVEMENT_REPULSE = new State<>("repulse", War3Bool.class);
		public final static State<War3Int> DATA_MOVEMENT_REPULSE_PARAM = new State<>("repulseParam", War3Int.class);
		public final static State<War3Int> DATA_MOVEMENT_REPULSE_GROUP = new State<>("repulseGroup", War3Int.class);
		public final static State<War3Int> DATA_MOVEMENT_REPULSE_PRIO = new State<>("repulsePrio", War3Int.class);
		public final static State<War3Real> DATA_COLLISION = new State<>("collision", War3Real.class);

		public final static State<War3Bool> EDITOR_IN_BETA = new State<>("InBeta", War3Bool.class);

		public State(@Nonnull String idString, @Nonnull DataTypeInfo typeInfo, @Nullable T defVal) {
			super(idString, typeInfo, defVal);
		}

		public State(@Nonnull String idString, @Nonnull DataTypeInfo typeInfo) {
			super(idString, typeInfo);
		}

		public State(@Nonnull String idString, @Nonnull Class<T> type) {
			super(idString, type);
		}

		public State(@Nonnull String idString, @Nonnull Class<T> type, @Nullable T defVal) {
			super(idString, type, defVal);
		}
	}

	public static class Obj extends SLK.Obj<UnitId> {
		private final Map<State, DataType> _stateVals = new LinkedHashMap<>();

		@Override
		public Map<State, DataType> getStateVals() {
			return new LinkedHashMap<>(_stateVals);
		}

		@Override
		protected void on_set(@Nonnull FieldId fieldId, @Nullable DataType val) {
			State state = (State) State.valueByField(State.class, fieldId);

			if (state != null) _stateVals.put(state, val);
		}

		@Override
		protected void on_remove(@Nonnull FieldId fieldId) {
			State state = (State) State.valueByField(State.class, fieldId);

			if (state != null) _stateVals.remove(state);
		}

		@Override
		protected void on_clear() {
			_stateVals.clear();
		}

		public <T extends DataType> T get(State<T> state) {
			try {
				return state.tryCastVal(super.get(state.getFieldId()));
			} catch (DataTypeInfo.CastException ignored) {
			}

			return null;
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

			for (State<?> state : State.values(State.class)) {
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
	public void write(@Nonnull File file) throws IOException {
		super.write(file);
	}

	public UnitBalanceSLK(SLK slk) {
		read(slk);
	}

	public UnitBalanceSLK() {
		super();

		addField(State.OBJ_ID);

		for (State state : State.values(State.class)) {
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
