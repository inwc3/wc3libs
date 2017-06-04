package net.moonlightflower.wc3libs.dataTypes.app;

import java.io.File;

import net.moonlightflower.wc3libs.dataTypes.FileType;

public class MusicFile extends FileType {
	public MusicFile(File file) {
		super(file);
	}

	@Override
	public MusicFile decode(Object val) {
		return new MusicFile(new File(val.toString()));
	}
}
