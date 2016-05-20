package net.moonlightflower.wc3libs.bin.app.objMod;

import java.util.ArrayList;
import java.util.List;

import net.moonlightflower.wc3libs.bin.MetaState;
import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.dataTypes.app.Bool;
import net.moonlightflower.wc3libs.dataTypes.app.DefType;
import net.moonlightflower.wc3libs.dataTypes.app.DestructableClass;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.Model;
import net.moonlightflower.wc3libs.dataTypes.app.PathingTex;
import net.moonlightflower.wc3libs.dataTypes.app.Real;
import net.moonlightflower.wc3libs.dataTypes.app.ShadowTex;
import net.moonlightflower.wc3libs.dataTypes.app.SoundLabel;
import net.moonlightflower.wc3libs.dataTypes.app.TargetList;
import net.moonlightflower.wc3libs.dataTypes.app.Tex;
import net.moonlightflower.wc3libs.dataTypes.app.TilesetList;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.SLKState;

/**
 * destructable modifications file for wrapping war3map.w3b
 */
public class W3B extends ObjMod {
	public final static String GAME_PATH = "war3map.w3b";
	
	public static class States {
		public static class State<T> extends MetaState<T> {
			private static List<State> _values = new ArrayList<>();
			
			public static List<State> values() {
				return _values;
			}
			
			public State(String idString) {
				this(idString, null);
			}

			public State(String idString, T defVal) {
				super(idString, defVal);
				
				_values.add(this);
			}
		}
		
		public static List<State> values() {
			return State.values();
		}
		
		public final static State<Int> ART_COLOR_RED = new State<>("bvcr");
		public final static State<Int> ART_COLOR_GREEN = new State<>("bvcg");
		public final static State<Int> ART_COLOR_BLUE = new State<>("bvcb");
		public final static State<Real> ART_ELEV_RAD = new State<>("brad");
		public final static State<Bool> ART_FAT_LINE_OF_SIGHT = new State<>("bflo");
		public final static State<Real> ART_FIXED_ROTATION = new State<>("bfxr");
		public final static State<Real> ART_FLY_HEIGHT = new State<>("bflh");
		public final static State<Real> ART_FOG_RAD = new State<>("bfra");
		public final static State<Bool> ART_LIGHTWEIGHT = new State<>("blit");
		public final static State<Int> ART_MINIMAP_COLOR_BLUE = new State<>("bmmb");
		public final static State<Int> ART_MINIMAP_COLOR_GREEN = new State<>("bmmg");
		public final static State<Int> ART_MINIMAP_COLOR_RED = new State<>("bmmr");
		public final static State<Bool> ART_MINIMAP_USE_COLOR = new State<>("bumm");
		public final static State<Model> ART_MODEL = new State<>("bfil");
		public final static State<Model> ART_MODEL_PORTRAIT = new State<>("bgpm");
		public final static State<Real> ART_OCCLUSION_HEIGHT = new State<>("boch");
		public final static State<Real> ART_PITCH_MAX = new State<>("bmap");
		public final static State<Real> ART_ROLL_MAX = new State<>("bmar");
		public final static State<Bool> ART_SELECTABLE = new State<>("bgse");
		public final static State<Real> ART_SELECTION_SCALE_EDITOR = new State<>("bsel");
		public final static State<Real> ART_SELECTION_SCALE_GAME = new State<>("bgsc");
		public final static State<ShadowTex> ART_SHADOW_TEX = new State<>("bshd");
		public final static State<Bool> ART_SHOW_IN_FOG = new State<>("bfvi");
		public final static State<Bool> ART_SHOW_ON_MINIMAP = new State<>("bsmm");
		public final static State<Tex> ART_TEX = new State<>("btxf");
		public final static State<Int> ART_TEX_ID = new State<>("btxi");
		public final static State<Int> ART_VARIATIONS = new State<>("bvar");
		
		public final static State<DefType> COMBAT_ARMOR_TYPE = new State<>("barm");
		public final static State<TargetList> COMBAT_TARGETS = new State<>("btar");
		
		public final static State<Int> DATA_BUILD_TIME = new State<>("bbut");
		public final static State<Int> DATA_COST_GOLD_REPAIR = new State<>("breg");
		public final static State<Int> DATA_COST_LUMBER_REPAIR = new State<>("brel");
		public final static State<Real> DATA_LIFE = new State<>("bhps");
		public final static State<Int> DATA_REPAIR_TIME = new State<>("bret");
		
		public final static State<Bool> EDITOR_CAN_PLACE_DEAD = new State<>("bcpd");
		public final static State<Bool> EDITOR_CAN_PLACE_RANDOM_SCALE = new State<>("bcpr");
		public final static State<DestructableClass> EDITOR_CLASS = new State<>("bcat");
		public final static State<Bool> EDITOR_IN_USER_LIST = new State<>("busr");
		public final static State<Bool> EDITOR_ON_CLIFFS = new State<>("bonc");
		public final static State<Bool> EDITOR_ON_WATER = new State<>("bonw");
		public final static State<Real> EDITOR_SCALE_MIN = new State<>("bmis");
		public final static State<Real> EDITOR_SCALE_MAX = new State<>("bmas");
		public final static State<Bool> EDITOR_TILESET_SPECIFIC = new State<>("btsp");
		public final static State<TilesetList> EDITOR_TILESETS = new State<>("btil");
		public final static State<Bool> EDITOR_USE_CLICK_HELPER = new State<>("buch");

		public final static State<Int> PATH_CLIFF_LEVEL = new State<>("bclh");
		public final static State<PathingTex> PATH_TEX = new State<>("bptx");
		public final static State<PathingTex> PATH_TEX_DEAD = new State<>("bptd");
		public final static State<Bool> PATH_WALKABLE = new State<>("bwal");
		
		public final static State<SoundLabel> SOUND_DEATH = new State<>("bdsn");

		public final static State<String> TEXT_EDITOR_SUFFIX = new State<>("bsuf");
		public final static State<String> TEXT_NAME = new State<>("bnam");
	}
	
	public static class Obj extends ObjMod.Obj {		
		public Obj(ObjId id, ObjId baseId) {
			super(id, baseId);
		}
	}
}
