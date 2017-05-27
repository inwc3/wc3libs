package net.moonlightflower.wc3libs.txt.app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.moonlightflower.wc3libs.bin.app.objMod.W3T.States.State;
import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.AttackTable;
import net.moonlightflower.wc3libs.dataTypes.app.Bool;
import net.moonlightflower.wc3libs.dataTypes.app.DefenseTable;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.Real;
import net.moonlightflower.wc3libs.dataTypes.app.UnitId;
import net.moonlightflower.wc3libs.dataTypes.app.UnitList;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.txt.TXT;
import net.moonlightflower.wc3libs.txt.TXTSectionId;
import net.moonlightflower.wc3libs.txt.TXTState;

public class MiscTXT extends TXT {
	public final static File GAME_PATH = new File("war3mapMisc.txt");
	
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
		
		private static class State<T extends DataType> extends TXTState<T> {
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

		public final static State<Bool> ABIL_DEPENDENCY_CHECK = new State<>(Section.MISC, "DepCheckAlias", Bool.class);
		
		public final static State<Bool> ABIL_AMULET_OF_RECALL_CLUSTER = new State<>(Section.MISC, "AmuletOfRecallCluster", Bool.class);
		public final static State<Bool> ABIL_AVATAR_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateAvatar", Bool.class);
		public final static State<Bool> ABIL_AVENGER_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateAvengerForm", Bool.class);
		public final static State<Bool> ABIL_BARKSKIN_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateBarkskin", Bool.class);
		public final static State<Bool> ABIL_BEAR_FORM_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateBearForm", Bool.class);
		public final static State<Bool> ABIL_BLADESTORM_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateBladestorm", Bool.class);
		public final static State<Bool> ABIL_BURROW_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateBurrow", Bool.class);
		public final static State<Bool> ABIL_CALL_TO_ARMS_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateCallToArms", Bool.class);
		public final static State<Bool> ABIL_CHEMICAL_RAGE_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateChemicalRage", Bool.class);
		public final static State<Bool> ABIL_CORPOREAL_FORM_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateCorporealForm", Bool.class);
		public final static State<Bool> ABIL_CYCLONE_HIDES_TARGET = new State<>(Section.MISC, "CycloneStasis", Bool.class);
		public final static State<Bool> ABIL_DARK_SUMMONING_CLUSTER = new State<>(Section.MISC, "DarkSummoningCluster", Bool.class);
		public final static State<Bool> ABIL_DEFEND_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateDefend", Bool.class);
		public final static State<Bool> ABIL_DEFEND_CAN_DEFLECT = new State<>(Section.MISC, "DefendDeflection", Bool.class);
		public final static State<Bool> ABIL_DRAIN_GIVES_BONUS_LIFE = new State<>(Section.MISC, "DrainGivesBonusLife", Bool.class);
		public final static State<Bool> ABIL_DRAIN_GIVES_BONUS_MANA = new State<>(Section.MISC, "DrainGivesBonusMana", Bool.class);
		public final static State<Bool> ABIL_DRAIN_USES_ETHEREAL_BONUS = new State<>(Section.MISC, "DrainUsesEtherealBonus", Bool.class);
		public final static State<Bool> ABIL_DRAIN_TRANFERS_LIFE = new State<>(Section.MISC, "DrainTransfersLife", Bool.class);
		public final static State<Bool> ABIL_DRAIN_TRANFERS_MANA = new State<>(Section.MISC, "DrainTransfersMana", Bool.class);
		public final static State<Bool> ABIL_DIVINE_SHIELD_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateDivineShield", Bool.class);
		public final static State<Bool> ABIL_ENSNARE_IS_MAGIC = new State<>(Section.MISC, "EnsnareIsMagic", Bool.class);
		public final static State<Int> ABIL_HERO_LEVEL_SKIP = new State<>(Section.MISC, "HeroAbilityLevelSkip", Int.class);
		public final static State<Bool> ABIL_IMMOLATION_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateImmolation", Bool.class);
		public final static State<Bool> ABIL_MANA_FLARE_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateManaFlare", Bool.class);
		public final static State<Bool> ABIL_MANA_SHIELD_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateManaShield", Bool.class);
		public final static State<Bool> ABIL_MASS_TELEPORT_CLUSTER = new State<>(Section.MISC, "MassTeleportCluster", Bool.class);
		public final static State<Bool> ABIL_METAMORPHOSIS_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateMetamorphosis", Bool.class);
		public final static State<Bool> ABIL_RAVEN_FORM_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateRavenForm", Bool.class);
		public final static State<Bool> ABIL_ROBO_GOBLIN_FORM_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateRoboGoblin", Bool.class);
		public final static State<Bool> ABIL_STONE_FORM_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateStoneForm", Bool.class);
		public final static State<Bool> ABIL_SUBMERGE_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateSubmerge", Bool.class);
		public final static State<Bool> ABIL_TOWN_PORTAL_CLUSTER = new State<>(Section.MISC, "TownPortalCluster", Bool.class);
		public final static State<Bool> ABIL_WEB_IS_MAGIC = new State<>(Section.MISC, "WebIsMagic", Bool.class);
		public final static State<Bool> ABIL_WIND_WALK_CAN_DEACTIVATE = new State<>(Section.MISC, "CanDeactivateWindWalk", Bool.class);
		
