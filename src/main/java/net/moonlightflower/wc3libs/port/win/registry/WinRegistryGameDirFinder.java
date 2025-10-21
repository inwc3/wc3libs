package net.moonlightflower.wc3libs.port.win.registry;

import net.moonlightflower.wc3libs.misc.WinRegistryHandler;
import net.moonlightflower.wc3libs.port.GameDirFinder;
import net.moonlightflower.wc3libs.port.NotFoundException;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class WinRegistryGameDirFinder extends GameDirFinder {
	public WinRegistryGameDirFinder() {
	}

	private File getRegEntry(@Nonnull WinRegistryHandler.Entry entry) {
		try {
			String val = getRegistryHandler().get(entry);
			if (val == null) return null;

			// normalize: strip quotes and trailing ",0"
			String s = val.trim();
			if (s.startsWith("\"")) s = s.substring(1);
			if (s.endsWith("\"")) s = s.substring(0, s.length() - 1);
			if (s.endsWith(",0")) s = s.substring(0, s.length() - 2);

			File f = new File(s);

			// If value is an .exe path (e.g., ApplicationIcon), go up to install root.
			if (f.isFile() && s.toLowerCase().endsWith(".exe")) {
				File parent = f.getParentFile(); // e.g. ...\x86_64
				if (parent != null && ("x86_64".equalsIgnoreCase(parent.getName()) || "x86".equalsIgnoreCase(parent.getName()))) {
					parent = parent.getParentFile(); // ...\Warcraft III
				}
				if (parent != null && parent.exists()) return parent;
			}

			// Otherwise assume it's already a directory (e.g., InstallLocation)
			if (f.exists()) return f;
		} catch (UnsupportedOperationException ignored) {
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	protected WinRegistryHandler getRegistryHandler() {
		return new WinRegistryHandler();
	}

	@Nonnull
	@Override
	public File find() throws NotFoundException {
		List<WinRegistryHandler.Entry> entries = Arrays.asList(
			// Battle.net “Uninstall” (most reliable on Reforged)
			WinRegistryHandler.Wc3UninstallEntry.INSTALL_LOCATION_WOW6432,
			WinRegistryHandler.Wc3UninstallEntry.INSTALL_LOCATION_NATIVE,

			// Classic user/local-machine keys
			WinRegistryHandler.Wc3Entry.INSTALL_PATH,
			WinRegistryHandler.Wc3Entry.INSTALL_PATH_X,
			WinRegistryHandler.Wc3LocalMachineEntry.INSTALL_PATH,
			WinRegistryHandler.Wc3LocalMachineEntry.INSTALL_PATH_X,
			WinRegistryHandler.Wc3LocalMachineEntry.WAR3_INSTALL_PATH,

			// Reforged Capabilities\ApplicationIcon (exe path -> normalized)
			WinRegistryHandler.Wc3ReforgedEntry.INSTALL_PATH
		);

		for (WinRegistryHandler.Entry entry : entries) {
			File dir = getRegEntry(entry);
			if (dir != null && dir.exists()) return dir;
		}

		throw new NotFoundException();
	}
}
