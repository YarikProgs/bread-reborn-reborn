package net.aros.breadreborn.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.util.math.Box;
import org.lwjgl.system.NonnullDefault;

import java.util.List;
import java.util.Optional;

public class RootSolesEffect extends StatusEffect {
    public RootSolesEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    @NonnullDefault
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        final int minFallDistance = 0;
        final int defaultFallDistance = 10;

        entity.fallDistance = Math.min(entity.fallDistance, Math.max(minFallDistance, defaultFallDistance-amplifier));

        int horDistance = 11;
        int verDistance = 3;
        if (!entity.world.isClient) {
            List<HoglinEntity> hoglins = entity.world.getEntitiesByClass(HoglinEntity.class,
                    new Box(entity.getX() - horDistance, entity.getY() - verDistance, entity.getZ() - horDistance,
                             entity.getX() + verDistance, entity.getY() + verDistance, entity.getZ() + horDistance
                    ),
                    hoglin -> true
            );

            hoglins.forEach(hoglin -> hoglin.getBrain().remember(MemoryModuleType.NEAREST_REPELLENT, Optional.of(entity.getBlockPos())));
        }
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
