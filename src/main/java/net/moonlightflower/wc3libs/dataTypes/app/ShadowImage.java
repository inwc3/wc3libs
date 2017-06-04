package net.moonlightflower.wc3libs.dataTypes.app;

import java.io.File;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.FileType;

public class ShadowImage extends FileType {
	public final static ShadowImage FLY = new ShadowImage("ShadowFlyer");
	public final static ShadowImage GROUND = new ShadowImage("Shadow");
	public final static ShadowImage NONE = new ShadowImage("");
	
	public ShadowImage(File file) {
		super(file);
	}
	
	private ShadowImage(String file) {
		this(new File(file));
	}

	@Override
	public ShadowImage decode(Object val) {
		return new ShadowImage(new File(val.toString()));
	}
}
