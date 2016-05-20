package net.moonlightflower.wc3libs.bin.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.moonlightflower.wc3libs.bin.Format;
import net.moonlightflower.wc3libs.bin.Wc3bin;
import net.moonlightflower.wc3libs.bin.Wc3bin.Stream;
import net.moonlightflower.wc3libs.bin.Wc3bin.StreamException;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.txt.TXT;
import net.moonlightflower.wc3libs.txt.TXTSectionId;

/**
 * gui triggers file for wrapping war3map.wtg
 */
public class WTG {
	public final static String GAME_PATH = "war3map.wtg";
	
	private static class Func {
		public final static Func BOOLCALL = new Func("boolcall");
		public final static Func BOOLEXPR = new Func("boolexpr");
		public final static Func CODE = new Func("code");
		public final static Func EVENTCALL = new Func("eventcall");
		
		private List<String> _params = new ArrayList<>();
		
		public List<String> getParams() {
			return _params;
		}
		
		public String getParam(int index) {
			return getParams().get(index);
		}
		
		public void addParam(String val) {
			if (val.equals("nothing")) val = null;
			
			_params.add(val);
		}
		
		String _name;
		
		public String getName() {
			return _name;
		}
		
		public Func(String name) {
			_name = name;
		}
	}
	
	private Map<String, Func> _funcs = new HashMap<>();
	
	private Map<String, Func> getFuncs() {
		return _funcs;
	}
	
	private Func getFunc(String name) {
		return getFuncs().get(name);
	}
	
	private Func addFunc(String name) {
		Func func = new Func(name);
		
		_funcs.put(name, func);
		
		return func;
	}
	
	int _unknownNumB;
	
	public int getUnknownNumB() {
		return _unknownNumB;
	}
	
	public void setUnknownNumB(int val) {
		_unknownNumB = val;
	}
	
	public static class Trig {
		WTG _wtg;
		
		String _name;
		
		public String getName() {
			return _name;
		}
		
		public void setName(String val) {
			_name = val;
		}
		
		String _description;
		
		public String getDescription() {
			return _description;
		}
		
		public void setDescription(String val) {
			_description = val;
		}
		
		public static class TrigType {
			public final static TrigType COMMENT = new TrigType(1);
			public final static TrigType NORMAL = new TrigType(0);
			
			private static Map<Integer, TrigType> _map = new HashMap<>();
			
			private int _val;
			
			public int getVal() {
				return _val;
			}
			
			private TrigType(int val) {
				_val = val;
				
				_map.put(val, this);
			}
			
			public static TrigType valueOf(int val) {
				return _map.get(val);
			}
		}
		
		private TrigType _type = TrigType.NORMAL;
		
		public TrigType getType() {
			return _type;
		}
		
		public void setType(TrigType val) {
			_type = val;
		}
		
		private boolean _enabled = true;
		
		public boolean isEnabled() {
			return _enabled;
		}
		
		public void setEnabled(boolean val) {
			_enabled = val;
		}
		
		private boolean _customTxt = false;
		
		public boolean isCustomTxt() {
			return _customTxt;
		}
		
		public void setCustomTxt(boolean val) {
			_customTxt = val;
		}
		
		private boolean _initiallyOn = true;
		
		public boolean isInitiallyOn() {
			return _initiallyOn;
		}
		
		public void setInitiallyOn(boolean val) {
			_initiallyOn = val;
		}

		private boolean _runOnMapInit = true;
		
		public boolean isRunOnMapInit() {
			return _runOnMapInit;
		}
		
		public void setRunOnMapInit(boolean val) {
			_runOnMapInit = val;
		}
		
		private int _catIndex;
		
		public int getCatIndex() {
			return _catIndex;
		}
		
		public void setCatIndex(int val) {
			_catIndex = val;
		}
		
		public static class ECA {
			private WTG _wtg;
			
			public WTG getWTG() {
				return _wtg;
			}
			
