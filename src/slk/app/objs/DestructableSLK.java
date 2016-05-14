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
import dataTypes.app.Bool;
import dataTypes.app.CombatTarget;
import dataTypes.app.DefType;
import dataTypes.app.DestructableId;
import dataTypes.app.DoodadClass;
import dataTypes.app.Int;
import dataTypes.app.Model;
import dataTypes.app.PathingTex;
import dataTypes.app.Real;
import dataTypes.app.ShadowTex;
import dataTypes.app.SoundLabel;
import dataTypes.app.TilesetList;
import dataTypes.app.Wc3String;
import misc.ObjId;
import misc.StateInfo;
import slk.ObjSLK;
import slk.SLK;
import slk.SLKState;

public class DestructableSLK extends ObjSLK<DestructableSLK, DestructableId, DestructableSLK.Obj> {
	public final static File GAME_USE_PATH = new File("Units\\DestructableData.slk");
	
	public static class States {
		static public class State<T extends DataType> extends SLKState<T> {
			static List<State> _values = new ArrayList<>();
			
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

		public final static State<DestructableId> OBJ_ID = new State<>("DestructableID", DestructableId.class);

		public static class Art {
			public final static State<Int> COLOR_BLUE = new State<>("colorB", Int.class);
			public final static State<Int> COLOR_GREEN = new State<>("colorG", Int.class);
			public final static State<Int> COLOR_RED = new State<>("colorR", Int.class);
			public final static State<Int> COSTS_GOLD_REPAIR = new State<>("goldRep", Int.class);
			public final static State<Int> COSTS_LUMBER_REPAIR = new State<>("lumberRep", Int.class);
			public final static State<Real> ELEV_RAD = new State<>("radius", Real.class);
			public final static State<Bool> FAT_LINE_OF_SIGHT = new State<>("fatLOS", Bool.class);
			public final static State<Real> FIXED_ROTATION = new State<>("fixedRot", Real.class);
			public final static State<Real> FLY_HEIGHT = new State<>("flyH", Real.class);
			public final static State<Real> FOG_RAD = new State<>("fogRadius", Real.class);
			public final static State<Bool> FOG_VISIBILITY = new State<>("fogVis", Bool.class);
			public final static State<Bool> LIGHTWEIGHT = new State<>("lightweight", Bool.class);
			public final static State<Bool> MINIMAP_SHOW = new State<>("showInMM", Bool.class);
			public final static State<Bool> MINIMAP_USE_COLOR = new State<>("useMMColor", Bool.class);
			public final static State<Int> MINIMAP_COLOR_BLUE = new State<>("MMBlue", Int.class);
			public final static State<Int> MINIMAP_COLOR_GREEN = new State<>("MMGreen", Int.class);
			public final static State<Int> MINIMAP_COLOR_RED = new State<>("MMRed", Int.class);
			public final static State<Model> MODEL = new State<>("file", Model.class);
			public final static State<Model> MODEL_PORTRAIT = new State<>("portraitmodel", Model.class);
			public final static State<Real> OCCLUSION_HEIGHT = new State<>("occH", Real.class);
			public final static State<Real> PITCH_MAX = new State<>("maxPitch", Real.class);
			public final static State<Real> ROLL_MAX = new State<>("maxRoll", Real.class);
			public final static State<Real> SCALE_MAX = new State<>("maxScale", Real.class);
			public final static State<Real> SCALE_MIN = new State<>("minScale", Real.class);
			public final static State<Real> SELECTION_SCALE_GAME = new State<>("selcircsize", Real.class);
			public final static State<ShadowTex> SHADOW_TEX = new State<>("shadow", ShadowTex.class);
			public final static State<Int> TEX_ID = new State<>("texID", Int.class);
			public final static State<Wc3String> TEX_FILE = new State<>("texFile", Wc3String.class);
		}

		public static class Data {
			public final static State<DefType> ARMOR_TYPE = new State<>("armor", DefType.class);
			public final static State<Int> BUILD_TIME = new State<>("buildTime", Int.class);
			public final static State<Int> CLIFF_HEIGHT = new State<>("cliffHeight", Int.class);
			//public final static State<BuffCode> CODE = new State<BuffCode>("code", BuffCode.class);
			public final static State<Int> LIFE = new State<>("HP", Int.class);
			public final static State<Int> REPAIR_TIME = new State<>("repairTime", Int.class);
			public final static State<Bool> SELECTABLE = new State<>("selectable", Bool.class);
			public final static State<DataList<CombatTarget>> TARGS = new State<>("targType", StateInfo.listOf(CombatTarget.class));
			public final static State<Int> VARIATION_COUNT = new State<>("numVar", Int.class);
			public final static State<Bool> WALKABLE = new State<>("walkable", Bool.class);
		}
		
		public static class Editor {
			public final static State<Bool> CAN_PLACE_DEAD = new State<>("canPlaceDead", Bool.class);
			public final static State<Bool> CAN_PLACE_RANDOM_SCALE = new State<>("canPlaceRandScale", Bool.class);
			public final static State<Wc3String> CATEGORY = new State<>("category", Wc3String.class);
			public final static State<Wc3String> COMMENT = new State<>("comment", Wc3String.class);
			public final static State<DoodadClass> DOOD_CLASS = new State<>("doodClass", DoodadClass.class);
			public final static State<Wc3String> EDITOR_SUFFIX = new State<>("EditorSuffix", Wc3String.class);
			public final static State<Bool> IN_BETA = new State<>("InBeta", Bool.class);
			public final static State<Bool> IN_USER_LIST = new State<>("UserList", Bool.class);
			public final static State<Bool> ON_CLIFFS = new State<>("onCliffs", Bool.class);
			public final static State<Bool> ON_WATER = new State<>("onWater", Bool.class);
			public final static State<Real> SELECTION_SCALE_EDITOR = new State<>("selSize", Real.class);
			public final static State<Bool> TILESET_SPECIFIC = new State<>("tilesetSpecific", Bool.class);
			public final static State<TilesetList> TILESETS = new State<>("tilesets", TilesetList.class);
			public final static State<Bool> USE_CLICK_HELPER = new State<>("useClickHelper", Bool.class);
			public final static State<Int> VERSION = new State<>("version", Int.class);
		}
		
		public static class Path {
			public final static State<PathingTex> PATH_TEX = new State<>("pathTex", PathingTex.class);
			public final static State<PathingTex> PATH_TEX_DEAD = new State<>("pathTexDeath", PathingTex.class);
		}
		
		public static class Sound {
			public final static State<SoundLabel> SOUND_DEATH = new State<>("deathSnd", SoundLabel.class);
		}
		
		public static class Text {
			public final static State<Wc3String> NAME = new State<>("Name", Wc3String.class);
		}
	}
	
	public static class Obj extends SLK.Obj<DestructableId> {
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
			this(DestructableId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(DestructableId id) {
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
	
	private Map<DestructableId, Obj> _objs = new HashMap<>();
	
	@Override
	public Map<DestructableId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Override
	public Obj addObj(DestructableId id) {
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
	
	public DestructableSLK() {
		super();
		
		addField(States.OBJ_ID);
		
		for (States.State state : States.values()) {
			addField(state);
		}
	}

	@Override
	public Obj createObj(ObjId id) {
		return new Obj(DestructableId.valueOf(id));
	}

	@Override
	public void merge(DestructableSLK other, boolean overwrite) {
		// TODO Auto-generated method stub
		
	}
}
