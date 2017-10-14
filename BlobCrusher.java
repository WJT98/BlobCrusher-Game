/*=================================================================
BlobCrusher
Author:William Tang
Date: March 22,2016
Java, jre7, Eclipse
=================================================================
Problem Definition 
- Required to create a guessing game where the user enters the x and y coordinate 
of a blob on a 40x40 quadrant. By hitting any coordinate of a blob, 
the rest of the blob will be "crushed" and shown on the grid with a "#". 
- There are 2 blobs in each 20x20 quadrant needed to be crushed and the game ends when the user misses 4 times or crushes all 8 blobs.
Input 
- The user inputs the x and y coordinate of the blobs until they miss 4 times.
Output 
- Output the grid with the blobs crushed, the amount of lives the user has left, and the total amount of blobs the user has crushed.
Process 
- Compare the coordinates the user inputs with the corresponding value in the 
gameVal array (which stores the value of the hidden asterisks)
- If the value of that value of the spot on the grid that the user guessed contains the blob, the method will check for 
more blobs around it by checking in all 8 directions. If the user's guess is incorrect, the # of wrong guesses will be incremented.
- If the user's guess is correct, the # of blobs crushed will be incremented. The recursive method of the game itself will end 
once the wrongGuess counter reaches 4 or the blobs crushed counter reaches 8.
=================================================================
<Class>
This class creates a template for the BlobCrusherFinal object.
=================================================================
 */ 
//Importing Packages
import java.io.*;
public class BlobCrusher {
	/**List of Class Variables
	 * guess - Keeps track of whether user's guess is incorrect<type boolean>
	 * gridVal- value of a position on the grid which includes the borders and crushed blobs <type char[][]>
	 * gameVal - value of a position on the grid which includes the borders and not yet crushed blobs <type char[][]>
	 * LIVES_ALLOWED - number of lives allowed <type int>
	 * TOTAL_BLOBS - number of blobs to be crushed <type int>
	 * BOARD_SIZE - size of length and width of grid <type int>
	 * Blobs - value of the number of blobs crushed by the user <type int>
	 * wrongGuess - value of wrong guesses made by the user <type int>
	 */
	private boolean guess;
	private char[][] gridVal;
	private char[][] gameVal;
	final int BOARD_SIZE = 43;
	final int LIVES_ALLOWED = 4;
	final int TOTAL_BLOBS = 8;
	private int blobs;
	private int wrongGuess ;


	/**BlobCrusher Class Constructor
	 * Initializes all properties of the object.
	 * Counters (blobs, wrong guesses) are set to 0
	 * Row and column index of arrays set to board length/width
	 */
	public BlobCrusher() {
		gridVal = new char[BOARD_SIZE][BOARD_SIZE];
		gameVal = new char[BOARD_SIZE][BOARD_SIZE];
		blobs = 0;
		wrongGuess =0;
	}


	/**initGrid method:
	 * This method uses recursion to initialize all the values of the 40x40 table that will be shown to the user.
	 * The total array size will be 43x43 as borders '-' + '|' are included in the array.
	 * The actual 40x40 table will be initialized as 1 space (" ")
	 * The recursion process works by calling the method again with the 2nd index being incremented by 1 each time. 
	 * Once the 2nd index reaches the upper limit of 42, the 1st index will be incremented by 1 and the 2nd index will be set to 0.
	 * The recursion continues until it reaches the very last element which will then not recall itself.
	 *
	 * @param row - The coordinates of the y axis which will be the 1st index of the gridVal 2D Array<type int>
	 * @param column - The coordinates of the x axis which will be the 2nd index of the gridVal 2D Array<type int>
	 * @return  void 
	 */
	public void initGrid (int row, int column) {

		if (row == BOARD_SIZE - 1 && column == BOARD_SIZE - 1) {
			gridVal[row][column] = '|';
		}
		else if (column == BOARD_SIZE - 1) { 
			gridVal[row][column] = '|';
			initGrid(row+1,0);//Recursive call with row incremented by 1, column set to 0
		}
		else if (column == 0 || column == 21 || column == BOARD_SIZE - 1) {
			gridVal[row][column] = '|';
			initGrid(row,column+1);//Recursive call with column incremented by 1
		}
		else if (row == 0 || row == 21 || row == BOARD_SIZE - 1) {
			gridVal[row][column] = '-';
			initGrid(row,column+1);//Recursive call with column incremented by 1
		}
		else  {
			gridVal[row][column] = ' ';
			initGrid(row,column+1);//Recursive call with column incremented by 1
		}
	}//end initGrid

