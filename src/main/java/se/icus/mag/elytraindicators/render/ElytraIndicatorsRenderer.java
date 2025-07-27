/*
 * Copyright © Magnus Ihse Bursie 2025.
 * This file is released under LGPLv3. See LICENSE for full license details.
 */
package se.icus.mag.elytraindicators.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import se.icus.mag.elytraindicators.ElytraIndicatorsMod;
import se.icus.mag.elytraindicators.gauges.Gauge;

public class ElytraIndicatorsRenderer {
    private IndicatorSize getIndicatorSize() {
        return ElytraIndicatorsMod.getConfig().getIndicatorSize();
    }

    public void render(DrawContext context, MinecraftClient mc) {
        if (!(mc.cameraEntity instanceof PlayerEntity playerEntity)) return;
        if (mc.options.hudHidden) return;
        if (!mc.player.isGliding()) return;

        boolean rightHandSide = playerEntity.getMainArm().getOpposite() == Arm.LEFT;
        int xOffset = rightHandSide ? 98 : -102 - getIndicatorSize().getWidth();
        int x = context.getScaledWindowWidth() / 2 + xOffset;
        int y = context.getScaledWindowHeight() - IndicatorSize.TEXTURE_HEIGHT;

        renderBackground(context, x, y);
        renderGauges(context, mc, x, y);
    }

    private void renderBackground(DrawContext context, int x, int y) {
        Identifier texture = getIndicatorSize().getIdentifier();
        int width = getIndicatorSize().getWidth();
        int height = IndicatorSize.TEXTURE_HEIGHT;

        context.drawTexture(RenderPipelines.GUI_TEXTURED, texture, x, y, 0, 0, width, height, width, height);
    }

    private void renderGauges(DrawContext context, MinecraftClient mc, int x, int y) {
        for (int slot = 0; slot < Gauge.getGaugeCount(); slot++) {
            Gauge gauge = Gauge.getGauge(slot);
            renderGauge(context, mc, gauge, x + 4 + slot * getIndicatorSize().getGaugeOffset(), y + 4);
        }
    }

    private void renderGauge(DrawContext context, MinecraftClient mc, Gauge gauge, int x, int y) {
        int value = gauge.getValue(mc);
        int width = getIndicatorSize().getGaugeWidth();

        // Draw the face (background) of the gauge
        drawQuad(context, x, y, width + 1, 15, Colors.BLACK, 0xFF);

        int partStart = 0;
        Gauge.GaugeFacePart[] faceParts = gauge.getFaceParts();
        for (var facePart : faceParts) {
            drawQuad(context, x, y + partStart, width, facePart.steps(), facePart.color(), 0xFF);
            partStart += facePart.steps();
        }

        // Draw the marker frame and actual marker
        int markerY = y + Gauge.MAX_GAUGE_VALUE - value;
        drawQuad(context, x - 1, markerY - 1, width + 2, 4, Colors.WHITE, 0xFF);
        drawQuad(context, x, markerY, width, 2, Colors.BLACK, 0xB0);
    }

    private static void drawQuad(DrawContext context, int x, int y, int width, int height, int color, int alpha) {
        context.fill(x, y, x + width, y + height, ColorHelper.withAlpha(alpha, color));
    }
}
