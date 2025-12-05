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
    boolean finished;
    public int order_id;
    public String tanggal;
    public int passenger_id;
    public int driver_id;
    public float biaya;
    public float jarak_km;
    
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
    
    public void setBiaya(int totalBiaya) { 
        this.biaya = totalBiaya;
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
    
    public String getPassengerName() {
        return passengerName;
    }
    public String getDriverName() {
        return driverName;
    }
    public int getPassengerID() {
        return passenger_id;
    }
    public void setPassengerID(int id) {
        passenger_id=id;
    }
    public int getDriverID() {
        return driver_id;
    }
    public String getTanggal() {
        return tanggal;
    }
    public float getTotal() {
        return biaya;
    }
    public String getTujuan() {
        return alamatTujuan;
    }
    public String getJemput() {
        return alamatJemput;
    }
    public float getBiaya() {
        return biaya;
    }
    public String getStatus() {
        if (!accepted) return "NOT ACCEPTED";
        else return "ACCEPTED";
    }
        
}
