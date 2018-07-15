package net.moonlightflower.wc3libs.app;

import net.moonlightflower.wc3libs.txt.Profile;

import static net.moonlightflower.wc3libs.slk.app.terrainArts.CliffSLK.State.EDITOR_NAME;
import static net.moonlightflower.wc3libs.txt.Profile.UnitState.EDITOR_SUFFIX;

public class ProfileCleaner {

    public static void clean(Profile txt) {
        txt.getObjs().entrySet().forEach(entry -> {
            if (entry.getValue().containsField(EDITOR_SUFFIX.getFieldId())) {
                entry.getValue().getField(EDITOR_SUFFIX.getFieldId()).setLine("");
            }
            if (entry.getValue().containsField(EDITOR_NAME.getFieldId())) {
                entry.getValue().getField(EDITOR_NAME.getFieldId()).setLine("");
            }
        });
    }

}
