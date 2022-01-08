/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siap.Meninggal;



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
 * @author ASUS
 */
public class ModelMeninggal {
    
       public ModelMeninggal(){
        connection = Koneksi.connection();
    }
    Connection connection;
    String INSERTKEMATIAN = "INSERT INTO dt_kematian values(?,?,?,?,?,?,?,?,?,?,?)";
    String UPDATEKEMATIAN = "UPDATE dt_kematian SET no_ktp=?,no_kk=?, nama_lengkap=?,jenis_kelamin=?,usia=?,penyebab=?,tanggal_wafat=?,jumlah=?,jenis_warga=?,status_warga=? WHERE kode_kematian=?";
    String DELETEKEMATIAN = "DELETE FROM dt_kematian WHERE no_ktp=?";
    String SELECTKEMATIAN = "SELECT * FROM dt_kematian order by kode_kematian";
    String CARIKEMATIAN = "SELECT * FROM dt_kematian where no_kk like ? or nama_lengkap like ?";
    String COUNTERKEMATIAN = "SELECT ifnull(max(convert(right(kode_kematian,2),signed integer)),0) as kode,"
            + "ifnull(length(max(convert(right(kode_kematian,2),signed integer))),0)as panjang "
            + "from dt_kematian";
    
    // untuk surat
    String INSERTSURAT = "INSERT INTO dt_suratkematian values(?,?,?,?)";
    String UPDATESURAT = "UPDATE dt_suratkematian SET kode_kematian=?,kode_rt=?,tgl_surat=? WHERE no_surat=?";
    String DELETESURAT = "DELETE FROM dt_suratkematian WHERE kode_kematian=?";
    String SELECTSURAT = "SELECT * FROM dt_suratkematian";
    String CARISURAT = "SELECT * FROM dt_suratkematian where kode_kematian like ? or no_surat like ?";
    String COUNTERSURAT = "SELECT ifnull(max(convert(right(kode_kematian,2),signed integer)),0) as kode,"
            + "ifnull(length(max(convert(right(kode_kematiankode_kematian,2),signed integer))),0)as panjang "
            + "from dt_suratkematian ";
    
    // untuk warga
    String DELETEWARGA = "DELETE FROM dt_warga WHERE no_ktp=?";
    
  
       // untuk kk
      String DELETEKK = "DELETE FROM detail_kk WHERE no_ktp=?";
      
          String SELECT = "SELECT dt_kematian.kode_kematian,dt_suratkematian.no_surat,dt_suratkematian.tanggal_surat,\n" +
"            dt_kematian.no_ktp,dt_kematian.nama_lengkap,dt_kematian.jenis_kelamin,dt_kematian.tanggal_wafat, \n" +
"            dt_kematian.usia,dt_kematian.penyebab,dt_kematian.no_kk,dt_kematian.jenis_warga,dt_kematian.status_warga,"
                  + "dt_kematian.jumlah,dt_suratkematian.kode_rt\n" +
"            FROM dt_kematian INNER JOIN dt_suratkematian ON dt_kematian.kode_kematian=dt_suratkematian.kode_kematian ORDER BY dt_kematian.kode_kematian ASC";
    String SELECTKK = "SELECT * FROM dt_kartukeluarga";
// untuk warga kematian
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
            statement = connection.prepareStatement(COUNTERKEMATIAN);
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
            
