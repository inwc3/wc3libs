package net.xetanth87.campaignsplitter;

public class XT87Utils {
	public static final String STRING_PREFIX = "TRIGSTR_";

	public static String zeroedNumber(int number, int digitCount)
	{
		String buttonIndexText = number + "";
		int addedZeroes = Math.max(digitCount - buttonIndexText.length(), 0);
		for (int i = 0; i < addedZeroes; i ++)
			buttonIndexText = "0" + buttonIndexText;
		return buttonIndexText;
	}
}
