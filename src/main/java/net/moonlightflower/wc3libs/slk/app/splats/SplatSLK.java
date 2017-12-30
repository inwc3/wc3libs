package net.moonlightflower.wc3libs.slk.app.splats;

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
import net.moonlightflower.wc3libs.slk.RawSLK;
import net.moonlightflower.wc3libs.slk.SLK;
import net.moonlightflower.wc3libs.slk.SLKState;

import javax.annotation.Nonnull;

public class SplatSLK extends SLK<SplatSLK, SplatId, SplatSLK.Obj> {
	public final static File GAME_USE_PATH = new File("Splats\\SplatData.slk");
	
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
		
		public final static State<SplatId> OBJ_ID = new State<>("Name", SplatId.class);
		
		public final static State<BlendMode> ART_BLEND_MODE = new State<>("BlendMode", BlendMode.class);
		public final static State<Wc3Int> ART_COLOR_END_ALPHA = new State<>("EndA", Wc3Int.class, Wc3Int.valueOf(255));
		public final static State<Wc3Int> ART_COLOR_END_BLUE = new State<>("EndB", Wc3Int.class, Wc3Int.valueOf(255));
		public final static State<Wc3Int> ART_COLOR_END_GREEN = new State<>("EndG", Wc3Int.class, Wc3Int.valueOf(255));
		public final static State<Wc3Int> ART_COLOR_END_RED = new State<>("EndR", Wc3Int.class, Wc3Int.valueOf(255));
		public final static State<Wc3Int> ART_COLOR_MID_ALPHA = new State<>("MiddleA", Wc3Int.class, Wc3Int.valueOf(255));
		public final static State<Wc3Int> ART_COLOR_MID_BLUE = new State<>("MiddleB", Wc3Int.class, Wc3Int.valueOf(255));
		public final static State<Wc3Int> ART_COLOR_MID_GREEN = new State<>("MiddleG", Wc3Int.class, Wc3Int.valueOf(255));
		public final static State<Wc3Int> ART_COLOR_MID_RED = new State<>("MiddleR", Wc3Int.class, Wc3Int.valueOf(255));
		public final static State<Wc3Int> ART_COLOR_START_ALPHA = new State<>("StartA", Wc3Int.class, Wc3Int.valueOf(255));
		public final static State<Wc3Int> ART_COLOR_START_BLUE = new State<>("StartB", Wc3Int.class, Wc3Int.valueOf(255));
		public final static State<Wc3Int> ART_COLOR_START_GREEN = new State<>("StartG", Wc3Int.class, Wc3Int.valueOf(255));
		public final static State<Wc3Int> ART_COLOR_START_RED = new State<>("StartR", Wc3Int.class, Wc3Int.valueOf(255));
		public final static State<Wc3Int> ART_COLS = new State<>("Columns", Wc3Int.class);
		public final static State<Real> ART_DECAY = new State<>("Decay", Real.class);
		public final static State<Real> ART_DECAY_REPEAT = new State<>("DecayRepeat", Real.class);
		public final static State<Real> ART_DECAY_UV_END = new State<>("UVDecayEnd", Real.class);
		public final static State<Real> ART_DECAY_UV_START = new State<>("UVDecayStart", Real.class);
		public final static State<Wc3Int> ART_ROWS = new State<>("Rows", Wc3Int.class);
		public final static State<Real> ART_LIFESPAN = new State<>("Lifespan", Real.class);
		public final static State<Real> ART_LIFESPAN_REPEAT = new State<>("LifespanRepeat", Real.class);
		public final static State<Real> ART_LIFESPAN_UV_END = new State<>("UVLifespanEnd", Real.class);
		public final static State<Real> ART_LIFESPAN_UV_START = new State<>("UVLifespanStart", Real.class);
		public final static State<Real> ART_SCALE = new State<>("Scale", Real.class, Real.valueOf(1F));
		public final static State<Wc3String> ART_TEX_DIR = new State<>("Dir", Wc3String.class);
		public final static State<Wc3String> ART_TEX_FILE = new State<>("file", Wc3String.class);
		public final static State<WaterCode> ART_WATER = new State<>("Water", WaterCode.class);
		
		public final static State<Wc3String> EDITOR_COMMENT = new State<>("comment", Wc3String.class);
		
