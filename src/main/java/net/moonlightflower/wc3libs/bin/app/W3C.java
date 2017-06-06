package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Format;
import net.moonlightflower.wc3libs.bin.Wc3BinStream;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DF;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//import bin.Wc3bin;

/**
 * cameras file for wrapping war3map.w3c
 */
public class W3C {
	public final static File GAME_PATH = new File("war3map.w3c");
	
	private class Obj {
		private Coords2DF _target = new Coords2DF(0F, 0F);
		
		public Coords2DF getTarget() {
			return _target;
		}
		
		public void setTarget(Coords2DF val) {
			_target = val;
		}
		
		private float _zOffset = 0;
		
		public float getZOffset() {
			return _zOffset;
		}
		
		public void setZOffset(float val) {
			_zOffset = val;
		}
		
		private float _rotation = 0;
		
		public float getRotation() {
			return _rotation;
		}
		
		public void setRotation(float val) {
			_rotation = val;
		}

		private float _angleOfAttack = 0;
		
		public float getAngleOfAttack() {
			return _angleOfAttack;
		}
		
		public void setAngleOfAttack(float val) {
			_angleOfAttack = val;
		}
		
		private float _roll = 0;
		
		public float getRoll() {
			return _roll;
		}
		
		public void setRoll(float val) {
			_roll = val;
		}
		
		private float _dist = 0;
		
		public float getDist() {
			return _dist;
		}
		
		public void setDist(float val) {
			_dist = val;
		}

		private float _fieldOfView = 0;
		
		public float getFieldOfView() {
			return _fieldOfView;
		}
		
		public void setFieldOfView(float val) {
			_fieldOfView = val;
		}

		private float _farZ = 10000;
		
		public float getFarZ() {
			return _farZ;
		}
		
		public void setFarZ(float val) {
			_farZ = val;
		}
		
		private float _unknown = 100;
		
		public float getUnknown() {
			return _unknown;
		}
		
		public void setUnknown(float val) {
			_unknown = val;
		}

		private String _cineName;
		
		public String getCineName() {
			return _cineName;
		}
		
		public void setCineName(String val) {
			_cineName = val;
		}

		public void write_0x0(Wc3BinStream stream) {
			Coords2DF target = getTarget();
			
			stream.writeFloat(target.getX());
			stream.writeFloat(target.getY());
			stream.writeFloat(getZOffset());
			
			stream.writeFloat(getRotation());
			stream.writeFloat(getAngleOfAttack());
			stream.writeFloat(getDist());
			
			stream.writeFloat(getRoll());
			stream.writeFloat(getFieldOfView());
			stream.writeFloat(getFarZ());
			
			stream.writeFloat(getUnknown());
			
			stream.writeString(getCineName());
		}

		public void read_0x0(Wc3BinStream stream) throws BinStream.StreamException {
			setTarget(new Coords2DF(stream.readFloat("targetX"), stream.readFloat("targetY")));
			setZOffset(stream.readFloat("zOffset"));

			setRotation(stream.readFloat("rotation"));
			setAngleOfAttack(stream.readFloat("angleOfAttack"));
			setDist(stream.readFloat("dist"));
			
			setRoll(stream.readFloat("roll"));
			setFieldOfView(stream.readFloat("fieldOfView"));
			setFarZ(stream.readFloat("farZ"));
			setUnknown(stream.readFloat("unknown"));
			
			setCineName(stream.readString("cineName"));
		}
		
		public void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
			switch (format.toEnum()) {
			case W3C_0x0: {
				read_0x0(stream);
				
				break;
			}
			}
		}
		
		public void write(Wc3BinStream stream, EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case W3C_0x0: {
				write_0x0(stream);
				
				break;
			}
			}
		}
		
		public Obj(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
			read(stream, format);
		}
		
		public Obj() {
		}
	}
	
	private List<Obj> _objs = new ArrayList<>();
	
	public List<Obj> getObjs() {
		return _objs;
	}
	
	private void addObj(Obj val) {
		_objs.add(val);
	}
	
	public Obj addObj() {
		Obj obj = new Obj();

		addObj();
		
		return obj;
	}
	
	private static class EncodingFormat extends Format<EncodingFormat.Enum> {
		enum Enum {
			AUTO,
			W3C_0x0,
		}

		private static Map<Integer, EncodingFormat> _map = new LinkedHashMap<>();
		
		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat WPM_0x0 = new EncodingFormat(Enum.W3C_0x0, 0x0);
		
		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}
		
		private EncodingFormat(Enum enumVal, int version) {
			super(enumVal, version);
			
			_map.put(version, this);
		}
	}
	
	public void read_0x0(Wc3BinStream stream) throws BinStream.StreamException {
		int version = stream.readInt("version");

		Wc3BinStream.checkFormatVer("camMaskFunc", EncodingFormat.WPM_0x0.getVersion(), version);

		int camsCount = stream.readInt("camsCount");

		for (int i = 0; i < camsCount; i++) {
			addObj(new Obj(stream, EncodingFormat.WPM_0x0));
		}
	}
	
	public void write_0x0(Wc3BinStream stream) {
		stream.writeInt(EncodingFormat.WPM_0x0.getVersion());
		
		stream.writeInt(getObjs().size());
		
		for (Obj obj : getObjs()) {
			obj.write(stream, EncodingFormat.WPM_0x0);
		}
	}
	
	private void read_auto(Wc3BinStream stream) throws BinStream.StreamException {
		int version = stream.readInt();
		
		stream.rewind();

		read(stream, EncodingFormat.valueOf(version));
	}

	private void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {		
		switch (format.toEnum()) {
		case AUTO: {
			read_auto(stream);
			
			break;
		}
		case W3C_0x0: {
			read_0x0(stream);
			
			break;
		}
		}
	}
	
	private void write(Wc3BinStream stream, EncodingFormat format) {
		switch (format.toEnum()) {
		case AUTO:
		case W3C_0x0: {
			write_0x0(stream);
			
			break;
		}
		}
	}
	
	private void read(Wc3BinStream stream) throws BinStream.StreamException {
		read(stream, EncodingFormat.AUTO);
	}
	
	private void write(Wc3BinStream stream) {
		write(stream, EncodingFormat.AUTO);
	}
	
	private void read(InputStream inStream, EncodingFormat format) throws IOException, BinStream.StreamException {
		read(new Wc3BinStream(inStream), format);
	}
	
	private void write(File file, EncodingFormat format) throws IOException {
		write(new Wc3BinStream(file), format);
	}
	
	private void read(InputStream inStream) throws IOException {
		read(inStream, EncodingFormat.AUTO);
	}

	private void write(File file) throws IOException {
		write(new Wc3BinStream(file));
	}
	
	public W3C(Wc3BinStream inStream) throws BinStream.StreamException {
		this();
		
		read(inStream);
	}
	
	public W3C(InputStream inStream) throws IOException {
		this();
		
		read(inStream);
	}
	
	public W3C() {
		
	}
}
