package me.unreal.titansrebirth.item;

import me.unreal.titansrebirth.TitansRebirth;
import me.unreal.titansrebirth.components.TRComponents;
import me.unreal.titansrebirth.item.custom.DawnBladeItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class TRItems {

    public static final Item DAWNWOOD_STICK = registerItem("dawnwood_stick",
            new Item(new Item.Settings()));

    public static final Item SUNLIT_SHARD = registerItem("sunlit_shard",
            new Item(new Item.Settings()));

    public static final Item DAWNWOOD_SAP = registerItem("dawnwood_sap",
            new Item(new Item.Settings()));

    // May rename to Aurite
    public static final Item RAW_AURIC = registerItem("raw_auric",
            new Item(new Item.Settings()));

    // May rename to Aurite
    public static final Item AURIC = registerItem("auric",
            new Item(new Item.Settings()));

    public static final Item SAP_TAP = registerItem("sap_tap",
            new Item(new Item.Settings()));

    public static final Item SYLVAN_RESERVOIR = registerItem("sylvan_reservoir",
            new Item(new Item.Settings()));

    public static final Item DAWNWOOD_STICK2 = registerItem("dawnwood_stick2",
            new Item(new Item.Settings()));

    public static final Item DAWN_BLADE = registerItem("dawn_blade",
            new DawnBladeItem(TRToolMaterials.DAWN, new Item.Settings()
                    .rarity(Rarity.RARE)
                    .component(TRComponents.CAN_CHARGE, true)
                    .component(TRComponents.HOLD_TIME, 0L)
                    .component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))
                    .attributeModifiers(SwordItem.createAttributeModifiers(TRToolMaterials.DAWN, 3, -2.8F))));







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
