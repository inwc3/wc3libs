package net.moonlightflower.wc3libs.misc;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcCaller {
	private static class StreamReader extends Thread {
		private InputStream _inStream;
		private StringWriter _stringWriter = new StringWriter();
		
		@Override
		public String toString() {
			return _stringWriter.toString();
		}
		
		@Override
		public void run() {			
			try {
				byte[] bytes = new byte[1024];
				int len;
				
				while ((len = _inStream.read(bytes, 0, bytes.length)) != -1) {
					_stringWriter.write(new String(bytes, 0, len));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public StreamReader(InputStream inStream) {
			_inStream = inStream;
		}
	}
	
	private ProcessBuilder _builder;
	private Process _proc;
	private String _outString;
	
	public String getOutString() {
		return _outString;
	}
	
	public int exitVal() {
		return (_proc != null) ? _proc.exitValue() : -1;
	}
	
	public void exec() throws IOException {
		if (_minimized && System.getProperty("os.name").startsWith("Windows")) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("cmd.exe /c ");
			sb.append("start /wait /min \"abc\" ");
			
			for (String s : _builder.command()) {
				if (s.startsWith("/")) {
					sb.append(String.format("%s ", s));
				} else {
					sb.append(String.format("\"%s\" ", s.replace("/", "/")));
				}
			}
			
			_proc = Runtime.getRuntime().exec(sb.toString());
		} else {
			_proc = _builder.start();
		}

		StreamReader reader = new StreamReader(_proc.getInputStream());
		
		reader.start();
		
		try {
			_proc.waitFor();
			
			reader.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		_outString = reader.toString();
	}
	
	private boolean _minimized = false;
	
	public void setMinimized(boolean val) {
		_minimized = val;
	}
	
	private List<String> _cmds = new ArrayList<>();
	
	public ProcCaller(String... command) {
        Collections.addAll(_cmds, command);
		
		_builder = new ProcessBuilder(_cmds);
	}
}
