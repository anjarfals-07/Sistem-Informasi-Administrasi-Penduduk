/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siap.Login;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import siap.Koneksi.Koneksi;
import siap.ModelSiap.ModelSiap;
import siap.SessionLogin.UserLogin;


/**
 *
 * @author Anjar
 */
public class ControllerLogin {
    public boolean cekLogin(ModelSiap usr) throws SQLException {
        String sql = "SELECT * FROM dt_erte WHERE username = ? AND password = ? AND status_rt='Aktif'";
        PreparedStatement pst =Koneksi.connection().prepareStatement(sql);
        pst.setString(1, usr.getUsername());
        pst.setString(2, usr.getPassword());
     
        ResultSet rs;
        rs = pst.executeQuery();
        if (rs.next()) { 
            UserLogin.setKodeLogin(rs.getString("kode_rt"));
            UserLogin.setNamaLogin(rs.getString("ketua_rt"));
            return true;
        }
        return false;
    }

}
