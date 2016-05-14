package txt.app;

import java.io.File;
import java.io.IOException;

import dataTypes.app.Icon;
import dataTypes.app.Model;
import dataTypes.app.MusicFile;
import dataTypes.app.SoundLabel;
import txt.TXT;
import txt.TXTSectionId;
import txt.TXTState;

public class SkinTXT extends TXT {
	public final static String GAME_PATH = "war3mapSkin.txt";
	
	public static class States {
		private static class Section {
			public final static Section CUSTOM_SKIN = new Section("CustomSkin");
			
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

		public final static State<Icon> DEFAULT_BTN = new State<>(Section.CUSTOM_SKIN, "DefaultButton");
		public final static State<Icon> IDLE_PEON = new State<>(Section.CUSTOM_SKIN, "IdlePeon");
		public final static State<Icon> ICON_ARMOR_DIVINE = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorDivineNeutral");
		public final static State<Icon> ICON_ARMOR_DIVINE_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorDivine");
		public final static State<Icon> ICON_ARMOR_FORT = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorFortNeutral");
		public final static State<Icon> ICON_ARMOR_FORT_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorFort");
		public final static State<Icon> ICON_ARMOR_HERO = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorHeroNeutral");
		public final static State<Icon> ICON_ARMOR_HERO_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorHero");
		public final static State<Icon> ICON_ARMOR_LARGE = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorLargeNeutral");
		public final static State<Icon> ICON_ARMOR_LARGE_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorLarge");
		public final static State<Icon> ICON_ARMOR_MEDIUM = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorMediumNeutral");
		public final static State<Icon> ICON_ARMOR_MEDIUM_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorMedium");
		public final static State<Icon> ICON_ARMOR_NONE = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorNoneNeutral");
		public final static State<Icon> ICON_ARMOR_NONE_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorNone");
		public final static State<Icon> ICON_ARMOR_NORMAL = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorNormalNeutral");
		public final static State<Icon> ICON_ARMOR_NORMAL_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorNormal");
		public final static State<Icon> ICON_ARMOR_SMALL = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorSmallNeutral");
		public final static State<Icon> ICON_ARMOR_SMALL_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorSmall");
		public final static State<Icon> ICON_ARMOR_UNKNOWN = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorUnknownNeutral");
		public final static State<Icon> ICON_ARMOR_UNKNOWN_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorUnknown");
		public final static State<Icon> ICON_ATTACK_CHAOS = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageChaosNeutral");
		public final static State<Icon> ICON_ATTACK_CHAOS_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageChaos");
		public final static State<Icon> ICON_ATTACK_HERO = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageHeroNeutral");
		public final static State<Icon> ICON_ATTACK_HERO_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageHero");
		public final static State<Icon> ICON_ATTACK_MAGIC = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageMagicNeutral");
		public final static State<Icon> ICON_ATTACK_MAGIC_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageMagic");
		public final static State<Icon> ICON_ATTACK_MELEE = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageMeleeNeutral");
		public final static State<Icon> ICON_ATTACK_MELEE_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageMelee");
		public final static State<Icon> ICON_ATTACK_NORMAL = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageNormalNeutral");
		public final static State<Icon> ICON_ATTACK_NORMAL_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageNormal");
		public final static State<Icon> ICON_ATTACK_PIERCE = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamagePierceNeutral");
		public final static State<Icon> ICON_ATTACK_PIERCE_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamagePierce");
		public final static State<Icon> ICON_ATTACK_SIEGE = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageSiegeNeutral");
		public final static State<Icon> ICON_ATTACK_SIEGE_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageSiege");
		public final static State<Icon> ICON_ATTACK_UNKNOWN = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageUnknownNeutral");
		public final static State<Icon> ICON_ATTACK_UNKNOWN_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageUnknown");
		public final static State<Icon> ICON_GOLD = new State<>(Section.CUSTOM_SKIN, "GoldIcon");
		public final static State<Icon> ICON_GOLD_INFO = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconGold");
		public final static State<Icon> ICON_HERO_AGI = new State<>(Section.CUSTOM_SKIN, "HeroAgilityIcon");
		public final static State<Icon> ICON_HERO_AGI_INFO = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconHeroIconAGI");
		public final static State<Icon> ICON_HERO_INT = new State<>(Section.CUSTOM_SKIN, "HeroIntelligenceIcon");
		public final static State<Icon> ICON_HERO_INT_INFO = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconHeroIconINT");
		public final static State<Icon> ICON_HERO_STR = new State<>(Section.CUSTOM_SKIN, "HeroStrengthIcon");
		public final static State<Icon> ICON_HERO_STR_INFO = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconHeroIconSTR");
		public final static State<Icon> ICON_LUMBER = new State<>(Section.CUSTOM_SKIN, "LumberIcon");
		public final static State<Icon> ICON_UPKEEP = new State<>(Section.CUSTOM_SKIN, "UpkeepIcon");
		public final static State<Icon> ICON_REPLAY_LOOP = new State<>(Section.CUSTOM_SKIN, "ReplayLoopIcon");
		public final static State<Icon> ICON_REPLAY_LOOP_DISABLED = new State<>(Section.CUSTOM_SKIN, "ReplayLoopIconDisabled");
		public final static State<Icon> ICON_REPLAY_PAUSE = new State<>(Section.CUSTOM_SKIN, "ReplayPauseIcon");
		public final static State<Icon> ICON_REPLAY_PAUSE_DISABLED = new State<>(Section.CUSTOM_SKIN, "ReplayPauseIconDisabled");
		public final static State<Icon> ICON_REPLAY_RESTART = new State<>(Section.CUSTOM_SKIN, "ReplayRestartIcon");
		public final static State<Icon> ICON_REPLAY_RESTART_DISABLED = new State<>(Section.CUSTOM_SKIN, "ReplayRestartIconDisabled");
		public final static State<Icon> ICON_REPLAY_SPEED_DOWN = new State<>(Section.CUSTOM_SKIN, "ReplaySpeedDownIcon");
		public final static State<Icon> ICON_REPLAY_SPEED_DOWN_DISABLED = new State<>(Section.CUSTOM_SKIN, "ReplaySpeedDownIconDisabled");
		public final static State<Icon> ICON_REPLAY_SPEED_UP = new State<>(Section.CUSTOM_SKIN, "ReplaySpeedUpIcon");
		public final static State<Icon> ICON_REPLAY_SPEED_UP_DISABLED = new State<>(Section.CUSTOM_SKIN, "ReplaySpeedUpIconDisabled");
		public final static State<Icon> ICON_SUPPLY = new State<>(Section.CUSTOM_SKIN, "SupplyIcon");
		public final static State<Icon> ICON_SUPPLY_INFO = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconFood");
		public final static State<Icon> ICON_UPGRADE_ARMOR = new State<>(Section.CUSTOM_SKIN, "UpgradeArmorIcon");
		public final static State<Icon> ICON_UPGRADE_ARTILLERY = new State<>(Section.CUSTOM_SKIN, "UpgradeArtilleryIcon");
		public final static State<Icon> ICON_UPGRADE_MELEE = new State<>(Section.CUSTOM_SKIN, "UpgradeMeleeIcon");
		public final static State<Icon> ICON_UPGRADE_RANGED = new State<>(Section.CUSTOM_SKIN, "UpgradeRangedIcon");
		
