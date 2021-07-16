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
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.RawSLK;
import net.moonlightflower.wc3libs.slk.SLK;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class UberSplatSLK extends ObjSLK<UberSplatSLK, UberSplatId, UberSplatSLK.Obj> {
	public final static File GAME_PATH = new File("Units\\UberSplatData.slk");

	public static class State<T extends DataType> extends ObjSLK.State<T> {
		public static Collection<State> values() {
			return (Collection<State>) State.values(State.class);
		}

		public final static State<UberSplatId> OBJ_ID = new State<>("Name", UberSplatId.class);

		public final static State<War3Real> ART_BIRTH_TIME = new State<>("BirthTime", War3Real.class);
		public final static State<BlendMode> ART_BLEND_MODE = new State<>("BlendMode", BlendMode.class);
		public final static State<War3Int> ART_COLOR_ALPHA_END = new State<>("EndA", War3Int.class);
		public final static State<War3Int> ART_COLOR_ALPHA_MID = new State<>("MiddleA", War3Int.class);
		public final static State<War3Int> ART_COLOR_ALPHA_START = new State<>("StartA", War3Int.class);
		public final static State<War3Int> ART_COLOR_BLUE_END = new State<>("EndB", War3Int.class);
		public final static State<War3Int> ART_COLOR_BLUE_MID = new State<>("MiddleB", War3Int.class);
		public final static State<War3Int> ART_COLOR_BLUE_START = new State<>("StartB", War3Int.class);
		public final static State<War3Int> ART_COLOR_GREEN_END = new State<>("EndG", War3Int.class);
		public final static State<War3Int> ART_COLOR_GREEN_MID = new State<>("MiddleG", War3Int.class);
		public final static State<War3Int> ART_COLOR_GREEN_START = new State<>("StartG", War3Int.class);
		public final static State<War3Int> ART_COLOR_RED_END = new State<>("EndR", War3Int.class);
		public final static State<War3Int> ART_COLOR_RED_MID = new State<>("MiddleR", War3Int.class);
		public final static State<War3Int> ART_COLOR_RED_START = new State<>("StartR", War3Int.class);
		public final static State<War3Bool> ART_DECAY = new State<>("Decay", War3Bool.class);
		public final static State<War3Real> ART_PAUSE_TIME = new State<>("PauseTime", War3Real.class);
		public final static State<War3Real> ART_SCALE = new State<>("Scale", War3Real.class);
		public final static State<War3String> ART_TEX_DIR = new State<>("Dir", War3String.class);
		public final static State<War3String> ART_TEX_FILE = new State<>("file", War3String.class);

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
	
	public static class Obj extends SLK.Obj<UberSplatId> {
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
		
		public War3Real getBirthTime() {
			return get(State.ART_BIRTH_TIME);
		}
		
		public void setBirthTime(War3Real val) {
			set(State.ART_BIRTH_TIME, val);
		}
		
		public War3Real getPauseTime() {
			return get(State.ART_PAUSE_TIME);
		}
		
		public void setPauseTime(War3Real val) {
			set(State.ART_PAUSE_TIME, val);
		}
		
		Color _colorStart;
		
		public Color getColorStart() {
			return Color.fromBGRA255(get(State.ART_COLOR_BLUE_START).toInt(), get(State.ART_COLOR_GREEN_START).toInt(), get(State.ART_COLOR_RED_START).toInt(), get(State.ART_COLOR_ALPHA_START).toInt());
		}
		
		public void setColorStart(Color val) {
			set(State.ART_COLOR_ALPHA_START, War3Int.valueOf(val.getAlpha255()));
			set(State.ART_COLOR_BLUE_START, War3Int.valueOf(val.getBlue255()));
			set(State.ART_COLOR_GREEN_START, War3Int.valueOf(val.getGreen255()));
			set(State.ART_COLOR_RED_START, War3Int.valueOf(val.getRed255()));
		}
		
		public Color getColorMid() {
			return Color.fromBGRA255(get(State.ART_COLOR_BLUE_MID).toInt(), get(State.ART_COLOR_GREEN_MID).toInt(), get(State.ART_COLOR_RED_MID).toInt(), get(State.ART_COLOR_ALPHA_MID).toInt());
		}
		
		public void setColorMid(Color val) {
			set(State.ART_COLOR_ALPHA_MID, War3Int.valueOf(val.getAlpha255()));
			set(State.ART_COLOR_BLUE_MID, War3Int.valueOf(val.getBlue255()));
			set(State.ART_COLOR_GREEN_MID, War3Int.valueOf(val.getGreen255()));
			set(State.ART_COLOR_RED_MID, War3Int.valueOf(val.getRed255()));
		}
		
		public Color getColorEnd() {
			return Color.fromBGRA255(get(State.ART_COLOR_BLUE_END).toInt(), get(State.ART_COLOR_GREEN_END).toInt(), get(State.ART_COLOR_RED_END).toInt(), get(State.ART_COLOR_ALPHA_END).toInt());
		}
		
		public void setColorEnd(Color val) {
			set(State.ART_COLOR_ALPHA_END, War3Int.valueOf(val.getAlpha255()));
			set(State.ART_COLOR_BLUE_END, War3Int.valueOf(val.getBlue255()));
			set(State.ART_COLOR_GREEN_END, War3Int.valueOf(val.getGreen255()));
			set(State.ART_COLOR_RED_END, War3Int.valueOf(val.getRed255()));
		}
		
		public War3Bool getDecay() {
			return get(State.ART_DECAY);
		}
		
		public void setDecay(War3Bool val) {
			set(State.ART_DECAY, val);
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
		
		private void read(SLK.Obj<? extends ObjId> slkObj) {
			merge(slkObj, true);
		}

		public Obj(SLK.Obj<? extends ObjId> slkObj) {
			super(UberSplatId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(UberSplatId id) {
			super(id);

			for (State state : State.values()) {
				set(state, state.getDefVal());
			}
		}

		@Override
		public void reduce() {
			// TODO Auto-generated method stub
			
		}
	}
	
	//private Map<UberSplatId, Camera> _objs = new LinkedHashMap<>();
	
	@Nonnull
	@Override
	public Map<UberSplatId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(@Nonnull Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Nonnull
	@Override
	public Obj addObj(@Nonnull UberSplatId id) {
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
	
	private RawSLK toRawSLK() {
		RawSLK slk = new RawSLK();
		
		for (Obj obj : getObjs().values()) {
			ObjId id = obj.getId();

			SLK.Obj<ObjId> slkObj = slk.addObj(id);

			slkObj.merge(obj, true);
		}
		
		return slk;
	}

	@Override
	public void read(@Nonnull File file) throws IOException {
		RawSLK slk = new RawSLK();
	
		slk.read(file);
		
		read(slk);
	}
	
	@Override
	public void write(@Nonnull File file) throws IOException {
		RawSLK slk = toRawSLK();
		
		slk.write(file);
	}
	
	public UberSplatSLK() {
		addField(State.OBJ_ID);

		for (State state : State.values()) {
			addField(state);
		}
	}

	@Nonnull
	@Override
	public Obj createObj(@Nonnull ObjId id) {
		return new Obj(UberSplatId.valueOf(id));
	}

	@Override
	public void merge(@Nonnull UberSplatSLK other, boolean overwrite) {
		// TODO Auto-generated method stub
		
	}
}
