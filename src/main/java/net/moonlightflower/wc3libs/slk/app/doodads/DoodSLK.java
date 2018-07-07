package net.moonlightflower.wc3libs.slk.app.doodads;

import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.SLK;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DoodSLK extends ObjSLK<DoodSLK, DoodId, DoodSLK.Obj> {
	public final static File GAME_USE_PATH = new File("Doodads\\Doodads.slk");
	
	public static class States {
		public static class State<T extends DataType> extends ObjSLK.State<T> {
			public State(String idString, DataTypeInfo typeInfo, T defVal) {
				super(idString, typeInfo, defVal);
			}

			public State(String idString, DataTypeInfo typeInfo) {
				super(idString, typeInfo);
			}

			public State(String idString, Class<T> type) {
				super(idString, type);
			}

			public State(String idString, Class<T> type, T defVal) {
				super(idString, type, defVal);
			}
		}

		public static Collection<State> values() {
			return (Collection<State>) State.values(State.class);
		}

		public final static State<DoodId> OBJ_ID = new State<>("doodID", DoodId.class);
		
		public final static State<Char> CATEGORY = new State<>("category", Char.class);
		public final static State<DataList<Tileset>> TILESETS = new State<>("tilesets", new DataTypeInfo(DataList.class, Tileset.class));
		public final static State<Bool> TILESET_SPECIFIC = new State<>("tilesetSpecific", Bool.class);
		public final static State<Model> MODEL = new State<>("file", Model.class);
		public final static State<Wc3String> COMMENT = new State<>("comment", Wc3String.class);
		public final static State<Wc3String> NAME = new State<>("Name", Wc3String.class);
		public final static State<DoodadClass> CLASS = new State<>("doodClass", DoodadClass.class);
		public final static State<SoundLabel> SOUND_LOOP = new State<>("soundLoop", SoundLabel.class);
		public final static State<Real> SELECTION_SIZE = new State<>("selSize", Real.class);//unreal
		public final static State<Real> SCALE_DEFAULT = new State<>("defScale", Real.class);//unreal
		public final static State<Real> SCALE_MIN = new State<>("minScale", Real.class);//unreal
		public final static State<Real> SCALE_MAX = new State<>("maxScale", Real.class);//unreal
		public final static State<Bool> CAN_PLACE_RAND_SCALE = new State<>("canPlaceRandScale", Bool.class);
		public final static State<Bool> USE_CLICK_HELPER = new State<>("useClickHelper", Bool.class);
		public final static State<Bool> IGNORE_MODEL_CLICK = new State<>("ignoreModelClick", Bool.class);
		public final static State<Real> PITCH_MAX = new State<>("maxPitch", Real.class);//unreal
		public final static State<Real> ROLL_MAX = new State<>("maxRoll", Real.class);//unreal
		public final static State<Real> VISIBLE_RADIUS = new State<>("visRadius", Real.class);//unreal
		public final static State<Bool> WALKABLE = new State<>("walkable", Bool.class);
		public final static State<Wc3Int> NUM_VAR = new State<>("numVar", Wc3Int.class);
		public final static State<Bool> ON_CLIFFS = new State<>("onCliffs", Bool.class);
		public final static State<Bool> ON_WATER = new State<>("onWater", Bool.class);
		public final static State<Bool> FLOATS = new State<>("floats", Bool.class);
		public final static State<Bool> CASTS_SHADOW = new State<>("shadow", Bool.class);
		public final static State<Bool> SHOW_IN_FOG = new State<>("showInFog", Bool.class);
		public final static State<Bool> ANIM_IN_FOG = new State<>("animInFog", Bool.class);
		public final static State<Real> FIXED_ROT = new State<>("fixedRot", Real.class);//unreal
		public final static State<PathingTex> PATH_TEX = new State<>("pathTex", PathingTex.class);
		public final static State<Bool> SHOW_IN_MINIMAP = new State<>("showInMM", Bool.class);
		public final static State<Bool> USE_MINIMAP_COLOR = new State<>("useMMColor", Bool.class);
		public final static State<Wc3Int> MINIMAP_RED = new State<>("MMRed", Wc3Int.class);
		public final static State<Wc3Int> MINIMAP_GREEN = new State<>("MMGreen", Wc3Int.class);
		public final static State<Wc3Int> MINIMAP_BLUE = new State<>("MMBlue", Wc3Int.class);
		public final static State<Wc3Int> COLOR_RED1 = new State<>("vertR01", Wc3Int.class);
		public final static State<Wc3Int> COLOR_GREEN1 = new State<>("vertG01", Wc3Int.class);
		public final static State<Wc3Int> COLOR_BLUE1 = new State<>("vertB01", Wc3Int.class);
		public final static State<Wc3Int> COLOR_RED2 = new State<>("vertR02", Wc3Int.class);
		public final static State<Wc3Int> COLOR_GREEN2 = new State<>("vertG02", Wc3Int.class);
		public final static State<Wc3Int> COLOR_BLUE2 = new State<>("vertB02", Wc3Int.class);
		public final static State<Wc3Int> COLOR_RED3 = new State<>("vertR03", Wc3Int.class);
		public final static State<Wc3Int> COLOR_GREEN3 = new State<>("vertG03", Wc3Int.class);
		public final static State<Wc3Int> COLOR_BLUE3 = new State<>("vertB03", Wc3Int.class);
		public final static State<Wc3Int> COLOR_RED4 = new State<>("vertR04", Wc3Int.class);
		public final static State<Wc3Int> COLOR_GREEN4 = new State<>("vertG04", Wc3Int.class);
		public final static State<Wc3Int> COLOR_BLUE4 = new State<>("vertB04", Wc3Int.class);
		public final static State<Wc3Int> COLOR_RED5 = new State<>("vertR05", Wc3Int.class);
		public final static State<Wc3Int> COLOR_GREEN5 = new State<>("vertG05", Wc3Int.class);
		public final static State<Wc3Int> COLOR_BLUE5 = new State<>("vertB05", Wc3Int.class);
		public final static State<Wc3Int> COLOR_RED6 = new State<>("vertR06", Wc3Int.class);
		public final static State<Wc3Int> COLOR_GREEN6 = new State<>("vertG06", Wc3Int.class);
		public final static State<Wc3Int> COLOR_BLUE6 = new State<>("vertB06", Wc3Int.class);
		public final static State<Wc3Int> COLOR_RED7 = new State<>("vertR07", Wc3Int.class);
		public final static State<Wc3Int> COLOR_GREEN7 = new State<>("vertG07", Wc3Int.class);
		public final static State<Wc3Int> COLOR_BLUE7 = new State<>("vertB07", Wc3Int.class);
		public final static State<Wc3Int> COLOR_RED8 = new State<>("vertR08", Wc3Int.class);
		public final static State<Wc3Int> COLOR_GREEN8 = new State<>("vertG08", Wc3Int.class);
		public final static State<Wc3Int> COLOR_BLUE8 = new State<>("vertB08", Wc3Int.class);
		public final static State<Wc3Int> COLOR_RED9 = new State<>("vertR09", Wc3Int.class);
		public final static State<Wc3Int> COLOR_GREEN9 = new State<>("vertG09", Wc3Int.class);
		public final static State<Wc3Int> COLOR_BLUE9 = new State<>("vertB09", Wc3Int.class);
		public final static State<Wc3Int> COLOR_RED10 = new State<>("vertR10", Wc3Int.class);
		public final static State<Wc3Int> COLOR_GREEN10 = new State<>("vertG10", Wc3Int.class);
		public final static State<Wc3Int> COLOR_BLUE10 = new State<>("vertB10", Wc3Int.class);
		public final static State<Bool> IN_USER_LIST = new State<>("UserList", Bool.class);
		public final static State<Bool> IN_BETA = new State<>("InBeta", Bool.class);
		public final static State<Wc3Int> VERSION = new State<>("version", Wc3Int.class);
	}
	
	public static class Obj extends SLK.Obj<DoodId> {
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
			super(DoodId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(DoodId id) {
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
	
	//private Map<DoodId, Camera> _objs = new LinkedHashMap<>();
	
	@Nonnull
	@Override
	public Map<DoodId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(@Nonnull Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Nonnull
	@Override
	public Obj addObj(@Nonnull DoodId id) {
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
	
	public DoodSLK(SLK slk) {
		read(slk);
	}
	
	public DoodSLK() {
		super();
		
		addField(States.OBJ_ID);
		
		for (State state : States.values()) {
			addField(state);
		}
	}

	public DoodSLK(File file) throws IOException {
		this();
		
		read(file);
	}
	
	@Nonnull
	@Override
	public Obj createObj(@Nonnull ObjId id) {
		return new Obj(DoodId.valueOf(id));
	}

	@Override
	public void merge(@Nonnull DoodSLK other, boolean overwrite) {
		for (Map.Entry<DoodId, Obj> objEntry : other.getObjs().entrySet()) {
			DoodId objId = objEntry.getKey();
			Obj otherObj = objEntry.getValue();
			
			Obj obj = addObj(objId);
			
			obj.merge(otherObj);
		}
	}
}
