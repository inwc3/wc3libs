package slk.app.splats;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dataTypes.DataType;
import dataTypes.app.Bool;
import dataTypes.app.Int;
import dataTypes.app.Model;
import dataTypes.app.SpawnId;
import misc.ObjId;
import misc.StateInfo;
import slk.ObjSLK;
import slk.RawSLK;
import slk.SLK;
import slk.SLKState;

public class SpawnSLK extends ObjSLK<SpawnSLK, SpawnId, SpawnSLK.Obj> {
	public final static File GAME_USE_PATH = new File("Splats\\SpawnData.slk");
	
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

		public final static State<SpawnId> OBJ_ID = new State<>("Name", SpawnId.class);
		
		public final static State<Model> ART_MODEL = new State<>("Model", Model.class);
		
		public final static State<Bool> EDITOR_IN_BETA = new State<>("InBeta", Bool.class);
		public final static State<Int> EDITOR_VERSION = new State<>("version", Int.class);
	}
	
	public static class Obj extends SLK.Obj<SpawnId> {
		public Model getModel() {
			return get(States.ART_MODEL);
		}
		
		public void setModel(Model val) {
			set(States.ART_MODEL, val);
		}

		public <T extends DataType> T get(States.State<T> state) {
			return (T) super.get(state);
		}
		
		public <T extends DataType> void set(States.State<T> state, T val) {
			super.set(state, val);
		}
		
		public <T extends DataType> void remove(States.State<T> state) {
			super.set(state, null);
		}
		
		private void read(SLK.Obj<? extends ObjId> slkObj) {
			merge(slkObj, true);
		}
		
		public Obj(SLK.Obj<? extends ObjId> slkObj) {
			this(SpawnId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(SpawnId id) {
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
	
	private Map<SpawnId, Obj> _objs = new HashMap<>();
	
	@Override
	public Map<SpawnId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Override
	public Obj addObj(SpawnId id) {
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
		read(new RawSLK(file));
	}
	
	public SpawnSLK() {
		addField(States.OBJ_ID);

		for (States.State state : States.values()) {
			addField(state);
		}
	}

	@Override
	public Obj createObj(ObjId id) {
		return new Obj(SpawnId.valueOf(id));
	}

	@Override
	public void merge(SpawnSLK other, boolean overwrite) {
		// TODO Auto-generated method stub
		
	}
}
