package at.neonartworks.fxparticles.base;

public interface IBaseParticleSystem
{
	public void update();
	public void draw();
	public void addParticleModifier(BaseParticleModifier b);
	public void removeParticleModifier(BaseParticleModifier b);
	public void addParticleEmitter(BaseParticleEmitter b);
	public void removeParticleEmitter(BaseParticleEmitter b);
	public void modifyParticles();
	public void modifyParticlesAll();
	public void createParticle(IBaseParticle particle);
	
}
