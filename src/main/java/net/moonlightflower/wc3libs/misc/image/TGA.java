package net.moonlightflower.wc3libs.misc.image;

import net.moonlightflower.wc3libs.misc.Size;
import net.moonlightflower.wc3libs.misc.UnsupportedFormatException;

import javax.annotation.Nonnull;
import java.awt.image.BufferedImage;
import java.io.*;

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
