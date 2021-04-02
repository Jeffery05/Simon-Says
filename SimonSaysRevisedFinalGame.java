/*
Names: Jeffery Hu and Simon Bakan
Date: January 29, 2021.
FileName: SimonSaysRevisedFinalGame.java
Teacher: Ms. Krasteva
Description: This program will execute the traditional Simon Says game that is played on a circle with 4 or 6
	     colours. The program has the user play the game with key presses using the getChar method of
	     rtp.

	     The program starts out with a splash screen of a rotating simon says animation and the title
	     fading in.

	     After the splash screen the user is directed to a main menu where they can choose one of 4
	     options; i for instruction; l to see the leaderboard; c to continue to level and difficulty selection and
	     then the game; and q to quit and close the screen.

	     Instructions is a simple screen telling the user how to play and then goes back to the menu
	     after a key press.

	     The leader board reads from a file with up to 10 high scores and prints them out on the screen.
	     It then goes back to the main menu after a key press.

	     The level selection allows the user to choose a level from one to five using 'w' and 's' to navigate. Once they have decided on a level, they can press 'enter' to
	     continue to difficulty selection or e to go back to the main menu.

	     The difficulty selection then allows the user to choose a difficulty of  either 4 or 6 colours. It also contains the option
	     to return to the main menu.

	     After the level selection the game starts showing the circle with 4 or 6 colours with letters labeled on each colour ('qwas' for 4 colours, 'qweasd' for 6 colours).
	     The computer first displays the pattern by flashing in order the selected colours. Then, the user must repeat the pattern by pressing the matching letters the selected colours in order.
	     Each level will start with one flash that the user has to repeat and slowly build up the pattern by one until the user is repeating the whole pattern back.

	     If the user successfully plays back the full pattern, the win screen will be displayed where the user can return to level
	     selection (and keep adding to their points) or exit to the menu. If the user loses
	     the lose screen will be displayed and the user will be taken back to the menu.

	     If the user has a sufficient score to get onto the leaderboard, the program will ask for their name whenever they exit. This can be in the lose screen or when they decide to exit after beating a level.

	     To exit, the user must press 'q' at the main menu. This will bring them to the goodbye screen where they can close the program.

Multiple Screens:
1. Splash Screen: The splash screen is used to draw a short animation when the program starts.
2. Main menu: The main menu screen is the centerpiece of program flow. It allows the user to either continue to level selection, view the leaderboard, view the instructions, or exit the program.
3. Instructions: The instructions screen displays the instructions of how the program will work to the user.
4. Leaderboard: The leaderboard screen displays the high score leaderboard in the game.
5. Level Selecion: The level selection screen displays the levels and gets the level the user would like to play.
6. Difficulty Selecion: The difficulty selection screen asks the user to choose between the two difficulties. It also has the option to exit.
6. Display and Get Data Screen: The Display and Get Data Screen will play the pattern on the Simon Says wheel and prompt the user to play it back.
7. Win Screen: If the user successfully plays back the whole pattern, then the win screen will be displayed. The win screen contains a win message and a checkmark.
8. Lose Screen: If the user loses at any point during the playback, the lose screen will be displayed. The lose screen contains a lose message and a sad face.
9. Good Bye Screen: The good bye screen displays a farewell message to the user and prompts them to exit the game.

GlobalVariable Dictionary:
       Name            |      Type      |        Purpose
    -------------------|----------------|--------------------------------------------------------------------------------------------------------
	 c             |    Console     |  Creates an instance of the console class
      pattern          |     char[]     |  Stores all the characters inside the pattern in the correct order
	win            |     boolean    |  Stores the value of whether the user has won or lost the level. It will be set to false immediately when/if the user types in the wrong character. It will be set to true as default. It decides if the program displays the win or lose screen, and if the user can continue playing.
       level           |      int       |  Stores which level the user would like to try
      choice           |      char      |  Stores where the user wants to go from main menu
     endChoice         |      char      |  Stores where the user wants to go from the win screen (either continues to level selection or back to main menu)
    CORRECTANSPOINTS   |  final int     |  Stores the amount of points that will be given for each correct character/colour playback
      score            |      int       |  Stores the total score during an attempt from the user (will end once the user loses)
    levelSelection     |      char      |  Stores what the user inputs in the level selection screen (it is also used in the main method so that the user can exit to main menu from level selection)
    difficultySelection|      char      |  Stores which difficulty the user would like to try (it is also used in the main method so that the user can exit to main menu from level selection)

Citations:
1. Sorting method taken from "Leson 1-7 Arrays Intro.pdf" lesson sheet by Ms. Krasteva
2. JOptionPane input aquired from https://www.javatpoint.com/java-joptionpane
    */

//Import statements
import java.awt.image.*;
import java.awt.*;
import hsa.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

// The "SimonSaysRevisedFinalGame" class.
public class SimonSaysRevisedFinalGame
{
    Console c;
    char[] pattern;
    boolean win;
    int level = 1;
    char choice;
    char endChoice;
    final int CORRECTANSPOINTS = 10;
    int score = 0;
    char levelSelection;
    char difficultySelection;


    public SimonSaysRevisedFinalGame ()  // The constructor
    {
	c = new Console ("Simon Says"); // Set "Simon Says" as the title of the console window

    }


    /*
    Purpose: The purpose of the splashScreen is to display an animation with the simon says wheel to start off the program. This method will display that screen to introduce and welcome the user to the program.

    Local Variable Dictionary:
	Name     | Type  |        Purpose
    -------------|-------|--------------------------------------------------------------------------------------------------------
	  x      |  int  |  Stores the X coordinate of the Simon Says wheel.
	  y      |  int  |  Stores the Y coordinate of the Simon Says wheel.
     incrementX  |  int  |  Stores how much further the Simon Says wheel will move left or right during the animation.
     incrementY  |  int  |  Stores how much further the Simon Says wheel will move up or down during the animation.
      arcAngle   |  int  |  Stores the base angle for all the arcs making up the colour wheel, which allows the Simon Says wheel spin around as the variable changes.
    colourOpacity|  int  |  Stores how transparent the title is so it can fade in.
      lightBlue  | Color |  Stores the light blue colour.

    While loops:
    1. The first while loop is used to animate the Simon Says wheel so it spins in and bounces off the edge.
	a) The try-catch statement is used to slow down the animation.
	b) The if statement is used to reverse the direction once it hits the edge.
	b) The if else statement is used to animate the Simon Says wheel. Once it hits the correct final position in the middle of the screen, it will break out of the loop and stop animating.
    2. The second while loop is used to create the "fading in effect" for the title.
    */

