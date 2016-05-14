package txt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WTS {
	private Map<Integer, String> _vals = new HashMap<>();
	
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
	
	public void writeToFile(File file) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
		
		for (Map.Entry<Integer, String> entry : _vals.entrySet()) {
			int key = entry.getKey();
			String val = entry.getValue();
			
			writer.write(String.format("STRING %s\n{\n%s\n}\n", key, val));
		}

		writer.close();
	}
	
	public void readFromFile(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

		char[] buf = new char[(int) file.length()];
		
		reader.read(buf);

		reader.close();
		
		String input = new String(buf);
		
		input.replaceAll("//[^\\n]*\\n", "");
		
		Pattern pat = Pattern.compile("STRING ([\\d]+)[\\n\\s]*{([^}]*)[\\n]*}");
		
		Matcher matcher = pat.matcher(input);
		
		while (matcher.find()) {
			Integer key = Integer.parseInt(matcher.group(1));
			String val = matcher.group(2);
			
			val = Pattern.compile("^\\p{Cntrl}*(.*)").matcher(val).group(1);
			val = Pattern.compile("^(.*[\\p{Cntrl}])").matcher(val).group(1);
			
			if (val == null) val = "";
			
			addEntry(key, val);
		}
	}
	
	public WTS() {
	}
}
