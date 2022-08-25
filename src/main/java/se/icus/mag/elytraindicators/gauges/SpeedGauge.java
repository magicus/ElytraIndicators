package se.icus.mag.elytraindicators.gauges;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import se.icus.mag.elytraindicators.Gauge;

public class SpeedGauge extends Gauge {
    @Override
    public double getRealValue(MinecraftClient mc) {
        Entity player = mc.player;
        double dx = player.getX() - player.lastRenderX;
        double dy = player.getY() - player.lastRenderY;
        double dz = player.getZ() - player.lastRenderZ;
        return Math.sqrt(dx * dx + dy * dy + dz * dz) * 20.0;
    }

    @Override
    public double rescale(double realValue) {
        return Math.round(realValue / 65.0 * 12);
    }
}
