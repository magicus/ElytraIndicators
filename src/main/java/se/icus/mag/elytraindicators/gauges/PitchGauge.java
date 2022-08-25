package se.icus.mag.elytraindicators.gauges;

import net.minecraft.client.MinecraftClient;
import se.icus.mag.elytraindicators.Gauge;

public class PitchGauge extends Gauge {
    private static final GaugeFacePart[] GAUGE_FACE_PARTS = {
            new GaugeFacePart(2, ALERT),
            new GaugeFacePart(2, CAUTION),
            new GaugeFacePart(6, OK),
            new GaugeFacePart(1, CAUTION),
            new GaugeFacePart(3, ALERT)
    };

    @Override
    public double getRealValue(MinecraftClient mc) {
        return mc.player.getPitch();
    }

    @Override
    public double rescale(double realValue) {
        double sqrtValue = Math.signum(realValue) * Math.sqrt(Math.abs(realValue));
        return Math.round(6.2 - sqrtValue / 9.0 * 6.0);
    }

    @Override
    public GaugeFacePart[] getFaceParts() {
        return GAUGE_FACE_PARTS;
    }
}
