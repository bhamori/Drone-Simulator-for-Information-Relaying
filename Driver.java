// James Caldwell

// Import Java libraries
import java.lang.*;
import java.io.*;
import java.util.*;

public class Driver
{
	// Main method
	public static void main( String[] args ) throws IOException
	{
		// Scanner takes input file and uses delimiter to separate each value
		Scanner inputFile = new Scanner( new File( "input.txt" )).useDelimiter( "\\s" );
		
		// Defines ArrayList for input values and a temporaty string to take the values
		List<String> inputArrayList = new ArrayList<String>();
		String tempString = null;
		
		// While the input file has next value
		while ( inputFile.hasNext() )
		{
			// Assign next value to tempString and add to inputArrayList
			tempString = inputFile.next();
			inputArrayList.add( tempString );
		}

		// Creates string Array the same size as the ArrayList
		String[] inputArray = new String[ inputArrayList.size() ];
		
		// Copies all values from ArrayList to Array
		for( int i = 0; i < inputArrayList.size(); i++ )
		{
			inputArray[ i ] = inputArrayList.get( i );
		}
		
		// Closes inputFile now that it has been read
		inputFile.close();

		// Builds Playground using first 2 values in Array
		int width = Integer.parseInt( inputArray[ 0 ] );
		int height = Integer.parseInt( inputArray[ 1 ] );
		Playground pg = new Playground ( width, height );

		// Prints Playground and its variables to Command Prompt
		System.out.println( "Playground:" );
		System.out.println( "Width: " + width );
		System.out.println( "Height: " + height );
		System.out.println( " " );
		
		// Creates Object ArrayList
		ArrayList<Object> objs = new ArrayList<Object>();

		// For loop that runs through each input value in the array
		for( int i = 0; i < inputArray.length; i++ )
		{
			// Takes value in position i and reads it as object id
			int id = Integer.parseInt( inputArray[ i ] );

			// If object id is 1 then build rectangle
			// Rectangle
			if( id == 1 )
			{
				// Take next 5 values in array and assign to variables
				double x = Double.parseDouble( inputArray[ i + 1 ] );
				double y = Double.parseDouble( inputArray[ i + 2 ] );
				double z = Double.parseDouble( inputArray[ i + 3 ] );
				width = Integer.parseInt( inputArray[ i + 4 ] );
				height = Integer.parseInt( inputArray[ i + 5 ] );
				i = i + 5;
				
				// Use variables to build new rectangle and add rectangle object to Object ArrayList
				Rectangle r = new Rectangle( x, y, z, width, height );
				objs.add( objs.size(), r );

				// Print Rectangle and variables to Command Prompt
				System.out.println( "Rectangle:" );
				System.out.println( "X Position: " + x );
				System.out.println( "Y Position: " + y );
				System.out.println( "Z Position: " + z );
				System.out.println( "Width: " + width );
				System.out.println( "Height: " + height );
				System.out.println( " " );
				
			}
			
			// If object id is 2 then build EnemyUnit
			// EnemyUnit
			if( id == 2 )
			{	
				// Take next 6 values in array and assign to variables
				double diameter = Double.parseDouble( inputArray[ i + 1 ] );
				int cPNumber = Integer.parseInt( inputArray[ i + 2 ] );
				int range = Integer.parseInt( inputArray[ i + 3 ] );
				int rPM = Integer.parseInt( inputArray[ i + 4 ] );
				boolean loop = Boolean.parseBoolean( inputArray[ i + 5 ] );
				double speed = Double.parseDouble( inputArray[ i + 6 ] );	
				
				// Take next 3 values and assign to first checkpoint
				double aX = Double.parseDouble( inputArray[ i + 7 ] );
				double aY = Double.parseDouble( inputArray[ i + 8 ] );
				double aZ = Double.parseDouble( inputArray[ i + 9 ] );
				
				// Print EnemyUnit and variables to Command Prompt
				System.out.println( "EnemyUnit:" );
				System.out.println( "Diameter: " + diameter );
				System.out.println( "Number of Checkpoints: " + cPNumber );
				System.out.println( "Shot Range: " + range );
				System.out.println( "Rounds Per Minute: " + rPM );
				System.out.println( "Loop: " + loop );
				System.out.println( "Speed: " + speed );
				
				System.out.println( "Checkpoint A, X Position: " + aX );
				System.out.println( "Checkpoint A, Y Position: " + aY );
				System.out.println( "Checkpoint A, Z Position: " + aZ );
				
				// If EnemyUnit object has 2 checkpoints
				if( cPNumber == 2 )
				{
					// Take checkpoint values and assigns to variables
					double bX = Double.parseDouble( inputArray[ i + 10 ] );
					double bY = Double.parseDouble( inputArray[ i + 11 ] );
					double bZ = Double.parseDouble( inputArray[ i + 12 ] );
					
					// Builds EnemyUnit object using variables and adds EnemyUnit object to Object ArrayList
					EnemyUnit e = new EnemyUnit( diameter, cPNumber, range, rPM, loop, speed, aX, aY, aZ, bX, bY, bZ );
					objs.add( objs.size(), e );
					
					i = i + 12;
					
					// Print variables to Command Prompt
					System.out.println( "Checkpoint B, X Position: " + bX );
					System.out.println( "Checkpoint B, Y Position: " + bY );
					System.out.println( "Checkpoint B, Z Position: " + bZ );
					
				}

				// If EnemyUnit object has 3 checkpoints
				else if( cPNumber == 3 )
				{
					// Take checkpoint values and assigns to variables
					double bX = Double.parseDouble( inputArray[ i + 10 ] );
					double bY = Double.parseDouble( inputArray[ i + 11 ] );
					double bZ = Double.parseDouble( inputArray[ i + 12 ] );				
					
					double cX = Double.parseDouble( inputArray[ i + 13 ] );
					double cY = Double.parseDouble( inputArray[ i + 14 ] );
					double cZ = Double.parseDouble( inputArray[ i + 15 ] );
					
					// Builds EnemyUnit object using variables and adds EnemyUnit object to Object ArrayList
					EnemyUnit e = new EnemyUnit( diameter, cPNumber, range, rPM, loop, speed, aX, aY, aZ, bX, bY, bZ, cX, cY, cZ );
					objs.add( objs.size(), e );
					
					i = i + 15;
					
					// Print variables to Command Prompt
					System.out.println( "Checkpoint B, X Position: " + bX );
					System.out.println( "Checkpoint B, Y Position: " + bY );
					System.out.println( "Checkpoint B, Z Position: " + bZ );
					
					System.out.println( "Checkpoint C, X Position: " + cX );
					System.out.println( "Checkpoint C, Y Position: " + cY );
					System.out.println( "Checkpoint C, Z Position: " + cZ );
					
				}
				
				// If EnemyUnit object has 4 checkpoints
				else if( cPNumber == 4 )
				{
					// Take checkpoint values and assigns to variables
					double bX = Double.parseDouble( inputArray[ i + 10 ] );
					double bY = Double.parseDouble( inputArray[ i + 11 ] );
					double bZ = Double.parseDouble( inputArray[ i + 12 ] );					
					
					double cX = Double.parseDouble( inputArray[ i + 13 ] );
					double cY = Double.parseDouble( inputArray[ i + 14 ] );
					double cZ = Double.parseDouble( inputArray[ i + 15 ] );
					
					double dX = Double.parseDouble( inputArray[ i + 16 ] );
					double dY = Double.parseDouble( inputArray[ i + 17 ] );
					double dZ = Double.parseDouble( inputArray[ i + 18 ] );
					
					// Builds EnemyUnit object using variables and adds EnemyUnit object to Object ArrayList
					EnemyUnit e = new EnemyUnit( diameter, cPNumber, range, rPM, loop, speed, aX, aY, aZ, bX, bY, bZ, cX, cY, cZ, dX, dY, dZ );
					objs.add( objs.size(), e );
					
					i = i + 18;
					
					// Print variables to Command Prompt
					System.out.println( "Checkpoint B, X Position: " + bX );
					System.out.println( "Checkpoint B, Y Position: " + bY );
					System.out.println( "Checkpoint B, Z Position: " + bZ );
					
					System.out.println( "Checkpoint C, X Position: " + cX );
					System.out.println( "Checkpoint C, Y Position: " + cY );
					System.out.println( "Checkpoint C, Z Position: " + cZ );
					
					System.out.println( "Checkpoint D, X Position: " + dX );
					System.out.println( "Checkpoint D, Y Position: " + dY );
					System.out.println( "Checkpoint D, Z Position: " + dZ );
				}
				
				// If EnemyUnit object has 5 checkpoints
				else if( cPNumber == 5 )
				{
					// Take checkpoint values and assigns to variables
					double bX = Double.parseDouble( inputArray[ i + 10 ] );
					double bY = Double.parseDouble( inputArray[ i + 11 ] );
					double bZ = Double.parseDouble( inputArray[ i + 12 ] );					
					
					double cX = Double.parseDouble( inputArray[ i + 13 ] );
					double cY = Double.parseDouble( inputArray[ i + 14 ] );
					double cZ = Double.parseDouble( inputArray[ i + 15 ] );
					
					double dX = Double.parseDouble( inputArray[ i + 16 ] );
					double dY = Double.parseDouble( inputArray[ i + 17 ] );
					double dZ = Double.parseDouble( inputArray[ i + 18 ] );
					
					double eX = Double.parseDouble( inputArray[ i + 19 ] );
					double eY = Double.parseDouble( inputArray[ i + 20 ] );
					double eZ = Double.parseDouble( inputArray[ i + 21 ] );
					
					// Builds EnemyUnit object using variables and adds EnemyUnit object to Object ArrayList
					EnemyUnit e = new EnemyUnit( diameter, cPNumber, range, rPM, loop, speed, aX, aY, aZ, bX, bY, bZ, cX, cY, cZ, dX, dY, dZ, eX, eY, eZ );
					objs.add( objs.size(), e );
					
					i = i + 21;
					
					// Print variables to Command Prompt
					System.out.println( "Checkpoint B, X Position: " + bX );
					System.out.println( "Checkpoint B, Y Position: " + bY );
					System.out.println( "Checkpoint B, Z Position: " + bZ );
					
					System.out.println( "Checkpoint C, X Position: " + cX );
					System.out.println( "Checkpoint C, Y Position: " + cY );
					System.out.println( "Checkpoint C, Z Position: " + cZ );
					
					System.out.println( "Checkpoint D, X Position: " + dX );
					System.out.println( "Checkpoint D, Y Position: " + dY );
					System.out.println( "Checkpoint D, Z Position: " + dZ );
					
					System.out.println( "Checkpoint E, X Position: " + eX );
					System.out.println( "Checkpoint E, Y Position: " + eY );
					System.out.println( "Checkpoint E, Z Position: " + eZ );
				}
				
				// If EnemyUnit object only has 1 checkpoint
				else
				{
					// Builds EnemyUnit object using variables and adds EnemyUnit object to Object ArrayList
					EnemyUnit e = new EnemyUnit( diameter, cPNumber, range, rPM, loop, speed, aX, aY, aZ );
					objs.add( objs.size(), e );
					
					i = i + 9;
				}
				
				System.out.println( " " );		
			}
			
			// Swarm
			if( id == 3 )
			{
				// Take next 4 values in array and assign to variables
				double x = Double.parseDouble( inputArray[ i + 1 ] );
				double y = Double.parseDouble( inputArray[ i + 2 ] );
				double z = Double.parseDouble( inputArray[ i + 3 ] );
				int drones = Integer.parseInt( inputArray[ i + 4 ] );
				
				// Builds Swarm object using variables and adds Swarm object to Object ArrayList
				Swarm s = new Swarm( x, y, z, drones );
				objs.add( objs.size(), s );
				
				// Print variables to Command Prompt
				System.out.println( "Swarm:" );
				System.out.println( "X Position: " + x );
				System.out.println( "Y Position: " + y );
				System.out.println( "Z Position: " + z );
				System.out.println( "Number of Drones: " + drones );
				System.out.println( "" );
				
				i = i + 4;					
			}

			// Base
			if( id == 4 )
			{
				// Take next 4 values in array and assign to variables
				double x = Double.parseDouble( inputArray[ i + 1 ] );
				double y = Double.parseDouble( inputArray[ i + 2 ] );
				double z = Double.parseDouble( inputArray[ i + 3 ] );
				int diameter = Integer.parseInt( inputArray[ i + 4 ] );
				
				// Builds Base object using variables and adds Base object to Object ArrayList
				Base b = new Base( x, y, z, diameter );
				objs.add( objs.size(), b );
				
				// Print variables to Command Prompt
				System.out.println( "Base:" );
				System.out.println( "X Position: " + x );
				System.out.println( "Y Position: " + y );
				System.out.println( "Z Position: " + z );
				System.out.println( "Diameter: " + diameter );
				System.out.println( "" );
				
				i = i + 4;
			}
			
			// Receiver
			if( id == 5 )
			{
				// Take next 4 values in array and assign to variables
				double x = Double.parseDouble( inputArray[ i + 1 ] );
				double y = Double.parseDouble( inputArray[ i + 2 ] );
				double z = Double.parseDouble( inputArray[ i + 3 ] );
				int diameter = Integer.parseInt( inputArray[ i + 4 ] );
				
				// Builds Reveiver object using variables and adds Receiver object to Object ArrayList
				Receiver r = new Receiver( x, y, z, diameter );
				objs.add( objs.size(), r );
				
				// Print variables to Command Prompt
				System.out.println( "Receiver:" );
				System.out.println( "X Position: " + x );
				System.out.println( "Y Position: " + y );
				System.out.println( "Z Position: " + z );
				System.out.println( "Diameter: " + diameter );
				System.out.println( "" );
				
				i = i + 4;
			}
			
			// Transmitter
			if( id == 6 )
			{
				// Take next 4 values in array and assign to variables
				double x = Double.parseDouble( inputArray[ i + 1 ] );
				double y = Double.parseDouble( inputArray[ i + 2 ] );
				double z = Double.parseDouble( inputArray[ i + 3 ] );
				int diameter = Integer.parseInt( inputArray[ i + 4 ] );
				
				// Builds Transmitter object using variables and adds Transmitter object to Object ArrayList
				Transmitter t = new Transmitter( x, y, z, diameter );
				objs.add( objs.size(), t );
				
				// Print variables to Command Prompt
				System.out.println( "Transmitter:" );
				System.out.println( "X Position: " + x );
				System.out.println( "Y Position: " + y );
				System.out.println( "Z Position: " + z );
				System.out.println( "Diameter: " + diameter );
				System.out.println( "" );
				
				i = i + 4;
			}
		}
		
		// Defines variable firstRun
		boolean firstRun = true;

		// While loop that runs application until stopped
		while( true )
		{
			// Loops through Object Arraylist
			for( Object o : objs )
			{
				// If object is a Rectangle
				if( o instanceof Rectangle )
				{
					// Take Rectangle and add object to Playground
					Rectangle tempRectangle = ( Rectangle ) o;
					pg.addRectangle( tempRectangle );
				}
				
				// If object is an EnemyUnit
				if( o instanceof EnemyUnit )
				{
					// Take EnemyUnit and add object to Playground and updates values object location
					EnemyUnit tempEnemy = ( EnemyUnit ) o;
					pg.addEnemyUnit( tempEnemy );
					tempEnemy.update();
					
					// If while loop is in first run, add EnemyUnit object to ArrayList in Playground
					if( firstRun )
					{
						pg.addEnemyUnitList( tempEnemy );
					}
				}
				
				// If object is a Swarm
				if( o instanceof Swarm )
				{
					// Take Swarm and add object to Playground and updates values object location
					Swarm tempSwarm = ( Swarm ) o;
					pg.addSwarm( tempSwarm );
					tempSwarm.update();
					
					// If while loop is in first run, add Swarm object to ArrayList in Playground
					if( firstRun )
					{
						pg.addSwarmList( tempSwarm );
					}
				}
				
				// If object is a Base
				if( o instanceof Base )
				{
					// Take Base and add object to Playground
					Base tempBase = ( Base ) o;
					pg.addBase( tempBase );
				}
				
				// If object is a Receiver
				if( o instanceof Receiver )
				{
					// Take Receiver and add object to Playground
					Receiver tempReceiver = ( Receiver ) o;
					pg.addReceiver( tempReceiver );
				}
				
				// If object is a Transmitter
				if( o instanceof Transmitter )
				{
					// Take Transmitter and add object to Playground
					Transmitter tempTransmitter = ( Transmitter ) o;
					pg.addTransmitter( tempTransmitter );
				}
			}
			
			// After first run of loop, set firstRun to false
			firstRun = false;
			
			// Run playground and track objects
			pg.run();
			pg.trackObjects();
		}
	}	
}