    private void splashScreen ()
    {
	int x = 0;
	int y = 0;
	int incrementX = 5;
	int incrementY = 2;
	int arcAngle = 90;
	int colourOpacity = 0;
	Color lightBlue = new Color (51, 152, 255);

	while (true)
	{
	    //Outer black base
	    c.setColor (Color.black);
	    c.fillOval (x - 10, y - 10, 200, 200);

	    c.setColor (Color.red);
	    c.fillArc (x, y, 180, 180, arcAngle, 90);
	    c.setColor (Color.blue);
	    c.fillArc (x, y, 180, 180, (arcAngle + 270), 90);
	    c.setColor (Color.yellow);
	    c.fillArc (x, y, 180, 180, (arcAngle + 180), 90);
	    c.setColor (Color.green);
	    c.fillArc (x, y, 180, 180, (arcAngle + 90), 90);

	    //center
	    c.setColor (Color.black);
	    c.fillOval (x + 65, y + 65, 50, 50);

	    try
	    {
		Thread.sleep (45);
	    }
	    catch (Exception e)
	    {
	    }

	    if (x == 450)
	    {
		incrementX *= -1;
	    }
	    if (x == 230 && incrementX < 0)
	    {
		break;
	    }
	    else
	    {
		c.setColor (Color.white);
		c.fillOval (x - 10, y - 10, 200, 200);
	    }
	    arcAngle += 45;
	    x += incrementX;
	    y += incrementY;
	}

	c.setColor (lightBlue);
	c.fillRoundRect (40, 50, 570, 140, 20, 20);

	while (true)
	{
	    if (colourOpacity < 100)
	    {
		c.setColor (new Color (51 + (51 * colourOpacity / 100), 152 + (103 * colourOpacity / 100), 255 - (153 * colourOpacity / 100)));
		c.setFont (new Font ("Arial", Font.PLAIN, 100));
		c.drawString ("Simon Says", 60, 150);
		colourOpacity++;
		try
		{
		    Thread.sleep (20);
		}
		catch (Exception e)
		{
		}
	    }
	    else
	    {
		break;
	    }
	}
	try
	{
	    Thread.sleep (2000);
	}
	catch (Exception e)
	{
	}
    }


    /*
    Purpose: The purpose of the title is to clear the screen and display a background with colours on the edges and a grey rectangle in the middle (as a template for most of the screens).

    Local Variable Dictionary:
	Name     | Type  |        Purpose
    -------------|-------|--------------------------------------------------------------------------------------------------------
	  x      | int[] |  Stores the X coordinates for the green triangle on the left of the screen.
	  y      | int[] |  Stores the Y coordinates for the green triangle on the left of the screen.
	  x2     | int[] |  Stores the X coordinates for the red triangle on the top of the screen.
	  y2     | int[] |  Stores the Y coordinates for the red triangle on the top of the screen.
	  x3     | int[] |  Stores the X coordinates for the yellow triangle on the right of the screen.
	  y3     | int[] |  Stores the Y coordinates for the yellow triangle on the right of the screen.
	  x4     | int[] |  Stores the X coordinates for the blue triangle on the bottom of the screen.
	  y4     | int[] |  Stores the Y coordinates for the blue triangle on the bottom of the screen.
     */

    private void title ()
    {
	c.clear ();
	c.setColor (Color.green);
	int x[] = {0, 0, 320, 0};
	int y[] = {0, 500, 250, 0};
	c.fillPolygon (x, y, 3); // Green triangle on the left of the screen
	c.setColor (Color.red);
	int x2[] = {0, 640, 320, 0};
	int y2[] = {0, 0, 250, 0};
	c.fillPolygon (x2, y2, 3); // Red triangle on the top of the screen
	c.setColor (Color.yellow);
	int x3[] = {640, 640, 320, 640};
	int y3[] = {0, 500, 250, 0};
	c.fillPolygon (x3, y3, 3); // Yellow triangle on the right of the screen
	c.setColor (Color.blue);
	int x4[] = {0, 640, 320, 0};
	int y4[] = {500, 500, 250, 640};
	c.fillPolygon (x4, y4, 3); // Blue triangle on the bottom of the screen
	c.setColor (new Color (230, 230, 230));
	c.fillRect (10, 8, 620, 484); // Grey rectangle in the background
	c.setColor (Color.black);
    }


    /*
    Purpose: The purpose of the mainMenu is to control the flow of the program and to give the user a choice of what they want to do.

    While loops:
    1. The while loop is a while true loop with a break conditional to error trap the choice so it keeps prompting the user until they enter a valid choice.

    Conditional:
    1. The conditional is an if else statement that has a condition testing if the user entered character is one of the ones it is looking for. If it is it breaks the loop and if it isnt it throws an error.

    Try-catch blocks:
    1. The try-catch statement allows the program to throw a custom error when an invalid character is entered to display a JOption pane.
    */

    public void mainMenu ()
    {
	title ();

	//title
	c.setFont (new Font ("Arial", Font.BOLD, 50));
	c.drawString ("Menu:", 250, 80);
	c.setFont (new Font ("Arial", Font.PLAIN, 20));
	c.drawString ("Press a key to choose what to do:", 50, 130);
	c.drawString ("i - See instructions", 50, 190);
	c.drawString ("c - Continue to Level Selection", 50, 250);
	c.drawString ("l - See leader board", 50, 310);
	c.drawString ("q - Quit and close the game", 50, 370);
	while (true)
	{
	    try
	    {
		choice = c.getChar ();
		if (choice == 'c' || choice == 'C' || choice == 'i' || choice == 'I' || choice == 'l' || choice == 'L' || choice == 'q' || choice == 'Q')
		{
		    break;
		}
		else
		{
		    throw new IllegalArgumentException ();
		}
	    }
	    catch (IllegalArgumentException e)
	    {
		JOptionPane.showMessageDialog (null, "Sorry, the value you entered is invalid. Please try again.\npress c to continue.\npress i for instructions\npress l for the leaderboard\npress q to quit", "Error", JOptionPane.ERROR_MESSAGE); // error message
	    }
	}
    }


