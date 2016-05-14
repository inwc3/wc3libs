package txt.app;

import java.io.File;
import java.io.IOException;

import dataTypes.app.AttackTable;
import dataTypes.app.Bool;
import dataTypes.app.DefenseTable;
import dataTypes.app.Int;
import dataTypes.app.IntList;
import dataTypes.app.Real;
import dataTypes.app.RealList;
import dataTypes.app.UnitList;
import txt.TXT;
import txt.TXTSectionId;
import txt.TXTState;

public class MiscTXT extends TXT {
	public final static String GAME_PATH = "war3mapMisc.txt";
	
	public static class States {
		private static class Section {
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
			
			private TXTSectionId _id;
			
			public TXTSectionId getId() {
				return _id;
			}
			
			public Section(String name) {
				_id = TXTSectionId.valueOf(name);
			}
		}
		
		private static class State<T> extends TXTState {
			public State(Section section, String name) {
				super(section.getId(), name);
			}
		}

		public final static State<Bool> ABIL_DEPENDENCY_CHECK = new State<>(Section.MISC, "DepCheckAlias");
		
		public final static State<Bool> ABIL_AMULET_OF_RECALL_CLUSTER = new State<>(Section.MISC, "AmuletOfRecallCluster");
		public final static State<Bool> ABIL_AVATAR_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateAvatar");
		public final static State<Bool> ABIL_AVENGER_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateAvengerForm");
		public final static State<Bool> ABIL_BARKSKIN_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateBarkskin");
		public final static State<Bool> ABIL_BEAR_FORM_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateBearForm");
		public final static State<Bool> ABIL_BLADESTORM_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateBladestorm");
		public final static State<Bool> ABIL_BURROW_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateBurrow");
		public final static State<Bool> ABIL_CALL_TO_ARMS_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateCallToArms");
		public final static State<Bool> ABIL_CHEMICAL_RAGE_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateChemicalRage");
		public final static State<Bool> ABIL_CORPOREAL_FORM_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateCorporealForm");
		public final static State<Bool> ABIL_CYCLONE_HIDES_TARGET = new State<>(Section.MISC, "CycloneStasis");
		public final static State<Bool> ABIL_DARK_SUMMONING_CLUSTER = new State<>(Section.MISC, "DarkSummoningCluster");
		public final static State<Bool> ABIL_DEFEND_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateDefend");
		public final static State<Bool> ABIL_DEFEND_CAN_DEFLECT = new State<>(Section.MISC, "DefendDeflection");
		public final static State<Bool> ABIL_DRAIN_GIVES_BONUS_LIFE = new State<>(Section.MISC, "DrainGivesBonusLife");
		public final static State<Bool> ABIL_DRAIN_GIVES_BONUS_MANA = new State<>(Section.MISC, "DrainGivesBonusMana");
		public final static State<Bool> ABIL_DRAIN_USES_ETHEREAL_BONUS = new State<>(Section.MISC, "DrainUsesEtherealBonus");
		public final static State<Bool> ABIL_DRAIN_TRANFERS_LIFE = new State<>(Section.MISC, "DrainTransfersLife");
		public final static State<Bool> ABIL_DRAIN_TRANFERS_MANA = new State<>(Section.MISC, "DrainTransfersMana");
		public final static State<Bool> ABIL_DIVINE_SHIELD_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateDivineShield");
		public final static State<Bool> ABIL_ENSNARE_IS_MAGIC = new State<>(Section.MISC, "EnsnareIsMagic");
		public final static State<Int> ABIL_HERO_LEVEL_SKIP = new State<>(Section.MISC, "HeroAbilityLevelSkip");
		public final static State<Bool> ABIL_IMMOLATION_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateImmolation");
		public final static State<Bool> ABIL_MANA_FLARE_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateManaFlare");
		public final static State<Bool> ABIL_MANA_SHIELD_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateManaShield");
		public final static State<Bool> ABIL_MASS_TELEPORT_CLUSTER = new State<>(Section.MISC, "MassTeleportCluster");
		public final static State<Bool> ABIL_METAMORPHOSIS_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateMetamorphosis");
		public final static State<Bool> ABIL_RAVEN_FORM_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateRavenForm");
		public final static State<Bool> ABIL_ROBO_GOBLIN_FORM_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateRoboGoblin");
		public final static State<Bool> ABIL_STONE_FORM_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateStoneForm");
		public final static State<Bool> ABIL_SUBMERGE_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateSubmerge");
		public final static State<Bool> ABIL_TOWN_PORTAL_CLUSTER = new State<>(Section.MISC, "TownPortalCluster");
		public final static State<Bool> ABIL_WEB_IS_MAGIC = new State<>(Section.MISC, "WebIsMagic");
		public final static State<Bool> ABIL_WIND_WALK_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateWindWalk");
		
