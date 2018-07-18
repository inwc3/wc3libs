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

public class ItemSLK extends ObjSLK<ItemSLK, ItemId, ItemSLK.Obj> {
    public final static File GAME_PATH = new File("Units\\ItemData.slk");

    public static class State<T extends DataType> extends ObjSLK.State<T> {
        public final static State<ItemId> OBJ_ID = new State<>("itemID", ItemId.class);

        public final static State<War3String> EDITOR_COMMENT = new State<>("comment", War3String.class);
        public final static State<War3String> DATA_SCRIPT_NAME = new State<>("scriptname", War3String.class);
        public final static State<War3Int> EDITOR_VERSION = new State<>("version", War3Int.class);
        public final static State<ItemClass> DATA_CLASS = new State<>("class", ItemClass.class);
        public final static State<War3Int> DATA_LEVEL = new State<>("Level", War3Int.class);
        public final static State<War3Int> DATA_LEVEL_OLD = new State<>("oldLevel", War3Int.class);
        public final static State<DataList<AbilId>> DATA_ABILS = new State<>("abilList", new DataTypeInfo(DataList.class, AbilId.class));
        public final static State<AbilId> DATA_COOLDOWN_ID = new State<>("cooldownID", AbilId.class);
        public final static State<War3Bool> DATA_IGNORE_COOLDOWN = new State<>("ignoreCD", War3Bool.class);
        public final static State<War3Int> DATA_CHARGES = new State<>("uses", War3Int.class);
        public final static State<War3Int> DATA_PRIO = new State<>("prio", War3Int.class);
        public final static State<War3Bool> DATA_USABLE = new State<>("usable", War3Bool.class);
        public final static State<War3Bool> DATA_PERISHABLE = new State<>("perishable", War3Bool.class);
        public final static State<War3Bool> DATA_DROPPABLE = new State<>("droppable", War3Bool.class);
        public final static State<War3Bool> DATA_PAWNABLE = new State<>("pawnable", War3Bool.class);
        public final static State<War3Bool> DATA_SELLABLE = new State<>("sellable", War3Bool.class);
        public final static State<War3Bool> DATA_USE_IN_RANDOM = new State<>("pickRandom", War3Bool.class);
        public final static State<War3Bool> DATA_POWERUP = new State<>("powerup", War3Bool.class);
        public final static State<War3Bool> DATA_DROPPED = new State<>("drop", War3Bool.class);
        public final static State<War3Int> DATA_STOCK_REGEN = new State<>("stockMax", War3Int.class);
        public final static State<War3Int> DATA_STOCK_MAX = new State<>("stockRegen", War3Int.class);
        public final static State<War3Int> DATA_STOCK_INITIAL = new State<>("stockStart", War3Int.class);
        public final static State<War3Int> DATA_COSTS_GOLD = new State<>("goldcost", War3Int.class);
        public final static State<War3Int> DATA_COSTS_LUMBER = new State<>("lumbercost", War3Int.class);
        public final static State<War3Int> DATA_LIFE = new State<>("HP", War3Int.class);
        public final static State<War3Bool> DATA_MORPHABLE = new State<>("morph", War3Bool.class);
        public final static State<ArmorSound> SOUND_ARMOR = new State<>("armor", ArmorSound.class);
        public final static State<Model> ART_MODEL = new State<>("file", Model.class);
        public final static State<War3Real> ART_SCALE = new State<>("scale", War3Real.class);
        public final static State<War3Real> ART_SELECTION_SCALE = new State<>("selSize", War3Real.class);
        public final static State<War3Int> ART_COLOR_RED = new State<>("colorB", War3Int.class);
        public final static State<War3Int> ART_COLOR_BLUE = new State<>("colorR", War3Int.class);
        public final static State<War3Int> ART_COLOR_GREEN = new State<>("colorG", War3Int.class);
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

    public static class Obj extends SLK.Obj<ItemId> {
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

        public <T extends DataType> T get(State<T> state) {
            try {
                if (!super.contains(state.getFieldId())) return state.getDefVal();

                return state.tryCastVal(super.get(state.getFieldId()));
            } catch (DataTypeInfo.CastException ignored) {
            }

            return null;
        }

        public <T extends DataType> void set(State<T> state, T val) {
            super.set(state.getFieldId(), val);
        }

        private void read(SLK.Obj<? extends ObjId> slkObj) {
            merge(slkObj, true);
        }

        public Obj(SLK.Obj<? extends ObjId> slkObj) {
            super(ItemId.valueOf(slkObj.getId()));

            read(slkObj);
        }

        public Obj(ItemId id) {
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

    //private Map<ItemId, Camera> _objs = new LinkedHashMap<>();

    /*@Override
    public Map<ItemId, Camera> getCameras() {
        return _objs;
    }

    @Override
    public void addCamera(Camera val) {
        _objs.put(val.getId(), val);
    }

    @Override
    public Camera addCamera(ItemId id) {
        if (_objs.containsKey(id)) return _objs.get(id);

        Camera obj = new Camera(id);

        addCamera(obj);

        return obj;
    }*/

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

    public ItemSLK(SLK slk) {
        read(slk);
    }

    public ItemSLK() {
        super();

        addField(State.OBJ_ID);

        for (State state : State.values(State.class)) {
            addField(state);
        }
    }

    public ItemSLK(File file) throws IOException {
        this();

        read(file);
    }

    @Nonnull
    @Override
    public Obj createObj(@Nonnull ObjId id) {
        return new Obj(ItemId.valueOf(id));
    }

    @Override
    public void merge(@Nonnull ItemSLK other, boolean overwrite) {
        for (Map.Entry<ItemId, Obj> objEntry : other.getObjs().entrySet()) {
            ItemId objId = objEntry.getKey();
            Obj otherObj = objEntry.getValue();

            Obj obj = addObj(objId);

            obj.merge(otherObj);
        }
    }
}