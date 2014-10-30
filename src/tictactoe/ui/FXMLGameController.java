/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tictactoe.ui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import tictactoe.model.Board;
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

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        board = Board.getInstance();
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
    @FXML
    private void onGrid00Clicked(MouseEvent event) {
        String url = getURL();
        boolean moveMade = makeNextMove(0, 0);
        
        if(moveMade)
            grid00.setImage(getImage(url));
        System.out.println(moveNo);
    }

    @FXML
    private void onGrid01Clicked(MouseEvent event) {
        String url = getURL();
        boolean moveMade = makeNextMove(0, 1);
        
        if(moveMade)
            grid01.setImage(getImage(url));
        System.out.println(moveNo);
    }
    
    @FXML
    private void onGrid02Clicked(MouseEvent event) {
        String url = getURL();
        boolean moveMade = makeNextMove(0, 2);
        
        if(moveMade)
            grid02.setImage(getImage(url));
        System.out.println(moveNo);
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
}
