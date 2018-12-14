package at.neonartworks.fxparticles.base;

import java.util.List;

import at.neonartworks.fxparticles.core2d.system.ParticleSystem2D;
import at.neonartworks.fxparticles.core2d.system.particle.Particle2D;
import at.neonartworks.fxparticles.math.Delta;
import at.neonartworks.fxparticles.math.Vec2D;
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
 * the {@link #modifyParticle(BaseParticle, ParticleSystem2D)} method. This
 * method allows you to modify a single particle at a time. This method is
 * called for all particles.
 * 
 * Or you can use the {@link #modifyParticles(List, ParticleSystem2D)} method.
 * This method gives you a {@link List} with all {@link Particle2D}s currently in
 * the system. This way you can add complex modifiers.
 * 
 * 
 * @author Florian Wagner
 *
 */
public abstract class BaseParticleModifier extends Rectangle implements IBaseParticleModifier
{
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
		strengthProperty = new SimpleDoubleProperty(1);
		paintProperty = new SimpleObjectProperty<>(Color.WHITE);
		sizeProperty = new SimpleDoubleProperty(10);
		setStrength(strength);
		prepareHandler();
		setLayoutX(x);
		setLayoutY(y);
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
				// setPosition(new Vec2D(me.getX() + getLayoutX(), me.getY() + getLayoutY()));
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
		setLayoutX(position.getX());
		setLayoutY(position.getY());
	}

	/**
	 * Returns the 2D position of the modifier.
	 * 
	 * @return the position
	 */
	public Vec2D getPosition2D()
	{
		return new Vec2D(getLayoutX(), getLayoutY());
	}

	/**
	 * Returns the x position of the modifier in the particle system. Note: do not
	 * use the setX and setY methods inherited from the {@link Node} class
	 * 
	 * @return the x position
	 */
	public double getPositionX()
	{

		return getLayoutX();
	}

	/**
	 * Returns the y position of the modifier in the particle system. Note: do not
	 * use the setX and setY methods inherited from the {@link Node} class
	 * 
	 * @return the y position
	 */
	public double getPositionY()
	{

		return getLayoutY();
	}

	/**
	 * Sets the x position of the modifier in the particle system.
	 * 
	 * @param x the x position
	 */
	public void setPositiontX(double x)
	{

		setLayoutX(x);

	}

	/**
	 * Sets the y position of the modifer in the particle system.
	 * 
	 * @param y the y position
	 */
	public void setPositionY(double y)
	{

		setLayoutY(y);

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
		return "ParticleModifier: position:" + getPosition2D() + ", strength:" + getStrength();
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
