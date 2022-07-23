package net.xetanth87.campaignsplitter;

import net.moonlightflower.wc3libs.app.Minimap;

public class MinimapRewriter extends ScriptRewriter {
	public final String oldPath;
	public boolean insideInitialization;

	public MinimapRewriter(MapInjector mi, String oldPath)
	{
		super(mi);
		this.oldPath = oldPath;
	}

	public static String getNewPath(String oldPath)
	{
		return XT87Utils.PATH_PREFIX + "/" + oldPath;
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
						"    call BlzChangeMinimapTerrainTex(\"" + getNewPath(oldPath) + "\")", sb);
				insideInitialization = false;
			}
		}
		else if (line.contains("function RunInitializationTriggers takes") || line.contains("function main takes"))
		{
			insideInitialization = true;
		}
		// replaces the path to the original image with its new path
		append(line.replace(oldPath, getNewPath(oldPath)), sb);
	}
}
