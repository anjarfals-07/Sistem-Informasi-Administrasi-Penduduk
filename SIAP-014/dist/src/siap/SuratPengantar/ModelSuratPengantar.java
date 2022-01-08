/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siap.SuratPengantar;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import siap.Koneksi.Koneksi;
import siap.ModelSiap.ModelSiap;

/*
 *
 * @author ASUS
 */
public class ModelSuratPengantar{
    
       public ModelSuratPengantar(){
        connection = Koneksi.connection();
    }
    Connection connection;
    String INSERTSURAT = "INSERT INTO dt_suratpengantar values(?,?,?,?,?)";
    String UPDATESURAT = "UPDATE dt_suratpengantar SET kode_warga=?,kode_rt=?,keterangan=?,tanggal_surat=? WHERE no_surat=?";
    String DELETESURAT = "DELETE FROM dt_suratpengantar WHERE no_surat=?";
    String SELECTSURAT = "SELECT dt_suratpengantar.no_surat,dt_suratpengantar.tanggal_surat,dt_suratpengantar.kode_warga,dt_warga.no_ktp,\n" +
                        "dt_warga.nama_lengkap,dt_warga.jenis_kelamin,dt_warga.status_keluarga,dt_warga.alamat_tinggal, \n" +
                        "dt_suratpengantar.keterangan,dt_suratpengantar.kode_rt\n" +
                        "FROM dt_suratpengantar INNER JOIN dt_warga ON dt_suratpengantar.kode_warga=dt_warga.kode_warga ORDER BY dt_suratpengantar.no_surat ASC";
    String SELECTKK = "SELECT * FROM dt_kartukeluarga";
    String COUNTERSURAT = "SELECT ifnull(max(convert(right(no_surat,2),signed integer)),0) as kode,"
            + "ifnull(length(max(convert(right(no_surat,2),signed integer))),0)as panjang "
            + "from dt_suratpengantar";
    
String TAMPIRT = "SELECT * FROM dt_erte order by kode_rt";
    String CARIRT  = "SELECT * FROM dt_erte where kode_rt=? ";

    
     //tampil rt
      public List<ModelSiap> isicombort() {
        PreparedStatement statement;
        List<ModelSiap> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(TAMPIRT);
            ResultSet rs = statement.executeQuery();              
            while (rs.next()) {
                ModelSiap b = new ModelSiap();  
                b.setKode_rt(rs.getString("kode_rt"));
                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
        
         //manggil data supplier lewat combo supplier
    public List<ModelSiap> getrt(String nama) {
        PreparedStatement statement;
        List<ModelSiap> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(CARIRT);
            statement.setString(1, nama);
            ResultSet rs = statement.executeQuery();   
           
            while (rs.next()) {                         
                ModelSiap b = new ModelSiap();
                 b.setKode_rt(rs.getString("kode_rt"));
                list.add(b);            
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }  
  
    public int autonumber(ModelSiap object) {
        PreparedStatement statement = null;
         int nomor=0;
        try{
            statement = connection.prepareStatement(COUNTERSURAT);
            ResultSet rs = statement.executeQuery();
            if(rs.next())             
                nomor=rs.getInt("kode")+1;          
            
        }catch (Exception e) {
            e.printStackTrace();
        }   
        return nomor;
    }

    public void insert(ModelSiap object) {
       PreparedStatement statement2 = null;
        try {
            
                statement2 = connection.prepareStatement(INSERTSURAT);
                statement2.setString(1, object.getNo_surat());
                statement2.setString(2, object.getKode_warga());
                statement2.setString(3, object.getKode_rt());
                statement2.setString(4, object.getKeterangan());
                statement2.setDate(5, new java.sql.Date (object.getTanggal_surat().getTime()));
    
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Surat Pengantar berhasil di simpan!");
            } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement2 != null) {
                    statement2.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ModelSuratPengantar.class.getName()).log(Level.SEVERE, null, e);
            }
        }
         
    } 

    public void update(ModelSiap object) {
         PreparedStatement statement = null;
        try {
         
                statement = connection.prepareStatement(UPDATESURAT);
                statement.setString(1, object.getKode_warga());
                statement.setString(2, object.getKode_rt());
                statement.setString(3, object.getKeterangan());
                statement.setDate(4, new java.sql.Date (object.getTanggal_surat().getTime()));
                statement.setString(5, object.getNo_surat());
                statement.executeUpdate();                    
                JOptionPane.showMessageDialog(null, "Data Surat Pengantar berhasil di ubah!");                
           } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ModelSuratPengantar.class.getName()).log(Level.SEVERE, null, e);
            }
        }
         
    } 

    public void delete(String id) {
            PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETESURAT);
            statement.setString(1, id);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Surat Pengantar berhasil di hapus!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelSuratPengantar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<ModelSiap> getAll() {
        List<ModelSiap> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<ModelSiap>();
            statement = connection.prepareStatement(SELECTSURAT);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ModelSiap b = new ModelSiap();
                b.setNo_surat(rs.getString("no_surat"));
                b.setTanggal_surat(rs.getDate("tanggal_surat"));
                b.setKode_warga(rs.getString("kode_warga"));
                b.setNo_ktp(rs.getString("no_ktp"));
                b.setNama_warga(rs.getString("nama_lengkap"));
                b.setJenis_kelamin(rs.getString("jenis_kelamin"));
                b.setStatus_keluarga(rs.getString("status_keluarga"));
                b.setAlamat_warga(rs.getString("alamat_tinggal"));
                b.setKeterangan(rs.getString("keterangan"));
                b.setKode_rt(rs.getString("kode_rt"));
              
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        }


   
}
