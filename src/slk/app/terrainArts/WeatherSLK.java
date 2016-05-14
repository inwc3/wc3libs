package slk.app.terrainArts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dataTypes.DataType;
import dataTypes.app.AlphaMode;
import dataTypes.app.Bool;
import dataTypes.app.Color;
import dataTypes.app.Int;
import dataTypes.app.Real;
import dataTypes.app.SoundLabel;
import dataTypes.app.Wc3String;
import dataTypes.app.WeatherId;
import misc.FieldId;
import misc.ObjId;
import misc.StateInfo;
import slk.ObjSLK;
import slk.RawSLK;
import slk.SLK;
import slk.SLKState;

public class WeatherSLK extends ObjSLK<WeatherSLK, WeatherId, WeatherSLK.Obj> {
	public final static File GAME_USE_PATH = new File("TerrainArt\\Weather.slk");
	
	static public class States {
		static public class State<T extends DataType> extends SLKState<T> {
			private static List<State> _values = new ArrayList<>();
			
			public static List<State> values() {
				return _values;
			}
			
			public State(String idString, StateInfo typeInfo, T defVal) {
				super(idString, typeInfo, defVal);
				
				_values.add(this);
			}
			
			public State(String idString, StateInfo typeInfo) {
				this(idString, typeInfo, null);
			}

			public State(String idString, Class<T> type) {
				this(idString, StateInfo.valueOf(type));
			}
			
			public State(String idString, Class<T> type, T defVal) {
				this(idString, StateInfo.valueOf(type), defVal);
			}
		}
		
		public static List<State> values() {
			return State.values();
		}

		public final static State<WeatherId> OBJ_ID = new State<>("effectID", WeatherId.class);
		
		public final static State<AlphaMode> ART_ALPHA_MODE = new State<>("alphaMode", AlphaMode.class);
		public final static State<Real> ART_ANGLE_X = new State<>("angx", Real.class, Real.valueOf(-50F));
		public final static State<Real> ART_ANGLE_Y = new State<>("angy", Real.class, Real.valueOf(50F));
		public final static State<Real> ART_EMISSION_RATE = new State<>("emrate", Real.class, Real.valueOf(10F));
		public final static State<Int> ART_END_ALPHA = new State<>("alphaEnd", Int.class, Int.valueOf(0));
		public final static State<Int> ART_END_BLUE = new State<>("blueEnd", Int.class, Int.valueOf(0));
		public final static State<Int> ART_END_GREEN = new State<>("greenEnd", Int.class, Int.valueOf(0));
		public final static State<Int> ART_END_RED = new State<>("redEnd", Int.class, Int.valueOf(0));
		public final static State<Real> ART_END_SCALE = new State<>("scaleEnd", Real.class, Real.valueOf(100F));
		public final static State<Real> ART_END_UV_H = new State<>("hUVEnd", Real.class, Real.valueOf(0F));
		public final static State<Real> ART_END_UV_T = new State<>("tUVEnd", Real.class, Real.valueOf(0F));
		public final static State<Real> ART_HEIGHT = new State<>("height", Real.class, Real.valueOf(100F));
		public final static State<Real> ART_LATITUDE = new State<>("lati", Real.class, Real.valueOf(2.5F));
		public final static State<Real> ART_LIFESPAN = new State<>("lifespan", Real.class, Real.valueOf(5F));
		public final static State<Real> ART_LONGITUDE = new State<>("long", Real.class, Real.valueOf(180F));
		public final static State<Int> ART_MID_ALPHA = new State<>("alphaMid", Int.class, Int.valueOf(127));
		public final static State<Int> ART_MID_BLUE = new State<>("blueMid", Int.class, Int.valueOf(127));
		public final static State<Int> ART_MID_GREEN = new State<>("greenMid", Int.class, Int.valueOf(127));
		public final static State<Int> ART_MID_RED = new State<>("redMid", Int.class, Int.valueOf(127));
		public final static State<Real> ART_MID_SCALE = new State<>("scaleMid", Real.class, Real.valueOf(100F));
		public final static State<Real> ART_MID_TIME = new State<>("midTime", Real.class, Real.valueOf(0.5F));
		public final static State<Real> ART_MID_UV_H = new State<>("hUVMid", Real.class, Real.valueOf(0F));
		public final static State<Real> ART_MID_UV_T = new State<>("tUVMid", Real.class, Real.valueOf(0F));
		public final static State<Int> ART_PARTICLES = new State<>("particles", Int.class, Int.valueOf(1000));
		public final static State<Real> ART_SPEED = new State<>("veloc", Real.class, Real.valueOf(-100F));
		public final static State<Real> ART_SPEED_ACCEL = new State<>("accel", Real.class, Real.valueOf(0F));
		public final static State<Int> ART_START_ALPHA = new State<>("alphaStart", Int.class, Int.valueOf(255));
		public final static State<Int> ART_START_BLUE = new State<>("blueStart", Int.class, Int.valueOf(255));
		public final static State<Int> ART_START_GREEN = new State<>("greenStart", Int.class, Int.valueOf(255));
		public final static State<Int> ART_START_RED = new State<>("redStart", Int.class, Int.valueOf(255));
		public final static State<Real> ART_START_SCALE = new State<>("scaleStart", Real.class, Real.valueOf(100F));
		public final static State<Real> ART_START_UV_H = new State<>("hUVStart", Real.class, Real.valueOf(0F));
		public final static State<Real> ART_START_UV_T = new State<>("tUVStart", Real.class, Real.valueOf(0F));
		public final static State<Real> ART_TAIL_LEN = new State<>("tailLen", Real.class, Real.valueOf(1F));
		public final static State<Real> ART_TEX_C = new State<>("texc", Real.class, Real.valueOf(10F));
		public final static State<Wc3String> ART_TEX_DIR = new State<>("Dir", Wc3String.class);
		public final static State<Wc3String> ART_TEX_FILE = new State<>("file", Wc3String.class);
		public final static State<Real> ART_TEX_R = new State<>("texr", Real.class, Real.valueOf(10F));
		public final static State<Bool> ART_USE_FOG = new State<>("useFog", Bool.class, Bool.valueOf(true));
		public final static State<Bool> ART_USE_HEAD = new State<>("head", Bool.class, Bool.valueOf(true));
		public final static State<Bool> ART_USE_TAIL = new State<>("tail", Bool.class, Bool.valueOf(true));
		public final static State<Real> ART_VARIANCE = new State<>("var", Real.class, Real.valueOf(0.05F));
		
