package net.moonlightflower.wc3libs.app;

import java.util.ArrayList;
import java.util.List;

import net.moonlightflower.wc3libs.bin.app.DOO;
import net.moonlightflower.wc3libs.bin.app.DOO_UNITS;
import net.moonlightflower.wc3libs.bin.app.W3C;
import net.moonlightflower.wc3libs.bin.app.W3R;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.dataTypes.app.War3Int;

import javax.annotation.Nonnull;

public class Placements {
	private DOO _doo;
	private DOO_UNITS _dooUnits;
	
	private W3C _w3c;
	private W3R _w3r;
	
	public static class Rect {
		private War3Int _index;
		
		public War3Int getIndex() {
			return _index;
		}
		
		public void setIndex(War3Int val) {
			_index = val;
		}
		
		private Bounds _bounds;
		
		public Bounds getBounds() {
			return _bounds;
		}
		
		public void setBounds(Bounds val) {
			_bounds = val;
		}
		
		private War3String _name;
		
		public War3String getName() {
			return _name;
		}
	
		public void setName(War3String val) {
			_name = val;
		}

		private WeatherId _weather;
		
		public WeatherId getWeather() {
			return _weather;
		}
	
		public void setWeather(WeatherId val) {
			_weather = val;
		}
		
		private SoundLabel _sound;
		
		public SoundLabel getSound() {
			return _sound;
		}
	
		public void setSound(SoundLabel val) {
			_sound = val;
		}

		private Color _color;
		
		public Color getColor() {
			return _color;
		}
	
		public void setColor(Color val) {
			_color = val;
		}
		
		public Rect() {
			setBounds(new Bounds(0, 0, 0, 0));
		}
	}

	private final List<Rect> _rects = new ArrayList<>();
	
	public List<Rect> getRects() {
		return new ArrayList<>(_rects);
	}

	public Rect addRect() {
		Rect rect = new Rect();
	
		getRects().add(rect);

		return rect;
	}

	public W3R encode() {
		W3R enc = new W3R();

		for (Rect rect : getRects()) {
			W3R.Rect encRect = enc.addRect();
			
			encRect.setBounds(rect.getBounds());
			encRect.setColor(rect.getColor());
			encRect.setIndex(rect.getIndex());
			encRect.setName(rect.getName());
			encRect.setSound(rect.getSound());
			encRect.setWeather(rect.getWeather());
		}

		return enc;
	}

	public void decode(@Nonnull W3R enc) {
		for (W3R.Rect encRect : enc.getRects()) {
			Rect rect = addRect();

			rect.setBounds(encRect.getBounds());
			rect.setColor(encRect.getColor());
			rect.setName(encRect.getName());
			rect.setSound(encRect.getSound());
			rect.setWeather(encRect.getWeather());
		}
	}

	public Placements(@Nonnull W3R w3r) {
		_w3r = w3r;
	}
	
	public Placements() {
		this(new W3R());
	}
}
