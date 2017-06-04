package net.moonlightflower.wc3libs.port;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import net.moonlightflower.wc3libs.misc.ProcCaller;

public class Registry {	
	public static String get(File dir, String key) throws IOException {
		ProcCaller proc = new ProcCaller("REG", "QUERY", dir.toString(), "/v", key);
		
		proc.exec();
		
		String result = proc.getOutString();
		
		result = result.replaceAll("\\p{Cntrl}", "");
		
		String[] splits = result.split("    ");
		
		String val = splits[3];

		return val;
	}
	
	public static String get(String dirS, String key) throws IOException {
		return get(new File(dirS), key);
	}
}
