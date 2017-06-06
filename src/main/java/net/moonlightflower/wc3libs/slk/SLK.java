package net.moonlightflower.wc3libs.slk;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.app.Bool;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.Real;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.Mergeable;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.app.doodads.DoodSLK;
import net.moonlightflower.wc3libs.slk.app.objs.*;

import java.io.*;
import java.util.*;

public abstract class SLK<Self extends SLK<Self, ObjIdType, ObjType>, ObjIdType extends ObjId, ObjType extends SLK.Obj<? extends ObjIdType>> implements Mergeable<Self>, SLKable {
	public static class FieldData {
		private final DataType _defVal;

		public DataType getDefVal() {
			return _defVal;
		}

		public FieldData(DataType defVal) {
			_defVal = defVal;
		}
	}

	private final Map<FieldId, FieldData> _fields = new LinkedHashMap<>();
	
	private FieldId _pivotField = null;

	public Map<FieldId, FieldData> getFields() {
		return _fields;
	}
	
	public boolean containsField(FieldId field) {
		if (field == null) throw new RuntimeException("field is null");

		return _fields.containsKey(field);
	}
	
	public FieldId getPivotField() {
		return _pivotField;
	}
	
	public void addField(FieldId field, DataType defVal) {
		if (field == null) throw new RuntimeException("field is null");

		FieldData fieldData = new FieldData(defVal);

		_fields.put(field, fieldData);

		if (_pivotField == null) {
			_pivotField = field;
		}
	}
	
	public void addField(SLKState state, DataType defVal) {
		addField(state.getFieldId(), defVal);
	}
	
	public void addField(FieldId field) {
		addField(field, null);
	}
	
	public void addField(SLKState state) {
		addField(state.getFieldId());
	}
	
	public abstract static class Obj<T extends ObjId> {		
		private Map<FieldId, DataType> _vals = new LinkedHashMap<>();
		
		public Map<FieldId, DataType> getVals() {
			return _vals;
		}
		
		public DataType get(FieldId field) {
			return getVals().get(field);
		}

		public DataType get(SLKState state) {
			return getVals().get(state.getFieldId());
		}
		
		public DataType get(String fieldS) {
			return get(FieldId.valueOf(fieldS));
		}
		
		public String getS(FieldId field) {
			return get(field).toString();
		}
		
		public void set(FieldId field, DataType val) {
			_vals.put(field, val);
		}
		
		public void set(SLKState state, DataType val) {
			set(state.getFieldId(), val);
		}
		
		public void setRaw(FieldId field, String val) {
			set(field, Wc3String.valueOf(val));
		}
		
		public void merge(Obj<? extends ObjId> otherObj, boolean overwrite) {
			for (Map.Entry<FieldId, DataType> entry : otherObj.getVals().entrySet()) {
				FieldId field = entry.getKey();
				DataType val = entry.getValue();

				if (overwrite || (get(field) == null)) {
					if (val != null) {
						set(field, val);
					}
				}
			}
		}
		
		public void merge(Obj<? extends ObjId> otherObj) {
			merge(otherObj, true);
		}
		
		public abstract void reduce();
		
		private T _id;
		
		public T getId() {
			return _id;
		}
		
		public Obj(T id) {
			_id = id;
		}
	}
	
	protected Map<ObjIdType, ObjType> _objs = new LinkedHashMap<>();
	
	public Map<ObjIdType, ObjType> getObjs() {
		return _objs;
	}
	
	public ObjType getObj(ObjIdType id) {
		return getObjs().get(id);
	}

	public void addObj(ObjType val) {
		_objs.put(val.getId(), val);
	}

	public void removeObj(ObjType val) {
		_objs.remove(val.getId());
	}
	
	public void removeObj(ObjIdType id) {
		if (_objs.containsKey(id)) removeObj(_objs.get(id));
	}
	
	public void clearObjs() {
		_objs.clear();
	}
	
	protected abstract ObjType createObj(ObjId id);
	
	public ObjType addObj(ObjIdType id) {
		if (getObjs().containsKey(id)) return getObj(id);
		
		ObjType obj = createObj(id);
		
		addObj(obj);
		
		return obj;
	}
	
	public void merge(Self other, boolean overwrite) {
		if (overwrite) {
			_pivotField = other.getPivotField();
		}

		for (Map.Entry<FieldId, FieldData> fieldEntry : other.getFields().entrySet()) {
			FieldId fieldId = fieldEntry.getKey();
			FieldData fieldData = fieldEntry.getValue();
			
			if (!containsField(fieldId)) {
				addField(fieldId, fieldData.getDefVal());
			}
		}
		
		for (ObjType otherObj : other.getObjs().values()) {
			ObjType obj = addObj(otherObj.getId());
			
			obj.merge(otherObj, overwrite);
		}
	}
	
	@Override
	public void merge(Self other) {
		merge(other, true);
	}
	
	//protected abstract ObjIdType readObj(ObjId id);
	
