package se.icus.mag.elytraindicators.gauges;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import se.icus.mag.elytraindicators.Gauge;

public class HeightGauge extends Gauge {
    @Override
    public double getRealValue(MinecraftClient mc) {
        return mc.player.getY();
    }

    @Override
    public double rescale(double realValue) {
        return Math.round((realValue - 64.0) / 256.0 * 12);
    }
}