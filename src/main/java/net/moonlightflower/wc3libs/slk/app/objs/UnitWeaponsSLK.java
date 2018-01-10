package net.moonlightflower.wc3libs.slk.app.objs;

import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.SLK;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class UnitWeaponsSLK extends ObjSLK<UnitWeaponsSLK, UnitId, UnitWeaponsSLK.Obj> {
    public final static File GAME_USE_PATH = new File("Units\\UnitWeapons.slk");

    public static class States {
        public static List<State> values() {
            return State.values();
        }

        public final static State<UnitId> OBJ_ID = new State<>("unitWeapID", UnitId.class);

        //public final static State<BuffCode> CODE = new State<>("code", BuffCode.class);
        public final static State<Wc3String> EDITOR_SORT = new State<>("sortWeap", Wc3String.class);
        public final static State<Wc3String> EDITOR_SORT2 = new State<>("sort2", Wc3String.class);
        public final static State<Wc3String> EDITOR_COMMENTS = new State<>("comment(s)", Wc3String.class);

        public final static State<AttackBits> COMBAT_ATTACKS_USED = new State<>("weapsOn", AttackBits.class);
        public final static State<Real> COMBAT_ACQUIRE = new State<>("acquire", Real.class);
        public final static State<Real> COMBAT_RANGE_MIN = new State<>("minRange", Real.class);
        public final static State<Real> COMBAT_CAST_PT = new State<>("castpt", Real.class);
        public final static State<Real> COMBAT_CAST_BACKSWING = new State<>("castbsw", Real.class);
        public final static State<Real> ART_LAUNCH_X = new State<>("launchX", Real.class);
        public final static State<Real> ART_LAUNCH_Y = new State<>("launchY", Real.class);
        public final static State<Real> ART_LAUNCH_Z = new State<>("launchZ", Real.class);

        public final static State<Real> ART_LAUNCH_Z_SWIM = new State<>("launchSwimZ", Real.class);
        public final static State<Real> ART_IMPACT_Z = new State<>("impactZ", Real.class);
        public final static State<Real> ART_IMPACT_Z_SWIM = new State<>("impactSwimZ", Real.class);

        public final static State<CombatSound> COMBAT_ATTACK1_WEAPON_SOUND = new State<>("weapType1", CombatSound.class);
        public final static State<DataList<CombatTarget>> COMBAT_ATTACK1_TARGS = new State<>("targs1", new DataTypeInfo(DataList.class, CombatTarget.class));

        public final static State<Bool> COMBAT_ATTACK1_SHOW_UI = new State<>("showUI1", Bool.class);
        public final static State<Real> COMBAT_ATTACK1_RANGE = new State<>("rangeN1", Real.class);
        public final static State<Real> COMBAT_ATTACK1_RANGE_TEST = new State<>("RngTst", Real.class);
        public final static State<Real> COMBAT_ATTACK1_RANGE_BUFFER = new State<>("RngBuff1", Real.class);
        public final static State<AttackType> COMBAT_ATTACK1_ATK_TYPE = new State<>("atkType1", AttackType.class);
        public final static State<WeaponType> COMBAT_ATTACK1_WEAPON_TYPE = new State<>("weapTp1", WeaponType.class);
        public final static State<Real> COMBAT_ATTACK1_COOLDOWN = new State<>("cool1", Real.class);
        public final static State<Real> COMBAT_ATTACK1_COOLDOWN_MIN = new State<>("mincool1", Real.class);
        public final static State<Real> COMBAT_ATTACK1_DMG_DICE = new State<>("dice1", Real.class);
        public final static State<Real> COMBAT_ATTACK1_DMG_DICE_SIDES = new State<>("sides1", Real.class);
        public final static State<Real> COMBAT_ATTACK1_DMG_BASE = new State<>("dmgplus1", Real.class);
        public final static State<Real> COMBAT_ATTACK1_DMG_UP = new State<>("dmgUp1", Real.class);
        public final static State<Real> COMBAT_ATTACK1_DMG_MIN = new State<>("mindmg1", Real.class);
        public final static State<Real> COMBAT_ATTACK1_DMG_AVG = new State<>("avgdmg1", Real.class);
        public final static State<Real> COMBAT_ATTACK1_DMG_MAX = new State<>("maxdmg1", Real.class);
        public final static State<Real> COMBAT_ATTACK1_DMG_PT = new State<>("dmgpt1", Real.class);
        public final static State<Real> COMBAT_ATTACK1_DMG_BACKSWING = new State<>("backSw1", Real.class);
        public final static State<Real> COMBAT_ATTACK1_AREA_FULL = new State<>("Farea1", Real.class);
        public final static State<Real> COMBAT_ATTACK1_AREA_MEDIUM = new State<>("Harea1", Real.class);
        public final static State<Real> COMBAT_ATTACK1_AREA_SMALL = new State<>("Qarea1", Real.class);
        public final static State<Real> COMBAT_ATTACK1_AREA_MEDIUM_DMG_FACTOR = new State<>("Hfact1", Real.class);
        public final static State<Real> COMBAT_ATTACK1_AREA_SMALL_DMG_FACTOR = new State<>("Qfact1", Real.class);
        public final static State<DataList<CombatTarget>> COMBAT_ATTACK1_AREA_TARGS = new State<>("splashTargs1", new DataTypeInfo(DataList.class,
                CombatTarget.class));
        public final static State<Wc3Int> COMBAT_ATTACK1_TARGS_MAX = new State<>("targCount1", Wc3Int.class);
        public final static State<Real> COMBAT_ATTACK1_DMG_LOSS_FACTOR = new State<>("damageLoss1", Real.class);
        public final static State<Real> COMBAT_ATTACK1_DMG_SPILL_DIST = new State<>("spillDist1", Real.class);
        public final static State<Real> COMBAT_ATTACK1_DMG_SPILL_RAD = new State<>("spillRadius1", Real.class);
        public final static State<Wc3String> COMBAT_DMG_UPG = new State<>("DmgUpg", Wc3String.class);
        public final static State<Wc3Int> COMBAT_DMG_MOD = new State<>("dmod1", Wc3Int.class);
        public final static State<Real> COMBAT_DMG_PER_SEC = new State<>("DPS", Real.class);

        public final static State<CombatSound> COMBAT_ATTACK2_WEAPON_SOUND = new State<>("weapType2", CombatSound.class);
        public final static State<DataList<CombatTarget>> COMBAT_ATTACK2_TARGS = new State<>("targs2", new DataTypeInfo(DataList.class, CombatTarget.class));
        public final static State<Bool> COMBAT_ATTACK2_SHOW_UI = new State<>("showUI2", Bool.class);
        public final static State<Real> COMBAT_ATTACK2_RANGE = new State<>("rangeN2", Real.class);
        public final static State<Real> COMBAT_ATTACK2_RANGE_TEST = new State<>("RngTst2", Real.class);
        public final static State<Real> COMBAT_ATTACK2_RANGE_BUFFER = new State<>("RngBuff2", Real.class);
        public final static State<AttackType> COMBAT_ATTACK2_ATK_TYPE = new State<>("atkType2", AttackType.class);
        public final static State<WeaponType> COMBAT_ATTACK2_WEAPON_TYPE = new State<>("weapTp2", WeaponType.class);
        public final static State<Real> COMBAT_ATTACK2_COOLDOWN = new State<>("cool2", Real.class);
        public final static State<Real> COMBAT_ATTACK2_COOLDOWN_MIN = new State<>("mincool2", Real.class);
        public final static State<Real> COMBAT_ATTACK2_DMG_DICE = new State<>("dice2", Real.class);
        public final static State<Real> COMBAT_ATTACK2_DMG_DICE_SIDES = new State<>("sides2", Real.class);
        public final static State<Real> COMBAT_ATTACK2_DMG_BASE = new State<>("dmgplus2", Real.class);
        public final static State<Real> COMBAT_ATTACK2_DMG_UP = new State<>("dmgUp2", Real.class);
        public final static State<Real> COMBAT_ATTACK2_DMG_MIN = new State<>("mindmg2", Real.class);
        public final static State<Real> COMBAT_ATTACK2_DMG_AVG = new State<>("avgdmg2", Real.class);
        public final static State<Real> COMBAT_ATTACK2_DMG_MAX = new State<>("maxdmg2", Real.class);
        public final static State<Real> COMBAT_ATTACK2_DMG_PT = new State<>("dmgpt2", Real.class);
        public final static State<Real> COMBAT_ATTACK2_DMG_BACKSWING = new State<>("backSw2", Real.class);
        public final static State<Real> COMBAT_ATTACK2_AREA_FULL = new State<>("Farea2", Real.class);
        public final static State<Real> COMBAT_ATTACK2_AREA_MEDIUM = new State<>("Harea2", Real.class);
        public final static State<Real> COMBAT_ATTACK2_AREA_SMALL = new State<>("Qarea2", Real.class);
        public final static State<Real> COMBAT_ATTACK2_AREA_MEDIUM_DMG_FACTOR = new State<>("Hfact2", Real.class);
        public final static State<Real> COMBAT_ATTACK2_AREA_SMALL_DMG_FACTOR = new State<>("Qfact2", Real.class);
        public final static State<DataList<CombatTarget>> COMBAT_ATTACK2_AREA_TARGS = new State<>("splashTargs2", new DataTypeInfo(DataList.class,
				CombatTarget.class));
        public final static State<Wc3Int> COMBAT_ATTACK2_TARGS_MAX = new State<>("targCount2", Wc3Int.class);
        public final static State<Real> COMBAT_ATTACK2_DMG_LOSS_FACTOR = new State<>("damageLoss2", Real.class);
        public final static State<Real> COMBAT_ATTACK2_DMG_SPILL_DIST = new State<>("spillDist2", Real.class);
        public final static State<Real> COMBAT_ATTACK2_DMG_SPILL_RAD = new State<>("spillRadius2", Real.class);
        public final static State<Bool> EDITOR_IN_BETA = new State<>("InBeta", Bool.class);
    }

    public static class Obj extends SLK.Obj<UnitId> {
        public <T extends DataType> T get(State<T> state) {
            return state.tryCastVal(super.get(state));
        }

        public <T extends DataType> void set(State<T> state, T val) {
            super.set(state, val);
        }

        private void read(SLK.Obj<? extends ObjId> slkObj) {
            merge(slkObj, true);
        }

        public Obj(SLK.Obj<? extends ObjId> slkObj) {
            this(UnitId.valueOf(slkObj.getId()));

            read(slkObj);
        }

        public Obj(UnitId id) {
            super(id);

            for (State state : States.values()) {
                set(state, state.getDefVal());
            }
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
    public void read(File file) throws IOException {
        super.read(file);
    }

    @Override
    public void write(File file) throws IOException {
        super.write(file);
    }

    public UnitWeaponsSLK(SLK slk) {
        read(slk);
    }

    public UnitWeaponsSLK() {
        super();

        addField(States.OBJ_ID.getFieldId());

        for (State state : States.values()) {
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
