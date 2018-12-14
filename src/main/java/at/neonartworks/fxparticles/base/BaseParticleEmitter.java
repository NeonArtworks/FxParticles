package at.neonartworks.fxparticles.base;

import at.neonartworks.fxparticles.math.Delta;
import at.neonartworks.fxparticles.math.Vec2D;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Cursor;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public abstract class BaseParticleEmitter extends Rectangle implements IBaseEmitter
{
	private LongProperty amountProperty;
	private DoubleProperty sizePropery;
	private ObjectProperty<Paint> paintProperty;

	public BaseParticleEmitter(double x, double y, long pAmount)
	{
		amountProperty = new SimpleLongProperty(10);
		sizePropery = new SimpleDoubleProperty(10);
		paintProperty = new SimpleObjectProperty<>(Color.GREEN);

		setPosition2D(new Vec2D(x, y));
		setAmountToEmit(pAmount);
		prepareHandler();
		setLayoutX(x);
		setLayoutY(y);

		setFill(paintProperty.get());
		setWidth(getSize());
		setHeight(getSize());
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
				// setPosition2D(new Vec2D(me.getX() + getLayoutX(), me.getY() + getLayoutY()));
			});
	}

	public Paint getColor()
	{
		return paintProperty.get();
	}

	public void setColor(Color color)
	{
		paintProperty.set(color);
		setFill(color);
	}

	public double getSize()
	{
		return sizePropery.get();
	}

	public void setSize(double size)
	{
		sizePropery.set(size);
		setWidth(getSize());
		setHeight(getSize());
	}

	public long getAmountToEmit()
	{
		return amountProperty.get();
	}

	public void setAmountToEmit(long amountToEmit)
	{
		amountProperty.set(amountToEmit);
	}

	public Vec2D getPosition2D()
	{
		return new Vec2D(getLayoutX(), getLayoutY());
	}

	public void setPosition2D(Vec2D pos)
	{
		setLayoutX(pos.getX());
		setLayoutY(pos.getY());
	}

	public void setPositionX(double x)
	{
		getPosition2D().setX(x);
		setLayoutX(x);

	}

	public void setPositionY(double y)
	{
		getPosition2D().setY(y);
		setLayoutY(y);

	}

	public double getPositionX()
	{
		return getPosition2D().getX();
	}

	public double getPositionY()
	{
		return getPosition2D().getY();
	}

	// public ObjectProperty<Vec2D> getPositionProperty()
	// {
	// return positionProperty;
	// }
	//
	// public void setPositionProperty(ObjectProperty<Vec2D> positionProperty)
	// {
	// this.positionProperty = positionProperty;
	// }

	public LongProperty getAmountProperty()
	{
		return amountProperty;
	}

	public void setAmountProperty(LongProperty amountProperty)
	{
		this.amountProperty = amountProperty;
	}

	public DoubleProperty getSizePropery()
	{
		return sizePropery;
	}

	public void setSizePropery(DoubleProperty sizePropery)
	{
		this.sizePropery = sizePropery;
	}

	public ObjectProperty<Paint> getPaintProperty()
	{
		return paintProperty;
	}

	public void setPaintProperty(ObjectProperty<Paint> paintProperty)
	{
		this.paintProperty = paintProperty;
	}

	@Override
	public String toString()
	{
		return "BaseParticleEmitter: position:" + getPosition2D() + ", amountToEmit:" + getAmountToEmit() + ", size:"
				+ getSize() + ", color:" + getColor();
	}

}
