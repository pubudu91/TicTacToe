/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tictactoe.controller;

import java.net.URL;
import java.util.Optional;
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
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import tictactoe.model.AIPlayerMinimax;
import tictactoe.model.Board;
import tictactoe.model.Player;
import tictactoe.ui.Game;
import tictactoe.ui.GameComputer;
import tictactoe.ui.MainUI;
import tictactoe.util.State;

/**
 * FXML Controller class
 *
 * @author Pubudu
 */
public class FXMLGameComputerController implements Initializable {
 /* Models */
    private Board board;
    private AIPlayerMinimax computer;
    private int moveNo;
    private Player winner;
    
    /* Views */
    private MainUI mainScreen;
    private GameComputer game;
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
    @FXML
    private Label player1Label;
    @FXML
    private Label player2Label;
    @FXML
    private Label p2ScoreLabel;
    @FXML
    private Label p1ScoreLabel;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        board = Board.getInstance(); // GET A NEW BOARD!!
        game = GameComputer.getInstance();
        moveNo = 0;
        setUpPlayers(); 
        
        Optional<String> response = Dialogs.create()
                                    .owner(game.getStage())
                                    .title("Single Player Mode")
                                    .masthead("Player 1 Details")
                                    .message("Please enter your name:")
                                    .styleClass(Dialog.STYLE_CLASS_UNDECORATED)
                                    .showTextInput();
        
        if(response.isPresent())
            board.getPlayer1().setUserName(response.get());
        
        player1Label.setText(board.getPlayer1().getUserName());
        player2Label.setText("Computer");
    }   
    
    private void setUpPlayers(){
        board.setPlayer1(new Player("Player 1"));
        board.setComputer((computer = new AIPlayerMinimax(board)));
        board.setPlayer2(computer);
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
        Board.reset();
        board = Board.getNewInstance();
        winner = null;
        moveNo = 0;
        
        board.setPlayer1(new Player(player1Label.getText()));
        board.setComputer((computer = new AIPlayerMinimax(board)));
        board.setPlayer2(computer);

        //System.out.println(board.getPlayer1().getUserName());

        grid00.setImage(null); grid01.setImage(null); grid02.setImage(null); 
        grid10.setImage(null); grid11.setImage(null); grid12.setImage(null); 
        grid20.setImage(null); grid21.setImage(null); grid22.setImage(null); 
    }

    @FXML
    private void onGridClicked(MouseEvent event) {
        ImageView view = (ImageView) event.getSource();
        int row = GridPane.getRowIndex(view);
        int col = GridPane.getColumnIndex(view);
        
        scale(view);
        
        String url = getURL();
        boolean moveMade = makeNextMove(row, col);
        //System.out.println(view+" "+moveMade+" "+board.getPlayer1()==null);
        System.out.println(moveMade+" player : "+row+""+col+" move no: "+moveNo);
        
        if(moveMade){
            view.setImage(getImage(url));
            
            if(board.isGameOver()){
                ifGameOver();
            }
            else {
                makeComputerMove();
                if(board.isGameOver())
                    ifGameOver();
            }
        }
    }
    
    private void ifGameOver(){
        if((winner=board.getWinner()) != null){
            showWinnerDialog(winner);

            int currentScore;

            if(winner == board.getPlayer1()){
                currentScore = Integer.parseInt(p1ScoreLabel.getText().substring(2)) + 1;
                p1ScoreLabel.setText("- "+currentScore);
            }
            else{
                currentScore = Integer.parseInt(p2ScoreLabel.getText().substring(2)) + 1;
                p2ScoreLabel.setText("- "+currentScore);
            }           
        }
        else
            showDrawDialog();

        resetGame();
    }
    
    private boolean makeComputerMove(){
        int nextMove[] = computer.move();
        System.out.println(nextMove[0]+", "+nextMove[1]);
        boolean moveMade = makeNextMove(nextMove[0], nextMove[1]);
        int gridNo = nextMove[0]*10 + nextMove[1]; 
        String grid = "grid"+gridNo;
        String url = getURL();
        
        System.out.println("computer move : "+gridNo+" move no: "+moveNo);
        
        switch(grid){
            case "grid0": grid00.setImage(getImage(url)); scale(grid00); break;
            case "grid1": grid01.setImage(getImage(url)); scale(grid01); break; 
            case "grid2": grid02.setImage(getImage(url)); scale(grid02); break;
            
            case "grid10": grid10.setImage(getImage(url)); scale(grid10); break;
            case "grid11": grid11.setImage(getImage(url)); scale(grid11); break; 
            case "grid12": grid12.setImage(getImage(url)); scale(grid12); break;
                
            case "grid20": grid20.setImage(getImage(url)); scale(grid20); break;
            case "grid21": grid21.setImage(getImage(url)); scale(grid21); break; 
            case "grid22": grid22.setImage(getImage(url)); scale(grid22); break;
                
            default: return false;
        }
        
        return moveMade;
    }
    
    private void scale(ImageView view){
        view.setScaleX(.75);
        view.setScaleY(.75);
    }
    
    private void showWinnerDialog(Player winner){
        String msg;
        
        if(winner == board.getPlayer1())
            msg = "Congratulations!!!";
        else
            msg = "Better luck next time...";
        
        Dialogs.create()
            .owner(game.getStage())
            .title("We have a winner!")
            .masthead(msg)
            .message(winner.getUserName()+" WINS!!!")
            .styleClass(Dialog.STYLE_CLASS_UNDECORATED)
            .showInformation();
    }
    
    private void showDrawDialog(){
        Dialogs.create()
            .owner(game.getStage())
            .title("Good game!")
            .masthead("DRAW!!!")
            .message("Better luck next time...")
            .styleClass(Dialog.STYLE_CLASS_UNDECORATED)
            .showInformation();
    }
}
