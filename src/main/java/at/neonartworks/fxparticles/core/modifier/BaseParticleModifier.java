package at.neonartworks.fxparticles.core.modifier;

import java.util.List;

import at.neonartworks.fxparticles.core.system.ParticleSystem;
import at.neonartworks.fxparticles.core.system.particle.BaseParticle;
import at.neonartworks.fxparticles.core.system.particle.Particle;
import at.neonartworks.fxparticles.util.Delta;
import at.neonartworks.fxparticles.util.Vec2D;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * This is the super class for all particle modifiers in the particle system. If
 * you want to create your own particle modifier extend this class.
 * 
 * There are two options on how to modify particles in the system. You can use
 * the {@link #modifyParticle(BaseParticle, ParticleSystem)} method. This method
 * allows you to modify a single particle at a time. This method is called for
 * all particles.
 * 
 * Or you can use the {@link #modifyParticles(List, ParticleSystem)} method.
 * This method gives you a {@link List} with all {@link Particle}s currently in
 * the system. This way you can add complex modifiers.
 * 
 * 
 * @author Florian Wagner
 *
 */
public abstract class BaseParticleModifier extends Rectangle implements IBaseParticleModifier
{
	private ObjectProperty<Vec2D> positionProperty;
	private DoubleProperty strengthProperty;
	private ObjectProperty<Paint> paintProperty;
	private DoubleProperty sizeProperty;

	/**
	 * Instantiates a new BaseParticleModifier.
	 * 
	 * @param x        the x coordinate of the modifier
	 * @param y        the y coordinate of the modifier
	 * @param strength the strength of the modifier
	 */
	public BaseParticleModifier(double x, double y, double strength)
	{
		positionProperty = new SimpleObjectProperty<>(new Vec2D(x, y));
		strengthProperty = new SimpleDoubleProperty(1);
		paintProperty = new SimpleObjectProperty<>(Color.WHITE);
		sizeProperty = new SimpleDoubleProperty(10);
		setStrength(strength);
		prepareHandler();
		setX(x);
		setY(y);
		setFill(paintProperty.get());
		setWidth(getSize());
		setHeight(getSize());
		setArcHeight(20d);
		setArcWidth(20d);
		Tooltip.install(this, new Tooltip(this.toString()));
	}

	private void prepareHandler()
	{
		final Delta dragDelta = new Delta();

		setOnMouseEntered(me ->
			{
				if (!me.isPrimaryButtonDown())
				{
					getScene().setCursor(Cursor.HAND);
				}
			});
		setOnMouseExited(me ->
			{
				if (!me.isPrimaryButtonDown())
				{
					getScene().setCursor(Cursor.DEFAULT);
				}
			});
		setOnMousePressed(me ->
			{
				if (me.isPrimaryButtonDown())
				{
					getScene().setCursor(Cursor.DEFAULT);
				}
				dragDelta.x = me.getX();
				dragDelta.y = me.getY();
				getScene().setCursor(Cursor.MOVE);
			});
		setOnMouseReleased(me ->
			{
				if (!me.isPrimaryButtonDown())
				{
					getScene().setCursor(Cursor.DEFAULT);
				}
			});
		setOnMouseDragged(me ->
			{
				setLayoutX(getLayoutX() + me.getX() - dragDelta.x);
				setLayoutY(getLayoutY() + me.getY() - dragDelta.y);
				setPosition(new Vec2D(me.getX() + getLayoutX(), me.getY() + getLayoutY()));
			});
	}

	/**
	 * Returns the color of the modifier.
	 * 
	 * @return the color
	 */
	public Paint getColor()
	{
		return getPaintProperty().get();
	}

	/**
	 * Sets the color of the modifier.
	 * 
	 * @param color the paint
	 */
	public void setColor(Paint color)
	{
		getPaintProperty().set(color);
		setFill(color);
	}

	/**
	 * Returns the size of the modifier.
	 * 
	 * @return the size
	 */
	public double getSize()
	{
		return getSizeProperty().get();
	}

	/**
	 * Sets the size of the modifier.
	 * 
	 * @param size the size
	 */
	public void setSize(double size)
	{
		getSizeProperty().set(size);
		setWidth(getSize());
		setHeight(getSize());
	}

	/**
	 * Sets the 2D position of the modifier.
	 * 
	 * @param position the position
	 */
	public void setPosition(Vec2D position)
	{
		getPositionProperty().set(position);

	}

	/**
	 * Returns the 2D position of the modifier.
	 * 
	 * @return the position
	 */
	public Vec2D getPosition()
	{
		return getPositionProperty().get();
	}

	/**
	 * Returns the x position of the modifier in the particle system. Note: do not
	 * use the setX and setY methods inherited from the {@link Node} class
	 * 
	 * @return the x position
	 */
	public double getPositionX()
	{

		return getPositionProperty().get().getX();
	}

	/**
	 * Returns the y position of the modifier in the particle system. Note: do not
	 * use the setX and setY methods inherited from the {@link Node} class
	 * 
	 * @return the y position
	 */
	public double getPositionY()
	{

		return getPositionProperty().get().getY();
	}

	/**
	 * Sets the x position of the modifier in the particle system.
	 * 
	 * @param x the x position
	 */
	public void setPositiontX(double x)
	{

		getPositionProperty().get().setX(x);

	}

	/**
	 * Sets the y position of the modifer in the particle system.
	 * 
	 * @param y the y position
	 */
	public void setPositionY(double y)
	{

		getPositionProperty().get().setY(y);

	}

	/**
	 * Sets the strength of the modifier. The strength value is not used internally.
	 * It should be used by the new modifier.
	 * 
	 * @param strength the strength
	 */
	public void setStrength(double strength)
	{
		getStrengthProperty().set(strength);

	}

	/**
	 * Returns the strength of the modifier
	 * 
	 * @return the strength
	 */
	public double getStrength()
	{
		return getStrengthProperty().get();
	}

	@Override
	public String toString()
	{
		return "ParticleModifier: position:" + getPosition() + ", strength:" + getStrength();
	}

	public ObjectProperty<Vec2D> getPositionProperty()
	{
		return positionProperty;
	}

	public void setPositionProperty(ObjectProperty<Vec2D> positionProperty)
	{
		this.positionProperty = positionProperty;
	}

	public DoubleProperty getStrengthProperty()
	{
		return strengthProperty;
	}

	public void setStrengthProperty(DoubleProperty strengthProperty)
	{
		this.strengthProperty = strengthProperty;
	}

	public ObjectProperty<Paint> getPaintProperty()
	{
		return paintProperty;
	}

	public void setPaintProperty(ObjectProperty<Paint> paintProperty)
	{
		this.paintProperty = paintProperty;
	}

	public DoubleProperty getSizeProperty()
	{
		return sizeProperty;
	}

	public void setSizeProperty(DoubleProperty sizeProperty)
	{
		this.sizeProperty = sizeProperty;
	}
}
