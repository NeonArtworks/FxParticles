package at.neonartworks.fxparticles.math;

public class Vec2D
{

	private double x;
	private double y;

	public Vec2D(double x, double y)
	{
		setX(x);
		setY(y);
	}

	public Vec2D(Vec2D vector)
	{
		setX(vector.getX());
		setY(vector.getY());
	}

	public static Vec2D add(Vec2D v1, Vec2D v2)
	{
		return new Vec2D(v1.getX() + v2.getX(), v1.getY() + v2.getY());
	}

	public static Vec2D subtract(Vec2D v1, Vec2D v2)
	{
		return new Vec2D(v1.getX() - v2.getX(), v1.getY() - v2.getY());
	}

	public double getMagnitude()
	{
		return (x * x + y * y);
	}

	public double distance(Vec2D v2)
	{
		return Math.sqrt((v2.getY() - getY()) * (v2.getY() - getY()) + (v2.getX() - getX()) * (v2.getX() - getX()));
	}

	public static double distance(Vec2D v1, Vec2D v2)
	{
		return Math.sqrt(
				(v2.getY() - v1.getY()) * (v2.getY() - v1.getY()) + (v2.getX() - v1.getX()) * (v2.getX() - v1.getX()));
	}

	public static Vec2D clamp(Vec2D vec, double xmax, double ymax)
	{
		if (vec.getX() > xmax)
			vec.setX(xmax);
		if (vec.getY() > ymax)
			vec.setY(ymax);
		return vec;
	}

	public void setMagnitude(double magnitude)
	{
		double tmpMag = Math.sqrt((x * x + y * y));

		setX(x * magnitude / tmpMag);
		setY(y * magnitude / tmpMag);
	}

	public void atBounds()
	{
		x = (-x * 0.9d);
		y = (-y * 0.9d);

	}

	public void add(Vec2D vec)
	{
		setX(x + vec.getX());
		setY(y + vec.getY());
	}

	public void sub(Vec2D vec)
	{
		this.x -= vec.getX();
		this.y -= vec.getY();
	}

	public double getX()
	{
		return x;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	public void setXY(double x, double y)
	{
		setX(x);
		setY(y);
	}

	@Override
	public String toString()
	{
		return "Vec2D: x:" + x + ", y:" + y;
	}

}
