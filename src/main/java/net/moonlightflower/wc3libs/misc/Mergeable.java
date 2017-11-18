package net.moonlightflower.wc3libs.misc;

import javax.annotation.Nonnull;

public interface Mergeable<T> {
	void merge(@Nonnull T other);
}
