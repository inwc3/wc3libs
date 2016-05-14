package slk.app.objs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dataTypes.DataList;
import dataTypes.DataType;
import dataTypes.app.AbilCode;
import dataTypes.app.AbilId;
import dataTypes.app.Bool;
import dataTypes.app.BuffId;
import dataTypes.app.CombatTarget;
import dataTypes.app.Int;
import dataTypes.app.Real;
import dataTypes.app.UnitId;
import dataTypes.app.UnitRace;
import dataTypes.app.Wc3String;
import misc.ObjId;
import misc.StateInfo;
import slk.ObjSLK;
import slk.SLK;
import slk.SLKState;

public class AbilSLK extends ObjSLK<AbilSLK, AbilId, AbilSLK.Obj> {
	public final static File GAME_USE_PATH = new File("Units\\AbilityData.slk");
	
	public static class States {
		static public class State<T extends DataType> extends SLKState<T> {
			private static List<State> _values = new ArrayList<>();
			
			public static List<State> values() {
				return _values;
			}
			
			public State(String idString, StateInfo typeInfo, T defVal) {
				super(idString, typeInfo, defVal);
				
				_values.add(this);
			}
			
			public State(String idString, StateInfo typeInfo) {
				this(idString, typeInfo, null);
			}

			public State(String idString, Class<T> type) {
				this(idString, StateInfo.valueOf(type));
			}
			
			public State(String idString, Class<T> type, T defVal) {
				this(idString, StateInfo.valueOf(type), defVal);
			}
		}
		
		public static List<State> values() {
			return State.values();
		}

		public final static State<AbilId> OBJ_ID = new State<>("alias", AbilId.class);
		
		public final static State<DataList<BuffId>> ART_EFFECT_ID = new State<>("EfctID", StateInfo.listOf(BuffId.class));
		
		public final static State<DataList<Real>> DATA_AREA = new State<>("Area", StateInfo.listOf(Real.class));
		public final static State<DataList<BuffId>> DATA_BUFF_ID = new State<>("BuffID", StateInfo.listOf(BuffId.class));
		public final static State<DataList<Real>> DATA_CAST_TIME = new State<>("Cast", StateInfo.listOf(Real.class));
		public final static State<DataList<Real>> DATA_COOLDOWN = new State<>("Cool", StateInfo.listOf(Real.class));
		public final static State<Bool> DATA_CHECK_DEPENDENCY = new State<>("checkDep", Bool.class);
		public final static State<AbilCode> DATA_CODE = new State<>("code", AbilCode.class);
		public final static State<DataList<DataType>> DATA_A = new State<>("DataA", StateInfo.listOf(DataType.class));
		public final static State<DataList<DataType>> DATA_B = new State<>("DataA", StateInfo.listOf(DataType.class));
		public final static State<DataList<DataType>> DATA_C = new State<>("DataA", StateInfo.listOf(DataType.class));
		public final static State<DataList<DataType>> DATA_D = new State<>("DataA", StateInfo.listOf(DataType.class));
		public final static State<DataList<DataType>> DATA_E = new State<>("DataA", StateInfo.listOf(DataType.class));
		public final static State<DataList<DataType>> DATA_F = new State<>("DataA", StateInfo.listOf(DataType.class));
		public final static State<DataList<DataType>> DATA_G = new State<>("DataA", StateInfo.listOf(DataType.class));
		public final static State<DataList<DataType>> DATA_H = new State<>("DataA", StateInfo.listOf(DataType.class));
		public final static State<DataList<DataType>> DATA_I = new State<>("DataA", StateInfo.listOf(DataType.class));
		public final static State<DataList<Real>> DATA_DURATION = new State<>("Dur", StateInfo.listOf(Real.class));
		public final static State<DataList<Real>> DATA_DURATION_HERO = new State<>("HeroDur", StateInfo.listOf(Real.class));
		public final static State<Bool> DATA_IS_HERO = new State<>("hero", Bool.class);
		public final static State<Bool> DATA_IS_ITEM = new State<>("item", Bool.class);
		public final static State<Int> DATA_LEVEL_COUNT = new State<>("levels", Int.class);
		public final static State<Int> DATA_LEVEL_REQ = new State<>("reqLevel", Int.class);
		public final static State<Int> DATA_LEVEL_SKIP = new State<>("levelSkip", Int.class);
		public final static State<DataList<Int>> DATA_MANA_COST = new State<>("Cost", StateInfo.listOf(Int.class));
		public final static State<Int> DATA_PRIO = new State<>("priority", Int.class);
		public final static State<UnitRace> DATA_RACE = new State<>("race", UnitRace.class);
		public final static State<DataList<Real>> DATA_RANGE = new State<>("Rng", StateInfo.listOf(Real.class));
		public final static State<DataList<CombatTarget>> DATA_TARGS = new State<>("targs", StateInfo.listOf(CombatTarget.class));
		public final static State<DataList<UnitId>> DATA_UNIT_ID = new State<>("UnitID", StateInfo.listOf(UnitId.class));
		
		public final static State<Wc3String> EDITOR_COMMENTS = new State<>("comments", Wc3String.class);
		public final static State<Bool> EDITOR_IN_BETA = new State<>("InBeta", Bool.class);
		public final static State<Bool> EDITOR_IN_EDITOR = new State<>("useInEditor", Bool.class);
		public final static State<Wc3String> EDITOR_SORT = new State<>("sort", Wc3String.class);
		public final static State<Int> EDITOR_VERSION = new State<>("version", Int.class);
	}
	
	public static class Obj extends SLK.Obj<AbilId> {
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
			this(AbilId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(AbilId id) {
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
	
	private Map<AbilId, Obj> _objs = new HashMap<>();
	
	@Override
	public Map<AbilId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Override
	public Obj addObj(AbilId id) {
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
	
	public AbilSLK(SLK slk) {
		read(slk);
	}
	
	public AbilSLK() {
		super();
		
		addField(States.OBJ_ID);
		
		for (States.State state : States.values()) {
			addField(state);
		}
	}

	@Override
	public Obj createObj(ObjId id) {
		return new Obj(AbilId.valueOf(id));
	}

	@Override
	public void merge(AbilSLK other, boolean overwrite) {
		// TODO Auto-generated method stub
		
	}
}
