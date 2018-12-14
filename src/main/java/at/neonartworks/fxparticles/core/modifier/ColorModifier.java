package at.neonartworks.fxparticles.core.modifier;

import java.util.List;

import at.neonartworks.fxparticles.core.system.ParticleSystem;
import at.neonartworks.fxparticles.core.system.particle.BaseParticle;
import at.neonartworks.fxparticles.util.ParticleUtil;
import javafx.scene.paint.Color;

/**
 * Color Modifier<br>
 * The color modifier is an example of how to use the
 * {@link BaseParticleModifier}.{@link #modifyParticles(List, ParticleSystem)}
 * method.
 * 
 * @author Florian Wagner
 *
 */

public class ColorModifier extends BaseParticleModifier
{

	public ColorModifier(double x, double y, double strength)
	{
		super(x, y, strength);

	}

	@Override
	public void modifyParticles(List<BaseParticle> particles, ParticleSystem system)
	{

		particles.stream().forEach(p ->
			{

				double hue = ParticleUtil.map(p.getLifeTime().getAge(), p.getLifeTime().getLifeTime(), 0, 0, 360);

				p.setColor(Color.hsb(hue, 1, 1));

			});

	}

	@Override
	public void modifyParticle(BaseParticle particle, ParticleSystem system)
	{

	}

	@Override
	public String toString()
	{
		return "ColorModifier [x=" + getX() + ", y=" + getY() + "]";
	}

}
