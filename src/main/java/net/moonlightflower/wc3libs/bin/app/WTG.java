package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.BinStream.StreamException;
import net.moonlightflower.wc3libs.bin.Format;
import net.moonlightflower.wc3libs.bin.Wc3BinStream;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.port.LadikMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.txt.TXT;
import net.moonlightflower.wc3libs.txt.TXTSectionId;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * gui triggers file for wrapping war3map.wtg
 */
public class WTG {
	public final static File GAME_PATH = new File("war3map.wtg");
	
	public static class FuncCat {
		private File _iconFile;
		
		public File getIconFile() {
			return _iconFile;
		}
		
		public void setIconFile(File val) {
			_iconFile = val;
		}
		
		private FieldId _id;
		
		public FieldId getId() {
			return _id;
		}
		
		public FuncCat(FieldId id) {
			_id = id;
		}
	}
	
	private Map<FieldId, FuncCat> _funcCats = new LinkedHashMap<>();
	
	public Map<FieldId, FuncCat> getFuncCats() {
		return _funcCats;
	}
	
	public void addFuncCat(FuncCat val) {
		_funcCats.put(val.getId(), val);
	}
	
	public FuncCat addFuncCat(FieldId id) {
		if (_funcCats.containsKey(id)) return _funcCats.get(id);
		
		FuncCat cat = new FuncCat(id);
		
		addFuncCat(cat);
		
		return cat;
	}
	
	public static class Func {
		public static enum SpecialType {
			NORMAL,
			BOOLCALL,
			BOOLEXPR,
			CODE,
			EVENTCALL
		}
		
		public final static Func BOOLCALL = new Func("boolcall");
		public final static Func BOOLEXPR = new Func("boolexpr");
		public final static Func CODE = new Func("code");
		public final static Func EVENTCALL = new Func("eventcall");
		
		private SpecialType _type = SpecialType.NORMAL;
		
		public SpecialType getType() {
			return _type;
		}
		
		private String _cat = null;
		
		public String getCat() {
			return _cat;
		}
		
		public void setCat(String val) {
			_cat = val;
		}
		
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
		
		private String _name;
		
		public String getName() {
			return _name;
		}
		
		@Override
		public String toString() {
			return getName();
		}
		
		public Func(String name) {
			_name = name;

			Map<String, SpecialType> typeMap = new LinkedHashMap<>();
			
			typeMap.put("boolcall", SpecialType.BOOLCALL);
			typeMap.put("boolexpr", SpecialType.BOOLEXPR);
			typeMap.put("eventcall", SpecialType.EVENTCALL);
			typeMap.put("code", SpecialType.CODE);
		}
	}
	
	private Map<String, Func> _funcs = new LinkedHashMap<>();
	
	private Map<String, Func> getFuncs() {
		return _funcs;
	}
	
	private Func getFunc(String name) {
		return getFuncs().get(name);
	}
	
	private void addFunc(Func val) {
		_funcs.put(val.getName(), val);
	}
	
	private Func addFunc(String name) {
		if (_funcs.containsKey(name)) return _funcs.get(name);
		
		Func func = new Func(name);
		
		addFunc(func);
		
		return func;
	}
	
	private int _unknownNumB;
	
	public int getUnknownNumB() {
		return _unknownNumB;
	}
	
	public void setUnknownNumB(int val) {
		_unknownNumB = val;
	}
	
	public static class Trig {
		private String _name;
		
		public String getName() {
			return _name;
		}
		
		public void setName(String val) {
			_name = val;
		}
		
		private String _description;
		
		public String getDescription() {
			return _description;
		}
		
		public void setDescription(String val) {
			_description = val;
		}
		
		public static class TrigType {
			private static Map<Integer, TrigType> _map = new LinkedHashMap<>();
			
			public final static TrigType COMMENT = new TrigType(1);
			public final static TrigType NORMAL = new TrigType(0);
			
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
			public static class ECAType {
				private static Map<Integer, ECAType> _map = new LinkedHashMap<>();
				
				public final static ECAType ACTION = new ECAType(2);
				public final static ECAType CONDITION = new ECAType(1);
				public final static ECAType EVENT = new ECAType(0);
				
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
			
			private boolean _enabled = true;
			
			public boolean isEnabled() {
				return _enabled;
			}
			
			public void setEnabled(boolean val) {
				_enabled = val;
			}
			
			@Override
			public String toString() {
				return getFunc().toString();
			}
			
			public abstract static class Param {
				private String _val;
				
				public String getVal() {
					return _val;
				}
				
				public void setVal(String val) {
					_val = val;
				}
				
				private List<Param> _params = new ArrayList<>();
				
				public List<Param> getParams() {
					return _params;
				}
				
				public void addParam(Param val) {
					_params.add(val);
				}
				
