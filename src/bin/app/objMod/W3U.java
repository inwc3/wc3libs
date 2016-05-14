package bin.app.objMod;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bin.MetaState;
import bin.ObjMod;
import dataTypes.app.AIPlaceType;
import dataTypes.app.AbilCode;
import dataTypes.app.AbilList;
import dataTypes.app.AttackBits;
import dataTypes.app.AttackType;
import dataTypes.app.AttributeType;
import dataTypes.app.Bool;
import dataTypes.app.Char;
import dataTypes.app.CombatSound;
import dataTypes.app.DeathType;
import dataTypes.app.DefType;
import dataTypes.app.Icon;
import dataTypes.app.Int;
import dataTypes.app.IntList;
import dataTypes.app.ItemList;
import dataTypes.app.Model;
import dataTypes.app.ModelList;
import dataTypes.app.MoveType;
import dataTypes.app.PathingPreventList;
import dataTypes.app.PathingRequireList;
import dataTypes.app.PathingTex;
import dataTypes.app.Real;
import dataTypes.app.RegenType;
import dataTypes.app.ShadowImage;
import dataTypes.app.ShadowTex;
import dataTypes.app.SoundLabel;
import dataTypes.app.StringList;
import dataTypes.app.TargetList;
import dataTypes.app.TeamColor;
import dataTypes.app.TechList;
import dataTypes.app.TilesetList;
import dataTypes.app.UberSplatId;
import dataTypes.app.UnitClass;
import dataTypes.app.UnitList;
import dataTypes.app.UnitRace;
import dataTypes.app.UnitSound;
import dataTypes.app.UpgradeList;
import dataTypes.app.VersionFlags;
import dataTypes.app.WeaponType;
import misc.ObjId;
import slk.SLKState;

/**
 * unit modifications file for wrapping war3map.w3u
 */
public class W3U extends ObjMod {
	public final static String GAME_PATH = "war3map.w3u";
	
	public static class States {
		static public class State<T> extends MetaState<T> {
			private static List<State> _values = new ArrayList<>();
			
			public static List<State> values() {
				return _values;
			}
			
			public State(String idString) {
				this(idString, null);
			}

			public State(String idString, T defVal) {
				super(idString, defVal);
				
				_values.add(this);
			}
		}
		
		public static List<State> values() {
			return State.values();
		}

		public final static State<AbilCode> ABIL_AUTO = new State<>("udaa");
		public final static State<AbilList> ABIL_HERO = new State<>("uhab");
		public final static State<AbilList> ABIL_NORMAL = new State<>("uabi");