		public final static State<Bool> EXP_GLOBAL = new State<>(Section.MISC, "GlobalExperience");
		public final static State<Bool> EXP_STRUCTURE_KILLS_GIVE = new State<>(Section.MISC, "BuildingKillsGiveExp");
		public final static State<Int> EXP_HERO_MAX_LEVEL = new State<>(Section.MISC, "MaxHeroLevel");
		public final static State<Bool> EXP_HERO_MAX_LEVEL_DRAIN = new State<>(Section.MISC, "MaxLevelHeroesDrainExp");
		public final static State<IntList> EXP_HERO_NEEDED = new State<>(Section.MISC, "NeedHeroXP");
		public final static State<Real> EXP_HERO_NEEDED_PREV_VAL_FACTOR = new State<>(Section.MISC, "NeedHeroXPFormulaA");
		public final static State<Real> EXP_HERO_NEEDED_LEVEL_FACTOR = new State<>(Section.MISC, "NeedHeroXPFormulaB");
		public final static State<Real> EXP_HERO_NEEDED_CONST_FACTOR = new State<>(Section.MISC, "NeedHeroXPFormulaC");
		public final static State<Real> EXP_HERO_RANGE = new State<>(Section.MISC, "HeroExpRange");
		public final static State<Int> EXP_UNIT_MAX_LEVEL = new State<>(Section.MISC, "MaxUnitLevel");

		public final static State<IntList> EXP_GRANTED_HERO = new State<>(Section.MISC, "GrantHeroXP");
		public final static State<Real> EXP_GRANTED_HERO_PREV_VAL_FACTOR = new State<>(Section.MISC, "GrantHeroXPFormulaA");
		public final static State<Real> EXP_GRANTED_HERO_CONST_FACTOR = new State<>(Section.MISC, "GrantHeroXPFormulaC");
		public final static State<Real> EXP_GRANTED_HERO_LEVEL_FACTOR = new State<>(Section.MISC, "GrantHeroXPFormulaB");
		public final static State<IntList> EXP_GRANTED_NORMAL = new State<>(Section.MISC, "GrantNormalXP");
		public final static State<Real> EXP_GRANTED_NORMAL_PREV_VAL_FACTOR = new State<>(Section.MISC, "GrantNormalXPFormulaA");
		public final static State<Real> EXP_GRANTED_NORMAL_CONST_FACTOR = new State<>(Section.MISC, "GrantNormalXPFormulaC");
		public final static State<Real> EXP_GRANTED_NORMAL_LEVEL_FACTOR = new State<>(Section.MISC, "GrantNormalXPFormulaB");
		public final static State<Real> EXP_GRANTED_SUMMON_FACTOR = new State<>(Section.MISC, "SummonedKillFactor");
		
