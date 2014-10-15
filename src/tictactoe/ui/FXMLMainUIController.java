/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tictactoe.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private Game game;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void onMouseClicked(MouseEvent event){
        /*game = new TicTacToe();
        game.start(MainUI.getStage());*/
        game = Game.getInstance();
        try {
            game.start(MainUI.getStage());
        } catch (IOException ex) {
            Logger.getLogger(FXMLMainUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
