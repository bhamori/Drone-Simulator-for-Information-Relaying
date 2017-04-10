/*Botond Hamori*/

import java.util.*;
import java.*;


/* This class creates the swarm.
 * Coordinates, movement, and anything related to a single drone is handled here.
 * The class Playground uses this class to display the swarm, however the position of each drone
 * is defined in the Playground class.
 */
public class Swarm
{
	int i = 0;
	int j =0;

	int leaderDrone;
	public ArrayList<Integer> droneList = new ArrayList<Integer>();
	
	String dotColour = "BLUE";
	int dotSize = 10;
	
	//arrays of coordinates
	ArrayList<Integer> listOfXCoordinates = new ArrayList<Integer>();
	ArrayList<Integer> listOfYCoordinates = new ArrayList<Integer>();
	ArrayList<Integer> listOfZCoordinates = new ArrayList<Integer>();
	
	String[] points = new String[] {"A", "B", "C", "D", "E", "transmitter", "F", "G", "H", "I", "J", "receiver"};
	
	public int objectLayer;
	
	//current coordinates
	public double xPosition;
	public double yPosition;
	public double zPosition;
	
	//target coordinates
	double pathX;
	double pathY;
	double pathZ;
	
	//move based on these coordinates
	double moveX;
	double moveY;
	double moveZ;
	
	//differneces in X and Y between current and target position
	public double diffX;
	public double diffY;
	public double diffZ;
	public boolean xNeg = false;
	public boolean yNeg = false;
	public boolean zNeg = false;
	
	boolean reached = false;
	int numberOfDrones;
	int energy = 2500;
	int energyAtStart = energy;
	int id = 0;
	int percent;
	boolean isShot;
	boolean defPath = true;
	
	//an individual drone's coords
	public double[] droneX = new double[30];
	public double[] droneY = new double[30];
	public double[] droneZ = new double[30];
	
	//variables used for communication
	ArrayList<Integer> fullData = new ArrayList<Integer>();
	
	ArrayList receivedData = new ArrayList();
	
	
	ArrayList<ArrayList<Integer>> dataStored = new ArrayList<ArrayList<Integer>>();
	
	int numberOfPackets = 150;

	//create instances
	Drone singleDrone = new Drone(true, energy, 10, id); //first drone (leader)
	Information information = new Information(numberOfPackets, droneList.size());
	missionCoords mc = new missionCoords();
	
	//set targets
	public void targets()
	{
		if(mc.getConfirmation() == true)
		{
			defPath = false;
			for(int i = 0; i < mc.getLength(); i++)
			{
				listOfXCoordinates.add(mc.getXCoord(i));
				listOfYCoordinates.add(mc.getYCoord(i));
				listOfZCoordinates.add(mc.getZCoord(i));
			}
		}
		else
		{
			listOfXCoordinates.addAll(Arrays.asList(120, 190, 260, 330, 400, 500, 600, 670, 740, 810, 880, 950));
			listOfYCoordinates.addAll(Arrays.asList(730, 710, 490, 470, 250, 50, 250, 470, 490, 710, 730, 950));
			listOfZCoordinates.addAll(Arrays.asList(25, 30, 35, 35, 35, 35, 35, 35, 35, 30, 25, 20));
		}
	}
	
	
	//getters
	public double getXPosition()
	{
		return xPosition;
	}
	
	public double getYPosition()
	{
		return yPosition;
	}

	public double getZPosition()
	{
		return zPosition;
	}
	
	public double getSize()
	{
		return singleDrone.droneSize;
	}
	
	public String getColour()
	{
		return singleDrone.droneColour;
	}
	
	public int getLayer()
	{
		return objectLayer;
	}
	
	public double getPathX()
	{
		return pathX;
	}
	
	public double getPathY()
	{
		return pathY;
	}
	
	public double getPathZ()
	{
		return pathZ;
	}
	
	public int getNumberOfDrones()
	{
		return numberOfDrones;
	}
	
	public String getDotColour()
	{
		return dotColour;
	}
	
	public int getDotSize()
	{
		return dotSize;
	}
	
	public double getDroneX(int i)
	{
		return droneX[i];
	}
	
