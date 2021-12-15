package com.packageindex;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class HomeController {

    public void btnPatientClick(JButton btnPatient){

        btnPatient.addActionListener(e -> {
            new PatientView();
        });

    }

    public void  btnMedicineClick(JButton btnMedicine){
        btnMedicine.addActionListener(e -> {
            new MedicineView();
        });

    }

    public void rowSelectionClick(JFrame frame, JTable tablePatient){
        tablePatient.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //prevent calling twice
                if(!e.getValueIsAdjusting()){
                    String id = tablePatient.getValueAt(tablePatient.getSelectedRow(), 0).toString();
                    new AppointmentView(id);
                    frame.dispose();
                }
            }
        });
    }


}