    /*
    Purpose: The purpose of the instructions method is to display the instructions on how the game will work to the user.
    */

    public void instructions ()
    {
	title ();

	//title
	c.setFont (new Font ("Arial", Font.PLAIN, 50));
	c.drawString ("Instructions:", 180, 80);
	c.setFont (new Font ("Arial", Font.PLAIN, 20));
	c.drawString ("The video game of Simon Says is a game of memory. A circle", 50, 130);
	c.drawString ("with 4 or 6 colours will be displayed. It will do a pattern of", 50, 160);
	c.drawString ("flashes, turning white and you will have to repeat the sequence", 50, 190);
	c.drawString ("with the appropriate key that is labeled on the colour.", 50, 220);
	c.drawString ("It will go up in length each round and you will get", 50, 250);
	c.drawString ("points for each correct character repeat. To beat", 50, 280);
	c.drawString ("a level, you need to repeat the full pattern. If you win,", 50, 310);
	c.drawString ("you can choose to play again to continue racking up points.", 50, 340);
	c.drawString ("If you lose, your points will reset. Beating a level will", 50, 370);
	c.drawString ("result in an extra point bonus. Good luck!", 50, 400);
	c.drawString ("Press any key to continue.", 50, 430);
	c.getChar ();
    }


    /*
    Purpose: The purpose of the leaderboard method is to display the leaderboard for the game. It will read the names and scores on the Simon Says High Scores file and display it in the console window.

    Local Variable Dictionary:
	Name     |      Type      |        Purpose
    -------------|----------------|--------------------------------------------------------------------------------------------------------
	input    | BufferedReader |  Helps to read lines in the file
	line     |      int       |  Keeps track of the line number the buffered reader is reading.
    nameScore    |    String      |  Stores a string with the name of the person on the leader board with a " - " and their score.
    runThrough   |    String      |  Stores how much further the Simon Says wheel will move up or down during the animation.
	  y      |      int       |  Stores the y coordinate of each highscore on the leaderboard.
	 end     |    boolean     |  Stores how transparent the title is so it can fade in.
      inputFile  |    String      |  Stores the name of the input file.

    Try-catch statement:
    1. The try catch statement is used to try and read the names and high scores on the Simon Says High Scores file.

    While loops:
    1. The while loop is used to keep reading names and scores from the file until it reads null, in which case it will stop reading.

    If-else statements:
    1. The first if else statement is used to make sure that what is read is not null. If it is, end is set to true and the loop will stop.
    2. The second if else statement is used to see if what is read on the line is the name or high score. If it is the name, then it will store it in variable nameScore. If it is the score, then it will add it onto the name in nameScore and display it on the console window.
    */

    public void leaderBoard ()
    {
	BufferedReader input;
	int line = 0;
	String nameScore = " ";
	String runThrough;
	int y = 180;
	boolean end = false;
	String inputFile = "SimonSaysHighScores.txt";

	title ();
	c.setFont (new Font ("Arial", Font.PLAIN, 50));
	c.drawString ("Leaderboard:", 180, 80);
	c.setFont (new Font ("Arial", Font.PLAIN, 20));
	c.drawString ("The leaderboard displays the top 10 scores. If your score is", 50, 120);
	c.drawString ("higher than the 10th largest score, it will be displayed here.", 50, 150);

	try
	{
	    input = new BufferedReader (new FileReader (inputFile));

	    while (end != true)
	    {
		runThrough = input.readLine ();
		line += 1;
		if (runThrough == null)
		{
		    end = true;
		}
		else
		{
		    if (line % 2 == 1)
		    {
			nameScore += runThrough;
		    }
		    else if (line % 2 == 0)
		    {
			nameScore = nameScore + " ----- " + runThrough;
			c.drawString (line / 2 + ". " + nameScore, 50, y);
			nameScore = " ";
			y += 30;
		    }
		}
	    }
	}
	catch (IOException e)
	{
	}

	c.drawString ("Press any key to exit.", 50, y);
	c.getChar ();
    }


    /*
    Purpose: The purpose of the getLevel method is to ask the user for the level which determines the number of flashes in the pattern.

    While loops:
    1. The first while loop keeps the user inputting characters to change the level until the user presses enter to continue or e to exit.

    Conditionals:
    1. The first conditional is an if else if statement that evaluates the key pressed.
	a) an if statement that sends a JOption pane if the charactered entered is not one of the ones expected.
	b) an else if statement that subtracts one from level if w is pressed.
	c) an else if statement that adds one to level if s is pressed.
	d) an else if statement that breaks which will send the user to menu if e is pressed.
	e) an else if statement that breaks which will send the user to the game if the enter key is pressed.
    2. an if statement that will set level to 1 if it gets to 6 to loop back up to the top.
    2. an if statement that will set level to 5 if it gets to 0 to loop to the bottom.
    */

    public void getLevel ()
    {

	title ();

	//title
	c.setFont (new Font ("Arial", Font.PLAIN, 50));
	c.drawString ("Level Selection:", 150, 80);

	//explanation
	c.setFont (new Font ("Arial", Font.PLAIN, 20));
	c.drawString ("Press enter to select the level.", 50, 130);
	c.drawString ("1 - Level 1 (up to 4 flashes)", 50, 160);
	c.drawString ("2 - Level 2 (up to 6 flashes)", 50, 190);
	c.drawString ("3 - Level 3 (up to 8 flashes)", 50, 220);
	c.drawString ("4 - Level 4 (up to 10 flashes)", 50, 250);
	c.drawString ("5 - Level 5 (up to 12 flashes)", 50, 280);
	c.drawString ("*Use 'w' and 's' to move up or down the levels.", 50, 310);
	c.drawString ("*Press 'e' to return to the main menu.", 50, 340);
	while (true)
	{
	    //star
	    c.setColor (Color.black);
	    c.fillStar (10, 140 + (level - 1) * 30, 25, 25);

	    levelSelection = c.getChar ();

	    //background
	    c.setColor (new Color (230, 230, 230));
	    c.fillStar (10, 140 + (level - 1) * 30, 25, 25);

	    if (levelSelection != 'w' && levelSelection != 'W' && levelSelection != 's' && levelSelection != 'S' && levelSelection != 10 && levelSelection != 10 && levelSelection != 'e' && levelSelection != 'E')
	    {
		JOptionPane.showMessageDialog (null, "Sorry, the value you entered is invalid. Please enter either 'w', 's', 'e', or 'enter'.\n\n", "Error", JOptionPane.ERROR_MESSAGE); // error message
	    }
	    else if (levelSelection == 'w' || levelSelection == 'W')
	    {
		level -= 1;
	    }
	    else if (levelSelection == 's' || levelSelection == 'S')
	    {
		level += 1;
	    }
	    else if (levelSelection == 'e' || levelSelection == 'E')
	    {
		break;
	    }
	    else if (levelSelection == 10 || levelSelection == 10)
	    {
		break;
	    }
	    if (level == 6)
	    {
		level = 1;
	    }
	    if (level == 0)
	    {
		level = 5;
	    }
	}
    }


