package at.neonartworks.fxparticles.core.modifier;

import java.util.List;

import at.neonartworks.fxparticles.core.system.ParticleSystem;
import at.neonartworks.fxparticles.core.system.particle.BaseParticle;
import at.neonartworks.fxparticles.util.Vec2D;

/**
 * Gravity Modifier<br>
 * The gravity modifier is an example of how to use the
 * {@link BaseParticleModifier}.{@link #modifyParticles(List, ParticleSystem)}
 * method.
 * 
 * @author Florian Wagner
 *
 */
public class GravityModifier extends BaseParticleModifier
{

	public GravityModifier(double x, double y, double strength)
	{
		super(x, y, strength);
	}

	@Override
	public void modifyParticles(List<BaseParticle> particles, ParticleSystem system)
	{
		for (BaseParticle particle : particles)
			particle.addAcceleration(new Vec2D(0, getStrength() / 100));

	}

	@Override
	public void modifyParticle(BaseParticle particle, ParticleSystem system)
	{

	}

	@Override
	public String toString()
	{
		return "GravityModifier [x=" + getX() + ", y=" + getY() + "]";
	}
}
