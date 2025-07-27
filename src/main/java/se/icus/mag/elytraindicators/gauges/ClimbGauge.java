package se.icus.mag.elytraindicators.gauges;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;

public class ClimbGauge extends Gauge {
    private static final GaugeFacePart[] GAUGE_FACE_PARTS = {
            new GaugeFacePart(1, ALERT),
            new GaugeFacePart(3, CAUTION),
            new GaugeFacePart(5, OK),
            new GaugeFacePart(2, CAUTION),
            new GaugeFacePart(3, ALERT)
    };

    @Override
    public double getRealValue(MinecraftClient mc) {
        Entity player = mc.player;
        return (player.lastRenderY - player.getY()) * 20.0;
    }

    @Override
    public double rescale(double realValue) {
        double offsettedValue = realValue - 1.5;
        double sqrtValue = Math.signum(offsettedValue) * Math.sqrt(Math.abs(offsettedValue));
        if (offsettedValue <= 0) {
            return Math.floor(9.0 - sqrtValue / 5.55 * 3);
        } else {
            return Math.floor(9.0 - offsettedValue * 1.6);
        }
    }

    @Override
    public GaugeFacePart[] getFaceParts() {
        return GAUGE_FACE_PARTS;
    }
}
