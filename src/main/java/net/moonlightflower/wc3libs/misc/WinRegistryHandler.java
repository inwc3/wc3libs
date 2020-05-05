package net.moonlightflower.wc3libs.misc;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import net.moonlightflower.wc3libs.port.Orient;

import javax.annotation.Nonnull;

public class WinRegistryHandler {
	public WinRegistryHandler() throws UnsupportedOperationException {
		if (!Orient.isWindowsSystem()) throw new UnsupportedOperationException("not a windows system: " + Orient.getSystem());
	}

	public enum EntryType {
		REG_SZ,
		REG_DWORD,
		REG_MULTI_SZ
	}

	public static class Entry {
		private final File _dir;
		private final String _key;
		private final EntryType _entryType;

		public Entry(@Nonnull File dir, @Nonnull String key, @Nonnull EntryType entryType) {
			_dir = dir;
			_key = key;
			_entryType = entryType;
		}

		private Entry(@Nonnull String dirS, @Nonnull String key, @Nonnull EntryType entryType) {
			this(new File(dirS), key, entryType);
		}
	}

	/** A warcraft3-specific registry entry. Present on wc3 versions before 1.32 (reforged). */
	public static class Wc3Entry extends Entry {
		public final static String PREFIX = "HKCU\\Software\\Blizzard Entertainment\\Warcraft III";

		public static class DelOpt0Entry extends Entry {
			public final static String PREFIX = Wc3Entry.PREFIX + "\\DelOpt0";

			public final static DelOpt0Entry FILE_0 = new DelOpt0Entry("File0", EntryType.REG_SZ);
			public final static DelOpt0Entry FILE_1 = new DelOpt0Entry("File1", EntryType.REG_SZ);
			public final static DelOpt0Entry FILE_2 = new DelOpt0Entry("File2", EntryType.REG_SZ);
			public final static DelOpt0Entry PATH_0 = new DelOpt0Entry("Path0", EntryType.REG_SZ);
			public final static DelOpt0Entry PATH_1 = new DelOpt0Entry("Path1", EntryType.REG_SZ);
			public final static DelOpt0Entry PATH_2 = new DelOpt0Entry("Path2", EntryType.REG_SZ);

			public DelOpt0Entry(@Nonnull String key, @Nonnull EntryType entryType) {
				super(PREFIX, key, entryType);
			}
		}

		public static class DelOpt1Entry extends Entry {
			public final static String PREFIX = Wc3Entry.PREFIX + "\\DelOpt1";

			public final static DelOpt1Entry FILE_0 = new DelOpt1Entry("File0", EntryType.REG_SZ);
			public final static DelOpt1Entry PATH_0 = new DelOpt1Entry("Path0", EntryType.REG_SZ);

			public DelOpt1Entry(@Nonnull String key, @Nonnull EntryType entryType) {
				super(PREFIX, key, entryType);
			}
		}

		public static class DelOpt2Entry extends Entry {
			public final static String PREFIX = Wc3Entry.PREFIX + "\\DelOpt2";

			public final static DelOpt2Entry FILE_0 = new DelOpt2Entry("File0", EntryType.REG_SZ);
			public final static DelOpt2Entry FILE_1 = new DelOpt2Entry("File1", EntryType.REG_SZ);
			public final static DelOpt2Entry FILE_2 = new DelOpt2Entry("File2", EntryType.REG_SZ);
			public final static DelOpt2Entry FILE_3 = new DelOpt2Entry("File3", EntryType.REG_SZ);
			public final static DelOpt2Entry PATH_0 = new DelOpt2Entry("Path0", EntryType.REG_SZ);
			public final static DelOpt2Entry PATH_1 = new DelOpt2Entry("Path1", EntryType.REG_SZ);
			public final static DelOpt2Entry PATH_2 = new DelOpt2Entry("Path2", EntryType.REG_SZ);
			public final static DelOpt2Entry PATH_3 = new DelOpt2Entry("Path3", EntryType.REG_SZ);

			public DelOpt2Entry(@Nonnull String key, @Nonnull EntryType entryType) {
				super(PREFIX, key, entryType);
			}
		}

		public static class DelOpt3Entry extends Entry {
			public final static String PREFIX = Wc3Entry.PREFIX + "\\DelOpt3";

			public final static DelOpt3Entry FILE_0 = new DelOpt3Entry("File0", EntryType.REG_SZ);
			public final static DelOpt3Entry PATH_0 = new DelOpt3Entry("Path0", EntryType.REG_SZ);

			public DelOpt3Entry(@Nonnull String key, @Nonnull EntryType entryType) {
				super(PREFIX, key, entryType);
			}
		}

