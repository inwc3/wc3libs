package net.moonlightflower.wc3libs.txt;

import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.Printable;
import net.moonlightflower.wc3libs.misc.Translator;
import net.moonlightflower.wc3libs.txt.app.profile.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * handles profile files like Units\CommandFunc.txt
 */
public class Profile extends TXT implements Printable {
	public Profile() {
	}

	public static class UnitState<T extends DataType> extends TXTState<T> {
		public final static UnitState<War3String> ART_ANIM_PROPS = new UnitState<>("animProps", new DataTypeInfo(DataList.class, War3String.class));
		public final static UnitState<Icon> ART_ICON = new UnitState<>("Art", Icon.class);
		public final static UnitState<War3String> ART_ATTACHMENT_ANIM_PROPS = new UnitState<>("Attachmentanimprops", new DataTypeInfo(DataList.class, War3String.class));
		public final static UnitState<War3String> ART_ATTACHMENT_LINKS_PROPS = new UnitState<>("Attachmentlinkprops", new DataTypeInfo(DataList.class, War3String.class));
		public final static UnitState<War3String> ART_AWAKEN_TIP = new UnitState<>("Awakentip", War3String.class);
		public final static UnitState<War3String> ART_BONE_PROPS = new UnitState<>("Boneprops", new DataTypeInfo(DataList.class, War3String.class));
		public final static UnitState<SoundLabel> ART_BUILDING_SOUND_LABEL = new UnitState<>("BuildingSoundLabel", SoundLabel.class);

		public final static UnitState<War3String> TECH_BUILDS = new UnitState<>("BuildingSoundLabel", new DataTypeInfo(DataList.class, UnitId.class));

		public final static UnitState<War3String> ART_BUTTON_POS = new UnitState<>("Buttonpos", new DataTypeInfo(DataList.class, War3Int.class));

		public final static UnitState<Icon> ART_CASTER_UPGRADE = new UnitState<>("Casterupgradeart", Icon.class);
		public final static UnitState<War3String> ART_CASTER_UPGRADE_NAME = new UnitState<>("Casterupgradeart", new DataTypeInfo(DataList.class, War3String.class));
		public final static UnitState<War3String> ART_CASTER_UPGRADE_TIP = new UnitState<>("Casterupgradetip", new DataTypeInfo(DataList.class, War3String.class));

		public final static UnitState<War3Bool> TECH_DEPENDENCY_OR = new UnitState<>("DependencyOr", War3Bool.class);

		public final static UnitState<War3String> TEXT_DESCRIPTION = new UnitState<>("Description", War3String.class);

		public final static UnitState<War3String> EDITOR_SUFFIX = new UnitState<>("EditorSuffix", War3String.class);
		public final static UnitState<War3String> EDITOR_NAME = new UnitState<>("EditorName", War3String.class);

		public final static UnitState<War3Char> TEXT_HOTKEY = new UnitState<>("Hotkey", War3Char.class);

		public final static UnitState<War3Int> SOUND_LOOPING_FADE_IN = new UnitState<>("LoopingSoundFadeIn", War3Int.class);
		public final static UnitState<War3Int> SOUND_LOOPING_FADE_OUT = new UnitState<>("LoopingSoundFadeOut", War3Int.class);

		public final static UnitState<War3String> TECH_MAKE_ITEMS = new UnitState<>("Makeitems", new DataTypeInfo(DataList.class, ItemId.class));

		public final static UnitState<War3String> COMBAT_MISSILE_ARCS = new UnitState<>("Missilearc", new DataTypeInfo(DataList.class, War3Real.class));

		public final static UnitState<War3String> COMBAT_MISSILE_ARTS = new UnitState<>("Missileart", new DataTypeInfo(DataList.class, Model.class));

		public final static UnitState<War3String> COMBAT_MISSILE_HOMINGS = new UnitState<>("MissileHoming", new DataTypeInfo(DataList.class, War3Bool.class));
		public final static UnitState<War3String> COMBAT_MISSILE_SPEEDS = new UnitState<>("Missilespeed", new DataTypeInfo(DataList.class, War3Int.class));

		public final static UnitState<SoundLabel> SOUND_MOVEMENT = new UnitState<>("MovementSoundLabel", SoundLabel.class);
		public final static UnitState<War3String> TEXT_NAME = new UnitState<>("Name", War3String.class);

		public final static UnitState<War3String> TEXT_PROPER_NAMES = new UnitState<>("Propernames", new DataTypeInfo(DataList.class, War3String.class));

		public final static UnitState<SoundLabel> SOUND_RANDOM = new UnitState<>("RandomSoundLabel", SoundLabel.class);