				@Override
				public String toString() {
					return getVal();
				}
			}
			
			public static class NormalParam extends Param {				
				public static class SpecType {
					private static Map<Integer, SpecType> _map = new LinkedHashMap<>();
					
					public final static SpecType PRESET = new SpecType(0);
					public final static SpecType VARIABLE = new SpecType(1);
					public final static SpecType FUNCTION = new SpecType(2);
					public final static SpecType STRING = new SpecType(3);
					
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
				
				private int _beginFunc = 0;
				
				public int getBeginFunc() {
					return _beginFunc;
				}
				
				public void setBeginFunc(int val) {
					_beginFunc = val;
				}
				
				private void read_0x4(Wc3BinStream stream, Map<String, Func> funcMap, Map<String, Var> varMap, boolean hasBranch) throws Exception {					
					SpecType specType = SpecType.valueOf(stream.readInt("specType"));
					/*
					 * 0 - preset
					 * 1 - variable
					 * 2 - function
					 * 3 - literal
					 */
					
					String val = stream.readString("val");
					
					int beginFunc = stream.readInt("beginFunc");
					
					if (beginFunc > 0) {
						int type2 = stream.readInt("specType2");
						
						String val2 = stream.readString("val2");
						
						int beginFunc2 = stream.readInt("beginFunc2");
						
						if (beginFunc2 > 0) {
							Func func = funcMap.get(val2);
							System.out.println("try " + val);
							if (func != null) {
							System.out.println("enter " + val+";"+func +";" + func.getParams().size());
							System.out.println("ABC " + func.getParams());
							for (int i = 0; i < func.getParams().size(); i++) {
								stream.beginGroup(String.format("param%d", i));
								
								Param sub = new NormalParam(stream, EncodingFormat.WTG_0x4, funcMap, varMap, true);
								
								stream.endGroup();
								
								addParam(sub);
							}
							}
							
							if (!hasBranch) {
								//stream.readInt("unknownB");  //unknown
							}
						}

						stream.readInt("endToken");  //unknown
					} else {
						stream.readInt("unknownC");  //unknown
					}
				}
				
				private void write_0x4(Wc3BinStream stream) {
					
				}
				
				private void read_0x7(Wc3BinStream stream, Map<String, Func> funcMap, Map<String, Var> varMap, boolean hasBranch) throws Exception {					
					SpecType specType = SpecType.valueOf(stream.readInt("specType"));
					String val = stream.readString("val");
					
					setSpecType(specType);
					setVal(val);
					
					int beginFunc = stream.readInt("beginFunc");
					
					setBeginFunc(beginFunc);
					
					if (beginFunc == 1) {
						stream.beginGroup("beginFunc2");
						
						setSpecType(SpecType.valueOf(stream.readInt("specType2"))); //beginFunc_type = 3
						
						int pos = stream.getPos();
						
						String funcName = stream.readString("funcName2");
						
						setVal(funcName); //beginFunc_val = val
						
						Func func = funcMap.get(funcName);
						
						setBeginFunc(stream.readInt()); //beginFunc_beginFunc = 1

						if (func != null) {
							for (int i = 0; i < func.getParams().size(); i++) {
								Param sub = null;
								
								String subFuncName = func.getParam(i);
								
								Func subFunc = funcMap.get(funcName);

								if (subFunc != null) {
									stream.beginGroup(String.format("param%d", i));

									switch (subFunc.getType()) {
									case BOOLCALL:
									case BOOLEXPR:
									case EVENTCALL: {
										//sub = new BoolCodeParam(stream, EncodingFormat.WTG_0x7, funcMap, varMap);
										
										break;
									}
									case CODE: {
										//sub = new CodeParam(stream, EncodingFormat.WTG_0x7, funcMap, varMap);
										
										break;
									}
									default: {
										sub = new NormalParam(stream, EncodingFormat.WTG_0x7, funcMap, varMap, false);
									}
									}
									
									stream.endGroup();
								} else {
									throw new IOException(String.format("unknown func %s", subFuncName));
								}
								
								addParam(sub);
							}
						} else {
							stream.endGroup();
							
							stream.printLog(System.err);
							
							throw new IOException(String.format("unknown func %s at %s", funcName, pos, String.format("%x", pos)));
						}
						
						stream.readInt("endToken"); //beginFunc_endToken = 0
						
						stream.endGroup();
					}
					
					stream.readInt("endToken"); //endToken = 0
System.out.println(val);
					if ((specType == SpecType.VARIABLE) && (varMap.containsKey(val) && varMap.get(val).isArray())) {
						stream.beginGroup("arrayIndex");
						
						Param sub = new NormalParam(stream, EncodingFormat.WTG_0x7, funcMap, varMap, false);
						
						stream.endGroup();
						
						addParam(sub);
					}
				}
				
