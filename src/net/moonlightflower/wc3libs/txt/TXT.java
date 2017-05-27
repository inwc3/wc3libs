package net.moonlightflower.wc3libs.txt;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthSpinnerUI;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.Translator;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.port.Orient;

/**
 * manages txt files like UI\\WorldEditStrings.txt and parses their entries to register key->value pairings
 */
public class TXT {	
	public static class Section {
		public static class Field {
			private final List<DataType> _vals = new ArrayList<>();
			
			public List<DataType> getVals() {
				return _vals;
			}
			
			public String getValLine(Translator translator) {
				StringBuilder s = new StringBuilder();
				
				int c = 0;
				
				for (DataType val : getVals()) {
					if (c > 0) s.append(","); 
					
					String valS = (val == null) ? null : val.toTXTVal().toString();
					
					if (valS != null) {
						if ((valS instanceof String) && (translator != null)) {
							valS = translator.translate(valS);
							
							valS = "\"" + valS + "\"";
						}
						
						s.append(valS);
					}
					
					c++;
				}
				
				return s.toString();
			}
			
			public DataType get(int index) {
				try {
					return _vals.get(index);
				} catch (Exception e) {
				}
				
				return null;
			}
			
			public DataType get() {
				return get(0);
			}
			
			public void clear() {
				_vals.clear();
			}
			
			public void ensureSize(int size) {
				while (_vals.size() < size) {
					_vals.add(null);
				}
			}
			
			public void set(DataType val, int index) {
				ensureSize(index + 1);
				
				_vals.set(index, val);
			}
			
			public void set(DataType val) {
				clear();
				
				set(val, 0);
			}
			
			private static String dequote(String s) {
				if ((s.charAt(0) == '\"') && (s.charAt(s.length() - 1) == '\"')) {
					s = s.substring(1, s.length() - 1);
				}
				
				return s;
			}
			
			public static List<String> tokenize(String line) {
				if (line.length() == 0) return null;
				
				List<String> ret = new ArrayList<>();
				
				int startPos = 0;

				while (startPos < line.length()) {
					if (line.charAt(startPos) == '\"') {
						int endPos = line.indexOf('\"', startPos + 1);
						
						if (endPos == -1) {
							endPos = line.length() - 1;
						}
						
						String val = line.substring(startPos, endPos + 1);
						
						val = dequote(val);
						
						ret.add(val);
						
						startPos = endPos + 1;
					} else {
						int endPos = line.indexOf(',', startPos);

						if (endPos == -1) {
							endPos = line.length() - 1;
						} else {
							endPos = endPos - 1;
						}

						String val = (endPos < startPos) ? "" : line.substring(startPos, endPos + 1);
						
						ret.add(val);
						
						startPos = endPos + 2;
					}
				}
				
				return ret;
			}
			
			public void setLine(String line) {
				clear();

				for (String val : tokenize(line)) {
					_vals.add(Wc3String.valueOf(val));
				}
			}
			
			public void add(DataType val) {
				_vals.add(val);
			}
			
			public void merge(Field otherField, boolean overwrite) {
				for (DataType val : otherField.getVals()) {
					add(val);
				}
			}
			
			public void merge(Field otherField) {
				merge(otherField, true);
			}
			
			private FieldId _id;
			
			public FieldId getId() {
				return _id;
			}
			
			public Field(FieldId id) {
				_id = id;
			}
		}
		
		protected final Map<FieldId, Field> _fields = new HashMap<>();
		
		public Map<FieldId, ? extends Field> getFields() {
			return _fields;
		}
		
		public Field getField(FieldId fieldId) {
			return getFields().get(fieldId);
		}

		public boolean containsField(FieldId fieldId) {
			return getFields().containsKey(fieldId);
		}
		
		public DataType get(FieldId fieldId, int index) throws Exception {
			Field field = getField(fieldId);
			
			if (field == null) throw new Exception();
			
			return getField(fieldId).get(index);
		}
		
		public DataType get(FieldId fieldId) throws Exception {			
			return get(fieldId, 0);
		}
		
		protected void addField(Field field) {
			_fields.put(field.getId(), field);
		}
		
