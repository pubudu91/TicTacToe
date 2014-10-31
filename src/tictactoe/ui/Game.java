/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tictactoe.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import tictactoe.util.Const;

/**
 *
 * @author Pubudu
 */
public class Game extends Application {
    private static Game game;
    private Stage stage;
    
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        stage.setResizable(false); 
        stage.setTitle(".:: Tic-Tac-Toe ::.");
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLGame.fxml"));
        
        Scene scene = new Scene(root,Const.WIDTH+10,Const.HEIGHT+10);
        
        stage.setScene(scene);
        stage.show();
        
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2); 
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);  
    }

    private Game() { }
    
    public static Game getInstance(){
        //if(game == null)
            game = new Game();
        
        return game;
    }
    
    public Stage getStage() {
        return stage;
    }
}
