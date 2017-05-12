package net.moonlightflower.wc3libs.txt.app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.Icon;
import net.moonlightflower.wc3libs.dataTypes.app.Model;
import net.moonlightflower.wc3libs.dataTypes.app.MusicFile;
import net.moonlightflower.wc3libs.dataTypes.app.SoundLabel;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.txt.TXT;
import net.moonlightflower.wc3libs.txt.TXTSectionId;
import net.moonlightflower.wc3libs.txt.TXTState;

public class SkinTXT extends TXT {
	public final static File GAME_PATH = new File("war3mapSkin.txt");
	
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

		public final static State<Icon> DEFAULT_BTN = new State<>(Section.CUSTOM_SKIN, "DefaultButton", Icon.class);
		public final static State<Icon> IDLE_PEON = new State<>(Section.CUSTOM_SKIN, "IdlePeon", Icon.class);
		public final static State<Icon> ICON_ARMOR_DIVINE = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorDivineNeutral", Icon.class);
		public final static State<Icon> ICON_ARMOR_DIVINE_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorDivine", Icon.class);
		public final static State<Icon> ICON_ARMOR_FORT = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorFortNeutral", Icon.class);
		public final static State<Icon> ICON_ARMOR_FORT_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorFort", Icon.class);
		public final static State<Icon> ICON_ARMOR_HERO = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorHeroNeutral", Icon.class);
		public final static State<Icon> ICON_ARMOR_HERO_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorHero", Icon.class);
		public final static State<Icon> ICON_ARMOR_LARGE = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorLargeNeutral", Icon.class);
		public final static State<Icon> ICON_ARMOR_LARGE_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorLarge", Icon.class);
		public final static State<Icon> ICON_ARMOR_MEDIUM = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorMediumNeutral", Icon.class);
		public final static State<Icon> ICON_ARMOR_MEDIUM_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorMedium", Icon.class);
		public final static State<Icon> ICON_ARMOR_NONE = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorNoneNeutral", Icon.class);
		public final static State<Icon> ICON_ARMOR_NONE_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorNone", Icon.class);
		public final static State<Icon> ICON_ARMOR_NORMAL = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorNormalNeutral", Icon.class);
		public final static State<Icon> ICON_ARMOR_NORMAL_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorNormal", Icon.class);
		public final static State<Icon> ICON_ARMOR_SMALL = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorSmallNeutral", Icon.class);
		public final static State<Icon> ICON_ARMOR_SMALL_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorSmall", Icon.class);
		public final static State<Icon> ICON_ARMOR_UNKNOWN = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorUnknownNeutral", Icon.class);
		public final static State<Icon> ICON_ARMOR_UNKNOWN_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconArmorUnknown", Icon.class);
		public final static State<Icon> ICON_ATTACK_CHAOS = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageChaosNeutral", Icon.class);
		public final static State<Icon> ICON_ATTACK_CHAOS_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageChaos", Icon.class);
		public final static State<Icon> ICON_ATTACK_HERO = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageHeroNeutral", Icon.class);
		public final static State<Icon> ICON_ATTACK_HERO_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageHero", Icon.class);
		public final static State<Icon> ICON_ATTACK_MAGIC = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageMagicNeutral", Icon.class);
		public final static State<Icon> ICON_ATTACK_MAGIC_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageMagic", Icon.class);
		public final static State<Icon> ICON_ATTACK_MELEE = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageMeleeNeutral", Icon.class);
		public final static State<Icon> ICON_ATTACK_MELEE_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageMelee", Icon.class);
		public final static State<Icon> ICON_ATTACK_NORMAL = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageNormalNeutral", Icon.class);
		public final static State<Icon> ICON_ATTACK_NORMAL_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageNormal", Icon.class);
		public final static State<Icon> ICON_ATTACK_PIERCE = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamagePierceNeutral", Icon.class);
		public final static State<Icon> ICON_ATTACK_PIERCE_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamagePierce", Icon.class);
		public final static State<Icon> ICON_ATTACK_SIEGE = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageSiegeNeutral", Icon.class);
		public final static State<Icon> ICON_ATTACK_SIEGE_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageSiege", Icon.class);
		public final static State<Icon> ICON_ATTACK_UNKNOWN = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageUnknownNeutral", Icon.class);
		public final static State<Icon> ICON_ATTACK_UNKNOWN_UP = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconDamageUnknown", Icon.class);
		public final static State<Icon> ICON_GOLD = new State<>(Section.CUSTOM_SKIN, "GoldIcon", Icon.class);
		public final static State<Icon> ICON_GOLD_INFO = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconGold", Icon.class);
		public final static State<Icon> ICON_HERO_AGI = new State<>(Section.CUSTOM_SKIN, "HeroAgilityIcon", Icon.class);
		public final static State<Icon> ICON_HERO_AGI_INFO = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconHeroIconAGI", Icon.class);
		public final static State<Icon> ICON_HERO_INT = new State<>(Section.CUSTOM_SKIN, "HeroIntelligenceIcon", Icon.class);
		public final static State<Icon> ICON_HERO_INT_INFO = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconHeroIconINT", Icon.class);
		public final static State<Icon> ICON_HERO_STR = new State<>(Section.CUSTOM_SKIN, "HeroStrengthIcon", Icon.class);
		public final static State<Icon> ICON_HERO_STR_INFO = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconHeroIconSTR", Icon.class);
		public final static State<Icon> ICON_LUMBER = new State<>(Section.CUSTOM_SKIN, "LumberIcon", Icon.class);
		public final static State<Icon> ICON_UPKEEP = new State<>(Section.CUSTOM_SKIN, "UpkeepIcon", Icon.class);
		public final static State<Icon> ICON_REPLAY_LOOP = new State<>(Section.CUSTOM_SKIN, "ReplayLoopIcon", Icon.class);
		public final static State<Icon> ICON_REPLAY_LOOP_DISABLED = new State<>(Section.CUSTOM_SKIN, "ReplayLoopIconDisabled", Icon.class);
		public final static State<Icon> ICON_REPLAY_PAUSE = new State<>(Section.CUSTOM_SKIN, "ReplayPauseIcon", Icon.class);
		public final static State<Icon> ICON_REPLAY_PAUSE_DISABLED = new State<>(Section.CUSTOM_SKIN, "ReplayPauseIconDisabled", Icon.class);
		public final static State<Icon> ICON_REPLAY_RESTART = new State<>(Section.CUSTOM_SKIN, "ReplayRestartIcon", Icon.class);
		public final static State<Icon> ICON_REPLAY_RESTART_DISABLED = new State<>(Section.CUSTOM_SKIN, "ReplayRestartIconDisabled", Icon.class);
		public final static State<Icon> ICON_REPLAY_SPEED_DOWN = new State<>(Section.CUSTOM_SKIN, "ReplaySpeedDownIcon", Icon.class);
		public final static State<Icon> ICON_REPLAY_SPEED_DOWN_DISABLED = new State<>(Section.CUSTOM_SKIN, "ReplaySpeedDownIconDisabled", Icon.class);
		public final static State<Icon> ICON_REPLAY_SPEED_UP = new State<>(Section.CUSTOM_SKIN, "ReplaySpeedUpIcon", Icon.class);
		public final static State<Icon> ICON_REPLAY_SPEED_UP_DISABLED = new State<>(Section.CUSTOM_SKIN, "ReplaySpeedUpIconDisabled", Icon.class);
		public final static State<Icon> ICON_SUPPLY = new State<>(Section.CUSTOM_SKIN, "SupplyIcon", Icon.class);
		public final static State<Icon> ICON_SUPPLY_INFO = new State<>(Section.CUSTOM_SKIN, "InfoPanelIconFood", Icon.class);
		public final static State<Icon> ICON_UPGRADE_ARMOR = new State<>(Section.CUSTOM_SKIN, "UpgradeArmorIcon", Icon.class);
		public final static State<Icon> ICON_UPGRADE_ARTILLERY = new State<>(Section.CUSTOM_SKIN, "UpgradeArtilleryIcon", Icon.class);
		public final static State<Icon> ICON_UPGRADE_MELEE = new State<>(Section.CUSTOM_SKIN, "UpgradeMeleeIcon", Icon.class);
		public final static State<Icon> ICON_UPGRADE_RANGED = new State<>(Section.CUSTOM_SKIN, "UpgradeRangedIcon", Icon.class);
		
