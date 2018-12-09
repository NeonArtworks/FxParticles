package at.neonartworks.fxparticles.core.modifier;

import at.neonartworks.fxparticles.core.system.ParticleSystem;
import at.neonartworks.fxparticles.core.system.particle.BaseParticle;
import at.neonartworks.fxparticles.util.ParticleUtil;
import at.neonartworks.fxparticles.util.Vec2D;

public class TurbulenceModifier extends BaseParticleModifier
{

	public TurbulenceModifier(double x, double y, double strength)
	{
		super(x, y, strength);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void modifyParticle(BaseParticle particle, ParticleSystem system)
	{
		Vec2D dir = Vec2D.subtract(getPosition(), particle.getPosition());
		double dsquared = dir.getMagnitude();
		dsquared = ParticleUtil.constrain(dsquared, 100, 400);
		double strength = getStrength() / dsquared;
		dir.setMagnitude(strength);
		particle.addAcceleration(dir);
	}

}
