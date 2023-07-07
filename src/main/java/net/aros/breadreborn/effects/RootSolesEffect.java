package net.aros.breadreborn.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.phys.AABB;
import org.lwjgl.system.NonnullDefault;
import java.util.List;

public class RootSolesEffect extends MobEffect {
    public RootSolesEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    @NonnullDefault
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        final int minFallDistance = 0;
        final int defaultFallDistance = 10;

        entity.fallDistance = Math.min(entity.fallDistance, Math.max(minFallDistance, defaultFallDistance-amplifier));

        int horDistance = 11;
        int verDistance = 3;
        if (!entity.level.isClientSide) {
            List<Hoglin> hoglins = entity.level.getEntitiesOfClass(Hoglin.class,
                    new AABB(entity.getX() - horDistance, entity.getY() - verDistance, entity.getZ() - horDistance,
                             entity.getX() + verDistance, entity.getY() + verDistance, entity.getZ() + horDistance
                    ),
                    hoglin -> true
            );

            hoglins.forEach(hoglin -> hoglin.getBrain().setMemory(MemoryModuleType.NEAREST_REPELLENT, entity.getOnPos()));
        }
        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return true;
    }
}
