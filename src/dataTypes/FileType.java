package dataTypes;

import java.io.File;

import dataTypes.app.Wc3String;

public abstract class FileType extends Wc3String {
	public FileType(File file) {
		super(file.getAbsolutePath());
	}
}
