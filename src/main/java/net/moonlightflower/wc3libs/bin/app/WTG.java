package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.port.Context;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.port.NotFoundException;
import net.moonlightflower.wc3libs.txt.TXT;
import net.moonlightflower.wc3libs.txt.TXTSectionId;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
		
		public FuncCat(@Nonnull FieldId id) {
			_id = id;
		}
	}
	
	private final Map<FieldId, FuncCat> _funcCats = new LinkedHashMap<>();

	@Nonnull
	public Map<FieldId, FuncCat> getFuncCats() {
		return new LinkedHashMap<>(_funcCats);
	}
	
	public void addFuncCat(@Nonnull FuncCat val) {
		_funcCats.put(val.getId(), val);
	}

	@Nonnull
	public FuncCat addFuncCat(@Nonnull FieldId id) {
		if (_funcCats.containsKey(id)) return _funcCats.get(id);
		
		FuncCat cat = new FuncCat(id);
		
		addFuncCat(cat);
		
		return cat;
	}
	
	public static class Func {
		public enum SpecialType {
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
		
		private final List<String> _params = new ArrayList<>();

		@Nonnull
		public List<String> getParams() {
			return new ArrayList<>(_params);
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
	
	private final Map<String, Func> _funcs = new LinkedHashMap<>();

	@Nonnull
	private Map<String, Func> getFuncs() {
		return new LinkedHashMap<>(_funcs);
	}
	
	private Func getFunc(String name) {
		return getFuncs().get(name);
	}
	
	private void addFunc(@Nonnull Func val) {
		_funcs.put(val.getName(), val);
	}

	@Nonnull
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
			private final static Map<Integer, TrigType> _map = new LinkedHashMap<>();
			
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
				private final static Map<Integer, ECAType> _map = new LinkedHashMap<>();
				
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

				@Nullable
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
				protected String _val;
				
				public String getVal() {
					return _val;
				}
				
				public void setVal(String val) {
					_val = val;
				}
				
				private final List<Param> _params = new ArrayList<>();

				@Nonnull
				public List<Param> getParams() {
					return new ArrayList<>(_params);
				}
				
				public void addParam(@Nonnull Param val) {
					_params.add(val);
				}
				
				@Override
				public String toString() {
					return getVal();
				}

				//protected abstract void read(@Nonnull Reader reader) throws Exception;
				protected abstract void write(@Nonnull Writer writer) throws IOException;
			}
			
			public static class NormalParam extends Param {				
				public static class SpecType {
					private final static Map<Integer, SpecType> _map = new LinkedHashMap<>();
					
					public final static SpecType PRESET = new SpecType(0);
					public final static SpecType VARIABLE = new SpecType(1);
					public final static SpecType FUNCTION = new SpecType(2);
					public final static SpecType LITERAL = new SpecType(3);
					
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
				
				public void setSpecType(@Nonnull SpecType val) {
					_specType = val;
				}

				private int _beginFunc = 0;
				
				public int getBeginFunc() {
					return _beginFunc;
				}
				
				public void setBeginFunc(int val) {
					_beginFunc = val;
				}
				
				private void read_0x4(@Nonnull Reader reader, boolean hasBranch) throws Exception {
					Wc3BinInputStream stream = reader.getStream();

					_specType = SpecType.valueOf(stream.readInt32("specType"));

					_val = stream.readString("val");
					
					_beginFunc = stream.readInt32("beginFunc");
					
					if (_beginFunc > 0) {
						_specType = SpecType.valueOf(stream.readInt32("specType2"));
						
						_val = stream.readString("val2");
						
						_beginFunc = stream.readInt32("beginFunc2");
						
						if (_beginFunc > 0) {
							Func func = reader.getFuncMap().get(_val.toLowerCase());

							if (func != null) {
								int i = 0;
								int paramsCount = func.getParams().size();

								while (paramsCount > 0) {
									stream.beginGroup(String.format("param%d", i));

									Param sub = new NormalParam(reader, true);

									stream.endGroup();

									addParam(sub);

									paramsCount--;
									i++;
								}
							}
						}
					}

					stream.readInt32("endToken"); //always 0
				}
				
				private void write_0x4(@Nonnull Writer writer) throws IOException {
					Wc3BinOutputStream stream = writer.getStream();

					stream.writeInt32(_specType.getVal());

					stream.writeString(_val);

					stream.writeInt32(_beginFunc);

					if (_beginFunc > 0) {
						stream.writeInt32(_specType.getVal());

						stream.writeString(_val);

						stream.writeInt32(_beginFunc);

						if (_beginFunc > 0) {
							for (Param param : getParams()) {
								param.write(writer);
							}
						}
					}
				}
				
				private void read_0x7(@Nonnull Reader reader, boolean hasBranch) throws Exception {
					Wc3BinInputStream stream = reader.getStream();

					_specType = SpecType.valueOf(stream.readInt32("specType"));
					_val = stream.readString("val");
					
					_beginFunc = stream.readInt32("beginFunc");
					
					if (_beginFunc == 1) {
						stream.beginGroup("beginFunc2");
						
						setSpecType(SpecType.valueOf(stream.readInt32("specType2"))); //beginFunc_type = 3
						
						long pos = stream.getPos();
						
						String funcName = stream.readString("funcName2");
						
						setVal(funcName); //beginFunc_val = val
						
						Func func = reader.getFuncMap().get(funcName.toLowerCase());
						
						setBeginFunc(stream.readInt32()); //beginFunc_beginFunc = 1

						if (func != null) {
							for (int i = 0; i < func.getParams().size(); i++) {
								Param sub = null;
								
								String subFuncName = func.getParam(i);
								
								Func subFunc = reader.getFuncMap().get(funcName.toLowerCase());

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
										sub = new NormalParam(reader, false);
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
							
							throw new IOException(String.format("unknown func %s at %s (%s)", funcName, pos, String.format("%x", pos)));
						}
						
						stream.readInt32("endToken"); //beginFunc_endToken = 0
						
						stream.endGroup();
					}
					
					stream.readInt32("endToken"); //endToken = 0

					if ((_specType == SpecType.VARIABLE) && (reader.getVarMap().containsKey(_val) && reader.getVarMap().get(_val).isArray())) {
						stream.beginGroup("arrayIndex");
						
						Param sub = new NormalParam(reader, false);
						
						stream.endGroup();
						
						addParam(sub);
					}
				}
				
				private void write_0x7(@Nonnull Writer writer) throws IOException {
					Wc3BinOutputStream stream = writer.getStream();

					stream.writeInt32(_specType.getVal());
					stream.writeString(_val);

					stream.writeInt32(_beginFunc);

					if (_beginFunc > 0) {
						stream.writeInt32(getSpecType().getVal());

						stream.writeString(getVal());

						stream.writeInt32(getBeginFunc());

						for (Param param : getParams()) {
							param.write(writer);
						}

						stream.writeInt32(0); //endToken
					}

					stream.writeInt32(0); //endToken

					if ((_specType == SpecType.VARIABLE) && (writer.getVarMap().containsKey(_val) && writer.getVarMap().get(_val).isArray())) {
						Param param = getParams().get(getParams().size() - 1);

						if (param instanceof NormalParam) {
							param.write(writer);
						} else {
							throw new IllegalArgumentException("param " + param + " is not an array index");
						}
					}
				}
				
				private void read(@Nonnull Reader reader, boolean hasBranch) throws Exception {
					switch (reader.getFormat().toEnum()) {
					case WTG_0x7:
						read_0x7(reader, hasBranch);
						
						break;
					case WTG_0x4: {
						read_0x4(reader, hasBranch);
						
						break;
					}
					}
				}

				@Override
				protected void write(@Nonnull Writer writer) throws IOException {
					switch (writer.getFormat().toEnum()) {
					case AUTO:
					case WTG_0x7:
						write_0x7(writer);
						
						break;
					case WTG_0x4: {
						write_0x4(writer);
						
						break;
					}
					}
				}
				
				public NormalParam(@Nonnull Reader reader, boolean hasBranch) throws Exception {
					read(reader, hasBranch);
				}
			}
			
			public abstract static class ECAParam extends Param {
				protected ECA _eca;
				
				public ECA getEca() {
					return _eca;
				}
				
				public void setECA(ECA val) {
					_eca = val;
				}
				
				protected int _endToken = 0;
				
				public int getEndToken() {
					return _endToken;
				}
				
				public void setEndToken(int val) {
					_endToken = val;
				}
			}
			
			public static class BoolCodeParam extends ECAParam {
				private int _boolexpr_unknownA;
				
				public int getBoolexpr_UnknownA() {
					return _boolexpr_unknownA;
				}
				
				public void setBoolexpr_UnknownA(int val) {
					_boolexpr_unknownA = val;
				}

				private int _boolexpr_unknownB;
				
				public int getBoolexpr_UnknownB() {
					return _boolexpr_unknownB;
				}
				
				public void setBoolexpr_UnknownB(int val) {
					_boolexpr_unknownB = val;
				}

				private char _boolexpr_unknownC;
				
				public int getBoolexpr_UnknownC() {
					return _boolexpr_unknownC;
				}
				
				public void setBoolexpr_UnknownC(char val) {
					_boolexpr_unknownC = val;
				}
				
				private void read_0x4(@Nonnull Reader reader) throws Exception {
					Wc3BinInputStream stream = reader.getStream();

					stream.beginGroup("boolexpr");
					
					_boolexpr_unknownA = stream.readInt32("unknownA");
					_boolexpr_unknownB = stream.readInt32("unknownB");
					_boolexpr_unknownC = stream.readChar("unknownC");
					
					_eca = new ECA(reader, false);
					
					_endToken = stream.readInt32("endToken");
					
					stream.endGroup();
				}
				
				private void write_0x4(@Nonnull Writer writer) throws IOException {
					Wc3BinOutputStream stream = writer.getStream();

					stream.writeInt32(_boolexpr_unknownA);
					stream.writeInt32(_boolexpr_unknownB);
					stream.writeChar(_boolexpr_unknownC);

					_eca.write(writer, false);

					stream.writeInt32(_endToken);
				}
				
				private void read_0x7(@Nonnull Reader reader) throws Exception {
					Wc3BinInputStream stream = reader.getStream();

					stream.beginGroup("boolexpr");
					
					_boolexpr_unknownA = stream.readInt32("unknownA");
					_boolexpr_unknownB = stream.readInt32("unknownB");
					_boolexpr_unknownC = stream.readChar("unknownC");
					
					_eca = new ECA(reader, false);
					
					_endToken = stream.readInt32("endToken");
					
					stream.endGroup();
				}
				
				private void write_0x7(@Nonnull Writer writer) throws IOException {
					Wc3BinOutputStream stream = writer.getStream();

					stream.writeInt32(_boolexpr_unknownA);
					stream.writeInt32(_boolexpr_unknownB);
					stream.writeChar(_boolexpr_unknownC);

					_eca.write(writer, false);

					stream.writeInt32(_endToken);
				}
				
				private void read(@Nonnull Reader reader) throws Exception {
					switch (reader.getFormat().toEnum()) {
					case WTG_0x7:
						read_0x7(reader);
						
						break;
					case WTG_0x4: {
						read_0x4(reader);
						
						break;
					}
					}
				}
				
				@Override
				protected void write(@Nonnull Writer writer) throws IOException {
					switch (writer.getFormat().toEnum()) {
					case AUTO:
					case WTG_0x7:
						write_0x7(writer);
						
						break;
					case WTG_0x4: {
						write_0x4(writer);
						
						break;
					}
					}
				}
				
				public BoolCodeParam(@Nonnull Reader reader) throws Exception {
					read(reader);
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

				protected int _dummyDoNothing;

				private void read_0x4(@Nonnull Reader reader) throws Exception {
					Wc3BinInputStream stream = reader.getStream();

					stream.beginGroup("code");
					
					setCode_unknown1(stream.readInt32("unknown1"));
					
					_dummyDoNothing = stream.readInt32("dummyDoNothing");
					
					if (_dummyDoNothing == 0x100) {
						setCode_unknown2(stream.readChar("unknown2"));
					} else {
						for (int i = 3; i < 13; i++) {
							setCode_unknown(i, stream.readChar(String.format("unknown%d", i)));
						}
					}
					
					_eca = new ECA(reader, false);
					
					_endToken = stream.readInt32();

					stream.endGroup();
				}
				
				private void write_0x4(@Nonnull Writer writer) throws IOException {
					Wc3BinOutputStream stream = writer.getStream();

					stream.writeInt32(_code_unknown1);

					stream.writeInt32(_dummyDoNothing);

					if (_dummyDoNothing == 0x100) {
						stream.writeChar(_code_unknown2);
					} else {
						for (int i = 3; i < 13; i++) {
							stream.writeChar(getCode_unknown(i));
						}
					}

					_eca.write(writer, false);

					stream.writeInt32(_endToken);
				}
				
				private void read_0x7(@Nonnull Reader reader) throws Exception {
					Wc3BinInputStream stream = reader.getStream();

					stream.beginGroup("code");
					
					setCode_unknown1(stream.readInt32("unknown1"));
					
					_dummyDoNothing = stream.readInt32("dummyDoNothing");
					
					if (_dummyDoNothing == 0x100) {
						setCode_unknown2(stream.readChar("unknown2"));
					} else {
						for (int i = 3; i < 13; i++) {
							setCode_unknown(i, stream.readChar(String.format("unknown%d", i)));
						}
					}
					
					_eca = new ECA(reader, false);
					
					_endToken = stream.readInt32();
					
					stream.endGroup();
				}
				
				private void write_0x7(@Nonnull Writer writer) throws IOException {
					Wc3BinOutputStream stream = writer.getStream();

					stream.writeInt32(_code_unknown1);

					stream.writeInt32(_dummyDoNothing);

					if (_dummyDoNothing == 0x100) {
						stream.writeChar(_code_unknown2);
					} else {
						for (int i = 3; i < 13; i++) {
							stream.writeChar(getCode_unknown(i));
						}
					}

					_eca.write(writer, false);

					stream.writeInt32(_endToken);
				}

				private void read(@Nonnull Reader reader) throws Exception {
					switch (reader.getFormat().toEnum()) {
					case WTG_0x7:
						read_0x7(reader);
						
						break;
					case WTG_0x4: {
						read_0x4(reader);
						
						break;
					}
					}
				}
				
				@Override
				protected void write(@Nonnull Writer writer) throws IOException {
					switch (writer.getFormat().toEnum()) {
					case AUTO:
					case WTG_0x7:
						write_0x7(writer);
						
						break;
					case WTG_0x4: {
						write_0x4(writer);
						
						break;
					}
					}
				}
				
				public CodeParam(@Nonnull Reader reader) throws Exception {
					read(reader);
				}
			}
			
			private final List<Param> _params = new ArrayList<>();

			@Nonnull
			public List<Param> getParams() {
				return new ArrayList<>(_params);
			}
			
			public void addParam(@Nonnull Param val) {
				_params.add(val);
			}

			private final List<ECA> _ecas = new ArrayList<>();

			@Nonnull
			public List<ECA> getECAs() {
				return new ArrayList<>(_ecas);
			}
			
			public void addECA(@Nonnull ECA val) {
				_ecas.add(val);
			}

			@Nonnull
			public ECA addECA() {
				ECA sub = new ECA();
				
				_ecas.add(sub);
				
				return sub;
			}
			
			private void read_0x4(@Nonnull Reader reader, boolean hasBranch) throws Exception {
				Wc3BinInputStream stream = reader.getStream();

				_type = ECAType.valueOf(stream.readInt32("type"));
				
				if (hasBranch) {
					_branch = stream.readInt32("branch");
				}

				String funcName = stream.readString("func");

				_func = reader.getFuncMap().get(funcName);
				_enabled = (stream.readInt32("enabled") != 0);

				if (_func != null) {
					for (int i = 0; i < _func.getParams().size(); i++) {
						Param sub = null;

						/*FuncImpl subFunc = funcMap.get(func.getParam(i));

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

						sub = new NormalParam(reader, false);
						
						stream.endGroup();
						
						addParam(sub);
					}
				} else {
					throw new IOException(String.format("unknown func %s", funcName));
				}
			}
			
			private void write_0x4(@Nonnull Writer writer, boolean hasBranch) throws IOException {
				Wc3BinOutputStream stream = writer.getStream();

				stream.writeInt32(_type.getVal());

				if (hasBranch) {
					stream.writeInt32(_branch);
				}

				stream.writeString(_func.getName());
				stream.writeInt32(_enabled ? 1 : 0);

				for (Param param : getParams()) {
					param.write(writer);
				}
			}
			
			private void read_0x7(@Nonnull Reader reader, boolean hasBranch) throws Exception {
				Wc3BinInputStream stream = reader.getStream();

				_type = ECAType.valueOf(stream.readInt32("type"));
				
				if (hasBranch) {
					setBranch(stream.readInt32("branch"));
				}
				
				String funcName = stream.readString("funcName");
				
				_func = reader.getFuncMap().get(funcName);
				_enabled = (stream.readInt32("enabled") != 0);

				if (_func == null) throw new IOException(String.format("unknown func %s", funcName));

				for (int i = 0; i < _func.getParams().size(); i++) {
					Param sub = null;

					/*FuncImpl subFunc = funcMap.get(func.getParam(i).toLowerCase());

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

					sub = new NormalParam(reader, false);

					stream.endGroup();

					addParam(sub);
				}
				
				//stream.readInt32("unknown"); //unknown
				
				int ecasCount = stream.readInt32("ECAsCount");
				
				while (ecasCount > 0) {
					ECA sub = new ECA(reader, true);
					
					addECA(sub);

					ecasCount--;
				}
			}
			
			private void write_0x7(@Nonnull Writer writer, boolean hasBranch) throws IOException {
				Wc3BinOutputStream stream = writer.getStream();

				stream.writeInt32(_type.getVal());

				if (hasBranch) {
					stream.writeInt32(getBranch());
				}

				stream.writeString(getFunc().getName());

				stream.writeUInt32(isEnabled() ? 1 : 0);

				if (getFunc() != null) {
					for (Param param : getParams()) {
						param.write(writer);
					}
				}

				stream.writeInt32(getECAs().size());

				for (ECA eca : getECAs()) {
					eca.write(writer, true);
				}
			}
			
			private void read(@Nonnull Reader reader, boolean hasBranch) throws Exception {
				switch (reader.getFormat().toEnum()) {
				case WTG_0x7:
					read_0x7(reader, hasBranch);
					
					break;
				case WTG_0x4: {
					read_0x4(reader, hasBranch);
					
					break;
				}
				}
			}
			
			private void write(@Nonnull Writer writer, boolean hasBranch) throws IOException {
				switch (writer.getFormat().toEnum()) {
				case AUTO:
				case WTG_0x7:
					write_0x7(writer, hasBranch);
					
					break;
				case WTG_0x4: {
					write_0x4(writer, hasBranch);
					
					break;
				}
				}
			}
			
			public ECA(@Nonnull Reader reader, boolean hasBranch) throws Exception {
				read(reader, hasBranch);
			}

			public ECA() {
			}
		}
		
		private final List<ECA> _ecas = new ArrayList<>();

		@Nonnull
		public List<ECA> getECAs() {
			return new ArrayList<>(_ecas);
		}
		
		public void addECA(@Nonnull ECA eca) {
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
		
		private void write_0x4(@Nonnull Writer writer) throws IOException {
			Wc3BinOutputStream stream = writer.getStream();

			stream.writeString(getName());
			stream.writeString(getDescription());
			stream.writeInt32(getType().getVal());
			stream.writeInt32(isEnabled() ? 1 : 0);
			stream.writeInt32(isCustomTxt() ? 1 : 0);
			stream.writeInt32(isInitiallyOn() ? 1 : 0);
			stream.writeInt32(0);  //unknown
			stream.writeInt32(getCatIndex());
			
			stream.writeInt32(_ecas.size());
			
			for (ECA eca : _ecas) {
				eca.write(writer, false);
			}
		}

		private void read_0x4(@Nonnull Reader reader) throws Exception {
			Wc3BinInputStream stream = reader.getStream();

			setName(stream.readString("name"));
			setDescription(stream.readString("description"));
			//setType(TrigType.valueOf(stream.readInt32("type")));
			setEnabled(stream.readInt32("enabled") != 0);
			setCustomTxt(stream.readInt32("customTxt") != 0);
			setInitiallyOn(stream.readInt32("initiallyOn") == 0);
			stream.readInt32("unknown");
			setCatIndex(stream.readInt32("catIndex"));

			int ECAsCount = stream.readInt32("ECAsCount");
			
			for (int i = 0; i < ECAsCount; i++) {
				stream.beginGroup(String.format("ECA%d", i));
				
				ECA eca = new ECA(reader, false);

				stream.endGroup();
				
				addECA(eca);
			}
		}
		
		private void write_0x7(@Nonnull Writer writer) throws IOException {
			Wc3BinOutputStream stream = writer.getStream();

			stream.writeString(getName());
			stream.writeString(getDescription());
			stream.writeInt32(getType().getVal());
			stream.writeInt32(isEnabled() ? 1 : 0);
			stream.writeInt32(isCustomTxt() ? 1 : 0);
			stream.writeInt32(isInitiallyOn() ? 0 : 1);
			stream.writeInt32(isRunOnMapInit() ? 1 : 0);
			stream.writeInt32(getCatIndex());
			
			stream.writeInt32(_ecas.size());

			for (ECA eca : _ecas) {
				eca.write(writer, false);
			}
		}

		private void read_0x7(@Nonnull Reader reader) throws Exception {
			Wc3BinInputStream stream = reader.getStream();

			setName(stream.readString("name"));
			setDescription(stream.readString("description"));
			setType(TrigType.valueOf(stream.readInt32("type")));
			setEnabled(stream.readInt32("enabled") != 0);
			setCustomTxt(stream.readInt32("customTxt") != 0);
			setInitiallyOn(stream.readInt32("initiallyOn") == 0);
			setRunOnMapInit(stream.readInt32("runOnMapInit") != 0);
			setCatIndex(stream.readInt32("catIndex"));
			
			int ECAsCount = stream.readInt32("ECAsCount");
			
			for (int i = 0; i < ECAsCount; i++) {
				stream.beginGroup(String.format("ECA%d", i));
				
				ECA eca = new ECA(reader, false);

				stream.endGroup();
				
				addECA(eca);
			}
		}
		
		public void read(@Nonnull Reader reader) throws Exception {
			switch (reader.getFormat().toEnum()) {
			case WTG_0x7:
				read_0x7(reader);
				
				break;
			case WTG_0x4: {
				read_0x4(reader);
				
				break;
			}
			}
		}
		
		public void write(@Nonnull Writer writer) throws IOException {
			switch (writer.getFormat().toEnum()) {
			case AUTO:
			case WTG_0x7:
				write_0x7(writer);
				
				break;
			case WTG_0x4: {
				write_0x4(writer);
				
				break;
			}
			}
		}
		
		public Trig(@Nonnull Reader reader) throws Exception {
			read(reader);
		}
		
		public Trig() {
		}
	}
	
	private final List<Trig> _trigs = new ArrayList<>();

	@Nonnull
	public List<Trig> getTrigs() {
		return new ArrayList<>(_trigs);
	}
	
	private void addTrig(@Nonnull Trig val) {
		_trigs.add(val);
	}

	@Nonnull
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
		
		public void setName(@Nullable String val) {
			_name = val;
		}
		
		public static class Type {
			private final static Map<Integer, Type> _map = new LinkedHashMap<>();
			
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

			@Nullable
			public static Type valueOf(int val) {
				return _map.get(val);
			}
		}
		
		private Type _type = Type.NORMAL;

		@Nonnull
		public Type getType() {
			return _type;
		}
		
		public void setType(@Nonnull Type val) {
			_type = val;
		}
		
		@Override
		public String toString() {
			return getName();
		}
		
		private void read_0x4(@Nonnull Reader reader) throws BinInputStream.StreamException {
			Wc3BinInputStream stream = reader.getStream();

			setIndex(stream.readInt32("index"));
			setName(stream.readString("name"));
		}
		
		private void write_0x4(@Nonnull Writer writer) {
			Wc3BinOutputStream stream = writer.getStream();

			stream.writeInt32(getIndex());
			stream.writeString(getName());
		}
		
		private void read_0x7(@Nonnull Reader reader) throws BinInputStream.StreamException {
			Wc3BinInputStream stream = reader.getStream();

			setIndex(stream.readInt32("index"));
			setName(stream.readString("name"));
			setType(Type.valueOf(stream.readInt32("type")));
		}
		
		private void write_0x7(@Nonnull Writer writer) {
			Wc3BinOutputStream stream = writer.getStream();

			stream.writeInt32(getIndex());
			stream.writeString(getName());
			stream.writeInt32(getType().getVal());
		}
		
		private void read(@Nonnull Reader reader) throws BinInputStream.StreamException {
			switch (reader.getFormat().toEnum()) {
			case WTG_0x7:
				read_0x7(reader);
				
				break;
			case WTG_0x4: {
				read_0x4(reader);
				
				break;
			}
			}
		}
		
		private void write(@Nonnull Writer writer) {
			switch (writer.getFormat().toEnum()) {
			case AUTO:
			case WTG_0x7:
				write_0x7(writer);
				
				break;
			case WTG_0x4: {
				write_0x4(writer);
				
				break;
			}
			}
		}

		public TrigCat(@Nonnull Reader reader) throws BinStream.StreamException {
			read(reader);
		}

		public TrigCat() {
			
		}
	}

	private final List<TrigCat> _trigCats = new ArrayList<>();

	@Nonnull
	public List<TrigCat> getTrigCats() {
		return new ArrayList<>(_trigCats);
	}
	
	public void addTrigCat(@Nonnull TrigCat val) {
		_trigCats.add(val);
	}

	@Nonnull
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
		
		public void setName(@Nullable String val) {
			_name = val;
		}
		
		private String _type;
		
		public String getType() {
			return _type;
		}
		
		public void setType(@Nullable String val) {
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
		
		public void setInitVal(@Nullable String val) {
			_initVal = val;
		}

		@Override
		public String toString() {
			return getName();
		}
		
		private void read_0x4(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			setName(stream.readString("name"));
			setType(stream.readString("type"));
			setUnknownNumE(stream.readInt32("unknownNumE"));
			
			setArray(stream.readInt32("array") != 0);
			setInited(stream.readInt32("inited") != 0);
			setInitVal(stream.readString("initVal"));
		}

		private void write_0x4(@Nonnull Wc3BinOutputStream stream) {
			stream.writeString(_name);
			stream.writeString(_type);
			stream.writeInt32(_unknownNumE);
			
			stream.writeInt32(_arrayFlag ? 1 : 0);
			stream.writeInt32(_initedFlag ? 1 : 0);
			stream.writeString(_initVal);
		}
		
		private void read_0x7(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			_name = stream.readString("name");
			_type = stream.readString("type");
			_unknownNumE = stream.readInt32("unknownNumE");
			
			_arrayFlag = (stream.readInt32("array") != 0);
			_arraySize = stream.readInt32("arraySize");
			_initedFlag = (stream.readInt32("inited") != 0);
			_initVal = stream.readString("initVal");
		}

		private void write_0x7(@Nonnull Wc3BinOutputStream stream) {
			stream.writeString(_name);
			stream.writeString(_type);
			stream.writeInt32(_unknownNumE);

			stream.writeInt32(_arrayFlag ? 1 : 0);
			stream.writeInt32(_arraySize);
			stream.writeInt32(_initedFlag ? 1 : 0);
			stream.writeString(_initVal);
		}
		
		private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
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

		private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
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
		
		public Var(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			read(stream, format);
		}
		
		public Var(@Nullable String name) {
			_name = name;
		}
	}

	private final Map<String, Var> _vars = new LinkedHashMap<>();
	
	public Map<String, Var> getVars() {
		return new LinkedHashMap<>(_vars);
	}

	@Nullable
	public Var getVar(@Nonnull String name) {
		return _vars.get(name);
	}
	
	private void addVar(@Nonnull Var val) {
		_vars.put(val.getName(), val);
	}

	@Nonnull
	public Var addVar(@Nullable String name) {
		Var var = new Var(name);
		
		addVar(var);
		
		return var;
	}

	public void addTriggerData(@Nonnull TXT txt) {
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
						
							//System.out.println("set " + func+"->" + vals[0]);
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

	@Nonnull
	protected MpqPort getMpqPort() {
		return Context.getService(MpqPort.class);
	}

	public void addGameTriggerData() throws NotFoundException {
		try {
			InputStream inputStream = getMpqPort().getGameFiles(new File("UI\\TriggerData.txt")).getInputStream(new File("UI\\TriggerData.txt"));

			if (inputStream == null) throw new NotFoundException();

			TXT txt = new TXT(inputStream);

			addTriggerData(txt);
		} catch (IOException e) {
			throw new NotFoundException(e);
		}
	}
	
	public void print(@Nonnull PrintStream outStream) {
		
	}
	
	public void print() {
		print(System.out);
	}
	
	public static class EncodingFormat extends Format<EncodingFormat.Enum> {
		public enum Enum {
			AUTO,
			WTG_0x4,
			WTG_0x7
		}
		
		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat WTG_0x4 = new EncodingFormat(Enum.WTG_0x4, 0x4);
		public final static EncodingFormat WTG_0x7 = new EncodingFormat(Enum.WTG_0x7, 0x7);

		@Nullable
		public static EncodingFormat valueOf(@Nonnull Integer version) {
			return get(EncodingFormat.class, version);
		}
		
		private EncodingFormat(@Nonnull Enum enumVal, int version) {
			super(enumVal, version);
		}
	}

	private void read_0x4(@Nonnull Reader reader) throws Exception {
		Wc3BinInputStream stream = reader.getStream();

		Id startToken = stream.readId("startToken");
		
		int version = stream.readInt32("version");
		
		stream.checkFormatVersion(EncodingFormat.WTG_0x4.getVersion(), version);
		
		int trigCatsCount = stream.readInt32("trigCatsCount");
		
		for (int i = 0; i < trigCatsCount; i++) {
			TrigCat trigCat = new TrigCat(reader);
			
			stream.beginGroup(String.format("trigCat%d", i));
			
			addTrigCat(trigCat);
			
			stream.endGroup();
		}
		
		int unknownNumB = stream.readInt32("unknownNumB");
		
		int varsCount = stream.readInt32("varsCount");
		
		for (int i = 0; i < varsCount; i++) {
			stream.beginGroup(String.format("var%d", i));
			
			addVar(new Var(stream, EncodingFormat.WTG_0x4));
			
			stream.endGroup();
		}

		reader.getFuncMap().putAll(getFuncs());
		reader.getVarMap().putAll(getVars());
		
		int trigsCount = stream.readInt32("trigsCount");

		for (int i = 0; i < trigsCount; i++) {
			stream.beginGroup(String.format("trig%d", i));
			
			addTrig(new Trig(reader));
			
			stream.endGroup();
		}
	}
	
	private void write_0x4(@Nonnull Writer writer) throws IOException {
		Wc3BinOutputStream stream = writer.getStream();

		stream.writeId(Id.valueOf("WTG!"));
		
		stream.writeInt32(EncodingFormat.WTG_0x4.getVersion());
		
		stream.writeInt32(_trigCats.size());
		
		for (TrigCat trigCat : _trigCats) {
			trigCat.write(writer);
		}
		
		stream.writeInt32(_unknownNumB);
		
		stream.writeInt32(_vars.size());
		
		for (Var var : _vars.values()) {
			var.write(stream, EncodingFormat.WTG_0x4);
		}
		
		stream.writeInt32(_trigs.size());
		
		for (Trig trig : _trigs) {
			trig.write(writer);
		}
	}
	
	private void read_0x7(@Nonnull Reader reader) throws Exception {
		Wc3BinInputStream stream = reader.getStream();

		Id startToken = stream.readId("startToken");
		
		int version = stream.readInt32("version");
		
		stream.checkFormatVersion(EncodingFormat.WTG_0x7.getVersion(), version);
		
		int trigCatsCount = stream.readInt32("trigCatsCount");
		
		for (int i = 0; i < trigCatsCount; i++) {
			stream.beginGroup(String.format("trigCat%d", i));
			
			TrigCat trigCat = new TrigCat(reader);
			
			stream.endGroup();
			
			addTrigCat(trigCat);
		}
		
		_unknownNumB = stream.readInt32("unknownNumB");
		
		int varsCount = stream.readInt32("varsCount");
		
		for (int i = 0; i < varsCount; i++) {
			stream.beginGroup(String.format("var%d", i));
			
			addVar(new Var(stream, EncodingFormat.WTG_0x7));
			
			stream.endGroup();
		}

		reader.getFuncMap().putAll(getFuncs());
		reader.getVarMap().putAll(getVars());

		int trigsCount = stream.readInt32("trigsCount");

		for (int i = 0; i < trigsCount; i++) {
			stream.beginGroup(String.format("trig%d", i));
			
			Trig trig = new Trig(reader);
			
			stream.endGroup();
			
			addTrig(trig);
		}
	}
	
	private void write_0x7(@Nonnull Writer writer) throws IOException {
		Wc3BinOutputStream stream = writer.getStream();

		stream.writeId(Id.valueOf("WTG!"));
		
		stream.writeInt32(EncodingFormat.WTG_0x7.getVersion());
		
		stream.writeInt32(_trigCats.size());
		
		for (TrigCat trigCat : _trigCats) {
			trigCat.write(writer);
		}
		
		stream.writeInt32(_unknownNumB);
		
		stream.writeInt32(_vars.size());
		
		for (Var var : _vars.values()) {
			var.write(stream, EncodingFormat.WTG_0x7);
		}
		
		stream.writeInt32(_trigs.size());

		for (Trig trig : _trigs) {
			trig.write(writer);
		}
	}
	
	private void read_auto(@Nonnull Reader reader) throws Exception {
		Wc3BinInputStream stream = reader.getStream();

		Id startToken = stream.readId("startToken");
		int version = stream.readInt32("version");
		
		stream.rewind();

		reader.setFormat(stream.getFormat(EncodingFormat.class, version));

		read(reader);
	}

	private void read(@Nonnull Reader reader) throws Exception {
		switch (reader.getFormat().toEnum()) {
		case WTG_0x4: {
			read_0x4(reader);
			
			break;
		}
		case WTG_0x7: {
			read_0x7(reader);
			
			break;
		}
		case AUTO: {
			read_auto(reader);
			
			break;
		}
		}
	}

	public static class Reader {
		private final Wc3BinInputStream _stream;

		@Nonnull
		public Wc3BinInputStream getStream() {
			return _stream;
		}

		private EncodingFormat _format = EncodingFormat.AUTO;

		public EncodingFormat getFormat() {
			return _format;
		}

		public void setFormat(@Nonnull EncodingFormat val) {
			_format = val;
		}

		private final Map<String, Func> _funcMap = new LinkedHashMap<>();

		@Nonnull
		public Map<String, Func> getFuncMap() {
			return _funcMap;
		}

		private final Map<String, Var> _varMap = new LinkedHashMap<>();

		@Nonnull
		public Map<String, Var> getVarMap() {
			return _varMap;
		}

		public Reader(@Nonnull Wc3BinInputStream stream) {
			_stream = stream;
		}
	}

	public static class Writer {
		private final Wc3BinOutputStream _stream;

		@Nonnull
		public Wc3BinOutputStream getStream() {
			return _stream;
		}

		private EncodingFormat _format = EncodingFormat.AUTO;

		public EncodingFormat getFormat() {
			return _format;
		}

		public void setFormat(@Nonnull EncodingFormat val) {
			_format = val;
		}

		private final Map<String, Func> _funcMap = new LinkedHashMap<>();

		@Nonnull
		public Map<String, Func> getFuncMap() {
			return _funcMap;
		}

		private final Map<String, Var> _varMap = new LinkedHashMap<>();

		@Nonnull
		public Map<String, Var> getVarMap() {
			return _varMap;
		}

		public Writer(@Nonnull Wc3BinOutputStream stream) {
			_stream = stream;
		}
	}

	private void write(@Nonnull Writer writer) throws IOException {
		switch (writer.getFormat().toEnum()) {
		case WTG_0x4: {
			write_0x4(writer);
			
			break;
		}
		case WTG_0x7: {
			write_0x7(writer);
			
			break;
		}
		case AUTO: {
			write_0x7(writer);
			
			break;
		}
		}
	}
	
	public void write(@Nonnull Wc3BinOutputStream stream) throws IOException {
		write(new Writer(stream));
	}

	public void read(@Nonnull Wc3BinInputStream stream) throws Exception {
		read(new Reader(stream));
	}

	private void write(@Nonnull File file) throws IOException {
		Wc3BinOutputStream outStream = new Wc3BinOutputStream(file);

		write(outStream);

		outStream.close();
	}

	public WTG(@Nonnull Wc3BinInputStream stream) throws Exception {
		read(stream);
	}

	public WTG(@Nonnull File file) throws Exception {
		Wc3BinInputStream stream = new Wc3BinInputStream(file);

		read(stream);

		stream.close();
	}
	
	public WTG() {
	}

	@Nullable
	public static WTG ofMapFile(@Nonnull File mapFile) throws Exception {
		if (!mapFile.exists()) throw new IOException(String.format("file %s does not exist", mapFile));
		
		MpqPort.Out port = new JMpqPort.Out();
		
		port.add(GAME_PATH);
		
		MpqPort.Out.Result portResult = port.commit(mapFile);

		if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract wtg file");

		InputStream inStream = portResult.getInputStream(GAME_PATH);

		if (inStream == null) return null;

		Wc3BinInputStream stream = new Wc3BinInputStream(inStream);

		WTG wtg = new WTG(stream);

		stream.close();

		return wtg;
	}
}
