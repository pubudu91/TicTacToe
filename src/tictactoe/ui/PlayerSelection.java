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

/**
 *
 * @author Mampitiya
 */
public class PlayerSelection extends Application{
    private static PlayerSelection selector;
    
    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false); 
        stage.setTitle(".:: Tic-Tac-Toe ::.");
        stage.setX(530);
        stage.setY(250);
        Parent root = FXMLLoader.load(getClass().getResource("FXMLPlayerSelection.fxml"));
        
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.show();
    }

    private PlayerSelection() { }
    
    public static PlayerSelection getInstance(){
        if(selector == null)
            selector = new PlayerSelection();
        
        return selector;
    }
}