		public static class GameplayEntry extends Entry {
			public final static String PREFIX = Wc3Entry.PREFIX + "\\Gameplay";

			public final static GameplayEntry ALLY_FILTER = new GameplayEntry("allyFilter", EntryType.REG_DWORD);
			public final static GameplayEntry AMM_MAP_HASHES = new GameplayEntry("ammmaphashes", EntryType.REG_SZ);
			public final static GameplayEntry AMM_MAP_PREFS = new GameplayEntry("ammmapprefs", EntryType.REG_SZ);
			public final static GameplayEntry AMM_RACE = new GameplayEntry("ammrace", EntryType.REG_DWORD);
			public final static GameplayEntry AMM_STYLES = new GameplayEntry("ammstyles", EntryType.REG_SZ);
			public final static GameplayEntry AMM_TYPE = new GameplayEntry("ammtype", EntryType.REG_DWORD);
			public final static GameplayEntry AUTOSAVE_REPLAY = new GameplayEntry("autosaveReplay", EntryType.REG_DWORD);
			public final static GameplayEntry CREEP_FILTER = new GameplayEntry("creepFilter", EntryType.REG_DWORD);
			public final static GameplayEntry CUSTOM_FILTER = new GameplayEntry("customfilter", EntryType.REG_DWORD);
			public final static GameplayEntry CUSTOM_KEYS = new GameplayEntry("customkeys", EntryType.REG_DWORD);
			public final static GameplayEntry CUSTOM_MASK = new GameplayEntry("custommask", EntryType.REG_DWORD);
			public final static GameplayEntry FORMATIONS = new GameplayEntry("formations", EntryType.REG_DWORD);
			public final static GameplayEntry FORMATION_TOGGLE = new GameplayEntry("formationtoggle", EntryType.REG_DWORD);
			public final static GameplayEntry GAME_SPEED = new GameplayEntry("gamespeed", EntryType.REG_DWORD);
			public final static GameplayEntry HEALTH_BARS = new GameplayEntry("healthbars", EntryType.REG_DWORD);
			public final static GameplayEntry HERO_BAR = new GameplayEntry("herobar", EntryType.REG_DWORD);
			public final static GameplayEntry INPUT_SPROCKET = new GameplayEntry("inputsprocket", EntryType.REG_DWORD);
			public final static GameplayEntry KEY_SCROLL = new GameplayEntry("keyscroll", EntryType.REG_DWORD);
			public final static GameplayEntry MOUSE_SCROLL = new GameplayEntry("mousescroll", EntryType.REG_DWORD);
			public final static GameplayEntry MOUSE_SCROLL_DISABLE = new GameplayEntry("mousescrolldisable", EntryType.REG_DWORD);
			public final static GameplayEntry MULTIBOARD_ON = new GameplayEntry("multiboardon", EntryType.REG_DWORD);
			public final static GameplayEntry NET_GAME_PORT = new GameplayEntry("netgameport", EntryType.REG_DWORD);
			public final static GameplayEntry SCHED_RACE = new GameplayEntry("schedrace", EntryType.REG_DWORD);
			public final static GameplayEntry SUB_GROUP_ORDER = new GameplayEntry("subgrouporder", EntryType.REG_DWORD);
			public final static GameplayEntry TERRAIN_FILTER = new GameplayEntry("terrainFilter", EntryType.REG_DWORD);
			public final static GameplayEntry TOOLTIPS = new GameplayEntry("tooltips", EntryType.REG_DWORD);

			public GameplayEntry(@Nonnull String key, @Nonnull EntryType entryType) {
				super(PREFIX, key, entryType);
			}
		}

		public static class MapEntry extends Entry {
			public final static String PREFIX = Wc3Entry.PREFIX + "\\Map";

			public final static MapEntry BATTLE_NET_V0 = new MapEntry("battlenet_V0", EntryType.REG_SZ);
			public final static MapEntry BATTLE_NET_V1 = new MapEntry("battlenet_V1", EntryType.REG_SZ);
			public final static MapEntry LAN_V0 = new MapEntry("lan_V0", EntryType.REG_SZ);
			public final static MapEntry LAN_V1 = new MapEntry("lan_V1", EntryType.REG_SZ);
			public final static MapEntry SKIRMISH_V0 = new MapEntry("skirmish_V0", EntryType.REG_SZ);
			public final static MapEntry SKIRMISH_V1 = new MapEntry("skirmish_V1", EntryType.REG_SZ);

			public MapEntry(@Nonnull String key, @Nonnull EntryType entryType) {
				super(PREFIX, key, entryType);
			}
		}

