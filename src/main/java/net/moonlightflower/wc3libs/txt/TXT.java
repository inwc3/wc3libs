package net.moonlightflower.wc3libs.txt;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.app.War3String;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.Printable;
import net.moonlightflower.wc3libs.misc.Printer;
import net.moonlightflower.wc3libs.misc.Translator;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.port.Orient;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * manages txt files like UI\\WorldEditStrings.txt and parses their entries to register key->value pairings
 */
public class TXT implements Printable {
	@Override
	public void print(@Nonnull Printer printer) {
		getDefaultSection().print(printer);

		for (Section section : getSections().values()) {
			if (section.getId() != null) printer.beginGroup(String.format("[%s]", section.getId()));

			section.print(printer);

			if (section.getId() != null) printer.endGroup();
		}
	}

	public static class Section implements Printable {
		@Override
		public void print(@Nonnull Printer printer) {
			if (getId() != null) printer.print(getId().toString());

			for (Field field : getFields().values()) {
				printer.beginGroup(field);

				field.print(printer);

				printer.endGroup();
			}
		}

		public static class Field implements Printable {
			private final List<DataType> _vals = new ArrayList<>();

			@Nonnull
			public List<DataType> getVals() {
				return _vals;
			}

			@Nonnull
			public String getValLine(@Nullable Translator translator) {
				StringBuilder s = new StringBuilder();
				
				int c = 0;

				for (DataType val : getVals()) {
					if (c > 0) s.append(",");

					String valS = (val == null) ? null : val.toTXTVal().toString();

					if (valS != null) {
						if (translator != null) {
							valS = translator.translate(valS);
						}

						if (valS.length() == 0 || valS.contains(",") || valS.contains(";")) {
							valS = "\"" + valS + "\"";
						}

						s.append(valS);
					}

					c++;
				}
				
				return s.toString();
			}

			@Nullable
			public DataType get(int index) {
				return _vals.get(index);
			}

			@Nullable
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

			public void set(@Nullable DataType val, int index) {
				ensureSize(index + 1);
				
				_vals.set(index, val);
			}
			
			public void set(@Nullable DataType val) {
				clear();
				
				set(val, 0);
			}

			@Nullable
			private static String dequote(@Nullable String s) {
				if (s == null) return null;

				Pattern pattern = Pattern.compile("^\"(.*)\"$");
				
				Matcher matcher = pattern.matcher(s);
				
				if (matcher.find()) {
					s = matcher.group(1);
				}
				
				return s;
			}

			@Nonnull
			public static List<String> tokenize(@Nonnull String line) {
				if (line.length() == 0) return new ArrayList<>();
				
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
						if (line.charAt(startPos) == ',' && startPos > 0 && line.charAt(startPos-1) == '\"') {
							startPos++;
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
				}
				
				return ret;
			}
			
			public void setLine(@Nonnull String line) {
				clear();

				List<String> tokenize = tokenize(line);
				for (String val : tokenize) {
					_vals.add(War3String.valueOf(val));
				}
			}
			
			public void add(@Nullable DataType val) {
				_vals.add(val);
			}
			
			public void merge(@Nonnull Field otherField, boolean overwrite) {
				if (overwrite) {
					//TODO: buttonpos field uses index and objmod reductions should only per-index overwrite one another
					if (getId().lower().equals(FieldId.valueOf("buttonpos"))) {
						List<DataType> otherVals = new ArrayList<>(otherField.getVals());
						
						ensureSize(otherVals.size());
						
						for (int i = 0; i < otherVals.size(); i++) {
							if (otherVals.get(i) == null) continue;
							
							_vals.set(i, otherVals.get(i));
						}
					} else {
						_vals.clear();
	
						_vals.addAll(otherField.getVals());
					}
				} else {
					for (DataType val : otherField.getVals()) {
						add(val);
					}
				}
			}
			
			public void merge(@Nonnull Field otherField) {
				merge(otherField, true);
			}
			
