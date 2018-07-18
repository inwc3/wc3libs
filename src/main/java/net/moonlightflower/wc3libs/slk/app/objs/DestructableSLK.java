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
import java.util.*;
import java.util.Map.Entry;

public class DestructableSLK extends ObjSLK<DestructableSLK, DestructableId, DestructableSLK.Obj> {
    public final static File GAME_PATH = new File("Units\\DestructableData.slk");

    public static class State<T extends DataType> extends ObjSLK.State<T> {
        public final static State<DestructableId> OBJ_ID = new State<>("DestructableID", DestructableId.class);
        public final static State<War3String> CATEGORY = new State<>("category", War3String.class);
        public final static State<DataList<Tileset>> TILESETS = new State<>("tilesets", new DataTypeInfo(DataList.class, Tileset.class));
        public final static State<War3Bool> TILESET_SPECIFIC = new State<>("tilesetSpecific", War3Bool.class);
        public final static State<Model> MODEL = new State<>("file", Model.class);
        public final static State<War3Bool> LIGHTWEIGHT = new State<>("lightweight", War3Bool.class);
        public final static State<War3Bool> FAT_LINE_OF_SIGHT = new State<>("fatLOS", War3Bool.class);
        public final static State<War3Int> TEX_ID = new State<>("texID", War3Int.class);
        public final static State<War3String> TEX_FILE = new State<>("texFile", War3String.class);
        public final static State<War3String> COMMENT = new State<>("comment", War3String.class);
        public final static State<War3String> NAME = new State<>("Name", War3String.class);
        public final static State<War3String> EDITOR_SUFFIX = new State<>("EditorSuffix", War3String.class);
        public final static State<DoodadClass> DOOD_CLASS = new State<>("doodClass", DoodadClass.class);
        public final static State<War3Bool> USE_CLICK_HELPER = new State<>("useClickHelper", War3Bool.class);
        public final static State<War3Bool> ON_CLIFFS = new State<>("onCliffs", War3Bool.class);
        public final static State<War3Bool> ON_WATER = new State<>("onWater", War3Bool.class);
        public final static State<War3Bool> CAN_PLACE_DEAD = new State<>("canPlaceDead", War3Bool.class);
        public final static State<War3Bool> WALKABLE = new State<>("walkable", War3Bool.class);
        public final static State<War3Int> CLIFF_HEIGHT = new State<>("CLIFF_HEIGHT", War3Int.class);
        public final static State<DataList<CombatTarget>> TARGS = new State<>("targType", new DataTypeInfo(DataList.class, CombatTarget.class));
        public final static State<DefType> ARMOR_TYPE = new State<>("armor", DefType.class);
        public final static State<War3Int> VARIATION_COUNT = new State<>("numVar", War3Int.class);
        public final static State<War3Int> LIFE = new State<>("HP", War3Int.class);
        public final static State<War3Real> OCCLUSION_HEIGHT = new State<>("occH", War3Real.class);
        public final static State<War3Real> FLY_HEIGHT = new State<>("flyH", War3Real.class);
        public final static State<War3Real> FIXED_ROTATION = new State<>("fixedRot", War3Real.class);
        public final static State<War3Real> SELECTION_SCALE_EDITOR = new State<>("selSize", War3Real.class);
        public final static State<War3Real> SCALE_MIN = new State<>("minScale", War3Real.class);
        public final static State<War3Real> SCALE_MAX = new State<>("maxScale", War3Real.class);
        public final static State<War3Bool> CAN_PLACE_RANDOM_SCALE = new State<>("canPlaceRandScale", War3Bool.class);
        public final static State<War3Real> PITCH_MAX = new State<>("maxPitch", War3Real.class);
        public final static State<War3Real> ROLL_MAX = new State<>("maxRoll", War3Real.class);

