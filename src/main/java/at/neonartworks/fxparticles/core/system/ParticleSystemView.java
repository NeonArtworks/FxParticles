package at.neonartworks.fxparticles.core.system;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ParticleSystemView extends AnchorPane
{

	private Timeline systemLoop;
	private final ParticleSystem particleSystem;
	
	public ParticleSystemView(double width, double height, double updateTime)
	{
		this.particleSystem = new ParticleSystem(this);
		this.particleSystem.setWidth(width);
		this.particleSystem.setHeight(height);
		this.getChildren().add(particleSystem);

		particleSystem.widthProperty().bind(widthProperty());
		particleSystem.heightProperty().bind(heightProperty());
		startSystemLoop(updateTime);
		
	}
	
	public Timeline getSystemLoop()
	{
		return systemLoop;
	}

	public void setSystemLoop(Timeline systemLoop)
	{
		this.systemLoop = systemLoop;
	}

	public ParticleSystem getParticleSystem()
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

	public void stopSystemLoop()
	{
		systemLoop.stop();
	}

}