			public static class ECAType {
				public final static ECAType ACTION = new ECAType(2);
				public final static ECAType CONDITION = new ECAType(1);
				public final static ECAType EVENT = new ECAType(0);
				
				private static Map<Integer, ECAType> _map = new HashMap<>();
				
				private int _val;
				
				public int getVal() {
					return _val;
				}
				
				private ECAType(int val) {
					_val = val;
					
					_map.put(val, this);
				}
				
				public static ECAType valueOf(int val) {
					return _map.get(val);
				}
			}
			
			private ECAType _type;
			
			public ECAType getType() {
				return _type;
			}
			
			public void setType(ECAType val) {
				_type = val;
			}
			
			private int _branch = -1;
			
			public int getBranch() {
				return _branch;
			}
			
			public void setBranch(int val) {
				_branch = val;
			}
			
			private Func _func;
			
			public Func getFunc() {
				return _func;
			}
			
			public void setFunc(Func val) {
				_func = val;
			}
			
			public void setFunc(String name) {
				setFunc(_wtg.getFunc(name));
			}
			
			private boolean _enabled = true;
			
			public boolean isEnabled() {
				return _enabled;
			}
			
			public void setEnabled(boolean val) {
				_enabled = val;
			}
			
			public static class Param {
				private WTG _wtg;
				
				private Object _parent;
				
				public Object getParentECA() {
					return _parent;
				}

				private Func _func;
				
				public Func getFunc() {
					return _func;
				}
				
				public void setFunc(Func val) {
					_func = val;
				}
				
				private String _valType;
				
				public String getValType() {
					return _valType;
				}
				
				public void setValType(String val) {
					_valType = val;
				}
				
				private int _endToken = 0;
				
				public int getEndToken() {
					return _endToken;
				}
				
				public void setEndToken(int val) {
					_endToken = val;
				}
				
				public static class SpecType {
					public final static SpecType PRESET = new SpecType(0);
					public final static SpecType VARIABLE = new SpecType(1);
					public final static SpecType FUNCTION = new SpecType(2);
					public final static SpecType STRING = new SpecType(3);
					
					private static Map<Integer, SpecType> _map = new HashMap<>();
					
					private int _val;
					
					public int getVal() {
						return _val;
					}
					
					private SpecType(int val) {
						_val = val;
						
						_map.put(val, this);
					}
					
					public static SpecType valueOf(int val) {
						return _map.get(val);
					}
				}
				
				private SpecType _specType;
				
				public SpecType getSpecType() {
					return _specType;
				}
				
				public void setSpecType(SpecType val) {
					_specType = val;
				}
				
				private String _val;
				
				public String getVal() {
					return _val;
				}
				
				public void setVal(String val) {
					_val = val;
				}
				
				private int _beginFunc = 0;
				
				public int getBeginFunc() {
					return _beginFunc;
				}
				
				public void setBeginFunc(int val) {
					_beginFunc = val;
				}
				
				private int _boolexpr_unknown1;
				
				public int getBoolexpr_Unknown1() {
					return _boolexpr_unknown1;
				}
				
				public void setBoolexpr_Unknown1(int val) {
					_boolexpr_unknown1 = val;
				}

				private int _boolexpr_unknown2;
				
				public int getBoolexpr_Unknown2() {
					return _boolexpr_unknown2;
				}
				
				public void setBoolexpr_Unknown2(int val) {
					_boolexpr_unknown2 = val;
				}

				private char _boolexpr_unknown3;
				
				public int getBoolexpr_Unknown3() {
					return _boolexpr_unknown1;
				}
				
				public void setBoolexpr_Unknown3(char val) {
					_boolexpr_unknown3 = val;
				}
				
				private int _code_unknown1;
				
				public int getCode_unknown1() {
					return _code_unknown1;
				}
				
				public void setCode_unknown1(int val) {
					_code_unknown1 = val;
				}
				
				private char _code_unknown2;
				
