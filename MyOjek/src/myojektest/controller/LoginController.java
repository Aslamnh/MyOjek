/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myojektest.controller;

import myojektest.view.LoginView;
import myojektest.view.RegisterView;
import myojektest.view.MainPassengerView;
import myojektest.view.MainDriverView;

import myojektest.model.OrderDAO;
import myojektest.model.Passenger;
import myojektest.model.PassengerDAO;
import myojektest.model.DriverDAO;
import myojektest.model.Driver;






import javax.swing.JOptionPane;

/**
 *
 * @author aslam
 */
public class LoginController {
    private LoginView loginView;
    private PassengerDAO passengerDAO;
    private OrderDAO orderDAO;
    private DriverDAO driverDAO;

    public LoginController(LoginView loginView, PassengerDAO passengerDAO, OrderDAO orderDAO,DriverDAO driverDAO) {
        this.loginView = loginView;
        this.passengerDAO = passengerDAO;
        this.orderDAO = orderDAO;
        this.driverDAO= driverDAO;
        this.loginView.addLoginListener(e -> handleLogin());
        this.loginView.addSwitchToRegisterListener(e -> switchToRegister());
    }

    private void handleLogin() {
        // ambil data
        String noHp = loginView.getNoHpField().getText();
        String password = loginView.getPasswordField().getText();

        
        
        if(!loginView.driver){
        // autentikasi
        Passenger authenticatedPassenger = passengerDAO.authenticate(noHp, password);

        
        // update loginView
                if (authenticatedPassenger != null) {
                    JOptionPane.showMessageDialog(loginView, "Masuk Berhasil! Selamat datang, " + authenticatedPassenger.nama + ".", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                 java.awt.EventQueue.invokeLater(() -> {      
                          new MainPassengerView(orderDAO,passengerDAO,noHp,driverDAO).setVisible(true);
                });


                }
        
                else {
                    JOptionPane.showMessageDialog(loginView, "Nomor HP atau Password salah.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
        }
        
        
        else if (loginView.driver){

                 // autentikasi
        Driver authenticatedDriver = driverDAO.authenticate(noHp, password);

        
        // update loginView
                if (authenticatedDriver != null) {
                    JOptionPane.showMessageDialog(loginView, "Masuk Berhasil! Selamat datang, " + authenticatedDriver.nama + ".", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                 java.awt.EventQueue.invokeLater(() -> {      
                          new MainDriverView(orderDAO, noHp,driverDAO, passengerDAO).setVisible(true);
                });


                }
        
                else {
                    JOptionPane.showMessageDialog(loginView, "Nomor HP atau Password salah.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            
     
    }
        
    
    }
  
    
    private void switchToRegister() {
        loginView.dispose();
        new RegisterView(this.passengerDAO, this.orderDAO,driverDAO).setVisible(true);
    }
}