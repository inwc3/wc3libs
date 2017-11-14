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
	
	@Override
	public String toString() {
		return String.format("%s;%s", getX(), getY());
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Coords2DF coords2DF = (Coords2DF) o;

		if (_x != null ? !_x.equals(coords2DF._x) : coords2DF._x != null) return false;
		return _y != null ? _y.equals(coords2DF._y) : coords2DF._y == null;
	}

	@Override
	public int hashCode() {
		int result = _x != null ? _x.hashCode() : 0;
		result = 31 * result + (_y != null ? _y.hashCode() : 0);
		return result;
	}
}
