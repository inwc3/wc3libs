package net.moonlightflower.wc3libs.slk.app.splats;

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
import net.moonlightflower.wc3libs.dataTypes.app.Color;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.LightningId;
import net.moonlightflower.wc3libs.dataTypes.app.Real;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.RawSLK;
import net.moonlightflower.wc3libs.slk.SLK;
import net.moonlightflower.wc3libs.slk.SLKState;

public class LightningSLK extends ObjSLK<LightningSLK, LightningId, LightningSLK.Obj> {
	public final static File GAME_USE_PATH = new File("Splats\\LightningData.slk");
	
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
		
		public final static State<LightningId> OBJ_ID = new State<>("Name", LightningId.class);
		
		public final static State<Real> ART_COLOR_ALPHA = new State<>("A", Real.class, Real.valueOf(1F));
		public final static State<Real> ART_COLOR_BLUE = new State<>("B", Real.class, Real.valueOf(1F));
		public final static State<Real> ART_COLOR_GREEN = new State<>("G", Real.class, Real.valueOf(1F));
		public final static State<Real> ART_COLOR_RED = new State<>("R", Real.class, Real.valueOf(1F));
		public final static State<Real> ART_DURATION = new State<>("Duration", Real.class, Real.valueOf(1F));
		public final static State<Real> ART_NOISE_SCALE = new State<>("NoiseScale", Real.class, Real.valueOf(0.02F));
		public final static State<Wc3String> ART_TEX_DIR = new State<>("Dir", Wc3String.class);
		public final static State<Wc3String> ART_TEX_FILE = new State<>("file", Wc3String.class);
		public final static State<Real> ART_SEG_LEN_AVG = new State<>("AvgSegLen", Real.class, Real.valueOf(10F));
		public final static State<Real> ART_TEX_COORD_SCALE = new State<>("TexCoordScale", Real.class);
		public final static State<Real> ART_WIDTH = new State<>("Width", Real.class, Real.valueOf(10F));
		
		public final static State<Int> EDITOR_VERSION = new State<>("version", Int.class);
	}
	
	public static class Obj extends SLK.Obj<LightningId> {
		public Path getTex() {
			return Paths.get(get(States.ART_TEX_DIR).getVal(), get(States.ART_TEX_FILE).getVal());
		}
		
		public void setTex(Path val) {
			set(States.ART_TEX_DIR, Wc3String.valueOf(val.getParent().toString()));
			set(States.ART_TEX_FILE, Wc3String.valueOf(val.getFileName().toString()));
		}
		
		public Real getAvgSegLen() {
			return get(States.ART_SEG_LEN_AVG);
		}
		
		public void setAvgSegLen(Real val) {
			set(States.ART_SEG_LEN_AVG, val);
		}
		
		public Real getWidth() {
			return get(States.ART_WIDTH);
		}
		
		public void setWidth(Real val) {
			set(States.ART_WIDTH, val);
		}
		
		public Color getColor() {
			return Color.fromBGRA255((int) get(States.ART_COLOR_BLUE).toFloat() * 255, (int) get(States.ART_COLOR_GREEN).toFloat() * 255, (int) get(States.ART_COLOR_RED).toFloat() * 255, (int) get(States.ART_COLOR_ALPHA).toFloat() * 255);
		}
		
		public void setColor(Color val) {
			set(States.ART_COLOR_ALPHA, Real.valueOf(((float) val.getAlpha()) / 255));
			set(States.ART_COLOR_BLUE, Real.valueOf(((float) val.getBlue()) / 255));
			set(States.ART_COLOR_GREEN, Real.valueOf(((float) val.getGreen()) / 255));
			set(States.ART_COLOR_RED, Real.valueOf(((float) val.getRed()) / 255));
		}
		
		public Real getNoiseScale() {
			return get(States.ART_NOISE_SCALE);
		}
		
		public void setNoiseScale(Real val) {
			set(States.ART_NOISE_SCALE, val);
		}
	
		public Real getTexCoordScale() {
			return get(States.ART_TEX_COORD_SCALE);
		}
		
		public void setTexCoordScale(Real val) {
			set(States.ART_TEX_COORD_SCALE, val);
		}
		
		public Real getDuration() {
			return get(States.ART_DURATION);
		}
		
		public void setDuration(Real val) {
			//assert (val > 0);

			set(States.ART_DURATION, val);
		}
		
		public <T extends DataType> T get(States.State<T> state) {
			return (T) get(state.getFieldId());
		}
		
		public <T extends DataType> void set(States.State<T> state, T val) {
			set(state.getFieldId(), val);
		}
		
		public <T extends DataType> void remove(States.State<T> state) {
			super.set(state, null);
		}
		
		private void read(SLK.Obj<? extends ObjId> slkObj) {
			merge(slkObj, true);
		}
		
		public Obj(SLK.Obj<? extends ObjId> slkObj) {
			this(LightningId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(LightningId id) {
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
	
	//private Map<LightningId, Obj> _objs = new LinkedHashMap<>();
	
	@Override
	public Map<LightningId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Override
	public Obj addObj(LightningId id) {
		if (_objs.containsKey(id)) return _objs.get(id);
		
		Obj obj = new Obj(id);
		
		addObj(obj);
		
		return obj;
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
	
	public LightningSLK() {
		addField(States.OBJ_ID);
		
		for (States.State state : States.values()) {
			addField(state);
		}
	}

	@Override
	public Obj createObj(ObjId id) {
		return new Obj(LightningId.valueOf(id));
	}

	@Override
	public void merge(LightningSLK other, boolean overwrite) {
		// TODO Auto-generated method stub
		
	}
}
