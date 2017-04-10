// James Caldwell
import java.*;
  
public class EnemyUnit
{
	// Variable used to determine how often the EnemyUnit object moves
    public float roundsPerMinute;
	public long rPMLong;
	
	// Sets all EnemyUnit objects to red
    public String unitColour = "RED";
      
	// Variables for object position
    public double xPosition;
    public double yPosition;
    public double zPosition;
      
	// Variables for EnemyUnit object, including size, number of checkpoints, checkpoint locations, path option and range
    public double unitSize;
    public int numberOfCheckpoints;
    public double checkpointLocations[][] = new double[5][3];
    public boolean loopedPath;
	public double enemyRange;
      
	// Variables for amount EnemyUnit object moves in each direction from current position
    public double moveX;
    public double moveY;
    public double moveZ;
      
	// Variable for direction of navigation between checkpoints
    public boolean forward;
	public int targetCheckpoint;
	public boolean zeroPath;
	
	// Variables used to calculate EnemyUnit objects shooting Drone objects
    public double hitPercentage;
    public int accuracy;
    public double xDiff;
    public double yDiff;
    public double zDiff;
    public boolean xNeg = false;
    public boolean yNeg = false;
    public boolean zNeg = false;
    public double rand;
    public int targetDrone;
	public boolean gotTarget;
	public boolean reloaded;
	
	// Variables uesd to calculate distance between objects
	public double distance;
	public boolean firstCheck;
	public double distanceA1;
	public double distanceB1;
	public double distanceA2;
	public double distanceB2;
	public double aXDist;
	public double aYDist;
	public double aZDist;
	public double bXDist;
	public double bYDist;
	public double bZDist;
	
	// Variables used when EnemyUnit object is reloading
    public long timeShot;
	public long timeDone;
	public boolean hit;
	public int shotsFired;
	public int dronesDowned;
	
	// Variable used to describe the speed that the EnemyUnit object moves
	public double speed;
	
	// EnemyUnit object constructor with 1 checkpoint
	public EnemyUnit(   double diameter, int cPNumber, int range, float rPM, boolean loop, double enemySpeed,
                        double aX, double aY, double aZ )
    {
		// Assigns constructor inputs to values in each object
        xPosition = aX;
        yPosition = aY;
        zPosition = aZ;
        unitSize = diameter;
        unitColour = "RED";
        numberOfCheckpoints = cPNumber;
        loopedPath = loop;
		forward = true;
		enemyRange = range;
		roundsPerMinute = ( 60 / rPM );
		speed = 2 / ( 10 / enemySpeed );

		// Variables that aren't declared in the constructor but need to be individual for each object
		distance = 0;
		gotTarget = false;
		reloaded = true;
		timeShot = 0;
		hit = false;
		zeroPath = true;
		targetCheckpoint = 0;
		rPMLong = 0;
		this.setRPMLong( roundsPerMinute );
		firstCheck = true;
		distanceA2 = 0;
		distanceB2 = 0;
        
		// Assigns values the checkpoint locations
        checkpointLocations[0][0] = aX;
        checkpointLocations[0][1] = aY;
        checkpointLocations[0][2] = aZ;
    }
	
	// EnemyUnit object constructor with 1 checkpoint
    public EnemyUnit(   double diameter, int cPNumber, int range, float rPM, boolean loop, double enemySpeed,
                        double aX, double aY, double aZ,
                        double bX, double bY, double bZ )
    {
		// Assigns constructor inputs to values in each object
        xPosition = aX;
        yPosition = aY;
        zPosition = aZ;
        unitSize = diameter;
        unitColour = "RED";
        numberOfCheckpoints = cPNumber;
        loopedPath = loop;
		forward = true;		
		enemyRange = range;
		roundsPerMinute = 60/rPM;
		speed = 2 / ( 10 / enemySpeed );

		// Variables that aren't declared in the constructor but need to be individual for each object
		distance = 0;
		gotTarget = false;
		reloaded = true;
		timeShot = 0;
		hit = false;
		zeroPath = false;
		targetCheckpoint = 1;
		rPMLong = 0;
		this.setRPMLong( roundsPerMinute );
		firstCheck = true;
		distanceA2 = 0;
		distanceB2 = 0;
        
		// Assigns values the checkpoint locations		
        checkpointLocations[0][0] = aX;
        checkpointLocations[0][1] = aY;
        checkpointLocations[0][2] = aZ;
          
        checkpointLocations[1][0] = bX;
        checkpointLocations[1][1] = bY;
        checkpointLocations[1][2] = bZ;
    }
    
