package tests.objMergerTest;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.Vector;

import com.sun.glass.ui.GestureSupport;

import net.moonlightflower.wc3libs.app.ObjMerger;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.port.Orient;

public class ObjMergerTest {
	public ObjMergerTest() {
		try {
			ObjMerger merger = new ObjMerger();
			
			//File classDir = new File(Orient.getWorkingDir(), "tests\\objMergerTest");
			File inMapFile = new File(getClass().getResource("in.w3x").getFile());
			
			File workDir = new File(new File(getClass().getResource("").getFile()), "work");
			
			System.out.println(workDir);
			
			//merger.setWorkDir(workDir);

			System.out.println("map:" + inMapFile);
			//merger.readFromMap(inMapFile, true);
			
			File inDir = new File(workDir, "in");
			File outDir = new File(workDir, "out");
			
			//merger.exportMap(inMapFile, inDir);
			
			merger.addDir(inDir);
			
			merger.writeToDir(outDir);
			
			File outMapFile = new File(workDir, "merged.w3x");
			
			Orient.copyFile(inMapFile, outMapFile, true);
			System.out.println(outMapFile + ";" + outMapFile.exists());
			merger.writeToMap(outMapFile, outDir);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//merger.addDir(dir);
	}
	
	public static void main2(String[] args) {
		new ObjMergerTest();
	}
	
	public static void main(String[] args) throws Exception {
		/*File fileA = new File("C:\\Users\\Win7F\\workspaceMars\\wc3libs\\bin\\tests\\objMergerTest\\work\\out\\Units\\UndeadUnitStrings.txt");
		File fileB = new File("C:\\Users\\Win7F\\workspaceMars\\wc3libs\\bin\\tests\\objMergerTest\\work\\out\\Units\\OrcUpgradeStrings.txt");
		
		ByteBuffer dataA = ByteBuffer.wrap(Files.readAllBytes(fileA.toPath()));
		ByteBuffer dataB = ByteBuffer.wrap(Files.readAllBytes(fileB.toPath()));
		
		System.out.println(dataA.hashCode() + ";" + dataB.hashCode());*/
		
		File outFile = new File("C:\\Users\\Win7F\\workspaceMars\\wc3libs\\bin\\tests\\objMergerTest\\work\\out\\war3map.w3u");
		File inFile = new File("war3map.w3u");
		
		MpqPort.In portIn = new JMpqPort.In();
		
		portIn.add(outFile, inFile);
		
		portIn.commit(new File("C:\\Users\\Win7F\\workspaceMars\\wc3libs\\bin\\tests\\objMergerTest\\work\\merged.w3x"));
	}
}