    /*
    Purpose: The purpose of the getDifficulty method is to ask the user for the difficulty they want to play at which determines the number of colours in the Simon Says wheel.

    While loops:
    1. The first while loop keeps prompting the user to input characters until they enter a valid one ('n','h', or 'e').

    Conditionals:
    1. The first conditional is an if statements that throws an error if the inputted character is not one of the excpected ones.

    Try-Catch Blocks:
    1. The only try catch block is for the error trapping where a custom error is thrown if the user enters an unexpected character. A JOption pane will be displayed if the user input is invalid.
    */

    public void getDifficulty ()
    {

	title ();

	//title
	c.setFont (new Font ("Arial", Font.PLAIN, 50));
	c.drawString ("Difficulty Selection:", 110, 80);

	//explanation
	c.setFont (new Font ("Arial", Font.PLAIN, 20));
	c.drawString ("Choose your difficulty. Hard will get you 50% more points:", 50, 130);
	c.drawString ("Press n for normal - 4 colours", 50, 190);
	c.drawString ("Press h for hard - 6 colours", 50, 250);
	c.drawString ("Press e to exit to the main menu.", 50, 310);
	while (true)
	{
	    try
	    {
		difficultySelection = c.getChar ();
		if (difficultySelection != 'n' && difficultySelection != 'N' && difficultySelection != 'h' && difficultySelection != 'H' && difficultySelection != 'e' && difficultySelection != 'E')
		{
		    throw new IllegalArgumentException ();
		}
		break;
	    }
	    catch (IllegalArgumentException e)
	    {
		JOptionPane.showMessageDialog (null, "Sorry, the value you entered is invalid. Please enter either 'n', 'h', or 'e'.\n\n", "Error", JOptionPane.ERROR_MESSAGE); // error message
	    }
	}
    }


    /*
    Purpose: The purpose of the getPattern method is to get a random pattern for the colour sequence. The length of the pattern will also be taken into account, and will be taken from the parameter.

    Local Variable Dictionary:
	Name     | Type  |        Purpose
    -------------|-------|--------------------------------------------------------------------------------------------------------
	size     |  int  |  Stores the size of the array.
     difficulty  | char  |  Determines if the user wants 4 colours or 6 colours in the pattern.
      charRand   |  int  |  Stores the random numbers between 1-4 or 1-6 before they are converted into chars

    For loop:
    1. The for loop is used to keep coming up with random characters (either 'q', 'w', 'a', or 's') until it reaches the required length of the pattern.

    If-else statements:
    1. The first if else statement is used to see if the program should generate a pattern with 4 letters or 6 letters.
    2. The second if else statement is used to take the randomized number and associate it with a character (either 'q', 'w', 'a', or 's', the same number will be associated with the same character each time).
    It will then store that character in the given index of the array (creating a pattern).
    */

    public void getPattern (int size, char difficulty)
    {
	int charRand;

	c.clear ();
	pattern = new char [size];
	for (int i = 0 ; i < size ; i++)
	{
	    if (difficulty == 'n' || difficulty == 'N')
	    {
		charRand = (int) (Math.random () * (4) + 1);
	    }
	    else
	    {
		charRand = (int) (Math.random () * (6) + 1);
	    }
	    if (charRand == 1)
	    {
		pattern [i] = 'q';
	    }
	    else if (charRand == 2)
	    {
		pattern [i] = 'w';
	    }
	    else if (charRand == 3)
	    {
		pattern [i] = 'a';
	    }
	    else if (charRand == 4)
	    {
		pattern [i] = 's';
	    }
	    else if (charRand == 5)
	    {
		pattern [i] = 'e';
	    }
	    else
	    {
		pattern [i] = 'd';
	    }
	}
    }


