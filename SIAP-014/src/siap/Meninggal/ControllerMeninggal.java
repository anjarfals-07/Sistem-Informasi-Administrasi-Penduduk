/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siap.Meninggal;



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
 * @author ASUS
 */
public class ControllerMeninggal {

    ViewDataMeninggal form;
  ModelMeninggal model;
//    ModelWarga model1;
    List<ModelSiap> list; //deklarasi method "List" yang sudah dibuat pada interface MODEL_DAO
 
    String[] header; //deklarasi variable untuk membuat judul kolom pada objek JTable
  
    public ControllerMeninggal(ViewDataMeninggal form) {
        this.form = form;
        model = new ModelMeninggal();
//        model1 = new ModelWarga();
        list = model.getAll();
     
        header = new String[]{"KODE", "NO SURAT","TANGGAL SURAT","NO KTP", "NAMA LENGKAP","JENIS KELAMIN","TANGGAL WAFAT","USIA","PENYEBAB","STATUS WARGA"};
       // headerd = new String[]{"NO SURAT", "KODE KEMATIAN", "KODE RT", "TANGGAL SURAT"};
        
        form.getTableKematian().setShowGrid(true);
        form.getTableKematian().setShowVerticalLines(true);
        form.getTableKematian().setGridColor(Color.blue);
    }
    
     public void isicombort() {
        form.getTxtKodeRt().removeAllItems();
        form.getTxtKodeRt().addItem("--Pilih Kode RT--");
        for (ModelSiap b : model.isicombort()) {
            form.getTxtKodeRt().addItem(b.getKode_rt());
        }
    }
    
