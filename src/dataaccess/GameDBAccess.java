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
    private Connection con;
    private ResultSet rst;
    private Statement statement;
    int restult;
    
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
        return null;
    }
    
    public ResultSet searchAll() throws ClassNotFoundException, SQLException{
        con = DBConnection.getConnection();
        statement = con.createStatement();
        String sql = "Select * from user"; 
        rst = statement.executeQuery(sql);
        return rst;
    }
    
    public boolean isExisting(String username) throws ClassNotFoundException, SQLException{
        con = DBConnection.getConnection();
        statement = con.createStatement();
        String sql = "Select * from user where username = '" +username+ "'"; 
        rst = statement.executeQuery(sql);
        return rst.first();
    }
    
    public int incrementWins(String username) throws ClassNotFoundException, SQLException{
        con = DBConnection.getConnection();
        statement = con.createStatement();
        String sql = "Update user set total_wins = total_wins + 1, total_played = total_played + 1 where username = '" +username+ "'"; 
        restult = statement.executeUpdate(sql);
        return restult;
    }
    
    public int incrementLosses(String username) throws ClassNotFoundException, SQLException{
        con = DBConnection.getConnection();
        statement = con.createStatement();
        String sql = "Update user set total_losses = total_losses + 1, total_played = total_played + 1 where username = '" +username+ "'"; 
        restult = statement.executeUpdate(sql);
        return restult;
    }
    
    public int incrementDraws(String username) throws ClassNotFoundException, SQLException{
        con = DBConnection.getConnection();
        statement = con.createStatement();
        String sql = "Update user set total_draws = total_draws + 1, total_played = total_played + 1 where username = '" +username+ "'"; 
        restult = statement.executeUpdate(sql);
        return restult;
    }
    
    public int addNewPlayer(String username) throws ClassNotFoundException, SQLException{
        con = DBConnection.getConnection();
        statement = con.createStatement();
        String sql = "insert into user (username) values ('"+username+"')"; 
        restult = statement.executeUpdate(sql);
        return restult;
    }
}
