package me.unreal.titansrebirth.particle;

import me.unreal.titansrebirth.TitansRebirth;
import me.unreal.titansrebirth.particle.custom.DawnBeamParticle;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class TRParticles {
    public static final SimpleParticleType DAWN_BEAM_PARTICLE = registerParticle("dawn_beam", FabricParticleTypes.simple(true));

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(TitansRebirth.MOD_ID, name), particleType);
    }


    public static void registerParticles() {
        TitansRebirth.LOGGER.info("Registering Particles for " + TitansRebirth.MOD_ID);
    }
}
