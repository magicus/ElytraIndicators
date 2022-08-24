package se.icus.mag.elytraindicators;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;

public class ElytraIndicatorsRenderer extends DrawableHelper {
    private static final Identifier WIDGETS_TEXTURE = new Identifier("textures/gui/widgets.png");
    private final ElytraIndicatorsValues values = new ElytraIndicatorsValues();

    public void render(MatrixStack matrices, MinecraftClient mc, int scaledWidth, int scaledHeight) {
        if (!(mc.cameraEntity instanceof PlayerEntity playerEntity)) return;

        boolean rightHandSide = playerEntity.getMainArm().getOpposite() == Arm.LEFT;

        matrices.push();
        int oldZOffset = getZOffset();
        setZOffset(-90);

        renderBackground(matrices, scaledWidth, scaledHeight, rightHandSide);
        renderGauges(mc, scaledWidth, scaledHeight, rightHandSide);

        setZOffset(oldZOffset);
        matrices.pop();
    }

    private void renderBackground(MatrixStack matrices, int scaledWidth, int scaledHeight, boolean rightHandSide) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, WIDGETS_TEXTURE);

        int middleX = scaledWidth / 2;
        int xOffset = rightHandSide ? (91 + 7) : (-91 - 29);
        this.drawTexture(matrices, middleX + xOffset, scaledHeight - 23, 24, 22, 29, 24);
    }

    private void renderGauges(MinecraftClient mc, int scaledWidth, int scaledHeight, boolean rightHandSide) {
        RenderSystem.disableDepthTest();
        RenderSystem.disableTexture();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        int middleX = scaledWidth / 2;
        int xOffset = rightHandSide ? (91 + 10) : (-91 - 26);
        int backgroundX = middleX + xOffset;
        int backgroundY = scaledHeight - 16 - 3;

        drawGauge(bufferBuilder, values.getPitch(mc), 0xFF0000, 0, backgroundX, backgroundY);
        drawGauge(bufferBuilder, values.getSpeed(mc), 0xFFAA00, 1, backgroundX, backgroundY);
        drawGauge(bufferBuilder, values.getClimb(mc), 0xDDFF00, 2, backgroundX, backgroundY);
        drawGauge(bufferBuilder, values.getHeight(mc), 0x00FF00, 3, backgroundX, backgroundY);
        drawGauge(bufferBuilder, values.getDurability(mc), 0xFFFFFF, 4, backgroundX, backgroundY);

        RenderSystem.enableTexture();
        RenderSystem.enableDepthTest();
    }

    private void drawGauge(BufferBuilder bufferBuilder, int value, int color, int slot, int backgroundX, int backgroundY) {
        int xOffset = -12 + slot*3;
        // Draw background color for gauge
        drawQuad(bufferBuilder, backgroundX + 13 + xOffset, backgroundY + 1, 2, 15, 0, 0, 0, 255);
        drawQuad(bufferBuilder, backgroundX + 13 + xOffset, backgroundY + 1, 1, 14, color >> 16 & 0xFF, color >> 8 & 0xFF, color & 0xFF, 255);
        // Draw marker
        drawQuad(bufferBuilder, backgroundX + 13 + xOffset, backgroundY + 1 + (12 - value), 1, 2, 0x40, 0x40, 0x40, 255);
    }

    private static void drawQuad(BufferBuilder buffer, int x, int y, int width, int height, int red, int green, int blue, int alpha) {
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        buffer.vertex(x + 0, y + 0, 0.0).color(red, green, blue, alpha).next();
        buffer.vertex(x + 0, y + height, 0.0).color(red, green, blue, alpha).next();
        buffer.vertex(x + width, y + height, 0.0).color(red, green, blue, alpha).next();
        buffer.vertex(x + width, y + 0, 0.0).color(red, green, blue, alpha).next();
        BufferRenderer.drawWithShader(buffer.end());
    }
}