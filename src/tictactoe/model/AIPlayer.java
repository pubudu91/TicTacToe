/*
CODE ADAPTED FROM THE SOURCE GIVEN HERE:
http://www.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe_AI.html
 */

package tictactoe.model;

import tictactoe.util.State;

/**
 *
 * @author Pubudu
 */
public abstract class AIPlayer {
   protected int ROWS = 3;  // number of rows
   protected int COLS = 3;  // number of columns
 
   protected State[][] board; // the board's ROWS-by-COLS array of Cells
   protected State mySeed;    // computer's seed
   protected State oppSeed;   // opponent's seed
 
   /** Constructor with reference to game board */
   public AIPlayer(Board board) {
      this.board = board.getGrid();
   }
 
   /** Set/change the seed used by computer and opponent */
   public void setSeed(State seed) {
      this.mySeed = seed;
      oppSeed = (mySeed == State.X) ? State.O : State.X;
   }
 
   /** Abstract method to get next move. Return int[2] of {row, col} */
   abstract int[] move();  // to be implemented by subclasses
}