				public char getCode_unknown2() {
					return _code_unknown2;
				}
				
				public void setCode_unknown2(char val) {
					_code_unknown2 = val;
				}
				
				private Character[] _code_unknown = new Character[12];
				
				public char getCode_unknown(int index) {
					return _code_unknown[index];
				}
				
				public void setCode_unknown(int index, char val) {
					_code_unknown[index] = val;
				}
				
				private ECA _eca;
				
				public ECA getEca() {
					return _eca;
				}
				
				public void setECA(ECA val) {
					_eca = val;
				}

				private List<Param> _params = new ArrayList<>();
				
				public Param addParam(String valType) {
					Param sub = new Param(_wtg, this, valType);
					
					_params.add(sub);
					
					return sub;
				}
				
				public void read_0x4(Stream stream) {
					Func func = getFunc();
					
					if ((func == Func.BOOLEXPR) || (func == Func.BOOLCALL) || (func == Func.EVENTCALL)) {
						setBoolexpr_Unknown1(stream.readInt());
						setBoolexpr_Unknown2(stream.readInt());
						setBoolexpr_Unknown3(stream.readChar());
						
						ECA eca = new ECA(_wtg);
						
						eca.read(stream, EncodingFormat.WTG_0x4, false);
						
						setECA(eca);
						
						setEndToken(stream.readInt());
					} else if (func == Func.CODE) {
						setCode_unknown1(stream.readInt());
						
						int dummyDoNothing = stream.readInt();
						
						if (dummyDoNothing == 0x100) {
							setCode_unknown2(stream.readChar());
						} else {
							for (int i = 2; i < 12; i++) {
								setCode_unknown(i, stream.readChar());
							}
						}
						
						ECA eca = new ECA(_wtg);
						
						eca.read(stream, EncodingFormat.WTG_0x4, false);
						
						setECA(eca);
						
						setEndToken(stream.readInt());
					} else {
						SpecType specType = SpecType.valueOf(stream.readInt());
						String val = stream.readString();
						
						setSpecType(specType);
						setVal(val);
						
						int beginFunc = stream.readInt();
						
						setBeginFunc(beginFunc);
						
						if (beginFunc == 1) {
							setSpecType(SpecType.valueOf(stream.readInt())); //beginFunc_type = 3
							
							int pos = stream.getPos();
							
							String funcName = stream.readString();
							
							setVal(funcName); //beginFunc_val = val
							
							setBeginFunc(stream.readInt()); //beginFunc_beginFunc = 1
							
							func = getFunc();
							
							if (func != null) {
								for (int i = 0; i < func.getParams().size(); i++) {
									Param sub = addParam(func.getParam(i));
									
									sub.read(stream, EncodingFormat.WTG_0x4);
								}
							} else {
								throw (String.format("unknown func %s at %s", funcName, pos, String.format("%x", pos)));
							}
							
							stream.readInt(); //beginFunc_endToken = 0
						}
						
						stream.readInt(); //endToken = 0

						if ((specType == SpecType.VARIABLE) && (_wtg.getVar(val).isArray())) {
							Param sub = addParam("int");
							
							sub.read(stream, EncodingFormat.WTG_0x4);
						}
					}
				}
				
				public void write_0x4(Stream stream) {
					// TODO Auto-generated method stub
					
				}
				
				public void read(Stream stream, EncodingFormat format) {
					switch (format.toEnum()) {
					case WTG_0x7:
					case WTG_0x4: {
						read_0x4(stream);
					}
					}
				}
				
				public void write(Stream stream, EncodingFormat format) {
					switch (format.toEnum()) {
					case AUTO:
					case WTG_0x7:
					case WTG_0x4: {
						write_0x4(stream);
					}
					}
				}

				public Param(WTG wtg, Param parentParam, String valType) {
					_parent = parentParam;
					
					setValType(valType);
				}
				
				public Param(WTG wtg, ECA parentECA, String valType) {
					_parent = parentECA;
					
					setValType(valType);
				}
			}
			
