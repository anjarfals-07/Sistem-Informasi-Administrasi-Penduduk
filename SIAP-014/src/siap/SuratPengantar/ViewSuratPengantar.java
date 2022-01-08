/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siap.SuratPengantar;

import chumbucket.plankton.widget.layar.Layar;
import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import siap.Koneksi.Koneksi;
import siap.ListWarga.ViewListWarga;
import siap.Meninggal.ViewDataMeninggal;
import siap.Pindah.ViewDataPindah;
import siap.SessionLogin.UserLogin;

/**
 *
 * @author Anjar
 */
public class ViewSuratPengantar extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewSuratPengantar
     */
     private Connection conn = new Koneksi().connection();
    ControllerSuratPengantar control;
    public ViewSuratPengantar() {
        initComponents();
        control = new ControllerSuratPengantar(this);
        control.isiTableSurat();
        control.awal();

        String KD = UserLogin.getKodeLogin();
        lb_id_admin.setText(KD);
        
        String NM = UserLogin.getNamaLogin();
        lb_nama_admin.setText(NM);
        control.isicombort();
    }
 private void toExcel(JTable table, File file) {
        
        try {
            TableModel model = tbsurat.getModel();
            FileWriter excel = new FileWriter(file);
            for (int i = 0; i < model.getColumnCount(); i++) {
                excel.write(model.getColumnName(i)+"\t");
            }
            excel.write("\n");
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    excel.write(model.getValueAt(i, j).toString()+"\t");
                    
                }
                excel.write("\n");
            }
            excel.close();
        } catch (IOException e) {
            System.out.println(e);
        }
      }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel12 = new javax.swing.JPanel();
        lb_id_admin = new javax.swing.JLabel();
        lb_nama_admin = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbsurat = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        txtjumlahsurat = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtsurat = new javax.swing.JTextField();
        txtwarga = new javax.swing.JTextField();
        txtktp = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtketerangan = new javax.swing.JTextArea();
        bsimpan = new javax.swing.JButton();
        bubah = new javax.swing.JButton();
        bhapus1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        txtnama = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbrt = new javax.swing.JComboBox<>();
        jdtgl = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();

        jPanel12.setBackground(new java.awt.Color(255, 0, 0));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lb_id_admin.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lb_id_admin.setForeground(new java.awt.Color(255, 255, 255));
        lb_id_admin.setText("ID ADMIN");

        lb_nama_admin.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lb_nama_admin.setForeground(new java.awt.Color(255, 255, 255));
        lb_nama_admin.setText("NAMA ADMIN");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_nama_admin, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(lb_id_admin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lb_id_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_nama_admin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-envelope-25.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new java.awt.CardLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tbsurat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbsurat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbsuratMouseClicked(evt);
            }
        });
        tbsurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbsuratKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbsurat);

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-new-25.png"))); // NOI18N
        jButton9.setText("TAMBAH DATA");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        bhapus.setBackground(new java.awt.Color(255, 255, 255));
        bhapus.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-remove-25.png"))); // NOI18N
        bhapus.setText("Hapus ");
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });

        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcariKeyReleased(evt);
            }
        });

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-search-25.png"))); // NOI18N

        jButton22.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-print-25.png"))); // NOI18N
        jButton22.setText("Cetak Surat Pengantar");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-microsoft-excel-25.png"))); // NOI18N
        jButton23.setText("Export Excel");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        txtjumlahsurat.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtjumlahsurat.setText("jLabel7");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addGap(37, 37, 37))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(bhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtjumlahsurat)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel14)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bhapus)
                        .addComponent(jButton22)
                        .addComponent(jButton23))
                    .addComponent(txtjumlahsurat))
                .addGap(28, 28, 28))
        );

        jPanel2.add(jPanel3, "card2");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-back-to-25.png"))); // NOI18N
        jButton3.setText("KEMBALI");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 117, 35));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Form Input", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("Kode RT");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("No. Surat");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setText("Warga Kode");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setText("No. KTP");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setText("Keperluan");

        txtsurat.setEditable(false);
        txtsurat.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        txtwarga.setEditable(false);
        txtwarga.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        txtktp.setEditable(false);
        txtktp.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        txtketerangan.setColumns(20);
        txtketerangan.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtketerangan.setRows(4);
        jScrollPane2.setViewportView(txtketerangan);

        bsimpan.setBackground(new java.awt.Color(255, 255, 255));
        bsimpan.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        bsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-save-close-25.png"))); // NOI18N
        bsimpan.setText("SIMPAN");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });

        bubah.setBackground(new java.awt.Color(255, 255, 255));
        bubah.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        bubah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-edit-property-25.png"))); // NOI18N
        bubah.setText("UBAH");
        bubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bubahActionPerformed(evt);
            }
        });

        bhapus1.setBackground(new java.awt.Color(255, 255, 255));
        bhapus1.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        bhapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-remove-25.png"))); // NOI18N
        bhapus1.setText("HAPUS");
        bhapus1.setPreferredSize(new java.awt.Dimension(65, 28));
        bhapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapus1ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-cancel-25.png"))); // NOI18N
        jButton7.setText("CANCEL");
        jButton7.setPreferredSize(new java.awt.Dimension(33, 26));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-search-25.png"))); // NOI18N
        jButton8.setPreferredSize(new java.awt.Dimension(27, 26));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        txtnama.setEditable(false);
        txtnama.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel9.setText("Nama Lengkap");

        cbrt.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cbrt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbrtItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setText("Tanggal Surat");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel9)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbrt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdtgl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtsurat)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(txtwarga)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtktp)
                            .addComponent(txtnama)
                            .addComponent(jScrollPane2)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 251, Short.MAX_VALUE)
                        .addComponent(bsimpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bubah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bhapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdtgl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbrt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsurat, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtwarga, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtktp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnama)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bubah, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bhapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 700, -1));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-new-25.png"))); // NOI18N
        jButton2.setText("Tambah Data Surat");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, 34));

        jPanel2.add(jPanel4, "card3");

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 62, 810, 440));

        jPanel6.setBackground(new java.awt.Color(255, 0, 0));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Data Surat Pengantar");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 310, 50));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 190));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbsuratMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbsuratMouseClicked
        // TODO add your handling code here:
        control.isiFieldsurat(tbsurat.getSelectedRow());
    }//GEN-LAST:event_tbsuratMouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        Layar.pindahPanel(jPanel2, "card3");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        // TODO add your handling code here:
        control.deletesurat();
        control.isiTableSurat();
        control.reset();
    }//GEN-LAST:event_bhapusActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Layar.pindahPanel(jPanel2, "card2");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased
        // TODO add your handling code here:
        TableRowSorter<TableModel>sorter = new TableRowSorter<TableModel>(((DefaultTableModel)tbsurat.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter(txtcari.getText().toString()));
        tbsurat.setRowSorter(sorter);
    }//GEN-LAST:event_txtcariKeyReleased

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        // TODO add your handling code here:
        if (jdtgl.getDate()==null|cbrt.equals("--Pilih Kode RT--")|txtktp.equals("")|txtnama.equals("")|txtsurat.equals("")
            |txtketerangan.equals("")|txtwarga.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Mohon Lengkapi Data ..!");
        }else{
            control.insertsurat();
            control.isiTableSurat();
            control.reset();
        }

    }//GEN-LAST:event_bsimpanActionPerformed

    private void bubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bubahActionPerformed
        // TODO add your handling code here:
        control.updatesurat();
        control.isiTableSurat();
        control.reset();
    }//GEN-LAST:event_bubahActionPerformed

    private void bhapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapus1ActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_bhapus1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        control.reset();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        ViewListWarga list = new ViewListWarga(new ViewDataMeninggal(), this);
        this.getParent().add(list);
        list.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void cbrtItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbrtItemStateChanged
        // TODO add your handling code here:
        if(getTxtKodeRt().getSelectedIndex()>0){
            control.tampilnort();
        }
    }//GEN-LAST:event_cbrtItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        control.kondisitambah();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        int option = fc.showSaveDialog(ViewSuratPengantar.this);
        if (option== JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getName();
            String path = fc.getSelectedFile().getParentFile().getPath();
            int len = filename.length();
            String ext="";
            String file = "";
            if (len > 4) {
                ext = filename.substring(len-4, len);
            }
            if (ext.equals(".xls")) {
                file = path + "\\" + filename+ "";

            }else{
                file = path + "\\" + filename+ ".xls";
            }
            toExcel(tbsurat, new File(file));
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        control.cetakSuratPengantar();
    }//GEN-LAST:event_jButton22ActionPerformed

    private void tbsuratKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbsuratKeyReleased
        // TODO add your handling code here:
           int row = tbsurat.getSelectedRow();
   if (evt.getKeyCode()== KeyEvent.VK_ENTER) {
     String kode = (String)tbsurat.getValueAt(row,2);
     txtwarga.setText(kode);
     String keterangan = (String)tbsurat.getValueAt(row,8);
     txtketerangan.setText(keterangan);
     Date tanggal = (Date)tbsurat.getValueAt(row,9);
     jdtgl.setDate(tanggal);
     String no_surat = (String)tbsurat.getValueAt(row,0);
     txtsurat.setText(no_surat);
     
   
        control.updatesurat();
        control.isiTableSurat();
        control.reset();
         }
    
    }//GEN-LAST:event_tbsuratKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bhapus1;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton bubah;
    private javax.swing.JComboBox<String> cbrt;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdtgl;
    private javax.swing.JLabel lb_id_admin;
    private javax.swing.JLabel lb_nama_admin;
    private javax.swing.JTable tbsurat;
    private javax.swing.JTextField txtcari;
    private javax.swing.JLabel txtjumlahsurat;
    private javax.swing.JTextArea txtketerangan;
    private javax.swing.JTextField txtktp;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtsurat;
    private javax.swing.JTextField txtwarga;
    // End of variables declaration//GEN-END:variables
public JLabel getTxtNamaErte(){
    return lb_nama_admin;
}
    public JTextField getTxtNo_Surat(){
    return txtsurat;
}
public JComboBox getTxtKodeRt(){
    return cbrt;
}
public JTextField getTxtKodeWarga(){
    return txtwarga;
}
public JTextField getTxtNoKtp(){
    return txtktp;
}
public JTextField getTxtNamaLengkap(){
    return txtnama;
}
public JLabel getTxtJumlahSurat(){
    return txtjumlahsurat;
}
public JTextField getTxtCari(){
    return txtcari;
}
public JDateChooser getTxtTanggalSurat(){
    return jdtgl;
}
public JTextArea getTxtKeterangan(){
    return txtketerangan;
}
public JTable getTableSuratPengantar(){
    return tbsurat;
}
public JButton getSimpan(){
    return bsimpan;
}
public JButton getUbah(){
    return bubah;
}
public JButton getHapus(){
    return bhapus;
}
}
