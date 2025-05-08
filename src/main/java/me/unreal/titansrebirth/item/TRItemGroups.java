package me.unreal.titansrebirth.item;

import me.unreal.titansrebirth.TitansRebirth;
import me.unreal.titansrebirth.block.TRBlocks;
import me.unreal.titansrebirth.components.TRComponents;
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
            FabricItemGroup.builder().displayName(Text.literal("Titans Rebirth Blocks"))
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
            FabricItemGroup.builder().displayName(Text.literal("Titans Rebirth Items"))
                    .icon(() -> new ItemStack(TRBasicItems.DAWNWOOD_STICK)).entries((displayContext, entries) -> {
                        entries.add(TRBasicItems.DAWNWOOD_STICK);
                        entries.add(TRBasicItems.DAWN_BLADE);


                        ItemStack dawnBladeLevel0noCharge = new ItemStack(TRBasicItems.DAWN_BLADE);
                        dawnBladeLevel0noCharge.set(TRComponents.HOLD_TIME, 0L);
                        dawnBladeLevel0noCharge.set(TRComponents.CAN_CHARGE, false);
                        entries.add(dawnBladeLevel0noCharge);

                        ItemStack dawnBladeLevel1 = new ItemStack(TRBasicItems.DAWN_BLADE);
                        dawnBladeLevel1.set(TRComponents.HOLD_TIME, 20000L);
                        dawnBladeLevel1.set(TRComponents.CAN_CHARGE, false);
                        entries.add(dawnBladeLevel1);

                        ItemStack dawnBladeLevel2 = new ItemStack(TRBasicItems.DAWN_BLADE);
                        dawnBladeLevel2.set(TRComponents.HOLD_TIME, 60000L);
                        dawnBladeLevel2.set(TRComponents.CAN_CHARGE, false);
                        entries.add(dawnBladeLevel2);

                        ItemStack dawnBladeLevel3 = new ItemStack(TRBasicItems.DAWN_BLADE);
                        dawnBladeLevel3.set(TRComponents.HOLD_TIME, 132000L);
                        dawnBladeLevel3.set(TRComponents.CAN_CHARGE, false);
                        entries.add(dawnBladeLevel3);

                        ItemStack dawnBladeLevel4 = new ItemStack(TRBasicItems.DAWN_BLADE);
                        dawnBladeLevel4.set(TRComponents.HOLD_TIME, 252000L);
                        dawnBladeLevel4.set(TRComponents.CAN_CHARGE, false);
                        entries.add(dawnBladeLevel4);

                        ItemStack dawnBladeLevel5 = new ItemStack(TRBasicItems.DAWN_BLADE);
                        dawnBladeLevel5.set(TRComponents.HOLD_TIME, 432000L);
                        dawnBladeLevel5.set(TRComponents.CAN_CHARGE, false);
                        entries.add(dawnBladeLevel5);
                    }).build());




    public static void registerItemGroups() {
        TitansRebirth.LOGGER.info("Registering Item Groups for " + TitansRebirth.MOD_ID);
    }
}
