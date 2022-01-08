/*
 * To change this license headerp, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siap.Pindah;



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
public class ControllerTambahPindah {

    TambahWargaPindah form;
    ModelPindah model;
   
    List<ModelSiap> lisp; //deklarasi method "List" yang sudah dibuat pada interface MODEL_DA
    String[] headerp; //deklarasi variable untuk membuat judul kolom pada objek JTable
    List<ModelSiap> lisd; //deklarasi method "List" yang sudah dibuat pada interface MODEL_DA
    String[] headerd; //deklarasi variable untuk membuat judul kolom pada objek JTable
    
    public ControllerTambahPindah(TambahWargaPindah form) {
        this.form = form;
        model = new ModelPindah();
      
        lisp = model.getAllWarga();
        headerp = new String[]{"NO KK", "NO KTP", "NAMA LENGKAP", "JENIS KELAMIN","STATUS WARGA","FOTO"};
        lisd = model.getDetailPindah();
      
        headerd = new String[]{"KODE", "NO KK", "NO KTP", "NAMA LENGKAP", "JENIS KELAMIN","STATUS WARGA","STATUS","FOTO","JUMLAH"};
        form.getTableWarga().setShowGrid(true);
        form.getTableWarga().setShowVerticalLines(true);
        form.getTableWarga().setGridColor(Color.blue);
    }

     public void isiTableWarga() {
        lisp = model.getAllWarga();
        //Script agar jtable tidak bisa di edit
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, headerp) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        Object[] data = new Object[headerp.length];
        for (ModelSiap B : lisp) {
            data[0] = B.getNo_kk();
            data[1] = B.getNo_ktp();
            data[2] = B.getNama_warga();
            data[3] = B.getJenis_kelamin();
            data[4] = B.getStatus_warga();
            data[5] = B.getFoto();
            tblModel.addRow(data);
        }
        form.getTableWarga().setModel(tblModel);
         int a = tblModel.getRowCount();
//        form.getTxtJumlahD().setText(""+a);
    }
      
      public void awalpindah(){
         form.getTxtKodeDetail().setEnabled(false);
         form.getTxtJenisKelaminDetail().setEnabled(false);
         form.getTxtKtpDetail().setEnabled(false);
         form.getTxtKKDetail().setEnabled(false);
         form.getTxtNamaDetail().setEnabled(false);
         form.getTxtStatusPindah().setEnabled(false);
         form.getTxtStatusWargaDetail().setEnabled(false);
         form.getTxtFoto().setEnabled(false);
      }
     
       public void kondisitambahpindah(){
        form.getTxtKodeDetail().setEnabled(false);
         form.getTxtJenisKelaminDetail().setEnabled(false);
         form.getTxtKtpDetail().setEnabled(true);
         form.getTxtKKDetail().setEnabled(false);
         form.getTxtNamaDetail().setEnabled(false);
         form.getTxtStatusPindah().setEnabled(false);
         form.getTxtStatusWargaDetail().setEnabled(false);
         form.getTxtFoto().setEnabled(false);
     }
     
     public void reset() {      
     form.getTxtNamaDetail().setText("");
     form.getTxtKtpDetail().setText("");
     form.getTxtJenisKelaminDetail().setText("");     
     form.getTxtStatusWargaDetail().setText(""); 
     form.getTxtFoto().setText("");  
    }
   
     public void isiFieldwarga(int row) {
       //form.getTxtKodeDetail().setText(String.valueOf(lisp.get(row).getKode_pindah()));
        form.getTxtKtpDetail().setText(lisp.get(row).getNo_ktp());
       //form.getTxtKKDetail().setText(lisp.get(row).getNo_kk());
        form.getTxtNamaDetail().setText(lisp.get(row).getNama_warga());
        form.getTxtJenisKelaminDetail().setText(lisp.get(row).getJenis_kelamin());
       //form.getTxtStatusPindah().setText(lisp.get(row).getStatus());
        form.getTxtStatusWargaDetail().setText(lisp.get(row).getStatus_warga());
        form.getTxtFoto().setText(lisp.get(row).getFoto());
      // form.getTxtJumlahDetail().setText(String.valueOf(lisp.get(row).getJumlah()));
    }
      public void insertdetailpindah() {
        ModelSiap B = new ModelSiap();
        
        B.setKode_pindah(form.getTxtKodeDetail().getText());
        B.setNo_ktp(form.getTxtKtpDetail().getText());
        B.setNo_kk(form.getTxtKKDetail().getText());
        B.setNama_warga(form.getTxtNamaDetail().getText());
        B.setStatus(form.getTxtStatusPindah().getText());
        B.setStatus_warga(form.getTxtStatusWargaDetail().getText());
        B.setFoto(form.getTxtFoto().getText());
        B.setJenis_kelamin(form.getTxtJenisKelaminDetail().getText());
        B.setJumlah(Integer.parseInt(form.getTxtJumlahDetail().getText()));
         
       model.insertdetail(B);
       model.update_jumlahpindah(B);
      }
      
       public void deletestatistikpindah() {
        ModelSiap B = new ModelSiap();
        B.setStatus(form.getTxtStatusPindah().getText());
        B.setJumlah(Integer.parseInt(form.getTxtJumlahDetail().getText()));
        model.delete_statistikpindah(B);
       
      }
       public void deletestatistikwarga() {
        ModelSiap B = new ModelSiap();
        B.setStatus_warga(form.getTxtStatusWargaDetail().getText());
        B.setJumlah(Integer.parseInt(form.getTxtJumlahDetail().getText()));
        model.delete_statistikwarga(B);
       
      }
       
       public void deletedetailpindah() {
          
        if (!form.getTxtKtpDetail().getText().toString().isEmpty()) {
            String id = (form.getTxtKtpDetail().getText().toString());
            model.deletedetail(id);
            
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }
       
        public void deletewarga() {
          
        if (!form.getTxtKtpDetail().getText().toString().isEmpty()) {
            String id = (form.getTxtKtpDetail().getText().toString());
            model.deletewarga(id);
            
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }
       
        public void isiTablePindahDetail() {
        lisd = model.getDetailPindah();
        //Script agar jtable tidak bisa di edit
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, headerd) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        Object[] data = new Object[headerd.length];
        for (ModelSiap B : lisd) {
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
        form.getTableDetailWargaPindah().setModel(tblModel);
         int a = tblModel.getRowCount();
//        form.getTxtJumlahD().setText(""+a);
    }
      
       public void isiFieldpindahdetail(int row) {
        form.getTxtKodeDetail().setText(String.valueOf(lisd.get(row).getKode_pindah()));
        form.getTxtKtpDetail().setText(lisd.get(row).getNo_ktp());
        form.getTxtKKDetail().setText(lisd.get(row).getNo_kk());
        form.getTxtNamaDetail().setText(lisd.get(row).getNama_warga());
        form.getTxtJenisKelaminDetail().setText(lisd.get(row).getJenis_kelamin());
        form.getTxtStatusPindah().setText(lisd.get(row).getStatus());
        form.getTxtStatusWargaDetail().setText(lisd.get(row).getStatus_warga());
        form.getTxtFoto().setText(lisd.get(row).getFoto());
        form.getTxtJumlahDetail().setText(String.valueOf(lisd.get(row).getJumlah()));
    }
}
