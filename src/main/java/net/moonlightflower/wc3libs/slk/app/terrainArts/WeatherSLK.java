package net.moonlightflower.wc3libs.slk.app.terrainArts;

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
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;

public class WeatherSLK extends ObjSLK<WeatherSLK, WeatherId, WeatherSLK.Obj> {
	public final static File GAME_PATH = new File("TerrainArt\\Weather.slk");

	public static class State<T extends DataType> extends ObjSLK.State<T> {
		public final static State<WeatherId> OBJ_ID = new State<>("effectID", WeatherId.class);

		public final static State<AlphaMode> ART_ALPHA_MODE = new State<>("alphaMode", AlphaMode.class);
		public final static State<War3Real> ART_ANGLE_X = new State<>("angx", War3Real.class, War3Real.valueOf(-50F));
		public final static State<War3Real> ART_ANGLE_Y = new State<>("angy", War3Real.class, War3Real.valueOf(50F));
		public final static State<War3Real> ART_EMISSION_RATE = new State<>("emrate", War3Real.class, War3Real.valueOf(10F));
		public final static State<War3Int> ART_END_ALPHA = new State<>("alphaEnd", War3Int.class, War3Int.valueOf(0));
		public final static State<War3Int> ART_END_BLUE = new State<>("blueEnd", War3Int.class, War3Int.valueOf(0));
		public final static State<War3Int> ART_END_GREEN = new State<>("greenEnd", War3Int.class, War3Int.valueOf(0));
		public final static State<War3Int> ART_END_RED = new State<>("redEnd", War3Int.class, War3Int.valueOf(0));
		public final static State<War3Real> ART_END_SCALE = new State<>("scaleEnd", War3Real.class, War3Real.valueOf(100F));
		public final static State<War3Real> ART_END_UV_H = new State<>("hUVEnd", War3Real.class, War3Real.valueOf(0F));
		public final static State<War3Real> ART_END_UV_T = new State<>("tUVEnd", War3Real.class, War3Real.valueOf(0F));
		public final static State<War3Real> ART_HEIGHT = new State<>("height", War3Real.class, War3Real.valueOf(100F));
		public final static State<War3Real> ART_LATITUDE = new State<>("lati", War3Real.class, War3Real.valueOf(2.5F));
		public final static State<War3Real> ART_LIFESPAN = new State<>("lifespan", War3Real.class, War3Real.valueOf(5F));
		public final static State<War3Real> ART_LONGITUDE = new State<>("long", War3Real.class, War3Real.valueOf(180F));
		public final static State<War3Int> ART_MID_ALPHA = new State<>("alphaMid", War3Int.class, War3Int.valueOf(127));
		public final static State<War3Int> ART_MID_BLUE = new State<>("blueMid", War3Int.class, War3Int.valueOf(127));
		public final static State<War3Int> ART_MID_GREEN = new State<>("greenMid", War3Int.class, War3Int.valueOf(127));
		public final static State<War3Int> ART_MID_RED = new State<>("redMid", War3Int.class, War3Int.valueOf(127));
		public final static State<War3Real> ART_MID_SCALE = new State<>("scaleMid", War3Real.class, War3Real.valueOf(100F));
		public final static State<War3Real> ART_MID_TIME = new State<>("midTime", War3Real.class, War3Real.valueOf(0.5F));
		public final static State<War3Real> ART_MID_UV_H = new State<>("hUVMid", War3Real.class, War3Real.valueOf(0F));
		public final static State<War3Real> ART_MID_UV_T = new State<>("tUVMid", War3Real.class, War3Real.valueOf(0F));
		public final static State<War3Int> ART_PARTICLES = new State<>("particles", War3Int.class, War3Int.valueOf(1000));
		public final static State<War3Real> ART_SPEED = new State<>("veloc", War3Real.class, War3Real.valueOf(-100F));
		public final static State<War3Real> ART_SPEED_ACCEL = new State<>("accel", War3Real.class, War3Real.valueOf(0F));
		public final static State<War3Int> ART_START_ALPHA = new State<>("alphaStart", War3Int.class, War3Int.valueOf(255));
		public final static State<War3Int> ART_START_BLUE = new State<>("blueStart", War3Int.class, War3Int.valueOf(255));
		public final static State<War3Int> ART_START_GREEN = new State<>("greenStart", War3Int.class, War3Int.valueOf(255));
		public final static State<War3Int> ART_START_RED = new State<>("redStart", War3Int.class, War3Int.valueOf(255));
		public final static State<War3Real> ART_START_SCALE = new State<>("scaleStart", War3Real.class, War3Real.valueOf(100F));
		public final static State<War3Real> ART_START_UV_H = new State<>("hUVStart", War3Real.class, War3Real.valueOf(0F));
		public final static State<War3Real> ART_START_UV_T = new State<>("tUVStart", War3Real.class, War3Real.valueOf(0F));
		public final static State<War3Real> ART_TAIL_LEN = new State<>("tailLen", War3Real.class, War3Real.valueOf(1F));
		public final static State<War3Real> ART_TEX_C = new State<>("texc", War3Real.class, War3Real.valueOf(10F));
		public final static State<War3String> ART_TEX_DIR = new State<>("Dir", War3String.class);
		public final static State<War3String> ART_TEX_FILE = new State<>("file", War3String.class);
		public final static State<War3Real> ART_TEX_R = new State<>("texr", War3Real.class, War3Real.valueOf(10F));
		public final static State<War3Bool> ART_USE_FOG = new State<>("useFog", War3Bool.class, War3Bool.valueOf(true));
		public final static State<War3Bool> ART_USE_HEAD = new State<>("head", War3Bool.class, War3Bool.valueOf(true));
		public final static State<War3Bool> ART_USE_TAIL = new State<>("tail", War3Bool.class, War3Bool.valueOf(true));
		public final static State<War3Real> ART_VARIANCE = new State<>("var", War3Real.class, War3Real.valueOf(0.05F));

