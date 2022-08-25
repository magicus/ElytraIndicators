package se.icus.mag.elytraindicators.gauges;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import se.icus.mag.elytraindicators.Gauge;

public class DurabilityGauge extends Gauge {
    @Override
    public int getValue(MinecraftClient mc) {
        ItemStack item = mc.player.getInventory().getArmorStack(2);
        if (!item.isOf(Items.ELYTRA)) return 0;
        double realDurability = ((double) item.getDamage() / item.getMaxDamage());

        // Rescale it to 0-12
        int durability = (int) Math.floor(12.3 - realDurability * 12);
        return limit(durability);
    }
}
