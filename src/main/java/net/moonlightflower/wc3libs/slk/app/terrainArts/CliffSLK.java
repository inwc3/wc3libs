package net.moonlightflower.wc3libs.slk.app.terrainArts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.dataTypes.app.War3Int;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.RawSLK;
import net.moonlightflower.wc3libs.slk.SLK;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CliffSLK extends ObjSLK<CliffSLK, CliffId, CliffSLK.Obj> {
	public final static File GAME_PATH = new File("TerrainArt\\CliffTypes.slk");

	public static class State<T extends DataType> extends ObjSLK.State<T> {
		public final static State<CliffId> OBJ_ID = new State<>("cliffID", CliffId.class);

		public final static State<War3String> ART_CLIFF_MODEL_DIR = new State<>("cliffModelDir", War3String.class);
		public final static State<War3String> ART_RAMP_MODEL_DIR = new State<>("rampModelDir", War3String.class);
		public final static State<War3String> ART_TEX_DIR = new State<>("texDir", War3String.class);
		public final static State<War3String> ART_TEX_FILE = new State<>("texFile", War3String.class);

		public final static State<CliffClass> DATA_CLIFF_CLASS = new State<>("cliffClass", CliffClass.class);
		public final static State<TileId> DATA_GROUND_TILE = new State<>("groundTile", TileId.class);
		public final static State<TileId> DATA_OLD_ID = new State<>("oldID", TileId.class);
		public final static State<TileId> DATA_UPPER_TILE = new State<>("upperTile", TileId.class);

		public final static State<War3Bool> EDITOR_IN_BETA = new State<>("inBeta", War3Bool.class);
		public final static State<War3String> EDITOR_NAME = new State<>("name", War3String.class);
		public final static State<War3Int> EDITOR_VERSION = new State<>("version", War3Int.class);

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
	
	public static class Obj extends SLK.Obj<CliffId> {
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

		public Path getRampModelDir() {
			return Paths.get(get(State.ART_RAMP_MODEL_DIR).toString());
		}
		
		public void setRampModelDir(Path val) {
			set(State.ART_RAMP_MODEL_DIR, War3String.valueOf(val.toString()));
		}
		
		public Path getCliffModelDir() {
			return Paths.get(get(State.ART_CLIFF_MODEL_DIR).toString());
		}
		
		public void setCliffModelDir(Path val) {
			set(State.ART_CLIFF_MODEL_DIR, War3String.valueOf(val.toString()));
		}
		
		public Path getTex() {
			return Paths.get(get(State.ART_TEX_DIR).toString(), get(State.ART_TEX_FILE).toString());
		}
		
		public void setTex(Path val) {
			set(State.ART_TEX_DIR, War3String.valueOf(val.getParent().toString()));
			set(State.ART_TEX_DIR, War3String.valueOf(val.getFileName().toString()));
		}
		
		public String getName() {
			return get(State.EDITOR_NAME).toString();
		}
		
		public void setName(String val) {
			set(State.EDITOR_NAME, War3String.valueOf(val));
		}
		
		public TileId getGroundTile() {
			return get(State.DATA_GROUND_TILE);
		}
		
		public void setGroundTile(TileId val) {
			set(State.DATA_GROUND_TILE, val);
		}
		
		public TileId getUpperTile() {
			return get(State.DATA_UPPER_TILE);
		}
		
		public void setUpperTile(TileId val) {
			set(State.DATA_UPPER_TILE, val);
		}
		
		public CliffClass getCliffClass() {
			return get(State.DATA_CLIFF_CLASS);
		}
		
		public void setCliffClass(CliffClass val) {
			set(State.DATA_CLIFF_CLASS, val);
		}
		
		public TileId getOldId() {
			return get(State.DATA_OLD_ID);
		}
		
		public void setOldId(TileId val) {
			set(State.DATA_OLD_ID, val);
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
		
		public <T extends DataType> void remove(State<T> state) {
			super.set(state, null);
		}
		
		private void read(SLK.Obj<? extends ObjId> slkObj) {
			merge(slkObj, true);
		}
		
		public Obj(SLK.Obj<? extends ObjId> slkObj) {
			super(CliffId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(CliffId id) {
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
	
	//private Map<CliffId, Camera> _objs = new LinkedHashMap<>();
	
	@Nonnull
	@Override
	public Map<CliffId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(@Nonnull Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Nonnull
	@Override
	public Obj addObj(@Nonnull CliffId id) {
		if (_objs.containsKey(id)) return _objs.get(id);
		
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
	
	public CliffSLK() {
		addField(State.OBJ_ID);
		
		for (State<?> state : State.values(State.class)) {
			addField(state);
		}
	}

	@Nonnull
	@Override
	public Obj createObj(@Nonnull ObjId id) {
		return new Obj(CliffId.valueOf(id));
	}

	@Override
	public void merge(@Nonnull CliffSLK other, boolean overwrite) {
		// TODO Auto-generated method stub
		
	}
}
