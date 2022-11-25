/**
 * This class implements an ordered dictionary using a binary search tree.
 * Each node of the tree will store a Pel object.
 * The attribute Location of the Pel object stored in a node will be its key attribute.
 * 
 * @author Benjamin Namo
 *
 */


public class BinarySearchTree implements BinarySearchTreeADT{

	private BNode root;

	/**
	 * Constructor that creates a tree who's root is a leaf node.
	 * 
	 */
	public BinarySearchTree() {
		
		root = new BNode();
	}
	
	/**
	 * Helper method to find a pel object storing key in bst
	 * 
	 * @param r - root of bst we are searching
	 * @param key - key of node we are looking for
	 * 
	 * @return root if node doesn't exist, or node if it does exist
	 */
	private BNode locate(BNode r, Location key) {				//gets pel object storing key, r is root
		
		if (r.isLeaf()) {
			return r;
		}
		
		else {
			
			if (r.getData().getLocus().compareTo(key) == 0) {	//if found node, return the pel value
				
				return r;
			}
			
			if (r.getData().getLocus().compareTo(key) < 0) {	//if key is less, traverse left
				
				return locate(r.rightChild(),key);
			}

			if (r.getData().getLocus().compareTo(key) > 0) {	//if key is more, traverse right
	
				return locate(r.leftChild(),key);
			}
			
		}
		
		return r;	//if key doesn't exist in the tree, return root					
	}

	/**
	 * Returns the Pel object storing the given key, if the key is stored in the tree; returns null otherwise.
	 * 
	 * @param r - root of the bst
	 * @param key - key of node we are trying to get
	 * @return - pel object storing key if found, null otherwise
	 */
	public Pel get(BNode r, Location key) {						//gets pel object storing key, r is root
		
		BNode getPel = locate(r,key);							//use locate helper method
		
		if (!getPel.isLeaf()) {									//if locate is successful
			
			return getPel.getData();							//return the value at that node
		}
		
		else {
			
			return null;										//otherwise return null
		}
		
	}

	/**
	 * Inserts newData in the tree if no data item with the same key is already there.
	 * if a node already stores the same key, the algorithm throws a DuplicatedKeyException.
	 * 
	 * @param r - root of the bst
	 * @param newData - data we are inserting into the bst
	 */
	public void put(BNode r, Pel newData) throws DuplicatedKeyException {
		
		//first we will use our helper function to see if it exists and isn't a leaf
		BNode node = locate(r, newData.getLocus());
		
		//check if it has non-leaf children, if it does it is a duplicate and we throw exception
		if ((node.leftChild() != null) || (node.rightChild() != null)) { 
			
			throw new DuplicatedKeyException("Error: duplicated key");	
		}
		
		else {	//if it isn't a duplicate
		
			node.setContent(newData);		//copy over data
			BNode leftChild = new BNode();	//create children
			BNode rightChild = new BNode();
			
			node.setLeftChild(leftChild); 	//assign children
			node.setRightChild(rightChild);
			 
			leftChild.setParent(node);	//assign the children's parent 
			rightChild.setParent(node);

		}
	}

	/**
	 * Removes the data item with the given key
	 * Throws an InexistentKeyException if the key doesn't exist
	 * 
	 * @param r - root of the bst
	 * @param key - key of the node we want to remove
	 */
	public void remove(BNode r, Location key) throws InexistentKeyException {

		BNode removeNode = locate(r,key);
		
		if (removeNode.isLeaf()) {				//if we can't find the key, throw exception
			
			throw new InexistentKeyException("Error: Key does not exist");
		}
		
		else {									//if we found key
	
			BNode rLeft = removeNode.leftChild();
			BNode rRight = removeNode.rightChild();
			BNode rParent = removeNode.parent();
			
			//if there is a leaf child on left, right child takes spot
			if (rLeft.isLeaf()) {
			
				if (rParent == null) {			//if parent is null, we're removing the root
					
					this.root = rRight;
					rRight.setParent(null);
				}
				
				else {
				
					if (rParent.leftChild().equals(removeNode)) {	//if the node we are removing was the left node
					
						rParent.setLeftChild(rRight);				//set parents right (larger) child as removed nodes right child
						rRight.setParent(rParent);
					}
				
					else {
					
						rParent.setRightChild(rRight);				//if it was the right node, we still want right child
						rRight.setParent(rParent);
					}
				}
				
			}
			//if there is a leaf child on right, left child takes spot
			if (rRight.isLeaf()) {

				if (rParent == null) {			//if parent is null, we're removing the root
					
					this.root = rLeft;
					rLeft.setParent(null);	
				}
				
				else {
				
					if (rParent.leftChild().equals(removeNode)) {	//if the node we are removing was the left node
					
						rParent.setLeftChild(rLeft);				//set parents left = node
						rLeft.setParent(rParent);
					}
					
					else {
					
					rParent.setRightChild(rLeft);				//if it was the right node, we must still set to left
					rLeft.setParent(rParent);
					}
				}
			}
			
			else {	//none of children are leafs
	
				while(rRight.leftChild().isLeaf() == false) {
					rRight = rRight.leftChild();
				}
				
				removeNode.setContent(rRight.getData());
				remove(rRight, rRight.getData().getLocus());
				
			}
		}
	}

