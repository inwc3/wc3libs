package net.moonlightflower.wc3libs.bin.app.objMod;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.app.objs.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

/**
 * unit modifications file for wrapping war3map.w3u
 */
public class W3U extends ObjMod<W3U.Unit> {
	public final static File GAME_PATH = new File("war3map.w3u");
	public final static File CAMPAIGN_PATH = new File("war3campaign.w3u");

	public W3U(@Nonnull Wc3BinInputStream stream) throws IOException {
		super(stream);
	}

	public W3U(@Nonnull File file) throws Exception {
		super(file);
	}

	public W3U() {
		super();
	}

	@Nonnull
	public static W3U ofMapFile(@Nonnull File mapFile) throws IOException {
		return ofMapFile(W3U.class, mapFile);
	}

	@Override
	protected Unit createObj(@Nonnull ObjId id, @Nullable ObjId baseId) {
		return new Unit(id, baseId);
	}

	@Nonnull
	@Override
	protected Unit createObj(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
		return new Unit(stream, format);
	}

	@Nonnull
	@Override
	public W3U copy() {
		W3U other = new W3U();

		other.merge(this);

		return other;
	}

	@Override
	public Collection<File> getSLKs() {
		return Arrays.asList(UnitAbilsSLK.GAME_PATH, UnitBalanceSLK.GAME_PATH, UnitDataSLK.GAME_PATH, UnitUISLK.GAME_PATH, UnitWeaponsSLK.GAME_PATH);
	}

	@Override
	public Collection<File> getNecessarySLKs() {
		return Arrays.asList(UnitAbilsSLK.GAME_PATH, UnitBalanceSLK.GAME_PATH, UnitDataSLK.GAME_PATH, UnitUISLK.GAME_PATH, UnitWeaponsSLK.GAME_PATH);
	}