			private List<Param> _params = new ArrayList<>();
			
			public Param addParam(String type) {
				Param param = new Param(_wtg, this, type);
				
				_params.add(param);
				
				return param;
			}

			private List<ECA> _ecas = new ArrayList<>();
			
			public ECA addECA() {
				ECA sub = new ECA(getWTG());
				
				_ecas.add(sub);
				
				return sub;
			}
			
			public void read_0x4(Stream stream, boolean hasBranch) {
				setType(ECAType.valueOf(stream.readInt()));
				
				if (hasBranch) {
					setBranch(stream.readInt());
				}
				
				String funcName = stream.readString();
				
				Func func = getWTG().getFunc(funcName);
				
				setFunc(func);
				setEnabled(stream.readInt() != 0);
				
				if (func != null) {
					for (int i = 0; i < func.getParams().size(); i++) {
						Param param = addParam(func.getParam(i));
						
						param.read(stream, EncodingFormat.WTG_0x4);
					}
				} else {
					throw (String.format("unknown func %s", funcName));
				}
				
				int ECAsCount = stream.readInt();
				
				for (int i = 0; i < ECAsCount; i++) {
					ECA sub = addECA();
					
					sub.read(stream, EncodingFormat.WTG_0x4, true);
				}
			}
			
			public void write_0x4(Stream stream) {
				// TODO Auto-generated method stub
				
			}
			
			public void read(Stream stream, EncodingFormat format, boolean hasBranch) {
				switch (format.toEnum()) {
				case WTG_0x7:
				case WTG_0x4: {
					read_0x4(stream, hasBranch);
				}
				}
			}
			
			public void write(Stream stream, EncodingFormat format) {
				switch (format.toEnum()) {
				case AUTO:
				case WTG_0x7:
				case WTG_0x4: {
					write_0x4(stream);
				}
				}
			}
			
			public ECA(WTG wtg) {
			}
		}
		
		private List<ECA> _ecas = new ArrayList<>();
		
		public ECA addECA() {
			ECA eca = new ECA(_wtg);
			
			_ecas.add(eca);
			
			return eca;
		}

		public void write_0x4(Stream stream) {
			stream.writeString(getName());
			stream.writeString(getDescription());
			stream.writeInt(getType().getVal());
			stream.writeInt(isEnabled() ? 1 : 0);
			stream.writeInt(isCustomTxt() ? 1 : 0);
			stream.writeInt(isInitiallyOn() ? 1 : 0);
			stream.writeInt(isRunOnMapInit() ? 1 : 0);
			stream.writeInt(getCatIndex());
			
			for (ECA eca : _ecas) {
				eca.write(stream, EncodingFormat.WTG_0x4);
			}
		}

		public void read_0x4(Stream stream) throws StreamException {
			setName(stream.readString());
			setDescription(stream.readString());
			setType(TrigType.valueOf(stream.readInt()));
			setEnabled(stream.readInt() != 0);
			setCustomTxt(stream.readInt() != 0);
			setInitiallyOn(stream.readInt() != 0);
			setRunOnMapInit(stream.readInt() != 0);
			setCatIndex(stream.readInt());
			
			int ECAsCount = stream.readInt();
			
			for (int i = 0; i < ECAsCount; i++) {
				ECA eca = addECA();

				eca.read(stream, EncodingFormat.WTG_0x4, false);
			}
		}
		
		public void read(Stream stream, EncodingFormat format) throws StreamException {
			switch (format.toEnum()) {
			case WTG_0x7:
			case WTG_0x4: {
				read_0x4(stream);
			}
			}
		}
		
		public void write(Stream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case WTG_0x7:
			case WTG_0x4: {
				write_0x4(stream);
			}
			}
		}
		
		public Trig(Stream stream, EncodingFormat format) throws StreamException {
			read(stream, format);
		}
		