				private void write_0x7(Wc3BinStream stream) {
					
				}
				
				private void read(Wc3BinStream stream, EncodingFormat format, Map<String, Func> funcMap, Map<String, Var> varMap, boolean hasBranch) throws Exception {
					switch (format.toEnum()) {
					case WTG_0x7:
						read_0x7(stream, funcMap, varMap, hasBranch);
						
						break;
					case WTG_0x4: {
						read_0x4(stream, funcMap, varMap, hasBranch);
						
						break;
					}
					}
				}
				
				private void write(Wc3BinStream stream, EncodingFormat format) {
					switch (format.toEnum()) {
					case AUTO:
					case WTG_0x7:
						write_0x7(stream);
						
						break;
					case WTG_0x4: {
						write_0x4(stream);
						
						break;
					}
					}
				}
				
				public NormalParam(Wc3BinStream stream, EncodingFormat format, Map<String, Func> funcMap, Map<String, Var> varMap, boolean hasBranch) throws Exception {
					read(stream, format, funcMap, varMap, hasBranch);
				}
			}
			
			public abstract static class ECAParam extends Param {
				private ECA _eca;
				
				public ECA getEca() {
					return _eca;
				}
				
				public void setECA(ECA val) {
					_eca = val;
				}
				
				private int _endToken = 0;
				
				public int getEndToken() {
					return _endToken;
				}
				
				public void setEndToken(int val) {
					_endToken = val;
				}
			}
			
			public static class BoolCodeParam extends ECAParam {
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
				
				private void read_0x4(Wc3BinStream stream, Map<String, Func> funcMap, Map<String, Var> varMap) throws Exception {					
					stream.beginGroup("boolexpr");
					
					setBoolexpr_Unknown1(stream.readInt("unknown1"));
					setBoolexpr_Unknown2(stream.readInt("unknown2"));
					setBoolexpr_Unknown3(stream.readChar("unknown3"));
					
					ECA eca = new ECA(stream, EncodingFormat.WTG_0x4, funcMap, varMap, false);
					
					setECA(eca);
					
					setEndToken(stream.readInt("endToken"));
					
					stream.endGroup();
				}
				
				private void write_0x4(Wc3BinStream stream) {
					
				}
				
				private void read_0x7(Wc3BinStream stream, Map<String, Func> funcMap, Map<String, Var> varMap) throws Exception {					
					stream.beginGroup("boolexpr");
					
					setBoolexpr_Unknown1(stream.readInt("unknown1"));
					setBoolexpr_Unknown2(stream.readInt("unknown2"));
					setBoolexpr_Unknown3(stream.readChar("unknown3"));
					
					ECA eca = new ECA(stream, EncodingFormat.WTG_0x4, funcMap, varMap, false);
					
					setECA(eca);
					
					setEndToken(stream.readInt("endToken"));
					
					stream.endGroup();
				}
				
				private void write_0x7(Wc3BinStream stream) {
					
				}
				
				private void read(Wc3BinStream stream, EncodingFormat format, Map<String, Func> funcMap, Map<String, Var> varMap) throws Exception {
					switch (format.toEnum()) {
					case WTG_0x7:
						read_0x7(stream, funcMap, varMap);
						
						break;
					case WTG_0x4: {
						read_0x4(stream, funcMap, varMap);
						
						break;
					}
					}
				}
				
				private void write(Wc3BinStream stream, EncodingFormat format) {
					switch (format.toEnum()) {
					case AUTO:
					case WTG_0x7:
						write_0x7(stream);
						
						break;
					case WTG_0x4: {
						write_0x4(stream);
						
						break;
					}
					}
				}
				
				public BoolCodeParam(Wc3BinStream stream, EncodingFormat format, Map<String, Func> funcMap, Map<String, Var> varMap) throws Exception {
					read(stream, format, funcMap, varMap);
				}
			}
			
			public static class CodeParam extends ECAParam {
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
				
				private void read_0x4(Wc3BinStream stream, Map<String, Func> funcMap, Map<String, Var> varMap) throws Exception {					
					stream.beginGroup("code");
					
					setCode_unknown1(stream.readInt("unknown1"));
					
					int dummyDoNothing = stream.readInt("dummyDoNothing");
					
					if (dummyDoNothing == 0x100) {
						setCode_unknown2(stream.readChar("unknown2"));
					} else {
						for (int i = 3; i < 13; i++) {
							setCode_unknown(i, stream.readChar(String.format("unknown%d", i)));
						}
					}
					
					ECA eca = new ECA(stream, EncodingFormat.WTG_0x4, funcMap, varMap, false);
					
					setECA(eca);
					
					setEndToken(stream.readInt());
					
					stream.endGroup();
				}
				
