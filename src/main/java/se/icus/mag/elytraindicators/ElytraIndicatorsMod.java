package se.icus.mag.elytraindicators;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import se.icus.mag.elytraindicators.config.ElytraIndicatorsConfig;

public class ElytraIndicatorsMod implements ClientModInitializer {
    private static ElytraIndicatorsConfig config;
    private final ElytraIndicatorsRenderer renderer = new ElytraIndicatorsRenderer();

    @Override
    public void onInitializeClient() {
        ConfigHolder<ElytraIndicatorsConfig> configHolder = AutoConfig.register(ElytraIndicatorsConfig.class, GsonConfigSerializer::new);
        config = configHolder.getConfig();

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            renderer.render(drawContext, MinecraftClient.getInstance());
        });
    }

    public static ElytraIndicatorsConfig getConfig() {
        return config;
    }

}
