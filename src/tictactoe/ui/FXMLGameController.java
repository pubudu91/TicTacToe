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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import tictactoe.model.Board;
import tictactoe.model.Player;
import tictactoe.util.State;

/**
 * FXML Controller class
 *
 * @author Pubudu
 */
public class FXMLGameController implements Initializable {
    /* Models */
    private Board board;
    private int moveNo;
    
    /* Views */
    private MainUI mainScreen;
    private Game game;
    @FXML
    private GridPane grid;
    @FXML
    private Button backBtn;
    @FXML
    private ImageView grid00;
    @FXML
    private ImageView grid01;
    @FXML
    private ImageView grid02;
    @FXML
    private ImageView grid10;
    @FXML
    private ImageView grid11;
    @FXML
    private ImageView grid12;
    @FXML
    private ImageView grid20;
    @FXML
    private ImageView grid21;
    @FXML
    private ImageView grid22;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        board = Board.getInstance(); // GET A NEW BOARD!!
        game = Game.getInstance();
        moveNo = 0;
    }    
    
    @FXML
    public void onMouseClicked(MouseEvent event){
        mainScreen = MainUI.getInstance();
        try {
            mainScreen.start(mainScreen.getStage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean makeNextMove(int x, int y) {
        if(isNextMoveCross())
            return board.makeAMove(State.X, x, y);
        else
            return board.makeAMove(State.O, x, y);
    }
    
    private Image getImage(String url) {
        moveNo++;
        return new Image(getClass().getResourceAsStream(url));
    }
    
    private boolean isNextMoveCross() {
        return moveNo%2 == 0;
    }
    
    private String getURL() {
        if(moveNo%2 == 0)
            return "/tictactoe/res/cross.png";
        else
            return "/tictactoe/res/circle.png";
    }
    
    private void resetGame() {
        Player p1 = board.getPlayer1();
        Player p2 = board.getPlayer2();
        
        Board.reset();
        board = Board.getInstance();
        board.setPlayer1(p1);
        board.setPlayer2(p2);
        
        //System.out.println(board.getPlayer1().getUserName());
        
        moveNo = 0;

        try {
            System.out.println("STAGE "+game.getStage()==null);
            game.start(game.getStage()); 
        } catch (IOException ex) {
            Logger.getLogger(FXMLGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onGridClicked(MouseEvent event) {
        ImageView view = (ImageView) event.getSource();
        int row = GridPane.getRowIndex(view);
        int col = GridPane.getColumnIndex(view);
        
        view.setScaleX(.75);
        view.setScaleY(.75);
        
        String url = getURL();
        boolean moveMade = makeNextMove(row, col);
        //System.out.println(view+" "+moveMade+" "+board.getPlayer1()==null);
        System.out.println(moveMade+" "+moveNo);
        
        if(moveMade){
            view.setImage(getImage(url));
            
            Player winner;

            if(board.isGameOver()){
                if((winner=board.getWinner()) != null){
                    Dialogs.create()
                            .owner(game.getStage())
                            .title("We have a winner!")
                            .masthead("Congratulations!!!")
                            .message(winner.getUserName()+" WINS!!!")
                            .showInformation();
                }
                else{
                    Dialogs.create()
                            .owner(game.getStage())
                            .title("Good game!")
                            .masthead("DRAW!!!")
                            .message("Better luck next time...")
                            .styleClass(Dialog.STYLE_CLASS_UNDECORATED)
                            .showInformation();
                }
                //resetGame();
                //System.out.println(board.getPlayer1().getUserName());
                Board.reset();
            }       
        }
    }
}
