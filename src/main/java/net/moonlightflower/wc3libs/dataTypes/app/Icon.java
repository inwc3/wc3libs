package net.moonlightflower.wc3libs.dataTypes.app;

import java.io.File;

import net.moonlightflower.wc3libs.dataTypes.FileType;

public class Icon extends FileType {
	private Icon(File file) {
		super(file);
	}

	public static Icon valueOf(File file) {
		return new Icon(file);
	}
	
	@Override
	public Icon decode(Object val) {
		return valueOf(new File(val.toString()));
	}
	
	public static Icon decodeStatic(Object val) {
		if (val != null)
			return valueOf(new File(val.toString()));

		return null;
	}
}
