package me.unreal.titansrebirth.particle.custom;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class DawnBeamParticle extends ExplosionLargeParticle {
    protected DawnBeamParticle(ClientWorld clientWorld, double x, double y, double z, double d, SpriteProvider spriteProvider) {
        super(clientWorld, x, y, z, d, spriteProvider);
        this.maxAge = 16;
        this.scale = 1.5F;
        this.setSpriteForAge(spriteProvider);
    }

    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double x, double y, double z, double d, double h, double i) {
            return new DawnBeamParticle(clientWorld, x, y, z, d, this.spriteProvider);
        }
    }
}