		public final static State<Icon> ICON_BTN_HERO_GLOW = new State<>(Section.CUSTOM_SKIN, "CommandButtonHeroGlow");
		public final static State<Icon> ICON_BTN_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "CommandButtonActiveHighlight");
		public final static State<Icon> ICON_BTN_NUM_OVERLAY = new State<>(Section.CUSTOM_SKIN, "CommandButtonNumberOverlay");

		public final static State<Icon> ICON_MINIMAP_ALLY_ACTIVE_DISABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapAllyButtonActiveDisabled");
		public final static State<Icon> ICON_MINIMAP_ALLY_ACTIVE_ENABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapAllyButtonActiveEnabled");
		public final static State<Icon> ICON_MINIMAP_ALLY_ACTIVE_PRESSED = new State<>(Section.CUSTOM_SKIN, "MiniMapAllyButtonActivePushed");
		public final static State<Icon> ICON_MINIMAP_ALLY_INACTIVE_DISABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapAllyButtonInactiveDisabled");
		public final static State<Icon> ICON_MINIMAP_ALLY_INACTIVE_ENABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapAllyButtonInactiveEnabled");
		public final static State<Icon> ICON_MINIMAP_ALLY_INACTIVE_PRESSED = new State<>(Section.CUSTOM_SKIN, "MiniMapAllyButtonInactivePushed");
		public final static State<Icon> ICON_MINIMAP_ALLY_OFF_DISABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapAllyButtonOffDisabled");
		public final static State<Icon> ICON_MINIMAP_ALLY_OFF_ENABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapAllyButtonOffEnabled");
		public final static State<Icon> ICON_MINIMAP_ALLY_OFF_PRESSED = new State<>(Section.CUSTOM_SKIN, "MiniMapAllyButtonOffPushed");
		public final static State<Icon> ICON_MINIMAP_CREEP_ACTIVE_DISABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapCreepButtonActiveDisabled");
		public final static State<Icon> ICON_MINIMAP_CREEP_ACTIVE_ENABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapCreepButtonActiveEnabled");
		public final static State<Icon> ICON_MINIMAP_CREEP_ACTIVE_PRESSED = new State<>(Section.CUSTOM_SKIN, "MiniMapCreepButtonActivePushed");
		public final static State<Icon> ICON_MINIMAP_CREEP_INACTIVE_DISABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapCreepButtonInactiveDisabled");
		public final static State<Icon> ICON_MINIMAP_CREEP_INACTIVE_ENABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapCreepButtonInactiveEnabled");
		public final static State<Icon> ICON_MINIMAP_CREEP_INACTIVE_PRESSED = new State<>(Section.CUSTOM_SKIN, "MiniMapCreepButtonInactivePushed");
		public final static State<Icon> ICON_MINIMAP_FORMATION_OFF_DISABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapFormationOffButtonDisabled");
		public final static State<Icon> ICON_MINIMAP_FORMATION_OFF_ENABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapFormationOffButtonEnabled");
		public final static State<Icon> ICON_MINIMAP_FORMATION_OFF_PRESSED = new State<>(Section.CUSTOM_SKIN, "MiniMapFormationOffButtonEnabledPushed");
		public final static State<Icon> ICON_MINIMAP_FORMATION_ON_DISABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapFormationOnButtonDisabled");
		public final static State<Icon> ICON_MINIMAP_FORMATION_ON_ENABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapFormationOnButtonEnabled");
		public final static State<Icon> ICON_MINIMAP_FORMATION_ON_PRESSED = new State<>(Section.CUSTOM_SKIN, "MiniMapFormationOnButtonEnabledPushed");
		public final static State<Icon> ICON_MINIMAP_SIGNAL_DISABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapSignalButtonDisabled");
		public final static State<Icon> ICON_MINIMAP_SIGNAL_ENABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapSignalButtonEnabled");
		public final static State<Icon> ICON_MINIMAP_SIGNAL_PRESSED = new State<>(Section.CUSTOM_SKIN, "MiniMapSignalButtonPushed");
		public final static State<Icon> ICON_MINIMAP_TERRAIN_ACTIVE_DISABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapTerrainButtonActiveDisabled");
		public final static State<Icon> ICON_MINIMAP_TERRAIN_ACTIVE_ENABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapTerrainButtonActiveEnabled");
		public final static State<Icon> ICON_MINIMAP_TERRAIN_ACTIVE_PRESSED = new State<>(Section.CUSTOM_SKIN, "MiniMapTerrainButtonActivePushed");
		public final static State<Icon> ICON_MINIMAP_TERRAIN_INACTIVE_DISABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapTerrainButtonInactiveDisabled");
		public final static State<Icon> ICON_MINIMAP_TERRAIN_INACTIVE_ENABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapTerrainButtonInactiveEnabled");
		public final static State<Icon> ICON_MINIMAP_TERRAIN_INACTIVE_PRESSED = new State<>(Section.CUSTOM_SKIN, "MiniMapTerrainButtonInactivePushed");
		
