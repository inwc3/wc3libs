package wc3libs.misc;

import net.moonlightflower.wc3libs.misc.AsyncTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AsyncTaskTest {
    private static final Logger log = LoggerFactory.getLogger(AsyncTaskTest.class.getName());

    @Test()
    public void test() throws InterruptedException {
        boolean finishedCalled[] = {false};
        final boolean[] runCalled = {false};
        final boolean[] onPostExecCalled = {false};

        AsyncTask task = new AsyncTask(() -> finishedCalled[0] = true) {
            @Override
            public void run() {
                runCalled[0] = true;

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }

            @Override
            public void onPostExec() {
                onPostExecCalled[0] = true;
            }
        };

        task.start();

        Assert.assertFalse(finishedCalled[0]);
        Assert.assertFalse(onPostExecCalled[0]);

        Thread.sleep(100);

        Assert.assertTrue(runCalled[0]);

        Thread.sleep(100);

        Assert.assertTrue(finishedCalled[0]);
        Assert.assertTrue(onPostExecCalled[0]);
    }
}
