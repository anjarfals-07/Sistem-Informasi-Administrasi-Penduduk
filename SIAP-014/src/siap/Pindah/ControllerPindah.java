/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siap.Pindah;

import java.awt.Color;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
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
 * @author ASUS
 */
public class ControllerPindah {

    ViewDataPindah form;
    ModelPindah model;
    List<ModelSiap> list; //deklarasi method "List" yang sudah dibuat pada interface MODEL_DAO
    String[] header; //deklarasi variable untuk membuat judul kolom pada objek JTable
     List<ModelSiap> lisp; //deklarasi method "List" yang sudah dibuat pada interface MODEL_DAO
    String[] headerp; //deklarasi variable untuk membuat judul kolom pada objek JTable
    
    public ControllerPindah( ViewDataPindah form) {
        this.form = form;
        model = new ModelPindah();   
       list = model.getAllPindah(); 
        header = new String[]{"KODE", "TANGGAL PINDAH", "NO SURAT", "TANGGAL SURAT", "NO.KK"," ALAMAT PINDAH","JUMLAH DATA","KETERANGAN","KODE RT"};
        form.getTableDataPindah().setShowGrid(true);
        form.getTableDataPindah().setShowVerticalLines(true);
        form.getTableDataPindah().setGridColor(Color.blue);

         lisp = model.getDetailPindah();
      
        headerp = new String[]{"KODE", "NO KK", "NO KTP", "NAMA LENGKAP", "JENIS KELAMIN","STATUS WARGA","STATUS","FOTO","JUMLAH"};
      
        form.getTablePindahDetail().setShowGrid(true);
        form.getTablePindahDetail().setShowVerticalLines(true);
        form.getTablePindahDetail().setGridColor(Color.blue);
    }
    
