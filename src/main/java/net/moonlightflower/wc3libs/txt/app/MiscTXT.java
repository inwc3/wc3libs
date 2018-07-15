package net.moonlightflower.wc3libs.txt.app;

import java.io.File;
import java.io.IOException;

import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.dataTypes.app.War3Int;
import net.moonlightflower.wc3libs.txt.TXT;
import net.moonlightflower.wc3libs.txt.TXTState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MiscTXT extends TXT {
	public final static File GAME_PATH = new File("war3mapMisc.txt");

	public static class Section extends TXT.Section {
		public final static Section HERO = new Section("HERO");
		public final static Section MISC = new Section("Misc");
		public final static Section TALT = new Section("TALT");
		public final static Section TWN1 = new Section("TWN1");
		public final static Section TWN2 = new Section("TWN2");
		public final static Section TWN3 = new Section("TWN3");
		public final static Section TWN4 = new Section("TWN4");
		public final static Section TWN5 = new Section("TWN5");
		public final static Section TWN6 = new Section("TWN6");
		public final static Section TWN7 = new Section("TWN7");
		public final static Section TWN8 = new Section("TWN8");
		public final static Section TWN9 = new Section("TWN9");

		public Section(@Nonnull String name) {
			super(name);
		}
	}

	public static class State<T extends DataType> extends TXTState<T> {
		public final static State<War3Bool> ABIL_DEPENDENCY_CHECK = new State<>(Section.MISC, "DepCheckAlias", War3Bool.class);

		public final static State<War3Bool> ABIL_AMULET_OF_RECALL_CLUSTER = new State<>(Section.MISC, "AmuletOfRecallCluster", War3Bool.class);
		public final static State<War3Bool> ABIL_AVATAR_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateAvatar", War3Bool.class);
		public final static State<War3Bool> ABIL_AVENGER_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateAvengerForm", War3Bool.class);
		public final static State<War3Bool> ABIL_BARKSKIN_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateBarkskin", War3Bool.class);
		public final static State<War3Bool> ABIL_BEAR_FORM_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateBearForm", War3Bool.class);
		public final static State<War3Bool> ABIL_BLADESTORM_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateBladestorm", War3Bool.class);
		public final static State<War3Bool> ABIL_BURROW_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateBurrow", War3Bool.class);
		public final static State<War3Bool> ABIL_CALL_TO_ARMS_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateCallToArms", War3Bool.class);
		public final static State<War3Bool> ABIL_CHEMICAL_RAGE_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateChemicalRage", War3Bool.class);
		public final static State<War3Bool> ABIL_CORPOREAL_FORM_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateCorporealForm", War3Bool.class);
		public final static State<War3Bool> ABIL_CYCLONE_HIDES_TARGET = new State<>(Section.MISC, "CycloneStasis", War3Bool.class);
		public final static State<War3Bool> ABIL_DARK_SUMMONING_CLUSTER = new State<>(Section.MISC, "DarkSummoningCluster", War3Bool.class);
		public final static State<War3Bool> ABIL_DEFEND_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateDefend", War3Bool.class);
		public final static State<War3Bool> ABIL_DEFEND_CAN_DEFLECT = new State<>(Section.MISC, "DefendDeflection", War3Bool.class);
		public final static State<War3Bool> ABIL_DRAIN_GIVES_BONUS_LIFE = new State<>(Section.MISC, "DrainGivesBonusLife", War3Bool.class);
		public final static State<War3Bool> ABIL_DRAIN_GIVES_BONUS_MANA = new State<>(Section.MISC, "DrainGivesBonusMana", War3Bool.class);
		public final static State<War3Bool> ABIL_DRAIN_USES_ETHEREAL_BONUS = new State<>(Section.MISC, "DrainUsesEtherealBonus", War3Bool.class);
		public final static State<War3Bool> ABIL_DRAIN_TRANFERS_LIFE = new State<>(Section.MISC, "DrainTransfersLife", War3Bool.class);
		public final static State<War3Bool> ABIL_DRAIN_TRANFERS_MANA = new State<>(Section.MISC, "DrainTransfersMana", War3Bool.class);
		public final static State<War3Bool> ABIL_DIVINE_SHIELD_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateDivineShield", War3Bool.class);
		public final static State<War3Bool> ABIL_ENSNARE_IS_MAGIC = new State<>(Section.MISC, "EnsnareIsMagic", War3Bool.class);
		public final static State<War3Int> ABIL_HERO_LEVEL_SKIP = new State<>(Section.MISC, "HeroAbilityLevelSkip", War3Int.class);
		public final static State<War3Bool> ABIL_IMMOLATION_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateImmolation", War3Bool.class);
		public final static State<War3Bool> ABIL_MANA_FLARE_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateManaFlare", War3Bool.class);
		public final static State<War3Bool> ABIL_MANA_SHIELD_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateManaShield", War3Bool.class);
		public final static State<War3Bool> ABIL_MASS_TELEPORT_CLUSTER = new State<>(Section.MISC, "MassTeleportCluster", War3Bool.class);
		public final static State<War3Bool> ABIL_METAMORPHOSIS_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateMetamorphosis", War3Bool.class);
		public final static State<War3Bool> ABIL_RAVEN_FORM_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateRavenForm", War3Bool.class);
		public final static State<War3Bool> ABIL_ROBO_GOBLIN_FORM_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateRoboGoblin", War3Bool.class);
		public final static State<War3Bool> ABIL_STONE_FORM_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateStoneForm", War3Bool.class);
		public final static State<War3Bool> ABIL_SUBMERGE_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateSubmerge", War3Bool.class);
		public final static State<War3Bool> ABIL_TOWN_PORTAL_CLUSTER = new State<>(Section.MISC, "TownPortalCluster", War3Bool.class);
		public final static State<War3Bool> ABIL_WEB_IS_MAGIC = new State<>(Section.MISC, "WebIsMagic", War3Bool.class);
		public final static State<War3Bool> ABIL_WIND_WALK_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateWindWalk", War3Bool.class);

		public final static State<War3Bool> EXP_GLOBAL = new State<>(Section.MISC, "GlobalExperience", War3Bool.class);
		public final static State<War3Bool> EXP_STRUCTURE_KILLS_GIVE = new State<>(Section.MISC, "BuildingKillsGiveExp", War3Bool.class);
		public final static State<War3Int> EXP_HERO_MAX_LEVEL = new State<>(Section.MISC, "MaxHeroLevel", War3Int.class);
		public final static State<War3Bool> EXP_HERO_MAX_LEVEL_DRAIN = new State<>(Section.MISC, "MaxLevelHeroesDrainExp", War3Bool.class);
		public final static State<DataList<War3Int>> EXP_HERO_NEEDED = new State<>(Section.MISC, "NeedHeroXP", new DataTypeInfo(DataList.class, War3Int.class));
		public final static State<War3Real> EXP_HERO_NEEDED_PREV_VAL_FACTOR = new State<>(Section.MISC, "NeedHeroXPFormulaA", War3Real.class);
		public final static State<War3Real> EXP_HERO_NEEDED_LEVEL_FACTOR = new State<>(Section.MISC, "NeedHeroXPFormulaB", War3Real.class);
		public final static State<War3Real> EXP_HERO_NEEDED_CONST_FACTOR = new State<>(Section.MISC, "NeedHeroXPFormulaC", War3Real.class);
		public final static State<War3Real> EXP_HERO_RANGE = new State<>(Section.MISC, "HeroExpRange", War3Real.class);
		public final static State<War3Int> EXP_UNIT_MAX_LEVEL = new State<>(Section.MISC, "MaxUnitLevel", War3Int.class);

		public final static State<DataList<War3Int>> EXP_GRANTED_HERO = new State<>(Section.MISC, "GrantHeroXP", new DataTypeInfo(DataList.class, War3Int.class));
		public final static State<War3Real> EXP_GRANTED_HERO_PREV_VAL_FACTOR = new State<>(Section.MISC, "GrantHeroXPFormulaA", War3Real.class);
		public final static State<War3Real> EXP_GRANTED_HERO_CONST_FACTOR = new State<>(Section.MISC, "GrantHeroXPFormulaC", War3Real.class);
		public final static State<War3Real> EXP_GRANTED_HERO_LEVEL_FACTOR = new State<>(Section.MISC, "GrantHeroXPFormulaB", War3Real.class);
		public final static State<DataList<War3Int>> EXP_GRANTED_NORMAL = new State<>(Section.MISC, "GrantNormalXP", new DataTypeInfo(DataList.class, War3Int.class));
		public final static State<War3Real> EXP_GRANTED_NORMAL_PREV_VAL_FACTOR = new State<>(Section.MISC, "GrantNormalXPFormulaA", War3Real.class);
		public final static State<War3Real> EXP_GRANTED_NORMAL_CONST_FACTOR = new State<>(Section.MISC, "GrantNormalXPFormulaC", War3Real.class);
		public final static State<War3Real> EXP_GRANTED_NORMAL_LEVEL_FACTOR = new State<>(Section.MISC, "GrantNormalXPFormulaB", War3Real.class);
		public final static State<War3Real> EXP_GRANTED_SUMMON_FACTOR = new State<>(Section.MISC, "SummonedKillFactor", War3Real.class);

		public final static State<DataList<War3Int>> EXP_GRANTED_CREEP_REDUCTION = new State<>(Section.MISC, "HeroFactorXP", new DataTypeInfo(DataList.class, War3Int.class));
		public final static State<DataList<War3Real>> EXP_GRANTED_TOWNHALL1_FACTOR = new State<>(Section.TWN1, "XPFactor", new DataTypeInfo(DataList.class, War3Real.class));
		public final static State<DataList<War3Real>> EXP_GRANTED_TOWNHALL2_FACTOR = new State<>(Section.TWN2, "XPFactor", new DataTypeInfo(DataList.class, War3Real.class));
		public final static State<DataList<War3Real>> EXP_GRANTED_TOWNHALL3_FACTOR = new State<>(Section.TWN3, "XPFactor", new DataTypeInfo(DataList.class, War3Real.class));
		public final static State<DataList<War3Real>> EXP_GRANTED_TOWNHALL4_FACTOR = new State<>(Section.TWN4, "XPFactor", new DataTypeInfo(DataList.class, War3Real.class));
		public final static State<DataList<War3Real>> EXP_GRANTED_TOWNHALL5_FACTOR = new State<>(Section.TWN5, "XPFactor", new DataTypeInfo(DataList.class, War3Real.class));
		public final static State<DataList<War3Real>> EXP_GRANTED_TOWNHALL6_FACTOR = new State<>(Section.TWN6, "XPFactor", new DataTypeInfo(DataList.class, War3Real.class));
		public final static State<DataList<War3Real>> EXP_GRANTED_TOWNHALL7_FACTOR = new State<>(Section.TWN7, "XPFactor", new DataTypeInfo(DataList.class, War3Real.class));
		public final static State<DataList<War3Real>> EXP_GRANTED_TOWNHALL8_FACTOR = new State<>(Section.TWN8, "XPFactor", new DataTypeInfo(DataList.class, War3Real.class));
		public final static State<DataList<War3Real>> EXP_GRANTED_TOWNHALL9_FACTOR = new State<>(Section.TWN9, "XPFactor", new DataTypeInfo(DataList.class, War3Real.class));

		public final static State<War3Bool> UI_DISPLAY_STRUCTURE_STATUS = new State<>(Section.MISC, "DisplayBuildingStatus", War3Bool.class);
		public final static State<War3Bool> UI_DISPLAY_ENEMY_INV = new State<>(Section.MISC, "DisplayEnemyInventory", War3Bool.class);

		public final static State<War3Real> COMBAT_AGGRO_RANGE_SALE_ABIL = new State<>(Section.MISC, "AbilSaleAggroRange", War3Real.class);
		public final static State<War3Real> COMBAT_AGGRO_RANGE_SALE_ITEM = new State<>(Section.MISC, "ItemSaleAggroRange", War3Real.class);
		public final static State<War3Real> COMBAT_AGGRO_RANGE_SALE_UNIT = new State<>(Section.MISC, "UnitSaleAggroRange", War3Real.class);
		public final static State<War3Real> COMBAT_ARMOR_DAMAGE_REDUCTION_FACTOR = new State<>(Section.MISC, "DefenseArmor", War3Real.class);
		public final static State<War3Bool> COMBAT_ATTACK_BOUNCE_ALLOW_MULTI = new State<>(Section.MISC, "AllowMultiBounce", War3Bool.class);
		public final static State<War3Real> COMBAT_ATTACK_NOTIFY_DELAY = new State<>(Section.MISC, "AttackNotifyDelay", War3Real.class);
		public final static State<War3Real> COMBAT_ATTACK_NOTIFY_RANGE = new State<>(Section.MISC, "AttackNotifyRange", War3Real.class);
		public final static State<War3Bool> COMBAT_AURA_BY_BURROWED = new State<>(Section.MISC, "BurrowedUnitsBestowAuras", War3Bool.class);
		public final static State<War3Bool> COMBAT_AURA_BY_FLYINGS_HEROES = new State<>(Section.MISC, "FlyingHeroesBestowAuras", War3Bool.class);
		public final static State<War3Bool> COMBAT_AURA_BY_INVIS = new State<>(Section.MISC, "InvisibleUnitsBestowAuras", War3Bool.class);
		public final static State<War3Bool> COMBAT_AURA_BY_POLYMORPHED = new State<>(Section.MISC, "PolymorphedUnitsBestowAuras", War3Bool.class);
		public final static State<War3Bool> COMBAT_AURA_BY_REANIMATED = new State<>(Section.MISC, "AnimatedUnitsBestowAuras", War3Bool.class);
		public final static State<War3Real> COMBAT_CALL_FOR_HELP_RANGE = new State<>(Section.MISC, "CallForHelp", War3Real.class);
		public final static State<War3Real> COMBAT_CALL_FOR_HELP_RANGE_CREEP = new State<>(Section.MISC, "CreepCallForHelp", War3Real.class);
		public final static State<DefenseTable> COMBAT_DAMAGE_TABLE_CHAOS = new State<>(Section.MISC, "DamageBonusChaos", DefenseTable.class);
		public final static State<DefenseTable> COMBAT_DAMAGE_TABLE_HERO = new State<>(Section.MISC, "DamageBonusHero", DefenseTable.class);
		public final static State<DefenseTable> COMBAT_DAMAGE_TABLE_MAGIC = new State<>(Section.MISC, "DamageBonusMagic", DefenseTable.class);
		public final static State<DefenseTable> COMBAT_DAMAGE_TABLE_NORMAL = new State<>(Section.MISC, "DamageBonusNormal", DefenseTable.class);
		public final static State<DefenseTable> COMBAT_DAMAGE_TABLE_PIERCE = new State<>(Section.MISC, "DamageBonusPierce", DefenseTable.class);
		public final static State<DefenseTable> COMBAT_DAMAGE_TABLE_SIEGE = new State<>(Section.MISC, "DamageBonusSiege", DefenseTable.class);
		public final static State<DefenseTable> COMBAT_DAMAGE_TABLE_SPELL = new State<>(Section.MISC, "DamageBonusSpells", DefenseTable.class);
		public final static State<War3Bool> COMBAT_ETHEREAL_DAMAGE_BONUS_ON_ALLY = new State<>(Section.MISC, "EtherealDamageBonusAlly", War3Bool.class);
		public final static State<AttackTable> COMBAT_ETHEREAL_DMG_FACTOR = new State<>(Section.MISC, "EtherealDamageBonus", AttackTable.class);
		public final static State<War3Real> COMBAT_ETHEREAL_HEAL_FACTOR = new State<>(Section.MISC, "EtherealHealBonus", War3Real.class);
		public final static State<War3Real> COMBAT_FOG_ATTACK_REVEAL_RAD = new State<>(Section.MISC, "FoggedAttackRevealRadius", War3Real.class);
		public final static State<War3Real> COMBAT_FOG_DEATH_REVEAL_RAD = new State<>(Section.MISC, "DyingRevealRadius", War3Real.class);
		public final static State<War3Real> COMBAT_GUARD_RANGE = new State<>(Section.MISC, "GuardDistance", War3Real.class);
		public final static State<War3Real> COMBAT_GUARD_RETURN_RANGE_MAX = new State<>(Section.MISC, "MaxGuardDistance", War3Real.class);
		public final static State<War3Real> COMBAT_GUARD_RETURN_TIME_MAX = new State<>(Section.MISC, "GuardReturnTime", War3Real.class);
		public final static State<War3Bool> COMBAT_INVU_SUMMONS_TAKE_DISPEL_DAMAGE = new State<>(Section.MISC, "InvulnSummonDispelDamage", War3Bool.class);
		public final static State<War3Bool> COMBAT_MAGIC_IMMUNE_RESIST_DAMAGE = new State<>(Section.MISC, "MagicImmunesResistDamage", War3Bool.class);
		public final static State<War3Bool> COMBAT_MAGIC_IMMUNE_RESIST_LEECH = new State<>(Section.MISC, "MagicImmunesResistLeech", War3Bool.class);
		public final static State<War3Bool> COMBAT_MAGIC_IMMUNE_RESIST_THORNS = new State<>(Section.MISC, "MagicImmunesResistThorns", War3Bool.class);
		public final static State<War3Bool> COMBAT_MAGIC_IMMUNE_RESIST_ULTIMATES = new State<>(Section.MISC, "MagicImmunesResistUltimates", War3Bool.class);
		public final static State<War3Real> COMBAT_MISS_CHANCE = new State<>(Section.MISC, "ChanceToMiss", War3Real.class);
		public final static State<War3Real> COMBAT_MISS_DAMAGE_REDUCTION = new State<>(Section.MISC, "MissDamageReduction", War3Real.class);

		public final static State<War3Real> DECAY_DURATION_BONE = new State<>(Section.MISC, "BoneDecayTime", War3Real.class);
		public final static State<War3Real> DECAY_DURATION_CANCEL = new State<>(Section.MISC, "CancelTime", War3Real.class);
		public final static State<War3Real> DECAY_DURATION_FLESH = new State<>(Section.MISC, "DecayTime", War3Real.class);
		public final static State<War3Real> DECAY_DURATION_FOG_FLASH = new State<>(Section.MISC, "FogFlashTime", War3Real.class);
		public final static State<War3Real> DECAY_DURATION_HERO = new State<>(Section.MISC, "DissipateTime", War3Real.class);
		public final static State<War3Real> DECAY_DURATION_MISSILE = new State<>(Section.MISC, "BulletDeathTime", War3Real.class);
		public final static State<War3Real> DECAY_DURATION_SFX = new State<>(Section.MISC, "EffectDeathTime", War3Real.class);
		public final static State<War3Real> DECAY_DURATION_STRUCTURE = new State<>(Section.MISC, "StructureDecayTime", War3Real.class);

		public final static State<War3Real> HERO_ATTR_ARMOR_BASE = new State<>(Section.MISC, "AgiDefenseBase", War3Real.class);
		public final static State<War3Real> HERO_ATTR_AGI_ARMOR_UP = new State<>(Section.MISC, "AgiDefenseBonus", War3Real.class);
		public final static State<War3Real> HERO_ATTR_AGI_ATTACK_SPEED_UP = new State<>(Section.MISC, "AgiAttackSpeedBonus", War3Real.class);
		public final static State<War3Real> HERO_ATTR_AGI_MOVE_SPEED_UP = new State<>(Section.MISC, "AgiMoveBonus", War3Real.class);
		public final static State<War3Real> HERO_ATTR_INT_MANA_UP = new State<>(Section.MISC, "IntManaBonus", War3Real.class);
		public final static State<War3Real> HERO_ATTR_INT_MANA_REGEN_UP = new State<>(Section.MISC, "IntRegenBous", War3Real.class);
		public final static State<War3Real> HERO_ATTR_STR_ATTACK_UP = new State<>(Section.MISC, "StrAttackBonus", War3Real.class);
		public final static State<War3Real> HERO_ATTR_STR_LIFE_UP = new State<>(Section.MISC, "StrHitPointBonus", War3Real.class);
		public final static State<War3Real> HERO_ATTR_STR_LIFE_REGEN_UP = new State<>(Section.MISC, "StrRegenBonus", War3Real.class);
		public final static State<War3Real> HERO_AWAKEN_COSTS_GOLD_CONST_FACTOR = new State<>(Section.MISC, "AwakenBaseFactor", War3Real.class);
		public final static State<War3Real> HERO_AWAKEN_COSTS_GOLD_LEVEL_FACTOR = new State<>(Section.MISC, "AwakenLevelFactor", War3Real.class);
		public final static State<War3Real> HERO_AWAKEN_COSTS_GOLD_LUMBER_FACTOR_MAX = new State<>(Section.MISC, "AwakenMaxFactor", War3Real.class);
		public final static State<War3Int> HERO_AWAKEN_COSTS_GOLD_MAX = new State<>(Section.MISC, "HeroMaxAwakenCostGold", War3Int.class);
		public final static State<War3Real> HERO_AWAKEN_COSTS_LUMBER_CONST_FACTOR = new State<>(Section.MISC, "AwakenBaseLumberFactor", War3Real.class);
		public final static State<War3Real> HERO_AWAKEN_COSTS_LUMBER_LEVEL_FACTOR = new State<>(Section.MISC, "AwakenLumberLevelFactor", War3Real.class);
		public final static State<War3Int> HERO_AWAKEN_COSTS_LUMBER_MAX = new State<>(Section.MISC, "HeroMaxAwakenCostLumber", War3Int.class);
		public final static State<War3Real> HERO_AWAKEN_LIFE_FACTOR = new State<>(Section.MISC, "HeroAwakenLifeFactor", War3Real.class);
		public final static State<War3Real> HERO_AWAKEN_MANA_FACTOR = new State<>(Section.MISC, "HeroAwakenManaFactor", War3Real.class);
		public final static State<War3Bool> HERO_AWAKEN_USE_INITIAL_MANA = new State<>(Section.MISC, "HeroAwakenManaStart", War3Bool.class);
		public final static State<War3Real> HERO_REVIVE_COSTS_GOLD_CONST_FACTOR = new State<>(Section.MISC, "ReviveBaseFactor", War3Real.class);
		public final static State<War3Real> HERO_REVIVE_COSTS_GOLD_LEVEL_FACTOR = new State<>(Section.MISC, "ReviveLevelFactor", War3Real.class);
		public final static State<War3Real> HERO_REVIVE_COSTS_GOLD_LUMBER_FACTOR_MAX = new State<>(Section.MISC, "ReviveMaxFactor", War3Real.class);
		public final static State<War3Int> HERO_REVIVE_COSTS_GOLD_MAX = new State<>(Section.MISC, "HeroMaxReviveCostGold", War3Int.class);
		public final static State<War3Real> HERO_REVIVE_COSTS_LUMBER_CONST_FACTOR = new State<>(Section.MISC, "ReviveBaseLumberFactor", War3Real.class);
		public final static State<War3Real> HERO_REVIVE_COSTS_LUMBER_LEVEL_FACTOR = new State<>(Section.MISC, "ReviveLumberLevelFactor", War3Real.class);
		public final static State<War3Int> HERO_REVIVE_COSTS_LUMBER_MAX = new State<>(Section.MISC, "HeroMaxReviveCostLumber", War3Int.class);
		public final static State<War3Real> HERO_REVIVE_COSTS_TIME_FACTOR = new State<>(Section.MISC, "ReviveTimeFactor", War3Real.class);
		public final static State<War3Real> HERO_REVIVE_COSTS_TIME_FACTOR_MAX = new State<>(Section.MISC, "ReviveMaxTimeFactor", War3Real.class);
		public final static State<War3Real> HERO_REVIVE_COSTS_TIME_MAX = new State<>(Section.MISC, "HeroMaxReviveTime", War3Real.class);
		public final static State<War3Real> HERO_REVIVE_LIFE_FACTOR = new State<>(Section.MISC, "HeroReviveLifeFactor", War3Real.class);
		public final static State<War3Real> HERO_REVIVE_MANA_FACTOR = new State<>(Section.MISC, "HeroReviveManaFactor", War3Real.class);
		public final static State<War3Bool> HERO_REVIVE_USE_INITIAL_MANA = new State<>(Section.MISC, "HeroReviveManaStart", War3Bool.class);

		public final static State<War3Bool> ILLUSIONS_ARE_AUTOCAST_TARGS = new State<>(Section.MISC, "IllusionsGetAutocast", War3Bool.class);
		public final static State<War3Bool> ILLUSIONS_BESTOW_AURAS = new State<>(Section.MISC, "IllusionsBestowAuras", War3Bool.class);
		public final static State<War3Bool> ILLUSIONS_CAN_RESTORE_LIFE = new State<>(Section.MISC, "IllusionsCanRestoreLife", War3Bool.class);
		public final static State<War3Bool> ILLUSIONS_CAN_RESTORE_MANA = new State<>(Section.MISC, "IllusionsCanRestoreMana", War3Bool.class);
		public final static State<War3Bool> ILLUSIONS_RECEIVE_ARMOR_BONUSES = new State<>(Section.MISC, "IllusionsGetDefenseBonus", War3Bool.class);
		public final static State<War3Bool> ILLUSIONS_RECEIVE_ATTACK_BONUSES = new State<>(Section.MISC, "IllusionsGetAttackBonus", War3Bool.class);
		public final static State<War3Bool> ILLUSIONS_RECEIVE_ATTACK_SPEED_BONUSES = new State<>(Section.MISC, "IllusionsGetAttackSpeedBonus", War3Bool.class);
		public final static State<War3Bool> ILLUSIONS_RECEIVE_MOVE_SPEED_BONUSES = new State<>(Section.MISC, "IllusionsGetMoveSpeedBonus", War3Bool.class);

		public final static State<War3Real> ITEM_DROP_RANGE = new State<>(Section.MISC, "DropItemRange", War3Real.class);
		public final static State<War3Real> ITEM_GIVE_RANGE = new State<>(Section.MISC, "GiveItemRange", War3Real.class);
		public final static State<War3Real> ITEM_PICK_RANGE = new State<>(Section.MISC, "PickupItemRange", War3Real.class);
		public final static State<War3Real> ITEM_PAWN_RANGE = new State<>(Section.MISC, "PawnItemRange", War3Real.class);
		public final static State<War3Real> ITEM_PAWN_RATE = new State<>(Section.MISC, "PawnItemRate", War3Real.class);

		public final static State<War3Real> MOVE_FOLLOW_RANGE = new State<>(Section.MISC, "FollowRange", War3Real.class);
		public final static State<War3Real> MOVE_FOLLOW_RANGE_ITEM = new State<>(Section.MISC, "FollowItemRange", War3Real.class);
		public final static State<War3Real> MOVE_FOLLOW_RANGE_STRUCTURE = new State<>(Section.MISC, "StructureFollowRange", War3Real.class);
		public final static State<War3Bool> MOVE_SPEED_BONUSES_STACK = new State<>(Section.MISC, "MoveSpeedBonusesStack", War3Bool.class);
		public final static State<War3Real> MOVE_SPEED_UNIT_MAX = new State<>(Section.MISC, "MaxUnitSpeed", War3Real.class);
		public final static State<War3Real> MOVE_SPEED_UNIT_MIN = new State<>(Section.MISC, "MinUnitSpeed", War3Real.class);
		public final static State<War3Real> MOVE_SPEED_STRUCTURE_MAX = new State<>(Section.MISC, "MaxBldgSpeed", War3Real.class);
		public final static State<War3Real> MOVE_SPEED_STRUCTURE_MIN = new State<>(Section.MISC, "MinBldgSpeed", War3Real.class);

		public final static State<War3Int> RESOURCES_GOLD_MINE_GOLD_LOW = new State<>(Section.MISC, "LowGoldAmount", War3Int.class);
		public final static State<War3Int> RESOURCES_GOLD_MINE_GOLD_MAX = new State<>(Section.MISC, "GoldMineMaxGold", War3Int.class);
		public final static State<War3Int> RESOURCES_GOLD_MINE_OWN_DURATION = new State<>(Section.MISC, "GoldMineOwnDuration", War3Int.class);
		public final static State<War3Int> RESOURCES_SUPPLY_MAX = new State<>(Section.MISC, "FoodCeiling", War3Int.class);
		public final static State<DataList<War3Real>> RESOURCES_SUPPLY_UPKEEP_COSTS_GOLD = new State<>(Section.MISC, "UpkeepGoldTax", new DataTypeInfo(DataList.class, War3Real.class));
		public final static State<DataList<War3Real>> RESOURCES_SUPPLY_UPKEEP_COSTS_LUMBER = new State<>(Section.MISC, "UpkeepLumberTax", new DataTypeInfo(DataList.class, War3Real.class));
		public final static State<DataList<War3Int>> RESOURCES_SUPPLY_UPKEEP_LEVELS = new State<>(Section.MISC, "UpkeepUsage", new DataTypeInfo(DataList.class, War3Int.class));

		public final static State<War3Real> STRUCTURE_ANGLE = new State<>(Section.MISC, "BuildingAngle", War3Real.class);
		public final static State<War3Real> STRUCTURE_ANGLE_ROOTED = new State<>(Section.MISC, "RootAngle", War3Real.class);
		public final static State<War3Real> STRUCTURE_LIFE_DRAIN_RATE = new State<>(Section.MISC, "ConstructionLifeDrainRate", War3Real.class);
		public final static State<War3Real> STRUCTURE_NEUTRAL_USE_NOTIFY_RAD = new State<>(Section.MISC, "NeutralUseNotifyRadius", War3Real.class);
		public final static State<War3Real> STRUCTURE_PLACEMENT_NOTIFY_RAD = new State<>(Section.MISC, "BuildingPlacementNotifyRadius", War3Real.class);
		public final static State<War3Real> STRUCTURE_UNBLIGHT_RAD = new State<>(Section.MISC, "BuildingUnblightRadius", War3Real.class);

		public final static State<War3Bool> TECH_CONSTRUCTION_REFUND_DAMAGE_PENALTY = new State<>(Section.MISC, "ConstructionDamageRefundPenalty", War3Bool.class);
		public final static State<War3Real> TECH_CONSTRRUCTION_REFUND_RATE = new State<>(Section.MISC, "ConstructionRefundRate", War3Real.class);
		public final static State<DataList<UnitId>> TECH_DEPENDENCY_ALTAR = new State<>(Section.TALT, "DependencyOr", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<UnitId>> TECH_DEPENDENCY_HERO = new State<>(Section.HERO, "DependencyOr", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<UnitId>> TECH_DEPENDENCY_TOWNHALL1 = new State<>(Section.TWN1, "DependencyOr", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<UnitId>> TECH_DEPENDENCY_TOWNHALL2 = new State<>(Section.TWN2, "DependencyOr", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<UnitId>> TECH_DEPENDENCY_TOWNHALL3 = new State<>(Section.TWN3, "DependencyOr", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<UnitId>> TECH_DEPENDENCY_TOWNHALL4 = new State<>(Section.TWN4, "DependencyOr", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<UnitId>> TECH_DEPENDENCY_TOWNHALL5 = new State<>(Section.TWN5, "DependencyOr", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<UnitId>> TECH_DEPENDENCY_TOWNHALL6 = new State<>(Section.TWN6, "DependencyOr", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<UnitId>> TECH_DEPENDENCY_TOWNHALL7 = new State<>(Section.TWN7, "DependencyOr", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<UnitId>> TECH_DEPENDENCY_TOWNHALL8 = new State<>(Section.TWN8, "DependencyOr", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<DataList<UnitId>> TECH_DEPENDENCY_TOWNHALL9 = new State<>(Section.TWN9, "DependencyOr", new DataTypeInfo(DataList.class, UnitId.class));
		public final static State<War3Real> TECH_RESEARCH_REFUND_RATE = new State<>(Section.MISC, "ResearchRefundRate", War3Real.class);
		public final static State<War3Real> TECH_REVIVE_REFUND_RATE = new State<>(Section.MISC, "ReviveRefundRate", War3Real.class);
		public final static State<War3Real> TECH_TRAIN_REFUND_RATE = new State<>(Section.MISC, "TrainRefundRate", War3Real.class);
		public final static State<War3Bool> TECH_UPGRADE_COSTS_ARE_RELATIVE = new State<>(Section.MISC, "RelativeUpgradeCost", War3Bool.class);
		public final static State<War3Bool> TECH_UPGRADE_REFUND_DAMAGE_PENALTY = new State<>(Section.MISC, "UpgradeDamageRefundPenalty", War3Bool.class);
		public final static State<War3Real> TECH_UPGRADE_REFUND_RATE = new State<>(Section.MISC, "UpgradeRefundRate", War3Real.class);

		public final static State<War3String> TEXT_ALTAR_NAME = new State<>(Section.TALT, "Name", War3String.class);
		public final static State<War3String> TEXT_HERO_NAME = new State<>(Section.HERO, "Name", War3String.class);
		public final static State<War3String> TEXT_TOWNHALL1_NAME = new State<>(Section.TWN1, "Name", War3String.class);
		public final static State<War3String> TEXT_TOWNHALL2_NAME = new State<>(Section.TWN2, "Name", War3String.class);
		public final static State<War3String> TEXT_TOWNHALL3_NAME = new State<>(Section.TWN3, "Name", War3String.class);
		public final static State<War3String> TEXT_TOWNHALL4_NAME = new State<>(Section.TWN4, "Name", War3String.class);
		public final static State<War3String> TEXT_TOWNHALL5_NAME = new State<>(Section.TWN5, "Name", War3String.class);
		public final static State<War3String> TEXT_TOWNHALL6_NAME = new State<>(Section.TWN6, "Name", War3String.class);
		public final static State<War3String> TEXT_TOWNHALL7_NAME = new State<>(Section.TWN7, "Name", War3String.class);
		public final static State<War3String> TEXT_TOWNHALL8_NAME = new State<>(Section.TWN8, "Name", War3String.class);
		public final static State<War3String> TEXT_TOWNHALL9_NAME = new State<>(Section.TWN9, "Name", War3String.class);

		public final static State<War3Real> TIME_DAWN = new State<>(Section.MISC, "Dawn", War3Real.class);
		public final static State<War3Real> TIME_DAY_DURATION = new State<>(Section.MISC, "DayLength", War3Real.class);
		public final static State<War3Real> TIME_DAY_HOURS = new State<>(Section.MISC, "DayHours", War3Real.class);
		public final static State<War3Real> TIME_DUSK = new State<>(Section.MISC, "Dusk", War3Real.class);

		public final static State<War3Int> TRADING_INC_SMALL = new State<>(Section.MISC, "TradingIncSmall", War3Int.class);
		public final static State<War3Int> TRADING_INC_LARGE = new State<>(Section.MISC, "TradingIncLarge", War3Int.class);

		public final static State<War3Real> COLLISION_SIZE_MAX = new State<>(Section.MISC, "MaxCollisionRadius", War3Real.class);
		public final static State<War3Int> CREEP_CAMP_RADIUS_CELLS = new State<>(Section.MISC, "CreepCampPathingCellDistance", War3Int.class);
		public final static State<War3Real> FROST_ATTACK_SPEED_DEC = new State<>(Section.MISC, "FrostAttackSpeedDecrease", War3Real.class);
		public final static State<War3Real> FROST_MOVE_SPEED_DEC = new State<>(Section.MISC, "FrostMoveSpeedDecrease", War3Real.class);
		public final static State<War3Real> INVIS_SPEED = new State<>(Section.MISC, "InvisSpeed", War3Real.class);
		public final static State<War3Bool> MORPH_LAND_CLOSEST = new State<>(Section.MISC, "MorphLandClosest", War3Bool.class);
		public final static State<War3Bool> MORPH_DISABLE_ALTERNATE_ABILS = new State<>(Section.MISC, "MorphAlternateDisable", War3Bool.class);
		public final static State<War3Real> RALLY_Z_OFFSET = new State<>(Section.MISC, "RallyZOffset", War3Real.class);
		public final static State<War3Real> REACTION_DELAY = new State<>(Section.MISC, "ReactionDelay", War3Real.class);
		public final static State<War3Real> SCALED_ANIM_TIME = new State<>(Section.MISC, "ScaledAnimTime", War3Real.class);
		public final static State<War3Real> SPELL_CAST_RANGE_BUF = new State<>(Section.MISC, "SpellCastRangeBuffer", War3Real.class);

		public State(@Nonnull String name, @Nonnull DataTypeInfo typeInfo, @Nullable T defVal) {
			super(name, typeInfo, defVal);
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
	
	@Override
	public void read(@Nonnull File file) throws IOException {
		super.read(file);
	}
	
	@Override
	public void write(@Nonnull File file) throws IOException {
		super.write(file);
	}
	
	public MiscTXT() {
		
	}
}