	public double getDroneY(int i)
	{
		return droneY[i];
	}

	public double getDroneZ(int i)
	{
		return droneZ[i];
	}
	
	public int getSingleDrone(int i)
	{
		return (int)droneList.get(i);
	}
	
	public int getArraySize()
	{
		return droneList.size();
	}
	
	public boolean getShot()
	{
		return isShot;
	}
	
	
	//setters
	public void setColour(int i, String colour)
	{
		droneList.get(i);
		singleDrone.setDroneColour(colour);
	}
	
	public void setXPosition(double x)
	{
		this.xPosition = x;
	}
	
	public void setYPosition(double y)
	{
		this.yPosition = y;
	}

	public void setZPosition(double z)
	{
		this.zPosition = z;
	}	
	
	public void setEnergy(int energyLevel)
	{
		this.energy = energyLevel;
	}

	public void setPathX(int X)
	{
		this.pathX = X;
	}
	
	public void setPathY(int Y)
	{
		this.pathY = Y;
	}

	public void setPathZ(int Z)
	{
		this.pathZ = Z;
	}	
	
	public void setDroneX(double X, int i)
	{
		this.droneX[i] = X;
	}
	
	public void setDroneY(double Y, int i)
	{
		this.droneY[i] = Y;
	}

	public void setDroneZ(double Z, int i)
	{
		this.droneZ[i] = Z;
	}
	
	public void setShot(boolean shot)
	{
		this.isShot = shot;
	}
	
	public void removeDrone(int i)
	{
		if(i == droneList.get(0))
		{
			System.out.println("Leader's down. New leader: " + droneList.get(1));
		}
		else
		{
			System.out.println("Drone with the ID " + droneList.get(i) + " has been eliminated.");
		}	
		droneList.remove(i);
		if(dataStored.size() != 0)
		{
			dataStored.remove(i);
		}
	}
	
	//create a list from the received list of packets from information
	//assign info to each drone
	//remove a number of packets from each data carried by a single drone
	//the range of the removed number of packets remains the same, position shifts each time the loop iterates
	public void createPackets()
	{
		System.out.println("Whole message created.");
		System.out.println("Formatting message...");
		fullData = information.getWholeInfo();
		int x = numberOfPackets;
		int y = droneList.size();
		int div = (int)(((double)y/(double)x)*100);
		int nextIndex = 0;
		for(i = 0; i < droneList.size(); i++)
		{
			ArrayList tempData = new ArrayList();
			for(int j = 0; j < fullData.size(); j++)
			{
				tempData.add(fullData.get(j));
			}
			//format message
			for(int k = 0; k < div; k++)
			{
				tempData.remove(nextIndex);
			}
			dataStored.add(tempData);
			System.out.println("Drone " + droneList.get(i) + " holds packets: " + tempData);
			nextIndex += (int)(100/y);
		}
		
	}
	
	public void recreateInformation()
	{
		ArrayList tempList = new ArrayList();
		for(int i = 0; i < droneList.size(); i++) 
		{
			tempList = dataStored.get(i);
			for(int j = 0; j < tempList.size(); j++) 
			{
				if(receivedData.contains(tempList.get(j)) == false)
				{
					receivedData.add(j, tempList.get(j));
				}
			}
		}

		System.out.println("Reconstructed information: " + receivedData);
		
		fullData = information.getWholeInfo();
		if(fullData.equals(receivedData) == true)
		{
			System.out.println("Mission passed. All packets have been delivered.");
		}
		else
		{
			fullData.removeAll(receivedData);
			System.out.println("Mission failed. Missing packets: " + fullData);
		}	
	}
	