				private void write_0x4(Wc3BinStream stream) {
					
				}
				
				private void read_0x7(Wc3BinStream stream, Map<String, Func> funcMap, Map<String, Var> varMap) throws Exception {					
					stream.beginGroup("code");
					
					setCode_unknown1(stream.readInt("unknown1"));
					
					int dummyDoNothing = stream.readInt("dummyDoNothing");
					
					if (dummyDoNothing == 0x100) {
						setCode_unknown2(stream.readChar("unknown2"));
					} else {
						for (int i = 3; i < 13; i++) {
							setCode_unknown(i, stream.readChar(String.format("unknown%d", i)));
						}
					}
					
					ECA eca = new ECA(stream, EncodingFormat.WTG_0x4, funcMap, varMap, false);
					
					setECA(eca);
					
					setEndToken(stream.readInt());
					
					stream.endGroup();
				}
				
				private void write_0x7(Wc3BinStream stream) {
					
				}

				private void read(Wc3BinStream stream, EncodingFormat format, Map<String, Func> funcMap, Map<String, Var> varMap) throws Exception {
					switch (format.toEnum()) {
					case WTG_0x7:
						read_0x7(stream, funcMap, varMap);
						
						break;
					case WTG_0x4: {
						read_0x4(stream, funcMap, varMap);
						
						break;
					}
					}
				}
				
				private void write(Wc3BinStream stream, EncodingFormat format) {
					switch (format.toEnum()) {
					case AUTO:
					case WTG_0x7:
						write_0x7(stream);
						
						break;
					case WTG_0x4: {
						write_0x4(stream);
						
						break;
					}
					}
				}
				
				public CodeParam(Wc3BinStream stream, EncodingFormat format, Map<String, Func> funcMap, Map<String, Var> varMap) throws Exception {
					read(stream, format, funcMap, varMap);
				}
			}
			
			private List<Param> _params = new ArrayList<>();
			
			public List<Param> getParams() {
				return _params;
			}
			
			public void addParam(Param val) {
				_params.add(val);
			}

			private List<ECA> _ecas = new ArrayList<>();
			
			public List<ECA> getECAs() {
				return _ecas;
			}
			
			public void addECA(ECA val) {
				_ecas.add(val);
			}
			
			public ECA addECA() {
				ECA sub = new ECA();
				
				_ecas.add(sub);
				
				return sub;
			}
			
			private void read_0x4(Wc3BinStream stream, Map<String, Func> funcMap, Map<String, Var> varMap, boolean hasBranch) throws Exception {
				setType(ECAType.valueOf(stream.readInt("type")));
				
				if (hasBranch) {
					setBranch(stream.readInt("branch"));
				}
				
				String funcName = stream.readString("funcName");
				
				Func func = funcMap.get(funcName);
				
				setFunc(func);
				setEnabled(stream.readInt("enabled") != 0);
				System.out.println("func "+((func==null) ? "null" : func.getName()));
				if (func != null) {
					System.out.println("params " + func.getParams().size());
					for (int i = 0; i < func.getParams().size(); i++) {
						Param sub = null;

						/*Func subFunc = funcMap.get(func.getParam(i));

						switch (subFunc.getType()) {
						case BOOLCALL:
						case BOOLEXPR:
						case EVENTCALL: {
							sub = new BoolCodeParam(stream, EncodingFormat.WTG_0x4, funcMap, varMap);
							
							break;
						}
						case CODE: {
							sub = new CodeParam(stream, EncodingFormat.WTG_0x4, funcMap, varMap);
							
							break;
						}
						default: {
							sub = new NormalParam(stream, EncodingFormat.WTG_0x4, funcMap, varMap, subFunc);
						}
						}*/
						
						stream.beginGroup(String.format("param%d", i));

						sub = new NormalParam(stream, EncodingFormat.WTG_0x4, funcMap, varMap, false);
						
						stream.endGroup();
						
						addParam(sub);
					}
				} else {
					stream.printLog(System.err);
					
					throw new IOException(String.format("unknown func %s", funcName));
				}
			}
			
			private void write_0x4(Wc3BinStream stream) {
				// TODO Auto-generated method stub
				
			}
			
