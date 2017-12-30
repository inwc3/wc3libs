package net.moonlightflower.wc3libs.slk.app.terrainArts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3Int;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.RawSLK;
import net.moonlightflower.wc3libs.slk.SLK;
import net.moonlightflower.wc3libs.slk.SLKState;

public class WaterSLK extends ObjSLK<WaterSLK, WaterId, WaterSLK.Obj> {
	public final static File GAME_USE_PATH = new File("TerrainArt\\Water.slk");
	
	public static class States {
		static public class State<T extends DataType> extends SLKState<T> {
			private final static List<State> _values = new ArrayList<>();
			
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

		public final static State<WaterId> OBJ_ID = new State<>("waterID", WaterId.class);
		
		public final static State<AlphaMode> ALPHA_MODE = new State<>("alphaMode", AlphaMode.class);
		public final static State<Wc3Int> CELLS = new State<>("cells", Wc3Int.class);
		public final static State<Wc3Int> D_MAX_ALPHA = new State<>("DmaxA", Wc3Int.class);
		public final static State<Wc3Int> D_MAX_BLUE = new State<>("DmaxB", Wc3Int.class);
		public final static State<Wc3Int> D_MAX_GREEN = new State<>("DmaxG", Wc3Int.class);
		public final static State<Wc3Int> D_MAX_RED = new State<>("DmaxR", Wc3Int.class);
		public final static State<Wc3Int> D_MIN_ALPHA = new State<>("DminA", Wc3Int.class);
		public final static State<Wc3Int> D_MIN_BLUE = new State<>("DminB", Wc3Int.class);
		public final static State<Wc3Int> D_MIN_GREEN = new State<>("DminG", Wc3Int.class);
		public final static State<Wc3Int> D_MIN_RED = new State<>("DminR", Wc3Int.class);
		public final static State<Real> HEIGHT = new State<>("height", Real.class);
		public final static State<Bool> IMPASSABLE = new State<>("impassable", Bool.class);
		public final static State<Bool> LIGHTING = new State<>("lighting", Bool.class);
		public final static State<Real> MAX_X = new State<>("maxX", Real.class);
		public final static State<Real> MAX_Y = new State<>("maxY", Real.class);
		public final static State<Real> MAX_Z = new State<>("maxZ", Real.class);
		public final static State<Real> MIN_X = new State<>("minX", Real.class);
		public final static State<Real> MIN_Y = new State<>("minX", Real.class);
		public final static State<Real> MIN_Z = new State<>("minX", Real.class);
		public final static State<Wc3Int> MINIMAP_COLOR_ALPHA = new State<>("mmAlpha", Wc3Int.class);
		public final static State<Wc3Int> MINIMAP_COLOR_BLUE = new State<>("mmBlue", Wc3Int.class);
		public final static State<Wc3Int> MINIMAP_COLOR_GREEN = new State<>("mmGreen", Wc3Int.class);
		public final static State<Wc3Int> MINIMAP_COLOR_RED = new State<>("mmRed", Wc3Int.class);
		public final static State<Real> RATE_X = new State<>("rateX", Real.class);
		public final static State<Real> RATE_Y = new State<>("rateY", Real.class);
		public final static State<Real> RATE_Z = new State<>("rateZ", Real.class);
		public final static State<Real> REV_X = new State<>("revX", Real.class);
		public final static State<Real> REV_Y = new State<>("revY", Real.class);
		public final static State<Wc3Int> S_MAX_ALPHA = new State<>("SmaxA", Wc3Int.class);
		public final static State<Wc3Int> S_MAX_BLUE = new State<>("SmaxB", Wc3Int.class);
		public final static State<Wc3Int> S_MAX_GREEN = new State<>("SmaxG", Wc3Int.class);
		public final static State<Wc3Int> S_MAX_RED = new State<>("SmaxR", Wc3Int.class);
		public final static State<Wc3Int> S_MIN_ALPHA = new State<>("SminA", Wc3Int.class);
		public final static State<Wc3Int> S_MIN_BLUE = new State<>("SminB", Wc3Int.class);
		public final static State<Wc3Int> S_MIN_GREEN = new State<>("SminG", Wc3Int.class);
		public final static State<Wc3Int> S_MIN_RED = new State<>("SminR", Wc3Int.class);
		public final static State<Bool> SHORE_IN_FOG = new State<>("shoreInFog", Bool.class);
		public final static State<Wc3String> SHORE_DIR = new State<>("shoreDir", Wc3String.class);
		public final static State<Wc3String> SHORE_IC_FILE = new State<>("shoreICFile", Wc3String.class);
		public final static State<Real> SHORE_IC_VAR = new State<>("shoreICVar", Real.class);
		public final static State<Wc3String> SHORE_OC_FILE = new State<>("shoreOCFile", Wc3String.class);
		public final static State<Real> SHORE_OC_VAR = new State<>("shoreOCVar", Real.class);
		public final static State<Wc3String> SHORE_S_FILE = new State<>("shoreSFile", Wc3String.class);
		public final static State<Real> SHORE_S_VAR = new State<>("shoreSVar", Real.class);
		public final static State<Wc3String> TEX_FILE = new State<>("texFile", Wc3String.class);
		public final static State<Wc3Int> TEX_NUM = new State<>("numTex", Wc3Int.class);
		public final static State<Real> TEX_RATE = new State<>("texRate", Real.class);
		public final static State<Real> TEX_OFFSET = new State<>("texOffset", Real.class);
	}
	