		public Field addField(FieldId fieldId) {
			if (containsField(fieldId)) return getField(fieldId);
			
			Field field = new Field(fieldId);
			
			addField(field);
			
			return field;
		}
		
		public void set(FieldId fieldId, DataType val) {
			String fieldIdString = fieldId.toString();
			
			if (fieldIdString.startsWith("//")) return;
			
			addField(fieldId).set(val);
		}
		
		public void set(String key, String val) {
			set(FieldId.valueOf(key), Wc3String.valueOf(val));
		}
		
		public <T extends DataType> void set(TXTState<T> state, T val) {
			set(state.getFieldId(), val);
		}
		
		public void setLine(FieldId fieldId, String val) {
			String fieldIdString = fieldId.toString();
			
			if (fieldIdString.startsWith("//")) return;

			addField(fieldId).setLine(val);
		}
		
		public void merge(Section otherSection, boolean overwrite) {
			for (Map.Entry<FieldId, ? extends Field> entry : otherSection.getFields().entrySet()) {
				FieldId fieldId = entry.getKey();
				Field otherField = entry.getValue();

				Field field = addField(fieldId);

				field.merge(otherField, overwrite);
			}
		}
		
		public void print(PrintStream outStream) {
			for (Map.Entry<FieldId, ? extends TXT.Section.Field> fieldEntry : getFields().entrySet()) {
				FieldId fieldId = fieldEntry.getKey();
				TXT.Section.Field field = fieldEntry.getValue();
				
				outStream.print(String.format("\t%s: ", fieldId.toString()));
				
				int c = 0;
				
				for (DataType val : field.getVals()) {
					outStream.print(val);
					
					if (c > 0) {
						outStream.print("; ");
					}
					
					c++;
				}
				
				outStream.println();
			}
		}
		
		public void print() {
			print(System.out);
		}
		
		private final TXTSectionId _id;
		
		public TXTSectionId getId() {
			return _id;
		}
		
		public void write(BufferedWriter writer, Translator translator) throws IOException {
			if (getId() != null) {
				writer.write(String.format("[%s]", getId().toString()));
				
				writer.newLine();
			}
			
			for (Map.Entry<FieldId, ? extends TXT.Section.Field> fieldEntry : getFields().entrySet()) {
				FieldId fieldId = fieldEntry.getKey();
				Field field = fieldEntry.getValue();

				String valLine = field.getValLine(translator);

				writer.write(String.format("%s=%s", fieldId.toString(), valLine));
				
				writer.newLine();
			}
		}
		
		public Section(TXTSectionId id) {
			_id = id;
		}
		
		public Section(String name) {
			this(TXTSectionId.valueOf(name));
		}
	}

	private final Section _defaultSection = new Section((TXTSectionId) null);
	private final Map<TXTSectionId, Section> _sections = new HashMap<>();
	
	public Map<TXTSectionId, Section> getSections() {
		return _sections;
	}
	
	public Section getSection(TXTSectionId id) {
		return getSections().get(id);
	}

	protected void addSection(Section val) {
		_sections.put(val.getId(), val);
	}
	
	public Section addSection(TXTSectionId id) {
		if (_sections.containsKey(id)) return getSection(id);
		
		Section section = new Section(id);
		
		addSection(section);
		
		return section;
	}

	public boolean containsKey(FieldId key) {
		if (_defaultSection.containsField(key)) return true;
		
		for (Section section : getSections().values()) {
			if (section.containsField(key)) return true;
		}
		
		return false;
	}
	
	public DataType get(FieldId key) throws Exception {
		if (_defaultSection.containsField(key)) return _defaultSection.get(key);
		
		for (Section section : getSections().values()) {
			if (section.containsField(key)) return section.get(key);
		}
		
		return null;
	}
	
	public void set(FieldId key, DataType val) {
		_defaultSection.addField(key).set(val);
	}
	
	public void merge(TXT other, boolean overwrite) {
		_defaultSection.merge(other._defaultSection, overwrite);
		
		for (Map.Entry<TXTSectionId, Section> entry : other.getSections().entrySet()) {
			TXTSectionId sectionId = entry.getKey();
			Section otherSection = entry.getValue();

			Section section = addSection(sectionId);
			
			section.merge(otherSection, overwrite);
		}
	}
	
