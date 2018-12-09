package at.neonartworks.fxparticles.core.system.particle;

import at.neonartworks.fxparticles.core.system.LifeTime;
import at.neonartworks.fxparticles.core.system.ParticleSystem;
import at.neonartworks.fxparticles.util.ParticleUtil;
import at.neonartworks.fxparticles.util.Vec2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public class SmokeParticle extends BaseParticle
{

	Stop[] stops = new Stop[2];
	RadialGradient grad;
	Color shadow = new Color(0, 0, 0, 1);

	public SmokeParticle(double x, double y, ParticleSystem system)
	{
		this(x, y, Color.ORANGE, 50d, SmokeParticle.getRandomVelocity(), LifeTime.getRandom(), system);
	}

	public SmokeParticle(double x, double y, LifeTime lifeTime, ParticleSystem system)
	{
		this(x, y, Color.ORANGE, 50d, SmokeParticle.getRandomVelocity(), lifeTime, system);
	}

	public SmokeParticle(double x, double y, Paint color, ParticleSystem system)
	{
		this(x, y, color, 50d, SmokeParticle.getRandomVelocity(), LifeTime.getRandom(), system);
	}

	public SmokeParticle(double x, double y, Paint color, double size, ParticleSystem system)
	{
		this(x, y, color, size, SmokeParticle.getRandomVelocity(), LifeTime.getRandom(), system);
	}

	public SmokeParticle(double x, double y, Paint color, double size, Vec2D velocity, LifeTime lifeTime,
			ParticleSystem system)
	{
		setParticleSystem(system);
		setPosition(new Vec2D(x, y));
		setColor(color);
		setSize(size * Math.random());
		setVelocity(velocity);
		setLifeTime(lifeTime);
		setId(system.getNextID());
		Color c = (Color) getColor();

		stops[0] = new Stop(0, new Color(c.getRed(), c.getGreen(), c.getBlue(), Math.random()));
		stops[1] = new Stop(1, Color.TRANSPARENT);

	}

	@Override
	public void draw(GraphicsContext graphiX)
	{
		if (getLifeTime().isAlive())
		{
			grad = new RadialGradient(0d, 0d, getX() + getSize() / 2, getY() + getSize() / 2, getSize() / 2, false,
					CycleMethod.REFLECT, stops);

			graphiX.setFill(grad);
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
