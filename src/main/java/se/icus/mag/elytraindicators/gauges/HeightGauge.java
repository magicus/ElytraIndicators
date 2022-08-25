package se.icus.mag.elytraindicators.gauges;

import net.minecraft.client.MinecraftClient;
import se.icus.mag.elytraindicators.Gauge;

public class HeightGauge extends Gauge {
    private static final GaugeFacePart[] GAUGE_FACE_PARTS = {
            new GaugeFacePart(9, OK),
            new GaugeFacePart(2, CAUTION),
            new GaugeFacePart(3, ALERT)
    };

    @Override
    public double getRealValue(MinecraftClient mc) {
        return mc.player.getY();
    }

    @Override
    public double rescale(double realValue) {
        return Math.round((realValue - 64.0) / 300.0 * 12);
    }

    @Override
    public GaugeFacePart[] getFaceParts() {
        return GAUGE_FACE_PARTS;
    }
}