		public final static UnitState<War3Int> TECH_REQUIRES_COUNT = new UnitState<>("Requirescount", War3Int.class);

		public final static UnitState<War3String> TECH_REQUIRES = new UnitState<>("Requires", new DataTypeInfo(DataList.class, TechId.class));
		public final static UnitState<War3String> TECH_REQUIRES1 = new UnitState<>("Requires1", new DataTypeInfo(DataList.class, TechId.class));
		public final static UnitState<War3String> TECH_REQUIRES2 = new UnitState<>("Requires2", new DataTypeInfo(DataList.class, TechId.class));
		public final static UnitState<War3String> TECH_REQUIRES3 = new UnitState<>("Requires3", new DataTypeInfo(DataList.class, TechId.class));
		public final static UnitState<War3String> TECH_REQUIRES4 = new UnitState<>("Requires4", new DataTypeInfo(DataList.class, TechId.class));
		public final static UnitState<War3String> TECH_REQUIRES5 = new UnitState<>("Requires5", new DataTypeInfo(DataList.class, TechId.class));
		public final static UnitState<War3String> TECH_REQUIRES6 = new UnitState<>("Requires6", new DataTypeInfo(DataList.class, TechId.class));
		public final static UnitState<War3String> TECH_REQUIRES7 = new UnitState<>("Requires7", new DataTypeInfo(DataList.class, TechId.class));
		public final static UnitState<War3String> TECH_REQUIRES8 = new UnitState<>("Requires8", new DataTypeInfo(DataList.class, TechId.class));
		public final static UnitState<War3String> TECH_REQUIRES_AMOUNT = new UnitState<>("Requiresamount", new DataTypeInfo(DataList.class, War3Int.class));

		public final static UnitState<War3String> TECH_RESEARCHES = new UnitState<>("Requiresamount", new DataTypeInfo(DataList.class, UpgradeId.class));

		public final static UnitState<War3Bool> TECH_REVIVE = new UnitState<>("Revive", War3Bool.class);
		public final static UnitState<War3String> TECH_REVIVE_TIP = new UnitState<>("Revivetip", War3String.class);

		public final static UnitState<Icon> ART_SCORE_SCREEN_ICON = new UnitState<>("ScoreScreenIcon", Icon.class);
		public final static UnitState<War3String> TECH_SELL_ITEMS = new UnitState<>("Sellitems", new DataTypeInfo(DataList.class, ItemId.class));
		public final static UnitState<War3String> TECH_SELL_UNITS = new UnitState<>("Sellunits", new DataTypeInfo(DataList.class, UnitId.class));

		public final static UnitState<War3String> ART_SPECIAL = new UnitState<>("Specialart", new DataTypeInfo(DataList.class, Model.class));
		public final static UnitState<War3String> ART_TARGET = new UnitState<>("Targetart", new DataTypeInfo(DataList.class, Model.class));

		public final static UnitState<War3String> TEXT_TIP = new UnitState<>("Tip", War3String.class);

		public final static UnitState<War3String> TECH_TRAINS = new UnitState<>("Trains", new DataTypeInfo(DataList.class, UnitId.class));
		public final static UnitState<War3String> TECH_REVIVE_AT = new UnitState<>("Reviveat", new DataTypeInfo(DataList.class, UnitId.class));

		public final static UnitState<War3String> TEXT_UBER_TIP = new UnitState<>("Ubertip", War3String.class);

		public final static UnitState<War3String> TECH_UPGRADE = new UnitState<>("Upgrade", new DataTypeInfo(DataList.class, UnitId.class));

		public UnitState(@Nonnull String fieldIdS, @Nonnull DataTypeInfo typeInfo, @Nullable T defVal) {
			super(fieldIdS, typeInfo, defVal);
		}

		public UnitState(@Nonnull String fieldIdS, @Nonnull DataTypeInfo typeInfo) {
			super(fieldIdS, typeInfo);
		}

		public UnitState(@Nonnull String fieldIdS, @Nonnull Class<T> type) {
			super(fieldIdS, type);
		}

		public UnitState(@Nonnull String fieldIdS, @Nonnull Class<T> type, @Nullable T defVal) {
			super(fieldIdS, type, defVal);
		}
	}

