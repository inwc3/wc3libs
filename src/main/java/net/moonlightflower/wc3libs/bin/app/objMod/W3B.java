package net.moonlightflower.wc3libs.bin.app.objMod;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import net.moonlightflower.wc3libs.slk.app.objs.AbilSLK;
import net.moonlightflower.wc3libs.slk.app.objs.DestructableSLK;
import net.moonlightflower.wc3libs.bin.MetaState;
import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.Bool;
import net.moonlightflower.wc3libs.dataTypes.app.CombatTarget;
import net.moonlightflower.wc3libs.dataTypes.app.DefType;
import net.moonlightflower.wc3libs.dataTypes.app.DestructableClass;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.Model;
import net.moonlightflower.wc3libs.dataTypes.app.PathingTex;
import net.moonlightflower.wc3libs.dataTypes.app.Real;
import net.moonlightflower.wc3libs.dataTypes.app.ShadowTex;
import net.moonlightflower.wc3libs.dataTypes.app.SoundLabel;
import net.moonlightflower.wc3libs.dataTypes.app.Tex;
import net.moonlightflower.wc3libs.dataTypes.app.Tileset;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.port.LadikMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;

/**
 * destructable modifications file for wrapping war3map.w3b
 */
public class W3B extends ObjMod {
	public final static File GAME_PATH = new File("war3map.w3b");
	public final static File CAMPAIGN_PATH = new File("war3campaign.w3b");
	
	public static class States {
		public static class State<T extends DataType> extends MetaState<T> {
			private static List<State> _values = new ArrayList<>();
			
			public static List<State> values() {
				return _values;
			}
			
			public State(String idString, DataTypeInfo typeInfo, T defVal) {
				super(idString, typeInfo, defVal);
				
				_values.add(this);
			}
			
			public State(String idString, Class<T> type, T defVal) {
				this(idString, new DataTypeInfo(type), defVal);
			}
			
			public State(String idString, DataTypeInfo typeInfo) {
				this(idString, typeInfo, null);
			}
			
			public State(String idString, Class<T> type) {
				this(idString, new DataTypeInfo(type), null);
			}
		}
		
		public static List<State> values() {
			return State.values();
		}
		
		public final static State<Int> ART_COLOR_RED = new State<>("bvcr", Int.class);
		public final static State<Int> ART_COLOR_GREEN = new State<>("bvcg", Int.class);
		public final static State<Int> ART_COLOR_BLUE = new State<>("bvcb", Int.class);
		public final static State<Real> ART_ELEV_RAD = new State<>("brad", Real.class);
		public final static State<Bool> ART_FAT_LINE_OF_SIGHT = new State<>("bflo", Bool.class);
		public final static State<Real> ART_FIXED_ROTATION = new State<>("bfxr", Real.class);
		public final static State<Real> ART_FLY_HEIGHT = new State<>("bflh", Real.class);
		public final static State<Real> ART_FOG_RAD = new State<>("bfra", Real.class);
		public final static State<Bool> ART_LIGHTWEIGHT = new State<>("blit", Bool.class);
		public final static State<Int> ART_MINIMAP_COLOR_BLUE = new State<>("bmmb", Int.class);
		public final static State<Int> ART_MINIMAP_COLOR_GREEN = new State<>("bmmg", Int.class);
		public final static State<Int> ART_MINIMAP_COLOR_RED = new State<>("bmmr", Int.class);
		public final static State<Bool> ART_MINIMAP_USE_COLOR = new State<>("bumm", Bool.class);
		public final static State<Model> ART_MODEL = new State<>("bfil", Model.class);
		public final static State<Model> ART_MODEL_PORTRAIT = new State<>("bgpm", Model.class);
		public final static State<Real> ART_OCCLUSION_HEIGHT = new State<>("boch", Real.class);
		public final static State<Real> ART_PITCH_MAX = new State<>("bmap", Real.class);
		public final static State<Real> ART_ROLL_MAX = new State<>("bmar", Real.class);
		public final static State<Bool> ART_SELECTABLE = new State<>("bgse", Bool.class);
		public final static State<Real> ART_SELECTION_SCALE_EDITOR = new State<>("bsel", Real.class);
		public final static State<Real> ART_SELECTION_SCALE_GAME = new State<>("bgsc", Real.class);
		public final static State<ShadowTex> ART_SHADOW_TEX = new State<>("bshd", ShadowTex.class);
		public final static State<Bool> ART_SHOW_IN_FOG = new State<>("bfvi", Bool.class);
		public final static State<Bool> ART_SHOW_ON_MINIMAP = new State<>("bsmm", Bool.class);
		public final static State<Tex> ART_TEX = new State<>("btxf", Tex.class);
		public final static State<Int> ART_TEX_ID = new State<>("btxi", Int.class);
		public final static State<Int> ART_VARIATIONS = new State<>("bvar", Int.class);
		
