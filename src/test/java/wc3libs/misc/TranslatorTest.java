package wc3libs.misc;

import net.moonlightflower.wc3libs.misc.Translator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TranslatorTest {
    @Test
    public void translateTextLooksUpKnownKeysAndPreservesUnknownText() {
        Translator translator = new Translator();
        translator.set("TRIGSTR_001", "Translated text");

        Assert.assertEquals(translator.translateText("TRIGSTR_001"), "Translated text");
        Assert.assertEquals(translator.translateText("literal text"), "literal text");
    }
}
