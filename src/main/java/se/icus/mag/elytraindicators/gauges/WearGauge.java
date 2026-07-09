/*
 * Copyright © Magnus Ihse Bursie 2025-2026.
 * This file is released under LGPLv3. See LICENSE for full license details.
 */
package se.icus.mag.elytraindicators.gauges;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class WearGauge extends Gauge {
    private static final GaugeFacePart[] GAUGE_FACE_PARTS = {
        new GaugeFacePart(7, OK), new GaugeFacePart(3, CAUTION), new GaugeFacePart(4, ALERT)
    };

    @Override
    public double getRealValue(Minecraft mc) {
        ItemStack item = mc.player.getItemBySlot(EquipmentSlot.CHEST);
        if (!item.is(Items.ELYTRA)) return 0;

        return ((double) (item.getMaxDamage() - item.getDamageValue()) / item.getMaxDamage());
    }

    @Override
    public double rescale(double realValue) {
        return Math.round((-19 * realValue * realValue + 36 * realValue - 0.4) / 17.0 * Gauge.MAX_GAUGE_VALUE);
    }

    @Override
    public GaugeFacePart[] getFaceParts() {
        return GAUGE_FACE_PARTS;
    }
}
