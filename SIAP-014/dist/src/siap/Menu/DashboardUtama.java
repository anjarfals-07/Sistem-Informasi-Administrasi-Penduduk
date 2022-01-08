/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siap.Menu;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import siap.About.About;
import siap.BackupDB.BackupRestore;
import siap.DataKK.ViewKartuKeluarga;
import siap.DataRT.ViewDataRT;
import siap.Koneksi.Koneksi;
import siap.Login.LoginAplikasi;
import siap.Meninggal.ViewDataMeninggal;
import siap.Pindah.ViewDataPindah;
import siap.SendEmail.SendEmail;
import siap.SessionLogin.UserLogin;
import siap.SuratPengantar.ViewSuratPengantar;
import siap.Warga.ViewWarga;

/**
 *
 * @author Anjar
 */
public class DashboardUtama extends javax.swing.JFrame implements Runnable {

         Thread t;
        boolean kanan=true;
        boolean kiri=false;
        boolean jalan=true;
        int x,y;
        int a,b;
     private Connection conn = new Koneksi().connection();  
    public DashboardUtama() {
         ImageIcon icon = new ImageIcon(getClass().getResource("/Gambar/LOGOO.png"));
        setIconImage(icon.getImage());
        initComponents();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        initListener();
         
        String KD = UserLogin.getKodeLogin();
        lb_id_admin.setText(KD);
        
        String NM = UserLogin.getNamaLogin();
        lb_nama_admin.setText(NM);
          jPanel3.setVisible(false);
          jPanel3.setEnabled(false);

         tampilstatistikwarga();
                //pemanggilan text bergerak kekanan dan kira
        x = text_gerak.getX();
        y= text_gerak.getY();
        t= new Thread(this);
        t.start();//pemanggilan text gerak
    }
 public void getBackgroundColorSide(){
        Color d= jColorChooser1.showDialog(null,"Panel Side",getBackground());
        p_side.setBackground(d);
     //   Color e= jColorChooser1.showDialog(null, "Panel Menu",jPanel9.getBackground());
        jPanel3.setBackground(d);
      //  p_user.setBackground(d);
        pmenu.setBackground(d);
        menu1.setBackground(d);
        menu2.setBackground(d);
         
        p_top.setBackground(d);
        panel_menu.setBackground(d);
        jScrollPane1.setBackground(d);
    }
         public void getBackgroundColor(){
        Color c= jColorChooser1.showDialog(null,"Background Color",getBackground());
        DP.setBackground(c);
       tampilMenuHome.setBackground(c);   
        pstat.setBackground(c);     
    }

         public void getTextColor(){
         Color d= jColorChooser1.showDialog(null,"Title Text Color ",getForeground());
         jLabel37.setForeground(d);
         lbl_tgl.setForeground(d);
         lbl_jam.setForeground(d);
         lbversi.setForeground(d);
    }
         
    public void tampilstatistikwarga(){   
        try {
           String sql = "SELECT * FROM dt_statistik";
           JDBCCategoryDataset data = new JDBCCategoryDataset(Koneksi.connection(),sql);
           JFreeChart chart = ChartFactory.createBarChart3D("INDEKS WARGA RT014", "DAFTAR WARGA", "JUMLAH WARGA", data,PlotOrientation.VERTICAL,false,true,true);
           pstat.setLayout(new java.awt.BorderLayout());
           ChartPanel cp = new ChartPanel(chart);
            
           pstat.removeAll();
           pstat.repaint();
           pstat.revalidate();
           pstat.setSize(200, 300);
           pstat.add(cp, BorderLayout.CENTER);
           pstat.repaint();
           pstat.revalidate();
           cp.setVisible(true);
        } catch (Exception e) {
        }
    }
    
