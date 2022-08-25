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

    @Override
    public int getFaceColor() {
        return 0x00FF00;
    }

    public int getHighLimit() {
        // ~ 320 m
        return 12;
    }

    public int getLowLimit() {
        // ~ 100 m
        return 2;
    }
}
