package net.moonlightflower.wc3libs.slk;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.app.Bool;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3Int;
import net.moonlightflower.wc3libs.dataTypes.app.Real;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.Mergeable;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.app.doodads.DoodSLK;
import net.moonlightflower.wc3libs.slk.app.objs.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.util.*;

public abstract class SLK<Self extends SLK<Self, ObjIdType, ObjType>, ObjIdType extends ObjId, ObjType extends SLK.Obj<? extends ObjIdType>> implements Mergeable<Self>, SLKable {
	public static class FieldData {
		private final DataType _defVal;

		@Nullable
		public DataType getDefVal() {
			return _defVal;
		}

		public FieldData(@Nullable DataType defVal) {
			_defVal = defVal;
		}
	}

	private final Map<FieldId, FieldData> _fields = new LinkedHashMap<>();
	
	private FieldId _pivotField = null;

	@Nonnull
	public Map<FieldId, FieldData> getFields() {
		return _fields;
	}
	
	public boolean containsField(@Nonnull FieldId field) {
		return _fields.containsKey(field);
	}

	@Nullable
	public FieldId getPivotField() {
		return _pivotField;
	}
	
	public void addField(@Nonnull FieldId field, @Nullable DataType defVal) {
		FieldData fieldData = new FieldData(defVal);

		_fields.put(field, fieldData);

		if (_pivotField == null) {
			_pivotField = field;
		}
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
		private Map<FieldId, DataType> _vals = new LinkedHashMap<>();

		@Nonnull
		public Map<FieldId, DataType> getVals() {
			return _vals;
		}
		
		public DataType get(@Nonnull FieldId field) {
			return getVals().get(field);
		}

		public DataType get(@Nonnull SLKState state) {
			return getVals().get(state.getFieldId());
		}
		
		public DataType get(@Nonnull String fieldS) {
			return get(FieldId.valueOf(fieldS));
		}
		
		public String getS(@Nonnull FieldId field) {
			return get(field).toString();
		}
		
		public void set(@Nonnull FieldId field, DataType val) {
			_vals.put(field, val);
		}
		
		public void set(@Nonnull SLKState state, DataType val) {
			set(state.getFieldId(), val);
		}
		
		public void setRaw(@Nonnull FieldId field, String val) {
			set(field, Wc3String.valueOf(val));
		}
		
		public void merge(@Nonnull Obj<? extends ObjId> otherObj, boolean overwrite) {
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
		
		public void merge(@Nonnull Obj<? extends ObjId> otherObj) {
			merge(otherObj, true);
		}
		
		public void print(@Nonnull PrintStream stream) {
			for (Map.Entry<FieldId, DataType> valEntry : getVals().entrySet()) {
				FieldId fieldId = valEntry.getKey();
				DataType val = valEntry.getValue();
				
				stream.println(fieldId + " -> " + val);
			}
		}
		
		public void print() {
			print(System.out);
		}
		
		public abstract void reduce();
		
		private T _id;

		@Nonnull
		public T getId() {
			return _id;
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
	public void merge(@Nonnull Self other) {
		merge(other, true);
	}

	//protected abstract ObjIdType readObj(ObjId id);
	
	protected abstract void read(@Nonnull SLK<?, ? extends ObjId, ? extends SLK.Obj<? extends ObjId>> slk);

	public void read(@Nonnull File file, boolean onlyHeader) throws IOException {
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

				for (String aT : t) {
					String symbol = aT.substring(0, 1);

					if (symbol.equals("X")) {
						x = Integer.parseInt(aT.substring(1, aT.length()));
					}
					if (symbol.equals("Y")) {
						y = Integer.parseInt(aT.substring(1, aT.length()));
					}
					if (symbol.equals("K")) {
						boolean skip = false;
						String valS = aT.substring(1, aT.length());

						if (valS.substring(0, 1).equals("\"")) {
							valS = valS.substring(1, valS.length() - 1);

							val = Wc3String.valueOf(valS);
						} else {
							try {
								val = Wc3Int.valueOf(Integer.parseInt(valS));
							} catch (NumberFormatException e) {
								try {
									val = Real.valueOf(Float.parseFloat(valS));
								} catch (NumberFormatException ignored) {
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
					if (!data.containsKey(y)) data.put(y, new LinkedHashMap<>());
	
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
				if (val.equals("-")) return null;

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

	@Nullable
	public static SLK createFromInFile(@Nonnull File inFile, @Nonnull SLK sourceSlk) {
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

	@Nullable
	public static SLK createFromInFile(@Nonnull File inFile) {
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
