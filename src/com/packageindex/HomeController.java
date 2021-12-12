package com.packageindex;

import javax.swing.*;

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

}
