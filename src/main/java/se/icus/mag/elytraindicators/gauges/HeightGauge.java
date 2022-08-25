package se.icus.mag.elytraindicators.gauges;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import se.icus.mag.elytraindicators.Gauge;

public class HeightGauge extends Gauge {
    @Override
    public int getValue(MinecraftClient mc) {
        Entity entity = mc.player;
        double realHeight = entity.getY();

        // Rescale it to 0-12
        int height = (int) Math.round((realHeight - 64.0) / 256.0 * 12);
        return limit(height);
    }
}
