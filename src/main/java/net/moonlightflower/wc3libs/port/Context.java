package net.moonlightflower.wc3libs.port;

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
    }
}
