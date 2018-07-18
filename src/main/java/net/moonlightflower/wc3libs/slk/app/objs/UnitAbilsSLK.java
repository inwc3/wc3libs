package net.moonlightflower.wc3libs.slk.app.objs;

import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.AbilId;
import net.moonlightflower.wc3libs.dataTypes.app.War3Bool;
import net.moonlightflower.wc3libs.dataTypes.app.UnitId;
import net.moonlightflower.wc3libs.dataTypes.app.War3String;
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

public class UnitAbilsSLK extends ObjSLK<UnitAbilsSLK, UnitId, UnitAbilsSLK.Obj> {
	public final static File GAME_PATH = new File("Units\\UnitAbilities.slk");

	public static class State<T extends DataType> extends ObjSLK.State<T> {
		public final static State<UnitId> OBJ_ID = new State<>("unitAbilID", UnitId.class);

		public final static State<War3String> EDITOR_SORT = new State<>("sortAbil", War3String.class);
		public final static State<War3String> EDITOR_COMMENT = new State<>("comment(s)", War3String.class);
		public final static State<AbilId> DATA_AUTO = new State<>("auto", AbilId.class);

		public final static State<DataList<AbilId>> DATA_ABILS = new State<>("abilList", new DataTypeInfo(DataList.class, AbilId.class));
		public final static State<DataList<AbilId>> DATA_ABILS_HERO = new State<>("heroAbilList", new DataTypeInfo(DataList.class, AbilId.class));
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
				if (!super.contains(state.getFieldId())) return state.getDefVal();

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
	
	/*@Override
	public Map<UnitId, Camera> getCameras() {
		return _objs;
	}
	
	@Override
	public void addCamera(Camera val) {
		_objs.put(val.getId(), val);
	}
	
	@Override
	public Camera addCamera(UnitId id) {
		if (_objs.containsKey(id)) return _objs.get(id);
		
		Camera obj = new Camera(id);
		
		addCamera(obj);
		
		return obj;
	}*/
	
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
		for (Obj obj : getObjs().values()) {
			DataType auto = obj.get(FieldId.valueOf("auto"));
			
			if ((auto == null) || auto.toString().isEmpty()) {
				obj.set(FieldId.valueOf("auto"), War3String.valueOf("_"));
			}
		}
		
		super.write(file);
	}
	
	public UnitAbilsSLK(SLK slk) {
		read(slk);
	}
	
	public UnitAbilsSLK() {
		super();
		
		addField(State.OBJ_ID);
		
		for (State state : State.values(State.class)) {
			addField(state);
		}
	}

	public UnitAbilsSLK(File file) throws IOException {
		this();
		
		read(file);
	}
	
	@Nonnull
    @Override
	public Obj createObj(@Nonnull ObjId id) {
		return new Obj(UnitId.valueOf(id));
	}

	@Override
	public void merge(@Nonnull UnitAbilsSLK other, boolean overwrite) {
		for (Map.Entry<UnitId, Obj> objEntry : other.getObjs().entrySet()) {
			UnitId objId = objEntry.getKey();
			Obj otherObj = objEntry.getValue();
			
			Obj obj = addObj(objId);
			
			obj.merge(otherObj);
		}
	}
}
