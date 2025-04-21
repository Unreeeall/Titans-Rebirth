package me.unreal.titansrebirth.datagen;

import me.unreal.titansrebirth.block.BasicBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class TRModelProvider extends FabricModelProvider {
    public TRModelProvider(FabricDataOutput output) { super(output); }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {


        blockStateModelGenerator.registerLog(BasicBlocks.DAWNWOOD_LOG).log(BasicBlocks.DAWNWOOD_LOG).wood(BasicBlocks.DAWNWOOD_WOOD);
        blockStateModelGenerator.registerLog(BasicBlocks.STRIPPED_DAWNWOOD_LOG).log(BasicBlocks.STRIPPED_DAWNWOOD_LOG).wood(BasicBlocks.STRIPPED_DAWNWOOD_WOOD);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
