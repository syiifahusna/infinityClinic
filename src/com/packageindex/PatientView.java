package com.packageindex;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PatientView {

    private JPanel mainPanel,registerPanel,editPanel;
    private JLabel lblPatient,lblSearch;
    private JButton btnRegisterPatient,btnBack, btnRegister, btnSearch, btnUpdate;
    private JTextField txtSearch,txtName,txtTel,txtAddress;
    private JTable tablePatient;
    private JScrollPane scrollPane;

    public PatientView(){
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
        mainPanel.setBackground(new Color(255,204,204));
        mainPanel.setBounds(0,0,500,500);
        mainPanel.setLayout(null);
        frame.add(mainPanel);

        //Adding Label to panel
        lblPatient = new JLabel("Patient");
        lblPatient.setBounds(50,20,100,30);
        mainPanel.add(lblPatient);


        btnRegisterPatient = new JButton("Register New Patient");
        btnRegisterPatient.setBounds(50, 60 ,200, 30);
        btnRegisterPatient.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            register(frame);

        });
        mainPanel.add(btnRegisterPatient);

        lblSearch = new JLabel("Search Patient Name:");
        lblSearch.setBounds(50, 100 ,130, 30);
        mainPanel.add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(190, 100 ,120, 30);
//        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                System.out.println(txtSearch.getText());
//                System.out.println("insert");
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                System.out.println(txtSearch.getText());
//                System.out.println("remove");
//
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                System.out.println(txtSearch.getText());
//                System.out.println("change");
//            }
//        });
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
                        {"101","Amit","670000"},
                        {"102","Jai","780000"},
                        {"103","May","780000"},
                };

        String[] thead={"Id","Name","Tel"};

        //calling the boolean editCellAt to prevent editable cell;
        tablePatient = new JTable(tbody,thead){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };

        tablePatient.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //prevent calling twice
                if(!e.getValueIsAdjusting()){
                    System.out.println(tablePatient.getValueAt(tablePatient.getSelectedRow(), 0).toString());
                    frame.getContentPane().removeAll();
                    frame.repaint();
                    edit(frame,tablePatient.getValueAt(tablePatient.getSelectedRow(), 0).toString());
                }
            }
        });

        //=========Table End Here===========

        scrollPane = new JScrollPane(tablePatient);
        scrollPane.setBounds(50,150,400,300);
        mainPanel.add(scrollPane);

        return mainPanel;
    }

    private JPanel register(JFrame frame){
        registerPanel = new JPanel();
        registerPanel.setBackground(new Color(255,204,204));
        registerPanel.setBounds(0,0,500,500);
        registerPanel.setLayout(null);
        frame.add(registerPanel);

        //Adding Label to panel
        lblPatient = new JLabel("Register Patient");
        lblPatient.setBounds(50,20,100,30);
        registerPanel.add(lblPatient);

        JLabel lblName = new JLabel("Patient Name:");
        lblName.setBounds(50, 60 ,130, 30);
        registerPanel.add(lblName);

        JLabel lblTel = new JLabel("Tel:");
        lblTel.setBounds(50, 100 ,130, 30);
        registerPanel.add(lblTel);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(50, 140 ,130, 30);
        registerPanel.add(lblAddress);

        txtName = new JTextField();
        txtName.setBounds(150, 60 ,200, 30);
        registerPanel.add(txtName);

        txtTel = new JTextField();
        txtTel.setBounds(150, 100 ,200, 30);
        registerPanel.add(txtTel);

        txtAddress = new JTextField();
        txtAddress.setBounds(150, 140 ,200, 30);
        registerPanel.add(txtAddress);







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
        editPanel.setBackground(new Color(255,204,204));
        editPanel.setBounds(0,0,500,500);
        editPanel.setLayout(null);
        frame.add(editPanel);

        //Adding Label to panel
        lblPatient = new JLabel("Edit Patient " + id);
        lblPatient.setBounds(50,20,100,30);
        editPanel.add(lblPatient);

        JLabel lblName = new JLabel("Patient Name:");
        lblName.setBounds(50, 60 ,130, 30);
        editPanel.add(lblName);

        JLabel lblTel = new JLabel("Tel:");
        lblTel.setBounds(50, 100 ,130, 30);
        editPanel.add(lblTel);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(50, 140 ,130, 30);
        editPanel.add(lblAddress);

        txtName = new JTextField();
        txtName.setBounds(150, 60 ,200, 30);
        editPanel.add(txtName);

        txtTel = new JTextField();
        txtTel.setBounds(150, 100 ,200, 30);
        editPanel.add(txtTel);

        txtAddress = new JTextField();
        txtAddress.setBounds(150, 140 ,200, 30);
        editPanel.add(txtAddress);


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
