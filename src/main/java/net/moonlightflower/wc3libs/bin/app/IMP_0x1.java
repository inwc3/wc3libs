package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;

import javax.annotation.Nonnull;

class IMP_0x1 extends IMP implements IMP_Streamable {
	public static class Obj extends IMP.Obj {
		@Override
		public void read(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			setStdFlag(StdFlag.fromVal(stream.readUByte()));
			setPath(stream.readString());
		}
		
		@Override
		public void write(@Nonnull Wc3BinOutputStream stream) {
			stream.writeUByte(getStdFlag().getVal());
			stream.writeString(getPath());
		}
		
		public Obj(Wc3BinInputStream stream) throws BinInputStream.StreamException {
			read(stream);
		}
		
		public Obj() {
		}
	}
	
	@Override
	public void read(Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int version = stream.readInt();

		Wc3BinInputStream.checkFormatVer("impMaskFunc", EncodingFormat.IMP_0x1.getVersion(), version);
		
		int impsCount = stream.readInt();
		
		for (int i = 0; i < impsCount; i++) {
			addObj(new Obj(stream));
		}
	}
	
	@Override
	public void write(Wc3BinOutputStream stream) {
		stream.writeInt(EncodingFormat.IMP_0x1.getVersion());
		
		stream.writeInt(_objs.size());
		
		for (IMP.Obj obj : _objs) {
			obj.write(stream);
		}
	}
	
	public IMP_0x1(Wc3BinInputStream stream) throws BinInputStream.StreamException {
		read(stream);
	}
	
	public IMP_0x1() {
	}
}
