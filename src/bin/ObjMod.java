package bin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import bin.Wc3bin.Stream;
import bin.Wc3bin.StreamException;
import dataTypes.app.Int;
import dataTypes.app.Wc3String;
import misc.FieldId;
import misc.ObjId;
import slk.MetaSLK;
import slk.RawSLK;
import slk.SLK;
import txt.Profile;
import txt.TXTSectionId;;

/**
 * base class for object modification files
 */
public class ObjMod {
	public static class Val {
		Object _val;
		
		private Val(int val) {
			_val = val;
		}
		
		private Val(float val) {
			_val = val;
		}

		private Val(String val) {
			_val = val;
		}
		
		public static Val valueOf(int val) {
			return new Val(val);
		}
		
		public static Val valueOf(float val) {
			return new Val(val);
		}

		public static Val valueOf(String val) {
			return new Val(val);
		}
	}
	
	public static class Obj {
		public static class Field {
			enum VarType {
				INT(0),
				REAL(1),
				UNREAL(2),
				STRING(3);
				
				private int _val;
				
				public int getVal() {
					return _val;
				}
				
				private static Map<Integer, VarType> _map = new HashMap<>();
				
				static public VarType fromVal(int val) {
					return _map.get(val);
				}
				
				private VarType(int val) {
					_val = val;
				}
				
				static {
					for (VarType varType : VarType.values()) {
						_map.put(varType.getVal(), varType);
					}
				}
			}
			
			private Map<Integer, Val> _vals = new HashMap<>();
			
			public Map<Integer, Val> getVals() {
				return _vals;
			}
			
			public Val getVal(int level) {
				return _vals.get(level);
			}
			
			public Val getVal() {
				return getVal(0);
			}
			
			public void setVal(Val val, int level, VarType varType, int dataPt) {
				_vals.put(level, val);
			}
			
			public void setVal(Val val, int level) {
				setVal(val, level, null, 0);
			}
			
			public void setVal(Val val) {
				setVal(val, 0);
			}
			
			public void addVal(Val val) {
				int level = 1;

				while (getVal(level) == null) {
					level++;
				}
				
				setVal(val, level);
			}
			
			public void deleteVal(int level) {
				_vals.remove(level);
			}
			
			private VarType _varType;
			
			public VarType getVarType() {
				return _varType;
			}
			
			public void setVarType(VarType val) {
				_varType = val;
			}
			
			private int _dataPt;
			
			public int getDataPt() {
				return _dataPt;
			}
			
			public void setDataPt(int val) {
				_dataPt = val;
			}
			
			public void merge(Field otherField) {
				setVarType(otherField.getVarType());
				setDataPt(otherField.getDataPt());
				
				for (Entry<Integer, Val> valEntry : otherField.getVals().entrySet()) {
					Integer level = valEntry.getKey();
					Val val = valEntry.getValue();
					
					setVal(val, level);
				}
			}
			
			private MetaFieldId _id;
			
			public MetaFieldId getId() {
				return _id;
			}
			
			public Field(MetaFieldId id) {
				_id = id;
			}
		}

		private Map<MetaFieldId, Field> _fieldsMap = new HashMap<>();
		
		public Map<MetaFieldId, Field> getFields() {
			return _fieldsMap;
		}
		
		public Field getField(MetaFieldId id) {
			return _fieldsMap.get(id);
		}
		
		public Field addField(MetaFieldId id) {
			if (getFields().containsKey(id)) return getFields().get(id);
			
			Field field = new Field(id);
			
			_fieldsMap.put(id, field);
			
			return field;
		}
		
		public void removeField(MetaFieldId id) {
			_fieldsMap.remove(id);
		}
		
		public void remove() {
			/*objs:remove(obj)

			objsById[id] = nil

			if custom then
				customObjs:remove(obj)
			else
				origObjs:remove(obj)
			end*/
		}
		
		public void merge(Obj otherObj) {
			for (Field otherField : otherObj.getFields().values()) {
				MetaFieldId fieldId = otherField.getId();
				
				Field field = addField(fieldId);
				
				field.merge(otherField);
			}
		}
		
		private ObjId _id;
		
		public ObjId getId() {
			return _id;
		}
		
		private ObjId _baseId;
		
		public ObjId getBaseId() {
			return _baseId;
		}

