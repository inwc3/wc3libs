package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinStream;

class IMP_0x1 extends IMP implements IMP_Streamable {
	public static class Obj extends IMP.Obj {
		@Override
		public void read(Wc3BinStream stream) throws BinStream.StreamException {
			setStdFlag(StdFlag.fromVal(stream.readUByte()));
			setPath(stream.readString());
		}
		
		@Override
		public void write(Wc3BinStream stream) {
			stream.writeUByte(getStdFlag().getVal());
			stream.writeString(getPath());
		}
		
		public Obj(Wc3BinStream stream) throws BinStream.StreamException {
			read(stream);
		}
		
		public Obj() {
		}
	}
	
	@Override
	public void read(Wc3BinStream stream) throws BinStream.StreamException {
		int version = stream.readInt();

		Wc3BinStream.checkFormatVer("impMaskFunc", EncodingFormat.IMP_0x1.getVersion(), version);
		
		int impsCount = stream.readInt();
		
		for (int i = 0; i < impsCount; i++) {
			addObj(new Obj(stream));
		}
	}
	
	@Override
	public void write(Wc3BinStream stream) {
		stream.writeInt(EncodingFormat.IMP_0x1.getVersion());
		
		stream.writeInt(_objs.size());
		
		for (IMP.Obj obj : _objs) {
			obj.write(stream);
		}
	}
	
	public IMP_0x1(Wc3BinStream stream) throws BinStream.StreamException {
		read(stream);
	}
	
	public IMP_0x1() {
	}
}
