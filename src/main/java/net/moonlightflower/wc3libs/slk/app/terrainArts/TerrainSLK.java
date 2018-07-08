package net.moonlightflower.wc3libs.slk.app.terrainArts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;

import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.Bool;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3Int;
import net.moonlightflower.wc3libs.dataTypes.app.TileId;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.RawSLK;
import net.moonlightflower.wc3libs.slk.SLK;
import net.moonlightflower.wc3libs.slk.SLKState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TerrainSLK extends ObjSLK<TerrainSLK, TileId, TerrainSLK.Obj> {
	public final static File GAME_USE_PATH = new File("TerrainArt\\Terrain.slk");
	
	public static class States {
		public static class State<T extends DataType> extends ObjSLK.State<T> {
			public State(String idString, DataTypeInfo typeInfo, T defVal) {
				super(idString, typeInfo, defVal);
			}

			public State(String idString, DataTypeInfo typeInfo) {
				super(idString, typeInfo);
			}

			public State(String idString, Class<T> type) {
				super(idString, type);
			}

			public State(String idString, Class<T> type, T defVal) {
				super(idString, type, defVal);
			}
		}

		public static Collection<State> values() {
			return (Collection<State>) State.values(State.class);
		}

		public final static State<TileId> OBJ_ID = new State<>("tileID", TileId.class);
		
		public final static State<Bool> ART_FOOTPRINTS = new State<>("footprints", Bool.class);
		public final static State<Wc3String> ART_TEX_DIR = new State<>("dir", Wc3String.class);
		public final static State<Wc3String> ART_TEX_FILE = new State<>("file", Wc3String.class);
		
		public final static State<Wc3Int> DATA_BLIGHT_PRIO = new State<>("blightPri", Wc3Int.class);
		public final static State<Bool> DATA_BUILDABLE = new State<>("buildable", Bool.class);
		public final static State<Wc3Int> DATA_CLIFF_SET = new State<>("cliffSet", Wc3Int.class);
		public final static State<DataList<TileId>> DATA_CONVERT_TO = new State<>("convertTo", new DataTypeInfo(DataList.class, TileId.class));
		public final static State<Bool> DATA_FLYABLE = new State<>("flyable", Bool.class);
		public final static State<Bool> DATA_WALKABLE = new State<>("walkable", Bool.class);
		
		public final static State<Wc3String> EDITOR_COMMENT = new State<>("comment", Wc3String.class);
		public final static State<Bool> EDITOR_IN_BETA = new State<>("InBeta", Bool.class);
		public final static State<Wc3Int> EDITOR_VERSION = new State<>("version", Wc3Int.class);
	}
	
	public static class Obj extends SLK.Obj<TileId> {
		private final Map<State, DataType> _stateVals = new LinkedHashMap<>();

		@Override
		public Map<State, DataType> getStateVals() {
			return new LinkedHashMap<>(_stateVals);
		}

		@Override
		protected void on_set(@Nonnull FieldId fieldId, @Nullable DataType val) {
			State state = State.valueByField(States.State.class, fieldId);

			if (state != null) _stateVals.put(state, val);
		}

		@Override
		protected void on_remove(@Nonnull FieldId fieldId) {
			State state = State.valueByField(States.State.class, fieldId);

			if (state != null) _stateVals.remove(state);
		}

		@Override
		protected void on_clear() {
			_stateVals.clear();
		}

		public Path getTex() {
			return Paths.get(get(States.ART_TEX_DIR).toString(), get(States.ART_TEX_FILE).toString());
		}
		
		public void setTex(Path val) {
			set(States.ART_TEX_DIR, Wc3String.valueOf(val.getParent().toString()));
			set(States.ART_TEX_FILE, Wc3String.valueOf(val.getFileName().toString()));
		}
		
		public Wc3Int getCliffSet() {
			return get(States.DATA_CLIFF_SET);
		}
		
		public void setCliffSet(Wc3Int val) {
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
		
		public Wc3Int getBlightPrio() {
			return get(States.DATA_BLIGHT_PRIO);
		}
		
		public void setBlightPrio(Wc3Int val) {
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
			super(TileId.valueOf(slkObj.getId()));
			
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

	//private Map<TileId, Camera> _objs = new LinkedHashMap<>();
	
	@Nonnull
	@Override
	public Map<TileId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(@Nonnull Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Nonnull
	@Override
	public Obj addObj(@Nonnull TileId id) {
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
		read(new RawSLK(file));
	}
	
	@Override
	public void write(@Nonnull File file) throws IOException {
		toRawSLK().write(file);
	}
	
	public TerrainSLK() {
		addField(States.OBJ_ID);
		
		for (States.State state : States.values()) {
			addField(state);
		}
	}

	@Nonnull
	@Override
	public Obj createObj(@Nonnull ObjId id) {
		return new Obj(TileId.valueOf(id));
	}

	@Override
	public void merge(@Nonnull TerrainSLK other, boolean overwrite) {
		// TODO Auto-generated method stub
		
	}
}
