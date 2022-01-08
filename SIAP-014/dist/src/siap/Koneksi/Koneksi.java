/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siap.Koneksi;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Anjar
 */
public class Koneksi {
 static Connection con;
    
    public static Connection connection() {
    
        if (con==null){
            MysqlDataSource data = new MysqlDataSource();
            data.setDatabaseName("siwarga014");
            data.setUser("root");
            data.setPort(3306);
            data.setPassword("");
            
            try{
            con = data.getConnection();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    
        return con;
    }
}

