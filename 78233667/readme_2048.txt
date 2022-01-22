/**********************************************************************
 *  readme template
 *  Project: 2048
 **********************************************************************/

Name: Bhrajit Thakur
PennKey: bhrajit
Hours to complete assignment (optional): 25



/**********************************************************************
 *  Please list all help, collaboration, and outside resources
 *  you used here. 
 *
 *  If you did not get any help in outside of TA office hours,
 *  and did not use any materials outside of the standard
 *  course materials and piazza, write the following statement below:
 *  "I did not receive any help outside of TA office hours.  I
 *  did not collaborate with anyone, and I did not use any
 *  resources beyond the standard course materials."
 **********************************************************************/

I did not receive any help outside of TA office hours, did not collaborate
with anyone, and I did not use any resources beyond the standard course 
materials.


/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/

Figuring out how to shift the array was super difficult. I had to differentiate that
shifting and merging are two different steps and that you also have to
shift after you merge. It was also really hard to figure out the edge cases
for 2048, like the 3 or 4 in the row because I kept trying to add if statements
instead of changing the logic of the iteration.


/**********************************************************************
 *  If you completed the extra credit or added any additional features,
 *  provide DETAILED and CLEAR instructions for how to use them and 
 *  what we should look for when grading your assignment.
 *********************************************************************/

I included the color gradient extra credit portion in the draw function
in Board.java. It will be in the draw method in Board.java.


/**********************************************************************
 *  Instructions                                    
 **********************************************************************/

Run Game.java with no command line arguments

/**********************************************************************
 * Description of each file
 *********************************************************************/
 
 Board.java essentially writes the rules to the game 2048, and Game.java
 executes those rules, while having a broader perspective on the game state.
 Board.java declares the 2D array, outlines how to manipulate the array, draws the
 array, and methods to see if the board is still playable. Game.java uses Board.java
 to draw a preliminary board with two numbers. If a valid key is pressed, then
 run the appropriate changes to the gamestate of the board from Board.java. Game
 also stops the game when the game is won or lost, and will display whether or
 you won or lost the game.