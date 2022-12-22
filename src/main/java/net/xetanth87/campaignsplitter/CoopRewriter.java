package net.xetanth87.campaignsplitter;

import net.moonlightflower.wc3libs.bin.app.W3I;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CoopRewriter extends ScriptRewriter {
	public static final String SET_PLAYERS = "call SetPlayers(";
	public static final String SETPLAYERCOLOR = "call SetPlayerColor(";
	public static final String DEFINE_START_LOC = "call DefineStartLocation(";
	public static final String SETPLAYERCOLORBJ = "call SetPlayerColorBJ(";
	public static final String CONVERTPLAYERCOLOR = "ConvertPlayerColor(";
	public static final String CUSTOM_PREFIX = "XT87CS_Coop";
	public static final String FORCE_NAME = "udg_XT87CSCoopForce";
	public static final String LAST_CREATED_COOP_CACHE = "XT87CSCoop_lastCreatedGameCache";
	public static final String CAMPAIGNFILE_KEY = "XT87CSCoopCampaignFile";
	public static final String VALUE_PREFIX = "v";
	public static final List<String> ALL_RESOURCE_NAMES = Arrays.asList("Gold", "Lumber", "FoodUsed", "FoodCap", "FoodCeiling");
	public boolean triggersAdded, globalsAdded, insideInitialization, insideInitGlobals, insideInitPlayers, withCustomGameCache;
	public W3I info;
	public W3I.Player mainPlayer;
	public List<W3I.Player> secondaryPlayers;
	public List<Integer> allPlayerNumbers;

	public CoopRewriter(MapInjector mi, W3I info, W3I.Player mainPlayer, List<W3I.Player> secondaryPlayers) {
		super(mi);
		this.info = info;
		this.mainPlayer = mainPlayer;
		this.secondaryPlayers = secondaryPlayers;
		allPlayerNumbers = new ArrayList<>(1 + secondaryPlayers.size());
		allPlayerNumbers.add(mainPlayer.getNum());
		secondaryPlayers.forEach(x -> allPlayerNumbers.add(x.getNum()));
		withCustomGameCache = true;
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

	public String toPlayerFunc(int i) {
		return "Player(" + i + ")";
	}

	public String toMainPlayerFunc() {
		return toPlayerFunc(mainPlayer.getNum());
	}

	public void appendWithPlayerCondition(String referencedPlayer, List<String> lines, StringBuffer sb) {
		if (referencedPlayer.equals(toMainPlayerFunc())) {
			for (String line : lines)
				append(line, sb);
			return;
		}
		append("    if((" + referencedPlayer + ")==" + toMainPlayerFunc() + ")then", sb);
		for (String line : lines)
			append("    " + line, sb);
		append("    " + END_IF, sb);
	}

	public String toCallString(String call, String[] params, int replacedParamIndex, String replacementParam) {
		String result = "    " + call;
		for (int i = 0; i < params.length; i++) {
			if (i != 0)
				result += ",";
			if (i == replacedParamIndex)
				result += replacementParam;
			else
				result += params[i];
		}
		return result + ")";
	}

	public void appendCallWithReplacement(String call, String[] params, int replacedParamIndex, StringBuffer sb) {
		appendWithPlayerCondition(params[replacedParamIndex],
				secondaryPlayers.stream().map(x -> toCallString(call, params, replacedParamIndex, toPlayerFunc(x.getNum())))
						.collect(Collectors.toList()), sb);
	}

	@Override
	public void onReadLine(String line, StringBuffer sb) {
		line = line.replace("bj_FORCE_PLAYER[" + mainPlayer.getNum() + "]", FORCE_NAME);
		if (withCustomGameCache) {
			line = line.replace("bj_lastCreatedGameCache", LAST_CREATED_COOP_CACHE);
			line = line.replace("GetLastCreatedGameCacheBJ()", LAST_CREATED_COOP_CACHE);
			// region game cache call list
			List<String> gameCacheCalls = Arrays.asList("call InitGameCache(",
					"call SaveGameCache(",

					"call StoreReal(",
					"call StoreInteger(",
					"call StoreBoolean(",
					"call StoreString(",
					"call StoreUnit(",

					"call GetStoredReal(",
					"call GetStoredInteger(",
					"call GetStoredBoolean(",
					"call GetStoredString(",
					"call RestoreUnit(",

					"call InitGameCacheBJ(",
					"call SaveGameCacheBJ(",

					"call StoreRealBJ(",
					"call StoreIntegerBJ(",
					"call StoreBooleanBJ(",
					"call StoreStringBJ(",
					"call StoreUnitBJ(",

					"call GetStoredRealBJ(",
					"call GetStoredIntegerBJ(",
					"call GetStoredBooleanBJ(",
					"call GetStoredStringBJ(",
					"call RestoreUnitLocFacingAngleBJ(",
					"call RestoreUnitLocFacingPointBJ(");
			// endregion
			for (String call : gameCacheCalls) {
				if (line.contains(call))
					line = line.replace("call ", "call " + CUSTOM_PREFIX);
			}
		}

		if (insideInitialization) {
			if (line.equals(END_FUNCTION)) {
				append("    " + SETPLAYERCOLORBJ + toMainPlayerFunc() + "," + CONVERTPLAYERCOLOR + mainPlayer.getNum() + "), true)", sb);
				for (W3I.Player player : secondaryPlayers) {
					String playerFunc = toPlayerFunc(player.getNum());
					append("    " + SETPLAYERCOLOR + playerFunc + "," + CONVERTPLAYERCOLOR + player.getNum() + "))", sb);
					for (W3I.Player otherPlayer : secondaryPlayers) {
						int otherNum = otherPlayer.getNum();
						if (otherNum == player.getNum()) {
							append("    call SetPlayerAllianceStateBJ(" + playerFunc + "," + toMainPlayerFunc() + ",bj_ALLIANCE_ALLIED_VISION)", sb);
							append("    call SetPlayerAllianceStateBJ(" + toMainPlayerFunc() + "," + playerFunc + ",bj_ALLIANCE_ALLIED_ADVUNITS)", sb);
						} else
							append("    call SetPlayerAllianceStateBJ(" + playerFunc + "," + toPlayerFunc(otherNum) + ",bj_ALLIANCE_ALLIED_VISION)", sb);
					}
				}
				append("    if (GetLocalPlayer()!=" + toMainPlayerFunc() + ") then" + JASS_DELIM +
						"        call SetReservedLocalHeroButtons(0)" + JASS_DELIM +
						"    endif", sb);
				insideInitialization = false;
			}
		} else if (insideInitGlobals) {
			if (line.contains(END_FUNCTION)) {
				insideInitGlobals = false;
			} else if (!globalsAdded && line.contains("set ")) {
				append("    set " + FORCE_NAME + "=CreateForce()", sb);
				for (int playerNumber : allPlayerNumbers)
					append("    call ForceAddPlayer(" + FORCE_NAME + "," + toPlayerFunc(playerNumber) + ")", sb);
				globalsAdded = true;
			}
			else if (line.trim().startsWith("cache ")) {
				line = line.replaceFirst("cache ", "hashtable ");
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
			if (line.contains(SETPLAYERCOLOR + toMainPlayerFunc())) {
				String[] params = getParamsFromLine(line);
				append(line.replace(params[1], CONVERTPLAYERCOLOR + "1)"), sb);
				String secondaryColorLine = line.replace(params[1], CONVERTPLAYERCOLOR + "4)");
				for (W3I.Player player : secondaryPlayers) {
					append(secondaryColorLine.replace(toMainPlayerFunc(), toPlayerFunc(player.getNum())), sb);
				}
				return;
			}
		} else if (!triggersAdded && line.contains("function Trig")) {
			if (withCustomGameCache) {
				// region game cache basics natives
				append("function " + CUSTOM_PREFIX + "InitGameCache takes string campaignFile returns hashtable" + JASS_DELIM +
						"    local hashtable coopCache = InitHashtable()" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"InitGameCache \" + campaignFile)" + JASS_DELIM +
						"    call SaveStringBJ(campaignFile,StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),coopCache)" + JASS_DELIM +
						"    return coopCache" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function  " + CUSTOM_PREFIX + "SaveGameCache takes hashtable coopCache returns boolean" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"SaveGameCache \"+LoadStringBJ(StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),coopCache))" + JASS_DELIM +
						"    return false" + JASS_DELIM +
						END_FUNCTION, sb);
				// endregion
				// region store natives
				append("function " + CUSTOM_PREFIX + "StoreReal takes hashtable coopCache,string key0,string key1,real value returns nothing" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"StoreReal \"+LoadStringBJ(StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),coopCache)+\"|\"+key0+\"|\"+key1)" + JASS_DELIM +
						"    call SaveReal(coopCache, StringHashBJ(key0), StringHashBJ(\"" + VALUE_PREFIX + "\"+key1), value)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + CUSTOM_PREFIX + "StoreInteger takes hashtable coopCache,string key0,string key1,integer value returns nothing" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"StoreInteger \"+LoadStringBJ(StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),coopCache)+\"|\" + key0 + \"|\" + key1)" + JASS_DELIM +
						"    call SaveInteger(coopCache, StringHashBJ(key0), StringHashBJ(\"" + VALUE_PREFIX + "\"+key1), value)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + CUSTOM_PREFIX + "StoreBoolean takes hashtable coopCache,string key0,string key1,boolean value returns nothing" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"StoreBoolean \"+LoadStringBJ(StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),coopCache)+\"|\" + key0 + \"|\" + key1)" + JASS_DELIM +
						"    call SaveBoolean(coopCache, StringHashBJ(key0), StringHashBJ(\"" + VALUE_PREFIX + "\"+key1), value)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + CUSTOM_PREFIX + "StoreString takes hashtable coopCache,string key0,string key1,string value returns boolean" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"StoreString \"+LoadStringBJ(StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),coopCache)+\"|\" + key0 + \"|\" + key1 + \" > \" + value)" + JASS_DELIM +
						"    return SaveStr(coopCache, StringHashBJ(key0), StringHashBJ(\"" + VALUE_PREFIX + "\"+key1), value)" + JASS_DELIM +
						END_FUNCTION, sb);
				// endregion
				// region save unit native
				append( JASS_DELIM +
						"function " + CUSTOM_PREFIX + "StoreUnit takes hashtable coopCache,string key0,string key1, unit value returns boolean" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"StoreUnit \"+LoadStringBJ(StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),coopCache)+\"|\" + key0 + \"|\" + key1  + \" > \" + GetUnitName(value))" + JASS_DELIM +
						"    return SaveStr(coopCache, StringHashBJ(key0), StringHashBJ(\"" + VALUE_PREFIX + "\"+key1), GetUnitName(value))" + JASS_DELIM +
						END_FUNCTION, sb);
				// endregion
				// region load natives
				append("function " + CUSTOM_PREFIX + "GetStoredReal takes hashtable coopCache,string key0,string key1 returns real" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"GetStoredReal \"+LoadStringBJ(StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),coopCache)+\"|\" + key0 + \"|\" + key1)" + JASS_DELIM +
						"    return LoadReal(coopCache, StringHashBJ(key0), StringHashBJ(\"" + VALUE_PREFIX + "\"+key1))" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + CUSTOM_PREFIX + "GetStoredInteger takes hashtable coopCache,string key0,string key1 returns integer" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"GetStoredInteger \"+LoadStringBJ(StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),coopCache)+\"|\" + key0 + \"|\" + key1)" + JASS_DELIM +
						"    return LoadInteger(coopCache, StringHashBJ(key0), StringHashBJ(\"" + VALUE_PREFIX + "\"+key1))" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + CUSTOM_PREFIX + "GetStoredBoolean takes hashtable coopCache,string key0,string key1 returns boolean" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"GetStoredBoolean \"+LoadStringBJ(StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),coopCache)+\"|\" + key0 + \"|\" + key1)" + JASS_DELIM +
						"    return LoadBoolean(coopCache, StringHashBJ(key0), StringHashBJ(\"" + VALUE_PREFIX + "\"+key1))" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + CUSTOM_PREFIX + "GetStoredString takes hashtable coopCache,string key0,string key1 returns string" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"GetStoredString \"+LoadStringBJ(StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),coopCache)+\"|\" + key0 + \"|\" + key1)" + JASS_DELIM +
						"    return LoadStr(coopCache, StringHashBJ(key0), StringHashBJ(\"" + VALUE_PREFIX + "\"+key1))" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + CUSTOM_PREFIX + "RestoreUnit takes hashtable coopCache,string key0,string key1,player forWhichPlayer,real x,real y,real facing returns unit" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"RestoreUnit \"+LoadStringBJ(StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),StringHashBJ(\""+ CAMPAIGNFILE_KEY + "\"),coopCache)+\"|\" + key0 + \"|\" + key1 + \" > \" + LoadStr(coopCache, StringHashBJ(key0), StringHashBJ(\"" + VALUE_PREFIX + "\"+key1)))" + JASS_DELIM +
						"    return null" + JASS_DELIM +
						END_FUNCTION, sb);
				// endregion

				// region game cache basics functions
				append("function " + CUSTOM_PREFIX + "InitGameCacheBJ takes string campaignFile returns string" + JASS_DELIM +
						"    set " + LAST_CREATED_COOP_CACHE + "=" + CUSTOM_PREFIX + "InitGameCache(campaignFile)" + JASS_DELIM +
						//"    return " + LAST_CREATED_COOP_CACHE + JASS_DELIM +
						"    return null" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function  " + CUSTOM_PREFIX + "SaveGameCacheBJ takes hashtable coopCache returns boolean" + JASS_DELIM +
						"    return " + CUSTOM_PREFIX + "SaveGameCache(coopCache)" + JASS_DELIM +
						END_FUNCTION, sb);
				// endregion
				// region save functions
				append("function " + CUSTOM_PREFIX + "StoreRealBJ takes real value,string key1,string key0,hashtable coopCache returns nothing" + JASS_DELIM +
						"    call " + CUSTOM_PREFIX + "StoreReal(coopCache,key0,key1,value)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + CUSTOM_PREFIX + "StoreIntegerBJ takes integer value,string key1,string key0,hashtable coopCache returns nothing" + JASS_DELIM +
						"    call " + CUSTOM_PREFIX + "StoreInteger(coopCache,key0,key1,value)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + CUSTOM_PREFIX + "StoreBooleanBJ takes boolean value,string key1,string key0,hashtable coopCache returns nothing" + JASS_DELIM +
						"    call " + CUSTOM_PREFIX + "StoreBoolean(coopCache,key0,key1,value)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + CUSTOM_PREFIX + "StoreStringBJ takes string value,string key1,string key0,hashtable coopCache returns boolean" + JASS_DELIM +
						"    return " + CUSTOM_PREFIX + "StoreString(coopCache,key0,key1,value)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + CUSTOM_PREFIX + "StoreUnitBJ takes unit whichUnit,string key1,string key0,hashtable coopCache returns boolean" + JASS_DELIM +
						"    return " + CUSTOM_PREFIX + "StoreUnit(coopCache,key0,key1,whichUnit)" + JASS_DELIM +
						END_FUNCTION, sb);
				// endregion
				// region load functions
				append("function " + CUSTOM_PREFIX + "GetStoredRealBJ takes string key1,string key0,hashtable coopCache returns real" + JASS_DELIM +
						"    return " + CUSTOM_PREFIX + "GetStoredReal(coopCache,key0,key1)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + CUSTOM_PREFIX + "GetStoredIntegerBJ takes string key1,string key0,hashtable coopCache returns integer" + JASS_DELIM +
						"    return " + CUSTOM_PREFIX + "GetStoredInteger(coopCache,key0,key1)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + CUSTOM_PREFIX + "GetStoredBooleanBJ takes string key1,string key0,hashtable coopCache returns boolean" + JASS_DELIM +
						"    return " + CUSTOM_PREFIX + "GetStoredBoolean(coopCache,key0,key1)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + CUSTOM_PREFIX + "GetStoredStringBJ takes string key1,string key0,hashtable coopCache returns string" + JASS_DELIM +
						"    local string s" + JASS_DELIM + JASS_DELIM +
						"    set s = " + CUSTOM_PREFIX + "GetStoredString(coopCache,key0,key1)" + JASS_DELIM +
						"    if (s == null) then" + JASS_DELIM +
						"        return \"\"" + JASS_DELIM +
						"    endif" + JASS_DELIM +
						"    return s" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + CUSTOM_PREFIX + "RestoreUnitLocFacingAngleBJ takes string key1,string key0,hashtable coopCache,player forWhichPlayer,location loc,real facing returns unit" + JASS_DELIM +
						"    set bj_lastLoadedUnit = " + CUSTOM_PREFIX + "RestoreUnit(coopCache,key0,key1,forWhichPlayer,GetLocationX(loc),GetLocationY(loc),facing)" + JASS_DELIM +
						"    return bj_lastLoadedUnit" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + CUSTOM_PREFIX + "RestoreUnitLocFacingPointBJ takes string key1,string key0,hashtable coopCache,player forWhichPlayer,location loc,location lookAt returns unit" + JASS_DELIM +
						"    return " + CUSTOM_PREFIX + "RestoreUnitLocFacingAngleBJ(key1,key0,coopCache,forWhichPlayer,loc,AngleBetweenPoints(loc,lookAt))" + JASS_DELIM +
						END_FUNCTION, sb);
				// endregion
			}
			// gold and lumber synchronization
			List<String> resourceTypes = Arrays.asList("PLAYER_STATE_RESOURCE_GOLD", "PLAYER_STATE_RESOURCE_LUMBER");
			List<String> resourceNames = ALL_RESOURCE_NAMES.subList(0, 2);
			for (int i = 0; i < resourceTypes.size(); i++) {
				String resourceType = resourceTypes.get(i);
				String resourceName = resourceNames.get(i);
				append("// Trigger: XT87CS_Coop" + resourceName + "Sync" + JASS_DELIM +
						"function Trig_XT87CS_Coop" + resourceName + "Sync_Actions takes nothing returns nothing" + JASS_DELIM +
						"    local integer resourceSyncValue=GetPlayerState(GetTriggerPlayer()," + resourceType + ")" + JASS_DELIM +
						"    " + XT87Utils.DISABLE_TRIGGER + XT87Utils.THIS_TRIGGER + ")", sb);
				for (int playerNumber : allPlayerNumbers) {
					String playerFunc = toPlayerFunc(playerNumber);
					append("    if (GetTriggerPlayer()!=" + playerFunc + ") then", sb);
					append("        call SetPlayerState(" + playerFunc + "," + resourceType + ",resourceSyncValue)", sb);
					append("    " + END_IF, sb);
				}
				append("    " + XT87Utils.ENABLE_TRIGGER + XT87Utils.THIS_TRIGGER + ")" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function InitTrig_XT87CS_Coop" + resourceName + "Sync takes nothing returns nothing" + JASS_DELIM +
						"    set gg_trg_XT87CS_Coop" + resourceName + "Sync=CreateTrigger()", sb);
				for (int playerNumber : allPlayerNumbers)
					append("    call TriggerRegisterPlayerStateEvent(gg_trg_XT87CS_Coop" + resourceName + "Sync," + toPlayerFunc(playerNumber) + "," + resourceType + ", GREATER_THAN_OR_EQUAL, 0.00)", sb);
				append("    call TriggerAddAction(gg_trg_XT87CS_Coop" + resourceName + "Sync,function Trig_XT87CS_Coop" + resourceName + "Sync_Actions)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM, sb);
			}

			// food and population cap synchronization
			resourceTypes = Arrays.asList("PLAYER_STATE_RESOURCE_FOOD_USED", "PLAYER_STATE_RESOURCE_FOOD_CAP", "PLAYER_STATE_FOOD_CAP_CEILING");
			resourceNames = ALL_RESOURCE_NAMES.subList(resourceNames.size(), resourceNames.size() + 3);
			for (int i = 0; i < resourceTypes.size(); i++) {
				String resourceType = resourceTypes.get(i);
				String resourceName = resourceNames.get(i);
				append("// Trigger: XT87CS_Coop" + resourceName + "Sync" + JASS_DELIM +
						"function Trig_XT87CS_Coop" + resourceName + "Sync_Actions takes nothing returns nothing" + JASS_DELIM +
						"    local integer resourceSyncValue=GetPlayerState(GetTriggerPlayer()," + resourceType + ")" + JASS_DELIM +
						"    " + XT87Utils.DISABLE_TRIGGER + XT87Utils.THIS_TRIGGER + ")", sb);
				for (int playerNumber : allPlayerNumbers) {
					if (playerNumber == mainPlayer.getNum())
						continue;
					String playerFunc = toPlayerFunc(playerNumber);
					append("    call SetPlayerState(" + playerFunc + "," + resourceType + ",resourceSyncValue)", sb);
				}
				append("    " + XT87Utils.ENABLE_TRIGGER + XT87Utils.THIS_TRIGGER + ")" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"//===========================================================================" + JASS_DELIM +
						"function InitTrig_XT87CS_Coop" + resourceName + "Sync takes nothing returns nothing" + JASS_DELIM +
						"    set gg_trg_XT87CS_Coop" + resourceName + "Sync=CreateTrigger()", sb);
				append("    call TriggerRegisterPlayerStateEvent(gg_trg_XT87CS_Coop" + resourceName + "Sync," + toMainPlayerFunc() + "," + resourceType + ", GREATER_THAN_OR_EQUAL, 0.00)", sb);
				append("    call TriggerAddAction(gg_trg_XT87CS_Coop" + resourceName + "Sync,function Trig_XT87CS_Coop" + resourceName + "Sync_Actions)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM, sb);
			}

			// give units to main player
			append("//===========================================================================" + JASS_DELIM +
					"// Trigger: XT87CS_CoopUnitShare" + JASS_DELIM +
					"//===========================================================================" + JASS_DELIM +
					"function Trig_XT87CS_CoopUnitShare_Conditions takes nothing returns boolean", sb);
			for (int playerNumber : allPlayerNumbers) {
				if (playerNumber == mainPlayer.getNum())
					continue;
				append(
						"    if (GetOwningPlayer(GetEnteringUnit())==" + toPlayerFunc(playerNumber) + ") then" + JASS_DELIM +
								"        return true" + JASS_DELIM +
								"    endif", sb);
			}
			append("    return false" + JASS_DELIM +
					END_FUNCTION + JASS_DELIM +
					"function Trig_XT87CS_CoopUnitShare_Actions takes nothing returns nothing" + JASS_DELIM +
					"    call SetUnitOwner(GetEnteringUnit()," + toMainPlayerFunc() + ",true)" + JASS_DELIM +
					"endfunction" + JASS_DELIM +
					"function InitTrig_XT87CS_CoopUnitShare takes nothing returns nothing" + JASS_DELIM +
					"    set gg_trg_XT87CS_CoopUnitShare=CreateTrigger()" + JASS_DELIM +
					"    call TriggerRegisterEnterRectSimple(gg_trg_XT87CS_CoopUnitShare,GetPlayableMapRect())" + JASS_DELIM +
					"    call TriggerAddCondition(gg_trg_XT87CS_CoopUnitShare,Condition(function Trig_XT87CS_CoopUnitShare_Conditions))" + JASS_DELIM +
					"    call TriggerAddAction(gg_trg_XT87CS_CoopUnitShare,function Trig_XT87CS_CoopUnitShare_Actions)" + JASS_DELIM +
					END_FUNCTION + JASS_DELIM, sb);
			triggersAdded = true;
		}

		append(line, sb);

		if (line.equals("globals")) {
			for (String resourceName : ALL_RESOURCE_NAMES)
				append("trigger gg_trg_XT87CS_Coop" + resourceName + "Sync=null", sb);
			append("trigger gg_trg_XT87CS_CoopUnitShare=null", sb);
			append("force " + FORCE_NAME + "=null", sb);
			append("hashtable " + LAST_CREATED_COOP_CACHE + "= null", sb);
		} else if (insideInitPlayers) {
			if (line.equals(END_FUNCTION)) {
				insideInitPlayers = false;
			} else if (line.contains(toMainPlayerFunc())) {
				for (W3I.Player player : secondaryPlayers) {
					append(line.replace(toMainPlayerFunc(), toPlayerFunc(player.getNum())), sb);
				}
			}
		} else if (line.contains("function InitCustomPlayerSlots takes") || line.contains("function InitCustomTeams takes")
			//|| line.contains("function InitAllyPriorities takes")
		) {
			insideInitPlayers = true;
		} else if (line.contains("function InitCustomTriggers takes")) {
			for (String resourceName : ALL_RESOURCE_NAMES)
				append("    call InitTrig_XT87CS_Coop" + resourceName + "Sync()", sb);
			append("    call InitTrig_XT87CS_CoopUnitShare()", sb);
		}

		String call = getCallFromLine(line);
		if (call != null) {
			String[] params = getParamsFromLine(line, call);
			if (params != null) {
				switch (params.length) {
					case 2:
						// p _
						if (Arrays.asList("call SetCameraQuickPositionLocForPlayer(",
								"call ResetToGameCameraForPlayer(",
								"call CustomDefeatBJ(").contains(call)) {
							appendCallWithReplacement(call, params, 0, sb);
							return;
						}
						// _ p
						if (Arrays.asList("call TriggerRegisterPlayerEventEndCinematic(").contains(call)) {
							appendCallWithReplacement(call, params, 1, sb);
							return;
						}
						break;
					case 3:
						// p _ _
						if (Arrays.asList("call PanCameraToTimedLocForPlayer(",
								"call SmartCameraPanBJ(",
								"call CustomVictoryBJ(").contains(call)) {
							appendCallWithReplacement(call, params, 0, sb);
							return;
						}
						// _ p _
						if (Arrays.asList("call TriggerRegisterPlayerSelectionEventBJ(").contains(call)) {
							appendCallWithReplacement(call, params, 1, sb);
							return;
						}
						break;
					case 4:
						// _ p _ _
						if (Arrays.asList("call TriggerRegisterPlayerChatEvent(").contains(call)) {
							appendCallWithReplacement(call, params, 1, sb);
							return;
						}
						// _ _ p _
						if (Arrays.asList("call CameraSetupApplyForPlayer(").contains(call)) {
							appendCallWithReplacement(call, params, 2, sb);
							return;
						}
						break;
					case 5:
						// _ p _ _ _
						if (Arrays.asList("call CreateFogModifierRadiusLocBJ(").contains(call)) {
							appendCallWithReplacement(call, params, 1, sb);
							return;
						}
						break;
				}
			}
		}

		if (line.contains(DEFINE_START_LOC + mainPlayer.getNum() + ",")) {
			for (W3I.Player player : secondaryPlayers) {
				append(line.replace(DEFINE_START_LOC + mainPlayer.getNum(), DEFINE_START_LOC + player.getNum()), sb);
			}
		}
	}
}
