package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.util.BitSet;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class FlagsInt extends DataType {
	private static final Logger log = LoggerFactory.getLogger(FlagsInt.class.getName());

	public interface IsFlag {
		int getVal();
	}
	
	public static class Flag extends net.moonlightflower.wc3libs.misc.Flag {
		public Flag(@Nonnull String label, int pos) {
			super(label, pos);
		}
	}
	
	//public abstract Class<? extends Enum<Flags>> getEnum();
	
	public static Class<Enum> getEnum(Class<?> c) {
		try {
			return (Class<Enum>) c.getMethod("getEnumStatic").invoke(null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			log.error(e.getMessage(), e);
		}
		
		return null;
	}
	
	private BitSet _val;
	
	public int toInt() {
		if (_val.toLongArray().length == 0) {
			return 0;
		}

		return (int) _val.toLongArray()[0];
	}

	@Nonnull
	public Set<Integer> getPoses() {
		return _val.stream().boxed().collect(Collectors.toSet());
	}
	
	public void setVal(int val) {
		_val = BitSet.valueOf(new long[]{val});
	}
	
	public boolean containsFlag(@Nonnull Flag flag) {
		return _val.get(flag.getPos());
	}
	
	public void setPos(int pos, boolean val) {
		_val.set(pos, val);
	}

	public void setPoses(@Nonnull Collection<Integer> poses, boolean val) {
		BitSet posesBitSet = new BitSet();

		for (Integer pos : poses) {
			posesBitSet.set(pos);
		}

		if (val) {
			_val.or(posesBitSet);
		} else {
			_val.andNot(posesBitSet);
		}
	}
	
	public void setFlag(@Nonnull Flag flag, boolean val) {
		_val.set(flag.getPos(), val);
	}
	
	protected FlagsInt() {
		
	}
	
	protected FlagsInt(int val) {
		_val = BitSet.valueOf(new long[]{val});
	}

	protected FlagsInt(@Nonnull BitSet val) {
		_val = val;
	}
}
