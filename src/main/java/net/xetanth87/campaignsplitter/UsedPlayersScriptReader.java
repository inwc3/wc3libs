package net.xetanth87.campaignsplitter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsedPlayersScriptReader extends ScriptRewriter {
	private final Set<Integer> usedSet;
	private final Set<Integer> unusedSet;

	public UsedPlayersScriptReader(MapInjector mi, Set<Integer> list) {
		super(mi);
		this.usedSet = list;
		unusedSet = new HashSet<>();
		for (int id = 0; id < XT87Utils.MAX_PLAYER_COUNT; id++) {
			if (!usedSet.contains(id))
				unusedSet.add(id);
		}
	}

	@Override
	public void onStartRead(String text, StringBuffer sb) {
	}

	@Override
	public void onReadLine(String line, StringBuffer sb) {
		Set<Integer> addedList = new HashSet<>();
		for (Integer id : unusedSet)
			if (line.contains("Player(" + id + ")") || line.contains("ConvertedPlayer(" + (id + 1) + ")"))
				addedList.add(id);
		for (Integer id : addedList) {
			usedSet.add(id);
			unusedSet.remove(id);
		}
	}

	public Set<Integer> getUsedSet() {
		return usedSet;
	}
}
