package at.neonartworks.fxparticles.core2d.modifier;

import java.util.List;

import at.neonartworks.fxparticles.base.BaseParticle2D;
import at.neonartworks.fxparticles.base.BaseParticleModifier;
import at.neonartworks.fxparticles.base.IBaseParticle;
import at.neonartworks.fxparticles.base.IBaseParticleSystem;
import at.neonartworks.fxparticles.core2d.system.ParticleSystem2D;
import at.neonartworks.fxparticles.util.ParticleUtil;
import javafx.scene.paint.Color;

/**
 * Color Modifier<br>
 * The color modifier is an example of how to use the
 * {@link BaseParticleModifier}.{@link #modifyParticles(List, ParticleSystem2D)}
 * method.
 * 
 * @author Florian Wagner
 *
 */

public class ColorModifier2D extends BaseParticleModifier
{

	public ColorModifier2D(double x, double y, double strength)
	{
		super(x, y, strength);

	}

	@Override
	public void modifyParticles(List<IBaseParticle> particles, IBaseParticleSystem system)
	{

		particles.stream().forEach(p ->
			{

				double hue = ParticleUtil.map( ((BaseParticle2D)p).getLifeTime().getAge(), ((BaseParticle2D)p).getLifeTime().getLifeTime(), 0, 0, 360);
				((BaseParticle2D)p).setColor(Color.hsb(hue, 1, 1));

			});

	}

	@Override
	public void modifyParticle(IBaseParticle particle, IBaseParticleSystem system)
	{

	}

	@Override
	public String toString()
	{
		return "ColorModifier [x=" + getX() + ", y=" + getY() + "]";
	}

}