	/**
	 * Returns the Pel object with the smallest key larger than the given one
	 * 
	 * @param r - root of bst
	 * @param key - key that we will find successor of
	 * @return object with smallest key larger than given one if found, null if not found
	 */
	public Pel successor(BNode r, Location key) {	//find the smallest value larger than key, return null if no such value																
		
		//first we get the actual node (should change this since node can potentially not exist)
		BNode node = locate(r,key);
		
		if (r.isLeaf()) {
			return null;
		}
		
		else {
		
		//if node isn't a leaf and has a right side child (internal)
		if ((node.isLeaf() == false)&&(node.rightChild().isLeaf()==false)){
			//we find the smallest value in the right child subtree
			return findSmallest(node.rightChild()).getData();
		}
		
			else {	//otherwise, if not an internal
			
				BNode parent = node.parent();
			
				//we will traverse from node until it isn't a right child
				while ((parent != r) && (node == parent.rightChild())){
					node = parent;
					parent = parent.parent();
				}
			
				if (node == r) {
					return null;
				}
			
				else {
					//either we find original node or we reach null
					return parent.getData();
				}
			}
		}
	}

	/**
	 * Returns the Pel object with the largest key smaller than the given one 
	 * 
	 * @param r - root of bst
	 * @param key - key that we will find predecessor of
	 * @return object with largest key smaller than given one if found, null if not found
	 */
	public Pel predecessor(BNode r, Location key) {
		
		//first we get the actual node (should change this since node can potentially not exist)
		BNode node = locate(r,key);
				
		//if node isnt a leaf and has a left side child (internal)
		if ((node.isLeaf() == false)&&(node.leftChild().isLeaf()==false)){
			//we find the smallest value in the right child subtree
			return findSmallest(node.leftChild()).getData();
		}
				
		else {	//otherwise, if not an internal
					
			BNode parent = node.parent();
					
			//we will traverse from node until it isn't a right child
			while ((parent != r)&&(node == parent.leftChild())){
				node = parent;
				parent = parent.parent();
			}
					
			//either we find original node or we reach null
			return parent.getData();
		}
	}

	/**
	 * Helper function that finds the node in the tree with the smallest key
	 * 
	 * @param r - root of bst
	 * @return smallest node in the bst
	 */
	private BNode findSmallest(BNode r) {
		
		if (r.isLeaf()) {								//if tree is empty
			return null;
		}
		
		else {
		
			while(r.leftChild().isLeaf() == false) {	//while we haven't reached end of tree
				r = r.leftChild();
			}
	
		return r;
		}
		
	}
	
	/**
	 * Returns Pel object in the tree with the smallest key.
	 * Throws EmptyTreeException if the tree does not contain any data.
	 * 
	 * @param r - root of the bst
	 * @return the pel object in the tree with the smallest key
	 */
	public Pel smallest(BNode r) throws EmptyTreeException {

		BNode smallest = findSmallest(r);
		
		if (smallest == null) {
			
			throw new EmptyTreeException("Error: Empty Tree");
		}
		
		else {
			return smallest.getData();
		}
	
	}
	/**
	 * Returns Pel object in the tree with the largest key.
	 * Throws EmptyTreeException if the tree does not contain any data.
	 * 
	 * @param r - root of the bst
	 * @return the pel object in the tree with the largest key
	 */
	public Pel largest(BNode r) throws EmptyTreeException {
		
		if (r.isLeaf()) {
			throw new EmptyTreeException ("Error: Empty Tree at Node Location");
		}
		
		while(r.rightChild().isLeaf() == false) {
			r = r.rightChild();
		}
		
		return r.getData();
	}
	
	/**
	 * Getter that returns the root of the binary tree.
	 * 
	 * @return - root of bst
	 */
	public BNode getRoot() {
		
		return root;
	}

	
	

}
