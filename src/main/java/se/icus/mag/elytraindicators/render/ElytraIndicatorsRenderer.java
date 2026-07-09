/*
 * Copyright © Magnus Ihse Bursie 2025-2026.
 * This file is released under LGPLv3. See LICENSE for full license details.
 */
package se.icus.mag.elytraindicators.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.util.CommonColors;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import se.icus.mag.elytraindicators.ElytraIndicatorsMod;
import se.icus.mag.elytraindicators.gauges.Gauge;

public class ElytraIndicatorsRenderer {
    private IndicatorSize getIndicatorSize() {
        return ElytraIndicatorsMod.getConfig().getIndicatorSize();
    }

    public void render(GuiGraphicsExtractor graphics, Minecraft mc) {
        if (!(mc.getCameraEntity() instanceof Player playerEntity)) return;
        if (mc.options.hideGui) return;
        if (!mc.player.isFallFlying()) return;

        boolean rightHandSide = playerEntity.getMainArm().getOpposite() == HumanoidArm.LEFT;
        int xOffset = rightHandSide ? 98 : -102 - getIndicatorSize().getWidth();
        int x = graphics.guiWidth() / 2 + xOffset;
        int y = graphics.guiHeight() - IndicatorSize.TEXTURE_HEIGHT;

        renderBackground(graphics, x, y);
        renderGauges(graphics, mc, x, y);
    }

    private void renderBackground(GuiGraphicsExtractor graphics, int x, int y) {
        Identifier texture = getIndicatorSize().getIdentifier();
        int width = getIndicatorSize().getWidth();
        int height = IndicatorSize.TEXTURE_HEIGHT;

        graphics.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, 0, 0, width, height, width, height);
    }

    private void renderGauges(GuiGraphicsExtractor graphics, Minecraft mc, int x, int y) {
        for (int slot = 0; slot < Gauge.getGaugeCount(); slot++) {
            Gauge gauge = Gauge.getGauge(slot);
            renderGauge(graphics, mc, gauge, x + 4 + slot * getIndicatorSize().getGaugeOffset(), y + 4);
        }
    }

    private void renderGauge(GuiGraphicsExtractor graphics, Minecraft mc, Gauge gauge, int x, int y) {
        int value = gauge.getValue(mc);
        int width = getIndicatorSize().getGaugeWidth();

        // Draw the face (background) of the gauge
        drawQuad(graphics, x, y, width + 1, 15, CommonColors.BLACK, 0xFF);

        int partStart = 0;
        Gauge.GaugeFacePart[] faceParts = gauge.getFaceParts();
        for (var facePart : faceParts) {
            drawQuad(graphics, x, y + partStart, width, facePart.steps(), facePart.color(), 0xFF);
            partStart += facePart.steps();
        }

        // Draw the marker frame and actual marker
        int markerY = y + Gauge.MAX_GAUGE_VALUE - value;
        drawQuad(graphics, x - 1, markerY - 1, width + 2, 4, CommonColors.WHITE, 0xFF);
        drawQuad(graphics, x, markerY, width, 2, CommonColors.BLACK, 0xB0);
    }

    private static void drawQuad(
            GuiGraphicsExtractor graphics, int x, int y, int width, int height, int color, int alpha) {
        graphics.fill(x, y, x + width, y + height, ARGB.color(alpha, color));
    }
}