	// EnemyUnit object constructor with 1 checkpoint	
    public EnemyUnit(   double diameter, int cPNumber, int range, float rPM, boolean loop, double enemySpeed,
                        double aX, double aY, double aZ,
                        double bX, double bY, double bZ,
                        double cX, double cY, double cZ )
    {
		// Assigns constructor inputs to values in each object
        xPosition = aX;
        yPosition = aY;
        zPosition = aZ;
        unitSize = diameter;
        unitColour = "RED";
        numberOfCheckpoints = cPNumber;
        loopedPath = loop;
		forward = true;
		enemyRange = range;
		roundsPerMinute = 60 / rPM ;
		speed = 2 / ( 10 / enemySpeed );

		// Variables that aren't declared in the constructor but need to be individual for each object
		distance = 0;
		gotTarget = false;
		reloaded = true;
		timeShot = 0;
		hit = false;
		zeroPath = false;
		targetCheckpoint = 1;
		rPMLong = 0;
		this.setRPMLong( roundsPerMinute );
		firstCheck = true;
		distanceA2 = 0;
		distanceB2 = 0;
        
		// Assigns values the checkpoint locations
        checkpointLocations[0][0] = aX;
        checkpointLocations[0][1] = aY;
        checkpointLocations[0][2] = aZ;
         
        checkpointLocations[1][0] = bX;
        checkpointLocations[1][1] = bY;
        checkpointLocations[1][2] = bZ;
         
        checkpointLocations[2][0] = cX;
        checkpointLocations[2][1] = cY;
        checkpointLocations[2][2] = cZ;
    }
     
	// EnemyUnit object constructor with 1 checkpoint 
    public EnemyUnit(   double diameter,  int cPNumber, int range, float rPM, boolean loop, double enemySpeed,
                        double aX, double aY, double aZ,
                        double bX, double bY, double bZ,
                        double cX, double cY, double cZ,
                        double dX, double dY, double dZ )
    {
		// Assigns constructor inputs to values in each object
        xPosition = aX;
        yPosition = aY;
        zPosition = aZ;
        unitSize = diameter;
        unitColour = "RED";
        numberOfCheckpoints = cPNumber;
        loopedPath = loop;
		forward = true;
		enemyRange = range;
		roundsPerMinute = 60/rPM;
		speed = 2 / ( 10 / enemySpeed );

		// Variables that aren't declared in the constructor but need to be individual for each object
		distance = 0;
		gotTarget = false;
		reloaded = true;
		timeShot = 0;
		hit = false;
		zeroPath = false;
		targetCheckpoint = 1;
		rPMLong = 0;
		this.setRPMLong( roundsPerMinute );
		firstCheck = true;
		distanceA2 = 0;
		distanceB2 = 0;
		
		// Assigns values the checkpoint locations
        checkpointLocations[0][0] = aX;
        checkpointLocations[0][1] = aY;
        checkpointLocations[0][2] = aZ;
         
        checkpointLocations[1][0] = bX;
        checkpointLocations[1][1] = bY;
        checkpointLocations[1][2] = bZ;
         
        checkpointLocations[2][0] = cX;
        checkpointLocations[2][1] = cY;
        checkpointLocations[2][2] = cZ;
         
        checkpointLocations[3][0] = dX;
        checkpointLocations[3][1] = dY;
        checkpointLocations[3][2] = dZ;
    }
    
