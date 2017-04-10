// James Caldwell

// Imports Java libraries
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;
import java.util.Collections;

public class Playground extends JPanel implements Runnable
{
	// Size of playground
	private int playgroundWidth;
	private int playgroundHeight;
	
	// Size of redraw area
	private int canvasWidth;
	private int canvasHeight;
	
	// Boolean to exit
	private boolean exiting = false;
	
	// ArrayList of all objects and individual EnemyUnit and Swarm ArrayLists
	private ArrayList< Object > things = new ArrayList< Object >();
	public ArrayList< EnemyUnit > enemyList = new ArrayList< EnemyUnit >();
	public ArrayList< Swarm > swarmList = new ArrayList< Swarm >();
	
	// Variable for Drones
	public int dronesInRing = 7;
	public int drones = 30;
	
	// Variable for JFrame
	private JFrame window;
	
	// Creates window
	public Playground( int width, int height )
	{
		this.window = new JFrame();
		this.setSize( width, height );
		
		window.setTitle( "UAS Simulation" );
		window.setBackground( Color.WHITE );
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		window.setContentPane( this );
		window.setVisible( true );
		
		Thread t = new Thread( this );
		t.start();
	}

	// Repaints graphics
	public void run()
	{
		try
		{
			this.repaint();
			Thread.sleep( 30 );
		}
		catch (Exception e) { }

		synchronized ( this )
		{
			things.clear();
		}
	}
	
	// Set the size of the window
	public void setSize( int width, int height )
	{
		this.playgroundWidth = width;
		this.playgroundHeight = height;
		
		this.canvasWidth = width+this.getInsets().left+this.getInsets().right;
		this.canvasHeight = width+this.getInsets().top+this.getInsets().bottom;
		window.setSize( canvasWidth, canvasHeight );
	}
	
	// Closes window
	public void exit()
	{
		this.exiting = true;
	}
	
