package at.neonartworks.fxparticles.base;

import java.util.List;

import at.neonartworks.fxparticles.core2d.system.ParticleSystem2D;

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
	 * {@link #modifyParticles(List, ParticleSystem2D)} method.
	 * 
	 * @param particle the {@link BaseParticle2D} to modify
	 * @param system   the corresponding {@link ParticleSystem2D}
	 */
	public void modifyParticle(IBaseParticle particle, IBaseParticleSystem system);

	/**
	 * Called once in the system.This method is called after <b>all</b>
	 * {@link #modifyParticle(BaseParticle2D, ParticleSystem2D)} calls! Some algorithm
	 * need all particles in a system for such an algorithm use the
	 * {@link #modifyParticles(List, ParticleSystem2D)} method.
	 * 
	 * @param particles the {@link List} of {@link BaseParticle2D} to modify
	 * @param system   the corresponding {@link ParticleSystem2D}
	 */
	public void modifyParticles(List<IBaseParticle> particles, IBaseParticleSystem system);
}