			private final FieldId _id;

			@Nonnull
			public FieldId getId() {
				return _id;
			}

			@Nonnull
			@Override
			public String toString() {
				return _id.toString();
			}
			
			public Field(@Nonnull FieldId id) {
				_id = id;
			}

			@Override
			public void print(@Nonnull Printer printer) {
				printer.print(getId() + ": " + getValLine(null));
			}
		}
		
		protected final Map<FieldId, Field> _fields = new LinkedHashMap<>();
		protected final Map<FieldId, Field> _fieldsLower = new LinkedHashMap<>();

		@Nonnull
		public Map<FieldId, ? extends Field> getFields() {
			return new LinkedHashMap<>(_fields);
		}

		@Nullable
		public Field getField(@Nonnull FieldId fieldId) throws FieldDoesNotExistException {
			return _fieldsLower.get(fieldId.lower());
		}
		
		public boolean containsField(@Nonnull FieldId fieldId) {
			return _fieldsLower.containsKey(fieldId.lower());
		}

		public void removeField(@Nonnull FieldId fieldId) {
			_fields.remove(fieldId.lower());
			_fieldsLower.remove(fieldId.lower());
		}

		public class FieldDoesNotExistException extends Exception {
			public FieldDoesNotExistException(@Nonnull FieldId fieldId) {
				super(fieldId.toString());
			}
		}

		public DataType get(@Nonnull FieldId fieldId, int index) throws FieldDoesNotExistException {
			Field field = getField(fieldId);
			
			if (field == null) throw new FieldDoesNotExistException(fieldId);
			
			return field.get(index);
		}
		
		public DataType get(@Nonnull FieldId fieldId) throws FieldDoesNotExistException {
			return get(fieldId, 0);
		}
		
		protected void addField(@Nonnull Field field) {
			_fields.put(field.getId(), field);
			_fieldsLower.put(field.getId().lower(), field);
		}
		
		public Field addField(@Nonnull FieldId fieldId) {
			if (containsField(fieldId)) {
				try {
					return getField(fieldId);
				} catch (FieldDoesNotExistException ignored) {
				}
			}
			
			Field field = new Field(fieldId);
			
			addField(field);
			
			return field;
		}
		
		public void set(@Nonnull FieldId fieldId, @Nullable DataType val) {
			String fieldIdString = fieldId.toString();
			
			if (fieldIdString.startsWith("//")) return;
			
			addField(fieldId).set(val);
		}
		
		public void set(@Nonnull String key, @Nullable String val) {
			set(FieldId.valueOf(key), War3String.valueOf(val));
		}
		
		public <T extends DataType> void set(@Nonnull TXTState<T> state, T val) {
			set(state.getFieldId(), val);
		}
		
		public void setLine(@Nonnull FieldId fieldId, @Nonnull String val) {
			String fieldIdString = fieldId.toString();
			
			if (fieldIdString.startsWith("//")) return;

			addField(fieldId).setLine(val);
		}
		
		public void merge(@Nonnull Section otherSection, boolean overwrite) {
			for (Map.Entry<FieldId, ? extends Field> entry : otherSection.getFields().entrySet()) {
				FieldId fieldId = entry.getKey();
				Field otherField = entry.getValue();

				Field field = addField(fieldId);

				field.merge(otherField, overwrite);
			}
		}
		
