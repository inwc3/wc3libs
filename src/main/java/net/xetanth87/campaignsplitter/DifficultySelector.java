package net.xetanth87.campaignsplitter;

import net.moonlightflower.wc3libs.bin.app.W3I;
import net.moonlightflower.wc3libs.txt.app.jass.Jass;
import systems.crigges.jmpq3.JMpqEditor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DifficultySelector {
	public static final String JASS_DELIM = "\r\n";
	public static final String END_FUNCTION = "endfunction";
	public static final String INIT_CALL = "call ConditionalTriggerExecute(";

	public static String initializationTriggerFromCall(String line)
	{
		String s = line.trim().replace(INIT_CALL, "");
		s = s.substring(0, s.indexOf(')')).trim();
		return s;
	}

	public static boolean isInitFunction(String line)
	{
		return line.contains("function RunInitializationTriggers takes") || line.contains("function main takes");
	}

	public static Set<String> findInitializationTriggers(String s)
	{
		Scanner sc = new Scanner(s);
		Set<String> initializationTriggers = new HashSet<>();
		boolean insideInitialization = false;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (insideInitialization)
			{
				if (line.equals(END_FUNCTION))
					insideInitialization = false;
				else if (line.contains(INIT_CALL))
					initializationTriggers.add(initializationTriggerFromCall(line));
			}
			else if (isInitFunction(line))
				insideInitialization = true;
		}
		sc.close();
		return initializationTriggers;
	}

	public static void addDifficultySelectionToFile(File extractedFile, int difficultyStringOffset, int playerId) throws IOException {
		Scanner sc = new Scanner(extractedFile);
		StringBuffer sb = new StringBuffer();
		boolean first = true;
		while (sc.hasNextLine()) {
			if (first)
				first = false;
			else
				sb.append(JASS_DELIM);
			sb.append(sc.nextLine());
		}
		sc.close();
		String s = sb.toString();
		Set<String> initializationTriggers = findInitializationTriggers(s);
		sc = new Scanner(s);
		sb = new StringBuffer();
		first = true;
		boolean triggersAdded = false, insideInitialization = false, insideInitGlobals = false;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (insideInitialization)
			{
				if (line.equals(END_FUNCTION))
					insideInitialization = false;
				else if (line.contains(INIT_CALL))
					continue;
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
						"function Trig_XT87CS_Dif_Init_Actions takes nothing returns nothing" + JASS_DELIM +
						"    call DialogSetMessageBJ(udg_XT87CSDifDiag, \"" + XT87Utils.STRING_PREFIX + XT87Utils.zeroedNumber(difficultyStringOffset, 3) + "\")" + JASS_DELIM +
						"    call DialogAddButtonBJ(udg_XT87CSDifDiag, \"" + XT87Utils.STRING_PREFIX + XT87Utils.zeroedNumber(difficultyStringOffset + 1, 3) + "\")" + JASS_DELIM +
						"    set udg_XT87CSDifDiagEasy=GetLastCreatedButtonBJ()" + JASS_DELIM +
						"    call DialogAddButtonBJ(udg_XT87CSDifDiag, \"" + XT87Utils.STRING_PREFIX + XT87Utils.zeroedNumber(difficultyStringOffset + 2, 3) + "\")" + JASS_DELIM +
						"    set udg_XT87CSDifDiagNormal=GetLastCreatedButtonBJ()" + JASS_DELIM +
						"    call DialogAddButtonBJ(udg_XT87CSDifDiag, \"" + XT87Utils.STRING_PREFIX + XT87Utils.zeroedNumber(difficultyStringOffset + 3, 3) + "\")" + JASS_DELIM +
						"    set udg_XT87CSDifDiagHard=GetLastCreatedButtonBJ()" + JASS_DELIM +
						"    call DialogDisplayBJ(true, udg_XT87CSDifDiag, Player(" + playerId + "))" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"" + JASS_DELIM +
						"//===========================================================================" + JASS_DELIM +
						"function InitTrig_XT87CS_Dif_Init takes nothing returns nothing" + JASS_DELIM +
						"    set gg_trg_XT87CS_Dif_Init=CreateTrigger()" + JASS_DELIM +
						"    call TriggerRegisterTimerEventSingle(gg_trg_XT87CS_Dif_Init, 0.00)" + JASS_DELIM +
						"    call TriggerAddAction(gg_trg_XT87CS_Dif_Init, function Trig_XT87CS_Dif_Init_Actions)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"" + JASS_DELIM +
						"//===========================================================================" + JASS_DELIM +
						"// Trigger: XT87CS Dif Click" + JASS_DELIM +
						"//===========================================================================" + JASS_DELIM +
						"function Trig_XT87CS_Dif_Click_Func001Func001Func001C takes nothing returns boolean" + JASS_DELIM +
						"    if ( not ( GetClickedButtonBJ() == udg_XT87CSDifDiagHard ) ) then" + JASS_DELIM +
						"        return false" + JASS_DELIM +
						"    endif" + JASS_DELIM +
						"    return true" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"" + JASS_DELIM +
						"function Trig_XT87CS_Dif_Click_Func001Func001C takes nothing returns boolean" + JASS_DELIM +
						"    if ( not ( GetClickedButtonBJ() == udg_XT87CSDifDiagNormal ) ) then" + JASS_DELIM +
						"        return false" + JASS_DELIM +
						"    endif" + JASS_DELIM +
						"    return true" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"" + JASS_DELIM +
						"function Trig_XT87CS_Dif_Click_Func001C takes nothing returns boolean" + JASS_DELIM +
						"    if ( not ( GetClickedButtonBJ() == udg_XT87CSDifDiagEasy ) ) then" + JASS_DELIM +
						"        return false" + JASS_DELIM +
						"    endif" + JASS_DELIM +
						"    return true" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"" + JASS_DELIM +
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
						"    call StartTimerBJ(udg_XT87CSMeleeInitialization, false, 0.00)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"" + JASS_DELIM +
						"//===========================================================================" + JASS_DELIM +
						"function InitTrig_XT87CS_Dif_Click takes nothing returns nothing" + JASS_DELIM +
						"    set gg_trg_XT87CS_Dif_Click=CreateTrigger()" + JASS_DELIM +
						"    call TriggerRegisterDialogEventBJ(gg_trg_XT87CS_Dif_Click, udg_XT87CSDifDiag)" + JASS_DELIM +
						"    call TriggerAddAction(gg_trg_XT87CS_Dif_Click, function Trig_XT87CS_Dif_Click_Actions)" + JASS_DELIM +
						END_FUNCTION + JASS_DELIM +
						"" + JASS_DELIM +
						"//===========================================================================");
				triggersAdded = true;
			}
			if (first)
				first = false;
			else
				sb.append(JASS_DELIM);
			sb.append(line);
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

		FileWriter writer = new FileWriter(extractedFile);
		writer.append(sb.toString());
		writer.close();
	}

	public static void addDifficultySelection(JMpqEditor mapEditor, String tempPath, int difficultyStringOffset, int playerId) throws IOException {
		File extractedFile = new File(tempPath + "_" + Jass.GAME_PATH);
		XT87Utils.createNewFile(extractedFile);
		Jass jass;
		try {
			mapEditor.extractFile(Jass.GAME_PATH.getName(), extractedFile);
			addDifficultySelectionToFile(extractedFile, difficultyStringOffset, playerId);
			mapEditor.insertFile(Jass.GAME_PATH.getName(), extractedFile, false, true);
		} catch (Exception e) {
		} finally {
			extractedFile.delete();
		}
	}
}
