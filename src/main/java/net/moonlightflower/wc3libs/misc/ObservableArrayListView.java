package net.moonlightflower.wc3libs.misc;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.function.Predicate;

public class ObservableArrayListView<T, SubType extends T> extends ArrayList<SubType> {
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

    private final ObservableArrayList<T> _parent;

    public ObservableArrayListView(@Nonnull ObservableArrayList<T> parent, @Nonnull Predicate<T> filter) {
        _parent = parent;

        for (T val : _parent) {
            if (filter.test(val)) add((SubType) val);
        }

        _parent._listeners.add(new ObservableArrayList.Listener<T>() {
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
