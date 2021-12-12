package com.packageindex;

import javax.swing.*;
import java.util.Arrays;

public class LoginController {

    public void loginClick(JFrame frame,JButton btnLogin, JTextField txtUsername, JPasswordField txtPassword){
        // btnLogin action
        btnLogin.addActionListener(e -> {

            //convert password to string
            //getPassword return array of character by default
            String password = String.valueOf(txtPassword.getPassword());

            HomeView home = new HomeView();
            if(txtUsername.getText().equals("doctor")){
                if(password.equals("doctor")){
                    JOptionPane.showMessageDialog(frame,"Welcome Doctor");
                    home.doctorHome();
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(frame,"Login Fail");
                }

            }else if(txtUsername.getText().equals("staff")){
                if(password.equals("staff")){
                    JOptionPane.showMessageDialog(frame,"Welcome Staff");
                    home.staffHome();
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(frame,"Login Fail");
                }

            }else{
                JOptionPane.showMessageDialog(frame,"Login Fail");
            }
        });

    }

}
