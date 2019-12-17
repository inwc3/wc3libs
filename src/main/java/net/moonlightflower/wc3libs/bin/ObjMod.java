package net.moonlightflower.wc3libs.bin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;

import net.moonlightflower.wc3libs.bin.app.objMod.W3B;
import net.moonlightflower.wc3libs.bin.app.objMod.W3D;
import net.moonlightflower.wc3libs.bin.app.objMod.W3H;
import net.moonlightflower.wc3libs.bin.app.objMod.W3Q;
import net.moonlightflower.wc3libs.bin.app.objMod.W3T;
import net.moonlightflower.wc3libs.bin.app.objMod.W3U;
import net.moonlightflower.wc3libs.bin.app.objMod.W3A;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.War3Num;
import net.moonlightflower.wc3libs.dataTypes.app.War3Int;
import net.moonlightflower.wc3libs.dataTypes.app.War3Real;
import net.moonlightflower.wc3libs.dataTypes.app.War3String;
import net.moonlightflower.wc3libs.misc.*;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.slk.MetaSLK;
import net.moonlightflower.wc3libs.slk.RawSLK;
import net.moonlightflower.wc3libs.slk.SLK;
import net.moonlightflower.wc3libs.slk.app.meta.CommonMetaSLK;
import net.moonlightflower.wc3libs.txt.Profile;
import net.moonlightflower.wc3libs.txt.TXTSectionId;

;import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * base class for object modification files
 */
public abstract class ObjMod<ObjType extends ObjMod.Obj> implements Printable {
	public enum ValType {
		INT(0),
		REAL(1),
		UNREAL(2),
		STRING(3);

		private int _val;

		public int getVal() {
			return _val;
		}

		private final static Map<Integer, ValType> _map = new LinkedHashMap<>();

		public static ValType valueOf(int val) {
			return _map.get(val);
		}

		ValType(int val) {
			_val = val;
		}

		static {
			for (ValType varType : ValType.values()) {
				_map.put(varType.getVal(), varType);
			}
		}
	}

	public abstract static class Obj implements Printable {
		public abstract boolean isExtended();

		public static class Mod implements Printable {
			protected MetaFieldId _id;

			public MetaFieldId getId() {
				return _id;
			}

			private ValType _valType;

			@Nullable
			public ValType getValType() {
				return _valType;
			}

			protected final DataType _val;

			@Nullable
			public DataType getVal() {
				return _val;
			}

			@Override
			public String toString() {
				return getId().toString();
			}
			
			public Mod(@Nonnull MetaFieldId id, @Nonnull ValType valType, @Nullable DataType val) {
				_id = id;
				_valType = valType;
				_val = val;
			}

			public Mod(@Nonnull MetaFieldId id, @Nullable DataType val) {
				_id = id;
				_valType = null;
				_val = val;
			}

			@Override
			public void print(@Nonnull Printer printer) {
				printer.print(String.format("%s: %s", _id, _val));
			}
		}

		public static class ExtendedMod extends Mod implements Printable {
			private int _level;

			public int getLevel() {
				return _level;
			}

			private int _dataPt;

			public int getDataPt() {
				return _dataPt;
			}

			public void setDataPt(int val) {
				_dataPt = val;
			}

			public ExtendedMod(@Nonnull MetaFieldId id, @Nonnull ValType valType, @Nullable DataType val, int level, int dataPt) {
				super(id, valType, val);

				_level = level;
				_dataPt = dataPt;
			}

			public ExtendedMod(@Nonnull MetaFieldId id, @Nullable DataType val, int level, int dataPt) {
				super(id, val);

				_level = level;
				_dataPt = dataPt;
			}

			@Override
			public void print(@Nonnull Printer printer) {
				printer.print(String.format("%s (level %d, dataPt %d): %s", _id, _level, _dataPt, _val));
			}
		}

		private List<Mod> _mods = new ArrayList<>();
		private Map<MetaFieldId, List<Mod>> _modsMap = new LinkedHashMap<>();

		@Nonnull
		public List<Mod> getMods() {
			return _mods;
		}