		public static class MiscEntry extends Entry {
			public final static String PREFIX = Wc3Entry.PREFIX + "\\Misc";

			public final static MiscEntry BATTLE_NET_GATEWAY = new MiscEntry("bnetGateway", EntryType.REG_DWORD);
			public final static MiscEntry CAMPAIGN_PROFILE = new MiscEntry("campaignprofile", EntryType.REG_SZ);
			public final static MiscEntry CHAT_SUPPORT = new MiscEntry("chatsupport", EntryType.REG_DWORD);
			public final static MiscEntry CLICKED_AD = new MiscEntry("clickedad", EntryType.REG_DWORD);
			public final static MiscEntry CLICKED_CLAN = new MiscEntry("clickedclan", EntryType.REG_DWORD);
			public final static MiscEntry CLICKED_LADDER = new MiscEntry("clickedladder", EntryType.REG_DWORD);
			public final static MiscEntry CLICKED_TOURNAMENT = new MiscEntry("clickedtourn", EntryType.REG_DWORD);
			public final static MiscEntry SEEN_INTRO = new MiscEntry("seenintromovie", EntryType.REG_DWORD);

			public MiscEntry(@Nonnull String key, @Nonnull EntryType entryType) {
				super(PREFIX, key, entryType);
			}
		}

		public static class SoundEntry extends Entry {
			public final static String PREFIX = Wc3Entry.PREFIX + "\\Sound";

			public final static SoundEntry AMBIENT = new SoundEntry("ambient", EntryType.REG_DWORD);
			public final static SoundEntry DO_NOT_USE_WAVE_OUT = new SoundEntry("donotusewaveout", EntryType.REG_DWORD);
			public final static SoundEntry ENV = new SoundEntry("environmental", EntryType.REG_DWORD);
			public final static SoundEntry MOVEMENT = new SoundEntry("movement", EntryType.REG_DWORD);
			public final static SoundEntry MUSIC = new SoundEntry("music", EntryType.REG_DWORD);
			public final static SoundEntry MUSIC_VOLUME = new SoundEntry("musicvolume", EntryType.REG_DWORD);
			public final static SoundEntry NO_MIDI = new SoundEntry("nomidi", EntryType.REG_DWORD);
			public final static SoundEntry NO_SOUND_WARN = new SoundEntry("nosoundwarn", EntryType.REG_DWORD);
			public final static SoundEntry POSITIONAL = new SoundEntry("positional", EntryType.REG_DWORD);
			public final static SoundEntry PROVIDER = new SoundEntry("provider", EntryType.REG_DWORD);
			public final static SoundEntry SFX = new SoundEntry("sfx", EntryType.REG_DWORD);
			public final static SoundEntry SFX_VOLUME = new SoundEntry("sfxvolume", EntryType.REG_DWORD);
			public final static SoundEntry SOFTWARE_MIDI = new SoundEntry("softwaremidi", EntryType.REG_DWORD);
			public final static SoundEntry SUBTITLES = new SoundEntry("subtitles", EntryType.REG_DWORD);
			public final static SoundEntry UNIT = new SoundEntry("unit", EntryType.REG_DWORD);

			public SoundEntry(@Nonnull String key, @Nonnull EntryType entryType) {
				super(PREFIX, key, entryType);
			}
		}

		public static class StringEntry extends Entry {
			public final static String PREFIX = Wc3Entry.PREFIX + "\\String";

			public final static StringEntry USER_BNET = new StringEntry("userbnet", EntryType.REG_SZ);
			public final static StringEntry USER_LOCAL = new StringEntry("userlocal", EntryType.REG_SZ);

			public StringEntry(@Nonnull String key, @Nonnull EntryType entryType) {
				super(PREFIX, key, entryType);
			}
		}

		public static class VideoEntry extends Entry {
			public final static String PREFIX = Wc3Entry.PREFIX + "\\Video";

