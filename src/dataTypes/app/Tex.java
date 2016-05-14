package dataTypes.app;

import java.io.File;

import dataTypes.DataType;
import dataTypes.FileType;

public class Tex extends FileType {

	public Tex(File file) {
		super(file);
	}

	@Override
	public Tex decode(Object val) {
		return new Tex(new File(val.toString()));
	}

}