		public final static State<Bool> EXP_GLOBAL = new State<>(Section.MISC, "GlobalExperience", Bool.class);
		public final static State<Bool> EXP_STRUCTURE_KILLS_GIVE = new State<>(Section.MISC, "BuildingKillsGiveExp", Bool.class);
		public final static State<Int> EXP_HERO_MAX_LEVEL = new State<>(Section.MISC, "MaxHeroLevel", Int.class);
		public final static State<Bool> EXP_HERO_MAX_LEVEL_DRAIN = new State<>(Section.MISC, "MaxLevelHeroesDrainExp", Bool.class);
		public final static State<DataList<Int>> EXP_HERO_NEEDED = new State<>(Section.MISC, "NeedHeroXP", new DataTypeInfo(DataList.class, Int.class));
		public final static State<Real> EXP_HERO_NEEDED_PREV_VAL_FACTOR = new State<>(Section.MISC, "NeedHeroXPFormulaA", Real.class);
		public final static State<Real> EXP_HERO_NEEDED_LEVEL_FACTOR = new State<>(Section.MISC, "NeedHeroXPFormulaB", Real.class);
		public final static State<Real> EXP_HERO_NEEDED_CONST_FACTOR = new State<>(Section.MISC, "NeedHeroXPFormulaC", Real.class);
		public final static State<Real> EXP_HERO_RANGE = new State<>(Section.MISC, "HeroExpRange", Real.class);
		public final static State<Int> EXP_UNIT_MAX_LEVEL = new State<>(Section.MISC, "MaxUnitLevel", Int.class);

		public final static State<DataList<Int>> EXP_GRANTED_HERO = new State<>(Section.MISC, "GrantHeroXP", new DataTypeInfo(DataList.class, Int.class));
		public final static State<Real> EXP_GRANTED_HERO_PREV_VAL_FACTOR = new State<>(Section.MISC, "GrantHeroXPFormulaA", Real.class);
		public final static State<Real> EXP_GRANTED_HERO_CONST_FACTOR = new State<>(Section.MISC, "GrantHeroXPFormulaC", Real.class);
		public final static State<Real> EXP_GRANTED_HERO_LEVEL_FACTOR = new State<>(Section.MISC, "GrantHeroXPFormulaB", Real.class);
		public final static State<DataList<Int>> EXP_GRANTED_NORMAL = new State<>(Section.MISC, "GrantNormalXP", new DataTypeInfo(DataList.class, Int.class));
		public final static State<Real> EXP_GRANTED_NORMAL_PREV_VAL_FACTOR = new State<>(Section.MISC, "GrantNormalXPFormulaA", Real.class);
		public final static State<Real> EXP_GRANTED_NORMAL_CONST_FACTOR = new State<>(Section.MISC, "GrantNormalXPFormulaC", Real.class);
		public final static State<Real> EXP_GRANTED_NORMAL_LEVEL_FACTOR = new State<>(Section.MISC, "GrantNormalXPFormulaB", Real.class);
		public final static State<Real> EXP_GRANTED_SUMMON_FACTOR = new State<>(Section.MISC, "SummonedKillFactor", Real.class);
		
