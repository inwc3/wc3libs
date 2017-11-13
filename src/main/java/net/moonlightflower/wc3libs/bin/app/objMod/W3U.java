package net.moonlightflower.wc3libs.bin.app.objMod;

import net.moonlightflower.wc3libs.bin.MetaState;
import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.slk.app.objs.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * unit modifications file for wrapping war3map.w3u
 */
public class W3U extends ObjMod {
	public final static File GAME_PATH = new File("war3map.w3u");
	public final static File CAMPAIGN_PATH = new File("war3campaign.w3u");
	
	public static class States {
		static public class State<T extends DataType> extends MetaState<T> {
			private static List<State> _values = new ArrayList<>();
			
			public static List<State> values() {
				return _values;
			}
			
			public State(String idString, DataTypeInfo typeInfo, T defVal) {
				super(idString, typeInfo, defVal);
				
				_values.add(this);
			}
			
			public State(String idString, Class<T> type, T defVal) {
				this(idString, new DataTypeInfo(type), defVal);
			}
			
			public State(String idString, DataTypeInfo typeInfo) {
				this(idString, typeInfo, null);
			}
			
			public State(String idString, Class<T> type) {
				this(idString, new DataTypeInfo(type), null);
			}
		}
		
		public static List<State> values() {
			return State.values();
		}

		public final static State<AbilCode> ABIL_AUTO = new State<>("udaa", AbilCode.class);
		public final static State<DataList<AbilId>> ABIL_HERO = new State<>("uhab", new DataTypeInfo(DataList.class, AbilId.class));
		public final static State<DataList<AbilId>> ABIL_NORMAL = new State<>("uabi", new DataTypeInfo(DataList.class, AbilId.class));

