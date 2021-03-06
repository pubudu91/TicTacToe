/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tictactoe.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.util.Const;
import tictactoe.util.ScreenUtils;

/**
 *
 * @author Pubudu
 */
public class GameComputer extends Application {
    private static GameComputer game;
    private Stage stage;
    private static String player1;
    
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        stage.setResizable(false); 
        stage.setTitle(".:: Tic-Tac-Toe ::.");
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLGameComputer.fxml"));
        
        Scene scene = new Scene(root,Const.WIDTH+10,Const.HEIGHT+10);
        
        stage.setScene(scene);
        stage.show();
        
        ScreenUtils.centerStage(stage); 
    }

    private GameComputer() { }
    
    public static GameComputer getInstance(){
        if(game == null)
            game = new GameComputer();
        
        return game;
    }
    
    public static GameComputer getInstance(String name){
        player1 = name;
        return getInstance();
    }
    
    public String getPlayer1Name() {
        return player1;
    }
    
    public Stage getStage() {
        return stage;
    }
}
