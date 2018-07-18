package net.moonlightflower.wc3libs.slk.app.objs;

import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.SLK;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class UnitWeaponsSLK extends ObjSLK<UnitWeaponsSLK, UnitId, UnitWeaponsSLK.Obj> {
    public final static File GAME_PATH = new File("Units\\UnitWeapons.slk");

    public static class State<T extends DataType> extends ObjSLK.State<T> {
        public final static State<UnitId> OBJ_ID = new State<>("unitWeapID", UnitId.class);

        //public final static State<BuffCode> CODE = new State<>("code", BuffCode.class);
        public final static State<War3String> EDITOR_SORT = new State<>("sortWeap", War3String.class);
        public final static State<War3String> EDITOR_SORT2 = new State<>("sort2", War3String.class);
        public final static State<War3String> EDITOR_COMMENTS = new State<>("comment(s)", War3String.class);

        public final static State<AttackBits> COMBAT_ATTACKS_USED = new State<>("weapsOn", AttackBits.class);
        public final static State<War3Real> COMBAT_ACQUIRE = new State<>("acquire", War3Real.class);
        public final static State<War3Real> COMBAT_RANGE_MIN = new State<>("minRange", War3Real.class);
        public final static State<War3Real> COMBAT_CAST_PT = new State<>("castpt", War3Real.class);
        public final static State<War3Real> COMBAT_CAST_BACKSWING = new State<>("castbsw", War3Real.class);
        public final static State<War3Real> ART_LAUNCH_X = new State<>("launchX", War3Real.class);
        public final static State<War3Real> ART_LAUNCH_Y = new State<>("launchY", War3Real.class);
        public final static State<War3Real> ART_LAUNCH_Z = new State<>("launchZ", War3Real.class);

        public final static State<War3Real> ART_LAUNCH_Z_SWIM = new State<>("launchSwimZ", War3Real.class);
        public final static State<War3Real> ART_IMPACT_Z = new State<>("impactZ", War3Real.class);
        public final static State<War3Real> ART_IMPACT_Z_SWIM = new State<>("impactSwimZ", War3Real.class);

        public final static State<CombatSound> COMBAT_ATTACK1_WEAPON_SOUND = new State<>("weapType1", CombatSound.class);
        public final static State<DataList<CombatTarget>> COMBAT_ATTACK1_TARGS = new State<>("targs1", new DataTypeInfo(DataList.class, CombatTarget.class));

        public final static State<War3Bool> COMBAT_ATTACK1_SHOW_UI = new State<>("showUI1", War3Bool.class);
        public final static State<War3Real> COMBAT_ATTACK1_RANGE = new State<>("rangeN1", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK1_RANGE_TEST = new State<>("RngTst", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK1_RANGE_BUFFER = new State<>("RngBuff1", War3Real.class);
        public final static State<AttackType> COMBAT_ATTACK1_ATK_TYPE = new State<>("atkType1", AttackType.class);
        public final static State<WeaponType> COMBAT_ATTACK1_WEAPON_TYPE = new State<>("weapTp1", WeaponType.class);
        public final static State<War3Real> COMBAT_ATTACK1_COOLDOWN = new State<>("cool1", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK1_COOLDOWN_MIN = new State<>("mincool1", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK1_DMG_DICE = new State<>("dice1", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK1_DMG_DICE_SIDES = new State<>("sides1", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK1_DMG_BASE = new State<>("dmgplus1", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK1_DMG_UP = new State<>("dmgUp1", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK1_DMG_MIN = new State<>("mindmg1", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK1_DMG_AVG = new State<>("avgdmg1", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK1_DMG_MAX = new State<>("maxdmg1", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK1_DMG_PT = new State<>("dmgpt1", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK1_DMG_BACKSWING = new State<>("backSw1", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK1_AREA_FULL = new State<>("Farea1", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK1_AREA_MEDIUM = new State<>("Harea1", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK1_AREA_SMALL = new State<>("Qarea1", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK1_AREA_MEDIUM_DMG_FACTOR = new State<>("Hfact1", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK1_AREA_SMALL_DMG_FACTOR = new State<>("Qfact1", War3Real.class);
        public final static State<DataList<CombatTarget>> COMBAT_ATTACK1_AREA_TARGS = new State<>("splashTargs1", new DataTypeInfo(DataList.class,
                CombatTarget.class));
        public final static State<War3Int> COMBAT_ATTACK1_TARGS_MAX = new State<>("targCount1", War3Int.class);
        public final static State<War3Real> COMBAT_ATTACK1_DMG_LOSS_FACTOR = new State<>("damageLoss1", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK1_DMG_SPILL_DIST = new State<>("spillDist1", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK1_DMG_SPILL_RAD = new State<>("spillRadius1", War3Real.class);
        public final static State<War3String> COMBAT_DMG_UPG = new State<>("DmgUpg", War3String.class);
        public final static State<War3Int> COMBAT_DMG_MOD = new State<>("dmod1", War3Int.class);
        public final static State<War3Real> COMBAT_DMG_PER_SEC = new State<>("DPS", War3Real.class);

        public final static State<CombatSound> COMBAT_ATTACK2_WEAPON_SOUND = new State<>("weapType2", CombatSound.class);
        public final static State<DataList<CombatTarget>> COMBAT_ATTACK2_TARGS = new State<>("targs2", new DataTypeInfo(DataList.class, CombatTarget.class));
        public final static State<War3Bool> COMBAT_ATTACK2_SHOW_UI = new State<>("showUI2", War3Bool.class);
        public final static State<War3Real> COMBAT_ATTACK2_RANGE = new State<>("rangeN2", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK2_RANGE_TEST = new State<>("RngTst2", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK2_RANGE_BUFFER = new State<>("RngBuff2", War3Real.class);
        public final static State<AttackType> COMBAT_ATTACK2_ATK_TYPE = new State<>("atkType2", AttackType.class);
        public final static State<WeaponType> COMBAT_ATTACK2_WEAPON_TYPE = new State<>("weapTp2", WeaponType.class);
        public final static State<War3Real> COMBAT_ATTACK2_COOLDOWN = new State<>("cool2", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK2_COOLDOWN_MIN = new State<>("mincool2", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK2_DMG_DICE = new State<>("dice2", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK2_DMG_DICE_SIDES = new State<>("sides2", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK2_DMG_BASE = new State<>("dmgplus2", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK2_DMG_UP = new State<>("dmgUp2", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK2_DMG_MIN = new State<>("mindmg2", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK2_DMG_AVG = new State<>("avgdmg2", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK2_DMG_MAX = new State<>("maxdmg2", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK2_DMG_PT = new State<>("dmgpt2", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK2_DMG_BACKSWING = new State<>("backSw2", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK2_AREA_FULL = new State<>("Farea2", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK2_AREA_MEDIUM = new State<>("Harea2", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK2_AREA_SMALL = new State<>("Qarea2", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK2_AREA_MEDIUM_DMG_FACTOR = new State<>("Hfact2", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK2_AREA_SMALL_DMG_FACTOR = new State<>("Qfact2", War3Real.class);
        public final static State<DataList<CombatTarget>> COMBAT_ATTACK2_AREA_TARGS = new State<>("splashTargs2", new DataTypeInfo(DataList.class,
                CombatTarget.class));
        public final static State<War3Int> COMBAT_ATTACK2_TARGS_MAX = new State<>("targCount2", War3Int.class);
        public final static State<War3Real> COMBAT_ATTACK2_DMG_LOSS_FACTOR = new State<>("damageLoss2", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK2_DMG_SPILL_DIST = new State<>("spillDist2", War3Real.class);
        public final static State<War3Real> COMBAT_ATTACK2_DMG_SPILL_RAD = new State<>("spillRadius2", War3Real.class);
        public final static State<War3Bool> EDITOR_IN_BETA = new State<>("InBeta", War3Bool.class);

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

    public static class Obj extends SLK.Obj<UnitId> {
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

        public <T extends DataType> T get(State<T> state) throws DataTypeInfo.CastException {
            if (!super.contains(state.getFieldId())) return state.getDefVal();

            return state.tryCastVal(super.get(state));
        }

        public <T extends DataType> void set(State<T> state, T val) {
            super.set(state, val);
        }

        private void read(SLK.Obj<? extends ObjId> slkObj) {
            merge(slkObj, true);
        }

        public Obj(SLK.Obj<? extends ObjId> slkObj) {
            super(UnitId.valueOf(slkObj.getId()));

            read(slkObj);
        }

        public Obj(UnitId id) {
            super(id);

            /*for (State<?> state : State.values(State.class)) {
                set(state, state.getDefVal());
            }*/
        }

        @Override
        public void reduce() {
            // TODO Auto-generated method stub

        }
    }

    //private Map<UnitId, Camera> _objs = new LinkedHashMap<>();

    @Nonnull
    @Override
    public Map<UnitId, Obj> getObjs() {
        return _objs;
    }

    @Override
    public void addObj(@Nonnull Obj val) {
        _objs.put(val.getId(), val);
    }

    @Nonnull
    @Override
    public Obj addObj(@Nonnull UnitId id) {
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
        super.read(file);
    }

    @Override
    public void write(@Nonnull File file) throws IOException {
        super.write(file);
    }

    public UnitWeaponsSLK(SLK slk) {
        read(slk);
    }

    public UnitWeaponsSLK() {
        super();

        addField(State.OBJ_ID.getFieldId());

        for (State<?> state : State.values(State.class)) {
            addField(state);
        }
    }

    public UnitWeaponsSLK(File file) throws IOException {
        this();

        read(file);
    }

    @Nonnull
    @Override
    public Obj createObj(@Nonnull ObjId id) {
        return new Obj(UnitId.valueOf(id));
    }

    @Override
    public void merge(@Nonnull UnitWeaponsSLK other, boolean overwrite) {
        for (Map.Entry<UnitId, Obj> objEntry : other.getObjs().entrySet()) {
            UnitId objId = objEntry.getKey();
            Obj otherObj = objEntry.getValue();

            Obj obj = addObj(objId);

            obj.merge(otherObj);
        }
    }
}