		public final static State<IntList> EXP_GRANTED_CREEP_REDUCTION = new State<>(Section.MISC, "HeroFactorXP");
		public final static State<RealList> EXP_GRANTED_TOWNHALL1_FACTOR = new State<>(Section.TWN1, "XPFactor");
		public final static State<RealList> EXP_GRANTED_TOWNHALL2_FACTOR = new State<>(Section.TWN2, "XPFactor");
		public final static State<RealList> EXP_GRANTED_TOWNHALL3_FACTOR = new State<>(Section.TWN3, "XPFactor");
		public final static State<RealList> EXP_GRANTED_TOWNHALL4_FACTOR = new State<>(Section.TWN4, "XPFactor");
		public final static State<RealList> EXP_GRANTED_TOWNHALL5_FACTOR = new State<>(Section.TWN5, "XPFactor");
		public final static State<RealList> EXP_GRANTED_TOWNHALL6_FACTOR = new State<>(Section.TWN6, "XPFactor");
		public final static State<RealList> EXP_GRANTED_TOWNHALL7_FACTOR = new State<>(Section.TWN7, "XPFactor");
		public final static State<RealList> EXP_GRANTED_TOWNHALL8_FACTOR = new State<>(Section.TWN8, "XPFactor");
		public final static State<RealList> EXP_GRANTED_TOWNHALL9_FACTOR = new State<>(Section.TWN9, "XPFactor");
		
		public final static State<Bool> UI_DISPLAY_STRUCTURE_STATUS = new State<>(Section.MISC, "DisplayBuildingStatus");
		public final static State<Bool> UI_DISPLAY_ENEMY_INV = new State<>(Section.MISC, "DisplayEnemyInventory");

