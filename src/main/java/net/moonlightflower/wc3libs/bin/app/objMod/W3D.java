package net.moonlightflower.wc3libs.bin.app.objMod;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import net.moonlightflower.wc3libs.bin.MetaState;
import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.Bool;
import net.moonlightflower.wc3libs.dataTypes.app.DoodadClass;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.Model;
import net.moonlightflower.wc3libs.dataTypes.app.PathingTex;
import net.moonlightflower.wc3libs.dataTypes.app.Real;
import net.moonlightflower.wc3libs.dataTypes.app.SoundLabel;
import net.moonlightflower.wc3libs.dataTypes.app.Tileset;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.port.LadikMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.slk.SLKState;
import net.moonlightflower.wc3libs.slk.app.doodads.DoodSLK;
import net.moonlightflower.wc3libs.slk.app.objs.AbilSLK;

/**
 * doodad modifications file for wrapping war3map.w3d
 */
public class W3D extends ObjMod {
	public final static File GAME_PATH = new File("war3map.w3d");
	public final static File CAMPAIGN_PATH = new File("war3campaign.w3d");
	
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
		
		public final static State<Bool> ART_ANIM_IN_FOG = new State<>("danf", Bool.class);
		public final static State<Bool> ART_CASTS_SHADOW = new State<>("dshd", Bool.class);
		public final static State<DataList<Int>> ART_COLOR_RED = new State<>("dvr1", new DataTypeInfo(DataList.class, Int.class)); //mult
		public final static State<DataList<Int>> ART_COLOR_GREEN = new State<>("dvg1", new DataTypeInfo(DataList.class, Int.class)); //mult
		public final static State<DataList<Int>> ART_COLOR_BLUE = new State<>("dvb1", new DataTypeInfo(DataList.class, Int.class)); //mult
		public final static State<Real> ART_FIXED_ROTATION = new State<>("dfxr", Real.class);
		public final static State<Bool> ART_FLOATS = new State<>("dflt", Bool.class);
		public final static State<Real> ART_PITCH_MAX = new State<>("dmap", Real.class);
		public final static State<Real> ART_ROLL_MAX = new State<>("dmar", Real.class);
		public final static State<Real> ART_SCALE_DEFAULT = new State<>("ddes", Real.class);
		public final static State<Real> ART_SELECTION_SCALE = new State<>("dsel", Real.class);
		public final static State<Bool> ART_SHOW_IN_FOG = new State<>("dshf", Bool.class);
		public final static State<Bool> ART_SHOW_ON_MINIMAP = new State<>("dsmm", Bool.class);
		public final static State<Int> ART_MINIMAP_COLOR_BLUE = new State<>("dmmb", Int.class);
		public final static State<Int> ART_MINIMAP_COLOR_GREEN = new State<>("dmmg", Int.class);
		public final static State<Int> ART_MINIMAP_COLOR_RED = new State<>("dmmr", Int.class);
		public final static State<Bool> ART_MINIMAP_USE_COLOR = new State<>("dumc", Bool.class);
		public final static State<Model> ART_MODEL = new State<>("dfil", Model.class);
		public final static State<Int> ART_VARIATIONS = new State<>("dvar", Int.class);
		public final static State<Real> ART_VISIBILITY_RAD = new State<>("dvis", Real.class);
		
		public final static State<Bool> EDITOR_CAN_PLACE_RANDOM_SCALE = new State<>("dcpr", Bool.class);
		public final static State<DoodadClass> EDITOR_CLASS = new State<>("dcat", DoodadClass.class);
		public final static State<Bool> EDITOR_IGNORE_MODEL_CLICK = new State<>("dimc", Bool.class);
		public final static State<Bool> EDITOR_IN_USER_LIST = new State<>("dusr", Bool.class);
		public final static State<Bool> EDITOR_ON_CLIFFS = new State<>("donc", Bool.class);
		public final static State<Bool> EDITOR_ON_WATER = new State<>("donw", Bool.class);
		public final static State<Real> EDITOR_SCALE_MAX = new State<>("dmas", Real.class);
		public final static State<Real> EDITOR_SCALE_MIN = new State<>("dmis", Real.class);
		public final static State<Bool> EDITOR_TILESET_SPECIFIC = new State<>("dtsp", Bool.class);
		public final static State<DataList<Tileset>> EDITOR_TILESETS = new State<>("dtil", new DataTypeInfo(DataList.class, Tileset.class));
		public final static State<Bool> EDITOR_USE_CLICK_HELPER = new State<>("duch", Bool.class);
		
		public final static State<PathingTex> PATH_TEX = new State<>("dptx", PathingTex.class);
		public final static State<Bool> PATH_WALKABLE = new State<>("dwlk", Bool.class);
		
		public final static State<SoundLabel> SOUND_LOOP = new State<>("dsnd", SoundLabel.class);
		
		public final static State<Wc3String> TEXT_NAME = new State<>("dnam", Wc3String.class);
	}
	
	public static class Obj extends ObjMod.Obj {
		public Obj(ObjId id, ObjId baseId) {
			super(id, baseId);
		}
	}

	@Override
	public Collection<File> getSLKs() {
		return Arrays.asList(DoodSLK.GAME_USE_PATH);
	}
	
	@Override
	public Collection<File> getNecessarySLKs() {
		return Arrays.asList(DoodSLK.GAME_USE_PATH);
	}

	public W3D(InputStream inStream) throws IOException {
		super(inStream, true);
	}
	
	public W3D(File file) throws Exception {
		super(file, true);
	}
	
	public W3D() {
		super();
	}

	public static W3D ofMapFile(File mapFile) throws Exception {
		if (!mapFile.exists()) throw new IOException(String.format("file %s does not exist", mapFile));
		
		MpqPort.Out port = new LadikMpqPort.Out();
		
		port.add(GAME_PATH);
		
		MpqPort.Out.Result portResult = port.commit(mapFile);

		if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract w3d file");

		InputStream inStream = portResult.getInputStream(GAME_PATH);
		
		return new W3D(inStream);
	}
}
