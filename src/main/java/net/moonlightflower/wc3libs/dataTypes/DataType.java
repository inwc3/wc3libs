package net.moonlightflower.wc3libs.dataTypes;

import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.TypeInfoed;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class DataType implements Serializable, TypeInfoed {
	public abstract DataType decode(Object val) throws DataTypeInfo.CastException;

	@Nonnull
    @Override
	public DataTypeInfo getTypeInfo() {
		return new DataTypeInfo(getClass());
	}
	
	@Override
	public abstract Object toSLKVal();
	@Override
	public abstract Object toTXTVal();
	
	private final static Map<String, DataTypeInfo> _typeInfoMap = new LinkedHashMap<>();

	@Nullable
	public static DataTypeInfo getTypeInfoFromName(@Nonnull String name) {
		return _typeInfoMap.get(name);
	}
	
	public static void regClass(@Nonnull String name, @Nonnull DataTypeInfo typeInfo) {
		_typeInfoMap.put(name, typeInfo);
	}
	
	public static void regClass(@Nonnull String name, @Nonnull Class<? extends DataType> type) {
		regClass(name, new DataTypeInfo(type));
	}
	
	protected DataType() {
	}
	
	static {
		regClass("abilCode", AbilId.class);
		regClass("abilityList", new DataTypeInfo(DataList.class, AbilId.class));
		regClass("aiBuffer", AIPlaceType.class);
		regClass("alphaMode", AlphaMode.class);
		regClass("armorType", ArmorSound.class);
		regClass("attackBits", AttackBits.class);
		regClass("attackTable", AttackTable.class);
		regClass("attackType", AttackType.class);
		regClass("attributeType", AttributeType.class);
		regClass("blendMode", BlendMode.class);
		regClass("bool", War3Bool.class);
		regClass("buffCode", BuffId.class);
		regClass("char", War3Char.class);
		regClass("combatSound", CombatSound.class);
		regClass("combatTarget", CombatTarget.class);
		//regClass("controller", Controller.class);
		regClass("deathType", DeathType.class);
		regClass("defenseType", DefType.class);
		regClass("destructableCategory", DestructableClass.class);
		regClass("doodadCategory", DoodadClass.class);
		regClass("effectList", new DataTypeInfo(DataList.class, Effect.class));
		regClass("heroAbilityList", new DataTypeInfo(DataList.class, AbilId.class));
		regClass("icon", Icon.class);
		regClass("int", War3Int.class);
		regClass("intList", new DataTypeInfo(DataList.class, War3Int.class));
		regClass("itemClass", ItemClass.class);
		regClass("itemList", new DataTypeInfo(DataList.class, ItemId.class));
		regClass("lightning", LightningId.class);
		regClass("lightningList", new DataTypeInfo(DataList.class, LightningId.class));
		regClass("model", Model.class);
		regClass("modelList", new DataTypeInfo(DataList.class, Model.class));
		regClass("moveType", MoveType.class);
		regClass("musicFile", MusicFile.class);
		regClass("orderString", OrderString.class);
		regClass("pathingListPrevent", new DataTypeInfo(DataList.class, PathingPrevent.class));
		regClass("pathingListRequire", new DataTypeInfo(DataList.class, PathingRequire.class));
		regClass("pathingTexture", PathingTex.class);
		regClass("playerColor", PlayerColor.class);
		regClass("real", War3Real.class);
		regClass("realList", new DataTypeInfo(DataList.class, War3Real.class));
		regClass("regenType", RegenType.class);
		regClass("requireList", new DataTypeInfo(DataList.class, Req.class));
		regClass("shadowImage", ShadowImage.class);
		regClass("shadowTexture", ShadowTex.class);
		regClass("soundLabel", SoundLabel.class);
		regClass("spellDetail", SpellDetail.class);
		regClass("string", War3String.class);
		regClass("stringList", new DataTypeInfo(DataList.class, War3String.class));
		regClass("targetList", new DataTypeInfo(DataList.class, CombatTarget.class));
		regClass("teamColor", TeamColor.class);
		regClass("techList", new DataTypeInfo(DataList.class, TechId.class));
		regClass("terrainFogType", TerrainFogType.class);
		regClass("texture", Tex.class);
		regClass("tileId", TileId.class);
		regClass("tileIdList", new DataTypeInfo(DataList.class, TileId.class));
		regClass("tilesetList", new DataTypeInfo(DataList.class, Tileset.class));
		regClass("uberSplat", UberSplatId.class);
		regClass("unitClass", UnitClass.class);
		regClass("unitId", UnitId.class);
		regClass("unitList", new DataTypeInfo(DataList.class, UnitId.class));
		regClass("unitRace", UnitRace.class);
		regClass("unitSound", UnitSound.class);
		regClass("unreal", War3Real.class);
		regClass("upgradeClass", UpgradeClass.class);
		regClass("upgradeEffect", UpgradeEffect.class);
		regClass("upgradeList", new DataTypeInfo(DataList.class, UpgradeId.class));
		regClass("versionFlags", VersionFlags.class);
		regClass("waterCode", WaterCode.class);
		regClass("weaponType", WeaponType.class);
	}
}
