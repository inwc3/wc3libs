package net.moonlightflower.wc3libs.txt;

import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WTS {
	public final static File GAME_PATH = new File("war3map.wts");
	public final static File CAMPAIGN_PATH = new File("war3campaign.wts");
	
	private final Map<Integer, String> _vals = new LinkedHashMap<>();

    @Nonnull
    public Map<Integer, String> getKeyedEntries() {
        return _vals;
    }

	@Nonnull
	public Map<String, String> getNamedEntries() {
		Map<String, String> res = new LinkedHashMap<>();
		
		for (Map.Entry<Integer, String> valEntry : _vals.entrySet()) {
			int key = valEntry.getKey();
			String val = valEntry.getValue();
			
			res.put(String.format("TRIGSTR_%03d", key), val);
		}
		
		return res;
	}

	@Nonnull
	public TXT toTXT() {
		TXT txt = new TXT();
		
		for (Map.Entry<String, String> entry : getNamedEntries().entrySet()) {
			String key = entry.getKey();
			String val = entry.getValue();
			
			txt.set(key, val);
		}
		
		return txt;
	}

	@Nonnull
	public String getEntry(int key) {
		return _vals.getOrDefault(key, "");
	}
	
	public void addEntry(int key, @Nullable String val) {
		_vals.put(key, val);
	}
	
	public void write(@Nonnull File file) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
		
		for (Map.Entry<Integer, String> entry : _vals.entrySet()) {
			int key = entry.getKey();
			String val = entry.getValue();
			
			writer.write(String.format("STRING %s\n{\n%s\n}\n", key, val));
		}

		writer.close();
	}
	
	private void read(@Nonnull InputStream inStream) throws IOException {
		UTF8 reader = new UTF8(inStream);
		
		String input = reader.readAll();
		
		Pattern commentPattern = Pattern.compile("//[^\\n]*\\n", Pattern.DOTALL);
		
		Matcher commentMatcher = commentPattern.matcher(input);
		
		input = commentMatcher.replaceAll("");
		
		Pattern pat = Pattern.compile("STRING ([\\d]+)[\\n\\s]*\\{([^\\}]*)[\\n]*\\}");
		
		Matcher matcher = pat.matcher(input);
		
		while (matcher.find()) {
			Integer key = Integer.parseInt(matcher.group(1));
			String val = matcher.group(2);
			
			val = val.replaceAll("\\p{Cntrl}", "");
			
			Matcher entryMatcher = Pattern.compile("^(.*)").matcher(val);
			
			if (entryMatcher.find()) {
				val = entryMatcher.group(1);

				if (val == null) val = "";

				addEntry(key, val);
			}
		}
	}
	
	public WTS() {
	}
	
	public WTS(@Nonnull InputStream inStream) throws IOException {
		read(inStream);
	}
	
	public WTS(@Nonnull File file) throws IOException {
		InputStream inStream = new FileInputStream(file);
		
		read(inStream);
		
		inStream.close();
	}

	@Nonnull
	public static WTS ofMapFile(@Nonnull File mapFile) throws Exception {
		MpqPort.Out portOut = new JMpqPort.Out();
		
		portOut.add(WTS.GAME_PATH);
		
		MpqPort.Out.Result portResult = portOut.commit(mapFile);
		
		if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract wts file");
		
		byte[] bytes = portResult.getExports().get(GAME_PATH).getOutBytes();
		
		InputStream inStream = new ByteArrayInputStream(bytes);
		
		WTS wts = new WTS(inStream);
		
		inStream.close();
		
		return wts;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WTS wts = (WTS) o;
        return Objects.equals(_vals, wts._vals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_vals);
    }
}
