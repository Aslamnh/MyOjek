/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myojektest.controller;

import myojektest.view.LoginView;
import myojektest.view.RegisterView;

import myojektest.model.Passenger;
import myojektest.model.PassengerDAO;
import myojektest.model.OrderDAO;

import myojektest.model.Driver;
import myojektest.model.DriverDAO;

import javax.swing.JOptionPane;

/**
 *
 * @author aslam
 */
public class RegisterController {
    private RegisterView registerView;
    private PassengerDAO passengerDAO;
    private DriverDAO driverDAO;
    private OrderDAO orderDAO;

    public RegisterController(RegisterView loginView, PassengerDAO passengerDAO, OrderDAO orderDAO,DriverDAO driverDAO) {
        this.registerView = loginView;
        this.passengerDAO = passengerDAO;
        this.orderDAO = orderDAO;
        this.driverDAO= driverDAO;
        this.registerView.addRegisterListener(e -> handleRegister());
    }

    private void handleRegister() {
        
        boolean isdriver= registerView.getDriverstatus();
        boolean ispassanger =registerView.getPassangerstatus();
        if (!isdriver&&!ispassanger){
                    JOptionPane.showMessageDialog(registerView, "Pilih pnedaftaran sebagai apa.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        // ambil data
        String nama = registerView.getNamaField().getText();
        String email = registerView.getEmailField().getText();
        String noHp = registerView.getNoHpField().getText();
        String password = registerView.getPasswordField().getText();
        String confirmPassword = registerView.getConfirmPasswordField().getText();

        // wajib diisi
        if (nama.isEmpty() || noHp.isEmpty() || password.isEmpty() || !password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(registerView, "Semua field harus diisi dan Password harus sama.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        
        
        if (ispassanger){
        // mengecek no hp
        if (passengerDAO.isPhoneNumberExists(noHp)) {
            JOptionPane.showMessageDialog(registerView, "Nomor HP sudah terdaftar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // buat objek Passenger baru
        Passenger newPassenger = new Passenger();
        newPassenger.nama = nama;
        newPassenger.email = email;
        newPassenger.no_hp = noHp;
        newPassenger.password = password;

        // masuk ke database
        passengerDAO.insert(newPassenger);

        JOptionPane.showMessageDialog(registerView, "Pendaftaran Berhasil! Silakan Masuk.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        registerView.dispose();
        new LoginView(passengerDAO, orderDAO,driverDAO).setVisible(true);
    }
        
        
        else if (isdriver){
        // mengecek no hp
        if (driverDAO.isPhoneNumberExists(noHp)) {
            JOptionPane.showMessageDialog(registerView, "Nomor HP sudah terdaftar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // buat objek Passenger baru
        Driver newDriver = new Driver();
        newDriver.nama = nama;
        newDriver.email = email;
        newDriver.no_hp = noHp;
        newDriver.password = password;

        // masuk ke database
        driverDAO.insert(newDriver);

        JOptionPane.showMessageDialog(registerView, "Pendaftaran Berhasil! Silakan Masuk.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        registerView.dispose();
        new LoginView(passengerDAO, orderDAO,driverDAO).setVisible(true);
    } 
    
    
    }
}