                statement2 = connection.prepareStatement(INSERTKEMATIAN);
                statement2.setString(1, object.getKode_kematian());
                statement2.setString(2, object.getNo_ktp());
                statement2.setString(3, object.getNo_kk());
                statement2.setString(4, object.getNama_warga());
                statement2.setString(5, object.getJenis_kelamin());
                statement2.setString(6, object.getUsia());
                statement2.setString(7, object.getPenyebab());
                statement2.setDate(8, new java.sql.Date (object.getTanggal_wafat().getTime()));
                statement2.setInt(9, object.getJumlah());
                statement2.setString(10, object.getStatus());
                statement2.setString(11, object.getStatus_warga());
              
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Meninggal berhasil di simpan!");
            } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement2 != null) {
                    statement2.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ModelSiap.class.getName()).log(Level.SEVERE, null, e);
            }
        }
         
    } 


    public void update(ModelSiap object) {
          PreparedStatement statement = null;
        try {
         
                statement = connection.prepareStatement(UPDATEKEMATIAN);
                statement.setString(1, object.getNo_ktp());
                statement.setString(2, object.getNo_kk());
                statement.setString(3, object.getNama_warga());
                 statement.setString(4, object.getJenis_kelamin());
                 statement.setString(5, object.getUsia());
                statement.setString(6, object.getPenyebab());
                statement.setDate(7, new java.sql.Date (object.getTanggal_wafat().getTime()));
                statement.setInt(8, object.getJumlah());
                statement.setString(9, object.getStatus());
                statement.setString(10, object.getStatus_warga());
                statement.setString(11, object.getKode_kematian());
                statement.executeUpdate();                    
                JOptionPane.showMessageDialog(null, "Data Meninggal berhasil di ubah!");                
           } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ModelSiap.class.getName()).log(Level.SEVERE, null, e);
            }
        }
         
    } 


    
    public void delete(String id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETEKEMATIAN);
            statement.setString(1, id);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Meninggal  berhasil di hapus!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelSiap.class.getName()).log(Level.SEVERE, null, ex);
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
                b.setKode_kematian(rs.getString("kode_kematian"));
                b.setNo_surat(rs.getString("no_surat"));
                b.setTanggal_surat(rs.getDate("tanggal_surat"));
                b.setNo_ktp(rs.getString("no_ktp"));
                b.setNama_warga(rs.getString("nama_lengkap"));
                b.setJenis_kelamin(rs.getString("jenis_kelamin"));
                b.setTanggal_wafat(rs.getDate("tanggal_wafat"));
                b.setUsia(rs.getString("usia"));
                b.setPenyebab(rs.getString("penyebab"));
                b.setStatus(rs.getString("jenis_warga"));
                b.setStatus_warga(rs.getString("status_warga"));
               b.setJumlah(rs.getInt("jumlah"));
               b.setNo_kk(rs.getString("no_kk"));
               b.setKode_rt(rs.getString("kode_rt"));
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        }

    
//    public List<ModelSiap> getCari(String key, String nama) {
//          List<ModelSiap> list = null;
//        PreparedStatement statement = null;
//        try {
//            list = new ArrayList<ModelSiap>();
//            statement = connection.prepareStatement(CARIKEMATIAN);
//            statement.setString(1, "%"+key+"%");
//            statement.setString(2, "%"+key+"%");
//            ResultSet rs = statement.executeQuery();
// 
//            while (rs.next()) {
//            ModelSiap b = new ModelSiap();
//                b.setKode_kematian(rs.getString("kode_kematian"));
//                b.setNo_ktp(rs.getString("no_ktp"));
//                b.setNo_kk(rs.getString("no_kk"));
//                b.setNama_warga(rs.getString("nama_lengkap"));
//                b.setJenis_kelamin(rs.getString("jenis_kelamin"));
//                b.setUsia(rs.getString("usia"));
//                b.setPenyebab(rs.getString("penyebab"));
//                b.setTanggal_wafat(rs.getDate("tanggal_wafat"));
//                b.setJumlah(rs.getInt("jumlah"));
//                b.setStatus(rs.getString("jenis_warga"));
//                b.setStatus_warga(rs.getString("status_warga"));
//            list.add(b);
//            }
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//        }
    
    
    // untuk surat kematian
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
    public void insertsurat(ModelSiap object) {
     PreparedStatement statement2 = null;
        try {
            
                statement2 = connection.prepareStatement(INSERTSURAT);
                statement2.setString(1, object.getNo_surat());
                statement2.setString(2, object.getKode_kematian());
                statement2.setString(3, object.getKode_rt());
                statement2.setDate(4, new java.sql.Date (object.getTanggal_surat().getTime()));
                statement2.executeUpdate();
             //   JOptionPane.showMessageDialog(null, "Data surat berhasil di simpan!");
            } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement2 != null) {
                    statement2.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ModelSiap.class.getName()).log(Level.SEVERE, null, e);
            }
        }
         
    } 


    public void updatesurat(ModelSiap object) {
          PreparedStatement statement = null;
        try {
              statement.setString(4, object.getNo_surat());
              statement.setString(1, object.getKode_kematian());
              statement.setString(2, object.getKode_rt());
              statement.setDate(3, new java.sql.Date (object.getTanggal_surat().getTime()));
                statement.executeUpdate();                    
               // JOptionPane.showMessageDialog(null, "Data surat berhasil di ubah!");                
           } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ModelSiap.class.getName()).log(Level.SEVERE, null, e);
            }
        }
         
    } 


    public void deletesurat(String id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETESURAT);
            statement.setString(1, id);
            statement.executeUpdate();
          //  JOptionPane.showMessageDialog(null, "Data surat berhasil di hapus!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelSiap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