	//create swarm
	public Swarm(double x, double y, double z, int drones)
	{
		this.targets();
		xPosition = x;
		yPosition = y;
		zPosition = z;
		pathX = listOfXCoordinates.get(j);
		pathY = listOfYCoordinates.get(j);
		pathZ = listOfZCoordinates.get(j);
		numberOfDrones = drones - 1;
		

		
		singleDrone.setDroneID(id); //set drone ID
		droneList.add(id);		//add first drone to array
		System.out.println(singleDrone.getDroneID());	

		
		for (id = 1; id < drones; id++)
		{
			new Drone(true, energy, 10, id);
			droneList.add(id);
			singleDrone.setDroneID(id);
			System.out.println(singleDrone.getDroneID());
			
			droneX[i] = this.getDroneX(i);
			droneY[i] = this.getDroneY(i);
			droneZ[i] = this.getDroneZ(i);
		}
		
		int leaderDrone = (int)droneList.get(0); //Assign a leader drone based on the ID.
		
		System.out.println("The drones are all set, swarm is created. Now tracking energy level and destination points.");
		if(defPath == true)
		{
			System.out.println("Heading to " + points[j] + ".");
		}
	}
	
	
	//movement
	public void move()
	{
		dot d = new dot(this.getPathX(), this.getPathY(), 15, 7, dotColour);
		
		diffX = this.getPathX() - this.getXPosition(); 
		diffY = this.getPathY() - this.getYPosition();
		diffZ = this.getPathZ() - this.getZPosition();
		
		if(reached == false) //reached?
		{
			//This set of coordinate definitions used for movement is based on the code in the EnemyUnit class by James Caldwell
			if(diffX != 0 || diffY != 0 || diffY != 0)
			{
				if(diffX < 0)
				{
					xNeg = true;
					diffX = -diffX;
				}
				if(diffY < 0)
				{
					yNeg = true;
					diffY = -diffY;
				}
				if(diffZ < 0)
				{
					zNeg = true;
					diffZ = -diffZ;
				}
				
				
				if(diffX >= diffY && diffX >= diffZ)
				{
					moveX = 1;
					moveY = diffY/diffX;
					moveZ = diffZ/diffX;
				}
				else if(diffX <= diffY && diffY >= diffZ)
				{
					moveX = diffX/diffY;
					moveY = 1;
					moveZ = diffZ/diffY;
				}
				else if(diffX <= diffZ && diffY <= diffZ)
				{
					moveX = diffX/diffZ;
					moveY = diffY/diffZ;
					moveZ = 1;
				}
				
				if(xNeg == true)
				{
					moveX = -moveX;
					xNeg = false;
				}
				if(yNeg == true)
				{
					moveY = -moveY;
					yNeg = false;
				}
				if(zNeg == true)
				{
					moveZ = -moveZ;
					zNeg = false;
				}
				
				this.setXPosition(this.getXPosition() + moveX);
				this.setYPosition(this.getYPosition() + moveY);
				this.setZPosition(this.getZPosition() + moveZ);
				
				this.setEnergy(energy - 1);
				singleDrone.setEnergyLevel(energy - 1);
				percent = (singleDrone.getEnergyLevel()*100)/2500;				
			}
			else if (j < listOfXCoordinates.size() - 1)
			{
				j = j + 1;
				this.setPathX(listOfXCoordinates.get(j));
				this.setPathY(listOfYCoordinates.get(j));
				this.setPathZ(listOfZCoordinates.get(j));
				new dot(this.getPathX(), this.getPathY(), 15, 7, "BLUE");
				
				if(defPath == true)
				{
					System.out.println("Heading to " + points[j] + ", remaining energy:" + percent + "%");
				}
				else
				{
					System.out.println("Remaining energy:" + percent + "%");
				}
				if(this.getXPosition() == 500 && this.getYPosition() == 50)
				{
					System.out.println("Reached the transmitter.");
					information.update();
					this.createPackets();
					System.out.println("Packets have been picked up.");
				}
			}
			else if(this.getXPosition() == 950 && this.getYPosition() == 950)
			{
				reached = true;
				this.recreateInformation();
				System.out.println("Remaining drones: " + droneList.size());
				System.out.println("Remaining energy:" + percent + "%");
			}
			else
			{
				reached = true;
				System.out.println("There are no more target locations left, however the drones have not arrived at the receiver.");
			}
		}
		else if(singleDrone.getEnergyLevel() <= 0)
		{
			droneList.clear();
			System.out.println("The drones in the swarm have no energy left." );
		}
	}
	
	public void update()
	{
		this.move();	
	}
}