    /*
    Purpose: The purpose of the displayAndGetData method is to run the game by displaying the pattern and asking the user to repeat the pattern.

    Local Variable Dictionary:
	Name     | Type  |        Purpose
    -------------|-------|--------------------------------------------------------------------------------------------------------
      flashSpeed |  int  |  Tracks the speed of the computer flashes that get faster each round.
	print    |  char |  Stores which colour will be the one flashing on the wheel.
	  m      |  int  |  A FOR loop count that is used to track how many flashes of the pattern it is showing.
	  i      |  int  |  A FOR loop count that is used to go through the array and display all the flashes. It also asks for the user input key presses.

    Conditional:
    1. The first conditional is an if else statement that prints the wheel with 4 or 6 colours depending on the difficulty.
    2. The second conditional is an if else statement that either plays the pattern or gets user input playing back the pattern (depending on the loop count).
    3. The third conditional is an if statement that error trapps for an invalid entry when the user plays back the sequence. This if-else statement differenciates between 4 colours vs 6 colours, so you cannot enter 'e' or 'd' for 4 colours.
    4. The fourth conditional is an if else statement that displays a JOption pane message with the keys that you can press depending on which difficulty it is.
    5. The fifth conditional is an if else block that returns if the user got it wrong and adds points to score if they got it right (after calling the black box return method).
    6. The sixth conditional is an if else block that adds correctAnsPoints to the user's score if the difficulty is normal or 150% of correctAnsPoints if it is hard difficulty.
    7. The seventh conditional is an if else block that controls whether it flashes on a Simon Says wheel with 4 or 6 colours.
    8. The eighth conditional is an if else if block that determines which colour to flash (depending on the value of print) for the normal difficulty.
    9. The ninth conditional is an if else if block that determines which colour to flash (depending on the value of print) for the hard difficulty.
    10. The tenth conditional is an if else statement that pauses for more time between flashes if it is the computer's turn or less if it is the user's.
    11. The eleventh conditional statement is an if else statement that determines whether to return the Simon Says wheel to a 4 colour wheel or a 6 colour wheel.
    12. The twelfth conditional is an if else if block that returns the sector that was flashed back to its normal colour in the normal dificulty.
    13. The thirteenth conditional is an if else if block that returns the sector that was flashed back to its normal colour in the hard dificulty.

    For loops:
    1. The first for loop is used to increase the length/index that is last read in the pattern each time the loop repeats.
    2. The second for loop repeats twice as much as the outer loop counter. The first half of the iterations assign the character (at index of i/the loop count) from the Pattern array to print.
       The second half of the iterations get user input which is assigned to print as well. The program displays a flash each time the loop is run depending on the value of print, so it will either flash the pattern or flash what the user inputted. (We decided to use 1 loop to make it more efficient so we don't have to draw the graphics twice)

    While loops:
    1. The one while loop is used to error trap the user input until they enter a letter that is on the wheel ('q', 'w', 'a', 's', 'e' or 'd').

    Try-catch blocks:
    1. The first try-catch block is used for a thread sleep before the computer's flash.
    2. The second try-catch block is used for a custom error for error trapping the character so if user enters an invalid key it displays an error message.
    3. The third try-catch block is used for a thread sleep during the flash so the user can see where it has flashed before it disappears.
    */

