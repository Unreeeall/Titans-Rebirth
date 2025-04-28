package me.unreal.titansrebirth.item;

import me.unreal.titansrebirth.TitansRebirth;
import me.unreal.titansrebirth.block.TRBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class TRItemGroups {

    public static final ItemGroup TR_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TitansRebirth.MOD_ID, "tr_blocks"),
            FabricItemGroup.builder().displayName(Text.translatable("Titans Rebirth Blocks"))
                    .icon(() -> new ItemStack(TRBlocks.DAWNWOOD_LOG)).entries((displayContext, entries) -> {
                        /* Dawnwood */
                        entries.add(TRBlocks.DAWNWOOD_LOG);
                        entries.add(TRBlocks.DAWNWOOD_WOOD);
                        entries.add(TRBlocks.STRIPPED_DAWNWOOD_LOG);
                        entries.add(TRBlocks.STRIPPED_DAWNWOOD_WOOD);
                        entries.add(TRBlocks.DAWNWOOD_PLANKS);
                        entries.add(TRBlocks.DAWNWOOD_LEAVES);
                        entries.add(TRBlocks.DAWNWOOD_STAIRS);
                        entries.add(TRBlocks.DAWNWOOD_SLAB);
                        entries.add(TRBlocks.DAWNWOOD_BUTTON);
                        entries.add(TRBlocks.DAWNWOOD_PRESSURE_PLATE);
                        entries.add(TRBlocks.DAWNWOOD_FENCE_GATE);
                        entries.add(TRBlocks.DAWNWOOD_FENCE);
                        entries.add(TRBlocks.DAWNWOOD_TRAPDOOR);
                        entries.add(TRBlocks.DAWNWOOD_DOOR);
                        entries.add(TRBlocks.DAWNWOOD_WALL);
                        entries.add(TRBlocks.SMOOTH_DAWNWOOD_PLANKS);


                        entries.add(TRBlocks.NETHERTHORN_LOG);

                    }).build());


    public static final ItemGroup TR_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TitansRebirth.MOD_ID, "tr_items"),
            FabricItemGroup.builder().displayName(Text.translatable("Titans Rebirth Items"))
                    .icon(() -> new ItemStack(TRBasicItems.DAWNWOOD_STICK)).entries((displayContext, entries) -> {
                        entries.add(TRBasicItems.DAWNWOOD_STICK);
                    }).build());




    public static void registerItemGroups() {
        TitansRebirth.LOGGER.info("Registering Item Groups for " + TitansRebirth.MOD_ID);
    }
}
