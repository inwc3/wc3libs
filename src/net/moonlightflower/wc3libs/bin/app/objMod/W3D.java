package net.moonlightflower.wc3libs.bin.app.objMod;

import java.util.ArrayList;
import java.util.List;

import net.moonlightflower.wc3libs.bin.MetaState;
import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.dataTypes.app.Bool;
import net.moonlightflower.wc3libs.dataTypes.app.DoodadClass;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.Model;
import net.moonlightflower.wc3libs.dataTypes.app.PathingTex;
import net.moonlightflower.wc3libs.dataTypes.app.Real;
import net.moonlightflower.wc3libs.dataTypes.app.SoundLabel;
import net.moonlightflower.wc3libs.dataTypes.app.TilesetList;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.SLKState;

/**
 * doodad modifications file for wrapping war3map.w3d
 */
public class W3D extends ObjMod {
	public final static String GAME_PATH = "war3map.w3d";
	
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
		
		public final static State<Bool> ART_ANIM_IN_FOG = new State<>("danf");
		public final static State<Bool> ART_CASTS_SHADOW = new State<>("dshd");
		public final static State<Int> ART_COLOR_RED = new State<>("dvr1"); //mult
		public final static State<Int> ART_COLOR_GREEN = new State<>("dvg1"); //mult
		public final static State<Int> ART_COLOR_BLUE = new State<>("dvb1"); //mult
		public final static State<Real> ART_FIXED_ROTATION = new State<>("dfxr");
		public final static State<Bool> ART_FLOATS = new State<>("dflt");
		public final static State<Real> ART_PITCH_MAX = new State<>("dmap");
		public final static State<Real> ART_ROLL_MAX = new State<>("dmar");
		public final static State<Real> ART_SCALE_DEFAULT = new State<>("ddes");
		public final static State<Real> ART_SELECTION_SCALE = new State<>("dsel");
		public final static State<Bool> ART_SHOW_IN_FOG = new State<>("dshf");
		public final static State<Bool> ART_SHOW_ON_MINIMAP = new State<>("dsmm");
		public final static State<Int> ART_MINIMAP_COLOR_BLUE = new State<>("dmmb");
		public final static State<Int> ART_MINIMAP_COLOR_GREEN = new State<>("dmmg");
		public final static State<Int> ART_MINIMAP_COLOR_RED = new State<>("dmmr");
		public final static State<Bool> ART_MINIMAP_USE_COLOR = new State<>("dumc");
		public final static State<Model> ART_MODEL = new State<>("dfil");
		public final static State<Int> ART_VARIATIONS = new State<>("dvar");
		public final static State<Real> ART_VISIBILITY_RAD = new State<>("dvis");
		
		public final static State<Bool> EDITOR_CAN_PLACE_RANDOM_SCALE = new State<>("dcpr");
		public final static State<DoodadClass> EDITOR_CLASS = new State<>("dcat");
		public final static State<Bool> EDITOR_IGNORE_MODEL_CLICK = new State<>("dimc");
		public final static State<Bool> EDITOR_IN_USER_LIST = new State<>("dusr");
		public final static State<Bool> EDITOR_ON_CLIFFS = new State<>("donc");
		public final static State<Bool> EDITOR_ON_WATER = new State<>("donw");
		public final static State<Real> EDITOR_SCALE_MAX = new State<>("dmas");
		public final static State<Real> EDITOR_SCALE_MIN = new State<>("dmis");
		public final static State<Bool> EDITOR_TILESET_SPECIFIC = new State<>("dtsp");
		public final static State<TilesetList> EDITOR_TILESETS = new State<>("dtil");
		public final static State<Bool> EDITOR_USE_CLICK_HELPER = new State<>("duch");
		
		public final static State<PathingTex> PATH_TEX = new State<>("dptx");
		public final static State<Bool> PATH_WALKABLE = new State<>("dwlk");
		
		public final static State<SoundLabel> SOUND_LOOP = new State<>("dsnd");
		
		public final static State<String> TEXT_NAME = new State<>("dnam");
	}
	
	public static class Obj extends ObjMod.Obj {
		public Obj(ObjId id, ObjId baseId) {
			super(id, baseId);
		}
	}
}
