package com.ecarrascon.orpheus.entity.custom;

import com.ecarrascon.orpheus.registry.SoundsRegistry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

@SuppressWarnings("deprecation")
public class ViperEntity extends Monster {

    public ViperEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }



    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(4, new ViperAttackGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new ViperTargetGoal<>(this, Player.class));
        this.targetSelector.addGoal(3, new ViperTargetGoal<>(this, IronGolem.class));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 8.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegistry.ENTITY_VIPER_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegistry.ENTITY_VIPER_AMBIENT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegistry.ENTITY_VIPER_DEATH.get();
    }


    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1f);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.4f);
    }

    static class ViperAttackGoal extends MeleeAttackGoal {
        public ViperAttackGoal(ViperEntity viper) {
            super(viper, 1.0D, true);
        }

        public boolean canUse() {
            return super.canUse() && !this.mob.isVehicle();
        }

        public boolean canContinueToUse() {
            float f = this.mob.level().getLightLevelDependentMagicValue(BlockPos.containing(this.mob.getX(), this.mob.getEyeY(), this.mob.getZ()));
            if (f >= 0.5F && this.mob.getRandom().nextInt(100) == 0) {
                this.mob.setTarget((LivingEntity)null);
                return false;
            } else {
                return super.canContinueToUse();
            }
        }

        protected double getAttackReachSqr(LivingEntity pAttackTarget) {
            return (double)(4.0F + pAttackTarget.getBbWidth());
        }
    }

    static class ViperTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        public ViperTargetGoal(ViperEntity viper, Class<T> pEntityTypeToTarget) {
            super(viper, pEntityTypeToTarget, true);
        }
        public boolean canUse() {
            float f = this.mob.level().getLightLevelDependentMagicValue(BlockPos.containing(this.mob.getX(), this.mob.getEyeY(), this.mob.getZ()));
            return f >= 0.5F ? false : super.canUse();
        }
    }
}
