package se.icus.mag.elytraindicators;

import net.minecraft.client.MinecraftClient;
import se.icus.mag.elytraindicators.gauges.ClimbGauge;
import se.icus.mag.elytraindicators.gauges.DurabilityGauge;
import se.icus.mag.elytraindicators.gauges.HeightGauge;
import se.icus.mag.elytraindicators.gauges.PitchGauge;
import se.icus.mag.elytraindicators.gauges.SpeedGauge;

public abstract class Gauge {
    private final static Gauge[] GAUGES = {
            new PitchGauge(),
            new SpeedGauge(),
            new ClimbGauge(),
            new HeightGauge(),
            new DurabilityGauge()
    };
    protected final static int ALERT = 0xFF0000;
    protected final static int CAUTION = 0xFFFF00;
    protected final static int OK = 0x00FF00;

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
        return limit(value);
    }

    public abstract GaugeFacePart[] getFaceParts();

    protected abstract double getRealValue(MinecraftClient mc);
    protected abstract double rescale(double realValue);

    private int limit(int value) {
        if (value < 0) return 0;
        if (value > 12) return 12;
        return value;
    }

    public record GaugeFacePart(int steps, int color) {}
}
