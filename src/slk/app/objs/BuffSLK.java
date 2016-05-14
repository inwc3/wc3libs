package slk.app.objs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dataTypes.DataType;
import dataTypes.app.Bool;
import dataTypes.app.BuffCode;
import dataTypes.app.BuffId;
import dataTypes.app.Int;
import dataTypes.app.UnitRace;
import dataTypes.app.Wc3String;
import misc.ObjId;
import misc.StateInfo;
import slk.ObjSLK;
import slk.SLK;
import slk.SLKState;

public class BuffSLK extends ObjSLK<BuffSLK, BuffId, BuffSLK.Obj> {
	public final static File GAME_USE_PATH = new File("Units\\AbilityBuffData.slk");
	
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

		public final static State<BuffId> OBJ_ID = new State<>("alias", BuffId.class);
		
		public static class Data {
			public final static State<BuffCode> DATA_CODE = new State<>("code", BuffCode.class);
			public final static State<Bool> DATA_IS_EFFECT = new State<>("isEffect", Bool.class);
			public final static State<UnitRace> DATA_RACE = new State<>("race", UnitRace.class);
		}
		
		public static class Editor {
			public final static State<Wc3String> EDITOR_COMMENTS = new State<>("comments", Wc3String.class);
			public final static State<Bool> EDITOR_IN_BETA = new State<>("InBeta", Bool.class);
			public final static State<Bool> EDITOR_IN_EDITOR = new State<>("useInEditor", Bool.class);
			public final static State<Wc3String> EDITOR_SORT = new State<>("sort", Wc3String.class);
			public final static State<Int> EDITOR_VERSION = new State<>("version", Int.class);
		}
	}
	
	public static class Obj extends SLK.Obj<BuffId> {
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
			this(BuffId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(BuffId id) {
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
	
	private Map<BuffId, Obj> _objs = new HashMap<>();
	
	@Override
	public Map<BuffId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Override
	public Obj addObj(BuffId id) {
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
	
	public BuffSLK() {
		super();
		
		addField(States.OBJ_ID);
		
		for (States.State state : States.values()) {
			addField(state);
		}
	}

	@Override
	public Obj createObj(ObjId id) {
		return new Obj(BuffId.valueOf(id));
	}

	@Override
	public void merge(BuffSLK other, boolean overwrite) {
		// TODO Auto-generated method stub
		
	}
}
