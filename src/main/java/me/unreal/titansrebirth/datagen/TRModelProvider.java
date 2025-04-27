package me.unreal.titansrebirth.datagen;

import me.unreal.titansrebirth.block.TRBlocks;
import me.unreal.titansrebirth.item.TRBasicItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TexturedModel;

public class TRModelProvider extends FabricModelProvider {
    public TRModelProvider(FabricDataOutput output) { super(output); }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        BlockStateModelGenerator.BlockTexturePool dawnwoodPlankTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(TRBlocks.DAWNWOOD_PLANKS);
        blockStateModelGenerator.registerLog(TRBlocks.DAWNWOOD_LOG).log(TRBlocks.DAWNWOOD_LOG).wood(TRBlocks.DAWNWOOD_WOOD);
        blockStateModelGenerator.registerLog(TRBlocks.STRIPPED_DAWNWOOD_LOG).log(TRBlocks.STRIPPED_DAWNWOOD_LOG).wood(TRBlocks.STRIPPED_DAWNWOOD_WOOD);
        blockStateModelGenerator.registerSingleton(TRBlocks.DAWNWOOD_LEAVES, TexturedModel.LEAVES);

        dawnwoodPlankTexturePool.stairs(TRBlocks.DAWNWOOD_STAIRS);
        dawnwoodPlankTexturePool.slab(TRBlocks.DAWNWOOD_SLAB);
        dawnwoodPlankTexturePool.button(TRBlocks.DAWNWOOD_BUTTON);
        dawnwoodPlankTexturePool.pressurePlate(TRBlocks.DAWNWOOD_PRESSURE_PLATE);
        dawnwoodPlankTexturePool.fence(TRBlocks.DAWNWOOD_FENCE);
        dawnwoodPlankTexturePool.fenceGate(TRBlocks.DAWNWOOD_FENCE_GATE);
        dawnwoodPlankTexturePool.wall(TRBlocks.DAWNWOOD_WALL);

        blockStateModelGenerator.registerDoor(TRBlocks.DAWNWOOD_DOOR);
        blockStateModelGenerator.registerTrapdoor(TRBlocks.DAWNWOOD_TRAPDOOR);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(TRBasicItems.DAWNWOOD_STICK, Models.HANDHELD_ROD);
        itemModelGenerator.register(TRBasicItems.DAWNWOOD_STICK2, Models.GENERATED);
    }
}