		public final static State<Real> COMBAT_AGGRO_RANGE_SALE_ABIL = new State<>(Section.MISC, "AbilSaleAggroRange");
		public final static State<Real> COMBAT_AGGRO_RANGE_SALE_ITEM = new State<>(Section.MISC, "ItemSaleAggroRange");
		public final static State<Real> COMBAT_AGGRO_RANGE_SALE_UNIT = new State<>(Section.MISC, "UnitSaleAggroRange");
		public final static State<Real> COMBAT_ARMOR_DAMAGE_REDUCTION_FACTOR = new State<>(Section.MISC, "DefenseArmor");
		public final static State<Bool> COMBAT_ATTACK_BOUNCE_ALLOW_MULTI = new State<>(Section.MISC, "AllowMultiBounce");
		public final static State<Real> COMBAT_ATTACK_NOTIFY_DELAY = new State<>(Section.MISC, "AttackNotifyDelay");
		public final static State<Real> COMBAT_ATTACK_NOTIFY_RANGE = new State<>(Section.MISC, "AttackNotifyRange");
		public final static State<Bool> COMBAT_AURA_BY_BURROWED = new State<>(Section.MISC, "BurrowedUnitsBestowAuras");
		public final static State<Bool> COMBAT_AURA_BY_FLYINGS_HEROES = new State<>(Section.MISC, "FlyingHeroesBestowAuras");
		public final static State<Bool> COMBAT_AURA_BY_INVIS = new State<>(Section.MISC, "InvisibleUnitsBestowAuras");
		public final static State<Bool> COMBAT_AURA_BY_POLYMORPHED = new State<>(Section.MISC, "PolymorphedUnitsBestowAuras");
		public final static State<Bool> COMBAT_AURA_BY_REANIMATED = new State<>(Section.MISC, "AnimatedUnitsBestowAuras");
		public final static State<Real> COMBAT_CALL_FOR_HELP_RANGE = new State<>(Section.MISC, "CallForHelp");
		public final static State<Real> COMBAT_CALL_FOR_HELP_RANGE_CREEP = new State<>(Section.MISC, "CreepCallForHelp");
		public final static State<DefenseTable> COMBAT_DAMAGE_TABLE_CHAOS = new State<>(Section.MISC, "DamageBonusChaos");
		public final static State<DefenseTable> COMBAT_DAMAGE_TABLE_HERO = new State<>(Section.MISC, "DamageBonusHero");
		public final static State<DefenseTable> COMBAT_DAMAGE_TABLE_MAGIC = new State<>(Section.MISC, "DamageBonusMagic");
		public final static State<DefenseTable> COMBAT_DAMAGE_TABLE_NORMAL = new State<>(Section.MISC, "DamageBonusNormal");
		public final static State<DefenseTable> COMBAT_DAMAGE_TABLE_PIERCE = new State<>(Section.MISC, "DamageBonusPierce");
		public final static State<DefenseTable> COMBAT_DAMAGE_TABLE_SIEGE = new State<>(Section.MISC, "DamageBonusSiege");
		public final static State<DefenseTable> COMBAT_DAMAGE_TABLE_SPELL = new State<>(Section.MISC, "DamageBonusSpells");
		public final static State<Bool> COMBAT_ETHEREAL_DAMAGE_BONUS_ON_ALLY = new State<>(Section.MISC, "EtherealDamageBonusAlly");
		public final static State<AttackTable> COMBAT_ETHEREAL_DMG_FACTOR = new State<>(Section.MISC, "EtherealDamageBonus");
		public final static State<Real> COMBAT_ETHEREAL_HEAL_FACTOR = new State<>(Section.MISC, "EtherealHealBonus");
		public final static State<Real> COMBAT_FOG_ATTACK_REVEAL_RAD = new State<>(Section.MISC, "FoggedAttackRevealRadius");
		public final static State<Real> COMBAT_FOG_DEATH_REVEAL_RAD = new State<>(Section.MISC, "DyingRevealRadius");
		public final static State<Real> COMBAT_GUARD_RANGE = new State<>(Section.MISC, "GuardDistance");
		public final static State<Real> COMBAT_GUARD_RETURN_RANGE_MAX = new State<>(Section.MISC, "MaxGuardDistance");
		public final static State<Real> COMBAT_GUARD_RETURN_TIME_MAX = new State<>(Section.MISC, "GuardReturnTime");
		public final static State<Bool> COMBAT_INVU_SUMMONS_TAKE_DISPEL_DAMAGE = new State<>(Section.MISC, "InvulnSummonDispelDamage");
		public final static State<Bool> COMBAT_MAGIC_IMMUNE_RESIST_DAMAGE = new State<>(Section.MISC, "MagicImmunesResistDamage");
		public final static State<Bool> COMBAT_MAGIC_IMMUNE_RESIST_LEECH = new State<>(Section.MISC, "MagicImmunesResistLeech");
		public final static State<Bool> COMBAT_MAGIC_IMMUNE_RESIST_THORNS = new State<>(Section.MISC, "MagicImmunesResistThorns");
		public final static State<Bool> COMBAT_MAGIC_IMMUNE_RESIST_ULTIMATES = new State<>(Section.MISC, "MagicImmunesResistUltimates");
		public final static State<Real> COMBAT_MISS_CHANCE = new State<>(Section.MISC, "ChanceToMiss");
		public final static State<Real> COMBAT_MISS_DAMAGE_REDUCTION = new State<>(Section.MISC, "MissDamageReduction");
		
		public final static State<Real> DECAY_DURATION_BONE = new State<>(Section.MISC, "BoneDecayTime");
		public final static State<Real> DECAY_DURATION_CANCEL = new State<>(Section.MISC, "CancelTime");
		public final static State<Real> DECAY_DURATION_FLESH = new State<>(Section.MISC, "DecayTime");
		public final static State<Real> DECAY_DURATION_FOG_FLASH = new State<>(Section.MISC, "FogFlashTime");
		public final static State<Real> DECAY_DURATION_HERO = new State<>(Section.MISC, "DissipateTime");
		public final static State<Real> DECAY_DURATION_MISSILE = new State<>(Section.MISC, "BulletDeathTime");
		public final static State<Real> DECAY_DURATION_SFX = new State<>(Section.MISC, "EffectDeathTime");
		public final static State<Real> DECAY_DURATION_STRUCTURE = new State<>(Section.MISC, "StructureDecayTime");
		
