package net.moonlightflower.wc3libs.dataTypes;

import java.io.File;

import net.moonlightflower.wc3libs.dataTypes.app.War3String;

import javax.annotation.Nonnull;

public class FileType extends War3String {
	public FileType(@Nonnull File file) {
		super(file.toString());
	}
}
