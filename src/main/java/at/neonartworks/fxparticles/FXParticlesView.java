package at.neonartworks.fxparticles;

import at.neonartworks.fxparticles.base.IBaseParticleSystem;
import at.neonartworks.fxparticles.core2d.system.ParticleSystem2D;
import at.neonartworks.fxparticles.core3d.system.ParticleSystem3D;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class FXParticlesView extends AnchorPane
{

	private Timeline systemLoop;
	private RenderMode renderMode;
	private double width, height;
	private IBaseParticleSystem particleSystem;

	public FXParticlesView(double width, double height, double updateTime, RenderMode rendermode)
	{
		this.renderMode = rendermode;
		this.width = width;
		this.height = height;
		createParticleSystemBaseOnRenderMode();
		startSystemLoop(updateTime);

	}

	private void createParticleSystemBaseOnRenderMode()
	{

		switch (renderMode)
		{
		case _2D:

			create2DParticleSystem();

			break;

		case _3D:
			create3DParticleSystem();
			break;
		default:
			break;
		}

	}

	private void create2DParticleSystem()
	{
		this.particleSystem = new ParticleSystem2D(this);
		((Canvas) this.particleSystem).setWidth(width);
		((Canvas) this.particleSystem).setHeight(height);
		this.getChildren().add((Node) particleSystem);

		((Canvas) particleSystem).widthProperty().bind(widthProperty());
		((Canvas) particleSystem).heightProperty().bind(heightProperty());
	}

	private void create3DParticleSystem()
	{
		this.particleSystem = new ParticleSystem3D();
		setTopAnchor((Node) this.particleSystem, 0d);
		setBottomAnchor((Node) this.particleSystem, 0d);
		setLeftAnchor((Node) this.particleSystem, 0d);
		setRightAnchor((Node) this.particleSystem, 0d);
		getChildren().add((Node) this.particleSystem);

	}

	/**
	 * Returns the system loop. This loop ( {@link Timeline} ) is responsible for
	 * updating and drawing the particle system every frame.
	 * 
	 * @return the system loop
	 */
	public Timeline getSystemLoop()
	{
		return systemLoop;
	}

	/**
	 * The particle system if this {@link FXParticlesView} class. The particle
	 * system can either be a {@link ParticleSystem2D} or a
	 * {@link ParticleSystem3D}.
	 * 
	 * @return the current particle system.
	 */
	public IBaseParticleSystem getParticleSystem()
	{
		return particleSystem;
	}

	private void startSystemLoop(double updateTime)
	{
		EventHandler<ActionEvent> gameUpdate = event ->
			{
				particleSystem.update();
				particleSystem.draw();
			};
		systemLoop = new Timeline(new KeyFrame(Duration.millis(updateTime), gameUpdate));
		systemLoop.setCycleCount(Animation.INDEFINITE);
		systemLoop.play();

	}

	/**
	 * Stops the system loop completely.
	 */
	public void stopSystemLoop()
	{
		systemLoop.stop();
	}

}
