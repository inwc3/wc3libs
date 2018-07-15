package net.moonlightflower.wc3libs.app;

import net.moonlightflower.wc3libs.txt.Profile;

import static net.moonlightflower.wc3libs.txt.Profile.UnitState.EDITOR_NAME;
import static net.moonlightflower.wc3libs.txt.Profile.UnitState.EDITOR_SUFFIX;

public class ProfileCleaner {

    public static void clean(Profile txt) {
        txt.getObjs().entrySet().forEach(entry -> {
            if (entry.getValue().containsField(EDITOR_SUFFIX.getFieldId())) {
                entry.getValue().removeField(EDITOR_SUFFIX.getFieldId());
            }
            if (entry.getValue().containsField(EDITOR_NAME.getFieldId())) {
                entry.getValue().removeField(EDITOR_NAME.getFieldId());
            }
        });
    }

}