	// EnemyUnit object constructor with 1 checkpoint	
    public EnemyUnit(   double diameter, int cPNumber, int range, float rPM, boolean loop, double enemySpeed,
                        double aX, double aY, double aZ,
                        double bX, double bY, double bZ,
                        double cX, double cY, double cZ,
                        double dX, double dY, double dZ,
                        double eX, double eY, double eZ )
    {
		// Assigns constructor inputs to values in each object
        xPosition = aX;
        yPosition = aY;
        zPosition = aZ;
        unitSize = diameter;
        unitColour = "RED";
        numberOfCheckpoints = cPNumber;
        loopedPath = loop;
		forward = true;
		enemyRange = range;
		roundsPerMinute = 60/rPM;
		speed = 2 / ( 10 / enemySpeed );

		// Variables that aren't declared in the constructor but need to be individual for each object
		distance = 0;
		gotTarget = false;
		reloaded = true;
		timeShot = 0;
		hit = false;
		zeroPath = false;
		targetCheckpoint = 1;
		rPMLong = 0;
		this.setRPMLong( roundsPerMinute );
		firstCheck = true;
		distanceA2 = 0;
		distanceB2 = 0;
        
		// Assigns values the checkpoint locations		
        checkpointLocations[0][0] = aX;
        checkpointLocations[0][1] = aY;
        checkpointLocations[0][2] = aZ;
         
        checkpointLocations[1][0] = bX;
        checkpointLocations[1][1] = bY;
        checkpointLocations[1][2] = bZ;
         
        checkpointLocations[2][0] = cX;
        checkpointLocations[2][1] = cY;
        checkpointLocations[2][2] = cZ;
         
        checkpointLocations[3][0] = dX;
        checkpointLocations[3][1] = dY;
        checkpointLocations[3][2] = dZ;
         
        checkpointLocations[4][0] = eX;
        checkpointLocations[4][1] = eY;
        checkpointLocations[4][2] = eZ;
    }
    
	// Get x position
    public double getXPosition()
    {
        return xPosition;
    }
      
	// Get y position
    public double getYPosition()
    {
        return yPosition;
    }
	
    // Get z position  
    public double getZPosition()
    {
        return zPosition;
    }
	
    // Set x position  
    public void setXPosition(double x)
    {
        this.xPosition = x;
    }
	
    // Set y position    
    public void setYPosition(double y)
    {
        this.yPosition = y;
    }
	
    // Set z position    
    public void setZPosition(double z)
    {
        this.zPosition = z;
    }
	
	// Get colour  
    public String getColour()
    {
        return unitColour;
    }
    
	// Get size of object
    public double getSize()
    {
        return unitSize;
    }
     
	// Get checkpoint location at index [x][y]
    public double getCheckpointLocations(int x, int y)
    {
        return checkpointLocations[x][y];
    }
          
	// Get number of checkpoints
    public int getNumberOfCheckpoints()
    {
        return numberOfCheckpoints;
    }
    
	// Get loop
    public boolean getLoop()
    {
        return loopedPath;
    }
	
	// Get enemy range
	public double getEnemyRange()
    {
        return enemyRange;
    }

	// Get rounds per minute
	public double getRoundsPerMinute()
    {
        return roundsPerMinute;
    }
	
	// Get got target
	public boolean getGotTarget()
	{
		return gotTarget;
	}
	
	// Set got target
	public void setGotTarget( boolean t )
	{
		this.gotTarget = t;
	}
	
	// Get distance
	public double getDistance()
	{
		return distance;
	}
	
	// Set distance
	public void setDistance( double d )
	{
		this.distance = d;
	}
	
	// Get reloaded
	public boolean getReloaded()
	{
		return reloaded;
	}
	
