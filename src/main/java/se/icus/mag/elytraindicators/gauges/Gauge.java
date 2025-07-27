/*
 * Copyright © Magnus Ihse Bursie 2025.
 * This file is released under LGPLv3. See LICENSE for full license details.
 */
package se.icus.mag.elytraindicators.gauges;

import net.minecraft.client.MinecraftClient;

public abstract sealed class Gauge permits PitchGauge, SpeedGauge, ClimbGauge, HeightGauge, WearGauge {
    private static final Gauge[] GAUGES = {
        new PitchGauge(), new SpeedGauge(), new ClimbGauge(), new HeightGauge(), new WearGauge()
    };
    protected static final int ALERT = 0xFF0000;
    protected static final int CAUTION = 0xFFFF00;
    protected static final int OK = 0x00FF00;
    public static final int MAX_GAUGE_VALUE = 12;

    public static int getGaugeCount() {
        return GAUGES.length;
    }

    public static Gauge getGauge(int slot) {
        return GAUGES[slot];
    }

    public int getValue(MinecraftClient mc) {
        double realValue = getRealValue(mc);

        // Rescale it to 0-12
        int value = (int) rescale(realValue);
        return clamp(value);
    }

    public abstract GaugeFacePart[] getFaceParts();

    protected abstract double getRealValue(MinecraftClient mc);

    protected abstract double rescale(double realValue);

    private int clamp(int value) {
        if (value < 0) return 0;
        if (value > MAX_GAUGE_VALUE) return MAX_GAUGE_VALUE;
        return value;
    }

    public record GaugeFacePart(int steps, int color) {}
}
