package net.moonlightflower.wc3libs.misc;

import net.moonlightflower.wc3libs.dataTypes.app.FlagsInt;
import net.moonlightflower.wc3libs.port.Orient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcCaller {
	private static final Logger log = LoggerFactory.getLogger(FlagsInt.class.getName());

	private static class StreamReader extends Thread {
		private final InputStream _inStream;
		private final StringWriter _stringWriter = new StringWriter();
		
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
				log.error(e.getMessage(), e);
			}
		}
		
		public StreamReader(@Nonnull InputStream inStream) {
			_inStream = inStream;
		}
	}
	
	private final ProcessBuilder _builder;
	private Process _proc;
	private String _outString;
	
	public String getOutString() {
		return _outString;
	}

	private String _errString;

	public String getErrString() {
		return _errString;
	}
	
	public int exitVal() {
		return (_proc != null) ? _proc.exitValue() : -1;
	}
	
	public void exec() throws IOException {
		if (_minimized && Orient.isWindowsSystem()) {
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

			//System.out.println("execute " + sb.toString());

			_proc = Runtime.getRuntime().exec(sb.toString());
		} else {
			//System.out.println("execute " + _builder.command());

			_proc = _builder.start();
		}

		StreamReader reader = new StreamReader(_proc.getInputStream());
		StreamReader errReader = new StreamReader(_proc.getErrorStream());
		
		reader.start();
		errReader.start();
		
		try {
			_proc.waitFor();

			reader.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		_outString = reader.toString();
		_errString = errReader.toString();
	}
	
	private boolean _minimized = false;
	
	public void setMinimized(boolean val) {
		_minimized = val;
	}
	
	private final List<String> _cmds = new ArrayList<>();
	
	public ProcCaller(@Nonnull String... command) {
        Collections.addAll(_cmds, command);
		
		_builder = new ProcessBuilder(_cmds);
	}
}