		public void print(@Nonnull PrintStream outStream) {
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

		@Nullable
		public TXTSectionId getId() {
			return _id;
		}
		
		public void write(@Nonnull BufferedWriter writer, @Nullable Translator translator) throws IOException {
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
		
		public Section(@Nullable TXTSectionId id) {
			_id = id;
		}
		
		public Section(@Nonnull String name) {
			this(TXTSectionId.valueOf(name));
		}
	}

	private final Section _defaultSection = new Section((TXTSectionId) null);

	private final Map<TXTSectionId, Section> _sections = new LinkedHashMap<>();
	@Nonnull
	public Map<TXTSectionId, Section> getSections() {
		return _sections;
	}

	public Section getDefaultSection() {
		return _defaultSection;
	}

	public Section getSection(@Nonnull TXTSectionId id) {
		return getSections().get(id);
	}

	protected void addSection(@Nonnull Section val) {
		_sections.put(val.getId(), val);
	}
	
	public Section addSection(@Nonnull TXTSectionId id) {
		if (_sections.containsKey(id))
			return getSection(id);
		
		Section section = new Section(id);
		
		addSection(section);
		
		return section;
	}

	public boolean containsKey(@Nonnull FieldId key) {
		if (_defaultSection.containsField(key)) return true;
		
		for (Section section : getSections().values()) {
			if (section.containsField(key)) return true;
		}
		
		return false;
	}
	
	public DataType get(@Nonnull FieldId key) throws Section.FieldDoesNotExistException {
		if (_defaultSection.containsField(key)) return _defaultSection.get(key);
		
		for (Section section : getSections().values()) {
			if (section.containsField(key)) return section.get(key);
		}
		
		return null;
	}
	
	public void set(@Nonnull FieldId key, @Nullable DataType val) {
		_defaultSection.addField(key).set(val);
	}
	
	public void merge(@Nonnull TXT other, boolean overwrite) {
		_defaultSection.merge(other._defaultSection, overwrite);
		
		for (Map.Entry<TXTSectionId, Section> entry : other.getSections().entrySet()) {
			TXTSectionId sectionId = entry.getKey();
			Section otherSection = entry.getValue();

			Section section = addSection(sectionId);
			
			section.merge(otherSection, overwrite);
		}
	}
	
	public void merge(@Nonnull TXT other) {
		merge(other, true);
	}
	
	public void set(@Nonnull TXT.Section section, @Nonnull FieldId field, @Nullable DataType val) {
		addSection(section);
		
		section.set(field, val);
	}
	
	public void set(@Nonnull String section, @Nonnull String field, @Nullable String val) {
		set(addSection(TXTSectionId.valueOf(section)), FieldId.valueOf(field), War3String.valueOf(val));
	}
	
	public void set(@Nonnull String field, @Nullable String val) {
		_defaultSection.set(field, val);
	}
	
	public void read(@Nonnull InputStream inStream) throws IOException {
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
	
	public void read(@Nonnull File file) throws IOException {
		InputStream inStream = new FileInputStream(file);

		read(inStream);
		
		inStream.close();
	}
	
	public void write(@Nonnull BufferedWriter writer, @Nullable Translator translator) throws IOException {
		_defaultSection.write(writer, translator);
		
		for (Section section : getSections().values()) {
			section.write(writer, translator);
		}
	}
	
	public void write(@Nonnull OutputStream outStream) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
		
		write(writer, null);
		
		writer.close();
	}
	
	public void write(@Nonnull File file, @Nullable Translator translator) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Orient.createFileOutputStream(file), StandardCharsets.UTF_8));
		
		write(writer, translator);
		
		writer.close();
	}
	
	public void write(@Nonnull File file) throws IOException {
		write(file, null);
	}
	
	public TXT(@Nonnull File file) throws IOException {
		read(file);
	}
	
	public TXT() {
	}
	
	public TXT(@Nonnull InputStream inStream) throws IOException {
		read(inStream);
	}

	@Nonnull
	public static TXT ofGameFile(@Nonnull File inFile) throws Exception {
		MpqPort.Out.Result portResult = new JMpqPort().getGameFiles(inFile);
		
		if (!portResult.getExports().containsKey(inFile)) throw new IOException(String.format("could not extract %s file", inFile.toString()));
		
		byte[] bytes = portResult.getExports().get(inFile).getOutBytes();
		
		InputStream inStream = new ByteArrayInputStream(bytes);
		
		TXT txt = new TXT(inStream);
		
		inStream.close();
		
		return txt;
	}
}
