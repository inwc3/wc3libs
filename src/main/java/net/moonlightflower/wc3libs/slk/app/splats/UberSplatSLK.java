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
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.RawSLK;
import net.moonlightflower.wc3libs.slk.SLK;
import net.moonlightflower.wc3libs.slk.SLKState;

import javax.annotation.Nonnull;

public class UberSplatSLK extends ObjSLK<UberSplatSLK, UberSplatId, UberSplatSLK.Obj> {
	public final static File GAME_USE_PATH = new File("Units\\UberSplatData.slk");
	
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
		
		public final static State<UberSplatId> OBJ_ID = new State<>("Name", UberSplatId.class);
		
		public final static State<Real> ART_BIRTH_TIME = new State<>("BirthTime", Real.class);
		public final static State<BlendMode> ART_BLEND_MODE = new State<>("BlendMode", BlendMode.class);
		public final static State<Wc3Int> ART_COLOR_ALPHA_END = new State<>("EndA", Wc3Int.class);
		public final static State<Wc3Int> ART_COLOR_ALPHA_MID = new State<>("MiddleA", Wc3Int.class);
		public final static State<Wc3Int> ART_COLOR_ALPHA_START = new State<>("StartA", Wc3Int.class);
		public final static State<Wc3Int> ART_COLOR_BLUE_END = new State<>("EndB", Wc3Int.class);
		public final static State<Wc3Int> ART_COLOR_BLUE_MID = new State<>("MiddleB", Wc3Int.class);
		public final static State<Wc3Int> ART_COLOR_BLUE_START = new State<>("StartB", Wc3Int.class);
		public final static State<Wc3Int> ART_COLOR_GREEN_END = new State<>("EndG", Wc3Int.class);
		public final static State<Wc3Int> ART_COLOR_GREEN_MID = new State<>("MiddleG", Wc3Int.class);
		public final static State<Wc3Int> ART_COLOR_GREEN_START = new State<>("StartG", Wc3Int.class);
		public final static State<Wc3Int> ART_COLOR_RED_END = new State<>("EndR", Wc3Int.class);
		public final static State<Wc3Int> ART_COLOR_RED_MID = new State<>("MiddleR", Wc3Int.class);
		public final static State<Wc3Int> ART_COLOR_RED_START = new State<>("StartR", Wc3Int.class);
		public final static State<Bool> ART_DECAY = new State<>("Decay", Bool.class);
		public final static State<Real> ART_PAUSE_TIME = new State<>("PauseTime", Real.class);
		public final static State<Real> ART_SCALE = new State<>("Scale", Real.class);
		public final static State<Wc3String> ART_TEX_DIR = new State<>("Dir", Wc3String.class);
		public final static State<Wc3String> ART_TEX_FILE = new State<>("file", Wc3String.class);
		
		public final static State<Wc3String> EDITOR_COMMENT = new State<>("comment", Wc3String.class);
		
		public final static State<SoundLabel> SOUND_LABEL = new State<>("Sound", SoundLabel.class);
	}
	
	public static class Obj extends SLK.Obj<UberSplatId> {
		public Path getTex() {
			return Paths.get(get(States.ART_TEX_DIR).toString(), get(States.ART_TEX_FILE).toString());
		}
		
		public void setTex(Path val) {
			set(States.ART_TEX_DIR, Wc3String.valueOf(val.getParent().toString()));
			set(States.ART_TEX_FILE, Wc3String.valueOf(val.getFileName().toString()));
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
		
		public Real getBirthTime() {
			return get(States.ART_BIRTH_TIME);
		}
		
		public void setBirthTime(Real val) {
			set(States.ART_BIRTH_TIME, val);
		}
		
		public Real getPauseTime() {
			return get(States.ART_PAUSE_TIME);
		}
		
		public void setPauseTime(Real val) {
			set(States.ART_PAUSE_TIME, val);
		}
		
		Color _colorStart;
		
		public Color getColorStart() {
			return Color.fromBGRA255(get(States.ART_COLOR_BLUE_START).toInt(), get(States.ART_COLOR_GREEN_START).toInt(), get(States.ART_COLOR_RED_START).toInt(), get(States.ART_COLOR_ALPHA_START).toInt());
		}
		
		public void setColorStart(Color val) {
			set(States.ART_COLOR_ALPHA_START, Wc3Int.valueOf(val.getAlpha255()));
			set(States.ART_COLOR_BLUE_START, Wc3Int.valueOf(val.getBlue255()));
			set(States.ART_COLOR_GREEN_START, Wc3Int.valueOf(val.getGreen255()));
			set(States.ART_COLOR_RED_START, Wc3Int.valueOf(val.getRed255()));
		}
		
		public Color getColorMid() {
			return Color.fromBGRA255(get(States.ART_COLOR_BLUE_MID).toInt(), get(States.ART_COLOR_GREEN_MID).toInt(), get(States.ART_COLOR_RED_MID).toInt(), get(States.ART_COLOR_ALPHA_MID).toInt());
		}
		
		public void setColorMid(Color val) {
			set(States.ART_COLOR_ALPHA_MID, Wc3Int.valueOf(val.getAlpha255()));
			set(States.ART_COLOR_BLUE_MID, Wc3Int.valueOf(val.getBlue255()));
			set(States.ART_COLOR_GREEN_MID, Wc3Int.valueOf(val.getGreen255()));
			set(States.ART_COLOR_RED_MID, Wc3Int.valueOf(val.getRed255()));
		}
		
		public Color getColorEnd() {
			return Color.fromBGRA255(get(States.ART_COLOR_BLUE_END).toInt(), get(States.ART_COLOR_GREEN_END).toInt(), get(States.ART_COLOR_RED_END).toInt(), get(States.ART_COLOR_ALPHA_END).toInt());
		}
		
		public void setColorEnd(Color val) {
			set(States.ART_COLOR_ALPHA_END, Wc3Int.valueOf(val.getAlpha255()));
			set(States.ART_COLOR_BLUE_END, Wc3Int.valueOf(val.getBlue255()));
			set(States.ART_COLOR_GREEN_END, Wc3Int.valueOf(val.getGreen255()));
			set(States.ART_COLOR_RED_END, Wc3Int.valueOf(val.getRed255()));
		}
		
		public Bool getDecay() {
			return get(States.ART_DECAY);
		}
		
		public void setDecay(Bool val) {
			set(States.ART_DECAY, val);
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
		
		private void read(SLK.Obj<? extends ObjId> slkObj) {
			merge(slkObj, true);
		}

		public Obj(SLK.Obj<? extends ObjId> slkObj) {
			super(UberSplatId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(UberSplatId id) {
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

			SLK.Obj slkObj = slk.addObj(id);

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
	public void write(File file) throws IOException {
		RawSLK slk = toRawSLK();
		
		slk.write(file);
	}
	
	public UberSplatSLK() {
		addField(States.OBJ_ID);

		for (States.State state : States.values()) {
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
