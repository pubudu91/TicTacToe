/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mampitiya
 */
public class DBConnection {

    private static String userName = "root";
    private static String password = "";
    private static Connection conn;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe", userName, password);//Create DB Connection
        return conn;
    }
}