		public final static State<Real> HERO_ATTR_ARMOR_BASE = new State<>(Section.MISC, "AgiDefenseBase");
		public final static State<Real> HERO_ATTR_AGI_ARMOR_UP = new State<>(Section.MISC, "AgiDefenseBonus");
		public final static State<Real> HERO_ATTR_AGI_ATTACK_SPEED_UP = new State<>(Section.MISC, "AgiAttackSpeedBonus");
		public final static State<Real> HERO_ATTR_AGI_MOVE_SPEED_UP = new State<>(Section.MISC, "AgiMoveBonus");
		public final static State<Real> HERO_ATTR_INT_MANA_UP = new State<>(Section.MISC, "IntManaBonus");
		public final static State<Real> HERO_ATTR_INT_MANA_REGEN_UP = new State<>(Section.MISC, "IntRegenBous");
		public final static State<Real> HERO_ATTR_STR_ATTACK_UP = new State<>(Section.MISC, "StrAttackBonus");
		public final static State<Real> HERO_ATTR_STR_LIFE_UP = new State<>(Section.MISC, "StrHitPointBonus");
		public final static State<Real> HERO_ATTR_STR_LIFE_REGEN_UP = new State<>(Section.MISC, "StrRegenBonus");
		public final static State<Real> HERO_AWAKEN_COSTS_GOLD_CONST_FACTOR = new State<>(Section.MISC, "AwakenBaseFactor");
		public final static State<Real> HERO_AWAKEN_COSTS_GOLD_LEVEL_FACTOR = new State<>(Section.MISC, "AwakenLevelFactor");
		public final static State<Real> HERO_AWAKEN_COSTS_GOLD_LUMBER_FACTOR_MAX = new State<>(Section.MISC, "AwakenMaxFactor");
		public final static State<Int> HERO_AWAKEN_COSTS_GOLD_MAX = new State<>(Section.MISC, "HeroMaxAwakenCostGold");
		public final static State<Real> HERO_AWAKEN_COSTS_LUMBER_CONST_FACTOR = new State<>(Section.MISC, "AwakenBaseLumberFactor");
		public final static State<Real> HERO_AWAKEN_COSTS_LUMBER_LEVEL_FACTOR = new State<>(Section.MISC, "AwakenLumberLevelFactor");
		public final static State<Int> HERO_AWAKEN_COSTS_LUMBER_MAX = new State<>(Section.MISC, "HeroMaxAwakenCostLumber");
		public final static State<Real> HERO_AWAKEN_LIFE_FACTOR = new State<>(Section.MISC, "HeroAwakenLifeFactor");
		public final static State<Real> HERO_AWAKEN_MANA_FACTOR = new State<>(Section.MISC, "HeroAwakenManaFactor");
		public final static State<Bool> HERO_AWAKEN_USE_INITIAL_MANA = new State<>(Section.MISC, "HeroAwakenManaStart");
		public final static State<Real> HERO_REVIVE_COSTS_GOLD_CONST_FACTOR = new State<>(Section.MISC, "ReviveBaseFactor");
		public final static State<Real> HERO_REVIVE_COSTS_GOLD_LEVEL_FACTOR = new State<>(Section.MISC, "ReviveLevelFactor");
		public final static State<Real> HERO_REVIVE_COSTS_GOLD_LUMBER_FACTOR_MAX = new State<>(Section.MISC, "ReviveMaxFactor");
		public final static State<Int> HERO_REVIVE_COSTS_GOLD_MAX = new State<>(Section.MISC, "HeroMaxReviveCostGold");
		public final static State<Real> HERO_REVIVE_COSTS_LUMBER_CONST_FACTOR = new State<>(Section.MISC, "ReviveBaseLumberFactor");
		public final static State<Real> HERO_REVIVE_COSTS_LUMBER_LEVEL_FACTOR = new State<>(Section.MISC, "ReviveLumberLevelFactor");
		public final static State<Int> HERO_REVIVE_COSTS_LUMBER_MAX = new State<>(Section.MISC, "HeroMaxReviveCostLumber");
		public final static State<Real> HERO_REVIVE_COSTS_TIME_FACTOR = new State<>(Section.MISC, "ReviveTimeFactor");
		public final static State<Real> HERO_REVIVE_COSTS_TIME_FACTOR_MAX = new State<>(Section.MISC, "ReviveMaxTimeFactor");
		public final static State<Real> HERO_REVIVE_COSTS_TIME_MAX = new State<>(Section.MISC, "HeroMaxReviveTime");
		public final static State<Real> HERO_REVIVE_LIFE_FACTOR = new State<>(Section.MISC, "HeroReviveLifeFactor");
		public final static State<Real> HERO_REVIVE_MANA_FACTOR = new State<>(Section.MISC, "HeroReviveManaFactor");
		public final static State<Bool> HERO_REVIVE_USE_INITIAL_MANA = new State<>(Section.MISC, "HeroReviveManaStart");
		
