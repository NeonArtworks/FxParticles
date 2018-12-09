package at.neonartworks.fxparticles.core.modifier;

import at.neonartworks.fxparticles.core.system.ParticleSystem;
import at.neonartworks.fxparticles.core.system.particle.BaseParticle;
import at.neonartworks.fxparticles.util.Vec2D;

public class GravityModifier extends BaseParticleModifier
{

	public GravityModifier(double x, double y, double strength)
	{
		super(x, y, strength);
	}

	@Override
	public void modifyParticle(BaseParticle particle, ParticleSystem system)
	{

		particle.addAcceleration(new Vec2D(0, getStrength() / 100));

	}

	@Override
	public String toString()
	{
		return "GravityModifier [x=" + getX() + ", y=" + getY() + "]";
	}
}
