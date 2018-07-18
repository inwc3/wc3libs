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

public class UnitDataSLK extends ObjSLK<UnitDataSLK, UnitId, UnitDataSLK.Obj> {
	public final static File GAME_PATH = new File("Units\\UnitData.slk");

	public static class State<T extends DataType> extends ObjSLK.State<T> {
		public final static State<UnitId> OBJ_ID = new State<>("unitID", UnitId.class);

		public final static State<War3String> EDITOR_SORT = new State<>("sort", War3String.class);
		public final static State<War3String> EDITOR_COMMENTS = new State<>("comment(s)", War3String.class);
		public final static State<UnitRace> EDITOR_RACE = new State<>("race", UnitRace.class);
		public final static State<War3Int> DATA_PRIO = new State<>("prio", War3Int.class);
		public final static State<War3Bool> DATA_THREAT = new State<>("threat", War3Bool.class);
		public final static State<War3Bool> DATA_VALID = new State<>("valid", War3Bool.class);
		public final static State<DeathType> DATA_DEATH_TYPE = new State<>("deathType", DeathType.class);
		public final static State<War3Real> DATA_DEATH_TIME = new State<>("death", War3Real.class);
		public final static State<War3Bool> DATA_CAN_SLEEP = new State<>("canSleep", War3Bool.class);
		public final static State<War3Int> DATA_CARGO_USED = new State<>("cargoSize", War3Int.class);
		public final static State<MoveType> MOVEMENT_TYPE = new State<>("movetp", MoveType.class);
		public final static State<War3Int> MOVEMENT_HEIGHT = new State<>("moveHeight", War3Int.class);
		public final static State<War3Int> MOVEMENT_HEIGHT_MIN = new State<>("moveFloor", War3Int.class);
		public final static State<War3Real> MOVEMENT_TURN_RATE = new State<>("turnRate", War3Real.class);
		public final static State<War3Real> MOVEMENT_PROP_WINDOW = new State<>("propWin", War3Real.class);
		public final static State<War3Int> ART_ORIENT_INTERPOLATION = new State<>("orientInterp", War3Int.class);
		public final static State<War3Int> DATA_FORMATION = new State<>("formation", War3Int.class);
		public final static State<DataList<CombatTarget>> COMBAT_TARGS = new State<>("targType", new DataTypeInfo(DataList.class, CombatTarget.class));
		public final static State<PathingTex> PATH_TEX = new State<>("pathTex", PathingTex.class);
		public final static State<War3Bool> ART_FAT_LINE_OF_SIGHT = new State<>("fatLOS", War3Bool.class);
		public final static State<War3Int> DATA_POINT_VALUE = new State<>("points", War3Int.class);
		static final public State<AIPlaceType> PATH_STRUCTURE_AI_PLACE_TYPE = new State<>("buffType", AIPlaceType.class);
		static final public State<War3Real> PATH_STRUCTURE_AI_PLACE_RAD = new State<>("buffRadius", War3Real.class);
		public final static State<War3Int> TEXT_HERO_NAMES_COUNT = new State<>("nameCount", War3Int.class);
		public final static State<War3Bool> DATA_CAN_FLEE = new State<>("canFlee", War3Bool.class);
		static final public State<War3Real> PATH_STRUCTURE_REQUIRE_WATER_RAD = new State<>("requireWaterRadius", War3Real.class);
		static final public State<War3Bool> DATA_STRUCTURE_IS_BUILD_ON = new State<>("isBuildOn", War3Bool.class);
		static final public State<War3Bool> DATA_STRUCTURE_CAN_BUILD_ON = new State<>("canBuildOn", War3Bool.class);
		public final static State<War3Bool> EDITOR_IN_BETA = new State<>("InBeta", War3Bool.class);
		public final static State<War3Int> EDITOR_VERSION = new State<>("version", War3Int.class);

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
				if (!super.contains(state.getFieldId())) return state.getDefVal();

				return state.tryCastVal(super.get(state));
			} catch (DataTypeInfo.CastException ignored) {
			}

			return null;
		}
		
		public <T extends DataType> void set(State<T> state, T val) {
			super.set(state, val);
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

			/*for (State<?> state : State.values(State.class)) {
				set(state, state.getDefVal());
			}*/
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
	
	public UnitDataSLK(SLK slk) {
		read(slk);
	}
	
	public UnitDataSLK() {
		super();
		
		addField(State.OBJ_ID);
		
		for (State state : State.values(State.class)) {
			addField(state);
		}
	}

	public UnitDataSLK(File file) throws IOException {
		this();
		
		read(file);
	}
	
	@Nonnull
	@Override
	public Obj createObj(@Nonnull ObjId id) {
		return new Obj(UnitId.valueOf(id));
	}

	@Override
	public void merge(@Nonnull UnitDataSLK other, boolean overwrite) {
		for (Map.Entry<UnitId, Obj> objEntry : other.getObjs().entrySet()) {
			UnitId objId = objEntry.getKey();
			Obj otherObj = objEntry.getValue();
			
			Obj obj = addObj(objId);
			
			obj.merge(otherObj);
		}
	}
}
