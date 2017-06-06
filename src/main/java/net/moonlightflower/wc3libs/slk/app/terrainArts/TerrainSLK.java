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

import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.Bool;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.TileId;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.RawSLK;
import net.moonlightflower.wc3libs.slk.SLK;
import net.moonlightflower.wc3libs.slk.SLKState;

public class TerrainSLK extends ObjSLK<TerrainSLK, TileId, TerrainSLK.Obj> {
	public final static File GAME_USE_PATH = new File("TerrainArt\\Terrain.slk");
	
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

		public final static State<TileId> OBJ_ID = new State<>("tileID", TileId.class);
		
		public final static State<Bool> ART_FOOTPRINTS = new State<>("footprints", Bool.class);
		public final static State<Wc3String> ART_TEX_DIR = new State<>("dir", Wc3String.class);
		public final static State<Wc3String> ART_TEX_FILE = new State<>("file", Wc3String.class);
		
		public final static State<Int> DATA_BLIGHT_PRIO = new State<>("blightPri", Int.class);
		public final static State<Bool> DATA_BUILDABLE = new State<>("buildable", Bool.class);
		public final static State<Int> DATA_CLIFF_SET = new State<>("cliffSet", Int.class);
		public final static State<DataList<TileId>> DATA_CONVERT_TO = new State<>("convertTo", new DataTypeInfo(DataList.class, TileId.class));
		public final static State<Bool> DATA_FLYABLE = new State<>("flyable", Bool.class);
		public final static State<Bool> DATA_WALKABLE = new State<>("walkable", Bool.class);
		
		public final static State<Wc3String> EDITOR_COMMENT = new State<>("comment", Wc3String.class);
		public final static State<Bool> EDITOR_IN_BETA = new State<>("InBeta", Bool.class);
		public final static State<Int> EDITOR_VERSION = new State<>("version", Int.class);
	}
	
	public static class Obj extends SLK.Obj<TileId> {
		public Path getTex() {
			return Paths.get(get(States.ART_TEX_DIR).toString(), get(States.ART_TEX_FILE).toString());
		}
		
		public void setTex(Path val) {
			set(States.ART_TEX_DIR, Wc3String.valueOf(val.getParent().toString()));
			set(States.ART_TEX_FILE, Wc3String.valueOf(val.getFileName().toString()));
		}
		
		public Int getCliffSet() {
			return get(States.DATA_CLIFF_SET);
		}
		
		public void setCliffSet(Int val) {
			set(States.DATA_CLIFF_SET, val);
		}
		
		public Bool getWalkable() {
			return get(States.DATA_WALKABLE);
		}
		
		public void setWalkable(Bool val) {
			set(States.DATA_WALKABLE, val);
		}
		
		public Bool getFlyable() {
			return get(States.DATA_FLYABLE);
		}
		
		public void setFlyable(Bool val) {
			set(States.DATA_FLYABLE, val);
		}
		
		public Bool getBuildable() {
			return get(States.DATA_BUILDABLE);
		}
		
		public void setBuildable(Bool val) {
			set(States.DATA_BUILDABLE, val);
		}
		
		public Bool getFootprints() {
			return get(States.ART_FOOTPRINTS);
		}
		
		public void setFootprints(Bool val) {
			set(States.ART_FOOTPRINTS, val);
		}
		
		public Int getBlightPrio() {
			return get(States.DATA_BLIGHT_PRIO);
		}
		
		public void setBlightPrio(Int val) {
			set(States.DATA_BLIGHT_PRIO, val);
		}
		
		public <T extends DataType> T get(States.State<T> state) {
			return (T) super.get(state);
		}
		
		public <T extends DataType> void set(States.State<T> state, T val) {
			super.set(state, val);
		}
		
		public <T extends DataType> void remove(States.State<T> state) {
			super.set(state, null);
		}
		
		private void read(SLK.Obj slkObj) {
			merge(slkObj, true);
		}
		
		public Obj(SLK.Obj slkObj) {
			this(TileId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(TileId id) {
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

	//private Map<TileId, Obj> _objs = new LinkedHashMap<>();
	
	@Override
	public Map<TileId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Override
	public Obj addObj(TileId id) {
		if (_objs.containsKey(id)) return _objs.get(id);
		
		Obj obj = new Obj(id);
		
		addObj(obj);
		
		return obj;
	}
	
	public RawSLK toRawSLK() {
		RawSLK slk = new RawSLK();

		for (Obj obj : getObjs().values()) {
			ObjId id = obj.getId();

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
	
	public TerrainSLK() {
		addField(States.OBJ_ID);
		
		for (States.State state : States.values()) {
			addField(state);
		}
	}

	@Override
	public Obj createObj(ObjId id) {
		return new Obj(TileId.valueOf(id));
	}

	@Override
	public void merge(TerrainSLK other, boolean overwrite) {
		// TODO Auto-generated method stub
		
	}
}