		public final static State<Real> ART_ANIM_WALK_SPD = new State<>("uwal", Real.class);
		public final static State<Real> ART_ANIM_RUN_SPD = new State<>("urun", Real.class);
		public final static State<Real> ART_ANIM_CAST_PT = new State<>("ucpt", Real.class);
		public final static State<Real> ART_ANIM_CAST_BACKSWING = new State<>("ucbs", Real.class);
		public final static State<Real> ART_ANIM_BLEND = new State<>("uble", Real.class);
		public final static State<DataList<Wc3String>> ART_ANIM_PROPS = new State<>("uani", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<DataList<Wc3String>> ART_ANIM_PROPS_ATTACH = new State<>("uaap", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<DataList<Wc3String>> ART_ANIM_PROPS_ATTACH_LINK = new State<>("ualp", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<DataList<Wc3String>> ART_ANIM_PROPS_BONE = new State<>("ubpr", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<Int> ART_BUTTON_POS_X = new State<>("ubpx", Int.class);
		public final static State<Int> ART_BUTTON_POS_Y = new State<>("ubpy", Int.class);
		public final static State<DataList<Wc3String>> ART_CASTER_UPGRADE_NAMES = new State<>("ucun", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<DataList<Wc3String>> ART_CASTER_UPGRADE_TOOLTIP = new State<>("ucut", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<Int> ART_COLOR_BLUE = new State<>("uclb", Int.class);
		public final static State<Int> ART_COLOR_GREEN = new State<>("uclg", Int.class);
		public final static State<Int> ART_COLOR_RED = new State<>("uclr", Int.class);
		public final static State<Bool> ART_CUSTOM_TEAM_COLOR = new State<>("utcc", Bool.class);
		public final static State<Real> ART_DEATH_TIME = new State<>("udtm", Real.class);
		public final static State<Real> ART_ELEV_RAD = new State<>("uerd", Real.class);
		public final static State<Int> ART_ELEV_PTS = new State<>("uept", Int.class);
		public final static State<Bool> ART_FAT_LINE_OF_SIGHT = new State<>("ulos", Bool.class);
		public final static State<Real> ART_FOG_RADIUS = new State<>("ufrd", Real.class);
		public final static State<Icon> ART_ICON = new State<>("uico", Icon.class);
		public final static State<Icon> ART_ICON_CASTER_UPGRADE = new State<>("ucua", Icon.class);
		public final static State<Icon> ART_ICON_SCORE_SCREEN = new State<>("ussi", Icon.class);
		public final static State<Real> ART_IMPACT_Z_SWIM = new State<>("uisz", Real.class);
		public final static State<Real> ART_LAUNCH_X = new State<>("ulpx", Real.class);
		public final static State<Real> ART_LAUNCH_Y = new State<>("ulpy", Real.class);
		public final static State<Real> ART_LAUNCH_Z = new State<>("ulpz", Real.class);
		public final static State<Real> ART_LAUNCH_Z_SWIM = new State<>("ulsz", Real.class);
		public final static State<Real> ART_MODEL_SCALE = new State<>("usca", Real.class);
		public final static State<Real> ART_OCCLUSION_HEIGHT = new State<>("uocc", Real.class);
		public final static State<Int> ART_ORIENT_INTERP = new State<>("uori", Int.class);
		public final static State<Real> ART_PITCH_MAX = new State<>("umxp", Real.class);
		public final static State<Real> ART_PROP_WINDOW = new State<>("uprw", Real.class);
		public final static State<Real> ART_ROLL_MAX = new State<>("umxr", Real.class);
		public final static State<Bool> ART_SCALE_MISSILES = new State<>("uscb", Bool.class);
		public final static State<Bool> ART_SHADOW_ON_WATER = new State<>("ushr", Bool.class);
		public final static State<Real> ART_SELECTION_HEIGHT = new State<>("uslz", Real.class);
		public final static State<Real> ART_SELECTION_SCALE = new State<>("ussc", Real.class);
		public final static State<Bool> ART_SELECTION_SHOW_ON_WATER = new State<>("usew", Bool.class);
		public final static State<Real> ART_SHADOW_HEIGHT = new State<>("ushh", Real.class);
		public final static State<Real> ART_SHADOW_WIDTH = new State<>("ushw", Real.class);
		public final static State<ShadowTex> ART_SHADOW_STRUCTURE = new State<>("ushb", ShadowTex.class);
		public final static State<ShadowImage> ART_SHADOW_UNIT = new State<>("ushu", ShadowImage.class);
		public final static State<Real> ART_SHADOW_X = new State<>("ushx", Real.class);
		public final static State<Real> ART_SHADOW_Y = new State<>("ushy", Real.class);
		public final static State<DataList<Model>> ART_SPECIALS = new State<>("uspa", new DataTypeInfo(DataList.class, Model.class));
		public final static State<DataList<Model>> ART_TARGETS = new State<>("utaa", new DataTypeInfo(DataList.class, Model.class));
		public final static State<TeamColor> ART_TEAM_COLOR = new State<>("utco", TeamColor.class);
		public final static State<UberSplatId> ART_UBERSPLAT = new State<>("uubs", UberSplatId.class);
		public final static State<VersionFlags> ART_VERSION_FLAGS = new State<>("uver", VersionFlags.class);
		
		public final static State<Int> COMBAT_ARMOR_BASE = new State<>("udef", Int.class);
		public final static State<DefType> COMBAT_ARMOR_TYPE = new State<>("udty", DefType.class);
		public final static State<DeathType> COMBAT_DEATH_TYPE = new State<>("udea", DeathType.class);
		public final static State<Real> COMBAT_RANGE_ACQUISITION = new State<>("uacq", Real.class);
		public final static State<Int> COMBAT_RANGE_MIN = new State<>("uamn", Int.class);
		public final static State<DataList<CombatTarget>> COMBAT_TARGETD_AS = new State<>("utar", new DataTypeInfo(DataList.class, CombatTarget.class));
		public final static State<AttackBits> COMBAT_WEAPS_ON = new State<>("uaen", AttackBits.class);
		
		
		public final static State<Int> COMBAT_ATTACK1_AREA_FULL = new State<>("ua1f", Int.class);
		public final static State<Int> COMBAT_ATTACK1_AREA_MEDIUM = new State<>("ua1h", Int.class);
		public final static State<Real> COMBAT_ATTACK1_AREA_MEDIUM_DMG_FACTOR = new State<>("uhd1", Real.class);
		public final static State<Int> COMBAT_ATTACK1_AREA_SMALL = new State<>("ua1q", Int.class);
		public final static State<Real> COMBAT_ATTACK1_AREA_SMALL_DMG_FACTOR = new State<>("uqd1", Real.class);
		public final static State<DataList<CombatTarget>> COMBAT_ATTACK1_AREA_TARGS = new State<>("ua1p", new DataTypeInfo(DataList.class, CombatTarget.class));
		public final static State<AttackType> COMBAT_ATTACK1_ATK_TYPE = new State<>("ua1t", AttackType.class);
		public final static State<Real> COMBAT_ATTACK1_BACKSWING = new State<>("ubs1", Real.class);
		public final static State<Real> COMBAT_ATTACK1_COOLDOWN = new State<>("ua1c", Real.class);
		public final static State<Real> COMBAT_ATTACK1_MISSILE_ARC = new State<>("uma1", Real.class);
		public final static State<Model> COMBAT_ATTACK1_MISSILE_ART = new State<>("ua1m", Model.class);
		public final static State<Bool> COMBAT_ATTACK1_MISSILE_HOMING = new State<>("umh1", Bool.class);
		public final static State<Int> COMBAT_ATTACK1_MISSILE_SPEED = new State<>("ua1z", Int.class);
		public final static State<Int> COMBAT_ATTACK1_DMG_BASE = new State<>("ua1b", Int.class);
		public final static State<Int> COMBAT_ATTACK1_DMG_DICE_COUNT = new State<>("ua1d", Int.class);
		public final static State<Int> COMBAT_ATTACK1_DMG_DICE_SIDES = new State<>("ua1s", Int.class);
		public final static State<Real> COMBAT_ATTACK1_DMG_LOSS_FACTOR = new State<>("udl1", Real.class);
		public final static State<Real> COMBAT_ATTACK1_DMG_PT = new State<>("udp1", Real.class);
		public final static State<Real> COMBAT_ATTACK1_DMG_SPILL_DIST = new State<>("usd1", Real.class);
		public final static State<Real> COMBAT_ATTACK1_DMG_SPILL_RAD = new State<>("usr1", Real.class);
		public final static State<Int> COMBAT_ATTACK1_DMG_UP = new State<>("udu1", Int.class);
		public final static State<Int> COMBAT_ATTACK1_RANGE = new State<>("ua1r", Int.class);
		public final static State<Real> COMBAT_ATTACK1_RANGE_BUF = new State<>("urb1", Real.class);
		public final static State<Bool> COMBAT_ATTACK1_SHOW_UI = new State<>("uwu1", Bool.class);
		public final static State<DataList<CombatTarget>> COMBAT_ATTACK1_TARGS = new State<>("ua1g", new DataTypeInfo(DataList.class, CombatTarget.class));
		public final static State<Int> COMBAT_ATTACK1_TARGS_MAX = new State<>("utc1", Int.class);
		public final static State<CombatSound> COMBAT_ATTACK1_WEAPON_SOUND = new State<>("ucs1", CombatSound.class);
		public final static State<WeaponType> COMBAT_ATTACK1_WEAPON_TYPE = new State<>("ua1w", WeaponType.class);

		public final static State<Int> COMBAT_ATTACK2_AREA_FULL = new State<>("ua2f", Int.class);
		public final static State<Int> COMBAT_ATTACK2_AREA_MEDIUM = new State<>("ua2h", Int.class);
		public final static State<Real> COMBAT_ATTACK2_AREA_MEDIUM_DMG_FACTOR = new State<>("uhd2", Real.class);
		public final static State<Int> COMBAT_ATTACK2_AREA_SMALL = new State<>("ua2q", Int.class);
		public final static State<Real> COMBAT_ATTACK2_AREA_SMALL_DMG_FACTOR = new State<>("uqd2", Real.class);
		public final static State<DataList<CombatTarget>> COMBAT_ATTACK2_AREA_TARGS = new State<>("ua2p", new DataTypeInfo(DataList.class, CombatTarget.class));
		public final static State<AttackType> COMBAT_ATTACK2_ATK_TYPE = new State<>("ua2t", AttackType.class);
		public final static State<Real> COMBAT_ATTACK2_BACKSWING = new State<>("ubs2", Real.class);
		public final static State<Real> COMBAT_ATTACK2_COOLDOWN = new State<>("ua2c", Real.class);
		public final static State<Int> COMBAT_ATTACK2_DMG_BASE = new State<>("ua2b", Int.class);
		public final static State<Int> COMBAT_ATTACK2_DMG_DICE_COUNT = new State<>("ua2d", Int.class);
		public final static State<Int> COMBAT_ATTACK2_DMG_DICE_SIDES = new State<>("ua2s", Int.class);
		public final static State<Real> COMBAT_ATTACK2_DMG_LOSS_FACTOR = new State<>("udl2", Real.class);
		public final static State<Real> COMBAT_ATTACK2_DMG_PT = new State<>("udp2", Real.class);
		public final static State<Real> COMBAT_ATTACK2_DMG_SPILL_DIST = new State<>("usd2", Real.class);
		public final static State<Real> COMBAT_ATTACK2_DMG_SPILL_RAD = new State<>("usr2", Real.class);
		public final static State<Int> COMBAT_ATTACK2_DMG_UP = new State<>("udu2", Int.class);
		public final static State<Real> COMBAT_ATTACK2_MISSILE_ARC = new State<>("uma2", Real.class);
		public final static State<Model> COMBAT_ATTACK2_MISSILE_ART = new State<>("ua2m", Model.class);
		public final static State<Bool> COMBAT_ATTACK2_MISSILE_HOMING = new State<>("umh2", Bool.class);
		public final static State<Int> COMBAT_ATTACK2_MISSILE_SPEED = new State<>("ua2z", Int.class);
		public final static State<Int> COMBAT_ATTACK2_RANGE = new State<>("ua2r", Int.class);
		public final static State<Real> COMBAT_ATTACK2_RANGE_BUF = new State<>("urb2", Real.class);
		public final static State<Bool> COMBAT_ATTACK2_SHOW_UI = new State<>("uwu2", Bool.class);
		public final static State<DataList<CombatTarget>> COMBAT_ATTACK2_TARGS = new State<>("ua2g", new DataTypeInfo(DataList.class, CombatTarget.class));
		public final static State<Int> COMBAT_ATTACK2_TARGS_MAX = new State<>("utc2", Int.class);
		public final static State<CombatSound> COMBAT_ATTACK2_WEAPON_SOUND = new State<>("ucs2", CombatSound.class);
		public final static State<WeaponType> COMBAT_ATTACK2_WEAPON_TYPE = new State<>("ua2w", WeaponType.class);

		public final static State<Int> DATA_BOUNTY_LUMBER_BASE = new State<>("ulba", Int.class);
		public final static State<Int> DATA_BOUNTY_LUMBER_DICE_COUNT = new State<>("ulbd", Int.class);
		public final static State<Int> DATA_BOUNTY_LUMBER_DICE_SIDES = new State<>("ulbs", Int.class);
		public final static State<Int> DATA_BOUNTY_GOLD_BASE = new State<>("ubba", Int.class);
		public final static State<Int> DATA_BOUNTY_GOLD_DICE_SIDES = new State<>("ubsi", Int.class);
		public final static State<Int> DATA_BOUNTY_GOLD_DICE_COUNT = new State<>("ubdi", Int.class);
		public final static State<Int> DATA_BUILD_TIME = new State<>("ubld", Int.class);
		public final static State<Bool> DATA_CAN_FLEE = new State<>("ufle", Bool.class);
		public final static State<Int> DATA_CARGO_USED = new State<>("ucar", Int.class);
		public final static State<UnitClass> DATA_CLASSES = new State<>("utyp", UnitClass.class);
		public final static State<Real> DATA_COLLISION_SIZE = new State<>("ucol", Real.class);
		public final static State<Int> DATA_COSTS_GOLD = new State<>("ugol", Int.class);
		public final static State<Int> DATA_COSTS_GOLD_REPAIR = new State<>("ugor", Int.class);
		public final static State<Int> DATA_COSTS_LUMBER = new State<>("ulum", Int.class);
		public final static State<Int> DATA_COSTS_LUMBER_REPAIR = new State<>("ulur", Int.class);
		public final static State<Int> DATA_FORMATION = new State<>("ufor", Int.class);
		public final static State<Int> DATA_HERO_ATTR_AGI_BASE = new State<>("uagi", Int.class);
		public final static State<Real> DATA_HERO_ATTR_AGI_UP = new State<>("uagp", Real.class);
		public final static State<Int> DATA_HERO_ATTR_INT_BASE = new State<>("uint", Int.class);
		public final static State<Real> DATA_HERO_ATTR_INT_UP = new State<>("uinp", Real.class);
		public final static State<AttributeType> DATA_HERO_ATTR_PRIMARY = new State<>("upra", AttributeType.class);
		public final static State<Int> DATA_HERO_ATTR_STR_BASE = new State<>("ustr", Int.class);
		public final static State<Real> DATA_HERO_ATTR_STR_UP = new State<>("ustp", Real.class);
		public final static State<Bool> DATA_HERO_HIDE_BAR = new State<>("uhhb", Bool.class);
		public final static State<Bool> DATA_HERO_HIDE_ON_MINIMAP = new State<>("uhhm", Bool.class);
		public final static State<Bool> DATA_HERO_HIDE_DEATH_MSG = new State<>("uhhd", Bool.class);
		public final static State<Int> DATA_HERO_NAMES_COUNT = new State<>("upru", Int.class);
		public final static State<Bool> DATA_HIDE_ON_MINIMAP = new State<>("uhom", Bool.class);
		public final static State<Bool> DATA_IS_STRUCTURE = new State<>("ubdg", Bool.class);
		public final static State<Int> DATA_LEVEL = new State<>("ulev", Int.class);
		public final static State<Int> DATA_LIFE_MAX = new State<>("uhpm", Int.class);
		public final static State<Real> DATA_LIFE_REGEN = new State<>("uhpr", Real.class);
		public final static State<RegenType> DATA_LIFE_REGEN_TYPE = new State<>("uhrt", RegenType.class);
		public final static State<Int> DATA_MANA_INITIAL = new State<>("umpi", Int.class);
		public final static State<Int> DATA_MANA_MAX = new State<>("umpm", Int.class);
		public final static State<Real> DATA_MANA_REGEN = new State<>("umpr", Real.class);
		public final static State<Bool> DATA_NEUTRAL_STRUCTURE_RANDOMED = new State<>("unbr", Bool.class);
		public final static State<Bool> DATA_NEUTRAL_STRUCTURE_SHOW_ON_MINIMAP = new State<>("unbm", Bool.class);
		public final static State<Int> DATA_POINT_VALUE = new State<>("upoi", Int.class);
		public final static State<Int> DATA_PRIO = new State<>("upri", Int.class);
		public final static State<UnitRace> DATA_RACE = new State<>("urac", UnitRace.class);
		public final static State<Int> DATA_REPAIR_TIME = new State<>("urtm", Int.class);
		public final static State<Int> DATA_SIGHT_RANGE_DAY = new State<>("usid", Int.class);
		public final static State<Int> DATA_SIGHT_RANGE_NIGHT = new State<>("usin", Int.class);
		public final static State<Bool> DATA_SLEEPS = new State<>("usle", Bool.class);
		public final static State<Int> DATA_STOCK_INITIAL = new State<>("usst", Int.class);
		public final static State<Int> DATA_STOCK_MAX = new State<>("usma", Int.class);
		public final static State<Int> DATA_STOCK_REGEN_INTERVAL = new State<>("usrg", Int.class);
		public final static State<Bool> DATA_STRUCTURE_CAN_BUILD_ON = new State<>("ucbo", Bool.class);
		public final static State<Bool> DATA_STRUCTURE_IS_BUILD_ON = new State<>("uibo", Bool.class);
		public final static State<Int> DATA_SUPPLY_PRODUCED = new State<>("ufma", Int.class);
		public final static State<Int> DATA_SUPPLY_USED = new State<>("ufoo", Int.class);
		
		public final static State<Bool> EDITOR_CLASS_CAMPAIGN = new State<>("ucam", Bool.class);
		public final static State<Bool> EDITOR_CLASS_SPECIAL = new State<>("uspe", Bool.class);
		public final static State<Bool> EDITOR_CLICK_HELPER = new State<>("uuch", Bool.class);
		public final static State<Bool> EDITOR_DISPLAY_AS_NEUTRAL_HOSTILE = new State<>("uhos", Bool.class);
		public final static State<Bool> EDITOR_DROP_ITEMS = new State<>("udro", Bool.class);
		public final static State<Bool> EDITOR_PLACEABLE = new State<>("uine", Bool.class);
		public final static State<Bool> EDITOR_TILESET_SPECIFIC = new State<>("utss", Bool.class);
		public final static State<DataList<Tileset>> EDITOR_TILESETS = new State<>("util", new DataTypeInfo(DataList.class, Tileset.class));

		public final static State<Real> MOVEMENT_HEIGHT = new State<>("umvh", Real.class);
		public final static State<Real> MOVEMENT_HEIGHT_MIN = new State<>("umvf", Real.class);
		public final static State<Int> MOVEMENT_REPULSE_GROUPS = new State<>("urpg", Int.class);
		public final static State<Int> MOVEMENT_REPULSE_PARAM = new State<>("urpp", Int.class);
		public final static State<Int> MOVEMENT_REPULSE_PRIO = new State<>("urpr", Int.class);
		public final static State<Bool> MOVEMENT_REPULSE_USED = new State<>("urpo", Bool.class);
		public final static State<Int> MOVEMENT_SPEED = new State<>("umvs", Int.class);
		public final static State<Int> MOVEMENT_SPEED_MAX = new State<>("umas", Int.class);
		public final static State<Int> MOVEMENT_SPEED_MIN = new State<>("umis", Int.class);
		public final static State<Real> MOVEMENT_TURN_RATE = new State<>("umvr", Real.class);
		public final static State<MoveType> MOVEMENT_TYPE = new State<>("umvt", MoveType.class);

		public final static State<PathingTex> PATH_PATHING_TEX = new State<>("upat", PathingTex.class);
		public final static State<Real> PATH_STRUCTURE_AI_PLACE_RAD = new State<>("uabr", Real.class);
		public final static State<AIPlaceType> PATH_STRUCTURE_AI_PLACE_TYPE = new State<>("uabt", AIPlaceType.class);
		public final static State<DataList<PathingPrevent>> PATH_STRUCTURE_PREVENTED_PLACE = new State<>("upap", new DataTypeInfo(DataList.class, PathingPrevent.class));
		public final static State<Real> PATH_STRUCTURE_REQUIRE_WATER_RAD = new State<>("upaw", Real.class);
		public final static State<DataList<PathingRequire>> PATH_STRUCTURE_REQUIRED_PLACE = new State<>("upar", new DataTypeInfo(DataList.class, PathingRequire.class));

		public final static State<SoundLabel> SOUND_BUILD = new State<>("ubsl", SoundLabel.class);
		public final static State<Int> SOUND_LOOP_FADE_OUT = new State<>("ulfo", Int.class);
		public final static State<Int> SOUND_LOOP_FADE_IN = new State<>("ulfi", Int.class);
		public final static State<SoundLabel> SOUND_MOVE = new State<>("umsl", SoundLabel.class);
		public final static State<SoundLabel> SOUND_RANDOM = new State<>("ursl", SoundLabel.class);
		public final static State<UnitSound> SOUND_SET = new State<>("usnd", UnitSound.class);

		public final static State<DataList<UnitId>> TECH_BUILDS = new State<>("ubui", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<UnitId>> TECH_DEPS_OR = new State<>("udep", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<ItemId>> TECH_ITEMS_PRODUCED = new State<>("umki", new DataTypeInfo(DataList.class, ItemId.class));
		public final static State<DataList<ItemId>> TECH_ITEMS_SOLD = new State<>("usei", new DataTypeInfo(DataList.class, ItemId.class));
		public final static State<DataList<TechId>> TECH_REQS = new State<>("ureq", new DataTypeInfo(DataList.class, TechId.class));
		public final static State<DataList<TechId>> TECH_REQS1 = new State<>("urq1", new DataTypeInfo(DataList.class, TechId.class));
		public final static State<DataList<TechId>> TECH_REQS2 = new State<>("urq2", new DataTypeInfo(DataList.class, TechId.class));
		public final static State<DataList<TechId>> TECH_REQS3 = new State<>("urq3", new DataTypeInfo(DataList.class, TechId.class));
		public final static State<DataList<TechId>> TECH_REQS4 = new State<>("urq4", new DataTypeInfo(DataList.class, TechId.class));
		public final static State<DataList<TechId>> TECH_REQS5 = new State<>("urq5", new DataTypeInfo(DataList.class, TechId.class));
		public final static State<DataList<TechId>> TECH_REQS6 = new State<>("urq6", new DataTypeInfo(DataList.class, TechId.class));
		public final static State<DataList<TechId>> TECH_REQS7 = new State<>("urq7", new DataTypeInfo(DataList.class, TechId.class));
		public final static State<DataList<TechId>> TECH_REQS8 = new State<>("urq8", new DataTypeInfo(DataList.class, TechId.class));
		public final static State<DataList<Int>> TECH_REQS_COUNT = new State<>("urqa", new DataTypeInfo(DataList.class, Int.class));
		public final static State<DataList<UpgradeId>> TECH_RESEARCHES = new State<>("ures", new DataTypeInfo(DataList.class, UpgradeId.class));
		public final static State<DataList<UnitId>> TECH_REVIVED_AT = new State<>("urva", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<Bool> TECH_REVIVES = new State<>("urev", Bool.class);
		public final static State<DataList<UnitId>> TECH_UNITS_SOLD = new State<>("useu", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<UnitId>> TECH_UNITS_TAINED = new State<>("utra", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<UnitId>> TECH_UPGRADED_TO = new State<>("uupt", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<UpgradeId>> TECH_UPGRADES = new State<>("upgr", new DataTypeInfo(DataList.class, UpgradeId.class));

		public final static State<Wc3String> TEXT_DESCRIPTION = new State<>("ides", Wc3String.class);
		public final static State<Wc3String> TEXT_EDITOR_SUFFIX = new State<>("unsf", Wc3String.class);
		public final static State<DataList<Wc3String>> TEXT_HERO_PROPER_NAMES = new State<>("upro", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<Char> TEXT_HOTKEY = new State<>("uhot", Char.class);
		public final static State<Wc3String> TEXT_NAME = new State<>("unam", Wc3String.class);
		public final static State<Wc3String> TEXT_TOOLTIP = new State<>("utip", Wc3String.class);
		public final static State<Wc3String> TEXT_TOOLTIP_AWAKEN = new State<>("uawt", Wc3String.class);
		public final static State<Wc3String> TEXT_TOOLTIP_REVIVE = new State<>("utpr", Wc3String.class);
		public final static State<Wc3String> TEXT_TOOLTIP_UBER = new State<>("utub", Wc3String.class);
	}

	public static class Unit extends ObjMod.Obj {
		public Unit(ObjId id, ObjId baseId) {
			super(id, baseId);
		}
	}
	
	/*private Map<ObjId, Unit> _objs = new LinkedHashMap<>();

	@Override
	public Map<ObjId, Unit> getObjs() {
		return _objs;
	}
	
	public Map<ObjId, Unit> getUnits() {
		return getObjs();
	}
	
	public Unit getUnit(ObjId id) {
		return getObjs().get(id);
	}
	
	private void addObj(Unit val) {
		_objs.put(val.getId(), val);
	}
	
	@Override
	public Unit addObj(ObjId id, ObjId baseId) {
		if (getObjs().containsKey(id)) return getObjs().get(id);
		
		Unit obj = new Unit(id, baseId);
		
		addObj(obj);
		
		return obj;
	}*/

	@Override
	public Collection<File> getSLKs() {
		return Arrays.asList(UnitAbilsSLK.GAME_USE_PATH, UnitBalanceSLK.GAME_USE_PATH, UnitDataSLK.GAME_USE_PATH, UnitUISLK.GAME_USE_PATH, UnitWeaponsSLK.GAME_USE_PATH);
	}
	
	@Override
	public Collection<File> getNecessarySLKs() {
		return Arrays.asList(UnitAbilsSLK.GAME_USE_PATH, UnitBalanceSLK.GAME_USE_PATH, UnitDataSLK.GAME_USE_PATH, UnitUISLK.GAME_USE_PATH, UnitWeaponsSLK.GAME_USE_PATH);
	}
	
	public void read(InputStream inStream) throws IOException {
		ObjMod other = new ObjMod();
		
		other.read(inStream, false);
		
		merge(other);
	}
	
	public W3U(InputStream inStream) throws IOException {
		super(inStream, false);
	}
	
	public W3U(File file) throws Exception {
		super(file, false);
	}
	
	public W3U() {
		super();
	}

	public static W3U ofMapFile(File mapFile) throws Exception {
		if (!mapFile.exists()) throw new IOException(String.format("file %s does not exist", mapFile));
		
		MpqPort.Out port = new JMpqPort.Out();
		
		port.add(GAME_PATH);
		
		MpqPort.Out.Result portResult = port.commit(mapFile);

		if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract w3u file");

		InputStream inStream = portResult.getInputStream(GAME_PATH);
		
		return new W3U(inStream);
	}
}
