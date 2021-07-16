package net.moonlightflower.wc3libs.dataTypes.app;

import java.io.File;

import net.moonlightflower.wc3libs.dataTypes.FileType;

public class Model extends FileType {
	public Model(File file) {
		super(file);
	}

	@Override
	public Model decode(Object val) {
		return new Model(new File(val.toString()));
	}
}
