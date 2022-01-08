/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siap.DataKK;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import siap.ModelSiap.ModelSiap;

/**
 *
 * @author Anjar
 */
public class ControllerKartuKeluarga {
    ViewKartuKeluarga form;
    ModelKartuKeluarga model;
    List<ModelSiap> listkk;//untuk list kk
    List<ModelSiap> listdetailkk;//untuk list detailkk
  
    String[] headerkk;
    String[] headerdetailkk;
    public ControllerKartuKeluarga(ViewKartuKeluarga form) {
        this.form = form;
        model = new ModelKartuKeluarga();
        listkk = model.getAllKartuKeluarga();
        listdetailkk = model.getAllDetailKartuKeluarga(form.getTxtCariDetail().getText());
        headerkk = new String[]{"NO", "NO KK"};
        headerdetailkk = new String[]{"KODE", "NO.KK", "NO.KTP", "NAMA LENGKAP", "JENIS KELAMIN","STATUS","STATUS KELUARGA","FILE PHOTO"};
        
    }
    
    
        
        public void isiTableKK() {
        listkk = model.getAllKartuKeluarga();
        //Script agar jtable tidak bisa di edit
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, headerkk) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        Object[] data = new Object[headerkk.length];
        for (ModelSiap B : listkk) {
            data[0] = B.getNo();
            data[1] = B.getNo_kk();
         
            tblModel.addRow(data);
        }
        form.getTableKartuKeluarga().setModel(tblModel);
        int c = tblModel.getRowCount();
        form.getTxtJumlahKK().setText("Jumlah Data Kartu Keluarga : "+c);
    }
      public void isiTableDetailKK() {
        listdetailkk = model.getAllDetailKartuKeluarga(form.getTxtCariDetail().getText());
        //Script agar jtable tidak bisa di edit
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, headerdetailkk) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        Object[] data = new Object[headerdetailkk.length];
        for (ModelSiap B : listdetailkk) {
            data[0] = B.getKode_warga();
            data[1] = B.getNo_kk();
            data[2] = B.getNo_ktp();
            data[3] = B.getNama_warga();
            data[4] = B.getJenis_kelamin();
            data[5] = B.getStatus_warga();
            data[6] = B.getStatus_keluarga();
            data[7] = B.getFoto();
         
            tblModel.addRow(data);
        }
        form.getTableDetailKK().setModel(tblModel);
        int c = tblModel.getRowCount();
        //form.getTxtJumlahDataAsli().setText(""+c);
    }
      
      
     
        public void isiFieldDetailKK(int row) {
       form.getTxtNamaDetail().setText (listdetailkk.get(row).getNama_warga());
       form.getTxtKelaminDetail().setText (listdetailkk.get(row).getJenis_kelamin());
       form.getTxtStatusDetail().setText (listdetailkk.get(row).getStatus_warga());
       form.getTxtStatusKeluargaDetail().setText (listdetailkk.get(row).getStatus_keluarga());
       form.getTxtKodeDetail().setText (listdetailkk.get(row).getKode_warga());
       form.getTxtKKDetail().setText (listdetailkk.get(row).getNo_kk());
       form.getTxtKtpDetail().setText (listdetailkk.get(row).getNo_ktp());

    }
        
     public void isiFieldKartuKeluarga(int row) {
    form.getTxtKartuKK().setText (listkk.get(row).getNo_kk());
        form.getTxtCariDetail().setText(listkk.get(row).getNo_kk());
       form.getTxtNo().setText (listkk.get(row).getNo());
    }
     
     public void update() {
         ModelSiap B = new ModelSiap();
        
       B.setNo_kk(form.getTxtKartuKK().getText());
        B.setNo(form.getTxtNo().getText());
     
        model.updateKK(B);
      }

}