		public void read_0x1(Wc3bin.Stream stream) throws StreamException {
			int modsAmount = stream.readInt();
			
			for (int i = 0; i < modsAmount; i++) {
				Field field = addField(MetaFieldId.valueOf(stream.readId()));
				
				Field.VarType varType = Field.VarType.fromVal(stream.readInt());
				
				field.setVarType(varType);
				
				Val val = null;
				
				switch (varType) {
				case INT: {
					val = Val.valueOf(stream.readInt());
					
					break;
				}
				case REAL: {
					val = Val.valueOf(stream.readFloat());
					
					break;
				}
				case UNREAL: {
					val = val.valueOf(stream.readFloat());
					
					break;
				}
				case STRING: {
					val = val.valueOf(stream.readString());
					
					break;
				}
				default: {
					val = val.valueOf(stream.readString());
				}
				}
				
				field.setVal(val, 0);
			}
		}

		public void write_0x1(Wc3bin.Stream stream) {
			int modsAmount = 0;
			
			for (Field field : getFields().values()) {
				modsAmount += field.getVals().size();
			}

			stream.writeInt(modsAmount);
			
			for (Field field : getFields().values()) {
				MetaFieldId id = field.getId();

				stream.writeId(id);
				
				Field.VarType varType = field.getVarType();
				
				stream.writeInt(varType.getVal());
				
				Object val = field.getVal(0);
				
				switch (varType) {
				case INT: {
					stream.writeInt((Integer) val);
					
					break;
				}
				case REAL: {
					stream.writeFloat((Float) val);
					
					break;
				}
				case UNREAL: {
					stream.writeFloat((Float) val);
					
					break;
				}
				case STRING: {
					stream.writeString((String) val);
					
					break;
				}
				default: {
					stream.writeString((String) val);
				}
				}
				
				stream.writeId(null);
			}
		}
		
		private void read(Stream stream, EncodingFormat format) throws StreamException {		
			switch (format.toEnum()) {
			case OBJ_0x1: {
				read_0x1(stream);
				
				break;
			}
			}
		}
		
		private void write(Stream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case OBJ_0x1: {
				write_0x1(stream);
				
				break;
			}
			}
		}
		
		public Obj(Stream stream, EncodingFormat format) throws StreamException {
			read(stream, format);
		}
		
