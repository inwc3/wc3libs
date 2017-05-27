package net.moonlightflower.wc3libs.txt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.moonlightflower.wc3libs.port.LadikMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.port.MpqPort.PortException;

public class WTS {
	public final static File GAME_PATH = new File("war3map.wts");
	public final static File CAMPAIGN_PATH = new File("war3campaign.wts");
	
	private final Map<Integer, String> _vals = new HashMap<>();
	
	public Map<String, String> getNamedEntries() {
		Map<String, String> res = new HashMap<>();
		
		for (Map.Entry<Integer, String> valEntry : _vals.entrySet()) {
			int key = valEntry.getKey();
			String val = valEntry.getValue();
			
			res.put(String.format("TRIGSTR_%03d", key), val);
		}
		
		return res;
	}
	
	public TXT toTXT() {
		TXT txt = new TXT();
		
		for (Map.Entry<String, String> entry : getNamedEntries().entrySet()) {
			String key = entry.getKey();
			String val = entry.getValue();
			
			txt.set(key, val);
		}
		
		return txt;
	}
	
	public String getEntry(int key) {
		return _vals.get(key);
	}
	
	public void addEntry(int key, String val) {
		_vals.put(key, val);
	}
	
	/*function this:translate(s)
	local pos, posEnd, cap = s:find('TRIGSTR_0*(%d+)[^%_]')

	while (pos ~= nil) do
		local key = tonumber(cap)

		local val = this:getString(key)

		if (val ~= nil) then
			s = s:sub(1, pos - 1)..val..s:sub(posEnd + 1, s:len())
		else
		print('rep')
			s = s:sub(1, pos - 1)..'$'..'TRIGSTR_'..cap..'_NOT_FOUND'..'$'..s:sub(posEnd + 1, s:len())
		end

		pos, posEnd, cap = s:find('TRIGSTR_0*(%d+)[^%_]')
	end

	local pos, posEnd, key = s:find('([^%s]+)', 1)

	while (pos ~= nil) do
		local val = this:getString(key) or this.stringsLower[key:lower()]

		if (val ~= nil) then
			s = s:sub(1, pos - 1)..val..s:sub(posEnd + 1, s:len())

			pos = pos + val:len()
		else
			pos = posEnd + 1
		end

		pos, posEnd, key = s:find('([^%s]+)', pos)
	end

	return s
end*/
	
	public void write(File file) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
		
		for (Map.Entry<Integer, String> entry : _vals.entrySet()) {
			int key = entry.getKey();
			String val = entry.getValue();
			
			writer.write(String.format("STRING %s\n{\n%s\n}\n", key, val));
		}

		writer.close();
	}
	
	private void read(InputStream inStream) throws IOException {
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
			
			entryMatcher.find();
			
			val = entryMatcher.group(1);
			
			if (val == null) val = "";
			
			addEntry(key, val);
		}
	}
	
	public WTS() {
	}
	
	public WTS(InputStream inStream) throws IOException {
		read(inStream);
	}
	
	public WTS(File file) throws IOException {
		InputStream inStream = new FileInputStream(file);
		
		read(inStream);
		
		inStream.close();
	}
	
	public static WTS ofMapFile(File mapFile) throws IOException {
		MpqPort.Out portOut = new LadikMpqPort.Out();
		
		portOut.add(WTS.GAME_PATH);
		
		MpqPort.Out.Result portResult = portOut.commit(mapFile);
		
		if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract wts file");
		
		byte[] bytes = portResult.getExports().get(GAME_PATH).getOutBytes();
		
		InputStream inStream = new ByteArrayInputStream(bytes);
		
		WTS wts = new WTS(inStream);
		
		inStream.close();
		
		return wts;
	}
}
