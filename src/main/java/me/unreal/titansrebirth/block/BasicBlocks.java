package me.unreal.titansrebirth.block;

import me.unreal.titansrebirth.TitansRebirth;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BasicBlocks {


    /* Dawnwood set  */
    public static final Block DAWNWOOD_LOG = registerBlock("dawnwood_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));

    public static final Block DAWNWOOD_WOOD = registerBlock("dawnwood_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD)));

    public static final Block STRIPPED_DAWNWOOD_LOG = registerBlock("stripped_dawnwood_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG)));

    public static final Block STRIPPED_DAWNWOOD_WOOD = registerBlock("stripped_dawnwood_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD)));

    public static final Block DAWNWOOD_PLANKS = registerBlock("dawnwood_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));

    /* Netherthorn */

    /* Titanbloom */


    ///////////////////////////////////////////////////////////////////////////////////////
    /// Helpers

    private static Block registerBlock(String name, Block block) {
        registerBLockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(TitansRebirth.MOD_ID, name), block);
    }

    private static void registerBLockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(TitansRebirth.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(TitansRebirth.MOD_ID, name), block);
    }


    public static void registerModBlocks() {
        TitansRebirth.LOGGER.info("Registering BasicBlocks for " + TitansRebirth.MOD_ID);
    }

    /// Helpers
    ///////////////////////////////////////////////////////////////////////////////////////
}
