/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siap.SuratPengantar;


import java.awt.Color;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
public class ControllerSuratPengantar {
   ViewSuratPengantar form;
   ModelSuratPengantar model;
    List<ModelSiap> list; //deklarasi method "List" yang sudah dibuat pada interface MODEL_DAO
    String[] header; //deklarasi variable untuk membuat judul kolom pada objek JTable

    public ControllerSuratPengantar(ViewSuratPengantar form) {
        this.form = form;
        model = new ModelSuratPengantar();
        list = model.getAll();
        header = new String[]{"NO SURAT","TANGGAL SURAT","KODE WARGA", "NO KTP", "NAMA LENGKAP", "JENIS KELAMIN","STATUS","ALAMAT", "KETERANGAN","KODE RT"};
       
        form.getTableSuratPengantar().setShowGrid(true);
        form.getTableSuratPengantar().setShowVerticalLines(true);
        form.getTableSuratPengantar().setGridColor(Color.blue);
      
    }
     public void tampilurutan() {         
            ModelSiap p = new ModelSiap();
            model.autonumber(p);
            String no_mor=String.valueOf(model.autonumber(p));
            String oke="00".substring(0, 2 - no_mor.length());
            form.getTxtNo_Surat().setText( "SP-"+  oke +""+no_mor );
        }
     
    
     public void isiTableSurat() {
        list = model.getAll();
        //Script agar jtable tidak bisa di edit
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, header) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return true;
            }
        };

        Object[] data = new Object[header.length];
        for (ModelSiap B : list) {
            data[0] = B.getNo_surat();
            data[1] = B.getTanggal_surat();
            data[2] = B.getKode_warga();
            data[3] = B.getNo_ktp();
            data[4] = B.getNama_warga();
            data[5] = B.getJenis_kelamin();
            data[6] = B.getStatus_keluarga();
            data[7] = B.getAlamat_warga();
            data[8] = B.getKeterangan();
            data[9] = B.getTanggal_surat();
           
            tblModel.addRow(data);
        }
        form.getTableSuratPengantar().setModel(tblModel);
        int c = tblModel.getRowCount();
        form.getTxtJumlahSurat().setText("Jumlah Data Surat : "+c);
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
        
      public void awal(){
         form.getTxtNo_Surat().setEnabled(false);
         form.getTxtNoKtp().setEnabled(false);
         form.getTxtNamaLengkap().setEnabled(false);
         form.getTxtKodeRt().setEnabled(false);
         form.getTxtTanggalSurat().setEnabled(false);
         form.getTxtKeterangan().setEnabled(false);
         form.getTxtKodeWarga().setEnabled(false);
      
      }
      
      
       public void kondisitambah(){
         form.getTxtNo_Surat().setEnabled(true);
         form.getTxtNoKtp().setEnabled(true);
         form.getTxtKodeWarga().setEnabled(true);
         form.getTxtNamaLengkap().setEnabled(true);
         form.getTxtKodeRt().setEnabled(true);
         form.getTxtKeterangan().setEnabled(true);
         form.getTxtTanggalSurat().setEnabled(true);
         form.getTxtKeterangan().setEnabled(true);
         
        form.getSimpan().setEnabled(true);
       form.getUbah().setEnabled(false);
       form.getHapus().setEnabled(false); 
    
         
         tampilurutan(); 
     }
     
     public void reset() {      
        form.getTxtNo_Surat().setText("");
        form.getTxtNamaLengkap().setText("");
         form.getTxtKodeWarga().setText("");
        form.getTxtNoKtp().setText("");
        form.getTxtKeterangan().setText("");     
        form.getTxtKodeRt().setSelectedItem("--Pilih Kode RT--");   
         form.getTxtTanggalSurat().setDate(null);   
    }
       
       public void kondisiubahdanhapus(){
         form.getTxtNo_Surat().setEnabled(false);
         form.getTxtNoKtp().setEnabled(true);
         form.getTxtNamaLengkap().setEnabled(true);
         form.getTxtKodeWarga().setEnabled(true);
         form.getTxtKeterangan().setEnabled(true);
         form.getTxtTanggalSurat().setEnabled(true);
         form.getTxtKodeRt().setEnabled(false);

       form.getSimpan().setEnabled(false);
       form.getUbah().setEnabled(true);
       form.getHapus().setEnabled(true); 
     }
   
     public void isiFieldsurat(int row) {
        form.getTxtNo_Surat().setText(String.valueOf(list.get(row).getNo_surat()));
        form.getTxtNoKtp().setText(list.get(row).getNo_ktp());
        form.getTxtKodeWarga().setText(list.get(row).getKode_warga());
        form.getTxtNamaLengkap().setText(list.get(row).getNama_warga());
        form.getTxtKodeRt().setSelectedItem(list.get(row).getKode_rt());
        form.getTxtKeterangan().setText(list.get(row).getKeterangan());
        form.getTxtTanggalSurat().setDate(list.get(row).getTanggal_surat());
       
        kondisiubahdanhapus();

    }
      public void insertsurat() {
        ModelSiap B = new ModelSiap();
        
        B.setNo_surat(form.getTxtNo_Surat().getText());
        B.setKode_warga(form.getTxtKodeWarga().getText());
        B.setKode_rt(form.getTxtKodeRt().getSelectedItem().toString());
        B.setKeterangan(form.getTxtKeterangan().getText());
        B.setTanggal_surat(form.getTxtTanggalSurat().getDate());
       
      
        model.insert(B);
 
      }
      

       
      public void updatesurat() {
         ModelSiap B = new ModelSiap();
        
        B.setNo_surat(form.getTxtNo_Surat().getText());
        B.setKode_warga(form.getTxtKodeWarga().getText());
        B.setKode_rt(form.getTxtKodeRt().getSelectedItem().toString());
        B.setKeterangan(form.getTxtKeterangan().getText());
        B.setTanggal_surat(form.getTxtTanggalSurat().getDate());
      
    
        model.update(B);
      }
      
       public void deletesurat() {
          
        if (!form.getTxtNo_Surat().getText().trim().isEmpty()) {
            String id = (form.getTxtNo_Surat().getText());
            model.delete(id);
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }
       
       public void cetakSuratPengantar(){
            try {
               
           String fillname = ("src/Laporan/FormulirSuratPengantar.jasper");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/siwarga014","root","");
             
            HashMap hash = new HashMap();
            //Mengambil parameter dari ireport
                  hash.put("no_surat_pengantar",(form.getTxtNo_Surat().getText()));
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
