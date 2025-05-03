package me.unreal.titansrebirth.item.custom;

import me.unreal.titansrebirth.TitansRebirth;
import me.unreal.titansrebirth.block.TRBlocks;
import me.unreal.titansrebirth.components.TRComponents;
import me.unreal.titansrebirth.particle.TRParticles;
import me.unreal.titansrebirth.util.ItemTracker;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class DawnBladeItem extends SwordItem {
    private static long lastParticleTime = 0;
    private static long currentChargeTicks = 0;
    private static float currentChargePercent = 0;
    private static final int MAX_CHARGE = 100;
    private static final int MAX_CHARGE_TICKS = 432000; //36 ingame days

    public DawnBladeItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }


    @Override
    public ItemStack getDefaultStack() {
        TitansRebirth.LOGGER.info("getDefaultStack called for {}", this);
        ItemStack stack = super.getDefaultStack();
        stack.set(TRComponents.HOLD_TIME, 0L); // Initialize HOLD_TIME component
        return stack;
    }


    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        long ticks = ItemTracker.getHoldTime(stack);
        updateCharge(ticks);
        tooltip.add(Text.literal("Sun Power: " + String.format("%.1f", currentChargePercent) + "%" + " (" + ticks + " ticks)"));
        super.appendTooltip(stack, context, tooltip, type);
    }

    private static void updateCharge(long charge) {
        currentChargeTicks = charge;
        currentChargePercent = (float) (currentChargeTicks * 100.0 / MAX_CHARGE_TICKS);
        currentChargePercent = Math.min(currentChargePercent, MAX_CHARGE);
    }





    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastParticleTime >= 5000 && !attacker.getWorld().isClient) {
            spawnParticleWave(attacker);
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





    private void spawnParticleWave(LivingEntity attacker) {
        ServerWorld world = (ServerWorld) attacker.getWorld();
        Vec3d startPos = attacker.getPos().add(0, attacker.getEyeHeight(attacker.getPose()) -0.2F, 0);
        Vec3d direction = attacker.getRotationVec(0.0F).normalize();
        float speed = 0.5F; // Blocks per tick
        float maxDistance = 100.0F;
        float damage = 7.0F;
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
