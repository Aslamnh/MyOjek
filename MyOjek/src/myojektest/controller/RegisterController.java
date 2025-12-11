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

import javax.swing.JOptionPane;

/**
 *
 * @author aslam
 */
public class RegisterController {
    private RegisterView loginView;
    private PassengerDAO passengerDAO;
    private OrderDAO orderDAO;

    public RegisterController(RegisterView loginView, PassengerDAO passengerDAO, OrderDAO orderDAO) {
        this.loginView = loginView;
        this.passengerDAO = passengerDAO;
        this.orderDAO = orderDAO;
        this.loginView.addRegisterListener(e -> handleRegister());
    }

    private void handleRegister() {
        // ambil data
        String nama = loginView.getNamaField().getText();
        String email = loginView.getEmailField().getText();
        String noHp = loginView.getNoHpField().getText();
        String password = loginView.getPasswordField().getText();
        String confirmPassword = loginView.getConfirmPasswordField().getText();

        // wajib diisi
        if (nama.isEmpty() || noHp.isEmpty() || password.isEmpty() || !password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(loginView, "Semua field harus diisi dan Password harus sama.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // mengecek no hp
        if (passengerDAO.isPhoneNumberExists(noHp)) {
            JOptionPane.showMessageDialog(loginView, "Nomor HP sudah terdaftar.", "Error", JOptionPane.ERROR_MESSAGE);
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

        JOptionPane.showMessageDialog(loginView, "Pendaftaran Berhasil! Silakan Masuk.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        loginView.dispose();
        new LoginView(passengerDAO, orderDAO).setVisible(true);
    }
}