		public final static State<Real> ART_ANIM_WALK_SPD = new State<>("uwal");
		public final static State<Real> ART_ANIM_RUN_SPD = new State<>("urun");
		public final static State<Real> ART_ANIM_CAST_PT = new State<>("ucpt");
		public final static State<Real> ART_ANIM_CAST_BACKSWING = new State<>("ucbs");
		public final static State<Real> ART_ANIM_BLEND = new State<>("uble");
		public final static State<StringList> ART_ANIM_PROPS = new State<>("uani");
		public final static State<StringList> ART_ANIM_PROPS_ATTACH = new State<>("uaap");
		public final static State<StringList> ART_ANIM_PROPS_ATTACH_LINK = new State<>("ualp");
		public final static State<StringList> ART_ANIM_PROPS_BONE = new State<>("ubpr");
		public final static State<Int> ART_BUTTON_POS_X = new State<>("ubpx");
		public final static State<Int> ART_BUTTON_POS_Y = new State<>("ubpy");
		public final static State<StringList> ART_CASTER_UPGRADE_NAMES = new State<>("ucun");
		public final static State<StringList> ART_CASTER_UPGRADE_TOOLTIP = new State<>("ucut");
		public final static State<Int> ART_COLOR_BLUE = new State<>("uclb");
		public final static State<Int> ART_COLOR_GREEN = new State<>("uclg");
		public final static State<Int> ART_COLOR_RED = new State<>("uclr");
		public final static State<Bool> ART_CUSTOM_TEAM_COLOR = new State<>("utcc");
		public final static State<Real> ART_DEATH_TIME = new State<>("udtm");
		public final static State<Real> ART_ELEV_RAD = new State<>("uerd");
		public final static State<Int> ART_ELEV_PTS = new State<>("uept");
		public final static State<Bool> ART_FAT_LINE_OF_SIGHT = new State<>("ulos");
		public final static State<Real> ART_FOG_RADIUS = new State<>("ufrd");
		public final static State<Icon> ART_ICON = new State<>("uico");
		public final static State<Icon> ART_ICON_CASTER_UPGRADE = new State<>("ucua");
		public final static State<Icon> ART_ICON_SCORE_SCREEN = new State<>("ussi");
		public final static State<Real> ART_IMPACT_Z_SWIM = new State<>("uisz");
		public final static State<Real> ART_LAUNCH_X = new State<>("ulpx");
		public final static State<Real> ART_LAUNCH_Y = new State<>("ulpy");
		public final static State<Real> ART_LAUNCH_Z = new State<>("ulpz");
		public final static State<Real> ART_LAUNCH_Z_SWIM = new State<>("ulsz");
		public final static State<Real> ART_MODEL_SCALE = new State<>("usca");
		public final static State<Real> ART_OCCLUSION_HEIGHT = new State<>("uocc");
		public final static State<Int> ART_ORIENT_INTERP = new State<>("uori");
		public final static State<Real> ART_PITCH_MAX = new State<>("umxp");
		public final static State<Real> ART_PROP_WINDOW = new State<>("uprw");
		public final static State<Real> ART_ROLL_MAX = new State<>("umxr");
		public final static State<Bool> ART_SCALE_MISSILES = new State<>("uscb");
		public final static State<Bool> ART_SHADOW_ON_WATER = new State<>("ushr");
		public final static State<Real> ART_SELECTION_HEIGHT = new State<>("uslz");
		public final static State<Real> ART_SELECTION_SCALE = new State<>("ussc");
		public final static State<Bool> ART_SELECTION_SHOW_ON_WATER = new State<>("usew");
		public final static State<Real> ART_SHADOW_HEIGHT = new State<>("ushh");
		public final static State<Real> ART_SHADOW_WIDTH = new State<>("ushw");
		public final static State<ShadowTex> ART_SHADOW_STRUCTURE = new State<>("ushb");
		public final static State<ShadowImage> ART_SHADOW_UNIT = new State<>("ushu");
		public final static State<Real> ART_SHADOW_X = new State<>("ushx");
		public final static State<Real> ART_SHADOW_Y = new State<>("ushy");
		public final static State<ModelList> ART_SPECIALS = new State<>("uspa");
		public final static State<ModelList> ART_TARGETS = new State<>("utaa");
		public final static State<TeamColor> ART_TEAM_COLOR = new State<>("utco");
		public final static State<UberSplatId> ART_UBERSPLAT = new State<>("uubs");
		public final static State<VersionFlags> ART_VERSION_FLAGS = new State<>("uver");
		
