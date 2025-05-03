package me.unreal.titansrebirth.item.custom;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class DawnBladeItem extends SwordItem {
    public DawnBladeItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    private static long lastParticleTime = 0;

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
        Vec3d startPos = attacker.getPos().add(0, attacker.getEyeHeight(attacker.getPose()) -0.5F, 0);
        Vec3d direction = attacker.getRotationVec(0.0F).normalize();
        float speed = 0.05F; // Blocks per tick
        float maxDistance = 100.0F;
        float damage = 7.0F;

        // Schedule a tick loop to simulate the wave
        new Object() {
            private int ticks = 0;
            private Vec3d pos = startPos;
            private final double maxTicks = maxDistance / speed;

            public void tick() {
                if (ticks >= maxTicks || world.isClient) {
                    return;
                }

                // Move the wave
                pos = pos.add(direction.multiply(speed));
                ticks++;

                // Spawn particles (visible to all clients)
                world.spawnParticles(
                        ParticleTypes.END_ROD,
                        pos.x, pos.y, pos.z,
                        1, // Number of particles
                        0.5, 0.5, 0.5, // Spread
                        0.1 // Speed
                );

                // Check for block collision
                if (!world.getBlockState(BlockPos.ofFloored(pos)).isAir()) {
                    return; // Stop if hitting a block
                }

                // Check for entity collisions
                Box hitBox = new Box(pos, pos).expand(1.5); // 1x1x1 hitbox
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
