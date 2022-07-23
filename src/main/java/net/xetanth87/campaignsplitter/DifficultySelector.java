package net.xetanth87.campaignsplitter;

import net.moonlightflower.wc3libs.txt.app.jass.Jass;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class DifficultySelector extends JassRewritter {
	public int difficultyStringOffset;
	public int playerId;
	public Set<String> initializationTriggers = null;
	public boolean first, triggersAdded, insideInitialization, insideInitGlobals;

	public DifficultySelector(File extractedFile, int difficultyStringOffset, int playerId)
	{
		super(extractedFile);
		this.difficultyStringOffset = difficultyStringOffset;
		this.playerId = playerId;
	}

	public static void addDifficultySelection(MapInjector mi, int difficultyStringOffset, int playerId) throws IOException {
		try {
			String path = null, tempPath, scriptsPath = "scripts\\", luaName = "war3map.lua";
			if (mi.mapEditor.hasFile(tempPath = Jass.GAME_PATH.getName()))
				path = tempPath;
			else if (mi.mapEditor.hasFile(tempPath = scriptsPath + Jass.GAME_PATH.getName()))
				path = tempPath;
			else if (mi.mapEditor.hasFile(luaName) || mi.mapEditor.hasFile(scriptsPath + luaName))
				throw new Exception("Difficulty selection won't be added to the map with lua script \"" + mi.mapFile.getName() + "\".");
			else
				throw new Exception("No JASS script found for map \"" + mi.mapFile.getName() + "\".");
			mi.mapEditor.extractFile(path, mi.tempFile);
			new DifficultySelector(mi.tempFile, difficultyStringOffset, playerId).rewriteFile();
			mi.mapEditor.insertFile(path, mi.tempFile, false, true);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
		}
	}

	@Override
	public void onStartRead(String text, StringBuffer sb) {
		initializationTriggers = findInitializationTriggers(text);
		first = true;
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
					"    call PauseGameOff()" + JASS_DELIM +
					"    call CinematicFadeBJ(bj_CINEFADETYPE_FADEIN, 0.00, \"ReplaceableTextures\\\\CameraMasks\\\\Black_mask.blp\", 0, 0, 0, 0)" + JASS_DELIM +
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
}
