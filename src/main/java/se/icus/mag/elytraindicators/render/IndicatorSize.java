/*
 * Copyright © Magnus Ihse Bursie 2025-2026.
 * This file is released under LGPLv3. See LICENSE for full license details.
 */
package se.icus.mag.elytraindicators.render;

import net.minecraft.resources.ResourceLocation;
import se.icus.mag.elytraindicators.ElytraIndicatorsMod;

public enum IndicatorSize {
    COMPACT("compact-indicator-frame", 29, 5, 1),
    MEDIUM("medium-indicator-frame", 71, 13, 2),
    WIDE("wide-indicator-frame", 102, 20, 3);

    public static final int TEXTURE_HEIGHT = 22;

    private final ResourceLocation resourceLocation;
    private final int width;
    private final int gaugeOffset;
    private final int gaugeWidth;

    IndicatorSize(String textureName, int width, int gaugeOffset, int gaugeWidth) {
        this.resourceLocation = ResourceLocation.fromNamespaceAndPath(
                ElytraIndicatorsMod.MOD_ID, "textures/gui/" + textureName + ".png");
        this.width = width;
        this.gaugeOffset = gaugeOffset;
        this.gaugeWidth = gaugeWidth;
    }

    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    public int getWidth() {
        return width;
    }

    public int getGaugeOffset() {
        return gaugeOffset;
    }

    public int getGaugeWidth() {
        return gaugeWidth;
    }
}
