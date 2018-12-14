package at.neonartworks.fxparticles.core2d.system.particle;

public class LifeTime
{

	private double lifeTime = 0;
	private double age = 0;

	public LifeTime(double lifeTIme)
	{
		this.lifeTime = lifeTIme;
	}

	public double getAge()
	{
		return age;
	}

	public void setAge(double life)
	{
		this.age = life;
	}

	public static LifeTime getRandom()
	{
		return new LifeTime((double) (Math.random() * 10));
	}

	public void incrementAge(double inc)
	{
		this.age += inc;
	}

	public double getLifeTime()
	{
		return lifeTime;
	}

	public void setLifeTime(double lifeTime)
	{
		this.lifeTime = lifeTime;
	}

	public boolean isDead()
	{
		if (age > lifeTime)
		{
			return true;
		}
		return false;
	}

	public boolean isAlive()
	{
		if (age <= lifeTime)
			return true;
		return false;
	}

	@Override
	public String toString()
	{
		return "LifeTime: lifeTime:" + lifeTime + ", age:" + age + "stillAlive:" + isAlive();
	}

}
