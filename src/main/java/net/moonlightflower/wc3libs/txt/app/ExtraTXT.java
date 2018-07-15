package net.moonlightflower.wc3libs.txt.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.War3Int;
import net.moonlightflower.wc3libs.dataTypes.app.War3String;
import net.moonlightflower.wc3libs.txt.TXT;
import net.moonlightflower.wc3libs.txt.TXTState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExtraTXT extends TXT {
	public final static File GAME_PATH = new File("war3mapExtra.txt");
	
	public static class SkyModel extends War3Int {
		private static Map<Integer, SkyModel> _map = new LinkedHashMap<>();
		
		public final static SkyModel BLIZZARD = new SkyModel(0, "SKY08");
		public final static SkyModel DALARAN = new SkyModel(1, "SKY02");
		public final static SkyModel FELWOOD = new SkyModel(2, "SKY06");
		public final static SkyModel MIST = new SkyModel(3, "SKY07");
		public final static SkyModel STANDARD = new SkyModel(4, "SKY01");
		public final static SkyModel LORDAERON_FALL = new SkyModel(5, "SKY03");
		public final static SkyModel LORDAERON_SUMMER = new SkyModel(6, "SKY04");
		public final static SkyModel LORDAERON_WINTER = new SkyModel(7, "SKY05");
		public final static SkyModel LORDAERON_WINTER_LIGHTGREEN = new SkyModel(8, "SKY09");
		public final static SkyModel LORDAERON_WINTER_PINK = new SkyModel(9, "SKY10");
		public final static SkyModel LORDAERON_WINTER_PURPLE = new SkyModel(10, "SKY11");
		public final static SkyModel LORDAERON_WINTER_RED = new SkyModel(11, "SKY12");
		public final static SkyModel LORDAERON_WINTER_YELLOW = new SkyModel(12, "SKY13");
		public final static SkyModel OUTLAND = new SkyModel(13, "SKY14");
		
		private int _val;
		private String _label;
		
		@Override
		public String toString() {
			return _label;
		}
		
		private SkyModel(int val, String label) {
			super(val);
			
			_map.put(val, this);
			
			_label = String.format("WESTRING_SKYMODEL_%s", label);
			_val = val;
		}
		
		public static SkyModel valueOf(Integer val) {
			return _map.get(val);
		}
	}

	public static class TimeOfDay extends War3Int {
		private static Map<Integer, TimeOfDay> _map = new LinkedHashMap<>();
		
		public final static TimeOfDay UNFIXED = new TimeOfDay(0, "MAPPREFS_TIMEOFDAY_UNFIXED", null);
		public final static TimeOfDay DAWN = new TimeOfDay(1, "MAPPREFS_TIMEOFDAY_FIXED", "DAWN");
		public final static TimeOfDay NOON = new TimeOfDay(2, "MAPPREFS_TIMEOFDAY_FIXED", "NOON");
		public final static TimeOfDay DUSK = new TimeOfDay(3, "MAPPREFS_TIMEOFDAY_FIXED", "DUSK");
		public final static TimeOfDay MIDNIGHT = new TimeOfDay(4, "MAPPREFS_TIMEOFDAY_FIXED", "MIDNIGHT");
		
		private int _val;
		private String _label;
		
		@Override
		public String toString() {
			return _label;
		}
		
		private TimeOfDay(int val, String labelA, String labelB) {
			super(val);
			
			_map.put(val, this);
			
			_label = String.format("WESTRING_MAPPREFS_%s", labelA);
			
			if (labelB != null) _label = String.format(_label, labelB);
			
			_val = val;
		}
		
		public static TimeOfDay valueOf(Integer val) {
			return _map.get(val);
		}
	}

	public static class Section extends TXT.Section {
		public final static Section EXTRA = new Section("MapExtraInfo");

		public Section(@Nonnull String name) {
			super(name);
		}
	}

	public static class State<T extends DataType> extends TXTState<T> {
		public final static State<SkyModel> SKY_MODEL = new State<>(Section.EXTRA, "SkyModel", SkyModel.class);
		public final static State<TimeOfDay> TIME_OF_DAY = new State<>(Section.EXTRA, "TimeOfDay", TimeOfDay.class);
		public final static State<War3String> EXTERNAL_CUSTOM_DATA = new State<>(Section.EXTRA, "ExternalCustomData", War3String.class);

		public State(@Nonnull String name, @Nonnull DataTypeInfo typeInfo, @Nullable T defVal) {
			super(name, typeInfo, defVal);
		}

		public State(@Nonnull String fieldS, @Nonnull Class<T> type, @Nullable T defVal) {
			this(fieldS, new DataTypeInfo(type), defVal);
		}

		public State(@Nonnull String fieldS, @Nonnull DataTypeInfo typeInfo) {
			this(fieldS, typeInfo, null);
		}

		public State(@Nonnull String fieldS, @Nonnull Class<T> type) {
			this(fieldS, new DataTypeInfo(type), null);
		}

		private State(@Nonnull Section section, @Nonnull String fieldS, @Nonnull DataTypeInfo typeInfo) {
			this(fieldS, typeInfo);

			section.addField(getFieldId());
		}

		private State(@Nonnull Section section, @Nonnull String fieldS, @Nonnull Class<T> type) {
			this(fieldS, type);

			section.addField(getFieldId());
		}
	}
}