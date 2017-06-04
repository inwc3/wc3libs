package net.moonlightflower.wc3libs.misc;

import javafx.application.Platform;

public abstract class AsyncTask extends Thread {
	public class FinishedException extends RuntimeException {
		
	}

	@Override
	public abstract void run();
	public abstract void onPostExec();
	
	public interface FinishedHandler {
		public void finished();
	}
	
	private final FinishedHandler _finishedHandler;
	
	@Override
	public void start() {
		setDaemon(true);
		setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread thread, Throwable exception) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if (exception instanceof FinishedException) {
							onPostExec();
						} else {
							exception.printStackTrace();
						}
						
						if (_finishedHandler != null) {
							_finishedHandler.finished();
						}
					}
				});
			}
		});
		
		super.start();
	}
	
	public AsyncTask(FinishedHandler finishedHandler) {
		_finishedHandler = finishedHandler;
	}
}