/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siap.Warga;

import java.awt.Color;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import siap.ModelSiap.ModelSiap;

/**
 *
 * @author Anjar
 */
public class ControllerWarga {
    ViewWarga form;
    ModelWarga model;
    List<ModelSiap> listwarga; //deklarasi method "List" yang sudah dibuat pada interface MODEL_DAO
    List<ModelSiap> listdatang;// untuk list datang
    List<ModelSiap> listtetap;//untuk list tetap

    String[] headerwarga; //deklarasi variable untuk membuat judul kolom pada objek JTable
    String[] headerdatang;
    String[] headertetap;
   
    public ControllerWarga(ViewWarga form) {
        this.form = form;
        model = new ModelWarga();
        listwarga = model.getAllWarga();
        listdatang = model.getAllWargaPendatang();
        listtetap = model.getAllWargaTetap();
      
        headerwarga = new String[]{"KODE", "NO KTP", "NO KK", "NAMA LENGKAP", "JENIS KELAMIN","ALAMAT WARGA","ALAMAT ASAL","STATUS","FILE PHOTO"};
        headerdatang = new String[]{"KODE", "NO KTP", "NO KK", "NAMA LENGKAP", "JENIS KELAMIN","ALAMAT MENETAP","ALAMAT ASAL","STATUS","FILE PHOTO"};
        headertetap = new String[]{"KODE", "NO KTP", "NO KK", "NAMA LENGKAP", "JENIS KELAMIN","ALAMAT","STATUS","FILE PHOTO"};
        
        form.getTableWarga().setShowGrid(true);
        form.getTableWarga().setShowVerticalLines(true);
        form.getTableWarga().setGridColor(Color.blue);
        
        form.getTableTetap().setShowGrid(true);
        form.getTableTetap().setShowVerticalLines(true);
        form.getTableTetap().setGridColor(Color.blue);
        
        form.getTablePendatang().setShowGrid(true);
        form.getTablePendatang().setShowVerticalLines(true);
        form.getTablePendatang().setGridColor(Color.blue);
    }
     public void tampilurutankodewarga() {         
            ModelSiap p = new ModelSiap();
            model.autonumber(p);
            String no_mor=String.valueOf(model.autonumber(p));
            String oke="00".substring(0, 2 - no_mor.length());
            form.getTxtKodeWarga().setText( "WAR-"+  oke +""+no_mor );
        }
     
       public void isicombort() {
        form.getTxtKodeRT().removeAllItems();
        form.getTxtKodeRT().addItem("--Pilih Kode RT--");
        for (ModelSiap b : model.isicombort()) {
            form.getTxtKodeRT().addItem(b.getKode_rt());
        }
    }
    
    //method ini akan digunakan untuk menampilkan kode supplier dan lain-lain berdasarkan inputan combo nama supplier
    public void tampilnort() {
        if (form.getTxtKodeRT().getSelectedIndex() != 0) {
            for (ModelSiap b : model.getrt(form.getTxtKodeRT().getSelectedItem().toString())) {

            }
        }
    }  
        public void isicombokk() {
        form.getTxtNoKK().removeAllItems();
        form.getTxtNoKK().addItem("-Pilih No.KK--");
        form.getTxtNoKK().addItem("-");
        for (ModelSiap b : model.isicombokk()) {
            form.getTxtNoKK().addItem(b.getNo_kk());
        }
    }
    
    //method ini akan digunakan untuk menampilkan kode supplier dan lain-lain berdasarkan inputan combo nama supplier
    public void tampilnokk() {
        if (form.getTxtNoKK().getSelectedIndex() != 0) {
            for (ModelSiap b : model.getkk(form.getTxtNoKK().getSelectedItem().toString())) {

            }
        }
    }  
  
