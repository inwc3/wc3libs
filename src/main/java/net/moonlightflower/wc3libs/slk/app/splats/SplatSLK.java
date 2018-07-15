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

public class SplatSLK extends SLK<SplatSLK, SplatId, SplatSLK.Obj> {
	public final static File GAME_PATH = new File("Splats\\SplatData.slk");

	public static class State<T extends DataType> extends ObjSLK.State<T> {
		public final static State<SplatId> OBJ_ID = new State<>("Name", SplatId.class);

		public final static State<BlendMode> ART_BLEND_MODE = new State<>("BlendMode", BlendMode.class);
		public final static State<War3Int> ART_COLOR_END_ALPHA = new State<>("EndA", War3Int.class, War3Int.valueOf(255));
		public final static State<War3Int> ART_COLOR_END_BLUE = new State<>("EndB", War3Int.class, War3Int.valueOf(255));
		public final static State<War3Int> ART_COLOR_END_GREEN = new State<>("EndG", War3Int.class, War3Int.valueOf(255));
		public final static State<War3Int> ART_COLOR_END_RED = new State<>("EndR", War3Int.class, War3Int.valueOf(255));
		public final static State<War3Int> ART_COLOR_MID_ALPHA = new State<>("MiddleA", War3Int.class, War3Int.valueOf(255));
		public final static State<War3Int> ART_COLOR_MID_BLUE = new State<>("MiddleB", War3Int.class, War3Int.valueOf(255));
		public final static State<War3Int> ART_COLOR_MID_GREEN = new State<>("MiddleG", War3Int.class, War3Int.valueOf(255));
		public final static State<War3Int> ART_COLOR_MID_RED = new State<>("MiddleR", War3Int.class, War3Int.valueOf(255));
		public final static State<War3Int> ART_COLOR_START_ALPHA = new State<>("StartA", War3Int.class, War3Int.valueOf(255));
		public final static State<War3Int> ART_COLOR_START_BLUE = new State<>("StartB", War3Int.class, War3Int.valueOf(255));
		public final static State<War3Int> ART_COLOR_START_GREEN = new State<>("StartG", War3Int.class, War3Int.valueOf(255));
		public final static State<War3Int> ART_COLOR_START_RED = new State<>("StartR", War3Int.class, War3Int.valueOf(255));
		public final static State<War3Int> ART_COLS = new State<>("Columns", War3Int.class);
		public final static State<War3Real> ART_DECAY = new State<>("Decay", War3Real.class);
		public final static State<War3Real> ART_DECAY_REPEAT = new State<>("DecayRepeat", War3Real.class);
		public final static State<War3Real> ART_DECAY_UV_END = new State<>("UVDecayEnd", War3Real.class);
		public final static State<War3Real> ART_DECAY_UV_START = new State<>("UVDecayStart", War3Real.class);
		public final static State<War3Int> ART_ROWS = new State<>("Rows", War3Int.class);
		public final static State<War3Real> ART_LIFESPAN = new State<>("Lifespan", War3Real.class);
		public final static State<War3Real> ART_LIFESPAN_REPEAT = new State<>("LifespanRepeat", War3Real.class);
		public final static State<War3Real> ART_LIFESPAN_UV_END = new State<>("UVLifespanEnd", War3Real.class);
		public final static State<War3Real> ART_LIFESPAN_UV_START = new State<>("UVLifespanStart", War3Real.class);
		public final static State<War3Real> ART_SCALE = new State<>("Scale", War3Real.class, War3Real.valueOf(1F));
		public final static State<War3String> ART_TEX_DIR = new State<>("Dir", War3String.class);
		public final static State<War3String> ART_TEX_FILE = new State<>("file", War3String.class);
		public final static State<WaterCode> ART_WATER = new State<>("Water", WaterCode.class);

		public final static State<War3String> EDITOR_COMMENT = new State<>("comment", War3String.class);

		public final static State<SoundLabel> SOUND_LABEL = new State<>("Sound", SoundLabel.class);

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
	
	public static class Obj extends SLK.Obj<SplatId> {
		private final Map<ObjSLK.State, DataType> _stateVals = new LinkedHashMap<>();

		@Override
		public Map<ObjSLK.State, DataType> getStateVals() {
			return new LinkedHashMap<>(_stateVals);
		}

		@Override
		protected void on_set(@Nonnull FieldId fieldId, @Nullable DataType val) {
			ObjSLK.State state = (ObjSLK.State) ObjSLK.State.valueByField(State.class, fieldId);

			if (state != null) _stateVals.put(state, val);
		}