		@Nonnull
		public Map<MetaFieldId, List<Mod>> getModsMapByField() {
			return _modsMap;
		}

		@Nonnull
		public Set<MetaFieldId> getFields() {
			return _modsMap.keySet();
		}

		@Nonnull
		public List<Mod> getModsOfField(@Nonnull MetaFieldId fieldId) {
			return _modsMap.getOrDefault(fieldId, Collections.emptyList());
		}

		public void addMod(@Nonnull Mod mod) {
			_mods.add(mod);

			MetaFieldId id = mod.getId();

			if (!_modsMap.containsKey(id)) {
				_modsMap.put(id, new ArrayList<>());
			}

			_modsMap.get(id).add(mod);
		}

		@Nullable
		public DataType get(@Nonnull MetaFieldId id) {
			List<Mod> modsMapList = _modsMap.get(id);

			if (modsMapList == null) {
				return null;
			}

			Mod mod = modsMapList.get(0);

			if (mod == null) {
				return null;
			}

			return mod.getVal();
		}

		public void set(@Nonnull MetaFieldId id, @Nullable DataType val) {
			Mod mod = new Mod(id, val);

			if (_modsMap.containsKey(id)) {
				_mods.removeIf((Mod filterMod) -> {return filterMod._id.equals(id);});
				_modsMap.remove(id);
			}

			_mods.add(mod);
			_modsMap.put(id, new ArrayList<>());

			_modsMap.get(id).add(mod);
 		}

 		public void remove(@Nonnull Mod mod) {
			_mods.remove(mod);

			List<Mod> modsMapList = _modsMap.get(mod.getId());

			if (modsMapList != null) {
				modsMapList.remove(mod);

				if (modsMapList.isEmpty()) {
					_modsMap.remove(mod.getId());
				}
			}
		}
		
		public void remove(@Nonnull MetaFieldId id) {
			_mods.removeIf((Mod mod) -> {return mod.getId().equals(id);});
			_modsMap.remove(id);
		}
		
		public void merge(@Nonnull Obj otherObj) {
			for (Map.Entry<MetaFieldId, List<Mod>> otherModEntry : otherObj.getModsMapByField().entrySet()) {
				MetaFieldId fieldId = otherModEntry.getKey();
				List<Mod> otherModsList = otherModEntry.getValue();

				List<Mod> modsList = _modsMap.getOrDefault(fieldId, Collections.emptyList());

				for (int i = 0; i < otherModsList.size(); i++) {
					modsList.set(i, otherModsList.get(i));
				}
			}
		}
		
		private ObjId _id;

		@Nonnull
		public ObjId getId() {
			return _id;
		}
		
		private ObjId _baseId;

		@Nullable
		public ObjId getBaseId() {
			return _baseId;
		}

		protected ObjId _newId;

		@Nullable
		public ObjId getNewId() {
			return _newId;
		}
		
		@Override
		public String toString() {
			if (getBaseId() == null) return String.format("%s", getId().toString());
			
			return String.format("%s (%s)", getId().toString(), getBaseId().toString());
		}

		@Override
		public void print(@Nonnull Printer printer) {
			printer.beginGroup(String.format("%s %s", getId(), getBaseId()));

			for (Map.Entry<MetaFieldId, List<Mod>> modEntry : getModsMapByField().entrySet()) {
				MetaFieldId fieldId = modEntry.getKey();
				List<Mod> modsList = modEntry.getValue();

				printer.beginGroup(String.format("%s", fieldId));

				for (Mod mod : modsList) {
					mod.print(printer);
				}

				printer.endGroup();
			}

			printer.endGroup();
		}
		
