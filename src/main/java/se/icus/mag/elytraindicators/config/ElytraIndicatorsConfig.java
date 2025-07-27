package se.icus.mag.elytraindicators.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import se.icus.mag.elytraindicators.render.IndicatorSize;

@Config(name = "elytraindicators")
public class ElytraIndicatorsConfig implements ConfigData {
	@ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
	IndicatorSize indicatorSize = IndicatorSize.MEDIUM;

	public IndicatorSize getIndicatorSize() {
		return indicatorSize;
	}
}
