package net.moonlightflower.wc3libs.txt.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.txt.TXT;
import net.moonlightflower.wc3libs.txt.TXTSectionId;
import net.moonlightflower.wc3libs.txt.TXTState;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExtraTXT extends TXT {
	public final static File GAME_PATH = new File("war3mapExtra.txt");
	
	public static class SkyModel extends Int {
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

	public static class TimeOfDay extends Int {
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

	public static class States {
		private static class Section {
			public final static Section EXTRA = new Section("MapExtraInfo");
			
			private TXTSectionId _id;
			
			public TXTSectionId getId() {
				return _id;
			}
			
			public Section(String name) {
				_id = TXTSectionId.valueOf(name);
			}
		}
		
		private static class State<T extends DataType> extends TXTState<T> {
			private static List<State> _values = new ArrayList<>();
			
			public static List<State> values() {
				return _values;
			}
			
			public State(States.Section section, String name, DataTypeInfo typeInfo, T defVal) {
				super(section.getId(), name, typeInfo, defVal);
				
				_values.add(this);
			}
			
			public State(States.Section section, String name, Class<T> type, T defVal) {
				this(section, name, new DataTypeInfo(type), defVal);
			}
			
			public State(States.Section section, String name, DataTypeInfo typeInfo) {
				this(section, name, typeInfo, null);
			}
			
			public State(States.Section section, String name, Class<T> type) {
				this(section, name, new DataTypeInfo(type), null);
			}
		}

		public final static State<SkyModel> SKY_MODEL = new State<>(Section.EXTRA, "SkyModel", SkyModel.class);
		public final static State<TimeOfDay> TIME_OF_DAY = new State<>(Section.EXTRA, "TimeOfDay", TimeOfDay.class);
		public final static State<Wc3String> EXTERNAL_CUSTOM_DATA = new State<>(Section.EXTRA, "ExternalCustomData", Wc3String.class);
	}
}