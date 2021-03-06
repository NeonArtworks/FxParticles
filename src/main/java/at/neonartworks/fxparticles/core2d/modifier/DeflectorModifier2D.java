package at.neonartworks.fxparticles.core2d.modifier;

import java.util.List;

import at.neonartworks.fxparticles.base.BaseParticle2D;
import at.neonartworks.fxparticles.base.BaseParticleModifier;
import at.neonartworks.fxparticles.base.IBaseParticle;
import at.neonartworks.fxparticles.base.IBaseParticleSystem;
import at.neonartworks.fxparticles.core2d.system.ParticleSystem2D;
import at.neonartworks.fxparticles.math.Vec2D;
import at.neonartworks.fxparticles.util.ParticleUtil;
import javafx.scene.paint.Color;

/**
 * Deflector Modifier <br>
 * The deflector modifier is an example of how to implement an basic particle
 * modifier using
 * {@link BaseParticleModifier}.{@link #modifyParticle(BaseParticle2D, ParticleSystem2D)}
 * 
 * @author Florian Wagner
 *
 */
public class DeflectorModifier2D extends BaseParticleModifier
{

	public DeflectorModifier2D(double x, double y, double strength)
	{
		super(x, y, strength);
		setColor(Color.VIOLET);
	}

	@Override
	public void modifyParticle(IBaseParticle particle, IBaseParticleSystem system)
	{
		Vec2D dir = Vec2D.subtract(((BaseParticle2D) particle).getPosition(), getPosition2D());
		double dsquared = dir.getMagnitude();
		dsquared = ParticleUtil.constrain(dsquared, 100, 400);
		double strength = getStrength() / dsquared;
		dir.setMagnitude(strength);
		((BaseParticle2D) particle).addAcceleration(dir);
	}

	@Override
	public void modifyParticles(List<IBaseParticle> particles, IBaseParticleSystem system)
	{

	}

	@Override
	public String toString()
	{
		return "DeflectorModifier: x:" + getPositionX() + ", y:" + getPositionY() + ", strength:" + getStrength();
	}

}