	// Set reloaded
	public void setReloaded( boolean r )
	{
		this.reloaded = r;
	}
	
	// Get time shot
	public long getTimeShot()
	{
		return timeShot;
	}
	
	// Set time shot
	public void setTimeShot( long nanoTime )
	{
		this.timeShot = nanoTime;
	}
	
	// Set hit
	public void setHit( boolean hit )
	{
		this.hit = hit;
	}
	
	// Get hit
	public boolean getHit()
	{
		return hit;
	}
	
	// Get speed
	public double getSpeed()
	{
		return speed;
	}
	
	// Get target checkpoint
	public int getTargetCheckpoint()
	{
		return targetCheckpoint;
	}
	
	// Set target checkpoint
	public void setTargetCheckpoint( int targetCheckpoint )
	{
		this.targetCheckpoint = targetCheckpoint;
	}
	
	// Get forward
	public boolean getForward()
	{
		return forward;
	}
	
	// Set forward
	public void setForward( boolean forward )
	{
		this.forward = forward;
	}
	
	// Get zero path
	public boolean getZeroPath()
	{
		return zeroPath;
	}
	
	// Set rpm long
	public void setRPMLong( float rPMDouble )
	{
		int nearestInt = ( int ) Math.rint( rPMDouble * 1000 );
		this.rPMLong = ( long ) nearestInt * 1000000;
	}
	
	// Get rpm long
	public long getRPMLong()
	{
		return rPMLong;
	}
	
	// Get first check
	public boolean getFirstCheck()
	{
		return firstCheck;
	}
	
	// Set first check
	public void setFirstCheck( boolean firstCheck )
	{
		this.firstCheck = firstCheck;
	}
	
	// Get distance a
	public double getDistanceA()
	{
		return distanceA2;
	}
	
	// Set distance a
	public void setDistanceA( double distanceA )
	{
		this.distanceA2 = distanceA;
	}

	// Get distance b
	public double getDistanceB()
	{
		return distanceB2;
	}
	
	// Set distance b
	public void setDistanceB( double distanceB )
	{
		this.distanceB2 = distanceB;
	}
	
	// Checks distance between the drone and the enemy
	public void checkRange( double aX, double aY, double aZ )
	{
		// aDist = SwarmLocation - EnemyUnitLocation
		aXDist = aX - this.getXPosition();
		aYDist = aY - this.getYPosition();
		aZDist = aZ - this.getZPosition();
		
		// If distance is negative number make positive
		if( aXDist < 0 )
		{
			aXDist = -aXDist;
		}
		
		// If distance is negative number make positive
		if( aYDist < 0 )
		{
			aYDist = -aYDist;
		}
		
		// If distance is negative number make positive
		if( aZDist < 0 )
		{
			aZDist = -aZDist;
		}
		
		// Uses Pythagoras to calculate distance on all axis
		distanceA1 = Math.sqrt( aXDist * aXDist + aYDist * aYDist );
		this.setDistanceA( Math.sqrt( distanceA1 * distanceA1 + aZDist * aZDist ));
		
		// If the Swarm object is within range of EnemyUnit object the set gotTarget to true
		if( this.getDistanceA() <= this.getEnemyRange() )
		{
			this.setGotTarget( true );
		}
	}	
	
