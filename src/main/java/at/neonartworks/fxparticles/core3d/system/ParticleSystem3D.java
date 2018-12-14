package at.neonartworks.fxparticles.core3d.system;

import at.neonartworks.fxparticles.IParticleSystem;
import at.neonartworks.fxparticles.base.BaseParticleEmitter;
import at.neonartworks.fxparticles.base.BaseParticleModifier;
import at.neonartworks.fxparticles.core3d.system.particle.Particle3D;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;

public class ParticleSystem3D extends AnchorPane implements IParticleSystem
{	
	private ObservableList<Particle3D> particles;
	private ObservableList<BaseParticleEmitter> particleEmitter;
	private ObservableList<BaseParticleModifier> modifierList;
	
	 public ParticleSystem3D()
	{
		 particles = FXCollections.observableArrayList();
		 particleEmitter = FXCollections.observableArrayList();
		 modifierList = FXCollections.observableArrayList();
	}
	
	@Override
	public void update()
	{

	}

	@Override
	public void draw()
	{

	}

}
