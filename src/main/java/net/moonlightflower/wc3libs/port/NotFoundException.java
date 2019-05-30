package net.moonlightflower.wc3libs.port;

import javax.annotation.Nonnull;

public class NotFoundException extends Exception {
    public NotFoundException() {
        super();
    }

    public NotFoundException(@Nonnull Exception enclosedException) {
        super(enclosedException);
    }
}
