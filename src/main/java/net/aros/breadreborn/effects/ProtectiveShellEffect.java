package net.aros.breadreborn.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import org.lwjgl.system.NonnullDefault;

public class ProtectiveShellEffect extends StatusEffect {
    public ProtectiveShellEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    @NonnullDefault
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.removeStatusEffect(StatusEffects.LEVITATION);
        entity.removeStatusEffect(StatusEffects.WITHER);
        entity.removeStatusEffect(StatusEffects.POISON);

        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
