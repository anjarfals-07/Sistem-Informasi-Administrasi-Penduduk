/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siap.Warga;

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

/**
 *
 * @author Anjar
 */
public class ModelWarga {
    
    public ModelWarga(){
        connection = Koneksi.connection();
    }
    Connection connection;
    String INSERTWARGA = "INSERT INTO dt_warga values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    String UPDATEWARGA = "UPDATE dt_warga SET no_ktp=?, no_kk=?, nama_lengkap=?, jenis_kelamin=?, tempat_lahir=?, tgl_lahir=?, agama=?"
                         + " , pekerjaan=? , pendidikan=?, alamat_tinggal=? , alamat_asal=? , status_keluarga=? "
                          + ", status_warga=?, telepon=?, jumlah_data=?, kode_rt=?, foto=? WHERE kode_warga=?";
    String DELETEWARGA = "DELETE FROM dt_warga WHERE kode_warga=?";
    String SELECTWARGA = "SELECT * FROM dt_warga order by kode_warga ASC";
    String IdOtomatis = "SELECT ifnull(max(convert(right(kode_warga,2),signed integer)),0) as kode,"
            + "ifnull(length(max(convert(right(kode_warga,2),signed integer))),0)as panjang "
            + "from dt_warga";
     String SELECTTETAP = "SELECT * FROM dt_warga where status_warga='Tetap'";
     String SELECTPENDATANG = "SELECT * FROM dt_warga where status_warga='Pendatang'";

    String InsertDetailKK = "INSERT INTO detail_kk values(?,?,?)";
    String UpdateDetailKK = "UPDATE detail_kk SET no_kk=?, no_ktp=? WHERE kode_warga=?";
    String DeleteDetailKK = "DELETE FROM detail_kk WHERE no_ktp=?";
    
    String InsertKK = "INSERT INTO dt_kartukeluarga (no_kk) values(?)";
    
     String TAMPIKK = "SELECT * FROM dt_kartukeluarga order by no_kk";
    String CARIKK  = "SELECT * FROM dt_kartukeluarga where no_kk=? ";
    
    String TAMPIRT = "SELECT * FROM dt_erte order by kode_rt";
    String CARIRT  = "SELECT * FROM dt_erte where kode_rt=? ";
    String CARIWARGASAMA = "SELECT * FROM dt_warga where no_ktp like ?";
    String CARIKKSAMA = "SELECT * FROM dt_kartukeluarga where no_kk like ?";
    
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
    
