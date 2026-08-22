package wc3libs.txt;

import net.moonlightflower.wc3libs.txt.app.jass.expr.num.IntLiteral;
import org.testng.annotations.Test;

import java.io.StringWriter;

import static org.testng.Assert.assertEquals;

public class IntLiteralTest {
	private static String write(IntLiteral literal) {
		StringWriter sw = new StringWriter();

		literal.write(sw);

		return sw.toString();
	}

	/**
	 * The grammar hands over the literal with its {@code 0x} prefix, and the hex
	 * alphabet is upper case and has no {@code x} in it. Both spellings used to
	 * decode to something unrelated.
	 */
	@Test
	public void readsHexWithEitherPrefixAndEitherCase() {
		assertEquals(IntLiteral.parse("0xFF").getVal(), 255);
		assertEquals(IntLiteral.parse("0xff").getVal(), 255);
		assertEquals(IntLiteral.parse("0XABcd").getVal(), 0xABCD);
		assertEquals(IntLiteral.parse("$ABCD1234").getVal(), 0xABCD1234);
		assertEquals(IntLiteral.parse("0x0").getVal(), 0);
	}

	/** The quotes belong to the syntax, not to the id. */
	@Test
	public void readsIdWithoutItsQuotes() {
		assertEquals(IntLiteral.parse("'hfoo'").getVal(), 0x68666F6F);
		assertEquals(IntLiteral.parse("'A000'").getVal(), 0x41303030);
	}

	@Test
	public void readsDecimalAndOctal() {
		assertEquals(IntLiteral.parse("123").getVal(), 123);
		assertEquals(IntLiteral.parse("0").getVal(), 0);
		assertEquals(IntLiteral.parse("0777").getVal(), 0777);
		assertEquals(IntLiteral.parse("010").getVal(), 8);
	}

	/**
	 * Writing dropped the syntax around the digits, so nothing it produced for a
	 * hex or id literal could be parsed again -- and a hex value made only of
	 * decimal digits came back as a different number.
	 */
	@Test
	public void writesBackWhatItRead() {
		for (String source : new String[]{"0xFF", "0xABCD1234", "'hfoo'", "123", "0777", "0x10"}) {
			IntLiteral literal = IntLiteral.parse(source);
			String written = write(literal);

			assertEquals(IntLiteral.parse(written).getVal(), literal.getVal(),
				source + " wrote as " + written);
			assertEquals(IntLiteral.parse(written).getType(), literal.getType(),
				source + " wrote as " + written);
		}
	}

	/**
	 * Encoding zero gives no digits at all, so each spelling has to supply the
	 * digit itself rather than emitting a prefix with nothing after it.
	 */
	@Test
	public void writesZeroForEachSpelling() {
		assertEquals(write(IntLiteral.parse("0")), "0");
		assertEquals(write(IntLiteral.parse("0x0")), "0x0");
		assertEquals(write(IntLiteral.parse("'\0\0\0\0'")), "''");

		// An octal zero is spelled the same as a decimal zero; the value is what
		// has to survive, and re-reading it as decimal loses nothing.
		assertEquals(IntLiteral.parse(write(IntLiteral.parse("00"))).getVal(), 0);
	}
}
