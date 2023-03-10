package luckytnt.tnteffects;

import luckytnt.registry.BlockRegistry;
import luckytnt.registry.EntityRegistry;
import luckytntlib.entity.PrimedLTNT;
import luckytntlib.util.IExplosiveEntity;
import luckytntlib.util.explosions.ImprovedExplosion;
import luckytntlib.util.tnteffects.PrimedTNTEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class SquaringTNTEffect extends PrimedTNTEffect{
	
	@Override
	public void baseTick(IExplosiveEntity entity) {
		super.baseTick(entity);
		if(((Entity)entity).isOnGround() && ((Entity)entity).getPersistentData().getInt("level") > 0) {
			serverExplosion(entity);
			if(((Entity)entity).getPersistentData().getInt("level") == 5) {
				Level level = entity.level();
				entity.level().playSound((Entity)entity, new BlockPos(entity.getPos()), SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 4f, (1f + (level.random.nextFloat() - level.random.nextFloat()) * 0.2f) * 0.7f);
			}
		}
	}
	
	@Override
	public void serverExplosion(IExplosiveEntity entity) {
		int level = ((Entity)entity).getPersistentData().getInt("level");
		if(level == 5) {
			ImprovedExplosion explosion = new ImprovedExplosion(entity.level(), entity.getPos(), 15f);
			explosion.doEntityExplosion(1.5f, true);
			explosion.doBlockExplosion(1f, 1f, 1f, 1.25f, false, false);
		} else if(level == 0) {
			for(int count = 0; count < 4; count++) {
				PrimedLTNT tnt = EntityRegistry.SQUARING_TNT.get().create(entity.level());
				tnt.setPos(entity.getPos());
				tnt.setDeltaMovement(Math.random() * 2.5D - 1.25D, 1 + Math.random(), Math.random() * 2.5D - 1.25D);
				tnt.getPersistentData().putInt("level", level + 1);
				entity.level().addFreshEntity(tnt);
			}
		} else {
			for(int count = 0; count < level * level; count++) {
				PrimedLTNT tnt = EntityRegistry.SQUARING_TNT.get().create(entity.level());
				tnt.setPos(entity.getPos());
				tnt.setDeltaMovement(Math.random() * 2.5D - 1.25D, 1 + Math.random(), Math.random() * 2.5D - 1.25D);
				tnt.getPersistentData().putInt("level", level + 1);
				entity.level().addFreshEntity(tnt);
			}
		}
		entity.destroy();
	}
	
	@Override
	public Block getBlock() {
		return BlockRegistry.SQUARING_TNT.get();
	}
	
	@Override
	public int getDefaultFuse(IExplosiveEntity entity) {
		return ((Entity)entity).getPersistentData().getInt("level") == 5 ? 100000 : 200;
	}
}
