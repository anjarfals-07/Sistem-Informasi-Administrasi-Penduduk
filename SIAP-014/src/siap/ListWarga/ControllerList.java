/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siap.ListWarga;



import java.awt.Color;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import siap.ModelSiap.ModelSiap;
import siap.Warga.ModelWarga;



/**
 *
 * @author ASUS
 */
public class ControllerList {
    ViewListWarga form;
   ModelWarga model;
   List<ModelSiap> list; //deklarasi method "List" yang sudah dibuat pada interface MODEL_DAO
   
    String[] header; //deklarasi variable untuk membuat judul kolom pada objek JTable
    String[] headerd;
    public ControllerList(ViewListWarga form) {
        this.form = form;
        model = new ModelWarga();
        list = model.getAllWarga();
      
        header = new String[]{"KODE","NO KTP", "NO KK", "NAMA LENGKAP", "JENIS KELAMIN","ALAMAT WARGA","STATUS"};
       
        
        form.getTableList().setShowGrid(true);
        form.getTableList().setShowVerticalLines(true);
        form.getTableList().setGridColor(Color.blue);
    }

          public void isiTablewarga() {
        list = model.getAllWarga();
        //Script agar jtable tidak bisa di edit
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, header) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        Object[] data = new Object[header.length];
        for (ModelSiap B : list) {
            data[0] = B.getKode_warga();
            data[1] = B.getNo_ktp();
            data[2] = B.getNo_kk();
            data[3] = B.getNama_warga();
            data[4] = B.getJenis_kelamin();
            data[5] = B.getAlamat_warga();
            data[6] = B.getStatus_warga();
            tblModel.addRow(data);
        }
        form.getTableList().setModel(tblModel);
    }
          
          
}
    

