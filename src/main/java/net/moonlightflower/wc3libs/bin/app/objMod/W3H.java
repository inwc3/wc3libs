package net.moonlightflower.wc3libs.bin.app.objMod;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import net.moonlightflower.wc3libs.slk.app.objs.BuffSLK;
import net.moonlightflower.wc3libs.bin.MetaState;
import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.dataTypes.DataList;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.Bool;
import net.moonlightflower.wc3libs.dataTypes.app.Icon;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.LightningId;
import net.moonlightflower.wc3libs.dataTypes.app.Model;
import net.moonlightflower.wc3libs.dataTypes.app.Real;
import net.moonlightflower.wc3libs.dataTypes.app.SoundLabel;
import net.moonlightflower.wc3libs.dataTypes.app.SpellDetail;
import net.moonlightflower.wc3libs.dataTypes.app.UnitRace;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.port.LadikMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;

/**
 * buff modifications file for wrapping war3map.w3h
 */
public class W3H extends ObjMod {
	public final static File GAME_PATH = new File("war3map.w3h");
	public final static File CAMPAIGN_PATH = new File("war3campaign.w3h");
	
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
		
		public final static State<SpellDetail> ART_DETAIL = new State<>("fspd", SpellDetail.class);
		public final static State<Icon> ART_ICON = new State<>("fart", Icon.class);
		public final static State<LightningId> ART_LIGHTNING = new State<>("flig", new DataTypeInfo(DataList.class, LightningId.class));
		public final static State<DataList<Model>> ART_MISSILE = new State<>("fmat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<Real> ART_MISSILE_ARC = new State<>("fmac", Real.class);
		public final static State<Bool> ART_MISSILE_HOMING = new State<>("fmho", Bool.class);
		public final static State<Int> ART_MISSILE_SPEED = new State<>("fmsp", Int.class);
		public final static State<DataList<Model>> ART_SFX = new State<>("feat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<DataList<Wc3String>> ART_SFX_ATTACH = new State<>("feft", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<DataList<Model>> ART_SFX_SPECIAL = new State<>("fsat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<DataList<Wc3String>> ART_SFX_SPECIAL_ATTACH = new State<>("fspt", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<DataList<Model>> ART_SFX_TARGET = new State<>("ftat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<Int> ART_SFX_TARGET_ATTACH_COUNT = new State<>("ftac", Int.class);
		public final static State<DataList<Wc3String>> ART_SFX_TARGET_ATTACH0 = new State<>("fta0", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<DataList<Wc3String>> ART_SFX_TARGET_ATTACH1 = new State<>("fta1", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<DataList<Wc3String>> ART_SFX_TARGET_ATTACH2 = new State<>("fta2", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<DataList<Wc3String>> ART_SFX_TARGET_ATTACH3 = new State<>("fta3", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<DataList<Wc3String>> ART_SFX_TARGET_ATTACH4 = new State<>("fta4", new DataTypeInfo(DataList.class, Wc3String.class));
		public final static State<DataList<Wc3String>> ART_SFX_TARGET_ATTACH5 = new State<>("fta5", new DataTypeInfo(DataList.class, Wc3String.class));
		
		public final static State<Bool> DATA_IS_EFFECT = new State<>("feff", Bool.class);
		public final static State<UnitRace> DATA_RACE = new State<>("frac", UnitRace.class);
		
		public final static State<SoundLabel> SOUND_ONESHOT = new State<>("fefs", SoundLabel.class);
		public final static State<SoundLabel> SOUND_LOOP = new State<>("fefl", SoundLabel.class);
		
		public final static State<Wc3String> TEXT_EDITOR_SUFFIX = new State<>("fnsf", Wc3String.class);
		public final static State<Wc3String> TEXT_NAME_EDITOR = new State<>("fnam", Wc3String.class);
		public final static State<Wc3String> TEXT_TOOLTIP = new State<>("ftip", Wc3String.class);
		public final static State<Wc3String> TEXT_TOOLTIP_UBER = new State<>("fube", Wc3String.class);
		
	}
	
	public static class Obj extends ObjMod.Obj {		
		public Obj(ObjId id, ObjId baseId) {
			super(id, baseId);
		}
	}

	@Override
	public Collection<File> getNecessarySLKs() {
		return Arrays.asList(BuffSLK.GAME_USE_PATH);
	}
	
	public W3H(InputStream inStream) throws IOException {
		super(inStream, false);
	}
	
	public W3H(File file) throws Exception {
		super(file, false);
	}
	
	public W3H() {
		super();
	}

	public static W3H ofMapFile(File mapFile) throws Exception {
		if (!mapFile.exists()) throw new IOException(String.format("file %s does not exist", mapFile));
		
		MpqPort.Out port = new LadikMpqPort.Out();
		
		port.add(GAME_PATH);
		
		MpqPort.Out.Result portResult = port.commit(mapFile);

		if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract w3h file");

		InputStream inStream = portResult.getInputStream(GAME_PATH);
		
		return new W3H(inStream);
	}
}
