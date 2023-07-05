package net.aros.breadreborn.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class FlourParticle extends SpriteBillboardParticle {
    private final SpriteProvider sprites;

    protected FlourParticle(ClientWorld world, double x, double y, double z, SpriteProvider set, double xd, double yd, double zd) {
        super(world, x, y, z, xd, yd, zd);


        velocityMultiplier = 0.8F;
        this.x = xd;
        this.y = yd;
        this.z = zd;
        this.scale *= 1.25F;
        this.maxAge = 20;

        sprites = set;

        this.setSpriteForAge(set);

        this.red = 1F;
        this.green = 1F;
        this.blue = 1F;
    }

    @Override
    public void tick() {
        super.tick();
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;

        if (this.age++ >= this.maxAge) {
            this.markDead();
        } else {
            this.setSpriteForAge(this.sprites);
        }
        fadeOut();
    }

    private void fadeOut() {
        this.alpha = (-(1/(float)maxAge) * age + 1);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_LIT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider sprites;

        public Factory(SpriteProvider spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(DefaultParticleType particleType, ClientWorld level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new FlourParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}
