package at.neonartworks.fxparticles.core.modifier;

import java.util.List;

import at.neonartworks.fxparticles.core.system.ParticleSystem;
import at.neonartworks.fxparticles.core.system.particle.BaseParticle;

/**
 * Interface for modifying methods.
 * 
 * @author Florian Wagner
 *
 */
public interface IBaseParticleModifier
{
	/**
	 * Called for every particle in the system (sequentially). Some algorithm need
	 * all particles in a system for such an algorithm use the
	 * {@link #modifyParticles(List, ParticleSystem)} method.
	 * 
	 * @param particle the {@link BaseParticle} to modify
	 * @param system   the corresponding {@link ParticleSystem}
	 */
	public void modifyParticle(BaseParticle particle, ParticleSystem system);

	/**
	 * Called once in the system.This method is called after <b>all</b>
	 * {@link #modifyParticle(BaseParticle, ParticleSystem)} calls! Some algorithm
	 * need all particles in a system for such an algorithm use the
	 * {@link #modifyParticles(List, ParticleSystem)} method.
	 * 
	 * @param particles the {@link List} of {@link BaseParticle} to modify
	 * @param system   the corresponding {@link ParticleSystem}
	 */
	public void modifyParticles(List<BaseParticle> particles, ParticleSystem system);
}
