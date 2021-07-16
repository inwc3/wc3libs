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
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.RawSLK;
import net.moonlightflower.wc3libs.slk.SLK;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WaterSLK extends ObjSLK<WaterSLK, WaterId, WaterSLK.Obj> {
	public final static File GAME_PATH = new File("TerrainArt\\Water.slk");

	public static class State<T extends DataType> extends ObjSLK.State<T> {
		public final static State<WaterId> OBJ_ID = new State<>("waterID", WaterId.class);

		public final static State<AlphaMode> ALPHA_MODE = new State<>("alphaMode", AlphaMode.class);
		public final static State<War3Int> CELLS = new State<>("cells", War3Int.class);
		public final static State<War3Int> D_MAX_ALPHA = new State<>("DmaxA", War3Int.class);
		public final static State<War3Int> D_MAX_BLUE = new State<>("DmaxB", War3Int.class);
		public final static State<War3Int> D_MAX_GREEN = new State<>("DmaxG", War3Int.class);
		public final static State<War3Int> D_MAX_RED = new State<>("DmaxR", War3Int.class);
		public final static State<War3Int> D_MIN_ALPHA = new State<>("DminA", War3Int.class);
		public final static State<War3Int> D_MIN_BLUE = new State<>("DminB", War3Int.class);
		public final static State<War3Int> D_MIN_GREEN = new State<>("DminG", War3Int.class);
		public final static State<War3Int> D_MIN_RED = new State<>("DminR", War3Int.class);
		public final static State<War3Real> HEIGHT = new State<>("height", War3Real.class);
		public final static State<War3Bool> IMPASSABLE = new State<>("impassable", War3Bool.class);
		public final static State<War3Bool> LIGHTING = new State<>("lighting", War3Bool.class);
		public final static State<War3Real> MAX_X = new State<>("maxX", War3Real.class);
		public final static State<War3Real> MAX_Y = new State<>("maxY", War3Real.class);
		public final static State<War3Real> MAX_Z = new State<>("maxZ", War3Real.class);
		public final static State<War3Real> MIN_X = new State<>("minX", War3Real.class);
		public final static State<War3Real> MIN_Y = new State<>("minX", War3Real.class);
		public final static State<War3Real> MIN_Z = new State<>("minX", War3Real.class);
		public final static State<War3Int> MINIMAP_COLOR_ALPHA = new State<>("mmAlpha", War3Int.class);
		public final static State<War3Int> MINIMAP_COLOR_BLUE = new State<>("mmBlue", War3Int.class);
		public final static State<War3Int> MINIMAP_COLOR_GREEN = new State<>("mmGreen", War3Int.class);
		public final static State<War3Int> MINIMAP_COLOR_RED = new State<>("mmRed", War3Int.class);
		public final static State<War3Real> RATE_X = new State<>("rateX", War3Real.class);
		public final static State<War3Real> RATE_Y = new State<>("rateY", War3Real.class);
		public final static State<War3Real> RATE_Z = new State<>("rateZ", War3Real.class);
		public final static State<War3Real> REV_X = new State<>("revX", War3Real.class);
		public final static State<War3Real> REV_Y = new State<>("revY", War3Real.class);
		public final static State<War3Int> S_MAX_ALPHA = new State<>("SmaxA", War3Int.class);
		public final static State<War3Int> S_MAX_BLUE = new State<>("SmaxB", War3Int.class);
		public final static State<War3Int> S_MAX_GREEN = new State<>("SmaxG", War3Int.class);
		public final static State<War3Int> S_MAX_RED = new State<>("SmaxR", War3Int.class);
		public final static State<War3Int> S_MIN_ALPHA = new State<>("SminA", War3Int.class);
		public final static State<War3Int> S_MIN_BLUE = new State<>("SminB", War3Int.class);
		public final static State<War3Int> S_MIN_GREEN = new State<>("SminG", War3Int.class);
		public final static State<War3Int> S_MIN_RED = new State<>("SminR", War3Int.class);
		public final static State<War3Bool> SHORE_IN_FOG = new State<>("shoreInFog", War3Bool.class);
		public final static State<War3String> SHORE_DIR = new State<>("shoreDir", War3String.class);
		public final static State<War3String> SHORE_IC_FILE = new State<>("shoreICFile", War3String.class);
		public final static State<War3Real> SHORE_IC_VAR = new State<>("shoreICVar", War3Real.class);
		public final static State<War3String> SHORE_OC_FILE = new State<>("shoreOCFile", War3String.class);
		public final static State<War3Real> SHORE_OC_VAR = new State<>("shoreOCVar", War3Real.class);
		public final static State<War3String> SHORE_S_FILE = new State<>("shoreSFile", War3String.class);
		public final static State<War3Real> SHORE_S_VAR = new State<>("shoreSVar", War3Real.class);
		public final static State<War3String> TEX_FILE = new State<>("texFile", War3String.class);
		public final static State<War3Int> TEX_NUM = new State<>("numTex", War3Int.class);
		public final static State<War3Real> TEX_RATE = new State<>("texRate", War3Real.class);
		public final static State<War3Real> TEX_OFFSET = new State<>("texOffset", War3Real.class);

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
	
	public static class Obj extends SLK.Obj<WaterId> {
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

		public War3Real getHeight() {
			return get(State.HEIGHT);
		}
		
		public void setHeight(War3Real val) {
			set(State.HEIGHT, val);
		}
		
		public War3Bool getImpassable() {
			return get(State.IMPASSABLE);
		}
		
		public void setImpassable(War3Bool val) {
			set(State.IMPASSABLE, val);
		}
		
		public Path getTex() {
			return Paths.get(get(State.TEX_FILE).toString());
		}
		
		public void setTex(Path val) {
			set(State.TEX_FILE, War3String.valueOf(val.toString()));
		}
		
		private Color _minimapColor = Color.fromBGRA255(0, 0, 0, 0);
		
		public Color getMinimapColor() {
			return Color.fromBGRA255(get(State.MINIMAP_COLOR_BLUE).toInt(), get(State.MINIMAP_COLOR_GREEN).toInt(), get(State.MINIMAP_COLOR_RED).toInt(), get(State.MINIMAP_COLOR_ALPHA).toInt());
		}
		
		public void setMinimapColor(Color val) {
			set(State.MINIMAP_COLOR_ALPHA, War3Int.valueOf(val.getAlpha255()));
			set(State.MINIMAP_COLOR_BLUE, War3Int.valueOf(val.getBlue255()));
			set(State.MINIMAP_COLOR_GREEN, War3Int.valueOf(val.getGreen255()));
			set(State.MINIMAP_COLOR_RED, War3Int.valueOf(val.getRed255()));
		}
		
		public War3Int getNumTex() {
			return get(State.TEX_NUM);
		}
		
		public void setNumTex(War3Int val) {
			set(State.TEX_NUM, val);
		}
		
		public War3Real getTexOffset() {
			return get(State.TEX_OFFSET);
		}
		
		public void setTexOffset(War3Real val) {
			set(State.TEX_OFFSET, val);
		}
		
		public War3Real getTexRate() {
			return get(State.TEX_RATE);
		}
		
		public void setTexRate(War3Real val) {
			set(State.TEX_RATE, val);
		}
		
		public AlphaMode getAlphaMode() {
			return get(State.ALPHA_MODE);
		}
		
		public void setAlphaMode(AlphaMode val) {
			set(State.ALPHA_MODE, val);
		}
		
		public War3Bool getLighting() {
			return get(State.LIGHTING);
		}
		
		public void setLighting(War3Bool val) {
			set(State.LIGHTING, val);
		}
		
		public War3Int getCells() {
			return get(State.CELLS);
		}
		
		public void setCells(War3Int val) {
			set(State.CELLS, val);
		}
		
		public Coords3DF getMinExtents() {
			return new Coords3DF(get(State.MIN_X), get(State.MIN_Y), get(State.MIN_Z));
		}
		
		public void setMinExtents(Coords3DF val) {
			set(State.MIN_X, val.getX());
			set(State.MIN_Y, val.getY());
			set(State.MIN_Z, val.getZ());
		}

		public Coords3DF getMaxExtents() {
			return new Coords3DF(get(State.MAX_X), get(State.MAX_Y), get(State.MAX_Z));
		}

		public void setMaxExtents(Coords3DF val) {
			set(State.MAX_X, val.getX());
			set(State.MAX_Y, val.getY());
			set(State.MAX_Z, val.getZ());
		}

		public Coords3DF getRates() {
			return new Coords3DF(get(State.RATE_X), get(State.RATE_Y), get(State.RATE_Z));
		}

		public void setRates(Coords3DF val) {
			set(State.RATE_X, val.getX());
			set(State.RATE_Y, val.getY());
			set(State.RATE_Z, val.getZ());
		}

		public Coords2DF getRevs() {
			return new Coords2DF(get(State.REV_X), get(State.REV_Y));
		}

		public void setRevs(Coords2DF val) {
			set(State.REV_X, val.getX());
			set(State.REV_Y, val.getY());
		}
		
		public War3Bool getShoreInFog() {
			return get(State.SHORE_IN_FOG);
		}

		public void setShoreInFog(War3Bool val) {
			set(State.SHORE_IN_FOG, val);
		}
		
		public String getShoreDir() {
			return get(State.SHORE_DIR).toString();
		}

		public void setShoreDir(String val) {
			set(State.SHORE_DIR, War3String.valueOf(val));
		}
		
		public String getShoreSFile() {
			return get(State.SHORE_S_FILE).toString();
		}

		public void setShoreSFile(String val) {
			set(State.SHORE_S_FILE, War3String.valueOf(val));
		}
		
		public War3Real getShoreSVar() {
			return get(State.SHORE_S_VAR);
		}

		public void setShoreSVar(War3Real val) {
			set(State.SHORE_S_VAR, val);
		}
		
		public String getShoreOCFile() {
			return get(State.SHORE_OC_FILE).toString();
		}

		public void setShoreOCFile(String val) {
			set(State.SHORE_OC_FILE, War3String.valueOf(val));
		}
		
		public War3Real getShoreOCVar() {
			return get(State.SHORE_OC_VAR);
		}

		public void setShoreOCVar(War3Real val) {
			set(State.SHORE_OC_VAR, val);
		}
		
		public String getShoreICFile() {
			return get(State.SHORE_IC_FILE).toString();
		}
		
		public void setShoreICFile(String val) {
			set(State.SHORE_IC_FILE, War3String.valueOf(val));
		}
		
		public War3Real getShoreICVar() {
			return get(State.SHORE_IC_VAR);
		}

		public void setShoreICVar(War3Real val) {
			set(State.SHORE_IC_VAR, val);
		}
		
		public Color getSMinColor() {
			return Color.fromBGRA255(get(State.S_MIN_BLUE).toInt(), get(State.S_MIN_GREEN).toInt(), get(State.S_MIN_RED).toInt(), get(State.S_MIN_ALPHA).toInt());
		}
		
		public void setSMinColor(Color val) {
			set(State.S_MIN_ALPHA, War3Int.valueOf(val.getAlpha255()));
			set(State.S_MIN_BLUE, War3Int.valueOf(val.getBlue255()));
			set(State.S_MIN_GREEN, War3Int.valueOf(val.getGreen255()));
			set(State.S_MIN_RED, War3Int.valueOf(val.getRed255()));
		}

		public Color getSMaxColor() {
			return Color.fromBGRA255(get(State.S_MAX_BLUE).toInt(), get(State.S_MAX_GREEN).toInt(), get(State.S_MAX_RED).toInt(), get(State.S_MAX_ALPHA).toInt());
		}

		public void setSMaxColor(Color val) {
			set(State.S_MAX_ALPHA, War3Int.valueOf(val.getAlpha255()));
			set(State.S_MAX_BLUE, War3Int.valueOf(val.getBlue255()));
			set(State.S_MAX_GREEN, War3Int.valueOf(val.getGreen255()));
			set(State.S_MAX_RED, War3Int.valueOf(val.getRed255()));
		}
		
		public Color getDMinColor() {
			return Color.fromBGRA255(get(State.D_MIN_BLUE).toInt(), get(State.D_MIN_GREEN).toInt(), get(State.D_MIN_RED).toInt(), get(State.D_MIN_ALPHA).toInt());
		}
		
		public void setDMinColor(Color val) {
			set(State.D_MIN_ALPHA, War3Int.valueOf(val.getAlpha255()));
			set(State.D_MIN_BLUE, War3Int.valueOf(val.getBlue255()));
			set(State.D_MIN_GREEN, War3Int.valueOf(val.getGreen255()));
			set(State.D_MIN_RED, War3Int.valueOf(val.getRed255()));
		}
		
		public Color getDMaxColor() {
			return Color.fromBGRA255(get(State.D_MAX_BLUE).toInt(), get(State.D_MAX_GREEN).toInt(), get(State.D_MAX_RED).toInt(), get(State.D_MAX_ALPHA).toInt());
		}
		
		public void setDMaxColor(Color val) {
			set(State.D_MAX_ALPHA, War3Int.valueOf(val.getAlpha255()));
			set(State.D_MAX_BLUE, War3Int.valueOf(val.getBlue255()));
			set(State.D_MAX_GREEN, War3Int.valueOf(val.getGreen255()));
			set(State.D_MAX_RED, War3Int.valueOf(val.getRed255()));
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
			super(WaterId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(WaterId id) {
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
	
	//private Map<WaterId, Camera> _objs = new LinkedHashMap<>();
	
	@Nonnull
	@Override
	public Map<WaterId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(@Nonnull Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Nonnull
	@Override
	public Obj addObj(@Nonnull WaterId id) {
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

			obj.merge(slkObj, true);
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
	
	public WaterSLK() {
		addField(State.OBJ_ID);
		
		for (State<?> state : State.values(State.class)) {
			addField(state);
		}
	}

	@Nonnull
	@Override
	public Obj createObj(@Nonnull ObjId id) {
		return new Obj(WaterId.valueOf(id));
	}

	@Override
	public void merge(@Nonnull WaterSLK other, boolean overwrite) {
		// TODO Auto-generated method stub
		
	}
}
