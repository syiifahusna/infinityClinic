package com.packageindex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginView {

    private final JLabel lblLogin,lblUsername,lblPassword;
    private final JTextField txtUsername;
    private final JPasswordField txtPassword;
    private final JButton btnLogin;

    public LoginView(){
        JFrame frame = new JFrame();
        //frame.setSize(500, 300);
        //Pop Center
        //frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(1,2));

        JPanel panelImage=new JPanel();
        //add image to panel
        panelImage.add(new JLabel(new ImageIcon("C:\\Users\\User\\Documents\\javaProj\\infinityClinic\\infinity.jpg")));
        frame.add(panelImage);

        //=====================================
        JPanel panelRight = new JPanel();
        panelRight.setBackground(Color.PINK);
        panelRight.setLayout(null);

        //Adding Label to panel
        lblLogin = new JLabel("Login");
        lblLogin.setBounds(100,20,100,30);
        panelRight.add(lblLogin);

        lblUsername = new JLabel("Username");
        lblUsername.setBounds(100,50,200,30);
        panelRight.add(lblUsername);
        txtUsername = new JTextField();
        txtUsername.setBounds(220,50,200,30);
        panelRight.add(txtUsername);

        lblPassword = new JLabel("Password");
        lblPassword.setBounds(100,100,200,30);
        panelRight.add(lblPassword);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(220,100,200,30);
        panelRight.add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(220,150,200,30);
        btnLogin.addActionListener(e -> loginClick(panelRight));
        panelRight.add(btnLogin);

        frame.add(panelRight);

        //use pack to resize according to component layout
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    private void loginClick(JPanel panel){
        JOptionPane.showMessageDialog(panel,"You Click Login");
        //System.out.println(getTxtUsername());
        //System.out.println(getTxtPassword());
        //call Controller user here
        //pass username and password
        //if return true open home

        HomeView home = new HomeView();
        home.doctorHome();
        home.staffHome();
    }

    public String getTxtUsername() {
        return txtUsername.getText();
    }

    public String getTxtPassword() {
        return String.valueOf(txtPassword.getPassword());
    }
}
