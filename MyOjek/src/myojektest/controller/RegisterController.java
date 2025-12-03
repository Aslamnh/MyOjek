/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myojektest.controller;

import myojektest.view.LoginView;
import myojektest.view.RegisterView;
import javax.swing.JOptionPane;
import myojektest.model.Passenger;
import myojektest.model.PassengerDAO;
import myojektest.model.OrderDAO;

/**
 *
 * @author aslam
 */
public class RegisterController {
    private RegisterView view;
    private PassengerDAO dao;
    private OrderDAO orderDAO;

    public RegisterController(RegisterView view, PassengerDAO dao, OrderDAO orderDAO) {
        this.view = view;
        this.dao = dao;
        this.orderDAO = orderDAO;
        this.view.addRegisterListener(e -> handleRegister());
    }

    private void handleRegister() {
        // ambil data
        String nama = view.getNamaField().getText();
        String email = view.getEmailField().getText();
        String noHp = view.getNoHpField().getText();
        String password = view.getPasswordField().getText();
        String confirmPassword = view.getConfirmPasswordField().getText();

        // wajib diisi
        if (nama.isEmpty() || noHp.isEmpty() || password.isEmpty() || !password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(view, "Semua field harus diisi dan Password harus sama.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // mengecek no hp
        if (dao.isPhoneNumberExists(noHp)) {
            JOptionPane.showMessageDialog(view, "Nomor HP sudah terdaftar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // buat objek Passenger baru
        Passenger newPassenger = new Passenger();
        newPassenger.nama = nama;
        newPassenger.email = email;
        newPassenger.no_hp = noHp;
        newPassenger.password = password;

        // masuk ke database
        dao.insert(newPassenger);

        JOptionPane.showMessageDialog(view, "Pendaftaran Berhasil! Silakan Masuk.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        view.dispose();
        new LoginView(dao, orderDAO).setVisible(true);
    }
}