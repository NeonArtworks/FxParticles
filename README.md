# FxParticles
JavaFX particle system

## Example

```java
public class FXParticlesDemo extends Application
{

	private final double width = 800;
	private final double height = 600;
	private boolean toggled = true;

	@Override
	public void start(Stage primaryStage)
	{
		//create a 2D particle system view
		FXParticlesView parSysView = new FXParticlesView(width, height, 33.3, RenderMode._2D);
    
		ParticleSystem2D parSys = (ParticleSystem2D) parSysView.getParticleSystem(); //get the particle system
		parSys.setParticlesAging(true); //default is true
    
		//adding one emitter and different kinds of modifiers to the system
		parSys.addParticleEmitter(new ParticleEmitter2D(width / 2, height / 2, 20));
		parSys.addParticleModifier(new ColorModifier2D(width / 3, height / 2, 0));
		parSys.addParticleModifier(new AttractorModifier2D(width / 3, height / 2, 10));
		parSys.addParticleModifier(new DeflectorModifier2D(width / 3, height / 2, 5));

		parSys.setMaxParticles(30000); //maximum particles

		Scene scene = new Scene(parSysView, width, height);

		primaryStage.setTitle("FXParticlesDemo");
		primaryStage.setScene(scene);

		primaryStage.show();

		primaryStage.setOnCloseRequest(e ->
			{
				parSysView.stopSystemLoop();
			});
    
		//this is for pausing and resuming the simulation
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
