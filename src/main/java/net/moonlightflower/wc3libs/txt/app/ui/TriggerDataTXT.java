package net.moonlightflower.wc3libs.txt.app.ui;

import java.util.ArrayList;
import java.util.List;

import net.moonlightflower.wc3libs.txt.TXT;

import javax.annotation.Nonnull;

public class TriggerDataTXT extends TXT {
	public static class TrigCat {
		
	}
	
	private final List<TrigCat> _trigCats = new ArrayList<>();
	
	private void addTrigCat(@Nonnull TrigCat val) {
		_trigCats.add(val);
	}
	
	public static class TrigType {
		
	}
	
	private final List<TrigType> _trigTypes = new ArrayList<>();
	
	private void addTrigType(@Nonnull TrigType val) {
		_trigTypes.add(val);
	}
	
	public static class TrigParam {
		
	}
	
	private final List<TrigParam> _trigParams = new ArrayList<>();
	
	private void addTrigParam(@Nonnull TrigParam val) {
		_trigParams.add(val);
	}
	
	public static class TrigEvent {
		
	}
	
	private final List<TrigEvent> _trigEvents = new ArrayList<>();
	
	private void addTrigEvent(@Nonnull TrigEvent val) {
		_trigEvents.add(val);
	}
	
	public static class TrigCond {
		
	}
	
	private final List<TrigCond> _trigConds = new ArrayList<>();
	
	private void addTrigCond(@Nonnull TrigCond val) {
		_trigConds.add(val);
	}
	
	public static class TrigAction {
		
	}
	
	private final List<TrigAction> _trigActions = new ArrayList<>();
	
	private void addTrigAction(@Nonnull TrigAction val) {
		_trigActions.add(val);
	}
	
	public static class TrigCall {
		
	}
	
	private final List<TrigCall> _trigCalls = new ArrayList<>();
	
	private void addTrigCall(@Nonnull TrigCall val) {
		_trigCalls.add(val);
	}
	
	public static class DefaultTrigCat {
		
	}
	
	private final List<DefaultTrigCat> _defaultTrigCats = new ArrayList<>();
	
	private void addDefaultTrigCat(@Nonnull DefaultTrigCat val) {
		_defaultTrigCats.add(val);
	}
	
	public static class DefaultTrig {
		
	}
	
	private final List<DefaultTrig> _defaultTrigs = new ArrayList<>();
	
	private void addDefaultTrig(@Nonnull DefaultTrig val) {
		_defaultTrigs.add(val);
	}
}