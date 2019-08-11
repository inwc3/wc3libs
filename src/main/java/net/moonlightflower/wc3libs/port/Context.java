package net.moonlightflower.wc3libs.port;

import net.moonlightflower.wc3libs.port.mac.MacGameDirFinder;
import net.moonlightflower.wc3libs.port.mac.MacGameExeFinder;
import net.moonlightflower.wc3libs.port.mac.MacGameVersionFinder;
import net.moonlightflower.wc3libs.port.mac.MacMapsDirFinder;
import net.moonlightflower.wc3libs.port.win.*;
import net.moonlightflower.wc3libs.port.win.registry.WinRegistryGameDirFinder;
import net.moonlightflower.wc3libs.port.win.registry.WinRegistryGameExeFinder;
import net.moonlightflower.wc3libs.port.win.registry.WinRegistryGameVersionFinder;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Stack;

public class Context {
    private static Map<Class<?>, Stack<Object>> _services = new LinkedHashMap<>();

    private static Stack<Object> getServiceStack(@Nonnull Class<?> clazz) {
        if (!_services.containsKey(clazz)) _services.put(clazz, new Stack<>());

        return _services.get(clazz);
    }

    public synchronized static <T> T getService(@Nonnull Class<T> clazz) {
        return (T) getServiceStack(clazz).peek();
    }

    public synchronized static <T> T popService(@Nonnull Class<T> clazz) {
        return (T) getServiceStack(clazz).pop();
    }

    public synchronized static <T> void pushService(@Nonnull Class<T> clazz, @Nonnull T obj) {
        getServiceStack(clazz).push(obj);
    }

    static {
        pushService(GameDirFinder.class, new StdGameDirFinder());
        pushService(GameExeFinder.class, new StdGameExeFinder());
        pushService(GameVersionFinder.class, new StdGameVersionFinder());
        pushService(MpqPort.class, new JMpqPort());
        pushService(MacGameDirFinder.class, new MacGameDirFinder());
        pushService(MacGameExeFinder.class, new MacGameExeFinder());
        pushService(MacGameVersionFinder.class, new MacGameVersionFinder());
        pushService(MacMapsDirFinder.class, new MacMapsDirFinder());
        pushService(WinDefaultGameDirFinder.class, new WinDefaultGameDirFinder());
        pushService(WinGameDirFinder.class, new WinGameDirFinder());
        pushService(WinGameExeFinder.class, new WinGameExeFinder());
        pushService(WinGameVersionFinder.class, new WinGameVersionFinder());
        pushService(WinMapsDirFinder.class, new WinMapsDirFinder());
        pushService(WinTelemetryGameVersionFinder.class, new WinTelemetryGameVersionFinder());
        pushService(WinRegistryGameDirFinder.class, new WinRegistryGameDirFinder());
        pushService(WinRegistryGameExeFinder.class, new WinRegistryGameExeFinder());
        pushService(WinRegistryGameVersionFinder.class, new WinRegistryGameVersionFinder());
    }
}
