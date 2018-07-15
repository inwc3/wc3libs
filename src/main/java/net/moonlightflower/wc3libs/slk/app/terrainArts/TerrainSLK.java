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
import net.moonlightflower.wc3libs.dataTypes.app.War3Bool;
import net.moonlightflower.wc3libs.dataTypes.app.War3Int;
import net.moonlightflower.wc3libs.dataTypes.app.TileId;
import net.moonlightflower.wc3libs.dataTypes.app.War3String;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.RawSLK;
import net.moonlightflower.wc3libs.slk.SLK;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TerrainSLK extends ObjSLK<TerrainSLK, TileId, TerrainSLK.Obj> {
	public final static File GAME_PATH = new File("TerrainArt\\Terrain.slk");

	public static class State<T extends DataType> extends ObjSLK.State<T> {
		public final static State<TileId> OBJ_ID = new State<>("tileID", TileId.class);

		public final static State<War3Bool> ART_FOOTPRINTS = new State<>("footprints", War3Bool.class);
		public final static State<War3String> ART_TEX_DIR = new State<>("dir", War3String.class);
		public final static State<War3String> ART_TEX_FILE = new State<>("file", War3String.class);

		public final static State<War3Int> DATA_BLIGHT_PRIO = new State<>("blightPri", War3Int.class);
		public final static State<War3Bool> DATA_BUILDABLE = new State<>("buildable", War3Bool.class);
		public final static State<War3Int> DATA_CLIFF_SET = new State<>("cliffSet", War3Int.class);
		public final static State<DataList<TileId>> DATA_CONVERT_TO = new State<>("convertTo", new DataTypeInfo(DataList.class, TileId.class));
		public final static State<War3Bool> DATA_FLYABLE = new State<>("flyable", War3Bool.class);
		public final static State<War3Bool> DATA_WALKABLE = new State<>("walkable", War3Bool.class);

		public final static State<War3String> EDITOR_COMMENT = new State<>("comment", War3String.class);
		public final static State<War3Bool> EDITOR_IN_BETA = new State<>("InBeta", War3Bool.class);
		public final static State<War3Int> EDITOR_VERSION = new State<>("version", War3Int.class);

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
	
	public static class Obj extends SLK.Obj<TileId> {
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

		public Path getTex() {
			return Paths.get(get(State.ART_TEX_DIR).toString(), get(State.ART_TEX_FILE).toString());
		}
		
		public void setTex(Path val) {
			set(State.ART_TEX_DIR, War3String.valueOf(val.getParent().toString()));
			set(State.ART_TEX_FILE, War3String.valueOf(val.getFileName().toString()));
		}
		
		public War3Int getCliffSet() {
			return get(State.DATA_CLIFF_SET);
		}
		
		public void setCliffSet(War3Int val) {
			set(State.DATA_CLIFF_SET, val);
		}
		
		public War3Bool getWalkable() {
			return get(State.DATA_WALKABLE);
		}
		
		public void setWalkable(War3Bool val) {
			set(State.DATA_WALKABLE, val);
		}
		
		public War3Bool getFlyable() {
			return get(State.DATA_FLYABLE);
		}
		
		public void setFlyable(War3Bool val) {
			set(State.DATA_FLYABLE, val);
		}
		
		public War3Bool getBuildable() {
			return get(State.DATA_BUILDABLE);
		}
		
		public void setBuildable(War3Bool val) {
			set(State.DATA_BUILDABLE, val);
		}
		
		public War3Bool getFootprints() {
			return get(State.ART_FOOTPRINTS);
		}
		
		public void setFootprints(War3Bool val) {
			set(State.ART_FOOTPRINTS, val);
		}
		
		public War3Int getBlightPrio() {
			return get(State.DATA_BLIGHT_PRIO);
		}
		
		public void setBlightPrio(War3Int val) {
			set(State.DATA_BLIGHT_PRIO, val);
		}
		
		public <T extends DataType> T get(State<T> state) {
			return (T) super.get(state);
		}
		
		public <T extends DataType> void set(State<T> state, T val) {
			super.set(state, val);
		}
		
		public <T extends DataType> void remove(State<T> state) {
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

			for (State<?> state : State.values(State.class)) {
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
		addField(State.OBJ_ID);
		
		for (State<?> state : State.values(State.class)) {
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
