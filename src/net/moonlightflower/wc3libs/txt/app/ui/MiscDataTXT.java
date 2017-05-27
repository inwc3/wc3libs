package net.moonlightflower.wc3libs.txt.app.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.FileType;
import net.moonlightflower.wc3libs.dataTypes.app.ColorARGB;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;
import net.moonlightflower.wc3libs.dataTypes.app.Coords3DF;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.Model;
import net.moonlightflower.wc3libs.dataTypes.app.Real;
import net.moonlightflower.wc3libs.dataTypes.app.TerrainFogType;
import net.moonlightflower.wc3libs.dataTypes.app.Tex;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.txt.TXT;
import net.moonlightflower.wc3libs.txt.TXTState;

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

	private static class Section extends TXT.Section {
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
		
		public Section(String name) {
			super(name);
		}
	}
	
	public static class State<T extends DataType> extends TXTState<T> {			
		private static List<State> _values = new ArrayList<>();
		
		public static List<State> values() {
			return _values;
		}
		
		public State(Section section, String name, DataTypeInfo typeInfo, T defVal) {
			super(section.getId(), name, typeInfo, defVal);
			
			_values.add(this);
		}
		
		public State(Section section, String name, Class<T> type, T defVal) {
			this(section, name, new DataTypeInfo(type), defVal);
		}
		
		public State(Section section, String name, DataTypeInfo typeInfo) {
			this(section, name, typeInfo, null);
		}
		
		public State(Section section, String name, Class<T> type) {
			this(section, name, new DataTypeInfo(type), null);
		}
	}
	
	public static List<State> values() {
		return State.values();
	}
	
	public static class States {		
		//Camera section
		public final static State<DataList<Real>> CAM_ANGLE_OF_ATTACK = new State<>(Section.CAM, "AOA", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> CAM_FIELD_OF_VIEW = new State<>(Section.CAM, "FOV", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> CAM_DIST = new State<>(Section.CAM, "Distance", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> CAM_FAR_Z = new State<>(Section.CAM, "FarZ", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> CAM_NEAR_Z = new State<>(Section.CAM, "NearZ", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> CAM_OFFSET_Z = new State<>(Section.CAM, "Height", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> CAM_ROT = new State<>(Section.CAM, "Rotation", new DataTypeInfo(DataList.class, Real.class));
		public final static State<Real> CAM_SMOOTH_BETA = new State<>(Section.CAM, "SmoothBeta", Real.class);
		public final static State<Real> CAM_SMOOTH_BIAS = new State<>(Section.CAM, "SmoothBias", Real.class);
		public final static State<Real> CAM_SMOOTH_FACTOR = new State<>(Section.CAM, "Smoothing", Real.class);
		public final static State<Real> CAM_SMOOTH_MAX_DIST = new State<>(Section.CAM, "SmoothMaxDist", Real.class);
		public final static State<Real> CAM_SMOOTH_SCALE = new State<>(Section.CAM, "SmoothScale", Real.class);
		public final static State<Real> CAM_SMOOTH_MIN_FPS = new State<>(Section.CAM, "SmoothMinFPS", Real.class);
		
		//CameraRates section
		public final static State<Real> CAM_RATE_ANGLE_OF_ATTACK = new State<>(Section.CAM_RATES, "AOA", Real.class);
		public final static State<Real> CAM_RATE_DIST = new State<>(Section.CAM_RATES, "Distance", Real.class);
		public final static State<Real> CAM_RATE_FIELD_OF_VIEW = new State<>(Section.CAM_RATES, "FOV", Real.class);
		public final static State<Real> CAM_RATE_FORWARD = new State<>(Section.CAM_RATES, "Forward", Real.class);
		public final static State<Real> CAM_RATE_ROT = new State<>(Section.CAM_RATES, "Rotation", Real.class);
		public final static State<Real> CAM_RATE_STRAFE = new State<>(Section.CAM_RATES, "Strafe", Real.class);
		
		//QuestIndicatorTimeout section
		public final static State<Real> QUEST_INDICATOR_TIMEOUT = new State<>(Section.QUEST_INDICATOR_TIMEOUT, "QuestIndicatorTimeout", Real.class);
		
		//CameraMargins section
		public final static State<Int> MARGIN_BOTTOM = new State<>(Section.CAM_MARGINS, "bottom", Int.class);
		public final static State<Int> MARGIN_LEFT = new State<>(Section.CAM_MARGINS, "left", Int.class);
		public final static State<Int> MARGIN_RIGHT = new State<>(Section.CAM_MARGINS, "right", Int.class);
		public final static State<Int> MARGIN_UP = new State<>(Section.CAM_MARGINS, "up", Int.class);

		//UnpathableMargins section
		public final static State<Int> UNPATH_MARGIN_BOTTOM = new State<>(Section.UNPATHABLE_MARGINS, "bottom", Int.class);
		public final static State<Int> UNPATH_MARGIN_LEFT = new State<>(Section.UNPATHABLE_MARGINS, "left", Int.class);
		public final static State<Int> UNPATH_MARGIN_RIGHT = new State<>(Section.UNPATHABLE_MARGINS, "right", Int.class);
		public final static State<Int> UNPATH_MARGIN_UP = new State<>(Section.UNPATHABLE_MARGINS, "up", Int.class);
		
		//DefaultZFog
		public final static State<ColorARGB> DEFAULT_Z_FOG_COLOR = new State<>(Section.DEFAULT_Z_FOG, "Color", ColorARGB.class);
		public final static State<Real> DEFAULT_Z_FOG_DENSITY = new State<>(Section.DEFAULT_Z_FOG, "Density", Real.class);
		public final static State<Int> DEFAULT_Z_FOG_END = new State<>(Section.DEFAULT_Z_FOG, "End", Int.class);
		public final static State<Int> DEFAULT_Z_FOG_START = new State<>(Section.DEFAULT_Z_FOG, "Start", Int.class);
		public final static State<TerrainFogType> DEFAULT_Z_FOG_STYLE = new State<>(Section.DEFAULT_Z_FOG, "Style", TerrainFogType.class);

		//TerrainZFog
		public final static State<ColorARGB> TERRAIN_Z_FOG_COLOR = new State<>(Section.TERRAIN_Z_FOG, "Color", ColorARGB.class);
		public final static State<Real> TERRAIN_Z_FOG_DENSITY = new State<>(Section.TERRAIN_Z_FOG, "Density", Real.class);
		public final static State<Int> TERRAIN_Z_FOG_END = new State<>(Section.TERRAIN_Z_FOG, "End", Int.class);
		public final static State<Int> TERRAIN_Z_FOG_START = new State<>(Section.TERRAIN_Z_FOG, "Start", Int.class);
		public final static State<TerrainFogType> TERRAIN_Z_FOG_STYLE = new State<>(Section.TERRAIN_Z_FOG, "Style", TerrainFogType.class);

		//MenuZFog
		public final static State<DataList<ColorARGB>> MENU_Z_FOG_COLOR = new State<>(Section.MENU_Z_FOG, "Color", new DataTypeInfo(DataList.class, ColorARGB.class));
		public final static State<Real> MENU_Z_FOG_DENSITY = new State<>(Section.MENU_Z_FOG, "Density", Real.class);
		public final static State<Int> MENU_Z_FOG_END = new State<>(Section.MENU_Z_FOG, "End", Int.class);
		public final static State<Int> MENU_Z_FOG_START = new State<>(Section.MENU_Z_FOG, "Start", Int.class);
		public final static State<TerrainFogType> MENU_Z_FOG_STYLE = new State<>(Section.MENU_Z_FOG, "Style", TerrainFogType.class);

		//ArthasIllidanFightZFog
		public final static State<ColorARGB> ARTHAS_ILLIDAN_FIGHT_Z_FOG_COLOR = new State<>(Section.ARTHAS_ILLIDAN_FIGHT_Z_FOG, "Color", ColorARGB.class);
		public final static State<Real> ARTHAS_ILLIDAN_FIGHT_Z_FOG_DENSITY = new State<>(Section.ARTHAS_ILLIDAN_FIGHT_Z_FOG, "Density", Real.class);
		public final static State<Int> ARTHAS_ILLIDAN_FIGHT_Z_FOG_END = new State<>(Section.ARTHAS_ILLIDAN_FIGHT_Z_FOG, "End", Int.class);
		public final static State<Int> ARTHAS_ILLIDAN_FIGHT_Z_FOG_START = new State<>(Section.ARTHAS_ILLIDAN_FIGHT_Z_FOG, "Start", Int.class);
		public final static State<TerrainFogType> ARTHAS_ILLIDAN_FIGHT_Z_FOG_STYLE = new State<>(Section.ARTHAS_ILLIDAN_FIGHT_Z_FOG, "Style", TerrainFogType.class);
		
		//ArthasIllidanFightPrefetchList
		
		//ArthasIllidanFightSounds
		public final static State<Wc3String> ARTHAS_ILLIDAN_FIGHT_SOUND_SCENE1 = new State<>(Section.ARTHAS_ILLIDAN_FIGHT_SOUNDS, "Scene1", Wc3String.class);
		
		//Terrain
		public final static State<Int> TERRAIN_MAX_HEIGHT = new State<>(Section.TERRAIN, "MaxHeight", Int.class);
		public final static State<Int> TERRAIN_MAX_SLOPE = new State<>(Section.TERRAIN, "MaxSlope", Int.class);
		public final static State<Int> TERRAIN_MIN_HEIGHT = new State<>(Section.TERRAIN, "MinHeight", Int.class);
		
		//FlyerMap
		public final static State<Real> FLYER_MAX_RAD = new State<>(Section.FLYER_MAP, "MaximizeRadius", Real.class);
		public final static State<Real> FLYER_SMOOTH_LEVEL = new State<>(Section.FLYER_MAP, "SmoothLevels", Real.class);
		
		//Sound
		public final static State<Real> SOUND_ATTENUATION = new State<>(Section.SOUND, "Attenuation", Real.class);
		public final static State<Int> SOUND_DIST_MAX = new State<>(Section.SOUND, "MaxDist", Int.class);
		public final static State<Int> SOUND_DIST_MIN = new State<>(Section.SOUND, "MinDist", Int.class);
		public final static State<Int> SOUND_MUSIC_FADE_INTERVAL = new State<>(Section.SOUND, "MusicFadeInterval", Int.class);
		public final static State<Int> SOUND_MUSIC_FADE_RATE = new State<>(Section.SOUND, "MusicFadeRate", Int.class);
		
		//Light
		public final static State<Coords3DF> LIGHT_ORIENT = new State<>(Section.LIGHT, "Direction", Coords3DF.class);
		
		//SelectionCircle		
		public final static State<Int> SELECTION_SCALE_FACTOR = new State<>(Section.SELECTION_CIRCLE, "ScaleFactor", Int.class);
		public final static State<Int> SELECTION_IMAGE_WALKABLE_Z_OFFSET = new State<>(Section.SELECTION_CIRCLE, "ImageWalkableZOffset", Int.class);
		
		public final static State<ColorARGB> SELECTION_COLOR_ALLY = new State<>(Section.SELECTION_CIRCLE, "ColorFriend", ColorARGB.class);
		public final static State<ColorARGB> SELECTION_COLOR_ENEMY = new State<>(Section.SELECTION_CIRCLE, "ColorEnemy", ColorARGB.class);
		public final static State<ColorARGB> SELECTION_COLOR_NEUTRAL = new State<>(Section.SELECTION_CIRCLE, "ColorNeutral", ColorARGB.class);
		
		public final static State<ColorARGB> SELECTION_ALLY_OFFSET = new State<>(Section.SELECTION_CIRCLE, "AllyOffset", ColorARGB.class);
		
		//Blight
		public final static State<Real> BLIGHT_PUFF_CHANCE_PERC = new State<>(Section.BLIGHT, "PuffChance", Real.class);
		public final static State<Real> BLIGHT_PUFF_CHANCE_D1 = new State<>(Section.BLIGHT, "DCT_d1", Real.class);
		public final static State<Real> BLIGHT_PUFF_CHANCE_D2 = new State<>(Section.BLIGHT, "DCT_d2", Real.class);
		public final static State<Real> BLIGHT_PUFF_CHANCE_S1 = new State<>(Section.BLIGHT, "DCT_s1", Real.class);
		public final static State<Real> BLIGHT_PUFF_CHANCE_S2 = new State<>(Section.BLIGHT, "DCT_s2", Real.class);
		public final static State<Real> BLIGHT_PUFF_CHANCE_SD = new State<>(Section.BLIGHT, "DCT_sd", Real.class);
		public final static State<Int> BLIGHT_PUFF_DURATION_MS = new State<>(Section.BLIGHT, "PuffDuration", Int.class);
		public final static State<Int> BLIGHT_PUFF_INTERVAL_MS = new State<>(Section.BLIGHT, "PuffInterval", Int.class);
		public final static State<Model> BLIGHT_PUFF_MODEL = new State<>(Section.BLIGHT, "PuffModel", Model.class);
		public final static State<Tex> BLIGHT_TEX = new State<>(Section.BLIGHT, "Texture", Tex.class);
		
		//Water
		public final static State<Int> WATER_DEEP_LEVEL = new State<>(Section.WATER, "DeepLevel", Int.class);
		public final static State<Int> WATER_DEEP_LEVEL_PATHING = new State<>(Section.WATER, "DeepLevelPathing", Int.class);
		public final static State<Int> WATER_DEPTH_MAX = new State<>(Section.WATER, "MaxDepth", Int.class);
		public final static State<Int> WATER_DEPTH_MIN = new State<>(Section.WATER, "MinDepth", Int.class);
		public final static State<Int> WATER_DEPTH_WAVES = new State<>(Section.WATER, "WavesDepth", Int.class);
		
		//TeamColorFilter
		public final static State<Int> TEAM_COLOR_FILTER_INDEX_ALLY = new State<>(Section.TEAM_COLOR_FILTER, "ColorIndexAlly", Int.class);
		public final static State<Int> TEAM_COLOR_FILTER_INDEX_CREEP = new State<>(Section.TEAM_COLOR_FILTER, "ColorIndexCreep", Int.class);
		public final static State<Int> TEAM_COLOR_FILTER_INDEX_ENEMY = new State<>(Section.TEAM_COLOR_FILTER, "ColorIndexEnemy", Int.class);
		public final static State<Int> TEAM_COLOR_FILTER_INDEX_PLAYER = new State<>(Section.TEAM_COLOR_FILTER, "ColorIndexPlayer", Int.class);
		
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
		
		public final static State<Int> FOG_COLOR_BLACK_MASK_ALPHA = new State<>(Section.FOG_OF_WAR, "FogColorBlackMaskAlpha", Int.class);
		public final static State<Int> FOG_COLOR_DARK_MASK_ALPHA = new State<>(Section.FOG_OF_WAR, "FogColorDarkMaskAlpha", Int.class);
		public final static State<Int> FOG_COLOR_FOG_MASK_ALPHA = new State<>(Section.FOG_OF_WAR, "FogColorFogMaskAlpha", Int.class);
		public final static State<Int> FOG_COLOR_VISIBLE_ALPHA = new State<>(Section.FOG_OF_WAR, "FogColorVisibleAlpha", Int.class);
		
		public final static State<ColorARGB> FOG_COLOR_BLIGHT_MASK = new State<>(Section.FOG_OF_WAR, "FogColorBlightMask", ColorARGB.class);
		
		//Minimap
		public final static State<ColorARGB> MINIMAP_BORDER_COLOR = new State<>(Section.MINIMAP, "MinimapColorBorder", ColorARGB.class);
		public final static State<Int> MINIMAP_CREEP_CAMP_THRESHOLD_LARGE = new State<>(Section.MINIMAP, "MinimapToughCampThreshold", Int.class);
		public final static State<Int> MINIMAP_CREEP_CAMP_THRESHOLD_MEDIUM = new State<>(Section.MINIMAP, "MinimapMiddleCampThreshold", Int.class);
		
		public final static State<Real> MINIMAP_CREEP_CAMP_SCALE_MEDIUM = new State<>(Section.MINIMAP, "MinimapMiddleCampScale", Real.class);
		
		public final static State<ColorARGB> MINIMAP_CREEP_CAMP_COLOR_LARGE = new State<>(Section.MINIMAP, "MinimapToughCampColor", ColorARGB.class);
		public final static State<ColorARGB> MINIMAP_CREEP_CAMP_COLOR_MEDIUM = new State<>(Section.MINIMAP, "MinimapMiddleCampColor", ColorARGB.class);
		public final static State<ColorARGB> MINIMAP_CREEP_CAMP_COLOR_SMALL = new State<>(Section.MINIMAP, "MinimapWeakCampColor", ColorARGB.class);
		
		public final static State<Real> MINIMAP_CREEP_CAMP_PULSE_SCALE = new State<>(Section.MINIMAP, "MinimapCampPulseScale", Real.class);
		
		//TargetingColors
		public final static State<ColorARGB> TARGETING_SELECTION_COLOR = new State<>(Section.TARGETING_COLORS, "SelTargetColor", ColorARGB.class);
		public final static State<ColorARGB> TARGETING_TARGET_COLOR_LIGHT = new State<>(Section.TARGETING_COLORS, "TargetLightColor", ColorARGB.class);
		
		//Glue
		public final static State<Real> GLUE_CONTROL_FADE_DUR = new State<>(Section.GLUE, "ControlFadeDuration", Real.class);
		public final static State<Real> GLUE_CONTROL_FADE_DUR_DEBUG = new State<>(Section.GLUE, "ControlFadeDurationDebug", Real.class);
		public final static State<Real> GLUE_CUSTOM_LOAD_TIMER_DURATION = new State<>(Section.GLUE, "CustomLoadTimerDuration", Real.class);
		
		//BattleNetCustomFilter
		public final static State<Coords2DI> BATTLE_NET_FILTER_LARGE = new State<>(Section.BATTLE_NET_CUSTOM_FILTER, "LargeMapRange", Coords2DI.class);
		public final static State<Coords2DI> BATTLE_NET_FILTER_MEDIUM = new State<>(Section.BATTLE_NET_CUSTOM_FILTER, "MediumMapRange", Coords2DI.class);
		public final static State<Coords2DI> BATTLE_NET_FILTER_SMALL = new State<>(Section.BATTLE_NET_CUSTOM_FILTER, "SmallMapRange", Coords2DI.class);
		
		//InfoPanel
		public final static State<Real> ATK_SPEED_FAST = new State<>(Section.INFO_PANEL, "AttackFast", Real.class);
		public final static State<Real> ATK_SPEED_NORMAL = new State<>(Section.INFO_PANEL, "AttackAverage", Real.class);
		public final static State<Real> ATK_SPEED_SLOW = new State<>(Section.INFO_PANEL, "AttackSlow", Real.class);
		public final static State<Real> ATK_SPEED_SLOWEST = new State<>(Section.INFO_PANEL, "AttackVerySlow", Real.class);
		public final static State<Int> MELEE_RANGE_MAX = new State<>(Section.INFO_PANEL, "MeleeRangeMax", Int.class);
		public final static State<Int> MOVE_SPEED_FAST = new State<>(Section.INFO_PANEL, "SpeedFast", Int.class);
		public final static State<Int> MOVE_SPEED_NORMAL = new State<>(Section.INFO_PANEL, "SpeedAverage", Int.class);
		public final static State<Int> MOVE_SPEED_SLOW = new State<>(Section.INFO_PANEL, "SpeedSlow", Int.class);
		public final static State<Int> MOVE_SPEED_SLOWEST = new State<>(Section.INFO_PANEL, "SpeedVerySlow", Int.class);
		
		//Occlusion
		public final static State<ColorARGB> OCCLUSION_MARK_COLOR = new State<>(Section.OCCLUSION, "MarkColor", ColorARGB.class);
		public final static State<Tex> OCCLUSION_MARK_TEX = new State<>(Section.OCCLUSION, "MarkImage", Tex.class);
		public final static State<ColorARGB> OCCLUSION_OCCLUDER_COLOR = new State<>(Section.OCCLUSION, "OccluderColor", ColorARGB.class);
		public final static State<Int> OCCLUSION_OCCLUDER_FADE_DUR_MS = new State<>(Section.OCCLUSION, "OccluderFadeTime", Int.class);
		public final static State<Int> OCCLUSION_STRUCTURES = new State<>(Section.OCCLUSION, "BuildingsOcclude", Int.class);  //maybe boolean?
		
		//Placement
		public final static State<ColorARGB> PLACEMENT_STRUCTURE_PAID = new State<>(Section.PLACEMENT, "PaidStructureColor", ColorARGB.class);
		public final static State<ColorARGB> PLACEMENT_STRUCTURE_UNPAID = new State<>(Section.PLACEMENT, "UnpaidStructureColor", ColorARGB.class);
		
		//PingColor
		public final static State<ColorARGB> PING_COLOR = new State<>(Section.PING_COLOR, "PingColor", ColorARGB.class);
		public final static State<ColorARGB> PING_COLOR_ADVISOR = new State<>(Section.PING_COLOR, "PingAdvisorColor", ColorARGB.class);
		public final static State<ColorARGB> PING_COLOR_ATTACK = new State<>(Section.PING_COLOR, "PingAttackColor", ColorARGB.class);
		public final static State<ColorARGB> PING_COLOR_WAYPOINT = new State<>(Section.PING_COLOR, "PingWaypointColor", ColorARGB.class);
		
		//Misc section
		public final static State<Real> FADE_BUFF_DURATION_MIN = new State<>(Section.MISC, "FadeBuffMinDuration", Real.class);
		public final static State<Real> FADE_BUFF_ALPHA_MAX = new State<>(Section.MISC, "FadeBuffMaxAlpha", Real.class);
		public final static State<Real> FADE_BUFF_ALPHA_MIN = new State<>(Section.MISC, "FadeBuffMinAlpha", Real.class);
		
		public final static State<ColorARGB> GOLD_TEXT_COLOR = new State<>(Section.MISC, "GoldTextColor", ColorARGB.class);
		public final static State<Speed> GOLD_TEXT_SPEED = new State<>(Section.MISC, "GoldTextVelocity", Speed.class);
		public final static State<Real> GOLD_TEXT_LIFESPAN = new State<>(Section.MISC, "GoldTextLifetime", Real.class);
		public final static State<Real> GOLD_TEXT_FADE_POINT = new State<>(Section.MISC, "GoldTextFadeStart", Real.class);
		
		public final static State<ColorARGB> LUMBER_TEXT_COLOR = new State<>(Section.MISC, "LumberTextColor", ColorARGB.class);
		public final static State<Speed> LUMBER_TEXT_SPEED = new State<>(Section.MISC, "LumberTextVelocity", Speed.class);
		public final static State<Real> LUMBER_TEXT_LIFESPAN = new State<>(Section.MISC, "LumberTextLifetime", Real.class);
		public final static State<Real> LUMBER_TEXT_FADE_POINT = new State<>(Section.MISC, "LumberTextFadeStart", Real.class);

		public final static State<ColorARGB> BOUNTY_TEXT_COLOR = new State<>(Section.MISC, "BountyTextColor", ColorARGB.class);
		public final static State<Speed> BOUNTY_TEXT_SPEED = new State<>(Section.MISC, "BountyTextVelocity", Speed.class);
		public final static State<Real> BOUNTY_TEXT_LIFESPAN = new State<>(Section.MISC, "BountyTextLifetime", Real.class);
		public final static State<Real> BOUNTY_TEXT_FADE_POINT = new State<>(Section.MISC, "BountyTextFadeStart", Real.class);

		public final static State<ColorARGB> MISS_TEXT_COLOR = new State<>(Section.MISC, "MissTextColor", ColorARGB.class);
		public final static State<Speed> MISS_TEXT_SPEED = new State<>(Section.MISC, "MissTextVelocity", Speed.class);
		public final static State<Real> MISS_TEXT_LIFESPAN = new State<>(Section.MISC, "MissTextLifetime", Real.class);
		public final static State<Real> MISS_TEXT_FADE_POINT = new State<>(Section.MISC, "MissTextFadeStart", Real.class);

		public final static State<ColorARGB> CRIT_STRIKE_TEXT_COLOR = new State<>(Section.MISC, "CriticalStrikeTextColor", ColorARGB.class);
		public final static State<Speed> CRIT_STRIKE_TEXT_SPEED = new State<>(Section.MISC, "CriticalStrikeTextVelocity", Speed.class);
		public final static State<Real> CRIT_STRIKE_TEXT_LIFESPAN = new State<>(Section.MISC, "CriticalStrikeTextLifetime", Real.class);
		public final static State<Real> CRIT_STRIKE_TEXT_FADE_POINT = new State<>(Section.MISC, "CriticalStrikeTextFadeStart", Real.class);

		public final static State<ColorARGB> SHADOW_STRIKE_TEXT_COLOR = new State<>(Section.MISC, "ShadowStrikeTextColor", ColorARGB.class);
		public final static State<Speed> SHADOW_STRIKE_TEXT_SPEED = new State<>(Section.MISC, "ShadowStrikeTextVelocity", Speed.class);
		public final static State<Real> SHADOW_STRIKE_TEXT_LIFESPAN = new State<>(Section.MISC, "ShadowStrikeTextLifetime", Real.class);
		public final static State<Real> SHADOW_STRIKE_TEXT_FADE_POINT = new State<>(Section.MISC, "ShadowStrikeTextFadeStart", Real.class);

		public final static State<ColorARGB> MANA_BURN_TEXT_COLOR = new State<>(Section.MISC, "ManaBurnTextColor", ColorARGB.class);
		public final static State<Speed> MANA_BURN_TEXT_SPEED = new State<>(Section.MISC, "ManaBurnTextVelocity", Speed.class);
		public final static State<Real> MANA_BURN_TEXT_LIFESPAN = new State<>(Section.MISC, "ManaBurnTextLifetime", Real.class);
		public final static State<Real> MANA_BURN_TEXT_FADE_POINT = new State<>(Section.MISC, "ManaBurnTextFadeStart", Real.class);

		public final static State<ColorARGB> BASH_TEXT_COLOR = new State<>(Section.MISC, "BashTextColor", ColorARGB.class);
		public final static State<Speed> BASH_TEXT_SPEED = new State<>(Section.MISC, "BashTextVelocity", Speed.class);
		public final static State<Real> BASH_TEXT_LIFESPAN = new State<>(Section.MISC, "BashTextLifetime", Real.class);
		public final static State<Real> BASH_TEXT_FADE_POINT = new State<>(Section.MISC, "BashTextFadeStart", Real.class);
		
		public final static State<DataList<ColorARGB>> LAVA_SPAWN_COLOR = new State<>(Section.MISC, "LavaSpawnColor", new DataTypeInfo(DataList.class, ColorARGB.class));
		
		public final static State<ColorARGB> BUTTON_COLOR_ACTIVE = new State<>(Section.MISC, "ActiveButtonColor", ColorARGB.class);
	}
	
	private List<File> _arthasIllidanFightPrefetch_files = new ArrayList<>();
	
	public void ArthasIllidanFightPrefetch_add(File file) {
		_arthasIllidanFightPrefetch_files.add(file);
		
		State<FileType> state = new State<>(Section.ARTHAS_ILLIDAN_FIGHT_PREFETCH_LIST, String.format("File%i", _arthasIllidanFightPrefetch_files.size() - 1), FileType.class);
		
		set(state, new FileType(file));
	}
	
	private List<File> _selectionCircle_files = new ArrayList<>();
	private List<File> _selectionCircle_dottedFiles = new ArrayList<>();
	private List<Int> _selectionCircle_sizes = new ArrayList<>();
	
	public void selectionCircle_add(Tex tex, Tex dottedTex, int maxSize) {
		int num = _selectionCircle_files.size();
		
		String numS = String.format("%02d", num);
		
		State<Tex> fileState = new State<>(Section.SELECTION_CIRCLE, "Texture" + numS, Tex.class);
		State<Tex> dottedFileState = new State<>(Section.SELECTION_CIRCLE, "TextureDotted" + numS, Tex.class);
		State<Int> sizeState = new State<>(Section.SELECTION_CIRCLE, "Size" + numS, Int.class);
		
		set(fileState, tex);
		set(dottedFileState, dottedTex);
		set(sizeState, Int.valueOf(maxSize));
	}
	
	public <T extends DataType> T get(State<T> state) throws Exception {
		return (T) super.get(state);
	}
	
	public <T extends DataType> void set(State<T> state, T val) {
		super.set(state, val);
	}
	
	@Override
	public void read(File file) throws IOException {
		super.read(file);
	}
	
	@Override
	public void write(File file) throws IOException {
		super.write(file);
	}
	
	public MiscDataTXT() {
		super();
	}
}