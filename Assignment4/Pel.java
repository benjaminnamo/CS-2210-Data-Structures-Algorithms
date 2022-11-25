/**
 * This class represents the data items to be stored in the nodes of the binary search tree.
 * This class has two instance variables: a Location and an int color. 
 * 
 * @author Benjamin Namo
 * 
 *
 */
public class Pel {
	
	private Location p;
	private int color;

	/**
	 * A constructor which initializes the new Pel with the specified coordinates and color. 
	 * Location p is the key attribute for a Pel object.
	 * 
	 * @param p - key attribute for pel object
	 * @param color
	 */
	public Pel(Location p, int color) {
		
		this.p = p;
		this.color = color;
		
	}
	
	/**
	 * Getter method for getting location P attribute from pel object
	 * 
	 * @return - location P attribute from pel object
	 */
	public Location getLocus() {
		
		return this.p;
		
	}
	
	/**
	 * Getter method for getting int color attribute
	 * 
	 * @return - int color attribute from pel object
	 */
	public int getColor() {
		
		return this.color;
				
	}

}
