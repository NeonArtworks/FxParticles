package at.neonartworks.fxparticles.core.system;

public class LifeTime
{

	private long lifeTime = 0;
	private long age = 0;

	public LifeTime(long lifeTIme)
	{
		this.lifeTime = lifeTIme;
	}

	public long getAge()
	{
		return age;
	}

	public void setAge(long life)
	{
		this.age = life;
	}

	public static LifeTime getRandom()
	{
		return new LifeTime((long) (Math.random()*10));
	}

	public void incrementAge(double inc)
	{
		this.age += inc;
	}

	public long getLifeTime()
	{
		return lifeTime;
	}

	public void setLifeTime(long lifeTime)
	{
		this.lifeTime = lifeTime;
	}

	public boolean isDead()
	{
		if (age >= lifeTime)
		{
			return true;
		}
		return false;
	}

	public boolean isAlive()
	{
		if (age < lifeTime)
			return true;
		return false;
	}

	@Override
	public String toString()
	{
		return "LifeTime: lifeTime:" + lifeTime + ", age:" + age + "stillAlive:" + isAlive();
	}

}
