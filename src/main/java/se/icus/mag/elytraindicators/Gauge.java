package se.icus.mag.elytraindicators;

import net.minecraft.client.MinecraftClient;
import se.icus.mag.elytraindicators.gauges.ClimbGauge;
import se.icus.mag.elytraindicators.gauges.DurabilityGauge;
import se.icus.mag.elytraindicators.gauges.HeightGauge;
import se.icus.mag.elytraindicators.gauges.PitchGauge;
import se.icus.mag.elytraindicators.gauges.SpeedGauge;

public abstract class Gauge {
    public static final Gauge[] GAUGES = {
            new PitchGauge(),
            new SpeedGauge(),
            new ClimbGauge(),
            new HeightGauge(),
            new DurabilityGauge()
    };

    public int getValue(MinecraftClient mc) {
        double realValue = getRealValue(mc);

        // Rescale it to 0-12
        int value = (int) rescale(realValue);
        return limit(value);
    }

    protected abstract double getRealValue(MinecraftClient mc);
    protected abstract double rescale(double realValue);

    private int limit(int value) {
        if (value < 0) return 0;
        if (value > 12) return 12;
        return value;
    }

}
