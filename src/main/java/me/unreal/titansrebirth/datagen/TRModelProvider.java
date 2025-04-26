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

        blockStateModelGenerator.registerSimpleCubeAll(TRBlocks.DAWNWOOD_PLANKS);
        blockStateModelGenerator.registerLog(TRBlocks.DAWNWOOD_LOG).log(TRBlocks.DAWNWOOD_LOG).wood(TRBlocks.DAWNWOOD_WOOD);
        blockStateModelGenerator.registerLog(TRBlocks.STRIPPED_DAWNWOOD_LOG).log(TRBlocks.STRIPPED_DAWNWOOD_LOG).wood(TRBlocks.STRIPPED_DAWNWOOD_WOOD);
        blockStateModelGenerator.registerSingleton(TRBlocks.DAWNWOOD_LEAVES, TexturedModel.LEAVES);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(TRBasicItems.DAWNWOOD_STICK, Models.HANDHELD_ROD);
        itemModelGenerator.register(TRBasicItems.DAWNWOOD_STICK2, Models.GENERATED);
    }
}
