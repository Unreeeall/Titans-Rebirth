package me.unreal.titansrebirth.item.custom;

import me.unreal.titansrebirth.TitansRebirth;
import me.unreal.titansrebirth.components.TRComponents;
import me.unreal.titansrebirth.particle.TRParticles;
import me.unreal.titansrebirth.util.ItemTracker;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import java.util.List;


public class DawnBladeItem extends SwordItem {
    private static long lastParticleTime = 0;
    private static long currentChargeTicks = 0;
    private static float currentChargePercent = 0;
    private static final int MAX_CHARGE = 100;
    private static final int MAX_CHARGE_TICKS = 432000; //36 ingame days
    private static final int POWER_LEVEL_1_TICKS = 20000; // Reach level 1 at 20,000 ticks ~4.67%
    private static final int POWER_LEVEL_2_TICKS = 60000; // Reach level 2 at 20,000 + 40,000 = 60,000 ticks ~16.67%
    private static final int POWER_LEVEL_3_TICKS = 132000; // Reach level 3 at 60,000 + 72,000 = 132,000 ticks ~27.78%
    private static final int POWER_LEVEL_4_TICKS = 252000; // Reach level 4 at 132,000 + 120,000 = 252,000 ticks ~41.67%
    private static final int POWER_LEVEL_5_TICKS = 432000; // Reach level 5 at 252,000 + 180,000 = 432,000 ticks



    public DawnBladeItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }


    @Override
    public ItemStack getDefaultStack() {
        TitansRebirth.LOGGER.info("getDefaultStack called for {}", this);
        ItemStack stack = super.getDefaultStack();
        stack.set(TRComponents.HOLD_TIME, 0L);
        return stack;
    }



    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        long ticks = ItemTracker.getHoldTime(stack);
        updateCharge(ticks);
        tooltip.add(Text.literal("Power: " + String.format("%.1f", currentChargePercent) + "%" + " (" + ticks + " ticks)"));
        tooltip.add(Text.literal("Level: ").styled(style -> style.withColor(8650846))
                .append(Text.literal(String.valueOf(getPowerLevel(ticks))).styled(style -> style.withColor(16754432))));

        if(Screen.hasShiftDown()) {
            tooltip.add(Text.literal("Attack Damage: +5").styled(style -> style.withColor(0x00AF00)));
            tooltip.add(Text.literal("Attack Speed: -2.8").styled(style -> style.withColor(0x00AF00)));
        }

    }

    private static void updateCharge(long charge) {
        currentChargeTicks = charge;
        currentChargePercent = (float) (currentChargeTicks * 100.0 / MAX_CHARGE_TICKS);
        currentChargePercent = Math.min(currentChargePercent, MAX_CHARGE);
    }

    public int getPowerLevel(long currentTicks) {
        if (currentTicks >= POWER_LEVEL_5_TICKS) return 5;
        if (currentTicks >= POWER_LEVEL_4_TICKS) return 4;
        if (currentTicks >= POWER_LEVEL_3_TICKS) return 3;
        if (currentTicks >= POWER_LEVEL_2_TICKS) return 2;
        if (currentTicks >= POWER_LEVEL_1_TICKS) return 1;
        return 0;
    }

    private int getBeamCooldownForLevel(int level) {
        if(level == 1) return 9000;
        if(level == 2) return 8500;
        if(level == 3) return 7000;
        if(level == 4) return 5000;
        if(level == 5) return 3000;
        return 10000;
    }

    private float getBeamRangeForLevel(int level) {
        if(level == 1) return 4.0F;
        if(level == 2) return 8.0F;
        if(level == 3) return 16.0F;
        if(level == 4) return 32.0F;
        if(level == 5) return 64.0F;
        return 2.0F;
    }

    private float getBeamDmgForLevel(int level) {
        if(level == 1) return 2.5F;
        if(level == 2) return 3.5F;
        if(level == 3) return 5.0F;
        if(level == 4) return 7.0F;
        if(level == 5) return 10.0F;
        return 2.0F;
    }







    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        long currentTime = System.currentTimeMillis();
        long ticks = ItemTracker.getHoldTime(stack);
        int level = getPowerLevel(ticks);
        if (currentTime - lastParticleTime >= getBeamCooldownForLevel(level) && !attacker.getWorld().isClient) {
            spawnParticleWave(attacker, level);
            lastParticleTime = currentTime;
        }

        /*
        if (!attacker.getWorld().isClient() && attacker instanceof PlayerEntity player) {
            float attackCooldown = attacker.getLastAttackTime();
            if(attackCooldown >= 5.0F && !attacker.getWorld().isClient)
            //if (player.getAttackCooldownProgress(0.5F) >= 1.0F
                {
                spawnParticleWave(attacker);
                player.sendMessage(Text.literal("Cooldown complete!"), false);
            } else {
                float progress = player.getAttackCooldownProgress(0.5F);
                player.sendMessage(Text.literal("Cooldown progress: " + progress), false);
            }
        }

         */
        return super.postHit(stack, target, attacker);
    }





    private void spawnParticleWave(LivingEntity attacker, int level) {
        ServerWorld world = (ServerWorld) attacker.getWorld();
        Vec3d startPos = attacker.getPos().add(0, attacker.getEyeHeight(attacker.getPose()) -0.2F, 0);
        Vec3d direction = attacker.getRotationVec(0.0F).normalize();
        float speed = 0.5F; // Blocks per tick
        float maxDistance = getBeamRangeForLevel(level);
        float damage = getBeamDmgForLevel(level);
        float particleSpacing = 0.5F;

        // Schedule a tick loop to simulate the wave
        new Object() {
            private int ticks = 0;
            private Vec3d pos = startPos;
            private final double maxTicks = maxDistance / speed;
            private double distanceTraveled = 0.0;

            public void tick() {
                if (ticks >= maxTicks || world.isClient) {
                    return;
                }

                // Move the wave
                pos = pos.add(direction.multiply(speed));
                distanceTraveled += speed;
                ticks++;

                // Spawn particles (visible to all clients)
                if (distanceTraveled >= particleSpacing){
                    Vec3d particleVelocity = direction.multiply(0.1);
                    world.spawnParticles(
                            TRParticles.DAWN_BEAM_PARTICLE,
                            pos.x, pos.y, pos.z,
                            1, // Number of particles
                            particleVelocity.x, particleVelocity.y, particleVelocity.z, // Spread
                            0 // Speed
                    );
                }


                // Check for block collision
                if (!world.getBlockState(BlockPos.ofFloored(pos)).isAir()) {
                    return; // Stop if hitting a block
                }

                // Check for entity collisions
                Box hitBox = new Box(pos, pos).expand(1.5, 0.5, 0.5); // 3 wide (x), 1 high (y), 1 deep (z)
                for (Entity entity : world.getEntitiesByClass(Entity.class, hitBox, e -> e != attacker && e instanceof LivingEntity)) {
                    LivingEntity target = (LivingEntity) entity;
                    target.damage(world.getDamageSources().playerAttack((PlayerEntity) attacker), damage);
                }

                // Schedule next tick
                world.getServer().execute(() -> {
                    if (ticks < maxTicks) {
                        tick();
                    }
                });
            }
        }.tick();
    }


}
