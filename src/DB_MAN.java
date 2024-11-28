 import java.sql.*;
 import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Junha
 */
public class DB_MAN {
    String dbDriver = "com.mysql.cj.jdbc.Driver";
    String dbURL = "jdbc:mysql://localhost:3306/footfoot";
    String dbID = "root";
    String dbPassword = "wnsgk7575";
    
    Connection conn;
    Statement stmt;
    ResultSet rs;
    
    public void dbOpen() throws IOException{
        try{
            Class.forName(dbDriver);
            
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            stmt = conn.createStatement();
        }catch(Exception e){
            System.out.println("SQLException:" + e.getMessage());
        }
}
    public void dbClose() throws IOException{
        try{
            stmt.close();
            conn.close();
        }catch(Exception e){
            System.out.println("SQLException:" + e.getMessage());
        }
    }
}

