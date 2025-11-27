/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myojektest;

/**
 *
 * @author rafae
 */
public class Order {

    String alamatTujuan;
    String alamatJemput;
    double harga;
    String passangerName;
    FormOrderView guiPassanger;
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

    public Order(String alamatTujuan, String alamatJemput, double harga, String passangerName, FormOrderView guiPassanger) {

        this.alamatTujuan = alamatTujuan;
        this.harga = harga;
        this.alamatJemput = alamatJemput;
        this.passangerName = passangerName;
        this.guiPassanger = guiPassanger;

    }

    public Order() {
    }

    
    public static void main(String[] args) {
        
    }
}