	public static File[] getNativePaths() {
		return new File[]{
				CampaignUnitStrings.GAME_PATH,

				HumanUnitStrings.GAME_PATH,
				NeutralUnitStrings.GAME_PATH,
				NightElfUnitStrings.GAME_PATH,
				OrcUnitStrings.GAME_PATH,
				UndeadUnitStrings.GAME_PATH,

				CampaignUnitFunc.GAME_PATH,
				HumanUnitFunc.GAME_PATH,
				NeutralUnitFunc.GAME_PATH,
				NightElfUnitFunc.GAME_PATH,
				OrcUnitFunc.GAME_PATH,
				UndeadUnitFunc.GAME_PATH,

				CampaignAbilityStrings.GAME_PATH,
				CommonAbilityStrings.GAME_PATH,
				HumanAbilityStrings.GAME_PATH,
				NeutralAbilityStrings.GAME_PATH,
				NightElfAbilityStrings.GAME_PATH,
				OrcAbilityStrings.GAME_PATH,
				UndeadAbilityStrings.GAME_PATH,
				ItemAbilityStrings.GAME_PATH,

				CampaignAbilityFunc.GAME_PATH,
				CommonAbilityFunc.GAME_PATH,
				HumanAbilityFunc.GAME_PATH,
				NeutralAbilityFunc.GAME_PATH,
				NightElfAbilityFunc.GAME_PATH,
				OrcAbilityFunc.GAME_PATH,
				UndeadAbilityFunc.GAME_PATH,
				ItemAbilityFunc.GAME_PATH,

				CampaignUpgradeStrings.GAME_PATH,
				HumanUpgradeStrings.GAME_PATH,
				NightElfUpgradeStrings.GAME_PATH,
				OrcUpgradeStrings.GAME_PATH,
				UndeadUpgradeStrings.GAME_PATH,
				//NeutralUpgradeStrings.GAME_PATH,

				//CampaignUpgradeFunc.GAME_PATH,
				HumanUpgradeFunc.GAME_PATH,
				NightElfUpgradeFunc.GAME_PATH,
				OrcUpgradeFunc.GAME_PATH,
				UndeadUpgradeFunc.GAME_PATH,
				NeutralUpgradeFunc.GAME_PATH,

				CommandStrings.GAME_PATH,
				ItemStrings.GAME_PATH,

				CommandFunc.GAME_PATH,
				ItemFunc.GAME_PATH
		};
	}
	
	public static class Obj extends TXT.Section {		
		public class Field extends TXT.Section.Field {
			@Override
			public void merge(@Nonnull TXT.Section.Field otherField, boolean overwrite) {
				super.merge(otherField, overwrite);
			}
			
			@Override
			public void merge(@Nonnull TXT.Section.Field otherField) {
				merge(otherField, true);
			}
			
			public Field(@Nonnull FieldId id) {
				super(id);
			}
		}
		
		/*private Map<FieldId, Field> _fields = new LinkedHashMap<>();*/
		
		@Override
		@Nonnull
		public Map<FieldId, Field> getFields() {
			Map<FieldId, Field> ret = new LinkedHashMap<>();
			
			ret.putAll((Map<? extends FieldId, ? extends Field>) super.getFields());
			
			return ret;
		}
		
		/*@Override
		public boolean containsField(FieldId id) {
			return _fields.containsKey(id);
		}*/

		@Override
		public boolean containsField(@Nonnull FieldId id) {
			return super.containsField(id);
		}

		@Override
		public void removeField(@Nonnull FieldId id) {
			super.removeField(id);
		}

		@Override
		@Nullable
		public Field getField(@Nonnull FieldId id) throws FieldDoesNotExistException {
			return (Field) super.getField(id);
		}
		
		@Override
		public Field addField(@Nonnull FieldId id) {
			if (containsField(id)) {
				try {
					return getField(id);
				} catch (FieldDoesNotExistException ignored) {
				}
			}
			//super.addField(id);

			Field field = new Field(id);

			super.addField(field);
			
			return field;
		}
		
		public void merge(@Nonnull Obj otherObj, boolean overwrite) {
			for (Entry<FieldId, Field> fieldEntry : otherObj.getFields().entrySet()) {
				FieldId fieldId = fieldEntry.getKey();
				Field otherField = fieldEntry.getValue();
				
				Field field = addField(fieldId);

				field.merge(otherField, overwrite);
			}
		}
		
		public void merge(@Nonnull Obj otherObj) {
			merge(otherObj, true);
		}
		
		@Override
		public void print(@Nonnull PrintStream outStream) {
			for (Entry<FieldId, Obj.Field> fieldEntry : getFields().entrySet()) {
				FieldId fieldId = fieldEntry.getKey();
				Obj.Field field = fieldEntry.getValue();

				System.out.println(String.format("%s=%s", fieldId.toTXTVal().toString(), field.getValLine(null)));
				
				/*for (int i = 0; i < field.getVals().size(); i++) {
					Object val = field.get(i);
					
					String valS = (val == null) ? "" : val.toString();
					
					System.out.println(String.format("%s=%s", fieldId.toTXTVal().toString(), valS));
				}*/
			}
		}
		
