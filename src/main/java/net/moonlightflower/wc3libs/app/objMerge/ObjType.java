package net.moonlightflower.wc3libs.app.objMerge;

import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.bin.app.objMod.W3A;
import net.moonlightflower.wc3libs.bin.app.objMod.W3B;
import net.moonlightflower.wc3libs.bin.app.objMod.W3D;
import net.moonlightflower.wc3libs.bin.app.objMod.W3H;
import net.moonlightflower.wc3libs.bin.app.objMod.W3Q;
import net.moonlightflower.wc3libs.bin.app.objMod.W3T;
import net.moonlightflower.wc3libs.bin.app.objMod.W3U;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * An object category: the object-modification file that describes it, the
 * metadata that maps its fields, and the SLK tables it lives in.
 */
public enum ObjType {
    UNIT(W3U.class, W3U.GAME_PATH, W3U.SKIN_PATH, "Units\\UnitMetaData.slk",
        "Units\\UnitData.slk", "Units\\UnitBalance.slk", "Units\\UnitUI.slk", "Units\\UnitWeapons.slk", "Units\\UnitAbilities.slk"),
    ITEM(W3T.class, W3T.GAME_PATH, W3T.SKIN_PATH, "Units\\UnitMetaData.slk",
        "Units\\ItemData.slk"),
    DESTRUCTABLE(W3B.class, W3B.GAME_PATH, W3B.SKIN_PATH, "Units\\DestructableMetaData.slk",
        "Units\\DestructableData.slk"),
    ABILITY(W3A.class, W3A.GAME_PATH, W3A.SKIN_PATH, "Units\\AbilityMetaData.slk",
        "Units\\AbilityData.slk"),
    BUFF(W3H.class, W3H.GAME_PATH, W3H.SKIN_PATH, "Units\\AbilityBuffMetaData.slk",
        "Units\\AbilityBuffData.slk"),
    UPGRADE(W3Q.class, W3Q.GAME_PATH, W3Q.SKIN_PATH, "Units\\UpgradeMetaData.slk",
        "Units\\UpgradeData.slk"),
    /**
     * Doodads are not merged -- their variations do not map onto SLK columns
     * the way levels do -- but their ids still occupy the shared namespace.
     */
    DOODAD(W3D.class, W3D.GAME_PATH, W3D.SKIN_PATH, "Doodads\\DoodadMetaData.slk",
        "Doodads\\Doodads.slk");

    private final Class<? extends ObjMod> _objModClass;
    private final File _objModPath;
    private final File _skinPath;
    private final String _metaPath;
    private final List<String> _tables;

    ObjType(@Nonnull Class<? extends ObjMod> objModClass, @Nonnull File objModPath, @Nonnull File skinPath,
            @Nonnull String metaPath, @Nonnull String... tables) {
        _objModClass = objModClass;
        _objModPath = objModPath;
        _skinPath = skinPath;
        _metaPath = metaPath;
        _tables = Collections.unmodifiableList(Arrays.asList(tables));
    }

    @Nonnull
    public Class<? extends ObjMod> getObjModClass() {
        return _objModClass;
    }

    /** @return the map file holding this type's object modifications */
    @Nonnull
    public File getObjModPath() {
        return _objModPath;
    }

    /** @return the map file holding this type's skin modifications, which are never merged */
    @Nonnull
    public File getSkinPath() {
        return _skinPath;
    }

    @Nonnull
    public String getMetaPath() {
        return _metaPath;
    }

    /** @return the SLK tables objects of this type live in, the defining one first */
    @Nonnull
    public List<String> getTables() {
        return _tables;
    }

    /** @return the table whose rows define which objects of this type exist */
    @Nonnull
    public String getPrimaryTable() {
        return _tables.get(0);
    }

    public boolean isMerged() {
        return this != DOODAD;
    }

    @Nullable
    public static ObjType of(@Nonnull ObjMod<?> objMod) {
        for (ObjType type : values()) {
            if (type._objModClass.isInstance(objMod)) return type;
        }

        return null;
    }

    /** Maps a metadata {@code slk} name such as {@code UnitBalance} to its table path. */
    @Nullable
    public static String tableOf(@Nonnull String slkName) {
        switch (slkName) {
            case "UnitData": return "Units\\UnitData.slk";
            case "UnitBalance": return "Units\\UnitBalance.slk";
            case "UnitUI": return "Units\\UnitUI.slk";
            case "UnitWeapons": return "Units\\UnitWeapons.slk";
            case "UnitAbilities": return "Units\\UnitAbilities.slk";
            case "ItemData": return "Units\\ItemData.slk";
            case "DestructableData": return "Units\\DestructableData.slk";
            case "AbilityData": return "Units\\AbilityData.slk";
            case "AbilityBuffData": return "Units\\AbilityBuffData.slk";
            case "UpgradeData": return "Units\\UpgradeData.slk";
            case "DoodadData": return "Doodads\\Doodads.slk";
            default: return null;
        }
    }
}
