package net.moonlightflower.wc3libs.slk;

import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.RawSLK.Obj;

import javax.annotation.Nonnull;

public class RawMetaSLK extends MetaSLK<RawMetaSLK, ObjId, RawMetaSLK.Obj> {
	public class Obj extends SLK.Obj<ObjId> {
		public Obj(@Nonnull ObjId id) {
			super(id);
		}

		@Override
		public void reduce() {
		}
	}

	@Nonnull
    @Override
	public Obj createObj(@Nonnull ObjId id) {
		return new Obj(id);
	}

	@Override
	public void merge(@Nonnull RawMetaSLK other, boolean overwrite) {
		super.merge(other, overwrite);
	}

	@Override
	protected void read(@Nonnull SLK<?, ? extends ObjId, ? extends SLK.Obj<? extends ObjId>> slk) {
		for (Entry<? extends ObjId, ? extends SLK.Obj<? extends ObjId>> slkEntry : slk.getObjs().entrySet()) {
		}
	}
	
	public RawMetaSLK(@Nonnull File file) throws IOException {
		super(file);
	}

	public RawMetaSLK() {
		super();
	}
}
