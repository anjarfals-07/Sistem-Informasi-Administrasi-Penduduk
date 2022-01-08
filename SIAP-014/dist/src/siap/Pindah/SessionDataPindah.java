/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siap.Pindah;

/**
 *
 * @author Anjar
 */
public class SessionDataPindah {
      private static String kode_pindah;
    private static String no_kk;

    public static String getKodePindah() {
        return kode_pindah;
    }
    
    public static void setKodePindah(String kode_pindah) {
        SessionDataPindah.kode_pindah = kode_pindah;
    }


    public static String getKK_Pindah() {
        return no_kk;
    }

    public static void setKK_Pindah(String no_kk) {
        SessionDataPindah.no_kk = no_kk;
    }
}
