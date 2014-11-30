/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tictactoe.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import tictactoe.ui.Game;
import tictactoe.ui.GameComputer;
import tictactoe.ui.MainUI;
import tictactoe.ui.PlayerSelection;

/**
 *
 * @author Pubudu
 */
public class FXMLMainUIController implements Initializable {
    @FXML
    private Label tictactoeHeading;
    @FXML
    private Button leaderBoardBtn;

    //private TicTacToe game;
    private PlayerSelection selector;
    @FXML
    private Button multiPlayerBtn;
    @FXML
    private Button singlePlayerBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void onMouseClicked(MouseEvent event){
        
        selector = PlayerSelection.getInstance();
        try {
            selector.start(MainUI.getStage());
        } catch (IOException ex) {            
        }
    }
    
    @FXML
    public void onSinglePlayerBtnClicked(MouseEvent event){
        try {
            System.out.println("Button clicked!");
            GameComputer.getInstance().start(MainUI.getStage());
        } catch (IOException ex) {            
            System.out.println("ERROR in Single Player button click");
            System.out.println(ex);
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void onLeaderBoardBtnClicked(MouseEvent event){
//        try {
//            //GameComputer.getInstance().start(MainUI.getStage());
//        } catch (IOException ex) {            
//        }
    }
}
