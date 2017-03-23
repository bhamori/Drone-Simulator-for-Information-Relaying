import java.awt.Transparency;

public class Rectangle 
{
	// The following instance variables define the
	// information needed to represent a Rectangle
	// Feel free to more instance variables if you think it will 
	// support your work... 
	
	private double xPosition;			// The X coordinate of this Rectangle
	private double yPosition;			// The Y coordinate of this Rectangle
	private double width;				// The width of this Rectangle
	private double height;				// The height of this Rectangle
	private double zPosition;
	private String colour = "SAND";	// The colour of this Rectangle
	
	
										// Permissable colours are:
										// BLACK, BLUE, CYAN, DARKGREY, GREY,
										// GREEN, LIGHTGREY, MAGENTA, ORANGE,
										// PINK, RED, WHITE, YELLOW, BROWN 


	public double getXPosition()
	{
		return xPosition;
	}

	public double getYPosition()
	{
		return yPosition;
	}

	public void setXPosition(double x)
	{
		this.xPosition = x;
	}

	public void setYPosition(double y)
	{
		this.yPosition = y;
	}

	public double getWidth()
	{
		return width;
	}

	public double getHeight()
	{
		return height;
	}

	// Sets the height of the rectangle
	public void setHeight(double h)
	{
		this.height = h;	
	}

	public String getColour()
	{
		return colour;
	}

	public double getZPosition()
	{
		return zPosition;
	}

	public Rectangle( double x, double y, double z, double w, double h )
	{
		xPosition = x;
		yPosition = y;
		zPosition = z;
		width = w;
		height = h;
	}	
}
