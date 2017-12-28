package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.app.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
	
	public static class Camera extends Bin {
		private static class State<T extends DataType> extends BinState<T> {
			private static final List<W3C.Camera.State> _values = new ArrayList<>();

			public static List<W3C.Camera.State> values() {
				return _values;
			}

			public State(@Nonnull DataTypeInfo typeInfo, @Nonnull String idString, T defVal) {
				super(typeInfo, idString, defVal);

				_values.add(this);
			}

			public State(@Nonnull DataTypeInfo typeInfo, @Nonnull String idString) {
				this(typeInfo, idString, null);
			}

			public State(@Nonnull Class<T> type, @Nonnull String idString) {
				this(new DataTypeInfo(type), idString);
			}

			public State(@Nonnull Class<T> type, @Nonnull String idString, T defVal) {
				this(new DataTypeInfo(type), idString, defVal);
			}
		}

		public final static W3C.Camera.State<Coords2DF> ART_TARGET = new W3C.Camera.State<>(Coords2DF.class, "target", new Coords2DF(0F, 0F));
		public final static W3C.Camera.State<Real> ART_Z_OFFSET = new W3C.Camera.State<>(Real.class, "zOffset", Real.valueOf(0F));
		public final static W3C.Camera.State<Real> ART_ROTATION = new W3C.Camera.State<>(Real.class, "rotation", Real.valueOf(0F));
		public final static W3C.Camera.State<Real> ART_ANGLE_OF_ATTACK = new W3C.Camera.State<>(Real.class, "angleOfAttack", Real.valueOf(0F));
		public final static W3C.Camera.State<Real> ART_ROLL = new W3C.Camera.State<>(Real.class, "roll", Real.valueOf(0F));
		public final static W3C.Camera.State<Real> ART_DIST = new W3C.Camera.State<>(Real.class, "dist", Real.valueOf(0F));
		public final static W3C.Camera.State<Real> ART_FIELD_OF_VIEW = new W3C.Camera.State<>(Real.class, "fieldOfView", Real.valueOf(0F));
		public final static W3C.Camera.State<Real> ART_FAR_Z = new W3C.Camera.State<>(Real.class, "farZ", Real.valueOf(10000F));
		public final static W3C.Camera.State<Real> ART_UNKNOWN = new W3C.Camera.State<>(Real.class, "unknown", Real.valueOf(100F));

		public final static W3C.Camera.State<Wc3String> EDITOR_CINE_NAME = new W3C.Camera.State<>(Wc3String.class, "cineName", Wc3String.valueOf("unnamed"));

		public <T extends DataType> T get(@Nonnull W3C.Camera.State<T> state) {
			return state.tryCastVal(super.get(state));
		}

		public <T extends DataType> void set(@Nonnull W3C.Camera.State<T> state, @Nullable T val) {
			super.set(state, val);
		}

		public <T extends DataType> void reset(@Nonnull W3C.Camera.State<T> state) {
			super.set(state, state.getDefVal());
		}

		public void init() {
			for (State<?> state : State.values()) {
				set(state, state.getDefVal());
			}
		}

		public Coords2DF getTarget() {
			return get(ART_TARGET);
		}
		
		public void setTarget(Coords2DF val) {
			set(ART_TARGET, val);
		}
		
		public Real getZOffset() {
			return get(ART_Z_OFFSET);
		}
		
		public void setZOffset(Real val) {
			set(ART_Z_OFFSET, val);
		}
		
		public Real getRotation() {
			return get(ART_ROTATION);
		}
		
		public void setRotation(Real val) {
			set(ART_ROTATION, val);
		}
		
		public Real getAngleOfAttack() {
			return get(ART_ANGLE_OF_ATTACK);
		}
		
		public void setAngleOfAttack(Real val) {
			set(ART_ANGLE_OF_ATTACK, val);
		}
		
		public Real getRoll() {
			return get(ART_ROLL);
		}
		
		public void setRoll(Real val) {
			set(ART_ROLL, val);
		}
		
		public Real getDist() {
			return get(ART_DIST);
		}
		
		public void setDist(Real val) {
			set(ART_DIST, val);
		}
		
		public Real getFieldOfView() {
			return get(ART_FIELD_OF_VIEW);
		}
		
		public void setFieldOfView(Real val) {
			set(ART_FIELD_OF_VIEW, val);
		}
		
		public Real getFarZ() {
			return get(ART_FAR_Z);
		}
		
		public void setFarZ(Real val) {
			set(ART_FAR_Z, val);
		}
		
		public Real getUnknown() {
			return get(ART_UNKNOWN);
		}
		
		public void setUnknown(Real val) {
			set(ART_UNKNOWN, val);
		}
		
		public Wc3String getCineName() {
			return get(EDITOR_CINE_NAME);
		}
		
		public void setCineName(Wc3String val) {
			set(EDITOR_CINE_NAME, val);
		}

		public void write_0x0(@Nonnull Wc3BinOutputStream stream) {
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
			
			stream.writeFloat32(getUnknown());
			
			stream.writeString(getCineName());
		}

		public void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
			setTarget(new Coords2DF(stream.readFloat32("targetX"), stream.readFloat32("targetY")));
			setZOffset(stream.readReal("zOffset"));

			setRotation(stream.readReal("rotation"));
			setAngleOfAttack(stream.readReal("angleOfAttack"));
			setDist(stream.readReal("dist"));
			
			setRoll(stream.readReal("roll"));
			setFieldOfView(stream.readReal("fieldOfView"));
			setFarZ(stream.readReal("farZ"));
			setUnknown(stream.readReal("unknown"));
			
			setCineName(Wc3String.valueOf(stream.readString("cineName")));
		}
		
		public void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
			switch (format.toEnum()) {
			case W3C_0x0: {
				read_0x0(stream);
				
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
	
	public Camera addCamera() {
		Camera camera = new Camera();

		addCamera(camera);
		
		return camera;
	}
	
	public static class EncodingFormat extends Format<EncodingFormat.Enum> {
		public enum Enum {
			AUTO,
			W3C_0x0,
		}

		private static Map<Integer, EncodingFormat> _map = new LinkedHashMap<>();
		
		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat WPM_0x0 = new EncodingFormat(Enum.W3C_0x0, 0x0);

		@Nullable
		public static EncodingFormat valueOf(int version) {
			return _map.get(version);
		}
		
		private EncodingFormat(@Nonnull Enum enumVal, int version) {
			super(enumVal, version);
			
			_map.put(version, this);
		}
	}
	
	public void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int version = stream.readInt32("version");

		Wc3BinInputStream.checkFormatVer("camMaskFunc", EncodingFormat.WPM_0x0.getVersion(), version);

		int camsCount = stream.readInt32("camsCount");

		for (int i = 0; i < camsCount; i++) {
			addCamera(new Camera(stream, EncodingFormat.WPM_0x0));
		}
	}
	
	public void write_0x0(@Nonnull Wc3BinOutputStream stream) {
		stream.writeInt32(EncodingFormat.WPM_0x0.getVersion());
		
		stream.writeInt32(getCameras().size());
		
		for (Camera camera : getCameras()) {
			camera.write(stream, EncodingFormat.WPM_0x0);
		}
	}
	
	private void read_auto(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int version = stream.readInt32();
		
		stream.rewind();

		EncodingFormat format = EncodingFormat.valueOf(version);

		if (format == null) throw new IllegalArgumentException("unknown format " + version);

		read(stream, format);
	}

	private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
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
	
	private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
		switch (format.toEnum()) {
		case AUTO:
		case W3C_0x0: {
			write_0x0(stream);
			
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
		Wc3BinOutputStream outStream = new Wc3BinOutputStream(file);

		write(outStream);

		outStream.close();
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
}
