package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class VersionFlags extends FlagsInt {
	public static String getTranslatorSection() {
		return "versionFlags";
	}

	private final static Map<Integer, VersionFlags> _indexMap = new LinkedHashMap<>();

	public final static VersionFlags NONE = new VersionFlags(0x0);
	public final static VersionFlags ROC = new VersionFlags(0x1);
	public final static VersionFlags TFT = new VersionFlags(0x2);
	public final static VersionFlags BOTH = new VersionFlags(0x3);
	
	enum Flags implements FlagsInt.IsFlag {
		ROC(0x1),
		TFT(0x2),
		;
		
		private int _val;
		
		@Override
		public int getVal() {
			return _val;
		}
		
		private Flags(int val) {
			_val = val;
		}
	}
	
	public static Class<? extends Enum<Flags>> getEnumStatic() {
		return Flags.class;
	}
	
	/*public static FlagsInt[] getEnum() {
		return new FlagsInt[]{NONE, ROC, TFT};
	}*/
	
	private VersionFlags(int index) {
		super(index);

		_indexMap.put(index, this);
	}

	@Nullable
	public static VersionFlags valueOf(@Nonnull Integer index) {
		return _indexMap.get(index);
	}

	@Override
	public DataType decode(Object val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object toSLKVal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object toTXTVal() {
		// TODO Auto-generated method stub
		return null;
	}
}
