/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myojektest;

import javax.swing.JOptionPane;

/**
 *
 * @author aslam
 */
public class RegisterController {
    private RegisterView view;
    private PassengerDAO dao;

    public RegisterController(RegisterView view, PassengerDAO dao) {
        this.view = view;
        this.dao = dao;
        this.view.addRegisterListener(e -> handleRegister());
    }

    private void handleRegister() {
        // 1. Ambil data dari View
        String nama = view.getNamaField().getText();
        String email = view.getEmailField().getText();
        String noHp = view.getNoHpField().getText();
        String password = view.getPasswordField().getText();
        String confirmPassword = view.getConfirmPasswordField().getText();

        // 2. Validasi Input
        if (nama.isEmpty() || noHp.isEmpty() || password.isEmpty() || !password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(view, "Semua field harus diisi dan Password harus sama.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Panggil DAO (Model)
        if (dao.isPhoneNumberExists(noHp)) {
            JOptionPane.showMessageDialog(view, "Nomor HP sudah terdaftar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Buat objek Passenger
        Passenger newPassenger = new Passenger();
        newPassenger.nama = nama;
        newPassenger.email = email;
        newPassenger.no_hp = noHp;
        newPassenger.password = password; // Sebaiknya di-hash di aplikasi nyata!

        // Simpan ke database
        dao.insert(newPassenger);

        // 4. Feedback ke user & Alihkan ke Login
        JOptionPane.showMessageDialog(view, "Pendaftaran Berhasil! Silakan Masuk.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        view.dispose(); // Tutup Register
        new LoginView(dao).setVisible(true); // Buka Login
    }
}