		public final static State<Icon> ICON_MINIMAP_CIRCLE_OF_POWER = new State<>(Section.CUSTOM_SKIN, "MinimapCopTexture");
		public final static State<Icon> ICON_MINIMAP_CREEP_CAMP = new State<>(Section.CUSTOM_SKIN, "MinimapCreepCampTexture");
		public final static State<Icon> ICON_MINIMAP_CREEP_CAMP_LARGE = new State<>(Section.CUSTOM_SKIN, "MinimapToughCreepCampTexture");
		public final static State<Icon> ICON_MINIMAP_HERO = new State<>(Section.CUSTOM_SKIN, "MinimapHeroTexture");
		public final static State<Icon> ICON_MINIMAP_NEUTRAL = new State<>(Section.CUSTOM_SKIN, "MinimapNeutralTexture");
		public final static State<Icon> ICON_MINIMAP_RESOURCE = new State<>(Section.CUSTOM_SKIN, "MinimapResourceTexture");
		public final static State<Icon> ICON_MINIMAP_RESOURCE_ENTANGLED = new State<>(Section.CUSTOM_SKIN, "MinimapEntangledResourceTexture");
		public final static State<Icon> ICON_MINIMAP_RESOURCE_HAUNTED = new State<>(Section.CUSTOM_SKIN, "MinimapHauntedResourceTexture");
		
		public final static State<Model> MODEL_MINIMAP_CREEP_CAMP = new State<>(Section.CUSTOM_SKIN, "MinimapCreepCamp");
		public final static State<Model> MODEL_MINIMAP_CREEP_CAMP_LARGE = new State<>(Section.CUSTOM_SKIN, "MinimapCreepCampTough");
		public final static State<Model> MODEL_MINIMAP_HERO = new State<>(Section.CUSTOM_SKIN, "MinimapHero");
		public final static State<Model> MODEL_MINIMAP_INDICATOR = new State<>(Section.CUSTOM_SKIN, "MinimapIndicator");
		public final static State<Model> MODEL_MINIMAP_ITEM = new State<>(Section.CUSTOM_SKIN, "MinimapItem");
		public final static State<Model> MODEL_MINIMAP_NEUTRAL = new State<>(Section.CUSTOM_SKIN, "MinimapNeutral");
		public final static State<Model> MODEL_MINIMAP_RESOURCE = new State<>(Section.CUSTOM_SKIN, "MinimapResource");
		public final static State<Model> MODEL_MINIMAP_SPRITE = new State<>(Section.CUSTOM_SKIN, "MinimapSprite");
		public final static State<Model> MODEL_MINIMAP_WAYPOINT = new State<>(Section.CUSTOM_SKIN, "MinimapWaypoint");
		
		public final static State<Icon> ICON_QUEST_UNDISCOVERED = new State<>(Section.CUSTOM_SKIN, "UndiscoveredQuestIcon");
		
		public final static State<Model> MODEL_QUEST_CHANGED_PARTICLES = new State<>(Section.CUSTOM_SKIN, "QuestChangedParticles");
		
		public final static State<Icon> ICON_SCORESCREEN_HUMAN = new State<>(Section.CUSTOM_SKIN, "ScoreScreenPlayerHuman");
		public final static State<Icon> ICON_SCORESCREEN_NIGHT_ELF = new State<>(Section.CUSTOM_SKIN, "ScoreScreenPlayerNightElf");
		public final static State<Icon> ICON_SCORESCREEN_ORC = new State<>(Section.CUSTOM_SKIN, "ScoreScreenPlayerOrc");
		public final static State<Icon> ICON_SCORESCREEN_UNDEAD = new State<>(Section.CUSTOM_SKIN, "ScoreScreenPlayerUndead");

