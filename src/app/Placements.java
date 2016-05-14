package app;

import java.util.ArrayList;
import java.util.List;

import bin.app.DOO;
import bin.app.DOO_UNITS;
import bin.app.W3C;
import bin.app.W3R;
import dataTypes.app.Bounds;
import dataTypes.app.Color;
import dataTypes.app.Int;
import dataTypes.app.SoundLabel;
import dataTypes.app.Wc3String;
import dataTypes.app.WeatherId;
import misc.Id;

interface IRemovalListener {
	void exec();
}

public class Placements {
	private DOO _doo;
	private DOO_UNITS _dooUnits;
	
	private W3C _w3c;
	private W3R _w3r;
	
	public static class Rect {
		private Int _index;
		
		public Int getIndex() {
			return _index;
		}
		
		public void setIndex(Int val) {
			_index = val;
		}
		
		private Bounds _bounds;
		
		public Bounds getBounds() {
			return _bounds;
		}
		
		public void setBounds(Bounds val) {
			_bounds = val;
		}
		
		private Wc3String _name;
		
		public Wc3String getName() {
			return _name;
		}
	
		public void setName(Wc3String val) {
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
		
		private List<IRemovalListener> _removalListeners = new ArrayList<>();
		
		public void addRemovalListener(IRemovalListener val) {
			_removalListeners.add(val);
		}
			
		
		public Rect() {
			setBounds(new Bounds(0, 0, 0, 0));
		}
	}

	private List<Rect> _rects = new ArrayList<>();
	
	public List<Rect> getRects() {
		return _rects;
	}

	public Rect addRect() {
		Rect rect = new Rect();
	
		getRects().add(rect);

		rect.addRemovalListener(new IRemovalListener() {
			@Override
			public void exec() {
				getRects().remove(rect);
			}
		});

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

	public void decode(W3R enc) {
		if (enc == null) {
			throw new IllegalArgumentException();
		}

		for (W3R.Rect encRect : enc.getRects()) {
			Rect rect = addRect();

			rect.setBounds(encRect.getBounds());
			rect.setColor(encRect.getColor());
			rect.setName(encRect.getName());
			rect.setSound(encRect.getSound());
			rect.setWeather(encRect.getWeather());
		}
	}

	public Placements(W3R w3r) {
		_w3r = w3r;
	}
	
	public Placements() {
		this(new W3R());
	}
}
