package me.unreal.titansrebirth.util;

import me.unreal.titansrebirth.components.TRComponents;
import me.unreal.titansrebirth.item.TRBasicItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

import java.util.Set;

public class ItemTracker {

    private static final Set<Item> DAWN_TOOLS = Set.of(
            TRBasicItems.DAWN_BLADE,
            TRBasicItems.DAWNWOOD_STICK
    );

    private static final int UPDATE_INTERVAL = 60;

    public static void initialize() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for(ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                ServerWorld world = player.getServerWorld();

                long timeOfDay = world.getTimeOfDay() % 24000;
                boolean isDaytime =timeOfDay >= 0 && timeOfDay < 12000;

                ItemStack mainHandStack = player.getMainHandStack();

                if (isDaytime && !mainHandStack.isEmpty() && DAWN_TOOLS.contains(mainHandStack.getItem())) {
                    if (mainHandStack.get(TRComponents.HOLD_TIME) == null) {
                        mainHandStack.set(TRComponents.HOLD_TIME, 0L);
                    }

                    long currentTicks = mainHandStack.getOrDefault(TRComponents.HOLD_TIME, 0L);

                    if (world.getTime() % UPDATE_INTERVAL == 0) {
                        mainHandStack.set(TRComponents.HOLD_TIME, currentTicks + UPDATE_INTERVAL);
                    }
                }

                ItemStack offHandStack = player.getOffHandStack();
                if (isDaytime && !offHandStack.isEmpty() && DAWN_TOOLS.contains(offHandStack.getItem())) {
                    if (offHandStack.get(TRComponents.HOLD_TIME) == null) {
                        offHandStack.set(TRComponents.HOLD_TIME, 0L);
                    }

                    long currentTicks = offHandStack.getOrDefault(TRComponents.HOLD_TIME, 0L);
                    if (world.getTime() % UPDATE_INTERVAL == 0) {
                        offHandStack.set(TRComponents.HOLD_TIME, currentTicks + UPDATE_INTERVAL);
                    }
                }
            }
        });
    }


    public static long getHoldTime(ItemStack stack) {
        return stack.getOrDefault(TRComponents.HOLD_TIME, 0L);
    }
}
