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
    private Button playBtn;
    @FXML
    private Button newPlayerBtn;
    @FXML
    private Button leaderBoardBtn;

    //private TicTacToe game;
    private PlayerSelection selector;
    
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
}