    public void displayAndGetData ()
    {
	int flashSpeed = 500;
	char print;

	title ();
	win = true;

	//Outer black base
	c.setColor (Color.black);
	c.fillOval (113, 73, 414, 414);

	if (difficultySelection == 'n' || difficultySelection == 'N')
	{
	    //Colours
	    c.setColor (Color.red);
	    c.fillArc (130, 90, 380, 380, 0, 90);
	    c.setColor (Color.blue);
	    c.fillArc (130, 90, 380, 380, 270, 90);
	    c.setColor (Color.yellow);
	    c.fillArc (130, 90, 380, 380, 180, 90);
	    c.setColor (Color.green);
	    c.fillArc (130, 90, 380, 380, 90, 90);

	    //Letters
	    c.setColor (Color.black);
	    c.setFont (new Font ("Arial", Font.PLAIN, 50));
	    c.drawString ("Q", 220, 210);
	    c.drawString ("W", 370, 210);
	    c.drawString ("A", 220, 380);
	    c.drawString ("S", 370, 380);
	}
	else
	{
	    // 6 colours
	    //Colours
	    c.setColor (Color.red);
	    c.fillArc (130, 90, 380, 380, 0, 60);
	    c.setColor (Color.blue);
	    c.fillArc (130, 90, 380, 380, 300, 60);
	    c.setColor (Color.pink);
	    c.fillArc (130, 90, 380, 380, 240, 60);
	    c.setColor (Color.yellow);
	    c.fillArc (130, 90, 380, 380, 180, 60);
	    c.setColor (Color.green);
	    c.fillArc (130, 90, 380, 380, 120, 60);
	    c.setColor (Color.orange);
	    c.fillArc (130, 90, 380, 380, 60, 60);

	    //Letters
	    c.setColor (Color.black);
	    c.setFont (new Font ("Arial", Font.PLAIN, 50));
	    c.drawString ("Q", 190, 235);
	    c.drawString ("W", 296, 170);
	    c.drawString ("E", 405, 235);
	    c.drawString ("A", 195, 355);
	    c.drawString ("S", 304, 425);
	    c.drawString ("D", 403, 355);
	}

	//center
	c.setColor (Color.black);
	c.fillOval (270, 230, 100, 100);

	for (int m = 1 ; m <= pattern.length ; m++)
	{
	    flashSpeed -= 25;
	    for (int i = 0 ; i < m * 2 ; i++)
	    {
		c.setColor (new Color (230, 230, 230));
		c.fillRect (50, 15, 580, 45);

		//Score
		c.setColor (Color.black);
		c.setFont (new Font ("Arial", Font.PLAIN, 20));
		c.drawString ("Score: " + score, 20, 40);

		if (i < m)
		{
		    c.setFont (new Font ("Arial", Font.PLAIN, 30));
		    c.drawString ("Pay Attention to the Pattern!", 150, 50);
		    try
		    {
			Thread.sleep (flashSpeed);
		    }
		    catch (Exception e)
		    {
		    }
		    print = pattern [i];
		}
		else
		{
		    c.setFont (new Font ("Arial", Font.PLAIN, 30));
		    c.drawString ("Play Back the Pattern!", 170, 50);
		    while (true)
		    {
			try
			{
			    print = c.getChar (); // Collect user input
			    if ((print != 'q' && print != 'Q' && print != 'w' && print != 'W' && print != 's' && print != 'S' && print != 'a' && print != 'A') && ((difficultySelection == 'n' || difficultySelection == 'N') || print != 'e' && print != 'E' && print != 'd' && print != 'D'))
			    {
				throw new IllegalArgumentException (); // Custom exception
			    }
			    break;
			}
			catch (IllegalArgumentException E)
			{
			    if (difficultySelection == 'n' || difficultySelection == 'N')
			    {
				JOptionPane.showMessageDialog (null, "Error! Please press either 'q', 'w', 's', or 'a'.\n\n", "Error", JOptionPane.ERROR_MESSAGE); // error message
			    }
			    else
			    {
				JOptionPane.showMessageDialog (null, "Error! Please press either 'q', 'w', 's', 'a', 'e', or 'd'.\n\n", "Error", JOptionPane.ERROR_MESSAGE); // error message
			    }
			}
		    }
		    win = patternIsEqual (print, i - m);
		    if (win == false)
		    {
			return;
		    }
		    else
		    {
			if (difficultySelection == 'n' || difficultySelection == 'N')
			{
			    score += CORRECTANSPOINTS;
			}
			else
			{
			    score += CORRECTANSPOINTS * 1.5;
			}
		    }
		}
		if (difficultySelection == 'n' || difficultySelection == 'N')
		{
		    if (print == 'q' || print == 'Q')
		    {
			c.setColor (Color.white);
			c.fillArc (130, 90, 380, 380, 90, 90);
		    }
		    else if (print == 'w' || print == 'W')
		    {
			c.setColor (Color.white);
			c.fillArc (130, 90, 380, 380, 0, 90);
		    }
		    else if (print == 'a' || print == 'A')
		    {
			c.setColor (Color.white);
			c.fillArc (130, 90, 380, 380, 180, 90);
		    }
		    else
		    {
			c.setColor (Color.white);
			c.fillArc (130, 90, 380, 380, 270, 90);
		    }
		}
		else // 6 colours
		{
		    if (print == 'q' || print == 'Q')
		    {
			c.setColor (Color.white);
			c.fillArc (130, 90, 380, 380, 120, 60);
		    }
		    else if (print == 'w' || print == 'W')
		    {
			c.setColor (Color.white);
			c.fillArc (130, 90, 380, 380, 60, 60);
		    }
		    else if (print == 'a' || print == 'A')
		    {
			c.setColor (Color.white);
			c.fillArc (130, 90, 380, 380, 180, 60);
		    }
		    else if (print == 's' || print == 'S')
		    {
			c.setColor (Color.white);
			c.fillArc (130, 90, 380, 380, 240, 60);
		    }
		    else if (print == 'e' || print == 'E')
		    {
			c.setColor (Color.white);
			c.fillArc (130, 90, 380, 380, 0, 60);
		    }
		    else
		    {
			c.setColor (Color.white);
			c.fillArc (130, 90, 380, 380, 300, 60);
		    }
		    //Colours
		}

		//center
		c.setColor (Color.black);
		c.fillOval (270, 230, 100, 100);

		try
		{
		    if (i < m)
		    {
			Thread.sleep (flashSpeed);
		    }
		    else
		    {
			Thread.sleep (200);
		    }
		}
		catch (Exception e)
		{
		}

		c.setFont (new Font ("Arial", Font.PLAIN, 50));
		if (difficultySelection == 'n' || difficultySelection == 'N')
		{
		    if (print == 'q' || print == 'Q')
		    {
			c.setColor (Color.green);
			c.fillArc (130, 90, 380, 380, 90, 90);
			c.setColor (Color.black);
			c.drawString ("Q", 220, 210);
		    }
		    else if (print == 'w' || print == 'W')
		    {
			c.setColor (Color.red);
			c.fillArc (130, 90, 380, 380, 0, 90);
			c.setColor (Color.black);
			c.drawString ("W", 370, 210);
		    }
		    else if (print == 'a' || print == 'A')
		    {
			c.setColor (Color.yellow);
			c.fillArc (130, 90, 380, 380, 180, 90);
			c.setColor (Color.black);
			c.drawString ("A", 220, 380);
		    }
		    else
		    {
			c.setColor (Color.blue);
			c.fillArc (130, 90, 380, 380, 270, 90);
			c.setColor (Color.black);
			c.drawString ("S", 370, 380);
		    }
		}
		else // 6 colours
		{
		    if (print == 'q' || print == 'Q')
		    {
			c.setColor (Color.green);
			c.fillArc (130, 90, 380, 380, 120, 60);
			c.setColor (Color.black);
			c.drawString ("Q", 190, 235);
		    }
		    else if (print == 'w' || print == 'W')
		    {
			c.setColor (Color.orange);
			c.fillArc (130, 90, 380, 380, 60, 60);
			c.setColor (Color.black);
			c.drawString ("W", 296, 170);
		    }
		    else if (print == 'a' || print == 'A')
		    {
			c.setColor (Color.yellow);
			c.fillArc (130, 90, 380, 380, 180, 60);
			c.setColor (Color.black);
			c.drawString ("A", 195, 355);
		    }
		    else if (print == 's' || print == 'S')
		    {
			c.setColor (Color.pink);
			c.fillArc (130, 90, 380, 380, 240, 60);
			c.setColor (Color.black);
			c.drawString ("S", 304, 425);
		    }
		    else if (print == 'e' || print == 'E')
		    {
			c.setColor (Color.red);
			c.fillArc (130, 90, 380, 380, 0, 60);
			c.setColor (Color.black);
			c.drawString ("E", 405, 235);
		    }
		    else
		    {
			c.setColor (Color.blue);
			c.fillArc (130, 90, 380, 380, 300, 60);
			c.setColor (Color.black);
			c.drawString ("D", 403, 355);
		    }
		    //Colours
		}
		//center
		c.setColor (Color.black);
		c.fillOval (270, 230, 100, 100);
	    }

	    try
	    {
		Thread.sleep (1000);
	    }
	    catch (Exception e)
	    {
	    }
	}
	//Bonus points
	score += 50 * level;
    }


    /*
    Purpose: The purpose of the patternIsEqual method is to check if the user input is equal to the specific character in the pattern.
    It is run after every character the user enters in displayAndGetData, so every character is checked individually (so that the lose screen appears right when the user enters a wrong input).
    It will return true if the user inputted the correct character or false if the user inputted the wrong character.

    Local Variable Dictionary:
	Name     | Type  |        Purpose
    -------------|-------|--------------------------------------------------------------------------------------------------------
	input    |  char |  Stores the character the user inputted.
	index    |  int  |  Provides the index in the array (which stores the pattern) to check the user input against.

    If-else statements:
    1. The if else statement is used to check the user input against the correct character in the pattern.
    */

    private boolean patternIsEqual (char input, int index)
    {
	if (pattern [index] == input)
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }


    /*
    Purpose: The purpose of the winScreen method is to display a win screen and message and prompt the user to either continue playing Simon Says or exit to main menu.

    Local Variable Dictionary:
	Name     | Type  |        Purpose
    -------------|-------|--------------------------------------------------------------------------------------------------------
      darkGreen  | Color |  Stores the dark green colour.
	  x      | int[] |  Provides the x coordinates for all the vertices in the left rectangle (to draw the left side of the check mark).
	  y      | int[] |  Provides the y coordinates for all the vertices in the left rectangle (to draw the left side of the check mark).
	  i      | int[] |  Provides the x coordinates for all the vertices in the right rectangle (to draw the right side of the check mark).
	  z      | int[] |  Provides the y coordinates for all the vertices in the right rectangle (to draw the right side of the check mark).

    User input statement:
    1. Gets and stores the user input into endChoice to see if the user wants to continue to level selection and keep getting points or if they want to exit to main menu.
    */