	public void merge(TXT other) {
		merge(other, true);
	}
	
	public DataType get(TXTState state, int index) throws Exception {
		return getSection(state.getSectionId()).get(state.getFieldId(), index);
	}
	
	public DataType get(TXTState state) throws Exception {
		return get(state, 0);
	}
	
	public void set(TXT.Section section, FieldId field, DataType val) {
		addSection(section);
		
		section.set(field, val);
	}
	
	public void set(TXTState state, DataType val) {
		set(getSection(state.getSectionId()), state.getFieldId(), val);
	}
	
	public void set(String section, String field, String val) {
		set(addSection(TXTSectionId.valueOf(section)), FieldId.valueOf(field), Wc3String.valueOf(val));
	}
	
	public void set(String field, String val) {
		_defaultSection.set(field, val);
	}
	
	public void print(PrintStream outStream) {
		_defaultSection.print(outStream);
		
		for (Map.Entry<TXTSectionId, TXT.Section> sectionEntry : getSections().entrySet()) {
			TXTSectionId sectionId = sectionEntry.getKey();
			TXT.Section section = sectionEntry.getValue();
			
			outStream.println(String.format("[%s]", sectionId.toString()));
			
			section.print(outStream);
		}
	}
	
	public void print() {
		print(System.out);
	}
	
	public void read(InputStream inStream) throws IOException {
		Section curSection = _defaultSection;
		String line;

		UTF8 reader = new UTF8(inStream);
		
		while ((line = reader.readLine()) != null) {
			line = line.replaceAll("//.*", "");

			if (line.matches("^\\s*$")) continue;

			Pattern sectionPattern = Pattern.compile("^\\[(.+)\\]$");
			
			String lineTrimmed = line.replaceAll("\\s", "");
			
			Matcher sectionMatcher = sectionPattern.matcher(lineTrimmed);
			
			if (sectionMatcher.matches()) {
				TXTSectionId sectionId = TXTSectionId.valueOf(sectionMatcher.group(1));
				
				if (sectionId.toString().startsWith("h")) {
					System.out.println("section " + sectionId);
				}
				
				curSection = addSection(sectionId);
			}
			
			Pattern valPattern = Pattern.compile("([^=]+)=(.+)");

			Matcher valMatcher = valPattern.matcher(line);

			if (valMatcher.matches()) {
				//System.out.println("setline " + FieldId.valueOf(valMatcher.group(1)) + ";" + valMatcher.group(2));
				curSection.setLine(FieldId.valueOf(valMatcher.group(1)), valMatcher.group(2));
			}
		}
	}
	
	public void read(File file) throws IOException {
		if (file == null) throw new IOException("file is null");
		
		InputStream inStream = new FileInputStream(file);

		read(inStream);
		
		inStream.close();
	}
	
	public void write(BufferedWriter writer, Translator translator) throws IOException {
		_defaultSection.write(writer, translator);
		
		for (Section section : getSections().values()) {
			section.write(writer, translator);
		}
	}
	
	public void write(OutputStream outStream) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
		
		write(writer, null);
		
		writer.close();
	}
	
	public void write(File file, Translator translator) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Orient.createFileOutputStream(file), StandardCharsets.UTF_8));
		
		write(writer, translator);
		
		writer.close();
	}
	
	public void write(File file) throws IOException {
		write(file, null);
	}
	
	public TXT(File file) throws IOException {
		read(file);
	}
	
	public TXT() {
	}
	
	public TXT(InputStream inStream) throws IOException {
		read(inStream);
	}
	
	public static TXT ofGameFile(File inFile) throws Exception {
		MpqPort.Out.Result portResult = MpqPort.getDefaultImpl().getGameFiles(inFile);
		
		if (!portResult.getExports().containsKey(inFile)) throw new IOException(String.format("could not extract %s file", inFile.toString()));
		
		byte[] bytes = portResult.getExports().get(inFile).getOutBytes();
		
		InputStream inStream = new ByteArrayInputStream(bytes);
		
		TXT txt = new TXT(inStream);
		
		inStream.close();
		
		return txt;
	}
}