		@Override
		public void write(@Nonnull BufferedWriter writer, @Nullable Translator translator) throws IOException {
			if (getId() != null) {
				writer.write(String.format("[%s]", getId().toString()));
				
				writer.newLine();
			}
			
			for (Map.Entry<FieldId, ? extends Obj.Field> fieldEntry : getFields().entrySet()) {
				FieldId fieldId = fieldEntry.getKey();
				Field field = fieldEntry.getValue();

				String valLine = field.getValLine(translator);
				
				if (valLine.equals("\"\"")) continue;
				
				writer.write(String.format("%s=%s", fieldId.toString(), valLine));
				
				writer.newLine();
			}
		}
		
		@Override
		public void merge(@Nonnull TXT.Section otherSection, boolean overwrite) {
			for (Map.Entry<FieldId, ? extends TXT.Section.Field> fieldEntry : otherSection.getFields().entrySet()) {
				FieldId fieldId = fieldEntry.getKey();
				TXT.Section.Field otherField = fieldEntry.getValue();
				
				Field field = addField(fieldId);
				
				field.merge(otherField, overwrite);
			}
		}
		
		private void read(@Nonnull TXT.Section txtSection) {
			merge(txtSection, true);
		}
		
		public Obj(@Nonnull TXT.Section txtSection) {
			this(txtSection.getId());
			
			read(txtSection);
		}
		
		public Obj(@Nonnull TXTSectionId id) {
			super(id);
		}
	}
	
	private final Map<TXTSectionId, Obj> _objs = new LinkedHashMap<>();

	@Nonnull
	public Map<TXTSectionId, Obj> getObjs() {
		return _objs;
	}
	
	public boolean containsObj(@Nonnull TXTSectionId id) {
		return _objs.containsKey(id);
	}
	
	public Obj getObj(@Nonnull TXTSectionId id) {
		return _objs.get(id);
	}
	
	private void addObj(@Nonnull Obj obj) {
		_objs.put(obj.getId(), obj);
		addSection(TXTSectionId.valueOf(obj.getId().toString()));
	}

	@Nonnull
	public Obj addObj(@Nonnull TXTSectionId id) {
		if (containsObj(id)) return getObj(id);
		
		Obj obj = new Obj(id);
		
		addObj(obj);
		
		return obj;
	}

	public void removeObj(@Nonnull Obj obj) {
		if (!containsObj(obj.getId())) return;
		
		_objs.remove(obj.getId());
	}
	
	public void removeObj(@Nonnull TXTSectionId id) {
		if (containsObj(id)) removeObj(getObj(id));
	}
	
	public void clearObjs() {
		_objs.clear();
	}
	
	public void merge(@Nonnull Profile otherProfile, boolean overwrite) {
		for (Entry<TXTSectionId, Obj> objEntry : otherProfile.getObjs().entrySet()) {
			TXTSectionId id = objEntry.getKey();
			Obj otherObj = objEntry.getValue();
			
			Obj obj = addObj(id);
			
			obj.merge(otherObj, overwrite);
		}
	}
	
	public void merge(@Nonnull Profile otherProfile) {
		merge(otherProfile, true);
	}
	
	private Translator _translator;

	@Nullable
	public Translator getTranslator() {
		return _translator;
	}
	
	public void setTranslator(@Nullable Translator val) {
		_translator = val;
	}
	
	@Override
	public void write(@Nonnull File file) throws IOException {
		super.write(file, _translator);
	}
	
	@Override
	public void write(@Nonnull BufferedWriter writer, @Nullable Translator translator) throws IOException {
		for (Entry<TXTSectionId, Obj> objEntry : getObjs().entrySet()) {
			TXTSectionId objId = objEntry.getKey();
			Obj obj = objEntry.getValue();

			obj.write(writer, translator);
		}
	}
	
	@Override
	public void read(@Nonnull InputStream inStream) throws IOException {
		super.read(inStream);
	}

	protected void read(@Nonnull TXT txt) {
		Profile other = new Profile();
		
		for (Entry<? extends TXTSectionId, ? extends TXT.Section> slkEntry : txt.getSections().entrySet()) {
			TXTSectionId id = slkEntry.getKey();
			TXT.Section txtSection = slkEntry.getValue();

			Obj otherObj = new Obj(txtSection);
			
			other.addObj(otherObj);
		}
		
		merge(other, true);
	}
	
	public Profile(@Nonnull File file) throws IOException {
		read(new TXT(file));
	}
}