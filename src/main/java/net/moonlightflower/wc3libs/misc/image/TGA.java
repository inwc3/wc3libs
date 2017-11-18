package net.moonlightflower.wc3libs.misc.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javafx.scene.image.Image;
import net.moonlightflower.wc3libs.misc.Size;
import net.moonlightflower.wc3libs.misc.UnsupportedFormatException;

import javax.annotation.Nonnull;

public class TGA extends Wc3RasterImg {	
	public void write(@Nonnull OutputStream outStream) throws IOException {
		//TODO
		throw new UnsupportedOperationException();
	}
	
	public void write(@Nonnull File file) throws IOException {
		OutputStream outStream = new FileOutputStream(file);
		
		write(outStream);
		
		outStream.close();
	}
	
	private void read(@Nonnull InputStream inStream) throws IOException, UnsupportedFormatException {
		BufferedImage img = TGADecoder.read(inStream);
		
		setBufImg(img);
	}
	
	public TGA(@Nonnull InputStream inStream) throws IOException, UnsupportedFormatException {
		super();
		
		read(inStream);
	}
	
	public TGA(@Nonnull File file) throws IOException, UnsupportedFormatException {
		super();

		InputStream inStream = new FileInputStream(file);
		
		read(inStream);
		
		inStream.close();
	}
	
	public TGA(@Nonnull Size size) {
		super(size);
	}
}
