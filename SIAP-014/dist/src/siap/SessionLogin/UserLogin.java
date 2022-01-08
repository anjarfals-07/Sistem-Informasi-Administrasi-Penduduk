/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siap.SessionLogin;

/**
 *
 * @author Anjar
 */
public class UserLogin {
    private static String id;
    private static String nama;

    public static String getKodeLogin() {
        return id;
    }
    
    public static void setKodeLogin(String id) {
        UserLogin.id = id;
    }


    public static String getNamaLogin() {
        return nama;
    }

    public static void setNamaLogin(String nama) {
        UserLogin.nama = nama;
    }
    
}
