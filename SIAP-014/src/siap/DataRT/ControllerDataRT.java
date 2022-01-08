/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siap.DataRT;

import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import siap.ModelSiap.ModelSiap;

/**
 *
 * @author ASUS
 */
public class ControllerDataRT {
    ViewDataRT form;
    ModelDataRt model;
    List<ModelSiap> list; //deklarasi method "List" yang sudah dibuat pada interface MODEL_DAO
    String[] header; //deklarasi variable untuk membuat judul kolom pada objek JTable

    public ControllerDataRT(ViewDataRT form) {
        this.form = form;
        model = new ModelDataRt();
        list = model.getAll();
        header = new String[]{"NO","KODE RT", "KETUA", "WAKIL", "SEKRETARIS","BENDAHARA","STATUS"};
       
        form.getTableRt().setShowGrid(true);
        form.getTableRt().setShowVerticalLines(true);
        form.getTableRt().setGridColor(Color.blue);
      
    }
    
    
     public void isiTableRT() {
        list = model.getAll();
        //Script agar jtable tidak bisa di edit
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, header) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return true;
            }
        };

        Object[] data = new Object[header.length];
        for (ModelSiap B : list) {
            data[0] = B.getNo();
            data[1] = B.getKode_rt();
            data[2] = B.getKetua();
            data[3] = B.getWakil();
            data[4] = B.getSekretaris();
            data[5] = B.getBendahara();
            data[6] = B.getStatus_rt();
             
            tblModel.addRow(data);
        }
        form.getTableRt().setModel(tblModel);
    }
     
        
      public void awal(){
         form.getTxtKodeRt().setEnabled(false);
         form.getTxtKetuaRt().setEnabled(false);
         form.getTxtWakil().setEnabled(false);
         form.getTxtSekretaris().setEnabled(false);
         form.getTxtBendahara().setEnabled(false);
         form.getTxtStatusRT().setEnabled(false);
         form.getTxtUsername().setEnabled(false);
         form.getTxtPassword().setEnabled(false);
      }
      
       
       public void kondisitambah(){
         form.getTxtKodeRt().setEnabled(true);
         form.getTxtKetuaRt().setEnabled(true);
         form.getTxtWakil().setEnabled(true);
         form.getTxtSekretaris().setEnabled(true);
         form.getTxtBendahara().setEnabled(true);
         form.getTxtStatusRT().setEnabled(true);
         form.getTxtUsername().setEnabled(true);
         form.getTxtPassword().setEnabled(true);
           
        form.getSimpan().setEnabled(true);
       form.getUbah().setEnabled(false);
       form.getHapus().setEnabled(false); 
    
     }
     
     public void reset() {      
        form.getTxtKetuaRt().setText("");
        form.getTxtKodeRt().setText("");
        form.getTxtWakil().setText("");
        form.getTxtSekretaris().setText("");     
        form.getTxtBendahara().setText("");   
        form.getTxtStatusRT().setSelectedItem(""); 
        form.getTxtUsername().setText("");  
        form.getTxtPassword().setText(""); 
    }
       
     
       public void kondisiubahdanhapus(){
         form.getTxtKodeRt().setEnabled(false);
         form.getTxtKetuaRt().setEnabled(true);
         form.getTxtWakil().setEnabled(true);
         form.getTxtSekretaris().setEnabled(true);
         form.getTxtBendahara().setEnabled(true);
         form.getTxtStatusRT().setEnabled(true);
         form.getTxtUsername().setEnabled(true);
         form.getTxtPassword().setEnabled(true);

       form.getSimpan().setEnabled(false);
       form.getUbah().setEnabled(true);
       form.getHapus().setEnabled(true); 
       
      
     }
   
     public void isiField(int row) {
          form.getTxtNo().setText(String.valueOf(list.get(row).getNo()));
        form.getTxtKodeRt().setText(String.valueOf(list.get(row).getKode_rt()));
        form.getTxtKetuaRt().setText(list.get(row).getKetua());
        form.getTxtWakil().setText(list.get(row).getWakil());
        form.getTxtSekretaris().setText(list.get(row).getSekretaris());
        form.getTxtBendahara().setText(list.get(row).getBendahara());
        form.getTxtStatusRT().setSelectedItem(list.get(row).getStatus_rt());
        form.getTxtUsername().setText(list.get(row).getUsername());
        form.getTxtPassword().setText(list.get(row).getPassword());
        
        kondisiubahdanhapus();
   
    }
      public void insert() {
        ModelSiap B = new ModelSiap();
        
        B.setKode_rt(form.getTxtKodeRt().getText());
        B.setKetua(form.getTxtKetuaRt().getText());
        B.setWakil(form.getTxtWakil().getText());
        B.setSekretaris(form.getTxtSekretaris().getText());
        B.setBendahara(form.getTxtBendahara().getText());
        B.setUsername(form.getTxtUsername().getText());
        B.setPassword(form.getTxtPassword().getText());
        B.setStatus_rt(form.getTxtStatusRT().getSelectedItem().toString());
        model.insert(B);
 
      }
      
      public void update() {
         ModelSiap B = new ModelSiap();
        
       B.setKode_rt(form.getTxtKodeRt().getText());
        B.setKetua(form.getTxtKetuaRt().getText());
        B.setWakil(form.getTxtWakil().getText());
        B.setSekretaris(form.getTxtSekretaris().getText());
        B.setBendahara(form.getTxtBendahara().getText());
        B.setStatus_rt(form.getTxtStatusRT().getSelectedItem().toString());
        B.setUsername(form.getTxtUsername().getText());
        B.setPassword(form.getTxtPassword().getText());
        
        model.update(B);
      }
      
     
     
}

