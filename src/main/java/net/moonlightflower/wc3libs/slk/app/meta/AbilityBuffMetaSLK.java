package net.moonlightflower.wc3libs.slk.app.meta;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;

public class AbilityBuffMetaSLK extends CommonMetaSLK {
    public static File GAME_PATH = new File("Units\\AbilityBuffMetaData.slk");

    public static class State<T extends DataType> extends CommonMetaSLK.State<T> {
        public State(@Nonnull String idString, @Nonnull DataTypeInfo typeInfo, @Nullable T defVal) {
            super(idString, typeInfo, defVal);
        }

        public State(@Nonnull String idString, @Nonnull DataTypeInfo typeInfo) {
            super(idString, typeInfo);
        }

        public State(@Nonnull String idString, @Nonnull Class<T> type) {
            super(idString, type);
        }

        public State(@Nonnull String idString, @Nonnull Class<T> type, @Nullable T defVal) {
            super(idString, type, defVal);
        }
    }
}