	// Compares the target of EnemyUnit to Drone object on opposite side of Swarm
	public void compDrones( double bX, double bY, double bZ )
	{
		// aDist = SwarmLocation - EnemyUnitLocation
		bXDist = bX - this.getXPosition();
		bYDist = bY - this.getYPosition();
		bZDist = bZ - this.getZPosition();
		
		// If distance is negative number make positive
		if( bXDist < 0 )
		{
			bXDist = -bXDist;
		}
		
		// If distance is negative number make positive
		if( bYDist < 0 )
		{
			bYDist = -bYDist;
		}
		
		// If distance is negative number make positive
		if( bZDist < 0 )
		{
			bZDist = -bZDist;
		}
		
		// Uses Pythagoras to calculate distance on all axis
		distanceB1 = Math.sqrt( bXDist * bXDist + bYDist * bYDist );
		this.setDistanceB( Math.sqrt( distanceB1 * distanceB1 + bZDist * bZDist ));
		
		// If distance between Drone A and EnemyUnit < Distance between Drone B and EnemyUnit
		if( this.getDistanceA() < this.getDistanceB() )
		{
			// Set target Drone A
			this.setFirstCheck( true );
			this.setDistance( this.getDistanceA() );
		}

		// If distance between Drone A and EnemyUnit > Distance between Drone B and EnemyUnit
		else
		{
			// Set target Drone B
			this.setFirstCheck( false );
			this.setDistance( this.getDistanceB() );
		}
	}
		
	// Shoot method
	public void shoot()
    {       
		// Calculates the accuracy percentage using distance between drone and enemyunit against the range
		hitPercentage = this.getEnemyRange() - ( ( this.getDistance() / this.getEnemyRange() ) * 100 );
		accuracy = (int) hitPercentage;
		 
		// Gets a random number between 0 and 100
		rand = Math.random() * 100;
	 
		// ystem.out.println( "SHOT FIRED" );
		
		// Uses the random number and the hit percentage to determine whether the shot hit or not
		if( rand <= hitPercentage )
		{
			this.setHit( true );
		}
		
		// After the shot, start the reload process
		this.reloaded = false;
		this.setTimeShot( System.nanoTime() );		
    }
	
