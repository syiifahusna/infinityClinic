package com.packageindex;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MedicineView {

    private JPanel mainPanel,registerPanel,editPanel;
    private JLabel lblMedicine,lblSearch,lblMedicineId;
    private JButton btnRegisterMedicine,btnBack, btnRegister, btnSearch, btnUpdate;
    private JTextField txtSearch,txtMedicineName;
    private JTable tableMedicine;
    private JScrollPane scrollPane;
    JComboBox comboAvailability;

    public MedicineView(){
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);


        main(frame);

        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

    }


    private JPanel main(JFrame frame){
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 230,204));
        mainPanel.setBounds(0,0,500,500);
        mainPanel.setLayout(null);
        frame.add(mainPanel);


        //Adding Label to panel
        lblMedicine = new JLabel("Medicine");
        lblMedicine.setBounds(50,20,100,30);
        mainPanel.add(lblMedicine);

        btnRegisterMedicine = new JButton("Register New Medicine");
        btnRegisterMedicine.setBounds(50, 60 ,200, 30);
        btnRegisterMedicine.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            register(frame);

        });
        mainPanel.add(btnRegisterMedicine);

        lblSearch = new JLabel("Search Medicine Name:");
        lblSearch.setBounds(50, 100 ,140, 30);
        mainPanel.add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(190, 100 ,120, 30);
        mainPanel.add(txtSearch);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(320, 100 ,100, 30);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = txtSearch.getText();
                System.out.println(search);
            }
        });
        mainPanel.add(btnSearch);

        //========= Table Start Here===========
        //dummy data
        String[][] tbody=
                {
                        {"101","Paracetamol","No"},
                        {"102","Fish Oil","Yes"},
                        {"103","Aspirin","Yes"},
                };

        String[] thead={"Id","Medicine","Availability"};

        tableMedicine = new JTable(tbody,thead);
        tableMedicine.setDefaultEditor(Object.class, null);

        tableMedicine.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //prevent calling twice
                if(!e.getValueIsAdjusting()){
                    System.out.println(tableMedicine.getValueAt(tableMedicine.getSelectedRow(), 0).toString());
                    frame.getContentPane().removeAll();
                    frame.repaint();
                    edit(frame,tableMedicine.getValueAt(tableMedicine.getSelectedRow(), 0).toString());
                }
            }
        });

        //=========Table End Here===========

        scrollPane = new JScrollPane(tableMedicine);
        scrollPane.setBounds(50,150,400,300);
        mainPanel.add(scrollPane);

        return mainPanel;
    }

    private JPanel register(JFrame frame){
        registerPanel = new JPanel();
        registerPanel.setBackground(new Color(255, 230,204));
        registerPanel.setBounds(0,0,500,500);
        registerPanel.setLayout(null);
        frame.add(registerPanel);

        //Adding Label to panel
        lblMedicine = new JLabel("Register Medicine");
        lblMedicine.setBounds(50,20,110,30);
        registerPanel.add(lblMedicine);

        JLabel lblMedicine = new JLabel("Medicine Name:");
        lblMedicine.setBounds(50, 60 ,130, 30);
        registerPanel.add(lblMedicine);

        JLabel lblAvailability = new JLabel("Availability:");
        lblAvailability.setBounds(50, 100 ,130, 30);
        registerPanel.add(lblAvailability);

        txtMedicineName = new JTextField();
        txtMedicineName.setBounds(150, 60 ,200, 30);
        registerPanel.add(txtMedicineName);

        String[] option = {"Yes","No"};
        comboAvailability = new JComboBox(option);
        comboAvailability.setBounds(150, 100 ,200, 30);
        registerPanel.add(comboAvailability);


        btnRegister = new JButton("Register");
        btnRegister.setBounds(50,360,100,30);
        btnRegister.addActionListener(e -> {
            System.out.println("Register Success!");
        });
        registerPanel.add(btnRegister);

        btnBack = new JButton("Back");
        btnBack.setBounds(50,400,100,30);
        btnBack.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            main(frame);
        });
        registerPanel.add(btnBack);

        return registerPanel;
    }

    private JPanel edit(JFrame frame, String id){
        editPanel = new JPanel();
        editPanel.setBackground(new Color(255, 230,204));
        editPanel.setBounds(0,0,500,500);
        editPanel.setLayout(null);
        frame.add(editPanel);

        //Adding Label to panel
        lblMedicine = new JLabel("Edit Medicine");
        lblMedicine.setBounds(50,20,100,30);
        editPanel.add(lblMedicine);

        lblMedicineId = new JLabel(id);
        lblMedicineId.setBounds(150,20,100,30);
        editPanel.add(lblMedicineId);

        JLabel lblMedicine = new JLabel("Medicine Name:");
        lblMedicine.setBounds(50, 60 ,130, 30);
        editPanel.add(lblMedicine);

        JLabel lblAvailability = new JLabel("Availability:");
        lblAvailability.setBounds(50, 100 ,130, 30);
        editPanel.add(lblAvailability);

        txtMedicineName = new JTextField();
        txtMedicineName.setBounds(150, 60 ,200, 30);
        editPanel.add(txtMedicineName);

        String[] option = {"Yes","No"};
        comboAvailability = new JComboBox(option);
        comboAvailability.setBounds(150, 100 ,200, 30);
        editPanel.add(comboAvailability);


        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(50,360,100,30);
        btnUpdate.addActionListener(e -> {
            System.out.println("Update Success!!");
        });
        editPanel.add(btnUpdate);

        btnBack = new JButton("Back");
        btnBack.setBounds(50,400,100,30);
        btnBack.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            main(frame);
        });
        editPanel.add(btnBack);

        return editPanel;
    }


}
