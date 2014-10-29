/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tictactoe.ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import tictactoe.controller.GridInputController;
import tictactoe.model.Board;

/**
 *
 * @author Pubudu
 */
public class TicTacToe extends Application {
    final int SIZE = 100;
    final int INC = 20;
    boolean wasCross = false;
    private final int GRID_SIZE = 3;
    Rectangle grid[][];
    
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, primaryStage.getWidth(),primaryStage.getHeight());        
        
        grid = new Rectangle[3][3];
        
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                grid[i-1][j-1] = new Rectangle(SIZE*i,SIZE*j,100,100);
                grid[i-1][j-1].setFill(Color.TRANSPARENT);
                grid[i-1][j-1].setStroke(Color.BLACK);
                final double x = grid[i-1][j-1].getX(); 
                final double y = grid[i-1][j-1].getY(); 
                grid[i-1][j-1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    
                    Line line1;
                    Line line2;
                    Circle circle;
                    
                    @Override
                    public void handle(MouseEvent event) {
                        if(line1 == null && line2 == null && circle == null){
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
                        }
                        
                    }
                    
                });
                root.getChildren().add(grid[i-1][j-1]);
            }
        }
        
        Board board = Board.getInstance();
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void registerListener(GridInputController cont){
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j].setOnMouseClicked(cont);
            }
        }
    } 
}
