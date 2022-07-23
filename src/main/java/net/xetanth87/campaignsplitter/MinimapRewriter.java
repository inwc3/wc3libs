package net.xetanth87.campaignsplitter;

import net.moonlightflower.wc3libs.app.Minimap;

public class MinimapRewriter extends ScriptRewriter {
	public final String newPath;
	public boolean insideInitialization;

	public MinimapRewriter(MapInjector mi, String newPath)
	{
		super(mi);
		this.newPath = newPath;
	}

	@Override
	public void onStartRead(String text, StringBuffer sb) {
		insideInitialization = false;
	}

	@Override
	public void onReadLine(String line, StringBuffer sb) {
		if (insideInitialization)
		{
			if (line.equals(END_FUNCTION)) {
				append(JASS_DELIM +
						"    call BlzChangeMinimapTerrainTex(\"" + newPath + "\")", sb);
				insideInitialization = false;
			}
		}
		else if (line.contains("function RunInitializationTriggers takes") || line.contains("function main takes"))
		{
			insideInitialization = true;
		}
		append(line, sb);
	}
}
