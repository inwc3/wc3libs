package net.moonlightflower.wc3libs.misc.audio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Vector;

import javax.sound.sampled.*;

public class WAV {
	final static int RATE = 16 * 1024;
	
   public static byte[] createSinWaveBuffer(double freq, int ms) {
       int samples = (ms * RATE) / 1000;
       byte[] output = new byte[samples];
           //
       double period = (double)RATE / freq;
       for (int i = 0; i < output.length; i++) {
           double angle = 2.0 * Math.PI * i / period;

           output[i] = (byte)(Math.abs(Math.sin(angle)) * 10f);
           
           if (angle > Math.PI) {
        	   output[i] = (byte) 10f;
           } else {
        	   output[i] = 0;
           }

           angle = angle % (2 * Math.PI);
           
           if (angle > Math.PI) {
        	   output[i] = (byte) (5f - 5f * angle / (2 * Math.PI));
           } else {
        	   output[i] = (byte) (0f + 5f * angle / (2 * Math.PI));
           }
           //output[i] = (byte) (10f * angle / 2 * Math.PI);
       }

       return output;
   }
	
	private byte[] getTone(int durMS) {		
		int count = (durMS * RATE) / 1000;
		
		byte[] ret = new byte[count];
		
		for (int i = 0; i < ret.length; i++) {
			ret[i] = (byte) 127F;
		}
		
		return ret;
	}
	
	public WAV(File inFile, boolean a) throws LineUnavailableException {
		AudioFormat af = new AudioFormat(RATE, 8, 1, true, true);
		
		SourceDataLine line = AudioSystem.getSourceDataLine(af);
		
		line.open(af, RATE);
		line.start();

       for(double freq = 3200; freq <= 3200;)  {
           byte [] toneBuffer = createSinWaveBuffer(freq, 5000);
           int count = line.write(toneBuffer, 0, toneBuffer.length);
           
           System.out.println(count);

           freq += 100;  
       }
		
		line.drain();
		line.close();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void play() {
		
	}
	
	private void read(InputStream inStream) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		Vector<Byte> bBuf = new Vector<>();
		int val;
		
		while ((val = inStream.read()) != -1) {
			bBuf.add((byte) val);
		}
		
		byte[] bytes = new byte[bBuf.size()];
		
		for (int i = 0; i < bBuf.size(); i++) {
			bytes[i] = bBuf.get(i);
		}
		
		ByteArrayInputStream inByteStream = new ByteArrayInputStream(bytes);
		
		AudioInputStream inAudioStream = AudioSystem.getAudioInputStream(inByteStream);
		
		System.out.println(inAudioStream);
		
		AudioFormat inFormat = inAudioStream.getFormat();
		
		System.out.println(inFormat);
		
		System.out.println(inFormat.getSampleRate()*4);
		
		AudioFormat outFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, inFormat.getSampleRate()*4, inFormat.getSampleSizeInBits(), inFormat.getChannels(), inFormat.getChannels() * 2, inFormat.getSampleRate(), !inFormat.isBigEndian());
		//AudioFormat outFormat = new AudioFormat(1, 1, 1, false, false);
		
		//ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		
		AudioInputStream newInAudioStream = AudioSystem.getAudioInputStream(outFormat, inAudioStream);
		
		//AudioSystem.write(inAudioStream, AudioFileFormat.Type.WAVE, outStream);
		
		DataLine.Info info = new DataLine.Info(Clip.class, outFormat);
		
		System.out.println(info);
		
		Clip clip = (Clip) AudioSystem.getLine(info);
		
		System.out.println(clip);
		
		clip.open(newInAudioStream);

		clip.setFramePosition(0);
		
		clip.start();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public WAV(InputStream inStream) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		read(inStream);
	}
	
	public WAV(File inFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		InputStream inStream = new FileInputStream(inFile);
		
		read(inStream);
		
		inStream.close();
	}
}