		private void read_0x1(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			_baseId = ObjId.valueOf(stream.readId("baseId"));
			_newId = ((Function<Id, ObjId>) id -> {
				if (id.equals(Id.valueOf("\0\0\0\0"))) return null;

				return ObjId.valueOf(id);
			}).apply(stream.readId("newId"));

			_id = (_newId == null) ? _baseId : _newId;

			int modsAmount = stream.readInt32("modsAmount");
			
			for (int i = 0; i < modsAmount; i++) {
				MetaFieldId fieldId = MetaFieldId.valueOf(stream.readId("fieldId"));
				
				int varTypeI = stream.readInt32("varType");

				ValType varType = ValType.valueOf(varTypeI);
				
				int level = 0;
				int dataPt = 0;

				if (isExtended()) {
					level = stream.readInt32("level/variation");
					dataPt = stream.readInt32("dataPt");
				}

				DataType val;

				switch (varType) {
				case INT: {
					val = War3Int.valueOf(stream.readInt32("val (int)"));
					
					break;
				}
				case REAL: {
					val = War3Real.valueOf(stream.readFloat32("val (real)"));
					
					break;
				}
				case UNREAL: {
					val = War3Real.valueOf(stream.readFloat32("val (unreal)"));
					
					break;
				}
				case STRING: {
					val = War3String.valueOf(stream.readString("val (string) "));
					
					break;
				}
				default: {
					val = War3String.valueOf(stream.readString("val (string default)"));
				}
				}

				Mod mod = isExtended() ? new ExtendedMod(fieldId, varType, val, level, dataPt) : new Mod(fieldId, varType, val);

				addMod(mod);
				
				stream.readId("endToken");
			}
		}

		private void read_0x2(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			_baseId = ObjId.valueOf(stream.readId("baseId"));
			_newId = ((Function<Id, ObjId>) id -> {
				if (id.equals(Id.valueOf("\0\0\0\0"))) return null;

				return ObjId.valueOf(id);
			}).apply(stream.readId("newId"));

			_id = (_newId == null) ? _baseId : _newId;

			int modsAmount = stream.readInt32("modsAmount");

			for (int i = 0; i < modsAmount; i++) {
				MetaFieldId fieldId = MetaFieldId.valueOf(stream.readId("fieldId"));

				int varTypeI = stream.readInt32("varType");

				ValType varType = ValType.valueOf(varTypeI);
				
				int level = 0;
				int dataPt = 0;

				if (isExtended()) {
					level = stream.readInt32("level/variation");
					dataPt = stream.readInt32("dataPt");
				}

				DataType val;
				
				switch (varType) {
				case INT: {
					val = War3Int.valueOf(stream.readInt32("val (int)"));
					
					break;
				}
				case REAL: {
					val = War3Real.valueOf(stream.readFloat32("val (real)"));
					
					break;
				}
				case UNREAL: {
					val = War3Real.valueOf(stream.readFloat32("val (unreal)"));
					
					break;
				}
				case STRING: {
					val = War3String.valueOf(stream.readString("val (string)"));
					
					break;
				}
				default: {
					val = War3String.valueOf(stream.readString("val (string default)"));
				}
				}

				Mod mod = isExtended() ? new ExtendedMod(fieldId, varType, val, level, dataPt) : new Mod(fieldId, varType, val);

				addMod(mod);

				stream.readId("endToken");
			}
		}
		
		private void write_0x1(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
			stream.writeId((_baseId == null) ? _id : _baseId);
			stream.writeId((_newId == null) ? Id.valueOf("\0\0\0\0") : _newId);

			int modsAmount = _mods.size();

			stream.writeInt32(modsAmount);

			for (Mod mod : _mods) {
				MetaFieldId id = mod.getId();
				int dataPt = mod instanceof ExtendedMod ? ((ExtendedMod) mod).getDataPt() : 0;
				int level = mod instanceof ExtendedMod ? ((ExtendedMod) mod).getLevel() : 0;

				ValType valType = mod.getValType() == null ? ValType.STRING : mod.getValType();
				DataType val = mod.getVal();

				stream.writeId(id);

				stream.writeInt32(valType.getVal());

				if (isExtended()) {
					stream.writeInt32(level);
					stream.writeInt32(dataPt);
				}

				switch (valType) {
					case INT: {
						if (!(val instanceof War3Int)) {
							throw new BinStream.StreamException(stream, "no int: " + val);
						}

						stream.writeInt32(((War3Int) val).toInt());

						break;
					}
					case REAL:
					case UNREAL: {
						if (!(val instanceof War3Num)) {
							throw new BinStream.StreamException(stream, "no real: " + val);
						}

						stream.writeFloat32(((War3Num) val).toFloat());

						break;
					}
					case STRING: {
						String valS = val == null ? "" : val.toString();

						stream.writeString(valS);

						break;
					}
					default: {
						String valS = val == null ? "" : val.toString();

						stream.writeString(valS);
					}
				}

				stream.writeId(null); //endToken
			}
		}
		
