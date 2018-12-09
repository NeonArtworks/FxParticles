package at.neonartworks.fxparticles.core.emitter;

import at.neonartworks.fxparticles.core.system.ParticleSystem;
import at.neonartworks.fxparticles.util.Delta;
import at.neonartworks.fxparticles.util.Vec2D;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BaseParticleEmitter extends Rectangle implements IBaseEmitter
{

	private Vec2D position;
	private long amountToEmit = 100;
	private double size = 10;
	private Color color = Color.GREEN;

	public BaseParticleEmitter(double x, double y, long pAmount)
	{
		setPosition(new Vec2D(x, y));
		setAmountToEmit(pAmount);
		prepareHandler();
		setX(x);
		setY(y);
		setFill(color);
		setWidth(getSize());
		setHeight(getSize());
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

	public long getAmountToEmit()
	{
		return amountToEmit;
	}

	public void setAmountToEmit(long amountToEmit)
	{
		this.amountToEmit = amountToEmit;
	}

	public Vec2D getPosition()
	{
		return position;
	}

	public void setPosition(Vec2D pos)
	{
		this.position = pos;

	}

	public void setPositionX(double x)
	{
		this.position.setX(x);

	}

	public void setPositionY(double y)
	{
		this.position.setY(y);

	}

	public double getPositionX()
	{
		return this.position.getX();
	}

	public double getPositionY()
	{
		return this.position.getY();
	}

	@Override
	public void emit(ParticleSystem particleSystem)
	{

	}

	@Override
	public String toString()
	{
		return "BaseParticleEmitter: position:" + position + ", amountToEmit:" + amountToEmit + ", size:" + size
				+ ", color:" + color;
	}

}