	// Method called by operating system to draw graphics
	public void paint ( Graphics gr )
	{
		// If canvas width/height is less than or equal to 0
		if ( this.getCanvasWidth() <= 0 || this.getCanvasHeight() <= 0 )
		{
			return;
		}
		
		// Create 2D graphics
		BufferedImage i = new BufferedImage( this.getCanvasWidth(), this.getCanvasHeight(), BufferedImage.TYPE_INT_ARGB );
		Graphics2D g = i.createGraphics();
		
		g.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

		// Draw graphics
		synchronized( this )
		{
			if( !this.exiting )
			{
				// Clear drawing
				g.clearRect( 0,0,this.getCanvasWidth(), this.getCanvasHeight() );

				// Loop through each object
				for( Object o : things )
				{
					// If Swarm object draw Swarm
					//Botond Hamori
					if( o instanceof Swarm )
					{
						//Botond Hamori
						if(o instanceof Swarm)
						{
							Swarm a = (Swarm) o;
							
							double dotX = a.getPathX();
							double dotY = a.getPathY();						
							g.setColor(this.getColourFromString(a.getDotColour()));
							g.fillOval((int)(a.getPathX()-a.getDotSize()/2), (int)(a.getPathY()-a.getDotSize()/2), (int)a.getDotSize(), (int)a.getDotSize());
							
							
							g.setColor(this.getColourFromString(a.getColour()));
							g.fillOval((int)(a.getXPosition()-a.getSize()/2), (int)(a.getYPosition()-a.getSize()/2), (int)a.getSize(), (int)a.getSize());
							
							int drones = a.getArraySize()-1;
							double centreX = a.getXPosition();
							double centreY = a.getYPosition();
							double centreZ = a.getZPosition();
							a.setDroneX(centreX, 0);
							a.setDroneY(centreY, 0);
							a.setDroneZ(centreZ, 0);
							double diameter = a.getSize(); //size of a drone
							double radians;
							
							int dronesInRing = 7;
							
							//find out if the number of drones is divisible by the max number of drones in a ring.
							float fraction = drones/dronesInRing;
							int numberOfRings = (int) Math.round(fraction);
							int rem = drones%dronesInRing;
							
							int count = 0;
							
							//create rings in swarm
							//number of rings depends on number of drones
							
							if(rem == 0) //no remainder, total number of drones around centre is divisible by the max number of drones in a ring
							{						
								while(count != drones)
								{
									for(int count2 = 1; count2 <= numberOfRings; count2++)
									{
										for(int count3 = 1; count3 <= dronesInRing; count3++)
										{
											count++;
											
											radians = 2*Math.PI*count3/(dronesInRing) + (Math.PI/2)*(count2-1);
											
											int newX = (int)(centreX + 7*count2*Math.sin(radians) - diameter);
											int newY = (int)(centreY + 7*count2*Math.cos(radians) - diameter);
											//int newZ = (int)(centreZ + 7*count2*Math.cos(radians) - diameter);
											g.fillOval(newX + (int)a.getSize()/2, newY + (int)a.getSize()/2, (int)a.getSize(), (int)a.getSize());
											
											a.setDroneX(newX, count);
											a.setDroneY(newY, count);
											//a.setDroneZ(newZ, count);
										}
									}
								}
							}
							else //there is remainder, the outer ring cannot be filled fully
							{
								while(count != (drones-rem)) //the difference between drones and rem is a value that's always divisible by the max number of drones in a ring
								{
									for(int count2 = 1; count2 <= numberOfRings; count2++)//... therefore create inner rings first
									{
										for(int count3 = 1; count3 <= dronesInRing; count3++)
										{
											count++;
											
											radians = 2*Math.PI*count3/(dronesInRing) + (Math.PI/2)*(count2-1);
											
											int newX = (int)(centreX + 7*count2*Math.sin(radians) - diameter);
											int newY = (int)(centreY + 7*count2*Math.cos(radians) - diameter);
											//int newZ = (int)(centreZ + 7*count2*Math.cos(radians) - diameter);
											g.fillOval(newX + (int)a.getSize()/2, newY + (int)a.getSize()/2, (int)a.getSize(), (int)a.getSize());
											
											a.setDroneX(newX, count);
											a.setDroneY(newY, count);
											//a.setDroneZ(newZ, count);
										}
									}									
								}
								for(int count3 = 1; count3 <= rem; count3++)//add remaining drones to outer ring, distance between drones changes based on the remainder
								{
									count++;
									
									radians = 2*Math.PI*count3/(rem) + (Math.PI/2)*(numberOfRings);
									
									int newX = (int)(centreX + 7*(numberOfRings + 1)*Math.sin(radians) - diameter);
									int newY = (int)(centreY + 7*(numberOfRings + 1)*Math.cos(radians) - diameter);
									//int newZ = (int)(centreZ + 7*(numberOfRings + 1)*Math.cos(radians) - diameter);
									g.fillOval(newX + (int)a.getSize()/2, newY + (int)a.getSize()/2, (int)a.getSize(), (int)a.getSize());
									
									a.setDroneX(newX, count);
									a.setDroneY(newY, count);
									//a.setDroneZ(newZ, count);
								}
							}
						}
					}
					
					if(o instanceof dot)
					{
						dot d = (dot) o;
						g.setColor(this.getColourFromString(d.getColour()));
						g.fillOval((int)(d.getDotX()-d.getSize()/2), (int)(d.getDotY()-d.getSize()/2), (int)d.getSize(), (int)d.getSize());
					}
					
					//If instance of EnemyUnit
					//James Caldwell
					if( o instanceof EnemyUnit )
					{
						// Draw set colour to colour of object and draw oval to represent EnemyUnit object's size in the EnemyUnit object's position
						EnemyUnit b = ( EnemyUnit ) o;
						g.setColor( this.getColourFromString( b.getColour() ));
						g.fillOval(( int )( b.getXPosition() - b.getSize() / 2 ), ( int )( b.getYPosition() - b.getSize() / 2 ), ( int ) b.getSize(), ( int ) b.getSize() );
						// Draws Circle that represents the range of the EnemyUnit objects
						// g.drawOval((int)(b.getXPosition()-b.getEnemyRange()/2), (int)(b.getYPosition()-b.getEnemyRange()/2), (int)b.getEnemyRange(), (int)b.getEnemyRange());
					}
					
					// If instance of Rectangle
					if( o instanceof Rectangle )
					{
						// Draw Rectangle using variables from the Rectangle object
						Rectangle r = ( Rectangle ) o;
						g.setColor( this.getColourFromString( r.getColour() ));
						g.fillRect(( int ) r.getXPosition(), ( int ) r.getYPosition(), ( int ) r.getWidth(), ( int ) r.getHeight() );
					}
					
					//If instance of Base
					if ( o instanceof Base )
					{
						// Draw Base using variables from the Base object
						Base base = ( Base ) o;
						g.setColor( this.getColourFromString( base.getColour() ));
						g.fillOval(( int ) ( base.getXPosition() - base.getSize() / 2 ), ( int )( base.getYPosition() - base.getSize() / 2 ), ( int ) base.getSize(), ( int ) base.getSize() );
					}
					
					//If instance of Transmitter
					//Botond Hamori
					if ( o instanceof Transmitter )
					{
						// Draw Transmitter using variables from the Transmitter object
						Transmitter tr = ( Transmitter ) o;
						g.setColor( this.getColourFromString( tr.getColour() ));
						g.fillOval(( int ) ( tr.getXPosition() - tr.getSize() / 2 ), ( int )( tr.getYPosition() - tr.getSize() / 2 ), ( int ) tr.getSize(), ( int ) tr.getSize() );
					}

					//If instance of Receiver
					if ( o instanceof Receiver )
					{
						// Draw Receiver using variables from the Receiver object
						Receiver rec = ( Receiver ) o;
						g.setColor( this.getColourFromString( rec.getColour() ));
						g.fillOval(( int ) ( rec.getXPosition()- rec.getSize() / 2 ), ( int )( rec.getYPosition() - rec.getSize() / 2 ), ( int ) rec.getSize(), ( int ) rec.getSize() );
					}	
				}

			}
			
			// Draw image
			gr.drawImage( i, this.getInsets().left, this.getInsets().top, this );
			try{ Thread.sleep( 0 ); } catch ( Exception e ) {} 
		}
	}
	
