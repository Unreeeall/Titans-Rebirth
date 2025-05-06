package me.unreal.titansrebirth.components;

import com.mojang.serialization.Codec;
import me.unreal.titansrebirth.TitansRebirth;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class TRComponents {
    public static final ComponentType<Long> HOLD_TIME = register("hold_time", builder -> builder.codec(Codec.LONG));
    public static final ComponentType<Integer> DAWN_CHARGE_LEVEL = register("dawn_charge_level", builder -> builder.codec(Codec.INT));
    public static final ComponentType<Boolean> CAN_CHARGE = register("can_charge", builder -> builder.codec(Codec.BOOL));


    private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderUnaryOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(TitansRebirth.MOD_ID, name),
                (builderUnaryOperator.apply(ComponentType.builder())).build());
    }


    public static void registerDataComponents(){
        TitansRebirth.LOGGER.info("Registering Data Component Types for " + TitansRebirth.MOD_ID);
    }
}