		public final static State<SoundLabel> SOUND_LABEL = new State<>("Sound", SoundLabel.class);
	}
	
	public static class Obj extends SLK.Obj<SplatId> {
		public Path getTex() {
			return Paths.get(get(States.ART_TEX_DIR).toString(), get(States.ART_TEX_FILE).toString());
		}
		
		public void setTex(Path val) {
			set(States.ART_TEX_DIR, Wc3String.valueOf(val.getParent().toString()));
			set(States.ART_TEX_FILE, Wc3String.valueOf(val.getFileName().toString()));
		}
		
		public Wc3Int getRows() {
			return get(States.ART_ROWS);
		}
		
		public void setRows(Wc3Int val) {
			set(States.ART_ROWS, val);
		}
		
		public Wc3Int getCols() {
			return get(States.ART_COLS);
		}
		
		public void setCols(Wc3Int val) {
			set(States.ART_COLS, val);
		}
		
		public BlendMode getBlendMode() {
			return get(States.ART_BLEND_MODE);
		}
		
		public void setBlendMode(BlendMode val) {
			set(States.ART_BLEND_MODE, val);
		}
		
		public Real getScale() {
			return get(States.ART_SCALE);
		}
		
		public void setScale(Real val) {
			set(States.ART_SCALE, val);
		}
		
		public Real getLifespan() { 
			return get(States.ART_LIFESPAN);
		}
		
		public void setLifespan(Real val) {
			set(States.ART_LIFESPAN, val);
		}
		
		public Real getLifespanRepeat() {
			return get(States.ART_LIFESPAN_REPEAT);
		}
		
		public Real getLifespanUVStart() {
			return get(States.ART_LIFESPAN_UV_START);
		}

		public Real getLifespanUVEnd() {
			return get(States.ART_LIFESPAN_UV_END);
		}
		
		public void setLifespanParams(Real repeatVal, Real startUV, Real endUV) {
			set(States.ART_LIFESPAN_REPEAT, repeatVal);
			set(States.ART_LIFESPAN_UV_END, endUV);
			set(States.ART_LIFESPAN_UV_START, startUV);
		}
		
		public Real getDecay() {
			return get(States.ART_DECAY);
		}

		public void setDecay(Real val) {
			set(States.ART_DECAY, val);
		}
		
		public Real getDecayRepeat() {
			return get(States.ART_DECAY_REPEAT);
		}
		
		public Real getDecayUVStart() {
			return get(States.ART_DECAY_UV_START);
		}

		public Real getDecayUVEnd() {
			return get(States.ART_DECAY_UV_END);
		}

		public void setDecayParams(Real repeatVal, Real startUV, Real endUV) {
			set(States.ART_DECAY_REPEAT, repeatVal);
			set(States.ART_DECAY_UV_END, endUV);
			set(States.ART_DECAY_UV_START, startUV);
		}
		
		public Color getColorStart() {
			return Color.fromBGRA255(get(States.ART_COLOR_START_BLUE).toInt(), get(States.ART_COLOR_START_GREEN).toInt(), get(States.ART_COLOR_START_RED).toInt(), get(States.ART_COLOR_START_ALPHA).toInt());
		}
		
		public void setColorStart(Color val) {
			set(States.ART_COLOR_START_ALPHA, Wc3Int.valueOf(val.getAlpha255()));
			set(States.ART_COLOR_START_BLUE, Wc3Int.valueOf(val.getBlue255()));
			set(States.ART_COLOR_START_GREEN, Wc3Int.valueOf(val.getGreen255()));
			set(States.ART_COLOR_START_RED, Wc3Int.valueOf(val.getRed255()));
		}
		
		public Color getColorMid() {
			return Color.fromBGRA255(get(States.ART_COLOR_MID_BLUE).toInt(), get(States.ART_COLOR_MID_GREEN).toInt(), get(States.ART_COLOR_MID_RED).toInt(), get(States.ART_COLOR_MID_ALPHA).toInt());
		}
		
		public void setColorMid(Color val) {
			set(States.ART_COLOR_MID_ALPHA, Wc3Int.valueOf(val.getAlpha255()));
			set(States.ART_COLOR_MID_BLUE, Wc3Int.valueOf(val.getBlue255()));
			set(States.ART_COLOR_MID_GREEN, Wc3Int.valueOf(val.getGreen255()));
			set(States.ART_COLOR_MID_RED, Wc3Int.valueOf(val.getRed255()));
		}
		
		public Color getColorEnd() {
			return Color.fromBGRA255(get(States.ART_COLOR_END_BLUE).toInt(), get(States.ART_COLOR_END_GREEN).toInt(), get(States.ART_COLOR_END_RED).toInt(), get(States.ART_COLOR_END_ALPHA).toInt());
		}
		
		public void setColorEnd(Color val) {
			set(States.ART_COLOR_END_ALPHA, Wc3Int.valueOf(val.getAlpha255()));
			set(States.ART_COLOR_END_BLUE, Wc3Int.valueOf(val.getBlue255()));
			set(States.ART_COLOR_END_GREEN, Wc3Int.valueOf(val.getGreen255()));
			set(States.ART_COLOR_END_RED, Wc3Int.valueOf(val.getRed255()));
		}
		
		public WaterCode getWater() {
			return get(States.ART_WATER);
		}
		
		public void setWater(WaterCode val) {
			set(States.ART_WATER, val);
		}
		
		public SoundLabel getSound() {
			return get(States.SOUND_LABEL);
		}
		
		public void setSound(SoundLabel val) {
			/*if (val.equals("NULL")) {
				val = null;
			}*/
			
			set(States.SOUND_LABEL, val);
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
		
		public Obj(SLK.Obj<? extends ObjId> slkObj) {
			this(SplatId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(SplatId id) {
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
	
	//private Map<SplatId, Camera> _objs = new LinkedHashMap<>();
	
	@Nonnull
	@Override
	public Map<SplatId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(@Nonnull Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Nonnull
	@Override
	public Obj addObj(@Nonnull SplatId id) {
		if (_objs.containsKey(id)) return _objs.get(id);
		
		Obj obj = new Obj(id);
		
		addObj(obj);
		
		return obj;
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
	
	private RawSLK toSlk() {
		RawSLK slk = new RawSLK();

		for (Obj obj : getObjs().values()) {
			ObjId id = obj.getId();

			SLK.Obj<ObjId> slkObj = slk.addObj(id);

			slkObj.merge(obj, true);

			if (obj.getSound() == null) {
				slkObj.set(States.SOUND_LABEL, SoundLabel.valueOf("NULL"));
			}
		}
		
		return slk;
	}
	
	@Override
	public void read(File file) throws IOException {
		read(new RawSLK(file));
	}
	
	public SplatSLK() {
		addField(States.OBJ_ID);
		
		for (States.State state : States.values()) {
			addField(state);
		}
	}

	@Nonnull
	@Override
	public Obj createObj(@Nonnull ObjId id) {
		return new Obj(SplatId.valueOf(id));
	}

	@Override
	public void merge(@Nonnull SplatSLK other, boolean overwrite) {
		// TODO Auto-generated method stub
		
	}
}
