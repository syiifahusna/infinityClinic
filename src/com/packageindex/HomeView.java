package com.packageindex;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HomeView {

    private JLabel lblDoctorHome,lblStaffHome,lblSearch;
    private JButton btnPatient,btnDoctor,btnStaff,btnMedicine,btnSearch;
    private JTextField txtSearch ;
    private JTable tablePatient;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    HomeController home = new HomeController();

    public void doctorHome(){
        PatientController patientController = new PatientController();

        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
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

        lblSearch = new JLabel("Search Patient:");
        lblSearch.setBounds(50, 60 ,140, 30);
        panel.add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(190, 60 ,120, 30);
        panel.add(txtSearch);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(320, 60 ,100, 30);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);
                String searchName = txtSearch.getText();
                if(searchName.equals("")){
                    patientController.fillTablePatient(tableModel);
                }else{
                    patientController.searchTablePatient(tableModel,searchName);
                }
            }
        });
        panel.add(btnSearch);

        //========= Table Start Here===========
        tablePatient = new JTable();
        tableModel = new DefaultTableModel();
        tablePatient.setModel(tableModel);

        //fill table
        patientController.fillTablePatient(tableModel);

        //disable table editor(style 2)
        tablePatient.setDefaultEditor(Object.class, null);

        home.rowSelectionClick(frame,tablePatient);


        //=========Table End Here===========

        scrollPane = new JScrollPane(tablePatient);
        scrollPane.setBounds(50,150,400,300);
        panel.add(scrollPane);

        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
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
        //btnPatient Click
        home.btnPatientClick(btnPatient);
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
        //btnMedicine Click
        home.btnMedicineClick(btnMedicine);
        panel.add(btnMedicine);

        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
