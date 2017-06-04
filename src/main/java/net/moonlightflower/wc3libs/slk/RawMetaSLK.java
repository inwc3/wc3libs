package net.moonlightflower.wc3libs.slk;

import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.RawSLK.Obj;

public class RawMetaSLK extends MetaSLK<RawMetaSLK, ObjId, RawMetaSLK.Obj> {
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
	public void merge(RawMetaSLK other, boolean overwrite) {
		super.merge(other, overwrite);
	}

	@Override
	protected void read(SLK<?, ? extends ObjId, ? extends SLK.Obj<? extends ObjId>> slk) {
		for (Entry<? extends ObjId, ? extends SLK.Obj<? extends ObjId>> slkEntry : slk.getObjs().entrySet()) {
		}
	}
	
	public RawMetaSLK(File file) throws IOException {
		super(file);
	}

	public RawMetaSLK() {
		super();
	}
}
