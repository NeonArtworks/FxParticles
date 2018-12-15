package at.neonartworks.fxparticles.core3d.emitter;

import at.neonartworks.fxparticles.base.BaseParticleEmitter;
import at.neonartworks.fxparticles.base.IBaseParticleSystem;
import at.neonartworks.fxparticles.core3d.system.ParticleSystem3D;
import at.neonartworks.fxparticles.core3d.system.particle.Particle3D;

public class ParticleEmitter3D extends BaseParticleEmitter
{

	public ParticleEmitter3D(double x, double y, long pAmount)
	{
		super(x, y, pAmount);
	}

	@Override
	public void emit(IBaseParticleSystem particleSystem)
	{
		for (int i = 0; i < getAmountToEmit(); i++)
		{
			((ParticleSystem3D) particleSystem).createParticle(new Particle3D(getLayoutX(), getLayoutY(), getTranslateZ(), 10, particleSystem));
		}

	}

	@Override
	public String toString()
	{
		return "ParticleEmitter: position:" + getPosition2D() + ", amountToEmit:" + getAmountToEmit() + ", size:"
				+ getSize() + ", color:" + getColor();
	}
}
