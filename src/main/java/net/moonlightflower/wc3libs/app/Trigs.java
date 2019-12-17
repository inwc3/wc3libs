package net.moonlightflower.wc3libs.app;

import net.moonlightflower.wc3libs.bin.app.WCT;
import net.moonlightflower.wc3libs.bin.app.WTG;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class Trigs {
	private final WCT _wct;
	private final WTG _wtg;

	@Nonnull
	public WCT getWCT() {
		return _wct;
	}

	@Nonnull
	public WTG getWTG() {
		return _wtg;
	}
	
	public class Var {
		private String _name;
		
		public String getName() {
			return _name;
		}
		
		public void setName(String val) {
			_name = val;
		}
		
		private String _type;
		
		public String getType() {
			return _type;
		}
		
		public void setType(String val) {
			_type = val;
		}
		
		private String _initVal = null;
		
		public String getInitVal() {
			return _initVal;
		}
		
		public void setInitVal(String val) {
			_initVal = val;
		}
		
		int _arraySize = -1;
		
		public int getArraySize() {
			return _arraySize;
		}
		
		public boolean isArray() {
			return (getArraySize() >= 0);
		}
		
		public void setArraySize(int val) {
			_arraySize = val;
		}
		
		public Var(@Nonnull WTG.Var wtgVar) {
			setName(wtgVar.getName());
			setType(wtgVar.getType());
			setInitVal(wtgVar.getInitVal());
			setArraySize(wtgVar.getArraySize());
		}
		
		public Var() {
		}
	}
	
	private final List<Var> _vars = new ArrayList<>();

	public final List<Var> getVars() {
		return _vars;
	}
	
	private void addVar(@Nonnull Var val) {
		_vars.add(val);
	}
	
	public class TrigCat {
		private String _name;
		
		public String getName() {
			return _name;
		}
		
		public void setName(String val) {
			_name = val;
		}
		
		private boolean _isComment = false;
		
		public boolean isComment() {
			return _isComment;
		}
		
		public void setComment(boolean val) {
			_isComment = val;
		}
		
		public TrigCat(@Nonnull WTG.TrigCat wtgTrigCat) {
			setName(wtgTrigCat.getName());
		}
		
		public TrigCat() {
		}
	}
	
	private final List<TrigCat> _trigCats = new ArrayList<>();
	
	public List<TrigCat> getTrigCats() {
		return new ArrayList<>(_trigCats);
	}
	
	private void addTrigCat(@Nonnull TrigCat val) {
		_trigCats.add(val);
	}
	
	public abstract class Trig {
		private String _name;
		
		public String getName() {
			return _name;
		}
		
		public void setName(String val) {
			_name = val;
		}
		
		private String _comment;
		
		public String getComment() {
			return _comment;
		}
		
		public void setComment(String val) {
			_comment = val;
		}
		
		private boolean _enabled = true;
		
		public boolean isEnabled() {
			return _enabled;
		}
		
		public void setEnabled(boolean val) {
			_enabled = val;
		}

		private boolean _initiallyOn = true;
		
		public boolean isInitiallyOn() {
			return _initiallyOn;
		}
		
		public void setInitiallyOn(boolean val) {
			_initiallyOn = val;
		}
		
		private boolean _isRunOnMapInit = false;
		
		public boolean isRunOnMapInit() {
			return _isRunOnMapInit;
		}
		
		public void setRunOnMapInit(boolean val) {
			_isRunOnMapInit = val;
		}
		
		private TrigCat _trigCat;
		
		public TrigCat getTrigCat() {
			return _trigCat;
		}
		
		public void setTrigCat(@Nonnull TrigCat val) {
			_trigCat = val;
		}
		
		public Trig(@Nonnull WTG.Trig wtgTrig) {
			setName(wtgTrig.getName());
			setComment(wtgTrig.getDescription());
			setEnabled(wtgTrig.isEnabled());
			setInitiallyOn(wtgTrig.isInitiallyOn());
			setRunOnMapInit(wtgTrig.isRunOnMapInit());
			setTrigCat(getTrigCats().get(wtgTrig.getCatIndex()));
		}
		
		public Trig() {
		}
	}
	
	public class TextTrig extends Trig {
		private String _text;
		
		public String getText() {
			return _text;
		}
		
		public void setText(String val) {
			_text = val;
		}
		
		public TextTrig(@Nonnull WTG.Trig wtgTrig, WCT.Trig wctTrig) {
			setText(wctTrig.getText());
		}
		
		public TextTrig() {
			super();
		}
	}
	
	public class GUITrig extends Trig {
		public abstract class ECA {
			private String _funcName;
			
			public String getFuncName() {
				return _funcName;
			}
			
			private void setFuncName(String val) {
				_funcName = val;
			}
			
			private boolean _isEnabled = true;
			
			public boolean isEnabled() {
				return _isEnabled;
			}
			
			public void setEnabled(boolean val) {
				_isEnabled = val;
			}

            public class Param {
				
			}
			
			public ECA(String funcName) {
				setFuncName(funcName);
			}
		}
		
		public class Event extends ECA {

			public Event(String funcName) {
				super(funcName);
			}
		}

		public class Condition extends ECA {

			public Condition(String funcName) {
				super(funcName);
			}
		}

		public class Action extends ECA {

			public Action(String funcName) {
				super(funcName);
			}
		}

		public GUITrig(@Nonnull WTG.Trig wtgTrig) {
			super(wtgTrig);
		}
		
		public GUITrig() {
			super();
		}
	}
	
	private final List<Trig> _trigs = new ArrayList<>();
	
	private List<Trig> getTrigs() {
		return new ArrayList<>(_trigs);
	}
	
	private void addTrig(@Nonnull Trig val) {
		_trigs.add(val);
	}
	
	public Trigs(@Nonnull WCT wct, @Nonnull WTG wtg) {
		_wct = wct;
		_wtg = wtg;
	}
}
