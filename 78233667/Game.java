/*  Name: Bhrajit Thakur
*  PennKey: bhrajit
*
*  Execution: java Game
*
*  DESCRIPTION: Game.java uses Board.java to run 2048. It uses public methods from
*  Board.java in order to instantiate a new board, draw the board, and then
*  based on user input, shift the board. The class also keeps a counter for how many
*  valid moves the player makes, and it also will display a victory or defeat message
*  depending on the board state.
*
*/

public class Game {
    
    public static void main(String[] args) {
        // //         TEST BOARDS
        //int[][] arr = {{2, 2, 2, 2}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0,0}};
        //int[][] arrThree = {{2, 2, 2, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0,0}};
        //int[][] arrDown = {{0, 0, 0, 2}, {0, 0, 0, 2}, {0, 0, 0, 4}, {0, 0, 0, 4}};
        //int[][] arrDow = {{2, 0, 0, 0}, {2, 0, 0, 0}, {4, 0, 0, 0}, {4, 0, 0, 0}};
        //int[][] arrRi = {{8, 16, 8, 8}, {0, 0, 4, 2}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        //int[][] arrTwo = {{0, 16, 8, 8}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        //int[][] arrThr = {{2, 2, 4, 8}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        //int[][] arrLeft = {{2, 2, 2, 2}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        //int[][] w = {{0, 0, 0, 1024}, {0, 0, 0, 1024}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        //Board b = new Board(w);
        
        // declare an empty board
        Board b = new Board();
        // insert 2 random numbers into the newly constructed board
        b.insert();
        b.insert();
        
        // draw the board
        b.draw();
        
        // declare a counting variable that will keep track of the number of moves
        int counter = 0;
        // while the game is neither won nor lost, the game is in play
        while (b.gameRunning()) {
            // generate a copy of the board to continously compare after shifts
            int[][] originalTempBoard = b.getBoardCopy();
            // if a key has been typed, then modify the board
            if (PennDraw.hasNextKeyTyped()) {
                // make a char variable that represents the key that was typed
                char key = PennDraw.nextKeyTyped();
                // shift only if key is a valid key to press
                if (key == 'w' || key == 'a' || key == 's' || key == 'd') {
                    // if a valid key has been typed, clear the board
                    PennDraw.clear();
                    // shift the board
                    b.shift(key);
                    // check to see if the board changed by the shift
                    if (b.boardChange(originalTempBoard)) {
                        // insert a new number after successful shift
                        b.insert();
                        // uptick the counter after a successful move
                        counter++;
                    }
                }
                // draw the results of the shifting and the new inserted number
                b.draw();
            }
        }
        // cacontonate counter
        String moves = "" + counter;
        // set the parameters for the font size and pen color
        PennDraw.setFontSize(28);
        PennDraw.setPenColor(PennDraw.RED);
        // check the board to see if the player wins
        if (b.checkWinningBoard()) {
            // print out a victory message
            PennDraw.text(2, 2.25, "YOU WIN! Number of Moves:" +  " " + moves);
            // check the board to see if the player loses
            } else if (b.checkLosingBoard()) {
            // print out a losing message
            PennDraw.text(2, 2.25, "YOU LOSE! Number of Moves:" +  " " + moves);
        }
    }
}
