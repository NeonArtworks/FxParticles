package fxparticles.test;

import at.neonartworks.fxparticles.FXParticlesView;
import at.neonartworks.fxparticles.RenderMode;
import at.neonartworks.fxparticles.core2d.emitter.ParticleEmitter;
import at.neonartworks.fxparticles.core2d.modifier.AttractorModifier;
import at.neonartworks.fxparticles.core2d.modifier.ColorModifier;
import at.neonartworks.fxparticles.core2d.modifier.DeflectorModifier;
import at.neonartworks.fxparticles.core2d.system.ParticleSystem2D;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class FXParticlesDemo extends Application
{

	private final double width = 800;
	private final double height = 600;
	private boolean toggled = true;

	@Override
	public void start(Stage primaryStage)
	{

		FXParticlesView parSysView = new FXParticlesView(width, height, 33.3, RenderMode._2D);
		ParticleSystem2D parSys = (ParticleSystem2D) parSysView.getParticleSystem();
		parSys.setParticlesAging(true);
		parSys.addParticleEmitter(new ParticleEmitter(width / 2, height / 2, 20));
		parSys.addParticleModifier(new ColorModifier(width / 3, height / 2, 0));
		parSys.addParticleModifier(new AttractorModifier(width / 3, height / 2, 10));
		parSys.addParticleModifier(new DeflectorModifier(width / 3, height / 2, 5));

		parSys.setMaxParticles(30000);

		Scene scene = new Scene(parSysView, width, height);

		primaryStage.setTitle("FXParticlesDemo");
		primaryStage.setScene(scene);

		primaryStage.show();

		primaryStage.setOnCloseRequest(e ->
			{
				parSysView.stopSystemLoop();
			});

		scene.setOnKeyPressed(event ->
			{
				if (event.getCode().equals(KeyCode.SPACE))
				{
					if (toggled)

						parSysView.getSystemLoop().pause();

					else

						parSysView.getSystemLoop().play();
					toggled = !toggled;

				}

			});

	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{
		launch(args);
	}

}