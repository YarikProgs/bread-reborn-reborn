package net.aros.breadreborn;

import net.aros.breadreborn.init.ModBlocks;
import net.aros.breadreborn.init.ModParticles;
import net.aros.breadreborn.network.ModMessages;
import net.aros.breadreborn.particles.FlourParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.render.RenderLayer;

public class BreadRebornClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.FLOUR_PARTICLE, FlourParticle.Factory::new);

        ModMessages.registerS2CPackets();

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.GRAPE_VINES, ModBlocks.BLOOMING_GRAPE_VINES);
    }
}