     public void isiTablewarga() {
        listwarga = model.getAllWarga();
        //Script agar jtable tidak bisa di edit
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, headerwarga) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return true;
                     
            }
        };

        Object[] data = new Object[headerwarga.length];
        for (ModelSiap B : listwarga) {
            data[0] = B.getKode_warga();
            data[1] = B.getNo_ktp();
            data[2] = B.getNo_kk();
            data[3] = B.getNama_warga();
            data[4] = B.getJenis_kelamin();
            data[5] = B.getAlamat_warga();
            data[6] = B.getAlamat_asal();
            data[7] = B.getStatus_warga();
            data[8] = B.getFoto();
            tblModel.addRow(data);
        }
        form.getTableWarga().setModel(tblModel);
        int b = tblModel.getRowCount();
        form.getTxtJumlahWarga().setText("Jumlah Warga : "+b);
    }
     
         public void isiTablewargadatang() {
        listdatang = model.getAllWargaPendatang();
        //Script agar jtable tidak bisa di edit
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, headerdatang) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        Object[] data = new Object[headerdatang.length];
        for (ModelSiap B : listdatang) {
            data[0] = B.getKode_warga();
            data[1] = B.getNo_ktp();
            data[2] = B.getNo_kk();
            data[3] = B.getNama_warga();
            data[4] = B.getJenis_kelamin();
            data[5] = B.getAlamat_warga();
            data[6] = B.getAlamat_asal();
            data[7] = B.getStatus_warga();
            data[8] = B.getFoto();
            tblModel.addRow(data);
        }
        form.getTablePendatang().setModel(tblModel);
         int a = tblModel.getRowCount();
        form.getTxtJumlahDatang().setText("Jumlah Warga Pendatang : "+a);
    }
         
        public void isiTablewargatatap() {
        listtetap = model.getAllWargaTetap();
        //Script agar jtable tidak bisa di edit
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, headertetap) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        Object[] data = new Object[headertetap.length];
        for (ModelSiap B : listtetap) {
            data[0] = B.getKode_warga();
            data[1] = B.getNo_ktp();
            data[2] = B.getNo_kk();
            data[3] = B.getNama_warga();
            data[4] = B.getJenis_kelamin();
            data[5] = B.getAlamat_warga();
            data[6] = B.getStatus_warga();
            data[7] = B.getFoto();
            tblModel.addRow(data);
        }
        form.getTableTetap().setModel(tblModel);
        int c = tblModel.getRowCount();
        form.getTxtJumlahTetap().setText("Jumlah Warga Tetap : "+c);
    }
        
        public void Awal(){
        form.getTxtKodeWarga().setEnabled(false);
        form.getTxtNamaWarga().setEnabled(false);
        form.getTxtNoKK().setEnabled(false);
        form.getTxtNoKtp().setEnabled(false);
        form.getTxtAlamatTinggal().setEnabled(false);     
        form.getTxtAlamatAsal().setEnabled(false); 
        form.getTxtPekerjaan().setEnabled(false); 
        form.getTxtJenisKelamin().setEnabled(false); 
        form.getTxtPendidikan().setEnabled(false); 
        form.getTxtStatusKeluarga().setEnabled(false); 
        form.getTxtStatusWarga().setEnabled(false);
        form.getTxtAgama().setEnabled(false); 
        form.getTxtTempatLahir().setEnabled(false);   
        form.getTxtFileFoto().setEnabled(false);   
        form.getTxtTanggalLahir().setEnabled(false);  
        form.getTxtTanggalLahir().setEnabled(false); 
      } 
      
        public void Mulai(){
        form.getTxtKodeWarga().setEnabled(true);
        form.getTxtNamaWarga().setEnabled(true);
        form.getTxtNoKK().setEnabled(true);
        form.getTxtNoKtp().setEnabled(true);
        form.getTxtAlamatTinggal().setEnabled(true);     
        form.getTxtAlamatAsal().setEnabled(true); 
        form.getTxtPekerjaan().setEnabled(true); 
        form.getTxtJenisKelamin().setEnabled(true); 
        form.getTxtPendidikan().setEnabled(true); 
        form.getTxtStatusKeluarga().setEnabled(true); 
        form.getTxtStatusWarga().setEnabled(true);
        form.getTxtAgama().setEnabled(true); 
        form.getTxtTempatLahir().setEnabled(true);   
        form.getTxtFileFoto().setEnabled(true);   
        form.getTxtTanggalLahir().setEnabled(true);  
        form.getTxtTanggalLahir().setEnabled(true); 
      } 
        
         public void ubahhapus(){
        form.getTxtKodeWarga().setEnabled(false);
        form.getTxtNamaWarga().setEnabled(true);
        form.getTxtNoKK().setEnabled(true);
        form.getTxtNoKtp().setEnabled(true);
        form.getTxtAlamatTinggal().setEnabled(true);     
        form.getTxtAlamatAsal().setEnabled(true); 
        form.getTxtPekerjaan().setEnabled(true); 
        form.getTxtJenisKelamin().setEnabled(true); 
        form.getTxtPendidikan().setEnabled(true); 
        form.getTxtStatusKeluarga().setEnabled(true); 
        form.getTxtStatusWarga().setEnabled(true);
        form.getTxtAgama().setEnabled(true); 
        form.getTxtTempatLahir().setEnabled(true);   
        form.getTxtFileFoto().setEnabled(true);   
        form.getTxtTanggalLahir().setEnabled(true);  
        form.getTxtTanggalLahir().setEnabled(true); 
        
      } 
       public void REFRESH(){
       form.getTxtKodeWarga().setText("");
        form.getTxtNamaWarga().setText("");
        form.getTxtNoKK().setSelectedItem("-");
        form.getTxtNoKtp().setText("");
        form.getTxtAlamatTinggal().setText("");     
        form.getTxtAlamatAsal().setText(""); 
        form.getTxtPekerjaan().setText(""); 
        form.getTxtJenisKelamin().setSelectedItem("--Pilih Jenis Kelamin--"); 
        form.getTxtPendidikan().setSelectedItem("--Pilih Pendidikan--"); 
        form.getTxtStatusKeluarga().setSelectedItem("--Pilih Status Keluarga--"); 
        form.getTxtStatusWarga().setSelectedItem("--Pilih Status--");
        form.getTxtAgama().setSelectedItem("--Pilih Agama--"); 
        form.getTxtTempatLahir().setText("");   
        form.getTxtFileFoto().setText("");   
        form.getTxtTanggalLahir().setDateFormatString("dd-MM-yyyy");  
        form.getTxtImgFoto().setIcon(null);
     form.getTxtKodeRT().setSelectedItem("--Pilih Kode RT--"); 
        form.getTxtTanggalLahir().setDate(null); 
      }
      
    public void isiFieldWarga(int row) {        
        form.getTxtNamaWarga().setText (listwarga.get(row).getNama_warga());
        form.getTxtJenisKelamin().setSelectedItem (listwarga.get(row).getJenis_kelamin());
        form.getTxtAlamatTinggal().setText (listwarga.get(row).getAlamat_warga());
        form.getTxtKodeWarga().setText (listwarga.get(row).getKode_warga());
        form.getTxtNoKK().setSelectedItem(listwarga.get(row).getNo_kk());
        form.getTxtNoKtp().setText (listwarga.get(row).getNo_ktp());
        form.getTxtTelepon().setText (listwarga.get(row).getTelepon()); 
        form.getTxtAgama().setSelectedItem (listwarga.get(row).getAgama());  
        form.getTxtFileFoto().setText (listwarga.get(row).getFoto());  
        form.getTxtJumlahData().setText (String.valueOf(listwarga.get(row).getJumlah()));  
        form.getTxtKodeRT().setSelectedItem(listwarga.get(row).getKode_rt());  
        form.getTxtPekerjaan().setText (listwarga.get(row).getPekerjaan());  
        form.getTxtPendidikan().setSelectedItem (listwarga.get(row).getPendidikan());  
        form.getTxtStatusKeluarga().setSelectedItem (listwarga.get(row).getStatus_keluarga());  
        form.getTxtStatusWarga().setSelectedItem (listwarga.get(row).getStatus_warga());  
        form.getTxtTempatLahir().setText (listwarga.get(row).getTempat_lahir());  
        form.getTxtTanggalLahir().setDate (listwarga.get(row).getTgl_lahir());  
    ubahhapus();
    
    }
     public void isiFieldwargaTetap(int row) {        
        form.getTxtNamaTetap().setText (listtetap.get(row).getNama_warga());
        form.getTxtKelaminTetap().setText (listtetap.get(row).getJenis_kelamin());
        form.getTxtAlamatTetap().setText (listtetap.get(row).getAlamat_warga());
        form.getTxtKodeTetap().setText (listtetap.get(row).getKode_warga());
        form.getTxtNoKKTetap().setText (listtetap.get(row).getNo_kk());
        form.getTxtNoKtpTetap().setText (listtetap.get(row).getNo_ktp());
        form.getTxtTeleponTetap().setText (listtetap.get(row).getTelepon());  
    }
     
     public void isiFieldwargaPendatang(int row) {
       form.getTxtNamaDatang().setText (listdatang.get(row).getNama_warga());
       form.getTxtKelaminDatang().setText (listdatang.get(row).getJenis_kelamin());
       form.getTxtAlamatDatang().setText (listdatang.get(row).getAlamat_warga());
       form.getTxtAsalDatang().setText (listdatang.get(row).getAlamat_asal());
       form.getTxtKodeDatang().setText (listdatang.get(row).getKode_warga());
       form.getTxtNoKKDatang().setText (listdatang.get(row).getNo_kk());
       form.getTxtNoKtpDatang().setText (listdatang.get(row).getNo_ktp());
       form.getTxtTeleponDatang().setText (listdatang.get(row).getTelepon());
    }
     
    
      public void insertwarga() {
         ModelSiap B = new ModelSiap();
        B.setKode_warga(form.getTxtKodeWarga().getText());
        B.setNo_ktp(form.getTxtNoKtp().getText());
        B.setNo_kk(form.getTxtNoKK().getSelectedItem().toString());
        B.setNama_warga(form.getTxtNamaWarga().getText());
        B.setJenis_kelamin(form.getTxtJenisKelamin().getSelectedItem().toString());
        B.setTempat_lahir(form.getTxtTempatLahir().getText());
        B.setTgl_lahir(form.getTxtTanggalLahir().getDate());
        B.setAgama(form.getTxtAgama().getSelectedItem().toString());
        B.setPekerjaan(form.getTxtPekerjaan().getText());
        B.setPendidikan(form.getTxtPendidikan().getSelectedItem().toString());
        B.setAlamat_warga(form.getTxtAlamatTinggal().getText());
        B.setAlamat_asal(form.getTxtAlamatAsal().getText());
        B.setStatus_keluarga(form.getTxtStatusKeluarga().getSelectedItem().toString());
        B.setStatus_warga(form.getTxtStatusWarga().getSelectedItem().toString());
        B.setTelepon(form.getTxtTelepon().getText());
        B.setJumlah(Integer.parseInt(form.getTxtJumlahData().getText()));
        B.setKode_rt(form.getTxtKodeRT().getSelectedItem().toString());
        B.setFoto(form.getTxtFileFoto().getText());
        model.insert(B);
        model.update_jumlahwarga(B);
      }
  
       
      public void updatewarga() {
         ModelSiap B = new ModelSiap();

        B.setNo_ktp(form.getTxtNoKtp().getText());
        B.setNo_kk(form.getTxtNoKK().getSelectedItem().toString());
        B.setNama_warga(form.getTxtNamaWarga().getText());
        B.setJenis_kelamin(form.getTxtJenisKelamin().getSelectedItem().toString());
        B.setTempat_lahir(form.getTxtTempatLahir().getText());
        B.setTgl_lahir(form.getTxtTanggalLahir().getDate());
        B.setAgama(form.getTxtAgama().getSelectedItem().toString());
        B.setPekerjaan(form.getTxtPekerjaan().getText());
        B.setPendidikan(form.getTxtPendidikan().getSelectedItem().toString());
        B.setAlamat_warga(form.getTxtAlamatTinggal().getText());
        B.setAlamat_asal(form.getTxtAlamatAsal().getText());
        B.setStatus_keluarga(form.getTxtStatusKeluarga().getSelectedItem().toString());
        B.setStatus_warga(form.getTxtStatusWarga().getSelectedItem().toString());
        B.setTelepon(form.getTxtTelepon().getText());
        B.setJumlah(Integer.parseInt(form.getTxtJumlahData().getText()));
        B.setKode_rt(form.getTxtKodeRT().getSelectedItem().toString());
        B.setFoto(form.getTxtFileFoto().getText());
        B.setKode_warga(form.getTxtKodeWarga().getText());
        model.update(B);
       
      }
      
        public void deletestatistiktetap() {
         ModelSiap B = new ModelSiap();
       
        B.setStatus_warga(form.getTxtStatusWarga().getSelectedItem().toString());
        B.setJumlah(Integer.parseInt(form.getTxtJumlahData().getText()));
        model.delete_statistikwarga(B);
       
      }
        
       public void deletewarga() {
          
        if (!form.getTxtKodeWarga().getText().trim().isEmpty()) {
            String id = (form.getTxtKodeWarga().getText());
            model.delete(id);
           
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }
       
     
      
     // untuk kk
      public void insertkk() {
         ModelSiap B = new ModelSiap();
        B.setNo_kk(form.getTxtKartuKeluarga().getText());
      
        model.insertKartuKeluarga(B);
      }
   
      
       public void insertdetailkk() {
         ModelSiap B = new ModelSiap();
        B.setKode_warga(form.getTxtKodeWarga().getText());
        B.setNo_kk(form.getTxtNoKK().getSelectedItem().toString());
        B.setNo_ktp(form.getTxtNoKtp().getText());
       
        model.insertDetailKK(B);
       
      }
       
        public void updatedetailkk() {
        ModelSiap B = new ModelSiap();
        B.setKode_warga(form.getTxtKodeWarga().getText());
        B.setNo_kk(form.getTxtNoKK().getSelectedItem().toString());
        B.setNo_ktp(form.getTxtNoKtp().getText());
        model.updateDetailKK(B);
      }
        

        public void deletedetailkk() {
        if (!form.getTxtNoKtp().getText().trim().isEmpty()) {
         
            String id = (form.getTxtNoKtp().getText());
            
            model.deleteDetailKK(id);
           
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }
        
  
//      
        public void cetakWarga(){
            try {
               
           String fillname = ("src/Laporan/LaporanDataWarga.jasper");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/siwarga014","root","");
             HashMap hash = new HashMap();
            //Mengambil parameter dari ireport
                 
                      hash.put("nama_rt",(form.getTxtNamaErte().getText()));
       
                   File file = new File(fillname);
                   JasperReport jasperReport =(JasperReport)JRLoader.loadObject(file.getPath());
                   JasperPrint jasperPrint = JasperFillManager.fillReport(fillname, hash, koneksi);
                   JasperViewer.viewReport(jasperPrint, false);
                   
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null,"Data Tidak dapat Dicetak !!"+"\n"+ex.getMessage(),"Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
        
  } 
//        
          public void cetakWargaStatusTetap(){
            try {
               
           String fillname = ("src/Laporan/LaporanWargaStatus.jasper");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/siwarga014","root","");
             
            HashMap hash = new HashMap();
            //Mengambil parameter dari ireport
                  hash.put("status_warga",(form.getTxtStatusTetap().getText()));
                  hash.put("nama_rt",(form.getTxtNamaErte().getText()));
                    
                   File file = new File(fillname);
                   JasperReport jasperReport =(JasperReport)JRLoader.loadObject(file.getPath());
                   JasperPrint jasperPrint = JasperFillManager.fillReport(fillname, hash, koneksi);
                   JasperViewer.viewReport(jasperPrint, false);
                   
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null,"Data Tidak dapat Dicetak !!"+"\n"+ex.getMessage(),"Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
        
  } 
           public void cetakWargaStatusDatang(){
            try {
               
           String fillname = ("src/Laporan/LaporanWargaStatus.jasper");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/siwarga014","root","");
             
            HashMap hash = new HashMap();
            //Mengambil parameter dari ireport
                  hash.put("status_warga",(form.getTxtStatusDatang().getText()));
                  hash.put("nama_rt",(form.getTxtNamaErte().getText()));
                    
                   File file = new File(fillname);
                   JasperReport jasperReport =(JasperReport)JRLoader.loadObject(file.getPath());
                   JasperPrint jasperPrint = JasperFillManager.fillReport(fillname, hash, koneksi);
                   JasperViewer.viewReport(jasperPrint, false);
                   
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null,"Data Tidak dapat Dicetak !!"+"\n"+ex.getMessage(),"Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
        
  } 
//            public void cetakWargaPendatang(){
//            try {
//               
//           String fillname = ("src/Report/LaporanWargaPendatang.jasper");
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//                Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/app_erte014","root","");
//             
//            HashMap hash = new HashMap();
//            //Mengambil parameter dari ireport
//                  hash.put("status_warga",(form.getTxtStatusDatang().getText()));
//                      hash.put("nama_rt",(form.getTxtNamaErte().getText()));
//                   File file = new File(fillname);
//                   JasperReport jasperReport =(JasperReport)JRLoader.loadObject(file.getPath());
//                   JasperPrint jasperPrint = JasperFillManager.fillReport(fillname, hash, koneksi);
//                   JasperViewer.viewReport(jasperPrint, false);
//                   
//        } catch(Exception ex) {
//            JOptionPane.showMessageDialog(null,"Data Tidak dapat Dicetak !!"+"\n"+ex.getMessage(),"Cetak Data",JOptionPane.ERROR_MESSAGE);
//        }
//        
//  } 
}