			public final static VideoEntry ADAPTER = new VideoEntry("adapter", EntryType.REG_DWORD);
			public final static VideoEntry ANIM_QUALITY = new VideoEntry("animquality", EntryType.REG_DWORD);
			public final static VideoEntry CINE_BITS_PER_PIXEL = new VideoEntry("cinematicbpp", EntryType.REG_DWORD);
			public final static VideoEntry CINE_HEIGHT = new VideoEntry("cinematicheight", EntryType.REG_DWORD);
			public final static VideoEntry CINE_OVERRIDES = new VideoEntry("cinematicoverrides", EntryType.REG_DWORD);
			public final static VideoEntry CINE_REFRESH = new VideoEntry("cinematicrefresh", EntryType.REG_DWORD);
			public final static VideoEntry CINE_WIDTH = new VideoEntry("cinematicwidth", EntryType.REG_DWORD);
			public final static VideoEntry COLOR_DEPTH = new VideoEntry("colordepth", EntryType.REG_DWORD);
			public final static VideoEntry FIXED_ASPECT = new VideoEntry("fixedaspect", EntryType.REG_DWORD);
			public final static VideoEntry GAMMA = new VideoEntry("gamma", EntryType.REG_DWORD);
			public final static VideoEntry LIGHTS = new VideoEntry("lights", EntryType.REG_DWORD);
			public final static VideoEntry LOCK_FB = new VideoEntry("lockfb", EntryType.REG_DWORD);
			public final static VideoEntry MAX_FPS = new VideoEntry("maxfps", EntryType.REG_DWORD);
			public final static VideoEntry MIP_LEVEL = new VideoEntry("miplevel", EntryType.REG_DWORD);
			public final static VideoEntry MODEL_DETAIL = new VideoEntry("modeldetail", EntryType.REG_DWORD);
			public final static VideoEntry OCCLUSION = new VideoEntry("occlusion", EntryType.REG_DWORD);
			public final static VideoEntry PARTICLES = new VideoEntry("particles", EntryType.REG_DWORD);
			public final static VideoEntry REFRESH_RATE = new VideoEntry("refreshrate", EntryType.REG_DWORD);
			public final static VideoEntry RES_HEIGHT = new VideoEntry("resheight", EntryType.REG_DWORD);
			public final static VideoEntry RES_WIDTH = new VideoEntry("reswidth", EntryType.REG_DWORD);
			public final static VideoEntry SPELL_FILTER = new VideoEntry("spellfilter", EntryType.REG_DWORD);
			public final static VideoEntry TEX_COLOR_DEPTH = new VideoEntry("texcolordepth", EntryType.REG_DWORD);
			public final static VideoEntry TEX_QUALITY = new VideoEntry("texquality", EntryType.REG_DWORD);
			public final static VideoEntry UNIT_SHADOWS = new VideoEntry("unitshadows", EntryType.REG_DWORD);

			public VideoEntry(@Nonnull String key, @Nonnull EntryType entryType) {
				super(PREFIX, key, entryType);
			}
		}

		public static class WorldEditEntry extends Entry {
			public final static String PREFIX = Wc3Entry.PREFIX + "\\WorldEdit";

			public final static WorldEditEntry LAST_OPEN_DIR = new WorldEditEntry("Last Open Directory", EntryType.REG_DWORD);

			public WorldEditEntry(@Nonnull String key, @Nonnull EntryType entryType) {
				super(PREFIX, key, entryType);
			}
		}

		public final static Wc3Entry ALLOW_LOCAL_FILES = new Wc3Entry("Allow Local Files", EntryType.REG_SZ);
		public final static Wc3Entry BATTLE_NET_GATEWAYS = new Wc3Entry("Battle.net Gateways", EntryType.REG_MULTI_SZ);
		public final static Wc3Entry INSTALL_PATH = new Wc3Entry("InstallPath", EntryType.REG_SZ);
		public final static Wc3Entry INSTALL_PATH_X = new Wc3Entry("InstallPathX", EntryType.REG_SZ);
		public final static Wc3Entry LOCALE = new Wc3Entry("Locale", EntryType.REG_SZ);
		public final static Wc3Entry MIGRATION_COMPLETE = new Wc3Entry("Migration Complete", EntryType.REG_DWORD);
		public final static Wc3Entry PREFERRED_GAME_VERSION = new Wc3Entry("Preferred Game Version", EntryType.REG_DWORD);
		public final static Wc3Entry PROGRAM = new Wc3Entry("Program", EntryType.REG_SZ);
		public final static Wc3Entry PROGRAM_X = new Wc3Entry("ProgramX", EntryType.REG_SZ);
		public final static Wc3Entry USER_GAME_SAVE_FOLDER = new Wc3Entry("User Game Save Folder", EntryType.REG_SZ);
		public final static Wc3Entry CD = new Wc3Entry("War3CD", EntryType.REG_SZ);
		public final static Wc3Entry CD_X = new Wc3Entry("War3XCD", EntryType.REG_SZ);

		public Wc3Entry(@Nonnull String key, @Nonnull EntryType entryType) {
			super(PREFIX, key, entryType);
		}
	}

	/** A Warcraft 3 Reforged compatible registry entry. Installations as of 1.32 may be void of all `Wc3Entry`s. */
	public static class Wc3ReforgedEntry extends Entry {
		public final static String PREFIX = "HKLM\\Software\\WOW6432Node\\Blizzard Entertainment\\Warcraft III\\Capabilities";

