/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tictactoe.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Pubudu
 */
public class LeaderboardEntry {
    //private Player player;
    private StringProperty name;
    private IntegerProperty wins;
    private IntegerProperty rank;

    public LeaderboardEntry(Player player, int rank) {
        setName(player.getUserName());
        setWins(player.getWins());
        setRank(rank);
    }

    public String getName() {
        return name.get();
    }

    public Integer getWins() {
        return wins.get();
    }
    
    public Integer getRank() {
        return rank.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public void setWins(int wins) {
        this.wins = new SimpleIntegerProperty(wins);
    }
    
    public void setRank(int rank) {
        this.rank = new SimpleIntegerProperty(rank);
    }
}
