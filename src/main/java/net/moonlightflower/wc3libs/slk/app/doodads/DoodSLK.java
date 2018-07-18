package net.moonlightflower.wc3libs.slk.app.doodads;

import net.moonlightflower.wc3libs.dataTypes.DataList;
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

public class DoodSLK extends ObjSLK<DoodSLK, DoodId, DoodSLK.Obj> {
	public final static File GAME_PATH = new File("Doodads\\Doodads.slk");

	public static class State<T extends DataType> extends ObjSLK.State<T> {
		public final static State<DoodId> OBJ_ID = new State<>("doodID", DoodId.class);

		public final static State<War3Char> CATEGORY = new State<>("category", War3Char.class);
		public final static State<DataList<Tileset>> TILESETS = new State<>("tilesets", new DataTypeInfo(DataList.class, Tileset.class));
		public final static State<War3Bool> TILESET_SPECIFIC = new State<>("tilesetSpecific", War3Bool.class);
		public final static State<Model> MODEL = new State<>("file", Model.class);
		public final static State<War3String> COMMENT = new State<>("comment", War3String.class);
		public final static State<War3String> NAME = new State<>("Name", War3String.class);
		public final static State<DoodadClass> CLASS = new State<>("doodClass", DoodadClass.class);
		public final static State<SoundLabel> SOUND_LOOP = new State<>("soundLoop", SoundLabel.class);
		public final static State<War3Real> SELECTION_SIZE = new State<>("selSize", War3Real.class);//unreal
		public final static State<War3Real> SCALE_DEFAULT = new State<>("defScale", War3Real.class);//unreal
		public final static State<War3Real> SCALE_MIN = new State<>("minScale", War3Real.class);//unreal
		public final static State<War3Real> SCALE_MAX = new State<>("maxScale", War3Real.class);//unreal
		public final static State<War3Bool> CAN_PLACE_RAND_SCALE = new State<>("canPlaceRandScale", War3Bool.class);
		public final static State<War3Bool> USE_CLICK_HELPER = new State<>("useClickHelper", War3Bool.class);
		public final static State<War3Bool> IGNORE_MODEL_CLICK = new State<>("ignoreModelClick", War3Bool.class);
		public final static State<War3Real> PITCH_MAX = new State<>("maxPitch", War3Real.class);//unreal
		public final static State<War3Real> ROLL_MAX = new State<>("maxRoll", War3Real.class);//unreal
		public final static State<War3Real> VISIBLE_RADIUS = new State<>("visRadius", War3Real.class);//unreal
		public final static State<War3Bool> WALKABLE = new State<>("walkable", War3Bool.class);
		public final static State<War3Int> NUM_VAR = new State<>("numVar", War3Int.class);
		public final static State<War3Bool> ON_CLIFFS = new State<>("onCliffs", War3Bool.class);
		public final static State<War3Bool> ON_WATER = new State<>("onWater", War3Bool.class);
		public final static State<War3Bool> FLOATS = new State<>("floats", War3Bool.class);
		public final static State<War3Bool> CASTS_SHADOW = new State<>("shadow", War3Bool.class);
		public final static State<War3Bool> SHOW_IN_FOG = new State<>("showInFog", War3Bool.class);
		public final static State<War3Bool> ANIM_IN_FOG = new State<>("animInFog", War3Bool.class);
		public final static State<War3Real> FIXED_ROT = new State<>("fixedRot", War3Real.class);//unreal
		public final static State<PathingTex> PATH_TEX = new State<>("pathTex", PathingTex.class);
		public final static State<War3Bool> SHOW_IN_MINIMAP = new State<>("showInMM", War3Bool.class);
		public final static State<War3Bool> USE_MINIMAP_COLOR = new State<>("useMMColor", War3Bool.class);
		public final static State<War3Int> MINIMAP_RED = new State<>("MMRed", War3Int.class);
		public final static State<War3Int> MINIMAP_GREEN = new State<>("MMGreen", War3Int.class);
		public final static State<War3Int> MINIMAP_BLUE = new State<>("MMBlue", War3Int.class);
		public final static State<War3Int> COLOR_RED1 = new State<>("vertR01", War3Int.class);
		public final static State<War3Int> COLOR_GREEN1 = new State<>("vertG01", War3Int.class);
		public final static State<War3Int> COLOR_BLUE1 = new State<>("vertB01", War3Int.class);
		public final static State<War3Int> COLOR_RED2 = new State<>("vertR02", War3Int.class);
		public final static State<War3Int> COLOR_GREEN2 = new State<>("vertG02", War3Int.class);
		public final static State<War3Int> COLOR_BLUE2 = new State<>("vertB02", War3Int.class);
		public final static State<War3Int> COLOR_RED3 = new State<>("vertR03", War3Int.class);
		public final static State<War3Int> COLOR_GREEN3 = new State<>("vertG03", War3Int.class);
		public final static State<War3Int> COLOR_BLUE3 = new State<>("vertB03", War3Int.class);
		public final static State<War3Int> COLOR_RED4 = new State<>("vertR04", War3Int.class);
		public final static State<War3Int> COLOR_GREEN4 = new State<>("vertG04", War3Int.class);
		public final static State<War3Int> COLOR_BLUE4 = new State<>("vertB04", War3Int.class);
		public final static State<War3Int> COLOR_RED5 = new State<>("vertR05", War3Int.class);
		public final static State<War3Int> COLOR_GREEN5 = new State<>("vertG05", War3Int.class);
		public final static State<War3Int> COLOR_BLUE5 = new State<>("vertB05", War3Int.class);
		public final static State<War3Int> COLOR_RED6 = new State<>("vertR06", War3Int.class);
		public final static State<War3Int> COLOR_GREEN6 = new State<>("vertG06", War3Int.class);
		public final static State<War3Int> COLOR_BLUE6 = new State<>("vertB06", War3Int.class);
		public final static State<War3Int> COLOR_RED7 = new State<>("vertR07", War3Int.class);
		public final static State<War3Int> COLOR_GREEN7 = new State<>("vertG07", War3Int.class);
		public final static State<War3Int> COLOR_BLUE7 = new State<>("vertB07", War3Int.class);
		public final static State<War3Int> COLOR_RED8 = new State<>("vertR08", War3Int.class);
		public final static State<War3Int> COLOR_GREEN8 = new State<>("vertG08", War3Int.class);
		public final static State<War3Int> COLOR_BLUE8 = new State<>("vertB08", War3Int.class);
		public final static State<War3Int> COLOR_RED9 = new State<>("vertR09", War3Int.class);
		public final static State<War3Int> COLOR_GREEN9 = new State<>("vertG09", War3Int.class);
		public final static State<War3Int> COLOR_BLUE9 = new State<>("vertB09", War3Int.class);
		public final static State<War3Int> COLOR_RED10 = new State<>("vertR10", War3Int.class);
		public final static State<War3Int> COLOR_GREEN10 = new State<>("vertG10", War3Int.class);
		public final static State<War3Int> COLOR_BLUE10 = new State<>("vertB10", War3Int.class);
		public final static State<War3Bool> IN_USER_LIST = new State<>("UserList", War3Bool.class);
		public final static State<War3Bool> IN_BETA = new State<>("InBeta", War3Bool.class);
		public final static State<War3Int> VERSION = new State<>("version", War3Int.class);

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
	
	public static class Obj extends SLK.Obj<DoodId> {
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

		public <T extends DataType> T get(State<T> state) throws DataTypeInfo.CastException {
			if (!super.contains(state.getFieldId())) return state.getDefVal();

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

			for (State<?> state : State.values(State.class)) {
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
		
		addField(State.OBJ_ID);
		
		for (State<?> state : State.values(State.class)) {
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
