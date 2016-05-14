package slk.app.objs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dataTypes.DataType;
import dataTypes.app.AbilId;
import dataTypes.app.AbilList;
import dataTypes.app.Bool;
import dataTypes.app.UnitId;
import dataTypes.app.Wc3String;
import misc.ObjId;
import misc.StateInfo;
import slk.ObjSLK;
import slk.SLK;
import slk.SLKState;

public class UnitAbilsSLK extends ObjSLK<UnitAbilsSLK, UnitId, UnitAbilsSLK.Obj> {
	public final static File GAME_USE_PATH = new File("Units\\UnitAbilities.slk");
	
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

		public final static State<UnitId> OBJ_ID = new State<>("unitAbilID", UnitId.class);
		
		public final static State<AbilList> DATA_ABILS = new State<>("abilList", AbilList.class);
		public final static State<AbilList> DATA_ABILS_HERO = new State<>("heroAbilList", AbilList.class);
		public final static State<AbilId> DATA_AUTO = new State<>("auto", AbilId.class);
		
		public final static State<Wc3String> EDITOR_COMMENT = new State<>("comment(s)", Wc3String.class);
		public final static State<Bool> EDITOR_IN_BETA = new State<>("InBeta", Bool.class);
		public final static State<Wc3String> EDITOR_SORT = new State<>("sortAbil", Wc3String.class);
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
	
	private Map<UnitId, Obj> _objs = new HashMap<>();
	
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
	
	public UnitAbilsSLK() {
		super();
		
		addField(States.OBJ_ID);
		
		for (States.State state : States.values()) {
			addField(state);
		}
	}

	@Override
	public Obj createObj(ObjId id) {
		return new Obj(UnitId.valueOf(id));
	}

	@Override
	public void merge(UnitAbilsSLK other, boolean overwrite) {
		// TODO Auto-generated method stub
		
	}
}
