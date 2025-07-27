package se.icus.mag.elytraindicators.gauges;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class DurabilityGauge extends Gauge {
    private static final GaugeFacePart[] GAUGE_FACE_PARTS = {
            new GaugeFacePart(7, OK),
            new GaugeFacePart(3, CAUTION),
            new GaugeFacePart(4, ALERT)
    };

    @Override
    public double getRealValue(MinecraftClient mc) {
        ItemStack item = mc.player.getEquippedStack(EquipmentSlot.CHEST);
        if (!item.isOf(Items.ELYTRA)) return 0;

        return ((double) (item.getMaxDamage() - item.getDamage()) / item.getMaxDamage());
    }

    @Override
    public double rescale(double realValue) {
        return Math.round((-19 * realValue * realValue + 36 * realValue - 0.4) / 17.0 * 12);
    }

    @Override
    public GaugeFacePart[] getFaceParts() {
        return GAUGE_FACE_PARTS;
    }
}