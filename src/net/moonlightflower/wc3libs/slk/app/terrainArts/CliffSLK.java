package net.moonlightflower.wc3libs.slk.app.terrainArts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.Bool;
import net.moonlightflower.wc3libs.dataTypes.app.CliffClass;
import net.moonlightflower.wc3libs.dataTypes.app.CliffId;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.TileId;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.RawSLK;
import net.moonlightflower.wc3libs.slk.SLK;
import net.moonlightflower.wc3libs.slk.SLKState;

public class CliffSLK extends ObjSLK<CliffSLK, CliffId, CliffSLK.Obj> {
	public final static File GAME_USE_PATH = new File("TerrainArt\\CliffTypes.slk");
	
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

		public final static State<CliffId> OBJ_ID = new State<>("cliffID", CliffId.class);

		public final static State<Wc3String> ART_CLIFF_MODEL_DIR = new State<>("cliffModelDir", Wc3String.class);
		public final static State<Wc3String> ART_RAMP_MODEL_DIR = new State<>("rampModelDir", Wc3String.class);
		public final static State<Wc3String> ART_TEX_DIR = new State<>("texDir", Wc3String.class);
		public final static State<Wc3String> ART_TEX_FILE = new State<>("texFile", Wc3String.class);

		public final static State<CliffClass> DATA_CLIFF_CLASS = new State<>("cliffClass", CliffClass.class);
		public final static State<TileId> DATA_GROUND_TILE = new State<>("groundTile", TileId.class);
		public final static State<TileId> DATA_OLD_ID = new State<>("oldID", TileId.class);
		public final static State<TileId> DATA_UPPER_TILE = new State<>("upperTile", TileId.class);

		public final static State<Bool> EDITOR_IN_BETA = new State<>("inBeta", Bool.class);
		public final static State<Wc3String> EDITOR_NAME = new State<>("name", Wc3String.class);
		public final static State<Int> EDITOR_VERSION = new State<>("version", Int.class);
	}
	
	public static class Obj extends SLK.Obj<CliffId> {
		public Path getRampModelDir() {
			return Paths.get(get(States.ART_RAMP_MODEL_DIR).toString());
		}
		
		public void setRampModelDir(Path val) {
			set(States.ART_RAMP_MODEL_DIR, Wc3String.valueOf(val.toString()));
		}
		
		public Path getCliffModelDir() {
			return Paths.get(get(States.ART_CLIFF_MODEL_DIR).toString());
		}
		
		public void setCliffModelDir(Path val) {
			set(States.ART_CLIFF_MODEL_DIR, Wc3String.valueOf(val.toString()));
		}
		
		public Path getTex() {
			return Paths.get(get(States.ART_TEX_DIR).toString(), get(States.ART_TEX_FILE).toString());
		}
		
		public void setTex(Path val) {
			set(States.ART_TEX_DIR, Wc3String.valueOf(val.getParent().toString()));
			set(States.ART_TEX_DIR, Wc3String.valueOf(val.getFileName().toString()));
		}
		
		public String getName() {
			return get(States.EDITOR_NAME).toString();
		}
		
		public void setName(String val) {
			set(States.EDITOR_NAME, Wc3String.valueOf(val));
		}
		
		public TileId getGroundTile() {
			return get(States.DATA_GROUND_TILE);
		}
		
		public void setGroundTile(TileId val) {
			set(States.DATA_GROUND_TILE, val);
		}
		
		public TileId getUpperTile() {
			return get(States.DATA_UPPER_TILE);
		}
		
		public void setUpperTile(TileId val) {
			set(States.DATA_UPPER_TILE, val);
		}
		
		public CliffClass getCliffClass() {
			return get(States.DATA_CLIFF_CLASS);
		}
		
		public void setCliffClass(CliffClass val) {
			set(States.DATA_CLIFF_CLASS, val);
		}
		
		public TileId getOldId() {
			return get(States.DATA_OLD_ID);
		}
		
		public void setOldId(TileId val) {
			set(States.DATA_OLD_ID, val);
		}

		public <T extends DataType> T get(States.State<T> state) {
			return state.tryCastVal(super.get(state));
		}
		
		public <T extends DataType> void set(States.State<T> state, T val) {
			super.set(state, val);
		}
		
		public <T extends DataType> void remove(States.State<T> state) {
			super.set(state, null);
		}
		
		private void read(SLK.Obj<? extends ObjId> slkObj) {
			merge(slkObj, true);
		}
		
		public Obj(SLK.Obj<? extends ObjId> slkObj) {
			this(CliffId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(CliffId id) {
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
	
	private Map<CliffId, Obj> _objs = new HashMap<>();
	
	@Override
	public Map<CliffId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Override
	public Obj addObj(CliffId id) {
		Obj obj = new Obj(id);
		
		addObj(obj);
		
		return obj;
	}
	
	public RawSLK toRawSLK() {
		RawSLK slk = new RawSLK();

		for (Obj obj : getObjs().values()) {
			CliffId id = obj.getId();
			
			SLK.Obj<ObjId> slkObj = slk.addObj(id);

			slkObj.merge(obj, true);
		}
		
		return slk;
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
		read(new RawSLK(file));
	}
	
	@Override
	public void write(File file) throws IOException {
		toRawSLK().write(file);
	}
	
	public CliffSLK() {
		addField(States.OBJ_ID);
		
		for (States.State state : States.values()) {
			addField(state);
		}
	}

	@Override
	public Obj createObj(ObjId id) {
		return new Obj(CliffId.valueOf(id));
	}

	@Override
	public void merge(CliffSLK other, boolean overwrite) {
		// TODO Auto-generated method stub
		
	}
}
