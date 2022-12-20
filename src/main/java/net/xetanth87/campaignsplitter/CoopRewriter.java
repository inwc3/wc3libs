package net.xetanth87.campaignsplitter;

import net.moonlightflower.wc3libs.bin.app.W3I;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CoopRewriter extends ScriptRewriter {
	public static final String SET_PLAYERS = "call SetPlayers(";
	public static final String SET_PLAYER_COLOR = "call SetPlayerColor(";
	public static final String DEFINE_START_LOC = "call DefineStartLocation(";
	public static final String SET_COLOR_CALL = "call SetPlayerColorBJ(";
	public static final String CONVERT_COLOR_FUNCTION = "ConvertPlayerColor(";
	public static final String FORCE_NAME = "udg_XT87CSCoopForce";
	public static final List<String> ALL_RESOURCE_NAMES = Arrays.asList("Gold", "Lumber", "FoodUsed", "FoodCap", "FoodCeiling");
	public boolean triggersAdded, globalsAdded, insideInitialization, insideInitGlobals, insideInitPlayers;
	public W3I info;
	public W3I.Player mainPlayer;
	public List<W3I.Player> secondaryPlayers;
	public List<Integer> allPlayerNumbers;
	public String targetPlayer;

	public CoopRewriter(MapInjector mi, W3I info, W3I.Player mainPlayer, List<W3I.Player> secondaryPlayers) {
		super(mi);
		this.info = info;
		this.mainPlayer = mainPlayer;
		this.secondaryPlayers = secondaryPlayers;
		targetPlayer = toPlayerFunc(mainPlayer.getNum());
		allPlayerNumbers = new ArrayList<>(1 + secondaryPlayers.size());
		allPlayerNumbers.add(mainPlayer.getNum());
		secondaryPlayers.forEach(x -> allPlayerNumbers.add(x.getNum()));
	}

	public static String getNewPath(String oldPath) {
		return XT87Utils.PATH_PREFIX + "/" + oldPath;
	}

	@Override
	public void onStartRead(String text, StringBuffer sb) {
		triggersAdded = false;
		globalsAdded = false;
		insideInitialization = false;
		insideInitGlobals = false;
		insideInitPlayers = false;
	}

	public void appendWithPlayerCondition(String referencedPlayer, List<String> lines, StringBuffer sb) {
		if (referencedPlayer.equals(targetPlayer)) {
			for (String line : lines)
				append(line, sb);
			return;
		}
		append("    if ((" + referencedPlayer + ") == " + targetPlayer + ") then", sb);
		for (String line : lines)
			append("    " + line, sb);
		append("    " + END_IF, sb);
	}

	public String toPlayerFunc(int i) {
		return "Player(" + i + ")";
	}

	@Override
	public void onReadLine(String line, StringBuffer sb) {
		line = line.replace("bj_FORCE_PLAYER[" + mainPlayer.getNum() + "]", FORCE_NAME);

		if (insideInitialization) {
			if (line.equals(END_FUNCTION)) {
				append("    " + SET_COLOR_CALL + targetPlayer + "," + CONVERT_COLOR_FUNCTION + mainPlayer.getNum() + "), true)", sb);
				for (W3I.Player player : secondaryPlayers) {
					append("    " + SET_COLOR_CALL + toPlayerFunc(player.getNum()) + ",GetPlayerColor(" + targetPlayer + "), true)", sb);
					for (W3I.Player otherPlayer : secondaryPlayers) {
						int otherNum = otherPlayer.getNum();
						if (otherNum == player.getNum()) {
							otherNum = mainPlayer.getNum();
							append("    call SetPlayerAllianceStateBJ(Player(" + otherNum + "), Player(" + player.getNum() + "), bj_ALLIANCE_ALLIED_ADVUNITS)", sb);
						}
						append("    call SetPlayerAllianceStateBJ(Player(" + player.getNum() + "), Player(" + otherNum + "), bj_ALLIANCE_ALLIED_ADVUNITS)", sb);
					}
				}
				append("    if (GetLocalPlayer()!=" + targetPlayer + ") then" + JASS_DELIM +
						"        call SetReservedLocalHeroButtons(0)" + JASS_DELIM +
						"    endif", sb);
				insideInitialization = false;
			}
		} else if (insideInitGlobals) {
			if (line.contains(END_FUNCTION)) {
				insideInitGlobals = false;
			}
			else if (!globalsAdded && line.contains("set ")) {
				append("    set " + FORCE_NAME + "=CreateForce()", sb);
				for (int playerNumber : allPlayerNumbers)
					append("    call ForceAddPlayerSimple(" + toPlayerFunc(playerNumber) + "," + FORCE_NAME + ")", sb);
				globalsAdded = false;
			}
		} else if (line.contains("function InitGlobals takes"))
			insideInitGlobals = true;
		else if (line.contains("function RunInitializationTriggers takes") || line.contains("function main takes"))
			insideInitialization = true;

		if (line.contains(SET_PLAYERS)) {
			append("    " + SET_PLAYERS + info.getPlayers().size() + ")", sb);
			return;
		}

		if (insideInitPlayers) {
			if (line.contains(SET_PLAYER_COLOR + targetPlayer)) {
				String[] params = getParams(line, SET_COLOR_CALL);
				append(line.replace(params[1], CONVERT_COLOR_FUNCTION + "1)"), sb);
				String secondaryColorLine = line.replace(params[1], CONVERT_COLOR_FUNCTION + "4)");
				for (W3I.Player player : secondaryPlayers) {
					append(secondaryColorLine.replace(targetPlayer, "Player(" + player.getNum() + ")"), sb);
				}
				return;
			}
		} else if (!triggersAdded && line.contains("function Trig")) {
			// gold and lumber synchronization
			List<String> resourceTypes = Arrays.asList("PLAYER_STATE_RESOURCE_GOLD", "PLAYER_STATE_RESOURCE_LUMBER");
			List<String> resourceNames = ALL_RESOURCE_NAMES.subList(0, 2);
			for (int i = 0; i < resourceTypes.size(); i++) {
				String resourceType = resourceTypes.get(i);
				String resourceName = resourceNames.get(i);
				append("//===========================================================================" + JASS_DELIM +
						"// Trigger: XT87CS_Coop" + resourceName + "Sync" + JASS_DELIM +
						"//===========================================================================" + JASS_DELIM +
						"function Trig_XT87CS_Coop" + resourceName + "Sync_Actions takes nothing returns nothing" + JASS_DELIM +
						"    local integer resourceSyncValue=GetPlayerState(GetTriggerPlayer(), " + resourceType + ")" + JASS_DELIM +
						"    " + XT87Utils.DISABLE_TRIGGER + XT87Utils.THIS_TRIGGER + ")", sb);
				for (int playerNumber : allPlayerNumbers) {
					String playerFunc = toPlayerFunc(playerNumber);
					append("    if (GetTriggerPlayer()!=" + playerFunc + ") then", sb);
					append("        call SetPlayerState(" + playerFunc + "," + resourceType + ", resourceSyncValue)", sb);
					append("    " + END_IF, sb);
				}
				append("    " + XT87Utils.ENABLE_TRIGGER + XT87Utils.THIS_TRIGGER + ")" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"//===========================================================================" + JASS_DELIM +
						"function InitTrig_XT87CS_Coop" + resourceName + "Sync takes nothing returns nothing" + JASS_DELIM +
						"    set gg_trg_XT87CS_Coop" + resourceName + "Sync=CreateTrigger()", sb);
				for (int playerNumber : allPlayerNumbers)
					append("    call TriggerRegisterPlayerStateEvent(gg_trg_XT87CS_Coop" + resourceName + "Sync, " + toPlayerFunc(playerNumber) + "," + resourceType + ", GREATER_THAN_OR_EQUAL, 0.00)", sb);
				append("    call TriggerAddAction(gg_trg_XT87CS_Coop" + resourceName + "Sync, function Trig_XT87CS_Coop" + resourceName + "Sync_Actions)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM, sb);
			}

			// food and population cap synchronization
			resourceTypes = Arrays.asList("PLAYER_STATE_RESOURCE_FOOD_USED", "PLAYER_STATE_RESOURCE_FOOD_CAP", "PLAYER_STATE_FOOD_CAP_CEILING");
			resourceNames = ALL_RESOURCE_NAMES.subList(resourceNames.size(), resourceNames.size() + 3);
			for (int i = 0; i < resourceTypes.size(); i++) {
				String resourceType = resourceTypes.get(i);
				String resourceName = resourceNames.get(i);
				append("//===========================================================================" + JASS_DELIM +
						"// Trigger: XT87CS_Coop" + resourceName + "Sync" + JASS_DELIM +
						"//===========================================================================" + JASS_DELIM +
						"function Trig_XT87CS_Coop" + resourceName + "Sync_Actions takes nothing returns nothing" + JASS_DELIM +
						"    local integer resourceSyncValue=GetPlayerState(GetTriggerPlayer(), " + resourceType + ")" + JASS_DELIM +
						"    " + XT87Utils.DISABLE_TRIGGER + XT87Utils.THIS_TRIGGER + ")", sb);
				for (int playerNumber : allPlayerNumbers) {
					if (playerNumber == mainPlayer.getNum())
						continue;
					String playerFunc = toPlayerFunc(playerNumber);
					append("    call SetPlayerState(" + playerFunc + "," + resourceType + ", resourceSyncValue)", sb);
				}
				append("    " + XT87Utils.ENABLE_TRIGGER + XT87Utils.THIS_TRIGGER + ")" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"//===========================================================================" + JASS_DELIM +
						"function InitTrig_XT87CS_Coop" + resourceName + "Sync takes nothing returns nothing" + JASS_DELIM +
						"    set gg_trg_XT87CS_Coop" + resourceName + "Sync=CreateTrigger()", sb);
				append("    call TriggerRegisterPlayerStateEvent(gg_trg_XT87CS_Coop" + resourceName + "Sync, " + targetPlayer + "," + resourceType + ", GREATER_THAN_OR_EQUAL, 0.00)", sb);
				append("    call TriggerAddAction(gg_trg_XT87CS_Coop" + resourceName + "Sync, function Trig_XT87CS_Coop" + resourceName + "Sync_Actions)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM, sb);
			}
			triggersAdded = true;
		}

		append(line, sb);

		if (line.equals("globals")) {
			for (String resourceName : ALL_RESOURCE_NAMES)
				append("trigger gg_trg_XT87CS_Coop" + resourceName + "Sync=null", sb);
			append("force " + FORCE_NAME + "=null", sb);
		} else if (insideInitPlayers) {
			if (line.equals(END_FUNCTION)) {
				insideInitPlayers = false;
			} else if (line.contains(targetPlayer)) {
				for (W3I.Player player : secondaryPlayers) {
					append(line.replace(targetPlayer, "Player(" + player.getNum() + ")"), sb);
				}
			}
		} else if (line.contains("function InitCustomPlayerSlots takes") || line.contains("function InitCustomTeams takes")
			//|| line.contains("function InitAllyPriorities takes")
		) {
			insideInitPlayers = true;
		} else if (line.contains("function InitCustomTriggers takes")) {
			for (String resourceName : ALL_RESOURCE_NAMES)
				append("    call InitTrig_XT87CS_Coop" + resourceName + "Sync()", sb);
		}

		String[] params = getParams(line);
		if (params != null) {
			switch (params.length) {
				case 2:
					// p _
					for (String specificCall : Arrays.asList("call SetCameraQuickPositionLocForPlayer(",
							"call ResetToGameCameraForPlayer(")) {
						if (line.contains(specificCall)) {
							String finalSpecificCall = specificCall;
							appendWithPlayerCondition(params[0],
									secondaryPlayers.stream().map(x -> "    " + finalSpecificCall + toPlayerFunc(x.getNum()) + "," + params[1] + ")")
											.collect(Collectors.toList()), sb);
							return;
						}
					}
					// _ p
					for (String specificCall : Arrays.asList("call TriggerRegisterPlayerEventEndCinematic(")) {
						if (line.contains(specificCall)) {
							String finalSpecificCall = specificCall;
							appendWithPlayerCondition(params[1],
									secondaryPlayers.stream().map(x -> "    " + finalSpecificCall + params[0] + "," + toPlayerFunc(x.getNum()) + ")")
											.collect(Collectors.toList()), sb);
							return;
						}
					}
					break;
				case 3:
					// p _ _
					for (String specificCall : Arrays.asList(SET_COLOR_CALL,
							"call PanCameraToTimedLocForPlayer(",
							"call SmartCameraPanBJ(")) {
						if (line.contains(specificCall)) {
							String finalSpecificCall = specificCall;
							appendWithPlayerCondition(params[0],
									secondaryPlayers.stream().map(x -> "    " + finalSpecificCall + toPlayerFunc(x.getNum()) + "," + params[1] + "," + params[2] + ")")
											.collect(Collectors.toList()), sb);
							return;
						}
					}
					// _ p _
					for (String specificCall : Arrays.asList("call TriggerRegisterPlayerSelectionEventBJ(")) {
						if (line.contains(specificCall)) {
							String finalSpecificCall = specificCall;
							appendWithPlayerCondition(params[1],
									secondaryPlayers.stream().map(x -> "    " + finalSpecificCall + params[0] + "," + toPlayerFunc(x.getNum()) + "," + params[2] + ")")
											.collect(Collectors.toList()), sb);
							return;
						}
					}
					break;
				case 4:
					// _ _ p _
					for (String specificCall : Arrays.asList("call CameraSetupApplyForPlayer(")) {
						if (line.contains(specificCall)) {
							String finalSpecificCall = specificCall;
							appendWithPlayerCondition(params[2],
									secondaryPlayers.stream().map(x -> "    " + finalSpecificCall + params[0] + "," + params[1] + "," + toPlayerFunc(x.getNum()) + "," + params[3] + ")")
											.collect(Collectors.toList()), sb);
							return;
						}
					}
					break;
				case 5:
					// _ p _ _ _
					for (String specificCall : Arrays.asList("call CreateFogModifierRadiusLocBJ(")) {
						if (line.contains(specificCall)) {
							String finalSpecificCall = specificCall;
							appendWithPlayerCondition(params[1],
									secondaryPlayers.stream().map(x -> "    " + finalSpecificCall + params[0] + "," + toPlayerFunc(x.getNum()) + "," + params[2] + "," + params[3] + "," + params[4] + ")")
											.collect(Collectors.toList()), sb);
							return;
						}
					}
					break;
			}
		}

		if (line.contains(DEFINE_START_LOC + mainPlayer.getNum() + ",")) {
			for (W3I.Player player : secondaryPlayers) {
				append(line.replace(DEFINE_START_LOC + mainPlayer.getNum(), DEFINE_START_LOC + player.getNum()), sb);
			}
		}
	}
}
