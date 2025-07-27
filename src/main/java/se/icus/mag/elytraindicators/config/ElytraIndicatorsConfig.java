/*
 * Copyright © Magnus Ihse Bursie 2025.
 * This file is released under LGPLv3. See LICENSE for full license details.
 */
package se.icus.mag.elytraindicators.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import se.icus.mag.elytraindicators.ElytraIndicatorsMod;
import se.icus.mag.elytraindicators.render.IndicatorSize;

@Config(name = ElytraIndicatorsMod.MOD_ID)
public class ElytraIndicatorsConfig implements ConfigData {
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    IndicatorSize indicatorSize = IndicatorSize.WIDE;

    public IndicatorSize getIndicatorSize() {
        return indicatorSize;
    }
}
