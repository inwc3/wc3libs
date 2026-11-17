package wc3libs.app.objMerge;

import net.moonlightflower.wc3libs.app.objMerge.ProfileFile;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;

public class ProfileFileTest {
    private static ProfileFile parse(String text) {
        return ProfileFile.parse(text.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void valuesAreKeptAsWritten() {
        ProfileFile file = parse("﻿// header\r\n[Hamg]\r\nTip=\"a, b\",c\r\nArt:hd=x.blp\r\n Name = Archmage \r\n\r\n[hfoo]\r\nfile=foo // not a comment\r\n");

        ProfileFile.Section hamg = file.getSection("Hamg");

        Assert.assertEquals(hamg.get("tip").getRawValue(), "\"a, b\",c");
        Assert.assertEquals(hamg.get("TIP").getKey(), "Tip");
        Assert.assertEquals(hamg.get("Art:hd").getRawValue(), "x.blp");
        Assert.assertEquals(hamg.get("Name").getRawValue(), " Archmage ");
        Assert.assertEquals(file.getSection("hfoo").get("file").getRawValue(), "foo // not a comment");
        Assert.assertEquals(file.getLineSeparator(), "\r\n");
    }

    @Test
    public void sectionIdsAreCaseSensitiveAndRepeatsContinue() {
        ProfileFile file = parse("[APbs]\nA=1\n[Apbs]\nA=2\n[APbs]\nB=3\nA=4\n");

        Assert.assertEquals(file.getSections().size(), 2);
        Assert.assertEquals(file.getSection("APbs").get("A").getRawValue(), "4");
        Assert.assertEquals(file.getSection("APbs").get("B").getRawValue(), "3");
        Assert.assertEquals(file.getSection("Apbs").get("A").getRawValue(), "2");
    }

    @Test
    public void qualifiedKeysShareTheirBaseKey() {
        ProfileFile.Section section = parse("[x]\nArt:hd=a\nArt:sd=b\nUbertip:melee,V0=c\nUbertip=d\n").getSection("x");

        Assert.assertTrue(section.hasBaseKey("art"));
        Assert.assertTrue(section.hasBaseKey("Ubertip"));
        Assert.assertFalse(section.hasBaseKey("file"));
        Assert.assertEquals(ProfileFile.baseKey("Ubertip:melee,V0"), "Ubertip");

        section.removeQualified("ubertip");

        Assert.assertNull(section.get("Ubertip:melee,V0"));
        Assert.assertEquals(section.get("Ubertip").getRawValue(), "d");
        Assert.assertNotNull(section.get("Art:hd"));
    }

    @Test
    public void tokenizeFollowsQuotes() {
        Assert.assertEquals(ProfileFile.tokenize("a,\"b, c\",d"), Arrays.asList("a", "b, c", "d"));
        Assert.assertEquals(ProfileFile.tokenize("\"Reincarnation\",\"\""), Arrays.asList("Reincarnation", ""));
        Assert.assertEquals(ProfileFile.tokenize("a,,c"), Arrays.asList("a", "", "c"));
        Assert.assertEquals(ProfileFile.tokenize(""), Collections.singletonList(""));
        Assert.assertEquals(ProfileFile.tokenize("\"unterminated, still one"), Collections.singletonList("unterminated, still one"));
    }

    @Test
    public void joinQuotesOnlyWhatNeedsIt() {
        Assert.assertEquals(ProfileFile.join(Arrays.asList("a", "b, c", "")), "a,\"b, c\",");
        Assert.assertEquals(ProfileFile.tokenize(ProfileFile.join(Arrays.asList("x, y", "z"))), Arrays.asList("x, y", "z"));
    }

    @Test
    public void sectionCopiesAreIndependent() {
        ProfileFile.Section base = parse("[hfoo]\nName=Footman\n").getSection("hfoo");
        ProfileFile.Section copy = base.copy("h000");

        copy.put("NAME", "Mine");

        Assert.assertEquals(copy.getId(), "h000");
        Assert.assertEquals(copy.get("Name").getKey(), "Name");
        Assert.assertEquals(copy.get("name").getRawValue(), "Mine");
        Assert.assertEquals(base.get("name").getRawValue(), "Footman");
        Assert.assertEquals(copy.toText("\n"), "[h000]\nName=Mine\n");
    }
}
