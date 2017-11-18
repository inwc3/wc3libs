package net.moonlightflower.wc3libs.slk;

import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

import net.moonlightflower.wc3libs.misc.ObjId;

public class RawSLK extends SLK<RawSLK, ObjId, RawSLK.Obj> {
	public class Obj extends SLK.Obj<ObjId> {
		public Obj(ObjId id) {
			super(id);
		}

		@Override
		public void reduce() {
		}
	}

	@Override
	public Obj createObj(ObjId id) {
		return new Obj(id);
	}

	@Override
	public void merge(RawSLK other, boolean overwrite) {
		super.merge(other, overwrite);
	}

	@Override
	protected void read(SLK<?, ? extends ObjId, ? extends SLK.Obj<? extends ObjId>> slk) {
		for (Entry<? extends ObjId, ? extends SLK.Obj<? extends ObjId>> slkEntry : slk.getObjs().entrySet()) {
		}
	}
	
	public RawSLK(File file) throws IOException {
		super(file);
	}

	public RawSLK() {
		super();
	}
	
	/*@Override
	public SLK<RawSLK, ObjId, Camera> toSlk() {
		return this;
	}*/
}
