package net.moonlightflower.wc3libs.txt;

import net.moonlightflower.wc3libs.misc.Id;

public class TXTSectionId extends Id {
	protected TXTSectionId(String idString) {
		super(idString);
	}
	
	public static TXTSectionId valueOf(String idString) {
		return new TXTSectionId(idString);
	}
}
