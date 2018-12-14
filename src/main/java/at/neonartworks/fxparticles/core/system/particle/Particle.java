package at.neonartworks.fxparticles.core.system.particle;

import at.neonartworks.fxparticles.core.system.ParticleSystem;
import at.neonartworks.fxparticles.util.Vec2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Particle extends BaseParticle
{

	public Particle(double x, double y, ParticleSystem system)
	{
		this(x, y, Color.ORANGE, 5d, Particle.getRandomVelocity(), LifeTime.getRandom(), system);
	}

	public Particle(double x, double y, LifeTime lifeTime, ParticleSystem system)
	{
		this(x, y, Color.ORANGE, 5d, Particle.getRandomVelocity(), lifeTime, system);
	}

	public Particle(double x, double y, Paint color, ParticleSystem system)
	{
		this(x, y, color, 5d, Particle.getRandomVelocity(), LifeTime.getRandom(), system);
	}

	public Particle(double x, double y, Paint color, double size, ParticleSystem system)
	{
		this(x, y, color, size, Particle.getRandomVelocity(), LifeTime.getRandom(), system);
	}

	public Particle(double x, double y, Paint color, double size, Vec2D velocity, LifeTime lifeTime,
			ParticleSystem system)
	{
		setParticleSystem(system);
		setPosition(new Vec2D(x, y));
		setColor(color);
		setSize(size);
		setVelocity(velocity);
		setLifeTime(lifeTime);
		setId(system.getNextID());
	}

	@Override
	public void draw(GraphicsContext graphiX)
	{
		if (getLifeTime().isAlive())
		{
			graphiX.setFill(getColor());
			graphiX.fillOval(getX(), getY(), getSize(), getSize());
		}
	}

	@Override
	public String toString()
	{
		return "Particle [ID=" + getId() + ", ParticleSystem=" + getParticleSystem() + ", LifeTime=" + getLifeTime()
				+ ", Position=" + getPosition() + "]";
	}

}
