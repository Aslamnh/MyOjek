/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myojektest.controller;

import myojektest.view.LoginView;
import myojektest.view.RegisterView;
import javax.swing.JOptionPane;
import myojektest.MainPassanger;
import myojektest.Passenger;
import myojektest.PassengerDAO;
import myojektest.DriverOrder;

/**
 *
 * @author aslam
 */
public class LoginController {
    private LoginView view;
    private PassengerDAO dao;

    public LoginController(LoginView view, PassengerDAO dao) {
        this.view = view;
        this.dao = dao;
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
            if (!view.driver) 
              java.awt.EventQueue.invokeLater(() -> {      
            new MainPassanger(noHp).setVisible(true);
              });
            else
              java.awt.EventQueue.invokeLater(() -> {      
            new DriverOrder().setVisible(true);
        });
            
            
            
            // TODO: Buka main application window (misalnya DashboardView)
        } else {
            JOptionPane.showMessageDialog(view, "Nomor HP atau Password salah.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void switchToRegister() {
        view.dispose(); // Tutup LoginView
        new RegisterView(this.dao).setVisible(true); // Buka RegisterView
    }
}