		private void write_0x2(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
			stream.writeId((_baseId == null) ? _id : _baseId);
			stream.writeId((_newId == null) ? Id.valueOf("\0\0\0\0") : _newId);

			int modsAmount = _mods.size();

			stream.writeInt32(modsAmount);

			for (Mod mod : _mods) {
				MetaFieldId id = mod.getId();
				int dataPt = mod instanceof ExtendedMod ? ((ExtendedMod) mod).getDataPt() : 0;
				int level = mod instanceof ExtendedMod ? ((ExtendedMod) mod).getLevel() : 0;

				ValType valType = mod.getValType() == null ? ValType.STRING : mod.getValType();
				DataType val = mod.getVal();

				stream.writeId(id);

				stream.writeInt32(valType.getVal());

				if (isExtended()) {
					stream.writeInt32(level);
					stream.writeInt32(dataPt);
				}

				switch (valType) {
					case INT: {
						if (!(val instanceof War3Int)) {
							throw new BinStream.StreamException(stream, "no int: " + val);
						}

						stream.writeInt32(((War3Int) val).toInt());

						break;
					}
					case REAL:
					case UNREAL: {
						if (!(val instanceof War3Num)) {
							throw new BinStream.StreamException(stream, "no real: " + val);
						}

						stream.writeFloat32(((War3Num) val).toFloat());

						break;
					}
					case STRING: {
						String valS = val == null ? "" : val.toString();

						stream.writeString(valS);

						break;
					}
					default: {
						String valS = val == null ? "" : val.toString();

						stream.writeString(valS);
					}
				}

				stream.writeId(null); //endToken
			}
		}
		
		public void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			try {
				switch (format.toEnum()) {
				case OBJ_0x1: {
					read_0x1(stream);
					
					break;
				}
				case OBJ_0x2: {
					read_0x2(stream);
					
					break;
				}
				}
			} catch (RuntimeException e) {
				throw new BinInputStream.StreamException(stream);
			}
		}
		
