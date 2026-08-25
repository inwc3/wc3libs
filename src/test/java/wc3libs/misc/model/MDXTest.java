package wc3libs.misc.model;

import net.moonlightflower.wc3libs.misc.model.MDX;
import net.moonlightflower.wc3libs.misc.model.mdx.Bone;
import net.moonlightflower.wc3libs.misc.model.mdx.BoneChunk;
import net.moonlightflower.wc3libs.misc.model.mdx.Node;
import net.moonlightflower.wc3libs.misc.model.mdx.RawChunk;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MDXTest extends Wc3LibTest {
    @Test()
    public void MDXTest() throws IOException {
        MDX mdx = new MDX(getFile("wc3data/Models/PackHorse.mdx"));

        Assert.assertTrue(mdx.getVersionChunk().isPresent());
        System.out.println(mdx.getVersionChunk().get().getVersion());
    }

    @Test()
    public void readWriteCycle() throws IOException {
        readWriteCycle(MDX.class, getFile("wc3data/Models/PackHorse.mdx"));
    }

    @Test()
    public void squishIsSafeWithoutTangents() throws IOException {
        MDX mdx = new MDX(getFile("wc3data/Models/PackHorse.mdx"));
        boolean patchedTrack = false;

        for (BoneChunk boneChunk : mdx.getBoneChunks()) {
            for (Bone bone : boneChunk.getBones()) {
                Node node = bone.getNode();

                for (Node.TranslationTrackChunk trackChunk : node.getTranslationTrackChunks()) {
                    if (!trackChunk.getTranslationTracks().isEmpty()) {
                        Node.TranslationTrackChunk.TranslationTrack track = trackChunk.getTranslationTracks().iterator().next();
                        trackChunk.setInterpolationType(Node.TranslationTrackChunk.InterpolationType.LINEAR);
                        track.setInTanTranslation(null);
                        track.setOutTanTranslation(null);
                        patchedTrack = true;
                        break;
                    }
                }
                if (patchedTrack) break;
            }
            if (patchedTrack) break;
        }

        Assert.assertTrue(patchedTrack, "Expected at least one translation track in PackHorse fixture");
        mdx.squish();
    }

    @Test()
    public void preservesUnknownChunksWhenSquishing() throws IOException {
        byte[] payload = new byte[] {0x01, 0x23, (byte) 0xFE, 0x45};
        Wc3BinOutputStream input = new Wc3BinOutputStream(new ByteArrayOutputStream());
        input.writeId(MDX.TOKEN);
        input.writeId(Id.valueOf("FAFX"));
        input.writeUInt32(payload.length);
        input.writeBytes(payload);

        byte[] original = input.getBytes();
        MDX mdx = new MDX(new Wc3BinInputStream(new ByteArrayInputStream(original)));
        Assert.assertEquals(mdx.getChunks().stream().filter(chunk -> chunk instanceof RawChunk).count(), 1L);

        mdx.squish();

        Wc3BinOutputStream output = new Wc3BinOutputStream(new ByteArrayOutputStream());
        mdx.write(output);
        Assert.assertEquals(output.getBytes(), original);
    }
}
