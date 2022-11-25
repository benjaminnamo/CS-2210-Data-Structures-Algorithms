/**
 * This class implements a dictionary using a hash table in which collisions are resolved using separate chaining. The hash table stores objects of the class Node, and within them objects of the class Record.
 * 
 * @author Benjamin Namo
 * 
 *
 */
public class Dictionary implements DictionaryADT {
	
	/**
	 * This is a Node class, which will hold our Record objects. This is required for separate chaining.
	 *
	 */
	private class Node{							//class Node, this is what our hashTable will be comprised of
		
		public Record rec;
		public Node next;
		
		private Node(Record rec) {				//creating Node
			
			this.rec = rec;
			this.next = null;
		}
		
		
		private void setNext(Node node) {		//function for adding a node next
			this.next = node;
		}
		
		private Node getNext() {				//function for returning the next node in the linked list
			return this.next;
		}
		
		
	}
	
	
	private int size;							//declaring the variable that will hold the size of the dictionary
	
	public Node[] hashTable;					//declaring the hash table of type Node[]
	
	/**
	 * Constructor for Dictionary(hashtable)
	 * 
	 * @param size - the size of the hashTable
	 * 
	 */
	public Dictionary(int size) {				
		
		this.size = size;
		hashTable = new Node[this.size]; 
	}
	
	/**
	 * Inserts the given Record object referenced by rec in the dictionary.
	 * 
	 * This method throws a DuplicatedKeyException if the string stored in the object referenced by rec is already in the dictionary.
	 *
	 */
	public int put(Record rec) throws DuplicatedKeyException{
		
		Node current;
		Node newNode;
		
		int index = hashFunction(rec);			//getting the index for the record
		
		
		
		if (hashTable[index] == null){			//if the index we are putting rec at is empty
			
			newNode = new Node(rec);
			
			hashTable[index] = newNode;
			
			return 0;							//since no collision, return 0
			
		}
		
		if ((hashTable[index].rec.key)!=rec.key) {//if the index we are putting rec at is populated
			
			
			current = hashTable[index];			//set current node to the index we are at
			
			while (current.getNext() != null) {	//traverse through the chain until we find next=empty
				
				current = current.getNext();	//we are now at the final part of the chain
				
			}
			
			newNode = new Node(rec);			//set newNode with our input data
			
			current.setNext(newNode);			//add the new node to the chain	
			
			return 1;							//collision, so return 1
			
			
		}
		
		else {
			
			throw new DuplicatedKeyException("This key is already in the Dictionary");
		}
		
		
	}
	/**
	 * This is the polynomial hash function that takes in a record, and converts it to an index position in the hash table, using it's key.
	 * 
	 * @param rec - the record to be stored in the hash table
	 * @return	index - the index position of where the record is to be stored.
	 * 
	 */
	private int hashFunction(Record rec) {
		
	int base = 13;										//determined through testing
	String indexString = rec.getKey();					//getting record key
	int index = 0;
	
	for(int i=0; i<indexString.length(); i++ ) {		//iterating through characters in string
		
		int strCode = indexString.charAt(i);		
		index = (index * base + strCode) % this.size;	//polynomial, recursive function 
		
	}

	return index;
		
	}

	/**
	 * Removes the Record object containing string key from the dictionary. Throws an InexistentKeyException if the hash table does not store any Record object with the given key value.
	 * 
	 * @param key - the key of the record object we are attempting to remove from the dictionary.
	 * 
	 */
	public void remove(String key) throws InexistentKeyException {
		
		Record temp = new Record(key,0,0);				//first we create a temporary record object holding this key							
		int hashKey = hashFunction(temp);				//then we get the hashKey/index using the hashFunction
		
		
		Node head = hashTable[hashKey];					//setting node Head to node at index hashKey in hashtable
		Node prev = null;								//setting node previous to null
		
		if (hashTable[hashKey]==null) {					//if the key doesn't exist, throw exception
			
			throw new InexistentKeyException("This key is not in the dictionary.");
		}
			
		while(head != null) {							//while the node exists
			
			if (head.rec.key.equals(key)) {				//if it matches the key we are looking for
				
				head.setNext(head.getNext());			//set head to next
				
				break;
			}
			
			else {
				prev = head;							//set prev to head
				head = head.getNext();					//make head next and remove from chain
			}
				
		}
						
	}
			
	/**
	 *  A method which returns the Record object stored in the hash table containing the given key value, or null if no Record object stored in the hash table contains the given key value.
	 * 
	 * @param key - the key of the record object we are attempting to retrieve from the dictionary.
	 * 
	 */
	public Record get(String key) {
		
		Record temp = new Record(key,0,0);				//create a temporary variable holding the key
		int hashKey = hashFunction(temp);				//apply hashFunction to record to temp to get hashkey
		
		Node head;										//setting node Head to node at index hashKey in hashtable
		head = hashTable[hashKey];						//finding node we are looking for
		
		while (head != null) {							//while the node exists
			
			if ((head.rec.key).equals(key)) {			//if the key is the same
				
				return head.rec;						//return the record
			}
			
			else {
				head = head.getNext();					//other wise, go through chain
			}
			
		}
		
		
		return null;
	}

	/**
	 * Returns the number of Record objects stored in the hash table.
	 * 
	 */
	public int numRecords() {							
		
		int totalRecords=0;								//counter for records
		Node current = null;							//create node current
		
		for (int i=0; i<this.size;i++) {				//iterate through all indexes of table
			
			if (hashTable[i]!= null) {					//if index isn't null(empty)
				
				current=hashTable[i];					//set current to that index
				
				while(current!=null) {					//while the chain node isn't empty
					
					totalRecords += 1;					//increment counter
					current = current.getNext();		//move to next item in chain
				}
				
			}
			
		}
		
		return 0;
	}
	
}