		public final static State<War3Int> EDITOR_VERSION = new State<>("version", War3Int.class, War3Int.valueOf(0));

		public final static State<SoundLabel> SOUND_AMBIENT = new State<>("AmbientSound", SoundLabel.class);

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
	
	public static class Obj extends SLK.Obj<WeatherId> {
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
			return Paths.get(get(State.ART_TEX_DIR).getVal(), get(State.ART_TEX_FILE).getVal());
		}
		
		public void setTex(Path path) {
			set(State.ART_TEX_DIR, War3String.valueOf(path.getParent().toString()));
			set(State.ART_TEX_FILE, War3String.valueOf(path.getFileName().toString()));
		}
		
		public AlphaMode getAlphaMode() {
			return get(State.ART_ALPHA_MODE);
		}
		
		public void setAlphaMode(WeatherId val) {
			set(State.ART_ALPHA_MODE, val);
		}
		
		public War3Bool getUseFog() {
			return get(State.ART_USE_FOG);
		}
		
		public void setUseFog(War3Bool val) {
			set(State.ART_USE_FOG, val);
		}
		
		public War3Real getHeight() {
			return get(State.ART_HEIGHT);
		}
		
		public void setHeight(War3Real val) {
			set(State.ART_HEIGHT, val);
		}
		
		public War3Real getAngX() {
			return get(State.ART_ANGLE_X);
		}
		
		public War3Real getAngY() {
			return get(State.ART_ANGLE_Y);
		}
		
		public void setAng(War3Real x, War3Real y) {
			set(State.ART_ANGLE_X, x);
			set(State.ART_ANGLE_Y, y);
		}
		
		public War3Real getEmissionRate() {
			return get(State.ART_EMISSION_RATE);
		}
		
		public void setEmissionRate(War3Real val) {
			set(State.ART_EMISSION_RATE, val);
		}
		
		public War3Real getLifespan() {
			return get(State.ART_LIFESPAN);
		}
		
		public void setLifespan(War3Real val) {
			set(State.ART_LIFESPAN, val);
		}
		
		public War3Int getParticles() {
			return get(State.ART_PARTICLES);
		}
		
		public void setParticles(War3Int val) {
			set(State.ART_PARTICLES, val);
		}
		
		public War3Real getSpeed() {
			return get(State.ART_SPEED);
		}
		
