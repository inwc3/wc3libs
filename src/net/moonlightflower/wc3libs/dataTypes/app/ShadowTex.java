package net.moonlightflower.wc3libs.dataTypes.app;

import java.io.File;

import net.moonlightflower.wc3libs.dataTypes.FileType;

public class ShadowTex extends FileType {
	public ShadowTex(File file) {
		super(file);
	}

	@Override
	public ShadowTex decode(Object val) {
		return new ShadowTex(new File(val.toString()));
	}

}
