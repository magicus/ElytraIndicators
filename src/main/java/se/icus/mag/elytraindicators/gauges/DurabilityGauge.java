package se.icus.mag.elytraindicators.gauges;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import se.icus.mag.elytraindicators.Gauge;

public class DurabilityGauge extends Gauge {
    @Override
    public double getRealValue(MinecraftClient mc) {
        ItemStack item = mc.player.getInventory().getArmorStack(2);
        if (!item.isOf(Items.ELYTRA)) return 0;

        return ((double) item.getDamage() / item.getMaxDamage());
    }

    @Override
    public double rescale(double realValue) {
        return Math.floor(12.3 - realValue * 12);
    }

    @Override
    public int getFaceColor() {
        return 0xFFFFFF;
    }
}
