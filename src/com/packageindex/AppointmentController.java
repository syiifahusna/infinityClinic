package com.packageindex;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class AppointmentController extends Clinic{

    public void fillComboMedicine(JComboBox comboMedicine){
        Medicine m = new Medicine();
        ArrayList<ArrayList<String>> medicineList = m.getMedicinesData();

        for(int i = 0; i<medicineList.size();i++){
            //add into combobox
            comboMedicine.addItem(medicineList.get(i).get(1));
        }
    }

    public void btnAddClick(JComboBox<String> comboMedicine,DefaultListModel<Object> listModel){
        //add from combobox to listmodel
        Object selectedMedicine = String.valueOf(comboMedicine.getSelectedItem());//get selected item in comboBox
        listModel.addElement(selectedMedicine);//add to list
    }

    public void btnRemoveClick(JList<Object> medicineList, DefaultListModel<Object> listModel){
        //remove from listModel
        Object selectedMedicine = medicineList.getSelectedValue();//get selected item in jlist
        listModel.removeElement(selectedMedicine);//remove from list
    }

    public void fillTableAppointment(DefaultTableModel tableModel,String id){
        tableModel.setRowCount(0);

        //set table head
        String[] thead={"Id","Date","Time"};
        tableModel.setColumnIdentifiers(thead);

        //set the table body
        Appointment a = new Appointment();
        ArrayList<ArrayList<String>> appointmentBody = a.getAppointmentsData(id);

        for (int i = 0; i < appointmentBody.size(); i++) {
            Object[] objs = appointmentBody.get(i).toArray();
            tableModel.addRow(objs);
        }
    }

    public String insertIntoAppointment(JLabel lblDate,JLabel lblTime,String patientId,JTextArea txtNotes, JList<Object> medicineList){
        String message;
        String date = lblDate.getText();
        String time = lblTime.getText();
        String notes = txtNotes.getText();
        String precription = "";

        for(int i = 0; i<medicineList.getModel().getSize();i++){
            precription += medicineList.getModel().getElementAt(i)+";";
        }

        Appointment a = new Appointment();
        boolean success = a.insertAppointment(date,time,patientId,notes,precription);

        if(success){
            message = "Add New Success";
        }else{
            message = "Add New Fail";
        }

        return message;
    }

}
