package me.unreal.titansrebirth.item;

import me.unreal.titansrebirth.TitansRebirth;
import me.unreal.titansrebirth.block.BasicBlocks;
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
                    .icon(() -> new ItemStack(BasicBlocks.DAWNWOOD_LOG)).entries((displayContext, entries) -> {
                        entries.add(BasicBlocks.DAWNWOOD_LOG);
                        entries.add(BasicBlocks.DAWNWOOD_WOOD);
                        entries.add(BasicBlocks.STRIPPED_DAWNWOOD_LOG);
                        entries.add(BasicBlocks.STRIPPED_DAWNWOOD_WOOD);
                        entries.add(BasicBlocks.DAWNWOOD_PLANKS);
                        entries.add(BasicBlocks.DAWNWOOD_LEAVES);

                    }).build());


    public static void registerItemGroups() {
        TitansRebirth.LOGGER.info("Registering Item Groups for " + TitansRebirth.MOD_ID);
    }
}
