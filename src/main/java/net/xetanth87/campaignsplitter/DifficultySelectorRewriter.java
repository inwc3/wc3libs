package net.xetanth87.campaignsplitter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DifficultySelectorRewriter extends ScriptRewriter {
	public int playerId;
	public Set<String> initializationTriggers = null, periodicTimeTriggers = null;
	public Map<String, Integer> elapsedTimeIndices = null;
	public boolean triggersAdded, insideInitialization, insideInitGlobals;
	public static final String ELAPSED_TIME_REGISTER = "call TriggerRegisterTimerEventSingle(";
	public static final String ELAPSED_TIMER_PREFIX = "XT87CSElapsedTimer_";
	public static final String PERIODIC_TIME_REGISTER = "call TriggerRegisterTimerEventPeriodic(";

	public DifficultySelectorRewriter(MapInjector mi, int playerId) {
		super(mi);
		this.playerId = playerId;
	}

	public String[] timeParams(String line, String call) {
		String[] params = getParams(line, call);
		return params;
	}

	public String[] elapsedTimeParams(String line) {
		return timeParams(line, ELAPSED_TIME_REGISTER);
	}

	public String[] periodicTimeParams(String line) {
		return timeParams(line, PERIODIC_TIME_REGISTER);
	}

	public void findInitializationTriggers(String text) {
		Scanner sc = new Scanner(text);
		initializationTriggers = new HashSet<>();
		elapsedTimeIndices = new HashMap<>();
		periodicTimeTriggers = new HashSet<>();
		boolean insideInitialization = false, insideTriggerCreation = false, disabledTrigger = false;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (insideInitialization) {
				if (line.equals(END_FUNCTION))
					insideInitialization = false;
				else if (line.contains(INIT_CALL))
					initializationTriggers.add(initializationTriggerFromCall(line));
			} else if (isInitFunction(line))
				insideInitialization = true;

			if (insideTriggerCreation) {
				if (line.equals(END_FUNCTION)) {
					insideTriggerCreation = false;
					disabledTrigger = false;
				}
				else if (line.contains(ELAPSED_TIME_REGISTER)) {
					String[] params = elapsedTimeParams(line);
					if (!elapsedTimeIndices.containsKey(params[1]))
						elapsedTimeIndices.put(params[1], elapsedTimeIndices.size());
				}
				else if (line.contains(XT87Utils.DISABLE_TRIGGER))
					disabledTrigger = true;
				else if (!disabledTrigger && line.contains(PERIODIC_TIME_REGISTER)) {
					String[] params = periodicTimeParams(line);
					periodicTimeTriggers.add(params[0]);
				}
			} else if (line.contains("CreateTrigger(")) {
				insideTriggerCreation = true;
			}
		}
		sc.close();
	}

	@Override
	public void onStartRead(String text, StringBuffer sb) {
		findInitializationTriggers(text);
		triggersAdded = false;
		insideInitialization = false;
		insideInitGlobals = false;
	}

	@Override
	public void onReadLine(String line, StringBuffer sb) {
		if (insideInitialization) {
			if (line.contains(END_FUNCTION)) {
				sb.append(JASS_DELIM +
						"    call CinematicFadeBJ(bj_CINEFADETYPE_FADEOUT, 0.00, \"ReplaceableTextures\\\\CameraMasks\\\\Black_mask.blp\", 0, 0, 0, 0)");
				insideInitialization = false;
			} else if (line.contains(INIT_CALL))
				return;
		} else if (insideInitGlobals) {
			if (line.contains(END_FUNCTION)) {
				insideInitGlobals = false;
				sb.append(JASS_DELIM +
						"    set udg_XT87CSDifDialog=DialogCreate()" + JASS_DELIM +
						"    set udg_XT87CSMapInitialization=CreateTimer()");
				for (int i = 0; i < elapsedTimeIndices.size(); i++)
					sb.append(JASS_DELIM +
							"    set udg_" + ELAPSED_TIMER_PREFIX + i + "=CreateTimer()");
			}
		}
		if (line.contains(ELAPSED_TIME_REGISTER)) {
			String[] params = elapsedTimeParams(line);
			line = "    call TriggerRegisterTimerExpireEventBJ(" + params[0] + ", udg_" + ELAPSED_TIMER_PREFIX + elapsedTimeIndices.get(params[1]) + " )";
		}
		else if (line.contains(PERIODIC_TIME_REGISTER)) {
			String[] params = periodicTimeParams(line);
			if (periodicTimeTriggers.contains(params[0]))
				sb.append(JASS_DELIM +
						"    " + XT87Utils.DISABLE_TRIGGER + params[0] + ")");
		}
		else if (!triggersAdded && line.contains("function Trig")) {
			sb.append(JASS_DELIM +
					"//===========================================================================" + JASS_DELIM +
					"// Trigger: XT87CS Dif Init" + JASS_DELIM +
					"//===========================================================================" + JASS_DELIM +
					"function Trig_XT87CS_Dif_Init_Actions takes nothing returns nothing" + JASS_DELIM +
					"    local string XT87CSDifDiagEasyName=GetLocalizedString(\"EASY\")" + JASS_DELIM +
					"    call PauseGameOn()" + JASS_DELIM +
					"    call DialogSetMessageBJ(udg_XT87CSDifDialog, GetLocalizedString(\"SELECT_DIFFICULTY_LEVEL\"))" + JASS_DELIM +
					"    if XT87CSDifDiagEasyName == \"Story\" then" + JASS_DELIM +
					"        set XT87CSDifDiagEasyName=\"Easy\"" + JASS_DELIM +
					"    endif" + JASS_DELIM +
					"    call DialogAddButtonBJ(udg_XT87CSDifDialog, \"|cff00ff00\" + XT87CSDifDiagEasyName + \"|r\")" + JASS_DELIM +
					"    set udg_XT87CSDifButtonEasy=GetLastCreatedButtonBJ()" + JASS_DELIM +
					"    call DialogAddButtonBJ(udg_XT87CSDifDialog, \"|cffffff00\" + GetLocalizedString(\"NORMAL\") + \"|r\")" + JASS_DELIM +
					"    set udg_XT87CSDifButtonNormal=GetLastCreatedButtonBJ()" + JASS_DELIM +
					"    call DialogAddButtonBJ(udg_XT87CSDifDialog, \"|cffff0000\" + GetLocalizedString(\"HARD\") + \"|r\")" + JASS_DELIM +
					"    set udg_XT87CSDifButttonHard=GetLastCreatedButtonBJ()" + JASS_DELIM +
					"    call DialogDisplayBJ(true, udg_XT87CSDifDialog, Player(" + playerId + "))" + JASS_DELIM +
					END_FUNCTION + JASS_DELIM +
					JASS_DELIM +
					"//===========================================================================" + JASS_DELIM +
					"function InitTrig_XT87CS_Dif_Init takes nothing returns nothing" + JASS_DELIM +
					"    set gg_trg_XT87CS_Dif_Init=CreateTrigger()" + JASS_DELIM +
					"    call TriggerRegisterTimerEventSingle(gg_trg_XT87CS_Dif_Init, 0.01)" + JASS_DELIM +
					"    call TriggerAddAction(gg_trg_XT87CS_Dif_Init, function Trig_XT87CS_Dif_Init_Actions)" + JASS_DELIM +
					END_FUNCTION + JASS_DELIM +
					JASS_DELIM +
					"//===========================================================================" + JASS_DELIM +
					"// Trigger: XT87CS Dif Click" + JASS_DELIM +
					"//===========================================================================" + JASS_DELIM +
					"function Trig_XT87CS_Dif_Click_Func001Func001Func001C takes nothing returns boolean" + JASS_DELIM +
					"    if ( not ( GetClickedButtonBJ() == udg_XT87CSDifButttonHard ) ) then" + JASS_DELIM +
					"        return false" + JASS_DELIM +
					"    endif" + JASS_DELIM +
					"    return true" + JASS_DELIM +
					END_FUNCTION + JASS_DELIM +
					JASS_DELIM +
					"function Trig_XT87CS_Dif_Click_Func001Func001C takes nothing returns boolean" + JASS_DELIM +
					"    if ( not ( GetClickedButtonBJ() == udg_XT87CSDifButtonNormal ) ) then" + JASS_DELIM +
					"        return false" + JASS_DELIM +
					"    endif" + JASS_DELIM +
					"    return true" + JASS_DELIM +
					END_FUNCTION + JASS_DELIM +
					JASS_DELIM +
					"function Trig_XT87CS_Dif_Click_Func001C takes nothing returns boolean" + JASS_DELIM +
					"    if ( not ( GetClickedButtonBJ() == udg_XT87CSDifButtonEasy ) ) then" + JASS_DELIM +
					"        return false" + JASS_DELIM +
					"    endif" + JASS_DELIM +
					"    return true" + JASS_DELIM +
					END_FUNCTION + JASS_DELIM +
					JASS_DELIM +
					"function Trig_XT87CS_Dif_Click_Actions takes nothing returns nothing" + JASS_DELIM +
					"    if ( Trig_XT87CS_Dif_Click_Func001C() ) then" + JASS_DELIM +
					"        call SetGameDifficulty(MAP_DIFFICULTY_EASY)" + JASS_DELIM +
					"    else" + JASS_DELIM +
					"        if ( Trig_XT87CS_Dif_Click_Func001Func001C() ) then" + JASS_DELIM +
					"            call SetGameDifficulty(MAP_DIFFICULTY_NORMAL)" + JASS_DELIM +
					"        else" + JASS_DELIM +
					"            if ( Trig_XT87CS_Dif_Click_Func001Func001Func001C() ) then" + JASS_DELIM +
					"                call SetGameDifficulty(MAP_DIFFICULTY_HARD)" + JASS_DELIM +
					"            else" + JASS_DELIM +
					"                call SetGameDifficulty(MAP_DIFFICULTY_INSANE)" + JASS_DELIM +
					"            endif" + JASS_DELIM +
					"        endif" + JASS_DELIM +
					"    endif" + JASS_DELIM +
					"    call DialogClear(udg_XT87CSDifDialog)" + JASS_DELIM +
					"    call DialogDestroy(udg_XT87CSDifDialog)" + JASS_DELIM +
					"    call PauseGameOff()" + JASS_DELIM +
					"    call CinematicFadeBJ(bj_CINEFADETYPE_FADEIN, 0.00, \"ReplaceableTextures\\\\CameraMasks\\\\Black_mask.blp\", 0, 0, 0, 0)" + JASS_DELIM +
					"    call StartTimerBJ(udg_XT87CSMapInitialization, false, 0.00)");
			if (!elapsedTimeIndices.isEmpty() || !periodicTimeTriggers.isEmpty())
				sb.append(JASS_DELIM +
						"    call TriggerSleepAction( 0.01 )");
			for (String elapsedTime : elapsedTimeIndices.keySet())
				sb.append(JASS_DELIM +
						"    call StartTimerBJ(udg_" + ELAPSED_TIMER_PREFIX + elapsedTimeIndices.get(elapsedTime) + ", false, " + elapsedTime + ")");
			for (String periodicTimeTrigger : periodicTimeTriggers)
				sb.append(JASS_DELIM +
						"    " + XT87Utils.ENABLE_TRIGGER + periodicTimeTrigger + ")");
			sb.append(JASS_DELIM +
					END_FUNCTION + JASS_DELIM +
					JASS_DELIM +
					"//===========================================================================" + JASS_DELIM +
					"function InitTrig_XT87CS_Dif_Click takes nothing returns nothing" + JASS_DELIM +
					"    set gg_trg_XT87CS_Dif_Click=CreateTrigger()" + JASS_DELIM +
					"    call TriggerRegisterDialogEventBJ(gg_trg_XT87CS_Dif_Click, udg_XT87CSDifDialog)" + JASS_DELIM +
					"    call TriggerAddAction(gg_trg_XT87CS_Dif_Click, function Trig_XT87CS_Dif_Click_Actions)" + JASS_DELIM +
					END_FUNCTION + JASS_DELIM +
					JASS_DELIM +
					"//===========================================================================");
			triggersAdded = true;
		}
		append(line, sb);
		if (line.equals("globals")) {
			sb.append(JASS_DELIM +
					"dialog udg_XT87CSDifDialog= null" + JASS_DELIM +
					"button udg_XT87CSDifButtonEasy= null" + JASS_DELIM +
					"button udg_XT87CSDifButtonNormal= null" + JASS_DELIM +
					"button udg_XT87CSDifButttonHard= null" + JASS_DELIM +
					"timer udg_XT87CSMapInitialization= null" + JASS_DELIM +
					"trigger gg_trg_XT87CS_Dif_Init= null" + JASS_DELIM +
					"trigger gg_trg_XT87CS_Dif_Click= null");
			for (int i = 0; i < elapsedTimeIndices.size(); i++)
				sb.append(JASS_DELIM +
						"timer udg_" + ELAPSED_TIMER_PREFIX + i + "= null");
		} else if (line.contains("function InitGlobals takes")) {
			insideInitGlobals = true;
		} else if (line.contains("function InitCustomTriggers takes")) {
			sb.append(JASS_DELIM +
					"    call InitTrig_XT87CS_Dif_Init()" + JASS_DELIM +
					"    call InitTrig_XT87CS_Dif_Click()");
		} else if (line.contains("function RunInitializationTriggers takes") || line.contains("function main takes")) {
			insideInitialization = true;
		} else {
			for (String trigger : initializationTriggers) {
				if (line.contains("set " + trigger + "=") || line.contains("set " + trigger + " ")) {
					sb.append(JASS_DELIM +
							"    call TriggerRegisterTimerExpireEventBJ(" + trigger + ", udg_XT87CSMapInitialization )");
					initializationTriggers.remove(trigger);
					break;
				}
			}
		}
	}
}