	protected abstract void read(SLK<?, ? extends ObjId, ? extends SLK.Obj<? extends ObjId>> slk);

	public void read(File file, boolean onlyHeader) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line;

		int curX = 0;
		int curY = 0;
		int maxX = 0;
		int maxY = 0;

		Map<Integer, Map<Integer, DataType>> data = new LinkedHashMap<>();

		while ((line = reader.readLine()) != null) {
			line = line.replaceAll("[\u0000-\u001f]", "");

			String[] t = line.split(";");

			if (t[0].equals("C")) {
				DataType val = null;
				int x = 0;
				int y = 0;

				for (int i = 0; i < t.length; i++) {
					String symbol = t[i].substring(0, 1);

					if (symbol.equals("X")) {
						x = Integer.parseInt(t[i].substring(1, t[i].length()));
					}
					if (symbol.equals("Y")) {
						y = Integer.parseInt(t[i].substring(1, t[i].length()));
					}
					if (symbol.equals("K")) {
						boolean skip = false;
						String valS = t[i].substring(1, t[i].length());

						if (valS.substring(0, 1).equals("\"")) {
							valS = valS.substring(1, valS.length() - 1);
							
							val = Wc3String.valueOf(valS);
						} else {
							try {
								val = Int.valueOf(Integer.parseInt(valS));
							} catch (NumberFormatException e) {
								try {
									val = Real.valueOf(Float.parseFloat(valS));
								} catch (NumberFormatException e2) {
								}
							}
							
							if (val == null) {
								if (valS.equals("#VALUE!")) skip = true;
								if (valS.equals("FALSE")) val = Bool.valueOf(false);
								if (valS.equals("TRUE")) val = Bool.valueOf(true);
							}
						}
						
						if (!skip) {
							if (val == null) throw new RuntimeException("could not parse value " + valS);
						}
					}
				}
				
				if (x == 0) x = curX;
				if (y == 0) y = curY;

				if (x > maxX) maxX = x;
				if (y > maxY) maxY = y;

				if (val != null) {
					if (!data.containsKey(y)) data.put(y, new LinkedHashMap<Integer, DataType>());
	
					data.get(y).put(x, val);
				}

				curX = x;
				curY = y;
			}
		}

		reader.close();

		for (Map.Entry<Integer, DataType> entry : data.get(1).entrySet()) {
			FieldId field = FieldId.valueOf(entry.getValue().toString());
			
			addField(field);
		}

		_pivotField = FieldId.valueOf(data.get(1).get(1).toString());
		
		if (onlyHeader) return;

