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
import net.moonlightflower.wc3libs.dataTypes.app.AbilCode;
import net.moonlightflower.wc3libs.dataTypes.app.AbilId;
import net.moonlightflower.wc3libs.dataTypes.app.Bool;
import net.moonlightflower.wc3libs.dataTypes.app.Char;
import net.moonlightflower.wc3libs.dataTypes.app.DefType;
import net.moonlightflower.wc3libs.dataTypes.app.Icon;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.ItemClass;
import net.moonlightflower.wc3libs.dataTypes.app.Model;
import net.moonlightflower.wc3libs.dataTypes.app.PathingRequire;
import net.moonlightflower.wc3libs.dataTypes.app.Real;
import net.moonlightflower.wc3libs.dataTypes.app.RequireList;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.port.LadikMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;

/**
 * item modifications file for wrapping war3map.w3t
 */
public class W3T extends ObjMod {
	public final static File GAME_PATH = new File("war3map.w3t");
	public final static File CAMPAIGN_PATH = new File("war3campaign.w3t");
	
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
		
		public final static State<DataList<AbilId>> ABIL_ABILS = new State<>("iabi", new DataTypeInfo(DataList.class, AbilId.class));
		
		public final static State<Int> ART_BUTTON_POS_X = new State<>("ubpx", Int.class);
		public final static State<Int> ART_BUTTON_POS_Y = new State<>("ubpy", Int.class);
		public final static State<Int> ART_COLOR_BLUE = new State<>("iclb", Int.class);
		public final static State<Int> ART_COLOR_GREEN = new State<>("iclg", Int.class);
		public final static State<Int> ART_COLOR_RED = new State<>("iclr", Int.class);
		public final static State<Icon> ART_ICON = new State<>("iico", Icon.class);
		public final static State<Model> ART_MODEL = new State<>("ifil", Model.class);
		public final static State<Real> ART_SCALE = new State<>("isca", Real.class);
		public final static State<Real> ART_SELECTION_SCALE = new State<>("issc", Real.class);
		
		public final static State<DefType> COMBAT_ARMOR_TYPE = new State<>("iarm", DefType.class);
		
		public final static State<Int> DATA_CHARGES = new State<>("iuse", Int.class);
		public final static State<ItemClass> DATA_CLASS = new State<>("icla", ItemClass.class);
		public final static State<AbilCode> DATA_COOLDOWN_ID = new State<>("icid", AbilCode.class);
		public final static State<Int> DATA_COST_GOLD = new State<>("igol", Int.class);
		public final static State<Int> DATA_COST_LUMBER = new State<>("ilum", Int.class);
		public final static State<Bool> DATA_DROPPABLE = new State<>("idro", Bool.class);
		public final static State<Bool> DATA_DROPPED = new State<>("idrp", Bool.class);
		public final static State<Bool> DATA_IGNORE_COOLDOWN = new State<>("iicd", Bool.class);
		public final static State<Int> DATA_LEVEL = new State<>("ilev", Int.class);
		public final static State<Int> DATA_LEVEL_OLD = new State<>("ilvo", Int.class);
		public final static State<Int> DATA_LIFE = new State<>("ihtp", Int.class);
		public final static State<Bool> DATA_MORPHABLE = new State<>("imor", Bool.class);
		public final static State<Bool> DATA_PAWNABLE = new State<>("ipaw", Bool.class);
		public final static State<Bool> DATA_PERISHABLE = new State<>("iper", Bool.class);
		public final static State<Bool> DATA_POWERUP = new State<>("ipow", Bool.class);
		public final static State<Int> DATA_PRIO = new State<>("ipri", Int.class);
		public final static State<Bool> DATA_RANDOMED = new State<>("iprn", Bool.class);
		public final static State<Bool> DATA_SELLABLE = new State<>("isel", Bool.class);
		public final static State<Int> DATA_STOCK_MAX = new State<>("isto", Int.class);
		public final static State<Int> DATA_STOCK_REGEN = new State<>("istr", Int.class);
		public final static State<Int> DATA_STOCK_INITIAL = new State<>("isst", Int.class);
		public final static State<Bool> DATA_USABLE = new State<>("iusa", Bool.class);
		
		public final static State<DataList<PathingRequire>> TECH_REQUIRES = new State<>("ureq", new DataTypeInfo(DataList.class, PathingRequire.class));
		public final static State<DataList<Int>> TECH_REQUIRES_COUNT = new State<>("urqa", new DataTypeInfo(DataList.class, Int.class));

		public final static State<Wc3String> TEXT_DESCRIPTION = new State<>("ides", Wc3String.class);
		public final static State<Char> TEXT_HOTKEY = new State<>("uhot", Char.class);
		public final static State<Wc3String> TEXT_NAME = new State<>("unam", Wc3String.class);
		public final static State<Wc3String> TEXT_TOOLTIP = new State<>("utip", Wc3String.class);
		public final static State<Wc3String> TEXT_TOOLTIP_UBER = new State<>("utub", Wc3String.class);
	}
	
	public static class Obj extends ObjMod.Obj {
		public Obj(ObjId id, ObjId baseId) {
			super(id, baseId);
		}
	}

	public W3T(InputStream inStream) throws IOException {
		super(inStream, false);
	}
	
	public W3T(File file) throws Exception {
		super(file, false);
	}
	
	public W3T() {
		super();
	}

	public static W3T ofMapFile(File mapFile) throws Exception {
		if (!mapFile.exists()) throw new IOException(String.format("file %s does not exist", mapFile));
		
		MpqPort.Out port = new LadikMpqPort.Out();
		
		port.add(GAME_PATH);
		
		MpqPort.Out.Result portResult = port.commit(mapFile);

		if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract w3t file");

		InputStream inStream = portResult.getInputStream(GAME_PATH);
		
		return new W3T(inStream);
	}
}
