package wc3libs.misc;

import com.esotericsoftware.minlog.Log;
import net.moonlightflower.wc3libs.misc.AsyncTask;
import net.moonlightflower.wc3libs.misc.Size;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AsyncTaskTest {
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
                    Log.error(e.getMessage());
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
