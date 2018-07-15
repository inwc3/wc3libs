package net.moonlightflower.wc3libs.slk.app.splats;

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

public class LightningSLK extends ObjSLK<LightningSLK, LightningId, LightningSLK.Obj> {
	public final static File GAME_PATH = new File("Splats\\LightningData.slk");

	public static class State<T extends DataType> extends ObjSLK.State<T> {
		public final static State<LightningId> OBJ_ID = new State<>("Name", LightningId.class);

		public final static State<War3Real> ART_COLOR_ALPHA = new State<>("A", War3Real.class, War3Real.valueOf(1F));
		public final static State<War3Real> ART_COLOR_BLUE = new State<>("B", War3Real.class, War3Real.valueOf(1F));
		public final static State<War3Real> ART_COLOR_GREEN = new State<>("G", War3Real.class, War3Real.valueOf(1F));
		public final static State<War3Real> ART_COLOR_RED = new State<>("R", War3Real.class, War3Real.valueOf(1F));
		public final static State<War3Real> ART_DURATION = new State<>("Duration", War3Real.class, War3Real.valueOf(1F));
		public final static State<War3Real> ART_NOISE_SCALE = new State<>("NoiseScale", War3Real.class, War3Real.valueOf(0.02F));
		public final static State<War3String> ART_TEX_DIR = new State<>("Dir", War3String.class);
		public final static State<War3String> ART_TEX_FILE = new State<>("file", War3String.class);
		public final static State<War3Real> ART_SEG_LEN_AVG = new State<>("AvgSegLen", War3Real.class, War3Real.valueOf(10F));
		public final static State<War3Real> ART_TEX_COORD_SCALE = new State<>("TexCoordScale", War3Real.class);
		public final static State<War3Real> ART_WIDTH = new State<>("Width", War3Real.class, War3Real.valueOf(10F));

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
	
	public static class Obj extends SLK.Obj<LightningId> {
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
		
		public void setTex(Path val) {
			set(State.ART_TEX_DIR, War3String.valueOf(val.getParent().toString()));
			set(State.ART_TEX_FILE, War3String.valueOf(val.getFileName().toString()));
		}
		
		public War3Real getAvgSegLen() {
			return get(State.ART_SEG_LEN_AVG);
		}
		
		public void setAvgSegLen(War3Real val) {
			set(State.ART_SEG_LEN_AVG, val);
		}
		
		public War3Real getWidth() {
			return get(State.ART_WIDTH);
		}
		
		public void setWidth(War3Real val) {
			set(State.ART_WIDTH, val);
		}
		
		public Color getColor() {
			return Color.fromBGRA255((int) get(State.ART_COLOR_BLUE).toFloat() * 255, (int) get(State.ART_COLOR_GREEN).toFloat() * 255, (int) get(State.ART_COLOR_RED).toFloat() * 255, (int) get(State.ART_COLOR_ALPHA).toFloat() * 255);
		}
		
		public void setColor(Color val) {
			set(State.ART_COLOR_ALPHA, War3Real.valueOf(((float) val.getAlpha255()) / 255));
			set(State.ART_COLOR_BLUE, War3Real.valueOf(((float) val.getBlue255()) / 255));
			set(State.ART_COLOR_GREEN, War3Real.valueOf(((float) val.getGreen255()) / 255));
			set(State.ART_COLOR_RED, War3Real.valueOf(((float) val.getRed255()) / 255));
		}
		
		public War3Real getNoiseScale() {
			return get(State.ART_NOISE_SCALE);
		}
		
		public void setNoiseScale(War3Real val) {
			set(State.ART_NOISE_SCALE, val);
		}
	
		public War3Real getTexCoordScale() {
			return get(State.ART_TEX_COORD_SCALE);
		}
		
		public void setTexCoordScale(War3Real val) {
			set(State.ART_TEX_COORD_SCALE, val);
		}
		
		public War3Real getDuration() {
			return get(State.ART_DURATION);
		}
		
		public void setDuration(War3Real val) {
			//assert (val > 0);

			set(State.ART_DURATION, val);
		}
		
		public <T extends DataType> T get(State<T> state) {
			return (T) get(state.getFieldId());
		}
		
		public <T extends DataType> void set(State<T> state, T val) {
			set(state.getFieldId(), val);
		}
		
		public <T extends DataType> void remove(State<T> state) {
			super.set(state, null);
		}
		
		private void read(SLK.Obj<? extends ObjId> slkObj) {
			merge(slkObj, true);
		}
		
		public Obj(SLK.Obj<? extends ObjId> slkObj) {
			super(LightningId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(LightningId id) {
			super(id);

			for (State state : State.values(State.class)) {
				set(state, state.getDefVal());
			}
		}

		@Override
		public void reduce() {
			// TODO Auto-generated method stub
			
		}
	}
	
	//private Map<LightningId, Camera> _objs = new LinkedHashMap<>();
	
	@Nonnull
	@Override
	public Map<LightningId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(@Nonnull Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Nonnull
	@Override
	public Obj addObj(@Nonnull LightningId id) {
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
	
	@Override
	public void read(@Nonnull File file) throws IOException {
		read(new RawSLK(file));
	}
	
	public LightningSLK() {
		addField(State.OBJ_ID);
		
		for (State state : State.values(State.class)) {
			addField(state);
		}
	}

	@Nonnull
	@Override
	public Obj createObj(@Nonnull ObjId id) {
		return new Obj(LightningId.valueOf(id));
	}

	@Override
	public void merge(@Nonnull LightningSLK other, boolean overwrite) {
		// TODO Auto-generated method stub
		
	}
}
