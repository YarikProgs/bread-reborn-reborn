package net.aros.breadreborn.events;

import net.aros.breadreborn.init.ModItems;
import net.aros.breadreborn.particle.FlourParticle;
import net.aros.breadreborn.particle.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.aros.breadreborn.BreadReborn.MOD_ID;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = MOD_ID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void wandererTradesEvent(final WandererTradesEvent event) {
            event.getGenericTrades().add(new BasicItemListing(1, new ItemStack(ModItems.GRAPE.get(),       1), 1, 1));
            event.getGenericTrades().add(new BasicItemListing(1, new ItemStack(ModItems.RAISINS.get(),     1), 1, 1));
            event.getGenericTrades().add(new BasicItemListing(1, new ItemStack(ModItems.GRAPE_VINES.get(), 1), 1, 1));
            event.getGenericTrades().add(new BasicItemListing(1, new ItemStack(ModItems.WINE.get(),        1), 1, 1));
        }
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        @SuppressWarnings("deprecation")
        public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
            Minecraft.getInstance().particleEngine.register(ModParticles.FLOUR_PARTICLE.get(), FlourParticle.Provider::new);
        }
    }
}