		public final static State<Int> COMBAT_ARMOR_BASE = new State<>("udef");
		public final static State<DefType> COMBAT_ARMOR_TYPE = new State<>("udty");
		public final static State<DeathType> COMBAT_DEATH_TYPE = new State<>("udea");
		public final static State<Real> COMBAT_RANGE_ACQUISITION = new State<>("uacq");
		public final static State<Int> COMBAT_RANGE_MIN = new State<>("uamn");
		public final static State<TargetList> COMBAT_TARGETD_AS = new State<>("utar");
		public final static State<AttackBits> COMBAT_WEAPS_ON = new State<>("uaen");
		
		
		public final static State<Int> COMBAT_ATTACK1_AREA_FULL = new State<>("ua1f");
		public final static State<Int> COMBAT_ATTACK1_AREA_MEDIUM = new State<>("ua1h");
		public final static State<Real> COMBAT_ATTACK1_AREA_MEDIUM_DMG_FACTOR = new State<>("uhd1");
		public final static State<Int> COMBAT_ATTACK1_AREA_SMALL = new State<>("ua1q");
		public final static State<Real> COMBAT_ATTACK1_AREA_SMALL_DMG_FACTOR = new State<>("uqd1");
		public final static State<TargetList> COMBAT_ATTACK1_AREA_TARGS = new State<>("ua1p");
		public final static State<AttackType> COMBAT_ATTACK1_ATK_TYPE = new State<>("ua1t");
		public final static State<Real> COMBAT_ATTACK1_BACKSWING = new State<>("ubs1");
		public final static State<Real> COMBAT_ATTACK1_COOLDOWN = new State<>("ua1c");
		public final static State<Real> COMBAT_ATTACK1_MISSILE_ARC = new State<>("uma1");
		public final static State<Model> COMBAT_ATTACK1_MISSILE_ART = new State<>("ua1m");
		public final static State<Bool> COMBAT_ATTACK1_MISSILE_HOMING = new State<>("umh1");
		public final static State<Int> COMBAT_ATTACK1_MISSILE_SPEED = new State<>("ua1z");
		public final static State<Int> COMBAT_ATTACK1_DMG_BASE = new State<>("ua1b");
		public final static State<Int> COMBAT_ATTACK1_DMG_DICE_COUNT = new State<>("ua1d");
		public final static State<Int> COMBAT_ATTACK1_DMG_DICE_SIDES = new State<>("ua1s");
		public final static State<Real> COMBAT_ATTACK1_DMG_LOSS_FACTOR = new State<>("udl1");
		public final static State<Real> COMBAT_ATTACK1_DMG_PT = new State<>("udp1");
		public final static State<Real> COMBAT_ATTACK1_DMG_SPILL_DIST = new State<>("usd1");
		public final static State<Real> COMBAT_ATTACK1_DMG_SPILL_RAD = new State<>("usr1");
		public final static State<Int> COMBAT_ATTACK1_DMG_UP = new State<>("udu1");
		public final static State<Int> COMBAT_ATTACK1_RANGE = new State<>("ua1r");
		public final static State<Real> COMBAT_ATTACK1_RANGE_BUF = new State<>("urb1");
		public final static State<Bool> COMBAT_ATTACK1_SHOW_UI = new State<>("uwu1");
		public final static State<TargetList> COMBAT_ATTACK1_TARGS = new State<>("ua1g");
		public final static State<Int> COMBAT_ATTACK1_TARGS_MAX = new State<>("utc1");
		public final static State<CombatSound> COMBAT_ATTACK1_WEAPON_SOUND = new State<>("ucs1");
		public final static State<WeaponType> COMBAT_ATTACK1_WEAPON_TYPE = new State<>("ua1w");

		public final static State<Int> COMBAT_ATTACK2_AREA_FULL = new State<>("ua2f");
		public final static State<Int> COMBAT_ATTACK2_AREA_MEDIUM = new State<>("ua2h");
		public final static State<Real> COMBAT_ATTACK2_AREA_MEDIUM_DMG_FACTOR = new State<>("uhd2");
		public final static State<Int> COMBAT_ATTACK2_AREA_SMALL = new State<>("ua2q");
		public final static State<Real> COMBAT_ATTACK2_AREA_SMALL_DMG_FACTOR = new State<>("uqd2");
		public final static State<TargetList> COMBAT_ATTACK2_AREA_TARGS = new State<>("ua2p");
		public final static State<AttackType> COMBAT_ATTACK2_ATK_TYPE = new State<>("ua2t");
		public final static State<Real> COMBAT_ATTACK2_BACKSWING = new State<>("ubs2");
		public final static State<Real> COMBAT_ATTACK2_COOLDOWN = new State<>("ua2c");
		public final static State<Int> COMBAT_ATTACK2_DMG_BASE = new State<>("ua2b");
		public final static State<Int> COMBAT_ATTACK2_DMG_DICE_COUNT = new State<>("ua2d");
		public final static State<Int> COMBAT_ATTACK2_DMG_DICE_SIDES = new State<>("ua2s");
		public final static State<Real> COMBAT_ATTACK2_DMG_LOSS_FACTOR = new State<>("udl2");
		public final static State<Real> COMBAT_ATTACK2_DMG_PT = new State<>("udp2");
		public final static State<Real> COMBAT_ATTACK2_DMG_SPILL_DIST = new State<>("usd2");
		public final static State<Real> COMBAT_ATTACK2_DMG_SPILL_RAD = new State<>("usr2");
		public final static State<Int> COMBAT_ATTACK2_DMG_UP = new State<>("udu2");
		public final static State<Real> COMBAT_ATTACK2_MISSILE_ARC = new State<>("uma2");
		public final static State<Model> COMBAT_ATTACK2_MISSILE_ART = new State<>("ua2m");
		public final static State<Bool> COMBAT_ATTACK2_MISSILE_HOMING = new State<>("umh2");
		public final static State<Int> COMBAT_ATTACK2_MISSILE_SPEED = new State<>("ua2z");
		public final static State<Int> COMBAT_ATTACK2_RANGE = new State<>("ua2r");
		public final static State<Real> COMBAT_ATTACK2_RANGE_BUF = new State<>("urb2");
		public final static State<Bool> COMBAT_ATTACK2_SHOW_UI = new State<>("uwu2");
		public final static State<TargetList> COMBAT_ATTACK2_TARGS = new State<>("ua2g");
		public final static State<Int> COMBAT_ATTACK2_TARGS_MAX = new State<>("utc2");
		public final static State<CombatSound> COMBAT_ATTACK2_WEAPON_SOUND = new State<>("ucs2");
		public final static State<WeaponType> COMBAT_ATTACK2_WEAPON_TYPE = new State<>("ua2w");