		public final static State<Int> EDITOR_VERSION = new State<>("version", Int.class, Int.valueOf(0));
		
		public final static State<SoundLabel> SOUND_AMBIENT = new State<>("AmbientSound", SoundLabel.class);
	}
	
	public static class Obj extends SLK.Obj<WeatherId> {
		public Path getTex() {
			return Paths.get(get(States.ART_TEX_DIR).getVal(), get(States.ART_TEX_FILE).getVal());
		}
		
		public void setTex(Path path) {
			set(States.ART_TEX_DIR, Wc3String.valueOf(path.getParent().toString()));
			set(States.ART_TEX_FILE, Wc3String.valueOf(path.getFileName().toString()));
		}
		
		public AlphaMode getAlphaMode() {
			return get(States.ART_ALPHA_MODE);
		}
		
		public void setAlphaMode(WeatherId val) {
			set(States.ART_ALPHA_MODE, val);
		}
		
		public Bool getUseFog() {
			return get(States.ART_USE_FOG);
		}
		
		public void setUseFog(Bool val) {
			set(States.ART_USE_FOG, val);
		}
		
		public Real getHeight() {
			return get(States.ART_HEIGHT);
		}
		
		public void setHeight(Real val) {
			set(States.ART_HEIGHT, val);
		}
		
		public Real getAngX() {
			return get(States.ART_ANGLE_X);
		}
		
		public Real getAngY() {
			return get(States.ART_ANGLE_Y);
		}
		
		public void setAng(Real x, Real y) {
			set(States.ART_ANGLE_X, x);
			set(States.ART_ANGLE_Y, y);
		}
		
		public Real getEmissionRate() {
			return get(States.ART_EMISSION_RATE);
		}
		
		public void setEmissionRate(Real val) {
			set(States.ART_EMISSION_RATE, val);
		}
		
		public Real getLifespan() {
			return get(States.ART_LIFESPAN);
		}
		
		public void setLifespan(Real val) {
			set(States.ART_LIFESPAN, val);
		}
		
		public Int getParticles() {
			return get(States.ART_PARTICLES);
		}
		
		public void setParticles(Int val) {
			set(States.ART_PARTICLES, val);
		}
		
		public Real getSpeed() {
			return get(States.ART_SPEED);
		}
		
