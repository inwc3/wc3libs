package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class CombatSound extends Wc3String {
	private final static Map<String, CombatSound> _map = new LinkedHashMap<>();

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

	@Override
	public boolean equals(Object other) {
		if (other instanceof CombatTarget)
			return equals((CombatTarget) other);

		return super.equals(other);
	}

	public boolean equals(CombatTarget other) {
		return getVal().equals(other.getVal());
	}

	public CombatSound(String name) {
		super(name);

		_map.put(name, this);
	}

	@Nullable
	public static CombatSound valueOf(@Nonnull String name) {
		return _map.get(name);
	}

	@Override
	public CombatSound decode(Object val) {
		return _map.get(val.toString());
	}
}