		@Override
		protected void on_remove(@Nonnull FieldId fieldId) {
			ObjSLK.State state = (ObjSLK.State) ObjSLK.State.valueByField(State.class, fieldId);

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

		public War3Int getRows() {
			return get(State.ART_ROWS);
		}

		public void setRows(War3Int val) {
			set(State.ART_ROWS, val);
		}

		public War3Int getCols() {
			return get(State.ART_COLS);
		}

		public void setCols(War3Int val) {
			set(State.ART_COLS, val);
		}

		public BlendMode getBlendMode() {
			return get(State.ART_BLEND_MODE);
		}

		public void setBlendMode(BlendMode val) {
			set(State.ART_BLEND_MODE, val);
		}

		public War3Real getScale() {
			return get(State.ART_SCALE);
		}

		public void setScale(War3Real val) {
			set(State.ART_SCALE, val);
		}

		public War3Real getLifespan() {
			return get(State.ART_LIFESPAN);
		}

		public void setLifespan(War3Real val) {
			set(State.ART_LIFESPAN, val);
		}

		public War3Real getLifespanRepeat() {
			return get(State.ART_LIFESPAN_REPEAT);
		}

		public War3Real getLifespanUVStart() {
			return get(State.ART_LIFESPAN_UV_START);
		}

		public War3Real getLifespanUVEnd() {
			return get(State.ART_LIFESPAN_UV_END);
		}

		public void setLifespanParams(War3Real repeatVal, War3Real startUV, War3Real endUV) {
			set(State.ART_LIFESPAN_REPEAT, repeatVal);
			set(State.ART_LIFESPAN_UV_END, endUV);
			set(State.ART_LIFESPAN_UV_START, startUV);
		}

		public War3Real getDecay() {
			return get(State.ART_DECAY);
		}

		public void setDecay(War3Real val) {
			set(State.ART_DECAY, val);
		}

		public War3Real getDecayRepeat() {
			return get(State.ART_DECAY_REPEAT);
		}

		public War3Real getDecayUVStart() {
			return get(State.ART_DECAY_UV_START);
		}

		public War3Real getDecayUVEnd() {
			return get(State.ART_DECAY_UV_END);
		}

		public void setDecayParams(War3Real repeatVal, War3Real startUV, War3Real endUV) {
			set(State.ART_DECAY_REPEAT, repeatVal);
			set(State.ART_DECAY_UV_END, endUV);
			set(State.ART_DECAY_UV_START, startUV);
		}

		public Color getColorStart() {
			return Color.fromBGRA255(get(State.ART_COLOR_START_BLUE).toInt(), get(State.ART_COLOR_START_GREEN).toInt(), get(State.ART_COLOR_START_RED).toInt(), get(State.ART_COLOR_START_ALPHA).toInt());
		}

		public void setColorStart(Color val) {
			set(State.ART_COLOR_START_ALPHA, War3Int.valueOf(val.getAlpha255()));
			set(State.ART_COLOR_START_BLUE, War3Int.valueOf(val.getBlue255()));
			set(State.ART_COLOR_START_GREEN, War3Int.valueOf(val.getGreen255()));
			set(State.ART_COLOR_START_RED, War3Int.valueOf(val.getRed255()));
		}

		public Color getColorMid() {
			return Color.fromBGRA255(get(State.ART_COLOR_MID_BLUE).toInt(), get(State.ART_COLOR_MID_GREEN).toInt(), get(State.ART_COLOR_MID_RED).toInt(), get(State.ART_COLOR_MID_ALPHA).toInt());
		}

		public void setColorMid(Color val) {
			set(State.ART_COLOR_MID_ALPHA, War3Int.valueOf(val.getAlpha255()));
			set(State.ART_COLOR_MID_BLUE, War3Int.valueOf(val.getBlue255()));
			set(State.ART_COLOR_MID_GREEN, War3Int.valueOf(val.getGreen255()));
			set(State.ART_COLOR_MID_RED, War3Int.valueOf(val.getRed255()));
		}

		public Color getColorEnd() {
			return Color.fromBGRA255(get(State.ART_COLOR_END_BLUE).toInt(), get(State.ART_COLOR_END_GREEN).toInt(), get(State.ART_COLOR_END_RED).toInt(), get(State.ART_COLOR_END_ALPHA).toInt());
		}

		public void setColorEnd(Color val) {
			set(State.ART_COLOR_END_ALPHA, War3Int.valueOf(val.getAlpha255()));
			set(State.ART_COLOR_END_BLUE, War3Int.valueOf(val.getBlue255()));
			set(State.ART_COLOR_END_GREEN, War3Int.valueOf(val.getGreen255()));
			set(State.ART_COLOR_END_RED, War3Int.valueOf(val.getRed255()));
		}

		public WaterCode getWater() {
			return get(State.ART_WATER);
		}

		public void setWater(WaterCode val) {
			set(State.ART_WATER, val);
		}

		public SoundLabel getSound() {
			return get(State.SOUND_LABEL);
		}

		public void setSound(SoundLabel val) {
			/*if (val.equals("NULL")) {
				val = null;
			}*/

			set(State.SOUND_LABEL, val);
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

		public Obj(SLK.Obj<? extends ObjId> slkObj) {
			super(SplatId.valueOf(slkObj.getId()));

			read(slkObj);
		}

		public Obj(SplatId id) {
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
				slkObj.set(State.SOUND_LABEL, SoundLabel.valueOf("NULL"));
			}
		}

		return slk;
	}

	@Override
	public void read(@Nonnull File file) throws IOException {
		read(new RawSLK(file));
	}

	public SplatSLK() {
		addField(State.OBJ_ID);

		for (State<?> state : State.values(State.class)) {
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
