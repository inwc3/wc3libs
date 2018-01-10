package net.moonlightflower.wc3libs.slk.app.objs;

import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.AbilId;
import net.moonlightflower.wc3libs.dataTypes.app.Bool;
import net.moonlightflower.wc3libs.dataTypes.app.UnitId;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.SLK;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class UnitAbilsSLK extends ObjSLK<UnitAbilsSLK, UnitId, UnitAbilsSLK.Obj> {
	public final static File GAME_USE_PATH = new File("Units\\UnitAbilities.slk");
	
	public static class States {
		public static List<State> values() {
			return State.values();
		}

		public final static State<UnitId> OBJ_ID = new State<>("unitAbilID", UnitId.class);

		public final static State<Wc3String> EDITOR_SORT = new State<>("sortAbil", Wc3String.class);
		public final static State<Wc3String> EDITOR_COMMENT = new State<>("comment(s)", Wc3String.class);
		public final static State<AbilId> DATA_AUTO = new State<>("auto", AbilId.class);

		public final static State<DataList<AbilId>> DATA_ABILS = new State<>("abilList", new DataTypeInfo(DataList.class, AbilId.class));
		public final static State<DataList<AbilId>> DATA_ABILS_HERO = new State<>("heroAbilList", new DataTypeInfo(DataList.class, AbilId.class));
		public final static State<Bool> EDITOR_IN_BETA = new State<>("InBeta", Bool.class);
	}
	
	public static class Obj extends SLK.Obj<UnitId> {
		public <T extends DataType> T get(State<T> state) {
			return state.tryCastVal(super.get(state.getFieldId()));
		}
		
		public <T extends DataType> void set(State<T> state, T val) {
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

			for (State state : States.values()) {
				set(state, state.getDefVal());
			}
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
	public void read(File file) throws IOException {
		super.read(file);
	}
	
	@Override
	public void write(File file) throws IOException {
		for (Obj obj : getObjs().values()) {
			DataType auto = obj.get(FieldId.valueOf("auto"));
			
			if ((auto == null) || auto.toString().isEmpty()) {
				obj.set(FieldId.valueOf("auto"), Wc3String.valueOf("_"));
			}
		}
		
		super.write(file);
	}
	
	public UnitAbilsSLK(SLK slk) {
		read(slk);
	}
	
	public UnitAbilsSLK() {
		super();
		
		addField(States.OBJ_ID);
		
		for (State state : States.values()) {
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
