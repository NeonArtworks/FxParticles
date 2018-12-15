package at.neonartworks.fxparticles.base;

import at.neonartworks.fxparticles.core2d.system.particle.LifeTime;
import at.neonartworks.fxparticles.core3d.system.ParticleSystem3D;
import at.neonartworks.fxparticles.math.P3D;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.scene.shape.Sphere;

public abstract class BaseParticle3D extends Sphere implements IBaseParticle
{
	private ParticleSystem3D particleSystem;
	private ObjectProperty<LifeTime> lifeTimeProperty;
	private DoubleProperty agingProperty;
	private LongProperty idProperty;
	private ObjectProperty<P3D> velocityProperty;
	private ObjectProperty<P3D> accelerationProperty;
	private ObjectProperty<P3D> positionProperty;

	public BaseParticle3D(double size)
	{
		super(size);
		velocityProperty = new SimpleObjectProperty<>();
		accelerationProperty = new SimpleObjectProperty<>(getRandomVelocity());
		positionProperty = new SimpleObjectProperty<>();
		lifeTimeProperty = new SimpleObjectProperty<>();
		agingProperty = new SimpleDoubleProperty(0.1d);
		idProperty = new SimpleLongProperty();

	}

	public static P3D getRandomVelocity()
	{
		return new P3D(Math.random() * 4 - 2, Math.random() * 4 - 2, Math.random() * 4 - 2);
	}

	public long getID()
	{
		return getIdProperty().get();
	}

	public void setID(long id)
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

	public ParticleSystem3D getParticleSystem()
	{
		return particleSystem;
	}

	public void setParticleSystem(ParticleSystem3D particleSystem)
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

	public ObjectProperty<LifeTime> getLifeTimeProperty()
	{
		return lifeTimeProperty;
	}

	public void setLifeTimeProperty(ObjectProperty<LifeTime> lifeTimeProperty)
	{
		this.lifeTimeProperty = lifeTimeProperty;
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

	public P3D getPosition()
	{
		return getPositionProperty().get();
	}

	public void setPosition(P3D p)
	{
		getPositionProperty().set(p);

	}

	public P3D getVelocity()
	{
		return getVelocityProperty().get();
	}

	public void setVelocity(P3D p)
	{
		getVelocityProperty().set(p);
	}

	public P3D getAcceleration()
	{
		return getAccelerationProperty().get();
	}

	public void setAcceleration(P3D p)
	{
		getAccelerationProperty().set(p);
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

		translateXProperty().bind(new SimpleDoubleProperty(positionProperty.get().getX()));
		translateYProperty().bind(new SimpleDoubleProperty(positionProperty.get().getY()));
		translateZProperty().bind(new SimpleDoubleProperty(positionProperty.get().getZ()));

	}

	public ObjectProperty<P3D> getVelocityProperty()
	{
		return velocityProperty;
	}

	public void setVelocityProperty(ObjectProperty<P3D> velocityProperty)
	{
		this.velocityProperty = velocityProperty;
	}

	public ObjectProperty<P3D> getAccelerationProperty()
	{
		return accelerationProperty;
	}

	public void setAccelerationProperty(ObjectProperty<P3D> accelerationProperty)
	{
		this.accelerationProperty = accelerationProperty;
	}

	public ObjectProperty<P3D> getPositionProperty()
	{
		return positionProperty;
	}

	public void setPositionProperty(ObjectProperty<P3D> positionProperty)
	{
		this.positionProperty = positionProperty;
	}

}
