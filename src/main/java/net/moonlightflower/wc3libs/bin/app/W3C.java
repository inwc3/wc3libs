package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DF;
import net.moonlightflower.wc3libs.dataTypes.app.War3Real;
import net.moonlightflower.wc3libs.dataTypes.app.War3String;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//import bin.Wc3bin;

/**
 * cameras file for wrapping war3map.w3c
 */
public class W3C {
	public final static File GAME_PATH = new File("war3map.w3c");
	
	public static class Camera extends Bin {
		public static class State<T extends DataType> extends BinState<T> {
			public final static W3C.Camera.State<Coords2DF> ART_TARGET = new W3C.Camera.State<>("target", Coords2DF.class, new Coords2DF(0F, 0F));
			public final static W3C.Camera.State<War3Real> ART_Z_OFFSET = new W3C.Camera.State<>("zOffset", War3Real.class, War3Real.valueOf(0F));
			public final static W3C.Camera.State<War3Real> ART_ROTATION = new W3C.Camera.State<>("rotation", War3Real.class, War3Real.valueOf(0F));
			public final static W3C.Camera.State<War3Real> ART_ANGLE_OF_ATTACK = new W3C.Camera.State<>("angleOfAttack", War3Real.class, War3Real.valueOf(0F));
			public final static W3C.Camera.State<War3Real> ART_ROLL = new W3C.Camera.State<>("roll", War3Real.class, War3Real.valueOf(0F));
			public final static W3C.Camera.State<War3Real> ART_DIST = new W3C.Camera.State<>("dist", War3Real.class, War3Real.valueOf(0F));
			public final static W3C.Camera.State<War3Real> ART_FIELD_OF_VIEW = new W3C.Camera.State<>("fieldOfView", War3Real.class, War3Real.valueOf(0F));
			public final static W3C.Camera.State<War3Real> ART_FAR_Z = new W3C.Camera.State<>("farZ", War3Real.class, War3Real.valueOf(10000F));
			public final static W3C.Camera.State<War3Real> ART_NEAR_Z = new W3C.Camera.State<>("nearZ", War3Real.class, War3Real.valueOf(100F));
			/** @deprecated use {@link #ART_NEAR_Z}. */
			@Deprecated
			public final static W3C.Camera.State<War3Real> ART_UNKNOWN = ART_NEAR_Z;
			public final static W3C.Camera.State<War3Real> ART_LOCAL_PITCH = new W3C.Camera.State<>("localPitch", War3Real.class, War3Real.valueOf(0F));
			public final static W3C.Camera.State<War3Real> ART_LOCAL_YAW = new W3C.Camera.State<>("localYaw", War3Real.class, War3Real.valueOf(0F));
			public final static W3C.Camera.State<War3Real> ART_LOCAL_ROLL = new W3C.Camera.State<>("localRoll", War3Real.class, War3Real.valueOf(0F));
			public final static W3C.Camera.State<War3Real> ART_DEPTH_OF_FIELD_DISTANCE = new W3C.Camera.State<>("depthOfFieldDistance", War3Real.class, War3Real.valueOf(0F));
			public final static W3C.Camera.State<War3Real> ART_DEPTH_OF_FIELD_SCALE = new W3C.Camera.State<>("depthOfFieldScale", War3Real.class, War3Real.valueOf(0F));
			public final static W3C.Camera.State<War3Real> ART_ABSOLUTE_Z = new W3C.Camera.State<>("absoluteZ", War3Real.class, War3Real.valueOf(0F));

			public final static W3C.Camera.State<War3String> EDITOR_CINE_NAME = new W3C.Camera.State<>("cineName", War3String.class, War3String.valueOf("unnamed"));

			public State(@Nonnull String idString, @Nonnull DataTypeInfo typeInfo, @Nullable T defVal) {
				super(idString, typeInfo, defVal);
			}

			public State(@Nonnull String idString, @Nonnull DataTypeInfo typeInfo) {
				super(idString, typeInfo);
			}

