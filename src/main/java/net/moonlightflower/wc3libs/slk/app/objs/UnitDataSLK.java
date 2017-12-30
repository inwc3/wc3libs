package net.moonlightflower.wc3libs.slk.app.objs;

import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.SLK;
import net.moonlightflower.wc3libs.slk.SLKState;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class UnitDataSLK extends ObjSLK<UnitDataSLK, UnitId, UnitDataSLK.Obj> {
	public final static File GAME_USE_PATH = new File("Units\\UnitData.slk");
	
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
		
		public final static State<UnitId> OBJ_ID = new State<>("unitID", UnitId.class);

		public final static State<Wc3String> EDITOR_SORT = new State<>("sort", Wc3String.class);
		public final static State<Wc3String> EDITOR_COMMENTS = new State<>("comment(s)", Wc3String.class);
		public final static State<UnitRace> EDITOR_RACE = new State<>("race", UnitRace.class);
		public final static State<Wc3Int> DATA_PRIO = new State<>("prio", Wc3Int.class);
		public final static State<Bool> DATA_THREAT = new State<>("threat", Bool.class);
		public final static State<Bool> DATA_VALID = new State<>("valid", Bool.class);
		public final static State<DeathType> DATA_DEATH_TYPE = new State<>("deathType", DeathType.class);
		public final static State<Real> DATA_DEATH_TIME = new State<>("death", Real.class);
		public final static State<Bool> DATA_CAN_SLEEP = new State<>("canSleep", Bool.class);
		public final static State<Wc3Int> DATA_CARGO_USED = new State<>("cargoSize", Wc3Int.class);
		public final static State<MoveType> MOVEMENT_TYPE = new State<>("movetp", MoveType.class);
		public final static State<Wc3Int> MOVEMENT_HEIGHT = new State<>("moveHeight", Wc3Int.class);
		public final static State<Wc3Int> MOVEMENT_HEIGHT_MIN = new State<>("moveFloor", Wc3Int.class);
		public final static State<Real> MOVEMENT_TURN_RATE = new State<>("turnRate", Real.class);
		public final static State<Real> MOVEMENT_PROP_WINDOW = new State<>("propWin", Real.class);
		public final static State<Wc3Int> ART_ORIENT_INTERPOLATION = new State<>("orientInterp", Wc3Int.class);
		public final static State<Wc3Int> DATA_FORMATION = new State<>("formation", Wc3Int.class);
		public final static State<DataList<CombatTarget>> COMBAT_TARGS = new State<>("targType", new DataTypeInfo(DataList.class, CombatTarget.class));
		public final static State<PathingTex> PATH_TEX = new State<>("pathTex", PathingTex.class);
		public final static State<Bool> ART_FAT_LINE_OF_SIGHT = new State<>("fatLOS", Bool.class);
		public final static State<Wc3Int> DATA_POINT_VALUE = new State<>("points", Wc3Int.class);
		static final public State<AIPlaceType> PATH_STRUCTURE_AI_PLACE_TYPE = new State<>("buffType", AIPlaceType.class);
		static final public State<Real> PATH_STRUCTURE_AI_PLACE_RAD = new State<>("buffRadius", Real.class);
		public final static State<Wc3Int> TEXT_HERO_NAMES_COUNT = new State<>("nameCount", Wc3Int.class);
		public final static State<Bool> DATA_CAN_FLEE = new State<>("canFlee", Bool.class);
		static final public State<Real> PATH_STRUCTURE_REQUIRE_WATER_RAD = new State<>("requireWaterRadius", Real.class);
		static final public State<Bool> DATA_STRUCTURE_IS_BUILD_ON = new State<>("isBuildOn", Bool.class);
		static final public State<Bool> DATA_STRUCTURE_CAN_BUILD_ON = new State<>("canBuildOn", Bool.class);
		public final static State<Bool> EDITOR_IN_BETA = new State<>("InBeta", Bool.class);
		public final static State<Wc3Int> EDITOR_VERSION = new State<>("version", Wc3Int.class);
	}
	
	public static class Obj extends SLK.Obj<UnitId> {
		public <T extends DataType> T get(States.State<T> state) {
			return state.tryCastVal(super.get(state));
		}
		
		public <T extends DataType> void set(States.State<T> state, T val) {
			super.set(state, val);
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
	public void read(File file) throws IOException {
		super.read(file);
	}
	
	@Override
	public void write(File file) throws IOException {
		super.write(file);
	}
	
	public UnitDataSLK(SLK slk) {
		read(slk);
	}
	
	public UnitDataSLK() {
		super();
		
		addField(States.OBJ_ID);
		
		for (States.State state : States.values()) {
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
