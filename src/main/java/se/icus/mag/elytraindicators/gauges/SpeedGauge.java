/*
 * Copyright © Magnus Ihse Bursie 2025.
 * This file is released under LGPLv3. See LICENSE for full license details.
 */
package se.icus.mag.elytraindicators.gauges;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;

public final class SpeedGauge extends Gauge {
    private static final GaugeFacePart[] GAUGE_FACE_PARTS = {
        new GaugeFacePart(2, ALERT),
        new GaugeFacePart(3, CAUTION),
        new GaugeFacePart(7, OK),
        new GaugeFacePart(1, CAUTION),
        new GaugeFacePart(1, ALERT)
    };

    @Override
    public double getRealValue(MinecraftClient mc) {
        Entity player = mc.player;
        double dx = player.getX() - player.lastRenderX;
        double dy = player.getY() - player.lastRenderY;
        double dz = player.getZ() - player.lastRenderZ;
        return Math.sqrt(dx * dx + dy * dy + dz * dz) * 20.0;
    }

    @Override
    public double rescale(double realValue) {
        return Math.round(realValue / 48.0 * 12);
    }

    @Override
    public GaugeFacePart[] getFaceParts() {
        return GAUGE_FACE_PARTS;
    }
}
