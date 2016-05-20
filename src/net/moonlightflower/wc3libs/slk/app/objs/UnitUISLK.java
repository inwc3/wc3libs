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
import net.moonlightflower.wc3libs.dataTypes.app.ArmorSound;
import net.moonlightflower.wc3libs.dataTypes.app.Bool;
import net.moonlightflower.wc3libs.dataTypes.app.Icon;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.Model;
import net.moonlightflower.wc3libs.dataTypes.app.Real;
import net.moonlightflower.wc3libs.dataTypes.app.ShadowImage;
import net.moonlightflower.wc3libs.dataTypes.app.SoundLabel;
import net.moonlightflower.wc3libs.dataTypes.app.TeamColor;
import net.moonlightflower.wc3libs.dataTypes.app.UberSplatId;
import net.moonlightflower.wc3libs.dataTypes.app.UnitClass;
import net.moonlightflower.wc3libs.dataTypes.app.UnitId;
import net.moonlightflower.wc3libs.dataTypes.app.VersionFlags;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.dataTypes.app.WeaponType;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.SLK;
import net.moonlightflower.wc3libs.slk.SLKState;

public class UnitUISLK extends ObjSLK<UnitUISLK, UnitId, UnitUISLK.Obj> {
	public final static File GAME_USE_PATH = new File("Units\\unitUI.slk");
	
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

		public final static State<UnitId> OBJ_ID = new State<>("unitUIID", UnitId.class);

		public final static State<Real> ART_ANIM_BLEND = new State<>("blend", Real.class);
		public final static State<Real> ART_ANIM_RUN = new State<>("run", Real.class);
		public final static State<Real> ART_ANIM_WALK = new State<>("walk", Real.class);
		public final static State<Real> ART_COLOR_BLUE = new State<>("blue", Real.class);
		public final static State<Real> ART_COLOR_GREEN = new State<>("green", Real.class);
		public final static State<Real> ART_COLOR_RED = new State<>("red", Real.class);
		public final static State<Int> ART_ELEV_PTS = new State<>("elevPts", Int.class);
		public final static State<Real> ART_ELEV_RAD = new State<>("elevRad", Real.class);
		public final static State<Real> ART_FOG_RAD = new State<>("fogRad", Real.class);
		public final static State<Bool> ART_HERO_HIDE_BAR = new State<>("hideHeroBar", Bool.class);
		public final static State<Bool> ART_HERO_HIDE_DEATH_MESSAGE = new State<>("hideHeroMinimap", Bool.class);
		public final static State<Bool> ART_HERO_HIDE_MINIMAP = new State<>("hideHeroDeathMsg", Bool.class);
		public final static State<Bool> ART_MINIMAP_HIDE = new State<>("hideOnMinimap", Bool.class);
		public final static State<Model> ART_MODEL = new State<>("file", Model.class);
		public final static State<Real> ART_MODEL_SCALE = new State<>("modelScale", Real.class);
		public final static State<VersionFlags> ART_MODEL_VERSION = new State<>("fileVerFlags", VersionFlags.class);
		public final static State<Icon> ART_NEUTRAL_STRUCTURE_MINIMAP_ICON = new State<>("nbmmIcon", Icon.class);
		public final static State<Real> ART_OCCLUSION_HEIGHT = new State<>("occH", Real.class);
		public final static State<Real> ART_PITCH_MAX = new State<>("maxPitch", Real.class);
		public final static State<Real> ART_ROLL_MAX = new State<>("maxRoll", Real.class);
		public final static State<Real> ART_SCALE = new State<>("scale", Real.class);
		public final static State<Bool> ART_SELECTION_SHOW_ON_WATER = new State<>("selCircOnWater", Bool.class);
		public final static State<Real> ART_SELECTION_SCALE = new State<>("scaleBull", Real.class);
		public final static State<Real> ART_SELECTION_Z = new State<>("selZ", Real.class);
		public final static State<ShadowImage> ART_SHADOW_STRUCTURE = new State<>("buildingShadow", ShadowImage.class);
		public final static State<ShadowImage> ART_SHADOW_UNIT = new State<>("unitShadow", ShadowImage.class);
		public final static State<Real> ART_SHADOW_HEIGHT = new State<>("shadowH", Real.class);
		public final static State<Bool> ART_SHADOW_ON_WATER = new State<>("shadowOnWater", Bool.class);
		public final static State<Real> ART_SHADOW_WIDTH = new State<>("shadowW", Real.class);
		public final static State<Real> ART_SHADOW_X = new State<>("shadowX", Real.class);
		public final static State<Real> ART_SHADOW_Y = new State<>("shadowY", Real.class);
		public final static State<TeamColor> ART_TEAM_COLOR = new State<>("teamColor", TeamColor.class);
		public final static State<Bool> ART_TEAM_COLOR_USE = new State<>("customTeamColor", Bool.class);
		public final static State<UberSplatId> ART_STRUCTURE_UBERSPLAT = new State<>("uberSplat", UberSplatId.class);
		
		public final static State<ArmorSound> COMBAT_ARMOR_SOUND = new State<>("armor", ArmorSound.class);
		
		public final static State<WeaponType> COMBAT_ATTACK1_WEAPON_SOUND = new State<>("weap1", WeaponType.class);
		public final static State<WeaponType> COMBAT_ATTACK2_WEAPON_SOUND = new State<>("weap2", WeaponType.class);
		
		public final static State<UnitClass> DATA_CLASS = new State<>("unitClass", UnitClass.class);
		public final static State<Bool> DATA_DROPS_ITEMS_ON_DEATH = new State<>("dropItems", Bool.class);
		
		public final static State<Bool> EDITOR_IN_BETA = new State<>("InBeta", Bool.class);
		public final static State<Bool> EDITOR_IN_EDITOR = new State<>("inEditor", Bool.class);
		public final static State<Bool> EDITOR_IN_EDITOR_HIDDEN = new State<>("hiddenInEditor", Bool.class);
		public final static State<Bool> EDITOR_IS_CAMPAIGN = new State<>("campaign", Bool.class);
		public final static State<Bool> EDITOR_IS_NEUTRAL_HOSTILE = new State<>("hostilePal", Bool.class);
		public final static State<Bool> EDITOR_IS_SPECIAL = new State<>("special", Bool.class);
		public final static State<Wc3String> EDITOR_SORT = new State<>("sortUI", Wc3String.class);
		public final static State<Bool> EDITOR_TILESET_SPECIFIC = new State<>("tilesetSpecific", Bool.class);
		public final static State<Bool> EDITOR_USE_CLICK_HELPER = new State<>("useClickHelper", Bool.class);
		
		public final static State<SoundLabel> SOUND_LABEL = new State<>("unitSound", SoundLabel.class);
		
		public final static State<Wc3String> TEXT_NAME = new State<>("name", Wc3String.class);
	}
	
	public static class Obj extends SLK.Obj<UnitId> {
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
			this(UnitId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(UnitId id) {
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
	
	private Map<UnitId, Obj> _objs = new HashMap<>();
	
	@Override
	public Map<UnitId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Override
	public Obj addObj(UnitId id) {
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
	
	public UnitUISLK() {
		super();
		
		addField(States.OBJ_ID);
		
		for (States.State state : States.values()) {
			addField(state);
		}
	}

	@Override
	public Obj createObj(ObjId id) {
		return new Obj(UnitId.valueOf(id));
	}

	@Override
	public void merge(UnitUISLK other, boolean overwrite) {
		// TODO Auto-generated method stub
		
	}
}