			private void read_0x7(Wc3BinStream stream, Map<String, Func> funcMap, Map<String, Var> varMap, boolean hasBranch) throws Exception {
				setType(ECAType.valueOf(stream.readInt("type")));
				
				if (hasBranch) {
					setBranch(stream.readInt("branch"));
				}
				
				String funcName = stream.readString("funcName");
				
				Func func = funcMap.get(funcName);
				
				setFunc(func);
				setEnabled(stream.readInt("enabled") != 0);
				stream.log("func "+((func==null) ? "null" : func.getName()));
				if (func != null) {
					stream.log("params " + func.getParams().size());
					for (int i = 0; i < func.getParams().size(); i++) {
						Param sub = null;

						/*Func subFunc = funcMap.get(func.getParam(i));

						switch (subFunc.getType()) {
						case BOOLCALL:
						case BOOLEXPR:
						case EVENTCALL: {
							sub = new BoolCodeParam(stream, EncodingFormat.WTG_0x4, funcMap, varMap);
							
							break;
						}
						case CODE: {
							sub = new CodeParam(stream, EncodingFormat.WTG_0x4, funcMap, varMap);
							
							break;
						}
						default: {
							sub = new NormalParam(stream, EncodingFormat.WTG_0x4, funcMap, varMap, subFunc);
						}
						}*/

						stream.beginGroup(String.format("param%d", i));
						
						sub = new NormalParam(stream, EncodingFormat.WTG_0x7, funcMap, varMap, false);
						
						stream.endGroup();
						
						addParam(sub);
					}
				} else {
					stream.printLog(System.err);
					
					throw new IOException(String.format("unknown func %s", funcName));
				}
				
				//stream.readInt("unknown"); //unknown
				
				int ECAsCount = stream.readInt("ECAsCount");
				
				for (int i = 0; i < ECAsCount; i++) {
					ECA sub = new ECA(stream, EncodingFormat.WTG_0x7, funcMap, varMap, true);
					
					addECA(sub);
				}
			}
			
			private void write_0x7(Wc3BinStream stream) {
				// TODO Auto-generated method stub
				
			}
			
			private void read(Wc3BinStream stream, EncodingFormat format, Map<String, Func> funcMap, Map<String, Var> varMap, boolean hasBranch) throws Exception {
				switch (format.toEnum()) {
				case WTG_0x7:
					read_0x7(stream, funcMap, varMap, hasBranch);
					
					break;
				case WTG_0x4: {
					read_0x4(stream, funcMap, varMap, hasBranch);
					
					break;
				}
				}
			}
			
			private void write(Wc3BinStream stream, EncodingFormat format) {
				switch (format.toEnum()) {
				case AUTO:
				case WTG_0x7:
					write_0x7(stream);
					
					break;
				case WTG_0x4: {
					write_0x4(stream);
					
					break;
				}
				}
			}
			
			public ECA(Wc3BinStream stream, EncodingFormat format, Map<String, Func> funcMap, Map<String, Var> varMap, boolean hasBranch) throws Exception {
				read(stream, format, funcMap, varMap, hasBranch);
			}
			
			public ECA() {
			}
		}
		
		private List<ECA> _ecas = new ArrayList<>();
		
		public List<ECA> getECAs() {
			return _ecas;
		}
		
		public void addECA(ECA eca) {
			_ecas.add(eca);
		}
		
		public ECA addECA() {
			ECA eca = new ECA();
			
			addECA(eca);
			
			return eca;
		}
		
		@Override
		public String toString() {
			return getName();
		}
		
		private void write_0x4(Wc3BinStream stream) {
			stream.writeString(getName());
			stream.writeString(getDescription());
			stream.writeInt(getType().getVal());
			stream.writeInt(isEnabled() ? 1 : 0);
			stream.writeInt(isCustomTxt() ? 1 : 0);
			stream.writeInt(isInitiallyOn() ? 1 : 0);
			stream.writeInt(0);  //unknown
			stream.writeInt(getCatIndex());
			
			stream.writeInt(_ecas.size());
			
			for (ECA eca : _ecas) {
				eca.write(stream, EncodingFormat.WTG_0x4);
			}
		}

		private void read_0x4(Wc3BinStream stream, Map<String, Func> funcMap, Map<String, Var> varMap) throws Exception {
			setName(stream.readString("name"));
			setDescription(stream.readString("description"));
			//setType(TrigType.valueOf(stream.readInt("type")));
			setEnabled(stream.readInt("enabled") != 0);
			setCustomTxt(stream.readInt("customTxt") != 0);
			setInitiallyOn(stream.readInt("initiallyOn") == 0);
			stream.readInt("unknown");
			setCatIndex(stream.readInt("catIndex"));

			int ECAsCount = stream.readInt("ECAsCount");
			
			for (int i = 0; i < ECAsCount; i++) {
				stream.beginGroup(String.format("ECA%d", i));
				
				ECA eca = new ECA(stream, EncodingFormat.WTG_0x4, funcMap, varMap, false);

				stream.endGroup();
				
				addECA(eca);
			}
		}
		