		public Trig() {
		}
	}
	
	private List<Trig> _trigs = new ArrayList<>();
	
	private void addTrig(Trig val) {
		_trigs.add(val);
	}
	
	public Trig addTrig() {
		Trig trig = new Trig();
		
		addTrig(trig);
		
		return trig;
	}
	
	public static class TrigCat {
		private int _index;
		
		public int getIndex() {
			return _index;
		}
		
		public void setIndex(int val) {
			_index = val;
		}
		
		private String _name;
		
		public String getName() {
			return _name;
		}
		
		public void setName(String val) {
			_name = val;
		}
		
		public static class Type {
			public final static Type COMMENT = new Type(1);
			public final static Type NORMAL = new Type(0);
			
			private static Map<Integer, Type> _map = new HashMap<>();
			
			private int _val;
			
			public int getVal() {
				return _val;
			}
			
			private Type(int val) {
				_val = val;
				
				_map.put(val, this);
			}
			
			public static Type valueOf(int val) {
				return _map.get(val);
			}
		}
		
		private Type _type = Type.NORMAL;
		
		public Type getType() {
			return _type;
		}
		
		public void setType(Type val) {
			_type = val;
		}
		
		public void read_0x4(Stream stream) throws StreamException {
			setIndex(stream.readInt());
			setName(stream.readString());
			setType(Type.valueOf(stream.readInt()));
		}
		
		public void write_0x4(Stream stream) {
			stream.writeInt(getIndex());
			stream.writeString(getName());
			stream.writeInt(getType().getVal());
		}
		
		public void read(Stream stream, EncodingFormat format) throws StreamException {
			switch (format.toEnum()) {
			case WTG_0x7:
			case WTG_0x4: {
				read_0x4(stream);
			}
			}
		}
		
		public void write(Stream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case WTG_0x7:
			case WTG_0x4: {
				write_0x4(stream);
			}
			}
		}
	}

	private List<TrigCat> _trigCats = new ArrayList<>();
	
	public TrigCat addTrigCat() {
		TrigCat trigCat = new TrigCat();
		
		_trigCats.add(trigCat);
		
		return trigCat;
	}

	public static class Var {
		private String _name;
		
		public String getName() {
			return _name;
		}
		
		public void setName(String val) {
			_name = val;
		}
		
		private String _type;
		
		public String getType() {
			return _type;
		}
		
		public void setType(String val) {
			_type = val;
		}
		
		private int _unknownNumE;
		
		public int getUnknownNumE() {
			return _unknownNumE;
		}
		
		public void setUnknownNumE(int val) {
			_unknownNumE = val;
		}
		
		private boolean _arrayFlag = false;
		
		public boolean isArray() {
			return _arrayFlag;
		}
		
		public void setArray(boolean val) {
			_arrayFlag = val;
		}
		
		private int _arraySize;
		
		public int getArraySize() {
			return _arraySize;
		}
		
		public void setArraySize(int val) {
			_arraySize = val;
		}
		
		private boolean _initedFlag = false;
		
		public boolean isInited() {
			return _initedFlag;
		}
		
		public void setInited(boolean val) {
			_initedFlag = val;
		}
		
		private String _initVal;
		
		public String getInitVal() {
			return _initVal;
		}
		
		public void setInitVal(String val) {
			_initVal = val;
		}

		public void read_0x4(Stream stream) throws StreamException {
			setName(stream.readString());
			setType(stream.readString());
			setUnknownNumE(stream.readInt());
			
			setArray(stream.readInt() != 0);
			setArraySize(stream.readInt());
			setInited(stream.readInt() != 0);
			setInitVal(stream.readString());
		}

		public void write_0x4(Stream stream) {
			stream.writeString(getName());
			stream.writeString(getType());
			stream.writeInt(getUnknownNumE());
			
			stream.writeInt(isArray() ? 1 : 0);
			stream.writeInt(getArraySize());
			stream.writeInt(isInited() ? 1 : 0);
			stream.writeString(getInitVal());
		}
		
		private void read(Stream stream, EncodingFormat format) throws StreamException {		
			switch (format.toEnum()) {
			case WTG_0x7:
			case WTG_0x4: {
				read_0x4(stream);
				
				break;
			}
			}
		}

		private void write(Stream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case WTG_0x7:
			case WTG_0x4: {
				write_0x4(stream);
				
				break;
			}
			}
		}
		
		public Var(Stream stream, EncodingFormat format) throws StreamException {
			read(stream, format);
		}
		
		public Var(String name) {
			_name = name;
		}
	}

	private Map<String, Var> _vars = new HashMap<>();
	
	public Map<String, Var> getVars() {
		return _vars;
	}
	
	public Var getVar(String name) {
		return _vars.get(name);
	}
	
	private void addVar(Var val) {
		_vars.put(val.getName(), val);
	}
	
	public Var addVar(String name) {
		Var var = new Var(name);
		
		addVar(var);
		
		return var;
	}

	public void addTriggerData(TXT txt) {
		Map<String, Integer> sections = new HashMap<>();
		
		sections.put("TriggerEvents", 2);
		sections.put("TriggerConditions", 2);
		sections.put("TriggerActions", 2);
		sections.put("TriggerCalls", 4);
		
		for (Map.Entry<String, Integer> sectionEntry : sections.entrySet()) {
			String sectionName = sectionEntry.getKey();
			int argsOffset = sectionEntry.getValue();
			
			TXT.Section section = txt.getSection(TXTSectionId.valueOf(sectionName));
			
			for (Map.Entry<FieldId, ? extends TXT.Section.Field> fieldEntry : section.getFields().entrySet()) {
				FieldId key = fieldEntry.getKey();
				TXT.Section.Field field = fieldEntry.getValue();
				
				String[] vals = field.getValLine(null).split(";");
				
				Func func = addFunc(key.toString());
				
				for (int i = argsOffset; i < vals.length; i++) {
					func.addParam(vals[i]);
				}
			}
		}
	}
	
	private static class EncodingFormat extends Format<EncodingFormat.Enum> {
		enum Enum {
			AUTO,
			WTG_0x4,
			WTG_0x7
		}

		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat WTG_0x4 = new EncodingFormat(Enum.WTG_0x4, 0x4);
		public final static EncodingFormat WTG_0x7 = new EncodingFormat(Enum.WTG_0x7, 0x7);

		private static Map<Integer, EncodingFormat> _map = new HashMap<>();
		
		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}
		
		private EncodingFormat(Enum enumVal, int version) {
			super(enumVal, version);
			
			_map.put(version, this);
		}
	}

	private void read_0x4(Stream stream) throws StreamException {
		Id startToken = stream.readId();
		
		int version = stream.readInt();
		
		Wc3bin.checkFormatVer("guiTrigMaskFunc", EncodingFormat.WTG_0x4.getVersion(), version);
		
		int trigCatsCount = stream.readInt();
		
		for (int i = 0; i < trigCatsCount; i++) {
			TrigCat trigCat = addTrigCat();
			
			trigCat.read(stream, EncodingFormat.WTG_0x4);
		}
		
		int unknownNumB = stream.readInt();
		
		int varsCount = stream.readInt();
		
		for (int i = 0; i < varsCount; i++) {
			addVar(new Var(stream, EncodingFormat.WTG_0x4));
		}
		
		int trigsCount = stream.readInt();

		for (int i = 0; i < trigsCount; i++) {
			addTrig(new Trig(stream, EncodingFormat.WTG_0x4));
		}
	}
	
	private void write_0x4(Stream stream) {
		stream.writeId(Id.valueOf("WTG!"));
		
		stream.writeInt(EncodingFormat.WTG_0x4.getVersion());
		
		stream.writeInt(_trigCats.size());
		
		for (TrigCat trigCat : _trigCats) {
			trigCat.write(stream, EncodingFormat.WTG_0x4);
		}
		
		stream.writeInt(getUnknownNumB());
		
		stream.writeInt(_vars.size());
		
		for (Var var : _vars.values()) {
			var.write(stream, EncodingFormat.WTG_0x4);
		}
		
		stream.writeInt(_trigs.size());
		
		for (Trig trig : _trigs) {
			trig.write(stream, EncodingFormat.WTG_0x4);
		}
	}
	
	private void read_0x7(Stream stream) throws StreamException {
		Id startToken = stream.readId();
		
		int version = stream.readInt();
		
		Wc3bin.checkFormatVer("guiTrigMaskFunc", EncodingFormat.WTG_0x7.getVersion(), version);
		
		int trigCatsCount = stream.readInt();
		
		for (int i = 0; i < trigCatsCount; i++) {
			TrigCat trigCat = addTrigCat();
			
			trigCat.read(stream, EncodingFormat.WTG_0x7);
		}
		
		int unknownNumB = stream.readInt();
		
		int varsCount = stream.readInt();
		
		for (int i = 0; i < varsCount; i++) {
			addVar(new Var(stream, EncodingFormat.WTG_0x7));
		}
		
		int trigsCount = stream.readInt();

		for (int i = 0; i < trigsCount; i++) {
			Trig trig = addTrig();
			
			trig.read(stream, EncodingFormat.WTG_0x7);
		}
	}
	
	private void write_0x7(Stream stream) {
		stream.writeId(Id.valueOf("WTG!"));
		
		stream.writeInt(EncodingFormat.WTG_0x7.getVersion());
		
		stream.writeInt(_trigCats.size());
		
		for (TrigCat trigCat : _trigCats) {
			trigCat.write(stream, EncodingFormat.WTG_0x7);
		}
		
		stream.writeInt(getUnknownNumB());
		
		stream.writeInt(_vars.size());
		
		for (Var var : _vars.values()) {
			var.write(stream, EncodingFormat.WTG_0x7);
		}
		
		stream.writeInt(_trigs.size());
		
		for (Trig trig : _trigs) {
			trig.write(stream, EncodingFormat.WTG_0x7);
		}
	}
	
	private void read_auto(Stream stream) throws StreamException {
		Id startToken = stream.readId();
		int version = stream.readInt();
		
		stream.rewind();

		read(stream, EncodingFormat.valueOf(version));
	}
	
	private void read(Stream stream, EncodingFormat format) throws StreamException {		
		switch (format.toEnum()) {
		case WTG_0x4: {
			read_0x4(stream);
			
			break;
		}
		case WTG_0x7: {
			read_0x7(stream);
			
			break;
		}
		case AUTO: {
			read_auto(stream);
			
			break;
		}
		}
	}
	
	private void write(Stream stream, EncodingFormat format) {
		switch (format.toEnum()) {
		case WTG_0x4: {
			write_0x4(stream);
			
			break;
		}
		case WTG_0x7: {
			write_0x7(stream);
			
			break;
		}
		case AUTO: {
			write_0x7(stream);
			
			break;
		}
		}
	}
	
	private void read(Stream stream) throws StreamException {
		read(stream, EncodingFormat.AUTO);
	}
	
	private void write(Stream stream) {
		write(stream, EncodingFormat.AUTO);
	}
	
	private void read(File file, EncodingFormat format) throws IOException {
		read(new Wc3bin.Stream(file), format);
	}
	
	private void write(File file, EncodingFormat format) throws IOException {
		write(new Wc3bin.Stream(file), format);
	}
	
	private void read(File file) throws IOException {
		read(file, EncodingFormat.AUTO);
	}

	private void write(File file) throws IOException {
		write(new Wc3bin.Stream(file));
	}
	
	public WTG(Stream stream) throws StreamException {
		read(stream);
	}
	
	public WTG(File file) throws IOException {
		this(new Wc3bin.Stream(file));
	}
	
	public WTG() {
	}
}
