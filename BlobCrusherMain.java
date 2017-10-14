import java.io.IOException;

/*
BlobCrusher
Author:William Tang
Date: March 22,2016
Java, jre7, Eclipse
=================================================================
Problem Definition 
- Required to create a guessing game where the user enters the x and y coordinate 
of a blob on a 40x40 quadrant. By hitting any coordinate of a blob, 
the rest of the blob will be "crushed" and shown on the grid with a "#". There are 2 blobs in each 
20x20 quadrant needed to be crushed and the game ends when the user misses 4 times or crushes all 8 blobs.
Input 
- The user inputs the x and y coordinate of the blobs until they miss 4 times.
Output 
- Output the grid with the blobs crushed, the amount of lives the user has left, and the total amount of blobs the user has crushed.
Process 
- Compare the coordinates the user inputs with the corresponding value in the 
gameVal array (which stores the value of the hidden asterisks)
If the value of that value of the spot on the grid that the user guessed contains the blob, the method will check for 
more blobs around it by checking in all 8 directions.If the user's guess is incorrect, the # of wrong guesses will be incremented.
If the user's guess is correct, the # of blobs crushed will be incremented. The recursive method of the game itself will end 
once the wrongGuess counter reaches 4 or the blobs crushed counter reaches 8.
=================================================================
<Class>
This class creates a template for the BlobCrusherFinal object.
=================================================================
 */ 

public class BlobCrusherMain {

	/**Main Method:
	 * This procedural method is called automatically and is used to organize the calling of other methods defined in the class
	 * Instantiates BlobCrusher Class
	 * Initializes grid values by calling initGame method
	 * Initializes game values by calling initGrid method
	 * Displays instructions to user through outputInstructions method
	 * Displays grid to user through drawGrid method
	 * Starts game through the playGame recursive method
	 * @param args <type String>
	 * @throws IO Exception	
	 * @return void
*/
	
	
	public static void main(String[] args) throws IOException {
		BlobCrusher bc = new BlobCrusher();
		bc.initGame(0,0);
		bc.initGrid(0,0);
		bc.outputInstructions();
		bc.drawGrid(0,0);
		bc.playGame();
	}//end main method
}
