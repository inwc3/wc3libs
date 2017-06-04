package net.moonlightflower.wc3libs.txt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.app.WaterId;
import net.moonlightflower.wc3libs.dataTypes.app.WeatherId;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.misc.Translator;
import net.moonlightflower.wc3libs.slk.SLK;
import net.moonlightflower.wc3libs.slk.app.terrainArts.WeatherSLK;
import net.moonlightflower.wc3libs.slk.app.terrainArts.WaterSLK.Obj;
import net.moonlightflower.wc3libs.slk.app.terrainArts.WeatherSLK.States;
import net.moonlightflower.wc3libs.txt.TXT.Section;
import net.moonlightflower.wc3libs.txt.TXT.Section.Field;

/**
 * handles profile files like Units\CommandFunc.txt
 */
public class Profile extends TXT {
	public final static File[] getNativePaths() {
		return new File[]{
			new File("Units\\CampaignUnitStrings.txt"),

			new File("Units\\HumanUnitStrings.txt"),
			new File("Units\\NeutralUnitStrings.txt"),
			new File("Units\\NightElfUnitStrings.txt"),
			new File("Units\\OrcUnitStrings.txt"),
			new File("Units\\UndeadUnitStrings.txt"),

			new File("Units\\CampaignUnitFunc.txt"),
			new File("Units\\HumanUnitFunc.txt"),
			new File("Units\\NeutralUnitFunc.txt"),
			new File("Units\\NightElfUnitFunc.txt"),
			new File("Units\\OrcUnitFunc.txt"),
			new File("Units\\UndeadUnitFunc.txt"),

			new File("Units\\CampaignAbilityStrings.txt"),
			new File("Units\\CommonAbilityStrings.txt"),
			new File("Units\\HumanAbilityStrings.txt"),
			new File("Units\\NeutralAbilityStrings.txt"),
			new File("Units\\NightElfAbilityStrings.txt"),
			new File("Units\\OrcAbilityStrings.txt"),
			new File("Units\\UndeadAbilityStrings.txt"),
			new File("Units\\ItemAbilityStrings.txt"),

			new File("Units\\CampaignAbilityFunc.txt"),
			new File("Units\\CommonAbilityFunc.txt"),
			new File("Units\\HumanAbilityFunc.txt"),
			new File("Units\\NeutralAbilityFunc.txt"),
			new File("Units\\NightElfAbilityFunc.txt"),
			new File("Units\\OrcAbilityFunc.txt"),
			new File("Units\\UndeadAbilityFunc.txt"),
			new File("Units\\ItemAbilityFunc.txt"),

			new File("Units\\CampaignUpgradeStrings.txt"),
			new File("Units\\HumanUpgradeStrings.txt"),
			new File("Units\\NightElfUpgradeStrings.txt"),
			new File("Units\\OrcUpgradeStrings.txt"),
			new File("Units\\UndeadUpgradeStrings.txt"),
			//new File("Units\\NeutralUpgradeStrings.txt"),

			//new File("Units\\CampaignUpgradeFunc.txt"),
			new File("Units\\HumanUpgradeFunc.txt"),
			new File("Units\\NightElfUpgradeFunc.txt"),
			new File("Units\\OrcUpgradeFunc.txt"),
			new File("Units\\UndeadUpgradeFunc.txt"),
			new File("Units\\NeutralUpgradeFunc.txt"),

			new File("Units\\CommandStrings.txt"),
			new File("Units\\ItemStrings.txt"),

			new File("Units\\CommandFunc.txt"),
			new File("Units\\ItemFunc.txt")
		};
	}
	
	public static class Obj extends TXT.Section {		
		public class Field extends TXT.Section.Field {
			@Override
			public void merge(TXT.Section.Field otherField, boolean overwrite) {
				super.merge(otherField, overwrite);
			}
			
			@Override
			public void merge(TXT.Section.Field otherField) {
				merge(otherField, true);
			}
			
			public Field(FieldId id) {
				super(id);
			}
		}
		
		/*private Map<FieldId, Field> _fields = new HashMap<>();*/
		
		@Override
		public Map<FieldId, Field> getFields() {
			Map<FieldId, Field> ret = new HashMap<>();
			
			ret.putAll((Map<? extends FieldId, ? extends Field>) super.getFields());
			
			return ret;
		}
		
		/*@Override
		public boolean containsField(FieldId id) {
			return _fields.containsKey(id);
		}*/
		
		@Override
		public Field getField(FieldId id) {
			return (Field) _fields.get(id);
		}
		
		@Override
		public Field addField(FieldId id) {
			if (containsField(id)) return getField(id);
			//super.addField(id);

			Field field = new Field(id);

			_fields.put(id, field);
			
			return field;
		}
		
		public void merge(Obj otherObj, boolean overwrite) {
			for (Entry<FieldId, Field> fieldEntry : otherObj.getFields().entrySet()) {
				FieldId fieldId = fieldEntry.getKey();
				Field otherField = fieldEntry.getValue();
				
				Field field = addField(fieldId);
				
				field.merge(otherField, overwrite);
			}
		}
		
