/**
 * This class represents the location (x, y) of a pel
 * 
 * @author Benjamin Namo
 * 
 *
 */

public class Location {
	

	private int x,y;
	
	/**
	 * Constructor for location object
	 * @param x
	 * @param y
 	*/
	public Location(int x, int y) {					//A constructor that initializes this Location object with the specified coordinates.
		
		this.x = x;
		this.y = y;
	}

	/**
	 * Getter for x coordinate in location object
	 * @return x coordinate of this location
	 */
	public int getx() {								//Returns the x coordinate of this Location
		return this.x;
	}
	
	/**
	 * Getter for y coordinate in location object
	 * @return y coordinate of this location
	 */
	public int gety() {								//Returns the y coordinate of this Location.
		return this.y;
	}
	
	/**
	 * Compares the coordinates of this location object to location object
	 * @param p location object we are comparing to
	 * @return	1 if greater than, -1 if less than, 0 if equal
	 */
	public int compareTo(Location p) {
		
		if ((this.gety() > p.gety())||((this.gety() == p.gety()) && (this.getx() > p.getx()))){
			
			return 1;
		
		}
		
		if ((this.getx() == p.getx()) && (this.gety()==p.gety())) {
			
			return 0;
			
		}
		
		if ((this.gety() < p.gety())||((this.gety() == p.gety()) && (this.getx() < p.getx()))){
			
			return -1;
			
		}
		return 2;
		
		
		
	}
	
}
