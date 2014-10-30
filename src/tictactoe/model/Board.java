/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.model;

import dataaccess.GameDBAccess;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tictactoe.util.State;

/**
 *
 * @author Pubudu
 */
public class Board {

    final static int GRID_SIZE = 3; // set the size of the tic-tac-toe grid
    private static Board game; // singleton representing the whole game board
    private Player player1;  // player 1 is 'X'
    private Player player2;  // player 2 is 'O'
    private State grid[][]; // the actual grid on which the game is played
    private boolean gameOver;

    private Board() {
        grid = new State[GRID_SIZE][GRID_SIZE]; // set up the grid
        gameOver = false;

        /* initialize the grid to EMPTY at the beginning of a game */
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = State.EMPTY;
            }
        }
    }

    public static Board getInstance() {
        if (game == null) {
            game = new Board();
        }
        return game;
    }

    public static Board getInstance(boolean isNew) {
        game = new Board();
        return game;
    }

    public boolean makeAMove(State state, int x, int y) {
        int draw = 0;
        GameDBAccess gameDBAccess = new GameDBAccess();
        if (!isTaken(x, y) && !gameOver) {
            grid[x][y] = state;
            draw++;
        } else {
            return false;
        }

        if (hasWon(state, x, y)) {
            try {                
                if (!gameDBAccess.isExisting(player1.getUserName())) {
                    gameDBAccess.addNewPlayer(player1.getUserName());
                }
                if (!gameDBAccess.isExisting(player2.getUserName())) {
                    gameDBAccess.addNewPlayer(player2.getUserName());
                }
                if (state == State.X) {
                    gameDBAccess.incrementWins(player1.getUserName());
                    gameDBAccess.incrementLosses(player2.getUserName());

                } else if (state == State.O) {
                    gameDBAccess.incrementWins(player2.getUserName());
                    gameDBAccess.incrementLosses(player1.getUserName());
                }
                gameOver = true;
                return true;
            } catch (ClassNotFoundException | SQLException ex) {

            }
        }
        
        if(isDraw(draw, gameOver)){
            try {
                if (!gameDBAccess.isExisting(player1.getUserName())) {
                    gameDBAccess.addNewPlayer(player1.getUserName());
                }
                if (!gameDBAccess.isExisting(player2.getUserName())) {
                    gameDBAccess.addNewPlayer(player2.getUserName());
                }
                gameDBAccess.incrementDraws(player1.getUserName());
                gameDBAccess.incrementDraws(player2.getUserName());
                gameOver = true;
                return true;
            } catch (ClassNotFoundException | SQLException ex) {                
            }
        }
        return true;
    }

    /* algorithm taken from http://www.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html */
    public boolean hasWon(State target, int row, int col) {
        return (grid[row][0] == target // 3-in-the-row
                && grid[row][1] == target
                && grid[row][2] == target
                || grid[0][col] == target // 3-in-the-column
                && grid[1][col] == target
                && grid[2][col] == target
                || row == col // 3-in-the-diagonal
                && grid[0][0] == target
                && grid[1][1] == target
                && grid[2][2] == target
                || row + col == 2 // 3-in-the-opposite-diagonal
                && grid[0][2] == target
                && grid[1][1] == target
                && grid[2][0] == target);
    }

    public boolean isDraw(int draw, boolean gameOver){        
        if(draw == 9 && !gameOver){
            return true;
        }
        return false;
    }
    public boolean isTaken(int x, int y) {
        return grid[x][y] != State.EMPTY;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
