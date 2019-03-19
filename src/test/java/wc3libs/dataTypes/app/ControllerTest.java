package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.CombatTarget;
import net.moonlightflower.wc3libs.dataTypes.app.Controller;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ControllerTest {
    @Test
    public void test() {
        Assert.assertEquals(Controller.valueOf(2), Controller.COMPUTER);
        Assert.assertEquals(Controller.valueOf(3), Controller.NEUTRAL);
        Assert.assertEquals(Controller.valueOf(4), Controller.RESCUABLE);
        Assert.assertEquals(Controller.valueOf(1), Controller.USER);
    }
}
