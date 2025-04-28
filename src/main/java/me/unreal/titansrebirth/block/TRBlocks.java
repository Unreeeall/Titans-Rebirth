package me.unreal.titansrebirth.block;

import me.unreal.titansrebirth.TitansRebirth;
import me.unreal.titansrebirth.block.custom.NetherThornLogBlock;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class TRBlocks {


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

    public static final Block DAWNWOOD_LEAVES = registerBlock("dawnwood_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));

    public static final Block DAWNWOOD_STAIRS = registerBlock("dawnwood_stairs",
            new StairsBlock(TRBlocks.DAWNWOOD_PLANKS.getDefaultState(), AbstractBlock.Settings.create()
                    .strength(2f)
                    .requiresTool()
            ));

    public static final Block DAWNWOOD_SLAB = registerBlock("dawnwood_slab",
            new SlabBlock(AbstractBlock.Settings.create()
                    .strength(2f)
                    .requiresTool()
            ));

    public static final Block DAWNWOOD_BUTTON = registerBlock("dawnwood_button",
            new ButtonBlock(BlockSetType.OAK, 4, AbstractBlock.Settings.create()
                    .requiresTool()
            ));

    public static final Block DAWNWOOD_PRESSURE_PLATE = registerBlock("dawnwood_pressure_plate",
            new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.create()
                    .requiresTool()
            ));

    public static final Block DAWNWOOD_FENCE = registerBlock("dawnwood_fence",
            new FenceBlock(AbstractBlock.Settings.create()
                    .requiresTool()
            ));

    public static final Block DAWNWOOD_FENCE_GATE = registerBlock("dawnwood_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create()
                    .requiresTool()
            ));

    public static final Block DAWNWOOD_WALL = registerBlock("dawnwood_wall",
            new WallBlock(AbstractBlock.Settings.create()
                    .requiresTool()
            ));

    public static final Block DAWNWOOD_DOOR = registerBlock("dawnwood_door",
            new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.create()
                    .requiresTool()
                    .nonOpaque()
            ));

    public static final Block DAWNWOOD_TRAPDOOR = registerBlock("dawnwood_trapdoor",
            new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.create()
                    .requiresTool()
                    .nonOpaque()
            ));

    public static final Block SMOOTH_DAWNWOOD_PLANKS = registerBlock("smooth_dawnwood_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));

    /* Netherthorn */
    public static final Block NETHERTHORN_LOG = registerBlock("netherthorn_log",
            new NetherThornLogBlock(AbstractBlock.Settings.create()
                    .strength(4f)
                    .requiresTool()
            ));


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
