package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.dataTypes.app.FlagsInt;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class MapFlag extends FlagsInt.Flag {
    private final static List<MapFlag> _all = new ArrayList<>();

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
    public final static MapFlag UNKNOWN = new MapFlag(13, "unknown");
    public final static MapFlag UNKNOWN_B = new MapFlag(14, "unknownB");
    public final static MapFlag UNKNOWN_C = new MapFlag(15, "unknownC");

    @Nonnull
    public static MapFlag[] values() {
        MapFlag[] flags = new MapFlag[_all.size()];

        return _all.toArray(flags);
    }

    private MapFlag(int pos, @Nonnull String label) {
        super(pos, label);

        _all.add(this);
    }
}
