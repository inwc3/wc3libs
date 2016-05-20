package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.Wc3bin;
import net.moonlightflower.wc3libs.bin.Wc3bin.Stream;
import net.moonlightflower.wc3libs.bin.Wc3bin.StreamException;

class IMP_0x1 extends IMP implements IMP_Streamable {
	public static class Obj extends IMP.Obj {
		@Override
		public void read(Stream stream) throws StreamException {
			int val = stream.readByte();
			
			setStdFlag(StdFlag.fromVal(val));
			setPath(stream.readString());
		}
		
		@Override
		public void write(Stream stream) {
			stream.writeByte(getStdFlag().getVal());
			stream.writeString(getPath());
		}
		
		public Obj(Wc3bin.Stream stream) throws StreamException {
			read(stream);
		}
		
		public Obj() {
		}
	}
	
	@Override
	public void read(Stream stream) throws StreamException {
		int version = stream.readInt();

		Wc3bin.checkFormatVer("impMaskFunc", EncodingFormat.IMP_0x1.getVersion(), version);
		
		int impsCount = stream.readInt();
		
		for (int i = 0; i < impsCount; i++) {
			addObj(new Obj(stream));
		}
	}
	
	@Override
	public void write(Stream stream) {
		stream.writeInt(EncodingFormat.IMP_0x1.getVersion());
		
		stream.writeInt(_objs.size());
		
		for (IMP.Obj obj : _objs) {
			obj.write(stream);
		}
	}
	
	public IMP_0x1(Stream stream) throws StreamException {
		read(stream);
	}
	
	public IMP_0x1() {
	}
}