		public Obj(ObjId id, ObjId baseId) {
			_id = id;
			_baseId = baseId;
		}
	}
	
	protected Map<ObjId, Obj> _objs = new HashMap<>();
	
	public Map<ObjId, ? extends Obj> getObjs() {
		return _objs;
	}
	
	public List<Obj> getOrigObjs() {
		List<Obj> ret = new ArrayList<>();
		
		for (Obj obj : getObjs().values()) {
			if (obj.getBaseId() == null) {
				ret.add(obj);
			}
		}
		
		return ret;
	}
	
	public List<Obj> getCustomObjs() {
		List<Obj> ret = new ArrayList<>();
		
		for (Obj obj : getObjs().values()) {
			if (obj.getBaseId() != null) {
				ret.add(obj);
			}
		}
		
		return ret;
	}
	
	public Obj getObj(ObjId id) {
		return getObjs().get(id);
	}
	
	private void addObj(Obj val) {
		_objs.put(val.getId(), val);
	}

	public Obj addObj(ObjId id, ObjId baseId) {
		if (getObjs().containsKey(id)) return getObjs().get(id);
		
		Obj obj = new Obj(id, baseId);
	
		addObj(obj);
		
		return obj;
	}
	
	public void merge(ObjMod other) {
		for (Obj otherObj : other.getObjs().values()) {
			ObjId objId = otherObj.getId();
			
			Obj obj = addObj(objId, otherObj.getBaseId());
			
			obj.merge(otherObj);
		}
	}
	
	public ObjMod copy() {
		ObjMod other = new ObjMod();
		
		other.merge(this);
		
		return other;
	}
	
	public void print() {
		for (Obj obj : getObjs().values()) {
			System.out.println(String.format("%s %s", obj.getId(), obj.getBaseId()));
			
			for (Obj.Field field : obj.getFields().values()) {
				System.out.println(String.format("\t%s", field.getId()));
				
				for (Entry<Integer, Val> valEntry : field.getVals().entrySet()) {
					int level = valEntry.getKey();
					Val val = valEntry.getValue();
					
					System.out.println(String.format("\t\t%i -> %s %s %s", level, field.getVal(level), field.getVarType().getVal(), field.getDataPt()));
				}
			}
		}
	}
	
	public void reduce(MetaSLK reduceMetaSlk) {
		Map<String, SLK> outSlks = new HashMap<>();
		Profile outProfile = new Profile();
		ObjMod outObjMod = copy();
		
		for (Obj obj : getObjs().values()) {
			ObjId objId = obj.getId();
			
			for (Obj.Field field : obj.getFields().values()) {
				MetaFieldId fieldId = field.getId();
				
				MetaSLK.Obj metaObj = reduceMetaSlk.getObj(ObjId.valueOf(fieldId));
				
				if (metaObj != null) {
					String slkName = metaObj.getS(FieldId.valueOf("slk"));
					
					String slkFieldName = metaObj.getS(FieldId.valueOf("field"));
					
					if (slkName.equals("Profile")) {
						for (Entry<Integer, Val> valEntry : field.getVals().entrySet()) {
							int level = valEntry.getKey();
							Val val = valEntry.getValue();
							
							int index = level;
							int metaIndex = Int.valueOf(metaObj.get(FieldId.valueOf("index"))).getVal();
							
							if (metaIndex > 0) {
								level += metaIndex;
							}
							
							Profile.Obj profileObj = outProfile.addObj(TXTSectionId.valueOf(objId.toString()));
							
							Profile.Obj.Field profileField = profileObj.addField(FieldId.valueOf(slkFieldName));
							
							/*TXTVal profileVal = TXTVal.valueOf(val);
							
							profileField.set(profileVal, index);*/
						}
					} else {
						//convert slk names
						switch (slkName) {
						
						}

						SLK outSlk = outSlks.get(slkName);
						
						if (outSlk == null) {
							outSlk = new RawSLK();
							
							outSlks.put(slkName, outSlk);
						}
						
						for (Entry<Integer, Val> valEntry : field.getVals().entrySet()) {
							int level = valEntry.getKey();
							Val val = valEntry.getValue();
							
							if (metaObj.get(FieldId.valueOf("Field")).equals("Data")) {
								slkFieldName += (char) ((int) 'A' + field.getDataPt() - 1);
							}
							
							Integer repeat = Int.valueOf(metaObj.get(FieldId.valueOf("repeat"))).toInt();

							if ((repeat != null) && (repeat > 0)) {
								slkFieldName += level;
							}
							
							FieldId slkFieldId = FieldId.valueOf(slkFieldName);
							
							outSlk.addField(slkFieldId);
							
							SLK.Obj slkObj = outSlk.addObj(ObjId.valueOf(objId));
							
							slkObj.set(slkFieldId, Wc3String.valueOf(val));
						}
					}
					
					outObjMod.getObj(objId).removeField(fieldId);
				}
			}
		}
	}
	
	private static class EncodingFormat extends Format<EncodingFormat.Enum> {
		enum Enum {
			AUTO,
			OBJ_0x1,
		}

		private static Map<Integer, EncodingFormat> _map = new HashMap<>();

		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat OBJ_0x1 = new EncodingFormat(Enum.OBJ_0x1, 0x1);
		
		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}
		
		private EncodingFormat(Enum enumVal, int version) {
			super(enumVal, version);

			_map.put(version, this);
		}
	}

	public void read_0x1(Wc3bin.Stream stream) throws StreamException {
		int version = stream.readInt();

		Wc3bin.checkFormatVer("objMaskFunc", EncodingFormat.OBJ_0x1.getVersion(), version);
		
		int origObjsAmount = stream.readInt();
		
		for (int i = 0; i < origObjsAmount; i++) {
			addObj(new Obj(ObjId.valueOf(stream.readId()), ObjId.valueOf(stream.readId())));
		}
		
		int customObjsAmount = stream.readInt();

		for (int i = 0; i < customObjsAmount; i++) {
			addObj(new Obj(ObjId.valueOf(stream.readId()), ObjId.valueOf(stream.readId())));
		}
	}

	public void write_0x1(Wc3bin.Stream stream) {
		stream.writeInt(EncodingFormat.OBJ_0x1.getVersion());
		
		stream.writeInt(getOrigObjs().size());
		
		for (Obj obj : getOrigObjs()) {
			obj.write(stream, EncodingFormat.OBJ_0x1);
		}
		
		stream.writeInt(getCustomObjs().size());
		
		for (Obj obj : getCustomObjs()) {
			obj.write(stream, EncodingFormat.OBJ_0x1);
		}
	}
	
	public void read(File file) throws IOException {
		read_0x1(new Stream(file));
	}
	
	public void write(File file) {
		
	}
	
	public ObjMod() {
		
	}
}
