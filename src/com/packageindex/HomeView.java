package com.packageindex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HomeView {

    private JLabel lblDoctorHome,lblStaffHome;
    private JButton btnPatient,btnDoctor,btnStaff,btnMedicine;

    public void doctorHome(){
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(204,229,255));
        panel.setBounds(0,0,500,500);
        panel.setLayout(null);
        frame.add(panel);

        //Adding Label to panel
        lblDoctorHome = new JLabel("Doctor Home");
        lblDoctorHome.setBounds(50,20,100,30);
        panel.add(lblDoctorHome);

        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

    }

    public void staffHome(){

        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255,204,204));
        panel.setBounds(0,0,500,500);
        panel.setLayout(null);
        frame.add(panel);

        //Adding Label to panel
        lblStaffHome = new JLabel("Staff Home");
        lblStaffHome.setBounds(50,20,100,30);
        panel.add(lblStaffHome);

        btnPatient = new JButton("Patient");
        btnPatient.setBounds(50,70,100,30);
        btnPatient.addActionListener(e -> {
            PatientView patientView = new PatientView();
        });
        panel.add(btnPatient);

        btnDoctor = new JButton("Doctor");
        btnDoctor.setBounds(160,70,100,30);
        panel.add(btnDoctor);

        btnStaff = new JButton("Staff");
        btnStaff.setBounds(270,70,100,30);
        panel.add(btnStaff);

        //=====================================
        btnMedicine = new JButton("Medicine");
        btnMedicine.setBounds(50,110,100,30);
        btnMedicine.addActionListener(e -> new MedicineView());
        panel.add(btnMedicine);

        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
    }
}