    //method ini akan digunakan untuk menampilkan kode supplier dan lain-lain berdasarkan inputan combo nama supplier
    public void tampilnort() {
        if (form.getTxtKodeRt().getSelectedIndex() != 0) {
            for (ModelSiap b : model.getrt(form.getTxtKodeRt().getSelectedItem().toString())) {

            }
        }
    }  
     public void tampilurutankodekematian() {         
            ModelSiap p = new ModelSiap();
            model.autonumber(p);
            String no_mor=String.valueOf(model.autonumber(p));
            String oke="00".substring(0, 2 - no_mor.length());
            form.getTxtKodeKematian().setText( "KMT-"+  oke +""+no_mor );
        }
      public void tampilurutankodesurat() {         
            ModelSiap p = new ModelSiap();
            model.autonumber(p);
            String no_mor=String.valueOf(model.autonumber(p));
            String oke="00".substring(0, 2 - no_mor.length());
            form.getTxtNoSurat().setText( "SK-"+  oke +""+no_mor );
        }
     
    
     public void isiTableKematian() {
        list = model.getAll();
        //Script agar jtable tidak bisa di edit
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, header) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return true;
            }
        };

        Object[] data = new Object[header.length];
        for (ModelSiap B : list) {
            data[0] = B.getKode_kematian();
            data[1] = B.getNo_surat();
            data[2] = B.getTanggal_surat();
            data[3] = B.getNo_ktp();
         
            data[4] = B.getNama_warga();
            data[5] = B.getJenis_kelamin();
            data[6] = B.getTanggal_wafat();
            data[7] = B.getUsia();
            data[8] = B.getPenyebab();
            data[9] = B.getStatus();
            tblModel.addRow(data);
        }
        form.getTableKematian().setModel(tblModel);
        int c = tblModel.getRowCount();
        form.getTxJumlahKematian().setText("Jumlah Data Kematian : "+c);
    }
     
     
      public void awalkematian(){
         form.getTxtKodeKematian().setEnabled(false);
         form.getTxtNoSurat().setEnabled(false);
         form.getTxtKtp().setEnabled(false);
         form.getTxtKk().setEnabled(false);
         form.getTxtNamaLengkap().setEnabled(false);
         form.getTxtKodeRt().setEnabled(false);
         form.getTxtTanggalKematian().setEnabled(false);
         form.getTxtTanggalSurat().setEnabled(false);
         form.getTxtPenyebab().setEnabled(false);
         form.getTxtStatusMeninggal().setEnabled(false);
         form.getTxtStatusWarga().setEnabled(false);
         form.getTxtJenisKelamin().setEnabled(false);
         form.getTxtUsia().setEnabled(false);
      
      }
      
       
       public void kondisitambah(){
         form.getTxtKodeKematian().setEnabled(true);
         form.getTxtKk().setEnabled(true);
         form.getTxtKtp().setEnabled(true);
         form.getTxtNamaLengkap().setEnabled(true);
         form.getTxtUsia().setEnabled(true);
         form.getTxtTanggalKematian().setEnabled(true);
         form.getTxtTanggalSurat().setEnabled(true);
         form.getTxtPenyebab().setEnabled(true);
         form.getTxtStatusMeninggal().setEnabled(true);
         form.getTxtStatusWarga().setEnabled(true);
         form.getTxtKodeRt().setEnabled(true);
         form.getTxtJenisKelamin().setEnabled(true);
         form.getTxtNoSurat().setEnabled(true);
        form.getSimpan().setEnabled(true);
       form.getUbah().setEnabled(false);
       form.getHapus().setEnabled(false); 
    
         
         tampilurutankodekematian(); 
     }
     
     public void reset() {      
        form.getTxtKodeKematian().setText("");
        form.getTxtNamaLengkap().setText("");
        form.getTxtKk().setText("");
        form.getTxtKtp().setText("");
        form.getTxtPenyebab().setText("");     
        form.getTxtUsia().setText(""); 
        form.getTxtKodeRt().setSelectedItem("--Pilih Kode RT--"); 
        form.getTxtJenisKelamin().setText(""); 
        form.getTxtStatusMeninggal().setText(""); 
        form.getTxtStatusWarga().setText(""); 
        form.getTxtNoSurat().setText("");   
        form.getTxtTanggalKematian().setDate(null);   
         form.getTxtTanggalSurat().setDate(null);   
    }
       
     
       public void kondisiubahdanhapus(){
         form.getTxtKk().setEnabled(false);
         form.getTxtKtp().setEnabled(false);
         form.getTxtNamaLengkap().setEnabled(false);
         form.getTxtStatusMeninggal().setEnabled(false);
         form.getTxtTanggalKematian().setEnabled(true);
         form.getTxtTanggalSurat().setEnabled(true);
         form.getTxtPenyebab().setEnabled(true);
         form.getTxtUsia().setEnabled(true);
         form.getTxtStatusWarga().setEnabled(false);
         form.getTxtKodeRt().setEnabled(false);
         form.getTxtNoSurat().setEnabled(false);
         form.getTxtKodeKematian().setEnabled(false);
    
       form.getSimpan().setEnabled(false);
       form.getUbah().setEnabled(true);
       form.getHapus().setEnabled(true); 
       
      
     }
   
     public void isiFieldkematian(int row) {
        form.getTxtKodeKematian().setText(String.valueOf(list.get(row).getKode_kematian()));
        form.getTxtKtp().setText(list.get(row).getNo_ktp());
        form.getTxtKk().setText(list.get(row).getNo_kk());
        form.getTxtNamaLengkap().setText(list.get(row).getNama_warga());
        form.getTxtJenisKelamin().setText(list.get(row).getJenis_kelamin());
        form.getTxtUsia().setText(list.get(row).getUsia());
        form.getTxtPenyebab().setText(list.get(row).getPenyebab()); 
        form.getTxtTanggalKematian().setDate(list.get(row).getTanggal_wafat());
        form.getTxtJumlahData().setText(String.valueOf(list.get(row).getJumlah()));
        form.getTxtStatusWarga().setText(list.get(row).getStatus());
        form.getTxtStatusMeninggal().setText(list.get(row).getStatus_warga());
        form.getTxtNoSurat().setText(String.valueOf(list.get(row).getNo_surat()));
        form.getTxtKodeRt().setSelectedItem(list.get(row).getKode_rt());
        form.getTxtTanggalSurat().setDate(list.get(row).getTanggal_surat());
    

        kondisiubahdanhapus();
         
        
    }
      public void insertkematian() {
        ModelSiap B = new ModelSiap();
        B.setKode_kematian(form.getTxtKodeKematian().getText());
        B.setNo_ktp(form.getTxtKtp().getText());
        B.setNo_kk(form.getTxtKk().getText());
        B.setNama_warga(form.getTxtNamaLengkap().getText());
        B.setJenis_kelamin(form.getTxtJenisKelamin().getText());
        B.setUsia(form.getTxtUsia().getText());
        B.setPenyebab(form.getTxtPenyebab().getText());
        B.setTanggal_wafat(form.getTxtTanggalKematian().getDate());
        B.setJumlah(Integer.parseInt(form.getTxtJumlahData().getText()));
        B.setStatus(form.getTxtStatusWarga().getText());
        B.setStatus_warga(form.getTxtStatusMeninggal().getText());    
        model.insert(B);
        model.update_jumlahkematian(B);
        deletestatistikwarga();
        model.delete_statistikwargapendatang(B);
      }
      
     public void deletestatistikwarga(){
       ModelSiap A = new ModelSiap();
       //A.setStatus_warga(form.getTxtJenisWarga().getText());
       A.setJumlah(Integer.parseInt(form.getTxtJumlahData().getText())); 
       model.delete_statistikwarga(A);
     }
       
      public void updatekematian() {
      ModelSiap B = new ModelSiap();  
        B.setKode_kematian(form.getTxtKodeKematian().getText());
        B.setNo_ktp(form.getTxtKtp().getText());
        B.setNo_kk(form.getTxtKk().getText());
        B.setNama_warga(form.getTxtNamaLengkap().getText());
        B.setJenis_kelamin(form.getTxtJenisKelamin().getText());
        B.setUsia(form.getTxtUsia().getText());
        B.setPenyebab(form.getTxtPenyebab().getText());
        B.setTanggal_wafat(form.getTxtTanggalKematian().getDate());
        B.setJumlah(Integer.parseInt(form.getTxtJumlahData().getText()));
        B.setStatus(form.getTxtStatusWarga().getText());
        B.setStatus_warga(form.getTxtStatusMeninggal().getText());     
        model.update(B);
      }
      
       public void deletestatistikkematian() {
        ModelSiap B = new ModelSiap();
        B.setStatus_warga(form.getTxtStatusMeninggal().getText());
        B.setJumlah(Integer.parseInt(form.getTxtJumlahData().getText()));
        model.delete_statistikkematian(B);
      }
       
       public void deletekematian() {
          
        if (!form.getTxtKtp().getText().trim().isEmpty()) {
            String id = (form.getTxtKtp().getText());
            model.delete(id);
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }

//      //---------------untuk surat
//        public void isiFieldsurat(int row) {
//        form.getTxtNoSurat().setText(String.valueOf(listd.get(row).getNo_surat()));
//        form.getTxtKodeKematian().setText(listd.get(row).getKode_kematian());
//        form.getTxtKodeRt().setText(listd.get(row).getKode_rt());
//        form.getTxtTanggalSurat().setDate(listd.get(row).getTanggal_surat());
//       
//        kondisiubahdanhapus();
//        
//    }
      
        public void insertsurat() {
        ModelSiap B = new ModelSiap();
        B.setNo_surat(form.getTxtNoSurat().getText());
        B.setKode_kematian(form.getTxtKodeKematian().getText());
        B.setKode_rt(form.getTxtKodeRt().getSelectedItem().toString());
        B.setTanggal_surat(form.getTxtTanggalSurat().getDate());   
        model.insertsurat(B);
      }
      
       public void updatesurat() {
         ModelSiap B = new ModelSiap();
        B.setNo_surat(form.getTxtNoSurat().getText());
        B.setKode_kematian(form.getTxtKodeKematian().getText());
        B.setKode_rt(form.getTxtKodeRt().getSelectedItem().toString());
        B.setTanggal_surat(form.getTxtTanggalSurat().getDate()); 
        model.updatesurat(B);
      }
       
        public void deletesurat() {
        
        if (!form.getTxtKodeKematian().getText().trim().isEmpty()) {
            String id = (form.getTxtKodeKematian().getText());  
            model.deletesurat(id);
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }
       
         public void deletewarga() {
        
        if (!form.getTxtKtp().getText().trim().isEmpty()) {
            String id = (form.getTxtKtp().getText());  
            model.deletewarga(id);
              model.deletekk(id);
               model.deletependatang(id);
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }

   
     
     public void cetakSuratKematian(){
            try {
               
           String fillname = ("src/Laporan/SuratKematian.jasper");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/siwarga014","root","");
             
            HashMap hash = new HashMap();
            //Mengambil parameter dari ireport
                  hash.put("no_surat",(form.getTxtNoSurat().getText()));
                     hash.put("nama_rt",(form.getTxtNamaErte().getText()));
                   File file = new File(fillname);
                   JasperReport jasperReport =(JasperReport)JRLoader.loadObject(file.getPath());
                   JasperPrint jasperPrint = JasperFillManager.fillReport(fillname, hash, koneksi);
                   JasperViewer.viewReport(jasperPrint, false);
                   
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null,"Data Tidak dapat Dicetak !!"+"\n"+ex.getMessage(),"Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
        
  } 
     
      public void cetakKematian(){
            try {
               
           String fillname = ("src/Laporan/LaporanWargaKematian.jasper");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/siwarga014","root","");
             
               HashMap hash = new HashMap();
//            //Mengambil parameter dari ireport
                  hash.put("status",(form.getTxtStatusWargaCombo().getSelectedItem()));
                     hash.put("nama_rt",( form.getTxtNamaErte().getText()));
                   File file = new File(fillname);
                   JasperReport jasperReport =(JasperReport)JRLoader.loadObject(file.getPath());
                   JasperPrint jasperPrint = JasperFillManager.fillReport(fillname, hash , koneksi);
                   JasperViewer.viewReport(jasperPrint, false);
                   
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null,"Data Tidak dapat Dicetak !!"+"\n"+ex.getMessage(),"Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
        
  } 
    
          
}
