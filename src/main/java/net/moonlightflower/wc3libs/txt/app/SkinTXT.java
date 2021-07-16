package net.moonlightflower.wc3libs.txt.app;

import java.io.File;
import java.io.IOException;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.txt.TXT;
import net.moonlightflower.wc3libs.txt.TXTState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SkinTXT extends TXT {
	public final static File GAME_PATH = new File("war3mapSkin.txt");
	
	public static class States {
		public static class Section extends TXT.Section {
			public final static Section CUSTOM_SKIN = new Section("CustomSkin");

			public Section(@Nonnull String name) {
				super(name);
			}
		}
		
		public static class State<T extends DataType> extends TXTState<T> {
			public final static State<Icon> DEFAULT_BTN = new State<>("DefaultButton", Icon.class);
			public final static State<Icon> IDLE_PEON = new State<>("IdlePeon", Icon.class);
			public final static State<Icon> ICON_ARMOR_DIVINE = new State<>("InfoPanelIconArmorDivineNeutral", Icon.class);
			public final static State<Icon> ICON_ARMOR_DIVINE_UP = new State<>("InfoPanelIconArmorDivine", Icon.class);
			public final static State<Icon> ICON_ARMOR_FORT = new State<>("InfoPanelIconArmorFortNeutral", Icon.class);
			public final static State<Icon> ICON_ARMOR_FORT_UP = new State<>("InfoPanelIconArmorFort", Icon.class);
			public final static State<Icon> ICON_ARMOR_HERO = new State<>("InfoPanelIconArmorHeroNeutral", Icon.class);
			public final static State<Icon> ICON_ARMOR_HERO_UP = new State<>("InfoPanelIconArmorHero", Icon.class);
			public final static State<Icon> ICON_ARMOR_LARGE = new State<>("InfoPanelIconArmorLargeNeutral", Icon.class);
			public final static State<Icon> ICON_ARMOR_LARGE_UP = new State<>("InfoPanelIconArmorLarge", Icon.class);
			public final static State<Icon> ICON_ARMOR_MEDIUM = new State<>("InfoPanelIconArmorMediumNeutral", Icon.class);
			public final static State<Icon> ICON_ARMOR_MEDIUM_UP = new State<>("InfoPanelIconArmorMedium", Icon.class);
			public final static State<Icon> ICON_ARMOR_NONE = new State<>("InfoPanelIconArmorNoneNeutral", Icon.class);
			public final static State<Icon> ICON_ARMOR_NONE_UP = new State<>("InfoPanelIconArmorNone", Icon.class);
			public final static State<Icon> ICON_ARMOR_NORMAL = new State<>("InfoPanelIconArmorNormalNeutral", Icon.class);
			public final static State<Icon> ICON_ARMOR_NORMAL_UP = new State<>("InfoPanelIconArmorNormal", Icon.class);
			public final static State<Icon> ICON_ARMOR_SMALL = new State<>("InfoPanelIconArmorSmallNeutral", Icon.class);
			public final static State<Icon> ICON_ARMOR_SMALL_UP = new State<>("InfoPanelIconArmorSmall", Icon.class);
			public final static State<Icon> ICON_ARMOR_UNKNOWN = new State<>("InfoPanelIconArmorUnknownNeutral", Icon.class);
			public final static State<Icon> ICON_ARMOR_UNKNOWN_UP = new State<>("InfoPanelIconArmorUnknown", Icon.class);
			public final static State<Icon> ICON_ATTACK_CHAOS = new State<>("InfoPanelIconDamageChaosNeutral", Icon.class);
			public final static State<Icon> ICON_ATTACK_CHAOS_UP = new State<>("InfoPanelIconDamageChaos", Icon.class);
			public final static State<Icon> ICON_ATTACK_HERO = new State<>("InfoPanelIconDamageHeroNeutral", Icon.class);
			public final static State<Icon> ICON_ATTACK_HERO_UP = new State<>("InfoPanelIconDamageHero", Icon.class);
			public final static State<Icon> ICON_ATTACK_MAGIC = new State<>("InfoPanelIconDamageMagicNeutral", Icon.class);
			public final static State<Icon> ICON_ATTACK_MAGIC_UP = new State<>("InfoPanelIconDamageMagic", Icon.class);
			public final static State<Icon> ICON_ATTACK_MELEE = new State<>("InfoPanelIconDamageMeleeNeutral", Icon.class);
			public final static State<Icon> ICON_ATTACK_MELEE_UP = new State<>("InfoPanelIconDamageMelee", Icon.class);
			public final static State<Icon> ICON_ATTACK_NORMAL = new State<>("InfoPanelIconDamageNormalNeutral", Icon.class);
			public final static State<Icon> ICON_ATTACK_NORMAL_UP = new State<>("InfoPanelIconDamageNormal", Icon.class);
			public final static State<Icon> ICON_ATTACK_PIERCE = new State<>("InfoPanelIconDamagePierceNeutral", Icon.class);
			public final static State<Icon> ICON_ATTACK_PIERCE_UP = new State<>("InfoPanelIconDamagePierce", Icon.class);
			public final static State<Icon> ICON_ATTACK_SIEGE = new State<>("InfoPanelIconDamageSiegeNeutral", Icon.class);
			public final static State<Icon> ICON_ATTACK_SIEGE_UP = new State<>("InfoPanelIconDamageSiege", Icon.class);
			public final static State<Icon> ICON_ATTACK_UNKNOWN = new State<>("InfoPanelIconDamageUnknownNeutral", Icon.class);
			public final static State<Icon> ICON_ATTACK_UNKNOWN_UP = new State<>("InfoPanelIconDamageUnknown", Icon.class);
			public final static State<Icon> ICON_GOLD = new State<>("GoldIcon", Icon.class);
			public final static State<Icon> ICON_GOLD_INFO = new State<>("InfoPanelIconGold", Icon.class);
			public final static State<Icon> ICON_HERO_AGI = new State<>("HeroAgilityIcon", Icon.class);
			public final static State<Icon> ICON_HERO_AGI_INFO = new State<>("InfoPanelIconHeroIconAGI", Icon.class);
			public final static State<Icon> ICON_HERO_INT = new State<>("HeroIntelligenceIcon", Icon.class);
			public final static State<Icon> ICON_HERO_INT_INFO = new State<>("InfoPanelIconHeroIconINT", Icon.class);
			public final static State<Icon> ICON_HERO_STR = new State<>("HeroStrengthIcon", Icon.class);
			public final static State<Icon> ICON_HERO_STR_INFO = new State<>("InfoPanelIconHeroIconSTR", Icon.class);
			public final static State<Icon> ICON_LUMBER = new State<>("LumberIcon", Icon.class);
			public final static State<Icon> ICON_UPKEEP = new State<>("UpkeepIcon", Icon.class);
			public final static State<Icon> ICON_REPLAY_LOOP = new State<>("ReplayLoopIcon", Icon.class);
			public final static State<Icon> ICON_REPLAY_LOOP_DISABLED = new State<>("ReplayLoopIconDisabled", Icon.class);
			public final static State<Icon> ICON_REPLAY_PAUSE = new State<>("ReplayPauseIcon", Icon.class);
			public final static State<Icon> ICON_REPLAY_PAUSE_DISABLED = new State<>("ReplayPauseIconDisabled", Icon.class);
			public final static State<Icon> ICON_REPLAY_RESTART = new State<>("ReplayRestartIcon", Icon.class);
			public final static State<Icon> ICON_REPLAY_RESTART_DISABLED = new State<>("ReplayRestartIconDisabled", Icon.class);
			public final static State<Icon> ICON_REPLAY_SPEED_DOWN = new State<>("ReplaySpeedDownIcon", Icon.class);
			public final static State<Icon> ICON_REPLAY_SPEED_DOWN_DISABLED = new State<>("ReplaySpeedDownIconDisabled", Icon.class);
			public final static State<Icon> ICON_REPLAY_SPEED_UP = new State<>("ReplaySpeedUpIcon", Icon.class);
			public final static State<Icon> ICON_REPLAY_SPEED_UP_DISABLED = new State<>("ReplaySpeedUpIconDisabled", Icon.class);
			public final static State<Icon> ICON_SUPPLY = new State<>("SupplyIcon", Icon.class);
			public final static State<Icon> ICON_SUPPLY_INFO = new State<>("InfoPanelIconFood", Icon.class);
			public final static State<Icon> ICON_UPGRADE_ARMOR = new State<>("UpgradeArmorIcon", Icon.class);
			public final static State<Icon> ICON_UPGRADE_ARTILLERY = new State<>("UpgradeArtilleryIcon", Icon.class);
			public final static State<Icon> ICON_UPGRADE_MELEE = new State<>("UpgradeMeleeIcon", Icon.class);
			public final static State<Icon> ICON_UPGRADE_RANGED = new State<>("UpgradeRangedIcon", Icon.class);

			public final static State<Icon> ICON_BTN_HERO_GLOW = new State<>("CommandButtonHeroGlow", Icon.class);
			public final static State<Icon> ICON_BTN_HIGHLIGHT = new State<>("CommandButtonActiveHighlight", Icon.class);
			public final static State<Icon> ICON_BTN_NUM_OVERLAY = new State<>("CommandButtonNumberOverlay", Icon.class);

			public final static State<Icon> ICON_MINIMAP_ALLY_ACTIVE_DISABLED = new State<>("MiniMapAllyButtonActiveDisabled", Icon.class);
			public final static State<Icon> ICON_MINIMAP_ALLY_ACTIVE_ENABLED = new State<>("MiniMapAllyButtonActiveEnabled", Icon.class);
			public final static State<Icon> ICON_MINIMAP_ALLY_ACTIVE_PRESSED = new State<>("MiniMapAllyButtonActivePushed", Icon.class);
			public final static State<Icon> ICON_MINIMAP_ALLY_INACTIVE_DISABLED = new State<>("MiniMapAllyButtonInactiveDisabled", Icon.class);
			public final static State<Icon> ICON_MINIMAP_ALLY_INACTIVE_ENABLED = new State<>("MiniMapAllyButtonInactiveEnabled", Icon.class);
			public final static State<Icon> ICON_MINIMAP_ALLY_INACTIVE_PRESSED = new State<>("MiniMapAllyButtonInactivePushed", Icon.class);
			public final static State<Icon> ICON_MINIMAP_ALLY_OFF_DISABLED = new State<>("MiniMapAllyButtonOffDisabled", Icon.class);
			public final static State<Icon> ICON_MINIMAP_ALLY_OFF_ENABLED = new State<>("MiniMapAllyButtonOffEnabled", Icon.class);
			public final static State<Icon> ICON_MINIMAP_ALLY_OFF_PRESSED = new State<>("MiniMapAllyButtonOffPushed", Icon.class);
			public final static State<Icon> ICON_MINIMAP_CREEP_ACTIVE_DISABLED = new State<>("MiniMapCreepButtonActiveDisabled", Icon.class);
			public final static State<Icon> ICON_MINIMAP_CREEP_ACTIVE_ENABLED = new State<>("MiniMapCreepButtonActiveEnabled", Icon.class);
			public final static State<Icon> ICON_MINIMAP_CREEP_ACTIVE_PRESSED = new State<>("MiniMapCreepButtonActivePushed", Icon.class);
			public final static State<Icon> ICON_MINIMAP_CREEP_INACTIVE_DISABLED = new State<>("MiniMapCreepButtonInactiveDisabled", Icon.class);
			public final static State<Icon> ICON_MINIMAP_CREEP_INACTIVE_ENABLED = new State<>("MiniMapCreepButtonInactiveEnabled", Icon.class);
			public final static State<Icon> ICON_MINIMAP_CREEP_INACTIVE_PRESSED = new State<>("MiniMapCreepButtonInactivePushed", Icon.class);
			public final static State<Icon> ICON_MINIMAP_FORMATION_OFF_DISABLED = new State<>("MiniMapFormationOffButtonDisabled", Icon.class);
			public final static State<Icon> ICON_MINIMAP_FORMATION_OFF_ENABLED = new State<>("MiniMapFormationOffButtonEnabled", Icon.class);
			public final static State<Icon> ICON_MINIMAP_FORMATION_OFF_PRESSED = new State<>("MiniMapFormationOffButtonEnabledPushed", Icon.class);
			public final static State<Icon> ICON_MINIMAP_FORMATION_ON_DISABLED = new State<>("MiniMapFormationOnButtonDisabled", Icon.class);
			public final static State<Icon> ICON_MINIMAP_FORMATION_ON_ENABLED = new State<>("MiniMapFormationOnButtonEnabled", Icon.class);
			public final static State<Icon> ICON_MINIMAP_FORMATION_ON_PRESSED = new State<>("MiniMapFormationOnButtonEnabledPushed", Icon.class);
			public final static State<Icon> ICON_MINIMAP_SIGNAL_DISABLED = new State<>("MiniMapSignalButtonDisabled", Icon.class);
			public final static State<Icon> ICON_MINIMAP_SIGNAL_ENABLED = new State<>("MiniMapSignalButtonEnabled", Icon.class);
			public final static State<Icon> ICON_MINIMAP_SIGNAL_PRESSED = new State<>("MiniMapSignalButtonPushed", Icon.class);
			public final static State<Icon> ICON_MINIMAP_TERRAIN_ACTIVE_DISABLED = new State<>("MiniMapTerrainButtonActiveDisabled", Icon.class);
			public final static State<Icon> ICON_MINIMAP_TERRAIN_ACTIVE_ENABLED = new State<>("MiniMapTerrainButtonActiveEnabled", Icon.class);
			public final static State<Icon> ICON_MINIMAP_TERRAIN_ACTIVE_PRESSED = new State<>("MiniMapTerrainButtonActivePushed", Icon.class);
			public final static State<Icon> ICON_MINIMAP_TERRAIN_INACTIVE_DISABLED = new State<>("MiniMapTerrainButtonInactiveDisabled", Icon.class);
			public final static State<Icon> ICON_MINIMAP_TERRAIN_INACTIVE_ENABLED = new State<>("MiniMapTerrainButtonInactiveEnabled", Icon.class);
			public final static State<Icon> ICON_MINIMAP_TERRAIN_INACTIVE_PRESSED = new State<>("MiniMapTerrainButtonInactivePushed", Icon.class);

			public final static State<Icon> ICON_MINIMAP_CIRCLE_OF_POWER = new State<>("MinimapCopTexture", Icon.class);
			public final static State<Icon> ICON_MINIMAP_CREEP_CAMP = new State<>("MinimapCreepCampTexture", Icon.class);
			public final static State<Icon> ICON_MINIMAP_CREEP_CAMP_LARGE = new State<>("MinimapToughCreepCampTexture", Icon.class);
			public final static State<Icon> ICON_MINIMAP_HERO = new State<>("MinimapHeroTexture", Icon.class);
			public final static State<Icon> ICON_MINIMAP_NEUTRAL = new State<>("MinimapNeutralTexture", Icon.class);
			public final static State<Icon> ICON_MINIMAP_RESOURCE = new State<>("MinimapResourceTexture", Icon.class);
			public final static State<Icon> ICON_MINIMAP_RESOURCE_ENTANGLED = new State<>("MinimapEntangledResourceTexture", Icon.class);
			public final static State<Icon> ICON_MINIMAP_RESOURCE_HAUNTED = new State<>("MinimapHauntedResourceTexture", Icon.class);

			public final static State<Model> MODEL_MINIMAP_CREEP_CAMP = new State<>("MinimapCreepCamp", Model.class);
			public final static State<Model> MODEL_MINIMAP_CREEP_CAMP_LARGE = new State<>("MinimapCreepCampTough", Model.class);
			public final static State<Model> MODEL_MINIMAP_HERO = new State<>("MinimapHero", Model.class);
			public final static State<Model> MODEL_MINIMAP_INDICATOR = new State<>("MinimapIndicator", Model.class);
			public final static State<Model> MODEL_MINIMAP_ITEM = new State<>("MinimapItem", Model.class);
			public final static State<Model> MODEL_MINIMAP_NEUTRAL = new State<>("MinimapNeutral", Model.class);
			public final static State<Model> MODEL_MINIMAP_RESOURCE = new State<>("MinimapResource", Model.class);
			public final static State<Model> MODEL_MINIMAP_SPRITE = new State<>("MinimapSprite", Model.class);
			public final static State<Model> MODEL_MINIMAP_WAYPOINT = new State<>("MinimapWaypoint", Model.class);

			public final static State<Icon> ICON_QUEST_UNDISCOVERED = new State<>("UndiscoveredQuestIcon", Icon.class);

			public final static State<Model> MODEL_QUEST_CHANGED_PARTICLES = new State<>("QuestChangedParticles", Model.class);

			public final static State<Icon> ICON_SCORESCREEN_HUMAN = new State<>("ScoreScreenPlayerHuman", Icon.class);
			public final static State<Icon> ICON_SCORESCREEN_NIGHT_ELF = new State<>("ScoreScreenPlayerNightElf", Icon.class);
			public final static State<Icon> ICON_SCORESCREEN_ORC = new State<>("ScoreScreenPlayerOrc", Icon.class);
			public final static State<Icon> ICON_SCORESCREEN_UNDEAD = new State<>("ScoreScreenPlayerUndead", Icon.class);

			public final static State<Icon> ICON_ALLIANCE_GOLD = new State<>("AllianceGold", Icon.class);
			public final static State<Icon> ICON_ALLIANCE_GOLD_DISABLED = new State<>("AllianceGoldDisabled", Icon.class);
			public final static State<Icon> ICON_ALLIANCE_LUMBER = new State<>("AllianceLumber", Icon.class);
			public final static State<Icon> ICON_ALLIANCE_LUMBER_DISABLED = new State<>("AllianceLumberDisabled", Icon.class);

			public final static State<Icon> ICON_ATTACK = new State<>("CommandAttack", Icon.class);
			public final static State<Icon> ICON_ATTACK_GROUND = new State<>("CommandAttackGround", Icon.class);
			public final static State<Icon> ICON_BUILD = new State<>("CommandBasicStruct", Icon.class);
			public final static State<Icon> ICON_BUILD_ADVANCED = new State<>("CommandAdvStruct", Icon.class);
			public final static State<Icon> ICON_BUILD_HUMAN = new State<>("CommandBasicStructHuman", Icon.class);
			public final static State<Icon> ICON_BUILD_NIGHT_ELF = new State<>("CommandBasicStructNightElf", Icon.class);
			public final static State<Icon> ICON_BUILD_ORC = new State<>("CommandBasicStructOrc", Icon.class);
			public final static State<Icon> ICON_BUILD_UNDEAD = new State<>("CommandBasicStructUndead", Icon.class);
			public final static State<Icon> ICON_CANCEL = new State<>("CommandCancel", Icon.class);
			public final static State<Icon> ICON_HARVEST = new State<>("CommandHarvest", Icon.class);
			public final static State<Icon> ICON_HOLD_POSITION = new State<>("CommandHoldPosition", Icon.class);
			public final static State<Icon> ICON_MOVE = new State<>("CommandMove", Icon.class);
			public final static State<Icon> ICON_NEW_SKILL = new State<>("CommandNewSkill", Icon.class);
			public final static State<Icon> ICON_PATROL = new State<>("CommandPatrol", Icon.class);
			public final static State<Icon> ICON_PICK_UP_ITEM = new State<>("CommandPickUpItem", Icon.class);
			public final static State<Icon> ICON_PURCHASE = new State<>("CommandPurchase", Icon.class);
			public final static State<Icon> ICON_RALLY = new State<>("CommandRally", Icon.class);
			public final static State<Icon> ICON_REPAIR = new State<>("CommandRepair", Icon.class);
			public final static State<Icon> ICON_RETURN_GOLD = new State<>("CommandReturnGold", Icon.class);
			public final static State<Icon> ICON_STOP = new State<>("CommandStop", Icon.class);

			public final static State<Icon> MODEL_AUTOCAST = new State<>("CommandButtonAutocast", Icon.class);
			public final static State<Icon> MODEL_COOLDOWN = new State<>("CommandButtonCooldown", Icon.class);

			public final static State<Icon> MODEL_CURSOR = new State<>("Cursor", Icon.class);
			public final static State<Icon> MODEL_MAP_LOAD_BACKGOUND = new State<>("MapLoadBg", Icon.class);
			public final static State<Icon> MODEL_MAP_LOAD_BUTTON = new State<>("MapLoadBtn", Icon.class);
			public final static State<Icon> MODEL_SELECTION_CIRCLE = new State<>("SelectionCircle", Icon.class);
			public final static State<Icon> MODEL_SELECTION_CIRCLE_PRE = new State<>("PreSelectionCircle", Icon.class);
			public final static State<Icon> MODEL_RALLY = new State<>("RallyIndicatorDst", Icon.class);
			public final static State<Icon> MODEL_TARGET_POINT_CONFIRM = new State<>("TargetPointConfirm", Icon.class);
			public final static State<Icon> MODEL_TARGET_SELECT_PRE = new State<>("TargetPreSelect", Icon.class);
			public final static State<Icon> MODEL_TARGET_UNIT_CONFIRM = new State<>("TargetUnitConfirm", Icon.class);
			public final static State<Icon> MODEL_WAYPOINT = new State<>("WaypointIndicator", Icon.class);

			public final static State<Model> MODEL_CONSOLE_EXP_BAR = new State<>("XpBarConsole", Model.class);
			public final static State<Model> MODEL_CONSOLE_LIFE_BAR = new State<>("HpBarConsole", Model.class);
			public final static State<Model> MODEL_CONSOLE_LIFE_BAR_SMALL = new State<>("HpBarConsoleSmall", Model.class);
			public final static State<Model> MODEL_CONSOLE_MANA_BAR = new State<>("ManaBarConsole", Model.class);
			public final static State<Model> MODEL_CONSOLE_MANA_BAR_SMALL = new State<>("ManaBarConsoleSmall", Model.class);
			public final static State<Model> MODEL_CONSOLE_PROGRESS_BAR = new State<>("ProgressBarConsole", Model.class);

			public final static State<Model> MODEL_RESOURCE_BAR = new State<>("ResourceBar", Model.class);

			public final static State<Model> MODEL_BUILD_TIME = new State<>("BuildTimeIndicator", Model.class);

			public final static State<Model> MODEL_ALLY_DIALOG_BACKGROUND = new State<>("AllyDialogBackground", Model.class);
			public final static State<Model> MODEL_ALLY_DIALOG_BUTTON = new State<>("AllyDialogButton", Model.class);
			public final static State<Model> MODEL_ALLY_DIALOG_CHECKBOX = new State<>("AllyDialogCheckbox", Model.class);

			public final static State<Model> MODEL_TIME_CLOCK = new State<>("TimeOfDayIndicator", Model.class);

			public final static State<Model> MODEL_LOADING_MELEE_BACKGROUND = new State<>("LoadingMeleeBackground", Model.class);
			public final static State<Model> MODEL_PROGRESS_BAR = new State<>("LoadingProgressBar", Model.class);

			public final static State<Model> MODEL_SCORESCREEN_DEFEAT = new State<>("ScoreScreenDefeat", Model.class);
			public final static State<Model> MODEL_SCORESCREEN_VICTORY = new State<>("ScoreScreenVictory_V1", Model.class);

			public final static State<Model> MODEL_COMMAND_BTN = new State<>("CommandButtonMDL", Model.class);

			public final static State<Model> MODEL_BOUNTY = new State<>("BountyArt", Model.class);
			public final static State<Model> MODEL_HERO_BAR_POINT = new State<>("HeroBarPointModel", Model.class);

			public final static State<Icon> ICON_CONSOLE_BACKGROUND = new State<>("ConsoleBackground", Icon.class);
			public final static State<Icon> ICON_CONSOLE_INVENTORY = new State<>("ConsoleInventoryCoverTexture", Icon.class);
			public final static State<Icon> ICON_CONSOLE_INVENTORY_NONE = new State<>("ConsoleInventoryNoCapacity", Icon.class);
			public final static State<Icon> ICON_CURSOR_PLACEMENT = new State<>("PlacementCursor", Icon.class);

			public final static State<Icon> ICON_CONSOLE_EXP_BAR = new State<>("SimpleXpBarConsole", Icon.class);
			public final static State<Icon> ICON_CONSOLE_EXP_BAR_BORDER = new State<>("SimpleXpBarBorder", Icon.class);
			public final static State<Icon> ICON_CONSOLE_LIFE_BAR = new State<>("SimpleHpBarConsole", Icon.class);
			public final static State<Icon> ICON_CONSOLE_LIFE_BAR_SMALL = new State<>("SimpleHpBarConsoleSmall", Icon.class);
			public final static State<Icon> ICON_CONSOLE_MANA_BAR = new State<>("SimpleManaBarConsole", Icon.class);
			public final static State<Icon> ICON_CONSOLE_MANA_BAR_SMALL = new State<>("SimpleManaBarConsoleSmall", Icon.class);
			public final static State<Icon> ICON_CONSOLE_PROGRESS_BAR = new State<>("SimpleProgressBarConsole", Icon.class);
			public final static State<Icon> ICON_CONSOLE_PROGRESS_BAR_BORDER = new State<>("SimpleProgressBarBorder", Icon.class);

			public final static State<Icon> ICON_SELECTED_SUBGROUP_HIGHLIGHT = new State<>("SelectedSubgroupHighlight", Icon.class);
			public final static State<Icon> ICON_BUILD_TIME_INDICATOR = new State<>("SimpleBuildTimeIndicator", Icon.class);
			public final static State<Icon> ICON_BUILD_TIME_INDICATOR_BORDER = new State<>("SimpleBuildTimeIndicatorBorder", Icon.class);

			public final static State<Icon> ICON_BUILD_QUEUE_BACKDROP = new State<>("BuildQueueBackdrop", Icon.class);
			public final static State<Icon> ICON_CARGO_BACKDROP = new State<>("CargoBackdrop", Icon.class);
			public final static State<Icon> ICON_OCCUPIED_BACKDROP = new State<>("OccupBackdrop", Icon.class);
			public final static State<Icon> ICON_OCCUPIED_BACKGROUND = new State<>("OccupBackground", Icon.class);
			public final static State<Icon> ICON_OCCUPIED_BORDER = new State<>("OccupBorder", Icon.class);

			public final static State<Icon> ICON_REPLAY_CHECKBOX_NORMAL = new State<>("ReplayCheckBoxNormal", Icon.class);
			public final static State<Icon> ICON_REPLAY_CHECKBOX_PRESSED = new State<>("ReplayCheckBoxPressed", Icon.class);
			public final static State<Icon> ICON_REPLAY_CHECKBOX_CHECKED = new State<>("ReplayCheckBoxCheck", Icon.class);
			public final static State<Icon> ICON_REPLAY_PANEL_BACKDROP = new State<>("ReplayPanelBackdrop", Icon.class);

			public final static State<Icon> ICON_CONSOLE_BUTTON_BACKGROUND = new State<>("ConsoleButtonBackground", Icon.class);
			public final static State<Icon> ICON_CONSOLE_BUTTON_BORDER = new State<>("ConsoleButtonBorder", Icon.class);
			public final static State<Icon> ICON_CONSOLE_BUTTON_PRESSED_BACKGROUND = new State<>("ConsoleButtonPushedBackground", Icon.class);
			public final static State<Icon> ICON_CONSOLE_BUTTON_PRESSED_BORDER = new State<>("ConsoleButtonPushedBorder", Icon.class);
			public final static State<Icon> ICON_CONSOLE_BUTTON_DISABLED_BACKGROUND = new State<>("ConsoleButtonDisabledBackground", Icon.class);
			public final static State<Icon> ICON_CONSOLE_BUTTON_DISABLED_BORDER = new State<>("ConsoleButtonDisabledBorder", Icon.class);

			public final static State<Icon> ICON_CONSOLE_BUTTON_MOUSE_OVER_HIGHLIGHT = new State<>("ConsoleButtonMouseOverHighlight", Icon.class);

			public final static State<Icon> ICON_TOOLTIP_BACKGROUND = new State<>("ToolTipBackground", Icon.class);
			public final static State<Icon> ICON_TOOLTIP_BORDER = new State<>("ToolTipBorder", Icon.class);
			public final static State<Icon> ICON_TOOLTIP_GOLD = new State<>("ToolTipGoldIcon", Icon.class);
			public final static State<Icon> ICON_TOOLTIP_LUMBER = new State<>("ToolTipLumberIcon", Icon.class);
			public final static State<Icon> ICON_TOOLTIP_MANA = new State<>("ToolTipManaIcon", Icon.class);
			public final static State<Icon> ICON_TOOLTIP_STONES = new State<>("ToolTipStonesIcon", Icon.class);
			public final static State<Icon> ICON_TOOLTIP_SUPPLY = new State<>("ToolTipSupplyIcon", Icon.class);

			public final static State<Icon> ICON_HERO_BAR_BORDER = new State<>("HeroBarBorder", Icon.class);
			public final static State<Icon> ICON_GROUP_BUTTON_BORDER = new State<>("GroupButtonBorder", Icon.class);
			public final static State<Icon> ICON_UPPER_MENU_BUTTON = new State<>("UpperMenuButtonTexture", Icon.class);
			public final static State<Icon> ICON_CINEMATIC_BORDER = new State<>("CinematicBorder", Icon.class);
			public final static State<Icon> ICON_QUEST_DIALOG_BACKGROUND = new State<>("QuestDialogBackground", Icon.class);
			public final static State<Icon> ICON_QUEST_DIALOG_BORDER = new State<>("QuestDialogBorder", Icon.class);
			public final static State<Icon> ICON_QUEST_DIALOG_BUTTON_MOUSE_OVER_HIGHLIGHT = new State<>("QuestDialogButtonMouseOverHighlight", Icon.class);
			public final static State<Icon> ICON_QUEST_DIALOG_NORMAL_BACKGROUND = new State<>("QuestDialogNormalBackground", Icon.class);
			public final static State<Icon> ICON_QUEST_DIALOG_NORMAL_BORDER = new State<>("QuestDialogNormalBorder", Icon.class);
			public final static State<Icon> ICON_QUEST_DIALOG_COMPLETED_BACKGROUND = new State<>("QuestDialogCompletedBackground", Icon.class);
			public final static State<Icon> ICON_QUEST_DIALOG_COMPLETED_BORDER = new State<>("QuestDialogCompletedBorder", Icon.class);
			public final static State<Icon> ICON_QUEST_DIALOG_COMPLETED_BUTTON_MOUSE_OVER_HIGHLIGHT = new State<>("QuestDialogCompletedMouseOverHighlight", Icon.class);
			public final static State<Icon> ICON_QUEST_DIALOG_FAILED_BACKGROUND = new State<>("QuestDialogFailedBackground", Icon.class);
			public final static State<Icon> ICON_QUEST_DIALOG_FAILED_BORDER = new State<>("QuestDialogFailedBorder", Icon.class);
			public final static State<Icon> ICON_QUEST_DIALOG_FAILED_BUTTON_MOUSE_OVER_HIGHLIGHT = new State<>("QuestDialogFailedMouseOverHighlight", Icon.class);

			public final static State<Icon> ICON_ESC_MENU_BACKGROUND = new State<>("EscMenuBackground", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_BORDER = new State<>("EscMenuBorder", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_BUTTON_BACKGROUND = new State<>("EscMenuButtonBackground", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_BUTTON_BORDER = new State<>("EscMenuButtonBorder", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_BUTTON_PRESSED_BACKGROUND = new State<>("EscMenuButtonPushedBackground", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_BUTTON_PRESSED_BORDER = new State<>("EscMenuButtonPushedBorder", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_BUTTON_DISABLED_BACKGROUND = new State<>("EscMenuButtonDisabledBackground", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_BUTTON_DISABLED_BORDER = new State<>("EscMenuButtonDisabledBorder", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_BUTTON_DISABLED_PRESSED_BACKGROUND = new State<>("EscMenuButtonDisabledPushedBackground", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_BUTTON_DISABLED_PRESSED_BORDER = new State<>("EscMenuButtonDisabledPushedBorder", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_BUTTON_MOUSE_OVER_HIGHLIGHT = new State<>("EscMenuButtonMouseOverHighlight", Icon.class);

			public final static State<Icon> ICON_ESC_MENU_SLIDER_BACKGROUND = new State<>("EscMenuSliderBackground", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_SLIDER_BORDER = new State<>("EscMenuSliderBorder", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_SLIDER_DISABLED_BORDER = new State<>("EscMenuSliderDisabledBorder", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_SLIDER_THUMB_BUTTON = new State<>("EscMenuSliderThumbButton", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_SLIDER_THUMB_BUTTON_DISABLED = new State<>("EscMenuSliderDisabledThumbButton", Icon.class);

			public final static State<Icon> ICON_ESC_MENU_CHECKBOX_BACKGROUND = new State<>("EscMenuCheckBoxBackground", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_CHECKBOX_CHECK_HIGHLIGHT = new State<>("EscMenuCheckBoxCheckHighlight", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_CHECKBOX_DISABLED_BACKGROUND = new State<>("EscMenuDisabledCheckBoxBackground", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_CHECKBOX_DISABLED_CHECK_HIGHLIGHT = new State<>("EscMenuDisabledCheckBoxCheckHighlight", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_CHECKBOX_PRESSED_BACKGROUND = new State<>("EscMenuCheckBoxPushedBackground", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_POPUP_MENU_ARROW = new State<>("EscMenuPopupMenuArrow", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_POPUP_MENU_MENU_BACKGROUND = new State<>("EscMenuPopupMenuMenuBackground", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_RADIO_BUTTON_BACKGROUND = new State<>("EscMenuRadioButtonBackground", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_RADIO_BUTTON_DISABLED_BACKGROUND = new State<>("EscMenuRadioButtonDisabledBackground", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_RADIO_BUTTON_DISABLED_SELECTED_HIGHLIGHT = new State<>("EscMenuDisabledRadioButtonSelectedHighlight", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_RADIO_BUTTON_PRESSED_BACKGROUND = new State<>("EscMenuRadioButtonPushedBackground", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_RADIO_BUTTON_SELECTED_HIGHLIGHT = new State<>("EscMenuRadioButtonSelectedHighlight", Icon.class);

			public final static State<Icon> ICON_ESC_MENU_BACKGROUND_BLANK = new State<>("EscMenuBlankBackground", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_EDIT_BOX_BACKGROUND = new State<>("EscMenuEditBoxBackground", Icon.class);
			public final static State<Icon> ICON_ESC_MENU_EDIT_BOX_BORDER = new State<>("EscMenuEditBoxBorder", Icon.class);

			public final static State<Icon> CONSOLE_TEX1 = new State<>("ConsoleTexture01", Icon.class);
			public final static State<Icon> CONSOLE_TEX2 = new State<>("ConsoleTexture02", Icon.class);
			public final static State<Icon> CONSOLE_TEX3 = new State<>("ConsoleTexture03", Icon.class);
			public final static State<Icon> CONSOLE_TEX4 = new State<>("ConsoleTexture04", Icon.class);

			public final static State<Icon> ICON_UNIT_TIP_BACKGROUND = new State<>("UnitTipBackground", Icon.class);

			public final static State<Icon> ICON_MULTIBOARD_BACKGROUND = new State<>("MultiboardBackground", Icon.class);
			public final static State<Icon> ICON_MULTIBOARD_BORDER = new State<>("MultiboardBorder", Icon.class);
			public final static State<Icon> ICON_MULTIBOARD_ICON_BORDER = new State<>("MultiboardIconBorder", Icon.class);
			public final static State<Icon> ICON_MULTIBOARD_ICON_DEFAULT = new State<>("MultiboardDefaultIcon", Icon.class);
			public final static State<Icon> ICON_MULTIBOARD_MAXIMIZE_BUTTON_ENABLED = new State<>("MultiboardMaximizeButtonEnabled", Icon.class);
			public final static State<Icon> ICON_MULTIBOARD_MAXIMIZE_BUTTON_DISABLED = new State<>("MultiboardMaximizeButtonDisabled", Icon.class);
			public final static State<Icon> ICON_MULTIBOARD_MAXIMIZE_BUTTON_PRESSED = new State<>("MultiboardMaximizeButtonPushed", Icon.class);
			public final static State<Icon> ICON_MULTIBOARD_MINIMIZE_BUTTON_ENABLED = new State<>("MultiboardMinimizeButtonEnabled", Icon.class);
			public final static State<Icon> ICON_MULTIBOARD_MINIMIZE_BUTTON_DISABLED = new State<>("MultiboardMinimizeButtonDisabled", Icon.class);
			public final static State<Icon> ICON_MULTIBOARD_MINIMIZE_BUTTON_PRESSED = new State<>("MultiboardMinimizeButtonPushed", Icon.class);

			public final static State<Icon> ICON_SCORESCREEN_BANNER_DEFEAT = new State<>("ScoreScreenBannerLoss", Icon.class);
			public final static State<Icon> ICON_SCORESCREEN_BANNER_VICTORY = new State<>("ScoreScreenBannerWin", Icon.class);

			public final static State<SoundLabel> SOUND_CANNOT_PLACE = new State<>("CantPlaceSound", SoundLabel.class);
			public final static State<SoundLabel> SOUND_CONSTRUCTING = new State<>("ConstructingBuilding", SoundLabel.class);
			public final static State<SoundLabel> SOUND_GOLD_MINE_COLLAPSED = new State<>("GoldMineCollapseSound", SoundLabel.class);
			public final static State<SoundLabel> SOUND_GOLD_MINE_LOW = new State<>("GoldMineLowSound", SoundLabel.class);
			public final static State<SoundLabel> SOUND_HERO_DEAD = new State<>("HeroDiesSound", SoundLabel.class);
			public final static State<SoundLabel> SOUND_HERO_DEAD_ALLY = new State<>("AllyHeroDiesSound", SoundLabel.class);
			public final static State<SoundLabel> SOUND_INV_FULL = new State<>("InventoryFullSound", SoundLabel.class);
			public final static State<SoundLabel> SOUND_JOB_DONE = new State<>("JobDoneSound", SoundLabel.class);
			public final static State<SoundLabel> SOUND_NO_GOLD = new State<>("NoGoldSound", SoundLabel.class);
			public final static State<SoundLabel> SOUND_NO_LUMBER = new State<>("NoLumberSound", SoundLabel.class);
			public final static State<SoundLabel> SOUND_NO_MANA = new State<>("NoManaSound", SoundLabel.class);
			public final static State<SoundLabel> SOUND_NO_SUPPLY = new State<>("NoFoodSound", SoundLabel.class);
			public final static State<SoundLabel> SOUND_PLACE_STRUCTURE = new State<>("PlaceBuilding", SoundLabel.class);
			public final static State<SoundLabel> SOUND_RESEARCH_COMPLETE = new State<>("ResearchComplete", SoundLabel.class);
			public final static State<SoundLabel> SOUND_UNDER_ATTACK = new State<>("UnderAttackSound", SoundLabel.class);
			public final static State<SoundLabel> SOUND_UNDER_ATTACK_ALLY = new State<>("AllyUnderAttackSound", SoundLabel.class);
			public final static State<SoundLabel> SOUND_UNDER_ATTACK_TOWN = new State<>("TownAttackSound", SoundLabel.class);
			public final static State<SoundLabel> SOUND_UNDER_ATTACK_TOWN_ALLY = new State<>("AllyTownUnderAttackSound", SoundLabel.class);
			public final static State<SoundLabel> SOUND_UPGRADE_COMPLETE = new State<>("UpgradeComplete", SoundLabel.class);

			public final static State<MusicFile> MUSIC_DEFEAT = new State<>("DefeatMusic_V1", MusicFile.class);
			public final static State<MusicFile> MUSIC_VICTORY = new State<>("VictoryMusic_V1", MusicFile.class);

			public final static State<War3String> PATH_COMMAND_BUTTON_DISABLED = new State<>("CommandButtonDisabledArtPath", War3String.class);
			public final static State<War3String> PATH_TEAM_COLOR = new State<>("TeamColor", War3String.class);
			public final static State<War3String> PATH_TEAM_GLOW = new State<>("TeamGlow", War3String.class);
			
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
	}
	
	@Override
	public void read(@Nonnull File file) throws IOException {
		super.read(file);
	}
	
	@Override
	public void write(@Nonnull File file) throws IOException {
		super.write(file);
	}
	
	public SkinTXT() {
		
	}
}
