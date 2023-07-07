package net.aros.breadreborn.events;

import net.aros.breadreborn.init.ModEffects;
import net.aros.breadreborn.init.ModItems;
import net.aros.breadreborn.particle.FlourParticle;
import net.aros.breadreborn.particle.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
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

        @SubscribeEvent
        public static void livingDamageEvent(final LivingDamageEvent event) {
            if (!(event.getEntity() instanceof Player player) || event.getEntity().level.isClientSide) return;
            if (player.hasEffect(ModEffects.RAGE.get())) {
                var effect = player.getEffect(ModEffects.RAGE.get());
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, effect.getAmplifier() == 0 ? 4 * 20 : 8 * 20 * effect.getAmplifier(), effect.getAmplifier(), false, false));
            }
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
