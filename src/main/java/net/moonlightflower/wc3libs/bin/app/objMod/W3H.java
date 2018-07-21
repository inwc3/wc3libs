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
import net.moonlightflower.wc3libs.slk.app.objs.BuffSLK;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

/**
 * buff modifications file for wrapping war3map.w3h
 */
public class W3H extends ObjMod {
	public final static File GAME_PATH = new File("war3map.w3h");
	public final static File CAMPAIGN_PATH = new File("war3campaign.w3h");

	public static class State<T extends DataType> extends MetaState<T> {
		public final static State<SpellDetail> ART_DETAIL = new State<>("fspd", SpellDetail.class);
		public final static State<Icon> ART_ICON = new State<>("fart", Icon.class);
		public final static State<LightningId> ART_LIGHTNING = new State<>("flig", new DataTypeInfo(DataList.class, LightningId.class));
		public final static State<DataList<Model>> ART_MISSILE = new State<>("fmat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<War3Real> ART_MISSILE_ARC = new State<>("fmac", War3Real.class);
		public final static State<War3Bool> ART_MISSILE_HOMING = new State<>("fmho", War3Bool.class);
		public final static State<War3Int> ART_MISSILE_SPEED = new State<>("fmsp", War3Int.class);
		public final static State<DataList<Model>> ART_SFX = new State<>("feat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<DataList<War3String>> ART_SFX_ATTACH = new State<>("feft", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<Model>> ART_SFX_SPECIAL = new State<>("fsat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<DataList<War3String>> ART_SFX_SPECIAL_ATTACH = new State<>("fspt", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<Model>> ART_SFX_TARGET = new State<>("ftat", new DataTypeInfo(DataList.class, Model.class));
		public final static State<War3Int> ART_SFX_TARGET_ATTACH_COUNT = new State<>("ftac", War3Int.class);
		public final static State<DataList<War3String>> ART_SFX_TARGET_ATTACH0 = new State<>("fta0", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<War3String>> ART_SFX_TARGET_ATTACH1 = new State<>("fta1", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<War3String>> ART_SFX_TARGET_ATTACH2 = new State<>("fta2", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<War3String>> ART_SFX_TARGET_ATTACH3 = new State<>("fta3", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<War3String>> ART_SFX_TARGET_ATTACH4 = new State<>("fta4", new DataTypeInfo(DataList.class, War3String.class));
		public final static State<DataList<War3String>> ART_SFX_TARGET_ATTACH5 = new State<>("fta5", new DataTypeInfo(DataList.class, War3String.class));

		public final static State<War3Bool> DATA_IS_EFFECT = new State<>("feff", War3Bool.class);
		public final static State<UnitRace> DATA_RACE = new State<>("frac", UnitRace.class);

		public final static State<SoundLabel> SOUND_ONESHOT = new State<>("fefs", SoundLabel.class);
		public final static State<SoundLabel> SOUND_LOOP = new State<>("fefl", SoundLabel.class);

		public final static State<War3String> TEXT_EDITOR_SUFFIX = new State<>("fnsf", War3String.class);
		public final static State<War3String> TEXT_NAME_EDITOR = new State<>("fnam", War3String.class);
		public final static State<War3String> TEXT_TOOLTIP = new State<>("ftip", War3String.class);
		public final static State<War3String> TEXT_TOOLTIP_UBER = new State<>("fube", War3String.class);

		public State(@Nonnull String idString, @Nonnull DataTypeInfo typeInfo, @Nullable T defVal) {
			super(idString, typeInfo, defVal);
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
	
	public static class Obj extends ObjMod.Obj {		
		public Obj(ObjId id, ObjId baseId) {
			super(id, baseId);
		}
	}

	@Nonnull
	@Override
	public ObjMod copy() {
		ObjMod other = new W3H();

		other.merge(this);

		return other;
	}

	@Override
	public Collection<File> getSLKs() {
		return Collections.singletonList(BuffSLK.GAME_PATH);
	}
	
	@Override
	public Collection<File> getNecessarySLKs() {
		return Collections.singletonList(BuffSLK.GAME_PATH);
	}

	@Override
	public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format, boolean extended) {
		super.write(stream, format, false);
	}

	public void write(@Nonnull Wc3BinOutputStream stream) {
		super.write(stream, false);
	}

	public W3H(@Nonnull Wc3BinInputStream stream) throws IOException {
		super(stream, false);
	}

	public W3H(@Nonnull File file) throws Exception {
		super(file, false);
	}
	
	public W3H() {
		super();
	}

	@Nonnull
	public static W3H ofMapFile(@Nonnull File mapFile) throws IOException {
		if (!mapFile.exists()) throw new IOException(String.format("file %s does not exist", mapFile));
		
		MpqPort.Out port = new JMpqPort.Out();
		
		port.add(GAME_PATH);
		
		MpqPort.Out.Result portResult = port.commit(mapFile);

		if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract w3h file");

		Wc3BinInputStream inStream = new Wc3BinInputStream(portResult.getInputStream(GAME_PATH));

		W3H w3h = new W3H();

		w3h.read(inStream, false);

		inStream.close();

		return w3h;
	}
}
