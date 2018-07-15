package net.moonlightflower.wc3libs.slk.app.meta;

import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.MetaSLK;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.SLK;
import net.moonlightflower.wc3libs.slk.SLKState;
import net.moonlightflower.wc3libs.slk.app.doodads.DoodSLK;
import net.moonlightflower.wc3libs.slk.app.objs.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.util.Map;

public class CommonMetaSLK extends MetaSLK<CommonMetaSLK, ObjId, CommonMetaSLK.Obj> {
    @Nonnull
    @Override
    protected Obj createObj(@Nonnull ObjId id) {
        return new Obj(id);
    }

    @Override
    protected void read(@Nonnull SLK<?, ? extends ObjId, ? extends SLK.Obj<? extends ObjId>> slk) {

    }

    public static class State<T extends DataType> extends ObjSLK.State<T> {
        public final static State<ObjId> ID = new State<>("ID", ObjId.class);

        public final static State<War3String> FIELD = new State<>("field", War3String.class);
        public final static State<War3String> SLK = new State<>("slk", War3String.class);
        public final static State<War3String> INDEX = new State<>("index", War3String.class);

        public final static State<War3Int> REPEAT = new State<>("repeat", War3Int.class);

        public final static State<War3String> CATEGORY = new State<>("category", War3String.class);
        public final static State<War3String> DISPLAY_NAME = new State<>("displayName", War3String.class);
        public final static State<War3String> SORT = new State<>("sort", War3String.class);
        public final static State<War3String> TYPE = new State<>("type", War3String.class);
        public final static State<War3Char> CHANGE_FLAGS = new State<>("changeFlags", War3Char.class);
        public final static State<War3String> IMPORT_TYPE = new State<>("importType", War3String.class);
        public final static State<War3Bool> STRING_EXT = new State<>("stringExt", War3Bool.class);
        public final static State<War3Bool> CASE_SENS = new State<>("caseSens", War3Bool.class);
        public final static State<War3Bool> CAN_BE_EMPTY = new State<>("canBeEmpty", War3Bool.class);
        public final static State<War3String> MIN_VAL = new State<>("minVal", War3String.class);
        public final static State<War3String> MAX_VAL = new State<>("maxVal", War3String.class);
        public final static State<War3Bool> FORCE_NON_NEG = new State<>("forceNonNeg", War3Bool.class);

        public final static State<War3Int> USE_SPECIFIC = new State<>("useSpecific", War3Int.class);
        public final static State<War3Int> NOT_SPECIFIC = new State<>("notSpecific", new DataTypeInfo(DataList.class, AbilId.class));
        public final static State<War3Int> VERSION = new State<>("version", War3Int.class);
        public final static State<War3Int> SECTION = new State<>("section", War3Int.class);

        public final static State<War3Bool> IN_BETA = new State<>("InBeta", War3Bool.class);

        public final static State<War3Int> DATA = new State<>("data", War3Int.class);

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

    /*public static Collection<State> values() {
        return (Collection<State>) State.values(State.class);
    }*/

    public static class Obj extends MetaSLK.Obj<ObjId> {
        public Obj(@Nonnull ObjId id) {
            super(id);
        }

        @Override
        public Map<? extends SLKState, DataType> getStateVals() {
            return null;
        }

        @Override
        protected void on_set(@Nonnull FieldId fieldId, @Nullable DataType val) {
        }

        @Override
        protected void on_remove(@Nonnull FieldId fieldId) {
        }

        @Override
        protected void on_clear() {
        }

        @Override
        public void reduce() {

        }
    }

    @Nullable
    public static File convertSLKName(@Nonnull String slkName) {
        //convert slk names
        switch (slkName) {
            case "AbilityData":
                return AbilSLK.GAME_PATH;
            case "AbilityBuffData":
                return BuffSLK.GAME_PATH;
            case "DestructableData":
                return DestructableSLK.GAME_PATH;
            case "DoodadData":
                return DoodSLK.GAME_PATH;
            case "ItemData":
                return ItemSLK.GAME_PATH;
            case "UnitAbilities":
                return UnitAbilsSLK.GAME_PATH;
            case "UnitBalance":
                return UnitBalanceSLK.GAME_PATH;
            case "UnitData":
                return UnitDataSLK.GAME_PATH;
            case "UnitUI":
                return UnitUISLK.GAME_PATH;
            case "UnitWeapons":
                return UnitWeaponsSLK.GAME_PATH;
            case "UpgradeData":
                return UpgradeSLK.GAME_PATH;
        }

        return null;
    }

    public static Integer getDataI(@Nonnull MetaSLK.Obj metaObj) {
        Integer repeatI = null;

        try {
            War3Int repeatVal = War3Int.valueOf(metaObj.get(FieldId.valueOf("data")));

            if (repeatVal != null) repeatI = repeatVal.toInt();
        } catch (Exception ignored) {
        }

        return repeatI;
    }

    public static Integer getRepeatI(@Nonnull MetaSLK.Obj metaObj) {
        Integer repeatI = null;

        try {
            War3Int repeatVal = War3Int.valueOf(metaObj.get(FieldId.valueOf("repeat")));

            if (repeatVal != null) repeatI = repeatVal.toInt();
        } catch (Exception ignored) {
        }

        return repeatI;
    }

    //data pts are used by abilities, a value of 1 maps to DataA, 2 to DataB, 3 to DataC and so on
    //the ability slk defines up to DataI, hence 9 is the max
    public final static int DATA_PT_MIN = 1;
    public final static int DATA_PT_MAX = 9;

    public static int getSLKFieldRepeatPlaces(@Nonnull File slkFile, int repeat) {
        int places = 1;

        if (slkFile.equals(DoodSLK.GAME_PATH)) {
            if (repeat > 10) return -1;

            places = 2;
        } else {
            if (repeat > 4) return -1;

            places = 1;
        }

        return places;
    }

    public static FieldId getSLKField(@Nonnull File slkFile, @Nonnull MetaSLK.Obj metaObj, @Nullable Integer level) {
        FieldId retFieldId = FieldId.valueOf(metaObj.getS(FieldId.valueOf("field")));

        if (retFieldId.toString().equals("Data")) {
            Integer dataPt = getDataI(metaObj);

            if (dataPt < DATA_PT_MIN) throw new RuntimeException("dataPt < " + DATA_PT_MIN);
            if (dataPt > DATA_PT_MAX) throw new RuntimeException("dataPt > " + DATA_PT_MAX);

            retFieldId = FieldId.valueOf(retFieldId.toString() + (char) ('A' + dataPt - 1));
        }

        Integer repeatI = getRepeatI(metaObj);

        if ((repeatI != null) && (repeatI > 0) && level != null) {
            int places = getSLKFieldRepeatPlaces(slkFile, repeatI);

            if (places > -1) {
                StringBuilder add = new StringBuilder(Integer.toString(level));

                while (add.length() < places) {
                    add.insert(0, "0");
                }

                retFieldId = FieldId.valueOf(retFieldId.toString() + add);
            }
        }

        return retFieldId;
    }
}