		public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
			switch (format.toEnum()) {
			case AUTO:
			case OBJ_0x2: {
				write_0x2(stream);
				
				break;
			}
			case OBJ_0x1: {
				write_0x1(stream);
				
				break;
			}
			}
		}

		protected abstract Obj copySpec();

		@Nonnull
		public <T extends Obj> T copy() {
			Obj ret = copySpec();

			ret._newId = _newId;

			ret.merge(this);

			return (T) ret;
		}
		
		public Obj(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			read(stream, format);
		}
		
		public Obj(@Nonnull ObjId id, @Nullable ObjId baseId) {
			_id = id;
			_baseId = baseId;

            _newId = (_baseId != null) ? id : null;
		}
	}
	
	protected final Map<ObjId, ObjType> _objs = new LinkedHashMap<>();
	private final List<ObjType> _objsList = new ArrayList<>();

	@Nonnull
	public Map<ObjId, ObjType> getObjs() {
		return _objs;
	}

	@Nonnull
	public List<ObjType> getObjsList() {
		return _objsList;
	}

	@Nonnull
	public List<ObjType> getOrigObjs() {
		List<ObjType> ret = new ArrayList<>();
		
		for (int i = 0; i < getObjsList().size(); i++) {
			ObjType obj = getObjsList().get(i);
			
			if (obj.getNewId() == null) {
				ret.add(obj);
			}
		}
		
		return ret;
	}

	@Nonnull
	public List<ObjType> getCustomObjs() {
		List<ObjType> ret = new ArrayList<>();

		for (int i = 0; i < getObjsList().size(); i++) {
			ObjType obj = getObjsList().get(i);

			if (obj.getNewId() != null) {
				ret.add(obj);
			}
		}

		return ret;
	}

	public boolean containsObj(@Nonnull ObjId id) {
		return _objs.containsKey(id);
	}

	@Nullable
	public ObjType getObj(@Nonnull ObjId id) {
		return getObjs().get(id);
	}
	
	private void addObj(@Nonnull ObjType val) {
		_objs.put(val.getId(), val);
		_objsList.add(val);
	}
	
	public void removeObj(@Nonnull ObjType val) {
		_objs.remove(val.getId());
		_objsList.remove(val);
	}

	public void removeObj(@Nonnull ObjId id) {
		ObjType obj = getObj(id);
		
		if (obj != null) {
			removeObj(obj);
		}
	}
	
	public void clearObjs() {
		_objs.clear();
		_objsList.clear();
	}

	protected abstract ObjType createObj(@Nonnull ObjId id, @Nullable ObjId baseId);
	
	public Obj addObj(@Nonnull ObjId id, @Nullable ObjId baseId) {
		if (getObjs().containsKey(id)) return getObjs().get(id);
		
		ObjType obj = createObj(id, baseId);
	
		addObj(obj);
		
		return obj;
	}
	
	public void merge(@Nonnull ObjMod<ObjType> other) {
		for (ObjType otherObj : other.getObjs().values()) {
			ObjType obj = otherObj.copy();

			addObj(obj);
		}
	}

	@Nonnull
	public abstract ObjMod<ObjType> copy();

	@Override
	public void print(@Nonnull Printer printer) {
		printer.beginGroup(getClass().getSimpleName());

		for (Obj obj : getObjs().values()) {
			obj.print(printer);
		}

		printer.endGroup();
	}
	
	public static class ObjPack<ObjType extends ObjMod.Obj> {
		private Map<ObjId, ObjId> _baseObjIds = new LinkedHashMap<>();
		
		public Map<ObjId, ObjId> getBaseObjIds() {
			return _baseObjIds;
		}
		
		private Map<File, SLK> _slks = new LinkedHashMap<>();
		
		public Map<File, SLK> getSlks() {
			return _slks;
		}
		
		private Profile _profile = new Profile();
		
		public Profile getProfile() {
			return _profile;
		}
		
		private ObjMod<ObjType> _objMod;
		
		public ObjMod<ObjType> getObjMod() {
			return _objMod;
		}
		
		private ObjPack(@Nonnull ObjMod<ObjType> orig) {
			_objMod = orig.copy();

			for (ObjType obj : _objMod.getCustomObjs()) {
				_baseObjIds.put(obj.getId(), obj.getBaseId());
			}
		}
	}

	public abstract Collection<File> getSLKs();
	public abstract Collection<File> getNecessarySLKs();

	@Nonnull
	public ObjPack reduce(@Nonnull MetaSLK reduceMetaSlk, Collection<Class<? extends ObjMod>> excludedClasses) throws Exception {
		ObjPack pack = new ObjPack(this);

		if (excludedClasses.contains(getClass())) return pack;
		
		Map<File, SLK> outSlks = pack.getSlks();
		Profile outProfile = pack.getProfile();
		ObjMod outObjMod = pack.getObjMod();
		
		for (Obj obj : getObjs().values()) {
			ObjId objId = obj.getId();
			/*if(objId.toString().equals("n01M")) {
				System.out.println("yay");
			}*/
			//
			//outObjMod.getObj(objId).removeField(MetaFieldId.valueOf("wurs"));

			//if (!objId.toString().matches("[0-9A-za-z]{4}")) continue;

			for (Obj.Mod mod : obj.getMods()) {
				MetaFieldId fieldId = mod.getId();

				ObjId metaObjId = ObjId.valueOf(fieldId);
				MetaSLK.Obj metaObj = reduceMetaSlk.getObj(metaObjId);

				if (metaObj != null) {
					String slkName = metaObj.getS(FieldId.valueOf("slk"));

					String slkFieldName = metaObj.getS(FieldId.valueOf("field"));

					if (slkName.equals("Profile")) {
						int level = mod instanceof Obj.ExtendedMod ? ((Obj.ExtendedMod) mod).getLevel() : 0;

						DataType val = mod.getVal();

						int index = (level == 0) ? 0 : (level - 1);
						int metaIndex = War3Int.valueOf(metaObj.get(FieldId.valueOf("index"), War3Int.valueOf(0))).getVal();

						if (metaIndex > 0) {
							index += metaIndex;
						}

						Profile.Obj profileObj = outProfile.addObj(TXTSectionId.valueOf(objId.toString()));

						for (File necessarySlkFile : getNecessarySLKs()) {
							SLK necessarySlk = outSlks.computeIfAbsent(necessarySlkFile, k -> new RawSLK());

							necessarySlk.addObj(objId);
						}

						FieldId profileFieldId = FieldId.valueOf(slkFieldName);

						Profile.Obj.Field profileField = profileObj.addField(profileFieldId);
						DataType profileVal = null;

						if (metaObj.getS(FieldId.valueOf("type")).endsWith("List")) {
							//split up list types like stringList
							profileVal = val;

							if (profileVal != null) {
								String[] vals = profileVal.toString().split(",");

								for (int i = 0; i < vals.length; i++) {
									profileField.set(War3String.valueOf(vals[i]), index + i);
								}
							}
						} else {
							profileVal = val;

							profileField.set(profileVal, index);
						}

						Obj outObj = outObjMod.getObj(objId);

						if (outObj != null) {
							outObj.remove(mod);
						}
					} else {
						File slkFile = CommonMetaSLK.convertSLKName(slkName);

						if (slkFile == null) throw new RuntimeException("no slkFile for name " + slkName);

						SLK outSlk = outSlks.computeIfAbsent(slkFile, k -> new RawSLK());

						int level = mod instanceof Obj.ExtendedMod ? ((Obj.ExtendedMod) mod).getLevel() : 0;

						DataType val = mod.getVal();

						FieldId slkFieldIdAdjusted = CommonMetaSLK.getSLKField(slkFile, metaObj, level);

						outSlk.addField(slkFieldIdAdjusted);

						SLK.Obj slkObj = outSlk.addObj(objId);

						for (File necessarySlkFile : getNecessarySLKs()) {
							SLK necessarySlk = outSlks.computeIfAbsent(necessarySlkFile, k -> new RawSLK());

							necessarySlk.addObj(objId);
						}

						/*if (slkFile.equals(UnitBalanceSLK.GAME_PATH) || slkFile.equals(UnitAbilsSLK.GAME_PATH) || slkFile.equals(UnitUISLK.GAME_PATH) || slkFile.equals(UnitWeaponsSLK.GAME_PATH)) {
							File unitBaseSLKFile = UnitDataSLK.GAME_PATH;

							SLK unitBaseSLK = outSlks.computeIfAbsent(unitBaseSLKFile, k -> new RawSLK());

							unitBaseSLK.addCamera(objId);

							//

							File unitAbilSLKFile = UnitAbilsSLK.GAME_PATH;

							SLK unitAbilSLK = outSlks.computeIfAbsent(unitAbilSLKFile, k -> new RawSLK());

							unitAbilSLK.addCamera(objId);
						}*/

						slkObj.set(slkFieldIdAdjusted, War3String.valueOf(val));

						Obj outObj = outObjMod.getObj(objId);

						if (outObj != null) {
							outObj.remove(mod);
						}
					}
				}
			}

			Obj outObj = outObjMod.getObj(objId);

			if (outObj != null) {
				outObj.remove(MetaFieldId.valueOf("wurs"));

				if (outObj.getFields().isEmpty()) {
					outObjMod.removeObj(objId);
				} else {
					outObjMod.removeObj(objId);

					outObjMod.addObj(objId, null).merge(outObj);
				}
			}
		}

		for (Map.Entry<File, SLK> slkEntry : outSlks.entrySet()) {
			SLK convSlk = SLK.createFromInFile(slkEntry.getKey(), slkEntry.getValue());

			outSlks.put(slkEntry.getKey(), convSlk);
		}

		return pack;
	}
	
	public static class EncodingFormat extends Format<EncodingFormat.Enum> {
		public enum Enum {
			AUTO,
			OBJ_0x1,
			OBJ_0x2
		}

		private final static Map<Integer, EncodingFormat> _map = new LinkedHashMap<>();

		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat OBJ_0x1 = new EncodingFormat(Enum.OBJ_0x1, 0x1);
		public final static EncodingFormat OBJ_0x2 = new EncodingFormat(Enum.OBJ_0x2, 0x2);

		@Nullable
		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}

		private EncodingFormat(@Nonnull Enum enumVal, int version) {
			super(enumVal, version);

			_map.put(version, this);
		}
	}

	private void read_0x1(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int version = stream.readInt32("version");

		stream.checkFormatVersion(EncodingFormat.OBJ_0x1.getVersion(), version);

		int origObjsAmount = stream.readInt32("origObjsAmount");
		
		for (int i = 0; i < origObjsAmount; i++) {
			ObjId baseId = ObjId.valueOf(stream.readId("baseId"));
			ObjId id = ObjId.valueOf(stream.readId("objId"));
			
			ObjType obj = createObj(id, null);
			
			obj.read(stream, EncodingFormat.OBJ_0x1);
			
			addObj(obj);
		}
		
		int customObjsAmount = stream.readInt32("customObjsAmount");

		for (int i = 0; i < customObjsAmount; i++) {
			ObjId baseId = ObjId.valueOf(stream.readId("baseId"));
			ObjId id = ObjId.valueOf(stream.readId("objId"));
			
			ObjType obj = createObj(baseId, baseId);
			
			obj.read(stream, EncodingFormat.OBJ_0x1);
			
			addObj(obj);
		}
	}

	@Nonnull
	protected abstract ObjType createObj(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException;

	private void read_0x2(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int version = stream.readInt32("version");

		stream.checkFormatVersion(EncodingFormat.OBJ_0x2.getVersion(), version);

		int origObjsAmount = stream.readInt32("origObjsAmount");

		for (int i = 0; i < origObjsAmount; i++) {
			ObjType obj = createObj(stream, EncodingFormat.OBJ_0x2);
			
			addObj(obj);
		}
		
		int customObjsAmount = stream.readInt32("customObjsAmount");

		for (int i = 0; i < customObjsAmount; i++) {
			ObjType obj = createObj(stream, EncodingFormat.OBJ_0x2);
		
			addObj(obj);
		}
	}
	
	private void write_0x1(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
		stream.writeInt32(EncodingFormat.OBJ_0x1.getVersion());
		
		stream.writeInt32(getOrigObjs().size());

		for (Obj obj : getOrigObjs()) {
			obj.write(stream, EncodingFormat.OBJ_0x1);
		}
		
		stream.writeInt32(getCustomObjs().size());
		
		for (Obj obj : getCustomObjs()) {
			obj.write(stream, EncodingFormat.OBJ_0x1);
		}
	}
	
	private void write_0x2(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
		stream.writeInt32(EncodingFormat.OBJ_0x2.getVersion());

		stream.writeInt32(getOrigObjs().size());
		
		for (int i = 0; i < getOrigObjs().size(); i++) {
			Obj obj = getOrigObjs().get(i);
			
			obj.write(stream, EncodingFormat.OBJ_0x2);
		}
		
		stream.writeInt32(getCustomObjs().size());
		
		for (int i = 0; i < getCustomObjs().size(); i++) {
			Obj obj = getCustomObjs().get(i);
			
			obj.write(stream, EncodingFormat.OBJ_0x2);
		}
	}
	
	private void read_auto(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int version = stream.readInt32("version");
		
		stream.rewind();

		EncodingFormat format = EncodingFormat.valueOf(version);

		if (format == null) throw new IllegalArgumentException("unknown format " + version);

		read(stream, format);
	}
	
	public void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
		switch (format.toEnum()) {
		case AUTO: {
			read_auto(stream);
			
			break;
		}
		case OBJ_0x1: {
			read_0x1(stream);
			
			break;
		}
		case OBJ_0x2: {
			read_0x2(stream);
			
			break;
		}
		}
	}

	public void read(@Nonnull Wc3BinInputStream stream) throws IOException {
		read(stream, EncodingFormat.AUTO);
	}
	
	public void read(@Nonnull InputStream inStream) throws IOException {
		read(new Wc3BinInputStream(inStream), EncodingFormat.AUTO);
	}
	
	public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
		switch (format.toEnum()) {
		case AUTO:
		case OBJ_0x2: {
			write_0x2(stream);
			
			break;
		}
		case OBJ_0x1: {
			write_0x1(stream);
			
			break;
		}
		}
	}

	public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
		write(stream, EncodingFormat.AUTO);
	}

	public ObjMod(@Nonnull Wc3BinInputStream stream) throws IOException {
		read(stream);
	}

	public ObjMod(@Nonnull File file) throws IOException {
		Wc3BinInputStream inStream = new Wc3BinInputStream(file);

		read(inStream);

		inStream.close();
	}
	
	public ObjMod() {
	}

	@Nullable
	public static ObjMod createFromInFile(@Nonnull File inFile, @Nonnull File outFile) throws Exception {
		ObjMod ret = null;

		if (inFile.equals(W3A.GAME_PATH)) {
			ret = new W3A(outFile);
		}
		if (inFile.equals(W3B.GAME_PATH)) {
			ret = new W3B(outFile);
		}
		if (inFile.equals(W3D.GAME_PATH)) {
			ret = new W3D(outFile);
		}
		if (inFile.equals(W3H.GAME_PATH)) {
			ret = new W3H(outFile);
		}
		if (inFile.equals(W3Q.GAME_PATH)) {
			ret = new W3Q(outFile);
		}
		if (inFile.equals(W3T.GAME_PATH)) {
			ret = new W3T(outFile);
		}
		if (inFile.equals(W3U.GAME_PATH)) {
			ret = new W3U(outFile);
		}
		
		return ret;
	}

	@Nullable
	public static ObjMod createFromInFile(@Nonnull File inFile) {
		ObjMod ret = null;

		if (inFile.equals(W3A.GAME_PATH)) {
			ret = new W3A();
		}
		if (inFile.equals(W3B.GAME_PATH)) {
			ret = new W3B();
		}
		if (inFile.equals(W3D.GAME_PATH)) {
			ret = new W3D();
		}
		if (inFile.equals(W3H.GAME_PATH)) {
			ret = new W3H();
		}
		if (inFile.equals(W3Q.GAME_PATH)) {
			ret = new W3Q();
		}
		if (inFile.equals(W3T.GAME_PATH)) {
			ret = new W3T();
		}
		if (inFile.equals(W3U.GAME_PATH)) {
			ret = new W3U();
		}
		
		return ret;
	}

	public static <T extends ObjMod> T create(@Nonnull Class<T> clazz) {
		try {
			return clazz.getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new AssertionError(e);
		}
	}

	@Nonnull
	public static <T extends ObjMod> T ofMapFile(@Nonnull Class<T> clazz, @Nonnull File mapFile) throws IOException {
		if (!mapFile.exists()) throw new IOException(String.format("file %s does not exist", mapFile));

		MpqPort.Out port = new JMpqPort.Out();

		try {
			File inFilePath = (File) clazz.getField("GAME_PATH").get(null);

			port.add(inFilePath);

			MpqPort.Out.Result portResult = port.commit(mapFile);

			if (!portResult.getExports().containsKey(inFilePath)) throw new IOException("could not extract w3a file");

			Wc3BinInputStream inStream = new Wc3BinInputStream(portResult.getInputStream(inFilePath));

			T objMod = create(clazz);

			objMod.read(inStream);

			inStream.close();

			return objMod;
		} catch (NoSuchFieldException | IllegalAccessException | ClassCastException e) {
			throw new AssertionError(e);
		}
	}
}