		public final static State<Bool> ILLUSIONS_ARE_AUTOCAST_TARGS = new State<>(Section.MISC, "IllusionsGetAutocast");
		public final static State<Bool> ILLUSIONS_BESTOW_AURAS = new State<>(Section.MISC, "IllusionsBestowAuras");
		public final static State<Bool> ILLUSIONS_CAN_RESTORE_LIFE = new State<>(Section.MISC, "IllusionsCanRestoreLife");
		public final static State<Bool> ILLUSIONS_CAN_RESTORE_MANA = new State<>(Section.MISC, "IllusionsCanRestoreMana");
		public final static State<Bool> ILLUSIONS_RECEIVE_ARMOR_BONUSES = new State<>(Section.MISC, "IllusionsGetDefenseBonus");
		public final static State<Bool> ILLUSIONS_RECEIVE_ATTACK_BONUSES = new State<>(Section.MISC, "IllusionsGetAttackBonus");
		public final static State<Bool> ILLUSIONS_RECEIVE_ATTACK_SPEED_BONUSES = new State<>(Section.MISC, "IllusionsGetAttackSpeedBonus");
		public final static State<Bool> ILLUSIONS_RECEIVE_MOVE_SPEED_BONUSES = new State<>(Section.MISC, "IllusionsGetMoveSpeedBonus");
		
		public final static State<Real> ITEM_DROP_RANGE = new State<>(Section.MISC, "DropItemRange");
		public final static State<Real> ITEM_GIVE_RANGE = new State<>(Section.MISC, "GiveItemRange");
		public final static State<Real> ITEM_PICK_RANGE = new State<>(Section.MISC, "PickupItemRange");
		public final static State<Real> ITEM_PAWN_RANGE = new State<>(Section.MISC, "PawnItemRange");
		public final static State<Real> ITEM_PAWN_RATE = new State<>(Section.MISC, "PawnItemRate");
		
		public final static State<Real> MOVE_FOLLOW_RANGE = new State<>(Section.MISC, "FollowRange");
		public final static State<Real> MOVE_FOLLOW_RANGE_ITEM = new State<>(Section.MISC, "FollowItemRange");
		public final static State<Real> MOVE_FOLLOW_RANGE_STRUCTURE = new State<>(Section.MISC, "StructureFollowRange");
		public final static State<Bool> MOVE_SPEED_BONUSES_STACK = new State<>(Section.MISC, "MoveSpeedBonusesStack");
		public final static State<Real> MOVE_SPEED_UNIT_MAX = new State<>(Section.MISC, "MaxUnitSpeed");
		public final static State<Real> MOVE_SPEED_UNIT_MIN = new State<>(Section.MISC, "MinUnitSpeed");
		public final static State<Real> MOVE_SPEED_STRUCTURE_MAX = new State<>(Section.MISC, "MaxBldgSpeed");
		public final static State<Real> MOVE_SPEED_STRUCTURE_MIN = new State<>(Section.MISC, "MinBldgSpeed");

