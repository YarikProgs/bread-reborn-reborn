package net.aros.breadreborn.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.lwjgl.system.NonnullDefault;

public class ProtectiveShellEffect extends MobEffect {
    public ProtectiveShellEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    @NonnullDefault
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        entity.removeEffect(MobEffects.LEVITATION);
        entity.removeEffect(MobEffects.WITHER);
        entity.removeEffect(MobEffects.POISON);

        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return true;
    }
}
