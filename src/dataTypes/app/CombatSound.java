package dataTypes.app;

import java.util.HashMap;
import java.util.Map;

public class CombatSound extends Wc3String {
	public final static CombatSound AXE_MEDIUM_CHOP = new CombatSound("AxeMediumChop");
	public final static CombatSound METAL_HEAVY_BASH = new CombatSound("MetalHeavyBash");
	public final static CombatSound METAL_HEAVY_CHOP = new CombatSound("MetalHeavyChop");
	public final static CombatSound METAL_HEAVY_SLICE = new CombatSound("MetalHeavySlice");
	public final static CombatSound METAL_LIGHT_CHOP = new CombatSound("MetalLightChop");
	public final static CombatSound METAL_LIGHT_SLICE = new CombatSound("MetalLightSlice");
	public final static CombatSound METAL_MEDIUM_BASH = new CombatSound("MetalMediumBash");
	public final static CombatSound METAL_MEDIUM_CHOP = new CombatSound("MetalMediumChop");
	public final static CombatSound METAL_MEDIUM_SLICE = new CombatSound("MetalMediumSlice");
	public final static CombatSound ROCK_HEAVY_BASH = new CombatSound("RockHeavyBash");
	public final static CombatSound WOOD_HEAVY_BASH = new CombatSound("WoodHeavyBash");
	public final static CombatSound WOOD_LIGHT_BASH = new CombatSound("WoodLightBash");
	public final static CombatSound WOOD_MEDIUM_BASH = new CombatSound("WoodMediumBash");
	
	private Map<String, CombatSound> _map = new HashMap<>();
	
	public CombatSound(String name) {
		super(name);
	}

	@Override
	public CombatSound decode(Object val) {
		return _map.get(val.toString());
	}
}
