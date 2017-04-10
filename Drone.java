//Botond Hamori

import java.*;

/* This class creates a single drone and defines its characteristics, such as appearance
 * A drone has an assigned ID which is defined in this class but no value is assigned to the variable.
 */

public class Drone
{
	public boolean isOnline = true;
	public int energyLevel;
	public int droneSpeed;
	public boolean isShot = false;
	public int droneID;
	public String droneColour = "GREEN";
	public int droneSize = 5;
	public int numberOfDrones;
	
	
	public Drone(boolean alive, int energy, int speed, int id)
	{
		isOnline = alive;
		energyLevel = energy;
		droneSpeed = speed;
		droneID = id;
	}
	
	//getters
	public boolean getIsOnline()
	{
		return isOnline;
	}
	
	public int getEnergyLevel()
	{
		return energyLevel;
	}
	
	public int getDroneSpeed()
	{
		return droneSpeed;
	}
	
	public int getDroneID()
	{
		return droneID;
	}
	
	public int getSize()
	{
		return droneSize;
	}
	public String getDroneColour()
	{
		return droneColour;
	}
	
	//setters
	public void setIsOnline(boolean alive)
	{
		this.isOnline = alive;
	}
	
	public void setEnergyLevel(int energy)
	{
		this.energyLevel = energy;
	}
	
	public void setDroneColour(String colour)
	{
		this.droneColour = colour;
	}
	
	public void setSize(int size)
	{
		this.droneSize = size;
	}
	
	public void setDroneID(int ID)
	{
		this.droneID = ID;
	}
}