		public final static State<Int> DATA_BOUNTY_LUMBER_BASE = new State<>("ulba");
		public final static State<Int> DATA_BOUNTY_LUMBER_DICE_COUNT = new State<>("ulbd");
		public final static State<Int> DATA_BOUNTY_LUMBER_DICE_SIDES = new State<>("ulbs");
		public final static State<Int> DATA_BOUNTY_GOLD_BASE = new State<>("ubba");
		public final static State<Int> DATA_BOUNTY_GOLD_DICE_SIDES = new State<>("ubsi");
		public final static State<Int> DATA_BOUNTY_GOLD_DICE_COUNT = new State<>("ubdi");
		public final static State<Int> DATA_BUILD_TIME = new State<>("ubld");
		public final static State<Bool> DATA_CAN_FLEE = new State<>("ufle");
		public final static State<Int> DATA_CARGO_USED = new State<>("ucar");
		public final static State<UnitClass> DATA_CLASSES = new State<>("utyp");
		public final static State<Real> DATA_COLLISION_SIZE = new State<>("ucol");
		public final static State<Int> DATA_COSTS_GOLD = new State<>("ugol");
		public final static State<Int> DATA_COSTS_GOLD_REPAIR = new State<>("ugor");
		public final static State<Int> DATA_COSTS_LUMBER = new State<>("ulum");
		public final static State<Int> DATA_COSTS_LUMBER_REPAIR = new State<>("ulur");
		public final static State<Int> DATA_FORMATION = new State<>("ufor");
		public final static State<Int> DATA_HERO_ATTR_AGI_BASE = new State<>("uagi");
		public final static State<Real> DATA_HERO_ATTR_AGI_UP = new State<>("uagp");
		public final static State<Int> DATA_HERO_ATTR_INT_BASE = new State<>("uint");
		public final static State<Real> DATA_HERO_ATTR_INT_UP = new State<>("uinp");
		public final static State<AttributeType> DATA_HERO_ATTR_PRIMARY = new State<>("upra");
		public final static State<Int> DATA_HERO_ATTR_STR_BASE = new State<>("ustr");
		public final static State<Real> DATA_HERO_ATTR_STR_UP = new State<>("ustp");
		public final static State<Bool> DATA_HERO_HIDE_BAR = new State<>("uhhb");
		public final static State<Bool> DATA_HERO_HIDE_ON_MINIMAP = new State<>("uhhm");
		public final static State<Bool> DATA_HERO_HIDE_DEATH_MSG = new State<>("uhhd");
		public final static State<Int> DATA_HERO_NAMES_COUNT = new State<>("upru");
		public final static State<Bool> DATA_HIDE_ON_MINIMAP = new State<>("uhom");
		public final static State<Bool> DATA_IS_STRUCTURE = new State<>("ubdg");
		public final static State<Int> DATA_LEVEL = new State<>("ulev");
		public final static State<Int> DATA_LIFE_MAX = new State<>("uhpm");
		public final static State<Real> DATA_LIFE_REGEN = new State<>("uhpr");
		public final static State<RegenType> DATA_LIFE_REGEN_TYPE = new State<>("uhrt");
		public final static State<Int> DATA_MANA_INITIAL = new State<>("umpi");
		public final static State<Int> DATA_MANA_MAX = new State<>("umpm");
		public final static State<Real> DATA_MANA_REGEN = new State<>("umpr");
		public final static State<Bool> DATA_NEUTRAL_STRUCTURE_RANDOMED = new State<>("unbr");
		public final static State<Bool> DATA_NEUTRAL_STRUCTURE_SHOW_ON_MINIMAP = new State<>("unbm");
		public final static State<Int> DATA_POINT_VALUE = new State<>("upoi");
		public final static State<Int> DATA_PRIO = new State<>("upri");
		public final static State<UnitRace> DATA_RACE = new State<>("urac");
		public final static State<Int> DATA_REPAIR_TIME = new State<>("urtm");
		public final static State<Int> DATA_SIGHT_RANGE_DAY = new State<>("usid");
		public final static State<Int> DATA_SIGHT_RANGE_NIGHT = new State<>("usin");
		public final static State<Bool> DATA_SLEEPS = new State<>("usle");
		public final static State<Int> DATA_STOCK_INITIAL = new State<>("usst");
		public final static State<Int> DATA_STOCK_MAX = new State<>("usma");
		public final static State<Int> DATA_STOCK_REGEN_INTERVAL = new State<>("usrg");
		public final static State<Bool> DATA_STRUCTURE_CAN_BUILD_ON = new State<>("ucbo");
		public final static State<Bool> DATA_STRUCTURE_IS_BUILD_ON = new State<>("uibo");
		public final static State<Int> DATA_SUPPLY_PRODUCED = new State<>("ufma");
		public final static State<Int> DATA_SUPPLY_USED = new State<>("ufoo");
		
