package bin.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bin.Format;
import bin.Wc3bin;
import bin.Wc3bin.Stream;
import bin.Wc3bin.StreamException;

/**
 * imports file for wrapping war3map.imp
 */
interface IMP_Streamable {
	public void read(Stream stream) throws StreamException;
	public void write(Stream stream);
}

public class IMP {
	public final static String GAME_PATH = "war3map.imp";
	
	public static class StdFlag {
		private final static Map<Integer, StdFlag> _map = new HashMap<>();
		
		public final static StdFlag CUSTOM = new StdFlag(0xA, 0xD);
		public final static StdFlag STD = new StdFlag(0x0, 0x5, 0x8);
		
		int _val;
		
		public int getVal() {
			return _val;
		}
		
		static public StdFlag fromVal(int val) {
			return _map.get(val);
		}
		
		private StdFlag(int ...vals) {
			for (int val : vals) {
				_val = val;
				
				_map.put(val, this);
			}
		}
	}
	
	public static class Obj implements IMP_Streamable {		
		private String _path;
		
		public String getPath() {
			return _path;
		}
		
		public void setPath(String val) {
			_path = val;
		}
		
		private StdFlag _stdFlag = StdFlag.STD;
		
		public StdFlag getStdFlag() {
			return _stdFlag;
		}
		
		public void setStdFlag(StdFlag val) {
			_stdFlag = val;
		}
		
		private void merge(Obj other) {
			
		}
		
		public void read(Stream stream, EncodingFormat format) throws StreamException {
			Obj other = null;
			
			switch (format.toEnum()) {
			case IMP_0x1: {
				other = new IMP_0x1.Obj(stream);
				
				break;
			}
			}
			
			if (other != null) merge(other);
		}
		
		public void write(Stream stream, EncodingFormat format) {
			Obj other = null;
			
			switch (format.toEnum()) {
			case AUTO:
			case IMP_0x1: {
				other = new IMP_0x1.Obj();
				
				break;
			}
			}
			
			other.merge(this);
			
			if (other != null) other.write(stream);
		}
		
		@Override
		public void read(Stream stream) {
		}

		@Override
		public void write(Stream stream) {
		}
		
		public Obj(Stream stream, EncodingFormat format) throws StreamException {
			read(stream, format);
		}
		
		public Obj() {
		}
	}
	
	protected List<Obj> _objs = new ArrayList<>();
	
	public List<Obj> getObjs() {
		return _objs;
	}
	
	public void addObj(Obj val) {
		_objs.add(val);
	}
	
	public Obj addObj() {
		Obj obj = new Obj();
		
		addObj(obj);
		
		return obj;
	}
	
	public void merge(IMP other) {
		for (Obj obj : other.getObjs()) {
			addObj(obj);
		}
	}
	
	public static class EncodingFormat extends Format<EncodingFormat.Enum> {
		enum Enum {
			AUTO,
			IMP_0x1,
		}

		static final Map<Integer, EncodingFormat> _map = new HashMap<>();
		
		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat IMP_0x1 = new EncodingFormat(Enum.IMP_0x1, 0x1);
		
		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}
		
		private EncodingFormat(Enum enumVal, int version) {
			super(enumVal, version);
			
			_map.put(version, this);
		}
	}
	
	private void read_auto(Stream stream) throws StreamException {
		int version = stream.readInt();
		
		stream.rewind();

		read(stream, EncodingFormat.valueOf(version));
	}

	private void read(Stream stream, EncodingFormat format) throws StreamException {
		IMP other = null;
		
		switch (format.toEnum()) {
		case AUTO: {
			read_auto(stream);
			
			break;
		}
		case IMP_0x1: {
			other = new IMP_0x1(stream);
			
			break;
		}
		}
		
		if (other != null) merge(other);
	}
	
	private void write(Stream stream, EncodingFormat format) {
		IMP other = null;
		
		switch (format.toEnum()) {
		case AUTO:
		case IMP_0x1: {
			other = new IMP_0x1();
			
			break;
		}
		}
		
		other.merge(this);
		
		other.write(stream);
	}
	
	private void read(Stream stream) throws StreamException {
		read(stream, EncodingFormat.AUTO);
	}
	
	public void write(Stream stream) {
		write(stream, EncodingFormat.AUTO);
	}
	
	private void read(File file, EncodingFormat format) throws IOException {
		read(new Wc3bin.Stream(file), format);
	}
	
	public void write(File file, EncodingFormat format) throws IOException {
		write(new Wc3bin.Stream(file), format);
	}
	
	private void read(File file) throws IOException {
		read(file, EncodingFormat.AUTO);
	}

	public void write(File file) throws FileNotFoundException {
		Stream stream = new Wc3bin.Stream();
		
		write(stream);
		
		stream.write(file);
	}

	public IMP(Stream stream) throws FileNotFoundException, StreamException {
		read(stream);
	}
	
	public IMP(File file) throws IOException {
		read(file);
	}

	public IMP() {
	}
}
