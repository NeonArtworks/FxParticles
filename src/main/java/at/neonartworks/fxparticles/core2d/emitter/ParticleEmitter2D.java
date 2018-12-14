package at.neonartworks.fxparticles.core2d.emitter;

import at.neonartworks.fxparticles.base.BaseParticleEmitter;
import at.neonartworks.fxparticles.base.IBaseParticleSystem;
import at.neonartworks.fxparticles.core2d.system.ParticleSystem2D;
import at.neonartworks.fxparticles.core2d.system.particle.LifeTime;
import at.neonartworks.fxparticles.core2d.system.particle.Particle2D;

public class ParticleEmitter2D extends BaseParticleEmitter
{

	public ParticleEmitter2D(double x, double y, long pAmount)
	{
		super(x, y, pAmount);
	}

	@Override
	public void emit(IBaseParticleSystem particleSystem)
	{

		for (int i = 0; i < getAmountToEmit(); i++)
		{
			((ParticleSystem2D) particleSystem).createParticle(new Particle2D(getPositionX(), getPositionY(),
					LifeTime.getRandom(), (ParticleSystem2D) particleSystem));
		}

	}

	@Override
	public String toString()
	{
		return "ParticleEmitter: position:" + getPosition2D() + ", amountToEmit:" + getAmountToEmit() + ", size:"
				+ getSize() + ", color:" + getColor();
	}
}
