package at.neonartworks.fxparticles.core.modifier;

import java.util.List;

import at.neonartworks.fxparticles.core.system.ParticleSystem;
import at.neonartworks.fxparticles.core.system.particle.BaseParticle;
import at.neonartworks.fxparticles.util.ParticleUtil;
import at.neonartworks.fxparticles.util.Vec2D;
import javafx.scene.paint.Color;

/**
 * Attractor Modifier <br>
 * The attractor modifier is an example of how to implement an basic particle
 * modifier using
 * {@link BaseParticleModifier}.{@link #modifyParticle(BaseParticle, ParticleSystem)}
 * 
 * @author Florian Wagner
 *
 */

public class AttractorModifier extends BaseParticleModifier
{

	public AttractorModifier(double x, double y, double strength)
	{
		super(x, y, strength);
		setColor(Color.YELLOW);
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

	@Override
	public void modifyParticles(List<BaseParticle> particles, ParticleSystem system)
	{

	}

	@Override
	public String toString()
	{
		return "AttractorModifier: x:" + getPositionX() + ", y:" + getPositionY() + ", strength:" + getStrength();
	}

}
