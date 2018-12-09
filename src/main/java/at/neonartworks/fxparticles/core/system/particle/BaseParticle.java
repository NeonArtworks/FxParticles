package at.neonartworks.fxparticles.core.system.particle;

import at.neonartworks.fxparticles.core.system.LifeTime;
import at.neonartworks.fxparticles.core.system.ParticleSystem;
import at.neonartworks.fxparticles.util.Vec2D;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public abstract class BaseParticle
{

	public abstract void draw(GraphicsContext graphiX);

	private LifeTime lifeTime;
	private Vec2D position;
	private Vec2D velocity;
	private Vec2D acceleration = new Vec2D(0, 0);
	private Paint color;
	private ParticleSystem particleSystem;
	private double size;
	private double agingSpeed = 0.01d;
	private long id;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public double getAgingSpeed()
	{
		return agingSpeed;
	}

	public void setAgingSpeed(double agingSpeed)
	{
		this.agingSpeed = agingSpeed;
	}

	public ParticleSystem getParticleSystem()
	{
		return particleSystem;
	}

	public void setParticleSystem(ParticleSystem particleSystem)
	{
		this.particleSystem = particleSystem;
	}

	public LifeTime getLifeTime()
	{
		return lifeTime;
	}

	public void setLifeTime(LifeTime lifeTime)
	{
		this.lifeTime = lifeTime;
	}

	public Vec2D getAcceleration()
	{
		return acceleration;
	}

	public void addAcceleration(Vec2D acc)
	{
		this.acceleration = Vec2D.clamp(Vec2D.add(acceleration, acc), 7d, 7d);
	}

	public void setAcceleration(Vec2D acceleration)
	{
		this.acceleration = acceleration;
	}

	public static Vec2D getRandomVelocity()
	{
		return new Vec2D(Math.random() * 4 - 2, Math.random() * 4 - 2);
	}

	public Vec2D getVelocity()
	{
		return velocity;
	}

	public void setVelocity(Vec2D velocity)
	{
		this.velocity = velocity;
	}

	public Paint getColor()
	{
		return color;
	}

	public void setColor(Paint color)
	{
		this.color = color;
	}

	public double getSize()
	{
		return size;
	}

	public void setSize(double size)
	{
		this.size = size;
	}

	public Vec2D getPosition()
	{
		return position;
	}

	public void setPosition(Vec2D pos)
	{
		this.position = pos;
	}

	public void setX(double x)
	{
		this.position.setX(x);
	}

	public void setY(double y)
	{
		this.position.setY(y);
	}

	public double getX()
	{
		return this.position.getX();
	}

	public double getY()
	{
		return this.position.getY();
	}

	public void update(boolean shouldAge)
	{
		getPosition().add(getVelocity());

		Bounds bounds = getParticleSystem().getBoundsInLocal();

		if (getPosition().getX() <= bounds.getMinX())
		{
			getPosition().setX(bounds.getMinX());
			getVelocity().atBounds();

		}

		if (getPosition().getX() >= bounds.getWidth())
		{
			getPosition().setX(bounds.getWidth());
			getVelocity().atBounds();

		}

		if (getPosition().getY() <= bounds.getMinY())
		{
			getPosition().setY(bounds.getMinY());
			getVelocity().atBounds();

		}

		if (getPosition().getY() >= bounds.getHeight())
		{
			getPosition().setY(bounds.getHeight());
			getVelocity().atBounds();

		}

		getVelocity().add(getAcceleration());
		if (shouldAge)
			getLifeTime().incrementAge(getAgingSpeed());
	}

}