		private void write_0x7(Wc3BinStream stream) {
			stream.writeString(getName());
			stream.writeString(getDescription());
			stream.writeInt(getType().getVal());
			stream.writeInt(isEnabled() ? 1 : 0);
			stream.writeInt(isCustomTxt() ? 1 : 0);
			stream.writeInt(isInitiallyOn() ? 1 : 0);
			stream.writeInt(isRunOnMapInit() ? 1 : 0);
			stream.writeInt(getCatIndex());
			
			stream.writeInt(_ecas.size());
			
			for (ECA eca : _ecas) {
				eca.write(stream, EncodingFormat.WTG_0x7);
			}
		}

		private void read_0x7(Wc3BinStream stream, Map<String, Func> funcMap, Map<String, Var> varMap) throws Exception {
			setName(stream.readString("name"));
			setDescription(stream.readString("description"));
			setType(TrigType.valueOf(stream.readInt("type")));
			setEnabled(stream.readInt("enabled") != 0);
			setCustomTxt(stream.readInt("customTxt") != 0);
			setInitiallyOn(stream.readInt("initiallyOn") == 0);
			setRunOnMapInit(stream.readInt("runOnMapInit") != 0);
			setCatIndex(stream.readInt("catIndex"));
			
			int ECAsCount = stream.readInt("ECAsCount");
			
			for (int i = 0; i < ECAsCount; i++) {
				stream.beginGroup(String.format("ECA%d", i));
				
				ECA eca = new ECA(stream, EncodingFormat.WTG_0x7, funcMap, varMap, false);

				stream.endGroup();
				
				addECA(eca);
			}
		}
		
		public void read(Wc3BinStream stream, EncodingFormat format, Map<String, Func> funcMap, Map<String, Var> varMap) throws Exception {
			switch (format.toEnum()) {
			case WTG_0x7:
				read_0x7(stream, funcMap, varMap);
				
				break;
			case WTG_0x4: {
				read_0x4(stream, funcMap, varMap);
				
				break;
			}
			}
		}
		
		public void write(Wc3BinStream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case WTG_0x7:
				write_0x7(stream);
				
				break;
			case WTG_0x4: {
				write_0x4(stream);
				
				break;
			}
			}
		}
		
		public Trig(Wc3BinStream stream, EncodingFormat format, Map<String, Func> funcMap, Map<String, Var> varMap) throws Exception {
			read(stream, format, funcMap, varMap);
		}
		
		public Trig() {
		}
	}
	
	private List<Trig> _trigs = new ArrayList<>();
	
	public List<Trig> getTrigs() {
		return _trigs;
	}
	
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
			private static Map<Integer, Type> _map = new LinkedHashMap<>();
			
			public final static Type COMMENT = new Type(1);
			public final static Type NORMAL = new Type(0);
			
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
		
		@Override
		public String toString() {
			return getName();
		}
		
		private void read_0x4(Wc3BinStream stream) throws BinStream.StreamException {
			setIndex(stream.readInt("index"));
			setName(stream.readString("name"));
		}
		
		private void write_0x4(Wc3BinStream stream) {
			stream.writeInt(getIndex());
			stream.writeString(getName());
		}
		
		private void read_0x7(Wc3BinStream stream) throws BinStream.StreamException {
			setIndex(stream.readInt("index"));
			setName(stream.readString("name"));
			setType(Type.valueOf(stream.readInt("type")));
		}
		
		private void write_0x7(Wc3BinStream stream) {
			stream.writeInt(getIndex());
			stream.writeString(getName());
			stream.writeInt(getType().getVal());
		}
		
		private void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
			switch (format.toEnum()) {
			case WTG_0x7:
				read_0x7(stream);
				
				break;
			case WTG_0x4: {
				read_0x4(stream);
				
				break;
			}
			}
		}
		
