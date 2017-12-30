package net.moonlightflower.wc3libs.port;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import net.moonlightflower.wc3libs.misc.ProcCaller;

import javax.annotation.Nonnull;

public class Registry {	
	public static String get(@Nonnull File dir, @Nonnull String key) throws IOException {
		//if (!System.getProperty("os.name").toLowerCase().startsWith("win")) throw new IllegalArgumentException("not a windows system");

		ProcCaller proc = new ProcCaller("REG", "QUERY", dir.toString(), "/v", key);
		
		proc.exec();
		
		String result = proc.getOutString();
		
		result = result.replaceAll("\\p{Cntrl}", "");
		
		String[] splits = result.split("    ");

		if (splits.length < 4) throw new IOException("entry " + dir + ";" + key + " not found");

		return splits[3];
	}
	
	public static String get(@Nonnull String dirS, @Nonnull String key) throws IOException {
		return get(new File(dirS), key);
	}
}