		public Real getAccel() {
			return get(States.ART_SPEED_ACCEL);
		}
		
		public void setSpeed(Real speed, Real accel) {
			set(States.ART_SPEED, speed);
			set(States.ART_SPEED_ACCEL, accel);
		}

		public Real getVariance() {
			return get(States.ART_VARIANCE);
		}
		
		public void setVariance(Real val) {
			set(States.ART_VARIANCE, val);
		}
		
		public Real getTexC() {
			return get(States.ART_TEX_C);
		}
		
		public Real getTexR() {
			return get(States.ART_TEX_R);
		}
		
		public void setTexOffsets(Real texC, Real texR) {
			set(States.ART_TEX_C, texC);
			set(States.ART_TEX_R, texR);
		}
		
		public Bool getHead() {
			return get(States.ART_USE_HEAD);
		}
		
		public void setHead(Bool val) {
			set(States.ART_USE_HEAD, val);
		}
		
		public Bool getTail() {
			return get(States.ART_USE_TAIL);
		}
		
		public Real getTailLen() {
			return get(States.ART_TAIL_LEN);
		}

		public void setTail(Bool val, Real len) {
			set(States.ART_USE_TAIL, val);
			set(States.ART_TAIL_LEN, len);
		}
		
		public Real getLatitude() {
			return get(States.ART_LATITUDE);
		}
		
		public void setLatitude(Real val) {
			set(States.ART_LATITUDE, val);
		}
		
		public Real getLongitude() {
			return get(States.ART_LONGITUDE);
		}
		
		public void setLongitude(Real val) {
			set(States.ART_LONGITUDE, val);
		}
		
		public Real getMidTime() {
			return get(States.ART_MID_TIME);
		}
		
		public void setMidTime(Real val) {
			set(States.ART_MID_TIME, val);
		}
	
		public Color getColorStart() {
			return Color.fromBGRA(get(States.ART_START_BLUE).toInt(), get(States.ART_START_GREEN).toInt(), get(States.ART_START_RED).toInt(), get(States.ART_START_ALPHA).toInt());
		}
		
		public void setColorStart(Color val) {
			set(States.ART_START_RED, Int.valueOf(val.getRed()));
			set(States.ART_START_GREEN, Int.valueOf(val.getGreen()));
			set(States.ART_START_BLUE, Int.valueOf(val.getBlue()));
			set(States.ART_START_ALPHA, Int.valueOf(val.getAlpha()));
		}
		
		public Color getColorMid() {
			return Color.fromBGRA(get(States.ART_MID_BLUE).toInt(), get(States.ART_MID_GREEN).toInt(), get(States.ART_MID_RED).toInt(), get(States.ART_MID_ALPHA).toInt());
		}
		
		public void setColorMid(Color val) {
			set(States.ART_MID_RED, Int.valueOf(val.getRed()));
			set(States.ART_MID_GREEN, Int.valueOf(val.getGreen()));
			set(States.ART_MID_BLUE, Int.valueOf(val.getBlue()));
			set(States.ART_MID_ALPHA, Int.valueOf(val.getAlpha()));
		}
		
		public Color getColorEnd() {
			return Color.fromBGRA(get(States.ART_END_BLUE).toInt(), get(States.ART_END_GREEN).toInt(), get(States.ART_END_RED).toInt(), get(States.ART_END_ALPHA).toInt());
		}
		
		public void setColorEnd(Color val) {
			set(States.ART_END_RED, Int.valueOf(val.getRed()));
			set(States.ART_END_GREEN, Int.valueOf(val.getGreen()));
			set(States.ART_END_BLUE, Int.valueOf(val.getBlue()));
			set(States.ART_END_ALPHA, Int.valueOf(val.getAlpha()));
		}
		
		public Real getScaleStart() {
			return get(States.ART_START_SCALE);
		}
		
		public Real getScaleMid() {
			return get(States.ART_MID_SCALE);
		}
		
		public Real getScaleEnd() {
			return get(States.ART_END_SCALE);
		}
		
		public void setScale(Real valStart, Real valMid, Real valEnd) {
			set(States.ART_START_SCALE, valStart);
			set(States.ART_MID_SCALE, valMid);
			set(States.ART_END_SCALE, valEnd);
		}
		
		public Real getHUVStart() {
			return get(States.ART_START_UV_H);
		}

		public Real getHUVMid() {
			return get(States.ART_MID_UV_H);
		}