		private void write(Wc3BinStream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case WTG_0x7:
				write_0x7(stream);
				
				break;
			case WTG_0x4: {
				write_0x4(stream);
				
				break;
			}
			}
		}

		public TrigCat(Wc3BinStream stream, EncodingFormat format) throws StreamException {
			read(stream, format);
		}

		public TrigCat() {
			
		}
	}

	private List<TrigCat> _trigCats = new ArrayList<>();
	
	public List<TrigCat> getTrigCats() {
		return _trigCats;
	}
	
	public void addTrigCat(TrigCat val) {
		_trigCats.add(val);
	}
	
	public TrigCat addTrigCat() {
		TrigCat trigCat = new TrigCat();

		addTrigCat(trigCat);
		
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

		@Override
		public String toString() {
			return getName();
		}
		
		private void read_0x4(Wc3BinStream stream) throws BinStream.StreamException {
			setName(stream.readString("name"));
			setType(stream.readString("type"));
			setUnknownNumE(stream.readInt("unknownNumE"));
			
			setArray(stream.readInt("array") != 0);
			setInited(stream.readInt("inited") != 0);
			setInitVal(stream.readString("initVal"));
		}

		private void write_0x4(Wc3BinStream stream) {
			stream.writeString(getName());
			stream.writeString(getType());
			stream.writeInt(getUnknownNumE());
			
			stream.writeInt(isArray() ? 1 : 0);
			stream.writeInt(isInited() ? 1 : 0);
			stream.writeString(getInitVal());
		}
		
		private void read_0x7(Wc3BinStream stream) throws BinStream.StreamException {
			setName(stream.readString("name"));
			setType(stream.readString("type"));
			setUnknownNumE(stream.readInt("unknownNumE"));
			
			setArray(stream.readInt("array") != 0);
			setArraySize(stream.readInt("arraySize"));
			setInited(stream.readInt("inited") != 0);
			setInitVal(stream.readString("initVal"));
		}

		private void write_0x7(Wc3BinStream stream) {
			stream.writeString(getName());
			stream.writeString(getType());
			stream.writeInt(getUnknownNumE());
			
			stream.writeInt(isArray() ? 1 : 0);
			stream.writeInt(getArraySize());
			stream.writeInt(isInited() ? 1 : 0);
			stream.writeString(getInitVal());
		}
		
		private void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {		
			switch (format.toEnum()) {
			case WTG_0x7:
				read_0x7(stream);
				
				break;
			case WTG_0x4: {
				read_0x4(stream);
				
				break;
			}
			}
		}

		private void write(Wc3BinStream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case WTG_0x7:
				write_0x7(stream);
				
				break;
			case WTG_0x4: {
				write_0x4(stream);
				
				break;
			}
			}
		}
		
		public Var(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
			read(stream, format);
		}
		
		public Var(String name) {
			_name = name;
		}
	}

	private Map<String, Var> _vars = new LinkedHashMap<>();
	
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
		for (Map.Entry<FieldId, ? extends TXT.Section.Field> fieldEntry : txt.getSection(TXTSectionId.valueOf("TriggerCategories")).getFields().entrySet()) {
			FieldId fieldId = fieldEntry.getKey();
			TXT.Section.Field field = fieldEntry.getValue();
			
			FuncCat cat = new FuncCat(fieldId);

			cat.setIconFile(new File(field.get(1).toString()));
			
			addFuncCat(cat);
		}
		
		Map<String, Integer> sections = new LinkedHashMap<>();
		
		sections.put("TriggerEvents", 1);
		sections.put("TriggerConditions", 1);
		sections.put("TriggerActions", 1);
		sections.put("TriggerCalls", 3);

		for (Map.Entry<String, Integer> sectionEntry : sections.entrySet()) {
			String sectionName = sectionEntry.getKey();
			int argsOffset = sectionEntry.getValue();
			
			TXT.Section section = txt.getSection(TXTSectionId.valueOf(sectionName));
			
			for (Map.Entry<FieldId, ? extends TXT.Section.Field> fieldEntry : section.getFields().entrySet()) {
				FieldId key = fieldEntry.getKey();
				TXT.Section.Field field = fieldEntry.getValue();
				
				String[] vals = field.getValLine(null).split(",");

				if (key.toString().startsWith("_")) {					
					if (key.toString().endsWith("_Category")) {
						Pattern pat = Pattern.compile("^_(.*)_Category$");
						
						Matcher mat = pat.matcher(key.toString());
						
						mat.find();

						if (mat.matches()) {							
							Func func = addFunc(mat.group(1));
						
							System.out.println("set " + func+"->" + vals[0]);
							func.setCat(vals[0]);
						}
					}
					
					if (key.toString().endsWith("_Defaults")) {
						
					}
				} else {
					Func func = addFunc(key.toString());
					
					for (int i = argsOffset; i < vals.length; i++) {
						if (vals[i].equals("nothing")) continue;
						
						func.addParam(vals[i]);
					}
				}
			}
		}
	}
	
	public void print(PrintStream outStream) {
		
	}
	
	public void print() {
		print(System.out);
	}
	
	private static class EncodingFormat extends Format<EncodingFormat.Enum> {
		enum Enum {
			AUTO,
			WTG_0x4,
			WTG_0x7
		}

		private static Map<Integer, EncodingFormat> _map = new LinkedHashMap<>();
		
		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat WTG_0x4 = new EncodingFormat(Enum.WTG_0x4, 0x4);
		public final static EncodingFormat WTG_0x7 = new EncodingFormat(Enum.WTG_0x7, 0x7);
		
		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}
		
		private EncodingFormat(Enum enumVal, int version) {
			super(enumVal, version);
			
			_map.put(version, this);
		}
	}

	private void read_0x4(Wc3BinStream stream) throws Exception {
		Id startToken = stream.readId("startToken");
		
		int version = stream.readInt("version");
		
		Wc3BinStream.checkFormatVer("guiTrigMaskFunc", EncodingFormat.WTG_0x4.getVersion(), version);
		
		int trigCatsCount = stream.readInt("trigCatsCount");
		
		for (int i = 0; i < trigCatsCount; i++) {
			TrigCat trigCat = new TrigCat(stream, EncodingFormat.WTG_0x4);
			
			stream.beginGroup(String.format("trigCat%d", i));
			
			addTrigCat(trigCat);
			
			stream.endGroup();
		}
		
		int unknownNumB = stream.readInt("unknownNumB");
		
		int varsCount = stream.readInt("varsCount");
		
		for (int i = 0; i < varsCount; i++) {
			stream.beginGroup(String.format("var%d", i));
			
			addVar(new Var(stream, EncodingFormat.WTG_0x4));
			
			stream.endGroup();
		}
		
		int trigsCount = stream.readInt("trigsCount");

		for (int i = 0; i < trigsCount; i++) {
			stream.beginGroup(String.format("trig%d", i));
			
			addTrig(new Trig(stream, EncodingFormat.WTG_0x4, getFuncs(), getVars()));
			
			stream.endGroup();
		}
	}
	
	private void write_0x4(Wc3BinStream stream) {
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
	
	private void read_0x7(Wc3BinStream stream) throws Exception {
		Id startToken = stream.readId("startToken");
		
		int version = stream.readInt("version");
		
		Wc3BinStream.checkFormatVer("guiTrigMaskFunc", EncodingFormat.WTG_0x7.getVersion(), version);
		
		int trigCatsCount = stream.readInt("trigCatsCount");
		
		for (int i = 0; i < trigCatsCount; i++) {
			stream.beginGroup(String.format("trigCat%d", i));
			
			TrigCat trigCat = new TrigCat(stream, EncodingFormat.WTG_0x7);
			
			stream.endGroup();
			
			addTrigCat(trigCat);
		}
		
		int unknownNumB = stream.readInt("unknownNumB");
		
		int varsCount = stream.readInt("varsCount");
		
		for (int i = 0; i < varsCount; i++) {
			stream.beginGroup(String.format("var%d", i));
			
			addVar(new Var(stream, EncodingFormat.WTG_0x7));
			
			stream.endGroup();
		}
		
		int trigsCount = stream.readInt("trigsCount");

		for (int i = 0; i < trigsCount; i++) {
			stream.beginGroup(String.format("trig%d", i));
			
			Trig trig = new Trig(stream, EncodingFormat.WTG_0x7, getFuncs(), getVars());
			
			stream.endGroup();
			
			addTrig(trig);
		}
	}
	
	private void write_0x7(Wc3BinStream stream) {
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
	
	private void read_auto(Wc3BinStream stream) throws Exception {
		Id startToken = stream.readId("startToken");
		int version = stream.readInt("version");
		
		stream.rewind();

		read(stream, EncodingFormat.valueOf(version));
	}
	
	private void read(Wc3BinStream stream, EncodingFormat format) throws Exception {
		System.out.println("version " +format.getVersion());
		
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
		
		stream.printLog();
	}
	
	private void write(Wc3BinStream stream, EncodingFormat format) {
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
	
	private void read(Wc3BinStream stream) throws Exception {
		read(stream, EncodingFormat.AUTO);
	}
	
	private void write(Wc3BinStream stream) {
		write(stream, EncodingFormat.AUTO);
	}
	
	private void read(File file, EncodingFormat format) throws Exception {
		read(new Wc3BinStream(file), format);
	}
	
	private void write(File file, EncodingFormat format) throws IOException {
		write(new Wc3BinStream(file), format);
	}
	
	private void read(InputStream inStream) throws Exception {
		read(new Wc3BinStream(inStream), EncodingFormat.AUTO);
	}
	
	private void read(File file) throws Exception {
		read(file, EncodingFormat.AUTO);
	}

	private void write(File file) throws IOException {
		write(new Wc3BinStream(file));
	}
	
	public WTG(InputStream inStream) throws Exception {
		TXT txt = new TXT(MpqPort.getDefaultImpl().getGameFiles(new File("UI\\TriggerData.txt")).getInputStream(new File("UI\\TriggerData.txt")));
		
		addTriggerData(txt);
		
		read(inStream);
	}
	
	public WTG(File file) throws Exception {
		read(file);
	}
	
	public WTG() {
	}

	public static WTG ofMapFile(File mapFile) throws Exception {
		if (!mapFile.exists()) throw new IOException(String.format("file %s does not exist", mapFile));
		
		MpqPort.Out port = new LadikMpqPort.Out();
		
		port.add(GAME_PATH);
		
		MpqPort.Out.Result portResult = port.commit(mapFile);

		if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract wtg file");

		InputStream inStream = portResult.getInputStream(GAME_PATH);
		
		return new WTG(inStream);
	}
}
