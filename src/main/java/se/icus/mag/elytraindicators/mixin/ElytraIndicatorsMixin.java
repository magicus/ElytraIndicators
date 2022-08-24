package se.icus.mag.elytraindicators.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import se.icus.mag.elytraindicators.ElytraIndicatorsRenderer;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class ElytraIndicatorsMixin extends DrawableHelper {
    @Shadow
    @Final
    private MinecraftClient client;
    @Shadow
    private int scaledWidth;
    @Shadow
    private int scaledHeight;

    @Unique
    private final ElytraIndicatorsRenderer renderer = new ElytraIndicatorsRenderer();

    @Inject(method = "render", at = @At("RETURN"))
    private void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        renderer.render(matrices, client, scaledWidth, scaledHeight);
    }
}
