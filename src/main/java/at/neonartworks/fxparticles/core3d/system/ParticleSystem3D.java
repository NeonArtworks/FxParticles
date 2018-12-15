package at.neonartworks.fxparticles.core3d.system;

import java.util.Iterator;

import at.neonartworks.fxparticles.base.BaseParticle3D;
import at.neonartworks.fxparticles.base.BaseParticleEmitter;
import at.neonartworks.fxparticles.base.BaseParticleModifier;
import at.neonartworks.fxparticles.base.IBaseParticle;
import at.neonartworks.fxparticles.base.IBaseParticleSystem;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ParticleSystem3D extends AnchorPane implements IBaseParticleSystem
{
	private ObservableList<IBaseParticle> particles;
	private ObservableList<BaseParticleEmitter> particleEmitter;
	private ObservableList<BaseParticleModifier> particleModifiers;

	private LongProperty particleAmountProperty;
	private LongProperty maxParticleProperty;
	private BooleanProperty shouldParticlesAgeProperty;

	private Rectangle background = new Rectangle();

	public ParticleSystem3D()
	{
		particleAmountProperty = new SimpleLongProperty();
		maxParticleProperty = new SimpleLongProperty(1000);
		shouldParticlesAgeProperty = new SimpleBooleanProperty(true);

		particles = FXCollections.observableArrayList();
		particleEmitter = FXCollections.observableArrayList();
		particleModifiers = FXCollections.observableArrayList();
		background.widthProperty().bind(widthProperty());
		background.heightProperty().bind(heightProperty());
		background.setFill(Color.BLACK);
		getChildren().add(background);
		

	}

	public void addModifier(BaseParticleModifier modifier)
	{
		particleModifiers.add(modifier);
	}

	public void addEmitter(BaseParticleEmitter modifier)
	{
		particleEmitter.remove(modifier);
	}

	private boolean canEmit(BaseParticleEmitter e)
	{
		return getParticleAmount() + e.getAmountToEmit() <= getMaxParticles();
	}

	/**
	 * Returns the current amount of particles in the system.
	 * 
	 * @return particle amount
	 */
	public long getParticleAmount()
	{
		return getParticleAmountProperty().get();
	}

	/**
	 * Returns the maximum particles the system can hold. This value can be changed
	 * with {@link #setMaxParticles(long)}
	 * 
	 * @return the maximum particles the system can hold
	 */
	public long getMaxParticles()
	{
		return getMaxParticleProperty().get();
	}

	public void setMaxParticles(long maxParticles)
	{
		getMaxParticleProperty().set(maxParticles);
	}

	/**
	 * Returns true if the particles age, otherwise false.
	 * 
	 * @return particle aging
	 */
	public boolean areParticlesAging()
	{
		return getShouldParticlesAgeProperty().get();
	}

	/**
	 * Through this method one can set whether the particles should age over time or
	 * not.
	 * 
	 * @param shouldParticlesAge particles aging or not
	 */
	public void setParticlesAging(boolean shouldParticlesAge)
	{
		getShouldParticlesAgeProperty().set(shouldParticlesAge);
	}

	private LongProperty getParticleAmountProperty()
	{
		return particleAmountProperty;
	}

	/**
	 * Returns the MaxParticles property. This property holds the information about
	 * how many (maximum) particles the system can hold at once.
	 * 
	 * @return the max particle property
	 */
	public LongProperty getMaxParticleProperty()
	{
		return maxParticleProperty;
	}

	/**
	 * Through this you can set the MaxParticles property.
	 * 
	 * @param maxParticleProperty the property
	 */
	public void setMaxParticleProperty(LongProperty maxParticleProperty)
	{
		this.maxParticleProperty = maxParticleProperty;
	}

	private BooleanProperty getShouldParticlesAgeProperty()
	{
		return shouldParticlesAgeProperty;
	}

	@Override
	public void createParticle(IBaseParticle particle)
	{
		particles.add(particle);
		getChildren().add((BaseParticle3D) particle);
		((BaseParticle3D) particle).toBack();
	}

	@Override
	public void addParticleModifier(BaseParticleModifier b)
	{
		particleModifiers.add(b);
		getChildren().add(b);
		b.toFront();
	}

	@Override
	public void removeParticleModifier(BaseParticleModifier b)
	{
		particleModifiers.remove(b);
		getChildren().remove(b);
	}

	@Override
	public void addParticleEmitter(BaseParticleEmitter b)
	{
		particleEmitter.add(b);
		getChildren().add(b);
		b.toFront();
	}

	@Override
	public void removeParticleEmitter(BaseParticleEmitter b)
	{
		particleEmitter.remove(b);
		getChildren().remove(b);
	}

	@Override
	public void modifyParticles()
	{
		particles.stream().forEach(p ->
			{
				for (BaseParticleModifier modifier : particleModifiers)
				{
					modifier.modifyParticle(p, this);
				}
			});
		modifyParticlesAll();
	}

	@Override
	public void modifyParticlesAll()
	{
		for (BaseParticleModifier modifier : particleModifiers)
		{
			modifier.modifyParticles(particles, this);
		}
	}

	@Override
	public void update()
	{
		
		particleEmitter.stream().forEach(e ->
			{
				
				if (canEmit(e))
				{
					
					e.emit(this);
				}
			});

		modifyParticles();

		// iterator loop for increased speed!
		for (Iterator<IBaseParticle> iterator = particles.iterator(); iterator.hasNext();)
		{
			BaseParticle3D par = (BaseParticle3D) iterator.next();
			if (par.getLifeTime().isDead())
			{
				getChildren().remove(par);
				iterator.remove();
			} else
			{
				par.update(areParticlesAging());
			}
		}

		particleAmountProperty.bind(new SimpleLongProperty(particles.size()));
		if (getScene() != null)
		{
			Stage st = (Stage) getScene().getWindow();
			st.setTitle("Particles: " + getParticleAmount() + "/" + getMaxParticles());

		}
	}

	private void fillBackground()
	{
		background.setFill(Color.BLACK);
		background.toBack();
	}

	@Override
	public void draw()
	{
		fillBackground();
	}
}
