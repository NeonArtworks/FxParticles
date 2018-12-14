package at.neonartworks.fxparticles.core2d.system;

import java.util.Iterator;
import java.util.List;

import at.neonartworks.fxparticles.FXParticlesView;
import at.neonartworks.fxparticles.IParticleSystem;
import at.neonartworks.fxparticles.base.BaseParticle2D;
import at.neonartworks.fxparticles.base.BaseParticleEmitter;
import at.neonartworks.fxparticles.base.BaseParticleModifier;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * The particle system is responsible for updateing, drawing and holding the
 * particles, emitters and modifiers.
 * 
 * @author Florian Wagner
 *
 */

public class ParticleSystem2D extends Canvas implements IParticleSystem
{

	private GraphicsContext graphiX;
	private LongProperty particleAmountProperty;
	private LongProperty maxParticleProperty;
	private ObjectProperty<Paint> backgroundPaintProperty;
	private BooleanProperty shouldParticlesAgeProperty;

	private ObservableList<BaseParticle2D> particles;
	private ObservableList<BaseParticleEmitter> particleEmitter;
	private ObservableList<BaseParticleModifier> modifierList;
	private FXParticlesView parent;

	public ParticleSystem2D(FXParticlesView parent)
	{
		particleAmountProperty = new SimpleLongProperty(0);
		maxParticleProperty = new SimpleLongProperty(1000);
		backgroundPaintProperty = new SimpleObjectProperty<>(Color.BLACK);
		shouldParticlesAgeProperty = new SimpleBooleanProperty(true);

		this.parent = parent;
		particles = FXCollections.observableArrayList();
		modifierList = FXCollections.observableArrayList();
		particleEmitter = FXCollections.observableArrayList();
		graphiX = getGraphicsContext2D();
		fillBackground();

	}

	/**
	 * Adds a new emitter to the particle system.
	 * 
	 * @param emitter the emitter to add
	 */
	public void addParticleEmitter(BaseParticleEmitter emitter)
	{
		particleEmitter.add(emitter);
		parent.getChildren().add(emitter);
		emitter.toFront();
	}

	/**
	 * Removes an existing particle emitter from the system.
	 * 
	 * @param emitter
	 */
	public void removeParticleEmitter(BaseParticleEmitter emitter)
	{
		particleEmitter.remove(emitter);
		parent.getChildren().remove(emitter);
	}

	/**
	 * Adds a new particle modifier to the system.
	 * 
	 * @param mod the modifier to add.
	 */
	public void addParticleModifier(BaseParticleModifier mod)
	{
		modifierList.add(mod);
		parent.getChildren().add(mod);
		mod.toFront();
	}

	/**
	 * Removes an existing particle modifier from the system.
	 * 
	 * @param mod
	 */
	public void removeModifier(BaseParticleModifier mod)
	{
		modifierList.remove(mod);
		parent.getChildren().remove(mod);
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

	/**
	 * Sets the maximum amount of particles the system can hold
	 * 
	 * @param maxParticles the maximum particle amount of the system
	 */
	public void setMaxParticles(long maxParticles)
	{
		getMaxParticleProperty().set(maxParticles);
	}

	/**
	 * Returns a {@link List} of {@link BaseParticle2D}. These are all particles
	 * currently available in the system. Modifying this list can cause concurrent
	 * modification exceptions!
	 * 
	 * @return the current particles
	 */
	public List<BaseParticle2D> getParticles()
	{
		return particles;
	}

	/**
	 * Returns a {@link List} of {@link BaseParticleEmitter}. These are all the
	 * emitter currently used in the system.
	 * 
	 * @return the emitters of the system
	 */
	public List<BaseParticleEmitter> getParticleEmitter()
	{
		return particleEmitter;
	}

	/**
	 * Returns a new ID for the particles.
	 * 
	 * @return new id
	 */
	public long getNextID()
	{
		return particles.size() + 1;
	}

	protected void modifyParticles()
	{
		particles.stream().forEach(p ->
			{
				for (BaseParticleModifier modifier : modifierList)
				{
					modifier.modifyParticle(p, this);
				}
			});
		modifyParticlesAll();
	}

	protected void modifyParticlesAll()
	{
		for (BaseParticleModifier modifier : modifierList)
		{
			modifier.modifyParticles(particles, this);
		}
	}

	/**
	 * Returns a {@link List} of {@link BaseParticleModifier}. These are all the
	 * modifiers currently held by the system.
	 * 
	 * @return the modifiers
	 */
	public List<BaseParticleModifier> getModifierList()
	{
		return modifierList;
	}

	public void createParticle(BaseParticle2D particle)
	{
		particles.add(particle);
	}

	/**
	 * Returns the background color of the particle system.
	 * 
	 * @return the background color
	 */
	public Paint getBackgroundPaint()
	{
		return getBackgroundPaintProperty().get();
	}

	/**
	 * Sets the background color of the system.
	 * 
	 * @param backgroundColor the background color
	 */
	public void setBackgroundPaint(Color backgroundColor)
	{
		getBackgroundPaintProperty().set(backgroundColor);
	}

	private void fillBackground()
	{
		graphiX.setFill(getBackgroundPaint());
		graphiX.fillRect(0, 0, getWidth(), getHeight());
	}

	
	private boolean canEmit(BaseParticleEmitter e)
	{
		return getParticleAmount() + e.getAmountToEmit() <= getMaxParticles();
	}
	
	/**
	 * Draws the particle system. This method is called on every frame through the
	 * {@link FXParticlesView} class.
	 */
	@Override
	public void draw()
	{
		fillBackground();

		particles.stream().forEach(p ->
			{
				p.draw(graphiX);
			});
	}

	
	/**
	 * Updates the particle system. This method is called on every frame through the
	 * {@link FXParticlesView} class.
	 */
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
		for (Iterator<BaseParticle2D> iterator = particles.iterator(); iterator.hasNext();)
		{
			BaseParticle2D par = iterator.next();
			if (par.getLifeTime().isDead())
			{
				iterator.remove();
			} else
			{
				par.update(areParticlesAging());
			}
		}

		particleAmountProperty.bind(new SimpleLongProperty(particles.size()));
		Stage st = (Stage) getScene().getWindow();
		st.setTitle("Particles: " + getParticleAmount() + "/" + getMaxParticles());
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

	/**
	 * Gets the background paint property. This property is used to fill the
	 * background of the system.
	 * 
	 * @return background property
	 */
	public ObjectProperty<Paint> getBackgroundPaintProperty()
	{
		return backgroundPaintProperty;
	}

	/**
	 * Sets the background paint property. This property is used to fill the
	 * background of the system.
	 * 
	 * @param backgroundPaintProperty the property
	 */
	public void setBackgroundPaintProperty(ObjectProperty<Paint> backgroundPaintProperty)
	{
		this.backgroundPaintProperty = backgroundPaintProperty;
	}

	private BooleanProperty getShouldParticlesAgeProperty()
	{
		return shouldParticlesAgeProperty;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return "ParticleSystem [particleAmount=" + getParticleAmount() + ", maxParticles=" + getMaxParticles()
				+ ", backgroundColor=" + getBackgroundPaint() + ", particles=" + particles + ", particleEmitter="
				+ particleEmitter + ", modifierList=" + modifierList + "]";
	}

}
