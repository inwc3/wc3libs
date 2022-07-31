package net.xetanth87.campaignsplitter;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class NextLevelRewriter extends ScriptRewriter {
	public int campaignKeyOffset;
	public boolean insideInitGlobals;
	public static final String SET_LEVEL_CALL = "call SetNextLevelBJ(";
	public static final String VICTORY_CALL = "call CustomVictoryBJ(";

	public NextLevelRewriter(MapInjector mi, int campaignKeyOffset) {
		super(mi);
		this.campaignKeyOffset = campaignKeyOffset;
	}

	@Override
	public void onStartRead(String text, StringBuffer sb) {
		insideInitGlobals = false;
	}

	@Override
	public void onReadLine(String line, StringBuffer sb) {
		if (insideInitGlobals) {
			if (line.equals(END_FUNCTION)) {
				insideInitGlobals = false;
				sb.append(JASS_DELIM +
						"    set udg_XT87CSNextLevel=\"\"");
			}
		}
		if (line.contains(VICTORY_CALL)) {
			line = line.substring(line.indexOf(VICTORY_CALL) + VICTORY_CALL.length());
			String[] words = line.substring(0, line.lastIndexOf(')')).split(",");
			sb.append(JASS_DELIM +
					"    " + VICTORY_CALL + words[0] + ", true," + words[2] +  ")" + JASS_DELIM +
							"    if ( udg_XT87CSNextLevel != \"\" ) then" + JASS_DELIM +
							"        call DisplayTimedTextToForce( GetForceOfPlayer(" + words[0] + "), 0.00, \"The next map is:\\n|cffffcc00\" + udg_XT87CSNextLevel + \"|r\" )" + JASS_DELIM +
							"    else" + JASS_DELIM +
							"    endif");
			return;
		}
		append(line, sb);
		if (line.equals("globals")) {
			sb.append(JASS_DELIM +
					"string udg_XT87CSNextLevel");
		} else if (line.contains("function InitGlobals takes")) {
			insideInitGlobals = true;
		} else if (line.contains(SET_LEVEL_CALL)) {
			line = line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\"")).toLowerCase();
			sb.append(JASS_DELIM +
							"set udg_XT87CSNextLevel=\"" + mi.cs.buttonNameMap.get(line) + "\"");
		}
	}
}
