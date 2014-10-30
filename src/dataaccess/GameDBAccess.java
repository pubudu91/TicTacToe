/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataaccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import tictactoe.model.Player;
import tictactoe.util.DBConnection;

/**
 *
 * @author Mampitiya
 */
public class GameDBAccess {
    private Connection con; //create a connection object to get the connection to the database
    private ResultSet rst;  //holds the data retrieved from the database
    private Statement statement;    //to execute sql commands
    int restult;    //whether successfully executed
    
    /*This method will search and return the user having the same username as given*/
    public Player searchPlayer(String username) throws ClassNotFoundException, SQLException{
        con = DBConnection.getConnection();
        statement = con.createStatement();
        String sql = "Select * from user where username = '" +username+ "'"; 
        rst = statement.executeQuery(sql);
        if(rst.next()){
            int totGames = rst.getInt(2);
            int totWins = rst.getInt(3);
            int totDraws = rst.getInt(4);
            int totLosses = rst.getInt(5);
            
            Player player = new Player(username, totGames, totWins, totDraws, totLosses);
            return player;
        }
        return null;    //if no such user return null
    }
    
    /*This method will return all the existing users in the database*/
    public ResultSet searchAll() throws ClassNotFoundException, SQLException{
        con = DBConnection.getConnection();
        statement = con.createStatement();
        String sql = "Select * from user"; 
        rst = statement.executeQuery(sql);
        return rst;
    }
    
    /*This method will check whether the given user is in the database*/
    public boolean isExisting(String username) throws ClassNotFoundException, SQLException{
        con = DBConnection.getConnection();
        statement = con.createStatement();
        String sql = "Select * from user where username = '" +username+ "'"; 
        rst = statement.executeQuery(sql);
        return rst.first();
    }
    
    /*this method will increment the no of winnings of the given user*/
    public int incrementWins(String username) throws ClassNotFoundException, SQLException{
        con = DBConnection.getConnection();
        statement = con.createStatement();
        String sql = "Update user set total_wins = total_wins + 1, total_played = total_played + 1 where username = '" +username+ "'"; 
        restult = statement.executeUpdate(sql);
        return restult;
    }
    
    /*this method will increment the no of lossess of the given user*/
    public int incrementLosses(String username) throws ClassNotFoundException, SQLException{
        con = DBConnection.getConnection();
        statement = con.createStatement();
        String sql = "Update user set total_losses = total_losses + 1, total_played = total_played + 1 where username = '" +username+ "'"; 
        restult = statement.executeUpdate(sql);
        return restult;
    }
    
    /*this method will increment the no of draws of the given user*/
    public int incrementDraws(String username) throws ClassNotFoundException, SQLException{
        con = DBConnection.getConnection();
        statement = con.createStatement();
        String sql = "Update user set total_draws = total_draws + 1, total_played = total_played + 1 where username = '" +username+ "'"; 
        restult = statement.executeUpdate(sql);
        return restult;
    }
    
    /*this method will add the given user to the database*/
    public int addNewPlayer(String username) throws ClassNotFoundException, SQLException{
        con = DBConnection.getConnection();
        statement = con.createStatement();
        String sql = "insert into user (username) values ('"+username+"')"; 
        restult = statement.executeUpdate(sql);
        return restult;
    }
}
