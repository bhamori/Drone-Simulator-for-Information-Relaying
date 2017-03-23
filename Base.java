//Botond Hamori

import java.*;

public class Base
{
	public double xPosition;
	public double yPosition;
	public int baseSize;
	public double zPosition;
	public String baseColour = "GRAY";
	
	public Base( double x, double y, double z, int diameter )
	{
		xPosition = x;
		yPosition = y;
		baseSize = diameter;
		baseColour = "GRAY";
		zPosition = z;
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