/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tictactoe.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.util.ScreenUtils;

/**
 *
 * @author Pubudu
 */
public class MainUI extends Application {
    private static Stage stage;
    private static double width;
    private static double height;
    private static MainUI screen;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setResizable(false); 
        stage.setTitle(".:: Tic-Tac-Toe ::.");
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMainUI.fxml")); 
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
//        stage.setWidth(Const.WIDTH);
//        stage.setHeight(Const.HEIGHT);
        stage.show(); 
        
        width = stage.getWidth();
        height = stage.getHeight(); 
        
        ScreenUtils.centerStage(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static Stage getStage(){
        return stage;
    }

    public static double getWidth() {
        return width;
    }

    public static double getHeight() {
        return height;
    }
    
    public static MainUI getInstance(){
        if(screen == null)
            screen = new MainUI();
        
        return screen;
    }
}
