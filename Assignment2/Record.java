/**
 * This class represents the records that will be stored in the hash table, implemented in class Dictionary.java
 * 
 * @author Benjamin Namo
 * 
 * 
 */

public class Record {
	
	String key;											//declaring variables
	int score;
	int level;
	
	/**
	 * Creates a record of type Record.
	 * 
	 * @param key										//string representation of the board
	 * @param score										//score of the match
	 * @param level										//level of the match
	 */
	public Record(String key, int score, int level) {	//constructor
		
		this.key = key;									
		this.score = score;
		this.level = level;
		
	}
	/**
	 * Returns the key string stored in a Record object
	 * 
	 * @return - key
	 */
	public String getKey() {							//getter for key
		
		return this.key;
	}
	/**
	 * Returns the score integer stored in a Record object.
	 * 
	 * @return - score
	 */
	public int getScore() {								//getter for score
		
		return this.score;
	}
	/**
	 * Returns the level integer stored in a Record object
	 * 
	 * @return - level
	 */
	public int getLevel() {								//getter for level
		
		return this.level;
	}
	

}
