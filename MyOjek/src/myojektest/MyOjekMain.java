/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package myojektest;

import myojektest.view.LoginView;

/**
 *
 * @author aslam
 */
public class MyOjekMain {

    /**aZ
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         java.awt.EventQueue.invokeLater(() -> {
            new LoginView(new PassengerDAO()).setVisible(true);
        });
       
    }
    
}
