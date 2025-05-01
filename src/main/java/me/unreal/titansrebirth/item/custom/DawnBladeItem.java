package me.unreal.titansrebirth.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class DawnBladeItem extends SwordItem {
    public DawnBladeItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        float attackCooldown = attacker.getLastAttackTime();
        if(attackCooldown >= 1.0F && !attacker.getWorld().isClient) {
            spawnParticleWave(attacker);
        }


        return super.postHit(stack, target, attacker);
    }

    private void spawnParticleWave(LivingEntity attacker) {
        ServerWorld world = (ServerWorld) attacker.getWorld();
        Vec3d startPos = attacker.getPos().add(0, attacker.getEyeHeight(attacker.getPose()), 0);
        Vec3d direction = attacker.getRotationVec(1.0F).normalize();
        float speed = 1.0F; // Blocks per tick
        float maxDistance = 100.0F; // Travel up to 10 blocks
        float damage = 5.0F; // 2.5 hearts of damage

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
                        ParticleTypes.SWEEP_ATTACK,
                        pos.x, pos.y, pos.z,
                        1, // Number of particles
                        0.2, 0.2, 0.2, // Spread
                        0.0 // Speed
                );

                // Check for block collision
                if (!world.getBlockState(BlockPos.ofFloored(pos)).isAir()) {
                    return; // Stop if hitting a block
                }

                // Check for entity collisions
                Box hitBox = new Box(pos, pos).expand(0.5); // 1x1x1 hitbox
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
