package se.icus.mag.elytraindicators.gauges;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import se.icus.mag.elytraindicators.Gauge;

public class ClimbGauge extends Gauge {
    @Override
    public double getRealValue(MinecraftClient mc) {
        Entity player = mc.player;
        return (player.lastRenderY - player.getY()) * 20.0;
    }

    @Override
    public double rescale(double realValue) {
        double sqrtValue = Math.signum(realValue) * Math.sqrt(Math.abs(realValue));
        return Math.round(6.0 -  sqrtValue/ 5.5 * 6);
    }
}
