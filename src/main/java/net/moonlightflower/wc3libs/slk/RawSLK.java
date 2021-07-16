package net.moonlightflower.wc3libs.slk;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.ObjId;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RawSLK extends SLK<RawSLK, ObjId, RawSLK.Obj> {
	public static class Obj extends SLK.Obj<ObjId> {
		@Override
		public Map<ObjSLK.State, DataType> getStateVals() {
			return null;
		}

		@Override
		protected void on_set(@Nonnull FieldId fieldId, @Nullable DataType val) {
		}

		@Override
		protected void on_remove(@Nonnull FieldId fieldId) {
		}

		@Override
		protected void on_clear() {
		}

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
	public void merge(@Nonnull RawSLK other, boolean overwrite) {
		super.merge(other, overwrite);
	}

	@Override
	protected void read(@Nonnull SLK<?, ? extends ObjId, ? extends SLK.Obj<? extends ObjId>> slk) {
		for (Entry<? extends ObjId, ? extends SLK.Obj<? extends ObjId>> slkEntry : slk.getObjs().entrySet()) {
		}
	}
	
	public RawSLK(@Nonnull File file) throws IOException {
		super(file);
	}

	public RawSLK() {
		super();
	}
}
