package at.neonartworks.fxparticles.core2d.modifier;

import java.util.List;

import at.neonartworks.fxparticles.IParticleSystem;
import at.neonartworks.fxparticles.base.BaseParticle2D;
import at.neonartworks.fxparticles.base.BaseParticleModifier;
import at.neonartworks.fxparticles.core2d.system.ParticleSystem2D;
import at.neonartworks.fxparticles.math.Vec2D;

/**
 * Gravity Modifier<br>
 * The gravity modifier is an example of how to use the
 * {@link BaseParticleModifier}.{@link #modifyParticles(List, ParticleSystem2D)}
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
	public void modifyParticles(List<BaseParticle2D> particles, IParticleSystem system)
	{
		for (BaseParticle2D particle : particles)
			particle.addAcceleration(new Vec2D(0, getStrength() / 100));

	}

	@Override
	public void modifyParticle(BaseParticle2D particle, IParticleSystem system)
	{

	}

	@Override
	public String toString()
	{
		return "GravityModifier [x=" + getX() + ", y=" + getY() + "]";
	}
}
