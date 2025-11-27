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
public class LoginController {
    private LoginView view; // Ganti dengan class View Anda
    private PassengerDAO dao;

    public LoginController(LoginView view, PassengerDAO dao) {
        this.view = view;
        this.dao = dao;
        // Asumsi: View Anda memiliki metode untuk mendaftarkan listener
        this.view.addLoginListener(e -> handleLogin());
        this.view.addSwitchToRegisterListener(e -> switchToRegister());
    }

    private void handleLogin() {
        // 1. Ambil data dari View
        String noHp = view.getNoHpField().getText();
        String password = view.getPasswordField().getText();

        // 2. Panggil DAO (Model) untuk otentikasi
        Passenger authenticatedPassenger = dao.authenticate(noHp, password);

        // 3. Update View berdasarkan hasil
        if (authenticatedPassenger != null) {
            JOptionPane.showMessageDialog(view, "Masuk Berhasil! Selamat datang, " + authenticatedPassenger.nama + ".", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            view.dispose();
            // TODO: Buka main application window (misalnya DashboardView)
        } else {
            JOptionPane.showMessageDialog(view, "Nomor HP atau Password salah.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void switchToRegister() {
        view.dispose(); // Tutup LoginView
        new RegisterView(dao).setVisible(true); // Buka RegisterView
    }
}