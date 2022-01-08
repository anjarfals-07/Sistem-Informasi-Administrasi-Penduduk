/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siap.Pindah;



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
 * @author ASUS
 */
public class ModelPindah{
      public ModelPindah(){
        connection = Koneksi.connection();
    }
    Connection connection;
    
    //untuk data pindah
    String INSERTPINDAH = "INSERT INTO dt_pindah values(?,?,?,?,?,?,?)";
    String UPDATEPINDAH = "UPDATE dt_pindah SET tgl_pindah=?,no_kk=?,alamat_pindah=?,jumlah_pindah=?,status=?,kode_rt=? WHERE kode_pindah=?";
    String DELETEPINDAH = "DELETE FROM dt_pindah WHERE kode_pindah=?";
    String CARIPINDAH = "SELECT * FROM dt_pendudukpindah where no_kk like ? or nama_lengkap like ?";
    String COUNTERPINDAH = "SELECT ifnull(max(convert(right(kode_pindah,2),signed integer)),0) as kode,"
            + "ifnull(length(max(convert(right(kode_pindah,2),signed integer))),0)as panjang "
            + "from dt_pindah";
    // untuk surat
    String INSERTSURAT = "INSERT INTO dt_suratpindah  values(?,?,?,?)";
    String UPDATESURAT = "UPDATE dt_suratpindah SET kode_pindah=?, tanggal_surat=?,keterangan=? WHERE kode_pindah=?";
    String DELETESURAT = "DELETE FROM dt_suratpindah WHERE kode_pindah=?";
    String COUNTERSURAT = "SELECT ifnull(max(convert(right(no_suratpindah,2),signed integer)),0) as kode,"
            + "ifnull(length(max(convert(right(no_suratpindah,2),signed integer))),0)as panjang "
            + "from dt_suratpindah ";
    String INSERTDETAIL = "INSERT INTO dt_isipindah (kode_pindah,no_kk,no_ktp,nama_lengkap,jenis_kelamin,status_warga,status_pindah,foto,jumlah) values(?,?,?,?,?,?,?,?,?)";
    //String UPDATEDETAIL = "UPDATE dt_isipindah SET kode_pindah=?,no_kk=?, no_ktp=?,nama_lengkap=?,jenis_kelamin=?,status_warga=?,status_pindah=?,foto=?,jumlah=? WHERE no=?";
    String DELETEDETAIL = "DELETE FROM dt_isipindah WHERE kode_pindah=?";
    String DELETEDETAILPINDAH = "DELETE FROM dt_isipindah WHERE no_ktp=?";
    // untuk warga
    String DELETEWARGA = "DELETE FROM dt_warga WHERE no_ktp=?";
    String TAMPIKK = "SELECT * FROM dt_kartukeluarga order by no_kk";
    String CARIKK  = "SELECT * FROM dt_warga where no_kk=? ";
    String TAMPIRT = "SELECT * FROM dt_erte order by kode_rt";
    String CARIRT  = "SELECT * FROM dt_erte where kode_rt=? ";
    String SELECTPINDAH = "SELECT dt_pindah.kode_pindah,dt_pindah.tanggal_pindah,dt_suratpindah.no_suratpindah,"
                         + "dt_suratpindah.tanggal_surat,dt_pindah.no_kk,dt_pindah.alamat_pindah,dt_pindah.jumlah_pindah,"
                         + "dt_pindah.status,dt_suratpindah.keterangan,dt_pindah.kode_rt "
                         + "FROM dt_pindah INNER JOIN dt_suratpindah ON dt_pindah.kode_pindah=dt_suratpindah.kode_pindah "
                         + "ORDER BY dt_pindah.kode_pindah ASC";
    String SELECTDETAIL = "SELECT * FROM dt_isipindah ORDER BY kode_pindah";
    String UPDATESTATISTIKPINDAH = "UPDATE dt_statistik SET jumlah_warga=? WHERE kode=?";  
    String UPDATESTATISTIKPINDAHALL = "UPDATE dt_statistik SET jumlah_warga=? WHERE kode=?";
    String CARIWARGA = "SELECT * FROM dt_warga where no_kk like ?";
    String WARGA = "SELECT * FROM dt_warga";
 
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
                 b.setNo_ktp(rs.getString("no_ktp"));
                list.add(b);            
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }  
    
