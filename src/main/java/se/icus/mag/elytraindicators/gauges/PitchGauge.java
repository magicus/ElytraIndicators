package se.icus.mag.elytraindicators.gauges;

import net.minecraft.client.MinecraftClient;
import se.icus.mag.elytraindicators.Gauge;

public class PitchGauge extends Gauge {
    @Override
    public double getRealValue(MinecraftClient mc) {
        return mc.player.getPitch();
    }

    @Override
    public double rescale(double realValue) {
        double sqrtValue = Math.signum(realValue) * Math.sqrt(Math.abs(realValue));
        return Math.round(6.5 -  sqrtValue/ 7.0 * 6);
    }

    @Override
    public int getFaceColor() {
        return 0xFF0000;
    }
}