		public Real getHUVEnd() {
			return get(States.ART_END_UV_H);
		}
		
		public void setHUV(Real valStart, Real valMid, Real valEnd) {
			set(States.ART_START_UV_H, valStart);
			set(States.ART_MID_UV_H, valMid);
			set(States.ART_END_UV_H, valEnd);
		}

		public Real getTUVStart() {
			return get(States.ART_START_UV_T);
		}

		public Real getTUVMid() {
			return get(States.ART_MID_UV_T);
		}

		public Real getTUVEnd() {
			return get(States.ART_END_UV_T);
		}
		
		public void setTUV(Real valStart, Real valMid, Real valEnd) {
			set(States.ART_START_UV_T, valStart);
			set(States.ART_MID_UV_T, valMid);
			set(States.ART_END_UV_T, valEnd);
		}
		
		public SoundLabel getSound() {
			return get(States.SOUND_AMBIENT);
		}
		
		public void setSound(SoundLabel val) {
			set(States.SOUND_AMBIENT, val);
		}
		
		public <T extends DataType> T get(States.State<T> state) {
			System.out.println("get " + state);
			
			return state.tryCastVal(super.get(state));
		}
		
		public <T extends DataType> void set(States.State<T> state, T val) {
			super.set(state, val);
		}
		
		public <T extends DataType> void remove(States.State<T> state) {
			super.set(state, null);
		}
		
		@Override
		public void reduce() {
			System.out.println("reduce " + get(States.SOUND_AMBIENT));
			
			if ((get(States.SOUND_AMBIENT) != null) && get(States.SOUND_AMBIENT).equals("-")) remove(States.SOUND_AMBIENT);
		}
		
		private void read(SLK.Obj<? extends ObjId> otherObj) {
			for (States.State state : States.State.values()) {
				FieldId field = state.getFieldId();
				
				set(field, state.tryCastVal(otherObj.get(field)));
			}
		}
		
		public Obj(SLK.Obj<? extends ObjId> otherObj) {
			this(WeatherId.valueOf(otherObj.getId()));
			
			read(otherObj);
			
			reduce();
		}
		
		public Obj(WeatherId id) {
			super(id);

			for (States.State state : States.values()) {
				set(state, state.getDefVal());
			}
		}
	}
	
	private Map<WeatherId, Obj> _objs = new HashMap<>();
	
	@Override
	public Map<WeatherId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Override
	public Obj createObj(ObjId id) {
		return new Obj(WeatherId.valueOf(id));
	}
	
	@Override
	public Obj addObj(WeatherId id) {
		Obj obj = new Obj(id);
		
		addObj(obj);
		
		return obj;
	}
	
	@Override
	public void merge(WeatherSLK other, boolean overwrite) {
		for (Obj otherObj : other.getObjs().values()) {
			WeatherId objId = otherObj.getId();
			
			Obj obj = addObj(objId);
			
			obj.merge(otherObj, true);
		}
	}

	private void reduce() {
		for (Obj obj : getObjs().values()) {
			obj.reduce();
		}
	}
	
	@Override
	protected void read(SLK<?, ? extends ObjId, ? extends SLK.Obj<? extends ObjId>> slk) {
		WeatherSLK other = new WeatherSLK();
		
		System.out.println("read " + slk.getObjs().size());
		
		for (Entry<? extends ObjId, ? extends SLK.Obj<? extends ObjId>> slkEntry : slk.getObjs().entrySet()) {
			ObjId id = slkEntry.getKey();
			SLK.Obj<? extends ObjId> slkObj = slkEntry.getValue();
			
			Obj obj = new Obj(slkObj);
			
			other.addObj(obj);
			
			obj.merge(slkObj);
		}
		
		merge(other);
	}

	@Override
	public void read(File file) throws IOException {
		read(new RawSLK(file));
		read(this);
	}
	
	@Override
	public void write(File file) throws IOException {
		WeatherSLK other = new WeatherSLK();
		
		other.merge(this);
		
		other.reduce();
		
		other.write(file);
	}
	
	public WeatherSLK(SLK slk) {
		this();
		
		read(slk);
		
		reduce();
	}
	
	public WeatherSLK(File file) throws IOException {
		this();
		
		read(new RawSLK(file));
	}
	
	public WeatherSLK() {
		super();
		
		addField(States.OBJ_ID);
		
		for (SLKState state : States.values()) {
			addField(state);
		}
	}
}