    //tampil kk
      public List<ModelSiap> isicombokk() {
        PreparedStatement statement;
        List<ModelSiap> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(TAMPIKK);
            ResultSet rs = statement.executeQuery();              
            while (rs.next()) {
                ModelSiap b = new ModelSiap();  
                b.setNo_kk(rs.getString("no_kk"));
                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
        
         //manggil data supplier lewat combo supplier
    public List<ModelSiap> getkk(String nama) {
        PreparedStatement statement;
        List<ModelSiap> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(CARIKK);
            statement.setString(1, nama);
            ResultSet rs = statement.executeQuery();   
           
            while (rs.next()) {                         
                ModelSiap b = new ModelSiap();
                 b.setNo_kk(rs.getString("no_kk"));
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
            statement = connection.prepareStatement(IdOtomatis);
            ResultSet rs = statement.executeQuery();
            if(rs.next())             
                nomor=rs.getInt("kode")+1;          
            
        }catch (Exception e) {
            e.printStackTrace();
        }   
        return nomor;
    }


    public void insert(ModelSiap object) {
          PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CARIWARGASAMA);
            statement.setString(1, object.getNo_ktp());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) //jika data sudah pernah disimpan
            {
                JOptionPane.showMessageDialog(null, "No Ktp sudah pernah di simpan!");
            } else {    //jika data belum pernah disimpan             
                PreparedStatement statement2 = null;
                statement2 = connection.prepareStatement(INSERTWARGA);
                statement2.setString(1, object.getKode_warga());
                statement2.setString(2, object.getNo_ktp());
                statement2.setString(3, object.getNo_kk());
                statement2.setString(4, object.getNama_warga());
                statement2.setString(5, object.getJenis_kelamin());
                statement2.setString(6, object.getTempat_lahir());
                statement2.setDate(7, new java.sql.Date(object.getTgl_lahir().getTime()));
                statement2.setString(8, object.getAgama());
                statement2.setString(9, object.getPekerjaan());
                statement2.setString(10, object.getPendidikan());
                statement2.setString(11, object.getAlamat_warga());
                statement2.setString(12, object.getAlamat_asal());
                statement2.setString(13, object.getStatus_keluarga());
                statement2.setString(14, object.getStatus_warga());
                statement2.setString(15, object.getTelepon());
                statement2.setInt(16, object.getJumlah());
                statement2.setString(17, object.getKode_rt());
                statement2.setString(18, object.getFoto());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Warga berhasil di simpan!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelWarga.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
    


    public void update(ModelSiap object) {
        PreparedStatement statement2 = null;
        try {
            
                statement2 = connection.prepareStatement(UPDATEWARGA);
                 statement2.setString(18, object.getKode_warga());
                statement2.setString(1, object.getNo_ktp());
                statement2.setString(2, object.getNo_kk());
                statement2.setString(3, object.getNama_warga());
                statement2.setString(4, object.getJenis_kelamin());
                statement2.setString(5, object.getTempat_lahir());
                statement2.setDate(6, new java.sql.Date(object.getTgl_lahir().getTime()));
                statement2.setString(7, object.getAgama());
                statement2.setString(8, object.getPekerjaan());
                statement2.setString(9, object.getPendidikan());
                statement2.setString(10, object.getAlamat_warga());
                statement2.setString(11, object.getAlamat_asal());
                statement2.setString(12, object.getStatus_keluarga());
                statement2.setString(13, object.getStatus_warga());
                statement2.setString(14, object.getTelepon());
                statement2.setInt(15, object.getJumlah());
                statement2.setString(16, object.getKode_rt());
                statement2.setString(17, object.getFoto());
               
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Warga berhasil di Ubah!");
            } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement2 != null) {
                    statement2.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ModelWarga.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    

    public void delete(String id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETEWARGA);
            statement.setString(1, id);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Warga berhasil di hapus!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelWarga.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


   
    public List<ModelSiap> getAllWarga() {
       List<ModelSiap> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<ModelSiap>();
            statement = connection.prepareStatement(SELECTWARGA);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ModelSiap b = new ModelSiap ();
                b.setKode_warga(rs.getString("kode_warga"));
                b.setNo_ktp(rs.getString("no_ktp"));
                b.setNo_kk(rs.getString("no_kk"));
                b.setNama_warga(rs.getString("nama_lengkap"));
                b.setJenis_kelamin(rs.getString("jenis_kelamin"));
                b.setTempat_lahir(rs.getString("tempat_lahir"));
                b.setTgl_lahir(rs.getDate("tgl_lahir"));
                b.setAgama(rs.getString("agama"));
                b.setPekerjaan(rs.getString("pekerjaan"));
                b.setPendidikan(rs.getString("pendidikan"));
                b.setAlamat_warga(rs.getString("alamat_tinggal"));
                b.setAlamat_asal(rs.getString("alamat_asal"));
                b.setStatus_keluarga(rs.getString("status_keluarga"));
                b.setStatus_warga(rs.getString("status_warga"));
                b.setTelepon(rs.getString("telepon"));
                b.setJumlah(rs.getInt("jumlah_data"));
                b.setKode_rt(rs.getString("kode_rt"));
                b.setFoto(rs.getString("foto"));
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        }
    
    //warga tetap
      public List<ModelSiap> getAllWargaTetap() {
       List<ModelSiap> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<ModelSiap>();
            statement = connection.prepareStatement(SELECTTETAP);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ModelSiap b = new ModelSiap ();
                b.setKode_warga(rs.getString("kode_warga"));
                b.setNo_ktp(rs.getString("no_ktp"));
                b.setNo_kk(rs.getString("no_kk"));
                b.setNama_warga(rs.getString("nama_lengkap"));
                b.setJenis_kelamin(rs.getString("jenis_kelamin"));
                b.setTempat_lahir(rs.getString("tempat_lahir"));
                b.setTgl_lahir(rs.getDate("tgl_lahir"));
                b.setAgama(rs.getString("agama"));
                b.setPekerjaan(rs.getString("pekerjaan"));
                b.setPendidikan(rs.getString("pendidikan"));
                b.setAlamat_warga(rs.getString("alamat_tinggal"));
                b.setAlamat_asal(rs.getString("alamat_asal"));
                b.setStatus_keluarga(rs.getString("status_keluarga"));
                b.setStatus_warga(rs.getString("status_warga"));
                b.setTelepon(rs.getString("telepon"));
                b.setJumlah(rs.getInt("jumlah_data"));
                b.setKode_rt(rs.getString("kode_rt"));
                b.setFoto(rs.getString("foto"));
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        }
      
      //warga pendatang
        public List<ModelSiap> getAllWargaPendatang() {
       List<ModelSiap> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<ModelSiap>();
            statement = connection.prepareStatement(SELECTPENDATANG);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ModelSiap b = new ModelSiap ();
                  b.setKode_warga(rs.getString("kode_warga"));
                b.setNo_ktp(rs.getString("no_ktp"));
                b.setNo_kk(rs.getString("no_kk"));
                b.setNama_warga(rs.getString("nama_lengkap"));
                b.setJenis_kelamin(rs.getString("jenis_kelamin"));
                b.setTempat_lahir(rs.getString("tempat_lahir"));
                b.setTgl_lahir(rs.getDate("tgl_lahir"));
                b.setAgama(rs.getString("agama"));
                b.setPekerjaan(rs.getString("pekerjaan"));
                b.setPendidikan(rs.getString("pendidikan"));
                b.setAlamat_warga(rs.getString("alamat_tinggal"));
                b.setAlamat_asal(rs.getString("alamat_asal"));
                b.setStatus_keluarga(rs.getString("status_keluarga"));
                b.setStatus_warga(rs.getString("status_warga"));
                b.setTelepon(rs.getString("telepon"));
                b.setJumlah(rs.getInt("jumlah_data"));
                b.setKode_rt(rs.getString("kode_rt"));
                b.setFoto(rs.getString("foto"));
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        }
  
    
    // insert kartukeluarga
      public void insertKartuKeluarga(ModelSiap object) {
          PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CARIKKSAMA);
            statement.setString(1, object.getNo_kk());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) //jika data sudah pernah disimpan
            {
                JOptionPane.showMessageDialog(null, "No Kartu Keluarga sudah pernah di simpan!");
            } else {    //jika data belum pernah disimpan             
                PreparedStatement statement2 = null;
                statement2 = connection.prepareStatement(InsertKK);
                
                statement2.setString(1, object.getNo_kk());
               
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Kartu Keluarga berhasil di simpan!");
                  }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelWarga.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
      
    
    
      //detail kk
       public void insertDetailKK(ModelSiap object) {
          PreparedStatement statement2 = null;
        try {
            
                statement2 = connection.prepareStatement(InsertDetailKK);
                statement2.setString(1, object.getKode_warga());
                statement2.setString(2, object.getNo_kk());
                statement2.setString(3, object.getNo_ktp());
               
                statement2.executeUpdate();
             //   JOptionPane.showMessageDialog(null, "Data Warga berhasil di simpan!");
            } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement2 != null) {
                    statement2.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ModelWarga.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
       
         public void updateDetailKK(ModelSiap object) {
        PreparedStatement statement2 = null;
        try {
            
                statement2 = connection.prepareStatement(UpdateDetailKK);
                 statement2.setString(3, object.getKode_warga());
                statement2.setString(1, object.getNo_kk());
                statement2.setString(2, object.getNo_ktp());
               
                statement2.executeUpdate();
                //JOptionPane.showMessageDialog(null, "Data Warga berhasil di Ubah!");
            } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement2 != null) {
                    statement2.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ModelWarga.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
         
       public void deleteDetailKK(String id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DeleteDetailKK);
            statement.setString(1, id);
            statement.executeUpdate();
          //  JOptionPane.showMessageDialog(null, "Data Warga berhasil di hapus!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelWarga.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
