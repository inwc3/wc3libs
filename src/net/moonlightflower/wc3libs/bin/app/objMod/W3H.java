package net.moonlightflower.wc3libs.bin.app.objMod;

import java.util.ArrayList;
import java.util.List;

import net.moonlightflower.wc3libs.bin.MetaState;
import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.dataTypes.app.Bool;
import net.moonlightflower.wc3libs.dataTypes.app.Icon;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.LightningId;
import net.moonlightflower.wc3libs.dataTypes.app.ModelList;
import net.moonlightflower.wc3libs.dataTypes.app.Real;
import net.moonlightflower.wc3libs.dataTypes.app.SoundLabel;
import net.moonlightflower.wc3libs.dataTypes.app.SpellDetail;
import net.moonlightflower.wc3libs.dataTypes.app.StringList;
import net.moonlightflower.wc3libs.dataTypes.app.UnitRace;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.SLKState;

/**
 * buff modifications file for wrapping war3map.w3h
 */
public class W3H extends ObjMod {
	public final static String GAME_PATH = "war3map.w3h";
	
	public static class States {
		public static class State<T> extends MetaState<T> {
			private static List<State> _values = new ArrayList<>();
			
			public static List<State> values() {
				return _values;
			}
			
			public State(String idString) {
				this(idString, null);
			}

			public State(String idString, T defVal) {
				super(idString, defVal);
				
				_values.add(this);
			}
		}
		
		public static List<State> values() {
			return State.values();
		}
		
		public final static State<SpellDetail> ART_DETAIL = new State<>("fspd");
		public final static State<Icon> ART_ICON = new State<>("fart");
		public final static State<LightningId> ART_LIGHTNING = new State<>("flig");
		public final static State<ModelList> ART_MISSILE = new State<>("fmat");
		public final static State<Real> ART_MISSILE_ARC = new State<>("fmac");
		public final static State<Bool> ART_MISSILE_HOMING = new State<>("fmho");
		public final static State<Int> ART_MISSILE_SPEED = new State<>("fmsp");
		public final static State<ModelList> ART_SFX = new State<>("feat");
		public final static State<StringList> ART_SFX_ATTACH = new State<>("feft");
		public final static State<ModelList> ART_SFX_SPECIAL = new State<>("fsat");
		public final static State<StringList> ART_SFX_SPECIAL_ATTACH = new State<>("fspt");
		public final static State<ModelList> ART_SFX_TARGET = new State<>("ftat");
		public final static State<Int> ART_SFX_TARGET_ATTACH_COUNT = new State<>("ftac");
		public final static State<StringList> ART_SFX_TARGET_ATTACH0 = new State<>("fta0");
		public final static State<StringList> ART_SFX_TARGET_ATTACH1 = new State<>("fta1");
		public final static State<StringList> ART_SFX_TARGET_ATTACH2 = new State<>("fta2");
		public final static State<StringList> ART_SFX_TARGET_ATTACH3 = new State<>("fta3");
		public final static State<StringList> ART_SFX_TARGET_ATTACH4 = new State<>("fta4");
		public final static State<StringList> ART_SFX_TARGET_ATTACH5 = new State<>("fta5");
		
		public final static State<Bool> DATA_IS_EFFECT = new State<>("feff");
		public final static State<UnitRace> DATA_RACE = new State<>("frac");
		
		public final static State<SoundLabel> SOUND_ONESHOT = new State<>("fefs");
		public final static State<SoundLabel> SOUND_LOOP = new State<>("fefl");
		
		public final static State<String> TEXT_EDITOR_SUFFIX = new State<>("fnsf");
		public final static State<String> TEXT_NAME_EDITOR = new State<>("fnam");
		public final static State<String> TEXT_TOOLTIP = new State<>("ftip");
		public final static State<String> TEXT_TOOLTIP_UBER = new State<>("fube");
		
	}
	
	public static class Obj extends ObjMod.Obj {		
		public Obj(ObjId id, ObjId baseId) {
			super(id, baseId);
		}
	}
}
