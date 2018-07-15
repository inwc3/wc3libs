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

public class UnitUISLK extends ObjSLK<UnitUISLK, UnitId, UnitUISLK.Obj> {
	public final static File GAME_PATH = new File("Units\\unitUI.slk");

	public static class State<T extends DataType> extends ObjSLK.State<T> {
		public final static State<UnitId> OBJ_ID = new State<>("unitUIID", UnitId.class);
		public final static State<War3String> EDITOR_SORT = new State<>("sortUI", War3String.class);
		public final static State<Model> ART_MODEL = new State<>("file", Model.class);
		public final static State<VersionFlags> ART_MODEL_VERSION = new State<>("fileVerFlags", VersionFlags.class);
		public final static State<SoundLabel> SOUND_LABEL = new State<>("unitSound", SoundLabel.class);
		public final static State<War3Bool> EDITOR_TILESET_SPECIFIC = new State<>("tilesetSpecific", War3Bool.class);
		public final static State<War3String> TEXT_NAME = new State<>("name", War3String.class);
		public final static State<UnitClass> DATA_CLASS = new State<>("unitClass", UnitClass.class);
		public final static State<War3Bool> EDITOR_IS_SPECIAL = new State<>("special", War3Bool.class);
		public final static State<War3Bool> EDITOR_IS_CAMPAIGN = new State<>("campaign", War3Bool.class);
		public final static State<War3Bool> EDITOR_IN_EDITOR = new State<>("inEditor", War3Bool.class);
		public final static State<War3Bool> EDITOR_IN_EDITOR_HIDDEN = new State<>("hiddenInEditor", War3Bool.class);
		public final static State<War3Bool> EDITOR_IS_NEUTRAL_HOSTILE = new State<>("hostilePal", War3Bool.class);
		public final static State<War3Bool> DATA_DROPS_ITEMS_ON_DEATH = new State<>("dropItems", War3Bool.class);
		public final static State<Icon> ART_NEUTRAL_STRUCTURE_MINIMAP_ICON = new State<>("nbmmIcon", Icon.class);
		public final static State<War3Bool> EDITOR_USE_CLICK_HELPER = new State<>("useClickHelper", War3Bool.class);
		public final static State<War3Bool> ART_HERO_HIDE_BAR = new State<>("hideHeroBar", War3Bool.class);
		public final static State<War3Bool> ART_HERO_HIDE_DEATH_MESSAGE = new State<>("hideHeroMinimap", War3Bool.class);
		public final static State<War3Bool> ART_HERO_HIDE_MINIMAP = new State<>("hideHeroDeathMsg", War3Bool.class);
		public final static State<War3Bool> ART_MINIMAP_HIDE = new State<>("hideOnMinimap", War3Bool.class);
		public final static State<War3Real> ART_ANIM_BLEND = new State<>("blend", War3Real.class);
		public final static State<War3Real> ART_SCALE = new State<>("scale", War3Real.class);
		public final static State<War3Real> ART_SELECTION_SCALE = new State<>("scaleBull", War3Real.class);
		public final static State<War3Real> ART_PITCH_MAX = new State<>("maxPitch", War3Real.class);
		public final static State<War3Real> ART_ROLL_MAX = new State<>("maxRoll", War3Real.class);
		public final static State<War3Int> ART_ELEV_PTS = new State<>("elevPts", War3Int.class);
		public final static State<War3Real> ART_ELEV_RAD = new State<>("elevRad", War3Real.class);
		public final static State<War3Real> ART_FOG_RAD = new State<>("fogRad", War3Real.class);
		public final static State<War3Real> ART_ANIM_WALK = new State<>("walk", War3Real.class);
		public final static State<War3Real> ART_ANIM_RUN = new State<>("run", War3Real.class);
		public final static State<War3Real> ART_SELECTION_Z = new State<>("selZ", War3Real.class);
		public final static State<WeaponType> COMBAT_ATTACK1_WEAPON_SOUND = new State<>("weap1", WeaponType.class);
		public final static State<WeaponType> COMBAT_ATTACK2_WEAPON_SOUND = new State<>("weap2", WeaponType.class);
		public final static State<TeamColor> ART_TEAM_COLOR = new State<>("teamColor", TeamColor.class);
		public final static State<War3Bool> ART_TEAM_COLOR_USE = new State<>("customTeamColor", War3Bool.class);
		public final static State<ArmorSound> COMBAT_ARMOR_SOUND = new State<>("armor", ArmorSound.class);
		public final static State<War3Real> ART_MODEL_SCALE = new State<>("modelScale", War3Real.class);
		public final static State<War3Real> ART_COLOR_RED = new State<>("red", War3Real.class);
		public final static State<War3Real> ART_COLOR_GREEN = new State<>("green", War3Real.class);
		public final static State<War3Real> ART_COLOR_BLUE = new State<>("blue", War3Real.class);
		public final static State<UberSplatId> ART_STRUCTURE_UBERSPLAT = new State<>("uberSplat", UberSplatId.class);
		public final static State<ShadowImage> ART_SHADOW_UNIT = new State<>("unitShadow", ShadowImage.class);
		public final static State<ShadowImage> ART_SHADOW_STRUCTURE = new State<>("buildingShadow", ShadowImage.class);
		public final static State<War3Real> ART_SHADOW_WIDTH = new State<>("shadowW", War3Real.class);
		public final static State<War3Real> ART_SHADOW_HEIGHT = new State<>("shadowH", War3Real.class);
		public final static State<War3Real> ART_SHADOW_X = new State<>("shadowX", War3Real.class);
		public final static State<War3Real> ART_SHADOW_Y = new State<>("shadowY", War3Real.class);
		public final static State<War3Bool> ART_SHADOW_ON_WATER = new State<>("shadowOnWater", War3Bool.class);
		public final static State<War3Bool> ART_SELECTION_SHOW_ON_WATER = new State<>("selCircOnWater", War3Bool.class);
		public final static State<War3Real> ART_OCCLUSION_HEIGHT = new State<>("occH", War3Real.class);
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
			super(UnitId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(UnitId id) {
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
	
	//private Map<UnitId, Camera> _objs = new LinkedHashMap<>();
	
	@Nonnull
	@Override
	public Map<UnitId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(@Nonnull Obj val) {
		_objs.put(val.getId(), val);
		
		val.set(UnitUISLK.State.TEXT_NAME, War3String.valueOf("custom_" + val.getId()));
	}
	
	@Nonnull
	@Override
	public Obj addObj(@Nonnull UnitId id) {
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
	
	public UnitUISLK(SLK slk) {
		this();
		
		read(slk);
	}
	
	public UnitUISLK() {
		super();
		
		addField(State.OBJ_ID);
		
		for (State state : State.values(State.class)) {
			addField(state);
		}
	}

	public UnitUISLK(File file) throws IOException {
		this();
		
		read(file);
	}
	
	@Nonnull
	@Override
	public Obj createObj(@Nonnull ObjId id) {
		return new Obj(UnitId.valueOf(id));
	}

	@Override
	public void merge(@Nonnull UnitUISLK other, boolean overwrite) {
		for (Map.Entry<UnitId, Obj> objEntry : other.getObjs().entrySet()) {
			UnitId objId = objEntry.getKey();
			Obj otherObj = objEntry.getValue();
			
			Obj obj = addObj(objId);
			
			obj.merge(otherObj);
		}
	}
}
