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
			public final static W3C.Camera.State<War3Real> ART_UNKNOWN = new W3C.Camera.State<>("unknown", War3Real.class, War3Real.valueOf(100F));

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
		
		public War3Real getUnknown() {
			return get(State.ART_UNKNOWN);
		}
		
		public void setUnknown(War3Real val) {
			set(State.ART_UNKNOWN, val);
		}
		
		public War3String getCineName() {
			return get(State.EDITOR_CINE_NAME);
		}
		
		public void setCineName(War3String val) {
			set(State.EDITOR_CINE_NAME, val);
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
			
			setCineName(War3String.valueOf(stream.readString("cineName")));
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
		}
		
		public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
		public final static EncodingFormat WPM_0x0 = new EncodingFormat(Enum.W3C_0x0, 0x0);

		@Nullable
		public static EncodingFormat valueOf(@Nonnull Integer version) {
			return get(EncodingFormat.class, version);
		}

		private EncodingFormat(@Nonnull Enum enumVal, int version) {
			super(enumVal, version);
		}
	}
	
	public void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
		int version = stream.readInt32("version");

		stream.checkFormatVersion(EncodingFormat.WPM_0x0.getVersion(), version);

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

		read(stream, stream.getFormat(EncodingFormat.class, version));
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
