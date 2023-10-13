package net.moonlightflower.wc3libs.misc;

import javax.annotation.Nonnull;

public abstract class AsyncTask {
	private final Thread _thread;

	public static class FinishedException extends RuntimeException {
		
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