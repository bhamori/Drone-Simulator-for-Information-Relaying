//Botond Hamori

import java.*;

public class Transmitter
{
	//basic appearance on the playground
	public double xPosition;
	public double yPosition;
	public int baseSize;
	public double zPosition;
	public String baseColour = "GRAY";
	
	public Transmitter(double x, double y, double z, int diameter)
	{
		xPosition = x;
		yPosition = y;
		zPosition = z;
		baseSize = diameter;
		baseColour = "GRAY";
	}
	
	public double getXPosition()
	{
		return xPosition;
	}
	
	public double getYPosition()
	{
		return yPosition;
	}
	
	public int getSize()
	{
		return baseSize;
	}
	
	public String getColour()
	{
		return baseColour;
	}
	
	public double getZPosition()
	{
		return zPosition;
	}
}