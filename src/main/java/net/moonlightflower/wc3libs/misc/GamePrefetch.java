package net.moonlightflower.wc3libs.misc;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.port.MpqPort.Out.FileExport;
import net.moonlightflower.wc3libs.port.MpqPort.Out.Result;
import net.moonlightflower.wc3libs.port.MpqPort.Out.Result.Segment;
import net.moonlightflower.wc3libs.port.Orient;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class GamePrefetch {
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

		private static Queue<Obj> _toFinish = new ArrayDeque<>();
		
		public Obj(File inFile) {
			if (inFile == null) throw new RuntimeException("no inFile");
			
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
			
			Result result = null;
			
			try {
				_result = port.commit(MpqPort.getWc3Mpqs());
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
		
		public PortTask(List<File> procFiles, Collection<Obj> objs, FinishedHandler finishedHandler) {
			super(finishedHandler);
			
			_procFiles = procFiles;
			_objs = objs;
		}
	}

	private Queue<Obj> _objs = new LinkedList<>();
	private List<File> _loadingFiles = new ArrayList<>();
	
	private static Map<File, MpqPort.Out.FileExport> _cache = new LinkedHashMap<>();
	
	private boolean _started = false;
	
	private final static int INTERVAL = 1000;
	private final static int MAX_OBJS_PER_CYCLE = 500;
	
	private Timeline _timeline;
	
	public void add(Obj obj) {
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
			_timeline = new Timeline(new KeyFrame(Duration.millis(INTERVAL), new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					List<File> procFiles = new ArrayList<>();

					for (File inFile : _loadingFiles) {					
						procFiles.add(inFile);
						
						if (procFiles.size() == MAX_OBJS_PER_CYCLE) break;
					}

                    _loadingFiles.removeAll(procFiles);

					final EventHandler<ActionEvent> eventHandler = this;
					
					AsyncTask task = new PortTask(procFiles, _objs, () -> {
                        List<Obj> removeObjs = new ArrayList<>();

removeObjs.addAll(_objs);

                        _objs.removeAll(removeObjs);

                        if (_loadingFiles.isEmpty()) {
                            _started = false;
                            _timeline = null;
                        } else {
                            _timeline = new Timeline(new KeyFrame(Duration.millis(INTERVAL), eventHandler));

                            _timeline.play();
                        }
                    });
					
					task.start();
				}
			}));
		}
		
		_started = true;
		
		_timeline.play();
	}
	
	private void clear() {
		Orient.removeDir(GamePrefetch.STORAGE_DIR);
		Orient.createDir(GamePrefetch.STORAGE_DIR);
		
		_cache.clear();
		_objs.clear();
	}
	
	private static GamePrefetch _instance = new GamePrefetch();
	
	public static GamePrefetch getInstance() {
		return _instance;
	}
	
	private GamePrefetch() {}
	
	public static void preload(File inFile) {
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
	
	public static void preload(String inFileS) {
		preload(new File(inFileS));
	}
	
	static {
		try {
			STORAGE_DIR = new File(new File(".").getCanonicalFile(), "prefetch");
			
			getInstance().clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
