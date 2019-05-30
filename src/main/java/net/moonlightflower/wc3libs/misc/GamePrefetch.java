package net.moonlightflower.wc3libs.misc;


import net.moonlightflower.wc3libs.dataTypes.app.FlagsInt;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.port.MpqPort.Out.FileExport;
import net.moonlightflower.wc3libs.port.MpqPort.Out.Result;
import net.moonlightflower.wc3libs.port.MpqPort.Out.Result.Segment;
import net.moonlightflower.wc3libs.port.Orient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GamePrefetch {
	private static final Logger log = LoggerFactory.getLogger(FlagsInt.class.getName());

	public static abstract class Obj implements Comparable<Obj> {
		private final File _inFile;
		
		public File getInFile() {
			return _inFile;
		}
		
		@Override
		public String toString() {
			return _inFile.toString();
		}
		
		private final static int PRIO_LOW = 0;
		private final static int PRIO_MEDIUM = 1;
		private final static int PRIO_HIGH = 2;
		
		private int _prio = PRIO_HIGH;
		
		@Override
		public int compareTo(@Nonnull Obj other) {
			if (_prio < other._prio) return -1;
			if (_prio > other._prio) return 1;
			
			return 0;
		}
		
		public void changePriority(int prio) {
			_prio = prio;
		}

		public abstract void onError();
		public abstract void onFinish(MpqPort.Out.FileExport outFile);

		private final static Queue<Obj> _toFinish = new ArrayDeque<>();
		
		public Obj(@Nonnull File inFile) {
			_inFile = inFile;
		}
	}
	
	private static File STORAGE_DIR = null;
	
	private static class PortTask extends AsyncTask {
		private final List<File> _procFiles;
		private final Collection<Obj> _objs;
		
		private Result _result = null;
		
		@Override
		public void run() {
			MpqPort.Out port = new JMpqPort.Out();
			
			for (File inFile : _procFiles) {
				File outFile = new File(STORAGE_DIR, inFile.toString());
				
				if (_cache.containsKey(inFile)) continue;
				
				port.add(inFile, outFile, false);
			}
			
			try {
				_result = port.commit(MpqPort.getWar3Mpqs());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			throw new AsyncTask.FinishedException();
		}
		
		@Override
		public void onPostExec() {
			if (_result != null) {
				for (Map.Entry<File, Segment> exportEntry : _result.getExports().entrySet()) {				
					File inFile = exportEntry.getKey();
					Segment segment = exportEntry.getValue();
					
					_cache.put(inFile, segment.getExport());
				}

				for (Obj obj : _objs) {
					File inFile = obj.getInFile();
					
					if (_cache.containsKey(inFile)) {
						obj.onFinish(_cache.get(inFile));
					} else {
						obj.onError();
					}
				}
			}
		}
		
		public PortTask(@Nonnull List<File> procFiles, @Nonnull Collection<Obj> objs, @Nonnull FinishedHandler finishedHandler) {
			super(finishedHandler);
			
			_procFiles = procFiles;
			_objs = objs;
		}
	}

	private final Queue<Obj> _objs = new LinkedList<>();
	private final List<File> _loadingFiles = new ArrayList<>();
	
	private final static Map<File, MpqPort.Out.FileExport> _cache = new LinkedHashMap<>();
	
	private boolean _started = false;
	
	private final static int INTERVAL = 1000;
	private final static int MAX_OBJS_PER_CYCLE = 500;
	
	private ScheduledExecutorService _timeline;
	private Runnable _timelineTask;
	
	public void add(@Nonnull Obj obj) {
		File inFile = obj.getInFile();
		
		if (new File(STORAGE_DIR, inFile.toString()).exists()) {
			//path still exists on disk, cache it
			
			//TODO
		}

		if (_cache.containsKey(inFile)) {
			//path was already requested before/still in cache, no need to repeat
			obj.onFinish(_cache.get(inFile));
			
			return;
		}

		_objs.add(obj);
		
		if (_loadingFiles.contains(inFile)) return;
		
		_loadingFiles.add(inFile);

		if (_started) return;
		
		if (_timeline == null) {
			_timeline = Executors.newSingleThreadScheduledExecutor();

			_timelineTask = new Runnable() {
				@Override
				public void run() {
					List<File> procFiles = new ArrayList<>();

					for (File inFile : _loadingFiles) {
						procFiles.add(inFile);

						if (procFiles.size() == MAX_OBJS_PER_CYCLE) break;
					}

					_loadingFiles.removeAll(procFiles);

					final Runnable eventHandler = this;

					AsyncTask task = new PortTask(procFiles, _objs, () -> {
						List<Obj> removeObjs = new ArrayList<>();

						removeObjs.addAll(_objs);

						_objs.removeAll(removeObjs);

						if (_loadingFiles.isEmpty()) {
							_started = false;
							_timeline = null;
							_timelineTask = null;
						} else {
							_timeline = Executors.newSingleThreadScheduledExecutor();

							_timeline.scheduleAtFixedRate(eventHandler, INTERVAL, INTERVAL, TimeUnit.MILLISECONDS);
						}
					});

					task.start();
				}
			};
		}
		
		_started = true;

		_timeline.scheduleAtFixedRate(_timelineTask, INTERVAL, INTERVAL, TimeUnit.MILLISECONDS);
	}
	
	private void clear() {
		Orient.removeDir(GamePrefetch.STORAGE_DIR);
		Orient.createDir(GamePrefetch.STORAGE_DIR);
		
		_cache.clear();
		_objs.clear();
	}
	
	private final static GamePrefetch _instance = new GamePrefetch();
	
	public static GamePrefetch getInstance() {
		return _instance;
	}
	
	private GamePrefetch() {}
	
	public static void preload(@Nonnull File inFile) {
		getInstance().add(new Obj(inFile) {
			@Override
			public void onError() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFinish(FileExport outFile) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	public static void preload(@Nonnull String inFileS) {
		preload(new File(inFileS));
	}
	
	static {
		try {
			STORAGE_DIR = new File(new File(".").getCanonicalFile(), "prefetch");
			
			getInstance().clear();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
}