	// Turns String value into colour for objects
	private Color getColourFromString( String col )
	{
		Color colour = Color.WHITE;
		col = col.toUpperCase();
		
		if( col.equals( "BLUE" ))
		{
			colour = Color.BLUE;
		}
		
		if( col.equals( "GRAY" ))
		{
			colour = Color.GRAY;
		}
		
		if( col.equals( "GREEN" ))
		{
			colour = new Color( 21, 198, 33 );
		}
		
		if( col.equals( "RED" ))
		{
			colour = Color.RED;
		}
		
		if( col.equals( "SAND" ))
		{
			colour = new Color( 255, 255, 102 );
		}
		
		return colour;
	}	
	
	// Add object to list of objects
	private void addThing( Object o, double zPosition )
	{
		boolean added = false;

		synchronized ( this )
		{
			// Try to insert this object into the list.
			for ( int i=0; i<things.size(); i++ )
			{
				double l = 0;
				Object obj = things.get( i );

				if ( obj instanceof Swarm )
					l = (( Swarm )obj ).getZPosition();
				
				if ( obj instanceof EnemyUnit )
					l = (( EnemyUnit )obj ).getZPosition();
				
				if ( obj instanceof Rectangle )
					l = (( Rectangle )obj ).getZPosition();
				
				if ( obj instanceof Base )
				{
					l = (( Base )obj ).getZPosition();
				}

				if ( zPosition <= l )
				{
					things.add( i,o );
					added = true;
					break;
				}
			}

			// If there are no items in the list with an equivalent or higher layer, append this object to the end of the list.
			if ( !added )
			{
				things.add( o );
			}
		}
	}

	// Remove object from list
	private void removeObject( Object o )
	{
		synchronized( this )
		{
			things.remove( o );
		}
	}

	// Add Rectangle to object list
	public void addRectangle( Rectangle r )
    {
        this.addThing( r, r.getZPosition() );
    }
	
	// Add Base to object list
	public void addBase( Base base )
	{
		this.addThing( base, base.getZPosition() );
	}

	// Add Transmitter to object list
	public void addTransmitter( Transmitter tr )
	{
		this.addThing( tr, tr.getZPosition() );
	}

	// Add Receiver to object list
	public void addReceiver( Receiver rec )
	{
		this.addThing( rec, rec.getZPosition() );
	}	
	
	// Remove Swarm from object list
	public void removeSwarm( Swarm a )
	{
		this.removeObject( a );
	}
	
	// Remove EnemyUnit from object list
	public void removeEnemyUnit( EnemyUnit b )
	{
		this.removeObject( b );
	}
	
	// Remove Rectangle from object list
	public void removeRectangle( Rectangle r )
	{
		this.removeObject( r );
	}
	
	// Remove Base from object list
	public void removeBase( Base base )
	{
		this.removeObject( base );
	}
	
	// Get Playground height
	public int getPlaygroundHeight()
	{
		return playgroundHeight;
	}

	// Get Playground width
	public int getPlaygroundWidth()
	{
		return playgroundWidth;
	}
	
	// Get canvas height
	private int getCanvasHeight()
    {
        return canvasHeight;
    }
 
	// Get canvas height
    private int getCanvasWidth()
    {
        return canvasWidth;
    }

	// James Caldwell
	// Add EnemyUnit to object list
	public void addEnemyUnit( EnemyUnit e )
    {
        this.addThing( e, e.getZPosition() );
    }
	
	// Add Swarm to object list
	public void addSwarm( Swarm s )
    {
        this.addThing( s, s.getZPosition() );
    }
	
	// Add EnemyUnit to EnemyUnit list for object tracking
	public void addEnemyUnitList( EnemyUnit e )
    {
		enemyList.add( enemyList.size(), e );
    }
	