	public static class Obj extends SLK.Obj<WaterId> {
		public Real getHeight() {
			return get(States.HEIGHT);
		}
		
		public void setHeight(Real val) {
			set(States.HEIGHT, val);
		}
		
		public Bool getImpassable() {
			return get(States.IMPASSABLE);
		}
		
		public void setImpassable(Bool val) {
			set(States.IMPASSABLE, val);
		}
		
		public Path getTex() {
			return Paths.get(get(States.TEX_FILE).toString());
		}
		
		public void setTex(Path val) {
			set(States.TEX_FILE, Wc3String.valueOf(val.toString()));
		}
		
		private Color _minimapColor = Color.fromBGRA255(0, 0, 0, 0);
		
		public Color getMinimapColor() {
			return Color.fromBGRA255(get(States.MINIMAP_COLOR_BLUE).toInt(), get(States.MINIMAP_COLOR_GREEN).toInt(), get(States.MINIMAP_COLOR_RED).toInt(), get(States.MINIMAP_COLOR_ALPHA).toInt());
		}
		
		public void setMinimapColor(Color val) {
			set(States.MINIMAP_COLOR_ALPHA, Wc3Int.valueOf(val.getAlpha255()));
			set(States.MINIMAP_COLOR_BLUE, Wc3Int.valueOf(val.getBlue255()));
			set(States.MINIMAP_COLOR_GREEN, Wc3Int.valueOf(val.getGreen255()));
			set(States.MINIMAP_COLOR_RED, Wc3Int.valueOf(val.getRed255()));
		}
		
		public Wc3Int getNumTex() {
			return get(States.TEX_NUM);
		}
		
		public void setNumTex(Wc3Int val) {
			set(States.TEX_NUM, val);
		}
		
		public Real getTexOffset() {
			return get(States.TEX_OFFSET);
		}
		
		public void setTexOffset(Real val) {
			set(States.TEX_OFFSET, val);
		}
		
		public Real getTexRate() {
			return get(States.TEX_RATE);
		}
		
		public void setTexRate(Real val) {
			set(States.TEX_RATE, val);
		}
		
		public AlphaMode getAlphaMode() {
			return get(States.ALPHA_MODE);
		}
		
		public void setAlphaMode(AlphaMode val) {
			set(States.ALPHA_MODE, val);
		}
		
		public Bool getLighting() {
			return get(States.LIGHTING);
		}
		
		public void setLighting(Bool val) {
			set(States.LIGHTING, val);
		}
		
		public Wc3Int getCells() {
			return get(States.CELLS);
		}
		
		public void setCells(Wc3Int val) {
			set(States.CELLS, val);
		}
		
		public Coords3DF getMinExtents() {
			return new Coords3DF(get(States.MIN_X), get(States.MIN_Y), get(States.MIN_Z));
		}
		
		public void setMinExtents(Coords3DF val) {
			set(States.MIN_X, val.getX());
			set(States.MIN_Y, val.getY());
			set(States.MIN_Z, val.getZ());
		}

		public Coords3DF getMaxExtents() {
			return new Coords3DF(get(States.MAX_X), get(States.MAX_Y), get(States.MAX_Z));
		}

		public void setMaxExtents(Coords3DF val) {
			set(States.MAX_X, val.getX());
			set(States.MAX_Y, val.getY());
			set(States.MAX_Z, val.getZ());
		}

		public Coords3DF getRates() {
			return new Coords3DF(get(States.RATE_X), get(States.RATE_Y), get(States.RATE_Z));
		}

		public void setRates(Coords3DF val) {
			set(States.RATE_X, val.getX());
			set(States.RATE_Y, val.getY());
			set(States.RATE_Z, val.getZ());
		}

		public Coords2DF getRevs() {
			return new Coords2DF(get(States.REV_X), get(States.REV_Y));
		}

		public void setRevs(Coords2DF val) {
			set(States.REV_X, val.getX());
			set(States.REV_Y, val.getY());
		}
		
		public Bool getShoreInFog() {
			return get(States.SHORE_IN_FOG);
		}

		public void setShoreInFog(Bool val) {
			set(States.SHORE_IN_FOG, val);
		}
		
		public String getShoreDir() {
			return get(States.SHORE_DIR).toString();
		}

