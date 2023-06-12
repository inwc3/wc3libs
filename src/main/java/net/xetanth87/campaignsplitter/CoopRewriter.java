package net.xetanth87.campaignsplitter;

import net.moonlightflower.wc3libs.bin.app.W3I;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoopRewriter extends ScriptRewriter {
	public static final String SET_PLAYERS = "call SetPlayers(";
	public static final String SETPLAYERCOLOR = "call SetPlayerColor(";
	public static final String DEFINE_START_LOC = "call DefineStartLocation(";
	public static final String SETPLAYERCOLORBJ = "call SetPlayerColorBJ(";
	public static final String CONVERTPLAYERCOLOR = "ConvertPlayerColor(";
	public static final String PLAYER_FUNC = "Player(";
	public static final String TRIGGER_PREFIX = "tr_";
	public static final String ARCHON_PREFIX = "XT87AS";
	public static final String FORCE_NAME = ARCHON_PREFIX + "Force";
	public static final String TEMP_POINT_NAME = ARCHON_PREFIX + "TempPoint";
	public static final String ENFORCE_ARCHON = ARCHON_PREFIX + "EnforceArchon";
	public static final String LAST_CREATED_COOP_CACHE = ARCHON_PREFIX + "_lastCreatedGameCache";
	public static final String GENERAL_HASHTABLE = ARCHON_PREFIX + "Hashtable";
	public static final String GH_HAS_ABILITY = "hasAbility";
	public static final String ABILITY_ARRAY = ARCHON_PREFIX + "AbilityArray";
	public static final String ABILITY_ARRAY_SIZE = ABILITY_ARRAY + "SIZE";

	public static final String CACHE_VALUE_PREFIX = "v";
	public static final String CACHE_UNIT_PREFIX = "u";
	public static final String CACHE_OTHER_PREFIX = "o";

	public static final String CACHE_CAMPAIGNFILE_KEY = CACHE_OTHER_PREFIX + "campaignFile";
	public static final String CACHE_REAL_KEY = CACHE_OTHER_PREFIX + "real";
	public static final String CACHE_INTEGER_KEY = CACHE_OTHER_PREFIX + "int";
	public static final String CACHE_BOOL_KEY = CACHE_OTHER_PREFIX + "bool";
	public static final String CACHE_STRING_KEY = CACHE_OTHER_PREFIX + "str";
	public static final String CACHE_UNIT_KEY = CACHE_OTHER_PREFIX + "unit";
	public static final String CACHE_COUNT_KEY = "count";

	public static final String CACHE_ID_SUFIX = "id";
	public static final String CACHE_IS_HERO_SUFIX = "iH";
	public static final String CACHE_XP_SUFIX = "xp";
	public static final String CACHE_STR_SUFIX = "aS";
	public static final String CACHE_AGI_SUFIX = "aA";
	public static final String CACHE_INT_SUFIX = "aI";
	public static final String CACHE_UNSPEND_POINTS_SUFIX = "uP";
	public static final String CACHE_HEALTH_MAX_SUFIX = "hp";
	public static final String CACHE_MANA_MAX_SUFIX = "mp";
	public static final String CACHE_ABILITY_ID_SUFIX = "A";
	public static final String CACHE_ABILITY_LVL_SUFIX = "L";
	public static final String CACHE_ABILITY_COUNT_SUFIX = "Ac";
	public static final String CACHE_ITEM_ID_SUFIX = "I";
	public static final String CACHE_ITEM_CHARGE_SUFIX = "C";

	public static final List<String> ALL_RESOURCE_NAMES = Arrays.asList("Gold", "Lumber", "FoodUsed", "FoodCap", "FoodCeiling");
	public boolean triggersAdded, initGlobalsAdded, insideInitialization, insideInitGlobals, insideInitPlayers, withCustomGameCache;
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
		withCustomGameCache = false;
	}

	public static String getNewPath(String oldPath) {
		return XT87Utils.PATH_PREFIX + "/" + oldPath;
	}

	@Override
	public void onStartRead(String text, StringBuffer sb) {
		triggersAdded = false;
		initGlobalsAdded = false;
		insideInitialization = false;
		insideInitGlobals = false;
		insideInitPlayers = false;
	}

	public static String toPlayerFunc(int i) {
		return PLAYER_FUNC + i + ")";
	}

	public String toMainPlayerFunc() {
		return toPlayerFunc(mainPlayer.getNum());
	}

	public Integer getPlayerIdFromPlayerFunc(String s) {
		if (!s.contains(PLAYER_FUNC))
			return null;
		s = s.replace(PLAYER_FUNC, "").replace("(", "").replace(")", "");
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public String toCallString(String call, String[] params, int replacedParamIndex, String replacementParam) {
		StringBuilder result = new StringBuilder("call " + call + "(");
		for (int i = 0; i < params.length; i++) {
			if (i != 0)
				result.append(",");
			if (replacementParam != null && i == replacedParamIndex)
				result.append(replacementParam);
			else
				result.append(params[i]);
		}
		return result + ")";
	}

	public String toCallString(String call, String[] params) {
		return toCallString(call, params, 0, null);
	}

	public void appendCallWithReplacement(String call, String[] params, CoopCallAdjustments.AdjustmentDetails ad, StringBuffer sb) {
		for (int index : ad.getParameterIndices()) {
			String referencedPlayer = params[index];
			Integer referencedPlayerId = getPlayerIdFromPlayerFunc(referencedPlayer);
			boolean runtimeResolve = referencedPlayerId == null;

			if (runtimeResolve)
				append("    if((" + referencedPlayer + ")==" + toMainPlayerFunc() + ")then", sb);
			else if (referencedPlayerId != mainPlayer.getNum())
				continue;

			if (ad.isLocal)
				append(toCallString(call, params, index, "GetLocalPlayer()"), sb);
			else
				secondaryPlayers.stream().map(x -> toCallString(call, params, index, toPlayerFunc(x.getNum()))).forEach(s -> append(s, sb));

			if (runtimeResolve)
				append("    " + END_IF, sb);
		}
	}

	@Override
	public void onReadLine(String line, StringBuffer sb) {
		line = line.replace("bj_FORCE_PLAYER[" + mainPlayer.getNum() + "]", FORCE_NAME);
		line = line.replace("GetLocalPlayer()", ARCHON_PREFIX + "GetMainPlayer()");
		if (withCustomGameCache) {
			line = line.replace("bj_lastCreatedGameCache", LAST_CREATED_COOP_CACHE);
			line = line.replace("GetLastCreatedGameCacheBJ()", LAST_CREATED_COOP_CACHE);
			// region game cache call list
			List<String> gameCacheCalls = Arrays.asList("InitGameCache(",
					"SaveGameCache(",

					"StoreReal(",
					"StoreInteger(",
					"StoreBoolean(",
					"StoreString(",
					"StoreUnit(",

					"GetStoredReal(",
					"GetStoredInteger(",
					"GetStoredBoolean(",
					"GetStoredString(",
					"RestoreUnit(",

					"InitGameCacheBJ(",
					"SaveGameCacheBJ(",

					"StoreRealBJ(",
					"StoreIntegerBJ(",
					"StoreBooleanBJ(",
					"StoreStringBJ(",
					"StoreUnitBJ(",

					"GetStoredRealBJ(",
					"GetStoredIntegerBJ(",
					"GetStoredBooleanBJ(",
					"GetStoredStringBJ(",
					"RestoreUnitLocFacingAngleBJ(",
					"RestoreUnitLocFacingPointBJ(");
			// endregion
			String call = getCallFromLine(line);
			for (String gameCacheCall : gameCacheCalls) {
				line = line.replace(gameCacheCall, ARCHON_PREFIX + gameCacheCall);
			}
			if (call != null) {
				String[] params = getParamsFromLine(line);
				if (call.equals("call SelectHeroSkill(")) {
					append("    call " + ARCHON_PREFIX + "WatchSkill(" + params[1] + ")", sb);
				}
			}
		}

		if (insideInitialization) {
			if (line.equals(END_FUNCTION)) {
				append("    " + SETPLAYERCOLORBJ + toMainPlayerFunc() + "," + CONVERTPLAYERCOLOR + mainPlayer.getNum() + "),true)", sb);
				for (W3I.Player player : secondaryPlayers) {
					String playerFunc = toPlayerFunc(player.getNum());
					append(SETPLAYERCOLOR + playerFunc + "," + CONVERTPLAYERCOLOR + player.getNum() + "))" + JASS_DELIM +
							"call SetPlayerOnScoreScreen(" + playerFunc + ",false)", sb);
				}
				append("call " + ENFORCE_ARCHON + "()" + JASS_DELIM +
						"if (GetLocalPlayer()!=" + toMainPlayerFunc() + ") then" + JASS_DELIM +
						"call SetReservedLocalHeroButtons(0)" + JASS_DELIM +
						END_IF, sb);
				insideInitialization = false;
			}
		} else if (insideInitGlobals) {
			if (line.contains(END_FUNCTION)) {
				insideInitGlobals = false;
			} else if (!initGlobalsAdded && line.contains("set ")) {
				append("    set " + FORCE_NAME + "=CreateForce()", sb);
				for (int playerNumber : allPlayerNumbers)
					append("    call ForceAddPlayer(" + FORCE_NAME + "," + toPlayerFunc(playerNumber) + ")", sb);
				initGlobalsAdded = true;
			} else if (line.trim().startsWith("cache ")) {
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
			// define functions
			if (withCustomGameCache) {
				// region game cache basics natives
				append("function " + ARCHON_PREFIX + "InitGameCache takes string campaignFile returns hashtable" + JASS_DELIM +
						"    local hashtable coopCache = InitHashtable()" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"InitGameCache \"+campaignFile)" + JASS_DELIM +
						"    call SaveStringBJ(campaignFile,StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),coopCache)" + JASS_DELIM +
						"    return coopCache" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function  " + ARCHON_PREFIX + "SaveGameCache takes hashtable coopCache returns boolean" + JASS_DELIM +
						"    local integer i" + JASS_DELIM +
						"    local integer n" + JASS_DELIM +
						"    local integer j" + JASS_DELIM +
						"    local integer c" + JASS_DELIM +
						"    local integer it" + JASS_DELIM +
						"    local integer k0h" + JASS_DELIM +
						"    local boolean h" + JASS_DELIM +
						"    local string k0" + JASS_DELIM +
						"    local string k2" + JASS_DELIM +
						"    local string uD" + JASS_DELIM +
						"    local string cacheName=LoadStringBJ(StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),coopCache)" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"SaveGameCache \"+cacheName)" + JASS_DELIM +
						// save reals to files
						"    set i=0" + JASS_DELIM +
						"    set n=LoadInteger(coopCache,StringHashBJ(\"" + CACHE_REAL_KEY + "\"),StringHashBJ(\"" + CACHE_COUNT_KEY + "\"))" + JASS_DELIM +
						"    loop" + JASS_DELIM +
						"        exitwhen i>=n" + JASS_DELIM +
						"        set k0=LoadStr(coopCache,StringHashBJ(\"" + CACHE_REAL_KEY + "\"),2*i)" + JASS_DELIM +
						"        set k2=LoadStr(coopCache,StringHashBJ(\"" + CACHE_REAL_KEY + "\"),2*i+1)" + JASS_DELIM +
						"        call " + ARCHON_PREFIX + "WriteString(cacheName,k0+\"\\\\\"+k2,R2S(LoadReal(coopCache,StringHashBJ(k0),StringHashBJ(k2))))" + JASS_DELIM +
						"        set i=i+1" + JASS_DELIM +
						"    endloop" + JASS_DELIM +
						// save integers to files
						"    set i=0" + JASS_DELIM +
						"    set n=LoadInteger(coopCache,StringHashBJ(\"" + CACHE_INTEGER_KEY + "\"),StringHashBJ(\"" + CACHE_COUNT_KEY + "\"))" + JASS_DELIM +
						"    loop" + JASS_DELIM +
						"        exitwhen i>=n" + JASS_DELIM +
						"        set k0=LoadStr(coopCache,StringHashBJ(\"" + CACHE_INTEGER_KEY + "\"),2*i)" + JASS_DELIM +
						"        set k2=LoadStr(coopCache,StringHashBJ(\"" + CACHE_INTEGER_KEY + "\"),2*i+1)" + JASS_DELIM +
						"        call " + ARCHON_PREFIX + "WriteString(cacheName,k0+\"\\\\\"+k2,I2S(LoadInteger(coopCache,StringHashBJ(k0),StringHashBJ(k2))))" + JASS_DELIM +
						"        set i=i+1" + JASS_DELIM +
						"    endloop" + JASS_DELIM +
						// save bools to files
						"    set i=0" + JASS_DELIM +
						"    set n=LoadInteger(coopCache,StringHashBJ(\"" + CACHE_BOOL_KEY + "\"),StringHashBJ(\"" + CACHE_COUNT_KEY + "\"))" + JASS_DELIM +
						"    loop" + JASS_DELIM +
						"        exitwhen i>=n" + JASS_DELIM +
						"        set k0=LoadStr(coopCache,StringHashBJ(\"" + CACHE_BOOL_KEY + "\"),2*i)" + JASS_DELIM +
						"        set k2=LoadStr(coopCache,StringHashBJ(\"" + CACHE_BOOL_KEY + "\"),2*i+1)" + JASS_DELIM +
						"        call " + ARCHON_PREFIX + "WriteString(cacheName,k0+\"\\\\\"+k2," + ARCHON_PREFIX + "B2S(LoadBoolean(coopCache,StringHashBJ(k0),StringHashBJ(k2))))" + JASS_DELIM +
						"        set i=i+1" + JASS_DELIM +
						"    endloop" + JASS_DELIM +
						// save strings to files
						"    set i=0" + JASS_DELIM +
						"    set n=LoadInteger(coopCache,StringHashBJ(\"" + CACHE_STRING_KEY + "\"),StringHashBJ(\"" + CACHE_COUNT_KEY + "\"))" + JASS_DELIM +
						"    loop" + JASS_DELIM +
						"        exitwhen i>=n" + JASS_DELIM +
						"        set k0=LoadStr(coopCache,StringHashBJ(\"" + CACHE_STRING_KEY + "\"),2*i)" + JASS_DELIM +
						"        set k2=LoadStr(coopCache,StringHashBJ(\"" + CACHE_STRING_KEY + "\"),2*i+1)" + JASS_DELIM +
						"        call " + ARCHON_PREFIX + "WriteString(cacheName,k0+\"\\\\\"+k2,LoadStr(coopCache,StringHashBJ(k0),StringHashBJ(k2)))" + JASS_DELIM +
						"        set i=i+1" + JASS_DELIM +
						"    endloop" + JASS_DELIM +
						// save units to files
						"    set i=0" + JASS_DELIM +
						"    set n=LoadInteger(coopCache,StringHashBJ(\"" + CACHE_UNIT_KEY + "\"),StringHashBJ(\"" + CACHE_COUNT_KEY + "\"))" + JASS_DELIM +
						"    loop" + JASS_DELIM +
						"        exitwhen i>=n" + JASS_DELIM +
						"        set k0=LoadStr(coopCache,StringHashBJ(\"" + CACHE_UNIT_KEY + "\"),2*i)" + JASS_DELIM +
						"        set k2=LoadStr(coopCache,StringHashBJ(\"" + CACHE_UNIT_KEY + "\"),2*i+1)" + JASS_DELIM +
						"        set k0h=StringHash(k0)" + JASS_DELIM +
						"        set uD=I2S(LoadInteger(coopCache,k0h,StringHashBJ(k2+\"" + CACHE_ID_SUFIX + "\")))" + JASS_DELIM +
						"        set h=LoadBoolean(coopCache,k0h,StringHashBJ(k2+\"" + CACHE_IS_HERO_SUFIX + "\"))" + JASS_DELIM +
						//"        set uD=uD+\"\\n\"+" + CUSTOM_PREFIX + "B2S(h)" + JASS_DELIM +
						"        if h then", sb);
				for (String sufix : Arrays.asList(CACHE_XP_SUFIX, CACHE_STR_SUFIX, CACHE_AGI_SUFIX, CACHE_INT_SUFIX, CACHE_UNSPEND_POINTS_SUFIX))
					append("            set uD=uD+\"\\n\"+I2S(LoadInteger(coopCache,k0h,StringHashBJ(k2+\"" + sufix + "\")))", sb);
				for (String sufix : Arrays.asList(CACHE_HEALTH_MAX_SUFIX, CACHE_MANA_MAX_SUFIX))
					append("            set uD=uD+\"\\n\"+R2S(LoadReal(coopCache,k0h,StringHashBJ(k2+\"" + sufix + "\")))", sb);
				append("            set c=LoadInteger(coopCache,k0h,StringHashBJ(k2+\"" + CACHE_ABILITY_COUNT_SUFIX + "\"))" + JASS_DELIM +
						"            set uD=uD+\"\\n\"+I2S(c)" + JASS_DELIM +
						"            set j=0" + JASS_DELIM +
						"            loop" + JASS_DELIM +
						"            exitwhen j>=c", sb);
				for (String sufix : Arrays.asList(CACHE_ABILITY_ID_SUFIX, CACHE_ABILITY_LVL_SUFIX))
					append("            set uD=uD+\"\\n\"+I2S(LoadInteger(coopCache,k0h,StringHashBJ(k2+\"" + sufix + "\"+I2S(j))))", sb);
				append("            set j=j+1" + JASS_DELIM +
						"            endloop" + JASS_DELIM +
						"        endif" + JASS_DELIM +
						"        set j=0" + JASS_DELIM +
						"        loop" + JASS_DELIM +
						"        exitwhen j>5" + JASS_DELIM +
						"        set it=LoadInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_ITEM_ID_SUFIX + "\"+I2S(j)))" + JASS_DELIM +
						"        set uD=uD+\"\\n\"+I2S(it)" + JASS_DELIM +
						"        if it!=0 then" + JASS_DELIM +
						"        set uD=uD+\"\\n\"+I2S(LoadInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_ITEM_CHARGE_SUFIX + "\"+I2S(j))))" + JASS_DELIM +
						"        endif" + JASS_DELIM +
						"        set j=j+1" + JASS_DELIM +
						"        endloop" + JASS_DELIM +
						"        call " + ARCHON_PREFIX + "WriteString(cacheName,k0+\"\\\\\"+k2,uD)" + JASS_DELIM +
						"        set i=i+1" + JASS_DELIM +
						"    endloop" + JASS_DELIM +
						"    return false" + JASS_DELIM +
						END_FUNCTION, sb);
				// endregion
				// region store natives
				append("function " + ARCHON_PREFIX + "StoreReference takes hashtable coopCache,string k,string k0,string k2 returns nothing" + JASS_DELIM +
						"    local integer c=LoadInteger(coopCache,StringHashBJ(k),StringHashBJ(\"" + CACHE_COUNT_KEY + "\"))" + JASS_DELIM +
						"    call SaveStr(coopCache,StringHashBJ(k),2*c,k0)" + JASS_DELIM +
						"    call SaveStr(coopCache,StringHashBJ(k),2*c+1,k2)" + JASS_DELIM +
						"    call SaveInteger(coopCache,StringHashBJ(k),StringHashBJ(\"" + CACHE_COUNT_KEY + "\"),c+1)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "StoreReal takes hashtable coopCache,string k0,string k1,real v returns nothing" + JASS_DELIM +
						"    local string k2=\"" + CACHE_VALUE_PREFIX + "\"+k1" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"StoreReal \"+LoadStringBJ(StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),coopCache)+\"|\"+k0+\"|\"+k1)" + JASS_DELIM +
						"    call " + ARCHON_PREFIX + "StoreReference(coopCache,\"" + CACHE_REAL_KEY + "\",k0,k2)" + JASS_DELIM +
						"    call SaveReal(coopCache,StringHashBJ(k0),StringHashBJ(k2),v)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "StoreInteger takes hashtable coopCache,string k0,string k1,integer v returns nothing" + JASS_DELIM +
						"    local string k2=\"" + CACHE_VALUE_PREFIX + "\"+k1" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"StoreInteger \"+LoadStringBJ(StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),coopCache)+\"|\"+k0+\"|\"+k1)" + JASS_DELIM +
						"    call " + ARCHON_PREFIX + "StoreReference(coopCache,\"" + CACHE_INTEGER_KEY + "\",k0,k2)" + JASS_DELIM +
						"    call SaveInteger(coopCache,StringHashBJ(k0),StringHashBJ(k2),v)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "StoreBoolean takes hashtable coopCache,string k0,string k1,boolean v returns nothing" + JASS_DELIM +
						"    local string k2=\"" + CACHE_VALUE_PREFIX + "\"+k1" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"StoreBoolean \"+LoadStringBJ(StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),coopCache)+\"|\"+k0+\"|\"+k1)" + JASS_DELIM +
						"    call " + ARCHON_PREFIX + "StoreReference(coopCache,\"" + CACHE_BOOL_KEY + "\",k0,k2)" + JASS_DELIM +
						"    call SaveBoolean(coopCache,StringHashBJ(k0),StringHashBJ(k2),v)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "StoreString takes hashtable coopCache,string k0,string k1,string v returns boolean" + JASS_DELIM +
						"    local string k2=\"" + CACHE_VALUE_PREFIX + "\"+k1" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"StoreString \"+LoadStringBJ(StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),coopCache)+\"|\"+k0+\"|\"+k1+\" > \"+v)" + JASS_DELIM +
						"    call " + ARCHON_PREFIX + "StoreReference(coopCache,\"" + CACHE_STRING_KEY + "\",k0,k2)" + JASS_DELIM +
						"    return SaveStr(coopCache,StringHashBJ(k0),StringHashBJ(k2),v)" + JASS_DELIM +
						END_FUNCTION, sb);
				// endregion
				// region save unit native
				append(JASS_DELIM +
						"function " + ARCHON_PREFIX + "StoreUnit takes hashtable coopCache,string k0,string k1,unit u returns boolean" + JASS_DELIM +
						"    local integer i" + JASS_DELIM +
						"    local item tempItem=null" + JASS_DELIM +
						"    local integer a" + JASS_DELIM +
						"    local integer l" + JASS_DELIM +
						"    local integer c=0" + JASS_DELIM +
						"    local integer k0h=StringHash(k0)" + JASS_DELIM +
						"    local string k2=\"" + CACHE_UNIT_PREFIX + "\"+k1" + JASS_DELIM +
						"    if (u==null) then" + JASS_DELIM +
						"        return false" + JASS_DELIM +
						"    endif" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"StoreUnit \"+LoadStringBJ(StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),coopCache)+\"|\"+k0+\"|\"+k1 +\" > \"+GetUnitName(u))" + JASS_DELIM +
						"    call SaveInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_ID_SUFIX + "\"),GetUnitTypeId(u))" + JASS_DELIM +
						"    call SaveBoolean(coopCache,k0h,StringHash(k2+\"" + CACHE_IS_HERO_SUFIX + "\"),IsUnitType(u,UNIT_TYPE_HERO))" + JASS_DELIM +
						"    if (IsUnitType(u,UNIT_TYPE_HERO)) then" + JASS_DELIM +
						"        call SaveInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_XP_SUFIX + "\"),GetHeroXP(u))" + JASS_DELIM +
						"        call SaveInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_STR_SUFIX + "\"),GetHeroStr(u,false))" + JASS_DELIM +
						"        call SaveInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_AGI_SUFIX + "\"),GetHeroAgi(u,false))" + JASS_DELIM +
						"        call SaveInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_INT_SUFIX + "\"),GetHeroInt(u,false))" + JASS_DELIM +
						"        call SaveInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_UNSPEND_POINTS_SUFIX + "\"),GetHeroSkillPoints(u))" + JASS_DELIM +
						"        call SaveReal(coopCache,StringHash(k0),StringHash(k2+\"hp\"),GetUnitState(u,UNIT_STATE_MAX_LIFE))" + JASS_DELIM +
						"        call SaveReal(coopCache,StringHash(k0),StringHash(k2+\"mp\"),GetUnitState(u,UNIT_STATE_MAX_MANA))" + JASS_DELIM +
						"        set i=0" + JASS_DELIM +
						"        loop" + JASS_DELIM +
						"            exitwhen i>=" + ABILITY_ARRAY_SIZE + JASS_DELIM +
						"            set a = " + ABILITY_ARRAY + "[i]" + JASS_DELIM +
						"            set l = GetUnitAbilityLevel(u,a)" + JASS_DELIM +
						"            if l>0 then" + JASS_DELIM +
						"               call SaveInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_ABILITY_ID_SUFIX + "\"+I2S(c)),a)" + JASS_DELIM +
						"               call SaveInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_ABILITY_LVL_SUFIX + "\"+I2S(c)),l)" + JASS_DELIM +
						"               set c = c+1" + JASS_DELIM +
						"            " + END_IF + JASS_DELIM +
						"            set i = i+1" + JASS_DELIM +
						"        endloop" + JASS_DELIM +
						"        call SaveInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_ABILITY_COUNT_SUFIX + "\"),c)" + JASS_DELIM +
						"    endif" + JASS_DELIM +
						"    set i=0" + JASS_DELIM +
						"    loop" + JASS_DELIM +
						"        exitwhen i>5" + JASS_DELIM +
						"        set tempItem = UnitItemInSlot(u,i)" + JASS_DELIM +
						"        call SaveInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_ITEM_ID_SUFIX + "\"+I2S(i)),GetItemTypeId(tempItem))" + JASS_DELIM +
						"        call SaveInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_ITEM_CHARGE_SUFIX + "\"+I2S(i)),GetItemCharges(tempItem))" + JASS_DELIM +
						"        set i = i+1" + JASS_DELIM +
						"    endloop" + JASS_DELIM +
						"    call " + ARCHON_PREFIX + "StoreReference(coopCache,\"" + CACHE_UNIT_KEY + "\",k0,k2)" + JASS_DELIM +
						"    return true" + JASS_DELIM +
						END_FUNCTION, sb);
				// endregion
				// region load natives
				append("function " + ARCHON_PREFIX + "GetStoredReal takes hashtable coopCache,string k0,string k1 returns real" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"GetStoredReal \"+LoadStringBJ(StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),coopCache)+\"|\"+k0+\"|\"+k1)" + JASS_DELIM +
						"    return LoadReal(coopCache,StringHashBJ(k0),StringHashBJ(\"" + CACHE_VALUE_PREFIX + "\"+k1))" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "GetStoredInteger takes hashtable coopCache,string k0,string k1 returns integer" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"GetStoredInteger \"+LoadStringBJ(StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),coopCache)+\"|\"+k0+\"|\"+k1)" + JASS_DELIM +
						"    return LoadInteger(coopCache,StringHashBJ(k0),StringHashBJ(\"" + CACHE_VALUE_PREFIX + "\"+k1))" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "GetStoredBoolean takes hashtable coopCache,string k0,string k1 returns boolean" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"GetStoredBoolean \"+LoadStringBJ(StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),coopCache)+\"|\"+k0+\"|\"+k1)" + JASS_DELIM +
						"    return LoadBoolean(coopCache,StringHashBJ(k0),StringHashBJ(\"" + CACHE_VALUE_PREFIX + "\"+k1))" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "GetStoredString takes hashtable coopCache,string k0,string k1 returns string" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"GetStoredString \"+LoadStringBJ(StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),coopCache)+\"|\"+k0+\"|\"+k1)" + JASS_DELIM +
						"    return LoadStr(coopCache,StringHashBJ(k0),StringHashBJ(\"" + CACHE_VALUE_PREFIX + "\"+k1))" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "RestoreUnit takes hashtable coopCache,string k0,string k1,player owner,real x,real y,real facing returns unit" + JASS_DELIM +
						"    local integer i" + JASS_DELIM +
						"    local integer j" + JASS_DELIM +
						"    local integer a" + JASS_DELIM +
						"    local integer l" + JASS_DELIM +
						"    local integer c" + JASS_DELIM +
						"    local integer id" + JASS_DELIM +
						"    local unit u=null" + JASS_DELIM +
						"    local boolean r=false" + JASS_DELIM +
						"    local integer k0h=StringHash(k0)" + JASS_DELIM +
						"    local string k2=\"" + CACHE_UNIT_PREFIX + "\"+k1" + JASS_DELIM +
						"    local string cacheName=LoadStringBJ(StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),StringHashBJ(\"" + CACHE_CAMPAIGNFILE_KEY + "\"),coopCache)" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"RestoreUnit \"+cacheName+\"|\"+k0+\"|\"+k1+\" > \"+LoadStr(coopCache,StringHashBJ(k0),StringHashBJ(\"" + CACHE_VALUE_PREFIX + "\"+k1)))" + JASS_DELIM +
						"    set id=LoadInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_ID_SUFIX + "\"))" + JASS_DELIM +
						"    if id==0 then" + JASS_DELIM +
						"        set id=" + ARCHON_PREFIX + "ReadUnit(cacheName,k0+\"\\\\\"+k2)" + JASS_DELIM +
						"set r=true" + JASS_DELIM +
						"    endif" + JASS_DELIM +
						"    if id==0 then" + JASS_DELIM +
						"        return null" + JASS_DELIM +
						"    endif" + JASS_DELIM +
						"    set u=CreateUnit(owner,id,x,y,facing)" + JASS_DELIM +
						"    if(IsUnitType(u,UNIT_TYPE_HERO))then" + JASS_DELIM +
						"if r then", sb);
				int lineIndex = 0;
				for (String sufix : Arrays.asList(CACHE_ID_SUFIX, CACHE_XP_SUFIX, CACHE_STR_SUFIX, CACHE_AGI_SUFIX, CACHE_INT_SUFIX, CACHE_UNSPEND_POINTS_SUFIX))
					append("call SaveInteger(coopCache,k0h,StringHash(k2+\"" + sufix + "\"),S2I(XT87ReadLines[" + (lineIndex++) + "]))", sb);
				for (String sufix : Arrays.asList(CACHE_HEALTH_MAX_SUFIX, CACHE_MANA_MAX_SUFIX))
					append("call SaveReal(coopCache,k0h,StringHash(k2+\"" + sufix + "\"),S2R(XT87ReadLines[" + (lineIndex++) + "]))", sb);
				append("call SaveInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_ABILITY_COUNT_SUFIX + "\"),S2I(XT87ReadLines[" + (lineIndex++) + "]))", sb);
				append(END_IF + JASS_DELIM +
						"        call SetHeroXP(u,LoadInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_XP_SUFIX + "\")),false)" + JASS_DELIM +
						"        call SetHeroStr(u,LoadInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_STR_SUFIX + "\")),true)" + JASS_DELIM +
						"        call SetHeroAgi(u,LoadInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_AGI_SUFIX + "\")),true)" + JASS_DELIM +
						"        call SetHeroInt(u,LoadInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_INT_SUFIX + "\")),true)" + JASS_DELIM +
						"        call ModifyHeroSkillPoints(u,bj_MODIFYMETHOD_SET,LoadInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_UNSPEND_POINTS_SUFIX + "\")))" + JASS_DELIM +
						"        call BlzSetUnitMaxHP(u,R2I(LoadReal(coopCache,k0h,StringHash(k2+\"" + CACHE_HEALTH_MAX_SUFIX + "\"))))" + JASS_DELIM +
						"        call BlzSetUnitMaxMana(u,R2I(LoadReal(coopCache,k0h,StringHash(k2+\"" + CACHE_MANA_MAX_SUFIX + "\"))))" + JASS_DELIM +
						"        set c=LoadInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_ABILITY_COUNT_SUFIX + "\"))" + JASS_DELIM +
						"        set i=0" + JASS_DELIM +
						//"        call DisplayTextToForce(GetPlayersAll(),\"AUX i \"+I2S(i)+\" c \"+I2S(c))"+JASS_DELIM +
						"        loop" + JASS_DELIM +
						"            exitwhen i>=c" + JASS_DELIM +
						"if r then", sb);
				for (String sufix : Arrays.asList(CACHE_ABILITY_ID_SUFIX, CACHE_ABILITY_LVL_SUFIX))
					append("call SaveInteger(coopCache,k0h,StringHash(k2+\"" + sufix + "\"),S2I(XT87ReadLines[" + (lineIndex++) + "]))", sb);
				append(END_IF + JASS_DELIM +
						"            set a=LoadInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_ABILITY_ID_SUFIX + "\"+I2S(i)))" + JASS_DELIM +
						"            set l=LoadInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_ABILITY_LVL_SUFIX + "\"+I2S(i)))" + JASS_DELIM +
						"            call ModifyHeroSkillPoints(u,bj_MODIFYMETHOD_ADD,l)" + JASS_DELIM +
						"            call " + ARCHON_PREFIX + "WatchSkill(a)" + JASS_DELIM +
						//"            call DisplayTextToForce(GetPlayersAll(),\"AUX i \"+I2S(i)+\" l \"+I2S(l))"+JASS_DELIM +
						"            set j=0" + JASS_DELIM +
						"            loop" + JASS_DELIM +
						"                exitwhen j>=l" + JASS_DELIM +
						//"                call DisplayTextToForce(GetPlayersAll(),\"AUX i \"+I2S(i)+\" j \"+I2S(j))"+JASS_DELIM +
						//"                call DisplayTextToForce(GetPlayersAll(),\"AUX i \"+I2S(i)+\" a \"+I2S(a))"+JASS_DELIM +
						"                call SelectHeroSkill(u,a)" + JASS_DELIM +
						"                set j=j+1" + JASS_DELIM +
						"            endloop" + JASS_DELIM +
						"            set i=i+1" + JASS_DELIM +
						"        endloop" + JASS_DELIM +
						"    " + END_IF + JASS_DELIM +
						"    set i=0" + JASS_DELIM +
						"    loop" + JASS_DELIM +
						"        exitwhen i>5" + JASS_DELIM +
						"if r then" + JASS_DELIM +
						"set c = S2I(XT87ReadLines[" + (lineIndex++) + "])" + JASS_DELIM +
						"call SaveInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_ITEM_ID_SUFIX + "\"),c)" + JASS_DELIM +
						"if c!=0 then" + JASS_DELIM +
						"call SaveInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_ITEM_CHARGE_SUFIX + "\"),S2I(XT87ReadLines[" + (lineIndex++) + "]))" + JASS_DELIM +
						END_IF + JASS_DELIM +
						END_IF + JASS_DELIM +
						"        call UnitAddItemToSlotById(u,LoadInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_ITEM_ID_SUFIX + "\"+I2S(i))),i)" + JASS_DELIM +
						"        call SetItemCharges(UnitItemInSlot(u,i),LoadInteger(coopCache,k0h,StringHash(k2+\"" + CACHE_ITEM_CHARGE_SUFIX + "\"+I2S(i))))" + JASS_DELIM +
						"        set i=i+1" + JASS_DELIM +
						"    endloop" + JASS_DELIM +
						"    return u" + JASS_DELIM +
						END_FUNCTION, sb);
				// endregion

				// region game cache basics functions
				append("function " + ARCHON_PREFIX + "InitGameCacheBJ takes string campaignFile returns string" + JASS_DELIM +
						"    set " + LAST_CREATED_COOP_CACHE + "=" + ARCHON_PREFIX + "InitGameCache(campaignFile)" + JASS_DELIM +
						//"    return "+LAST_CREATED_COOP_CACHE+JASS_DELIM +
						"    return null" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function  " + ARCHON_PREFIX + "SaveGameCacheBJ takes hashtable coopCache returns boolean" + JASS_DELIM +
						"    return " + ARCHON_PREFIX + "SaveGameCache(coopCache)" + JASS_DELIM +
						END_FUNCTION, sb);
				// endregion
				// region save functions
				append("function " + ARCHON_PREFIX + "StoreRealBJ takes real val,string k1,string k0,hashtable coopCache returns nothing" + JASS_DELIM +
						"    call " + ARCHON_PREFIX + "StoreReal(coopCache,k0,k1,val)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "StoreIntegerBJ takes integer val,string k1,string k0,hashtable coopCache returns nothing" + JASS_DELIM +
						"    call " + ARCHON_PREFIX + "StoreInteger(coopCache,k0,k1,val)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "StoreBooleanBJ takes boolean val,string k1,string k0,hashtable coopCache returns nothing" + JASS_DELIM +
						"    call " + ARCHON_PREFIX + "StoreBoolean(coopCache,k0,k1,val)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "StoreStringBJ takes string val,string k1,string k0,hashtable coopCache returns boolean" + JASS_DELIM +
						"    return " + ARCHON_PREFIX + "StoreString(coopCache,k0,k1,val)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "StoreUnitBJ takes unit u,string k1,string k0,hashtable coopCache returns boolean" + JASS_DELIM +
						"    return " + ARCHON_PREFIX + "StoreUnit(coopCache,k0,k1,u)" + JASS_DELIM +
						END_FUNCTION, sb);
				// endregion
				// region load functions
				append("function " + ARCHON_PREFIX + "GetStoredRealBJ takes string k1,string k0,hashtable coopCache returns real" + JASS_DELIM +
						"    return " + ARCHON_PREFIX + "GetStoredReal(coopCache,k0,k1)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "GetStoredIntegerBJ takes string k1,string k0,hashtable coopCache returns integer" + JASS_DELIM +
						"    return " + ARCHON_PREFIX + "GetStoredInteger(coopCache,k0,k1)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "GetStoredBooleanBJ takes string k1,string k0,hashtable coopCache returns boolean" + JASS_DELIM +
						"    return " + ARCHON_PREFIX + "GetStoredBoolean(coopCache,k0,k1)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "GetStoredStringBJ takes string k1,string k0,hashtable coopCache returns string" + JASS_DELIM +
						"    local string s" + JASS_DELIM + JASS_DELIM +
						"    set s = " + ARCHON_PREFIX + "GetStoredString(coopCache,k0,k1)" + JASS_DELIM +
						"    if (s==null) then" + JASS_DELIM +
						"        return \"\"" + JASS_DELIM +
						"    endif" + JASS_DELIM +
						"    return s" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "RestoreUnitLocFacingAngleBJ takes string k1,string k0,hashtable coopCache,player owner,location loc,real facing returns unit" + JASS_DELIM +
						"    set bj_lastLoadedUnit = " + ARCHON_PREFIX + "RestoreUnit(coopCache,k0,k1,owner,GetLocationX(loc),GetLocationY(loc),facing)" + JASS_DELIM +
						"    return bj_lastLoadedUnit" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "RestoreUnitLocFacingPointBJ takes string k1,string k0,hashtable coopCache,player owner,location loc,location lookAt returns unit" + JASS_DELIM +
						"    return " + ARCHON_PREFIX + "RestoreUnitLocFacingAngleBJ(k1,k0,coopCache,owner,loc,AngleBetweenPoints(loc,lookAt))" + JASS_DELIM +
						END_FUNCTION, sb);
				// endregion
			}
			// region gold and lumber synchronization
			List<String> resourceTypes = Arrays.asList("PLAYER_STATE_RESOURCE_GOLD", "PLAYER_STATE_RESOURCE_LUMBER");
			List<String> resourceNames = ALL_RESOURCE_NAMES.subList(0, 2);
			for (int i = 0; i < resourceTypes.size(); i++) {
				String resourceType = resourceTypes.get(i);
				String resourceName = resourceNames.get(i);
				append("// Trigger: " + ARCHON_PREFIX + resourceName + "Sync" + JASS_DELIM +
						"function Trig_" + ARCHON_PREFIX + resourceName + "Sync_Actions takes nothing returns nothing" + JASS_DELIM +
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
						"function InitTrig_" + ARCHON_PREFIX + resourceName + "Sync takes nothing returns nothing" + JASS_DELIM +
						"    set " + TRIGGER_PREFIX + ARCHON_PREFIX + resourceName + "Sync=CreateTrigger()", sb);
				for (int playerNumber : allPlayerNumbers)
					append("    call TriggerRegisterPlayerStateEvent(" + TRIGGER_PREFIX + ARCHON_PREFIX + resourceName + "Sync," + toPlayerFunc(playerNumber) + "," + resourceType + ",GREATER_THAN_OR_EQUAL,0.00)", sb);
				append("    call TriggerAddAction(" + TRIGGER_PREFIX + ARCHON_PREFIX + resourceName + "Sync,function Trig_" + ARCHON_PREFIX + resourceName + "Sync_Actions)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM, sb);
			}
			// endregion

			// region food and population cap synchronization
			resourceTypes = Arrays.asList("PLAYER_STATE_RESOURCE_FOOD_USED", "PLAYER_STATE_RESOURCE_FOOD_CAP", "PLAYER_STATE_FOOD_CAP_CEILING");
			resourceNames = ALL_RESOURCE_NAMES.subList(resourceNames.size(), resourceNames.size() + 3);
			for (int i = 0; i < resourceTypes.size(); i++) {
				String resourceType = resourceTypes.get(i);
				String resourceName = resourceNames.get(i);
				append("// Trigger: " + ARCHON_PREFIX + resourceName + "Sync" + JASS_DELIM +
						"function Trig_" + ARCHON_PREFIX + resourceName + "Sync_Actions takes nothing returns nothing" + JASS_DELIM +
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
						"function InitTrig_" + ARCHON_PREFIX + resourceName + "Sync takes nothing returns nothing" + JASS_DELIM +
						"    set " + TRIGGER_PREFIX + ARCHON_PREFIX + resourceName + "Sync=CreateTrigger()", sb);
				append("    call TriggerRegisterPlayerStateEvent(" + TRIGGER_PREFIX + ARCHON_PREFIX + resourceName + "Sync," + toMainPlayerFunc() + "," + resourceType + ",GREATER_THAN_OR_EQUAL,0.00)", sb);
				append("    call TriggerAddAction(" + TRIGGER_PREFIX + ARCHON_PREFIX + resourceName + "Sync,function Trig_" + ARCHON_PREFIX + resourceName + "Sync_Actions)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM, sb);
			}
			// endregion

			// region give units to main player
			append("//===========================================================================" + JASS_DELIM +
					"// Trigger: " + ARCHON_PREFIX + "UnitShare" + JASS_DELIM +
					"//===========================================================================" + JASS_DELIM +
					"function Trig_" + ARCHON_PREFIX + "UnitShare_Conditions takes nothing returns boolean", sb);
			for (int playerNumber : allPlayerNumbers) {
				if (playerNumber == mainPlayer.getNum())
					continue;
				append("    if (GetOwningPlayer(GetEnteringUnit())==" + toPlayerFunc(playerNumber) + ") then" + JASS_DELIM +
						"        return true" + JASS_DELIM +
						"    endif", sb);
			}
			append("    return false" + JASS_DELIM +
					END_FUNCTION + JASS_DELIM +
					"function Trig_" + ARCHON_PREFIX + "UnitShare_Actions takes nothing returns nothing" + JASS_DELIM +
					"    call SetUnitOwner(GetEnteringUnit()," + toMainPlayerFunc() + ",true)" + JASS_DELIM +
					"endfunction" + JASS_DELIM +
					"function InitTrig_" + ARCHON_PREFIX + "UnitShare takes nothing returns nothing" + JASS_DELIM +
					"    set " + TRIGGER_PREFIX + ARCHON_PREFIX + "UnitShare=CreateTrigger()" + JASS_DELIM +
					"    call TriggerRegisterEnterRectSimple(" + TRIGGER_PREFIX + ARCHON_PREFIX + "UnitShare,GetPlayableMapRect())" + JASS_DELIM +
					"    call TriggerAddCondition(" + TRIGGER_PREFIX + ARCHON_PREFIX + "UnitShare,Condition(function Trig_" + ARCHON_PREFIX + "UnitShare_Conditions))" + JASS_DELIM +
					"    call TriggerAddAction(" + TRIGGER_PREFIX + ARCHON_PREFIX + "UnitShare,function Trig_" + ARCHON_PREFIX + "UnitShare_Actions)" + JASS_DELIM +
					END_FUNCTION + JASS_DELIM, sb);
			// endregion

			// region get main player
			append("function " + ARCHON_PREFIX + "GetMainPlayer takes nothing returns player" + JASS_DELIM +
					"    local player p = GetLocalPlayer()" + JASS_DELIM +
					"    if IsPlayerInForce(p, " + FORCE_NAME + ") then" + JASS_DELIM +
					"        return " + toMainPlayerFunc() + JASS_DELIM +
					"    endif" + JASS_DELIM +
					"    return p" + JASS_DELIM +
					END_FUNCTION + JASS_DELIM, sb);
			// endregion

			// region enforce sharing
			append("function " + ENFORCE_ARCHON + " takes nothing returns nothing", sb);
			for (W3I.Player player : secondaryPlayers) {
				String playerFunc = toPlayerFunc(player.getNum());
				for (W3I.Player otherPlayer : secondaryPlayers) {
					int otherNum = otherPlayer.getNum();
					if (otherNum == player.getNum()) {
						append("    call SetPlayerAllianceStateBJ(" + playerFunc + "," + toMainPlayerFunc() + ",bj_ALLIANCE_ALLIED_VISION)", sb);
						append("    call SetPlayerAllianceStateBJ(" + toMainPlayerFunc() + "," + playerFunc + ",bj_ALLIANCE_ALLIED_ADVUNITS)", sb);
					} else
						append("    call SetPlayerAllianceStateBJ(" + playerFunc + "," + toPlayerFunc(otherNum) + ",bj_ALLIANCE_ALLIED_VISION)", sb);
				}
			}
			append(END_FUNCTION, sb);
			// endregion
			triggersAdded = true;
		}

		boolean appendLine = true;

		String call = getCallFromLine(line);
		if (call != null) {
			String[] params = getParamsFromLine(line, call);
			call = getTrimmedCallFromLine(call);
			if (params != null) {
				CoopCallAdjustments.AdjustmentDetails ad = CoopCallAdjustments.callParameters.get(call);
				if (ad != null) {
					appendLine = false;

					if (ad.pointIndex != null) {
						append("set " + TEMP_POINT_NAME + "=" + params[ad.pointIndex], sb);
						params[ad.pointIndex] = TEMP_POINT_NAME;
						line = toCallString(call, params);
					}

					if (!ad.isLocal)
						append(line, sb);

					appendCallWithReplacement(call, params, ad, sb);
				}
			}
		}

		if (appendLine)
			append(line, sb);

		if (line.equals("globals")) {
			for (String resourceName : ALL_RESOURCE_NAMES)
				append("trigger " + TRIGGER_PREFIX + ARCHON_PREFIX + resourceName + "Sync=null", sb);
			append("trigger " + TRIGGER_PREFIX + ARCHON_PREFIX + "UnitShare=null" + JASS_DELIM +
					"force " + FORCE_NAME + JASS_DELIM +
					"location " + TEMP_POINT_NAME + JASS_DELIM, sb);
			if (withCustomGameCache) {
				append("hashtable " + LAST_CREATED_COOP_CACHE + "=null" + JASS_DELIM +
						"hashtable " + GENERAL_HASHTABLE + "=InitHashtable()" + JASS_DELIM +
						"integer array " + ABILITY_ARRAY + JASS_DELIM +
						"integer " + ABILITY_ARRAY_SIZE + "=0" + JASS_DELIM +
						"trigger " + TRIGGER_PREFIX + ARCHON_PREFIX + "WatchLearnedSkill" + JASS_DELIM +
						"trigger " + TRIGGER_PREFIX + ARCHON_PREFIX + "SyncData" + JASS_DELIM +
						"constant integer s__File_AbilityCount=10" + JASS_DELIM +
						"constant integer s__File_PreloadLimit=200" + JASS_DELIM +
						"integer s__File_Counter=0" + JASS_DELIM +
						"integer array s__File_List" + JASS_DELIM +
						"integer array s__File_AbilityList" + JASS_DELIM +
						"boolean s__File_ReadEnabled=false" + JASS_DELIM +
						"string array s__File_filename" + JASS_DELIM +
						"string array s__File_buffer" + JASS_DELIM +
						"string array XT87ReadLines" + JASS_DELIM +
						"boolean XT87ReadLinesAreRead=false", sb);
			}
		} else if (line.equals("endglobals")) {
			if (withCustomGameCache) {
				// region FileIO library
				append("        function s__File_open takes string filename returns integer" + JASS_DELIM +
						"            local integer this= s__File_List[0]" + JASS_DELIM +
						"            if ( this==0 ) then" + JASS_DELIM +
						"                set this=s__File_Counter+1" + JASS_DELIM +
						"                set s__File_Counter=this" + JASS_DELIM +
						"            else" + JASS_DELIM +
						"                set s__File_List[0]=s__File_List[this]" + JASS_DELIM +
						"            endif" + JASS_DELIM +
						"            " + JASS_DELIM +
						"            set s__File_filename[this]=filename" + JASS_DELIM +
						"            set s__File_buffer[this]=null" + JASS_DELIM +
						"            " + JASS_DELIM +
						"            " + JASS_DELIM +
						"            return this" + JASS_DELIM +
						"        endfunction" + JASS_DELIM +
						"        // This is used to detect invalid characters which aren't supported in preload files." + JASS_DELIM +
						"        function s__File_write takes integer this,string contents returns integer" + JASS_DELIM +
						"            local integer i= 0" + JASS_DELIM +
						"            local integer c= 0" + JASS_DELIM +
						"            local integer len= StringLength(contents)" + JASS_DELIM +
						"            local integer lev= 0" + JASS_DELIM +
						"            local string prefix= \"-\"" + JASS_DELIM +
						"            local string chunk" + JASS_DELIM +
						"            set s__File_buffer[this]=null" + JASS_DELIM +
						"            // Check if the string is empty. If null,the contents will be cleared." + JASS_DELIM +
						"            if ( contents==\"\" ) then" + JASS_DELIM +
						"                set len=len+1" + JASS_DELIM +
						"            endif" + JASS_DELIM +
						"            // Begin to generate the file" + JASS_DELIM +
						"            call PreloadGenClear()" + JASS_DELIM +
						"            call PreloadGenStart()" + JASS_DELIM +
						"            loop" + JASS_DELIM +
						"                exitwhen i >= len" + JASS_DELIM +
						"                set lev=0" + JASS_DELIM +
						"                set chunk=SubString(contents,i,i+s__File_PreloadLimit)" + JASS_DELIM +
						"                call Preload(\"\\\" )\\ncall BlzSetAbilityTooltip(\"+I2S(s__File_AbilityList[c])+\",\\\"\"+prefix+chunk+\"\\\",\"+I2S(lev)+\")\\n//\")" + JASS_DELIM +
						"                set i=i+s__File_PreloadLimit" + JASS_DELIM +
						"                set c=c+1" + JASS_DELIM +
						"            endloop" + JASS_DELIM +
						"            call Preload(\"\\\" )\\nendfunction\\nfunction a takes nothing returns nothing\\n //\")" + JASS_DELIM +
						"            call PreloadGenEnd(s__File_filename[this])" + JASS_DELIM +
						"            return this" + JASS_DELIM +
						"        endfunction" + JASS_DELIM +
						"        function s__File_clear takes integer this returns integer" + JASS_DELIM +
						"            return s__File_write(this,null)" + JASS_DELIM +
						"        endfunction" + JASS_DELIM +
						"        function s__File_readPreload takes integer this returns string" + JASS_DELIM +
						"            local integer i= 0" + JASS_DELIM +
						"            local integer lev= 0" + JASS_DELIM +
						"            local string array original" + JASS_DELIM +
						"            local string chunk= \"\"" + JASS_DELIM +
						"            local string output= \"\"" + JASS_DELIM +
						"            loop" + JASS_DELIM +
						"                exitwhen i==s__File_AbilityCount" + JASS_DELIM +
						"                set original[i]=BlzGetAbilityTooltip(s__File_AbilityList[i],0)" + JASS_DELIM +
						"                set i=i+1" + JASS_DELIM +
						"            endloop" + JASS_DELIM +
						"            // Execute the preload file" + JASS_DELIM +
						"            call Preloader(s__File_filename[this])" + JASS_DELIM +
						"            // Read the output" + JASS_DELIM +
						"            set i=0" + JASS_DELIM +
						"            loop" + JASS_DELIM +
						"                exitwhen i==s__File_AbilityCount" + JASS_DELIM +
						"                " + JASS_DELIM +
						"                set lev=0" + JASS_DELIM +
						"                // Read from ability index 1 instead of 0 if " + JASS_DELIM +
						"                // backwards compatability is enabled" + JASS_DELIM +
						"                // Make sure the tooltip has changed" + JASS_DELIM +
						"                set chunk=BlzGetAbilityTooltip(s__File_AbilityList[i],lev)" + JASS_DELIM +
						"                if ( chunk==original[i] ) then" + JASS_DELIM +
						"                    if ( i==0 and output==\"\" ) then" + JASS_DELIM +
						"                        return null // empty file" + JASS_DELIM +
						"                    endif" + JASS_DELIM +
						"                    return output" + JASS_DELIM +
						"                endif" + JASS_DELIM +
						"                // Check if the file is an empty string or null" + JASS_DELIM +
						"                    if ( i==0 ) then" + JASS_DELIM +
						"                        if ( SubString(chunk,0,1) != \"-\" ) then" + JASS_DELIM +
						"                            return null // empty file" + JASS_DELIM +
						"                        endif" + JASS_DELIM +
						"                        set chunk=SubString(chunk,1,StringLength(chunk))" + JASS_DELIM +
						"                    endif" + JASS_DELIM +
						"                // Remove the prefix" + JASS_DELIM +
						"                if ( i > 0 ) then" + JASS_DELIM +
						"                    set chunk=SubString(chunk,1,StringLength(chunk))" + JASS_DELIM +
						"                endif" + JASS_DELIM +
						"                " + JASS_DELIM +
						"                // Restore the tooltip and append the chunk" + JASS_DELIM +
						"                call BlzSetAbilityTooltip(s__File_AbilityList[i],original[i],lev)" + JASS_DELIM +
						"                " + JASS_DELIM +
						"                set output=output+chunk" + JASS_DELIM +
						"                " + JASS_DELIM +
						"                set i=i+1" + JASS_DELIM +
						"            endloop" + JASS_DELIM +
						"            return output" + JASS_DELIM +
						"        endfunction" + JASS_DELIM +
						"        function s__File_close takes integer this returns nothing" + JASS_DELIM +
						"            if ( s__File_buffer[this] != null ) then" + JASS_DELIM +
						"                call s__File_write(this,s__File_readPreload(this)+s__File_buffer[this])" + JASS_DELIM +
						"                set s__File_buffer[this]=null" + JASS_DELIM +
						"            endif" + JASS_DELIM +
						"            set s__File_List[this]=s__File_List[0]" + JASS_DELIM +
						"            set s__File_List[0]=this" + JASS_DELIM +
						"        endfunction" + JASS_DELIM +
						"        function s__File_readEx takes integer this,boolean close returns string" + JASS_DELIM +
						"            local string output= s__File_readPreload(this)" + JASS_DELIM +
						"            local string buf= s__File_buffer[this]" + JASS_DELIM +
						"            if close then" + JASS_DELIM +
						"                call s__File_close(this)" + JASS_DELIM +
						"            endif" + JASS_DELIM +
						"            if output==null then" + JASS_DELIM +
						"                return buf" + JASS_DELIM +
						"            endif" + JASS_DELIM +
						"            if buf != null then" + JASS_DELIM +
						"                set output=output+buf" + JASS_DELIM +
						"            endif" + JASS_DELIM +
						"            return output" + JASS_DELIM +
						"        endfunction" + JASS_DELIM +
						"        function s__File_read takes integer this returns string" + JASS_DELIM +
						"            return s__File_readEx(this,false)" + JASS_DELIM +
						"        endfunction" + JASS_DELIM +
						"        function s__File_readAndClose takes integer this returns string" + JASS_DELIM +
						"            return s__File_readEx(this,true)" + JASS_DELIM +
						"        endfunction" + JASS_DELIM +
						"        function s__File_appendBuffer takes integer this,string contents returns integer" + JASS_DELIM +
						"            set s__File_buffer[this]=s__File_buffer[this]+contents" + JASS_DELIM +
						"            return this" + JASS_DELIM +
						"        endfunction" + JASS_DELIM +
						"        function s__File_readBuffer takes integer this returns string" + JASS_DELIM +
						"            return s__File_buffer[this]" + JASS_DELIM +
						"        endfunction" + JASS_DELIM +
						"        function s__File_writeBuffer takes integer this,string contents returns nothing" + JASS_DELIM +
						"            set s__File_buffer[this]=contents" + JASS_DELIM +
						"        endfunction" + JASS_DELIM +
						"        function s__File_create takes string filename returns integer" + JASS_DELIM +
						"            return s__File_write(s__File_open(filename),\"\")" + JASS_DELIM +
						"        endfunction" + JASS_DELIM +
						"//Implemented from module FileIO___FileInit:" + JASS_DELIM +
						"        function s__File_FileIO___FileInit__onInit takes nothing returns nothing" + JASS_DELIM +
						"            local string originalTooltip" + JASS_DELIM +
						"            // We can't use a single ability with multiple levels because" + JASS_DELIM +
						"            // tooltips return the first level's value if the value hasn't" + JASS_DELIM +
						"            // been set. This way we don't need to edit any object editor data." + JASS_DELIM +
						"            set s__File_AbilityList[0]='Amls'" + JASS_DELIM +
						"            set s__File_AbilityList[1]='Aroc'" + JASS_DELIM +
						"            set s__File_AbilityList[2]='Amic'" + JASS_DELIM +
						"            set s__File_AbilityList[3]='Amil'" + JASS_DELIM +
						"            set s__File_AbilityList[4]='Aclf'" + JASS_DELIM +
						"            set s__File_AbilityList[5]='Acmg'" + JASS_DELIM +
						"            set s__File_AbilityList[6]='Adef'" + JASS_DELIM +
						"            set s__File_AbilityList[7]='Adis'" + JASS_DELIM +
						"            set s__File_AbilityList[8]='Afbt'" + JASS_DELIM +
						"            set s__File_AbilityList[9]='Afbk'" + JASS_DELIM +
						"            set s__File_ReadEnabled=(s__File_readEx((s__File_write(s__File_open(\"FileTester.pld\"),\"FileIO_\")),true))==\"FileIO_\" // INLINED!!" + JASS_DELIM +
						"        endfunction" + JASS_DELIM +
						"    function FileIO_Write takes string filename,string contents returns nothing" + JASS_DELIM +
						"        call s__File_close(s__File_write(s__File_open(filename),contents))" + JASS_DELIM +
						"    endfunction" + JASS_DELIM +
						"    function FileIO_Read takes string filename returns string" + JASS_DELIM +
						"        return s__File_readEx(s__File_open(filename),true)" + JASS_DELIM +
						"    endfunction", sb);
				// endregion
				// region custom functions
				append("function " + ARCHON_PREFIX + "WatchSkill takes integer a returns boolean" + JASS_DELIM +
						"    if not(LoadBooleanBJ(StringHashBJ(\"" + GH_HAS_ABILITY + "\"),a," + GENERAL_HASHTABLE + ")) then" + JASS_DELIM +
						"        set " + ABILITY_ARRAY + "[" + ABILITY_ARRAY_SIZE + "]=a" + JASS_DELIM +
						"        call SaveBooleanBJ(true,StringHashBJ(\"" + GH_HAS_ABILITY + "\"),a," + GENERAL_HASHTABLE + ")" + JASS_DELIM +
						"        set " + ABILITY_ARRAY_SIZE + "=" + ABILITY_ARRAY_SIZE + "+1" + JASS_DELIM +
						"        return true" + JASS_DELIM +
						"    else" + JASS_DELIM +
						"        return false" + JASS_DELIM +
						"    " + END_IF + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function Trig_" + ARCHON_PREFIX + "WatchLearnedSkill_Actions takes nothing returns nothing" + JASS_DELIM +
						"    local integer i=0" + JASS_DELIM +
						"    local integer a=GetLearnedSkill()" + JASS_DELIM +
						"    if " + ARCHON_PREFIX + "WatchSkill(a)==true then" + JASS_DELIM +
						"        call DisplayTextToForce(GetPlayersAll(),I2S(i)+\"] \"+I2S(a)+\" _\"+I2S(" + ABILITY_ARRAY_SIZE + " - 1))" + JASS_DELIM +
						"        loop" + JASS_DELIM +
						"            exitwhen i>=" + ABILITY_ARRAY_SIZE + JASS_DELIM +
						"            call DisplayTextToForce(GetPlayersAll(),I2S(i)+\") \"+I2S(" + ABILITY_ARRAY + "[i])+\" \"+I2S(" + ABILITY_ARRAY_SIZE + " - 1))" + JASS_DELIM +
						"            set i = i+1" + JASS_DELIM +
						"        endloop" + JASS_DELIM +
						"    " + END_IF + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function InitTrig_" + ARCHON_PREFIX + "WatchLearnedSkill takes nothing returns nothing" + JASS_DELIM +
						"    set " + TRIGGER_PREFIX + ARCHON_PREFIX + "WatchLearnedSkill = CreateTrigger()" + JASS_DELIM +
						"    call TriggerRegisterAnyUnitEventBJ(" + TRIGGER_PREFIX + ARCHON_PREFIX + "WatchLearnedSkill,EVENT_PLAYER_HERO_SKILL)" + JASS_DELIM +
						"    call TriggerAddAction(" + TRIGGER_PREFIX + ARCHON_PREFIX + "WatchLearnedSkill,function Trig_" + ARCHON_PREFIX + "WatchLearnedSkill_Actions)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "S2B takes string s returns boolean" + JASS_DELIM +
						"    if s==\"t\" then" + JASS_DELIM +
						"        return true" + JASS_DELIM +
						"    endif" + JASS_DELIM +
						"    return false" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "B2S takes boolean b returns string" + JASS_DELIM +
						"    if b then" + JASS_DELIM +
						"        return \"t\"" + JASS_DELIM +
						"    endif" + JASS_DELIM +
						"    return \"f\"" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "WriteString takes string cacheName,string title,string v returns nothing" + JASS_DELIM +
						"    call FileIO_Write(\"XT87CampaignSplitter\\\\\"+cacheName+\"\\\\\"+title+\".pld\",v)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "SyncContents takes string path returns nothing" + JASS_DELIM +
						"    local string content=null" + JASS_DELIM +
						"    set XT87ReadLinesAreRead=false" + JASS_DELIM +
						"    if GetLocalPlayer()==" + toMainPlayerFunc() + " then" + JASS_DELIM +
						"        set content=(s__File_readEx(s__File_open((path)),true)) // INLINED!!" + JASS_DELIM +
						"        if content==null then" + JASS_DELIM +
						"            set content=\"\"" + JASS_DELIM +
						"        endif" + JASS_DELIM +
						"        call DisplayTextToForce(GetPlayersAll(),\">\"+content+\"<\")" + JASS_DELIM +
						"        call BlzSendSyncData(\"XTContents\",content)" + JASS_DELIM +
						"    endif" + JASS_DELIM +
						"    loop" + JASS_DELIM +
						"        exitwhen XT87ReadLinesAreRead" + JASS_DELIM +
						"        call TriggerSleepAction(bj_WAIT_FOR_COND_MIN_INTERVAL)" + JASS_DELIM +
						"    endloop" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "ReadString takes string cacheName,string title returns string" + JASS_DELIM +
						"    call " + ARCHON_PREFIX + "SyncContents(\"XT87CampaignSplitter\\\\\"+cacheName+\"\\\\\"+title+\".pld\")" + JASS_DELIM +
						"    return XT87ReadLines[0]" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function " + ARCHON_PREFIX + "ReadUnit takes string cacheName,string title returns integer" + JASS_DELIM +
						"    local integer i" + JASS_DELIM +
						"    if GetPlayerSlotState(" + toMainPlayerFunc() + ")!=PLAYER_SLOT_STATE_PLAYING then" + JASS_DELIM +
						"        return 0" + JASS_DELIM +
						END_IF + JASS_DELIM +
						"    call " + ARCHON_PREFIX + "SyncContents(\"XT87CampaignSplitter\\\\\"+cacheName+\"\\\\\"+title+\".pld\")" + JASS_DELIM +
						"    if XT87ReadLines[0]==null then" + JASS_DELIM +
						"        return 0" + JASS_DELIM +
						END_IF + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"0)\"+XT87ReadLines[0])" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"1)\"+XT87ReadLines[1])" + JASS_DELIM +
						"    call DisplayTextToForce(GetPlayersAll(),\"2)\"+XT87ReadLines[2])" + JASS_DELIM +
						"    return S2I(XT87ReadLines[0])" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function Trig_" + ARCHON_PREFIX + "SyncData_Actions takes nothing returns nothing" + JASS_DELIM +
						"    local string contents=BlzGetTriggerSyncData()" + JASS_DELIM +
						"    local integer len=StringLength(contents)" + JASS_DELIM +
						"    local string char=null" + JASS_DELIM +
						"    local string buffer=\"\"" + JASS_DELIM +
						"    local integer curLine=0" + JASS_DELIM +
						"    local integer i=0" + JASS_DELIM +
						"    if len<=0 then" + JASS_DELIM +
						"        set XT87ReadLines[0]=null" + JASS_DELIM +
						"    else" + JASS_DELIM +
						"        loop" + JASS_DELIM +
						"            exitwhen i > len" + JASS_DELIM +
						"            set char=SubString(contents,i,i+1)" + JASS_DELIM +
						"            if ( char==\"\\n\" ) then" + JASS_DELIM +
						"                set XT87ReadLines[curLine]=buffer" + JASS_DELIM +
						"                set curLine=curLine+1" + JASS_DELIM +
						"                set buffer=\"\"" + JASS_DELIM +
						"            else" + JASS_DELIM +
						"                set buffer=buffer+char" + JASS_DELIM +
						"            endif" + JASS_DELIM +
						"        set i=i+1" + JASS_DELIM +
						"        endloop" + JASS_DELIM +
						"        set XT87ReadLines[curLine]=buffer" + JASS_DELIM +
						"    endif" + JASS_DELIM +
						"    set XT87ReadLinesAreRead=true" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"function InitTrig_" + ARCHON_PREFIX + "SyncData takes nothing returns nothing" + JASS_DELIM +
						"    local integer i=0" + JASS_DELIM +
						"    set " + TRIGGER_PREFIX + ARCHON_PREFIX + "SyncData=CreateTrigger()" + JASS_DELIM +
						"    loop" + JASS_DELIM +
						"        call BlzTriggerRegisterPlayerSyncEvent(" + TRIGGER_PREFIX + ARCHON_PREFIX + "SyncData,Player(i),\"XTContents\",false)" + JASS_DELIM +
						"        set i=i+1" + JASS_DELIM +
						"        exitwhen i==bj_MAX_PLAYER_SLOTS" + JASS_DELIM +
						"    endloop" + JASS_DELIM +
						"    call TriggerAddAction(" + TRIGGER_PREFIX + ARCHON_PREFIX + "SyncData,function Trig_" + ARCHON_PREFIX + "SyncData_Actions)" + JASS_DELIM +
						END_FUNCTION, sb);
				// endregion
			}
		} else if (insideInitPlayers) {
			if (line.equals(END_FUNCTION)) {
				insideInitPlayers = false;
//			} else if (line.contains(toMainPlayerFunc())) {
//				for (W3I.Player player : secondaryPlayers) {
//					append(line.replace(toMainPlayerFunc(), toPlayerFunc(player.getNum())), sb);
//				}
			}
		} else if (line.contains("function InitCustomPlayerSlots takes") || line.contains("function InitCustomTeams takes")
			//|| line.contains("function InitAllyPriorities takes")
		) {
			insideInitPlayers = true;
		} else if (line.contains("function InitCustomTriggers takes")) {
			for (String resourceName : ALL_RESOURCE_NAMES)
				append("    call InitTrig_" + ARCHON_PREFIX + resourceName + "Sync()", sb);
			append("    call InitTrig_" + ARCHON_PREFIX + "UnitShare()" + JASS_DELIM, sb);
			if (withCustomGameCache) {
				append("    call InitTrig_" + ARCHON_PREFIX + "SyncData()" + JASS_DELIM +
						"    call InitTrig_" + ARCHON_PREFIX + "WatchLearnedSkill()" + JASS_DELIM +
						"    call ExecuteFunc(\"s__File_FileIO___FileInit__onInit\")", sb);
			}
		}
		else if (line.contains("SetForceAllianceStateBJ("))
			append("call " + ENFORCE_ARCHON + "()", sb);

		if (line.contains(DEFINE_START_LOC + mainPlayer.getNum() + ",")) {
			for (W3I.Player player : secondaryPlayers) {
				append(line.replace(DEFINE_START_LOC + mainPlayer.getNum(), DEFINE_START_LOC + player.getNum()), sb);
			}
		}
	}
}
