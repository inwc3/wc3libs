package net.moonlightflower.wc3libs.misc;

import net.moonlightflower.wc3libs.dataTypes.app.War3String;
import net.moonlightflower.wc3libs.txt.TXT;
import net.moonlightflower.wc3libs.txt.TXTSectionId;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.PrintStream;
import java.util.Map;

/**
 * Translates strings based on previously registered vocabulary, you can add txt game files like UI\WorldEditStrings.txt
 */
public class Translator implements Printable {
	private final TXT _txt = createTXT();

	protected TXT createTXT() {
		return new TXT();
	}

	@Nonnull
	public TXT getTXT() {
		return _txt;
	}
	
	public boolean contains(@Nonnull String key) {
		return _txt.containsKey(FieldId.valueOf(key));
	}
	
	public boolean contains(@Nonnull String sectionName, @Nonnull String key) {
		TXT.Section section = _txt.getSection(TXTSectionId.valueOf(sectionName));

		return section != null && section.containsField(FieldId.valueOf(key));

	}

	@Nullable
	public String get(@Nonnull String key) {
		try {
			return _txt.get(FieldId.valueOf(key)).toString();
		} catch (TXT.Section.FieldDoesNotExistException ignored) {
		}

		return null;
	}

	@Nullable
	public String get(@Nonnull String sectionName, @Nonnull String fieldName) {
		TXT.Section section = _txt.getSection(TXTSectionId.valueOf(sectionName));
		
		if (section == null) return null;
		
		try {
			return section.get(FieldId.valueOf(fieldName)).toString();
		} catch (Exception ignored) {
		}
		
		return null;
	}
	
	public void set(@Nonnull String key, @Nonnull String val) {
		_txt.set(FieldId.valueOf(key), War3String.valueOf(val));
	}

	@Nullable
	public String translate(@Nonnull String key) {
		if (contains(key)) return get(key);
		
		return key;
	}

	@Nullable
	public String translate(@Nonnull String sectionName, @Nonnull String val) {
		if (contains(sectionName, val)) return get(sectionName, val);
		
		return val;
	}

	@Nullable
	public String translateText(@Nonnull String text) {
		// TODO
		return text;
	}
	
	public void addTXT(@Nonnull TXT other) {
		_txt.merge(other);
	}

	public void addDataTypeTXT(@Nonnull TXT txt) {
		TXT other = new TXT();
		
		for (Map.Entry<TXTSectionId, TXT.Section> sectionEntry : txt.getSections().entrySet()) {
			TXTSectionId sectionId = sectionEntry.getKey();
			TXT.Section section = sectionEntry.getValue();
			
			TXT.Section otherSection = other.addSection(sectionId);
			
			for (Map.Entry<FieldId, ? extends TXT.Section.Field> fieldEntry : section.getFields().entrySet()) {
				FieldId fieldId = fieldEntry.getKey();
				TXT.Section.Field field = fieldEntry.getValue();

				if (field.getId().toString().matches("^\\d+$")) {;				
					String dataName = field.get(0).toString();
					String displayName = field.get(1).toString();

					TXT.Section.Field otherField = otherSection.addField(FieldId.valueOf(dataName));
					
					otherField.set(War3String.valueOf(displayName));
				}
			}
		}
		
		addTXT(other);
		
	}
	
	@Override
	public void print(@Nonnull Printer printer) {
		_txt.print(printer);
	}
	
	public Translator() {
		//addTXT(new TXT(new File("UI\\WorldEditStrings.txt")));
	}
}
