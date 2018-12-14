package at.neonartworks.fxparticles.base;

import at.neonartworks.fxparticles.core2d.system.ParticleSystem2D;
import at.neonartworks.fxparticles.core2d.system.particle.LifeTime;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.shape.Sphere;

public abstract class BaseParticle3D extends Sphere implements IBaseParticle
{
	private ParticleSystem2D particleSystem;
	private ObjectProperty<LifeTime> lifeTimeProperty;
	private DoubleProperty agingProperty;
	private LongProperty idProperty;

	public BaseParticle3D()
	{

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

	public ParticleSystem2D getParticleSystem()
	{
		return particleSystem;
	}

	public void setParticleSystem(ParticleSystem2D particleSystem)
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

}
