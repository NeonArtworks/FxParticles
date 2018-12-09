package at.neonartworks.fxparticles.core.emitter;

import at.neonartworks.fxparticles.core.system.LifeTime;
import at.neonartworks.fxparticles.core.system.ParticleSystem;
import at.neonartworks.fxparticles.core.system.particle.Particle;

public class ParticleEmitter extends BaseParticleEmitter
{

	public ParticleEmitter(double x, double y, long pAmount)
	{
		super(x, y, pAmount);
	}

	@Override
	public void emit(ParticleSystem particleSystem)
	{

		for (int i = 0; i < getAmountToEmit(); i++)
		{

			particleSystem
					.createParticle(new Particle(getPositionX(), getPositionY(), LifeTime.getRandom(), particleSystem));
		}

	}

	@Override
	public String toString()
	{
		return "ParticleEmitter: position:" + getPosition() + ", amountToEmit:" + getAmountToEmit() + ", size:"
				+ getSize() + ", color:" + getColor();
	}
}