// untuk pindah
    public int autonumber(ModelSiap object) {
        PreparedStatement statement = null;
         int nomor=0;
        try{
            statement = connection.prepareStatement(COUNTERPINDAH);
            ResultSet rs = statement.executeQuery();
            if(rs.next())             
                nomor=rs.getInt("kode")+1;          
            
        }catch (Exception e) {
            e.printStackTrace();
        }   
        return nomor;
    }
    
    public int autonumbersurat(ModelSiap object) {
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
            
                statement2 = connection.prepareStatement(INSERTPINDAH);
                statement2.setString(1, object.getKode_pindah());
                statement2.setDate(2, new java.sql.Date (object.getTanggal_pindah().getTime()));
                statement2.setString(3, object.getNo_kk());
                statement2.setString(4, object.getAlamat_pindah());
                statement2.setInt(5, object.getJumlah_pindah());
                statement2.setString(6, object.getStatus_pindah());
                statement2.setString(7, object.getKode_rt());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data warga pindah berhasil di simpan!");
            } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement2 != null) {
                    statement2.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ModelPindah.class.getName()).log(Level.SEVERE, null, e);
            }
        }
         
    } 

    public void update(ModelSiap object) {
       PreparedStatement statement = null;
        try {
         
                statement = connection.prepareStatement(UPDATEPINDAH);
                statement.setString(7, object.getKode_pindah());
                statement.setDate(1, new java.sql.Date (object.getTanggal_pindah().getTime()));
                statement.setString(2, object.getNo_kk());
                statement.setString(3, object.getAlamat_pindah());
                statement.setInt(4, object.getJumlah_pindah());
                statement.setString(5, object.getStatus_pindah());
                statement.setString(6, object.getKode_rt());

                statement.executeUpdate();                    
                JOptionPane.showMessageDialog(null, "Data warga pindah berhasil di ubah!");                
           } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ModelPindah.class.getName()).log(Level.SEVERE, null, e);
            }
        }
         
    } 


    public void delete(String id) {
          PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETEPINDAH);
            statement.setString(1, id);
            statement.executeUpdate();
           // JOptionPane.showMessageDialog(null, "Data warga pindah berhasil di hapus!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelPindah.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

 
    public List<ModelSiap> getAllPindah() {
        List<ModelSiap> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<ModelSiap>();
            statement = connection.prepareStatement(SELECTPINDAH);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ModelSiap b = new ModelSiap();
                b.setKode_pindah(rs.getString("kode_pindah"));
                b.setTanggal_pindah(rs.getDate("tanggal_pindah"));
                b.setNo_surat(rs.getString("no_suratpindah"));
                b.setTanggal_surat(rs.getDate("tanggal_surat"));
                b.setNo_kk(rs.getString("no_kk"));
                b.setAlamat_pindah(rs.getString("alamat_pindah"));
                b.setJumlah_pindah(rs.getInt("jumlah_pindah"));
                b.setStatus_pindah(rs.getString("status"));
                b.setKeterangan(rs.getString("keterangan"));
                b.setKode_rt(rs.getString("kode_rt"));

                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        }
    
    // untuk surat
    
    public void insertsurat(ModelSiap object) {
       PreparedStatement statement2 = null;
        try {
            
                statement2 = connection.prepareStatement(INSERTSURAT);
                statement2.setString(1, object.getNo_surat());
                statement2.setString(2, object.getKode_pindah());
                statement2.setDate(3, new java.sql.Date (object.getTanggal_surat().getTime()));
                statement2.setString(4, object.getKeterangan());

                statement2.executeUpdate();
//                JOptionPane.showMessageDialog(null, "Data surat berhasil di simpan!");
            } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement2 != null) {
                    statement2.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ModelPindah.class.getName()).log(Level.SEVERE, null, e);
            }
        }    
    } 
    
    public void updatesurat(ModelSiap object) {
       PreparedStatement statement = null;
        try {
         
                statement = connection.prepareStatement(UPDATESURAT);
               statement.setString(1, object.getNo_surat());
                statement.setString(4, object.getKode_pindah());
                statement.setDate(2, new java.sql.Date (object.getTanggal_surat().getTime()));
                statement.setString(3, object.getKeterangan());
                
                statement.executeUpdate();                    
              //  JOptionPane.showMessageDialog(null, "Data surat berhasil di ubah!");                
           } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ModelPindah.class.getName()).log(Level.SEVERE, null, e);
            }
        }
         
    } 
    
     public void deletesurat(String id) {
          PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETESURAT);
            statement.setString(1, id);
            statement.executeUpdate();
           // JOptionPane.showMessageDialog(null, "Data surat berhasil di hapus!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelPindah.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
     public void insertdetail(ModelSiap object) {
       PreparedStatement statement2 = null;
        try {
            
                statement2 = connection.prepareStatement(INSERTDETAIL);
                statement2.setString(1, object.getKode_pindah());
                statement2.setString(2, object.getNo_kk());
                statement2.setString(3, object.getNo_ktp());
                statement2.setString(4, object.getNama_warga());
                statement2.setString(5, object.getJenis_kelamin());
                statement2.setString(6, object.getStatus_warga());
                statement2.setString(7, object.getStatus());
                statement2.setString(8, object.getFoto());
                statement2.setInt(9, object.getJumlah());

                statement2.executeUpdate();
              JOptionPane.showMessageDialog(null, "Data surat berhasil di simpan!");
            } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement2 != null) {
                    statement2.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ModelPindah.class.getName()).log(Level.SEVERE, null, e);
            }
        }    
    } 
    

    
     public void deletedetail(String id) {
          PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETEDETAIL);
            statement.setString(1, id);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil di hapus!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelPindah.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
     
     public void deletedetailpindah(String id) {
          PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETEDETAILPINDAH);
            statement.setString(1, id);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil di hapus!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelPindah.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
 
    
    // untuk delete warga
     public void deletewarga(String id) {
          PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETEWARGA);
            statement.setString(1, id);
            statement.executeUpdate();
           // JOptionPane.showMessageDialog(null, "Data berhasil di hapus!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelPindah.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

      // untuk statistik pindah
       
     public void update_jumlahpindah(ModelSiap object) {
       PreparedStatement statement;
       int jumlah=0;
        try {
                String SELECT = "select jumlah_warga from dt_statistik where kode=?";   
                statement = connection.prepareStatement(SELECT);
                statement.setString(1, object.getStatus());
                ResultSet rs = statement.executeQuery();     
                
                if (rs.next()){ //jika data ditemukan (baik null maupun tidak null)
                        //hitung stok akhir berdasarkan qty masing2 kode barang yang ada di jtabel 
                        jumlah=rs.getInt("jumlah_warga")+ object.getJumlah();

                        //update stok barang ke dalam table barang                     
                        PreparedStatement statement2; 
                        String UPDATESTATISTIK ="update dt_statistik set jumlah_warga=? where kode=?";
                        statement2 = connection.prepareStatement(UPDATESTATISTIK);                
                        statement2.setInt(1, jumlah);
                        statement2.setString(2, object.getStatus());     
                        statement2.executeUpdate();
                    }
                        } catch (Exception e) {
            e.printStackTrace();
        }      
    }
     
     // jika delete warga pindah
      public void delete_statistikpindah(ModelSiap object) {
       PreparedStatement statement;
       int jumlah=0;
        try {
                String SELECT = "select jumlah_warga from dt_statistik where kode=?";   
                statement = connection.prepareStatement(SELECT);
                statement.setString(1, object.getStatus());
                ResultSet rs = statement.executeQuery();     
                
                if (rs.next()){ //jika data ditemukan (baik null maupun tidak null)
                        //hitung stok akhir berdasarkan qty masing2 kode barang yang ada di jtabel 
                        jumlah=rs.getInt("jumlah_warga")- object.getJumlah();

                        //update stok barang ke dalam table barang                     
                        PreparedStatement statement2; 
                        String UPDATESTATISTIK ="update dt_statistik set jumlah_warga=? where kode=?";
                        statement2 = connection.prepareStatement(UPDATESTATISTIK);                
                        statement2.setInt(1, jumlah);
                        statement2.setString(2, object.getStatus());     
                        statement2.executeUpdate();
                    }
                        } catch (Exception e) {
            e.printStackTrace();
        }      
    }
      
       // jika delete all data pindah
      public void delete_statistikjumlahpindah(ModelSiap object) {
       PreparedStatement statement;
       int jumlah=0;
        try {
                String SELECT = "select jumlah_warga from dt_statistik where kode=?";   
                statement = connection.prepareStatement(SELECT);
                statement.setString(1, object.getStatus());
                ResultSet rs = statement.executeQuery();     
                
                if (rs.next()){ //jika data ditemukan (baik null maupun tidak null)
                        //hitung stok akhir berdasarkan qty masing2 kode barang yang ada di jtabel 
                        jumlah=rs.getInt("jumlah_warga")- object.getJumlah_pindah();

                        //update stok barang ke dalam table barang                     
                        PreparedStatement statement2; 
                        String UPDATESTATISTIK ="update dt_statistik set jumlah_warga=? where kode=?";
                        statement2 = connection.prepareStatement(UPDATESTATISTIK);                
                        statement2.setInt(1, jumlah);
                        statement2.setString(2, object.getStatus_pindah());     
                        statement2.executeUpdate();
                    }
                        } catch (Exception e) {
            e.printStackTrace();
        }      
    }
      
      
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
                        String UPDATESTATISTIK ="update dt_statistik set jumlah_warga=? where kode=? ";
                        statement2 = connection.prepareStatement(UPDATESTATISTIK);                
                        statement2.setInt(1, jumlah);
                        statement2.setString(2, object.getStatus_warga());     
                        statement2.executeUpdate();
                    }
                        } catch (Exception e) {
            e.printStackTrace();
        }      
    }
        
         public List<ModelSiap> getDetailPindah() {
        List<ModelSiap> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<ModelSiap>();
            statement = connection.prepareStatement(SELECTDETAIL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ModelSiap b = new ModelSiap();
                b.setNo(rs.getString("id"));
                b.setKode_pindah(rs.getString("kode_pindah"));
                b.setNo_kk(rs.getString("no_kk"));
                b.setNo_ktp(rs.getString("no_ktp"));
                b.setNama_warga(rs.getString("nama_lengkap"));
                b.setJenis_kelamin(rs.getString("jenis_kelamin"));
                b.setStatus_warga(rs.getString("status_warga"));
                b.setStatus(rs.getString("status_pindah"));
                b.setFoto(rs.getString("foto"));
                b.setJumlah(rs.getInt("jumlah"));

                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        }
         
         
//      public void updatestatistikpindah(ModelSiap object) {
//       PreparedStatement statement = null;
//        try {
//         
//                statement = connection.prepareStatement(UPDATESTATISTIKPINDAH);
//                statement.setString(2, object.getStatus());
//                statement.setInt(1, object.getJumlah_data());
//
//                statement.executeUpdate();                    
//                JOptionPane.showMessageDialog(null, "Data warga pindah berhasil di ubah!");                
//           } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (statement != null) {
//                    statement.close();
//                }
//            } catch (SQLException e) {
//                Logger.getLogger(ModelPindah.class.getName()).log(Level.SEVERE, null, e);
//            }
//        }
//         
//    } 
      
     //untuk ambil kode dan no kk pindah
       public boolean cekPindah(ModelSiap usr) throws SQLException {
        String sql = "SELECT * FROM dt_pindah WHERE kode_pindah = ? AND no_kk = ? ";
        PreparedStatement pst = Koneksi.connection().prepareStatement(sql);
        pst.setString(1, usr.getKode_pindah());
        pst.setString(2, usr.getNo_kk());
     
        ResultSet rs;
        rs = pst.executeQuery();
        if (rs.next()) { 
            SessionDataPindah.setKodePindah(rs.getString("kode_pindah"));
            SessionDataPindah.setKK_Pindah(rs.getString("no_kk"));
            return true;
        }
        return false;
    }
     
        
        public List<ModelSiap> getAllWarga() {
        List<ModelSiap> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<ModelSiap>();
            statement = connection.prepareStatement(WARGA);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ModelSiap b = new ModelSiap();
                b.setNo_ktp(rs.getString("no_ktp"));
                b.setNo_kk(rs.getString("no_kk"));
                b.setNama_warga(rs.getString("nama_lengkap"));
                b.setJenis_kelamin(rs.getString("jenis_kelamin"));
                b.setStatus_warga(rs.getString("status_warga"));
                b.setFoto(rs.getString("foto"));
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        }
}
  