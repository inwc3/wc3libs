package net.moonlightflower.wc3libs.slk;

import java.io.File;
import java.io.IOException;

import net.moonlightflower.wc3libs.dataTypes.app.Wc3Int;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.MetaFieldId;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.app.doodads.DoodSLK;
import net.moonlightflower.wc3libs.slk.app.objs.*;

import javax.annotation.Nonnull;

public abstract class MetaSLK<Self extends SLK<Self, ObjIdType, ObjType>, ObjIdType extends ObjId, ObjType extends SLK.Obj<? extends ObjIdType>> extends SLK<Self, ObjIdType, ObjType> {
	public static File convertSLKName(@Nonnull String slkName) {
		//convert slk names
		switch (slkName) {
			case "AbilityData":
				return AbilSLK.GAME_USE_PATH;
			case "AbilityBuffData":
				return BuffSLK.GAME_USE_PATH;
			case "DestructableData":
				return DestructableSLK.GAME_USE_PATH;
			case "DoodadData":
				return DoodSLK.GAME_USE_PATH;
			case "ItemData":
				return ItemSLK.GAME_USE_PATH;
			case "UnitAbilities":
				return UnitAbilsSLK.GAME_USE_PATH;
			case "UnitBalance":
				return UnitBalanceSLK.GAME_USE_PATH;
			case "UnitData":
				return UnitDataSLK.GAME_USE_PATH;
			case "UnitUI":
				return UnitUISLK.GAME_USE_PATH;
			case "UnitWeapons":
				return UnitWeaponsSLK.GAME_USE_PATH;
			case "UpgradeData":
				return UpgradeSLK.GAME_USE_PATH;
		}

		return null;
	}

	public static Integer getDataI(MetaSLK.Obj metaObj) {
		Integer repeatI = null;

		try {
			Wc3Int repeatVal = Wc3Int.valueOf(metaObj.get(FieldId.valueOf("data")));

			if (repeatVal != null) repeatI = repeatVal.toInt();
		} catch (Exception ignored) {
		}

		return repeatI;
	}

	public static Integer getRepeatI(MetaSLK.Obj metaObj) {
		Integer repeatI = null;

		try {
			Wc3Int repeatVal = Wc3Int.valueOf(metaObj.get(FieldId.valueOf("repeat")));

			if (repeatVal != null) repeatI = repeatVal.toInt();
		} catch (Exception ignored) {
		}

		return repeatI;
	}

	//data pts are used by abilities, a value of 1 maps to DataA, 2 to DataB, 3 to DataC and so on
	//the ability slk defines up to DataI, hence 9 is the max
	public final static int DATA_PT_MIN = 1;
	public final static int DATA_PT_MAX = 9;

	public static int getSLKFieldRepeatPlaces(File slkFile, int repeat) {
		int places = 1;

		if (slkFile.equals(DoodSLK.GAME_USE_PATH)) {
			if (repeat > 10) return -1;

			places = 2;
		} else {
			if (repeat > 4) return -1;

			places = 1;
		}

		return places;
	}

	public static FieldId getSLKField(File slkFile, MetaSLK.Obj metaObj, Integer level) {
		FieldId retFieldId = FieldId.valueOf(metaObj.getS(FieldId.valueOf("field")));

		if (retFieldId.toString().equals("Data")) {
			Integer dataPt = getDataI(metaObj);

			if (dataPt < DATA_PT_MIN) throw new RuntimeException("dataPt < " + DATA_PT_MIN);
			if (dataPt > DATA_PT_MAX) throw new RuntimeException("dataPt > " + DATA_PT_MAX);

			retFieldId = FieldId.valueOf(retFieldId.toString() + (char) ('A' + dataPt - 1));
		}

		Integer repeatI = getRepeatI(metaObj);

		if ((repeatI != null) && (repeatI > 0) && level != null) {
			int places = getSLKFieldRepeatPlaces(slkFile, repeatI);

			if (places > -1) {
				StringBuilder add = new StringBuilder(Integer.toString(level));

				while (add.length() < places) {
					add.insert(0, "0");
				}

				retFieldId = FieldId.valueOf(retFieldId.toString() + add);
			}
		}

		return retFieldId;
	}

	public MetaSLK(@Nonnull File file) throws IOException {
		super(file);
	}
	
	public MetaSLK() {
		super();
	}
}