//    public List<ModelSiap> getAllSurat() {
//        List<ModelSiap> list = null;
//        PreparedStatement statement = null;
//        try {
//            list = new ArrayList<ModelSiap>();
//            statement = connection.prepareStatement(SELECTSURAT);
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                ModelSiap b = new ModelSiap();
//                b.setNo_surat(rs.getString("no_surat"));
//                b.setKode_kematian(rs.getString("kode_kematian"));
//                b.setKode_rt(rs.getString("kode_rt"));
//                b.setTanggal_surat(rs.getDate("tanggal_surat"));
//                list.add(b);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//        }
//
//    
//    public List<ModelSiap> getCariSurat(String key, String nama) {
//          List<ModelSiap> list = null;
//        PreparedStatement statement = null;
//        try {
//            list = new ArrayList<ModelSiap>();
//            statement = connection.prepareStatement(CARISURAT);
//            statement.setString(1, "%"+key+"%");
//            statement.setString(2, "%"+key+"%");
//            ResultSet rs = statement.executeQuery();
// 
//            while (rs.next()) {
//            ModelSiap b = new ModelSiap();
//                b.setNo_surat(rs.getString("no_surat"));
//                b.setKode_kematian(rs.getString("kode_kematian"));
//                b.setKode_rt(rs.getString("kode_rt"));
//                b.setTanggal_surat(rs.getDate("tanggal_surat"));
//            list.add(b);
//            }
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//        }
    
    // delete warga
    public void deletewarga(String id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETEWARGA);
            statement.setString(1, id);
            statement.executeUpdate();
          //  JOptionPane.showMessageDialog(null, "Data warga pindah berhasil di hapus!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelSiap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // delete warga pendatang
    
    public void deletependatang(String id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETESURAT);
            statement.setString(1, id);
            statement.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Data warga pindah berhasil di hapus!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelSiap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     // delete kk
    public void deletekk(String id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETEKK);
            statement.setString(1, id);
            statement.executeUpdate();
           // JOptionPane.showMessageDialog(null, "Data warga pindah berhasil di hapus!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelSiap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // untuk menambahkan statistik kematian
     public void update_jumlahkematian(ModelSiap object) {
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
      public void delete_statistikkematian(ModelSiap object) {
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
      
      // untuk mengurangkan statistik warga asli
        public void delete_statistikwarga(ModelSiap object) {
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
                        String UPDATESTATISTIK ="update dt_statistik set jumlah_warga=? where kode=? ";
                        statement2 = connection.prepareStatement(UPDATESTATISTIK);                
                        statement2.setInt(1, jumlah);
                        statement2.setString(2, object.getStatus());     
                        statement2.executeUpdate();
                    }
                        } catch (Exception e) {
            e.printStackTrace();
        }      
    }
        
        // untuk mengurangkan statistik warga pendatang
          public void delete_statistikwargapendatang(ModelSiap object) {
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
                        String UPDATESTATISTIK ="update dt_statistik set jumlah_warga=? where kode=? ";
                        statement2 = connection.prepareStatement(UPDATESTATISTIK);                
                        statement2.setInt(1, jumlah);
                        statement2.setString(2, object.getStatus());     
                        statement2.executeUpdate();
                    }
                        } catch (Exception e) {
            e.printStackTrace();
        }      
    }
    
}
