package net.moonlightflower.wc3libs.port.nativeMpq;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MPQ {
	private void read(InputStream inStream) throws IOException {
		new MPQReader(inStream);
	}
	
	public MPQ(InputStream inStream) throws IOException {
		read(inStream);
	}
	
	public MPQ(File file) throws IOException {
		InputStream inStream = new FileInputStream(file);
		
		read(inStream);
		
		inStream.close();
	}
}
