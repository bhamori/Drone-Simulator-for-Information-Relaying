//Botond Hamori
import java.*;

public class dot
{
	
	
	int size;
	String colour;
	double dX, dY;
	int objectDot;
	
	public dot(double dotX, double dotY, int dotLayer, int dotSize, String dotColour)
	{
		dX = dotX;
		dY = dotY;
		objectDot = dotLayer;
		size = dotSize;
		colour = dotColour;
	}
	
	public double getDotX()
	{
		return dX;
	}
	
	public double getDotY()
	{
		return dY;
	}
	
	public int getDotLayer()
	{
		return objectDot;
	}
	
	public double getSize()
	{
		return size;
	}
	
	public String getColour()
	{
		return colour;
	}
	
	public void setDotX(double x)
	{
		this.dX = x;
	}
	
	public void setDotY(double y)
	{
		this.dY = y;
	}
	
/*	public void draw()
	{
		this.setDotX(getPathX());
		this.setDotY(getPathY());		
	}
	
	public void update()
	{
		this.draw();
	}*/
}