	// Method that moves the EnemyUnit object
    public void move()
    {
		// If statement that checks if EnemyUnit object is meant to have a zero path
		if( this.getZeroPath() == true )
		{
			// If zeropath == true then the amount that the object moves is 0
			xDiff = 0;
			yDiff = 0;
			zDiff = 0;
		}
		
		// If the object doesn't have a zero path then caluclate how much the EnemyUnit object moves
		else
		{
			// Gets the distance between the EnemyUnit object and the checkpoint that it is moving towards
			xDiff = this.getCheckpointLocations( this.getTargetCheckpoint(), 0 ) - this.getXPosition();
			yDiff = this.getCheckpointLocations( this.getTargetCheckpoint(), 1 ) - this.getYPosition();
			zDiff = this.getCheckpointLocations( this.getTargetCheckpoint(), 2 ) - this.getZPosition();
		}
		
		// If statement that checks if EnemyUnit object has reached the target checkpoint
		if( xDiff != 0 || yDiff != 0 || zDiff != 0 )
		{
			// If the difference on the x axis is negative then change it to positive until later
			if( xDiff < 0 )
			{
				xNeg = true;
				xDiff = -xDiff;
			}
			
			// If the difference on the y axis is negative then change it to positive until later
			if( yDiff < 0 )
			{
				yNeg = true;
				yDiff = -yDiff;
			}
			
			// If the difference on the x axis is negative then change it to positive until later
			if( zDiff < 0 )
			{
				zNeg = true;
				zDiff = -zDiff;
			}
			
			// If statements that checks to see if the x difference is larger than both the y difference and the z difference
			if( xDiff >= yDiff && xDiff >= zDiff )
			{
				// Make the movement on the x axis and the movement on the y and z axis fractions of x 
				moveX = xDiff / xDiff;
				moveY = yDiff / xDiff;
				moveZ = zDiff / xDiff;
			}
			
			// If statements that checks to see if the y difference is larger than both the x difference and the z difference
			else if( yDiff >= xDiff && yDiff >= zDiff )
			{
				// Make the movement on the z axis and the movement on the x and z axis fractions of y 
				moveX = xDiff / yDiff;
				moveY = yDiff / yDiff;
				moveZ = zDiff / yDiff;
			}
			
			// If statements that checks to see if the z difference is larger than both the x difference and the y difference
			else if( zDiff >= xDiff && zDiff >= yDiff )
			{
				// Make the movement on the z axis and the movement on the x and y axis fractions of z 
				moveX = xDiff / zDiff;
				moveY = yDiff / zDiff;
				moveZ = zDiff / zDiff;
			}
			
			// If the x difference was once negative, change it back to negative
			if( xNeg == true )
			{
				moveX = -moveX;
				xNeg = false;
			}
			
			// If the y difference was once negative, change it back to negative
			if( yNeg == true )
			{
				moveY = -moveY;
				yNeg = false;
			}
			
			// If the z difference was once negative, change it back to negative 
			if( zNeg == true )
			{
				moveZ = -moveZ;
				zNeg = false;
			}
			
			// Move the 
			this.setXPosition( this.getXPosition() + ( moveX * this.getSpeed() ));
			this.setYPosition( this.getYPosition() + ( moveY * this.getSpeed() ));
			this.setZPosition( this.getZPosition() + ( moveZ * this.getSpeed() ));
		}
		
		// If the EnemyUnit has reached the checkpoint
		else
		{
			// If the EnemyUnit object has only 1 checkpoint, don't change the target checkpoint
			if( this.getNumberOfCheckpoints() == 1 )
			{
				this.setTargetCheckpoint( 0 );
			}
			
			// Else if the EnemyUnit object has 2 checkpoints
			else if( this.getNumberOfCheckpoints() == 2 )
			{
				// If the target checkpoint is 1 then set the target checkpoint to 0
				if( this.getTargetCheckpoint() == 1 )
				{
					this.setTargetCheckpoint( 0 );
				}
				
				// Else if the target checkpoint is 0 then set the target checkpoint to 1
				else
				{
					this.setTargetCheckpoint( 1 );
				}
			}
			
			// If the EnemyUnit object path is a loop
			else if( this.getLoop() == true )
			{
				// If the EnemyUnit object hasn't reached the end checkpoint
				if( this.getTargetCheckpoint() < this.getNumberOfCheckpoints() - 1 )
				{
					// Set the next target checkpoint as the next checkpoint in the list
					this.setTargetCheckpoint( this.getTargetCheckpoint() + 1 );
				}
				
				// If the EnemyUnit object has reached the last checkpoint
				else
				{
					// Set the EnemyUnit object's next checkpoint as the first checkpoint
					this.setTargetCheckpoint( 0 );
				}
			}
			
			// If the EnemyUnit object path isn't a loop
			else
			{	
				// If the EnemyUnit object is moving forward along the path
				if( this.getForward() == true )
				{
					// If the EnemyUnit object hasn't reached the end checkpoint
					if( this.getTargetCheckpoint() < this.getNumberOfCheckpoints() - 1 )
					{
						// Set the next target checkpoint as the next checkpoint in the list
						this.setTargetCheckpoint( this.getTargetCheckpoint() + 1 );
					}
					
					// If the EnemyUnit object has reached the end checkpoint
					else
					{
						// Set the forward value as false and move to previous checkpoint
						this.setForward( false );
						this.setTargetCheckpoint( this.getTargetCheckpoint() - 1 );
					}
				}
				
				// If the EnemyUnit object is moving backwards along the path
				else
				{
					// If the EnemyUnit hasn't reached the end checkpoint
					if( this.getTargetCheckpoint() > 0 )
					{
						// Set the next checkpoint as the next in the list going backwards
						this.setTargetCheckpoint( this.getTargetCheckpoint() - 1 );
					}
					
					// If the EnemyUnit object has reached the start checkpoint
					else
					{
						// Set the forward value as true and move to the next checkpoint
						this.setForward( true );
						this.setTargetCheckpoint( this.getTargetCheckpoint() + 1 );
					}
				}
			} 
		}  
    }   
 
    public void update()
    {
        this.move();
    }
}