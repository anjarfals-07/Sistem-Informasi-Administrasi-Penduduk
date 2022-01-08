
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siap.DataKK;


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
public class ModelKartuKeluarga {
    
    public ModelKartuKeluarga(){
        connection = Koneksi.connection();
    }
    Connection connection;
    
    String UpdateKK="UPDATE dt_kartukeluarga SET no_kk=? WHERE no=?";
    
    String SELECTDETAILKK = "SELECT detail_kk.kode_warga,detail_kk.no_kk,\n" +
"            detail_kk.no_ktp,dt_warga.nama_lengkap,dt_warga.jenis_kelamin,dt_warga.status_warga, \n" +
"            dt_warga.status_keluarga,dt_warga.foto\n" +
"            FROM detail_kk INNER JOIN dt_warga ON detail_kk.kode_warga=dt_warga.kode_warga WHERE detail_kk.no_kk=? ORDER BY detail_kk.no_kk ASC";
    String SELECTKK = "SELECT * FROM dt_kartukeluarga";
    
   
    
    
    //tampil kk
      
      
        public void updateKK(ModelSiap object) {
        PreparedStatement statement2 = null;
        try {
            
                statement2 = connection.prepareStatement(UpdateKK);
                 statement2.setString(2, object.getNo());
                statement2.setString(1, object.getNo_kk());
               
                statement2.executeUpdate();
               JOptionPane.showMessageDialog(null, "Data Kartu Keluarga berhasil di Ubah!");
            } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement2 != null) {
                    statement2.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ModelKartuKeluarga.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
     
        public List<ModelSiap> getAllDetailKartuKeluarga(String nama) {
       PreparedStatement statement=null;
        List<ModelSiap> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(SELECTDETAILKK);
            statement.setString(1, nama);
            ResultSet rs = statement.executeQuery();  
            while (rs.next()) {
                ModelSiap b = new ModelSiap ();
                b.setKode_warga(rs.getString("kode_warga"));
                b.setNo_kk(rs.getString("no_kk"));
                b.setNo_ktp(rs.getString("no_ktp"));
                b.setNama_warga(rs.getString("nama_lengkap"));
                b.setJenis_kelamin(rs.getString("jenis_kelamin"));
                b.setStatus_warga(rs.getString("status_warga"));
                b.setStatus_keluarga(rs.getString("status_keluarga"));
                b.setFoto(rs.getString("foto"));
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        }
        
          public List<ModelSiap> getAllKartuKeluarga() {
       List<ModelSiap> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<ModelSiap>();
            statement = connection.prepareStatement(SELECTKK);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ModelSiap b = new ModelSiap ();
                b.setNo(rs.getString("no"));
                b.setNo_kk(rs.getString("no_kk"));
               
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        }

          
          //statisik
          // untuk statistik warga
     public void update_jumlahwarga(ModelSiap object) {
       PreparedStatement statement;
       int jumlah=0;
        try {
                String SELECT = "select jumlah_warga from dt_statistik where kode=?";   
                statement = connection.prepareStatement(SELECT);
                statement.setString(1, object.getStatus_warga());
                ResultSet rs = statement.executeQuery();     
                
                if (rs.next()){ //jika data ditemukan (baik null maupun tidak null)
                        //hitung stok akhir berdasarkan qty masing2 kode barang yang ada di jtabel 
                        jumlah=rs.getInt("jumlah_warga")+ object.getJumlah();

                        //update stok barang ke dalam table barang                     
                        PreparedStatement statement2; 
                        String UPDATESTATISTIK ="update dt_statistik set jumlah_warga=? where kode=?";
                        statement2 = connection.prepareStatement(UPDATESTATISTIK);                
                        statement2.setInt(1, jumlah);
                        statement2.setString(2, object.getStatus_warga());     
                        statement2.executeUpdate();
                    }
                        } catch (Exception e) {
            e.printStackTrace();
        }      
    }
     
     // jika delete warga
      public void delete_statistikwarga(ModelSiap object) {
       PreparedStatement statement;
       int jumlah=0;
        try {
                String SELECT = "select jumlah_warga from dt_statistik where kode=?";   
                statement = connection.prepareStatement(SELECT);
                statement.setString(1, object.getStatus_warga());
                ResultSet rs = statement.executeQuery();     
                
                if (rs.next()){ //jika data ditemukan (baik null maupun tidak null)
                        //hitung stok akhir berdasarkan qty masing2 kode barang yang ada di jtabel 
                        jumlah=rs.getInt("jumlah_warga")- object.getJumlah();

                        //update stok barang ke dalam table barang                     
                        PreparedStatement statement2; 
                        String UPDATESTATISTIK ="update dt_statistik set jumlah_warga=? where kode=?";
                        statement2 = connection.prepareStatement(UPDATESTATISTIK);                
                        statement2.setInt(1, jumlah);
                        statement2.setString(2, object.getStatus_warga());     
                        statement2.executeUpdate();
                    }
                        } catch (Exception e) {
            e.printStackTrace();
        }      
    }
     
    
    
}
