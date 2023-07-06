package net.aros.breadreborn.init;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.aros.breadreborn.BreadReborn.MOD_ID;

public class ModParticles {
    public static final DefaultParticleType FLOUR_PARTICLE = FabricParticleTypes.simple();

    public static void init() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(MOD_ID, "flour_particles"), FLOUR_PARTICLE);
    }
}
