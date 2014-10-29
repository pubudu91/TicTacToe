/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tictactoe.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import tictactoe.model.Board;
import tictactoe.ui.TicTacToe;

/**
 *
 * @author Pubudu
 */
public class GridInputController implements EventHandler<MouseEvent> {
    
    static TicTacToe gameBoard;
    Board model;
    boolean wasCross = false;

    public GridInputController() {
        gameBoard = new TicTacToe();
        model = Board.getInstance();
    }
    
    public static void main(String[] args) {
        GridInputController cnt = new GridInputController();
        GridInputController.gameBoard.registerListener(cnt);
        TicTacToe.launch(args);
        
    }

    @Override
    public void handle(MouseEvent event) {
        Rectangle rect = (Rectangle)event.getSource();
        System.out.println("Success");
        /*if(line1 == null && line2 == null && circle == null){
            if(!wasCross){
                line1 = new Line(x+INC,y+INC,x+SIZE-INC,y+SIZE-INC);
                line2 = new Line(x+SIZE-INC,y+INC,x+INC,y+SIZE-INC);
                root.getChildren().add(line1);
                root.getChildren().add(line2);
                wasCross = true;
            }
            else{
                circle = new Circle(x+SIZE/2,y+SIZE/2,(SIZE-INC*2)/2);
                circle.setFill(Color.TRANSPARENT);
                circle.setStroke(Color.BLACK);
                root.getChildren().add(circle);
                wasCross = false;
            }
        }*/

        
    }
    
    
}
