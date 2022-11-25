/**
 * This file handles the objects on the game board and their data.
 * 
 * @author Benjamin Namo
 *
 */
public class MyObject implements MyObjectADT {
	
	private int id;						//private variables
	private int width;
	private int height;
	private String type;
	private Location pos;
	private BinarySearchTree tree;
	
	/**
	 * Constructor for object
	 * 
	 * @param id - identifier of this object
	 * @param width - width of enclosing rectangle for this object
	 * @param height - height of enclosing rentangle fot this object
	 * @param type - type of object (fixed,user,computer,target)
	 * @param pos - locus of object
	 */
	public MyObject (int id, int width, int height, String type, Location pos) {
		
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.pos = pos;
		tree = new BinarySearchTree();	//create empty binary tree
		
	}
	

	/**
	 * Getter method for width of object
	 * 
	 * @return width of this object
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Getter method for height of object
	 * 
	 * @return height of this object
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Getter method for type of object
	 * 
	 * @return type of this object
	 */
	public String getType() {
		return type;
	}

	/**
	 * Getter method for Id of object
	 * 
	 * @return Id of this object
	 */
	public int getId() {
		return id;
	}

	/**
	 * Getter method for locus of object
	 * 
	 * @return locus of this object
	 */
	public Location getLocus() {
		return pos;
	}

	/**
	 * Setter method for locus of object
	 * 
	 * @param value - the locus we want to place into object's pos variable
	 */
	public void setLocus(Location value) {
		this.pos = value;
		
	}

	/**
	 * Setter method for type of object
	 * 
	 * @param t - the string type we want to place into object's type variable
	 */
	public void setType(String t) {
		this.type = t;
		
	}

	/**
	 * Inserts pix into the BST associated with this object.
	 * Throws DuplicatedKeyException if an error occurs when inserting. 
	 * 
	 * @param pix - the pel object we wish to insert into the BST
	 * 
	 */
	public void addPel(Pel pix) throws DuplicatedKeyException {
		
		tree.put(tree.getRoot(),pix);
	}

	/**
	 * Method to determine if a MyObject fig will intersect with another this object.
	 * 
	 * @param fig - myObject we are checking
	 * 
	 */
	public boolean intersects(MyObject fig) {
		
		if (this.pos.getx() > fig.getLocus().getx() + fig.getWidth()) {		//if the position of x is greater than fig's x + fig's width
			return false;													//we know there isn't an overlap width wise (formula provided)
		}
		
		if (fig.getLocus().getx() > this.pos.getx() + this.getWidth()) {	//oppositely true as well.
			return false;
		}
			
		if (this.pos.gety() > fig.getLocus().gety() + fig.getHeight()) {	//if the position of y is greater than fig's y + figs height
			return false;													//we know there isn't an overlap height wise (formula provided)
		}
		
		if (fig.getLocus().gety() > this.pos.gety() + this.getHeight()) {	//opposite true as well.
			return false;
		}
	
		return true;														//if none of the above hold true, there was an intersection and we return true.
	}

}