		public final static State<Icon> ICON_ALLIANCE_GOLD = new State<>(Section.CUSTOM_SKIN, "AllianceGold");
		public final static State<Icon> ICON_ALLIANCE_GOLD_DISABLED = new State<>(Section.CUSTOM_SKIN, "AllianceGoldDisabled");
		public final static State<Icon> ICON_ALLIANCE_LUMBER = new State<>(Section.CUSTOM_SKIN, "AllianceLumber");
		public final static State<Icon> ICON_ALLIANCE_LUMBER_DISABLED = new State<>(Section.CUSTOM_SKIN, "AllianceLumberDisabled");

		public final static State<Icon> ICON_ATTACK = new State<>(Section.CUSTOM_SKIN, "CommandAttack");
		public final static State<Icon> ICON_ATTACK_GROUND = new State<>(Section.CUSTOM_SKIN, "CommandAttackGround");
		public final static State<Icon> ICON_BUILD = new State<>(Section.CUSTOM_SKIN, "CommandBasicStruct");
		public final static State<Icon> ICON_BUILD_ADVANCED = new State<>(Section.CUSTOM_SKIN, "CommandAdvStruct");
		public final static State<Icon> ICON_BUILD_HUMAN = new State<>(Section.CUSTOM_SKIN, "CommandBasicStructHuman");
		public final static State<Icon> ICON_BUILD_NIGHT_ELF = new State<>(Section.CUSTOM_SKIN, "CommandBasicStructNightElf");
		public final static State<Icon> ICON_BUILD_ORC = new State<>(Section.CUSTOM_SKIN, "CommandBasicStructOrc");
		public final static State<Icon> ICON_BUILD_UNDEAD = new State<>(Section.CUSTOM_SKIN, "CommandBasicStructUndead");
		public final static State<Icon> ICON_CANCEL = new State<>(Section.CUSTOM_SKIN, "CommandCancel");
		public final static State<Icon> ICON_HARVEST = new State<>(Section.CUSTOM_SKIN, "CommandHarvest");
		public final static State<Icon> ICON_HOLD_POSITION = new State<>(Section.CUSTOM_SKIN, "CommandHoldPosition");
		public final static State<Icon> ICON_MOVE = new State<>(Section.CUSTOM_SKIN, "CommandMove");
		public final static State<Icon> ICON_NEW_SKILL = new State<>(Section.CUSTOM_SKIN, "CommandNewSkill");
		public final static State<Icon> ICON_PATROL = new State<>(Section.CUSTOM_SKIN, "CommandPatrol");
		public final static State<Icon> ICON_PICK_UP_ITEM = new State<>(Section.CUSTOM_SKIN, "CommandPickUpItem");
		public final static State<Icon> ICON_PURCHASE = new State<>(Section.CUSTOM_SKIN, "CommandPurchase");
		public final static State<Icon> ICON_RALLY = new State<>(Section.CUSTOM_SKIN, "CommandRally");
		public final static State<Icon> ICON_REPAIR = new State<>(Section.CUSTOM_SKIN, "CommandRepair");
		public final static State<Icon> ICON_RETURN_GOLD = new State<>(Section.CUSTOM_SKIN, "CommandReturnGold");
		public final static State<Icon> ICON_STOP = new State<>(Section.CUSTOM_SKIN, "CommandStop");
		
		public final static State<Icon> MODEL_AUTOCAST = new State<>(Section.CUSTOM_SKIN, "CommandButtonAutocast");
		public final static State<Icon> MODEL_COOLDOWN = new State<>(Section.CUSTOM_SKIN, "CommandButtonCooldown");
		
		public final static State<Icon> MODEL_CURSOR = new State<>(Section.CUSTOM_SKIN, "Cursor");
		public final static State<Icon> MODEL_MAP_LOAD_BACKGOUND = new State<>(Section.CUSTOM_SKIN, "MapLoadBg");
		public final static State<Icon> MODEL_MAP_LOAD_BUTTON = new State<>(Section.CUSTOM_SKIN, "MapLoadBtn");
		public final static State<Icon> MODEL_SELECTION_CIRCLE = new State<>(Section.CUSTOM_SKIN, "SelectionCircle");
		public final static State<Icon> MODEL_SELECTION_CIRCLE_PRE = new State<>(Section.CUSTOM_SKIN, "PreSelectionCircle");
		public final static State<Icon> MODEL_RALLY = new State<>(Section.CUSTOM_SKIN, "RallyIndicatorDst");
		public final static State<Icon> MODEL_TARGET_POINT_CONFIRM = new State<>(Section.CUSTOM_SKIN, "TargetPointConfirm");
		public final static State<Icon> MODEL_TARGET_SELECT_PRE = new State<>(Section.CUSTOM_SKIN, "TargetPreSelect");
		public final static State<Icon> MODEL_TARGET_UNIT_CONFIRM = new State<>(Section.CUSTOM_SKIN, "TargetUnitConfirm");
		public final static State<Icon> MODEL_WAYPOINT = new State<>(Section.CUSTOM_SKIN, "WaypointIndicator");
		
		public final static State<Model> MODEL_CONSOLE_EXP_BAR = new State<>(Section.CUSTOM_SKIN, "XpBarConsole");
		public final static State<Model> MODEL_CONSOLE_LIFE_BAR = new State<>(Section.CUSTOM_SKIN, "HpBarConsole");
		public final static State<Model> MODEL_CONSOLE_LIFE_BAR_SMALL = new State<>(Section.CUSTOM_SKIN, "HpBarConsoleSmall");
		public final static State<Model> MODEL_CONSOLE_MANA_BAR = new State<>(Section.CUSTOM_SKIN, "ManaBarConsole");
		public final static State<Model> MODEL_CONSOLE_MANA_BAR_SMALL = new State<>(Section.CUSTOM_SKIN, "ManaBarConsoleSmall");
		public final static State<Model> MODEL_CONSOLE_PROGRESS_BAR = new State<>(Section.CUSTOM_SKIN, "ProgressBarConsole");
		