		public void merge(Obj otherObj) {
			merge(otherObj, true);
		}
		
		@Override
		public void print(PrintStream outStream) {
			for (Entry<FieldId, Obj.Field> fieldEntry : getFields().entrySet()) {
				FieldId fieldId = fieldEntry.getKey();
				Obj.Field field = fieldEntry.getValue();
				
				System.out.println(String.format("%s=%s", fieldId.toTXTVal().toString(), field.getValLine(null)));
			}
		}
		
		@Override
		public void print() {
			print(System.out);
		}
		
		@Override
		public void write(BufferedWriter writer, Translator translator) throws IOException {
			if (getId() != null) {
				writer.write(String.format("[%s]", getId().toString()));
				
				writer.newLine();
			}
			
			for (Map.Entry<FieldId, ? extends Obj.Field> fieldEntry : getFields().entrySet()) {
				FieldId fieldId = fieldEntry.getKey();
				Field field = fieldEntry.getValue();

				String valLine = field.getValLine(translator);

				writer.write(String.format("%s=%s", fieldId.toString(), valLine));
				
				writer.newLine();
			}
		}
		
		@Override
		public void merge(TXT.Section otherSection, boolean overwrite) {			
			for (Map.Entry<FieldId, ? extends TXT.Section.Field> fieldEntry : otherSection.getFields().entrySet()) {
				FieldId fieldId = fieldEntry.getKey();
				TXT.Section.Field otherField = fieldEntry.getValue();
				
				Field field = addField(fieldId);
				
				field.merge(otherField, overwrite);
			}
		}
		
		private void read(TXT.Section txtSection) {
			merge(txtSection, true);
		}
		
		public Obj(TXT.Section txtSection) {
			this(txtSection.getId());
			
			read(txtSection);
		}
		
		public Obj(TXTSectionId id) {
			super(id);
		}
	}
	
	private final Map<TXTSectionId, Obj> _objs = new HashMap<>();
	
	public Map<TXTSectionId, Obj> getObjs() {
		return _objs;
	}
	
	public boolean containsObj(TXTSectionId id) {
		return _objs.containsKey(id);
	}
	
	public Obj getObj(TXTSectionId id) {
		return _objs.get(id);
	}
	
	private void addObj(Obj obj) {
		_objs.put(obj.getId(), obj);
		addSection(TXTSectionId.valueOf(obj.getId().toString()));
	}
	
	public Obj addObj(TXTSectionId id) {
		if (containsObj(id)) return getObj(id);
		
		Obj obj = new Obj(id);
		
		addObj(obj);
		
		return obj;
	}

	public void removeObj(Obj obj) {
		if (!containsObj(obj.getId())) return;
		
		_objs.remove(obj.getId());
	}
	
	public void removeObj(TXTSectionId id) {
		if (containsObj(id)) removeObj(getObj(id));
	}
	
	public void clearObjs() {
		_objs.clear();
	}
	
	public void merge(Profile otherProfile, boolean overwrite) {
		for (Entry<TXTSectionId, Obj> objEntry : otherProfile.getObjs().entrySet()) {
			TXTSectionId id = objEntry.getKey();
			Obj otherObj = objEntry.getValue();
			
			Obj obj = addObj(id);
			
			obj.merge(otherObj, overwrite);
		}
	}
	
	public void merge(Profile otherProfile) {
		merge(otherProfile, true);
	}
	
	private Translator _translator;
	
	public Translator getTranslator() {
		return _translator;
	}
	
	public void setTranslator(Translator val) {
		_translator = val;
	}
	
	@Override
	public void write(File file) throws IOException {
		super.write(file, _translator);
	}
	
	@Override
	public void print() {
		for (Entry<TXTSectionId, Obj> objEntry : getObjs().entrySet()) {
			TXTSectionId objId = objEntry.getKey();
			Obj obj = objEntry.getValue();
			
			System.out.println(String.format("[%s]", objId.toString()));
			
			obj.print();
		}
	}
	
	@Override
	public void write(BufferedWriter writer, Translator translator) throws IOException {		
		for (Entry<TXTSectionId, Obj> objEntry : getObjs().entrySet()) {
			TXTSectionId objId = objEntry.getKey();
			Obj obj = objEntry.getValue();

			obj.write(writer, translator);
		}
	}
	
	@Override
	public void read(InputStream inStream) throws IOException {
		super.read(inStream);
	}

	protected void read(TXT txt) {
		Profile other = new Profile();
		
		for (Entry<? extends TXTSectionId, ? extends TXT.Section> slkEntry : txt.getSections().entrySet()) {
			TXTSectionId id = slkEntry.getKey();
			TXT.Section txtSection = slkEntry.getValue();

			Obj otherObj = new Obj(txtSection);
			
			other.addObj(otherObj);
		}
		
		merge(other, true);
	}
	
	public Profile(File file) throws IOException {
		read(new TXT(file));
	}
	
	public Profile() {
	}
}