	// Add Swarm to Swarm list for object tracking
	public void addSwarmList( Swarm s )
    {
		swarmList.add( swarmList.size(), s );
    }

	// Track objects in Playground
	public void trackObjects()
	{
		// For loop that runs through each EnemyUnit in list
		for( int i = 0; i < enemyList.size(); i++ )
		{
			// For loop that runs through each Swarm in list
			for( int j = 0; j < swarmList.size(); j++ )
			{
				// Assign object from list to temporary EnemyUnit and Swarm objects
				EnemyUnit tempEnemy = enemyList.get( i );
				Swarm tempSwarm = swarmList.get( j );

				// If EnemyUnit object is reloaded
				if( tempEnemy.getReloaded() == true )
				{
					// For loop that runs through each Drone in Swarm
					for( int k = tempSwarm.getArraySize() - 1; k >= 0; k-- )
					{
						// If EnemyUnit object is reloaded
						if( tempEnemy.getReloaded() == true )
						{							
							// Uses number of drones to calculate number of drones in each ring and number of rings
							double fraction = ( tempSwarm.getArraySize() - 1 ) / dronesInRing;
							int numberOfRings = ( int ) Math.round( fraction );
							int rem = drones % dronesInRing;
							int droneToComp = 0;
							
							// Gets drone on opposite side of swarm to compare distances
							// If the number of drones is more than half of a ring of drones
							if( tempSwarm.getArraySize() > ( dronesInRing / 2 ) )
							{
								// If the number of rings is less than or equal to 1
								if( numberOfRings <= 1 )
								{
									// If the remainder is not 0
									if( rem != 0 )
									{
										// Gets position of drone and finds drone on opposite side
										if( k >= (( numberOfRings - 1 ) * dronesInRing ) + 1 )
										{
											droneToComp = k - ( int )( Math.floor( dronesInRing / 2 ));
										}
										
										else
										{
											droneToComp = k + ( int )( Math.floor( dronesInRing / 2 ));
										}
									}
									
									else
									{
										// Gets position of drone and finds drone on opposite side
										if( k + ( Math.floor( dronesInRing / 2 )) > ( numberOfRings * dronesInRing ) + 1 )
										{
											droneToComp = k - ( int )( Math.floor( dronesInRing / 2 ));
										}
										
										else
										{
											droneToComp = k + ( int )( Math.floor( dronesInRing / 2 ));
										}
									}
								}
								
								// If there are more than 1 ring of drones
								else
								{
									// Gets position of drone and finds drone on opposite side
									if( k + ( Math.floor( dronesInRing / 2 )) > ( dronesInRing ) + 1 )
									{
										
										if( k - ( int )( Math.floor( dronesInRing / 2 )) < 0 )
										{
											droneToComp = 0;
										}
										
										else
										{
											droneToComp = k - ( int )( Math.floor( dronesInRing / 2 ));
										}
										
									}
									
									else
									{	
										droneToComp = k + ( int )( Math.floor( dronesInRing / 2 ));
									}
								}

								// Get Drone k from for loop at check range against EnemyUnit
								tempSwarm.getSingleDrone( k );
								tempEnemy.checkRange( tempSwarm.getDroneX( k ), tempSwarm.getDroneY( k ), tempSwarm.getDroneZ( k ) );
							}
							
							// Else compare drone in middle
							else
							{
								droneToComp = 0;
							}
											
							// If original Drone is in range
							if( tempEnemy.getGotTarget() == true )
							{
								// If Drone to compare isn't 0
								if( droneToComp != 0 )
								{
									// Compare location of both Drones
									tempSwarm.getSingleDrone( droneToComp );
									tempEnemy.compDrones( tempSwarm.getDroneX( droneToComp ), tempSwarm.getDroneY( droneToComp ), tempSwarm.getDroneZ( droneToComp ) );
								}
			
								// EnemyUnit shoots Drone
								tempEnemy.shoot();
								
								// If shot hits
								if( tempEnemy.getHit() == true )
								{
									// Kill Drone that was target
									if( tempEnemy.getFirstCheck() == true )
									{
										tempSwarm.removeDrone( k );
									}
									
									else
									{
										tempSwarm.removeDrone( droneToComp );
									}
									
									// Set hit to false and start reload process
									tempEnemy.setHit( false );
									tempSwarm.setShot( true );
								}
							}
						}
					}	
				}
				
				// Set times for reloading 
				else
				{
					// If object has reloaded set reloaded to true
					if( tempEnemy.getTimeShot() + ( tempEnemy.getRPMLong() ) <= System.nanoTime() )
					{
						tempEnemy.setReloaded( true );
					}
				}
			}
		}
	}	
}