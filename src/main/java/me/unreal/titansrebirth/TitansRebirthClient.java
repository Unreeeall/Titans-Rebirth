package me.unreal.titansrebirth;

import me.unreal.titansrebirth.block.TRBlocks;
import me.unreal.titansrebirth.particle.TRParticles;
import me.unreal.titansrebirth.particle.custom.DawnBeamParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.render.RenderLayer;

public class TitansRebirthClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(TRBlocks.DAWNWOOD_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TRBlocks.DAWNWOOD_TRAPDOOR, RenderLayer.getCutout());


        ParticleFactoryRegistry.getInstance().register(TRParticles.DAWN_BEAM_PARTICLE, DawnBeamParticle.Factory::new);
    }
}