        public final static State<War3Real> ELEV_RAD = new State<>("radius", War3Real.class);
        public final static State<War3Real> FOG_RAD = new State<>("fogRadius", War3Real.class);
        public final static State<War3Bool> FOG_VISIBILITY = new State<>("fogVis", War3Bool.class);
        public final static State<PathingTex> PATH_TEX = new State<>("pathTex", PathingTex.class);
        public final static State<PathingTex> PATH_TEX_DEAD = new State<>("pathTexDeath", PathingTex.class);
        public final static State<SoundLabel> SOUND_DEATH = new State<>("deathSnd", SoundLabel.class);

        public final static State<ShadowTex> SHADOW_TEX = new State<>("shadow", ShadowTex.class);
        public final static State<War3Bool> MINIMAP_SHOW = new State<>("showInMM", War3Bool.class);
        public final static State<War3Bool> MINIMAP_USE_COLOR = new State<>("useMMColor", War3Bool.class);
        public final static State<War3Int> MINIMAP_COLOR_RED = new State<>("MMRed", War3Int.class);
        public final static State<War3Int> MINIMAP_COLOR_GREEN = new State<>("MMGreen", War3Int.class);
        public final static State<War3Int> MINIMAP_COLOR_BLUE = new State<>("MMBlue", War3Int.class);
        public final static State<War3Int> COLOR_RED = new State<>("colorR", War3Int.class);
        public final static State<War3Int> COLOR_GREEN = new State<>("colorG", War3Int.class);
        public final static State<War3Int> COLOR_BLUE = new State<>("colorB", War3Int.class);
        public final static State<War3Int> BUILD_TIME = new State<>("buildTime", War3Int.class);
        public final static State<War3Int> REPAIR_TIME = new State<>("repairTime", War3Int.class);
        public final static State<War3Int> COSTS_GOLD_REPAIR = new State<>("goldRep", War3Int.class);
        public final static State<War3Int> COSTS_LUMBER_REPAIR = new State<>("lumberRep", War3Int.class);
        public final static State<War3Bool> IN_USER_LIST = new State<>("UserList", War3Bool.class);
        public final static State<War3Bool> IN_BETA = new State<>("InBeta", War3Bool.class);
        public final static State<War3Int> VERSION = new State<>("version", War3Int.class);
        public final static State<War3Bool> SELECTABLE = new State<>("selectable", War3Bool.class);
        public final static State<War3Real> SELECTION_SCALE_GAME = new State<>("selcircsize", War3Real.class);
        public final static State<Model> MODEL_PORTRAIT = new State<>("portraitmodel", Model.class);

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

    public static class Obj extends SLK.Obj<DestructableId> {
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
            super(DestructableId.valueOf(slkObj.getId()));

            read(slkObj);
        }

        public Obj(DestructableId id) {
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

    //private Map<DestructableId, Camera> _objs = new LinkedHashMap<>();

    @Nonnull
    @Override
    public Map<DestructableId, Obj> getObjs() {
        return _objs;
    }

    @Override
    public void addObj(@Nonnull Obj val) {
        _objs.put(val.getId(), val);
    }

    @Nonnull
    @Override
    public Obj addObj(@Nonnull DestructableId id) {
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

    public DestructableSLK(SLK slk) {
        read(slk);
    }

    public DestructableSLK() {
        super();

        addField(State.OBJ_ID);

        for (State state : State.values(State.class)) {
            addField(state);
        }
    }

    public DestructableSLK(File file) throws IOException {
        this();

        read(file);
    }

    @Nonnull
    @Override
    public Obj createObj(@Nonnull ObjId id) {
        return new Obj(DestructableId.valueOf(id));
    }

    @Override
    public void merge(@Nonnull DestructableSLK other, boolean overwrite) {
        for (Map.Entry<DestructableId, Obj> objEntry : other.getObjs().entrySet()) {
            DestructableId objId = objEntry.getKey();
            Obj otherObj = objEntry.getValue();

            Obj obj = addObj(objId);

            obj.merge(otherObj);
        }
    }
}
