package at.neonartworks.fxparticles.core.system;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import at.neonartworks.fxparticles.core.emitter.BaseParticleEmitter;
import at.neonartworks.fxparticles.core.modifier.BaseParticleModifier;
import at.neonartworks.fxparticles.core.system.particle.BaseParticle;
import at.neonartworks.fxparticles.core.system.particle.Particle;
import at.neonartworks.fxparticles.util.Vec2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
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

public class ParticleSystem extends Canvas
{

	private GraphicsContext graphiX;
	private long particleAmount = 0;
	private long maxParticles = 100000;
	private Paint backgroundColor = Color.BLACK;

	private List<BaseParticle> particles;
	private List<BaseParticleEmitter> particleEmitter;
	private List<BaseParticleModifier> modifierList;
	private AnchorPane parent;
	private static ParticleSystem system;
	private boolean shouldParticlesAge = true;

	/**
	 * Returns a ParticleSystem instance.
	 * 
	 * @param parent the parent anchor pane.
	 * @return ParticleSystem instance
	 */
	public static ParticleSystem getParticleSystem(AnchorPane parent)
	{
		if (system == null)
			system = new ParticleSystem(parent);
		return system;
	}

	public boolean areParticlesAging()
	{
		return shouldParticlesAge;
	}

	public void setParticlesAging(boolean shouldParticlesAge)
	{
		this.shouldParticlesAge = shouldParticlesAge;
	}

	private ParticleSystem(AnchorPane parent)
	{
		this.parent = parent;
		particles = new ArrayList<>();
		modifierList = new ArrayList<BaseParticleModifier>();
		particleEmitter = new ArrayList<BaseParticleEmitter>();
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
		return particleAmount;
	}

	/**
	 * Returns the maximum particles the system can hold. This value can be changed
	 * with {@link #setMaxParticles(long)}
	 * 
	 * @return the maximum particles the system can hold
	 */
	public long getMaxParticles()
	{
		return maxParticles;
	}

	/**
	 * Sets the maximum amount of particles the system can hold
	 * 
	 * @param maxParticles the maximum particle amount of the system
	 */
	public void setMaxParticles(long maxParticles)
	{
		this.maxParticles = maxParticles;
	}

	/**
	 * Returns a {@link List} of {@link BaseParticle}. These are all particles
	 * currently available in the system. Modifying this list can cause concurrent
	 * modification exceptions!
	 * 
	 * @return the current particles
	 */
	public List<BaseParticle> getParticles()
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

	private void increaseParticleAmount()
	{
		particleAmount++;
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

	public void createParticle(BaseParticle particle)
	{
		particles.add(particle);
		increaseParticleAmount();
	}

	/**
	 * Returns the background color of the particle system.
	 * 
	 * @return the background color
	 */
	public Color getBackgroundColor()
	{
		return (Color) backgroundColor;
	}

	/**
	 * Sets the background color of the system.
	 * 
	 * @param backgroundColor the background color
	 */
	public void setBackgroundColor(Color backgroundColor)
	{
		this.backgroundColor = backgroundColor;
	}

	/**
	 * Can be used to set the background to something else than a static color.
	 * 
	 * @param paint the paint for the background.
	 */
	public void setBackground(Paint paint)
	{
		this.backgroundColor = paint;
	}

	private void fillBackground()
	{
		graphiX.setFill(backgroundColor);
		graphiX.fillRect(0, 0, getWidth(), getHeight());
	}

	protected void draw()
	{
		fillBackground();

		particles.stream().forEach(p ->
			{
				p.draw(graphiX);
			});
	}

	private boolean canEmit(BaseParticleEmitter e)
	{
		return getParticleAmount() + e.getAmountToEmit() <= getMaxParticles();
	}

	protected void update()
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
		for (Iterator<BaseParticle> iterator = particles.iterator(); iterator.hasNext();)
		{
			BaseParticle par = iterator.next();
			if (par.getLifeTime().isDead())
			{
				iterator.remove();
			} else
			{
				par.update(shouldParticlesAge);
			}
		}

		particleAmount = particles.size();
		Stage st = (Stage) getScene().getWindow();
		st.setTitle("Particles: " + particleAmount + "/" + maxParticles);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return "ParticleSystem [particleAmount=" + particleAmount + ", maxParticles=" + maxParticles
				+ ", backgroundColor=" + backgroundColor + ", particles=" + particles + ", particleEmitter="
				+ particleEmitter + ", modifierList=" + modifierList + "]";
	}

}
