package net.moonlightflower.wc3libs.slk.app.meta;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.War3Bool;
import net.moonlightflower.wc3libs.dataTypes.app.War3Int;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;

public class AbilityMetaSLK extends CommonMetaSLK {
    public static File GAME_PATH = new File("Units\\AbilityMetaData.slk");

    public static class State<T extends DataType> extends CommonMetaSLK.State<T> {
        public final static State<War3Bool> USE_HERO = new State<>("useHero", War3Bool.class);
        public final static State<War3Bool> USE_UNIT = new State<>("useUnit", War3Bool.class);
        public final static State<War3Bool> USE_ITEM = new State<>("useItem", War3Bool.class);
        public final static State<War3Int> USE_CREEP = new State<>("useCreep", War3Int.class);

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
