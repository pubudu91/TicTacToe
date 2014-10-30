/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import tictactoe.model.Board;
import tictactoe.model.Player;

/**
 * FXML Controller class
 *
 * @author Mampitiya
 */
public class FXMLPlayerSelectionController implements Initializable {

    //private TicTacToe game;
    private Game game;
    private Board board;
    
    @FXML
    private TextField player1Txt;
    
    @FXML
    private TextField player2Txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void onMouseClicked(MouseEvent event) {        
        game = Game.getInstance();
        board = Board.getInstance();
        System.out.println(player1Txt);
        board.setPlayer1(new Player(player1Txt.getText()));
        board.setPlayer2(new Player(player2Txt.getText()));
        try {
            game.start(MainUI.getStage());
        } catch (IOException ex) {
        }
    }
}