		//for (int i = 2; i < data.size(); i++) {
		for (Integer i : data.keySet()) {
			if (i == 1) continue;
			
			DataType val = data.get(i).get(1);
			
			if (val == null) continue;
			
			ObjId objId = ObjId.valueOf(val.toString());
			
			ObjType obj = createObj(objId);

			if (obj == null) continue;
			
			addObj(obj);

			for (Map.Entry<Integer, DataType> entry : data.get(i).entrySet()) {
				DataType fieldRaw = data.get(1).get(entry.getKey());
				
				if (fieldRaw == null) continue;
				
				FieldId field = FieldId.valueOf(fieldRaw.toString());

				if ((field == null) || (field.equals(_pivotField))) continue;

				obj.set(field, entry.getValue());
			}
		}
	}
	
	public void read(File file) throws IOException {
		read(file, false);
	}
	
	public class Writer {
		private File _file;

		BufferedWriter _writer;
		private int _slkCurX;
		private int _slkCurY;

		private Object formatVal(Object val) {
			if (val == null) return null;
			
			try {
				return Integer.parseInt(val.toString());
			} catch (NumberFormatException e) {
			}
			try {
				return Float.parseFloat(val.toString());
			} catch (NumberFormatException e) {
			}
			try {
				return Double.parseDouble(val.toString());
			} catch (NumberFormatException e) {
			}
			
			if (val instanceof Boolean) {
				if (((Boolean) val).booleanValue()) return 1;
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
				if (val.equals("-")) return null;

				return "\"" + val + "\"";
			}
			
			return null;
		}
		
		private void writeCell(int x, int y, DataType slkVal) throws IOException {			
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

			_file.getParentFile().mkdirs();

			_writer = new BufferedWriter(new FileWriter(_file));

			_writer.write("ID;PWXL;N;E");

			_writer.newLine();

			_writer.write("B;Y" + (_objs.size() + 1) + ";X" + _fields.size() + ";D0");

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
			
			writeCell(1, 1, Wc3String.valueOf(_pivotField.toString()));

			for (Map.Entry<Integer, FieldId> entry : fieldsByX.entrySet()) {
				writeCell(entry.getKey(), 1, Wc3String.valueOf(entry.getValue().toString()));
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
			
			_writer.write("E");

			_writer.close();
		}

		public Writer(File file) {
			_file = file;
		}
	}

	/*public void write(Writer writer) {
		
	}*/
	
	public void write(File file) throws IOException {
		new Writer(file).exec();
	}
	
	public SLK(File file) throws IOException {
		this();
		
		read(file);
	}
	
	public SLK() {
	}
	
	public static SLK createFromInFile(File inFile, File outFile) throws IOException {
		SLK slk = null;
		
		if (inFile.equals(DoodSLK.GAME_USE_PATH)) {
			slk = new DoodSLK(outFile);
		}
		
		if (inFile.equals(UnitAbilsSLK.GAME_USE_PATH)) {
			slk = new UnitAbilsSLK(outFile);
		}
		if (inFile.equals(UnitBalanceSLK.GAME_USE_PATH)) {
			slk = new UnitBalanceSLK(outFile);
		}
		if (inFile.equals(UnitDataSLK.GAME_USE_PATH)) {
			slk = new UnitDataSLK(outFile);
		}
		if (inFile.equals(UnitUISLK.GAME_USE_PATH)) {
			slk = new UnitUISLK(outFile);
		}
		if (inFile.equals(UnitWeaponsSLK.GAME_USE_PATH)) {
			slk = new UnitWeaponsSLK(outFile);
		}
		
		if (inFile.equals(ItemSLK.GAME_USE_PATH)) {
			slk = new ItemSLK(outFile);
		}
		if (inFile.equals(DestructableSLK.GAME_USE_PATH)) {
			slk = new DestructableSLK(outFile);
		}
		if (inFile.equals(AbilSLK.GAME_USE_PATH)) {
			slk = new AbilSLK(outFile);
		}
		if (inFile.equals(BuffSLK.GAME_USE_PATH)) {
			slk = new BuffSLK(outFile);
		}
		if (inFile.equals(UpgradeSLK.GAME_USE_PATH)) {
			slk = new UpgradeSLK(outFile);
		}
		
		return slk;
	}
	
	public static SLK createFromInFile(File inFile, SLK sourceSlk) {
		SLK slk = null;

		if (inFile.equals(DoodSLK.GAME_USE_PATH)) {
			slk = new DoodSLK(sourceSlk);
		}

		if (inFile.equals(UnitAbilsSLK.GAME_USE_PATH)) {
			slk = new UnitAbilsSLK(sourceSlk);
		}
		if (inFile.equals(UnitBalanceSLK.GAME_USE_PATH)) {
			slk = new UnitBalanceSLK(sourceSlk);
		}
		if (inFile.equals(UnitDataSLK.GAME_USE_PATH)) {
			slk = new UnitDataSLK(sourceSlk);
		}
		if (inFile.equals(UnitUISLK.GAME_USE_PATH)) {
			slk = new UnitUISLK(sourceSlk);
		}
		if (inFile.equals(UnitWeaponsSLK.GAME_USE_PATH)) {
			slk = new UnitWeaponsSLK(sourceSlk);
		}

		if (inFile.equals(ItemSLK.GAME_USE_PATH)) {
			slk = new ItemSLK(sourceSlk);
		}
		if (inFile.equals(DestructableSLK.GAME_USE_PATH)) {
			slk = new DestructableSLK(sourceSlk);
		}
		if (inFile.equals(AbilSLK.GAME_USE_PATH)) {
			slk = new AbilSLK(sourceSlk);
		}
		if (inFile.equals(BuffSLK.GAME_USE_PATH)) {
			slk = new BuffSLK(sourceSlk);
		}
		if (inFile.equals(UpgradeSLK.GAME_USE_PATH)) {
			slk = new UpgradeSLK(sourceSlk);
		}

		return slk;
	}

	public static SLK createFromInFile(File inFile) {
		SLK slk = null;

		if (inFile.equals(DoodSLK.GAME_USE_PATH)) {
			slk = new DoodSLK();
		}
		
		if (inFile.equals(UnitAbilsSLK.GAME_USE_PATH)) {
			slk = new UnitAbilsSLK();
		}
		if (inFile.equals(UnitBalanceSLK.GAME_USE_PATH)) {
			slk = new UnitBalanceSLK();
		}
		if (inFile.equals(UnitDataSLK.GAME_USE_PATH)) {
			slk = new UnitDataSLK();
		}
		if (inFile.equals(UnitUISLK.GAME_USE_PATH)) {
			slk = new UnitUISLK();
		}
		if (inFile.equals(UnitWeaponsSLK.GAME_USE_PATH)) {
			slk = new UnitWeaponsSLK();
		}
		
		if (inFile.equals(ItemSLK.GAME_USE_PATH)) {
			slk = new ItemSLK();
		}
		if (inFile.equals(DestructableSLK.GAME_USE_PATH)) {
			slk = new DestructableSLK();
		}
		if (inFile.equals(AbilSLK.GAME_USE_PATH)) {
			slk = new AbilSLK();
		}
		if (inFile.equals(BuffSLK.GAME_USE_PATH)) {
			slk = new BuffSLK();
		}
		if (inFile.equals(UpgradeSLK.GAME_USE_PATH)) {
			slk = new UpgradeSLK();
		}
		
		return slk;
	}
}