		public Wc3ReforgedEntry(@Nonnull String key, @Nonnull EntryType entryType) {
			super(PREFIX, key, entryType);
		}

		/** Of the form `"C:\Program Files\Warcraft III\x86_64\Warcraft III.exe",0` */
		public final static Wc3Entry APPLICATION_ICON = new Wc3Entry("ApplicationIcon", EntryType.REG_SZ);

		/** Of the form `"C:\Program Files\Warcraft III\x86_64\Warcraft III.exe",0` */
		public final static Wc3Entry INSTALL_PATH = APPLICATION_ICON;
	}

	/** A warcraft3-specific registry entry. Present on wc3 versions before 1.32 (reforged). */
	public static class Wc3LocalMachineEntry extends Entry {
		public final static String PREFIX = "HKLM\\Software\\Blizzard Entertainment\\Warcraft III";

		public final static Wc3Entry INSTALL_PATH = new Wc3Entry("InstallPath", EntryType.REG_SZ);
		public final static Wc3Entry INSTALL_PATH_X = new Wc3Entry("InstallPathX", EntryType.REG_SZ);
		public final static Wc3Entry WAR3_INSTALL_PATH = new Wc3Entry("War3InstallPath", EntryType.REG_SZ);

		public Wc3LocalMachineEntry(@Nonnull File dir, @Nonnull String key, @Nonnull EntryType entryType) {
			super(dir, key, entryType);
		}
	}

	/** A warcraft3-specific registry entry. Present on wc3 versions before 1.32 (reforged). */
	public static class WorldEditEntry extends Entry {
		public final static String PREFIX = "HKCU\\Software\\Blizzard Entertainment\\WorldEdit";

		public WorldEditEntry(@Nonnull String key, @Nonnull EntryType entryType) {
			super(PREFIX, key, entryType);
		}
	}

	public String get(@Nonnull File dir, @Nonnull String key) throws IOException {
        if (!Orient.isWindowsSystem()) throw new UnsupportedOperationException("not a windows system: " + Orient.getSystem());

		ProcCaller proc = getQueryingProcCaller(dir, key);

		proc.exec();

		String result = proc.getOutString();

		result = result.replaceAll("\\p{Cntrl}", "");

		String[] splits = result.split("    ");

		if (splits.length < 4) return null;

		return splits[3];
	}

	public Optional<String> maybeGet(@Nonnull String dirS, @Nonnull String key) {
		try {
			return Optional.ofNullable(get(dirS, key));
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	protected ProcCaller getQueryingProcCaller(@Nonnull File dir, @Nonnull String key) {
		return new ProcCaller("REG", "QUERY", dir.toString(), "/v", key);
	}

	public String get(@Nonnull String dirS, @Nonnull String key) throws IOException {
		return get(new File(dirS), key);
	}

	public String get(@Nonnull Entry entry) throws IOException {
		return get(entry._dir, entry._key);
	}

	public void set(@Nonnull File dir, @Nonnull String key, @Nonnull EntryType entryType, @Nonnull String val) throws IOException {
        if (!Orient.isWindowsSystem()) throw new UnsupportedOperationException("not a windows system: " + Orient.getSystem());

		ProcCaller proc = getUpdatingProcCaller(dir, key, entryType.name(), val);

		proc.exec();

		if (proc.exitVal() != 0) throw new IOException(proc.getErrString());
	}

	protected ProcCaller getUpdatingProcCaller(@Nonnull File dir, @Nonnull String key, @Nonnull String entryTypeName, @Nonnull String val) {
		return new ProcCaller("REG", "ADD", dir.toString(), "/v", key, "/t", entryTypeName, "/d", val, "/f");
	}

	public void set(@Nonnull Entry entry, @Nonnull String val) throws IOException {
		set(entry._dir, entry._key, entry._entryType, val);
	}

	public void remove(@Nonnull File dir, @Nonnull String key) throws IOException {
	    if (!Orient.isWindowsSystem()) throw new UnsupportedOperationException("not a windows system: " + Orient.getSystem());

		ProcCaller proc = getDeletionProcCaller(dir, key);

		proc.exec();

		if (proc.exitVal() != 0) throw new IOException(proc.getErrString());
	}

	protected ProcCaller getDeletionProcCaller(@Nonnull File dir, @Nonnull String key) {
		return new ProcCaller("REG", "DELETE", dir.toString(), "/v", key, "/f");
	}

	public void remove(@Nonnull Entry entry) throws IOException {
		remove(entry._dir, entry._key);
	}
}
