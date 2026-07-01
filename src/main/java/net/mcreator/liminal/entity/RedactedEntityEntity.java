package net.mcreator.liminal.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.common.NeoForgeMod;

import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.entity.projectile.AbstractThrownPotion;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import java.util.EnumSet;

public class RedactedEntityEntity extends Monster {
	public RedactedEntityEntity(EntityType<RedactedEntityEntity> type, Level world) {
		super(type, world);
		xpReward = 0;
		setNoAi(false);
	}
	private static class CreakingFreezeGoal extends Goal {
		private final PathfinderMob mob;
		/*
		// Strict line-of-sight and distance conditions targeting non-creative, non-spectator players
		private final TargetingConditions playerCheckContext = TargetingConditions.forCombat()
				.range(32.0D)
				.ignoreLineOfSight() // Handled manually by dot product & raytrace
				.selector(entity -> entity instanceof Player player && !player.isCreative() && !player.isSpectator());
		*/
		public CreakingFreezeGoal(PathfinderMob mob) {
			this.mob = mob;
			// Mutex locks both navigation and head rotation
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
		}

		@Override
		public boolean canUse() {
			// Find the closest valid player within 32 blocks
			//Player nearestPlayer = this.mob.level().getNearestPlayer(this.playerCheckContext, this.mob);
			Player player = Minecraft.getInstance().player;
			if (player != null) {
				return isPlayerLookingAtMe(player);
			}
			return false;
		}

		@Override
		public boolean canContinueToUse() {
			return this.canUse();
		}

		@Override
		public void start() {
			freezeEntity();
		}

		@Override
		public void tick() {
			freezeEntity();
		}

		private void freezeEntity() {
			this.mob.getNavigation().stop();
			// Strip all horizontal and vertical inertia
			this.mob.setDeltaMovement(Vec3.ZERO);
			this.mob.setXRot(this.mob.getXRot());
			this.mob.setYRot(this.mob.getYRot());
			this.mob.yRotO = this.mob.getYRot();
			this.mob.xRotO = this.mob.getXRot();
			this.mob.yHeadRot = this.mob.getYRot();
			this.mob.yHeadRotO = this.mob.getYRot();
		}

		private boolean isPlayerLookingAtMe(Player player) {
			Vec3 playerEyePos = player.getEyePosition(1.0F);
			Vec3 mobCenter = this.mob.getBoundingBox().getCenter();
			
			// Step 1: Broad Field-Of-View (FOV) Angle Check using standard Dot Product
			Vec3 playerToMob = mobCenter.subtract(playerEyePos).normalize();
			Vec3 playerLook = player.getViewVector(1.0F).normalize();
			double dotProduct = playerLook.dot(playerToMob);
			
			// 0.866 equals roughly a 60-degree viewing cone
			if (dotProduct > 0.866) {
				// Step 2: Clear Line-of-Sight Block Occlusion Check via standard raycast
				net.minecraft.world.phys.HitResult.Type tmp = this.mob.level().clip(new net.minecraft.world.level.ClipContext(
						playerEyePos, 
						mobCenter, 
						net.minecraft.world.level.ClipContext.Block.VISUAL, 
						net.minecraft.world.level.ClipContext.Fluid.NONE, 
						player
				)).getType();
				return tmp == net.minecraft.world.phys.HitResult.Type.MISS;
			}
			return false;
		}
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new CreakingFreezeGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, false, false));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false) {
			@Override
			protected boolean canPerformAttack(LivingEntity entity) {
				return this.isTimeToAttack() && this.mob.distanceToSqr(entity) < (this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth()) && this.mob.getSensing().hasLineOfSight(entity);
			}
		});
	}

	@Override
	public Vec3 getPassengerRidingPosition(Entity entity) {
		return super.getPassengerRidingPosition(entity).add(0, -0.35F, 0);
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("entity.generic.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("entity.generic.death"));
	}

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource damagesource, float amount) {
		if (damagesource.is(DamageTypes.IN_FIRE))
			return false;
		if (damagesource.getDirectEntity() instanceof AbstractArrow)
			return false;
		if (damagesource.getDirectEntity() instanceof Player)
			return false;
		if (damagesource.getDirectEntity() instanceof AbstractThrownPotion || damagesource.getDirectEntity() instanceof AreaEffectCloud || damagesource.typeHolder().is(NeoForgeMod.POISON_DAMAGE))
			return false;
		if (damagesource.is(DamageTypes.FALL))
			return false;
		if (damagesource.is(DamageTypes.CACTUS))
			return false;
		if (damagesource.is(DamageTypes.DROWN))
			return false;
		if (damagesource.is(DamageTypes.LIGHTNING_BOLT))
			return false;
		if (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION))
			return false;
		if (damagesource.is(DamageTypes.TRIDENT))
			return false;
		if (damagesource.is(DamageTypes.FALLING_ANVIL))
			return false;
		if (damagesource.is(DamageTypes.DRAGON_BREATH))
			return false;
		if (damagesource.is(DamageTypes.WITHER) || damagesource.is(DamageTypes.WITHER_SKULL))
			return false;
		return super.hurtServer(level, damagesource, amount);
	}

	@Override
	public boolean ignoreExplosion(Explosion explosion) {
		return true;
	}

	public static void init(RegisterSpawnPlacementsEvent event) {

	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 10);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		builder = builder.add(Attributes.STEP_HEIGHT, 0.6);
		return builder;
	}

	
}