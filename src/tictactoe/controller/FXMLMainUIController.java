/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tictactoe.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import tictactoe.ui.GameComputer;
import tictactoe.ui.LeaderBoard;
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
            System.out.println("Single Player Button clicked!");
            Optional<String> response = Dialogs.create()
                                    .owner(MainUI.getStage())
                                    .title("Single Player Mode")
                                    .masthead("Player 1 Details")
                                    .message("Please enter your name:")
                                    .styleClass(Dialog.STYLE_CLASS_UNDECORATED)
                                    .showTextInput();
            
            String player1 = "";
            
            if(response.isPresent())
                player1 = response.get();
            else 
                return;
            
            GameComputer.getInstance(player1).start(MainUI.getStage());
        } catch (IOException ex) {            
            System.out.println("ERROR in Single Player button click");
            System.out.println(ex);
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void onLeaderBoardBtnClicked(MouseEvent event){
        try {
            System.out.println("Button clicked!");
            LeaderBoard.getInstance().start(MainUI.getStage());
        } catch (IOException ex) {            
            System.out.println("ERROR in Single Player button click");
            System.out.println(ex);
            ex.printStackTrace();
        }
    }
}
