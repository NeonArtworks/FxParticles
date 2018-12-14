package at.neonartworks.fxparticles.core.system.particle;

import at.neonartworks.fxparticles.core.system.ParticleSystem;
import at.neonartworks.fxparticles.util.Vec2D;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public abstract class BaseParticle
{

	public abstract void draw(GraphicsContext graphiX);

	private ObjectProperty<LifeTime> lifeTimeProperty;
	private ObjectProperty<Vec2D> positionProperty;
	private ObjectProperty<Vec2D> velocityProperty;
	private ObjectProperty<Vec2D> accelerationProperty;
	private ObjectProperty<Paint> paintProperty;
	private DoubleProperty sizeProperty;
	private DoubleProperty agingProperty;
	private LongProperty idProperty;

	private ParticleSystem particleSystem;

	public BaseParticle()
	{
		lifeTimeProperty = new SimpleObjectProperty<>();
		positionProperty = new SimpleObjectProperty<>();
		velocityProperty = new SimpleObjectProperty<>();
		accelerationProperty = new SimpleObjectProperty<>(getRandomVelocity());
		paintProperty = new SimpleObjectProperty<>();
		sizeProperty = new SimpleDoubleProperty(10);
		agingProperty = new SimpleDoubleProperty(0.01d);
		idProperty = new SimpleLongProperty();
	}

	public long getId()
	{
		return getIdProperty().get();
	}

	public void setId(long id)
	{
		getIdProperty().set(id);
	}

	public double getAgingSpeed()
	{
		return getAgingProperty().get();
	}

	public void setAgingSpeed(double agingSpeed)
	{
		getAgingProperty().set(agingSpeed);
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
		return getLifeTimeProperty().get();
	}

	public void setLifeTime(LifeTime lifeTime)
	{
		getLifeTimeProperty().set(lifeTime);
	}

	public Vec2D getAcceleration()
	{
		return getAccelerationProperty().get();
	}

	public void addAcceleration(Vec2D acc)
	{
		setAcceleration(Vec2D.clamp(Vec2D.add(getAcceleration(), acc), 7d, 7d));
	}

	public void setAcceleration(Vec2D acceleration)
	{
		getAccelerationProperty().set(acceleration);
	}

	public static Vec2D getRandomVelocity()
	{
		return new Vec2D(Math.random() * 4 - 2, Math.random() * 4 - 2);
	}

	public Vec2D getVelocity()
	{
		return getVelocityProperty().get();
	}

	public void setVelocity(Vec2D velocity)
	{
		getVelocityProperty().set(velocity);
	}

	public Paint getColor()
	{
		return getPaintProperty().get();
	}

	public void setColor(Paint color)
	{
		getPaintProperty().set(color);
	}

	public double getSize()

	{
		return getSizeProperty().get();
	}

	public void setSize(double size)
	{
		getSizeProperty().set(size);
	}

	public Vec2D getPosition()
	{
		return getPositionProperty().get();
	}

	public void setPosition(Vec2D pos)
	{
		getPositionProperty().set(pos);
	}

	public void setX(double x)
	{
		getPosition().setX(x);
	}

	public void setY(double y)
	{
		getPosition().setY(y);
	}

	public double getX()
	{
		return getPosition().getX();
	}

	public double getY()
	{
		return getPosition().getY();
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

	public ObjectProperty<LifeTime> getLifeTimeProperty()
	{
		return lifeTimeProperty;
	}

	public void setLifeTimeProperty(ObjectProperty<LifeTime> lifeTimeProperty)
	{
		this.lifeTimeProperty = lifeTimeProperty;
	}

	public ObjectProperty<Vec2D> getPositionProperty()
	{
		return positionProperty;
	}

	public void setPositionProperty(ObjectProperty<Vec2D> positionProperty)
	{
		this.positionProperty = positionProperty;
	}

	public ObjectProperty<Vec2D> getVelocityProperty()
	{
		return velocityProperty;
	}

	public void setVelocityProperty(ObjectProperty<Vec2D> velocityProperty)
	{
		this.velocityProperty = velocityProperty;
	}

	public ObjectProperty<Vec2D> getAccelerationProperty()
	{
		return accelerationProperty;
	}

	public void setAccelerationProperty(ObjectProperty<Vec2D> accelerationProperty)
	{
		this.accelerationProperty = accelerationProperty;
	}

	public ObjectProperty<Paint> getPaintProperty()
	{
		return paintProperty;
	}

	public void setPaintProperty(ObjectProperty<Paint> paintProperty)
	{
		this.paintProperty = paintProperty;
	}

	public DoubleProperty getSizeProperty()
	{
		return sizeProperty;
	}

	public void setSizeProperty(DoubleProperty sizeProperty)
	{
		this.sizeProperty = sizeProperty;
	}

	public DoubleProperty getAgingProperty()
	{
		return agingProperty;
	}

	public void setAgingProperty(DoubleProperty agingProperty)
	{
		this.agingProperty = agingProperty;
	}

	public LongProperty getIdProperty()
	{
		return idProperty;
	}

	public void setIdProperty(LongProperty idProperty)
	{
		this.idProperty = idProperty;
	}

}
