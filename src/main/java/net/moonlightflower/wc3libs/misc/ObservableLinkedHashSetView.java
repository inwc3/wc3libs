package net.moonlightflower.wc3libs.misc;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.function.Predicate;

public class ObservableLinkedHashSetView<T, SubType extends T> extends LinkedHashSet<SubType> {
    public void onAdd(T val) {

    }

    @Override
    public boolean add(SubType val) {
        boolean ret = super.add(val);

        if (ret) {
            onAdd(val);

            _parent.add(val);
        }

        return ret;
    }

    public void onRemove(T val) {

    }

    @Override
    public boolean remove(Object val) {
         boolean ret = super.remove(val);

         if (ret) {
             onRemove((T) val);

             _parent.remove(val);
         }

         return ret;
    }

    public void onClear() {

    }

    @Override
    public void clear() {
        _parent.clear();

        onClear();
    }

    private final ObservableLinkedHashSet<T> _parent;

    public ObservableLinkedHashSetView(@Nonnull ObservableLinkedHashSet<T> parent, @Nonnull Predicate<T> filter) {
        _parent = parent;

        for (T val : _parent) {
            if (filter.test(val)) add((SubType) val);
        }

        _parent._listeners.add(new ObservableLinkedHashSet.Listener<T>() {
            @Override
            public void onAdd(T val) {
                if (filter.test(val)) add((SubType) val);
            }

            @Override
            public void onRemove(T val) {
                if (filter.test(val)) remove((SubType) val);
            }

            @Override
            public void clear() {

            }
        });
    }
}
