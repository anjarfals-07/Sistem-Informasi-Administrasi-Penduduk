/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siap.pkg014;

import javax.swing.ImageIcon;
import javax.swing.UIManager;
import siap.Login.LoginAplikasi;
import siap.Splash.SplashScreen;

/**
 *
 * @author Anjar
 */
public class SIAP014 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
           
           UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
                   
                 //"com.jtattoo.plaf.hifi.HiFiLookAndFeel " com.jtattoo.plaf.mcwin.McWinLookAndFeel
      
      } catch (Exception ex) {
            ex.printStackTrace();
        }
       
       
        SplashScreen splash = new SplashScreen();
     
        splash.setVisible(true);
        try {
        
           for (int i = 0; i <= 100; i++) {
                 Thread.sleep(70);

               SplashScreen.loadingValue.setText(i+" %");
               SplashScreen.jProgressBar1.setValue(i);
             
                if (i==10) {
                    splash.loading.setText("Turning on Modules...");
                }
                if (i==20) {
                    splash.loading.setText("Loading Modules...");
                }
                if (i==50) {
                    splash.loading.setText("Connecting...");
                }
                if (i==70) {
                    splash.loading.setText("Connecting successful...");
                }
                if (i==80) {
                    splash.loading.setText("Launching Application...");
                }
                if (i==100) {
                 splash.loading.setText("Finish...");
                   LoginAplikasi lo= new LoginAplikasi();
                   splash.setVisible(false);
                   lo.setVisible(true);
                   lo.show();
                     
                } 
           }
        } catch (Exception e) {
        }
        
    }
}