			public State(@Nonnull String idString, @Nonnull Class<T> type) {
				super(idString, type);
			}

			public State(@Nonnull String idString, @Nonnull Class<T> type, @Nullable T defVal) {
				super(idString, type, defVal);
			}
		}

		public <T extends DataType> T get(@Nonnull W3C.Camera.State<T> state) {
			try {
				return state.tryCastVal(super.get(state));
			} catch (DataTypeInfo.CastException ignored) {
			}

			return null;
		}

		public <T extends DataType> void set(@Nonnull State<T> state, @Nullable T val) {
			super.set(state, val);
		}

		public <T extends DataType> void reset(@Nonnull State<T> state) {
			super.set(state, state.getDefVal());
		}

		public void init() {
			for (State<?> state : State.values(State.class)) {
				set(state, state.getDefVal());
			}
		}

		public Coords2DF getTarget() {
			return get(State.ART_TARGET);
		}
		
		public void setTarget(Coords2DF val) {
			set(State.ART_TARGET, val);
		}
		
		public War3Real getZOffset() {
			return get(State.ART_Z_OFFSET);
		}
		
		public void setZOffset(War3Real val) {
			set(State.ART_Z_OFFSET, val);
		}
		
		public War3Real getRotation() {
			return get(State.ART_ROTATION);
		}
		
		public void setRotation(War3Real val) {
			set(State.ART_ROTATION, val);
		}
		
		public War3Real getAngleOfAttack() {
			return get(State.ART_ANGLE_OF_ATTACK);
		}
		
		public void setAngleOfAttack(War3Real val) {
			set(State.ART_ANGLE_OF_ATTACK, val);
		}
		
		public War3Real getRoll() {
			return get(State.ART_ROLL);
		}
		
		public void setRoll(War3Real val) {
			set(State.ART_ROLL, val);
		}
		
		public War3Real getDist() {
			return get(State.ART_DIST);
		}
		
		public void setDist(War3Real val) {
			set(State.ART_DIST, val);
		}
		
		public War3Real getFieldOfView() {
			return get(State.ART_FIELD_OF_VIEW);
		}
		
		public void setFieldOfView(War3Real val) {
			set(State.ART_FIELD_OF_VIEW, val);
		}
		
		public War3Real getFarZ() {
			return get(State.ART_FAR_Z);
		}
		
		public void setFarZ(War3Real val) {
			set(State.ART_FAR_Z, val);
		}
		
		public War3Real getNearZ() {
			return get(State.ART_NEAR_Z);
		}

		public void setNearZ(War3Real val) {
			set(State.ART_NEAR_Z, val);
		}

		/** @deprecated use {@link #getNearZ()}. */
		@Deprecated
		public War3Real getUnknown() { return getNearZ(); }

		/** @deprecated use {@link #setNearZ(War3Real)}. */
		@Deprecated
		public void setUnknown(War3Real val) { setNearZ(val); }
		
		public War3String getCineName() {
			return get(State.EDITOR_CINE_NAME);
		}
		
		public void setCineName(War3String val) {
			set(State.EDITOR_CINE_NAME, val);
		}

		public War3Real getLocalPitch() { return get(State.ART_LOCAL_PITCH); }
		public void setLocalPitch(War3Real val) { set(State.ART_LOCAL_PITCH, val); }
		public War3Real getLocalYaw() { return get(State.ART_LOCAL_YAW); }
		public void setLocalYaw(War3Real val) { set(State.ART_LOCAL_YAW, val); }
		public War3Real getLocalRoll() { return get(State.ART_LOCAL_ROLL); }
		public void setLocalRoll(War3Real val) { set(State.ART_LOCAL_ROLL, val); }
		public War3Real getDepthOfFieldDistance() { return get(State.ART_DEPTH_OF_FIELD_DISTANCE); }
		public void setDepthOfFieldDistance(War3Real val) { set(State.ART_DEPTH_OF_FIELD_DISTANCE, val); }
		public War3Real getDepthOfFieldScale() { return get(State.ART_DEPTH_OF_FIELD_SCALE); }
		public void setDepthOfFieldScale(War3Real val) { set(State.ART_DEPTH_OF_FIELD_SCALE, val); }
		public War3Real getAbsoluteZ() { return get(State.ART_ABSOLUTE_Z); }
		public void setAbsoluteZ(War3Real val) { set(State.ART_ABSOLUTE_Z, val); }