		public final static State<Model> MODEL_RESOURCE_BAR = new State<>(Section.CUSTOM_SKIN, "ResourceBar");
		
		public final static State<Model> MODEL_BUILD_TIME = new State<>(Section.CUSTOM_SKIN, "BuildTimeIndicator");
		
		public final static State<Model> MODEL_ALLY_DIALOG_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "AllyDialogBackground");
		public final static State<Model> MODEL_ALLY_DIALOG_BUTTON = new State<>(Section.CUSTOM_SKIN, "AllyDialogButton");
		public final static State<Model> MODEL_ALLY_DIALOG_CHECKBOX = new State<>(Section.CUSTOM_SKIN, "AllyDialogCheckbox");
		
		public final static State<Model> MODEL_TIME_CLOCK = new State<>(Section.CUSTOM_SKIN, "TimeOfDayIndicator");
		
		public final static State<Model> MODEL_LOADING_MELEE_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "LoadingMeleeBackground");
		public final static State<Model> MODEL_PROGRESS_BAR = new State<>(Section.CUSTOM_SKIN, "LoadingProgressBar");
		
		public final static State<Model> MODEL_SCORESCREEN_DEFEAT = new State<>(Section.CUSTOM_SKIN, "ScoreScreenDefeat");
		public final static State<Model> MODEL_SCORESCREEN_VICTORY = new State<>(Section.CUSTOM_SKIN, "ScoreScreenVictory_V1");
		
		public final static State<Model> MODEL_COMMAND_BTN = new State<>(Section.CUSTOM_SKIN, "CommandButtonMDL");
		
		public final static State<Model> MODEL_BOUNTY = new State<>(Section.CUSTOM_SKIN, "BountyArt");
		public final static State<Model> MODEL_HERO_BAR_POINT = new State<>(Section.CUSTOM_SKIN, "HeroBarPointModel");
		
		public final static State<Icon> ICON_CONSOLE_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "ConsoleBackground");
		public final static State<Icon> ICON_CONSOLE_INVENTORY = new State<>(Section.CUSTOM_SKIN, "ConsoleInventoryCoverTexture");
		public final static State<Icon> ICON_CONSOLE_INVENTORY_NONE = new State<>(Section.CUSTOM_SKIN, "ConsoleInventoryNoCapacity");
		public final static State<Icon> ICON_CURSOR_PLACEMENT = new State<>(Section.CUSTOM_SKIN, "PlacementCursor");
		
		public final static State<Icon> ICON_CONSOLE_EXP_BAR = new State<>(Section.CUSTOM_SKIN, "SimpleXpBarConsole");
		public final static State<Icon> ICON_CONSOLE_EXP_BAR_BORDER = new State<>(Section.CUSTOM_SKIN, "SimpleXpBarBorder");
		public final static State<Icon> ICON_CONSOLE_LIFE_BAR = new State<>(Section.CUSTOM_SKIN, "SimpleHpBarConsole");
		public final static State<Icon> ICON_CONSOLE_LIFE_BAR_SMALL = new State<>(Section.CUSTOM_SKIN, "SimpleHpBarConsoleSmall");
		public final static State<Icon> ICON_CONSOLE_MANA_BAR = new State<>(Section.CUSTOM_SKIN, "SimpleManaBarConsole");
		public final static State<Icon> ICON_CONSOLE_MANA_BAR_SMALL = new State<>(Section.CUSTOM_SKIN, "SimpleManaBarConsoleSmall");
		public final static State<Icon> ICON_CONSOLE_PROGRESS_BAR = new State<>(Section.CUSTOM_SKIN, "SimpleProgressBarConsole");
		public final static State<Icon> ICON_CONSOLE_PROGRESS_BAR_BORDER = new State<>(Section.CUSTOM_SKIN, "SimpleProgressBarBorder");
		
		public final static State<Icon> ICON_SELECTED_SUBGROUP_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "SelectedSubgroupHighlight");
		public final static State<Icon> ICON_BUILD_TIME_INDICATOR = new State<>(Section.CUSTOM_SKIN, "SimpleBuildTimeIndicator");
		public final static State<Icon> ICON_BUILD_TIME_INDICATOR_BORDER = new State<>(Section.CUSTOM_SKIN, "SimpleBuildTimeIndicatorBorder");
		
		public final static State<Icon> ICON_BUILD_QUEUE_BACKDROP = new State<>(Section.CUSTOM_SKIN, "BuildQueueBackdrop");
		public final static State<Icon> ICON_CARGO_BACKDROP = new State<>(Section.CUSTOM_SKIN, "CargoBackdrop");
		public final static State<Icon> ICON_OCCUPIED_BACKDROP = new State<>(Section.CUSTOM_SKIN, "OccupBackdrop");
		public final static State<Icon> ICON_OCCUPIED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "OccupBackground");
		public final static State<Icon> ICON_OCCUPIED_BORDER = new State<>(Section.CUSTOM_SKIN, "OccupBorder");
		
		public final static State<Icon> ICON_REPLAY_CHECKBOX_NORMAL = new State<>(Section.CUSTOM_SKIN, "ReplayCheckBoxNormal");
		public final static State<Icon> ICON_REPLAY_CHECKBOX_PRESSED = new State<>(Section.CUSTOM_SKIN, "ReplayCheckBoxPressed");
		public final static State<Icon> ICON_REPLAY_CHECKBOX_CHECKED = new State<>(Section.CUSTOM_SKIN, "ReplayCheckBoxCheck");
		public final static State<Icon> ICON_REPLAY_PANEL_BACKDROP = new State<>(Section.CUSTOM_SKIN, "ReplayPanelBackdrop");
		
		public final static State<Icon> ICON_CONSOLE_BUTTON_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "ConsoleButtonBackground");
		public final static State<Icon> ICON_CONSOLE_BUTTON_BORDER = new State<>(Section.CUSTOM_SKIN, "ConsoleButtonBorder");
		public final static State<Icon> ICON_CONSOLE_BUTTON_PRESSED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "ConsoleButtonPushedBackground");
		public final static State<Icon> ICON_CONSOLE_BUTTON_PRESSED_BORDER = new State<>(Section.CUSTOM_SKIN, "ConsoleButtonPushedBorder");
		public final static State<Icon> ICON_CONSOLE_BUTTON_DISABLED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "ConsoleButtonDisabledBackground");
		public final static State<Icon> ICON_CONSOLE_BUTTON_DISABLED_BORDER = new State<>(Section.CUSTOM_SKIN, "ConsoleButtonDisabledBorder");
		
		public final static State<Icon> ICON_CONSOLE_BUTTON_MOUSE_OVER_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "ConsoleButtonMouseOverHighlight");
		
		public final static State<Icon> ICON_TOOLTIP_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "ToolTipBackground");
		public final static State<Icon> ICON_TOOLTIP_BORDER = new State<>(Section.CUSTOM_SKIN, "ToolTipBorder");
		public final static State<Icon> ICON_TOOLTIP_GOLD = new State<>(Section.CUSTOM_SKIN, "ToolTipGoldIcon");
		public final static State<Icon> ICON_TOOLTIP_LUMBER = new State<>(Section.CUSTOM_SKIN, "ToolTipLumberIcon");
		public final static State<Icon> ICON_TOOLTIP_MANA = new State<>(Section.CUSTOM_SKIN, "ToolTipManaIcon");
		public final static State<Icon> ICON_TOOLTIP_STONES = new State<>(Section.CUSTOM_SKIN, "ToolTipStonesIcon");
		public final static State<Icon> ICON_TOOLTIP_SUPPLY = new State<>(Section.CUSTOM_SKIN, "ToolTipSupplyIcon");
		
		public final static State<Icon> ICON_HERO_BAR_BORDER = new State<>(Section.CUSTOM_SKIN, "HeroBarBorder");
		public final static State<Icon> ICON_GROUP_BUTTON_BORDER = new State<>(Section.CUSTOM_SKIN, "GroupButtonBorder");
		public final static State<Icon> ICON_UPPER_MENU_BUTTON = new State<>(Section.CUSTOM_SKIN, "UpperMenuButtonTexture");
		public final static State<Icon> ICON_CINEMATIC_BORDER = new State<>(Section.CUSTOM_SKIN, "CinematicBorder");
		public final static State<Icon> ICON_QUEST_DIALOG_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "QuestDialogBackground");
		public final static State<Icon> ICON_QUEST_DIALOG_BORDER = new State<>(Section.CUSTOM_SKIN, "QuestDialogBorder");
		public final static State<Icon> ICON_QUEST_DIALOG_BUTTON_MOUSE_OVER_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "QuestDialogButtonMouseOverHighlight");
		public final static State<Icon> ICON_QUEST_DIALOG_NORMAL_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "QuestDialogNormalBackground");
		public final static State<Icon> ICON_QUEST_DIALOG_NORMAL_BORDER = new State<>(Section.CUSTOM_SKIN, "QuestDialogNormalBorder");
		public final static State<Icon> ICON_QUEST_DIALOG_COMPLETED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "QuestDialogCompletedBackground");
		public final static State<Icon> ICON_QUEST_DIALOG_COMPLETED_BORDER = new State<>(Section.CUSTOM_SKIN, "QuestDialogCompletedBorder");
		public final static State<Icon> ICON_QUEST_DIALOG_COMPLETED_BUTTON_MOUSE_OVER_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "QuestDialogCompletedMouseOverHighlight");
		public final static State<Icon> ICON_QUEST_DIALOG_FAILED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "QuestDialogFailedBackground");
		public final static State<Icon> ICON_QUEST_DIALOG_FAILED_BORDER = new State<>(Section.CUSTOM_SKIN, "QuestDialogFailedBorder");
		public final static State<Icon> ICON_QUEST_DIALOG_FAILED_BUTTON_MOUSE_OVER_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "QuestDialogFailedMouseOverHighlight");
		
		public final static State<Icon> ICON_ESC_MENU_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuBackground");
		public final static State<Icon> ICON_ESC_MENU_BORDER = new State<>(Section.CUSTOM_SKIN, "EscMenuBorder");
		public final static State<Icon> ICON_ESC_MENU_BUTTON_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuButtonBackground");
		public final static State<Icon> ICON_ESC_MENU_BUTTON_BORDER = new State<>(Section.CUSTOM_SKIN, "EscMenuButtonBorder");
		public final static State<Icon> ICON_ESC_MENU_BUTTON_PRESSED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuButtonPushedBackground");
		public final static State<Icon> ICON_ESC_MENU_BUTTON_PRESSED_BORDER = new State<>(Section.CUSTOM_SKIN, "EscMenuButtonPushedBorder");
		public final static State<Icon> ICON_ESC_MENU_BUTTON_DISABLED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuButtonDisabledBackground");
		public final static State<Icon> ICON_ESC_MENU_BUTTON_DISABLED_BORDER = new State<>(Section.CUSTOM_SKIN, "EscMenuButtonDisabledBorder");
		public final static State<Icon> ICON_ESC_MENU_BUTTON_DISABLED_PRESSED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuButtonDisabledPushedBackground");
		public final static State<Icon> ICON_ESC_MENU_BUTTON_DISABLED_PRESSED_BORDER = new State<>(Section.CUSTOM_SKIN, "EscMenuButtonDisabledPushedBorder");
		public final static State<Icon> ICON_ESC_MENU_BUTTON_MOUSE_OVER_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "EscMenuButtonMouseOverHighlight");
		
		public final static State<Icon> ICON_ESC_MENU_SLIDER_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuSliderBackground");
		public final static State<Icon> ICON_ESC_MENU_SLIDER_BORDER = new State<>(Section.CUSTOM_SKIN, "EscMenuSliderBorder");
		public final static State<Icon> ICON_ESC_MENU_SLIDER_DISABLED_BORDER = new State<>(Section.CUSTOM_SKIN, "EscMenuSliderDisabledBorder");
		public final static State<Icon> ICON_ESC_MENU_SLIDER_THUMB_BUTTON = new State<>(Section.CUSTOM_SKIN, "EscMenuSliderThumbButton");
		public final static State<Icon> ICON_ESC_MENU_SLIDER_THUMB_BUTTON_DISABLED = new State<>(Section.CUSTOM_SKIN, "EscMenuSliderDisabledThumbButton");
		
		public final static State<Icon> ICON_ESC_MENU_CHECKBOX_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuCheckBoxBackground");
		public final static State<Icon> ICON_ESC_MENU_CHECKBOX_CHECK_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "EscMenuCheckBoxCheckHighlight");
		public final static State<Icon> ICON_ESC_MENU_CHECKBOX_DISABLED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuDisabledCheckBoxBackground");
		public final static State<Icon> ICON_ESC_MENU_CHECKBOX_DISABLED_CHECK_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "EscMenuDisabledCheckBoxCheckHighlight");
		public final static State<Icon> ICON_ESC_MENU_CHECKBOX_PRESSED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuCheckBoxPushedBackground");
		public final static State<Icon> ICON_ESC_MENU_POPUP_MENU_ARROW = new State<>(Section.CUSTOM_SKIN, "EscMenuPopupMenuArrow");
		public final static State<Icon> ICON_ESC_MENU_POPUP_MENU_MENU_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuPopupMenuMenuBackground");
		public final static State<Icon> ICON_ESC_MENU_RADIO_BUTTON_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuRadioButtonBackground");
		public final static State<Icon> ICON_ESC_MENU_RADIO_BUTTON_DISABLED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuRadioButtonDisabledBackground");
		public final static State<Icon> ICON_ESC_MENU_RADIO_BUTTON_DISABLED_SELECTED_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "EscMenuDisabledRadioButtonSelectedHighlight");
		public final static State<Icon> ICON_ESC_MENU_RADIO_BUTTON_PRESSED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuRadioButtonPushedBackground");
		public final static State<Icon> ICON_ESC_MENU_RADIO_BUTTON_SELECTED_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "EscMenuRadioButtonSelectedHighlight");
		
		public final static State<Icon> ICON_ESC_MENU_BACKGROUND_BLANK = new State<>(Section.CUSTOM_SKIN, "EscMenuBlankBackground");
		public final static State<Icon> ICON_ESC_MENU_EDIT_BOX_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuEditBoxBackground");
		public final static State<Icon> ICON_ESC_MENU_EDIT_BOX_BORDER = new State<>(Section.CUSTOM_SKIN, "EscMenuEditBoxBorder");
		
		public final static State<Icon> CONSOLE_TEX1 = new State<>(Section.CUSTOM_SKIN, "ConsoleTexture01");
		public final static State<Icon> CONSOLE_TEX2 = new State<>(Section.CUSTOM_SKIN, "ConsoleTexture02");
		public final static State<Icon> CONSOLE_TEX3 = new State<>(Section.CUSTOM_SKIN, "ConsoleTexture03");
		public final static State<Icon> CONSOLE_TEX4 = new State<>(Section.CUSTOM_SKIN, "ConsoleTexture04");
		
		public final static State<Icon> ICON_UNIT_TIP_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "UnitTipBackground");
		
		public final static State<Icon> ICON_MULTIBOARD_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "MultiboardBackground");
		public final static State<Icon> ICON_MULTIBOARD_BORDER = new State<>(Section.CUSTOM_SKIN, "MultiboardBorder");
		public final static State<Icon> ICON_MULTIBOARD_ICON_BORDER = new State<>(Section.CUSTOM_SKIN, "MultiboardIconBorder");
		public final static State<Icon> ICON_MULTIBOARD_ICON_DEFAULT = new State<>(Section.CUSTOM_SKIN, "MultiboardDefaultIcon");
		public final static State<Icon> ICON_MULTIBOARD_MAXIMIZE_BUTTON_ENABLED = new State<>(Section.CUSTOM_SKIN, "MultiboardMaximizeButtonEnabled");
		public final static State<Icon> ICON_MULTIBOARD_MAXIMIZE_BUTTON_DISABLED = new State<>(Section.CUSTOM_SKIN, "MultiboardMaximizeButtonDisabled");
		public final static State<Icon> ICON_MULTIBOARD_MAXIMIZE_BUTTON_PRESSED = new State<>(Section.CUSTOM_SKIN, "MultiboardMaximizeButtonPushed");
		public final static State<Icon> ICON_MULTIBOARD_MINIMIZE_BUTTON_ENABLED = new State<>(Section.CUSTOM_SKIN, "MultiboardMinimizeButtonEnabled");
		public final static State<Icon> ICON_MULTIBOARD_MINIMIZE_BUTTON_DISABLED = new State<>(Section.CUSTOM_SKIN, "MultiboardMinimizeButtonDisabled");
		public final static State<Icon> ICON_MULTIBOARD_MINIMIZE_BUTTON_PRESSED = new State<>(Section.CUSTOM_SKIN, "MultiboardMinimizeButtonPushed");
		
		public final static State<Icon> ICON_SCORESCREEN_BANNER_DEFEAT = new State<>(Section.CUSTOM_SKIN, "ScoreScreenBannerLoss");
		public final static State<Icon> ICON_SCORESCREEN_BANNER_VICTORY = new State<>(Section.CUSTOM_SKIN, "ScoreScreenBannerWin");
		
		public final static State<SoundLabel> SOUND_CANNOT_PLACE = new State<>(Section.CUSTOM_SKIN, "CantPlaceSound");
		public final static State<SoundLabel> SOUND_CONSTRUCTING = new State<>(Section.CUSTOM_SKIN, "ConstructingBuilding");
		public final static State<SoundLabel> SOUND_GOLD_MINE_COLLAPSED = new State<>(Section.CUSTOM_SKIN, "GoldMineCollapseSound");
		public final static State<SoundLabel> SOUND_GOLD_MINE_LOW = new State<>(Section.CUSTOM_SKIN, "GoldMineLowSound");
		public final static State<SoundLabel> SOUND_HERO_DEAD = new State<>(Section.CUSTOM_SKIN, "HeroDiesSound");
		public final static State<SoundLabel> SOUND_HERO_DEAD_ALLY = new State<>(Section.CUSTOM_SKIN, "AllyHeroDiesSound");
		public final static State<SoundLabel> SOUND_INV_FULL = new State<>(Section.CUSTOM_SKIN, "InventoryFullSound");
		public final static State<SoundLabel> SOUND_JOB_DONE = new State<>(Section.CUSTOM_SKIN, "JobDoneSound");
		public final static State<SoundLabel> SOUND_NO_GOLD = new State<>(Section.CUSTOM_SKIN, "NoGoldSound");
		public final static State<SoundLabel> SOUND_NO_LUMBER = new State<>(Section.CUSTOM_SKIN, "NoLumberSound");
		public final static State<SoundLabel> SOUND_NO_MANA = new State<>(Section.CUSTOM_SKIN, "NoManaSound");
		public final static State<SoundLabel> SOUND_NO_SUPPLY = new State<>(Section.CUSTOM_SKIN, "NoFoodSound");
		public final static State<SoundLabel> SOUND_PLACE_STRUCTURE = new State<>(Section.CUSTOM_SKIN, "PlaceBuilding");
		public final static State<SoundLabel> SOUND_RESEARCH_COMPLETE = new State<>(Section.CUSTOM_SKIN, "ResearchComplete");
		public final static State<SoundLabel> SOUND_UNDER_ATTACK = new State<>(Section.CUSTOM_SKIN, "UnderAttackSound");
		public final static State<SoundLabel> SOUND_UNDER_ATTACK_ALLY = new State<>(Section.CUSTOM_SKIN, "AllyUnderAttackSound");
		public final static State<SoundLabel> SOUND_UNDER_ATTACK_TOWN = new State<>(Section.CUSTOM_SKIN, "TownAttackSound");
		public final static State<SoundLabel> SOUND_UNDER_ATTACK_TOWN_ALLY = new State<>(Section.CUSTOM_SKIN, "AllyTownUnderAttackSound");
		public final static State<SoundLabel> SOUND_UPGRADE_COMPLETE = new State<>(Section.CUSTOM_SKIN, "UpgradeComplete");

		public final static State<MusicFile> MUSIC_DEFEAT = new State<>(Section.CUSTOM_SKIN, "DefeatMusic_V1");
		public final static State<MusicFile> MUSIC_VICTORY = new State<>(Section.CUSTOM_SKIN, "VictoryMusic_V1");
		
		public final static State<String> PATH_COMMAND_BUTTON_DISABLED = new State<>(Section.CUSTOM_SKIN, "CommandButtonDisabledArtPath");
		public final static State<String> PATH_TEAM_COLOR = new State<>(Section.CUSTOM_SKIN, "TeamColor");
		public final static State<String> PATH_TEAM_GLOW = new State<>(Section.CUSTOM_SKIN, "TeamGlow");
	}
	
	@Override
	public void read(File file) throws IOException {
		super.read(file);
	}
	
	@Override
	public void write(File file) throws IOException {
		super.write(file);
	}
	
	public SkinTXT() {
		
	}
}
