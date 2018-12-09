package at.neonartworks.fxparticles.core.modifier;

import at.neonartworks.fxparticles.core.system.ParticleSystem;
import at.neonartworks.fxparticles.core.system.particle.BaseParticle;

public interface IBaseParticleModifier
{
	public void modifyParticle(BaseParticle particle, ParticleSystem system);
}
