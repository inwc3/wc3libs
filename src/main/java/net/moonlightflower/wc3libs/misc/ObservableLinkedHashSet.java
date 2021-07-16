package net.moonlightflower.wc3libs.misc;

import java.util.LinkedHashSet;
import java.util.Set;

public class ObservableLinkedHashSet<T> extends LinkedHashSet<T> {
    public interface Listener<T> {
        void onAdd(T val);
        void onRemove(T val);
        void clear();
    }

    protected final Set<Listener<T>> _listeners = new LinkedHashSet<>();

    public void onAdd(T val) {

    }

    @Override
    public boolean add(T val) {
        boolean ret = super.add(val);

        if (ret) onAdd(val);

        for (Listener<T> listener : _listeners) listener.onAdd(val);

        return ret;
    }

    public void onRemove(T val) {

    }

    @Override
    public boolean remove(Object val) {
         boolean ret = super.remove(val);

         if (ret) onRemove((T) val);

        for (Listener<T> listener : _listeners) listener.onRemove((T) val);

         return ret;
    }

    public void onClear() {
        for (Listener listener : _listeners) listener.clear();
    }

    @Override
    public void clear() {
        super.clear();

        onClear();
    }
}
