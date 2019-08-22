package net.moonlightflower.wc3libs.port;

import javax.annotation.Nonnull;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Finder<T> {
    @Nonnull
    public abstract T find() throws NotFoundException;

    static ThreadLocal<Set<Class<? extends Finder>>> _finders = new ThreadLocal<>();

    public T get() throws NotFoundException {
        if (_finders.get() == null) {
            _finders.set(new LinkedHashSet<>());
        }

        if (_finders.get().contains(this.getClass())) {
            throw new NotFoundException(new AlreadyTriedException());
        }

        _finders.get().add(this.getClass());

        try {
            return find();
        } finally {
            _finders.get().remove(this.getClass());
        }
    }
}
