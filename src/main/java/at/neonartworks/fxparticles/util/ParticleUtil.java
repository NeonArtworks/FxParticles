package at.neonartworks.fxparticles.util;

public class ParticleUtil
{
	public static double map(long l, long in_min, long m, double out_min, double out_max)
	{
		return (l - in_min) * (out_max - out_min) / (m - in_min) + out_min;
	}

	public static double constrain(double getal, double min, double max)
	{
		if (getal > max)
		{
			return max;
		} else if (getal < min)
		{
			return min;
		} else
		{
			return getal;
		}
	}

	public static double map(double magnitude, double d, double e, double out_min, double out_max)
	{
		return (magnitude - d) * (out_max - out_min) / (e - d) + out_min;
		
		
	}
	
	
	
}
