/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myojektest.controller;

import myojektest.view.LoginView;
import myojektest.view.RegisterView;
import javax.swing.JOptionPane;
import myojektest.view.MainPassengerView;
import myojektest.model.Passenger;
import myojektest.model.PassengerDAO;
import myojektest.view.MainDriverView;
import myojektest.model.OrderDAO;

/**
 *
 * @author aslam
 */
public class LoginController {
    private LoginView view;
    private PassengerDAO dao;
    private OrderDAO orderDAO;

    public LoginController(LoginView view, PassengerDAO dao, OrderDAO orderDAO) {
        this.view = view;
        this.dao = dao;
        this.orderDAO = orderDAO;
        this.view.addLoginListener(e -> handleLogin());
        this.view.addSwitchToRegisterListener(e -> switchToRegister());
    }

    private void handleLogin() {
        // ambil data
        String noHp = view.getNoHpField().getText();
        String password = view.getPasswordField().getText();

        // autentikasi
        Passenger authenticatedPassenger = dao.authenticate(noHp, password);

        // update view
        if (authenticatedPassenger != null) {
            JOptionPane.showMessageDialog(view, "Masuk Berhasil! Selamat datang, " + authenticatedPassenger.nama + ".", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            view.dispose();
            if (!view.driver) 
              java.awt.EventQueue.invokeLater(() -> {      
                  new MainPassengerView(orderDAO, noHp).setVisible(true);
              });
            else
              java.awt.EventQueue.invokeLater(() -> {      
                  new MainDriverView(orderDAO).setVisible(true);
        });
            
            
         
        } else {
            JOptionPane.showMessageDialog(view, "Nomor HP atau Password salah.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void switchToRegister() {
        view.dispose();
        new RegisterView(this.dao, this.orderDAO).setVisible(true);
    }
}