	/**initGame method:
	 * This method uses recursion to initialize all the values of the 40x40 table as a single space " ".
	 * Note:The total array size will be 43x43 as borders '-' + '|' are included in the array.
	 * The blobs will be initialized at preset positions with an asterisk '*'
	 * The recursion process works by calling the method again with the 2nd index being incremented by 1 each time. 
	 * Once the 2nd index reaches the upper limit of 42, the 1st index will be incremented by 1 and the 2nd index will be set to 0.
	 * The recursion continues until it reaches the very last element which will then not recall itself.
	 *
	 * @param row - The coordinates of the y axis which will be the 1st index of the gameVal 2D Array<type int>
	 * @param column - The coordinates of the x axis which will be the 2nd index of the gameVal 2D Array<type int>
	 * @return  void 
	 */
	public void initGame (int row, int column) {
		gameVal[row][column] = ' ';
		if (row == BOARD_SIZE -1 && column == BOARD_SIZE -1) { 
			gameVal[1][1]=gameVal[2][2] =gameVal[2][3] =gameVal[3][4] =gameVal[4][3] //Blob 1
			=gameVal[20][20]=gameVal[19][19] =gameVal[19][20] =gameVal[18][19] =gameVal[19][18] //Blob 2 		
			=gameVal[20][22]=gameVal[20][23] =gameVal[20][24] =gameVal[19][22] =gameVal[19][23]=gameVal[18][23] //Blob 3 		
			=gameVal[15][35]=gameVal[16][35] =gameVal[17][35] =gameVal[18][36] =gameVal[19][36]=gameVal[20][37]	//Blob 4	
			=gameVal[30][31]=gameVal[31][32] =gameVal[30][32] =gameVal[30][33] =gameVal[31][33]=gameVal[30][34]	//Blob 5
			=gameVal[41][39]=gameVal[41][40] =gameVal[41][41] =gameVal[40][41] =gameVal[39][41]=gameVal[39][40]	//Blob 6
			=gameVal[30][1]=gameVal[31][2] =gameVal[30][2] =gameVal[30][3] =gameVal[31][4]=gameVal[30][4]	//Blob 7
			=gameVal[30][16]=gameVal[31][16] =gameVal[30][15] =gameVal[30][14] =gameVal[31][15]=gameVal[30][13]	//Blob 8
			='*';
		}
		else if (column ==BOARD_SIZE -1) { 
			initGame(row+1,0); //Recursive call with row incremented by 1
		}
		else  {
			initGame (row,column+1);//Recursive call with column incremented by 1
		}
	}//end initGame

	/**drawGrid method:
	 * This procedural method is used to draw the grid with the gridVal array.
	 * The recursion process works by calling the method again with the 2nd index being incremented by 1 each time. 
	 * Once the 2nd index reaches the upper limit of 42, the 1st index will be incremented by 1 and the 2nd index will be set to 0.
	 * The recursion continues until it reaches the very last element which will then not recall itself.
	 *
	 * @param row - The coordinates of the y axis which will be the 1st index of the gridVal 2D Array<type int>
	 * @param column - The coordinates of the x axis which will be the 2nd index of the gridVal 2D Array<type int>
	 * @return  void 
	 */
	public void drawGrid(int row, int column) { 
		if (row == 42 && column == 42) {
			System.out.println(gridVal[row][column]);
		}
		else if (column == 42) {
			System.out.println(gridVal[row][column]);
			drawGrid(row+1,0);//Recursive call with row incremented by 1, column set to 0
		}
		else {
			System.out.print(gridVal[row][column]);
			drawGrid(row,column+1);//Recursive call with column incremented by 1
		}
	}

	/** input method:
	 * This functional method reads user input, and returns the value 
	 *
	 * List of Local Variables
	 * br - a BufferedReader object used for keyboard input <type BufferedReader>
	 *
	 * @param none
	 * @throws IO Exception	
	 * @return the number input by user <type int>
	 */	

	public int input() throws IOException {
		int num;
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		num =Integer.parseInt(br.readLine());
		return num;
	}//end input method

