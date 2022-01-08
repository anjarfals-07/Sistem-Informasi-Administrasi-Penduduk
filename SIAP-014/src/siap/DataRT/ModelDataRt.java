/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siap.DataRT;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import siap.ModelSiap.ModelSiap;
import siap.Koneksi.Koneksi;

/**
 *
 * @author Anjar
 */
public class ModelDataRt{
    
     public ModelDataRt(){
        connection = Koneksi.connection();
    }
      Connection connection;
    
    String INSERT = "INSERT INTO dt_erte (kode_rt, ketua_rt, wakil_rt, sekretaris, bendahara,status_rt,username,password) values(?,?,?,?,?,?,?,?)";
    String UPDATE = "UPDATE dt_erte SET ketua_rt=?,wakil_rt=?, sekretaris=?,bendahara=?,status_rt=?,username=?,password=? WHERE kode_rt=?";
    String SELECT = "SELECT * FROM dt_erte order by kode_rt";

    public void insert(ModelSiap object) {
       PreparedStatement statement2 = null;
        try {
            
                statement2 = connection.prepareStatement(INSERT);
                statement2.setString(1, object.getKode_rt());
                statement2.setString(2, object.getKetua());
                statement2.setString(3, object.getWakil());
                statement2.setString(4, object.getSekretaris());
                statement2.setString(5, object.getBendahara());
                statement2.setString(6, object.getStatus_rt());
                statement2.setString(7, object.getUsername());
                statement2.setString(8, object.getPassword());
                statement2.executeUpdate();
               JOptionPane.showMessageDialog(null, "Data RT  berhasil di simpan!");
            } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement2 != null) {
                    statement2.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ModelDataRt.class.getName()).log(Level.SEVERE, null, e);
            }
        }
         
    } 

    public void update(ModelSiap object) {
          PreparedStatement statement = null;
        try {
         
                statement = connection.prepareStatement(UPDATE);
                statement.setString(8, object.getKode_rt());
                statement.setString(1, object.getKetua());
                statement.setString(2, object.getWakil());
                statement.setString(3, object.getSekretaris());
                statement.setString(4, object.getBendahara());
                statement.setString(5, object.getStatus_rt());
                statement.setString(6, object.getUsername());
                 statement.setString(7, object.getPassword());
                statement.executeUpdate();                    
                JOptionPane.showMessageDialog(null, "Data RT berhasil di ubah!");                
           } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ModelDataRt.class.getName()).log(Level.SEVERE, null, e);
            }
        }
         
    } 


    public List<ModelSiap> getAll() {
        List<ModelSiap> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<ModelSiap>();
            statement = connection.prepareStatement(SELECT);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ModelSiap b = new ModelSiap();
                b.setNo(rs.getString("id"));
                b.setKode_rt(rs.getString("kode_rt"));
                b.setKetua(rs.getString("ketua_rt"));
                b.setWakil(rs.getString("wakil_rt"));
                b.setSekretaris(rs.getString("sekretaris"));
                b.setBendahara(rs.getString("bendahara"));
                b.setStatus_rt(rs.getString("status_rt"));
                b.setUsername(rs.getString("username"));
                b.setPassword(rs.getString("password"));
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        }

    
    public List<ModelSiap> getCari(String key, String nama) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
