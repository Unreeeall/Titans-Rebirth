package me.unreal.titansrebirth.datagen;

import me.unreal.titansrebirth.block.TRBasicBlocks;
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

        blockStateModelGenerator.registerSimpleCubeAll(TRBasicBlocks.DAWNWOOD_PLANKS);
        blockStateModelGenerator.registerLog(TRBasicBlocks.DAWNWOOD_LOG).log(TRBasicBlocks.DAWNWOOD_LOG).wood(TRBasicBlocks.DAWNWOOD_WOOD);
        blockStateModelGenerator.registerLog(TRBasicBlocks.STRIPPED_DAWNWOOD_LOG).log(TRBasicBlocks.STRIPPED_DAWNWOOD_LOG).wood(TRBasicBlocks.STRIPPED_DAWNWOOD_WOOD);
        blockStateModelGenerator.registerSingleton(TRBasicBlocks.DAWNWOOD_LEAVES, TexturedModel.LEAVES);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(TRBasicItems.DAWNWOOD_STICK, Models.GENERATED);
        itemModelGenerator.register(TRBasicItems.DAWNWOOD_STICK2, Models.GENERATED);
    }
}