		public War3Real getAccel() {
			return get(State.ART_SPEED_ACCEL);
		}
		
		public void setSpeed(War3Real speed, War3Real accel) {
			set(State.ART_SPEED, speed);
			set(State.ART_SPEED_ACCEL, accel);
		}

		public War3Real getVariance() {
			return get(State.ART_VARIANCE);
		}
		
		public void setVariance(War3Real val) {
			set(State.ART_VARIANCE, val);
		}
		
		public War3Real getTexC() {
			return get(State.ART_TEX_C);
		}
		
		public War3Real getTexR() {
			return get(State.ART_TEX_R);
		}
		
		public void setTexOffsets(War3Real texC, War3Real texR) {
			set(State.ART_TEX_C, texC);
			set(State.ART_TEX_R, texR);
		}
		
		public War3Bool getHead() {
			return get(State.ART_USE_HEAD);
		}
		
		public void setHead(War3Bool val) {
			set(State.ART_USE_HEAD, val);
		}
		
		public War3Bool getTail() {
			return get(State.ART_USE_TAIL);
		}
		
		public War3Real getTailLen() {
			return get(State.ART_TAIL_LEN);
		}

		public void setTail(War3Bool val, War3Real len) {
			set(State.ART_USE_TAIL, val);
			set(State.ART_TAIL_LEN, len);
		}
		
		public War3Real getLatitude() {
			return get(State.ART_LATITUDE);
		}
		
		public void setLatitude(War3Real val) {
			set(State.ART_LATITUDE, val);
		}
		
		public War3Real getLongitude() {
			return get(State.ART_LONGITUDE);
		}
		
		public void setLongitude(War3Real val) {
			set(State.ART_LONGITUDE, val);
		}
		
		public War3Real getMidTime() {
			return get(State.ART_MID_TIME);
		}
		
		public void setMidTime(War3Real val) {
			set(State.ART_MID_TIME, val);
		}
	
		public Color getColorStart() {
			return Color.fromBGRA255(get(State.ART_START_BLUE).toInt(), get(State.ART_START_GREEN).toInt(), get(State.ART_START_RED).toInt(), get(State.ART_START_ALPHA).toInt());
		}
		
		public void setColorStart(Color val) {
			set(State.ART_START_RED, War3Int.valueOf(val.getRed255()));
			set(State.ART_START_GREEN, War3Int.valueOf(val.getGreen255()));
			set(State.ART_START_BLUE, War3Int.valueOf(val.getBlue255()));
			set(State.ART_START_ALPHA, War3Int.valueOf(val.getAlpha255()));
		}
		
		public Color getColorMid() {
			return Color.fromBGRA255(get(State.ART_MID_BLUE).toInt(), get(State.ART_MID_GREEN).toInt(), get(State.ART_MID_RED).toInt(), get(State.ART_MID_ALPHA).toInt());
		}
		
		public void setColorMid(Color val) {
			set(State.ART_MID_RED, War3Int.valueOf(val.getRed255()));
			set(State.ART_MID_GREEN, War3Int.valueOf(val.getGreen255()));
			set(State.ART_MID_BLUE, War3Int.valueOf(val.getBlue255()));
			set(State.ART_MID_ALPHA, War3Int.valueOf(val.getAlpha255()));
		}
		
		public Color getColorEnd() {
			return Color.fromBGRA255(get(State.ART_END_BLUE).toInt(), get(State.ART_END_GREEN).toInt(), get(State.ART_END_RED).toInt(), get(State.ART_END_ALPHA).toInt());
		}
		
		public void setColorEnd(Color val) {
			set(State.ART_END_RED, War3Int.valueOf(val.getRed255()));
			set(State.ART_END_GREEN, War3Int.valueOf(val.getGreen255()));
			set(State.ART_END_BLUE, War3Int.valueOf(val.getBlue255()));
			set(State.ART_END_ALPHA, War3Int.valueOf(val.getAlpha255()));
		}
		
		public War3Real getScaleStart() {
			return get(State.ART_START_SCALE);
		}
		
		public War3Real getScaleMid() {
			return get(State.ART_MID_SCALE);
		}
		
