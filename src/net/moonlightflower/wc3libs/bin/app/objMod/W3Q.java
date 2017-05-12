package net.moonlightflower.wc3libs.bin.app.objMod;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.moonlightflower.wc3libs.bin.MetaState;
import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.Bool;
import net.moonlightflower.wc3libs.dataTypes.app.Char;
import net.moonlightflower.wc3libs.dataTypes.app.Icon;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.Real;
import net.moonlightflower.wc3libs.dataTypes.app.TechId;
import net.moonlightflower.wc3libs.dataTypes.app.UnitRace;
import net.moonlightflower.wc3libs.dataTypes.app.UpgradeClass;
import net.moonlightflower.wc3libs.dataTypes.app.UpgradeEffect;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.port.LadikMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;

/**
 * upgrade modifications file for wrapping war3map.w3q
 */
public class W3Q extends ObjMod {
	public final static File GAME_PATH = new File("war3map.w3q");
	public final static File CAMPAIGN_PATH = new File("war3campaign.w3q");
	
	public static class States {
		public static class State<T extends DataType> extends MetaState<T> {
			private static List<State> _values = new ArrayList<>();
			
			public static List<State> values() {
				return _values;
			}
			
			public State(String idString, DataTypeInfo typeInfo, T defVal) {
				super(idString, typeInfo, defVal);
				
				_values.add(this);
			}
			
			public State(String idString, Class<T> type, T defVal) {
				this(idString, new DataTypeInfo(type), defVal);
			}
			
			public State(String idString, DataTypeInfo typeInfo) {
				this(idString, typeInfo, null);
			}
			
			public State(String idString, Class<T> type) {
				this(idString, new DataTypeInfo(type), null);
			}
		}
		
		public static List<State> values() {
			return State.values();
		}
		
		public final static State<Int> ART_BUTTON_POS_X = new State<>("gbpx", Int.class);
		public final static State<Int> ART_BUTTON_POS_Y = new State<>("gbpy", Int.class);
		public final static State<Icon> ART_ICON = new State<>("gar1", Icon.class);
		
		public final static State<UpgradeClass> DATA_CLASS = new State<>("gcls", UpgradeClass.class);
		public final static State<UpgradeEffect> DATA_EFFECT1 = new State<>("gef1", UpgradeEffect.class);
		public final static State<Real> DATA_EFFECT1_BASE = new State<>("gba1", Real.class);
		public final static State<Real> DATA_EFFECT1_INC = new State<>("gmo1", Real.class);
		public final static State<Wc3String> DATA_EFFECT1_CODE = new State<>("gco1", Wc3String.class);
		public final static State<UpgradeEffect> DATA_EFFECT2 = new State<>("gef2", UpgradeEffect.class);
		public final static State<Real> DATA_EFFECT2_BASE = new State<>("gba2", Real.class);
		public final static State<Real> DATA_EFFECT2_INC = new State<>("gmo2", Real.class);
		public final static State<Wc3String> DATA_EFFECT2_CODE = new State<>("gco2", Wc3String.class);
		public final static State<UpgradeEffect> DATA_EFFECT3 = new State<>("gef3", UpgradeEffect.class);
		public final static State<Real> DATA_EFFECT3_BASE = new State<>("gba3", Real.class);
		public final static State<Real> DATA_EFFECT3_INC = new State<>("gmo3", Real.class);
		public final static State<Wc3String> DATA_EFFECT3_CODE = new State<>("gco3", Wc3String.class);
		public final static State<UpgradeEffect> DATA_EFFECT4 = new State<>("gef4", UpgradeEffect.class);
		public final static State<Real> DATA_EFFECT4_BASE = new State<>("gba4", Real.class);
		public final static State<Real> DATA_EFFECT4_INC = new State<>("gmo4", Real.class);
		public final static State<Wc3String> DATA_EFFECT4_CODE = new State<>("gco4", Wc3String.class);
		public final static State<Int> DATA_COSTS_GOLD = new State<>("gglb", Int.class);
		public final static State<Int> DATA_COSTS_GOLD_INC = new State<>("gglm", Int.class);
		public final static State<Int> DATA_COSTS_LUMBER = new State<>("glmb", Int.class);
		public final static State<Int> DATA_COSTS_LUMBER_INC = new State<>("glmm", Int.class);
		public final static State<Int> DATA_COSTS_TIME = new State<>("gtib", Int.class);
		public final static State<Int> DATA_COSTS_TIME_ADD = new State<>("gtim", Int.class);
		public final static State<Bool> DATA_IS_GLOBAL = new State<>("glob", Bool.class);
		public final static State<Bool> DATA_IS_INHERITED = new State<>("ginh", Bool.class);
		public final static State<Int> DATA_LEVEL_COUNT = new State<>("glvl", Int.class);
		public final static State<UnitRace> DATA_RACE = new State<>("grac", UnitRace.class);
		
		public final static State<DataList<TechId>> TECH_REQUIRES = new State<>("greq", new DataTypeInfo(DataList.class, TechId.class));
		public final static State<DataList<Int>> TECH_REQUIRES_LEVELS = new State<>("grqc", new DataTypeInfo(DataList.class, Int.class));
		
		public final static State<Wc3String> TEXT_EDITOR_SUFFIX = new State<>("gnsf", Wc3String.class);
		public final static State<Char> TEXT_HOTKEY = new State<>("ghk1", Char.class);
		public final static State<Wc3String> TEXT_NAME = new State<>("gnam", Wc3String.class);
		public final static State<Wc3String> TEXT_TOOLTIP = new State<>("gtp1", Wc3String.class);
		public final static State<Wc3String> TEXT_TOOLTIP_UBER = new State<>("gub1", Wc3String.class);
	}
	
	public static class Obj extends ObjMod.Obj {
		public Obj(ObjId id, ObjId baseId) {
			super(id, baseId);
		}
	}

	public W3Q(InputStream inStream) throws IOException {
		super(inStream, true);
	}
	
	public W3Q(File file) throws Exception {
		super(file, true);
	}
	
	public W3Q() {
		super();
	}

	public static W3Q ofMapFile(File mapFile) throws Exception {
		if (!mapFile.exists()) throw new IOException(String.format("file %s does not exist", mapFile));
		
		MpqPort.Out port = new LadikMpqPort.Out();
		
		port.add(GAME_PATH);
		
		MpqPort.Out.Result portResult = port.commit(mapFile);

		if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract w3q file");

		InputStream inStream = portResult.getInputStream(GAME_PATH);
		
		return new W3Q(inStream);
	}
}
