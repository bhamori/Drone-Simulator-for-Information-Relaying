/*Botond Hamori*/

import java.util.*;
import java.*;
import java.io.*;


public class missionCoords
{
	public ArrayList<Integer> xCoords = new ArrayList<Integer>();
	public ArrayList<Integer> yCoords = new ArrayList<Integer>();
	public ArrayList<Integer> zCoords = new ArrayList<Integer>();
	public Boolean applicable = false;
	
	int xVal;
	int yVal;
	int zVal;
	
	public missionCoords()
	{
		try
		{
			Scanner x = new Scanner(new FileReader("xCoords.txt"));
			Scanner y = new Scanner(new FileReader("yCoords.txt"));
			Scanner z = new Scanner(new FileReader("zCoords.txt"));
			
			//read x-coords
			while(x.hasNextInt())
			{
				xVal = x.nextInt();
				if(xVal < 50)
				{
					xVal = 50;
				}
				else if(xVal > 950)
				{
					xVal = 950;
				}
				xCoords.add(xVal);
			}
			x.close();
			System.out.println("X-coordinates: " + xCoords);
			
			//read y-coords
			while(y.hasNextInt())
			{
				yVal = y.nextInt();
				if(xVal < 50)
				{
					xVal = 50;
				}
				else if(yVal > 950)
				{
					yVal = 950;
				}
				yCoords.add(yVal);
			}
			y.close();
			System.out.println("Y-coordinates: " + yCoords);


			//read z-coords
			while(z.hasNextInt())
			{
				zVal = z.nextInt();
				if(zVal < 0)
				{
					zVal = 0;
				}
				zCoords.add(zVal);
			}
			z.close();
			System.out.println("Z-coordinates: " + zCoords);
			
			//compare the sizes of inputs to make sure they match
			//if so, set applicable to true, else print warning
			if(xCoords.size() == yCoords.size() && xCoords.size() == zCoords.size() && xCoords != null)
			{
				applicable = true;
				System.out.println("You have successfully defined " + xCoords.size() + " points for the mission.");
			}
			else
			{
				System.out.println("Please revise your input coordinates. You have defined " + xCoords.size() + " x-coordinates, " + yCoords.size() + " y-coordinates, and " + zCoords.size() + " z-coordinates.");
			}
			
			
		}
		catch(IOException e)
		{
			System.out.println("An error has occured.");
		}
	}
	
	public int getXCoord(int x)
	{
		return xCoords.get(x);
	}
	
	public int getYCoord(int y)
	{
		return yCoords.get(y);
	}	
	
	public int getZCoord(int z)
	{
		return zCoords.get(z);
	}
	
	public boolean getConfirmation()
	{
		return applicable;
	}
	
	public int getLength()
	{
		return xCoords.size();
	}	
}