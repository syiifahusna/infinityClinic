package com.packageindex;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class PatientController extends Clinic{

    public void fillTablePatient(DefaultTableModel tableModel){
        tableModel.setRowCount(0);

        //set table head
        String[] thead={"Id","Name","Tel"};
        tableModel.setColumnIdentifiers(thead);

        //set the table body
        Patient p = new Patient();
        ArrayList<ArrayList<String>> patientBody = p.getPatientsData();

        for (int i = 0; i < patientBody.size(); i++) {
            Object[] objs = patientBody.get(i).toArray();
            tableModel.addRow(objs);
        }
    }

    public void searchTablePatient(DefaultTableModel tableModel,String name){
        tableModel.setRowCount(0);

        Patient p = new Patient();
        ArrayList<ArrayList<String>> patientBody = p.getPatientData(name);

        for (int i = 0; i < patientBody.size(); i++) {
            Object[] objs = patientBody.get(i).toArray();
            tableModel.addRow(objs);
        }
    }

    public String insertIntoPatient(JTextField txtName,JTextField txtTel,JTextField txtAddress){

        String message;
        String name = txtName.getText();
        String tel = txtTel.getText();
        String address = txtAddress.getText();

        Patient p = new Patient();
        boolean success = p.insertPatient(name,tel,address);

        if(success){
            message = "Register Success";
        }else{
            message = "Register Fail";
        }

        return message;
    }

    public void fillEditForm(String id, JTextField txtName, JTextField txtTel, JTextField txtAddress){
        Patient p = new Patient();

        ArrayList<String> patientDetail = p.getPatientDetail(id);

        txtName.setText(patientDetail.get(1));
        txtTel.setText(patientDetail.get(2));
        txtAddress.setText(patientDetail.get(3));

    }

    public String updatePatientDetail(JLabel lblId,JTextField txtName, JTextField txtTel, JTextField txtAddress){
        String message;

        Patient p = new Patient();
        boolean success = p.updatePatient(lblId.getText(), txtName.getText(), txtTel.getText(),txtAddress.getText());

        if(success){
            message = "Update Success";
        }else{
            message = "Update Fail";
        }

        return message;
    }

}
