package net.moonlightflower.wc3libs.dataTypes;

import java.io.File;

import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;

public abstract class FileType extends Wc3String {
	public FileType(File file) {
		super(file.getAbsolutePath());
	}
}
