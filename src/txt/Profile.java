package txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import dataTypes.DataType;
import dataTypes.app.WaterId;
import dataTypes.app.WeatherId;
import misc.FieldId;
import misc.ObjId;
import misc.Translator;
import slk.SLK;
import slk.app.terrainArts.WeatherSLK;
import slk.app.terrainArts.WaterSLK.Obj;
import slk.app.terrainArts.WeatherSLK.States;

/**
 * handles profile files like Units\CommandFunc.txt
 */
public class Profile extends TXT {
	public static String[] getNativePaths() {
		return new String[]{
			"Units\\CampaignUnitStrings.txt",

			"Units\\HumanUnitStrings.txt",
			"Units\\NeutralUnitStrings.txt",
			"Units\\NightElfUnitStrings.txt",
			"Units\\OrcUnitStrings.txt",
			"Units\\UndeadUnitStrings.txt",

			"Units\\CampaignUnitFunc.txt",
			"Units\\HumanUnitFunc.txt",
			"Units\\NeutralUnitFunc.txt",
			"Units\\NightElfUnitFunc.txt",
			"Units\\OrcUnitFunc.txt",
			"Units\\UndeadUnitFunc.txt",

			"Units\\CampaignAbilityStrings.txt",
			"Units\\CommonAbilityStrings.txt",
			"Units\\HumanAbilityStrings.txt",
			"Units\\NeutralAbilityStrings.txt",
			"Units\\NightElfAbilityStrings.txt",
			"Units\\OrcAbilityStrings.txt",
			"Units\\UndeadAbilityStrings.txt",
			"Units\\ItemAbilityStrings.txt",

			"Units\\CampaignAbilityFunc.txt",
			"Units\\CommonAbilityFunc.txt",
			"Units\\HumanAbilityFunc.txt",
			"Units\\NeutralAbilityFunc.txt",
			"Units\\NightElfAbilityFunc.txt",
			"Units\\OrcAbilityFunc.txt",
			"Units\\UndeadAbilityFunc.txt",
			"Units\\ItemAbilityFunc.txt",

			"Units\\CampaignUpgradeStrings.txt",
			"Units\\HumanUpgradeStrings.txt",
			"Units\\NightElfUpgradeStrings.txt",
			"Units\\OrcUpgradeStrings.txt",
			"Units\\UndeadUpgradeStrings.txt",
			//"Units\\NeutralUpgradeStrings.txt",

			//"Units\\CampaignUpgradeFunc.txt",
			"Units\\HumanUpgradeFunc.txt",
			"Units\\NightElfUpgradeFunc.txt",
			"Units\\OrcUpgradeFunc.txt",
			"Units\\UndeadUpgradeFunc.txt",
			"Units\\NeutralUpgradeFunc.txt",

			"Units\\CommandStrings.txt",
			"Units\\ItemStrings.txt",

			"Units\\CommandFunc.txt",
			"Units\\ItemFunc.txt"
		};
	}
	
	public static class Obj extends TXT.Section {		
		public class Field extends TXT.Section.Field {
			@Override
			public void merge(TXT.Section.Field otherField, boolean overwrite) {
				for (DataType val : otherField.getVals()) {
					add(val);
				}
			}
			
			public Field(FieldId id) {
				super(id);
			}
		}
		
		private Map<FieldId, Field> _fields = new HashMap<>();
		
		@Override
		public Map<FieldId, Field> getFields() {
			return _fields;
		}
		
		@Override
		public boolean containsField(FieldId id) {
			return _fields.containsKey(id);
		}
		
		@Override
		public Field getField(FieldId id) {
			return _fields.get(id);
		}
		
		@Override
		public Field addField(FieldId id) {
			if (containsField(id)) return getField(id); 

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
		
		public void print() {
			for (Entry<FieldId, Obj.Field> fieldEntry : getFields().entrySet()) {
				FieldId fieldId = fieldEntry.getKey();
				Obj.Field field = fieldEntry.getValue();
				
				for (int i = 0; i < field.getVals().size(); i++) {
					Object val = field.get(i);
					
					String valS = (val == null) ? "" : val.toString();
					
					System.out.println(String.format("%s=%s", fieldId.toTXTVal().toString(), valS));
				}
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
	
	private Map<TXTSectionId, Obj> _objs = new HashMap<>();
	
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
	}
	
	public Obj addObj(TXTSectionId id) {
		if (containsObj(id)) return getObj(id);
		
		Obj obj = new Obj(id);
		
		addObj(obj);
		
		return obj;
	}

	public void merge(Profile otherProfile, boolean overwrite) {
		for (Entry<TXTSectionId, Obj> objEntry : otherProfile.getObjs().entrySet()) {
			TXTSectionId id = objEntry.getKey();
			Obj otherObj = objEntry.getValue();
			
			Obj obj = addObj(id);
			
			obj.merge(otherObj, overwrite);
		}
	}
	
	private Translator _translator;
	
	public void setTranslator(Translator val) {
		_translator = val;
	}
	
	public void print() {
		for (Entry<TXTSectionId, Obj> objEntry : getObjs().entrySet()) {
			TXTSectionId objId = objEntry.getKey();
			Obj obj = objEntry.getValue();
			
			System.out.println(String.format("[%s]", objId.toString()));
			
			obj.print();
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