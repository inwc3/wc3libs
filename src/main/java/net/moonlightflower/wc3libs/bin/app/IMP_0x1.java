package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;

import javax.annotation.Nonnull;

class IMP_0x1 extends IMP implements IMP_Streamable {
	public static class Obj extends IMP.Obj {
		@Override
		public void read(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			_stdFlag = StdFlag.fromVal(stream.readUByte("stdFlag"));
			_path = stream.readString("path");
		}
		
		@Override
		public void write(@Nonnull Wc3BinOutputStream stream) {
			stream.writeUByte(_stdFlag.getVal());
			stream.writeString(_path);
		}
		
		public Obj(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			read(stream);
		}
		
		public Obj() {
		}
	}
	
	@Override
	public void read(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int version = stream.readInt32();

		stream.checkFormatVersion(EncodingFormat.IMP_0x1.getVersion(), version);
		
		int impsCount = stream.readInt32();
		
		for (int i = 0; i < impsCount; i++) {
			addObj(new Obj(stream));
		}
	}
	
	@Override
	public void write(@Nonnull Wc3BinOutputStream stream) {
		stream.writeInt32(EncodingFormat.IMP_0x1.getVersion());
		
		stream.writeInt32(_objs.size());
		
		for (IMP.Obj obj : _objs) {
			obj.write(stream);
		}
	}
	
	public IMP_0x1(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		read(stream);
	}
	
	public IMP_0x1() {
	}
}