		public final static State<Icon> ICON_BTN_HERO_GLOW = new State<>(Section.CUSTOM_SKIN, "CommandButtonHeroGlow", Icon.class);
		public final static State<Icon> ICON_BTN_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "CommandButtonActiveHighlight", Icon.class);
		public final static State<Icon> ICON_BTN_NUM_OVERLAY = new State<>(Section.CUSTOM_SKIN, "CommandButtonNumberOverlay", Icon.class);

		public final static State<Icon> ICON_MINIMAP_ALLY_ACTIVE_DISABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapAllyButtonActiveDisabled", Icon.class);
		public final static State<Icon> ICON_MINIMAP_ALLY_ACTIVE_ENABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapAllyButtonActiveEnabled", Icon.class);
		public final static State<Icon> ICON_MINIMAP_ALLY_ACTIVE_PRESSED = new State<>(Section.CUSTOM_SKIN, "MiniMapAllyButtonActivePushed", Icon.class);
		public final static State<Icon> ICON_MINIMAP_ALLY_INACTIVE_DISABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapAllyButtonInactiveDisabled", Icon.class);
		public final static State<Icon> ICON_MINIMAP_ALLY_INACTIVE_ENABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapAllyButtonInactiveEnabled", Icon.class);
		public final static State<Icon> ICON_MINIMAP_ALLY_INACTIVE_PRESSED = new State<>(Section.CUSTOM_SKIN, "MiniMapAllyButtonInactivePushed", Icon.class);
		public final static State<Icon> ICON_MINIMAP_ALLY_OFF_DISABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapAllyButtonOffDisabled", Icon.class);
		public final static State<Icon> ICON_MINIMAP_ALLY_OFF_ENABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapAllyButtonOffEnabled", Icon.class);
		public final static State<Icon> ICON_MINIMAP_ALLY_OFF_PRESSED = new State<>(Section.CUSTOM_SKIN, "MiniMapAllyButtonOffPushed", Icon.class);
		public final static State<Icon> ICON_MINIMAP_CREEP_ACTIVE_DISABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapCreepButtonActiveDisabled", Icon.class);
		public final static State<Icon> ICON_MINIMAP_CREEP_ACTIVE_ENABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapCreepButtonActiveEnabled", Icon.class);
		public final static State<Icon> ICON_MINIMAP_CREEP_ACTIVE_PRESSED = new State<>(Section.CUSTOM_SKIN, "MiniMapCreepButtonActivePushed", Icon.class);
		public final static State<Icon> ICON_MINIMAP_CREEP_INACTIVE_DISABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapCreepButtonInactiveDisabled", Icon.class);
		public final static State<Icon> ICON_MINIMAP_CREEP_INACTIVE_ENABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapCreepButtonInactiveEnabled", Icon.class);
		public final static State<Icon> ICON_MINIMAP_CREEP_INACTIVE_PRESSED = new State<>(Section.CUSTOM_SKIN, "MiniMapCreepButtonInactivePushed", Icon.class);
		public final static State<Icon> ICON_MINIMAP_FORMATION_OFF_DISABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapFormationOffButtonDisabled", Icon.class);
		public final static State<Icon> ICON_MINIMAP_FORMATION_OFF_ENABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapFormationOffButtonEnabled", Icon.class);
		public final static State<Icon> ICON_MINIMAP_FORMATION_OFF_PRESSED = new State<>(Section.CUSTOM_SKIN, "MiniMapFormationOffButtonEnabledPushed", Icon.class);
		public final static State<Icon> ICON_MINIMAP_FORMATION_ON_DISABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapFormationOnButtonDisabled", Icon.class);
		public final static State<Icon> ICON_MINIMAP_FORMATION_ON_ENABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapFormationOnButtonEnabled", Icon.class);
		public final static State<Icon> ICON_MINIMAP_FORMATION_ON_PRESSED = new State<>(Section.CUSTOM_SKIN, "MiniMapFormationOnButtonEnabledPushed", Icon.class);
		public final static State<Icon> ICON_MINIMAP_SIGNAL_DISABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapSignalButtonDisabled", Icon.class);
		public final static State<Icon> ICON_MINIMAP_SIGNAL_ENABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapSignalButtonEnabled", Icon.class);
		public final static State<Icon> ICON_MINIMAP_SIGNAL_PRESSED = new State<>(Section.CUSTOM_SKIN, "MiniMapSignalButtonPushed", Icon.class);
		public final static State<Icon> ICON_MINIMAP_TERRAIN_ACTIVE_DISABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapTerrainButtonActiveDisabled", Icon.class);
		public final static State<Icon> ICON_MINIMAP_TERRAIN_ACTIVE_ENABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapTerrainButtonActiveEnabled", Icon.class);
		public final static State<Icon> ICON_MINIMAP_TERRAIN_ACTIVE_PRESSED = new State<>(Section.CUSTOM_SKIN, "MiniMapTerrainButtonActivePushed", Icon.class);
		public final static State<Icon> ICON_MINIMAP_TERRAIN_INACTIVE_DISABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapTerrainButtonInactiveDisabled", Icon.class);
		public final static State<Icon> ICON_MINIMAP_TERRAIN_INACTIVE_ENABLED = new State<>(Section.CUSTOM_SKIN, "MiniMapTerrainButtonInactiveEnabled", Icon.class);
		public final static State<Icon> ICON_MINIMAP_TERRAIN_INACTIVE_PRESSED = new State<>(Section.CUSTOM_SKIN, "MiniMapTerrainButtonInactivePushed", Icon.class);
		
		public final static State<Icon> ICON_MINIMAP_CIRCLE_OF_POWER = new State<>(Section.CUSTOM_SKIN, "MinimapCopTexture", Icon.class);
		public final static State<Icon> ICON_MINIMAP_CREEP_CAMP = new State<>(Section.CUSTOM_SKIN, "MinimapCreepCampTexture", Icon.class);
		public final static State<Icon> ICON_MINIMAP_CREEP_CAMP_LARGE = new State<>(Section.CUSTOM_SKIN, "MinimapToughCreepCampTexture", Icon.class);
		public final static State<Icon> ICON_MINIMAP_HERO = new State<>(Section.CUSTOM_SKIN, "MinimapHeroTexture", Icon.class);
		public final static State<Icon> ICON_MINIMAP_NEUTRAL = new State<>(Section.CUSTOM_SKIN, "MinimapNeutralTexture", Icon.class);
		public final static State<Icon> ICON_MINIMAP_RESOURCE = new State<>(Section.CUSTOM_SKIN, "MinimapResourceTexture", Icon.class);
		public final static State<Icon> ICON_MINIMAP_RESOURCE_ENTANGLED = new State<>(Section.CUSTOM_SKIN, "MinimapEntangledResourceTexture", Icon.class);
		public final static State<Icon> ICON_MINIMAP_RESOURCE_HAUNTED = new State<>(Section.CUSTOM_SKIN, "MinimapHauntedResourceTexture", Icon.class);
		
		public final static State<Model> MODEL_MINIMAP_CREEP_CAMP = new State<>(Section.CUSTOM_SKIN, "MinimapCreepCamp", Model.class);
		public final static State<Model> MODEL_MINIMAP_CREEP_CAMP_LARGE = new State<>(Section.CUSTOM_SKIN, "MinimapCreepCampTough", Model.class);
		public final static State<Model> MODEL_MINIMAP_HERO = new State<>(Section.CUSTOM_SKIN, "MinimapHero", Model.class);
		public final static State<Model> MODEL_MINIMAP_INDICATOR = new State<>(Section.CUSTOM_SKIN, "MinimapIndicator", Model.class);
		public final static State<Model> MODEL_MINIMAP_ITEM = new State<>(Section.CUSTOM_SKIN, "MinimapItem", Model.class);
		public final static State<Model> MODEL_MINIMAP_NEUTRAL = new State<>(Section.CUSTOM_SKIN, "MinimapNeutral", Model.class);
		public final static State<Model> MODEL_MINIMAP_RESOURCE = new State<>(Section.CUSTOM_SKIN, "MinimapResource", Model.class);
		public final static State<Model> MODEL_MINIMAP_SPRITE = new State<>(Section.CUSTOM_SKIN, "MinimapSprite", Model.class);
		public final static State<Model> MODEL_MINIMAP_WAYPOINT = new State<>(Section.CUSTOM_SKIN, "MinimapWaypoint", Model.class);
		
		public final static State<Icon> ICON_QUEST_UNDISCOVERED = new State<>(Section.CUSTOM_SKIN, "UndiscoveredQuestIcon", Icon.class);
		
		public final static State<Model> MODEL_QUEST_CHANGED_PARTICLES = new State<>(Section.CUSTOM_SKIN, "QuestChangedParticles", Model.class);
		
		public final static State<Icon> ICON_SCORESCREEN_HUMAN = new State<>(Section.CUSTOM_SKIN, "ScoreScreenPlayerHuman", Icon.class);
		public final static State<Icon> ICON_SCORESCREEN_NIGHT_ELF = new State<>(Section.CUSTOM_SKIN, "ScoreScreenPlayerNightElf", Icon.class);
		public final static State<Icon> ICON_SCORESCREEN_ORC = new State<>(Section.CUSTOM_SKIN, "ScoreScreenPlayerOrc", Icon.class);
		public final static State<Icon> ICON_SCORESCREEN_UNDEAD = new State<>(Section.CUSTOM_SKIN, "ScoreScreenPlayerUndead", Icon.class);

		public final static State<Icon> ICON_ALLIANCE_GOLD = new State<>(Section.CUSTOM_SKIN, "AllianceGold", Icon.class);
		public final static State<Icon> ICON_ALLIANCE_GOLD_DISABLED = new State<>(Section.CUSTOM_SKIN, "AllianceGoldDisabled", Icon.class);
		public final static State<Icon> ICON_ALLIANCE_LUMBER = new State<>(Section.CUSTOM_SKIN, "AllianceLumber", Icon.class);
		public final static State<Icon> ICON_ALLIANCE_LUMBER_DISABLED = new State<>(Section.CUSTOM_SKIN, "AllianceLumberDisabled", Icon.class);

		public final static State<Icon> ICON_ATTACK = new State<>(Section.CUSTOM_SKIN, "CommandAttack", Icon.class);
		public final static State<Icon> ICON_ATTACK_GROUND = new State<>(Section.CUSTOM_SKIN, "CommandAttackGround", Icon.class);
		public final static State<Icon> ICON_BUILD = new State<>(Section.CUSTOM_SKIN, "CommandBasicStruct", Icon.class);
		public final static State<Icon> ICON_BUILD_ADVANCED = new State<>(Section.CUSTOM_SKIN, "CommandAdvStruct", Icon.class);
		public final static State<Icon> ICON_BUILD_HUMAN = new State<>(Section.CUSTOM_SKIN, "CommandBasicStructHuman", Icon.class);
		public final static State<Icon> ICON_BUILD_NIGHT_ELF = new State<>(Section.CUSTOM_SKIN, "CommandBasicStructNightElf", Icon.class);
		public final static State<Icon> ICON_BUILD_ORC = new State<>(Section.CUSTOM_SKIN, "CommandBasicStructOrc", Icon.class);
		public final static State<Icon> ICON_BUILD_UNDEAD = new State<>(Section.CUSTOM_SKIN, "CommandBasicStructUndead", Icon.class);
		public final static State<Icon> ICON_CANCEL = new State<>(Section.CUSTOM_SKIN, "CommandCancel", Icon.class);
		public final static State<Icon> ICON_HARVEST = new State<>(Section.CUSTOM_SKIN, "CommandHarvest", Icon.class);
		public final static State<Icon> ICON_HOLD_POSITION = new State<>(Section.CUSTOM_SKIN, "CommandHoldPosition", Icon.class);
		public final static State<Icon> ICON_MOVE = new State<>(Section.CUSTOM_SKIN, "CommandMove", Icon.class);
		public final static State<Icon> ICON_NEW_SKILL = new State<>(Section.CUSTOM_SKIN, "CommandNewSkill", Icon.class);
		public final static State<Icon> ICON_PATROL = new State<>(Section.CUSTOM_SKIN, "CommandPatrol", Icon.class);
		public final static State<Icon> ICON_PICK_UP_ITEM = new State<>(Section.CUSTOM_SKIN, "CommandPickUpItem", Icon.class);
		public final static State<Icon> ICON_PURCHASE = new State<>(Section.CUSTOM_SKIN, "CommandPurchase", Icon.class);
		public final static State<Icon> ICON_RALLY = new State<>(Section.CUSTOM_SKIN, "CommandRally", Icon.class);
		public final static State<Icon> ICON_REPAIR = new State<>(Section.CUSTOM_SKIN, "CommandRepair", Icon.class);
		public final static State<Icon> ICON_RETURN_GOLD = new State<>(Section.CUSTOM_SKIN, "CommandReturnGold", Icon.class);
		public final static State<Icon> ICON_STOP = new State<>(Section.CUSTOM_SKIN, "CommandStop", Icon.class);
		
		public final static State<Icon> MODEL_AUTOCAST = new State<>(Section.CUSTOM_SKIN, "CommandButtonAutocast", Icon.class);
		public final static State<Icon> MODEL_COOLDOWN = new State<>(Section.CUSTOM_SKIN, "CommandButtonCooldown", Icon.class);
		
		public final static State<Icon> MODEL_CURSOR = new State<>(Section.CUSTOM_SKIN, "Cursor", Icon.class);
		public final static State<Icon> MODEL_MAP_LOAD_BACKGOUND = new State<>(Section.CUSTOM_SKIN, "MapLoadBg", Icon.class);
		public final static State<Icon> MODEL_MAP_LOAD_BUTTON = new State<>(Section.CUSTOM_SKIN, "MapLoadBtn", Icon.class);
		public final static State<Icon> MODEL_SELECTION_CIRCLE = new State<>(Section.CUSTOM_SKIN, "SelectionCircle", Icon.class);
		public final static State<Icon> MODEL_SELECTION_CIRCLE_PRE = new State<>(Section.CUSTOM_SKIN, "PreSelectionCircle", Icon.class);
		public final static State<Icon> MODEL_RALLY = new State<>(Section.CUSTOM_SKIN, "RallyIndicatorDst", Icon.class);
		public final static State<Icon> MODEL_TARGET_POINT_CONFIRM = new State<>(Section.CUSTOM_SKIN, "TargetPointConfirm", Icon.class);
		public final static State<Icon> MODEL_TARGET_SELECT_PRE = new State<>(Section.CUSTOM_SKIN, "TargetPreSelect", Icon.class);
		public final static State<Icon> MODEL_TARGET_UNIT_CONFIRM = new State<>(Section.CUSTOM_SKIN, "TargetUnitConfirm", Icon.class);
		public final static State<Icon> MODEL_WAYPOINT = new State<>(Section.CUSTOM_SKIN, "WaypointIndicator", Icon.class);
		
		public final static State<Model> MODEL_CONSOLE_EXP_BAR = new State<>(Section.CUSTOM_SKIN, "XpBarConsole", Model.class);
		public final static State<Model> MODEL_CONSOLE_LIFE_BAR = new State<>(Section.CUSTOM_SKIN, "HpBarConsole", Model.class);
		public final static State<Model> MODEL_CONSOLE_LIFE_BAR_SMALL = new State<>(Section.CUSTOM_SKIN, "HpBarConsoleSmall", Model.class);
		public final static State<Model> MODEL_CONSOLE_MANA_BAR = new State<>(Section.CUSTOM_SKIN, "ManaBarConsole", Model.class);
		public final static State<Model> MODEL_CONSOLE_MANA_BAR_SMALL = new State<>(Section.CUSTOM_SKIN, "ManaBarConsoleSmall", Model.class);
		public final static State<Model> MODEL_CONSOLE_PROGRESS_BAR = new State<>(Section.CUSTOM_SKIN, "ProgressBarConsole", Model.class);
		
		public final static State<Model> MODEL_RESOURCE_BAR = new State<>(Section.CUSTOM_SKIN, "ResourceBar", Model.class);
		
		public final static State<Model> MODEL_BUILD_TIME = new State<>(Section.CUSTOM_SKIN, "BuildTimeIndicator", Model.class);
		
		public final static State<Model> MODEL_ALLY_DIALOG_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "AllyDialogBackground", Model.class);
		public final static State<Model> MODEL_ALLY_DIALOG_BUTTON = new State<>(Section.CUSTOM_SKIN, "AllyDialogButton", Model.class);
		public final static State<Model> MODEL_ALLY_DIALOG_CHECKBOX = new State<>(Section.CUSTOM_SKIN, "AllyDialogCheckbox", Model.class);
		
		public final static State<Model> MODEL_TIME_CLOCK = new State<>(Section.CUSTOM_SKIN, "TimeOfDayIndicator", Model.class);
		
		public final static State<Model> MODEL_LOADING_MELEE_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "LoadingMeleeBackground", Model.class);
		public final static State<Model> MODEL_PROGRESS_BAR = new State<>(Section.CUSTOM_SKIN, "LoadingProgressBar", Model.class);
		
		public final static State<Model> MODEL_SCORESCREEN_DEFEAT = new State<>(Section.CUSTOM_SKIN, "ScoreScreenDefeat", Model.class);
		public final static State<Model> MODEL_SCORESCREEN_VICTORY = new State<>(Section.CUSTOM_SKIN, "ScoreScreenVictory_V1", Model.class);
		
		public final static State<Model> MODEL_COMMAND_BTN = new State<>(Section.CUSTOM_SKIN, "CommandButtonMDL", Model.class);
		
		public final static State<Model> MODEL_BOUNTY = new State<>(Section.CUSTOM_SKIN, "BountyArt", Model.class);
		public final static State<Model> MODEL_HERO_BAR_POINT = new State<>(Section.CUSTOM_SKIN, "HeroBarPointModel", Model.class);
		
		public final static State<Icon> ICON_CONSOLE_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "ConsoleBackground", Icon.class);
		public final static State<Icon> ICON_CONSOLE_INVENTORY = new State<>(Section.CUSTOM_SKIN, "ConsoleInventoryCoverTexture", Icon.class);
		public final static State<Icon> ICON_CONSOLE_INVENTORY_NONE = new State<>(Section.CUSTOM_SKIN, "ConsoleInventoryNoCapacity", Icon.class);
		public final static State<Icon> ICON_CURSOR_PLACEMENT = new State<>(Section.CUSTOM_SKIN, "PlacementCursor", Icon.class);
		
		public final static State<Icon> ICON_CONSOLE_EXP_BAR = new State<>(Section.CUSTOM_SKIN, "SimpleXpBarConsole", Icon.class);
		public final static State<Icon> ICON_CONSOLE_EXP_BAR_BORDER = new State<>(Section.CUSTOM_SKIN, "SimpleXpBarBorder", Icon.class);
		public final static State<Icon> ICON_CONSOLE_LIFE_BAR = new State<>(Section.CUSTOM_SKIN, "SimpleHpBarConsole", Icon.class);
		public final static State<Icon> ICON_CONSOLE_LIFE_BAR_SMALL = new State<>(Section.CUSTOM_SKIN, "SimpleHpBarConsoleSmall", Icon.class);
		public final static State<Icon> ICON_CONSOLE_MANA_BAR = new State<>(Section.CUSTOM_SKIN, "SimpleManaBarConsole", Icon.class);
		public final static State<Icon> ICON_CONSOLE_MANA_BAR_SMALL = new State<>(Section.CUSTOM_SKIN, "SimpleManaBarConsoleSmall", Icon.class);
		public final static State<Icon> ICON_CONSOLE_PROGRESS_BAR = new State<>(Section.CUSTOM_SKIN, "SimpleProgressBarConsole", Icon.class);
		public final static State<Icon> ICON_CONSOLE_PROGRESS_BAR_BORDER = new State<>(Section.CUSTOM_SKIN, "SimpleProgressBarBorder", Icon.class);
		
		public final static State<Icon> ICON_SELECTED_SUBGROUP_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "SelectedSubgroupHighlight", Icon.class);
		public final static State<Icon> ICON_BUILD_TIME_INDICATOR = new State<>(Section.CUSTOM_SKIN, "SimpleBuildTimeIndicator", Icon.class);
		public final static State<Icon> ICON_BUILD_TIME_INDICATOR_BORDER = new State<>(Section.CUSTOM_SKIN, "SimpleBuildTimeIndicatorBorder", Icon.class);
		
		public final static State<Icon> ICON_BUILD_QUEUE_BACKDROP = new State<>(Section.CUSTOM_SKIN, "BuildQueueBackdrop", Icon.class);
		public final static State<Icon> ICON_CARGO_BACKDROP = new State<>(Section.CUSTOM_SKIN, "CargoBackdrop", Icon.class);
		public final static State<Icon> ICON_OCCUPIED_BACKDROP = new State<>(Section.CUSTOM_SKIN, "OccupBackdrop", Icon.class);
		public final static State<Icon> ICON_OCCUPIED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "OccupBackground", Icon.class);
		public final static State<Icon> ICON_OCCUPIED_BORDER = new State<>(Section.CUSTOM_SKIN, "OccupBorder", Icon.class);
		
		public final static State<Icon> ICON_REPLAY_CHECKBOX_NORMAL = new State<>(Section.CUSTOM_SKIN, "ReplayCheckBoxNormal", Icon.class);
		public final static State<Icon> ICON_REPLAY_CHECKBOX_PRESSED = new State<>(Section.CUSTOM_SKIN, "ReplayCheckBoxPressed", Icon.class);
		public final static State<Icon> ICON_REPLAY_CHECKBOX_CHECKED = new State<>(Section.CUSTOM_SKIN, "ReplayCheckBoxCheck", Icon.class);
		public final static State<Icon> ICON_REPLAY_PANEL_BACKDROP = new State<>(Section.CUSTOM_SKIN, "ReplayPanelBackdrop", Icon.class);
		
		public final static State<Icon> ICON_CONSOLE_BUTTON_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "ConsoleButtonBackground", Icon.class);
		public final static State<Icon> ICON_CONSOLE_BUTTON_BORDER = new State<>(Section.CUSTOM_SKIN, "ConsoleButtonBorder", Icon.class);
		public final static State<Icon> ICON_CONSOLE_BUTTON_PRESSED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "ConsoleButtonPushedBackground", Icon.class);
		public final static State<Icon> ICON_CONSOLE_BUTTON_PRESSED_BORDER = new State<>(Section.CUSTOM_SKIN, "ConsoleButtonPushedBorder", Icon.class);
		public final static State<Icon> ICON_CONSOLE_BUTTON_DISABLED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "ConsoleButtonDisabledBackground", Icon.class);
		public final static State<Icon> ICON_CONSOLE_BUTTON_DISABLED_BORDER = new State<>(Section.CUSTOM_SKIN, "ConsoleButtonDisabledBorder", Icon.class);
		
		public final static State<Icon> ICON_CONSOLE_BUTTON_MOUSE_OVER_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "ConsoleButtonMouseOverHighlight", Icon.class);
		
		public final static State<Icon> ICON_TOOLTIP_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "ToolTipBackground", Icon.class);
		public final static State<Icon> ICON_TOOLTIP_BORDER = new State<>(Section.CUSTOM_SKIN, "ToolTipBorder", Icon.class);
		public final static State<Icon> ICON_TOOLTIP_GOLD = new State<>(Section.CUSTOM_SKIN, "ToolTipGoldIcon", Icon.class);
		public final static State<Icon> ICON_TOOLTIP_LUMBER = new State<>(Section.CUSTOM_SKIN, "ToolTipLumberIcon", Icon.class);
		public final static State<Icon> ICON_TOOLTIP_MANA = new State<>(Section.CUSTOM_SKIN, "ToolTipManaIcon", Icon.class);
		public final static State<Icon> ICON_TOOLTIP_STONES = new State<>(Section.CUSTOM_SKIN, "ToolTipStonesIcon", Icon.class);
		public final static State<Icon> ICON_TOOLTIP_SUPPLY = new State<>(Section.CUSTOM_SKIN, "ToolTipSupplyIcon", Icon.class);
		
		public final static State<Icon> ICON_HERO_BAR_BORDER = new State<>(Section.CUSTOM_SKIN, "HeroBarBorder", Icon.class);
		public final static State<Icon> ICON_GROUP_BUTTON_BORDER = new State<>(Section.CUSTOM_SKIN, "GroupButtonBorder", Icon.class);
		public final static State<Icon> ICON_UPPER_MENU_BUTTON = new State<>(Section.CUSTOM_SKIN, "UpperMenuButtonTexture", Icon.class);
		public final static State<Icon> ICON_CINEMATIC_BORDER = new State<>(Section.CUSTOM_SKIN, "CinematicBorder", Icon.class);
		public final static State<Icon> ICON_QUEST_DIALOG_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "QuestDialogBackground", Icon.class);
		public final static State<Icon> ICON_QUEST_DIALOG_BORDER = new State<>(Section.CUSTOM_SKIN, "QuestDialogBorder", Icon.class);
		public final static State<Icon> ICON_QUEST_DIALOG_BUTTON_MOUSE_OVER_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "QuestDialogButtonMouseOverHighlight", Icon.class);
		public final static State<Icon> ICON_QUEST_DIALOG_NORMAL_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "QuestDialogNormalBackground", Icon.class);
		public final static State<Icon> ICON_QUEST_DIALOG_NORMAL_BORDER = new State<>(Section.CUSTOM_SKIN, "QuestDialogNormalBorder", Icon.class);
		public final static State<Icon> ICON_QUEST_DIALOG_COMPLETED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "QuestDialogCompletedBackground", Icon.class);
		public final static State<Icon> ICON_QUEST_DIALOG_COMPLETED_BORDER = new State<>(Section.CUSTOM_SKIN, "QuestDialogCompletedBorder", Icon.class);
		public final static State<Icon> ICON_QUEST_DIALOG_COMPLETED_BUTTON_MOUSE_OVER_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "QuestDialogCompletedMouseOverHighlight", Icon.class);
		public final static State<Icon> ICON_QUEST_DIALOG_FAILED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "QuestDialogFailedBackground", Icon.class);
		public final static State<Icon> ICON_QUEST_DIALOG_FAILED_BORDER = new State<>(Section.CUSTOM_SKIN, "QuestDialogFailedBorder", Icon.class);
		public final static State<Icon> ICON_QUEST_DIALOG_FAILED_BUTTON_MOUSE_OVER_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "QuestDialogFailedMouseOverHighlight", Icon.class);
		
		public final static State<Icon> ICON_ESC_MENU_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuBackground", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_BORDER = new State<>(Section.CUSTOM_SKIN, "EscMenuBorder", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_BUTTON_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuButtonBackground", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_BUTTON_BORDER = new State<>(Section.CUSTOM_SKIN, "EscMenuButtonBorder", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_BUTTON_PRESSED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuButtonPushedBackground", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_BUTTON_PRESSED_BORDER = new State<>(Section.CUSTOM_SKIN, "EscMenuButtonPushedBorder", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_BUTTON_DISABLED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuButtonDisabledBackground", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_BUTTON_DISABLED_BORDER = new State<>(Section.CUSTOM_SKIN, "EscMenuButtonDisabledBorder", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_BUTTON_DISABLED_PRESSED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuButtonDisabledPushedBackground", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_BUTTON_DISABLED_PRESSED_BORDER = new State<>(Section.CUSTOM_SKIN, "EscMenuButtonDisabledPushedBorder", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_BUTTON_MOUSE_OVER_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "EscMenuButtonMouseOverHighlight", Icon.class);
		
		public final static State<Icon> ICON_ESC_MENU_SLIDER_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuSliderBackground", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_SLIDER_BORDER = new State<>(Section.CUSTOM_SKIN, "EscMenuSliderBorder", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_SLIDER_DISABLED_BORDER = new State<>(Section.CUSTOM_SKIN, "EscMenuSliderDisabledBorder", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_SLIDER_THUMB_BUTTON = new State<>(Section.CUSTOM_SKIN, "EscMenuSliderThumbButton", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_SLIDER_THUMB_BUTTON_DISABLED = new State<>(Section.CUSTOM_SKIN, "EscMenuSliderDisabledThumbButton", Icon.class);
		
		public final static State<Icon> ICON_ESC_MENU_CHECKBOX_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuCheckBoxBackground", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_CHECKBOX_CHECK_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "EscMenuCheckBoxCheckHighlight", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_CHECKBOX_DISABLED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuDisabledCheckBoxBackground", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_CHECKBOX_DISABLED_CHECK_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "EscMenuDisabledCheckBoxCheckHighlight", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_CHECKBOX_PRESSED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuCheckBoxPushedBackground", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_POPUP_MENU_ARROW = new State<>(Section.CUSTOM_SKIN, "EscMenuPopupMenuArrow", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_POPUP_MENU_MENU_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuPopupMenuMenuBackground", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_RADIO_BUTTON_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuRadioButtonBackground", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_RADIO_BUTTON_DISABLED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuRadioButtonDisabledBackground", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_RADIO_BUTTON_DISABLED_SELECTED_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "EscMenuDisabledRadioButtonSelectedHighlight", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_RADIO_BUTTON_PRESSED_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuRadioButtonPushedBackground", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_RADIO_BUTTON_SELECTED_HIGHLIGHT = new State<>(Section.CUSTOM_SKIN, "EscMenuRadioButtonSelectedHighlight", Icon.class);
		
		public final static State<Icon> ICON_ESC_MENU_BACKGROUND_BLANK = new State<>(Section.CUSTOM_SKIN, "EscMenuBlankBackground", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_EDIT_BOX_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "EscMenuEditBoxBackground", Icon.class);
		public final static State<Icon> ICON_ESC_MENU_EDIT_BOX_BORDER = new State<>(Section.CUSTOM_SKIN, "EscMenuEditBoxBorder", Icon.class);
		
		public final static State<Icon> CONSOLE_TEX1 = new State<>(Section.CUSTOM_SKIN, "ConsoleTexture01", Icon.class);
		public final static State<Icon> CONSOLE_TEX2 = new State<>(Section.CUSTOM_SKIN, "ConsoleTexture02", Icon.class);
		public final static State<Icon> CONSOLE_TEX3 = new State<>(Section.CUSTOM_SKIN, "ConsoleTexture03", Icon.class);
		public final static State<Icon> CONSOLE_TEX4 = new State<>(Section.CUSTOM_SKIN, "ConsoleTexture04", Icon.class);
		
		public final static State<Icon> ICON_UNIT_TIP_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "UnitTipBackground", Icon.class);
		
		public final static State<Icon> ICON_MULTIBOARD_BACKGROUND = new State<>(Section.CUSTOM_SKIN, "MultiboardBackground", Icon.class);
		public final static State<Icon> ICON_MULTIBOARD_BORDER = new State<>(Section.CUSTOM_SKIN, "MultiboardBorder", Icon.class);
		public final static State<Icon> ICON_MULTIBOARD_ICON_BORDER = new State<>(Section.CUSTOM_SKIN, "MultiboardIconBorder", Icon.class);
		public final static State<Icon> ICON_MULTIBOARD_ICON_DEFAULT = new State<>(Section.CUSTOM_SKIN, "MultiboardDefaultIcon", Icon.class);
		public final static State<Icon> ICON_MULTIBOARD_MAXIMIZE_BUTTON_ENABLED = new State<>(Section.CUSTOM_SKIN, "MultiboardMaximizeButtonEnabled", Icon.class);
		public final static State<Icon> ICON_MULTIBOARD_MAXIMIZE_BUTTON_DISABLED = new State<>(Section.CUSTOM_SKIN, "MultiboardMaximizeButtonDisabled", Icon.class);
		public final static State<Icon> ICON_MULTIBOARD_MAXIMIZE_BUTTON_PRESSED = new State<>(Section.CUSTOM_SKIN, "MultiboardMaximizeButtonPushed", Icon.class);
		public final static State<Icon> ICON_MULTIBOARD_MINIMIZE_BUTTON_ENABLED = new State<>(Section.CUSTOM_SKIN, "MultiboardMinimizeButtonEnabled", Icon.class);
		public final static State<Icon> ICON_MULTIBOARD_MINIMIZE_BUTTON_DISABLED = new State<>(Section.CUSTOM_SKIN, "MultiboardMinimizeButtonDisabled", Icon.class);
		public final static State<Icon> ICON_MULTIBOARD_MINIMIZE_BUTTON_PRESSED = new State<>(Section.CUSTOM_SKIN, "MultiboardMinimizeButtonPushed", Icon.class);
		
		public final static State<Icon> ICON_SCORESCREEN_BANNER_DEFEAT = new State<>(Section.CUSTOM_SKIN, "ScoreScreenBannerLoss", Icon.class);
		public final static State<Icon> ICON_SCORESCREEN_BANNER_VICTORY = new State<>(Section.CUSTOM_SKIN, "ScoreScreenBannerWin", Icon.class);
		
		public final static State<SoundLabel> SOUND_CANNOT_PLACE = new State<>(Section.CUSTOM_SKIN, "CantPlaceSound", SoundLabel.class);
		public final static State<SoundLabel> SOUND_CONSTRUCTING = new State<>(Section.CUSTOM_SKIN, "ConstructingBuilding", SoundLabel.class);
		public final static State<SoundLabel> SOUND_GOLD_MINE_COLLAPSED = new State<>(Section.CUSTOM_SKIN, "GoldMineCollapseSound", SoundLabel.class);
		public final static State<SoundLabel> SOUND_GOLD_MINE_LOW = new State<>(Section.CUSTOM_SKIN, "GoldMineLowSound", SoundLabel.class);
		public final static State<SoundLabel> SOUND_HERO_DEAD = new State<>(Section.CUSTOM_SKIN, "HeroDiesSound", SoundLabel.class);
		public final static State<SoundLabel> SOUND_HERO_DEAD_ALLY = new State<>(Section.CUSTOM_SKIN, "AllyHeroDiesSound", SoundLabel.class);
		public final static State<SoundLabel> SOUND_INV_FULL = new State<>(Section.CUSTOM_SKIN, "InventoryFullSound", SoundLabel.class);
		public final static State<SoundLabel> SOUND_JOB_DONE = new State<>(Section.CUSTOM_SKIN, "JobDoneSound", SoundLabel.class);
		public final static State<SoundLabel> SOUND_NO_GOLD = new State<>(Section.CUSTOM_SKIN, "NoGoldSound", SoundLabel.class);
		public final static State<SoundLabel> SOUND_NO_LUMBER = new State<>(Section.CUSTOM_SKIN, "NoLumberSound", SoundLabel.class);
		public final static State<SoundLabel> SOUND_NO_MANA = new State<>(Section.CUSTOM_SKIN, "NoManaSound", SoundLabel.class);
		public final static State<SoundLabel> SOUND_NO_SUPPLY = new State<>(Section.CUSTOM_SKIN, "NoFoodSound", SoundLabel.class);
		public final static State<SoundLabel> SOUND_PLACE_STRUCTURE = new State<>(Section.CUSTOM_SKIN, "PlaceBuilding", SoundLabel.class);
		public final static State<SoundLabel> SOUND_RESEARCH_COMPLETE = new State<>(Section.CUSTOM_SKIN, "ResearchComplete", SoundLabel.class);
		public final static State<SoundLabel> SOUND_UNDER_ATTACK = new State<>(Section.CUSTOM_SKIN, "UnderAttackSound", SoundLabel.class);
		public final static State<SoundLabel> SOUND_UNDER_ATTACK_ALLY = new State<>(Section.CUSTOM_SKIN, "AllyUnderAttackSound", SoundLabel.class);
		public final static State<SoundLabel> SOUND_UNDER_ATTACK_TOWN = new State<>(Section.CUSTOM_SKIN, "TownAttackSound", SoundLabel.class);
		public final static State<SoundLabel> SOUND_UNDER_ATTACK_TOWN_ALLY = new State<>(Section.CUSTOM_SKIN, "AllyTownUnderAttackSound", SoundLabel.class);
		public final static State<SoundLabel> SOUND_UPGRADE_COMPLETE = new State<>(Section.CUSTOM_SKIN, "UpgradeComplete", SoundLabel.class);

		public final static State<MusicFile> MUSIC_DEFEAT = new State<>(Section.CUSTOM_SKIN, "DefeatMusic_V1", MusicFile.class);
		public final static State<MusicFile> MUSIC_VICTORY = new State<>(Section.CUSTOM_SKIN, "VictoryMusic_V1", MusicFile.class);
		
		public final static State<Wc3String> PATH_COMMAND_BUTTON_DISABLED = new State<>(Section.CUSTOM_SKIN, "CommandButtonDisabledArtPath", Wc3String.class);
		public final static State<Wc3String> PATH_TEAM_COLOR = new State<>(Section.CUSTOM_SKIN, "TeamColor", Wc3String.class);
		public final static State<Wc3String> PATH_TEAM_GLOW = new State<>(Section.CUSTOM_SKIN, "TeamGlow", Wc3String.class);
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
