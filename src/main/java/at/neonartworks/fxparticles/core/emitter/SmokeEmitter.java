package at.neonartworks.fxparticles.core.emitter;

import at.neonartworks.fxparticles.core.system.LifeTime;
import at.neonartworks.fxparticles.core.system.ParticleSystem;
import at.neonartworks.fxparticles.core.system.particle.SmokeParticle;
import javafx.scene.paint.Color;

public class SmokeEmitter extends BaseParticleEmitter
{
	private Color color;

	public SmokeEmitter(double x, double y, long pAmount, Color color)
	{
		super(x, y, pAmount);
		this.color = color;
	}

	@Override
	public void emit(ParticleSystem system)
	{

		for (int i = 0; i < getAmountToEmit(); i++)
		{

			SmokeParticle particle = new SmokeParticle(getPositionX(), getPositionY(), color, system);
			particle.setLifeTime(new LifeTime(2000));
			system.createParticle(particle);
		}

	}

	@Override
	public String toString()
	{
		return "SmokeEmitter: position:" + getPosition() + ", amountToEmit:" + getAmountToEmit() + ", size:" + getSize()
				+ ", color:" + getColor();
	}
}
