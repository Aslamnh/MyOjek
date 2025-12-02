/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myojektest.model;

import myojektest.view.FormOrderView;

/**
 *
 * @author rafae
 */
public class Order {

    String alamatTujuan;
    String alamatJemput;
    double harga;
    String passengerName;
    FormOrderView guiPassenger;
    String driverName;
    boolean accepted;
    boolean finisihed;
    public int order_id;
    public String tanggal;
    public int passenger_id;
    public int driver_id;
    public float biaya;
    public float jarak_km;
    String DriverName;
    
    public static final int ADMIN_FEE = 2000;

    public Order(String alamatTujuan, String alamatJemput, double harga, String passangerName, FormOrderView guiPassanger) {

        this.alamatTujuan = alamatTujuan;
        this.harga = harga;
        this.alamatJemput = alamatJemput;
        this.passengerName = passengerName;
        this.guiPassenger = guiPassenger;

    }

    public Order() {
    }
    
    // Asumsi: setBiaya diubah dari float ke int/float tergantung kebutuhan
    public void setBiaya(int totalBiaya) { 
        // Asumsi variabel 'biaya' diubah menjadi private int totalBiaya
        // Jika menggunakan float, set float, dan perbaiki tipe data di OrderController
        this.biaya = totalBiaya; // Ganti jika variabel 'biaya' diubah
    }
    
    public void setTipeLayanan(String tipe) {
        // Asumsi ada variabel private String tipeLayanan;
        // this.tipeLayanan = tipe;
    }
    
    // Tambahkan method saveOrder() yang dipanggil Core Controller
    public boolean saveOrder() {
        // Simulasi Logika Penyimpanan ke DB (OrderDAO)
        // return OrderDAO.insert(this); // Jika menggunakan OrderDAO
        return true; 
    }
    
    public boolean getAccepted() {
        return accepted;
    }
    
    public void setAccepted(boolean b) {
        accepted = b;
    }
    
    public void setJarak_km(float jarak_km) {
        this.jarak_km = jarak_km;
    }
    
    public void setBiayaDasar(float biaya) {
        this.biaya = biaya;
    }

    public void setAlamatJemput(String asal) {
        this.alamatJemput = asal;
    }
    
    public void setAlamatTujuan(String tujuan) {
        this.alamatTujuan = tujuan;
    }
    
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    
    public void setTipeLayanan() {

    }
    public static void main(String[] args) {
        
    }

        
}