	@Override
	public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
		super.write(stream, format);
	}

	public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
		super.write(stream);
	}

	public static class Unit extends ObjMod.Obj {
		public Unit(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinStream.StreamException {
			super(stream, format);
		}

		public Unit(@Nonnull ObjId id, @Nullable ObjId baseId) {
			super(id, baseId);
		}

		@Override
		public boolean isExtended() {
			return false;
		}

		@Override
		protected Unit copySpec() {
			return new Unit(getId(), getBaseId());
		}

		public <T extends DataType> T get(@Nonnull State<T> state) {
			try {
				return state.tryCastVal(super.get(state.getFieldId()));
			} catch (DataTypeInfo.CastException ignored) {
			}

			return null;
		}

		public <T extends DataType> void set(@Nonnull State<T> state, T val) {
			super.set(state.getFieldId(), val);
		}

		public <T extends DataType> void remove(@Nonnull State<T> state) {
			super.remove(state.getFieldId());
		}
	}

	public static class State<T extends DataType> extends MetaState<T> {
		public final static State<AbilCode> ABIL_AUTO = new State<>("udaa", AbilCode.class);
		public final static State<DataList<AbilId>> ABIL_HERO = new State<>("uhab", new DataTypeInfo(DataList.class, AbilId.class));
		public final static State<DataList<AbilId>> ABIL_NORMAL = new State<>("uabi", new DataTypeInfo(DataList.class, AbilId.class));

		public final static State<War3Real> ART_ANIM_WALK_SPD = new State<>("uwal", War3Real.class);
		public final static State<War3Real> ART_ANIM_RUN_SPD = new State<>("urun", War3Real.class);
		public final static State<War3Real> ART_ANIM_CAST_PT = new State<>("ucpt", War3Real.class);
		public final static State<War3Real> ART_ANIM_CAST_BACKSWING = new State<>("ucbs", War3Real.class);
		public final static State<War3Real> ART_ANIM_BLEND = new State<>("uble", War3Real.class);
		public final static State<DataList<War3String>> ART_ANIM_PROPS = new State<>("uani", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<War3String>> ART_ANIM_PROPS_ATTACH = new State<>("uaap", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<War3String>> ART_ANIM_PROPS_ATTACH_LINK = new State<>("ualp", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<War3String>> ART_ANIM_PROPS_BONE = new State<>("ubpr", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<War3Int> ART_BUTTON_POS_X = new State<>("ubpx", War3Int.class);
		public final static State<War3Int> ART_BUTTON_POS_Y = new State<>("ubpy", War3Int.class);
		public final static State<DataList<War3String>> ART_CASTER_UPGRADE_NAMES = new State<>("ucun", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<War3String>> ART_CASTER_UPGRADE_TOOLTIP = new State<>("ucut", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<War3Int> ART_COLOR_BLUE = new State<>("uclb", War3Int.class);
		public final static State<War3Int> ART_COLOR_GREEN = new State<>("uclg", War3Int.class);
		public final static State<War3Int> ART_COLOR_RED = new State<>("uclr", War3Int.class);
		public final static State<War3Bool> ART_CUSTOM_TEAM_COLOR = new State<>("utcc", War3Bool.class);
		public final static State<War3Real> ART_DEATH_TIME = new State<>("udtm", War3Real.class);
		public final static State<War3Real> ART_ELEV_RAD = new State<>("uerd", War3Real.class);
		public final static State<War3Int> ART_ELEV_PTS = new State<>("uept", War3Int.class);
		public final static State<War3Bool> ART_FAT_LINE_OF_SIGHT = new State<>("ulos", War3Bool.class);
		public final static State<War3Real> ART_FOG_RADIUS = new State<>("ufrd", War3Real.class);
		public final static State<Icon> ART_ICON = new State<>("uico", Icon.class);
		public final static State<Icon> ART_ICON_CASTER_UPGRADE = new State<>("ucua", Icon.class);
		public final static State<Icon> ART_ICON_SCORE_SCREEN = new State<>("ussi", Icon.class);
		public final static State<War3Real> ART_IMPACT_Z_SWIM = new State<>("uisz", War3Real.class);
		public final static State<War3Real> ART_LAUNCH_X = new State<>("ulpx", War3Real.class);
		public final static State<War3Real> ART_LAUNCH_Y = new State<>("ulpy", War3Real.class);
		public final static State<War3Real> ART_LAUNCH_Z = new State<>("ulpz", War3Real.class);
		public final static State<War3Real> ART_LAUNCH_Z_SWIM = new State<>("ulsz", War3Real.class);
		public final static State<War3Real> ART_MODEL_SCALE = new State<>("usca", War3Real.class);
		public final static State<War3Real> ART_OCCLUSION_HEIGHT = new State<>("uocc", War3Real.class);
		public final static State<War3Int> ART_ORIENT_INTERP = new State<>("uori", War3Int.class);
		public final static State<War3Real> ART_PITCH_MAX = new State<>("umxp", War3Real.class);
		public final static State<War3Real> ART_PROP_WINDOW = new State<>("uprw", War3Real.class);
		public final static State<War3Real> ART_ROLL_MAX = new State<>("umxr", War3Real.class);
		public final static State<War3Bool> ART_SCALE_MISSILES = new State<>("uscb", War3Bool.class);
		public final static State<War3Bool> ART_SHADOW_ON_WATER = new State<>("ushr", War3Bool.class);
		public final static State<War3Real> ART_SELECTION_HEIGHT = new State<>("uslz", War3Real.class);
		public final static State<War3Real> ART_SELECTION_SCALE = new State<>("ussc", War3Real.class);
		public final static State<War3Bool> ART_SELECTION_SHOW_ON_WATER = new State<>("usew", War3Bool.class);
		public final static State<War3Real> ART_SHADOW_HEIGHT = new State<>("ushh", War3Real.class);
		public final static State<War3Real> ART_SHADOW_WIDTH = new State<>("ushw", War3Real.class);
		public final static State<ShadowTex> ART_SHADOW_STRUCTURE = new State<>("ushb", ShadowTex.class);
		public final static State<ShadowImage> ART_SHADOW_UNIT = new State<>("ushu", ShadowImage.class);
		public final static State<War3Real> ART_SHADOW_X = new State<>("ushx", War3Real.class);
		public final static State<War3Real> ART_SHADOW_Y = new State<>("ushy", War3Real.class);
		public final static State<DataList<Model>> ART_SPECIALS = new State<>("uspa", new DataTypeInfo(DataList.class, Model.class));
		public final static State<DataList<Model>> ART_TARGETS = new State<>("utaa", new DataTypeInfo(DataList.class, Model.class));
		public final static State<TeamColor> ART_TEAM_COLOR = new State<>("utco", TeamColor.class);
		public final static State<UberSplatId> ART_UBERSPLAT = new State<>("uubs", UberSplatId.class);
		public final static State<VersionFlags> ART_VERSION_FLAGS = new State<>("uver", VersionFlags.class);

		public final static State<War3Int> COMBAT_ARMOR_BASE = new State<>("udef", War3Int.class);
		public final static State<DefType> COMBAT_ARMOR_TYPE = new State<>("udty", DefType.class);
		public final static State<DeathType> COMBAT_DEATH_TYPE = new State<>("udea", DeathType.class);
		public final static State<War3Real> COMBAT_RANGE_ACQUISITION = new State<>("uacq", War3Real.class);
		public final static State<War3Int> COMBAT_RANGE_MIN = new State<>("uamn", War3Int.class);
		public final static State<DataList<CombatTarget>> COMBAT_TARGETD_AS = new State<>("utar", new DataTypeInfo(DataList.class, CombatTarget.class));
		public final static State<AttackBits> COMBAT_WEAPS_ON = new State<>("uaen", AttackBits.class);

		public final static State<War3Int> COMBAT_ATTACK1_AREA_FULL = new State<>("ua1f", War3Int.class);
		public final static State<War3Int> COMBAT_ATTACK1_AREA_MEDIUM = new State<>("ua1h", War3Int.class);
		public final static State<War3Real> COMBAT_ATTACK1_AREA_MEDIUM_DMG_FACTOR = new State<>("uhd1", War3Real.class);
		public final static State<War3Int> COMBAT_ATTACK1_AREA_SMALL = new State<>("ua1q", War3Int.class);
		public final static State<War3Real> COMBAT_ATTACK1_AREA_SMALL_DMG_FACTOR = new State<>("uqd1", War3Real.class);
		public final static State<DataList<CombatTarget>> COMBAT_ATTACK1_AREA_TARGS = new State<>("ua1p", new DataTypeInfo(DataList.class, CombatTarget.class));
		public final static State<AttackType> COMBAT_ATTACK1_ATK_TYPE = new State<>("ua1t", AttackType.class);
		public final static State<War3Real> COMBAT_ATTACK1_BACKSWING = new State<>("ubs1", War3Real.class);
		public final static State<War3Real> COMBAT_ATTACK1_COOLDOWN = new State<>("ua1c", War3Real.class);
		public final static State<War3Real> COMBAT_ATTACK1_MISSILE_ARC = new State<>("uma1", War3Real.class);
		public final static State<Model> COMBAT_ATTACK1_MISSILE_ART = new State<>("ua1m", Model.class);
		public final static State<War3Bool> COMBAT_ATTACK1_MISSILE_HOMING = new State<>("umh1", War3Bool.class);
		public final static State<War3Int> COMBAT_ATTACK1_MISSILE_SPEED = new State<>("ua1z", War3Int.class);
		public final static State<War3Int> COMBAT_ATTACK1_DMG_BASE = new State<>("ua1b", War3Int.class);
		public final static State<War3Int> COMBAT_ATTACK1_DMG_DICE_COUNT = new State<>("ua1d", War3Int.class);
		public final static State<War3Int> COMBAT_ATTACK1_DMG_DICE_SIDES = new State<>("ua1s", War3Int.class);
		public final static State<War3Real> COMBAT_ATTACK1_DMG_LOSS_FACTOR = new State<>("udl1", War3Real.class);
		public final static State<War3Real> COMBAT_ATTACK1_DMG_PT = new State<>("udp1", War3Real.class);
		public final static State<War3Real> COMBAT_ATTACK1_DMG_SPILL_DIST = new State<>("usd1", War3Real.class);
		public final static State<War3Real> COMBAT_ATTACK1_DMG_SPILL_RAD = new State<>("usr1", War3Real.class);
		public final static State<War3Int> COMBAT_ATTACK1_DMG_UP = new State<>("udu1", War3Int.class);
		public final static State<War3Int> COMBAT_ATTACK1_RANGE = new State<>("ua1r", War3Int.class);
		public final static State<War3Real> COMBAT_ATTACK1_RANGE_BUF = new State<>("urb1", War3Real.class);
		public final static State<War3Bool> COMBAT_ATTACK1_SHOW_UI = new State<>("uwu1", War3Bool.class);
		public final static State<DataList<CombatTarget>> COMBAT_ATTACK1_TARGS = new State<>("ua1g", new DataTypeInfo(DataList.class, CombatTarget.class));
		public final static State<War3Int> COMBAT_ATTACK1_TARGS_MAX = new State<>("utc1", War3Int.class);
		public final static State<CombatSound> COMBAT_ATTACK1_WEAPON_SOUND = new State<>("ucs1", CombatSound.class);
		public final static State<WeaponType> COMBAT_ATTACK1_WEAPON_TYPE = new State<>("ua1w", WeaponType.class);

		public final static State<War3Int> COMBAT_ATTACK2_AREA_FULL = new State<>("ua2f", War3Int.class);
		public final static State<War3Int> COMBAT_ATTACK2_AREA_MEDIUM = new State<>("ua2h", War3Int.class);
		public final static State<War3Real> COMBAT_ATTACK2_AREA_MEDIUM_DMG_FACTOR = new State<>("uhd2", War3Real.class);
		public final static State<War3Int> COMBAT_ATTACK2_AREA_SMALL = new State<>("ua2q", War3Int.class);
		public final static State<War3Real> COMBAT_ATTACK2_AREA_SMALL_DMG_FACTOR = new State<>("uqd2", War3Real.class);
		public final static State<DataList<CombatTarget>> COMBAT_ATTACK2_AREA_TARGS = new State<>("ua2p", new DataTypeInfo(DataList.class, CombatTarget.class));
		public final static State<AttackType> COMBAT_ATTACK2_ATK_TYPE = new State<>("ua2t", AttackType.class);
		public final static State<War3Real> COMBAT_ATTACK2_BACKSWING = new State<>("ubs2", War3Real.class);
		public final static State<War3Real> COMBAT_ATTACK2_COOLDOWN = new State<>("ua2c", War3Real.class);
		public final static State<War3Int> COMBAT_ATTACK2_DMG_BASE = new State<>("ua2b", War3Int.class);
		public final static State<War3Int> COMBAT_ATTACK2_DMG_DICE_COUNT = new State<>("ua2d", War3Int.class);
		public final static State<War3Int> COMBAT_ATTACK2_DMG_DICE_SIDES = new State<>("ua2s", War3Int.class);
		public final static State<War3Real> COMBAT_ATTACK2_DMG_LOSS_FACTOR = new State<>("udl2", War3Real.class);
		public final static State<War3Real> COMBAT_ATTACK2_DMG_PT = new State<>("udp2", War3Real.class);
		public final static State<War3Real> COMBAT_ATTACK2_DMG_SPILL_DIST = new State<>("usd2", War3Real.class);
		public final static State<War3Real> COMBAT_ATTACK2_DMG_SPILL_RAD = new State<>("usr2", War3Real.class);
		public final static State<War3Int> COMBAT_ATTACK2_DMG_UP = new State<>("udu2", War3Int.class);
		public final static State<War3Real> COMBAT_ATTACK2_MISSILE_ARC = new State<>("uma2", War3Real.class);
		public final static State<Model> COMBAT_ATTACK2_MISSILE_ART = new State<>("ua2m", Model.class);
		public final static State<War3Bool> COMBAT_ATTACK2_MISSILE_HOMING = new State<>("umh2", War3Bool.class);
		public final static State<War3Int> COMBAT_ATTACK2_MISSILE_SPEED = new State<>("ua2z", War3Int.class);
		public final static State<War3Int> COMBAT_ATTACK2_RANGE = new State<>("ua2r", War3Int.class);
		public final static State<War3Real> COMBAT_ATTACK2_RANGE_BUF = new State<>("urb2", War3Real.class);
		public final static State<War3Bool> COMBAT_ATTACK2_SHOW_UI = new State<>("uwu2", War3Bool.class);
		public final static State<DataList<CombatTarget>> COMBAT_ATTACK2_TARGS = new State<>("ua2g", new DataTypeInfo(DataList.class, CombatTarget.class));
		public final static State<War3Int> COMBAT_ATTACK2_TARGS_MAX = new State<>("utc2", War3Int.class);
		public final static State<CombatSound> COMBAT_ATTACK2_WEAPON_SOUND = new State<>("ucs2", CombatSound.class);
		public final static State<WeaponType> COMBAT_ATTACK2_WEAPON_TYPE = new State<>("ua2w", WeaponType.class);

		public final static State<War3Int> DATA_BOUNTY_LUMBER_BASE = new State<>("ulba", War3Int.class);
		public final static State<War3Int> DATA_BOUNTY_LUMBER_DICE_COUNT = new State<>("ulbd", War3Int.class);
		public final static State<War3Int> DATA_BOUNTY_LUMBER_DICE_SIDES = new State<>("ulbs", War3Int.class);
		public final static State<War3Int> DATA_BOUNTY_GOLD_BASE = new State<>("ubba", War3Int.class);
		public final static State<War3Int> DATA_BOUNTY_GOLD_DICE_SIDES = new State<>("ubsi", War3Int.class);
		public final static State<War3Int> DATA_BOUNTY_GOLD_DICE_COUNT = new State<>("ubdi", War3Int.class);
		public final static State<War3Int> DATA_BUILD_TIME = new State<>("ubld", War3Int.class);
		public final static State<War3Bool> DATA_CAN_FLEE = new State<>("ufle", War3Bool.class);
		public final static State<War3Int> DATA_CARGO_USED = new State<>("ucar", War3Int.class);
		public final static State<UnitClass> DATA_CLASSES = new State<>("utyp", UnitClass.class);
		public final static State<War3Real> DATA_COLLISION_SIZE = new State<>("ucol", War3Real.class);
		public final static State<War3Int> DATA_COSTS_GOLD = new State<>("ugol", War3Int.class);
		public final static State<War3Int> DATA_COSTS_GOLD_REPAIR = new State<>("ugor", War3Int.class);
		public final static State<War3Int> DATA_COSTS_LUMBER = new State<>("ulum", War3Int.class);
		public final static State<War3Int> DATA_COSTS_LUMBER_REPAIR = new State<>("ulur", War3Int.class);
		public final static State<War3Int> DATA_FORMATION = new State<>("ufor", War3Int.class);
		public final static State<War3Int> DATA_HERO_ATTR_AGI_BASE = new State<>("uagi", War3Int.class);
		public final static State<War3Real> DATA_HERO_ATTR_AGI_UP = new State<>("uagp", War3Real.class);
		public final static State<War3Int> DATA_HERO_ATTR_INT_BASE = new State<>("uint", War3Int.class);
		public final static State<War3Real> DATA_HERO_ATTR_INT_UP = new State<>("uinp", War3Real.class);
		public final static State<AttributeType> DATA_HERO_ATTR_PRIMARY = new State<>("upra", AttributeType.class);
		public final static State<War3Int> DATA_HERO_ATTR_STR_BASE = new State<>("ustr", War3Int.class);
		public final static State<War3Real> DATA_HERO_ATTR_STR_UP = new State<>("ustp", War3Real.class);
		public final static State<War3Bool> DATA_HERO_HIDE_BAR = new State<>("uhhb", War3Bool.class);
		public final static State<War3Bool> DATA_HERO_HIDE_ON_MINIMAP = new State<>("uhhm", War3Bool.class);
		public final static State<War3Bool> DATA_HERO_HIDE_DEATH_MSG = new State<>("uhhd", War3Bool.class);
		public final static State<War3Int> DATA_HERO_NAMES_COUNT = new State<>("upru", War3Int.class);
		public final static State<War3Bool> DATA_HIDE_ON_MINIMAP = new State<>("uhom", War3Bool.class);
		public final static State<War3Bool> DATA_IS_STRUCTURE = new State<>("ubdg", War3Bool.class);
		public final static State<War3Int> DATA_LEVEL = new State<>("ulev", War3Int.class);
		public final static State<War3Int> DATA_LIFE_MAX = new State<>("uhpm", War3Int.class);
		public final static State<War3Real> DATA_LIFE_REGEN = new State<>("uhpr", War3Real.class);
		public final static State<RegenType> DATA_LIFE_REGEN_TYPE = new State<>("uhrt", RegenType.class);
		public final static State<War3Int> DATA_MANA_INITIAL = new State<>("umpi", War3Int.class);
		public final static State<War3Int> DATA_MANA_MAX = new State<>("umpm", War3Int.class);
		public final static State<War3Real> DATA_MANA_REGEN = new State<>("umpr", War3Real.class);
		public final static State<War3Bool> DATA_NEUTRAL_STRUCTURE_RANDOMED = new State<>("unbr", War3Bool.class);
		public final static State<War3Bool> DATA_NEUTRAL_STRUCTURE_SHOW_ON_MINIMAP = new State<>("unbm", War3Bool.class);
		public final static State<War3Int> DATA_POINT_VALUE = new State<>("upoi", War3Int.class);
		public final static State<War3Int> DATA_PRIO = new State<>("upri", War3Int.class);
		public final static State<UnitRace> DATA_RACE = new State<>("urac", UnitRace.class);
		public final static State<War3Int> DATA_REPAIR_TIME = new State<>("urtm", War3Int.class);
		public final static State<War3Int> DATA_SIGHT_RANGE_DAY = new State<>("usid", War3Int.class);
		public final static State<War3Int> DATA_SIGHT_RANGE_NIGHT = new State<>("usin", War3Int.class);
		public final static State<War3Bool> DATA_SLEEPS = new State<>("usle", War3Bool.class);
		public final static State<War3Int> DATA_STOCK_INITIAL = new State<>("usst", War3Int.class);
		public final static State<War3Int> DATA_STOCK_MAX = new State<>("usma", War3Int.class);
		public final static State<War3Int> DATA_STOCK_REGEN_INTERVAL = new State<>("usrg", War3Int.class);
		public final static State<War3Bool> DATA_STRUCTURE_CAN_BUILD_ON = new State<>("ucbo", War3Bool.class);
		public final static State<War3Bool> DATA_STRUCTURE_IS_BUILD_ON = new State<>("uibo", War3Bool.class);
		public final static State<War3Int> DATA_SUPPLY_PRODUCED = new State<>("ufma", War3Int.class);
		public final static State<War3Int> DATA_SUPPLY_USED = new State<>("ufoo", War3Int.class);

		public final static State<War3Bool> EDITOR_CLASS_CAMPAIGN = new State<>("ucam", War3Bool.class);
		public final static State<War3Bool> EDITOR_CLASS_SPECIAL = new State<>("uspe", War3Bool.class);
		public final static State<War3Bool> EDITOR_CLICK_HELPER = new State<>("uuch", War3Bool.class);
		public final static State<War3Bool> EDITOR_DISPLAY_AS_NEUTRAL_HOSTILE = new State<>("uhos", War3Bool.class);
		public final static State<War3Bool> EDITOR_DROP_ITEMS = new State<>("udro", War3Bool.class);
		public final static State<War3Bool> EDITOR_PLACEABLE = new State<>("uine", War3Bool.class);
		public final static State<War3Bool> EDITOR_TILESET_SPECIFIC = new State<>("utss", War3Bool.class);
		public final static State<DataList<Tileset>> EDITOR_TILESETS = new State<>("util", new DataTypeInfo(DataList.class, Tileset.class));

		public final static State<War3Real> MOVEMENT_HEIGHT = new State<>("umvh", War3Real.class);
		public final static State<War3Real> MOVEMENT_HEIGHT_MIN = new State<>("umvf", War3Real.class);
		public final static State<War3Int> MOVEMENT_REPULSE_GROUPS = new State<>("urpg", War3Int.class);
		public final static State<War3Int> MOVEMENT_REPULSE_PARAM = new State<>("urpp", War3Int.class);
		public final static State<War3Int> MOVEMENT_REPULSE_PRIO = new State<>("urpr", War3Int.class);
		public final static State<War3Bool> MOVEMENT_REPULSE_USED = new State<>("urpo", War3Bool.class);
		public final static State<War3Int> MOVEMENT_SPEED = new State<>("umvs", War3Int.class);
		public final static State<War3Int> MOVEMENT_SPEED_MAX = new State<>("umas", War3Int.class);
		public final static State<War3Int> MOVEMENT_SPEED_MIN = new State<>("umis", War3Int.class);
		public final static State<War3Real> MOVEMENT_TURN_RATE = new State<>("umvr", War3Real.class);
		public final static State<MoveType> MOVEMENT_TYPE = new State<>("umvt", MoveType.class);

		public final static State<PathingTex> PATH_PATHING_TEX = new State<>("upat", PathingTex.class);
		public final static State<War3Real> PATH_STRUCTURE_AI_PLACE_RAD = new State<>("uabr", War3Real.class);
		public final static State<AIPlaceType> PATH_STRUCTURE_AI_PLACE_TYPE = new State<>("uabt", AIPlaceType.class);
		public final static State<DataList<PathingPrevent>> PATH_STRUCTURE_PREVENTED_PLACE = new State<>("upap", new DataTypeInfo(DataList.class, PathingPrevent.class));
		public final static State<War3Real> PATH_STRUCTURE_REQUIRE_WATER_RAD = new State<>("upaw", War3Real.class);
		public final static State<DataList<PathingRequire>> PATH_STRUCTURE_REQUIRED_PLACE = new State<>("upar", new DataTypeInfo(DataList.class, PathingRequire.class));

		public final static State<SoundLabel> SOUND_BUILD = new State<>("ubsl", SoundLabel.class);
		public final static State<War3Int> SOUND_LOOP_FADE_OUT = new State<>("ulfo", War3Int.class);
		public final static State<War3Int> SOUND_LOOP_FADE_IN = new State<>("ulfi", War3Int.class);
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
		public final static State<DataList<War3Int>> TECH_REQS_COUNT = new State<>("urqa", new DataTypeInfo(DataList.class, War3Int.class));
		public final static State<DataList<UpgradeId>> TECH_RESEARCHES = new State<>("ures", new DataTypeInfo(DataList.class, UpgradeId.class));
		public final static State<DataList<UnitId>> TECH_REVIVED_AT = new State<>("urva", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<War3Bool> TECH_REVIVES = new State<>("urev", War3Bool.class);
		public final static State<DataList<UnitId>> TECH_UNITS_SOLD = new State<>("useu", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<UnitId>> TECH_UNITS_TAINED = new State<>("utra", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<UnitId>> TECH_UPGRADED_TO = new State<>("uupt", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<UpgradeId>> TECH_UPGRADES = new State<>("upgr", new DataTypeInfo(DataList.class, UpgradeId.class));

		public final static State<War3String> TEXT_DESCRIPTION = new State<>("ides", War3String.class);
		public final static State<War3String> TEXT_EDITOR_SUFFIX = new State<>("unsf", War3String.class);
		public final static State<DataList<War3String>> TEXT_HERO_PROPER_NAMES = new State<>("upro", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<War3Char> TEXT_HOTKEY = new State<>("uhot", War3Char.class);
		public final static State<War3String> TEXT_NAME = new State<>("unam", War3String.class);
		public final static State<War3String> TEXT_TOOLTIP = new State<>("utip", War3String.class);
		public final static State<War3String> TEXT_TOOLTIP_AWAKEN = new State<>("uawt", War3String.class);
		public final static State<War3String> TEXT_TOOLTIP_REVIVE = new State<>("utpr", War3String.class);
		public final static State<War3String> TEXT_TOOLTIP_UBER = new State<>("utub", War3String.class);

		public State(@Nonnull String idString, @Nonnull DataTypeInfo typeInfo, @Nullable T defVal) {
			super(idString, typeInfo, defVal);
		}

		public State(@Nonnull String idString, @Nonnull Class<T> type, @Nullable T defVal) {
			this(idString, new DataTypeInfo(type), defVal);
		}

		public State(@Nonnull String idString, @Nonnull DataTypeInfo typeInfo) {
			this(idString, typeInfo, null);
		}

		public State(@Nonnull String idString, @Nonnull Class<T> type) {
			this(idString, new DataTypeInfo(type), null);
		}
	}
}
