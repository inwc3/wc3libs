package net.moonlightflower.wc3libs.slk.app.objs;

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

public class UpgradeSLK extends ObjSLK<UpgradeSLK, UpgradeId, UpgradeSLK.Obj> {
	public final static File GAME_PATH = new File("Units\\UpgradeData.slk");

	public static class State<T extends DataType> extends ObjSLK.State<T> {
		public final static State<UpgradeId> OBJ_ID = new State<>("upgradeid", UpgradeId.class, null);

		public final static State<UpgradeClass> DATA_CLASS = new State<>("class", UpgradeClass.class);
		public final static State<War3Int> DATA_COSTS_GOLD = new State<>("goldbase", War3Int.class);
		public final static State<War3Int> DATA_COSTS_GOLD_INC = new State<>("goldmod", War3Int.class);
		public final static State<War3Int> DATA_COSTS_LUMBER = new State<>("lumberbase", War3Int.class);
		public final static State<War3Int> DATA_COSTS_LUMBER_INC = new State<>("lumbermod", War3Int.class);
		public final static State<War3Int> DATA_COSTS_TIME = new State<>("timebase", War3Int.class);
		public final static State<War3Int> DATA_COSTS_TIME_ADD = new State<>("timemod", War3Int.class);
		public final static State<UpgradeEffect> DATA_EFFECT1 = new State<>("effect1", UpgradeEffect.class);
		public final static State<War3Real> DATA_EFFECT1_BASE = new State<>("base1", War3Real.class);
		public final static State<War3Real> DATA_EFFECT1_INC = new State<>("mod1", War3Real.class);
		public final static State<War3String> DATA_EFFECT1_CODE = new State<>("code1", War3String.class);
		public final static State<UpgradeEffect> DATA_EFFECT2 = new State<>("effect2", UpgradeEffect.class);
		public final static State<War3Real> DATA_EFFECT2_BASE = new State<>("base2", War3Real.class);
		public final static State<War3Real> DATA_EFFECT2_INC = new State<>("mod2", War3Real.class);
		public final static State<War3String> DATA_EFFECT2_CODE = new State<>("code2", War3String.class);
		public final static State<UpgradeEffect> DATA_EFFECT3 = new State<>("effect3", UpgradeEffect.class);
		public final static State<War3Real> DATA_EFFECT3_BASE = new State<>("base3", War3Real.class);
		public final static State<War3Real> DATA_EFFECT3_INC = new State<>("mod3", War3Real.class);
		public final static State<War3String> DATA_EFFECT3_CODE = new State<>("code3", War3String.class);
		public final static State<UpgradeEffect> DATA_EFFECT4 = new State<>("effect4", UpgradeEffect.class);
		public final static State<War3Real> DATA_EFFECT4_BASE = new State<>("base4", War3Real.class);
		public final static State<War3Real> DATA_EFFECT4_INC = new State<>("mod4", War3Real.class);
		public final static State<War3String> DATA_EFFECT4_CODE = new State<>("code4", War3String.class);
		public final static State<War3Bool> DATA_IS_GLOBAL = new State<>("global", War3Bool.class);
		public final static State<War3Bool> DATA_IS_INHERITED = new State<>("inherit", War3Bool.class);
		public final static State<War3Int> DATA_LEVEL_COUNT = new State<>("maxlevel", War3Int.class);
		public final static State<UnitRace> DATA_RACE = new State<>("race", UnitRace.class);

		public final static State<War3String> EDITOR_COMMENTS = new State<>("comments", War3String.class);
		public final static State<War3Bool> EDITOR_IN_BETA = new State<>("InBeta", War3Bool.class);
		public final static State<War3String> EDITOR_SORT = new State<>("sort", War3String.class);
		public final static State<War3Bool> EDITOR_USED = new State<>("used", War3Bool.class);
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
	
	public static class Obj extends SLK.Obj<UpgradeId> {
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
			super(UpgradeId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(UpgradeId id) {
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
	
	//private Map<UpgradeId, Camera> _objs = new LinkedHashMap<>();
	
	@Nonnull
	@Override
	public Map<UpgradeId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(@Nonnull Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Nonnull
	@Override
	public Obj addObj(@Nonnull UpgradeId id) {
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
	
	public UpgradeSLK(SLK slk) {
		read(slk);
	}
	
	public UpgradeSLK() {
		super();
		
		addField(State.OBJ_ID);
		
		for (State<?> state : State.values(State.class)) {
			addField(state);
		}
	}
	
	public UpgradeSLK(File file) throws IOException {
		this();
		
		read(file);
	}
	
	@Nonnull
	@Override
	public Obj createObj(@Nonnull ObjId id) {
		return new Obj(UpgradeId.valueOf(id));
	}

	@Override
	public void merge(@Nonnull UpgradeSLK other, boolean overwrite) {
		for (Map.Entry<UpgradeId, Obj> objEntry : other.getObjs().entrySet()) {
			UpgradeId objId = objEntry.getKey();
			Obj otherObj = objEntry.getValue();
			
			Obj obj = addObj(objId);
			
			obj.merge(otherObj);
		}
	}
}
