package se.icus.mag.elytraindicators.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
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
public abstract class ElytraIndicatorsMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Unique
    private final ElytraIndicatorsRenderer renderer = new ElytraIndicatorsRenderer();

    @Inject(method = "renderHotbar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At("RETURN"))
    private void constructor(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        renderer.render(context, client);
    }
}