		private int _cameraType = 0;

		public int getCameraType() { return _cameraType; }
		public void setCameraType(int val) { _cameraType = val; }
		public boolean isFreeCamera() { return getCameraType() == 1; }

		private void writeBase(@Nonnull Wc3BinOutputStream stream) {
			Coords2DF target = getTarget();
			
			stream.writeFloat32(target.getX());
			stream.writeFloat32(target.getY());
			stream.writeFloat32(getZOffset());
			
			stream.writeFloat32(getRotation());
			stream.writeFloat32(getAngleOfAttack());
			stream.writeFloat32(getDist());
			
			stream.writeFloat32(getRoll());
			stream.writeFloat32(getFieldOfView());
			stream.writeFloat32(getFarZ());
			
			stream.writeFloat32(getNearZ());
		}

		private void readBase(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			setTarget(new Coords2DF(stream.readFloat32("targetX"), stream.readFloat32("targetY")));
			setZOffset(stream.readReal("zOffset"));

			setRotation(stream.readReal("rotation"));
			setAngleOfAttack(stream.readReal("angleOfAttack"));
			setDist(stream.readReal("dist"));
			
			setRoll(stream.readReal("roll"));
			setFieldOfView(stream.readReal("fieldOfView"));
			setFarZ(stream.readReal("farZ"));
			setNearZ(stream.readReal("nearZ"));
		}

		public void write_0x0(@Nonnull Wc3BinOutputStream stream) {
			writeBase(stream);
			stream.writeString(getCineName());
		}

		public void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			readBase(stream);
			setCineName(War3String.valueOf(stream.readString("cineName")));
		}

		public void write_0x3(@Nonnull Wc3BinOutputStream stream) {
			writeBase(stream);
			stream.writeFloat32(getLocalPitch());
			stream.writeFloat32(getLocalYaw());
			stream.writeFloat32(getLocalRoll());
			stream.writeFloat32(getDepthOfFieldDistance());
			stream.writeFloat32(getDepthOfFieldScale());
			stream.writeFloat32(getAbsoluteZ());
			stream.writeString(getCineName());
			stream.writeInt32(getCameraType());
		}

		public void read_0x3(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			readBase(stream);
			setLocalPitch(stream.readReal("localPitch"));
			setLocalYaw(stream.readReal("localYaw"));
			setLocalRoll(stream.readReal("localRoll"));
			setDepthOfFieldDistance(stream.readReal("depthOfFieldDistance"));
			setDepthOfFieldScale(stream.readReal("depthOfFieldScale"));
			setAbsoluteZ(stream.readReal("absoluteZ"));
			setCineName(War3String.valueOf(stream.readString("cineName")));
			setCameraType(stream.readInt32("cameraType"));
		}
		
