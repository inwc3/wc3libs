package net.moonlightflower.wc3libs.slk.app.objs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3Int;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.SLK;
import net.moonlightflower.wc3libs.slk.SLKState;

public class UpgradeSLK extends ObjSLK<UpgradeSLK, UpgradeId, UpgradeSLK.Obj> {
	public final static File GAME_USE_PATH = new File("Units\\UpgradeData.slk");
	
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
		
		public final static State<UpgradeId> OBJ_ID = new State<>("upgradeid", UpgradeId.class, null);

		public final static State<UpgradeClass> DATA_CLASS = new State<>("class", UpgradeClass.class);
		public final static State<Wc3Int> DATA_COSTS_GOLD = new State<>("goldbase", Wc3Int.class);
		public final static State<Wc3Int> DATA_COSTS_GOLD_INC = new State<>("goldmod", Wc3Int.class);
		public final static State<Wc3Int> DATA_COSTS_LUMBER = new State<>("lumberbase", Wc3Int.class);
		public final static State<Wc3Int> DATA_COSTS_LUMBER_INC = new State<>("lumbermod", Wc3Int.class);
		public final static State<Wc3Int> DATA_COSTS_TIME = new State<>("timebase", Wc3Int.class);
		public final static State<Wc3Int> DATA_COSTS_TIME_ADD = new State<>("timemod", Wc3Int.class);
		public final static State<UpgradeEffect> DATA_EFFECT1 = new State<>("effect1", UpgradeEffect.class);
		public final static State<Real> DATA_EFFECT1_BASE = new State<>("base1", Real.class);
		public final static State<Real> DATA_EFFECT1_INC = new State<>("mod1", Real.class);
		public final static State<Wc3String> DATA_EFFECT1_CODE = new State<>("code1", Wc3String.class);
		public final static State<UpgradeEffect> DATA_EFFECT2 = new State<>("effect2", UpgradeEffect.class);
		public final static State<Real> DATA_EFFECT2_BASE = new State<>("base2", Real.class);
		public final static State<Real> DATA_EFFECT2_INC = new State<>("mod2", Real.class);
		public final static State<Wc3String> DATA_EFFECT2_CODE = new State<>("code2", Wc3String.class);
		public final static State<UpgradeEffect> DATA_EFFECT3 = new State<>("effect3", UpgradeEffect.class);
		public final static State<Real> DATA_EFFECT3_BASE = new State<>("base3", Real.class);
		public final static State<Real> DATA_EFFECT3_INC = new State<>("mod3", Real.class);
		public final static State<Wc3String> DATA_EFFECT3_CODE = new State<>("code3", Wc3String.class);
		public final static State<UpgradeEffect> DATA_EFFECT4 = new State<>("effect4", UpgradeEffect.class);
		public final static State<Real> DATA_EFFECT4_BASE = new State<>("base4", Real.class);
		public final static State<Real> DATA_EFFECT4_INC = new State<>("mod4", Real.class);
		public final static State<Wc3String> DATA_EFFECT4_CODE = new State<>("code4", Wc3String.class);
		public final static State<Bool> DATA_IS_GLOBAL = new State<>("global", Bool.class);
		public final static State<Bool> DATA_IS_INHERITED = new State<>("inherit", Bool.class);
		public final static State<Wc3Int> DATA_LEVEL_COUNT = new State<>("maxlevel", Wc3Int.class);
		public final static State<UnitRace> DATA_RACE = new State<>("race", UnitRace.class);
		
		public final static State<Wc3String> EDITOR_COMMENTS = new State<>("comments", Wc3String.class);
		public final static State<Bool> EDITOR_IN_BETA = new State<>("InBeta", Bool.class);
		public final static State<Wc3String> EDITOR_SORT = new State<>("sort", Wc3String.class);
		public final static State<Bool> EDITOR_USED = new State<>("used", Bool.class);
		public final static State<Wc3Int> EDITOR_VERSION = new State<>("version", Wc3Int.class);
	}
	
	public static class Obj extends SLK.Obj<UpgradeId> {
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
			this(UpgradeId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(UpgradeId id) {
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
	
	//private Map<UpgradeId, Camera> _objs = new LinkedHashMap<>();
	
	@Override
	public Map<UpgradeId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Override
	public Obj addObj(UpgradeId id) {
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
	
	public UpgradeSLK(SLK slk) {
		read(slk);
	}
	
	public UpgradeSLK() {
		super();
		
		addField(States.OBJ_ID);
		
		for (States.State state : States.values()) {
			addField(state);
		}
	}
	
	public UpgradeSLK(File file) throws IOException {
		this();
		
		read(file);
	}
	
	@Override
	public Obj createObj(ObjId id) {
		return new Obj(UpgradeId.valueOf(id));
	}

	@Override
	public void merge(UpgradeSLK other, boolean overwrite) {
		for (Map.Entry<UpgradeId, Obj> objEntry : other.getObjs().entrySet()) {
			UpgradeId objId = objEntry.getKey();
			Obj otherObj = objEntry.getValue();
			
			Obj obj = addObj(objId);
			
			obj.merge(otherObj);
		}
	}
}
