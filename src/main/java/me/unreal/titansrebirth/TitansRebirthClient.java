package me.unreal.titansrebirth;

import me.unreal.titansrebirth.block.TRBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class TitansRebirthClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(TRBlocks.DAWNWOOD_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TRBlocks.DAWNWOOD_TRAPDOOR, RenderLayer.getCutout());
    }
}
