/*
 * Copyright © Magnus Ihse Bursie 2025.
 * This file is released under LGPLv3. See LICENSE for full license details.
 */
package se.icus.mag.elytraindicators.render;

import net.minecraft.util.Identifier;

public enum IndicatorSize {
    COMPACT("compact-indicator-frame", 29, 5, 1),
    MEDIUM("medium-indicator-frame", 71, 13, 2),
    WIDE("wide-indicator-frame", 102, 20, 3);

    public static final int TEXTURE_HEIGHT = 22;

    private final Identifier identifier;
    private final int width;
    private final int gaugeOffset;
    private final int gaugeWidth;

    IndicatorSize(String textureName, int width, int gaugeOffset, int gaugeWidth) {
        this.identifier = Identifier.of("elytraindicators", "textures/gui/" + textureName + ".png");
        this.width = width;
        this.gaugeOffset = gaugeOffset;
        this.gaugeWidth = gaugeWidth;
    }

    public Identifier getIdentifier() {
        return identifier;
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
