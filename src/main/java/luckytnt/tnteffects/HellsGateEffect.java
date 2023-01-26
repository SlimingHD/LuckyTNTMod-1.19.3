package luckytnt.tnteffects;

import luckytnt.config.LuckyTNTConfigValues;
import luckytnt.registry.BlockRegistry;
import luckytntlib.util.IExplosiveEntity;
import luckytntlib.util.explosions.ExplosionHelper;
import luckytntlib.util.explosions.IForEachBlockExplosionEffect;
import luckytntlib.util.explosions.ImprovedExplosion;
import luckytntlib.util.explosions.PrimedTNTEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class HellsGateEffect extends PrimedTNTEffect{

	@Override
	public void serverExplosion(IExplosiveEntity entity) {
		ExplosionHelper.doSphericalExplosion(entity.level(), entity.getPos(), 30, new IForEachBlockExplosionEffect() {
		
			@Override
			public void doBlockExplosion(Level level, BlockPos pos, BlockState state, double distance) {
				BlockPos posTop = pos.offset(0, LuckyTNTConfigValues.ISLAND_HEIGHT.get(), 0);
				BlockState stateTop = level.getBlockState(posTop);
				
				if(state.getExplosionResistance(level, pos, ImprovedExplosion.dummyExplosion()) < 200 && stateTop.getMaterial() == Material.AIR && state.getMaterial() != Material.AIR && Math.abs(entity.y() - pos.getY()) <= 20) {
					level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
					if(state.getMaterial() == Material.WOOD) {
						level.setBlock(posTop, Blocks.OBSIDIAN.defaultBlockState(), 3);
					} else if(state.getMaterial() == Material.LEAVES) {
						level.setBlock(posTop, Blocks.NETHER_BRICKS.defaultBlockState(), 3);
					} else if(state.getBlock() instanceof LiquidBlock) {
						level.setBlock(posTop, Blocks.LAVA.defaultBlockState(), 3);
					} else {
						level.setBlock(posTop, Blocks.NETHERRACK.defaultBlockState(), 3);
					}
				}
			}
		});
		
		ExplosionHelper.doSphericalExplosion(entity.level(), entity.getPos(), 30, new IForEachBlockExplosionEffect() {
			
			@Override
			public void doBlockExplosion(Level level, BlockPos pos, BlockState state, double distance) {
				BlockPos posTop = pos.offset(0, LuckyTNTConfigValues.ISLAND_HEIGHT.get(), 0);
				BlockState stateTop = level.getBlockState(posTop);
				BlockPos posAbove = pos.offset(0, LuckyTNTConfigValues.ISLAND_HEIGHT.get() + 1, 0);
				BlockState stateAbove = level.getBlockState(posAbove);
				
				if(stateAbove.getMaterial() == Material.AIR && Block.isFaceFull(stateTop.getCollisionShape(level, posTop), Direction.UP) && Math.random() <= 0.1D) {
					level.setBlock(posAbove, Blocks.FIRE.defaultBlockState(), 3);
				}
			}
		});
	}
	
	@Override
	public void spawnParticles(IExplosiveEntity ent) {
		ent.level().addParticle(ParticleTypes.FLAME, ent.x(), ent.y() + 0.5f, ent.z(), 0.1f, 0, 0.1f);
		ent.level().addParticle(ParticleTypes.FLAME, ent.x(), ent.y() + 0.5f, ent.z(), -0.1f, 0, -0.1f);
		ent.level().addParticle(ParticleTypes.FLAME, ent.x(), ent.y() + 0.5f, ent.z(), -0.1f, 0, 0.1f);
		ent.level().addParticle(ParticleTypes.FLAME, ent.x(), ent.y() + 0.5f, ent.z(), 0.1f, 0, -0.1f);
		
		ent.level().addParticle(ParticleTypes.FLAME, ent.x(), ent.y() + 0.5f, ent.z(), 0.1f, 0.1f, 0.1f);
		ent.level().addParticle(ParticleTypes.FLAME, ent.x(), ent.y() + 0.5f, ent.z(), -0.1f, 0.1f, -0.1f);
		ent.level().addParticle(ParticleTypes.FLAME, ent.x(), ent.y() + 0.5f, ent.z(), -0.1f, 0.1f, 0.1f);
		ent.level().addParticle(ParticleTypes.FLAME, ent.x(), ent.y() + 0.5f, ent.z(), 0.1f, 0.1f, -0.1f);
		
		ent.level().addParticle(ParticleTypes.FLAME, ent.x(), ent.y() + 0.5f, ent.z(), 0.1f, 0.2f, 0.1f);
		ent.level().addParticle(ParticleTypes.FLAME, ent.x(), ent.y() + 0.5f, ent.z(), -0.1f, 0.2f, -0.1f);
		ent.level().addParticle(ParticleTypes.FLAME, ent.x(), ent.y() + 0.5f, ent.z(), -0.1f, 0.2f, 0.1f);
		ent.level().addParticle(ParticleTypes.FLAME, ent.x(), ent.y() + 0.5f, ent.z(), 0.1f, 0.2f, -0.1f);
		
		ent.level().addParticle(ParticleTypes.FLAME, ent.x(), ent.y() + 0.5f, ent.z(), 0.1f, -0.1f, 0.1f);
		ent.level().addParticle(ParticleTypes.FLAME, ent.x(), ent.y() + 0.5f, ent.z(), -0.1f, -0.1f, -0.1f);
		ent.level().addParticle(ParticleTypes.FLAME, ent.x(), ent.y() + 0.5f, ent.z(), -0.1f, -0.1f, 0.1f);
		ent.level().addParticle(ParticleTypes.FLAME, ent.x(), ent.y() + 0.5f, ent.z(), 0.1f, -0.1f, -0.1f);
		
		ent.level().addParticle(ParticleTypes.FLAME, ent.x(), ent.y() + 0.5f, ent.z(), 0.1f, -0.2f, 0.1f);
		ent.level().addParticle(ParticleTypes.FLAME, ent.x(), ent.y() + 0.5f, ent.z(), -0.1f, -0.2f, -0.1f);
		ent.level().addParticle(ParticleTypes.FLAME, ent.x(), ent.y() + 0.5f, ent.z(), -0.1f, -0.2f, 0.1f);
		ent.level().addParticle(ParticleTypes.FLAME, ent.x(), ent.y() + 0.5f, ent.z(), 0.1f, -0.2f, -0.1f);
	}

	@Override
	public Block getBlock() {
		return BlockRegistry.HELLS_GATE.get();
	}
	
	@Override
	public int getDefaultFuse(IExplosiveEntity entity) {
		return 140;
	}
}
