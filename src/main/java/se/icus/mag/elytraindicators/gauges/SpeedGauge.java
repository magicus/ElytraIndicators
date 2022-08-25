package se.icus.mag.elytraindicators.gauges;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import se.icus.mag.elytraindicators.Gauge;

public class SpeedGauge extends Gauge {
    @Override
    public int getValue(MinecraftClient mc) {
        Entity entity = mc.player;
        double dx = entity.getX() - entity.lastRenderX;
        double dy = entity.getY() - entity.lastRenderY;
        double dz = entity.getZ() - entity.lastRenderZ;
        double realSpeed = Math.sqrt(dx * dx + dy * dy + dz * dz) * 20.0;

        // Rescale it to 0-12
        int speed = (int) Math.round(realSpeed / 65.0 * 12);
        return limit(speed);
    }
}
