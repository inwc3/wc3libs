package net.moonlightflower.wc3libs.bin.app.objMod;

import net.moonlightflower.wc3libs.bin.MetaState;
import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.slk.app.objs.ItemSLK;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * item modifications file for wrapping war3map.w3t
 */
public class W3T extends ObjMod {
	public final static File GAME_PATH = new File("war3map.w3t");
	public final static File CAMPAIGN_PATH = new File("war3campaign.w3t");
	
	public static class States {
		public static class State<T extends DataType> extends MetaState<T> {
			private final static List<State> _values = new ArrayList<>();

			@Nonnull
			public static List<State> values() {
				return new ArrayList<>(_values);
			}
			
			public State(@Nonnull String idString, @Nonnull DataTypeInfo typeInfo, @Nullable T defVal) {
				super(idString, typeInfo, defVal);
				
				_values.add(this);
			}
			
			public State(@Nonnull String idString, @Nonnull Class<T> type, @Nullable T defVal) {
				this(idString, new DataTypeInfo(type), defVal);
			}
			
			public State(@Nonnull String idString, @Nonnull DataTypeInfo typeInfo) {
				this(idString, typeInfo, null);
			}
			
			public State(@Nonnull String idString, @Nonnull Class<T> type) {
				this(idString, new DataTypeInfo(type), null);
			}
		}
		
		public static List<State> values() {
			return State.values();
		}
		
		public final static State<DataList<AbilId>> ABIL_ABILS = new State<>("iabi", new DataTypeInfo(DataList.class, AbilId.class));
		
		public final static State<Wc3Int> ART_BUTTON_POS_X = new State<>("ubpx", Wc3Int.class);
		public final static State<Wc3Int> ART_BUTTON_POS_Y = new State<>("ubpy", Wc3Int.class);
		public final static State<Wc3Int> ART_COLOR_BLUE = new State<>("iclb", Wc3Int.class);
		public final static State<Wc3Int> ART_COLOR_GREEN = new State<>("iclg", Wc3Int.class);
		public final static State<Wc3Int> ART_COLOR_RED = new State<>("iclr", Wc3Int.class);
		public final static State<Icon> ART_ICON = new State<>("iico", Icon.class);
		public final static State<Model> ART_MODEL = new State<>("ifil", Model.class);
		public final static State<Real> ART_SCALE = new State<>("isca", Real.class);
		public final static State<Real> ART_SELECTION_SCALE = new State<>("issc", Real.class);
		
		public final static State<DefType> COMBAT_ARMOR_TYPE = new State<>("iarm", DefType.class);
		
		public final static State<Wc3Int> DATA_CHARGES = new State<>("iuse", Wc3Int.class);
		public final static State<ItemClass> DATA_CLASS = new State<>("icla", ItemClass.class);
		public final static State<AbilCode> DATA_COOLDOWN_ID = new State<>("icid", AbilCode.class);
		public final static State<Wc3Int> DATA_COST_GOLD = new State<>("igol", Wc3Int.class);
		public final static State<Wc3Int> DATA_COST_LUMBER = new State<>("ilum", Wc3Int.class);
		public final static State<Bool> DATA_DROPPABLE = new State<>("idro", Bool.class);
		public final static State<Bool> DATA_DROPPED = new State<>("idrp", Bool.class);
		public final static State<Bool> DATA_IGNORE_COOLDOWN = new State<>("iicd", Bool.class);
		public final static State<Wc3Int> DATA_LEVEL = new State<>("ilev", Wc3Int.class);
		public final static State<Wc3Int> DATA_LEVEL_OLD = new State<>("ilvo", Wc3Int.class);
		public final static State<Wc3Int> DATA_LIFE = new State<>("ihtp", Wc3Int.class);
		public final static State<Bool> DATA_MORPHABLE = new State<>("imor", Bool.class);
		public final static State<Bool> DATA_PAWNABLE = new State<>("ipaw", Bool.class);
		public final static State<Bool> DATA_PERISHABLE = new State<>("iper", Bool.class);
		public final static State<Bool> DATA_POWERUP = new State<>("ipow", Bool.class);
		public final static State<Wc3Int> DATA_PRIO = new State<>("ipri", Wc3Int.class);
		public final static State<Bool> DATA_RANDOMED = new State<>("iprn", Bool.class);
		public final static State<Bool> DATA_SELLABLE = new State<>("isel", Bool.class);
		public final static State<Wc3Int> DATA_STOCK_MAX = new State<>("isto", Wc3Int.class);
		public final static State<Wc3Int> DATA_STOCK_REGEN = new State<>("istr", Wc3Int.class);
		public final static State<Wc3Int> DATA_STOCK_INITIAL = new State<>("isst", Wc3Int.class);
		public final static State<Bool> DATA_USABLE = new State<>("iusa", Bool.class);
		
		public final static State<DataList<PathingRequire>> TECH_REQUIRES = new State<>("ureq", new DataTypeInfo(DataList.class, PathingRequire.class));
		public final static State<DataList<Wc3Int>> TECH_REQUIRES_COUNT = new State<>("urqa", new DataTypeInfo(DataList.class, Wc3Int.class));

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

	@Nonnull
	@Override
	public ObjMod copy() {
		ObjMod other = new W3T();

		other.merge(this);

		return other;
	}

	@Override
	public Collection<File> getSLKs() {
		return Collections.singletonList(ItemSLK.GAME_USE_PATH);
	}
	
	@Override
	public Collection<File> getNecessarySLKs() {
		return Collections.singletonList(ItemSLK.GAME_USE_PATH);
	}

	@Override
	public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format, boolean extended) {
		super.write(stream, format, false);
	}

	public void write(@Nonnull Wc3BinOutputStream stream) {
		super.write(stream, false);
	}

	public W3T(@Nonnull Wc3BinInputStream stream) throws IOException {
		super(stream, false);
	}

	public W3T(@Nonnull File file) throws Exception {
		super(file, false);
	}
	
	public W3T() {
		super();
	}

	public static W3T ofMapFile(@Nonnull File mapFile) throws Exception {
		if (!mapFile.exists()) throw new IOException(String.format("file %s does not exist", mapFile));
		
		MpqPort.Out port = new JMpqPort.Out();
		
		port.add(GAME_PATH);
		
		MpqPort.Out.Result portResult = port.commit(mapFile);

		if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract w3t file");

		Wc3BinInputStream inStream = new Wc3BinInputStream(portResult.getInputStream(GAME_PATH));

		W3T w3t = new W3T();

		w3t.read(inStream, false);

		inStream.close();

		return w3t;
	}
}