		public final static State<Bool> EDITOR_CLASS_CAMPAIGN = new State<>("ucam");
		public final static State<Bool> EDITOR_CLASS_SPECIAL = new State<>("uspe");
		public final static State<Bool> EDITOR_CLICK_HELPER = new State<>("uuch");
		public final static State<Bool> EDITOR_DISPLAY_AS_NEUTRAL_HOSTILE = new State<>("uhos");
		public final static State<Bool> EDITOR_DROP_ITEMS = new State<>("udro");
		public final static State<Bool> EDITOR_PLACEABLE = new State<>("uine");
		public final static State<Bool> EDITOR_TILESET_SPECIFIC = new State<>("utss");
		public final static State<TilesetList> EDITOR_TILESETS = new State<>("util");

		public final static State<Real> MOVEMENT_HEIGHT = new State<>("umvh");
		public final static State<Real> MOVEMENT_HEIGHT_MIN = new State<>("umvf");
		public final static State<Int> MOVEMENT_REPULSE_GROUPS = new State<>("urpg");
		public final static State<Int> MOVEMENT_REPULSE_PARAM = new State<>("urpp");
		public final static State<Int> MOVEMENT_REPULSE_PRIO = new State<>("urpr");
		public final static State<Bool> MOVEMENT_REPULSE_USED = new State<>("urpo");
		public final static State<Int> MOVEMENT_SPEED = new State<>("umvs");
		public final static State<Int> MOVEMENT_SPEED_MAX = new State<>("umas");
		public final static State<Int> MOVEMENT_SPEED_MIN = new State<>("umis");
		public final static State<Real> MOVEMENT_TURN_RATE = new State<>("umvr");
		public final static State<MoveType> MOVEMENT_TYPE = new State<>("umvt");

		public final static State<PathingTex> PATH_PATHING_TEX = new State<>("upat");
		public final static State<Real> PATH_STRUCTURE_AI_PLACE_RAD = new State<>("uabr");
		public final static State<AIPlaceType> PATH_STRUCTURE_AI_PLACE_TYPE = new State<>("uabt");
		public final static State<PathingPreventList> PATH_STRUCTURE_PREVENTED_PLACE = new State<>("upap");
		public final static State<Real> PATH_STRUCTURE_REQUIRE_WATER_RAD = new State<>("upaw");
		public final static State<PathingRequireList> PATH_STRUCTURE_REQUIRED_PLACE = new State<>("upar");

