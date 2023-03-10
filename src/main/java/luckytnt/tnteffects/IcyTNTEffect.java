package luckytnt.tnteffects;

import luckytnt.registry.BlockRegistry;
import luckytntlib.util.IExplosiveEntity;
import luckytntlib.util.explosions.ExplosionHelper;
import luckytntlib.util.explosions.IForEachBlockExplosionEffect;
import luckytntlib.util.explosions.ImprovedExplosion;
import luckytntlib.util.tnteffects.PrimedTNTEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class IcyTNTEffect extends PrimedTNTEffect {

	@Override
	public void explosionTick(IExplosiveEntity ent) {
		for(int i = 0; i <= 20; i++) {
			Snowball ball = new Snowball(ent.level(), ent.x() + Math.random() * 40 - Math.random() * 40, ent.y() + 30 + Math.random() * 10 - Math.random() * 10, ent.z() + Math.random() * 40 - Math.random() * 40);
			ball.setDeltaMovement(Math.random() * 0.4 - Math.random() * 0.4, -0.1D - Math.random() * 0.4D, Math.random() * 0.4 - Math.random() * 0.4);
			ent.level().addFreshEntity(ball);
		}
	}
	
	@Override
	public void serverExplosion(IExplosiveEntity ent) {
		ExplosionHelper.doSphericalExplosion(ent.level(), ent.getPos(), 40, new IForEachBlockExplosionEffect() {
			
			@Override
			public void doBlockExplosion(Level level, BlockPos pos, BlockState state, double distance) {
				if (distance <= 40 && state.getExplosionResistance(level, pos, ImprovedExplosion.dummyExplosion()) <= 100) {
					if(state.getMaterial() == Material.GRASS || state.getMaterial() == Material.LEAVES || state.getMaterial() == Material.SAND) {
						state.getBlock().onBlockExploded(state, level, pos, ImprovedExplosion.dummyExplosion()); 
						level.setBlock(pos, Blocks.BLUE_ICE.defaultBlockState(), 3);
					} if(state.getBlock() instanceof LiquidBlock) {
						level.setBlock(pos, Blocks.ICE.defaultBlockState(), 3);
					}
				}
			}
		});
		
		ExplosionHelper.doTopBlockExplosionForAll(ent.level(), ent.getPos(), 40, new IForEachBlockExplosionEffect() {
			
			@Override
			public void doBlockExplosion(Level level, BlockPos pos, BlockState state, double distance) {
				if(state.getExplosionResistance(level, pos, ImprovedExplosion.dummyExplosion()) < 100) {
					level.setBlock(pos, Blocks.SNOW.defaultBlockState(), 3);
				}
			}
		});
	}
	
	@Override
	public void spawnParticles(IExplosiveEntity ent) {
		
	}
	
	@Override
	public Block getBlock() {
		return BlockRegistry.ICY_TNT.get();
	}
	
	@Override
	public int getDefaultFuse(IExplosiveEntity ent) {
		return 180;
	}
}
