package luckytnt.tnteffects;

import java.util.Random;

import org.joml.Vector3f;

import luckytnt.registry.BlockRegistry;
import luckytntlib.util.IExplosiveEntity;
import luckytntlib.util.explosions.ImprovedExplosion;
import luckytntlib.util.tnteffects.PrimedTNTEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;

public class FluorineTNTEffect extends PrimedTNTEffect {

	@Override
	public void explosionTick(IExplosiveEntity ent) {
		if(ent.getTNTFuse() == 310) {
			if(!ent.level().isClientSide()) {
				ImprovedExplosion explosion = new ImprovedExplosion(ent.level(), (Entity)ent, ent.getPos(), 50f + Math.round(new Random().nextFloat(31f)));
				explosion.doEntityExplosion(7f, true);
				explosion.doBlockExplosion(1f, 1f, 0.75f, 0.5f, false, false);
			}
		}
		if(ent.getTNTFuse() < 300) {
			if(ent.getPersistentData().getInt("nextExplosion") <= 0) {
				double x = ent.x() + Math.random() * 80 - Math.random() * 80;
				double y = ent.y() + Math.random() * 30 - Math.random() * 30;
				double z = ent.z() + Math.random() * 80 - Math.random() * 80;
				if(!ent.level().isClientSide()) {
					ImprovedExplosion explosion = new ImprovedExplosion(ent.level(), (Entity)ent, new Vec3(x, y, z), 50f + Math.round(new Random().nextFloat(31f)));
					explosion.doEntityExplosion(7f, true);
					explosion.doBlockExplosion(1f, 1f, 0.75f, 0.5f, false, false);
				}
				ent.level().playSound(null, new BlockPos(x, y, z), SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 4, (1.0F + (ent.level().getRandom().nextFloat() - ent.level().getRandom().nextFloat()) * 0.2F) * 0.7F);
				ent.getPersistentData().putInt("nextExplosion", 5 + (int)Math.round(Math.random() * 2));
			}
			ent.getPersistentData().putInt("nextExplosion", ent.getPersistentData().getInt("nextExplosion") - 1);
		}
	}
	
	@Override
	public void spawnParticles(IExplosiveEntity ent) {
		for (double i = 0D; i < 1D; i += 0.05D) {
			ent.level().addParticle(new DustParticleOptions(new Vector3f(1f, 1f, 0.65f), 1f), ent.x() + 0.5D, ent.y() + i, ent.z() + 0.5D, 0, 0, 0);
			ent.level().addParticle(new DustParticleOptions(new Vector3f(1f, 1f, 0.65f), 1f), ent.x() - 0.5D, ent.y() + i, ent.z() + 0.5D, 0, 0, 0);
			ent.level().addParticle(new DustParticleOptions(new Vector3f(1f, 1f, 0.65f), 1f), ent.x() + 0.5D, ent.y() + i, ent.z() - 0.5D, 0, 0, 0);
			ent.level().addParticle(new DustParticleOptions(new Vector3f(1f, 1f, 0.65f), 1f), ent.x() - 0.5D, ent.y() + i, ent.z() - 0.5D, 0, 0, 0);
			ent.level().addParticle(new DustParticleOptions(new Vector3f(1f, 1f, 0.65f), 1f), ent.x() + 0.5D, ent.y() + i, ent.z(), 0, 0, 0);
			ent.level().addParticle(new DustParticleOptions(new Vector3f(1f, 1f, 0.65f), 1f), ent.x() - 0.5D, ent.y() + i, ent.z(), 0, 0, 0);
			ent.level().addParticle(new DustParticleOptions(new Vector3f(1f, 1f, 0.65f), 1f), ent.x(), ent.y() + i, ent.z() + 0.5D, 0, 0, 0);
			ent.level().addParticle(new DustParticleOptions(new Vector3f(1f, 1f, 0.65f), 1f), ent.x(), ent.y() + i, ent.z() - 0.5D, 0, 0, 0);
		}
	}
	
	@Override
	public Block getBlock() {
		return BlockRegistry.FLUORINE_TNT.get();
	}
	
	@Override
	public int getDefaultFuse(IExplosiveEntity ent) {
		return 400;
	}
}
