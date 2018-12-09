package fxparticles.test;

import at.neonartworks.fxparticles.core.emitter.ParticleEmitter;
import at.neonartworks.fxparticles.core.system.ParticleSystem;
import at.neonartworks.fxparticles.core.system.ParticleSystemView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXParticlesDemo extends Application
{

	private final double width = 800;
	private final double height = 600;

	@Override
	public void start(Stage primaryStage)
	{

		ParticleSystemView root = new ParticleSystemView(width, height, 33.3);
		ParticleSystem parSys = root.getParticleSystem();
		parSys.setParticlesAging(true);
		// parSys.addParticleEmitter(new SmokeEmitter(width / 2, height / 2, 100,
		// Color.DARKGRAY));
		// parSys.addParticleEmitter(new SnowEmitter(width / 2, height / 2, 100));
		parSys.addParticleEmitter(new ParticleEmitter(width / 2, height / 2, 200));
		// parSys.addParticleModifier(new AttractorModifier(width/3, height/3, 9));
		parSys.setMaxParticles(3000);

		Scene scene = new Scene(root, width, height);

		primaryStage.setTitle("FXParticlesDemo");
		primaryStage.setScene(scene);

		primaryStage.show();

		primaryStage.setOnCloseRequest(e ->
			{
				root.stopSystemLoop();
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