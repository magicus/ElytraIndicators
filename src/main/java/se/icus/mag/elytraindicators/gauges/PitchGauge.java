package se.icus.mag.elytraindicators.gauges;

import net.minecraft.client.MinecraftClient;
import se.icus.mag.elytraindicators.Gauge;

public class PitchGauge extends Gauge {
    @Override
    public int getValue(MinecraftClient mc) {
        double realPitch = mc.player.getPitch();
        double sqrtPitch = Math.signum(realPitch) * Math.sqrt(Math.abs(realPitch));

        // Rescale it to 0-12
        int pitch = (int) Math.round(6.5 -  sqrtPitch/ 7.0 * 6);
        return limit(pitch);
    }
}
