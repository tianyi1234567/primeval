
package com.tianyi.primeval.entity;

import com.tianyi.primeval.registry.PLEntiteRegristrys;
import com.tianyi.primeval.specialattacks.swrod.BaseTickProjectile;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

import java.util.*;

public class AreaAttackEnity extends BaseTickProjectile {
    public static AreaAttackEnity createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new AreaAttackEnity(PLEntiteRegristrys.aata, worldIn);
    }

    public List<UUID> isAttack = new ArrayList<>();

    public AreaAttackEnity(EntityType<? extends Projectile> p_37248_, Level p_37249_) {
        super(p_37248_, p_37249_);
        this.noCulling = true;
        noPhysics = true;
        setNoGravity(true);
    }

    //AreaAttack
    public double getAttackRange() {
        return Mth.clamp(0, 5.0 * getTick() / (getTick() + 5), 5);
    }

    @Override
    public void tick() {
        double attackRange = getAttackRange();
        if (attackRange > 4.5) this.discard();
        {
            if (getOwner() == null) return;
            final Vec3 _center = new Vec3(this.getX(), this.getY() + 0.2, this.getZ());
            List<Entity> _entfound = this.level().getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(getAttackRange() * 1.1f), a -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
            for (Entity entityiterator : _entfound) {
                // 添加对玩家的检查，避免对玩家造成伤害
                if (isAttack.contains(entityiterator.getUUID()) || entityiterator instanceof net.minecraft.world.entity.player.Player)
                    continue;
                if (entityiterator != this.getOwner() || entityiterator != this) {
                    if (entityiterator instanceof LivingEntity) {
                        isAttack.add(entityiterator.getUUID());
                        entityiterator.invulnerableTime = 0;
                        ((LivingEntity) entityiterator).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 1, (false), (false)));
                        entityiterator.hurt(new DamageSource(this.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC), this, this.getOwner()), (float) (Math.pow((float) ((LivingEntity) this.getOwner()).getAttribute(Attributes.ATTACK_DAMAGE).getValue(), 1.1f) * 5f));
                    }
                }
            }
        }

        super.tick();
    }
}