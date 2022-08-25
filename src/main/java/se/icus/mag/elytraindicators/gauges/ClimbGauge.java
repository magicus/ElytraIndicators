package se.icus.mag.elytraindicators.gauges;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import se.icus.mag.elytraindicators.Gauge;

public class ClimbGauge extends Gauge {
    @Override
    public int getValue(MinecraftClient mc) {
        Entity entity = mc.player;
        double realClimb = (entity.lastRenderY - entity.getY()) * 20.0;
        double sqrtClimb = Math.signum(realClimb) * Math.sqrt(Math.abs(realClimb));

        // Rescale it to 0-12
        int climb = (int) Math.round(6.0 -  sqrtClimb/ 5.5 * 6);
        return limit(climb);
    }
}
