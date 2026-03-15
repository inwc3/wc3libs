package wc3libs.misc.model;

import net.moonlightflower.wc3libs.misc.model.MDX;
import net.moonlightflower.wc3libs.misc.model.mdx.Bone;
import net.moonlightflower.wc3libs.misc.model.mdx.BoneChunk;
import net.moonlightflower.wc3libs.misc.model.mdx.Node;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

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
}
