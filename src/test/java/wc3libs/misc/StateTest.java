package wc3libs.misc;

import net.moonlightflower.wc3libs.misc.State;
import net.moonlightflower.wc3libs.slk.app.meta.AbilityMetaSLK;
import net.moonlightflower.wc3libs.slk.app.meta.CommonMetaSLK;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collection;

public class StateTest {
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Test()
    public void test() {
        Class c = AbilityMetaSLK.State.class;

        Collection<State> states = State.allValues(c);

        Assert.assertTrue(states.contains(AbilityMetaSLK.State.USE_CREEP));
        Assert.assertTrue(states.contains(CommonMetaSLK.State.CAN_BE_EMPTY));
    }
}
