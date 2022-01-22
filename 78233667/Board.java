/*  Name: Bhrajit Thakur
*  PennKey: bhrajit
*
*  Execution: used in Game.java
*
*  DESCRIPTION: constructs a 4 x 4 grid that represents a table from the game 2048.
*  The class has methods to check the game state, such as if the game is won, lost,
*  or over. The class also has methods to shift and merge different elements of the
*  2D array based on the cardinal directions. It is able to randomly insert a 2
*  or 4 into a randomly, unnocupied location in the grid. Lastly, it can draw the
*. the contents of the board.
*/

public class Board {
    private int[][] board;
    
    // constructor for an input board for testing
    public Board(int[][] board) {
        // if test board is null or row length is not 4, throw an error
        if (board == null || board.length != 4) {
            throw new IllegalArgumentException();
        }
        
        for (int i = 0; i < board.length; i++) {
            // if column length is not 4, throw an error
            if (board[i].length != 4) {
                throw new IllegalArgumentException();
            }
        }
        this.board = board;
    }
    // constructor for an empty board
    public Board() {
        // declare the size of the board
        board = new int[4][4];
    }
    
    /**
    * Inputs: none
    * Outputs: a boolean that is true when the game is won
    * Description: iterates through the entire 2d array and looks for 2048; if it
    * finds one, then the player has won the game.
    */
    public boolean checkWinningBoard() {
        boolean gameWon = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // after iterating through the array, check for 2048
                if (board[i][j] == 2048) {
                    // return true if 2048 found
                    gameWon = true;
                }
            }
        }
        return gameWon;
    }
    /**
    * Inputs: none
    * Outputs: a boolean that returns true if the board is full and there are no
    * possible moves to merge.
    * Description: checks the game state to see if the game is lost by checking
    * the total number of non-zero numbers in the 2d array. If it's 16, check
    * to see if there are any valid merges left. If so, then
    */
    public boolean checkLosingBoard() {
        boolean gameRunning = false;
        // declare a counter variable to see how many valid numbers are in the array
        int counter = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // increase counter by 1 if there's a number that's not 0
                if (board[i][j] != 0) {
                    counter++;
                }
            }
        }
        //  the board can still be merged when full
        if (counter == 16) {
            // check to see if it can be merged; if not, the game runs
            gameRunning = !canMerge();
        }
        return gameRunning;
    }
    /**
    * Inputs: none
    * Outputs: a boolean that is true when the board can be merged
    * Description: combines four helper functions of merging in different directions
    * to see if there is a possible move in any direction
    */
    private boolean canMerge() {
        boolean canMerge = false;
        // board can merge if it can merge in any of the four cardinal directions
        canMerge = canMergeUp() || canMergeLeft() || canMergeRight() ||
        canMergeDown();
        return canMerge;
    }
    /**
    * Inputs: none
    * Outputs: a boolean that is true when the board can be merged up
    * Description: checks to see if there's 2 adjacent cells that are equal and
    * can be merged upwards
    */
    private boolean canMergeUp() {
        boolean canMergeUp = false;
        // don't look at the first row because of index out of bonds exception
        for (int i = 1; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                //i - 1 is the index above i in the board
                if (board[i][j] == board[i - 1][j]) {
                    // if they can be merged, set the boolean to true
                    canMergeUp = true;
                }
            }
        }
        return canMergeUp;
    }
    /**
    * Inputs: none
    * Outputs: a boolean that is true when the board can be merged left
    * Description: checks to see if there's 2 adjacent cells that are equal and
    * can be merged left
    */
    private boolean canMergeLeft() {
        boolean canMergeLeft = false;
        for (int i = 0; i < board.length; i++) {
            // don't look at the first column because of out of bonds exception
            for (int j = 1; j < board[i].length; j++) {
                //j - 1 is the index left of j in the board
                if (board[i][j] == board[i][j - 1]) {
                    // if they can be merged, set the boolean to true
                    canMergeLeft = true;
                }
            }
        }
        return canMergeLeft;
    }
    /**
    * Inputs: none
    * Outputs: a boolean that is true when the board can be merged right
    * Description: checks to see if there's 2 adjacent cells that are equal and
    * can be merged right
    */
    private boolean canMergeRight() {
        boolean canMergeRight = false;
        for (int i = 0; i < board.length; i++) {
            // loop through the 2d array except for the far right column
            for (int j = 0; j < 3; j++) {
                // j + 1 is the index right of j in the board
                if (board[i][j] == board[i][j + 1]) {
                    // if they can be merged, set the boolean to true
                    canMergeRight = true;
                }
            }
        }
        return canMergeRight;
    }
    /**
    * Inputs: none
    * Outputs: a boolean that is true when the board can be merged down
    * Description: checks to see if there's 2 adjacent cells that are equal and
    * can be merged down
    */
    private boolean canMergeDown() {
        boolean canMergeDown = false;
        // don't look at i = 3 because of index out of bounds exception
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // i + 1 is the index down of i in the board
                if (board[i][j] == board[i + 1][j]) {
                    // if they can be merged, set the boolean to true
                    canMergeDown = true;
                }
            }
        }
        return canMergeDown;
    }
    
    /**
    * Inputs: none
    * Outputs: boolean that returns if the game is running
    * Description: checks both conditions of winning and losing. If the player
    * has not lost nor has won, then the game is still running
    */
    public boolean gameRunning() {
        // declare a boolean to see if the game is still going on
        boolean isGameRunning = true;
        // if the game is not won and not lost, then it is not over
        isGameRunning = !checkWinningBoard() && !checkLosingBoard();
        return isGameRunning;
    }
    
    /**
    * Inputs: none
    * Outputs: none
    * Description: draws the 4 x 4 grid and draws the 2d array in a grid formation,
    * where each number is in a different colored box (for extra credit)
    */
    public void draw() {
        // change the scale to make it simpler for a 4 x 4 grid
        PennDraw.setScale(0, 4);
        // make the grid lines are skinny so they don't clutter the screen
        PennDraw.setPenRadius(.001);
        PennDraw.setPenColor(PennDraw.BLACK);
        // draw a 4 x 4 grid
        PennDraw.line(1, 0, 1, 4);
        PennDraw.line(2, 0, 2, 4);
        PennDraw.line(3, 0, 3, 4);
        PennDraw.line(0, 3, 4, 3);
        PennDraw.line(0, 2, 4, 2);
        PennDraw.line(0, 1, 4, 1);
        // iterature through the entire 2d array
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // don't draw any zeroes
                if (board[i][j] > 0) {
                    // convert each integer at an index into a string to then draw
                    String value = board[i][j] + "";
                    PennDraw.setPenRadius(.05);
                    // for extra credit, create a color gradient for each number
                    if (board[i][j] == 2) {
                        PennDraw.setPenColor(PennDraw.BLACK);
                        } else if (board[i][j] == 4) {
                        PennDraw.setPenColor(PennDraw.BLUE);
                        } else if (board[i][j] == 8) {
                        PennDraw.setPenColor(PennDraw.BOOK_RED);
                        } else if (board[i][j] == 16) {
                        PennDraw.setPenColor(PennDraw.DARK_GRAY);
                        } else if (board[i][j] == 32) {
                        PennDraw.setPenColor(PennDraw.GREEN);
                        } else if (board[i][j] == 64) {
                        PennDraw.setPenColor(PennDraw.MAGENTA);
                        } else if (board[i][j] == 128) {
                        PennDraw.setPenColor(PennDraw.ORANGE);
                        } else if (board[i][j] == 256) {
                        PennDraw.setPenColor(PennDraw.PINK);
                        } else if (board[i][j] == 512) {
                        PennDraw.setPenColor(PennDraw.RED);
                        } else if (board[i][j] == 1024) {
                        PennDraw.setPenColor(PennDraw.YELLOW);
                        } else if (board[i][j] == 2048) {
                        PennDraw.setPenColor(PennDraw.BOOK_LIGHT_BLUE);
                    }
                    // draw the box in the correct place, which is determined by
                    // flipping the 2d array values vertically and adding .5 to
                    // be placed in center
                    PennDraw.filledSquare(j + .5, Math.abs(i - 3.5), .4);
                    // set the color of the number to white
                    PennDraw.setPenColor(PennDraw.WHITE);
                    PennDraw.setFontSize(32);
                    // same logic as the filled Square in terms of location
                    PennDraw.text(j + 0.5, Math.abs(i - 3.5), value);
                }
            }
        }
    }
    
    /**
    * Inputs: a character key that represents a key pressed
    * Outputs: none
    * Description: assosciate a key pressed with a corresponding shift function.
    * Then, shift thrice to ensure that it shifts fully and doesn't stop, merge
    * adjacent numbers, then shift twice again so that the nonmerged numbers fill
    * the spot of a number that was merged. The reason why this function shifts
    * multiple times, instead of once, is because multiple merges might cause such a
    * difference in the 2d array that the board should shift again to account
    * for edge situations. It also won't change the board state to shift multiple
    * times if it isn't in these edge sitautions because the numbers will already
    * be in the correct position.
    */
    public void shift(char key) {
        // if key is w, do the actions related to moving up
        if (key == 'w') {
            // shifting three times ensures that there will be no bug
            // where one isn't shifted if a lot of tiles are merged in one move
            shiftUp();
            shiftUp();
            shiftUp();
            // merge after shifting
            mergeUp();
            // shift again to account for the merge
            shiftUp();
            // in certain edge cases, will need to shift again
            shiftUp();
        }
        // if key is a, do the actions related to moving left
        if (key == 'a') {
            // shifting three times ensures that there will be no bug
            // where one isn't shifted if a lot of tiles are merged in one move
            shiftLeft();
            shiftLeft();
            shiftLeft();
            // merge after shifting
            mergeLeft();
            // shift again to account for the merge
            shiftLeft();
            // in certain edge cases, will need to shift again
            shiftLeft();
        }
        // if key is s, do the actions related to moving down
        if (key == 's') {
            // shifting three times ensures that there will be no bug
            // where one isn't shifted if a lot of tiles are merged in one move
            shiftDown();
            shiftDown();
            shiftDown();
            // merge after shifting
            mergeDown();
            // shift again to account for the merge
            shiftDown();
            // in certain edge cases, will need to shift again
            shiftDown();
        }
        // if key is d, do the actions related to moving right
        if (key == 'd') {
            // shifting three times ensures that there will be no bug
            // where one isn't shifted if a lot of tiles are merged in one move
            shiftRight();
            shiftRight();
            shiftRight();
            // merge after shifting
            mergeRight();
            shiftRight();
            // in certain edge cases, will need to shift again
            shiftRight();
        }
    }
    
    /**
    * Inputs: none
    * Outputs: none
    * Description: shifts, if possible, each number upwards and replaces the number
    * below with the the number above in the board
    *
    */
    private void shiftUp() {
        int initialValue = 0;
        // don't look at i = 0 because the top row can't be shifted up
        for (int i = 1; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i - 1][j] == 0) {
                    // save the initial board[i][j]
                    initialValue = board[i][j];
                    // set the index to be equal to the index above
                    board[i][j] = board[i - 1][j];
                    // set the index above equal to the initial value
                    board[i - 1][j] = initialValue;
                }
            }
        }
    }
    
    /**
    * Inputs: none
    * Outputs: none
    * Description: shifts, if possible, each number left and replaces the number
    * to right with the the number to the left in the board
    *
    */
    private void shiftLeft() {
        int initialValue = 0;
        for (int i = 0; i < board.length; i++) {
            // don't look at j = 0 because the first column can't be shifted left
            for (int j = 1; j < board[i].length; j++) {
                if (board[i][j - 1] == 0) {
                    // save the initial board[i][j]
                    initialValue = board[i][j];
                    // set the index to be equal to the index to the left
                    board[i][j] = board[i][j - 1];
                    // set the index to the left equal to the initial value
                    board[i][j - 1] = initialValue;
                }
            }
        }
    }
    
    /**
    * Inputs: none
    * Outputs: none
    * Description: shifts, if possible, each number right and replaces the number
    * below with the the number to the right in the board
    *
    */
    private void shiftRight() {
        int initialValue = 0;
        for (int i = 0; i < board.length; i++) {
            // pass over j = 3 because the rightmost column can't be shifted right
            for (int j = 0; j < 3; j++) {
                if (board[i][j + 1] == 0) {
                    // save the initial board[i][j]
                    initialValue = board[i][j];
                    // set the index to be equal to the index to the right
                    board[i][j] = board[i][j + 1];
                    // set the index to the right equal to the initial value
                    board[i][j + 1] = initialValue;
                }
            }
        }
    }
    /**
    * Inputs: none
    * Outputs: none
    * Description: shifts, if possible, each number down and replaces the number
    * below with the the number below in the board
    *
    */
    private void shiftDown() {
        int initialValue = 0;
        // pass over i = 0 because the bottom row can't be shiftedn down
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i + 1][j] == 0) {
                    // save the initial board[i][j]
                    initialValue = board[i][j];
                    // set the index to be equal to the index below
                    board[i][j] = board[i + 1][j];
                    // set the index below to the initial value
                    board[i + 1][j] = initialValue;
                }
            }
        }
    }
    
    /**
    * Inputs: none
    * Outputs: none
    * Description: merge 2 numbers adjacent vertically to another upwards. Set
    * the bottomost number that is merged equal to 0 because that index is now
    * empty. It iterates up to down, so that it follows the case in which
    * there are 3 or 4 numbers. In those situations, the numbers above are
    * merged first and have merging priority.
    */
    private void mergeUp() {
        // pass over i = 0 because index of out of bounds exception
        for (int i = 1; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // if the value above is equal to the element in the index
                if (board[i][j] == board[i - 1][j]) {
                    // set the value above to be the sum of both numbers
                    board[i - 1][j] = board[i][j] + board[i - 1][j];
                    // set the value to 0 because now it has been merged
                    board[i][j] = 0;
                }
            }
        }
    }
    /**
    * Inputs: none
    * Outputs: none
    * Description: merge 2 numbers adjacent horizontally to another on its left. Set
    * the right number that is merged equal to 0 because that index is now
    * empty. It iterates up left to right, so that it follows the case in which
    * there are 3 or 4 numbers. In those situations, the numbers on the left are
    * merged first and have merging priority.
    */
    private void mergeLeft() {
        for (int i = 0; i < board.length; i++) {
            // pass over j = 1 because index of out of bounds exception
            for (int j = 1; j < board[i].length; j++) {
                // if the value above is equal to the element in the index
                if (board[i][j] == board[i][j - 1]) {
                    // set the value to the left to be the sum of both numbers
                    board[i][j - 1] = board[i][j] + board[i][j  - 1];
                    // set the value to 0 because now it has been merged
                    board[i][j] = 0;
                }
            }
        }
    }
    /**
    * Inputs: none
    * Outputs: none
    * Description: merge 2 numbers adjacent horizontally to another on its right.
    * Set the left number that is merged equal to 0 because that index is now
    * empty. It iterates up right to left, so that it follows the case in which
    * there are 3 or 4 numbers. In those situations, the numbers on the right are
    * merged first and have merging priority.
    */
    private void mergeRight() {
        for (int i = 0; i < board.length; i++) {
            // loop through the 2d array except for the far right column
            for (int j = 3; j > 0; j--) {
                // if there's adjacent numbers that are adjacently equal to another
                if (board[i][j] == board[i][j - 1]) {
                    // set the number to the right to the sum of the two numbers
                    board[i][j] = board[i][j] + board[i][j - 1];
                    // set the initial number equal to 0
                    board[i][j - 1] = 0;
                }
            }
        }
    }
    
    /**
    * Inputs: none
    * Outputs: none
    * Description: merge 2 numbers adjacent vertically to another below. Set
    * the upmost number that is merged equal to 0 because that index is now
    * empty. It iterates up down to up, so that it follows the case in which
    * there are 3 or 4 numbers. In those situations, the numbers below should
    * are merged first and have merging priority.
    */
    private void mergeDown() {
        // loop through the 2d array except for the far bottom
        for (int i = 3; i > 0; i--) {
            for (int j = 0; j < board[i].length; j++) {
                // if there's adjacent numbers that are downwardly equal to another
                if (board[i][j] == board[i - 1][j]) {
                    // set the number below to the sum of the two numbers
                    board[i][j] = board[i][j]  + board[i - 1][j];
                    // set the initial number equal to 0
                    board[i - 1][j] = 0;
                }
            }
        }
    }
    /**
    * Inputs: a 2d array of ints called tempBoard
    * Outputs: a boolean that represents whether or not the tempBoard equals
    * to board
    * Description: checks to see if the board state has changed by comparing
    * each value in temp board and board. This function helps with checking
    * if a valid move in Game.java has been made because a valid move would
    * affect the board.
    */
    public boolean boardChange(int[][] tempBoard) {
        boolean didBoardChange = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // if a single value isn't equal, set the boolean to true
                if (board[i][j] != tempBoard[i][j]) {
                    didBoardChange = true;
                }
            }
        }
        return didBoardChange;
    }
    /**
    * Inputs: none
    * Outputs: a 2d array that represents board. This acts similarly to a getter
    * function.
    * Description: generates a copy of board in the form of a 2d array. This helps
    * with didBoardChange because it converts the board state into a 2d array.
    */
    public int[][] getBoardCopy() {
        // the grid is 4 x 4
        int[][] tempBoard = new int[4][4];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // generate a copy by assigning each value in tempBoard to board
                tempBoard[i][j] = board[i][j];
            }
        }
        return tempBoard;
    }
    /**
    * Inputs: none
    * Outputs: none
    * Description: inserts a 2 or 4 in an empty position with a 50% for each number
    * into the board. It randomly generate a number that is assigned with a
    * position on the board. If that position is filled, then generate another
    * number. Keep generating random numbers until a 2 or 4 has been inserted.
    */
    public void insert() {
        // have a default condition so that if it hasn't inserted anything
        boolean hasNotInsertedTwoOrFour = true;
        int randomTwoOrFour = 0;
        
        // inserted number has a 50% chance to be a 2 and 50% chance to be a 4
        double randomTwoOrFourProbability = Math.random();
        if (randomTwoOrFourProbability < .5) {
            randomTwoOrFour = 2;
        }
        if (randomTwoOrFourProbability >= .5) {
            randomTwoOrFour = 4;
        }
        double randNum = 0;
        while (hasNotInsertedTwoOrFour) {
            // multiply Math.random() by 4 so that it is divisible by 16 for the
            // 16 positions
            randNum = Math.random() * 4;
            // by dividing 4 by 16 and using each interval as a condition, each
            // position on the board has the same chance of being called
            if (randNum < .25 && board[0][0] == 0) {
                // set the number in the the array to 2 or 4
                board[0][0] = randomTwoOrFour;
                // if something has been inserted, set the boolean to false
                hasNotInsertedTwoOrFour = false;
                // repeat the above logic for each interval
                } else if (randNum < .5 && randNum >= .25 && board[0][1] == 0) {
                board[0][1] = randomTwoOrFour;
                hasNotInsertedTwoOrFour = false;
                } else if (randNum < .75 && randNum >= .5 && board[0][2] == 0) {
                board[0][2] = randomTwoOrFour;
                hasNotInsertedTwoOrFour = false;
                } else if (randNum < 1 && randNum >= .75 && board[0][3] == 0) {
                board[0][3] = randomTwoOrFour;
                hasNotInsertedTwoOrFour = false;
                } else if (randNum < 1.25 && randNum >= 1 && board[1][0] == 0) {
                board[1][0] = randomTwoOrFour;
                hasNotInsertedTwoOrFour = false;
                } else if (randNum < 1.5 && randNum >= 1.25 && board[1][1] == 0) {
                board[1][1] = randomTwoOrFour;
                hasNotInsertedTwoOrFour = false;
                } else if (randNum < 1.75 && randNum >= 1.5 && board[1][2] == 0) {
                board[1][2] = randomTwoOrFour;
                hasNotInsertedTwoOrFour = false;
                } else if (randNum < 2 && randNum >= 1.75 && board[1][3] == 0) {
                board[1][3] = randomTwoOrFour;
                hasNotInsertedTwoOrFour = false;
                } else if (randNum < 2.25 && randNum >= 2 && board[2][0] == 0) {
                board[2][0] = randomTwoOrFour;
                hasNotInsertedTwoOrFour = false;
                } else if (randNum < 2.5 && randNum >= 2.25 && board[2][1] == 0) {
                board[2][1] = randomTwoOrFour;
                hasNotInsertedTwoOrFour = false;
                } else if (randNum < 2.75 && randNum >= 2.5 && board[2][2] == 0) {
                board[2][2] = randomTwoOrFour;
                hasNotInsertedTwoOrFour = false;
                } else if (randNum < 3 && randNum >= 2.75 && board[2][3] == 0) {
                board[2][3] = randomTwoOrFour;
                hasNotInsertedTwoOrFour = false;
                } else if (randNum < 3.25 && randNum >= 3 && board[3][0] == 0) {
                board[3][0] = randomTwoOrFour;
                hasNotInsertedTwoOrFour = false;
                } else if (randNum < 3.5 && randNum >= 3.25 && board[3][1] == 0) {
                board[3][1] = randomTwoOrFour;
                hasNotInsertedTwoOrFour = false;
                } else if (randNum < 3.75 && randNum >= 3.5 && board[3][2] == 0) {
                board[3][2] = randomTwoOrFour;
                hasNotInsertedTwoOrFour = false;
                } else if (randNum < 4 && randNum >= 3.75 && board[3][3] == 0) {
                board[3][3] = randomTwoOrFour;
                hasNotInsertedTwoOrFour = false;
                // the random number generated was in a location that is filled, so
                // update the boolean to reflect that nothing was inserted
                } else {
                hasNotInsertedTwoOrFour = true;
            }
        }
    }
}
