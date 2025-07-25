package se.icus.mag.elytraindicators;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

public class ElytraIndicatorsRenderer {
    private static final Identifier HOTBAR_OFFHAND_LEFT_TEXTURE = Identifier.ofVanilla("hud/hotbar_offhand_left");
    private static final Identifier HOTBAR_OFFHAND_RIGHT_TEXTURE = Identifier.ofVanilla("hud/hotbar_offhand_right");

    public void render(DrawContext context, MinecraftClient mc) {
        if (!(mc.cameraEntity instanceof PlayerEntity playerEntity)) return;
        if (!mc.player.isGliding()) return;

        boolean rightHandSide = playerEntity.getMainArm().getOpposite() == Arm.LEFT;

        renderBackground(context, rightHandSide);
        renderGauges(context, mc, rightHandSide);
    }

    private void renderBackground(DrawContext context, boolean rightHandSide) {
        Identifier texture = rightHandSide ? HOTBAR_OFFHAND_LEFT_TEXTURE : HOTBAR_OFFHAND_RIGHT_TEXTURE;
        int middleX = context.getScaledWindowWidth() / 2;
        int xOffset = rightHandSide ? (91 + 7) : (-91 - 36);

        context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, texture, middleX + xOffset, context.getScaledWindowHeight() - 23, 29, 24);
    }

    private void renderGauges(DrawContext context, MinecraftClient mc, boolean rightHandSide) {
        int middleX = context.getScaledWindowWidth() / 2;
        int xOffset = rightHandSide ? (91 + 10) : (-91 - 26);
        int backgroundX = middleX + xOffset;
        int backgroundY = context.getScaledWindowHeight() - 19;

        for (int slot = 0; slot < Gauge.getGaugeCount(); slot++) {
            drawGauge(context, mc, slot, backgroundX, backgroundY);
        }
    }

    private void drawGauge(DrawContext context, MinecraftClient mc, int slot, int backgroundX, int backgroundY) {
        Gauge gauge = Gauge.getGauge(slot);
        int value = gauge.getValue(mc);

        int x = backgroundX + 1 + slot * 3;
        int y = backgroundY + 1;

        // Draw the face (background) of the gauge
        drawQuad(context, x, y, 2, 15, Colors.BLACK, 0xFF);

        int partStart = 0;
        Gauge.GaugeFacePart[] faceParts = gauge.getFaceParts();
        for (var facePart : faceParts) {
            drawQuad(context, x, y+partStart, 1, facePart.steps(), facePart.color(), 0xFF);
            partStart += facePart.steps();
        }

        // Draw the marker
        drawQuad(context, x, y + (12 - value), 1, 2, Colors.BLACK, 0x8A);
    }

    private static void drawQuad(DrawContext context, int x, int y, int width, int height, int color, int alpha) {
        context.fill(x, y, x + width, y + height, ColorHelper.withAlpha(alpha, color));
    }
}