		public final static State<SoundLabel> SOUND_BUILD = new State<>("ubsl");
		public final static State<Int> SOUND_LOOP_FADE_OUT = new State<>("ulfo");
		public final static State<Int> SOUND_LOOP_FADE_IN = new State<>("ulfi");
		public final static State<SoundLabel> SOUND_MOVE = new State<>("umsl");
		public final static State<SoundLabel> SOUND_RANDOM = new State<>("ursl");
		public final static State<UnitSound> SOUND_SET = new State<>("usnd");

		public final static State<UnitList> TECH_BUILDS = new State<>("ubui");
		public final static State<UnitList> TECH_DEPS_OR = new State<>("udep");
		public final static State<ItemList> TECH_ITEMS_PRODUCED = new State<>("umki");
		public final static State<ItemList> TECH_ITEMS_SOLD = new State<>("usei");
		public final static State<TechList> TECH_REQS = new State<>("ureq");
		public final static State<TechList> TECH_REQS1 = new State<>("urq1");
		public final static State<TechList> TECH_REQS2 = new State<>("urq2");
		public final static State<TechList> TECH_REQS3 = new State<>("urq3");
		public final static State<TechList> TECH_REQS4 = new State<>("urq4");
		public final static State<TechList> TECH_REQS5 = new State<>("urq5");
		public final static State<TechList> TECH_REQS6 = new State<>("urq6");
		public final static State<TechList> TECH_REQS7 = new State<>("urq7");
		public final static State<TechList> TECH_REQS8 = new State<>("urq8");
		public final static State<IntList> TECH_REQS_COUNT = new State<>("urqa");
		public final static State<UpgradeList> TECH_RESEARCHES = new State<>("ures");
		public final static State<UnitList> TECH_REVIVED_AT = new State<>("urva");
		public final static State<Bool> TECH_REVIVES = new State<>("urev");
		public final static State<UnitList> TECH_UNITS_SOLD = new State<>("useu");
		public final static State<UnitList> TECH_UNITS_TAINED = new State<>("utra");
		public final static State<UnitList> TECH_UPGRADED_TO = new State<>("uupt");
		public final static State<UpgradeList> TECH_UPGRADES = new State<>("upgr");

		public final static State<String> TEXT_DESCRIPTION = new State<>("ides");
		public final static State<String> TEXT_EDITOR_SUFFIX = new State<>("unsf");
		public final static State<StringList> TEXT_HERO_PROPER_NAMES = new State<>("upro");
		public final static State<Char> TEXT_HOTKEY = new State<>("uhot");
		public final static State<String> TEXT_NAME = new State<>("unam");
		public final static State<String> TEXT_TOOLTIP = new State<>("utip");
		public final static State<String> TEXT_TOOLTIP_AWAKEN = new State<>("uawt");
		public final static State<String> TEXT_TOOLTIP_REVIVE = new State<>("utpr");
		public final static State<String> TEXT_TOOLTIP_UBER = new State<>("utub");
	}

	public static class Unit extends ObjMod.Obj {
		public Unit(ObjId id, ObjId baseId) {
			super(id, baseId);
		}
	}
	
	private Map<ObjId, Unit> _objs = new HashMap<>();

	@Override
	public Map<ObjId, Unit> getObjs() {
		System.out.println("get units");
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
		System.out.println("add unit");
		if (getObjs().containsKey(id)) return getObjs().get(id);
		
		Unit obj = new Unit(id, baseId);
		
		addObj(obj);
		
		return obj;
	}
	
	@Override
	public void read(File file) throws IOException {
		ObjMod other = new ObjMod();
		
		other.read(file);
		
		other.merge(this);
		
		for (Obj obj : getObjs().values()) {
			Unit unit = new Unit(obj.getId(), obj.getBaseId());
			
			unit.merge(obj);
			
			addObj(unit);
		}
	}
	
	public W3U() {
	}
}
