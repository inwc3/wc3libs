package net.moonlightflower.wc3libs.slk.app.objs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.Bool;
import net.moonlightflower.wc3libs.dataTypes.app.CombatTarget;
import net.moonlightflower.wc3libs.dataTypes.app.DefType;
import net.moonlightflower.wc3libs.dataTypes.app.DestructableId;
import net.moonlightflower.wc3libs.dataTypes.app.DoodadClass;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.ItemId;
import net.moonlightflower.wc3libs.dataTypes.app.Model;
import net.moonlightflower.wc3libs.dataTypes.app.PathingTex;
import net.moonlightflower.wc3libs.dataTypes.app.Real;
import net.moonlightflower.wc3libs.dataTypes.app.ShadowTex;
import net.moonlightflower.wc3libs.dataTypes.app.SoundLabel;
import net.moonlightflower.wc3libs.dataTypes.app.Tileset;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.SLK;
import net.moonlightflower.wc3libs.slk.SLKState;
import net.moonlightflower.wc3libs.slk.app.objs.ItemSLK.Obj;

public class DestructableSLK extends ObjSLK<DestructableSLK, DestructableId, DestructableSLK.Obj> {
	public final static File GAME_USE_PATH = new File("Units\\DestructableData.slk");
	
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
			public final static State<DataList<CombatTarget>> TARGS = new State<>("targType", new DataTypeInfo(DataList.class, CombatTarget.class));
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
			public final static State<DataList<Tileset>> TILESETS = new State<>("tilesets", new DataTypeInfo(DataList.class, Tileset.class));
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
	
	//private Map<DestructableId, Obj> _objs = new HashMap<>();
	
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
	
	public DestructableSLK(SLK slk) {
		read(slk);
	}
	
	public DestructableSLK() {
		super();
		
		addField(States.OBJ_ID);
		
		for (States.State state : States.values()) {
			addField(state);
		}
	}
	
	public DestructableSLK(File file) throws IOException {
		this();
		
		read(file);
	}
	
	@Override
	public Obj createObj(ObjId id) {
		return new Obj(DestructableId.valueOf(id));
	}

	@Override
	public void merge(DestructableSLK other, boolean overwrite) {
		for (Map.Entry<DestructableId, Obj> objEntry : other.getObjs().entrySet()) {
			DestructableId objId = objEntry.getKey();
			Obj otherObj = objEntry.getValue();
			
			Obj obj = addObj(objId);
			
			obj.merge(otherObj);
		}
	}
}
