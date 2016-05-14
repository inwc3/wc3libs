package slk.app.objs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dataTypes.DataType;
import dataTypes.app.AbilId;
import dataTypes.app.AbilList;
import dataTypes.app.ArmorSound;
import dataTypes.app.Bool;
import dataTypes.app.Int;
import dataTypes.app.ItemClass;
import dataTypes.app.ItemId;
import dataTypes.app.Model;
import dataTypes.app.Real;
import dataTypes.app.Wc3String;
import misc.ObjId;
import misc.StateInfo;
import slk.ObjSLK;
import slk.SLK;
import slk.SLKState;

public class ItemSLK extends ObjSLK<ItemSLK, ItemId, ItemSLK.Obj> {
	public final static File GAME_USE_PATH = new File("Units\\ItemData.slk");
	
	public static class States {
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
		
		public final static State<ItemId> OBJ_ID = new State<>("itemID", ItemId.class);

		public final static State<Int> ART_COLOR_BLUE = new State<>("colorB", Int.class);
		public final static State<Int> ART_COLOR_GREEN = new State<>("colorG", Int.class);
		public final static State<Int> ART_COLOR_RED = new State<>("colorR", Int.class);
		public final static State<Model> ART_MODEL = new State<>("file", Model.class);
		public final static State<Real> ART_SCALE = new State<>("scale", Real.class);
		public final static State<Real> ART_SELECTION_SCALE = new State<>("selSize", Real.class);

		public final static State<AbilList> DATA_ABILS = new State<>("abilList", AbilList.class);
		public final static State<Int> DATA_CHARGES = new State<>("uses", Int.class);
		public final static State<ItemClass> DATA_CLASS = new State<>("class", ItemClass.class);
		public final static State<AbilId> DATA_COOLDOWN_ID = new State<>("cooldownID", AbilId.class);
		public final static State<Int> DATA_COSTS_GOLD = new State<>("goldcost", Int.class);
		public final static State<Int> DATA_COSTS_LUMBER = new State<>("lumbercost", Int.class);
		public final static State<Bool> DATA_DROPPABLE = new State<>("droppable", Bool.class);
		public final static State<Bool> DATA_DROPPED = new State<>("drop", Bool.class);
		public final static State<Bool> DATA_IGNORE_COOLDOWN = new State<>("ignoreCD", Bool.class);
		public final static State<Int> DATA_LEVEL = new State<>("Level", Int.class);
		public final static State<Int> DATA_LEVEL_OLD = new State<>("oldLevel", Int.class);
		public final static State<Int> DATA_LIFE = new State<>("HP", Int.class);
		public final static State<Bool> DATA_MORPHABLE = new State<>("morph", Bool.class);
		public final static State<Wc3String> DATA_SCRIPT_NAME = new State<>("scriptname", Wc3String.class);
		public final static State<Bool> DATA_USABLE = new State<>("usable", Bool.class);
		public final static State<Bool> DATA_USE_IN_RANDOM = new State<>("pickRandom", Bool.class);
		public final static State<Bool> DATA_PAWNABLE = new State<>("pawnable", Bool.class);
		public final static State<Bool> DATA_PERISHABLE = new State<>("perishable", Bool.class);
		public final static State<Bool> DATA_POWERUP = new State<>("powerup", Bool.class);
		public final static State<Int> DATA_PRIO = new State<>("prio", Int.class);
		public final static State<Bool> DATA_SELLABLE = new State<>("sellable", Bool.class);
		public final static State<Int> DATA_STOCK_INITIAL = new State<>("stockStart", Int.class);
		public final static State<Int> DATA_STOCK_MAX = new State<>("stockRegen", Int.class);
		public final static State<Int> DATA_STOCK_REGEN = new State<>("stockMax", Int.class);
		
		public final static State<Wc3String> EDITOR_COMMENT = new State<>("comment", Wc3String.class);
		public final static State<Bool> EDITOR_IN_BETA = new State<>("InBeta", Bool.class);
		public final static State<Int> EDITOR_VERSION = new State<>("version", Int.class);
		
		public final static State<ArmorSound> SOUND_ARMOR = new State<>("armor", ArmorSound.class);
	}
	
	public static class Obj extends SLK.Obj<ItemId> {
		public <T extends DataType> T get(States.State<T> state) {
			return state.tryCastVal(super.get(state.getFieldId()));
		}
		
		public <T extends DataType> void set(States.State<T> state, T val) {
			super.set(state.getFieldId(), val);
		}
		
		private void read(SLK.Obj<? extends ObjId> slkObj) {
			merge(slkObj, true);
		}
		
		public Obj(SLK.Obj<? extends ObjId> slkObj) {
			this(ItemId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(ItemId id) {
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
	
	private Map<ItemId, Obj> _objs = new HashMap<>();
	
	@Override
	public Map<ItemId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Override
	public Obj addObj(ItemId id) {
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
		super.read(file);
	}
	
	@Override
	public void write(File file) throws IOException {
		super.write(file);
	}
	
	public ItemSLK() {
		super();
		
		addField(States.OBJ_ID);
		
		for (States.State state : States.values()) {
			addField(state);
		}
	}

	@Override
	public Obj createObj(ObjId id) {
		return new Obj(ItemId.valueOf(id));
	}

	@Override
	public void merge(ItemSLK other, boolean overwrite) {
		// TODO Auto-generated method stub
		
	}
}
