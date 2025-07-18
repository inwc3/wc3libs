package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.dataTypes.app.FlagsInt;

import javax.annotation.Nonnull;
import java.util.Collection;

public class MapFlag extends FlagsInt.Flag {
    public final static MapFlag HIDE_MINIMAP = new MapFlag(0, "hideMinimap");
    public final static MapFlag MODIFY_ALLY_PRIORITIES = new MapFlag(1, "modifyAllyPriorities");
    public final static MapFlag MELEE_MAP = new MapFlag(2, "meleeMap");
    public final static MapFlag INITIAL_MAP_SIZE_LARGE_NEVER_MODIFIED = new MapFlag(3, "initialMapSizeLargeNeverModified");
    public final static MapFlag MASKED_AREAS_PARTIALLY_VISIBLE = new MapFlag(4, "maskedAreasPartiallyVisible");
    public final static MapFlag FIXED_PLAYER_FORCE_SETTING = new MapFlag(5, "fixedPlayerForceSetting");
    public final static MapFlag USE_CUSTOM_FORCES = new MapFlag(6, "useCustomForces");
    public final static MapFlag USE_CUSTOM_TECHS = new MapFlag(7, "useCustomTechs");
    public final static MapFlag USE_CUSTOM_ABILS = new MapFlag(8, "useCustomAbils");
    public final static MapFlag USE_CUSTOM_UPGRADES = new MapFlag(9, "useCustomUpgrades");
    public final static MapFlag MAP_PROPERTIES_WINDOW_OPENED_BEFORE = new MapFlag(10, "mapPrioritiesWindowOpenedBefore");
    public final static MapFlag SHOW_WATER_WAVES_ON_CLIFF_SHORES = new MapFlag(11, "showWaterWavesOnCliffShores");
    public final static MapFlag SHOW_WATER_WAVES_ON_ROLLING_SHORES = new MapFlag(12, "showWaterWavesOnRollingShores");
    public final static MapFlag USE_TERRAIN_FOG = new MapFlag(13, "useTerrainFog");
    public final static MapFlag REQUIRES_EXPANSION = new MapFlag(14, "requiresExpansion");
    public final static MapFlag USE_ITEM_CLASSIFICATION_SYSTEM = new MapFlag(15, "useItemClassificationSystem");
    public final static MapFlag USE_WATER_TINTING_COLOR = new MapFlag(16, "useWaterTintingColor");
    public final static MapFlag USE_ACCURATE_PROBABILITY_FOR_CALCULATIONS = new MapFlag(17, "useAccurateProbabilityForCalculations");
    public final static MapFlag USE_CUSTOM_ABIL_SKIN = new MapFlag(18, "useCustomAbilSkin");
    public final static MapFlag DISABLE_DENY_ICON = new MapFlag(19, "disableDenyIcon");
    public final static MapFlag USE_FORCE_DEFAULT_CAMERA_ZOOM = new MapFlag(20, "useForceDefaultCameraZoom");
    public final static MapFlag USE_FORCE_MAX_CAMERA_ZOOM = new MapFlag(21, "useForceMaxCameraZoom");
    public final static MapFlag USE_FORCE_MIN_CAMERA_ZOOM = new MapFlag(22, "useForceMinCameraZoom");

    @Nonnull
    public static Collection<MapFlag> values() {
        return values(MapFlag.class);
    }

    private MapFlag(int pos, @Nonnull String label) {
        super(label, pos);
    }
}
