package net.moonlightflower.wc3libs.app;

import net.moonlightflower.wc3libs.txt.Profile;

import static net.moonlightflower.wc3libs.txt.Profile.UnitState.EDITOR_NAME;
import static net.moonlightflower.wc3libs.txt.Profile.UnitState.EDITOR_SUFFIX;

public class ProfileCleaner {

    public static void clean(Profile txt) {
        txt.getObjs().forEach((key, value) -> {
            if (value.containsField(EDITOR_SUFFIX.getFieldId())) {
                value.removeField(EDITOR_SUFFIX.getFieldId());
            }
            if (value.containsField(EDITOR_NAME.getFieldId())) {
                value.removeField(EDITOR_NAME.getFieldId());
            }
        });
    }

}