		public void setShoreDir(String val) {
			set(States.SHORE_DIR, Wc3String.valueOf(val));
		}
		
		public String getShoreSFile() {
			return get(States.SHORE_S_FILE).toString();
		}

		public void setShoreSFile(String val) {
			set(States.SHORE_S_FILE, Wc3String.valueOf(val));
		}
		
		public Real getShoreSVar() {
			return get(States.SHORE_S_VAR);
		}

		public void setShoreSVar(Real val) {
			set(States.SHORE_S_VAR, val);
		}
		
		public String getShoreOCFile() {
			return get(States.SHORE_OC_FILE).toString();
		}

		public void setShoreOCFile(String val) {
			set(States.SHORE_OC_FILE, Wc3String.valueOf(val));
		}
		
		public Real getShoreOCVar() {
			return get(States.SHORE_OC_VAR);
		}

		public void setShoreOCVar(Real val) {
			set(States.SHORE_OC_VAR, val);
		}
		
		public String getShoreICFile() {
			return get(States.SHORE_IC_FILE).toString();
		}
		
		public void setShoreICFile(String val) {
			set(States.SHORE_IC_FILE, Wc3String.valueOf(val));
		}
		
		public Real getShoreICVar() {
			return get(States.SHORE_IC_VAR);
		}

		public void setShoreICVar(Real val) {
			set(States.SHORE_IC_VAR, val);
		}
		
		public Color getSMinColor() {
			return Color.fromBGRA255(get(States.S_MIN_BLUE).toInt(), get(States.S_MIN_GREEN).toInt(), get(States.S_MIN_RED).toInt(), get(States.S_MIN_ALPHA).toInt());
		}
		
		public void setSMinColor(Color val) {
			set(States.S_MIN_ALPHA, Wc3Int.valueOf(val.getAlpha255()));
			set(States.S_MIN_BLUE, Wc3Int.valueOf(val.getBlue255()));
			set(States.S_MIN_GREEN, Wc3Int.valueOf(val.getGreen255()));
			set(States.S_MIN_RED, Wc3Int.valueOf(val.getRed255()));
		}

		public Color getSMaxColor() {
			return Color.fromBGRA255(get(States.S_MAX_BLUE).toInt(), get(States.S_MAX_GREEN).toInt(), get(States.S_MAX_RED).toInt(), get(States.S_MAX_ALPHA).toInt());
		}

		public void setSMaxColor(Color val) {
			set(States.S_MAX_ALPHA, Wc3Int.valueOf(val.getAlpha255()));
			set(States.S_MAX_BLUE, Wc3Int.valueOf(val.getBlue255()));
			set(States.S_MAX_GREEN, Wc3Int.valueOf(val.getGreen255()));
			set(States.S_MAX_RED, Wc3Int.valueOf(val.getRed255()));
		}
		
		public Color getDMinColor() {
			return Color.fromBGRA255(get(States.D_MIN_BLUE).toInt(), get(States.D_MIN_GREEN).toInt(), get(States.D_MIN_RED).toInt(), get(States.D_MIN_ALPHA).toInt());
		}
		
		public void setDMinColor(Color val) {
			set(States.D_MIN_ALPHA, Wc3Int.valueOf(val.getAlpha255()));
			set(States.D_MIN_BLUE, Wc3Int.valueOf(val.getBlue255()));
			set(States.D_MIN_GREEN, Wc3Int.valueOf(val.getGreen255()));
			set(States.D_MIN_RED, Wc3Int.valueOf(val.getRed255()));
		}
		
		public Color getDMaxColor() {
			return Color.fromBGRA255(get(States.D_MAX_BLUE).toInt(), get(States.D_MAX_GREEN).toInt(), get(States.D_MAX_RED).toInt(), get(States.D_MAX_ALPHA).toInt());
		}
		
		public void setDMaxColor(Color val) {
			set(States.D_MAX_ALPHA, Wc3Int.valueOf(val.getAlpha255()));
			set(States.D_MAX_BLUE, Wc3Int.valueOf(val.getBlue255()));
			set(States.D_MAX_GREEN, Wc3Int.valueOf(val.getGreen255()));
			set(States.D_MAX_RED, Wc3Int.valueOf(val.getRed255()));
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
			this(WaterId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(WaterId id) {
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
	
	//private Map<WaterId, Camera> _objs = new LinkedHashMap<>();
	
	@Override
	public Map<WaterId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Override
	public Obj addObj(WaterId id) {
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

			obj.merge(slkObj, true);
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
	
	public WaterSLK() {
		addField(States.OBJ_ID);
		
		for (States.State state : States.values()) {
			addField(state);
		}
	}

	@Override
	public Obj createObj(ObjId id) {
		return new Obj(WaterId.valueOf(id));
	}

	@Override
	public void merge(WaterSLK other, boolean overwrite) {
		// TODO Auto-generated method stub
		
	}
}
