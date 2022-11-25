/**
 * This class implements all the auxiliary methods from class Dictionary and class Record needed by the algorithm that plays the game. 
 * 
 * @author Benjamin Namo
 *
 *
 */
public class Evaluate {
	
	
	private int size;						//declaring variables
	private int tilesToWin;
	private int maxLevels;
	
	private char[][] gameBoard;				//declaring gameboard, 2d char array
	
	public Evaluate (int size, int tilesToWin, int maxLevels) {
		
		this.size = size;
		this.tilesToWin = tilesToWin;
		this.maxLevels = maxLevels;
		
		
		gameBoard = new char[size][size];	//initializing gameboard
		
		for (int i=0; i<size; i++) {		//going through all elements of 2d array
			for (int j=0; j<size; j++) {
				gameBoard[i][j]='e';		//assigning all elements as empty or 'e'
			}
		}
	
	}
	
	/**
	 * returns an empty Dictionary of inputed size
	 * 
	 * @return - dictionary of size 9973
	 * 
	 */
	public Dictionary createDictionary() {
		
		Dictionary dictionary = new Dictionary(9973);
		return dictionary;
	}
	
	/**
	 * This method represents the board representation of the gameBoard and checks to see if it exists in the dictionary. If it exists, return it. Otherwise, return null.
	 * 
	 * @param dict
	 * @return
	 */
	public Record repeatedState(Dictionary dict) {
		
		String boardRepresentation = "";					//initialize string
		
		for (int i=0; i<size; i++) {						//first we will iterate through the board
			for (int j=0; j<size; j++) {
				boardRepresentation += gameBoard[i][j];		//as we iterate through, we are creating a string representation
			}
		}
		
		Record record = dict.get(boardRepresentation);
		
		return record;
		
	}
	
	/**
	 * This method represents the board representation as a string then creates a record holding it and and stores in dictionary.
	 * 
	 * @param dict
	 * @param score
	 * @param level
	 */
	public void insertState(Dictionary dict, int score, int level) {
		
		String key = "";
		
		for (int i=0; i<size; i++) {						//first we will iterate through the board
			for (int j=0; j<size; j++) {
				key += gameBoard[i][j];						//as we iterate through, we are creating a string representation
			}
		}
		
		Record rec = new Record(key, score, level);
		
		dict.put(rec);
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @param symbol
	 */
	public void storePlay(int row, int col, char symbol) {	//stores symbol in gameBoard [row][col]
		
		gameBoard[row][col] = symbol;
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean squareIsEmpty (int row, int col) {		//returns true if 'e'
		
		if (gameBoard[row][col] == 'e') {
			return true;
		}
		
		else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean tileOfComputer (int row, int col) {		//returns true if 'c'
		
		if (gameBoard[row][col] == 'c') {
			return true;
		}
		
		else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean tileOfHuman (int row, int col) {			//returns true if 'h'
		
		if (gameBoard[row][col] == 'h') {
			return true;
		}
		
		else {
			return false;
		}
	}
	
	/**
	 * Returns true if there are the required number of adjacent tiles of type symbol in the same row, column, or diagonal of gameBoard; otherwise it returns false.
	 * 
	 * @param symbol
	 * @return
	 */
	public boolean wins(char symbol) {						//returns true if required number of adjacent tiles in row,col,diagonal of gameboard
		
		int count = 0 ;
		boolean isWin = false;
		
		
		for (int j=0; j < size; j++) {						//go through all rows horizontally
			count = 0;
			for (int i=0; i<size; i++) {
				if (gameBoard[j][i] == symbol) {			//if symbol is found
					count += 1;								//add 1 to count
				}
				
				if (count == tilesToWin) {					//if count reaches tilesToWin
					isWin = true;							//return a win
					return isWin;
				}
				
				
				if (gameBoard[j][i] != symbol) {			//if the next symbol isnt the same, or we've reached the end of the row 
				count = 0;						    		//reset count to 0
				
				}
				
			}
		}
		
		
		for (int i=0; i<size; i++) {						//go through columns vertically
			count=0;
			for(int j=0; j<size; j++) {
				
				if (gameBoard[j][i] == symbol) {
					count +=1;
				}	
				if (count == tilesToWin) {					//if count reaches tilesToWin
					isWin = true;							//return a win
					return isWin;
				}
				
				if (gameBoard[j][i] != symbol) {			//if the symbol isnt the same 
					count = 0;								//reset count to 0
				}
				
			}
		}
		
			
		 for (int j = 0; j <= size + size - 2; j++) {		//diagonal right to left
			 count=0;
	            for (int k = 0; k <= j; k++) { 				// columns
	                int l = j - k; 							// rows
	                if (l < size && k < size) {
	                	if (gameBoard[l][k] == symbol) {
	    					count +=1;
	    				}
	                	
	                	if (count == tilesToWin) {			//if count reaches tilesToWin
	    					isWin = true;					//return a win
	    					return isWin;
	    				}
	                	
	                	if (gameBoard[l][k] != symbol) {	//if the symbol isnt the same 
	    					count = 0;						//reset count to 0
	    				}
	                    
	                }
	            }
	   
	        }
		 
		 for (int j = 0; j <= size + size - 2; j++) {		//diagonal left to right
			 count=0;
	            for (int k = 0; k <= j; k++) { 				//columns
	                int l = j - k; 							//rows
	                
	                int mirror = size-l;					//mirroring so that we can do the other side
	             
	                if (mirror >= 0 && mirror < size && k < size) {
	                	
	                	if (gameBoard[mirror][k] == symbol) {
	    					count +=1;
	    				}
	                	
	                	if (count == tilesToWin) {				//if count reaches tilesToWin
	    					isWin = true;						//return a win
	    					return isWin;
	    				}
	                	
	                	if (gameBoard[mirror][k] != symbol) {	//if the symbol isn't the same 
	    					count = 0;							//reset count to 0
	    				}
	                    
	                }
	            }
	   
	        }
		
		return isWin;
	}
	
	/**
	 * Returns true if there are no empty positions left in gameBoard; otherwise it returns false.
	 * 
	 * @return draw - boolean, if true then there was a draw, otherwise false
	 */
	public boolean isDraw() { 								//returns true if no empty positions left and no win
		
		boolean draw=true;
		
		for (int i=0; i<size; i++) {						//first we will iterate through the board
			for (int j=0; j<size; j++) {
				if (gameBoard[i][j] == 'e') {				//if there is any empty spaces, or 'e'
					draw=false;								//return false (the board is not full)
					return draw;
				}
			}
		}
		
		return draw;										//if the board is full and there is no wins, return true
	}
	
	/**
	 * This method implements the win method and assigns the corresponding score.
	 * 
	 * @return score - the score of the board
	 */
	public int evalBoard() {								
		
		int score;
		
		if (wins('c')) {									//if the computer wins, assign score of 3
			score = 3;
		}
		
		if (wins('h')) {									//if the human wins, assign score of 0
			score = 0;
		}
		
		if (isDraw()) {										//if a draw, assign score of 2
			score = 2;
		}
		
		else {												//if the game is undecided, assign score of 1
			score=1;
		}
		
		return score;
	}
	

}
