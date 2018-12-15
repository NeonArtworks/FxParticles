package fxparticles.test;

import at.neonartworks.fxparticles.FXParticlesView;
import at.neonartworks.fxparticles.RenderMode;
import at.neonartworks.fxparticles.core3d.emitter.ParticleEmitter3D;
import at.neonartworks.fxparticles.core3d.system.ParticleSystem3D;
import at.neonartworks.fxparticles.core3d.system.particle.Particle3D;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class FXParticles3DDemo extends Application
{

	private final double width = 800;
	private final double height = 600;
	private boolean toggled = true;

	@Override
	public void start(Stage primaryStage)
	{

		FXParticlesView parSysView = new FXParticlesView(width, height, 33.3, RenderMode._3D);
		ParticleSystem3D parSys = (ParticleSystem3D) parSysView.getParticleSystem();
		parSys.setMaxParticles(100000);
		parSys.addParticleEmitter(new ParticleEmitter3D(width/2, height/2, 10));
		
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