		public final static State<Int> RESOURCES_GOLD_MINE_GOLD_LOW = new State<>(Section.MISC, "LowGoldAmount");
		public final static State<Int> RESOURCES_GOLD_MINE_GOLD_MAX = new State<>(Section.MISC, "GoldMineMaxGold");
		public final static State<Int> RESOURCES_GOLD_MINE_OWN_DURATION = new State<>(Section.MISC, "GoldMineOwnDuration");
		public final static State<Int> RESOURCES_SUPPLY_MAX = new State<>(Section.MISC, "FoodCeiling");
		public final static State<RealList> RESOURCES_SUPPLY_UPKEEP_COSTS_GOLD = new State<>(Section.MISC, "UpkeepGoldTax");
		public final static State<RealList> RESOURCES_SUPPLY_UPKEEP_COSTS_LUMBER = new State<>(Section.MISC, "UpkeepLumberTax");
		public final static State<IntList> RESOURCES_SUPPLY_UPKEEP_LEVELS = new State<>(Section.MISC, "UpkeepUsage");
		
		public final static State<Real> STRUCTURE_ANGLE = new State<>(Section.MISC, "BuildingAngle");
		public final static State<Real> STRUCTURE_ANGLE_ROOTED = new State<>(Section.MISC, "RootAngle");
		public final static State<Real> STRUCTURE_LIFE_DRAIN_RATE = new State<>(Section.MISC, "ConstructionLifeDrainRate");
		public final static State<Real> STRUCTURE_NEUTRAL_USE_NOTIFY_RAD = new State<>(Section.MISC, "NeutralUseNotifyRadius");
		public final static State<Real> STRUCTURE_PLACEMENT_NOTIFY_RAD = new State<>(Section.MISC, "BuildingPlacementNotifyRadius");
		public final static State<Real> STRUCTURE_UNBLIGHT_RAD = new State<>(Section.MISC, "BuildingUnblightRadius");

		public final static State<Bool> TECH_CONSTRUCTION_REFUND_DAMAGE_PENALTY = new State<>(Section.MISC, "ConstructionDamageRefundPenalty");
		public final static State<Real> TECH_CONSTRRUCTION_REFUND_RATE = new State<>(Section.MISC, "ConstructionRefundRate");
		public final static State<UnitList> TECH_DEPENDENCY_ALTAR = new State<>(Section.TALT, "DependencyOr");
		public final static State<UnitList> TECH_DEPENDENCY_HERO = new State<>(Section.HERO, "DependencyOr");
		public final static State<UnitList> TECH_DEPENDENCY_TOWNHALL1 = new State<>(Section.TWN1, "DependencyOr");
		public final static State<UnitList> TECH_DEPENDENCY_TOWNHALL2 = new State<>(Section.TWN2, "DependencyOr");
		public final static State<UnitList> TECH_DEPENDENCY_TOWNHALL3 = new State<>(Section.TWN3, "DependencyOr");
		public final static State<UnitList> TECH_DEPENDENCY_TOWNHALL4 = new State<>(Section.TWN4, "DependencyOr");
		public final static State<UnitList> TECH_DEPENDENCY_TOWNHALL5 = new State<>(Section.TWN5, "DependencyOr");
		public final static State<UnitList> TECH_DEPENDENCY_TOWNHALL6 = new State<>(Section.TWN6, "DependencyOr");
		public final static State<UnitList> TECH_DEPENDENCY_TOWNHALL7 = new State<>(Section.TWN7, "DependencyOr");
		public final static State<UnitList> TECH_DEPENDENCY_TOWNHALL8 = new State<>(Section.TWN8, "DependencyOr");
		public final static State<UnitList> TECH_DEPENDENCY_TOWNHALL9 = new State<>(Section.TWN9, "DependencyOr");
		public final static State<Real> TECH_RESEARCH_REFUND_RATE = new State<>(Section.MISC, "ResearchRefundRate");
		public final static State<Real> TECH_REVIVE_REFUND_RATE = new State<>(Section.MISC, "ReviveRefundRate");
		public final static State<Real> TECH_TRAIN_REFUND_RATE = new State<>(Section.MISC, "TrainRefundRate");
		public final static State<Bool> TECH_UPGRADE_COSTS_ARE_RELATIVE = new State<>(Section.MISC, "RelativeUpgradeCost");
		public final static State<Bool> TECH_UPGRADE_REFUND_DAMAGE_PENALTY = new State<>(Section.MISC, "UpgradeDamageRefundPenalty");
		public final static State<Real> TECH_UPGRADE_REFUND_RATE = new State<>(Section.MISC, "UpgradeRefundRate");
		
