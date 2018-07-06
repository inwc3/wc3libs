package net.moonlightflower.wc3libs.misc;

import com.esotericsoftware.minlog.Log;
import javafx.application.Platform;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public abstract class AsyncTask {
	private final Thread _thread;

	public class FinishedException extends RuntimeException {
		
	}

	public abstract void run();
	public abstract void onPostExec();
	
	public interface FinishedHandler {
		void finished();
	}
	
	private final FinishedHandler _finishedHandler;

	public void start() {
		_thread.setDaemon(true);
		_thread.setUncaughtExceptionHandler((thread, exception) -> {
			if (exception instanceof FinishedException) {
                onPostExec();
            } else {
                exception.printStackTrace();
            }

			_finishedHandler.finished();
		});
		
		_thread.start();
	}
	
	public AsyncTask(@Nonnull FinishedHandler finishedHandler) {
		_finishedHandler = finishedHandler;
		_thread = new Thread(() -> {
            run();

            throw new FinishedException();
        });
	}
}