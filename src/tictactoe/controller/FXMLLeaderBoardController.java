/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tictactoe.controller;

import dataaccess.GameDBAccess;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tictactoe.model.LeaderboardEntry;
import tictactoe.model.Player;
import tictactoe.ui.MainUI;

/**
 * FXML Controller class
 *
 * @author Pubudu
 */
public class FXMLLeaderBoardController implements Initializable {
    @FXML
    private TableView<LeaderboardEntry> leaderBoardTable;
    @FXML
    private TableColumn<LeaderboardEntry, String> nameColumn;
    @FXML
    private TableColumn<LeaderboardEntry, Integer> winsColumn;
    @FXML
    private Button backBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<LeaderboardEntry> entries = FXCollections.observableArrayList(getHighScoreList());
        nameColumn.setCellValueFactory(new PropertyValueFactory<LeaderboardEntry, String>("name"));
        winsColumn.setCellValueFactory(new PropertyValueFactory<LeaderboardEntry, Integer>("wins"));
        leaderBoardTable.setItems(entries);
    }    
    
    public void fillTable(){
        
        
        leaderBoardTable.getColumns().addAll(nameColumn, winsColumn);
        
    }
    
    public List<LeaderboardEntry> getHighScoreList(){
        ArrayList<LeaderboardEntry> list = new ArrayList<LeaderboardEntry>();
        GameDBAccess conn = new GameDBAccess();
        ArrayList<Player> players = null;
        
        try {
            players = conn.getTopScorers();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLLeaderBoardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLLeaderBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Iterator<Player> it = players.iterator();
        
        while(it.hasNext())
            list.add(new LeaderboardEntry(it.next()));
        
        System.out.println(players);
        return list;
    }

    @FXML
    private void onBackBtnClicked(MouseEvent event) {
        MainUI mainScreen = MainUI.getInstance();
        try {
            mainScreen.start(mainScreen.getStage());
        } catch (Exception ex) {
            Logger.getLogger(FXMLGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
