package at.neonartworks.fxparticles.core.modifier;

import java.util.List;

import at.neonartworks.fxparticles.core.system.ParticleSystem;
import at.neonartworks.fxparticles.core.system.particle.BaseParticle;
import at.neonartworks.fxparticles.util.ParticleUtil;
import at.neonartworks.fxparticles.util.Vec2D;
import javafx.scene.paint.Color;

/**
 * Deflector Modifier <br>
 * The deflector modifier is an example of how to implement an basic particle
 * modifier using
 * {@link BaseParticleModifier}.{@link #modifyParticle(BaseParticle, ParticleSystem)}
 * 
 * @author Florian Wagner
 *
 */
public class DeflectorModifier extends BaseParticleModifier
{

	public DeflectorModifier(double x, double y, double strength)
	{
		super(x, y, strength);
		setColor(Color.VIOLET);
	}

	@Override
	public void modifyParticle(BaseParticle particle, ParticleSystem system)
	{
		Vec2D dir = Vec2D.subtract(particle.getPosition(), getPosition());
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
		return "DeflectorModifier: x:" + getPositionX() + ", y:" + getPositionY() + ", strength:" + getStrength();
	}

}
