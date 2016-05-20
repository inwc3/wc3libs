package net.moonlightflower.wc3libs.dataTypes.app;

import java.io.File;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.FileType;

public class Tex extends FileType {

	public Tex(File file) {
		super(file);
	}

	@Override
	public Tex decode(Object val) {
		return new Tex(new File(val.toString()));
	}

}