		public void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			switch (format.toEnum()) {
			case W3C_0x0: {
				read_0x0(stream);
				
				break;
			}
			case W3C_0x3: {
				read_0x3(stream);

				break;
			}
			}
		}
		
		public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
			switch (format.toEnum()) {
			case AUTO:
			case W3C_0x0: {
				write_0x0(stream);
				
				break;
			}
			case W3C_0x3: {
				write_0x3(stream);

				break;
			}
			}
		}
		
		public Camera(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			this();

			read(stream, format);
		}
		
		public Camera() {
			init();
		}
	}
	
	private List<Camera> _cameras = new ArrayList<>();

	@Nonnull
	public List<Camera> getCameras() {
		return _cameras;
	}
	
	private void addCamera(@Nonnull Camera val) {
		_cameras.add(val);
	}

	@Nonnull
	public Camera addCamera() {
		Camera camera = new Camera();

		addCamera(camera);
		
		return camera;
	}
	
	public static class EncodingFormat extends Format<EncodingFormat.Enum> {
		public enum Enum {
			AUTO,
			W3C_0x0,
			W3C_0x3,
		}
		
		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat WPM_0x0 = new EncodingFormat(Enum.W3C_0x0, 0x0);
		public final static EncodingFormat W3C_0x3 = new EncodingFormat(Enum.W3C_0x3, 0x3);

		@Nullable
		public static EncodingFormat valueOf(@Nonnull Integer version) {
			return get(EncodingFormat.class, version);
		}

		private EncodingFormat(@Nonnull Enum enumVal, int version) {
			super(enumVal, version);
		}
	}
	
	private EncodingFormat _format = EncodingFormat.WPM_0x0;

	@Nonnull
	public EncodingFormat getFormat() {
		return _format;
	}

	private void read_0x0(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
		int version = stream.readInt32("version");

		stream.checkFormatVersion(format.getVersion(), version);
		_format = format;

		int camsCount = stream.readInt32("camsCount");

		for (int i = 0; i < camsCount; i++) {
			addCamera(new Camera(stream, format));
		}
	}

	public void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		read_0x0(stream, EncodingFormat.WPM_0x0);
	}
	
	private void write_0x0(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
		stream.writeInt32(format.getVersion());
		
		stream.writeInt32(getCameras().size());
		
		for (Camera camera : getCameras()) {
			camera.write(stream, format);
		}
	}

	public void write_0x0(@Nonnull Wc3BinOutputStream stream) {
		write_0x0(stream, EncodingFormat.WPM_0x0);
	}
	
	private void read_auto(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int version = stream.readInt32();
		
		stream.rewind();

		read(stream, stream.getFormat(EncodingFormat.class, version));
	}

	private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
		switch (format.toEnum()) {
		case AUTO: {
			read_auto(stream);
			
			break;
		}
		case W3C_0x0:
		case W3C_0x3: {
			read_0x0(stream, format);
			
			break;
		}
		}
	}
	
	private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
		switch (format.toEnum()) {
		case AUTO: {
			write_0x0(stream, _format);

			break;
		}
		case W3C_0x0:
		case W3C_0x3: {
			write_0x0(stream, format);
			
			break;
		}
		}
	}
	
	private void read(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		read(stream, EncodingFormat.AUTO);
	}
	
	public void write(@Nonnull Wc3BinOutputStream stream) {
		write(stream, EncodingFormat.AUTO);
	}
	
	private void read(@Nonnull InputStream inStream, @Nonnull EncodingFormat format) throws IOException {
		read(new Wc3BinInputStream(inStream), format);
	}

	public void write(@Nonnull File file) throws IOException {
		try (Wc3BinOutputStream outStream = new Wc3BinOutputStream(file)) {
			write(outStream);
		}
	}
	
	public W3C(@Nonnull Wc3BinInputStream inStream) throws BinInputStream.StreamException {
		read(inStream);
	}
	
	public W3C(@Nonnull File file) throws IOException {
		Wc3BinInputStream inStream = new Wc3BinInputStream(file);

		read(inStream);

		inStream.close();
	}

	public W3C() {

	}

	@Nonnull
	public static W3C ofMapFile(@Nonnull File mapFile) throws IOException {
		if (!mapFile.exists()) throw new IOException(String.format("file %s does not exist", mapFile));

		MpqPort.Out port = new JMpqPort.Out();

		port.add(GAME_PATH);

		MpqPort.Out.Result portResult = port.commit(mapFile);

		if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract w3c file");

		Wc3BinInputStream inStream = new Wc3BinInputStream(portResult.getInputStream(GAME_PATH));

		W3C w3c = new W3C();

		w3c.read(inStream);

		inStream.close();

		return w3c;
	}
}
