package net.moonlightflower.wc3libs.slk.app.objs;

import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.ObjSLK;
import net.moonlightflower.wc3libs.slk.SLK;
import net.moonlightflower.wc3libs.slk.SLKState;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DestructableSLK extends ObjSLK<DestructableSLK, DestructableId, DestructableSLK.Obj> {
    public final static File GAME_USE_PATH = new File("Units\\DestructableData.slk");

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

        public final static State<DestructableId> OBJ_ID = new State<>("DestructableID", DestructableId.class);

        static {
            new State<>("category", Wc3String.class);
            new State<>("tilesets", new DataTypeInfo(DataList.class, Tileset.class));
            new State<>("tilesetSpecific", Bool.class);
            new State<>("file", Model.class);
            new State<>("lightweight", Bool.class);
            new State<>("fatLOS", Bool.class);
            new State<>("texID", Int.class);
            new State<>("texFile", Wc3String.class);
            new State<>("comment", Wc3String.class);
            new State<>("Name", Wc3String.class);
            new State<>("EditorSuffix", Wc3String.class);
            new State<>("doodClass", DoodadClass.class);
            new State<>("useClickHelper", Bool.class);
            new State<>("onCliffs", Bool.class);
            new State<>("onWater", Bool.class);
            new State<>("canPlaceDead", Bool.class);
            new State<>("colorB", Int.class);
            new State<>("walkable", Bool.class);
            new State<>("cliffHeight", Int.class);
            new State<>("targType", new DataTypeInfo(DataList.class, CombatTarget.class));
            new State<>("armor", DefType.class);
            new State<>("numVar", Int.class);
            new State<>("HP", Int.class);
            new State<>("occH", Real.class); // Occlusion Height
            new State<>("flyH", Real.class); // Occlusion Height
            new State<>("fixedRot", Real.class);
            new State<>("selSize", Real.class);
            new State<>("minScale", Real.class);
            new State<>("maxScale", Real.class);
            new State<>("canPlaceRandScale", Bool.class);
            new State<>("maxPitch", Real.class);
            new State<>("maxRoll", Real.class);
            new State<>("radius", Real.class);
            new State<>("fogRadius", Real.class);
            new State<>("fogVis", Bool.class);
            new State<>("pathTex", PathingTex.class);
            new State<>("pathTexDeath", PathingTex.class);
            new State<>("deathSnd", SoundLabel.class);
            new State<>("shadow", ShadowTex.class);
            new State<>("colorR", Int.class);
            new State<>("colorG", Int.class);
            new State<>("colorB", Int.class);
            new State<>("showInMM", Bool.class);
            new State<>("useMMColor", Bool.class);
            new State<>("MMRed", Int.class);
            new State<>("MMGreen", Int.class);
            new State<>("MMBlue", Int.class);
            new State<>("buildTime", Int.class);
            new State<>("repairTime", Int.class);
            new State<>("goldRep", Int.class);
            new State<>("lumberRep", Int.class);
            new State<>("UserList", Bool.class);
            new State<>("InBeta", Bool.class);
            new State<>("version", Int.class);
            new State<>("selectable", Bool.class);
            new State<>("selcircsize", Real.class);
            new State<>("portraitmodel", Model.class);
        }

    }

    public static class Obj extends SLK.Obj<DestructableId> {
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
            this(DestructableId.valueOf(slkObj.getId()));

            read(slkObj);
        }

        public Obj(DestructableId id) {
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

    //private Map<DestructableId, Obj> _objs = new LinkedHashMap<>();

    @Override
    public Map<DestructableId, Obj> getObjs() {
        return _objs;
    }

    @Override
    public void addObj(Obj val) {
        _objs.put(val.getId(), val);
    }

    @Override
    public Obj addObj(DestructableId id) {
        if (_objs.containsKey(id)) return _objs.get(id);

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

    public DestructableSLK(SLK slk) {
        read(slk);
    }

    public DestructableSLK() {
        super();

        for (States.State state : States.values()) {
            addField(state);
        }
    }

    public DestructableSLK(File file) throws IOException {
        this();

        read(file);
    }

    @Override
    public Obj createObj(ObjId id) {
        return new Obj(DestructableId.valueOf(id));
    }

    @Override
    public void merge(DestructableSLK other, boolean overwrite) {
        for (Map.Entry<DestructableId, Obj> objEntry : other.getObjs().entrySet()) {
            DestructableId objId = objEntry.getKey();
            Obj otherObj = objEntry.getValue();

            Obj obj = addObj(objId);

            obj.merge(otherObj);
        }
    }
}
