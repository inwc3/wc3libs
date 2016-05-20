package net.moonlightflower.wc3libs.slk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.Mergeable;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.SLK.Obj;

public abstract class SLK<Self extends SLK<Self, ObjIdType, ObjType>, ObjIdType extends ObjId, ObjType extends SLK.Obj<? extends ObjIdType>> implements Mergeable<Self>, SLKable {
	public static class FieldData {
		private DataType _defVal;

		public DataType getDefVal() {
			return _defVal;
		}

		public FieldData(DataType defVal) {
			_defVal = defVal;
		}
	}

	private Map<FieldId, FieldData> _fields = new HashMap<>();
	private FieldId _pivotField = null;

	public Map<FieldId, FieldData> getFields() {
		return _fields;
	}
	
	public boolean containsField(FieldId field) {
		assert(field != null);

		return _fields.containsKey(field);
	}
	
	public FieldId getPivotField() {
		return _pivotField;
	}
	
	public void addField(FieldId field, DataType defVal) {
		assert(field != null);

		FieldData fieldData = _fields.get(field);

		fieldData = new FieldData(defVal);

		_fields.put(field, fieldData);

		if (_pivotField == null) _pivotField = field;
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
		private Map<FieldId, DataType> _vals = new HashMap<>();
		
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
					set(field, val);
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
	
	private Map<ObjIdType, ObjType> _objs = new HashMap<>();
	
	public Map<ObjIdType, ObjType> getObjs() {
		return _objs;
	}
	
	public ObjType getObj(ObjIdType id) {
		return getObjs().get(id);
	}

	public void addObj(ObjType val) {
		_objs.put(val.getId(), val);
	}

	
	protected abstract ObjType createObj(ObjId id);
	
	public ObjType addObj(ObjIdType id) {
		if (getObjs().containsKey(id)) return getObj(id);
		
		ObjType obj = createObj(id);
		
		addObj(obj);
		
		return obj;
	}
	
	public void merge(Self other, boolean overwrite) {
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

		Map<Integer, Map<Integer, DataType>> data = new HashMap<>();

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
						String valS = t[i].substring(1, t[i].length());

						if (valS.substring(0, 1).equals("\"")) {
							valS = valS.substring(1, valS.length() - 1);
							
							val = Wc3String.valueOf(valS);
						} else {
							try {
								val = Int.valueOf(Integer.parseInt(valS));
							} catch (NumberFormatException e) {
							}
						}
					}
				}
				
				if (x == 0) x = curX;
				if (y == 0) y = curY;

				if (x > maxX) maxX = x;
				if (y > maxY) maxY = y;

				if (!data.containsKey(y)) data.put(y, new HashMap<Integer, DataType>());

				data.get(y).put(x, val);

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

		for (int i = 2; i < data.size(); i++) {
			ObjId objId = ObjId.valueOf(data.get(i).get(1).toString());
			
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

		private void writeCell(int x, int y, DataType slkVal) throws IOException {			
			if (slkVal == null) return;

			Object val = slkVal.toSLKVal();

			if (val == null) return;

			if (val instanceof Boolean) {
				if (((Boolean) val).booleanValue()) {
					val = 1;
				} else {
					val = null;
				}
			} else if (val instanceof Integer) {
				if (((Integer) val) == 0) {
					val = null;
				}
			} else if (val instanceof String) {
				if ((((String) val).equals("")) || (((String) val).equals("\"\"")) || (((String) val).equals("0")) || (((String) val).equals("-"))) {
					val = null;
				} else {
					val = "\"" + ((String) val) + "\"";
				}
			} else if (val instanceof Id) {
				val = val.toString();
				
				if ((((String) val).equals("")) || (((String) val).equals("\"\"")) || (((String) val).equals("0")) || (((String) val).equals("-"))) {
					val = null;
				} else {
					val = "\"" + ((String) val) + "\"";
				}
			}

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
			assert(_pivotField != null);

			_file.getParentFile().mkdirs();

			_writer = new BufferedWriter(new FileWriter(_file));

			_writer.write("ID;PWXL;N;E");

			_writer.newLine();

			_writer.write("B;Y" + (_objs.size() + 1) + ";X" + _fields.size() + ";D0");

			Map<FieldId, Integer> fieldX = new HashMap<>();
			Map<Integer, FieldId> fieldsByX = new HashMap<>();

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

				for (int x = 2; x < fieldsByX.size(); x++) {
					FieldId field = fieldsByX.get(x);

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
}