     public void isicombort() {
        form.getTxtKodeRt().removeAllItems();
        form.getTxtKodeRt().addItem("--Pilih Kode RT--");
        for (ModelSiap b : model.isicombort()) {
            form.getTxtKodeRt().addItem(b.getKode_rt());
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
    
   
     public void tampilurutankodepindah() {         
            ModelSiap p = new ModelSiap();
            model.autonumber(p);
            String no_mor=String.valueOf(model.autonumber(p));
            String oke="00".substring(0, 2 - no_mor.length());
            form.getTxtKodePindah().setText( "PDH-"+  oke +""+no_mor );
        }
     
     public void tampilurutankodeSURAT() {         
            ModelSiap p = new ModelSiap();
            model.autonumbersurat(p);
            String no_mor=String.valueOf(model.autonumbersurat(p));
            String oke="00".substring(0, 2 - no_mor.length());
            form.getTxtNoSurat().setText( "SKP-"+  oke +""+no_mor );
        }
     
    
     public void isiTablePindah() {
        list = model.getAllPindah();
        //Script agar jtable tidak bisa di edit
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, header) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        Object[] data = new Object[header.length];
        for (ModelSiap B : list) {
            data[0] = B.getKode_pindah();
            data[1] = B.getTanggal_pindah();
            data[2] = B.getNo_surat();
            data[3] = B.getTanggal_surat();
            data[4] = B.getNo_kk();
            data[5] = B.getAlamat_pindah();
            data[6] = B.getJumlah_pindah();
            data[7] = B.getKeterangan();
            data[8] = B.getKode_rt();
            tblModel.addRow(data);
        }
        form.getTableDataPindah().setModel(tblModel);
         int a = tblModel.getRowCount();
        form.getTxtJumlahPindah().setText("Jumlah KK Pindah : "+a);
    }
     
    
      public void awalpindah(){
         form.getTxtKodePindah().setEnabled(false);
         form.getTxtNoSurat().setEnabled(false);
         form.getTxtAlamatPindah().setEnabled(false);
         form.getTxtKodeRt().setEnabled(false);
         form.getTxtNoSurat().setEnabled(false);
         form.getTxtTanggalPindah().setEnabled(false);
         form.getTxtTanggalSurat().setEnabled(false);
         form.getTxtStatusPindah().setEnabled(false);
         form.getTxtAlasan().setEnabled(false);
         form.getTxtAlasan().setEnabled(false);
         form.getTxtNoKK().setEnabled(false);
      
      }
      
     
       public void kondisitambahpindah(){
        form.getTxtKodePindah().setEnabled(false);
         form.getTxtNoSurat().setEnabled(false);
         form.getTxtAlamatPindah().setEnabled(true);
         form.getTxtKodeRt().setEnabled(true);
         form.getTxtNoSurat().setEnabled(false);
         form.getTxtTanggalPindah().setEnabled(true);
         form.getTxtTanggalSurat().setEnabled(false);
         form.getTxtStatusPindah().setEnabled(false);
         form.getTxtAlasan().setEnabled(true);
         form.getTxtNoKK().setEnabled(true);

         tampilurutankodepindah(); 
         tampilurutankodeSURAT(); 
     }
     
     public void reset() {      
        form.getTxtKodePindah().setText("");
        form.getTxtAlamatPindah().setText("");
        form.getTxtNoKK().setSelectedItem("--Pilih No.KK--");
        form.getTxtAlasan().setText("");
        form.getTxtKodeRt().setSelectedItem("--Pilih Kode RT--");     
        form.getTxtNoSurat().setText(""); 
        form.getTxtTanggalPindah().setDate(null); 
           
    }
       
   
     public void isiFieldpindah(int row) {
        form.getTxtKodePindah().setText(String.valueOf(list.get(row).getKode_pindah()));
        form.getTxtAlamatPindah().setText(list.get(row).getAlamat_pindah());
        form.getTxtAlasan().setText(list.get(row).getKeterangan());
        form.getTxtKodeRt().setSelectedItem(list.get(row).getKode_rt());
        form.getTxtNoKK().setSelectedItem(list.get(row).getNo_kk());
        form.getTxtNoSurat().setText(list.get(row).getNo_surat());
        form.getTxtNoSurat().setText(list.get(row).getNo_surat());
        form.getTxtTanggalPindah().setDate(list.get(row).getTanggal_pindah());
//        form.getTxtStatusPindah().setText(list.get(row).getStatus());
        form.getTxtTanggalSurat().setText(String.valueOf(list.get(row).getTanggal_surat()));
        form.getTxtKodeDetail().setText(String.valueOf(list.get(row).getKode_pindah()));
        form.getTxtKKDetail().setText(list.get(row).getNo_kk());
        form.getTxtJumlahData().setText(String.valueOf(list.get(row).getJumlah_pindah()));
    }
      public void insertpindah() {
        ModelSiap B = new ModelSiap();
        
        B.setKode_pindah(form.getTxtKodePindah().getText());
        B.setTanggal_pindah(form.getTxtTanggalPindah().getDate());
        B.setNo_kk(form.getTxtNoKK().getSelectedItem().toString());
        B.setAlamat_pindah(form.getTxtAlamatPindah().getText());
        B.setJumlah_pindah(Integer.parseInt(form.getTxtJumlahData().getText()));
        B.setStatus_pindah(form.getTxtStatusPindah().getText());
        B.setKode_rt(form.getTxtKodeRt() .getSelectedItem().toString());
       
        model.insert(B);

      }
 
      public void updatepindah() {
        ModelSiap B = new ModelSiap();
        B.setKode_pindah(form.getTxtKodePindah().getText());
        B.setTanggal_pindah(form.getTxtTanggalPindah().getDate());
        B.setNo_kk(form.getTxtNoKK().getSelectedItem().toString());
        B.setAlamat_pindah(form.getTxtAlamatPindah().getText());
        B.setJumlah_pindah(Integer.parseInt(form.getTxtJumlahData().getText()));
        B.setStatus_pindah(form.getTxtStatusPindah().getText());
        B.setKode_rt(form.getTxtKodeRt() .getSelectedItem().toString());
        model.update(B);
      }
      
     
       public void deletepindah() {
          
        if (!form.getTxtKodePindah().getText().trim().isEmpty()) {
            String id = (form.getTxtKodePindah().getText());
            model.delete(id);
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }
        public void insertsurat() {
         ModelSiap B = new ModelSiap();
        B.setNo_surat(form.getTxtNoSurat().getText());
        B.setKode_pindah(form.getTxtKodePindah().getText());
        B.setTanggal_surat(Date.valueOf(form.getTxtTanggalSurat().getText()));
        B.setKeterangan(form.getTxtAlasan().getText());
      
        model.insertsurat(B);
      }
      
       public void updatesurat() {
       ModelSiap B = new ModelSiap();
        B.setNo_surat(form.getTxtNoSurat().getText());
        B.setKode_pindah(form.getTxtKodePindah().getText());
        B.setTanggal_surat(Date.valueOf(form.getTxtTanggalSurat().getText()));
        B.setKeterangan(form.getTxtAlasan().getText());
        model.updatesurat(B);
      }
       
        public void deletesurat() {
        
        if (!form.getTxtKodePindah().getText().trim().isEmpty()) {
            String id = (form.getTxtKodePindah().getText());  
            model.deletesurat(id);
    }
     }
         public void deletedetail() {
        
        if (!form.getTxtKodePindah().getText().trim().isEmpty()) {
            String id = (form.getTxtKodePindah().getText());  
            model.deletesurat(id);
       
            }
         }
         
//         public void updatestatistikpindah() {
//        ModelSiap B = new ModelSiap();
//        B.setStatus(form.getTxtStatusPindah().getText());
//        B.setJumlah_data(Integer.parseInt(form.getTxtJumlahDataPindah().getText()));
//       
//        model.updatestatistikpindah(B);
//      }
         public void deletestatistikpindah() {
        ModelSiap B = new ModelSiap();
        B.setStatus_pindah(form.getTxtStatusPindah().getText());
        B.setJumlah_pindah(Integer.parseInt(form.getTxtJumlahData().getText()));
        model.delete_statistikjumlahpindah(B);
       
      }

     public void isiTablePindahDetail() {
        lisp = model.getDetailPindah();
        //Script agar jtable tidak bisa di edit
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, headerp) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        Object[] data = new Object[headerp.length];
        for (ModelSiap B : lisp) {
            data[0] = B.getKode_pindah();
            data[1] = B.getNo_kk();
            data[2] = B.getNo_ktp();
            data[3] = B.getNama_warga();
            data[4] = B.getJenis_kelamin();
            data[5] = B.getStatus_warga();
            data[6] = B.getStatus();
            data[7] = B.getFoto();
            data[8] = B.getJumlah();
            tblModel.addRow(data);
        }
        form.getTablePindahDetail().setModel(tblModel);
         int a = tblModel.getRowCount();
//        form.getTxtJumlahD().setText(""+a);
    }
     

    
    //method ini akan digunakan untuk menampilkan kode supplier dan lain-lain berdasarkan inputan combo nama supplier
    public void tampilkodekk() {
//        if (form.getTxtNospp().action(null, this)) {
            for (ModelSiap b : model.getkk(form.getTxtKKDetail().getText().toString())) {
                form.getTxtKtpDetail().setText(String.valueOf(b.getNo_ktp()));
                 
            }
    } 
 
      public void awalpindahdetail(){
         form.getTxtKodeDetail().setEnabled(false);
         form.getTxtJenisKelaminDetail().setEnabled(false);
         form.getTxtKtpDetail().setEnabled(false);
         form.getTxtKKDetail().setEnabled(false);
         form.getTxtNamaDetail().setEnabled(false);
         form.getTxtStatusPindah().setEnabled(false);
         form.getTxtStatusWargaDetail().setEnabled(false);
         form.getTxtFoto().setEnabled(false); 
      }
     public void resetdetail() {      
     form.getTxtNamaDetail().setText("");
     form.getTxtKtpDetail().setText("");
     form.getTxtJenisKelaminDetail().setText("");     
     form.getTxtStatusWargaDetail().setText(""); 
     form.getTxtFoto().setText("");  
    }
   
     public void isiFieldpindahdetail(int row) {
        form.getTxtKodeDetail().setText(String.valueOf(lisp.get(row).getKode_pindah()));
        form.getTxtKtpDetail().setText(lisp.get(row).getNo_ktp());
        form.getTxtKKDetail().setText(lisp.get(row).getNo_kk());
        form.getTxtNamaDetail().setText(lisp.get(row).getNama_warga());
        form.getTxtJenisKelaminDetail().setText(lisp.get(row).getJenis_kelamin());
        form.getTxtStatusPindah().setText(lisp.get(row).getStatus());
        form.getTxtStatusWargaDetail().setText(lisp.get(row).getStatus_warga());
        form.getTxtFoto().setText(lisp.get(row).getFoto());
        form.getTxtJumlahDetail().setText(String.valueOf(lisp.get(row).getJumlah()));
    }
    
//       public void deletestatistikpindah() {
//        ModelSiap B = new ModelSiap();
//        B.setStatus(form.getTxtStatusPindah().getText());
//        B.setJumlah(Integer.parseInt(form.getTxtJumlahDetail().getText()));
//        model.delete_statistikpindah(B);
//       
//      }
       public void deletestatistikwarga() {
        ModelSiap B = new ModelSiap();
        B.setStatus_warga(form.getTxtStatusWargaDetail().getText());
        B.setJumlah(Integer.parseInt(form.getTxtJumlahDetail().getText()));
        model.delete_statistikwarga(B);
       
      }
       
       public void deletedetailpindah() {
          
        if (!form.getTxtKtpDetail().getText().trim().isEmpty()) {
            String id = (form.getTxtKtpDetail().getText());
            model.deletedetail(id);
            
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }
       
        public void deletewarga() {
          
        if (!form.getTxtKtpDetail().getText().trim().isEmpty()) {
            String id = (form.getTxtKtpDetail().getText());
            model.deletewarga(id);
            
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }
        
        
        
        public void cetakWargaPindah(){
            try {
               
           String fillname = ("src/Laporan/LaporanWargaPindah.jasper");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/siwarga014","root","");
             HashMap hash = new HashMap();
            //Mengambil parameter dari ireport
                    hash.put("status",(form.getTxtStatusWargaCombo().getSelectedItem()));
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
          public void cetakSuratPindah(){
            try {
               
           String fillname = ("src/Laporan/SuratPindah.jasper");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/siwarga014","root","");
             
            HashMap hash = new HashMap();
            //Mengambil parameter dari ireport
                  hash.put("surat",(form.getTxtNoSurat().getText()));
                  hash.put("nama_rt",(form.getTxtNamaErte().getText()));
                    
                   File file = new File(fillname);
                   JasperReport jasperReport =(JasperReport)JRLoader.loadObject(file.getPath());
                   JasperPrint jasperPrint = JasperFillManager.fillReport(fillname, hash, koneksi);
                   JasperViewer.viewReport(jasperPrint, false);
                   
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null,"Data Tidak dapat Dicetak !!"+"\n"+ex.getMessage(),"Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
        
  } 

}
