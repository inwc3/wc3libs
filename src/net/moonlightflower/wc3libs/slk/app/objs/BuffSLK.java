package net.moonlightflower.wc3libs.slk.app.objs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.Bool;
import net.moonlightflower.wc3libs.dataTypes.app.BuffCode;
import net.moonlightflower.wc3libs.dataTypes.app.BuffId;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.ItemId;
import net.moonlightflower.wc3libs.dataTypes.app.UnitRace;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.SLK;
import net.moonlightflower.wc3libs.slk.SLKState;
import net.moonlightflower.wc3libs.slk.app.objs.ItemSLK.Obj;

public class BuffSLK extends ObjSLK<BuffSLK, BuffId, BuffSLK.Obj> {
	public final static File GAME_USE_PATH = new File("Units\\AbilityBuffData.slk");
	
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
	
	//private Map<BuffId, Obj> _objs = new HashMap<>();
	
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
	
	public BuffSLK(SLK slk) {
		read(slk);
	}
	
	public BuffSLK() {
		super();
		
		addField(States.OBJ_ID);
		
		for (States.State state : States.values()) {
			addField(state);
		}
	}
	
	public BuffSLK(File file) throws IOException {
		this();
		
		read(file);
	}

	@Override
	public Obj createObj(ObjId id) {
		return new Obj(BuffId.valueOf(id));
	}

	@Override
	public void merge(BuffSLK other, boolean overwrite) {
		for (Map.Entry<BuffId, Obj> objEntry : other.getObjs().entrySet()) {
			BuffId objId = objEntry.getKey();
			Obj otherObj = objEntry.getValue();
			
			Obj obj = addObj(objId);
			
			obj.merge(otherObj);
		}
	}
}
