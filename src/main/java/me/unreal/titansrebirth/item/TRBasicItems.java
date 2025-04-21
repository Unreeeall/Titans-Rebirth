package me.unreal.titansrebirth.item;

import me.unreal.titansrebirth.TitansRebirth;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class TRBasicItems {

    public static final Item DAWNWOOD_STICK = registerItem("dawnwood_stick",
            new Item(new Item.Settings()));

    public static final Item DAWNWOOD_STICK2 = registerItem("dawnwood_stick2",
            new Item(new Item.Settings()));







    ///////////////////////////////////////////////////////////////////////////////////////
    /// Helpers

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(TitansRebirth.MOD_ID, name), item);
    }

    public static void registerModItems() {
        TitansRebirth.LOGGER.info("Registering Mod Items for " + TitansRebirth.MOD_ID);

        //ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::customIngredients);
    }



    /*
    private static void customIngredients(FabricItemGroupEntries entries) {
        entries.add(FLUORITE);
        entries.add(RAW_FLUORITE);
    }



     */
}