		public final static State<DataList<Int>> EXP_GRANTED_CREEP_REDUCTION = new State<>(Section.MISC, "HeroFactorXP", new DataTypeInfo(DataList.class, Int.class));
		public final static State<DataList<Real>> EXP_GRANTED_TOWNHALL1_FACTOR = new State<>(Section.TWN1, "XPFactor", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> EXP_GRANTED_TOWNHALL2_FACTOR = new State<>(Section.TWN2, "XPFactor", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> EXP_GRANTED_TOWNHALL3_FACTOR = new State<>(Section.TWN3, "XPFactor", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> EXP_GRANTED_TOWNHALL4_FACTOR = new State<>(Section.TWN4, "XPFactor", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> EXP_GRANTED_TOWNHALL5_FACTOR = new State<>(Section.TWN5, "XPFactor", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> EXP_GRANTED_TOWNHALL6_FACTOR = new State<>(Section.TWN6, "XPFactor", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> EXP_GRANTED_TOWNHALL7_FACTOR = new State<>(Section.TWN7, "XPFactor", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> EXP_GRANTED_TOWNHALL8_FACTOR = new State<>(Section.TWN8, "XPFactor", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> EXP_GRANTED_TOWNHALL9_FACTOR = new State<>(Section.TWN9, "XPFactor", new DataTypeInfo(DataList.class, Real.class));
		
		public final static State<Bool> UI_DISPLAY_STRUCTURE_STATUS = new State<>(Section.MISC, "DisplayBuildingStatus", Bool.class);
		public final static State<Bool> UI_DISPLAY_ENEMY_INV = new State<>(Section.MISC, "DisplayEnemyInventory", Bool.class);

		public final static State<Real> COMBAT_AGGRO_RANGE_SALE_ABIL = new State<>(Section.MISC, "AbilSaleAggroRange", Real.class);
		public final static State<Real> COMBAT_AGGRO_RANGE_SALE_ITEM = new State<>(Section.MISC, "ItemSaleAggroRange", Real.class);
		public final static State<Real> COMBAT_AGGRO_RANGE_SALE_UNIT = new State<>(Section.MISC, "UnitSaleAggroRange", Real.class);
		public final static State<Real> COMBAT_ARMOR_DAMAGE_REDUCTION_FACTOR = new State<>(Section.MISC, "DefenseArmor", Real.class);
		public final static State<Bool> COMBAT_ATTACK_BOUNCE_ALLOW_MULTI = new State<>(Section.MISC, "AllowMultiBounce", Bool.class);
		public final static State<Real> COMBAT_ATTACK_NOTIFY_DELAY = new State<>(Section.MISC, "AttackNotifyDelay", Real.class);
		public final static State<Real> COMBAT_ATTACK_NOTIFY_RANGE = new State<>(Section.MISC, "AttackNotifyRange", Real.class);
		public final static State<Bool> COMBAT_AURA_BY_BURROWED = new State<>(Section.MISC, "BurrowedUnitsBestowAuras", Bool.class);
		public final static State<Bool> COMBAT_AURA_BY_FLYINGS_HEROES = new State<>(Section.MISC, "FlyingHeroesBestowAuras", Bool.class);
		public final static State<Bool> COMBAT_AURA_BY_INVIS = new State<>(Section.MISC, "InvisibleUnitsBestowAuras", Bool.class);
		public final static State<Bool> COMBAT_AURA_BY_POLYMORPHED = new State<>(Section.MISC, "PolymorphedUnitsBestowAuras", Bool.class);
		public final static State<Bool> COMBAT_AURA_BY_REANIMATED = new State<>(Section.MISC, "AnimatedUnitsBestowAuras", Bool.class);
		public final static State<Real> COMBAT_CALL_FOR_HELP_RANGE = new State<>(Section.MISC, "CallForHelp", Real.class);
		public final static State<Real> COMBAT_CALL_FOR_HELP_RANGE_CREEP = new State<>(Section.MISC, "CreepCallForHelp", Real.class);
		public final static State<DefenseTable> COMBAT_DAMAGE_TABLE_CHAOS = new State<>(Section.MISC, "DamageBonusChaos", DefenseTable.class);
		public final static State<DefenseTable> COMBAT_DAMAGE_TABLE_HERO = new State<>(Section.MISC, "DamageBonusHero", DefenseTable.class);
		public final static State<DefenseTable> COMBAT_DAMAGE_TABLE_MAGIC = new State<>(Section.MISC, "DamageBonusMagic", DefenseTable.class);
		public final static State<DefenseTable> COMBAT_DAMAGE_TABLE_NORMAL = new State<>(Section.MISC, "DamageBonusNormal", DefenseTable.class);
		public final static State<DefenseTable> COMBAT_DAMAGE_TABLE_PIERCE = new State<>(Section.MISC, "DamageBonusPierce", DefenseTable.class);
		public final static State<DefenseTable> COMBAT_DAMAGE_TABLE_SIEGE = new State<>(Section.MISC, "DamageBonusSiege", DefenseTable.class);
		public final static State<DefenseTable> COMBAT_DAMAGE_TABLE_SPELL = new State<>(Section.MISC, "DamageBonusSpells", DefenseTable.class);
		public final static State<Bool> COMBAT_ETHEREAL_DAMAGE_BONUS_ON_ALLY = new State<>(Section.MISC, "EtherealDamageBonusAlly", Bool.class);
		public final static State<AttackTable> COMBAT_ETHEREAL_DMG_FACTOR = new State<>(Section.MISC, "EtherealDamageBonus", AttackTable.class);
		public final static State<Real> COMBAT_ETHEREAL_HEAL_FACTOR = new State<>(Section.MISC, "EtherealHealBonus", Real.class);
		public final static State<Real> COMBAT_FOG_ATTACK_REVEAL_RAD = new State<>(Section.MISC, "FoggedAttackRevealRadius", Real.class);
		public final static State<Real> COMBAT_FOG_DEATH_REVEAL_RAD = new State<>(Section.MISC, "DyingRevealRadius", Real.class);
		public final static State<Real> COMBAT_GUARD_RANGE = new State<>(Section.MISC, "GuardDistance", Real.class);
		public final static State<Real> COMBAT_GUARD_RETURN_RANGE_MAX = new State<>(Section.MISC, "MaxGuardDistance", Real.class);
		public final static State<Real> COMBAT_GUARD_RETURN_TIME_MAX = new State<>(Section.MISC, "GuardReturnTime", Real.class);
		public final static State<Bool> COMBAT_INVU_SUMMONS_TAKE_DISPEL_DAMAGE = new State<>(Section.MISC, "InvulnSummonDispelDamage", Bool.class);
		public final static State<Bool> COMBAT_MAGIC_IMMUNE_RESIST_DAMAGE = new State<>(Section.MISC, "MagicImmunesResistDamage", Bool.class);
		public final static State<Bool> COMBAT_MAGIC_IMMUNE_RESIST_LEECH = new State<>(Section.MISC, "MagicImmunesResistLeech", Bool.class);
		public final static State<Bool> COMBAT_MAGIC_IMMUNE_RESIST_THORNS = new State<>(Section.MISC, "MagicImmunesResistThorns", Bool.class);
		public final static State<Bool> COMBAT_MAGIC_IMMUNE_RESIST_ULTIMATES = new State<>(Section.MISC, "MagicImmunesResistUltimates", Bool.class);
		public final static State<Real> COMBAT_MISS_CHANCE = new State<>(Section.MISC, "ChanceToMiss", Real.class);
		public final static State<Real> COMBAT_MISS_DAMAGE_REDUCTION = new State<>(Section.MISC, "MissDamageReduction", Real.class);
		
		public final static State<Real> DECAY_DURATION_BONE = new State<>(Section.MISC, "BoneDecayTime", Real.class);
		public final static State<Real> DECAY_DURATION_CANCEL = new State<>(Section.MISC, "CancelTime", Real.class);
		public final static State<Real> DECAY_DURATION_FLESH = new State<>(Section.MISC, "DecayTime", Real.class);
		public final static State<Real> DECAY_DURATION_FOG_FLASH = new State<>(Section.MISC, "FogFlashTime", Real.class);
		public final static State<Real> DECAY_DURATION_HERO = new State<>(Section.MISC, "DissipateTime", Real.class);
		public final static State<Real> DECAY_DURATION_MISSILE = new State<>(Section.MISC, "BulletDeathTime", Real.class);
		public final static State<Real> DECAY_DURATION_SFX = new State<>(Section.MISC, "EffectDeathTime", Real.class);
		public final static State<Real> DECAY_DURATION_STRUCTURE = new State<>(Section.MISC, "StructureDecayTime", Real.class);
		
		public final static State<Real> HERO_ATTR_ARMOR_BASE = new State<>(Section.MISC, "AgiDefenseBase", Real.class);
		public final static State<Real> HERO_ATTR_AGI_ARMOR_UP = new State<>(Section.MISC, "AgiDefenseBonus", Real.class);
		public final static State<Real> HERO_ATTR_AGI_ATTACK_SPEED_UP = new State<>(Section.MISC, "AgiAttackSpeedBonus", Real.class);
		public final static State<Real> HERO_ATTR_AGI_MOVE_SPEED_UP = new State<>(Section.MISC, "AgiMoveBonus", Real.class);
		public final static State<Real> HERO_ATTR_INT_MANA_UP = new State<>(Section.MISC, "IntManaBonus", Real.class);
		public final static State<Real> HERO_ATTR_INT_MANA_REGEN_UP = new State<>(Section.MISC, "IntRegenBous", Real.class);
		public final static State<Real> HERO_ATTR_STR_ATTACK_UP = new State<>(Section.MISC, "StrAttackBonus", Real.class);
		public final static State<Real> HERO_ATTR_STR_LIFE_UP = new State<>(Section.MISC, "StrHitPointBonus", Real.class);
		public final static State<Real> HERO_ATTR_STR_LIFE_REGEN_UP = new State<>(Section.MISC, "StrRegenBonus", Real.class);
		public final static State<Real> HERO_AWAKEN_COSTS_GOLD_CONST_FACTOR = new State<>(Section.MISC, "AwakenBaseFactor", Real.class);
		public final static State<Real> HERO_AWAKEN_COSTS_GOLD_LEVEL_FACTOR = new State<>(Section.MISC, "AwakenLevelFactor", Real.class);
		public final static State<Real> HERO_AWAKEN_COSTS_GOLD_LUMBER_FACTOR_MAX = new State<>(Section.MISC, "AwakenMaxFactor", Real.class);
		public final static State<Int> HERO_AWAKEN_COSTS_GOLD_MAX = new State<>(Section.MISC, "HeroMaxAwakenCostGold", Int.class);
		public final static State<Real> HERO_AWAKEN_COSTS_LUMBER_CONST_FACTOR = new State<>(Section.MISC, "AwakenBaseLumberFactor", Real.class);
		public final static State<Real> HERO_AWAKEN_COSTS_LUMBER_LEVEL_FACTOR = new State<>(Section.MISC, "AwakenLumberLevelFactor", Real.class);
		public final static State<Int> HERO_AWAKEN_COSTS_LUMBER_MAX = new State<>(Section.MISC, "HeroMaxAwakenCostLumber", Int.class);
		public final static State<Real> HERO_AWAKEN_LIFE_FACTOR = new State<>(Section.MISC, "HeroAwakenLifeFactor", Real.class);
		public final static State<Real> HERO_AWAKEN_MANA_FACTOR = new State<>(Section.MISC, "HeroAwakenManaFactor", Real.class);
		public final static State<Bool> HERO_AWAKEN_USE_INITIAL_MANA = new State<>(Section.MISC, "HeroAwakenManaStart", Bool.class);
		public final static State<Real> HERO_REVIVE_COSTS_GOLD_CONST_FACTOR = new State<>(Section.MISC, "ReviveBaseFactor", Real.class);
		public final static State<Real> HERO_REVIVE_COSTS_GOLD_LEVEL_FACTOR = new State<>(Section.MISC, "ReviveLevelFactor", Real.class);
		public final static State<Real> HERO_REVIVE_COSTS_GOLD_LUMBER_FACTOR_MAX = new State<>(Section.MISC, "ReviveMaxFactor", Real.class);
		public final static State<Int> HERO_REVIVE_COSTS_GOLD_MAX = new State<>(Section.MISC, "HeroMaxReviveCostGold", Int.class);
		public final static State<Real> HERO_REVIVE_COSTS_LUMBER_CONST_FACTOR = new State<>(Section.MISC, "ReviveBaseLumberFactor", Real.class);
		public final static State<Real> HERO_REVIVE_COSTS_LUMBER_LEVEL_FACTOR = new State<>(Section.MISC, "ReviveLumberLevelFactor", Real.class);
		public final static State<Int> HERO_REVIVE_COSTS_LUMBER_MAX = new State<>(Section.MISC, "HeroMaxReviveCostLumber", Int.class);
		public final static State<Real> HERO_REVIVE_COSTS_TIME_FACTOR = new State<>(Section.MISC, "ReviveTimeFactor", Real.class);
		public final static State<Real> HERO_REVIVE_COSTS_TIME_FACTOR_MAX = new State<>(Section.MISC, "ReviveMaxTimeFactor", Real.class);
		public final static State<Real> HERO_REVIVE_COSTS_TIME_MAX = new State<>(Section.MISC, "HeroMaxReviveTime", Real.class);
		public final static State<Real> HERO_REVIVE_LIFE_FACTOR = new State<>(Section.MISC, "HeroReviveLifeFactor", Real.class);
		public final static State<Real> HERO_REVIVE_MANA_FACTOR = new State<>(Section.MISC, "HeroReviveManaFactor", Real.class);
		public final static State<Bool> HERO_REVIVE_USE_INITIAL_MANA = new State<>(Section.MISC, "HeroReviveManaStart", Bool.class);
		
		public final static State<Bool> ILLUSIONS_ARE_AUTOCAST_TARGS = new State<>(Section.MISC, "IllusionsGetAutocast", Bool.class);
		public final static State<Bool> ILLUSIONS_BESTOW_AURAS = new State<>(Section.MISC, "IllusionsBestowAuras", Bool.class);
		public final static State<Bool> ILLUSIONS_CAN_RESTORE_LIFE = new State<>(Section.MISC, "IllusionsCanRestoreLife", Bool.class);
		public final static State<Bool> ILLUSIONS_CAN_RESTORE_MANA = new State<>(Section.MISC, "IllusionsCanRestoreMana", Bool.class);
		public final static State<Bool> ILLUSIONS_RECEIVE_ARMOR_BONUSES = new State<>(Section.MISC, "IllusionsGetDefenseBonus", Bool.class);
		public final static State<Bool> ILLUSIONS_RECEIVE_ATTACK_BONUSES = new State<>(Section.MISC, "IllusionsGetAttackBonus", Bool.class);
		public final static State<Bool> ILLUSIONS_RECEIVE_ATTACK_SPEED_BONUSES = new State<>(Section.MISC, "IllusionsGetAttackSpeedBonus", Bool.class);
		public final static State<Bool> ILLUSIONS_RECEIVE_MOVE_SPEED_BONUSES = new State<>(Section.MISC, "IllusionsGetMoveSpeedBonus", Bool.class);
		
		public final static State<Real> ITEM_DROP_RANGE = new State<>(Section.MISC, "DropItemRange", Real.class);
		public final static State<Real> ITEM_GIVE_RANGE = new State<>(Section.MISC, "GiveItemRange", Real.class);
		public final static State<Real> ITEM_PICK_RANGE = new State<>(Section.MISC, "PickupItemRange", Real.class);
		public final static State<Real> ITEM_PAWN_RANGE = new State<>(Section.MISC, "PawnItemRange", Real.class);
		public final static State<Real> ITEM_PAWN_RATE = new State<>(Section.MISC, "PawnItemRate", Real.class);
		
		public final static State<Real> MOVE_FOLLOW_RANGE = new State<>(Section.MISC, "FollowRange", Real.class);
		public final static State<Real> MOVE_FOLLOW_RANGE_ITEM = new State<>(Section.MISC, "FollowItemRange", Real.class);
		public final static State<Real> MOVE_FOLLOW_RANGE_STRUCTURE = new State<>(Section.MISC, "StructureFollowRange", Real.class);
		public final static State<Bool> MOVE_SPEED_BONUSES_STACK = new State<>(Section.MISC, "MoveSpeedBonusesStack", Bool.class);
		public final static State<Real> MOVE_SPEED_UNIT_MAX = new State<>(Section.MISC, "MaxUnitSpeed", Real.class);
		public final static State<Real> MOVE_SPEED_UNIT_MIN = new State<>(Section.MISC, "MinUnitSpeed", Real.class);
		public final static State<Real> MOVE_SPEED_STRUCTURE_MAX = new State<>(Section.MISC, "MaxBldgSpeed", Real.class);
		public final static State<Real> MOVE_SPEED_STRUCTURE_MIN = new State<>(Section.MISC, "MinBldgSpeed", Real.class);

		public final static State<Int> RESOURCES_GOLD_MINE_GOLD_LOW = new State<>(Section.MISC, "LowGoldAmount", Int.class);
		public final static State<Int> RESOURCES_GOLD_MINE_GOLD_MAX = new State<>(Section.MISC, "GoldMineMaxGold", Int.class);
		public final static State<Int> RESOURCES_GOLD_MINE_OWN_DURATION = new State<>(Section.MISC, "GoldMineOwnDuration", Int.class);
		public final static State<Int> RESOURCES_SUPPLY_MAX = new State<>(Section.MISC, "FoodCeiling", Int.class);
		public final static State<DataList<Real>> RESOURCES_SUPPLY_UPKEEP_COSTS_GOLD = new State<>(Section.MISC, "UpkeepGoldTax", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Real>> RESOURCES_SUPPLY_UPKEEP_COSTS_LUMBER = new State<>(Section.MISC, "UpkeepLumberTax", new DataTypeInfo(DataList.class, Real.class));
		public final static State<DataList<Int>> RESOURCES_SUPPLY_UPKEEP_LEVELS = new State<>(Section.MISC, "UpkeepUsage", new DataTypeInfo(DataList.class, Int.class));
		
		public final static State<Real> STRUCTURE_ANGLE = new State<>(Section.MISC, "BuildingAngle", Real.class);
		public final static State<Real> STRUCTURE_ANGLE_ROOTED = new State<>(Section.MISC, "RootAngle", Real.class);
		public final static State<Real> STRUCTURE_LIFE_DRAIN_RATE = new State<>(Section.MISC, "ConstructionLifeDrainRate", Real.class);
		public final static State<Real> STRUCTURE_NEUTRAL_USE_NOTIFY_RAD = new State<>(Section.MISC, "NeutralUseNotifyRadius", Real.class);
		public final static State<Real> STRUCTURE_PLACEMENT_NOTIFY_RAD = new State<>(Section.MISC, "BuildingPlacementNotifyRadius", Real.class);
		public final static State<Real> STRUCTURE_UNBLIGHT_RAD = new State<>(Section.MISC, "BuildingUnblightRadius", Real.class);

		public final static State<Bool> TECH_CONSTRUCTION_REFUND_DAMAGE_PENALTY = new State<>(Section.MISC, "ConstructionDamageRefundPenalty", Bool.class);
		public final static State<Real> TECH_CONSTRRUCTION_REFUND_RATE = new State<>(Section.MISC, "ConstructionRefundRate", Real.class);
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
		public final static State<Real> TECH_RESEARCH_REFUND_RATE = new State<>(Section.MISC, "ResearchRefundRate", Real.class);
		public final static State<Real> TECH_REVIVE_REFUND_RATE = new State<>(Section.MISC, "ReviveRefundRate", Real.class);
		public final static State<Real> TECH_TRAIN_REFUND_RATE = new State<>(Section.MISC, "TrainRefundRate", Real.class);
		public final static State<Bool> TECH_UPGRADE_COSTS_ARE_RELATIVE = new State<>(Section.MISC, "RelativeUpgradeCost", Bool.class);
		public final static State<Bool> TECH_UPGRADE_REFUND_DAMAGE_PENALTY = new State<>(Section.MISC, "UpgradeDamageRefundPenalty", Bool.class);
		public final static State<Real> TECH_UPGRADE_REFUND_RATE = new State<>(Section.MISC, "UpgradeRefundRate", Real.class);
		
		public final static State<Wc3String> TEXT_ALTAR_NAME = new State<>(Section.TALT, "Name", Wc3String.class);
		public final static State<Wc3String> TEXT_HERO_NAME = new State<>(Section.HERO, "Name", Wc3String.class);
		public final static State<Wc3String> TEXT_TOWNHALL1_NAME = new State<>(Section.TWN1, "Name", Wc3String.class);
		public final static State<Wc3String> TEXT_TOWNHALL2_NAME = new State<>(Section.TWN2, "Name", Wc3String.class);
		public final static State<Wc3String> TEXT_TOWNHALL3_NAME = new State<>(Section.TWN3, "Name", Wc3String.class);
		public final static State<Wc3String> TEXT_TOWNHALL4_NAME = new State<>(Section.TWN4, "Name", Wc3String.class);
		public final static State<Wc3String> TEXT_TOWNHALL5_NAME = new State<>(Section.TWN5, "Name", Wc3String.class);
		public final static State<Wc3String> TEXT_TOWNHALL6_NAME = new State<>(Section.TWN6, "Name", Wc3String.class);
		public final static State<Wc3String> TEXT_TOWNHALL7_NAME = new State<>(Section.TWN7, "Name", Wc3String.class);
		public final static State<Wc3String> TEXT_TOWNHALL8_NAME = new State<>(Section.TWN8, "Name", Wc3String.class);
		public final static State<Wc3String> TEXT_TOWNHALL9_NAME = new State<>(Section.TWN9, "Name", Wc3String.class);
		
		public final static State<Real> TIME_DAWN = new State<>(Section.MISC, "Dawn", Real.class);
		public final static State<Real> TIME_DAY_DURATION = new State<>(Section.MISC, "DayLength", Real.class);
		public final static State<Real> TIME_DAY_HOURS = new State<>(Section.MISC, "DayHours", Real.class);
		public final static State<Real> TIME_DUSK = new State<>(Section.MISC, "Dusk", Real.class);
		
		public final static State<Int> TRADING_INC_SMALL = new State<>(Section.MISC, "TradingIncSmall", Int.class);
		public final static State<Int> TRADING_INC_LARGE = new State<>(Section.MISC, "TradingIncLarge", Int.class);

		public final static State<Real> COLLISION_SIZE_MAX = new State<>(Section.MISC, "MaxCollisionRadius", Real.class);
		public final static State<Int> CREEP_CAMP_RADIUS_CELLS = new State<>(Section.MISC, "CreepCampPathingCellDistance", Int.class);
		public final static State<Real> FROST_ATTACK_SPEED_DEC = new State<>(Section.MISC, "FrostAttackSpeedDecrease", Real.class);
		public final static State<Real> FROST_MOVE_SPEED_DEC = new State<>(Section.MISC, "FrostMoveSpeedDecrease", Real.class);
		public final static State<Real> INVIS_SPEED = new State<>(Section.MISC, "InvisSpeed", Real.class);
		public final static State<Bool> MORPH_LAND_CLOSEST = new State<>(Section.MISC, "MorphLandClosest", Bool.class);
		public final static State<Bool> MORPH_DISABLE_ALTERNATE_ABILS = new State<>(Section.MISC, "MorphAlternateDisable", Bool.class);
		public final static State<Real> RALLY_Z_OFFSET = new State<>(Section.MISC, "RallyZOffset", Real.class);
		public final static State<Real> REACTION_DELAY = new State<>(Section.MISC, "ReactionDelay", Real.class);
		public final static State<Real> SCALED_ANIM_TIME = new State<>(Section.MISC, "ScaledAnimTime", Real.class);
		public final static State<Real> SPELL_CAST_RANGE_BUF = new State<>(Section.MISC, "SpellCastRangeBuffer", Real.class);
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