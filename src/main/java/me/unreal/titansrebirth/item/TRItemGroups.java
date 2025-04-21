package me.unreal.titansrebirth.item;

import me.unreal.titansrebirth.TitansRebirth;
import me.unreal.titansrebirth.block.TRBasicBlocks;
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
            FabricItemGroup.builder().displayName(Text.translatable("Titan's Rebirth Blocks"))
                    .icon(() -> new ItemStack(TRBasicBlocks.DAWNWOOD_LOG)).entries((displayContext, entries) -> {
                        entries.add(TRBasicBlocks.DAWNWOOD_LOG);
                        entries.add(TRBasicBlocks.DAWNWOOD_WOOD);
                        entries.add(TRBasicBlocks.STRIPPED_DAWNWOOD_LOG);
                        entries.add(TRBasicBlocks.STRIPPED_DAWNWOOD_WOOD);
                        entries.add(TRBasicBlocks.DAWNWOOD_PLANKS);
                        entries.add(TRBasicBlocks.DAWNWOOD_LEAVES);

                    }).build());


    public static final ItemGroup TR_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TitansRebirth.MOD_ID, "tr_items"),
            FabricItemGroup.builder().displayName(Text.translatable("Titan's Rebirth Items"))
                    .icon(() -> new ItemStack(TRBasicItems.DAWNWOOD_STICK)).entries((displayContext, entries) -> {
                        entries.add(TRBasicItems.DAWNWOOD_STICK);
                    }).build());




    public static void registerItemGroups() {
        TitansRebirth.LOGGER.info("Registering Item Groups for " + TitansRebirth.MOD_ID);
    }
}
