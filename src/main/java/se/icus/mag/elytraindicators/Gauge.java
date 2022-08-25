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

    public abstract int getValue(MinecraftClient mc);

    protected int limit(int value) {
        if (value < 0) return 0;
        if (value > 12) return 12;
        return (int) value;
    }

}