      @Override
    public void run() {
        getjam(); 
      while(true){
          if(jalan){
              if(x >= p_gerak.getWidth()-130){
                  kiri=true;
                  kanan=false;   
              }
               if(kiri){
                   x --;
                   text_gerak.setLocation(x,y); 
              }
               if(x<=5){
                   kanan=true;
                   kiri=false;
              }
               if(kanan){
                   x++;
                   text_gerak.setLocation(x,y);
              } 

       } try {
         Thread.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(DashboardUtama.class.getName()).log(Level.SEVERE,null,ex);
        }
      }
    }
     private void initListener(){
        this.addWindowListener(new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent e){
              exit();  
            }
});
        
    }
    
    public void exit(){
         int konfirmasi = JOptionPane.showConfirmDialog(
                this, "Konfirmasi Keluar Aplikasi",
                "Yakin Ingin Keluar Dari Program",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        
        if (konfirmasi== JOptionPane.YES_OPTION) {
            System.exit(0);
            
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

        jDialog1 = new javax.swing.JDialog();
        jColorChooser1 = new javax.swing.JColorChooser();
        p_top = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        lbversi = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        p_gerak = new javax.swing.JPanel();
        text_gerak = new javax.swing.JLabel();
        lb_nama_admin = new javax.swing.JLabel();
        lb_id_admin = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        p_side = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panel_menu = new javax.swing.JPanel();
        pmenu = new javax.swing.JPanel();
        b_warga = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        b_kk = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        b_rt = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        b_meninggal = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        b_pengantar = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        b_rt1 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        lbl_tgl = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_jam = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        menu1 = new javax.swing.JPanel();
        lbdata3 = new javax.swing.JLabel();
        menu2 = new javax.swing.JPanel();
        lbdata4 = new javax.swing.JLabel();
        tampilMenuHome = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        pstat = new javax.swing.JPanel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        DP = new javax.swing.JDesktopPane();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialog1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jColorChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialog1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jColorChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        p_top.setBackground(new java.awt.Color(0, 0, 255));
        p_top.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        p_top.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Sistem Informasi Administrasi Penduduk RT 014 RW 05 Duren Tiga");
        p_top.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 30));

        lbversi.setFont(new java.awt.Font("Lucida G", 3, 12)); // NOI18N
        lbversi.setForeground(new java.awt.Color(255, 255, 255));
        lbversi.setText(" Dashboard V .1 .0");
        p_top.add(lbversi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 120, 40));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/microsoft-logo-24.png"))); // NOI18N
        p_top.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, -1, -1));

        jPanel1.setBackground(new java.awt.Color(204, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        p_gerak.setBackground(new java.awt.Color(204, 0, 0));

        text_gerak.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        text_gerak.setForeground(new java.awt.Color(255, 255, 255));
        text_gerak.setText("Selamat Datang Di Aplikasi SIAP");

        javax.swing.GroupLayout p_gerakLayout = new javax.swing.GroupLayout(p_gerak);
        p_gerak.setLayout(p_gerakLayout);
        p_gerakLayout.setHorizontalGroup(
            p_gerakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_gerakLayout.createSequentialGroup()
                .addComponent(text_gerak, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 732, Short.MAX_VALUE))
        );
        p_gerakLayout.setVerticalGroup(
            p_gerakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(text_gerak, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p_gerak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p_gerak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        p_top.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 60));

        lb_nama_admin.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lb_nama_admin.setForeground(new java.awt.Color(255, 255, 255));
        lb_nama_admin.setText("NAMA ADMIN");
        lb_nama_admin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        p_top.add(lb_nama_admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 90, 150, -1));

        lb_id_admin.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lb_id_admin.setForeground(new java.awt.Color(255, 255, 255));
        lb_id_admin.setText("ID ADMIN");
        lb_id_admin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        p_top.add(lb_id_admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 70, 150, -1));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-administrator-male-25.png"))); // NOI18N
        p_top.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 70, 40, 40));

        getContentPane().add(p_top, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 1100, 120));

        p_side.setBackground(new java.awt.Color(0, 0, 255));
        p_side.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        p_side.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(255, 255, 255)));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel7MouseExited(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logout.png"))); // NOI18N
        jLabel8.setText("Exit");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel8MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel8MousePressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-color-wheel-25.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(23, 23, 23))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel9MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel9MousePressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-color-wheel-25.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel7MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel10MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel10MousePressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-about-16.png"))); // NOI18N
        jLabel4.setText("About");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel4MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel11MouseExited(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-exit-sign-16.png"))); // NOI18N
        jLabel5.setText("Logout");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel17MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel17MousePressed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-color-wheel-25.png"))); // NOI18N
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel18MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(21, 21, 21))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel12MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel12MousePressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/database.png"))); // NOI18N
        jLabel9.setText("Backup");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel9MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(30, 30, 30))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        p_side.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, 270, 150));

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(255, 255, 255)));

        panel_menu.setBackground(new java.awt.Color(0, 0, 255));
        panel_menu.setLayout(new java.awt.CardLayout());

        pmenu.setBackground(new java.awt.Color(0, 0, 255));

        b_warga.setBackground(new java.awt.Color(255, 255, 255));
        b_warga.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b_warga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b_wargaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b_wargaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                b_wargaMousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-user-groups-25.png"))); // NOI18N
        jLabel3.setText("Data Warga");

        javax.swing.GroupLayout b_wargaLayout = new javax.swing.GroupLayout(b_warga);
        b_warga.setLayout(b_wargaLayout);
        b_wargaLayout.setHorizontalGroup(
            b_wargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, b_wargaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        b_wargaLayout.setVerticalGroup(
            b_wargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(b_wargaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        b_kk.setBackground(new java.awt.Color(255, 255, 255));
        b_kk.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b_kk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b_kkMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b_kkMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                b_kkMousePressed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/kk (1).png"))); // NOI18N
        jLabel23.setText("Data Kartu Keluarga");

        javax.swing.GroupLayout b_kkLayout = new javax.swing.GroupLayout(b_kk);
        b_kk.setLayout(b_kkLayout);
        b_kkLayout.setHorizontalGroup(
            b_kkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, b_kkLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        b_kkLayout.setVerticalGroup(
            b_kkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(b_kkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        b_rt.setBackground(new java.awt.Color(255, 255, 255));
        b_rt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b_rt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b_rtMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b_rtMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                b_rtMousePressed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-administrator-male-25.png"))); // NOI18N
        jLabel25.setText("Data Ketua RT");

        javax.swing.GroupLayout b_rtLayout = new javax.swing.GroupLayout(b_rt);
        b_rt.setLayout(b_rtLayout);
        b_rtLayout.setHorizontalGroup(
            b_rtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(b_rtLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        b_rtLayout.setVerticalGroup(
            b_rtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, b_rtLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        b_meninggal.setBackground(new java.awt.Color(255, 255, 255));
        b_meninggal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b_meninggal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b_meninggalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b_meninggalMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                b_meninggalMousePressed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-death-25.png"))); // NOI18N
        jLabel24.setText("Data Warga Meninggal");

        javax.swing.GroupLayout b_meninggalLayout = new javax.swing.GroupLayout(b_meninggal);
        b_meninggal.setLayout(b_meninggalLayout);
        b_meninggalLayout.setHorizontalGroup(
            b_meninggalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, b_meninggalLayout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        b_meninggalLayout.setVerticalGroup(
            b_meninggalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, b_meninggalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        b_pengantar.setBackground(new java.awt.Color(255, 255, 255));
        b_pengantar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b_pengantar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b_pengantarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b_pengantarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                b_pengantarMousePressed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-envelope-25.png"))); // NOI18N
        jLabel31.setText("Data Surat Pengantar");

        javax.swing.GroupLayout b_pengantarLayout = new javax.swing.GroupLayout(b_pengantar);
        b_pengantar.setLayout(b_pengantarLayout);
        b_pengantarLayout.setHorizontalGroup(
            b_pengantarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, b_pengantarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        b_pengantarLayout.setVerticalGroup(
            b_pengantarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(b_pengantarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        b_rt1.setBackground(new java.awt.Color(255, 255, 255));
        b_rt1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b_rt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b_rt1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b_rt1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                b_rt1MousePressed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon-Pindah-25x25 (1) (1).png"))); // NOI18N
        jLabel26.setText("Data Warga Pindah");

        javax.swing.GroupLayout b_rt1Layout = new javax.swing.GroupLayout(b_rt1);
        b_rt1.setLayout(b_rt1Layout);
        b_rt1Layout.setHorizontalGroup(
            b_rt1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(b_rt1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        b_rt1Layout.setVerticalGroup(
            b_rt1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pmenuLayout = new javax.swing.GroupLayout(pmenu);
        pmenu.setLayout(pmenuLayout);
        pmenuLayout.setHorizontalGroup(
            pmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pmenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b_meninggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_warga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_pengantar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_kk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_rt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_rt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pmenuLayout.setVerticalGroup(
            pmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pmenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(b_warga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_kk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_meninggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_rt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_pengantar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_rt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_menu.add(pmenu, "card2");

        jScrollPane1.setViewportView(panel_menu);

        p_side.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 270, 260));

        jPanel4.setBackground(new java.awt.Color(204, 0, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/date.png"))); // NOI18N
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lbl_tgl.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbl_tgl.setForeground(new java.awt.Color(255, 255, 255));
        lbl_tgl.setText("TANGGAL");
        jPanel4.add(lbl_tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/full-time.png"))); // NOI18N
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jPanel13.setBackground(new java.awt.Color(204, 0, 0));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(255, 255, 255)));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-xbox-menu-25.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });
        jPanel13.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 30, 30));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-xbox-menu-25.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });
        jPanel13.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 30, 30));

        jPanel4.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 50, 40));

        lbl_jam.setBackground(new java.awt.Color(255, 255, 255));
        lbl_jam.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbl_jam.setForeground(new java.awt.Color(255, 255, 255));
        lbl_jam.setText("JAM");
        jPanel4.add(lbl_jam, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        p_side.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 60));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/dki.png"))); // NOI18N
        jLabel15.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(255, 255, 255)));
        jLabel15.setOpaque(true);
        p_side.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 270, 160));

        menu1.setBackground(new java.awt.Color(0, 0, 255));
        menu1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(255, 255, 255)));
        menu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menu1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu1MousePressed(evt);
            }
        });

        lbdata3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbdata3.setForeground(new java.awt.Color(255, 255, 255));
        lbdata3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbdata3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/graphic-design.png"))); // NOI18N
        lbdata3.setText("Home");
        lbdata3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbdata3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbdata3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbdata3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout menu1Layout = new javax.swing.GroupLayout(menu1);
        menu1.setLayout(menu1Layout);
        menu1Layout.setHorizontalGroup(
            menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lbdata3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        menu1Layout.setVerticalGroup(
            menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbdata3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        p_side.add(menu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 130, 40));

        menu2.setBackground(new java.awt.Color(0, 0, 255));
        menu2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(255, 255, 255)));
        menu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menu2MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu2MousePressed(evt);
            }
        });

        lbdata4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbdata4.setForeground(new java.awt.Color(255, 255, 255));
        lbdata4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbdata4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-gmail-25.png"))); // NOI18N
        lbdata4.setText("E-Mail");
        lbdata4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbdata4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbdata4MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbdata4MousePressed(evt);
            }
        });

        javax.swing.GroupLayout menu2Layout = new javax.swing.GroupLayout(menu2);
        menu2.setLayout(menu2Layout);
        menu2Layout.setHorizontalGroup(
            menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lbdata4)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        menu2Layout.setVerticalGroup(
            menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbdata4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        p_side.add(menu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 130, 40));

        getContentPane().add(p_side, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 710));

        tampilMenuHome.setBackground(new java.awt.Color(255, 255, 255));
        tampilMenuHome.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tampilMenuHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel92.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel92.setText("JAKARTA SELATAN");
        tampilMenuHome.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 1010, -1));

        pstat.setBackground(new java.awt.Color(255, 255, 255));
        pstat.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pstat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pstatFocusGained(evt);
            }
        });
        pstat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pstatKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pstatLayout = new javax.swing.GroupLayout(pstat);
        pstat.setLayout(pstatLayout);
        pstatLayout.setHorizontalGroup(
            pstatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 986, Short.MAX_VALUE)
        );
        pstatLayout.setVerticalGroup(
            pstatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 436, Short.MAX_VALUE)
        );

        tampilMenuHome.add(pstat, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 990, 440));

        jLabel97.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel97.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel97.setText("JUMLAH DATA WARGA RT 014 RW 05");
        tampilMenuHome.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1010, -1));

        jLabel98.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel98.setText("KEL. DUREN TIGA  KEC. PANCORAN");
        tampilMenuHome.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 1010, -1));

        getContentPane().add(tampilMenuHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(291, 120, 1100, 590));

        DP.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout DPLayout = new javax.swing.GroupLayout(DP);
        DP.setLayout(DPLayout);
        DPLayout.setHorizontalGroup(
            DPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1110, Short.MAX_VALUE)
        );
        DPLayout.setVerticalGroup(
            DPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );

        getContentPane().add(DP, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 1110, 590));

        setSize(new java.awt.Dimension(1387, 742));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseEntered
        // TODO add your handling code here:
        jLabel6.setForeground(Color.white);
        jPanel8.setBackground(new Color(0,204,255));
    }//GEN-LAST:event_jPanel8MouseEntered

    private void jPanel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseExited
        // TODO add your handling code here:
          jLabel6.setForeground(Color.black);
        jPanel8.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel8MouseExited

    private void jPanel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MousePressed
        // TODO add your handling code here:
               getBackgroundColor();
    }//GEN-LAST:event_jPanel8MousePressed

    private void jPanel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseEntered
        // TODO add your handling code here:
        jLabel18.setForeground(Color.white);
        jPanel17.setBackground(new Color(0,204,255));
    }//GEN-LAST:event_jPanel17MouseEntered

    private void jPanel17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseExited
        // TODO add your handling code here:
        jLabel18.setForeground(Color.black);
        jPanel17.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel17MouseExited

    private void jPanel17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MousePressed
        // TODO add your handling code here:
         getBackgroundColorSide();
    }//GEN-LAST:event_jPanel17MousePressed

    private void jPanel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseEntered
        // TODO add your handling code here:
           jLabel7.setForeground(Color.white);
        jPanel9.setBackground(new Color(0,204,255));
    }//GEN-LAST:event_jPanel9MouseEntered

    private void jPanel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseExited
        // TODO add your handling code here:
         jLabel7.setForeground(Color.black);
        jPanel9.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel9MouseExited

    private void jPanel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MousePressed
        // TODO add your handling code here:
        getTextColor();
    }//GEN-LAST:event_jPanel9MousePressed

    private void jPanel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseEntered
        // TODO add your handling code here:
          jLabel4.setForeground(Color.white);
        jPanel10.setBackground(new Color(0,204,255));
    }//GEN-LAST:event_jPanel10MouseEntered

    private void jPanel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseExited
        // TODO add your handling code here:
          jLabel4.setForeground(Color.black);
        jPanel10.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel10MouseExited

    private void jPanel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseEntered
        // TODO add your handling code here:
        jLabel5.setForeground(Color.white);
        jPanel11.setBackground(new Color(0,204,255));
    }//GEN-LAST:event_jPanel11MouseEntered

    private void jPanel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseExited
        // TODO add your handling code here:
          jLabel5.setForeground(Color.black);
        jPanel11.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel11MouseExited

    private void b_meninggalMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_meninggalMousePressed
        // TODO add your handling code here:
        
        tampilMenuHome.setVisible(false);
        DP.setVisible(true);
        ViewDataMeninggal w = new ViewDataMeninggal();
        DP.removeAll();
        DP.repaint();
        DP.revalidate();
        DP.add(w);
        DP.repaint();
        DP.revalidate();
        w.setVisible(true);
       
    }//GEN-LAST:event_b_meninggalMousePressed

    private void b_meninggalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_meninggalMouseExited
        // TODO add your handling code here:
        jLabel24.setForeground(Color.black);
        b_meninggal.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_b_meninggalMouseExited

    private void b_meninggalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_meninggalMouseEntered
        // TODO add your handling code here:
        jLabel24.setForeground(Color.white);
        b_meninggal.setBackground(new Color(0,204,255));
    }//GEN-LAST:event_b_meninggalMouseEntered

    private void b_rtMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_rtMousePressed
        // TODO add your handling code here:       tampilMenuHome.setVisible(false);
          tampilMenuHome.setVisible(false);
        DP.setVisible(true);
        ViewDataRT w = new ViewDataRT();
        DP.removeAll();
        DP.repaint();
        DP.revalidate();
        DP.add(w);
        DP.repaint();
        DP.revalidate();
        w.setVisible(true);
    }//GEN-LAST:event_b_rtMousePressed

    private void b_rtMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_rtMouseExited
        // TODO add your handling code here:
        jLabel25.setForeground(Color.black);
        b_rt.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_b_rtMouseExited

    private void b_rtMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_rtMouseEntered
        // TODO add your handling code here:
        jLabel25.setForeground(Color.white);
        b_rt.setBackground(new Color(0,204,255));
    }//GEN-LAST:event_b_rtMouseEntered

    private void b_kkMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_kkMousePressed
        // TODO add your handling code here:
          tampilMenuHome.setVisible(false);
           DP.setVisible(true);
         ViewKartuKeluarga w = new ViewKartuKeluarga();
        DP.removeAll();
        DP.repaint();
        DP.revalidate();
        DP.add(w);
        DP.repaint();
        DP.revalidate();
        w.setVisible(true);

    }//GEN-LAST:event_b_kkMousePressed

    private void b_kkMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_kkMouseExited
        // TODO add your handling code here:
        jLabel23.setForeground(Color.black);
        b_kk.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_b_kkMouseExited

    private void b_kkMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_kkMouseEntered
        // TODO add your handling code here:
        jLabel23.setForeground(Color.white);
        b_kk.setBackground(new Color(0,204,255));
    }//GEN-LAST:event_b_kkMouseEntered

    private void b_wargaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_wargaMousePressed
         tampilMenuHome.setVisible(false);
         DP.setVisible(true);
        ViewWarga w = new ViewWarga();
        DP.removeAll();
        DP.repaint();
        DP.revalidate();
        DP.add(w);
        DP.repaint();
        DP.revalidate();
        w.setVisible(true);
    }//GEN-LAST:event_b_wargaMousePressed

    private void b_wargaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_wargaMouseExited
        // TODO add your handling code here:
        jLabel2.setForeground(Color.black);
        b_warga.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_b_wargaMouseExited

    private void b_wargaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_wargaMouseEntered
        // TODO add your handling code here:
        jLabel2.setForeground(Color.white);
        b_warga.setBackground(new Color(0,204,255));
    }//GEN-LAST:event_b_wargaMouseEntered

    private void b_pengantarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_pengantarMouseEntered
        // TODO add your handling code here:
        jLabel31.setForeground(Color.white);
        b_pengantar.setBackground(new Color(0,204,255));
    }//GEN-LAST:event_b_pengantarMouseEntered

    private void b_pengantarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_pengantarMouseExited
        // TODO add your handling code here:
         jLabel31.setForeground(Color.black);
        b_pengantar.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_b_pengantarMouseExited

    private void b_pengantarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_pengantarMousePressed
        // TODO add your handling code here:
        tampilMenuHome.setVisible(false);
         DP.setVisible(true);
        ViewSuratPengantar w = new ViewSuratPengantar();
        DP.removeAll();
        DP.repaint();
        DP.revalidate();
        DP.add(w);
        DP.repaint();
        DP.revalidate();
        w.setVisible(true);
    }//GEN-LAST:event_b_pengantarMousePressed

    private void jPanel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseEntered
        // TODO add your handling code here:
        jLabel8.setForeground(Color.white);
        jPanel7.setBackground(new Color(0,204,255));
    }//GEN-LAST:event_jPanel7MouseEntered

    private void jPanel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseExited
        // TODO add your handling code here:
        jLabel8.setForeground(Color.black);
        jPanel7.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel7MouseExited

    private void lbdata3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbdata3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lbdata3MouseEntered

    private void lbdata3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbdata3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lbdata3MouseExited

    private void lbdata3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbdata3MousePressed
        // TODO add your handling code here:
        tampilMenuHome.setVisible(true);
        tampilstatistikwarga();  
        DP.setVisible(false);
    }//GEN-LAST:event_lbdata3MousePressed

    private void menu1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu1MouseEntered
        // TODO add your handling code here:
          //pmenu.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_menu1MouseEntered

    private void menu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu1MousePressed
        // TODO add your handling code here:
         tampilMenuHome.setVisible(true);
         tampilstatistikwarga();
           DP.setVisible(false);
    }//GEN-LAST:event_menu1MousePressed

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        // TODO add your handling code here:
        jLabel1.setVisible(true);
        jLabel1.setEnabled(true);
        jLabel2.setVisible(false);
        jLabel2.setEnabled(false);
        jPanel3.setVisible(false);
        jPanel3.setEnabled(false);
    }//GEN-LAST:event_jLabel2MousePressed

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        // TODO add your handling code here:
        ImageIcon AS =  new ImageIcon(getClass().getResource("/icon/icons8-scroll-up-25.png"));
        jLabel2.setIcon(AS);
        jLabel2.setVisible(true);
        jLabel2.setEnabled(true);
        jLabel1.setVisible(false);
        jLabel1.setEnabled(false);
        jPanel3.setVisible(true);
        jPanel3.setEnabled(true);
    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        // TODO add your handling code here:
        jPanel13.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
        jPanel13.setBackground(new Color(0,204,255));
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jPanel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseEntered
        // TODO add your handling code here:
         jLabel9.setForeground(Color.white);
        jPanel12.setBackground(new Color(0,204,255));
    }//GEN-LAST:event_jPanel12MouseEntered

    private void jPanel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseExited
        // TODO add your handling code here:
         jLabel9.setForeground(Color.black);
        jPanel12.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jPanel12MouseExited

    private void jPanel12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MousePressed
        // TODO add your handling code here:
         BackupRestore b = new BackupRestore();
        b.setVisible(true);
    }//GEN-LAST:event_jPanel12MousePressed

    private void b_rt1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_rt1MouseEntered
        // TODO add your handling code here:
        jLabel26.setForeground(Color.white);
        b_rt1.setBackground(new Color(0,204,255));
    }//GEN-LAST:event_b_rt1MouseEntered

    private void b_rt1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_rt1MouseExited
        // TODO add your handling code here:
         jLabel26.setForeground(Color.black);
        b_rt1.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_b_rt1MouseExited

    private void b_rt1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b_rt1MousePressed
        // TODO add your handling code here:
        tampilMenuHome.setVisible(false);
        DP.setVisible(true);
        ViewDataPindah w = new ViewDataPindah();
        DP.removeAll();
        DP.repaint();
        DP.revalidate();
        DP.add(w);
        DP.repaint();
        DP.revalidate();
        w.setVisible(true);
    }//GEN-LAST:event_b_rt1MousePressed

    private void pstatFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pstatFocusGained
        // TODO add your handling code here:      
    }//GEN-LAST:event_pstatFocusGained

    private void pstatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pstatKeyReleased
        // TODO add your handling code here:     
    }//GEN-LAST:event_pstatKeyReleased

    private void lbdata4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbdata4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lbdata4MouseEntered

    private void lbdata4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbdata4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lbdata4MouseExited

    private void lbdata4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbdata4MousePressed
        // TODO add your handling code here:
        SendEmail send = new SendEmail();
        send.setVisible(true);
    }//GEN-LAST:event_lbdata4MousePressed

    private void menu2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_menu2MouseEntered

    private void menu2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu2MousePressed
        // TODO add your handling code here:
        SendEmail send = new SendEmail();
        send.setVisible(true);
    }//GEN-LAST:event_menu2MousePressed

    private void jLabel7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MousePressed
        // TODO add your handling code here:
        getTextColor();
    }//GEN-LAST:event_jLabel7MousePressed

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
        // TODO add your handling code here:
         getBackgroundColor();
    }//GEN-LAST:event_jLabel6MousePressed

    private void jLabel18MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MousePressed
        // TODO add your handling code here:
         getBackgroundColorSide();
    }//GEN-LAST:event_jLabel18MousePressed

    private void jPanel10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MousePressed
        // TODO add your handling code here:
        tampilMenuHome.setVisible(false);
        DP.setVisible(true);
        About w = new About();
        DP.removeAll();
        DP.repaint();
        DP.revalidate();
        DP.add(w);
        DP.repaint();
        DP.revalidate();
        w.setVisible(true);
    }//GEN-LAST:event_jPanel10MousePressed

    private void jLabel4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseReleased

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        // TODO add your handling code here:
        tampilMenuHome.setVisible(false);
        DP.setVisible(true);
        About w = new About();
        DP.removeAll();
        DP.repaint();
        DP.revalidate();
        DP.add(w);
        DP.repaint();
        DP.revalidate();
        w.setVisible(true);
    }//GEN-LAST:event_jLabel4MousePressed

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
        // TODO add your handling code here:
        BackupRestore b = new BackupRestore();
        b.setVisible(true);
                
    }//GEN-LAST:event_jLabel9MousePressed

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        // TODO add your handling code here:
        LoginAplikasi lo = new LoginAplikasi();
        this.dispose();
        lo.setVisible(true);
    }//GEN-LAST:event_jLabel5MousePressed

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
        // TODO add your handling code here:
        initListener();
    }//GEN-LAST:event_jLabel8MousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
     try {
          UIManager.setLookAndFeel(new McWinLookAndFeel());
                 //"com.jtattoo.plaf.hifi.HiFiLookAndFeel " com.jtattoo.plaf.mcwin.McWinLookAndFeel
      
      } catch (Exception ex) {
            ex.printStackTrace();
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane DP;
    private javax.swing.JPanel b_kk;
    private javax.swing.JPanel b_meninggal;
    private javax.swing.JPanel b_pengantar;
    private javax.swing.JPanel b_rt;
    private javax.swing.JPanel b_rt1;
    private javax.swing.JPanel b_warga;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_id_admin;
    private javax.swing.JLabel lb_nama_admin;
    private javax.swing.JLabel lbdata3;
    private javax.swing.JLabel lbdata4;
    private javax.swing.JLabel lbl_jam;
    private javax.swing.JLabel lbl_tgl;
    private javax.swing.JLabel lbversi;
    private javax.swing.JPanel menu1;
    private javax.swing.JPanel menu2;
    private javax.swing.JPanel p_gerak;
    private javax.swing.JPanel p_side;
    private javax.swing.JPanel p_top;
    private javax.swing.JPanel panel_menu;
    private javax.swing.JPanel pmenu;
    private javax.swing.JPanel pstat;
    private javax.swing.JPanel tampilMenuHome;
    private javax.swing.JLabel text_gerak;
    // End of variables declaration//GEN-END:variables
  private void jam() {
     Timer tt = new Timer();
     tt.scheduleAtFixedRate(new TimerTask() {
         
         @Override
         public void run() {
             lbl_jam.setText(new SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));
         }
     }, 0, 1000);
 }

  
      public void getjam(){
          ActionListener taskPerformer = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
               SimpleDateFormat tgl = new SimpleDateFormat("EEEE-dd-MMM-YYYY");
               String no1_jam="";
               String no1_menit="";
               String no1_detik="";
               Date dt= new Date();
               int nilai_jam = dt.getHours();
               int nilai_menit = dt.getMinutes();
               int nilai_detik = dt.getSeconds();
               if(nilai_jam<=9){
                   no1_jam="0";
               }
               if(nilai_menit<=9){
                   no1_menit="0";
               }
               if(nilai_detik<=9){
                   no1_detik="0";
               }
               String jam = no1_jam + Integer.toString(nilai_jam);
               String menit = no1_menit + Integer.toString(nilai_menit);
               String detik = no1_detik + Integer.toString(nilai_detik);
               lbl_jam.setText("  " + jam+ " : " +menit+ " : " +detik+ "  ");
               lbl_tgl.setText(tgl.format(dt));
           }
       };
       new javax.swing.Timer(1000, taskPerformer).start();
    }
       //text gerak
}