		public final static State<String> TEXT_ALTAR_NAME = new State<>(Section.TALT, "Name");
		public final static State<String> TEXT_HERO_NAME = new State<>(Section.HERO, "Name");
		public final static State<String> TEXT_TOWNHALL1_NAME = new State<>(Section.TWN1, "Name");
		public final static State<String> TEXT_TOWNHALL2_NAME = new State<>(Section.TWN2, "Name");
		public final static State<String> TEXT_TOWNHALL3_NAME = new State<>(Section.TWN3, "Name");
		public final static State<String> TEXT_TOWNHALL4_NAME = new State<>(Section.TWN4, "Name");
		public final static State<String> TEXT_TOWNHALL5_NAME = new State<>(Section.TWN5, "Name");
		public final static State<String> TEXT_TOWNHALL6_NAME = new State<>(Section.TWN6, "Name");
		public final static State<String> TEXT_TOWNHALL7_NAME = new State<>(Section.TWN7, "Name");
		public final static State<String> TEXT_TOWNHALL8_NAME = new State<>(Section.TWN8, "Name");
		public final static State<String> TEXT_TOWNHALL9_NAME = new State<>(Section.TWN9, "Name");
		
		public final static State<Real> TIME_DAWN = new State<>(Section.MISC, "Dawn");
		public final static State<Real> TIME_DAY_DURATION = new State<>(Section.MISC, "DayLength");
		public final static State<Real> TIME_DAY_HOURS = new State<>(Section.MISC, "DayHours");
		public final static State<Real> TIME_DUSK = new State<>(Section.MISC, "Dusk");
		
		public final static State<Int> TRADING_INC_SMALL = new State<>(Section.MISC, "TradingIncSmall");
		public final static State<Int> TRADING_INC_LARGE = new State<>(Section.MISC, "TradingIncLarge");

		public final static State<Real> COLLISION_SIZE_MAX = new State<>(Section.MISC, "MaxCollisionRadius");
		public final static State<Int> CREEP_CAMP_RADIUS_CELLS = new State<>(Section.MISC, "CreepCampPathingCellDistance");
		public final static State<Real> FROST_ATTACK_SPEED_DEC = new State<>(Section.MISC, "FrostAttackSpeedDecrease");
		public final static State<Real> FROST_MOVE_SPEED_DEC = new State<>(Section.MISC, "FrostMoveSpeedDecrease");
		public final static State<Real> INVIS_SPEED = new State<>(Section.MISC, "InvisSpeed");
		public final static State<Bool> MORPH_LAND_CLOSEST = new State<>(Section.MISC, "MorphLandClosest");
		public final static State<Bool> MORPH_DISABLE_ALTERNATE_ABILS = new State<>(Section.MISC, "MorphAlternateDisable");
		public final static State<Real> RALLY_Z_OFFSET = new State<>(Section.MISC, "RallyZOffset");
		public final static State<Real> REACTION_DELAY = new State<>(Section.MISC, "ReactionDelay");
		public final static State<Real> SCALED_ANIM_TIME = new State<>(Section.MISC, "ScaledAnimTime");
		public final static State<Real> SPELL_CAST_RANGE_BUF = new State<>(Section.MISC, "SpellCastRangeBuffer");
	}
	
	@Override
	public void read(File file) throws IOException {
		super.read(file);
	}
	
	@Override
	public void write(File file) throws IOException {
		super.write(file);
	}
	
	public MiscTXT() {
		
	}
}
