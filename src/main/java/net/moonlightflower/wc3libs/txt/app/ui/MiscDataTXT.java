package net.moonlightflower.wc3libs.txt.app.ui;

import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.FileType;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.txt.TXT;
import net.moonlightflower.wc3libs.txt.TXTState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MiscDataTXT extends TXT {
	public final static File GAME_USE_PATH = new File("UI\\MiscData.txt");
	
	public class Speed extends DataType {

		@Override
		public DataType decode(Object val) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object toSLKVal() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object toTXTVal() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	public static class Section extends TXT.Section {
		public final static Section ARTHAS_ILLIDAN_FIGHT_PREFETCH_LIST = new Section("ArthasIllidanFightPrefetchList");
		public final static Section ARTHAS_ILLIDAN_FIGHT_SOUNDS = new Section("ArthasIllidanFightSounds");
		public final static Section ARTHAS_ILLIDAN_FIGHT_Z_FOG = new Section("ArthasIllidanFightZFog");
		public final static Section BATTLE_NET_CUSTOM_FILTER = new Section("BattleNetCustomFilter");
		public final static Section BLIGHT = new Section("Blight");
		public final static Section CAM = new Section("Camera");
		public final static Section CAM_MARGINS = new Section("CameraMargins");
		public final static Section CAM_RATES = new Section("CameraRates");
		public final static Section DEFAULT_Z_FOG = new Section("DefaultZFog");
		public final static Section FLYER_MAP = new Section("FlyerMap");
		public final static Section FOG_OF_WAR = new Section("FogOfWar");
		public final static Section GLUE = new Section("Glue");
		public final static Section INFO_PANEL = new Section("InfoPanel");
		public final static Section LIGHT = new Section("Light");
		public final static Section MENU_Z_FOG = new Section("MenuZFog");
		public final static Section MISC = new Section("Misc");
		public final static Section MINIMAP = new Section("Minimap");
		public final static Section OCCLUSION = new Section("Occlusion");
		public final static Section PING_COLOR = new Section("PingColor");
		public final static Section PLACEMENT = new Section("Placement");
		public final static Section QUEST_INDICATOR_TIMEOUT = new Section("QuestIndicatorTimeout");
		public final static Section SELECTION_CIRCLE = new Section("SelectionCircle");
		public final static Section SOUND = new Section("Sound");
		public final static Section TARGETING_COLORS = new Section("TargetingColors");
		public final static Section TEAM_COLOR_FILTER = new Section("TeamColorFilter");
		public final static Section TERRAIN = new Section("Terrain");
		public final static Section TERRAIN_Z_FOG = new Section("TerrainZFog");
		public final static Section UNPATHABLE_MARGINS = new Section("UnpathableMargins");
		public final static Section WATER = new Section("Water");
		
		public Section(@Nonnull String name) {
			super(name);
		}
	}
	
	public static class State<T extends DataType> extends TXTState<T> {
		//Camera section
		public final static State<DataList<War3Real>> CAM_ANGLE_OF_ATTACK = new State<>(Section.CAM, "AOA", new DataTypeInfo(DataList.class, War3Real.class));
		public final static State<DataList<War3Real>> CAM_FIELD_OF_VIEW = new State<>(Section.CAM, "FOV", new DataTypeInfo(DataList.class, War3Real.class));
		public final static State<DataList<War3Real>> CAM_DIST = new State<>(Section.CAM, "Distance", new DataTypeInfo(DataList.class, War3Real.class));
		public final static State<DataList<War3Real>> CAM_FAR_Z = new State<>(Section.CAM, "FarZ", new DataTypeInfo(DataList.class, War3Real.class));
		public final static State<DataList<War3Real>> CAM_NEAR_Z = new State<>(Section.CAM, "NearZ", new DataTypeInfo(DataList.class, War3Real.class));
		public final static State<DataList<War3Real>> CAM_OFFSET_Z = new State<>(Section.CAM, "Height", new DataTypeInfo(DataList.class, War3Real.class));
		public final static State<DataList<War3Real>> CAM_ROT = new State<>(Section.CAM, "Rotation", new DataTypeInfo(DataList.class, War3Real.class));
		public final static State<War3Real> CAM_SMOOTH_BETA = new State<>(Section.CAM, "SmoothBeta", War3Real.class);
		public final static State<War3Real> CAM_SMOOTH_BIAS = new State<>(Section.CAM, "SmoothBias", War3Real.class);
		public final static State<War3Real> CAM_SMOOTH_FACTOR = new State<>(Section.CAM, "Smoothing", War3Real.class);
		public final static State<War3Real> CAM_SMOOTH_MAX_DIST = new State<>(Section.CAM, "SmoothMaxDist", War3Real.class);
		public final static State<War3Real> CAM_SMOOTH_SCALE = new State<>(Section.CAM, "SmoothScale", War3Real.class);
		public final static State<War3Real> CAM_SMOOTH_MIN_FPS = new State<>(Section.CAM, "SmoothMinFPS", War3Real.class);

		//CameraRates section
		public final static State<War3Real> CAM_RATE_ANGLE_OF_ATTACK = new State<>(Section.CAM_RATES, "AOA", War3Real.class);
		public final static State<War3Real> CAM_RATE_DIST = new State<>(Section.CAM_RATES, "Distance", War3Real.class);
		public final static State<War3Real> CAM_RATE_FIELD_OF_VIEW = new State<>(Section.CAM_RATES, "FOV", War3Real.class);
		public final static State<War3Real> CAM_RATE_FORWARD = new State<>(Section.CAM_RATES, "Forward", War3Real.class);
		public final static State<War3Real> CAM_RATE_ROT = new State<>(Section.CAM_RATES, "Rotation", War3Real.class);
		public final static State<War3Real> CAM_RATE_STRAFE = new State<>(Section.CAM_RATES, "Strafe", War3Real.class);

		//QuestIndicatorTimeout section
		public final static State<War3Real> QUEST_INDICATOR_TIMEOUT = new State<>(Section.QUEST_INDICATOR_TIMEOUT, "QuestIndicatorTimeout", War3Real.class);

		//CameraMargins section
		public final static State<War3Int> MARGIN_BOTTOM = new State<>(Section.CAM_MARGINS, "bottom", War3Int.class);
		public final static State<War3Int> MARGIN_LEFT = new State<>(Section.CAM_MARGINS, "left", War3Int.class);
		public final static State<War3Int> MARGIN_RIGHT = new State<>(Section.CAM_MARGINS, "right", War3Int.class);
		public final static State<War3Int> MARGIN_UP = new State<>(Section.CAM_MARGINS, "up", War3Int.class);

		//UnpathableMargins section
		public final static State<War3Int> UNPATH_MARGIN_BOTTOM = new State<>(Section.UNPATHABLE_MARGINS, "bottom", War3Int.class);
		public final static State<War3Int> UNPATH_MARGIN_LEFT = new State<>(Section.UNPATHABLE_MARGINS, "left", War3Int.class);
		public final static State<War3Int> UNPATH_MARGIN_RIGHT = new State<>(Section.UNPATHABLE_MARGINS, "right", War3Int.class);
		public final static State<War3Int> UNPATH_MARGIN_UP = new State<>(Section.UNPATHABLE_MARGINS, "up", War3Int.class);

		//DefaultZFog
		public final static State<ColorARGB> DEFAULT_Z_FOG_COLOR = new State<>(Section.DEFAULT_Z_FOG, "Color", ColorARGB.class);
		public final static State<War3Real> DEFAULT_Z_FOG_DENSITY = new State<>(Section.DEFAULT_Z_FOG, "Density", War3Real.class);
		public final static State<War3Int> DEFAULT_Z_FOG_END = new State<>(Section.DEFAULT_Z_FOG, "End", War3Int.class);
		public final static State<War3Int> DEFAULT_Z_FOG_START = new State<>(Section.DEFAULT_Z_FOG, "Start", War3Int.class);
		public final static State<TerrainFogType> DEFAULT_Z_FOG_STYLE = new State<>(Section.DEFAULT_Z_FOG, "Style", TerrainFogType.class);

		//TerrainZFog
		public final static State<ColorARGB> TERRAIN_Z_FOG_COLOR = new State<>(Section.TERRAIN_Z_FOG, "Color", ColorARGB.class);
		public final static State<War3Real> TERRAIN_Z_FOG_DENSITY = new State<>(Section.TERRAIN_Z_FOG, "Density", War3Real.class);
		public final static State<War3Int> TERRAIN_Z_FOG_END = new State<>(Section.TERRAIN_Z_FOG, "End", War3Int.class);
		public final static State<War3Int> TERRAIN_Z_FOG_START = new State<>(Section.TERRAIN_Z_FOG, "Start", War3Int.class);
		public final static State<TerrainFogType> TERRAIN_Z_FOG_STYLE = new State<>(Section.TERRAIN_Z_FOG, "Style", TerrainFogType.class);

		//MenuZFog
		public final static State<DataList<ColorARGB>> MENU_Z_FOG_COLOR = new State<>(Section.MENU_Z_FOG, "Color", new DataTypeInfo(DataList.class, ColorARGB.class));
		public final static State<War3Real> MENU_Z_FOG_DENSITY = new State<>(Section.MENU_Z_FOG, "Density", War3Real.class);
		public final static State<War3Int> MENU_Z_FOG_END = new State<>(Section.MENU_Z_FOG, "End", War3Int.class);
		public final static State<War3Int> MENU_Z_FOG_START = new State<>(Section.MENU_Z_FOG, "Start", War3Int.class);
		public final static State<TerrainFogType> MENU_Z_FOG_STYLE = new State<>(Section.MENU_Z_FOG, "Style", TerrainFogType.class);

		//ArthasIllidanFightZFog
		public final static State<ColorARGB> ARTHAS_ILLIDAN_FIGHT_Z_FOG_COLOR = new State<>(Section.ARTHAS_ILLIDAN_FIGHT_Z_FOG, "Color", ColorARGB.class);
		public final static State<War3Real> ARTHAS_ILLIDAN_FIGHT_Z_FOG_DENSITY = new State<>(Section.ARTHAS_ILLIDAN_FIGHT_Z_FOG, "Density", War3Real.class);
		public final static State<War3Int> ARTHAS_ILLIDAN_FIGHT_Z_FOG_END = new State<>(Section.ARTHAS_ILLIDAN_FIGHT_Z_FOG, "End", War3Int.class);
		public final static State<War3Int> ARTHAS_ILLIDAN_FIGHT_Z_FOG_START = new State<>(Section.ARTHAS_ILLIDAN_FIGHT_Z_FOG, "Start", War3Int.class);
		public final static State<TerrainFogType> ARTHAS_ILLIDAN_FIGHT_Z_FOG_STYLE = new State<>(Section.ARTHAS_ILLIDAN_FIGHT_Z_FOG, "Style", TerrainFogType.class);

		//ArthasIllidanFightPrefetchList

		//ArthasIllidanFightSounds
		public final static State<War3String> ARTHAS_ILLIDAN_FIGHT_SOUND_SCENE1 = new State<>(Section.ARTHAS_ILLIDAN_FIGHT_SOUNDS, "Scene1", War3String.class);

		//Terrain
		public final static State<War3Int> TERRAIN_MAX_HEIGHT = new State<>(Section.TERRAIN, "MaxHeight", War3Int.class);
		public final static State<War3Int> TERRAIN_MAX_SLOPE = new State<>(Section.TERRAIN, "MaxSlope", War3Int.class);
		public final static State<War3Int> TERRAIN_MIN_HEIGHT = new State<>(Section.TERRAIN, "MinHeight", War3Int.class);

		//FlyerMap
		public final static State<War3Real> FLYER_MAX_RAD = new State<>(Section.FLYER_MAP, "MaximizeRadius", War3Real.class);
		public final static State<War3Real> FLYER_SMOOTH_LEVEL = new State<>(Section.FLYER_MAP, "SmoothLevels", War3Real.class);

		//Sound
		public final static State<War3Real> SOUND_ATTENUATION = new State<>(Section.SOUND, "Attenuation", War3Real.class);
		public final static State<War3Int> SOUND_DIST_MAX = new State<>(Section.SOUND, "MaxDist", War3Int.class);
		public final static State<War3Int> SOUND_DIST_MIN = new State<>(Section.SOUND, "MinDist", War3Int.class);
		public final static State<War3Int> SOUND_MUSIC_FADE_INTERVAL = new State<>(Section.SOUND, "MusicFadeInterval", War3Int.class);
		public final static State<War3Int> SOUND_MUSIC_FADE_RATE = new State<>(Section.SOUND, "MusicFadeRate", War3Int.class);

		//Light
		public final static State<Coords3DF> LIGHT_ORIENT = new State<>(Section.LIGHT, "Direction", Coords3DF.class);

		//SelectionCircle
		public final static State<War3Int> SELECTION_SCALE_FACTOR = new State<>(Section.SELECTION_CIRCLE, "ScaleFactor", War3Int.class);
		public final static State<War3Int> SELECTION_IMAGE_WALKABLE_Z_OFFSET = new State<>(Section.SELECTION_CIRCLE, "ImageWalkableZOffset", War3Int.class);

		public final static State<ColorARGB> SELECTION_COLOR_ALLY = new State<>(Section.SELECTION_CIRCLE, "ColorFriend", ColorARGB.class);
		public final static State<ColorARGB> SELECTION_COLOR_ENEMY = new State<>(Section.SELECTION_CIRCLE, "ColorEnemy", ColorARGB.class);
		public final static State<ColorARGB> SELECTION_COLOR_NEUTRAL = new State<>(Section.SELECTION_CIRCLE, "ColorNeutral", ColorARGB.class);

		public final static State<ColorARGB> SELECTION_ALLY_OFFSET = new State<>(Section.SELECTION_CIRCLE, "AllyOffset", ColorARGB.class);

		//Blight
		public final static State<War3Real> BLIGHT_PUFF_CHANCE_PERC = new State<>(Section.BLIGHT, "PuffChance", War3Real.class);
		public final static State<War3Real> BLIGHT_PUFF_CHANCE_D1 = new State<>(Section.BLIGHT, "DCT_d1", War3Real.class);
		public final static State<War3Real> BLIGHT_PUFF_CHANCE_D2 = new State<>(Section.BLIGHT, "DCT_d2", War3Real.class);
		public final static State<War3Real> BLIGHT_PUFF_CHANCE_S1 = new State<>(Section.BLIGHT, "DCT_s1", War3Real.class);
		public final static State<War3Real> BLIGHT_PUFF_CHANCE_S2 = new State<>(Section.BLIGHT, "DCT_s2", War3Real.class);
		public final static State<War3Real> BLIGHT_PUFF_CHANCE_SD = new State<>(Section.BLIGHT, "DCT_sd", War3Real.class);
		public final static State<War3Int> BLIGHT_PUFF_DURATION_MS = new State<>(Section.BLIGHT, "PuffDuration", War3Int.class);
		public final static State<War3Int> BLIGHT_PUFF_INTERVAL_MS = new State<>(Section.BLIGHT, "PuffInterval", War3Int.class);
		public final static State<Model> BLIGHT_PUFF_MODEL = new State<>(Section.BLIGHT, "PuffModel", Model.class);
		public final static State<Tex> BLIGHT_TEX = new State<>(Section.BLIGHT, "Texture", Tex.class);

		//Water
		public final static State<War3Int> WATER_DEEP_LEVEL = new State<>(Section.WATER, "DeepLevel", War3Int.class);
		public final static State<War3Int> WATER_DEEP_LEVEL_PATHING = new State<>(Section.WATER, "DeepLevelPathing", War3Int.class);
		public final static State<War3Int> WATER_DEPTH_MAX = new State<>(Section.WATER, "MaxDepth", War3Int.class);
		public final static State<War3Int> WATER_DEPTH_MIN = new State<>(Section.WATER, "MinDepth", War3Int.class);
		public final static State<War3Int> WATER_DEPTH_WAVES = new State<>(Section.WATER, "WavesDepth", War3Int.class);

		//TeamColorFilter
		public final static State<War3Int> TEAM_COLOR_FILTER_INDEX_ALLY = new State<>(Section.TEAM_COLOR_FILTER, "ColorIndexAlly", War3Int.class);
		public final static State<War3Int> TEAM_COLOR_FILTER_INDEX_CREEP = new State<>(Section.TEAM_COLOR_FILTER, "ColorIndexCreep", War3Int.class);
		public final static State<War3Int> TEAM_COLOR_FILTER_INDEX_ENEMY = new State<>(Section.TEAM_COLOR_FILTER, "ColorIndexEnemy", War3Int.class);
		public final static State<War3Int> TEAM_COLOR_FILTER_INDEX_PLAYER = new State<>(Section.TEAM_COLOR_FILTER, "ColorIndexPlayer", War3Int.class);

		//FogOfWar
		public final static State<ColorARGB> FOG_TERRAIN_BOUNDARY = new State<>(Section.FOG_OF_WAR, "BoundaryTerrain", ColorARGB.class);
		public final static State<ColorARGB> FOG_TERRAIN_BOUNDARY_FOGGED = new State<>(Section.FOG_OF_WAR, "FoggedBoundaryTerrain", ColorARGB.class);
		public final static State<ColorARGB> FOG_TERRAIN_FOGGED = new State<>(Section.FOG_OF_WAR, "FoggedTerrain", ColorARGB.class);
		public final static State<ColorARGB> FOG_TERRAIN_MASKED_BLACK = new State<>(Section.FOG_OF_WAR, "BlackMaskedTerrain", ColorARGB.class);
		public final static State<ColorARGB> FOG_TERRAIN_MASKED_DARK = new State<>(Section.FOG_OF_WAR, "DarkMaskedTerrain", ColorARGB.class);

		public final static State<ColorARGB> FOG_OBJECT_BOUNDARY = new State<>(Section.FOG_OF_WAR, "BoundaryObject", ColorARGB.class);
		public final static State<ColorARGB> FOG_OBJECT_BOUNDARY_FOGGED = new State<>(Section.FOG_OF_WAR, "FoggedBoundaryObject", ColorARGB.class);
		public final static State<ColorARGB> FOG_OBJECT_FOGGED = new State<>(Section.FOG_OF_WAR, "FoggedObject", ColorARGB.class);
		public final static State<ColorARGB> FOG_OBJECT_MASKED_BLACK = new State<>(Section.FOG_OF_WAR, "BlackMaskedObject", ColorARGB.class);
		public final static State<ColorARGB> FOG_OBJECT_MASKED_DARK = new State<>(Section.FOG_OF_WAR, "DarkMaskedObject", ColorARGB.class);

		public final static State<ColorARGB> FOG_SHADOW_IMAGE_COLOR = new State<>(Section.FOG_OF_WAR, "ShadowImageColor", ColorARGB.class);

		public final static State<ColorARGB> FOG_COLOR_ALLY = new State<>(Section.FOG_OF_WAR, "FogColorAlly", ColorARGB.class);
		public final static State<ColorARGB> FOG_COLOR_ENEMY = new State<>(Section.FOG_OF_WAR, "FogColorEnemy", ColorARGB.class);
		public final static State<ColorARGB> FOG_COLOR_PLAYER = new State<>(Section.FOG_OF_WAR, "FogColorPlayer", ColorARGB.class);
		public final static State<ColorARGB> FOG_COLOR_RESOURCE = new State<>(Section.FOG_OF_WAR, "FogColorResource", ColorARGB.class);

		public final static State<ColorARGB> FOG_COLOR_DESTRUCTABLE = new State<>(Section.FOG_OF_WAR, "FogColorDestructable", ColorARGB.class);
		public final static State<ColorARGB> FOG_COLOR_HERO = new State<>(Section.FOG_OF_WAR, "FogColorHero", ColorARGB.class);
		public final static State<ColorARGB> FOG_COLOR_ITEM = new State<>(Section.FOG_OF_WAR, "FogColorItem", ColorARGB.class);

		public final static State<ColorARGB> FOG_COLOR_CREEP = new State<>(Section.FOG_OF_WAR, "FogColorCreepNormal", ColorARGB.class);
		public final static State<ColorARGB> FOG_COLOR_CREEP_ALLY = new State<>(Section.FOG_OF_WAR, "FogColorCreepAllied", ColorARGB.class);

		public final static State<War3Int> FOG_COLOR_BLACK_MASK_ALPHA = new State<>(Section.FOG_OF_WAR, "FogColorBlackMaskAlpha", War3Int.class);
		public final static State<War3Int> FOG_COLOR_DARK_MASK_ALPHA = new State<>(Section.FOG_OF_WAR, "FogColorDarkMaskAlpha", War3Int.class);
		public final static State<War3Int> FOG_COLOR_FOG_MASK_ALPHA = new State<>(Section.FOG_OF_WAR, "FogColorFogMaskAlpha", War3Int.class);
		public final static State<War3Int> FOG_COLOR_VISIBLE_ALPHA = new State<>(Section.FOG_OF_WAR, "FogColorVisibleAlpha", War3Int.class);

		public final static State<ColorARGB> FOG_COLOR_BLIGHT_MASK = new State<>(Section.FOG_OF_WAR, "FogColorBlightMask", ColorARGB.class);

		//Minimap
		public final static State<ColorARGB> MINIMAP_BORDER_COLOR = new State<>(Section.MINIMAP, "MinimapColorBorder", ColorARGB.class);
		public final static State<War3Int> MINIMAP_CREEP_CAMP_THRESHOLD_LARGE = new State<>(Section.MINIMAP, "MinimapToughCampThreshold", War3Int.class);
		public final static State<War3Int> MINIMAP_CREEP_CAMP_THRESHOLD_MEDIUM = new State<>(Section.MINIMAP, "MinimapMiddleCampThreshold", War3Int.class);

		public final static State<War3Real> MINIMAP_CREEP_CAMP_SCALE_MEDIUM = new State<>(Section.MINIMAP, "MinimapMiddleCampScale", War3Real.class);

		public final static State<ColorARGB> MINIMAP_CREEP_CAMP_COLOR_LARGE = new State<>(Section.MINIMAP, "MinimapToughCampColor", ColorARGB.class);
		public final static State<ColorARGB> MINIMAP_CREEP_CAMP_COLOR_MEDIUM = new State<>(Section.MINIMAP, "MinimapMiddleCampColor", ColorARGB.class);
		public final static State<ColorARGB> MINIMAP_CREEP_CAMP_COLOR_SMALL = new State<>(Section.MINIMAP, "MinimapWeakCampColor", ColorARGB.class);

		public final static State<War3Real> MINIMAP_CREEP_CAMP_PULSE_SCALE = new State<>(Section.MINIMAP, "MinimapCampPulseScale", War3Real.class);

		//TargetingColors
		public final static State<ColorARGB> TARGETING_SELECTION_COLOR = new State<>(Section.TARGETING_COLORS, "SelTargetColor", ColorARGB.class);
		public final static State<ColorARGB> TARGETING_TARGET_COLOR_LIGHT = new State<>(Section.TARGETING_COLORS, "TargetLightColor", ColorARGB.class);

		//Glue
		public final static State<War3Real> GLUE_CONTROL_FADE_DUR = new State<>(Section.GLUE, "ControlFadeDuration", War3Real.class);
		public final static State<War3Real> GLUE_CONTROL_FADE_DUR_DEBUG = new State<>(Section.GLUE, "ControlFadeDurationDebug", War3Real.class);
		public final static State<War3Real> GLUE_CUSTOM_LOAD_TIMER_DURATION = new State<>(Section.GLUE, "CustomLoadTimerDuration", War3Real.class);

		//BattleNetCustomFilter
		public final static State<Coords2DI> BATTLE_NET_FILTER_LARGE = new State<>(Section.BATTLE_NET_CUSTOM_FILTER, "LargeMapRange", Coords2DI.class);
		public final static State<Coords2DI> BATTLE_NET_FILTER_MEDIUM = new State<>(Section.BATTLE_NET_CUSTOM_FILTER, "MediumMapRange", Coords2DI.class);
		public final static State<Coords2DI> BATTLE_NET_FILTER_SMALL = new State<>(Section.BATTLE_NET_CUSTOM_FILTER, "SmallMapRange", Coords2DI.class);

		//InfoPanel
		public final static State<War3Real> ATK_SPEED_FAST = new State<>(Section.INFO_PANEL, "AttackFast", War3Real.class);
		public final static State<War3Real> ATK_SPEED_NORMAL = new State<>(Section.INFO_PANEL, "AttackAverage", War3Real.class);
		public final static State<War3Real> ATK_SPEED_SLOW = new State<>(Section.INFO_PANEL, "AttackSlow", War3Real.class);
		public final static State<War3Real> ATK_SPEED_SLOWEST = new State<>(Section.INFO_PANEL, "AttackVerySlow", War3Real.class);
		public final static State<War3Int> MELEE_RANGE_MAX = new State<>(Section.INFO_PANEL, "MeleeRangeMax", War3Int.class);
		public final static State<War3Int> MOVE_SPEED_FAST = new State<>(Section.INFO_PANEL, "SpeedFast", War3Int.class);
		public final static State<War3Int> MOVE_SPEED_NORMAL = new State<>(Section.INFO_PANEL, "SpeedAverage", War3Int.class);
		public final static State<War3Int> MOVE_SPEED_SLOW = new State<>(Section.INFO_PANEL, "SpeedSlow", War3Int.class);
		public final static State<War3Int> MOVE_SPEED_SLOWEST = new State<>(Section.INFO_PANEL, "SpeedVerySlow", War3Int.class);

		//Occlusion
		public final static State<ColorARGB> OCCLUSION_MARK_COLOR = new State<>(Section.OCCLUSION, "MarkColor", ColorARGB.class);
		public final static State<Tex> OCCLUSION_MARK_TEX = new State<>(Section.OCCLUSION, "MarkImage", Tex.class);
		public final static State<ColorARGB> OCCLUSION_OCCLUDER_COLOR = new State<>(Section.OCCLUSION, "OccluderColor", ColorARGB.class);
		public final static State<War3Int> OCCLUSION_OCCLUDER_FADE_DUR_MS = new State<>(Section.OCCLUSION, "OccluderFadeTime", War3Int.class);
		public final static State<War3Int> OCCLUSION_STRUCTURES = new State<>(Section.OCCLUSION, "BuildingsOcclude", War3Int.class);  //maybe boolean?

		//Placement
		public final static State<ColorARGB> PLACEMENT_STRUCTURE_PAID = new State<>(Section.PLACEMENT, "PaidStructureColor", ColorARGB.class);
		public final static State<ColorARGB> PLACEMENT_STRUCTURE_UNPAID = new State<>(Section.PLACEMENT, "UnpaidStructureColor", ColorARGB.class);

		//PingColor
		public final static State<ColorARGB> PING_COLOR = new State<>(Section.PING_COLOR, "PingColor", ColorARGB.class);
		public final static State<ColorARGB> PING_COLOR_ADVISOR = new State<>(Section.PING_COLOR, "PingAdvisorColor", ColorARGB.class);
		public final static State<ColorARGB> PING_COLOR_ATTACK = new State<>(Section.PING_COLOR, "PingAttackColor", ColorARGB.class);
		public final static State<ColorARGB> PING_COLOR_WAYPOINT = new State<>(Section.PING_COLOR, "PingWaypointColor", ColorARGB.class);

		//Misc section
		public final static State<War3Real> FADE_BUFF_DURATION_MIN = new State<>(Section.MISC, "FadeBuffMinDuration", War3Real.class);
		public final static State<War3Real> FADE_BUFF_ALPHA_MAX = new State<>(Section.MISC, "FadeBuffMaxAlpha", War3Real.class);
		public final static State<War3Real> FADE_BUFF_ALPHA_MIN = new State<>(Section.MISC, "FadeBuffMinAlpha", War3Real.class);

		public final static State<ColorARGB> GOLD_TEXT_COLOR = new State<>(Section.MISC, "GoldTextColor", ColorARGB.class);
		public final static State<Speed> GOLD_TEXT_SPEED = new State<>(Section.MISC, "GoldTextVelocity", Speed.class);
		public final static State<War3Real> GOLD_TEXT_LIFESPAN = new State<>(Section.MISC, "GoldTextLifetime", War3Real.class);
		public final static State<War3Real> GOLD_TEXT_FADE_POINT = new State<>(Section.MISC, "GoldTextFadeStart", War3Real.class);

		public final static State<ColorARGB> LUMBER_TEXT_COLOR = new State<>(Section.MISC, "LumberTextColor", ColorARGB.class);
		public final static State<Speed> LUMBER_TEXT_SPEED = new State<>(Section.MISC, "LumberTextVelocity", Speed.class);
		public final static State<War3Real> LUMBER_TEXT_LIFESPAN = new State<>(Section.MISC, "LumberTextLifetime", War3Real.class);
		public final static State<War3Real> LUMBER_TEXT_FADE_POINT = new State<>(Section.MISC, "LumberTextFadeStart", War3Real.class);

		public final static State<ColorARGB> BOUNTY_TEXT_COLOR = new State<>(Section.MISC, "BountyTextColor", ColorARGB.class);
		public final static State<Speed> BOUNTY_TEXT_SPEED = new State<>(Section.MISC, "BountyTextVelocity", Speed.class);
		public final static State<War3Real> BOUNTY_TEXT_LIFESPAN = new State<>(Section.MISC, "BountyTextLifetime", War3Real.class);
		public final static State<War3Real> BOUNTY_TEXT_FADE_POINT = new State<>(Section.MISC, "BountyTextFadeStart", War3Real.class);

		public final static State<ColorARGB> MISS_TEXT_COLOR = new State<>(Section.MISC, "MissTextColor", ColorARGB.class);
		public final static State<Speed> MISS_TEXT_SPEED = new State<>(Section.MISC, "MissTextVelocity", Speed.class);
		public final static State<War3Real> MISS_TEXT_LIFESPAN = new State<>(Section.MISC, "MissTextLifetime", War3Real.class);
		public final static State<War3Real> MISS_TEXT_FADE_POINT = new State<>(Section.MISC, "MissTextFadeStart", War3Real.class);

		public final static State<ColorARGB> CRIT_STRIKE_TEXT_COLOR = new State<>(Section.MISC, "CriticalStrikeTextColor", ColorARGB.class);
		public final static State<Speed> CRIT_STRIKE_TEXT_SPEED = new State<>(Section.MISC, "CriticalStrikeTextVelocity", Speed.class);
		public final static State<War3Real> CRIT_STRIKE_TEXT_LIFESPAN = new State<>(Section.MISC, "CriticalStrikeTextLifetime", War3Real.class);
		public final static State<War3Real> CRIT_STRIKE_TEXT_FADE_POINT = new State<>(Section.MISC, "CriticalStrikeTextFadeStart", War3Real.class);

		public final static State<ColorARGB> SHADOW_STRIKE_TEXT_COLOR = new State<>(Section.MISC, "ShadowStrikeTextColor", ColorARGB.class);
		public final static State<Speed> SHADOW_STRIKE_TEXT_SPEED = new State<>(Section.MISC, "ShadowStrikeTextVelocity", Speed.class);
		public final static State<War3Real> SHADOW_STRIKE_TEXT_LIFESPAN = new State<>(Section.MISC, "ShadowStrikeTextLifetime", War3Real.class);
		public final static State<War3Real> SHADOW_STRIKE_TEXT_FADE_POINT = new State<>(Section.MISC, "ShadowStrikeTextFadeStart", War3Real.class);

		public final static State<ColorARGB> MANA_BURN_TEXT_COLOR = new State<>(Section.MISC, "ManaBurnTextColor", ColorARGB.class);
		public final static State<Speed> MANA_BURN_TEXT_SPEED = new State<>(Section.MISC, "ManaBurnTextVelocity", Speed.class);
		public final static State<War3Real> MANA_BURN_TEXT_LIFESPAN = new State<>(Section.MISC, "ManaBurnTextLifetime", War3Real.class);
		public final static State<War3Real> MANA_BURN_TEXT_FADE_POINT = new State<>(Section.MISC, "ManaBurnTextFadeStart", War3Real.class);

		public final static State<ColorARGB> BASH_TEXT_COLOR = new State<>(Section.MISC, "BashTextColor", ColorARGB.class);
		public final static State<Speed> BASH_TEXT_SPEED = new State<>(Section.MISC, "BashTextVelocity", Speed.class);
		public final static State<War3Real> BASH_TEXT_LIFESPAN = new State<>(Section.MISC, "BashTextLifetime", War3Real.class);
		public final static State<War3Real> BASH_TEXT_FADE_POINT = new State<>(Section.MISC, "BashTextFadeStart", War3Real.class);

		public final static State<DataList<ColorARGB>> LAVA_SPAWN_COLOR = new State<>(Section.MISC, "LavaSpawnColor", new DataTypeInfo(DataList.class, ColorARGB.class));

		public final static State<ColorARGB> BUTTON_COLOR_ACTIVE = new State<>(Section.MISC, "ActiveButtonColor", ColorARGB.class);

		public State(@Nonnull String fieldS, @Nonnull DataTypeInfo typeInfo, @Nullable T defVal) {
			super(fieldS, typeInfo, defVal);
		}
		
		public State(@Nonnull String fieldS, @Nonnull Class<T> type, @Nullable T defVal) {
			this(fieldS, new DataTypeInfo(type), defVal);
		}
		
		public State(@Nonnull String fieldS, @Nonnull DataTypeInfo typeInfo) {
			this(fieldS, typeInfo, null);
		}
		
		public State(@Nonnull String fieldS, @Nonnull Class<T> type) {
			this(fieldS, new DataTypeInfo(type), null);
		}

		private State(@Nonnull Section section, @Nonnull String fieldS, @Nonnull DataTypeInfo typeInfo) {
			this(fieldS, typeInfo);

			section.addField(getFieldId());
		}

		private State(@Nonnull Section section, @Nonnull String fieldS, @Nonnull Class<T> type) {
			this(fieldS, type);

			section.addField(getFieldId());
		}
	}
	
	private final List<File> _arthasIllidanFightPrefetch_files = new ArrayList<>();
	
	public void ArthasIllidanFightPrefetch_add(@Nonnull File file) {
		_arthasIllidanFightPrefetch_files.add(file);
		
		State<FileType> state = new State<>(Section.ARTHAS_ILLIDAN_FIGHT_PREFETCH_LIST, String.format("File%i", _arthasIllidanFightPrefetch_files.size() - 1), FileType.class);
		
		set(state, new FileType(file));
	}
	
	private final List<File> _selectionCircle_files = new ArrayList<>();
	private final List<File> _selectionCircle_dottedFiles = new ArrayList<>();
	private final List<War3Int> _selectionCircle_sizes = new ArrayList<>();
	
	public void selectionCircle_add(@Nonnull Tex tex, @Nonnull Tex dottedTex, int maxSize) {
		int num = _selectionCircle_files.size();
		
		String numS = String.format("%02d", num);
		
		State<Tex> fileState = new State<>(Section.SELECTION_CIRCLE, "Texture" + numS, Tex.class);
		State<Tex> dottedFileState = new State<>(Section.SELECTION_CIRCLE, "TextureDotted" + numS, Tex.class);
		State<War3Int> sizeState = new State<>(Section.SELECTION_CIRCLE, "Size" + numS, War3Int.class);
		
		set(fileState, tex);
		set(dottedFileState, dottedTex);
		set(sizeState, War3Int.valueOf(maxSize));
	}
	
	public <T extends DataType> T get(@Nonnull State<T> state) throws Exception {
		return (T) super.get(state.getFieldId());
	}
	
	public <T extends DataType> void set(@Nonnull State<T> state, @Nullable T val) {
		super.set(state.getFieldId(), val);
	}
	
	@Override
	public void read(@Nonnull File file) throws IOException {
		super.read(file);
	}
	
	@Override
	public void write(@Nonnull File file) throws IOException {
		super.write(file);
	}
	
	public MiscDataTXT() {
		super();
	}

	public MiscDataTXT(@Nonnull File file) throws IOException {
		super();

		read(file);
	}
}