		public War3Real getScaleEnd() {
			return get(State.ART_END_SCALE);
		}
		
		public void setScale(War3Real valStart, War3Real valMid, War3Real valEnd) {
			set(State.ART_START_SCALE, valStart);
			set(State.ART_MID_SCALE, valMid);
			set(State.ART_END_SCALE, valEnd);
		}
		
		public War3Real getHUVStart() {
			return get(State.ART_START_UV_H);
		}

		public War3Real getHUVMid() {
			return get(State.ART_MID_UV_H);
		}

		public War3Real getHUVEnd() {
			return get(State.ART_END_UV_H);
		}
		
		public void setHUV(War3Real valStart, War3Real valMid, War3Real valEnd) {
			set(State.ART_START_UV_H, valStart);
			set(State.ART_MID_UV_H, valMid);
			set(State.ART_END_UV_H, valEnd);
		}

		public War3Real getTUVStart() {
			return get(State.ART_START_UV_T);
		}

		public War3Real getTUVMid() {
			return get(State.ART_MID_UV_T);
		}

		public War3Real getTUVEnd() {
			return get(State.ART_END_UV_T);
		}
		
		public void setTUV(War3Real valStart, War3Real valMid, War3Real valEnd) {
			set(State.ART_START_UV_T, valStart);
			set(State.ART_MID_UV_T, valMid);
			set(State.ART_END_UV_T, valEnd);
		}
		
		public SoundLabel getSound() {
			return get(State.SOUND_AMBIENT);
		}
		
		public void setSound(SoundLabel val) {
			set(State.SOUND_AMBIENT, val);
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
		
		@Override
		public void reduce() {
			if ((get(State.SOUND_AMBIENT) != null) && get(State.SOUND_AMBIENT).equals("-")) remove(State.SOUND_AMBIENT);
		}
		
		private void read(SLK.Obj<? extends ObjId> otherObj) {
			for (State<?> state : State.values(State.class)) {
				FieldId field = state.getFieldId();

				try {
					set(field, state.tryCastVal(otherObj.get(field)));
				} catch (DataTypeInfo.CastException ignored) {
				}
			}
		}
		
		public Obj(SLK.Obj<? extends ObjId> otherObj) {
			super(WeatherId.valueOf(otherObj.getId()));
			
			read(otherObj);
			
			reduce();
		}
		
		public Obj(WeatherId id) {
			super(id);

			for (State<?> state : State.values(State.class)) {
				set(state, state.getDefVal());
			}
		}
	}
	
	//private Map<WeatherId, Camera> _objs = new LinkedHashMap<>();
	
	@Nonnull
	@Override
	public Map<WeatherId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(@Nonnull Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Nonnull
	@Override
	public Obj createObj(@Nonnull ObjId id) {
		return new Obj(WeatherId.valueOf(id));
	}
	
	@Nonnull
	@Override
	public Obj addObj(@Nonnull WeatherId id) {
		if (_objs.containsKey(id)) return _objs.get(id);
		
		Obj obj = new Obj(id);
		
		addObj(obj);
		
		return obj;
	}
	
	@Override
	public void merge(@Nonnull WeatherSLK other, boolean overwrite) {
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
	protected void read(@Nonnull SLK<?, ? extends ObjId, ? extends SLK.Obj<? extends ObjId>> slk) {
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
	public void read(@Nonnull File file) throws IOException {
		read(new RawSLK(file));
		read(this);
	}
	
	@Override
	public void write(@Nonnull File file) throws IOException {
		WeatherSLK other = new WeatherSLK();
		
		other.merge(this);
		
//		other.reduce();
//
//		other.write(file);
	}
	
	public WeatherSLK(SLK slk) {
		this();
		
		read(slk);
		
//		reduce();
	}
	
	public WeatherSLK(File file) throws IOException {
		this();
		
		read(new RawSLK(file));
	}

	public void toJSON() {
		/*JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

		for (State state : State.values(State.class)) {
			state.
		}

		objectBuilder.add()*/
	}

	public WeatherSLK() {
		super();
		
		addField(State.OBJ_ID);
		
		for (State<?> state : State.values(State.class)) {
			addField(state);
		}
	}
}
