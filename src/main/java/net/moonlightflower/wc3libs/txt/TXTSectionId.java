package net.moonlightflower.wc3libs.txt;

import net.moonlightflower.wc3libs.misc.Id;

import javax.annotation.Nonnull;

public class TXTSectionId extends Id {
	protected TXTSectionId(@Nonnull String idString) {
		super(idString);
	}

	@Nonnull
	public static TXTSectionId valueOf(@Nonnull String idString) {
		return new TXTSectionId(idString);
	}
}
