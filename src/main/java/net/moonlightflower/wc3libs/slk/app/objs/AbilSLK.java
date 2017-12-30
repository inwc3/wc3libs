package net.moonlightflower.wc3libs.slk.app.objs;

import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.SLK;
import net.moonlightflower.wc3libs.slk.SLKState;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AbilSLK extends ObjSLK<AbilSLK, AbilId, AbilSLK.Obj> {
	public final static File GAME_USE_PATH = new File("Units\\AbilityData.slk");
	
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

		public final static State<AbilId> OBJ_ID = new State<>("alias", AbilId.class);
		public final static State<AbilCode> DATA_CODE = new State<>("code", AbilCode.class);
		public final static State<Wc3String> EDITOR_COMMENTS = new State<>("comments", Wc3String.class);
		public final static State<Wc3Int> EDITOR_VERSION = new State<>("version", Wc3Int.class);
		public final static State<Bool> EDITOR_IN_EDITOR = new State<>("useInEditor", Bool.class);
		public final static State<Bool> DATA_IS_HERO = new State<>("hero", Bool.class);
		public final static State<Bool> DATA_IS_ITEM = new State<>("item", Bool.class);
		public final static State<Wc3String> EDITOR_SORT = new State<>("sort", Wc3String.class);
		public final static State<UnitRace> DATA_RACE = new State<>("race", UnitRace.class);
		public final static State<Bool> DATA_CHECK_DEPENDENCY = new State<>("checkDep", Bool.class);
		public final static State<Wc3Int> DATA_LEVEL_COUNT = new State<>("levels", Wc3Int.class);
		public final static State<Wc3Int> DATA_LEVEL_REQ = new State<>("reqLevel", Wc3Int.class);
		public final static State<Wc3Int> DATA_LEVEL_SKIP = new State<>("levelSkip", Wc3Int.class);
		public final static State<Wc3Int> DATA_PRIO = new State<>("priority", Wc3Int.class);

		public final static State<DataList<CombatTarget>> DATA_TARGS1 = new State<>("targs1", new DataTypeInfo(DataList.class, CombatTarget.class));
		public final static State<DataList<Real>> DATA_CAST_TIME1 = new State<>("Cast1", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> DATA_DURATION1 = new State<>("Dur1", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> DATA_DURATION_HERO1 = new State<>("HeroDur1", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> DATA_COOLDOWN1 = new State<>("Cool1", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Wc3Int>> DATA_MANA_COST1 = new State<>("Cost1", new DataTypeInfo(DataList.class, Wc3Int.class));
		public final static State<DataList<Real>> DATA_AREA1 = new State<>("Area1", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> DATA_RANGE1 = new State<>("Rng1", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<DataType>> DATA_A1 = new State<>("DataA1", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_B1 = new State<>("DataB1", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_C1 = new State<>("DataC1", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_D1 = new State<>("DataD1", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_E1 = new State<>("DataE1", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_F1 = new State<>("DataF1", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_G1 = new State<>("DataG1", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_H1 = new State<>("DataH1", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_I1 = new State<>("DataI1", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<UnitId>> DATA_UNIT_ID1 = new State<>("UnitID1", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<BuffId>> DATA_BUFF_ID1 = new State<>("BuffID1", new DataTypeInfo(DataList.class, BuffId.class));
		public final static State<DataList<BuffId>> ART_EFFECT_ID1 = new State<>("EfctID1", new DataTypeInfo(DataList.class, BuffId.class));

		public final static State<DataList<CombatTarget>> DATA_TARGS2 = new State<>("targs2", new DataTypeInfo(DataList.class, CombatTarget.class));
		public final static State<DataList<Real>> DATA_CAST_TIME2 = new State<>("Cast2", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> DATA_DURATION2 = new State<>("Dur2", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> DATA_DURATION_HERO2 = new State<>("HeroDur2", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> DATA_COOLDOWN2 = new State<>("Cool2", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Wc3Int>> DATA_MANA_COST2 = new State<>("Cost2", new DataTypeInfo(DataList.class, Wc3Int.class));
		public final static State<DataList<Real>> DATA_AREA2 = new State<>("Area2", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> DATA_RANGE2 = new State<>("Rng2", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<DataType>> DATA_A2 = new State<>("DataA2", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_B2 = new State<>("DataB2", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_C2 = new State<>("DataC2", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_D2 = new State<>("DataD2", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_E2 = new State<>("DataE2", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_F2 = new State<>("DataF2", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_G2 = new State<>("DataG2", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_H2 = new State<>("DataH2", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_I2 = new State<>("DataI2", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<UnitId>> DATA_UNIT_ID2 = new State<>("UnitID2", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<BuffId>> DATA_BUFF_ID2 = new State<>("BuffID2", new DataTypeInfo(DataList.class, BuffId.class));
		public final static State<DataList<BuffId>> ART_EFFECT_ID2 = new State<>("EfctID2", new DataTypeInfo(DataList.class, BuffId.class));

		public final static State<DataList<CombatTarget>> DATA_TARGS3 = new State<>("targs3", new DataTypeInfo(DataList.class, CombatTarget.class));
		public final static State<DataList<Real>> DATA_CAST_TIME3 = new State<>("Cast3", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> DATA_DURATION3 = new State<>("Dur3", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> DATA_DURATION_HERO3 = new State<>("HeroDur3", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> DATA_COOLDOWN3 = new State<>("Cool3", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Wc3Int>> DATA_MANA_COST3 = new State<>("Cost3", new DataTypeInfo(DataList.class, Wc3Int.class));
		public final static State<DataList<Real>> DATA_AREA3 = new State<>("Area3", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> DATA_RANGE3 = new State<>("Rng3", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<DataType>> DATA_A3 = new State<>("DataA3", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_B3 = new State<>("DataB3", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_C3 = new State<>("DataC3", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_D3 = new State<>("DataD3", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_E3 = new State<>("DataE3", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_F3 = new State<>("DataF3", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_G3 = new State<>("DataG3", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_H3 = new State<>("DataH3", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_I3 = new State<>("DataI3", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<UnitId>> DATA_UNIT_ID3 = new State<>("UnitID3", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<BuffId>> DATA_BUFF_ID3 = new State<>("BuffID3", new DataTypeInfo(DataList.class, BuffId.class));
		public final static State<DataList<BuffId>> ART_EFFECT_ID3 = new State<>("EfctID3", new DataTypeInfo(DataList.class, BuffId.class));

		public final static State<DataList<CombatTarget>> DATA_TARGS4 = new State<>("targs4", new DataTypeInfo(DataList.class, CombatTarget.class));
		public final static State<DataList<Real>> DATA_CAST_TIME4 = new State<>("Cast4", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> DATA_DURATION4 = new State<>("Dur4", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> DATA_DURATION_HERO4 = new State<>("HeroDur4", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> DATA_COOLDOWN4 = new State<>("Cool4", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Wc3Int>> DATA_MANA_COST4 = new State<>("Cost4", new DataTypeInfo(DataList.class, Wc3Int.class));
		public final static State<DataList<Real>> DATA_AREA4 = new State<>("Area4", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> DATA_RANGE4 = new State<>("Rng4", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<DataType>> DATA_A4 = new State<>("DataA4", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_B4 = new State<>("DataB4", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_C4 = new State<>("DataC4", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_D4 = new State<>("DataD4", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_E4 = new State<>("DataE4", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_F4 = new State<>("DataF4", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_G4 = new State<>("DataG4", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_H4 = new State<>("DataH4", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<DataType>> DATA_I4 = new State<>("DataI4", new DataTypeInfo(DataList.class, DataType.class));
		public final static State<DataList<UnitId>> DATA_UNIT_ID4 = new State<>("UnitID4", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<BuffId>> DATA_BUFF_ID4 = new State<>("BuffID4", new DataTypeInfo(DataList.class, BuffId.class));
		public final static State<DataList<BuffId>> ART_EFFECT_ID4 = new State<>("EfctID4", new DataTypeInfo(DataList.class, BuffId.class));

		public final static State<Bool> EDITOR_IN_BETA = new State<>("InBeta", Bool.class);
	}
	
	public static class Obj extends SLK.Obj<AbilId> {
		public <T extends DataType> T get(States.State<T> state) {
			System.out.println("get");
			return state.tryCastVal(super.get(state.getFieldId()));
		}
		
		public <T extends DataType> void set(States.State<T> state, T val) {
			super.set(state.getFieldId(), val);
		}
		
		private void read(SLK.Obj<? extends ObjId> slkObj) {
			merge(slkObj, true);
		}
		
		public Obj(SLK.Obj<? extends ObjId> slkObj) {
			this(AbilId.valueOf(slkObj.getId()));
			
			read(slkObj);
		}
		
		public Obj(AbilId id) {
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
	
	//private Map<AbilId, Camera> _objs = new LinkedHashMap<>();
	
	@Nonnull
	@Override
	public Map<AbilId, Obj> getObjs() {
		return _objs;
	}
	
	@Override
	public void addObj(@Nonnull Obj val) {
		_objs.put(val.getId(), val);
	}
	
	@Nonnull
	@Override
	public Obj addObj(@Nonnull AbilId id) {
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
	public void read(File file) throws IOException {
		super.read(file);
	}
	
	@Override
	public void write(File file) throws IOException {
		super.write(file);
	}
	
	public AbilSLK(SLK slk) {
		read(slk);
	}
	
	public AbilSLK() {
		super();
		
		addField(States.OBJ_ID);

		for (States.State state : States.values()) {
			addField(state);
		}
	}

	public AbilSLK(File file) throws IOException {
		this();
		
		read(file);
	}
	
	@Nonnull
	@Override
	public Obj createObj(@Nonnull ObjId id) {
		return new Obj(AbilId.valueOf(id));
	}

	@Override
	public void merge(@Nonnull AbilSLK other, boolean overwrite) {
		for (Map.Entry<AbilId, Obj> objEntry : other.getObjs().entrySet()) {
			AbilId objId = objEntry.getKey();
			Obj otherObj = objEntry.getValue();
			
			Obj obj = addObj(objId);
			
			obj.merge(otherObj);
		}
	}
}
