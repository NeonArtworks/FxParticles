package at.neonartworks.fxparticles.core.modifier;

import at.neonartworks.fxparticles.core.system.ParticleSystem;
import at.neonartworks.fxparticles.core.system.particle.BaseParticle;
import at.neonartworks.fxparticles.util.Delta;
import at.neonartworks.fxparticles.util.Vec2D;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BaseParticleModifier extends Rectangle implements IBaseParticleModifier
{
	private Vec2D position;
	private double strength;
	private Color color = Color.WHITE;
	private double size = 10;

	public BaseParticleModifier(double x, double y, double strength)
	{
		position = new Vec2D(x, y);
		setStrength(strength);
		prepareHandler();
		setX(x);
		setY(y);
		setFill(color);
		setWidth(getSize());
		setHeight(getSize());
		setArcHeight(20d);
		setArcWidth(20d);
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

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
		setFill(color);
	}

	public double getSize()
	{
		return size;
	}

	public void setSize(double size)
	{
		this.size = size;
		setWidth(getSize());
		setHeight(getSize());
	}

	public void setPosition(Vec2D position)
	{
		this.position = position;

	}

	public Vec2D getPosition()
	{
		return position;
	}

	public double getPositionX()
	{

		return position.getX();
	}

	public double getPositionY()
	{

		return position.getY();
	}

	public void sePositiontX(double x)
	{

		position.setX(x);

	}

	public void setPositionY(double y)
	{

		position.setY(y);

	}

	public void setStrength(double strength)
	{
		this.strength = strength;

	}

	public double getStrength()
	{
		return this.strength;
	}

	@Override
	public String toString()
	{
		return "ParticleModifier: position:" + position + ", strength:" + strength;
	}

	@Override
	public void modifyParticle(BaseParticle particle, ParticleSystem system)
	{

	}
}
