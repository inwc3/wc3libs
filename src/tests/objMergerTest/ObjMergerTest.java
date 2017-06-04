package tests.objMergerTest;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.Vector;

import org.testng.annotations.Test;

import com.sun.glass.ui.GestureSupport;

import net.moonlightflower.wc3libs.app.ObjMerger;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.port.Orient;

public class ObjMergerTest {
	@Test
	public ObjMergerTest() {
		ObjMerger merger = new ObjMerger();
		
		try {
			File inFile = new File(getClass().getResource("in.w3x").getFile());
			File outFile = new File("D:\\merged\\merged.w3x");
			
			Orient.copyFile(inFile, outFile, true);
			
			File inDir = new File("D:\\merged\\in");
			
			merger.exportMap(inFile, inDir);
			
			//merger.readFromMap(inFile, true);
			merger.addDir(inDir);
			
			File outDir = new File("D:\\merged\\out");
			
			merger.writeToDir(outDir);
			
			merger.writeToMap(outFile, outDir);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//merger.addDir(dir);
	}
	
	public static void main(String[] args) {
		new ObjMergerTest();
	}
}