    public void winScreen ()
    {
	Color darkGreen = new Color (0, 100, 0);

	c.clear ();
	title ();
	c.setColor (darkGreen);
	c.setFont (new Font ("Arial", Font.PLAIN, 40));
	c.drawString ("YOU PASSED THE LEVEL!", 70, 90); // Title
	c.setFont (new Font ("Arial", Font.PLAIN, 22));
	c.drawString ("Press any key to continue back to level selection or 'e' to exit to", 20, 150); // Description
	c.drawString ("main menu.", 20, 180);

	//Check mark
	int x[] = {250, 300, 320, 270, 250};
	int y[] = {360, 410, 390, 340, 360};
	c.fillPolygon (x, y, 4); // Left rectangle for the left side of the checkmark
	int i[] = {300, 400, 380, 280, 300};
	int z[] = {410, 310, 290, 390, 410};
	c.fillPolygon (i, z, 4); // Right rectangle for the right side of the checkmark

	//Score
	c.setColor (Color.black);
	c.setFont (new Font ("Arial", Font.PLAIN, 20));
	c.drawString ("Score: " + score, 20, 30);

	endChoice = c.getChar ();
    }


    /*
    Purpose: The purpose of the loseScreen method is to display a lose screen and message and prompt the user to exit to main menu.

    Local Variable Dictionary:
	Name     |      Type      |        Purpose
    -------------|----------------|--------------------------------------------------------------------------------------------------------
      someImage  | BuefferedImage |  Reads the image file.

    Try catch statement:
    1. The purpose of the try catch statement is to try and read the sad face image file and display it on the lose screen. It catches for if the image file is not found.

    User input statement:
    1. The purpose of the user input statement is to wait and get user input before moving onto the next screen.
    This is so that the user has time to read and see the lose screen before they move on (so it doesn't just flash and disappear).
    */

    public void loseScreen ()
    {
	c.clear ();
	title ();
	c.setColour (Color.red);
	c.setFont (new Font ("Arial", Font.PLAIN, 50));
	c.drawString ("Sorry You Lost.", 150, 50); // Title
	c.setFont (new Font ("Arial", Font.PLAIN, 30));
	c.drawString ("Press any key to continue to the main menu.", 30, 100); // Description

	//Score
	c.setColor (Color.red);
	c.fillRect (15, 10, 125, 30);
	c.setColor (Color.white);
	c.setFont (new Font ("Arial", Font.BOLD, 20));
	c.drawString ("Score: " + score, 25, 30);

	try
	{
	    BufferedImage someImage = ImageIO.read (new File ("frowningface.png"));
	    c.drawImage (someImage, 130, 150, null);
	}
	catch (IOException e)
	{
	    JOptionPane.showMessageDialog (null, "Image not found", "Error", JOptionPane.ERROR_MESSAGE); // error message
	}

	c.getChar (); // Gets user input
    }


    /*
    Purpose: The purpose of the writeScore method is to read and reorder the file of the high scores when a user gets a new high score.

    Local Variable Dictionary:
	Name     |     Type     |        Purpose
    -------------|--------------|--------------------------------------------------------------------------------------------------------
      highScores |  String[][]  |  Stores all of the highscores and the names of the people who got them
      fileLength |     int      |  Keeps track of the amount of scores that are stored
      fileScore  |    String    |  Briefly stores each line from the file to see if it is null before trying to put it in the array
	  in     |BufferedReader|  For reading the SimonSaysHighScores text file
	  out    | PrintWriter  |  For printing to the the SimonSaysHighScores text file
	  row    |     int      |  Loop counter determines the row of the 2d array to put the already existing data from the file into (names or scores).
	column   |     int      |  Loop counter determines the column of the 2d array to put the already existing data from the file into (names or scores).
	   i     |     int      |  Loop counter that goes through the already stored scores to check if the current score is higher.
	   m     |     int      |  Loop counter that starts at the end of the array and moves all the scores down until it gets to where the user's current score fits.
       fileLine  |     int      |  Loop counter that is used cycle through the array to print its contents back to the file.

    Conditionals:
    1. The first conditional is an if statement that returns if the score is 0.
    2. The second conditional is an if else that stores the value that is read from the file if it is not null and exits the inner loop if it is.
    3. The third conditional exits the outer loop if the value is null.
    4. The fourth conditional checks if the user's score is bigger than each of the past high scores (starting from the lowest previous high score).
    5. The fifth conditional checks if the user inputted a name that is less than or equal to 20 characters. If it is, the while loop breaks.
    6. The sixth conditional checks if the user's name is null, and then sets it to "No Name" if it is (this is to account for if the user clicked the X button when prompted for their name).

    For loops:
    1. The first for loop is to increase the column of the 2D array as it stores the values on the file.
    1. The second for loop is to increase the row of the 2D array to swap between the name and score row.
    3. The third for loop is used to cycle through the array to see what is the highest existing score that is lower than the user's new score.
    4. The fourth for loop is used to shift all the old lower scores down until it gets to where the new score fits.
    4. The fifth for loop writes all the high scores in their new order onto the high scores file.

    While Loop:
    1. The while loop is used to keep prompting the user until they input a name that is not too long.

    Try-catch blocks:
    1. The first try-catch block is for the reading of the file.
    2. The second try-catch block is for the writing to the file.
    */

