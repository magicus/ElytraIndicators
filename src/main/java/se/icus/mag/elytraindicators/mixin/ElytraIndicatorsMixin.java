package se.icus.mag.elytraindicators.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LayeredDrawer;
import net.minecraft.client.gui.hud.InGameHud;
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

    @Shadow
    @Final
    private LayeredDrawer layeredDrawer;

    @Unique
    private final ElytraIndicatorsRenderer renderer = new ElytraIndicatorsRenderer();

    @Inject(method = "Lnet/minecraft/client/gui/hud/InGameHud;<init>(Lnet/minecraft/client/MinecraftClient;)V", at = @At("RETURN"))
    private void constructor(MinecraftClient client, CallbackInfo ci) {
        layeredDrawer.addLayer(this::renderElytraIndicators);
    }

    @Unique
    private void renderElytraIndicators(DrawContext context, float tickDelta) {
        renderer.render(context, client);
    }
}
