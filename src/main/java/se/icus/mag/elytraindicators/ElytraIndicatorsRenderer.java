package se.icus.mag.elytraindicators;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;

public class ElytraIndicatorsRenderer {
    private static final Identifier HOTBAR_OFFHAND_LEFT_TEXTURE = new Identifier("hud/hotbar_offhand_left");
    private static final Identifier HOTBAR_OFFHAND_RIGHT_TEXTURE = new Identifier("hud/hotbar_offhand_right");

    public void render(DrawContext context, MinecraftClient mc) {
        if (!(mc.cameraEntity instanceof PlayerEntity playerEntity)) return;
        if (!mc.player.isFallFlying()) return;

        boolean rightHandSide = playerEntity.getMainArm().getOpposite() == Arm.LEFT;

        renderBackground(context, rightHandSide);
        renderGauges(context, mc, rightHandSide);
    }

    private void renderBackground(DrawContext context, boolean rightHandSide) {
        RenderSystem.enableBlend();

        Identifier texture = rightHandSide ? HOTBAR_OFFHAND_LEFT_TEXTURE : HOTBAR_OFFHAND_RIGHT_TEXTURE;
        int middleX = context.getScaledWindowWidth() / 2;
        int xOffset = rightHandSide ? (91 + 7) : (-91 - 36);

        context.drawGuiTexture(texture, middleX + xOffset, context.getScaledWindowHeight() - 23, 29, 24);

        RenderSystem.disableBlend();
    }

    private void renderGauges(DrawContext context, MinecraftClient mc, boolean rightHandSide) {
        RenderSystem.disableDepthTest();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        int middleX = context.getScaledWindowWidth() / 2;
        int xOffset = rightHandSide ? (91 + 10) : (-91 - 26);
        int backgroundX = middleX + xOffset;
        int backgroundY = context.getScaledWindowHeight() - 19;

        for (int slot = 0; slot < Gauge.getGaugeCount(); slot++) {
            drawGauge(mc, bufferBuilder, slot, backgroundX, backgroundY);
        }

        RenderSystem.enableDepthTest();
    }

    private void drawGauge(MinecraftClient mc, BufferBuilder bufferBuilder, int slot, int backgroundX, int backgroundY) {
        Gauge gauge = Gauge.getGauge(slot);
        int value = gauge.getValue(mc);

        int x = backgroundX + 1 + slot * 3;
        int y = backgroundY + 1;

        // Draw the face (background) of the gauge
        drawQuad(bufferBuilder, x, y, 2, 15, 0, 255);

        int partStart = 0;
        Gauge.GaugeFacePart[] faceParts = gauge.getFaceParts();
        for (var facePart : faceParts) {
            drawQuad(bufferBuilder, x, y+partStart, 1, facePart.steps(), facePart.color(), 255);
            partStart += facePart.steps();
        }

        // Draw the marker
        drawQuad(bufferBuilder, x, y + (12 - value), 1, 2, 0, 138);
    }

    private static void drawQuad(BufferBuilder buffer, int x, int y, int width, int height, int color, int alpha) {
        int red = color >> 16 & 0xFF;
        int green = color >> 8 & 0xFF;
        int blue = color & 0xFF;

        RenderSystem.setShader(GameRenderer::getPositionColorTexProgram);
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        buffer.vertex(x + 0, y + 0, 0.0).color(red, green, blue, alpha).next();
        buffer.vertex(x + 0, y + height, 0.0).color(red, green, blue, alpha).next();
        buffer.vertex(x + width, y + height, 0.0).color(red, green, blue, alpha).next();
        buffer.vertex(x + width, y + 0, 0.0).color(red, green, blue, alpha).next();
        BufferRenderer.drawWithGlobalProgram(buffer.end());
    }
}