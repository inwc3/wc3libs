package net.moonlightflower.wc3libs.dataTypes.app;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class LoadingScreenBackground {
	public static class PresetBackground extends LoadingScreenBackground {
		private int curIndex = 0;
		private static Map<Integer, PresetBackground> _map = new HashMap<>();
		
		public final static PresetBackground TUTORIAL = new PresetBackground("TUTORIAL");
		public final static PresetBackground HUMAN = new PresetBackground("HUMAN");
		public final static PresetBackground ORC = new PresetBackground("ORC");
		public final static PresetBackground UNDEAD = new PresetBackground("UNDEAD");
		public final static PresetBackground NIGHTELF = new PresetBackground("NIGHTELF");
		public final static PresetBackground MAIN_MENU = new PresetBackground("MAINMENU");
		public final static PresetBackground BATTLE_NET = new PresetBackground("BATTLENET");
		public final static PresetBackground SCORESCREEN = new PresetBackground("SCORESCREEN");
		public final static PresetBackground NIGHTELF_EXP = new PresetBackground("NIGHTELFEXPANSION");
		public final static PresetBackground HUMAN_EXP = new PresetBackground("HUMANEXPANSION");
		public final static PresetBackground UNDEAD_EXP = new PresetBackground("UNDEADEXPANSION");
		public final static PresetBackground ORC_EXP = new PresetBackground("ORCEXPANSION");
		
		private int _index;
		
		public int getIndex() {
			return _index;
		}
		
		public static PresetBackground valueOf(int index) {
			return _map.get(index);
		}
		
		private String _label;

		public String getLabel() {
			return _label;
		}
		
		@Override
		public String toString() {
			return _label;
		}
		
		protected PresetBackground(String label, String otherPrefix) {
			int index = curIndex++;

			_map.put(index, this);

			_index = index;
			if (otherPrefix != null) {
				_label = String.format("%s%s", otherPrefix, label);
			} else {
				_label = String.format("WESTRING_CAMPAIGNSCREEN_%s", label);
			}
		}
		
		protected PresetBackground(String label) {
			this(label, null);
		}
	}

	public static class PresetBackgroundEx extends PresetBackground {
		public final static PresetBackgroundEx TUTORIAL0 = new PresetBackgroundEx("TUTORIAL01");
		public final static PresetBackgroundEx TUTORIAL1 = new PresetBackgroundEx("TUTORIAL02");
		
		public final static PresetBackgroundEx HUMAN1 = new PresetBackgroundEx("H01");
		public final static PresetBackgroundEx HUMAN2 = new PresetBackgroundEx("H02");
		public final static PresetBackgroundEx HUMAN2_INTERLUDE = new PresetBackgroundEx("H02_INTERLUDE");
		public final static PresetBackgroundEx HUMAN3 = new PresetBackgroundEx("H03");
		public final static PresetBackgroundEx HUMAN4 = new PresetBackgroundEx("H04");
		public final static PresetBackgroundEx HUMAN5 = new PresetBackgroundEx("H05");
		public final static PresetBackgroundEx HUMAN5_INTERLUDE = new PresetBackgroundEx("H05_INTERLUDE");
		public final static PresetBackgroundEx HUMAN6 = new PresetBackgroundEx("H06");
		public final static PresetBackgroundEx HUMAN6_INTERLUDE = new PresetBackgroundEx("H06_INTERLUDE");
		public final static PresetBackgroundEx HUMAN7 = new PresetBackgroundEx("H07");
		public final static PresetBackgroundEx HUMAN8 = new PresetBackgroundEx("H08");
		public final static PresetBackgroundEx HUMAN9 = new PresetBackgroundEx("H09");
		
		public final static PresetBackgroundEx UNDEAD1 = new PresetBackgroundEx("U01");
		public final static PresetBackgroundEx UNDEAD2 = new PresetBackgroundEx("U02");
		public final static PresetBackgroundEx UNDEAD2_INTERLUDE = new PresetBackgroundEx("U02_INTERLUDE");
		public final static PresetBackgroundEx UNDEAD3 = new PresetBackgroundEx("U03");
		public final static PresetBackgroundEx UNDEAD4 = new PresetBackgroundEx("U04");
		public final static PresetBackgroundEx UNDEAD5 = new PresetBackgroundEx("U05");
		public final static PresetBackgroundEx UNDEAD5_INTERLUDE = new PresetBackgroundEx("U05_INTERLUDE");
		public final static PresetBackgroundEx UNDEAD6 = new PresetBackgroundEx("U06");
		public final static PresetBackgroundEx UNDEAD7 = new PresetBackgroundEx("U07");
		public final static PresetBackgroundEx UNDEAD8 = new PresetBackgroundEx("U08");
		
		public final static PresetBackgroundEx ORC1 = new PresetBackgroundEx("O01");
		public final static PresetBackgroundEx ORC2 = new PresetBackgroundEx("O02");
		public final static PresetBackgroundEx ORC2_INTERLUDE = new PresetBackgroundEx("O02_INTERLUDE");
		public final static PresetBackgroundEx ORC3 = new PresetBackgroundEx("O03");
		public final static PresetBackgroundEx ORC4 = new PresetBackgroundEx("O04");
		public final static PresetBackgroundEx ORC4_INTERLUDE = new PresetBackgroundEx("O04_INTERLUDE");
		public final static PresetBackgroundEx ORC5 = new PresetBackgroundEx("O05");
		public final static PresetBackgroundEx ORC6 = new PresetBackgroundEx("O06");
		public final static PresetBackgroundEx ORC7 = new PresetBackgroundEx("O07");
		public final static PresetBackgroundEx ORC8 = new PresetBackgroundEx("O08");
		
		public final static PresetBackgroundEx NIGHTELF1 = new PresetBackgroundEx("N01");
		public final static PresetBackgroundEx NIGHTELF2 = new PresetBackgroundEx("N02");
		public final static PresetBackgroundEx NIGHTELF3 = new PresetBackgroundEx("N03");
		public final static PresetBackgroundEx NIGHTELF4 = new PresetBackgroundEx("N04");
		public final static PresetBackgroundEx NIGHTELF5 = new PresetBackgroundEx("N05");
		public final static PresetBackgroundEx NIGHTELF6 = new PresetBackgroundEx("N06");
		public final static PresetBackgroundEx NIGHTELF6_INTERLUDE = new PresetBackgroundEx("N06_INTERLUDE");
		public final static PresetBackgroundEx NIGHTELF7 = new PresetBackgroundEx("N07");
		public final static PresetBackgroundEx NIGHTELF7_INTERLUDE = new PresetBackgroundEx("N07_INTERLUDE");
		public final static PresetBackgroundEx NIGHTELF8 = new PresetBackgroundEx("N08");
		
		public final static PresetBackgroundEx CREDITS = new PresetBackgroundEx("CREDITS");
		public final static PresetBackgroundEx GENERIC = new PresetBackgroundEx("GENERIC");
		
		public final static PresetBackgroundEx NIGHTELF_EXP1 = new PresetBackgroundEx("NIGHTELFX01");
		public final static PresetBackgroundEx NIGHTELF_EXP2 = new PresetBackgroundEx("NIGHTELFX02");
		public final static PresetBackgroundEx NIGHTELF_EXP3 = new PresetBackgroundEx("NIGHTELFX03");
		public final static PresetBackgroundEx NIGHTELF_EXP4 = new PresetBackgroundEx("NIGHTELFX04");
		public final static PresetBackgroundEx NIGHTELF_EXP4_INTERLUDE = new PresetBackgroundEx("NIGHTELFX04_INTERLUDE");
		public final static PresetBackgroundEx NIGHTELF_EXP5 = new PresetBackgroundEx("NIGHTELFX05");
		public final static PresetBackgroundEx NIGHTELF_EXP6 = new PresetBackgroundEx("NIGHTELFX06");
		public final static PresetBackgroundEx NIGHTELF_EXP6_INTERLUDE = new PresetBackgroundEx("NIGHTELFX06_INTERLUDE");
		public final static PresetBackgroundEx NIGHTELF_EXP7 = new PresetBackgroundEx("NIGHTELFX07");
		public final static PresetBackgroundEx NIGHTELF_EXP8 = new PresetBackgroundEx("NIGHTELFX08");
		public final static PresetBackgroundEx NIGHTELF_EXP8_FINALE = new PresetBackgroundEx("NIGHTELFX08_FINALE");

		public final static PresetBackgroundEx HUMAN_EXP1 = new PresetBackgroundEx("HUMANX01");
		public final static PresetBackgroundEx HUMAN_EXP2 = new PresetBackgroundEx("HUMANX02");
		public final static PresetBackgroundEx HUMAN_EXP3 = new PresetBackgroundEx("HUMANX03");
		public final static PresetBackgroundEx HUMAN_EXP3_SECRET = new PresetBackgroundEx("HUMANX03_SECRET");
		public final static PresetBackgroundEx HUMAN_EXP3_INTERLUDE = new PresetBackgroundEx("HUMANX03_INTERLUDE");
		public final static PresetBackgroundEx HUMAN_EXP4 = new PresetBackgroundEx("HUMANX04");
		public final static PresetBackgroundEx HUMAN_EXP4_INTERLUDE = new PresetBackgroundEx("HUMANX04_INTERLUDE");
		public final static PresetBackgroundEx HUMAN_EXP5 = new PresetBackgroundEx("HUMANX05");
		public final static PresetBackgroundEx HUMAN_EXP6 = new PresetBackgroundEx("HUMANX06");
		public final static PresetBackgroundEx HUMAN_EXP6_FINALE = new PresetBackgroundEx("HUMANX06_FINALE");

		public final static PresetBackgroundEx UNDEAD_EXP1 = new PresetBackgroundEx("UNDEADX01");
		public final static PresetBackgroundEx UNDEAD_EXP1_INTERLUDE = new PresetBackgroundEx("UNDEADX01_INTERLUDE");
		public final static PresetBackgroundEx UNDEAD_EXP2 = new PresetBackgroundEx("UNDEADX02");
		public final static PresetBackgroundEx UNDEAD_EXP2_INTERLUDE = new PresetBackgroundEx("UNDEADX02_INTERLUDE");
		public final static PresetBackgroundEx UNDEAD_EXP3 = new PresetBackgroundEx("UNDEADX03");
		public final static PresetBackgroundEx UNDEAD_EXP4 = new PresetBackgroundEx("UNDEADX04");
		public final static PresetBackgroundEx UNDEAD_EXP5 = new PresetBackgroundEx("UNDEADX05");
		public final static PresetBackgroundEx UNDEAD_EXP6 = new PresetBackgroundEx("UNDEADX06");
		public final static PresetBackgroundEx UNDEAD_EXP7A = new PresetBackgroundEx("UNDEADX07A");
		public final static PresetBackgroundEx UNDEAD_EXP7B = new PresetBackgroundEx("UNDEADX07B");
		public final static PresetBackgroundEx UNDEAD_EXP7C = new PresetBackgroundEx("UNDEADX07C");
		public final static PresetBackgroundEx UNDEAD_EXP7_INTERLUDE = new PresetBackgroundEx("UNDEADX07_INTERLUDE");
		public final static PresetBackgroundEx UNDEAD_EXP8 = new PresetBackgroundEx("UNDEADX08");
		public final static PresetBackgroundEx UNDEAD_EXP8_FINALE = new PresetBackgroundEx("UNDEADX08_FINALE");
		
		public final static PresetBackgroundEx ORC_EXP1_1 = new PresetBackgroundEx("ORCX01_01");
		public final static PresetBackgroundEx ORC_EXP1_2 = new PresetBackgroundEx("ORCX01_02");
		public final static PresetBackgroundEx ORC_EXP1_3 = new PresetBackgroundEx("ORCX01_03");
		public final static PresetBackgroundEx ORC_EXP1_4 = new PresetBackgroundEx("ORCX01_04");
		public final static PresetBackgroundEx ORC_EXP1_5 = new PresetBackgroundEx("ORCX01_05");
		public final static PresetBackgroundEx ORC_EXP2_1 = new PresetBackgroundEx("ORCX02_01");
		public final static PresetBackgroundEx ORC_EXP2_2 = new PresetBackgroundEx("ORCX02_02");
		public final static PresetBackgroundEx ORC_EXP2_3 = new PresetBackgroundEx("ORCX02_03");
		public final static PresetBackgroundEx ORC_EXP2_4 = new PresetBackgroundEx("ORCX02_04");
		public final static PresetBackgroundEx ORC_EXP2_5 = new PresetBackgroundEx("ORCX02_05");
		public final static PresetBackgroundEx ORC_EXP2_6 = new PresetBackgroundEx("ORCX02_06");
		public final static PresetBackgroundEx ORC_EXP2_7 = new PresetBackgroundEx("ORCX02_07");
		public final static PresetBackgroundEx ORC_EXP2_8 = new PresetBackgroundEx("ORCX02_08");
		public final static PresetBackgroundEx ORC_EXP2_9 = new PresetBackgroundEx("ORCX02_09");
		public final static PresetBackgroundEx ORC_EXP2_10 = new PresetBackgroundEx("ORCX02_10");
		public final static PresetBackgroundEx ORC_EXP3A = new PresetBackgroundEx("ORCX03A");
		public final static PresetBackgroundEx ORC_EXP3B = new PresetBackgroundEx("ORCX03B");
		
		private PresetBackgroundEx(String label) {
			super(label, "WESTRING_LOADINGSCREEN_");
		}
	}
	
	public static class CustomBackground extends LoadingScreenBackground {
		private File _path;
		
		public File getCustomPath() {
			return _path;
		}
		
		@Override
		public String toString() {
			return getCustomPath().toString();
		}
		
		public CustomBackground(File file) {
			_path = file;
		}
	}
}