		public final static State<DefType> COMBAT_ARMOR_TYPE = new State<>("barm", DefType.class);
		public final static State<DataList<CombatTarget>> COMBAT_TARGETS = new State<>("btar", new DataTypeInfo(DataList.class, CombatTarget.class));
		
		public final static State<Int> DATA_BUILD_TIME = new State<>("bbut", Int.class);
		public final static State<Int> DATA_COST_GOLD_REPAIR = new State<>("breg", Int.class);
		public final static State<Int> DATA_COST_LUMBER_REPAIR = new State<>("brel", Int.class);
		public final static State<Real> DATA_LIFE = new State<>("bhps", Real.class);
		public final static State<Int> DATA_REPAIR_TIME = new State<>("bret", Int.class);
		
		public final static State<Bool> EDITOR_CAN_PLACE_DEAD = new State<>("bcpd", Bool.class);
		public final static State<Bool> EDITOR_CAN_PLACE_RANDOM_SCALE = new State<>("bcpr", Bool.class);
		public final static State<DestructableClass> EDITOR_CLASS = new State<>("bcat", DestructableClass.class);
		public final static State<Bool> EDITOR_IN_USER_LIST = new State<>("busr", Bool.class);
		public final static State<Bool> EDITOR_ON_CLIFFS = new State<>("bonc", Bool.class);
		public final static State<Bool> EDITOR_ON_WATER = new State<>("bonw", Bool.class);
		public final static State<Real> EDITOR_SCALE_MIN = new State<>("bmis", Real.class);
		public final static State<Real> EDITOR_SCALE_MAX = new State<>("bmas", Real.class);
		public final static State<Bool> EDITOR_TILESET_SPECIFIC = new State<>("btsp", Bool.class);
		public final static State<DataList<Tileset>> EDITOR_TILESETS = new State<>("btil", new DataTypeInfo(DataList.class, Tileset.class));
		public final static State<Bool> EDITOR_USE_CLICK_HELPER = new State<>("buch", Bool.class);

		public final static State<Int> PATH_CLIFF_LEVEL = new State<>("bclh", Int.class);
		public final static State<PathingTex> PATH_TEX = new State<>("bptx", PathingTex.class);
		public final static State<PathingTex> PATH_TEX_DEAD = new State<>("bptd", PathingTex.class);
		public final static State<Bool> PATH_WALKABLE = new State<>("bwal", Bool.class);
		
		public final static State<SoundLabel> SOUND_DEATH = new State<>("bdsn", SoundLabel.class);

		public final static State<Wc3String> TEXT_EDITOR_SUFFIX = new State<>("bsuf", Wc3String.class);
		public final static State<Wc3String> TEXT_NAME = new State<>("bnam", Wc3String.class);
	}
	
	public static class Obj extends ObjMod.Obj {		
		public Obj(ObjId id, ObjId baseId) {
			super(id, baseId);
		}
	}
	
	@Override
	public Collection<File> getSLKs() {
		return Arrays.asList(DestructableSLK.GAME_USE_PATH);
	}
	
	@Override
	public Collection<File> getNecessarySLKs() {
		return Arrays.asList(DestructableSLK.GAME_USE_PATH);
	}
	
	public W3B(InputStream inStream) throws IOException {
		super(inStream, false);
	}
	
	public W3B(File file) throws Exception {
		super(file, false);
	}
	
	public W3B() {
		super();
	}

	public static W3B ofMapFile(File mapFile) throws Exception {
		if (!mapFile.exists()) throw new IOException(String.format("file %s does not exist", mapFile));
		
		MpqPort.Out port = new LadikMpqPort.Out();
		
		port.add(GAME_PATH);
		
		MpqPort.Out.Result portResult = port.commit(mapFile);

		if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract w3b file");

		InputStream inStream = portResult.getInputStream(GAME_PATH);
		
		return new W3B(inStream);
	}
}
