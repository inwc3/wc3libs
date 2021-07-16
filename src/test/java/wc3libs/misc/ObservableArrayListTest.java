package wc3libs.misc;

import net.moonlightflower.wc3libs.misc.ObservableArrayList;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class ObservableArrayListTest {
    @Test()
    public void test() {
        final boolean[] onAddCalled = {false};
        final boolean[] onRemoveCalled = {false};
        final boolean[] onClearCalled = {false};

        ObservableArrayList<String> list = new ObservableArrayList<String>() {
            @Override
            public void onAdd(String val) {
                onAddCalled[0] = true;
            }

            @Override
            public void onRemove(String val) {
                onRemoveCalled[0] = true;
            }

            @Override
            public void onClear() {
                onClearCalled[0] = true;
            }
        };

        Assert.assertFalse(onAddCalled[0]);

        list.add("A");

        Assert.assertTrue(onAddCalled[0]);

        Assert.assertTrue(!list.isEmpty());
        Assert.assertEquals(list.get(0), "A");

        Assert.assertFalse(onRemoveCalled[0]);

        list.remove("A");

        Assert.assertTrue(onRemoveCalled[0]);

        Assert.assertTrue(list.isEmpty());

        list.addAll(Arrays.asList("A", "B", "C"));

        Assert.assertEquals(list.size(), 3);

        Assert.assertFalse(onClearCalled[0]);

        list.clear();

        Assert.assertTrue(list.isEmpty());

        Assert.assertTrue(onClearCalled[0]);
    }
}
