package net.moonlightflower.wc3libs.slk;

import net.moonlightflower.wc3libs.antlr.SLKLexer;
import net.moonlightflower.wc3libs.antlr.SLKParser;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.app.War3Int;
import net.moonlightflower.wc3libs.dataTypes.app.War3Real;
import net.moonlightflower.wc3libs.dataTypes.app.War3String;
import net.moonlightflower.wc3libs.misc.*;
import net.moonlightflower.wc3libs.slk.app.doodads.DoodSLK;
import net.moonlightflower.wc3libs.slk.app.objs.*;
import net.moonlightflower.wc3libs.txt.UTF8;
import org.antlr.v4.runtime.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.util.*;

public abstract class SLK<Self extends SLK<Self, ObjIdType, ObjType>, ObjIdType extends ObjId, ObjType extends SLK.Obj<? extends ObjIdType>> implements
        Mergeable<Self>, Printable {
    @Override
    public void print(@Nonnull Printer printer) {
        printer.beginGroup("slk");

        printer.println("pivotField " + getPivotField());

        printer.beginGroup("fields");
        for (Field field : getFields().values()) {
            //printer.beginGroup(field.getFieldId());

            field.print(printer);

            //printer.endGroup();
        }
        printer.endGroup();

        printer.beginGroup("objs");
        for (Obj obj : getObjs().values()) {
            obj.print(printer);
        }
        printer.endGroup();

        printer.endGroup();
    }

    public static class Field implements Printable {
        private FieldId _fieldId;

        public FieldId getFieldId() {
            return _fieldId;
        }

        private final DataType _defVal;

        @Nullable
        public DataType getDefVal() {
            return _defVal;
        }

        public Field(@Nonnull FieldId fieldId, @Nullable DataType defVal) {
            _fieldId = fieldId;
            _defVal = defVal;
        }

        @Override
        public void print(@Nonnull Printer printer) {
            printer.println(_fieldId + " (" + _defVal + ")");
        }
    }

    private final Map<FieldId, Field> _fields = new LinkedHashMap<>();

    private FieldId _pivotField = null;

    @Nonnull
    public Map<FieldId, Field> getFields() {
        return _fields;
    }

    public boolean containsField(@Nonnull FieldId field) {
        return _fields.containsKey(field);
    }

    @Nullable
    public FieldId getPivotField() {
        return _pivotField;
    }

    public void addField(@Nonnull FieldId fieldId, @Nullable DataType defVal) {
        Field field = new Field(fieldId, defVal);

        _fields.putIfAbsent(fieldId, field);

        if (_pivotField == null) _pivotField = fieldId;
    }

    public void addField(@Nonnull SLKState state, @Nullable DataType defVal) {
        addField(state.getFieldId(), defVal);
    }

    public void addField(@Nonnull FieldId field) {
        addField(field, null);
    }

    public void addField(@Nonnull SLKState state) {
        addField(state.getFieldId());
    }

    public abstract static class Obj<T extends ObjId> {
        private final Map<FieldId, DataType> _vals = new LinkedHashMap<>();

        @Nonnull
        public Map<FieldId, DataType> getVals() {
            return _vals;
        }

        private final Map<SLKState, DataType> _stateVals = new LinkedHashMap<>();

        public abstract Map<? extends SLKState, DataType> getStateVals();

        protected abstract void on_set(@Nonnull FieldId fieldId, @Nullable DataType val);
        protected abstract void on_remove(@Nonnull FieldId fieldId);
        protected abstract void on_clear();

        public boolean contains(@Nonnull FieldId field) {
            return _vals.containsKey(field);
        }

        public DataType get(@Nonnull FieldId field) {
            return _vals.get(field);
        }

        public DataType get(@Nonnull FieldId field, @Nonnull DataType defVal) {
            if (!_vals.containsKey(field)) return defVal;

            return _vals.get(field);
        }

        public DataType get(@Nonnull SLKState state) {
            return _vals.get(state.getFieldId());
        }

        public DataType get(@Nonnull String fieldS) {
            return get(FieldId.valueOf(fieldS));
        }

        public String getS(@Nonnull FieldId field) {
            if (!contains(field)) return null;

            return get(field).toString();
        }

        public void set(@Nonnull FieldId fieldId, DataType val) {
            _vals.put(fieldId, val);

            on_set(fieldId, val);
        }

        public void remove(@Nonnull FieldId fieldId) {
            _vals.remove(fieldId);

            on_remove(fieldId);
        }

        public void clear() {
            _vals.clear();

            on_clear();
        }

        public void set(@Nonnull SLKState state, DataType val) {
            set(state.getFieldId(), val);
            _stateVals.put(state, val);
        }

        public void setRaw(@Nonnull FieldId field, String val) {
            set(field, War3String.valueOf(val));
        }

        public void merge(@Nonnull Obj<? extends ObjId> otherObj, boolean overwrite) {
            for (Map.Entry<FieldId, DataType> entry : otherObj.getVals().entrySet()) {
                FieldId field = entry.getKey();
                DataType val = entry.getValue();
                //if (otherObj.getId().equals(Id.valueOf("nmer"))) System.out.println(field + "->" + val);
                if (overwrite || (get(field) == null)) {
                    if (val != null) {
                        set(field, val);
                    }
                }
            }
        }

        public void merge(@Nonnull Obj<? extends ObjId> otherObj) {
            merge(otherObj, true);
        }

        public void print(@Nonnull Printer printer) {
            printer.beginGroup(_id);

            for (Map.Entry<FieldId, DataType> valEntry : getVals().entrySet()) {
                FieldId fieldId = valEntry.getKey();
                DataType val = valEntry.getValue();

                printer.println(fieldId + " -> " + val);
            }

            printer.endGroup();
        }

        public abstract void reduce();

        private T _id;

        @Nonnull
        public T getId() {
            return _id;
        }

        @Override
        public String toString() {
            return _id.toString();
        }

        public Obj(@Nonnull T id) {
            _id = id;
        }
    }

    protected final Map<ObjIdType, ObjType> _objs = new LinkedHashMap<>();

    public boolean containsObj(@Nonnull ObjIdType objId) {
        return _objs.containsKey(objId);
    }

    @Nonnull
    public Map<ObjIdType, ObjType> getObjs() {
        return _objs;
    }

    @Nullable
    public ObjType getObj(@Nonnull ObjIdType id) {
        return getObjs().get(id);
    }

    public void addObj(@Nonnull ObjType val) {
        _objs.put(val.getId(), val);
    }

    public void removeObj(@Nonnull ObjType val) {
        _objs.remove(val.getId());
    }

    public void removeObj(@Nonnull ObjIdType id) {
        if (_objs.containsKey(id)) removeObj(_objs.get(id));
    }

    public void clearObjs() {
        _objs.clear();
    }

    @Nonnull
    protected abstract ObjType createObj(@Nonnull ObjId id);

    @Nonnull
    public ObjType addObj(@Nonnull ObjIdType id) {
        if (getObjs().containsKey(id)) return getObj(id);

        ObjType obj = createObj(id);

        addObj(obj);

        return obj;
    }

    public void merge(@Nonnull Self other, boolean overwrite) {
        if (overwrite) {
            _pivotField = other.getPivotField();
        }

        for (Map.Entry<FieldId, Field> fieldEntry : other.getFields().entrySet()) {
            FieldId fieldId = fieldEntry.getKey();
            Field field = fieldEntry.getValue();

            if (!containsField(fieldId)) {
                addField(fieldId, field.getDefVal());
            }
        }

        for (ObjType otherObj : other.getObjs().values()) {
            ObjType obj = addObj(otherObj.getId());

            obj.merge(otherObj, overwrite);
        }
    }

    @Override
    public void merge(@Nonnull Self other) {
        merge(other, true);
    }

    //protected abstract ObjIdType readObj(ObjId id);

    protected abstract void read(@Nonnull SLK<?, ? extends ObjId, ? extends SLK.Obj<? extends ObjId>> slk);

    public void cleanEmptyColumns() {
        HashMap<String, Integer> countMap = new HashMap<>();
        _fields.keySet().forEach(key -> countMap.put(key.toString(), 0));
        _objs.values().forEach(obj -> obj.getVals().forEach((k, v) -> {
            if (v != null && !v.toSLKVal().toString().isEmpty() && countMap.containsKey(k.toString())) {
                DataType defVal = v.getTypeInfo().getDefVal();
                if(defVal != null && !defVal.toSLKVal().toString().equals(v.toSLKVal().toString())) {
                    Integer val = countMap.get(k.toString());
                    countMap.put(k.toString(), val + 1);
                }
            }
        }));

        countMap.values().removeIf(count -> count != null && count > 0);

        _fields.entrySet().removeIf(entry -> countMap.containsKey(entry.getKey().toString()));
    }

    private static class RecordPart {
        private String _attr;
        private DataType _val;

        private RecordPart(@Nonnull String s) {
            if (s.startsWith("X") || s.startsWith("Y") || s.startsWith("K")) {
                _attr = s.substring(0, 1);

                String valS = s.substring(1);

                if (valS.startsWith("\"") && valS.endsWith("\"")) {
                    _val = War3String.valueOf(valS.substring(1, s.length() - 2));
                } else {
                    try {
                        _val = War3Int.valueOf(Integer.parseInt(valS));
                    } catch (NumberFormatException ignored) {
                        try {
                            _val = War3Real.valueOf(Float.parseFloat(valS));
                        } catch (NumberFormatException ignored2) {
                            _val = War3String.valueOf(valS);
                        }
                    }
                }
            } else {
                _attr = s;
                _val = null;
            }
        }
    }

    public void read(@Nonnull File file, boolean onlyHeader) throws IOException {
        InputStream inStream = new FileInputStream(file);

        UTF8 reader = new UTF8(inStream, false, true);

        String input = reader.readAll(false);

        inStream.close();

        CharStream antlrStream = CharStreams.fromString(input);

        Lexer lexer = new SLKLexer(antlrStream);

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        tokenStream.fill();

        List<Token> tokens = tokenStream.getTokens();

        SLKParser parser = new SLKParser(new CommonTokenStream(new ListTokenSource(tokens)));

        SLKParser.RootContext rootContext = parser.root();

        boolean foundB = false;

        int maxX = 0;
        int maxY = 0;

        //System.out.println(tokens);

        for (SLKParser.RecordContext record : rootContext.record()) {
            if (record.type.getText().equals("B")) {
                int x = 0;
                int y = 0;

                for (SLKParser.RecordPartContext part : record.recordPart()) {
                    RecordPart partData = new RecordPart(part.getText());

                    if (partData._attr.equals("X")) {
                        if (partData._val instanceof War3Int) {
                            x = ((War3Int) partData._val).toInt();
                        }
                    }
                    if (partData._attr.equals("Y")) {
                        if (partData._val instanceof War3Int) {
                            y = ((War3Int) partData._val).toInt();
                        }
                    }
                }

                maxX = x;
                maxY = y;
                //System.out.println("max " + maxX+";"+maxY);
                foundB = true;
            }
        }

        if (!foundB) throw new IllegalStateException("did not find B record");

        DataType[][] data = new DataType[maxY][maxX];

        int curX = 0;
        int curY = 0;
        //System.out.println("size " + rootContext.record().size());
        for (SLKParser.RecordContext record : rootContext.record()) {
            if (record.type.getText().equals("C")) {
                int x = curX;
                int y = curY;
                DataType val = null;

                for (SLKParser.RecordPartContext part : record.recordPart()) {
                    RecordPart partData = new RecordPart(part.getText());

                    if (partData._attr.equals("X") && partData._val instanceof War3Int) {
                        x = ((War3Int) partData._val).toInt() - 1;
                    }
                    if (partData._attr.equals("Y") && partData._val instanceof War3Int) {
                        y = ((War3Int) partData._val).toInt() - 1;
                    }
                    if (partData._attr.equals("K")) {
                        val = partData._val;
                    }
                }
                //System.out.println("set " + x + ";" + y + "->" + val);
                if (val != null) data[y][x] = val;

                if (x > maxX) maxX = x;
                if (y > maxY) maxY = y;

                curX = x;
                curY = y;
            }
        }

        _fields.clear();

        DataType[] headerData = data[0];

        for (DataType fieldRaw : headerData) {
            if (fieldRaw == null) continue;

            FieldId field = FieldId.valueOf(fieldRaw.toString());

            if (!_fields.containsKey(field)) addField(field);
        }

        _pivotField = FieldId.valueOf(headerData[0].toString());

        if (onlyHeader) return;

        for (int i = 1; i < data.length; i++) {
            DataType objIdRaw = data[i][0];

            if (objIdRaw == null) continue;

            ObjId objId = ObjId.valueOf(objIdRaw.toString());

            ObjType obj = createObj(objId);

            addObj(obj);

            for (int j = 1; j < headerData.length; j++) {
                DataType fieldRaw = headerData[j];

                if (fieldRaw == null) continue;

                FieldId field = FieldId.valueOf(fieldRaw.toString());

                if (field.equals(_pivotField)) continue;

                obj.set(field, data[i][j]);
            }
        }
    }

    public void read(@Nonnull File file) throws IOException {
        read(file, false);
    }

    public class Writer {
        private File _file;

        BufferedWriter _writer;
        private int _slkCurX;
        private int _slkCurY;

        @Nullable
        private Object formatVal(@Nullable Object val) {
            if (val == null) return null;

            try {
                return Integer.parseInt(val.toString());
            } catch (NumberFormatException ignored) {
            }
            try {
                return Float.parseFloat(val.toString());
            } catch (NumberFormatException ignored) {
            }
            try {
                return Double.parseDouble(val.toString());
            } catch (NumberFormatException ignored) {
            }

            if (val instanceof Boolean) {
                if ((Boolean) val) return 1;
            } else if (val instanceof Integer) {
                if (((Integer) val) != 0) return val;
            } else if (val instanceof Float) {
                if (((Float) val) != 0F) return val;
            } else if (val instanceof Double) {
                if (((Double) val) != 0D) return val;
            } else {
                val = val.toString();

                if (val.toString().isEmpty()) return null;
                if (val.equals("")) return null;
                if (val.equals("\"\"")) return null;

                return "\"" + val + "\"";
            }

            return null;
        }

        private void writeCell(int x, int y, @Nullable DataType slkVal) throws IOException {
            if (slkVal == null) return;

            Object val = slkVal.toSLKVal();

            if (val == null) return;

            val = formatVal(val);

            if (val == null) return;

            List<String> t = new ArrayList<>();

            t.add("C");

            if (x != _slkCurX) {
                t.add("X" + x);

                _slkCurX = x;
            }

            if (y != _slkCurY) {
                t.add("Y" + y);

                _slkCurY = y;
            }

            t.add("K" + val.toString());

            _writer.newLine();

            _writer.write(String.join(";", t));
        }

        public void exec() throws IOException {
            if (_pivotField == null) throw new RuntimeException("pivotField is null");

            if (_file.getParentFile() != null) _file.getParentFile().mkdirs();

            _writer = new BufferedWriter(new FileWriter(_file));

            _writer.write("ID;PWXL;N;E");

            _writer.newLine();

            _writer.write("B;X" + (_fields.size() + (_fields.containsKey(_pivotField) ? 0 : 1)) + ";Y" + (_objs.size() + 1) + ";D0");

            Map<FieldId, Integer> fieldX = new LinkedHashMap<>();
            Map<Integer, FieldId> fieldsByX = new LinkedHashMap<>();

            fieldX.put(_pivotField, 1);

            int fieldC = 1;

            for (FieldId field : _fields.keySet()) {
                if (field.equals(_pivotField)) continue;

                fieldC++;

                fieldX.put(field, fieldC);
                fieldsByX.put(fieldC, field);
            }

            _slkCurX = 0;
            _slkCurY = 0;

            writeCell(1, 1, War3String.valueOf(_pivotField.toString()));

            for (Map.Entry<Integer, FieldId> entry : fieldsByX.entrySet()) {
                writeCell(entry.getKey(), 1, War3String.valueOf(entry.getValue().toString()));
            }

            int y = 1;

            for (Map.Entry<ObjIdType, ? extends ObjType> entry : getObjs().entrySet()) {
                ObjId objId = entry.getKey();
                ObjType obj = entry.getValue();

                y++;

                writeCell(1, y, objId);

                for (Map.Entry<Integer, FieldId> fieldEntry : fieldsByX.entrySet()) {
                    int x = fieldEntry.getKey();
                    FieldId field = fieldEntry.getValue();

                    DataType val = obj.get(field);

                    if (val == null) val = _fields.get(field).getDefVal();

                    if (val == null) continue;

                    writeCell(x, y, val);
                }
            }

            _writer.newLine();

            _writer.write("E\n");

            _writer.close();
        }

        public Writer(@Nonnull File file) {
            _file = file;
        }
    }

	/*public void write(Writer writer) {
		
	}*/

    public void write(@Nonnull File file) throws IOException {
        new Writer(file).exec();
    }

    public SLK(@Nonnull File file) throws IOException {
        this();

        read(file);
    }

    public SLK() {
    }

    @Nullable
    public static SLK createFromInFile(@Nonnull File inFile, @Nonnull File outFile) throws IOException {
        SLK slk = null;

        if (inFile.equals(DoodSLK.GAME_PATH)) {
            slk = new DoodSLK(outFile);
        }

        if (inFile.equals(UnitAbilsSLK.GAME_PATH)) {
            slk = new UnitAbilsSLK(outFile);
        }
        if (inFile.equals(UnitBalanceSLK.GAME_PATH)) {
            slk = new UnitBalanceSLK(outFile);
        }
        if (inFile.equals(UnitDataSLK.GAME_PATH)) {
            slk = new UnitDataSLK(outFile);
        }
        if (inFile.equals(UnitUISLK.GAME_PATH)) {
            slk = new UnitUISLK(outFile);
        }
        if (inFile.equals(UnitWeaponsSLK.GAME_PATH)) {
            slk = new UnitWeaponsSLK(outFile);
        }

        if (inFile.equals(ItemSLK.GAME_PATH)) {
            slk = new ItemSLK(outFile);
        }
        if (inFile.equals(DestructableSLK.GAME_PATH)) {
            slk = new DestructableSLK(outFile);
        }
        if (inFile.equals(AbilSLK.GAME_PATH)) {
            slk = new AbilSLK(outFile);
        }
        if (inFile.equals(BuffSLK.GAME_PATH)) {
            slk = new BuffSLK(outFile);
        }
        if (inFile.equals(UpgradeSLK.GAME_PATH)) {
            slk = new UpgradeSLK(outFile);
        }

        return slk;
    }

    @Nullable
    public static SLK createFromInFile(@Nonnull File inFile, @Nonnull SLK sourceSlk) {
        SLK slk = null;

        if (inFile.equals(DoodSLK.GAME_PATH)) {
            slk = new DoodSLK(sourceSlk);
        }

        if (inFile.equals(UnitAbilsSLK.GAME_PATH)) {
            slk = new UnitAbilsSLK(sourceSlk);
        }
        if (inFile.equals(UnitBalanceSLK.GAME_PATH)) {
            slk = new UnitBalanceSLK(sourceSlk);
        }
        if (inFile.equals(UnitDataSLK.GAME_PATH)) {
            slk = new UnitDataSLK(sourceSlk);
        }
        if (inFile.equals(UnitUISLK.GAME_PATH)) {
            slk = new UnitUISLK(sourceSlk);
        }
        if (inFile.equals(UnitWeaponsSLK.GAME_PATH)) {
            slk = new UnitWeaponsSLK(sourceSlk);
        }

        if (inFile.equals(ItemSLK.GAME_PATH)) {
            slk = new ItemSLK(sourceSlk);
        }
        if (inFile.equals(DestructableSLK.GAME_PATH)) {
            slk = new DestructableSLK(sourceSlk);
        }
        if (inFile.equals(AbilSLK.GAME_PATH)) {
            slk = new AbilSLK(sourceSlk);
        }
        if (inFile.equals(BuffSLK.GAME_PATH)) {
            slk = new BuffSLK(sourceSlk);
        }
        if (inFile.equals(UpgradeSLK.GAME_PATH)) {
            slk = new UpgradeSLK(sourceSlk);
        }

        return slk;
    }

    @Nullable
    public static SLK createFromInFile(@Nonnull File inFile) {
        SLK slk = null;

        if (inFile.equals(DoodSLK.GAME_PATH)) {
            slk = new DoodSLK();
        }

        if (inFile.equals(UnitAbilsSLK.GAME_PATH)) {
            slk = new UnitAbilsSLK();
        }
        if (inFile.equals(UnitBalanceSLK.GAME_PATH)) {
            slk = new UnitBalanceSLK();
        }
        if (inFile.equals(UnitDataSLK.GAME_PATH)) {
            slk = new UnitDataSLK();
        }
        if (inFile.equals(UnitUISLK.GAME_PATH)) {
            slk = new UnitUISLK();
        }
        if (inFile.equals(UnitWeaponsSLK.GAME_PATH)) {
            slk = new UnitWeaponsSLK();
        }

        if (inFile.equals(ItemSLK.GAME_PATH)) {
            slk = new ItemSLK();
        }
        if (inFile.equals(DestructableSLK.GAME_PATH)) {
            slk = new DestructableSLK();
        }
        if (inFile.equals(AbilSLK.GAME_PATH)) {
            slk = new AbilSLK();
        }
        if (inFile.equals(BuffSLK.GAME_PATH)) {
            slk = new BuffSLK();
        }
        if (inFile.equals(UpgradeSLK.GAME_PATH)) {
            slk = new UpgradeSLK();
        }

        return slk;
    }
}
