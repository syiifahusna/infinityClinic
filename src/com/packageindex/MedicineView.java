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

public class MedicineView {

    private JFrame frame;
    private JPanel mainPanel,registerPanel,editPanel;
    private JLabel lblMedicine,lblSearch,lblMedicineId,lblRegisterMessage,lblUpdateMessage;
    private JButton btnRegisterMedicine,btnBack, btnRegister, btnSearch, btnUpdate;
    private JTextField txtSearch,txtMedicineName;
    private JTable tableMedicine;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    JComboBox comboAvailability;

    MedicineController medicineController = new MedicineController();

    public MedicineView(){
        frame = new JFrame();
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
            medicineController.clearFrame(frame);
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
                String searchMedicineName = txtSearch.getText();
                if(searchMedicineName.equals("")){
                    medicineController.fillTableMedicine(tableModel);
                }else{
                    medicineController.searchTableMedicine(tableModel,searchMedicineName);
                }
            }
        });
        mainPanel.add(btnSearch);

        //========= Table Start Here===========


        tableMedicine = new JTable();
        tableModel = new DefaultTableModel();
        tableMedicine.setModel(tableModel);

        //fill table
        medicineController.fillTableMedicine(tableModel);

        tableMedicine.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //prevent calling twice
                if(!e.getValueIsAdjusting()){
                    String id = tableMedicine.getValueAt(tableMedicine.getSelectedRow(), 0).toString();
                    medicineController.clearFrame(frame);
                    edit(frame,id);
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

        lblRegisterMessage = new JLabel();
        lblRegisterMessage.setBounds(150, 150 ,200, 30);
        lblRegisterMessage.setVisible(false);
        registerPanel.add(lblRegisterMessage);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(50,360,100,30);
        btnRegister.addActionListener(e -> {
            String message = medicineController.insertIntoMedicine(txtMedicineName, comboAvailability);
            lblRegisterMessage.setText(message);
            lblRegisterMessage.setVisible(true);
        });
        registerPanel.add(btnRegister);

        btnBack = new JButton("Back");
        btnBack.setBounds(50,400,100,30);
        btnBack.addActionListener(e -> {
            medicineController.clearFrame(frame);
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

        lblUpdateMessage = new JLabel();
        lblUpdateMessage.setBounds(150, 150 ,200, 30);
        lblUpdateMessage.setVisible(false);
        editPanel.add(lblUpdateMessage);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(50,360,100,30);
        btnUpdate.addActionListener(e -> {
            String message = medicineController.updateMedicineDetail(lblMedicineId,txtMedicineName,comboAvailability);
            lblUpdateMessage.setText(message);
            lblUpdateMessage.setVisible(true);
        });
        editPanel.add(btnUpdate);

        btnBack = new JButton("Back");
        btnBack.setBounds(50,400,100,30);
        btnBack.addActionListener(e -> {
            medicineController.clearFrame(frame);
            main(frame);
        });
        editPanel.add(btnBack);

        medicineController.fillEditForm(id, txtMedicineName, comboAvailability);

        return editPanel;
    }

}
