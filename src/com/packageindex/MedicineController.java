package com.packageindex;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class MedicineController extends Clinic{

    public void fillTableMedicine(DefaultTableModel tableModel){
        tableModel.setRowCount(0);

        //set table head
        String[] thead={"Id","Medicine","Availability"};
        tableModel.setColumnIdentifiers(thead);

        //set the table body
        Medicine m = new Medicine();
        ArrayList<ArrayList<String>> medicineBody = m.getMedicinesData();

        for (int i = 0; i < medicineBody.size(); i++) {

            Object[] objs = medicineBody.get(i).toArray();
            tableModel.addRow(objs);
        }
    }

    public void searchTableMedicine(DefaultTableModel tableModel,String medicineName){
        tableModel.setRowCount(0);

        Medicine m = new Medicine();
        ArrayList<ArrayList<String>> medicineBody = m.getMedicineData(medicineName);

        for (int i = 0; i < medicineBody.size(); i++) {
            Object[] objs = medicineBody.get(i).toArray();
            tableModel.addRow(objs);
        }
    }

    public String insertIntoMedicine(JTextField txtMedicineName,JComboBox comboAvailability){

        String message;
        String name = txtMedicineName.getText();
        String comboResult = String.valueOf(comboAvailability.getSelectedItem());

        //change yes no to true false
        String availability = "";
        if(comboResult.equals("Yes")){
            availability = "true";
        }else{
            availability = "false";
        }

        Medicine m = new Medicine();
        boolean success = m.insertMedicine(name,availability);

        if(success){
            message = "Register Success";
        }else{
            message = "Register Fail";
        }

        return message;
    }

    public void fillEditForm(String id, JTextField txtMedicineName, JComboBox comboAvailability){
        Medicine m = new Medicine();

        ArrayList<String> medicineDetail = m.getMedicineDetail(id);

        //change true false to yes no
        String comboResult = "";
        if(medicineDetail.get(2).equals("true")){
            comboResult = "Yes";
        }else{
            comboResult = "No";
        }

        txtMedicineName.setText(medicineDetail.get(1));
        comboAvailability.setSelectedItem(comboResult);
    }

    public String updateMedicineDetail(JLabel lblId,JTextField txtMedicineName, JComboBox comboAvailability){
        String message;
        String id = lblId.getText();
        String medicineName = txtMedicineName.getText();
        String comboResult = String.valueOf(comboAvailability.getSelectedItem());

        //change yes no to true false
        String availability = "";
        if(comboResult.equals("Yes")){
            availability = "true";
        }else{
            availability = "false";
        }

        Medicine m = new Medicine();
        boolean success = m.updateMedicine(id, medicineName, availability);

        if(success){
            message = "Update Success";
        }else{
            message = "Update Fail";
        }

        return message;
    }

}
