package net.moonlightflower.wc3libs.slk;

import java.io.File;
import java.io.IOException;

import net.moonlightflower.wc3libs.misc.ObjId;

public abstract class MetaSLK<Self extends SLK<Self, ObjIdType, ObjType>, ObjIdType extends ObjId, ObjType extends SLK.Obj<? extends ObjIdType>> extends SLK<Self, ObjIdType, ObjType> {
	public MetaSLK(File file) throws IOException {
		super(file);
	}
	
	public MetaSLK() {
		super();
	}
}
