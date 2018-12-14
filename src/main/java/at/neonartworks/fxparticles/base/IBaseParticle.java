package at.neonartworks.fxparticles.base;

import javafx.scene.canvas.GraphicsContext;

public interface IBaseParticle
{

	public void draw(GraphicsContext graphiX);

	public void update(boolean shouldParticlesAge);
}
