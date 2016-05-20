package net.moonlightflower.wc3libs.misc.image;

import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//import javafx.scene.image.Image;

import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiException;
import com.sun.jimi.core.raster.JimiRasterImage;

import net.moonlightflower.wc3libs.misc.Size;

public class TGA extends Wc3RasterImg {
	public void write(OutputStream outStream) throws IOException {		
		try {
			ImageProducer imgProd = getBufImg().getSource();
			
			JimiRasterImage imgOut = Jimi.createRasterImage(imgProd);
			
			Jimi.putImage("image/tga", imgOut, outStream);
		} catch (JimiException e) {
			System.err.println("TGA write error");
		}
	}
	
	public void write(File file) throws IOException {
		OutputStream outStream = new FileOutputStream(file);
		
		write(outStream);
		
		outStream.close();
	}
	
	public void read(InputStream inStream) throws IOException {
		try {
			JimiRasterImage imgIn = Jimi.getRasterImage(inStream, Jimi.SYNCHRONOUS);
			
			BufferedImage bufImg = new BufferedImage(imgIn.getWidth(), imgIn.getHeight(), BufferedImage.TYPE_INT_ARGB);
			
			for (int x = 0; x < imgIn.getWidth(); x++) {
				for (int y = 0; y < imgIn.getHeight(); y++) {					
					bufImg.setRGB(x, y, imgIn.getPixelRGB(x, y));
				}
			}

			setBufImg(bufImg);
		} catch (JimiException e) {
			System.err.println("TGA read error");
		}
	}
	
	public TGA(Size size) {
		super(size);
	}
	
	public TGA(File file) throws IOException {
		this();
		
		InputStream inStream = new FileInputStream(file);
		
		read(inStream);
		
		inStream.close();
	}
	
	public TGA() {
		super();
	}
}
