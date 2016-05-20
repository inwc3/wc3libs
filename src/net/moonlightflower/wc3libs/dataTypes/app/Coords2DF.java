package net.moonlightflower.wc3libs.dataTypes.app;

public class Coords2DF {
	private Real _x;
	private Real _y;
	
	public Real getX() {
		return _x;
	}

	public Real getY() {
		return _y;
	}
	
	/*public Coords2D<Real> toReal() {
		return new Coords2D<>(Real.valueOf(getX()), Real.valueOf(getY()));
	}*/
	
	public Coords2DF scale(double factor) {
		Double x = getX().toFloat() * factor;
		Double y = getY().toFloat() * factor;
		
		return new Coords2DF(x.intValue(), y.intValue());
	}
	
	public Coords2DF(float x, float y) {
		_x = Real.valueOf(x);
		_y = Real.valueOf(y);
	}
	
	public Coords2DF(Real x, Real y) {
		this(x.toFloat(), y.toFloat());
	}
}
