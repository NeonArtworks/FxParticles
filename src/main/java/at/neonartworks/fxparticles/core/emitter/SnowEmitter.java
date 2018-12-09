package at.neonartworks.fxparticles.core.emitter;

import at.neonartworks.fxparticles.core.system.LifeTime;
import at.neonartworks.fxparticles.core.system.ParticleSystem;
import at.neonartworks.fxparticles.core.system.particle.Particle;
import at.neonartworks.fxparticles.util.Vec2D;

public class SnowEmitter extends BaseParticleEmitter
{

	public SnowEmitter(double x, double y, long pAmount)
	{
		super(x, y, pAmount);

	}

	@Override
	public void emit(ParticleSystem particleSystem)
	{
		for (int i = 0; i < getAmountToEmit(); i++)
		{
			Vec2D initialVel = new Vec2D(0, Math.random());
			//LifeTime lifeTime = new LifeTime(10000);
			Particle particle = new Particle(
					Math.random() * particleSystem.getWidth() - (Math.random() * (particleSystem.getWidth() - 1)),
					getPositionY(), particleSystem);
			
			//particle.setLifeTime(lifeTime);
			particle.setVelocity(initialVel);
			
			particleSystem.createParticle(particle);
		}

	}

	@Override
	public String toString()
	{
		return "SnowEmitter: position:" + getPosition() + ", amountToEmit:" + getAmountToEmit() + ", size:" + getSize()
				+ ", color:" + getColor();
	}
}
