package se.icus.mag.elytraindicators;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

/**
 * Return values between 0 and 12, inclusive
 */
public class ElytraIndicatorsValues {
    int getPitch(MinecraftClient mc) {
        double realPitch = mc.player.getPitch();
        double sqrtPitch = Math.signum(realPitch) * Math.sqrt(Math.abs(realPitch));

        // Rescale it to 0-12
        int pitch = (int) Math.round(6.5 -  sqrtPitch/ 7.0 * 6);
        return limit(pitch);
    }

    int getSpeed(MinecraftClient mc) {
        Entity entity = mc.player;
        double dx = entity.getX() - entity.lastRenderX;
        double dy = entity.getY() - entity.lastRenderY;
        double dz = entity.getZ() - entity.lastRenderZ;
        double realSpeed = Math.sqrt(dx * dx + dy * dy + dz * dz) * 20.0;

        // Rescale it to 0-12
        int speed = (int) Math.round(realSpeed / 65.0 * 12);
        return limit(speed);
    }

    int getClimb(MinecraftClient mc) {
        Entity entity = mc.player;
        double realClimb = (entity.lastRenderY - entity.getY()) * 20.0;
        double sqrtClimb = Math.signum(realClimb) * Math.sqrt(Math.abs(realClimb));

        // Rescale it to 0-12
        int climb = (int) Math.round(6.0 -  sqrtClimb/ 5.5 * 6);
        return limit(climb);
    }

    int getHeight(MinecraftClient mc) {
        Entity entity = mc.player;
        double realHeight = entity.getY();

        // Rescale it to 0-12
        int height = (int) Math.round((realHeight - 64.0) / 256.0 * 12);
        return limit(height);
    }

    int getDurability(MinecraftClient mc) {
        ItemStack item = mc.player.getInventory().getArmorStack(2);
        if (!item.isOf(Items.ELYTRA)) return 0;
        double realDurability = ((double) item.getDamage() / item.getMaxDamage());

        // Rescale it to 0-12
        int durability = (int) Math.floor(12.3 - realDurability * 12);
        return limit(durability);
    }

    private static int limit(int value) {
        if (value < 0) return 0;
        if (value > 12) return 12;
        return (int) value;
    }


}
