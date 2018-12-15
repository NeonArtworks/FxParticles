package at.neonartworks.fxparticles.core3d.system.particle;

import at.neonartworks.fxparticles.base.BaseParticle3D;
import at.neonartworks.fxparticles.base.IBaseParticleSystem;
import at.neonartworks.fxparticles.core2d.system.particle.LifeTime;
import at.neonartworks.fxparticles.core3d.system.ParticleSystem3D;
import at.neonartworks.fxparticles.math.P3D;
import javafx.scene.canvas.GraphicsContext;

public class Particle3D extends BaseParticle3D
{

	public Particle3D(double x, double y, double z, double size, IBaseParticleSystem system)
	{
		super(size);
		setPosition(new P3D(x, y, z));
		setVelocity(Particle3D.getRandomVelocity());
		setParticleSystem((ParticleSystem3D) system);
		setLifeTime(LifeTime.getRandom());

	}

	@Override
	public void draw(GraphicsContext graphiX)
	{

	}

}
