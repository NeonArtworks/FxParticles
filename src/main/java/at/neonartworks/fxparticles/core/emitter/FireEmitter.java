package at.neonartworks.fxparticles.core.emitter;

import at.neonartworks.fxparticles.core.system.LifeTime;
import at.neonartworks.fxparticles.core.system.ParticleSystem;
import at.neonartworks.fxparticles.core.system.particle.Particle;
import at.neonartworks.fxparticles.util.Vec2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class FireEmitter extends BaseParticleEmitter
{

	public FireEmitter(double x, double y, long pAmount)
	{
		super(x, y, pAmount);

	}

	@Override
	public void emit(ParticleSystem particleSystem)
	{

		for (int i = 0; i < getAmountToEmit(); i++)
		{
			Stop[] stops = new Stop[] { new Stop(0, Color.RED), new Stop(1, Color.ORANGE), new Stop(2, Color.DARKRED),
					new Stop(3, Color.YELLOW) };
			Vec2D initialVel = new Vec2D(Math.random() * 4 - 2, -Math.random() * 5 - 2);
			LifeTime lifeTime = new LifeTime(3000);

			LinearGradient grad = new LinearGradient(getPositionX(), getPositionY(), getPositionX(), 0, false,
					CycleMethod.REPEAT, stops);

			Particle particle = new Particle(getPositionX(), getPositionY(), grad, particleSystem);
			particle.setLifeTime(lifeTime);
			particle.setVelocity(initialVel);
			particleSystem.createParticle(particle);
		}

	}

	@Override
	public String toString()
	{
		return "FireEmitter: position:" + getPosition() + ", amountToEmit:" + getAmountToEmit() + ", size:" + getSize()
				+ ", color:" + getColor();
	}

}
