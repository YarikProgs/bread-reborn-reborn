package net.aros.breadreborn.mixin;

import net.aros.breadreborn.init.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class PlayerHurtMixin {
    @Inject(method = "damage", at = @At("HEAD"))
    public void onAttackingInject(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!(((LivingEntity) (Object) this) instanceof PlayerEntity player) || player.world.isClient ) return;

        if (player.hasStatusEffect(ModEffects.RAGE)) {
            var effect = player.getStatusEffect(ModEffects.RAGE);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, effect.getAmplifier() == 0 ? 4 * 20 : 8 * 20 * effect.getAmplifier(), effect.getAmplifier(), false, false));

        }
    }
}