    public void writeScore ()
    {
	String highScores[] [] = new String [2] [10];
	int fileLength = 0;
	String fileScore = "";

	if (score == 0)
	{
	    return;
	}

	try
	{
	    BufferedReader in = new BufferedReader (new FileReader ("SimonSaysHighScores.txt"));
	    for (int column = 0 ; column < 10 ; column++)
	    {
		for (int row = 0 ; row < 2 ; row++)
		{
		    fileScore = in.readLine ();
		    if (fileScore != null)
		    {
			highScores [row] [column] = fileScore;
			fileLength++;
		    }
		    else
		    {
			fileLength += 2;
			highScores [1] [column] = "0";
			break;
		    }
		}
		if (fileScore == null)
		{
		    break;
		}
	    }
	    in.close ();
	}
	catch (IOException e)
	{
	    fileLength = 2;
	    highScores [1] [0] = "0";
	}

	//Sorting method taken from "Leson 1-7 Arrays Intro.pdf" lesson sheet by Ms. Krasteva
	// JOptionPane input aquired from https://www.javatpoint.com/java-joptionpane

	for (int i = 0 ; i < fileLength / 2 ; i++)
	{
	    if (score > Integer.parseInt (highScores [1] [i]))
	    {
		for (int m = (fileLength / 2) - 1 ; m > i ; m--)
		{
		    highScores [0] [m] = highScores [0] [m - 1];
		    highScores [1] [m] = highScores [1] [m - 1];
		}
		while (true)
		{
		    highScores [0] [i] = JOptionPane.showInputDialog ("Enter your name");
		    if (highScores [0] [i] == null)
		    {
			highScores [0] [i] = "No Name";
			break;
		    }
		    if (highScores [0] [i].length () <= 20)
		    {
			break;
		    }
		    JOptionPane.showMessageDialog (null, "Error, name too long!", "Error", JOptionPane.ERROR_MESSAGE); // error message
		}
		highScores [1] [i] = Integer.toString (score);
		break;
	    }
	}
	try
	{
	    PrintWriter out = new PrintWriter (new FileWriter ("SimonSaysHighScores.txt"));
	    for (int fileLine = 0 ; fileLine < fileLength / 2 ; fileLine++)
	    {
		out.println (highScores [0] [fileLine]);
		out.println (highScores [1] [fileLine]);
	    }
	    out.close ();
	}
	catch (IOException e)
	{
	}
	score = 0;
    }


    /*
    Purpose: The purpose of the goodBye method is to display a farewell screen and message and prompt the user to close the program.

    User input statement:
    1. The purpose of the user input statement is to wait and close the program when the user is ready.
       This is so that the user has time to read and see the good bye screen before the program closes (so it doesn't just flash and disappear).
    */

    private void goodBye ()
    {
	title ();

	//title
	c.setFont (new Font ("Arial", Font.PLAIN, 50));
	c.drawString ("Good Bye:", 190, 80);
	c.setFont (new Font ("Arial", Font.PLAIN, 20));
	c.drawString ("Thank you for playing Simon Says, a memory video game.", 50, 130);
	c.drawString ("By: Simon Bakan and Jeffery Hu", 50, 190);
	c.drawString ("Press any key to close:", 50, 470);

	c.setColor (Color.black);
	c.fillOval (220, 230, 200, 200);

	//Colours
	c.setColor (Color.red);
	c.fillArc (230, 240, 180, 180, 0, 90);
	c.setColor (Color.blue);
	c.fillArc (230, 240, 180, 180, 270, 90);
	c.setColor (Color.yellow);
	c.fillArc (230, 240, 180, 180, 180, 90);
	c.setColor (Color.green);
	c.fillArc (230, 240, 180, 180, 90, 90);

	//center
	c.setColor (Color.black);
	c.fillOval (295, 305, 50, 50);

	//Letters
	c.setFont (new Font ("Arial", Font.PLAIN, 35));
	c.drawString ("Q", 265, 305);
	c.drawString ("W", 340, 305);
	c.drawString ("A", 267, 375);
	c.drawString ("S", 345, 375);

	c.getChar ();
	c.close ();
    }


    /*
    Purpose: The purpose of the main method is to control the flow of the whole program

    Local Variable Dictionary:
	Name     |                Type                 |        Purpose
    -------------|-------------------------------------|--------------------------------------------------------------------------------------------------------
	  s      |       SimonSaysRevisedFinalGame     |  Creates an instance of the SimonSaysRevisedFinalGame class as an object

    Conditionals:
    1. The first conditional is an if else if statement that goes to either the game, leaderboard, instructions, or none of them depending on the user input in mainMenu.
    2. The second conditional is an if statement that breaks the inner while loop if the user chooses to exit from the level selection to the main menu .
    3. The third conditional is an if statement that breaks the inner while loop if the user chooses to exit from the difficulty selection to the main menu.
    4. The fourth conditional is an if else statement displays the appropiate screen if the user won or lost
    5. The fifth conditional is an if statement that breaks the inner while loop if the user choses to exit to the main menu from the win screen

    do while/ while loops:
    1. The first do while loop keeps running the program until the user chooses to quit in the main menu, in which case the good bye screen is displayed (in the goodBye() method)
    2. The second loop is a while(true) loop that keeps running the actual game and going back to level selection for the user to keep racking up points.
       It breaks when the user loses or chooses to exit from the levelselection/difficulty selection/win screens.
    */
    public static void main (String[] args)
    {
	SimonSaysRevisedFinalGame s = new SimonSaysRevisedFinalGame ();
	s.splashScreen ();
	do
	{
	    s.mainMenu ();
	    if (s.choice == 'c' || s.choice == 'C')
	    {
		while (true)
		{
		    s.getLevel ();
		    if (s.levelSelection == 'e' || s.levelSelection == 'E')
		    {
			break;
		    }
		    s.getDifficulty ();
		    if (s.difficultySelection == 'e' || s.difficultySelection == 'E')
		    {
			break;
		    }
		    s.getPattern (s.level * 2 + 2, s.difficultySelection);
		    s.displayAndGetData ();
		    if (s.win == true)
		    {
			s.winScreen ();
			if (s.endChoice == 'e' || s.endChoice == 'E')
			{
			    break;
			}
		    }
		    else
		    {
			s.loseScreen ();
			break;
		    }
		}
		s.writeScore ();
	    }
	    else if (s.choice == 'l' || s.choice == 'L')
	    {
		s.leaderBoard ();
	    }
	    else if (s.choice == 'i' || s.choice == 'I')
	    {
		s.instructions ();
	    }
	}
	while (s.choice != 'q' && s.choice != 'Q');
	s.goodBye ();

    } // main method
} // SimonSaysRevisedFinalGame class



