package at.neonartworks.fxparticles.core2d.system.particle;

import at.neonartworks.fxparticles.base.BaseParticle2D;
import at.neonartworks.fxparticles.core2d.system.ParticleSystem2D;
import at.neonartworks.fxparticles.math.Vec2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Particle2D extends BaseParticle2D
{

	public Particle2D(double x, double y, ParticleSystem2D system)
	{
		this(x, y, Color.ORANGE, 5d, Particle2D.getRandomVelocity(), LifeTime.getRandom(), system);
	}

	public Particle2D(double x, double y, LifeTime lifeTime, ParticleSystem2D system)
	{
		this(x, y, Color.ORANGE, 5d, Particle2D.getRandomVelocity(), lifeTime, system);
	}

	public Particle2D(double x, double y, Paint color, ParticleSystem2D system)
	{
		this(x, y, color, 5d, Particle2D.getRandomVelocity(), LifeTime.getRandom(), system);
	}

	public Particle2D(double x, double y, Paint color, double size, ParticleSystem2D system)
	{
		this(x, y, color, size, Particle2D.getRandomVelocity(), LifeTime.getRandom(), system);
	}

	public Particle2D(double x, double y, Paint color, double size, Vec2D velocity, LifeTime lifeTime,
			ParticleSystem2D system)
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
