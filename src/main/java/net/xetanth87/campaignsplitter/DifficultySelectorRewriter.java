package net.xetanth87.campaignsplitter;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DifficultySelectorRewriter extends ScriptRewriter {
	public int difficultyStringOffset;
	public int playerId;
	public Set<String> initializationTriggers = null;
	public boolean triggersAdded, insideInitialization, insideInitGlobals;

	public DifficultySelectorRewriter(MapInjector mi, int difficultyStringOffset, int playerId)
	{
		super(mi);
		this.difficultyStringOffset = difficultyStringOffset;
		this.playerId = playerId;
	}

	public Set<String> findInitializationTriggers(String s) {
		Scanner sc = new Scanner(s);
		Set<String> initializationTriggers = new HashSet<>();
		boolean insideInitialization = false;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (insideInitialization) {
				if (line.equals(END_FUNCTION))
					insideInitialization = false;
				else if (line.contains(INIT_CALL))
					initializationTriggers.add(initializationTriggerFromCall(line));
			} else if (isInitFunction(line))
				insideInitialization = true;
		}
		sc.close();
		return initializationTriggers;
	}

	@Override
	public void onStartRead(String text, StringBuffer sb) {
		initializationTriggers = findInitializationTriggers(text);
		triggersAdded = false;
		insideInitialization = false;
		insideInitGlobals = false;
	}

	@Override
	public void onReadLine(String line, StringBuffer sb) {
		if (insideInitialization)
		{
			if (line.equals(END_FUNCTION)) {
				sb.append(JASS_DELIM +
						"    call CinematicFadeBJ(bj_CINEFADETYPE_FADEOUT, 0.00, \"ReplaceableTextures\\\\CameraMasks\\\\Black_mask.blp\", 0, 0, 0, 0)");
				insideInitialization = false;
			}
			else if (line.contains(INIT_CALL))
				return;
		}
		else if (insideInitGlobals)
		{
			if (line.equals(END_FUNCTION)) {
				insideInitGlobals = false;
				sb.append(JASS_DELIM +
						"    set udg_XT87CSDifDiag=DialogCreate()" + JASS_DELIM +
						"    set udg_XT87CSMeleeInitialization=CreateTimer()");
			}
		}
		if (!triggersAdded && line.contains("function Trig"))
		{
			sb.append(JASS_DELIM +
					"//===========================================================================" + JASS_DELIM +
					"// Trigger: XT87CS Dif Init" + JASS_DELIM +
					"//===========================================================================" + JASS_DELIM +
					"function Trig_XT87CS_Dif_Init_Actions takes nothing returns nothing" + JASS_DELIM +
					"    call PauseGameOn()" + JASS_DELIM +
					"    call DialogSetMessageBJ(udg_XT87CSDifDiag, \"" + XT87Utils.STRING_PREFIX + XT87Utils.zeroedNumber(difficultyStringOffset, 3) + "\")" + JASS_DELIM +
					"    call DialogAddButtonBJ(udg_XT87CSDifDiag, \"" + XT87Utils.STRING_PREFIX + XT87Utils.zeroedNumber(difficultyStringOffset + 1, 3) + "\")" + JASS_DELIM +
					"    set udg_XT87CSDifDiagEasy=GetLastCreatedButtonBJ()" + JASS_DELIM +
					"    call DialogAddButtonBJ(udg_XT87CSDifDiag, \"" + XT87Utils.STRING_PREFIX + XT87Utils.zeroedNumber(difficultyStringOffset + 2, 3) + "\")" + JASS_DELIM +
					"    set udg_XT87CSDifDiagNormal=GetLastCreatedButtonBJ()" + JASS_DELIM +
					"    call DialogAddButtonBJ(udg_XT87CSDifDiag, \"" + XT87Utils.STRING_PREFIX + XT87Utils.zeroedNumber(difficultyStringOffset + 3, 3) + "\")" + JASS_DELIM +
					"    set udg_XT87CSDifDiagHard=GetLastCreatedButtonBJ()" + JASS_DELIM +
					"    call DialogDisplayBJ(true, udg_XT87CSDifDiag, Player(" + playerId + "))" + JASS_DELIM +
					END_FUNCTION + JASS_DELIM +
					JASS_DELIM +
					"//===========================================================================" + JASS_DELIM +
					"function InitTrig_XT87CS_Dif_Init takes nothing returns nothing" + JASS_DELIM +
					"    set gg_trg_XT87CS_Dif_Init=CreateTrigger()" + JASS_DELIM +
					"    call TriggerRegisterTimerEventSingle(gg_trg_XT87CS_Dif_Init, 0.00)" + JASS_DELIM +
					"    call TriggerAddAction(gg_trg_XT87CS_Dif_Init, function Trig_XT87CS_Dif_Init_Actions)" + JASS_DELIM +
					END_FUNCTION + JASS_DELIM +
					JASS_DELIM +
					"//===========================================================================" + JASS_DELIM +
					"// Trigger: XT87CS Dif Click" + JASS_DELIM +
					"//===========================================================================" + JASS_DELIM +
					"function Trig_XT87CS_Dif_Click_Func001Func001Func001C takes nothing returns boolean" + JASS_DELIM +
					"    if ( not ( GetClickedButtonBJ() == udg_XT87CSDifDiagHard ) ) then" + JASS_DELIM +
					"        return false" + JASS_DELIM +
					"    endif" + JASS_DELIM +
					"    return true" + JASS_DELIM +
					END_FUNCTION + JASS_DELIM +
					JASS_DELIM +
					"function Trig_XT87CS_Dif_Click_Func001Func001C takes nothing returns boolean" + JASS_DELIM +
					"    if ( not ( GetClickedButtonBJ() == udg_XT87CSDifDiagNormal ) ) then" + JASS_DELIM +
					"        return false" + JASS_DELIM +
					"    endif" + JASS_DELIM +
					"    return true" + JASS_DELIM +
					END_FUNCTION + JASS_DELIM +
					JASS_DELIM +
					"function Trig_XT87CS_Dif_Click_Func001C takes nothing returns boolean" + JASS_DELIM +
					"    if ( not ( GetClickedButtonBJ() == udg_XT87CSDifDiagEasy ) ) then" + JASS_DELIM +
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
					"    call PauseGameOff()" + JASS_DELIM +
					"    call CinematicFadeBJ(bj_CINEFADETYPE_FADEIN, 0.00, \"ReplaceableTextures\\\\CameraMasks\\\\Black_mask.blp\", 0, 0, 0, 0)" + JASS_DELIM +
					"    call StartTimerBJ(udg_XT87CSMeleeInitialization, false, 0.00)" + JASS_DELIM +
					END_FUNCTION + JASS_DELIM +
					JASS_DELIM +
					"//===========================================================================" + JASS_DELIM +
					"function InitTrig_XT87CS_Dif_Click takes nothing returns nothing" + JASS_DELIM +
					"    set gg_trg_XT87CS_Dif_Click=CreateTrigger()" + JASS_DELIM +
					"    call TriggerRegisterDialogEventBJ(gg_trg_XT87CS_Dif_Click, udg_XT87CSDifDiag)" + JASS_DELIM +
					"    call TriggerAddAction(gg_trg_XT87CS_Dif_Click, function Trig_XT87CS_Dif_Click_Actions)" + JASS_DELIM +
					END_FUNCTION + JASS_DELIM +
					JASS_DELIM +
					"//===========================================================================");
			triggersAdded = true;
		}
		append(line, sb);
		if (line.equals("globals"))
		{
			sb.append(JASS_DELIM +
					"dialog udg_XT87CSDifDiag= null" + JASS_DELIM +
					"button udg_XT87CSDifDiagEasy= null" + JASS_DELIM +
					"button udg_XT87CSDifDiagNormal= null" + JASS_DELIM +
					"button udg_XT87CSDifDiagHard= null" + JASS_DELIM +
					"timer udg_XT87CSMeleeInitialization= null" + JASS_DELIM +
					"trigger gg_trg_XT87CS_Dif_Init= null" + JASS_DELIM +
					"trigger gg_trg_XT87CS_Dif_Click= null");
		}
		else if (line.contains("function InitGlobals takes"))
		{
			insideInitGlobals = true;
		}
		else if (line.contains("function InitCustomTriggers takes"))
		{
			sb.append(JASS_DELIM +
					"    call InitTrig_XT87CS_Dif_Init()" + JASS_DELIM +
					"    call InitTrig_XT87CS_Dif_Click()");
		}
		else if (line.contains("function RunInitializationTriggers takes") || line.contains("function main takes"))
		{
			insideInitialization = true;
		}
		else
		{
			for (String trigger : initializationTriggers)
			{
				if (line.contains("set " + trigger))
				{
					sb.append(JASS_DELIM +
							"call TriggerRegisterTimerExpireEventBJ(" + trigger + ", udg_XT87CSMeleeInitialization )");
					initializationTriggers.remove(trigger);
					break;
				}
			}
		}
	}
}