	/** input method:
	 * This functional method reads user input (gets row and column values) and runs the clearBlob method.
	 * After the clearBlob method is called to determine if the user has crushed a blob, the drawGrid method 
	 * will be called again to update the grid. If no blob is crushed, the method will update the # of lives they have left.
	 * Finally it will display the # of lives the user has left and the # of blobs they have crushed.
	 * This method recursively calls itself until one of two requirements are met.
	 * Either the user wins because they have cleared all 8 blobs, or they lose because they used up all 4 lives.
	 * Note: Since borders are included into array, if the point is beyond the vertical/horizontal
	 * middle border, the column/row must be incremented by 1.
	 * 
	 * List of Local Variables
	 * row - The value of the y coordinate that the user inputs
	 * column - The value of the x coordinate that the user inputs
	 *
	 * @param none
	 * @throws IO Exception	
	 * @return void
	 */	

	public void playGame() throws IOException {
		int row,column;
		if (blobs == TOTAL_BLOBS ) { 
			System.out.println ("WINNER! All blobs are cleared.");
		}
		else if (wrongGuess == LIVES_ALLOWED ) {
			System.out.println ("Game over, You used up all your lives!");
		}
		else {
			System.out.println("Enter a y-coordinate that is an integer from 1-40:");
			row = input();
			System.out.println("Enter a x-coordinate that is an integer from 1-40:");
			column = input();
			if (row > 20 && column > 20) {
				clearBlob(row + 1, column +1); //Recursive calls
			}
			else if (row > 20) {
				clearBlob(row + 1, column);
			}
			else if (column > 20) {
				clearBlob(row, column + 1);
			}
			else {
				clearBlob(row, column);
			}
			if (guess) {
				blobs++;
				guess = false;
			}	
			drawGrid(0,0);
			System.out.println("Blobs crushed: " + blobs);
			System.out.println("Lives left: " + (LIVES_ALLOWED - wrongGuess));
			playGame();
		}
	}//end playGame method

	/** clearBlob method:
	 * This functional checks whether the actual position (gameVal[row][column]) with the 
	 * given row and column values is a blob. If position contains part of the blob/is a value of '*'
	 * the clearBlob method will recursively call itself 8 times to go all 8 directions - N,W,S,E,NW,NE,SW,SE
	 * The different directions are created by incrementing/decrementing the row/y coordinate and/or 
	 * column/x coordinate.
	 * 
	 * List of Local Variables
	 * guess = Boolean used to determine whether the recursion terminated on the 1st call.
	 * If it did not terminate on the first call, we will set the guess to true so that the 
	 * number of wrong guesses will not be incremented.<type boolean>
	 *
	 * @param none
	 * @throws IO Exception	
	 * @return void
	 */	

	public void clearBlob (int row, int column) {
		if (gameVal[row][column]=='*' && gridVal[row][column] !='#') {
			gridVal[row][column] = '#';		
			gameVal[row][column] = ' ';	
			guess = true;
			clearBlob(row-1,column-1); //Recursive calls for used to check all 8 directions
			clearBlob(row-1,column);
			clearBlob(row-1,column+1);
			clearBlob(row,column-1);
			clearBlob(row,column+1);
			clearBlob(row+1,column+1);
			clearBlob(row+1,column);
			clearBlob(row+1,column-1);
		}
		if (guess == false) {
			wrongGuess++; //Increment wrong guess counter
		}
	}//end of clearBlob
	/** outputInstructions method
	 * This procedural method outputs the game instructions
	 * Outputs instructions/rules of the blob crusher game.
	 */
	public void outputInstructions() {
		System.out.println("Welcome to Blobcrusher!");
		System.out.println("The objective of the game is to guess the location of the blobs before missing 4 times.");
		System.out.println("There are 2 blobs located in each quadrant.");
		System.out.println("The top left and bottom right coordinates are (1,1) and (40,40) respectively.");
		System.out.println("The x coordinates increase left to right and the y coordinates increase top to bottom.");
		System.out.println("A crushed blob will crush all blobs within 1 unit until there are no more blobs around it in that quadrant.");
		System.out.println("Crushed blobs are marked by '#'");
		System.out.println("The game will end when you crush all 8 blobs or miss 4 times.");
		System.out.println("Note: Choosing the coordinates of an already crushed blob will make you lose 1 life.");
	}//end of outputInstructions
}//end of BlobCrusherFinal class
