/**
 * This class represents the nodes of the binary search tree.
 * Each node will store an object of the class Pel and it
 * must have references to its left child, its right child, and its parent.
 * 
 * @author Benjamin Namo
 *
 */


public class BNode {

	private BNode parent;
	private BNode left;
	private BNode right;
	private Pel value;

	/**
	 * A constructor for the class. 
	 * Stores the Pel value in the node and sets left child, right child,
	 * and parent to the specified values.
	 * 
	 * @param value - Pel value of node
	 * @param left	- left child of node
	 * @param right	- right child of node
	 * @param parent - parent of node
	 */
	public BNode(Pel value, BNode left, BNode right, BNode parent) {
		
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
		
	}
	
	/**
	 * Constructor for leaf node. All values set to null.
	 * 
	 */
	public BNode() {
		
		value = null;
		left = null;
		right = null;
		parent = null;
	}
	
	/**
	 * Getter for parent node
	 * 
	 * @return - parent node of this node
	 */
	public BNode parent() {
	
		return this.parent;
	}
	
	/**
	 * Sets the parent of this node to the specified value.
	 * 
	 * @param newParent - node to set as parent
	 */
	public void setParent(BNode newParent) {
		
		this.parent = newParent;
	}
	
	/**
	 * Sets the left child of this node to the specified value.
	 * 
	 * @param p - node to set as left child
	 */
	public void setLeftChild(BNode p) {
		
		this.left = p;
	}
	
	/**
	 * Sets the right child of this node to the specified value
	 * 
	 * @param p - node to set as right child
	 */
	public void setRightChild(BNode p) {
		
		this.right = p;
	}
	
	/**
	 * Stores the given Pel object in this node.
	 * 
	 * @param value - pel value to store in Node
	 */
	public void setContent (Pel value) {
		
		this.value = value;
	}
	
	/**
	 * Returns true if this node is a leaf; returns false otherwise
	 * 
	 * @return true if leaf/false if internal node
	 */
	public boolean isLeaf() {
		
		 if (this.left == null && this.right == null && this.value == null) {
			 
			 return true;
		 }
		 
		 else {
		        
		return false;
		 }
	}
	
	/**
	 * Getter for pel value in Node
	 * 
	 * @return - the pel value stored in the node
	 */
	public Pel getData() {
		
		return this.value;
	}
	
	/**
	 * Getter for left child of node
	 * 
	 * @return - the left child of the node
	 */
	public BNode leftChild() {
		
		return this.left;
	}
	
	/**
	 * Getter for right child of node
	 * 
	 * @return - the right child of the node
	 */
	public BNode rightChild() {
		
		return this.right;
	}
	
}
