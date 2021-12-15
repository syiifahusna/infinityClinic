package com.packageindex;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppointmentView {

    private JFrame frame;
    private JPanel appointmentPanel,appointmentDetailPanel,newAppointmentPanel;
    private JLabel lblPatient,lblAppointment,lblNewAppointment;
    private JButton btnBack,btnNew,btnAddNew;
    private JTextField txtName,txtTel,txtAddress;
    private JTable tableAppointment;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JComboBox<String> comboMedicine;
    private JList<Object> medicineList;
    private DefaultListModel<Object> listModel;

    AppointmentController appointmentController = new AppointmentController();
    PatientController patientController = new PatientController();

    public AppointmentView(String id){

        frame = new JFrame();
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        patientAppointment(frame,id);

        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
    }

    public void patientAppointment(JFrame frame, String patientId){

        appointmentPanel = new JPanel();
        appointmentPanel.setBackground(new Color(204,229,255));
        appointmentPanel.setBounds(0,0,500,500);
        appointmentPanel.setLayout(null);
        frame.add(appointmentPanel);

        //Adding Label to panel
        lblPatient = new JLabel("Patient " + patientId + " Info");
        lblPatient.setBounds(50,20,100,30);
        appointmentPanel.add(lblPatient);

        JLabel lblName = new JLabel("Patient Name:");
        lblName.setBounds(50, 60 ,130, 30);
        appointmentPanel.add(lblName);

        JLabel lblTel = new JLabel("Tel:");
        lblTel.setBounds(50, 100 ,130, 30);
        appointmentPanel.add(lblTel);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(50, 140 ,130, 30);
        appointmentPanel.add(lblAddress);

        txtName = new JTextField();
        txtName.setBounds(150, 60 ,200, 30);
        txtName.setEditable(false);
        appointmentPanel.add(txtName);

        txtTel = new JTextField();
        txtTel.setBounds(150, 100 ,200, 30);
        txtTel.setEditable(false);
        appointmentPanel.add(txtTel);

        txtAddress = new JTextField();
        txtAddress.setBounds(150, 140 ,200, 30);
        txtAddress.setEditable(false);
        appointmentPanel.add(txtAddress);

        //========= Table Start Here===========


        //calling the boolean editCellAt to prevent editable cell;
        tableAppointment = new JTable(){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };

        tableModel = new DefaultTableModel();
        tableAppointment.setModel(tableModel);

        //fill table
        appointmentController.fillTableAppointment(tableModel,patientId);

        tableAppointment.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //prevent calling twice
                if(!e.getValueIsAdjusting()){
                   String appointmentId = tableAppointment.getValueAt(tableAppointment.getSelectedRow(), 0).toString();
                   appointmentController.clearFrame(frame);
                   appointmentDetail(frame, appointmentId,patientId);
                }
            }
        });

        //=========Table End Here===========

        scrollPane = new JScrollPane(tableAppointment);
        scrollPane.setBounds(50,190,400,200);
        appointmentPanel.add(scrollPane);

        btnBack = new JButton("Back");
        btnBack.setBounds(50,400,100,30);
        btnBack.addActionListener(e -> {
            new HomeView().doctorHome();
            frame.dispose();
        });
        appointmentPanel.add(btnBack);

        btnNew = new JButton("New Appointment");
        btnNew.setBounds(170,400,150,30);
        btnNew.addActionListener(e -> {
            appointmentController.clearFrame(frame);
            newAppointment(frame,patientId);
        });
        appointmentPanel.add(btnNew);

        patientController.fillEditForm(patientId, txtName, txtTel, txtAddress);

    }

    public void appointmentDetail(JFrame frame, String appointmentId, String patientId){
        appointmentDetailPanel = new JPanel();
        appointmentDetailPanel.setBackground(new Color(204,229,255));
        appointmentDetailPanel.setBounds(0,0,500,500);
        appointmentDetailPanel.setLayout(null);
        frame.add(appointmentDetailPanel);

        //Adding Label to panel
        lblAppointment = new JLabel("Appointment ID: " + appointmentId + "  From Patient ID: " + patientId);
        lblAppointment.setBounds(50,20,300,30);
        appointmentDetailPanel.add(lblAppointment);

        JLabel lblDate = new JLabel("Date");
        lblDate.setBounds(50, 60 ,130, 20);
        appointmentDetailPanel.add(lblDate);

        JLabel lblTime = new JLabel("Time");
        lblTime.setBounds(50, 80 ,130, 20);
        appointmentDetailPanel.add(lblTime);

        JLabel lblNotes = new JLabel("Notes:");
        lblNotes.setBounds(50, 110 ,130, 20);
        appointmentDetailPanel.add(lblNotes);

        JTextArea txtNotes = new JTextArea();
        txtNotes.setEditable(false);
        JScrollPane scrollTxtNotes = new JScrollPane(txtNotes);
        scrollTxtNotes.setBounds(50, 140 ,190, 200);
        appointmentDetailPanel.add(scrollTxtNotes);

        JLabel lblPrescription = new JLabel("Prescription:");
        lblPrescription.setBounds(260, 110 ,130, 20);
        appointmentDetailPanel.add(lblPrescription);

        listModel = new DefaultListModel<>();
        medicineList = new JList<>(listModel);
        JScrollPane scrollListMedicine = new JScrollPane(medicineList);
        scrollListMedicine.setBounds(260, 140 ,130, 150);
        appointmentDetailPanel.add(scrollListMedicine);

        btnBack = new JButton("Back");
        btnBack.setBounds(50,400,100,30);
        btnBack.addActionListener(e -> {
            appointmentController.clearFrame(frame);
            patientAppointment(frame,patientId);
        });
        appointmentDetailPanel.add(btnBack);

        appointmentController.fillAppointmentDetail(appointmentId,lblDate,lblTime,txtNotes,listModel);

    }

    public void newAppointment(JFrame frame, String patientId){
        newAppointmentPanel = new JPanel();
        newAppointmentPanel.setBackground(new Color(204,229,255));
        newAppointmentPanel.setBounds(0,0,500,500);
        newAppointmentPanel.setLayout(null);
        frame.add(newAppointmentPanel);

        //Adding Label to panel
        lblNewAppointment = new JLabel("New Appointment For Patient " + patientId);
        lblNewAppointment.setBounds(50,20,200,30);
        newAppointmentPanel.add(lblNewAppointment);

        //date
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayDate = localDateTime.format(dateFormatter);

        //date time
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String todayTime = localDateTime.format(timeFormatter);

        JLabel lblDate = new JLabel(todayDate);
        lblDate.setBounds(50, 60 ,130, 20);
        newAppointmentPanel.add(lblDate);

        JLabel lblTime = new JLabel(todayTime);
        lblTime.setBounds(50, 80 ,130, 20);
        newAppointmentPanel.add(lblTime);

        JLabel lblNotes = new JLabel("Notes:");
        lblNotes.setBounds(50, 110 ,130, 20);
        newAppointmentPanel.add(lblNotes);

        JTextArea txtNotes = new JTextArea();
        JScrollPane scrollTxtNotes = new JScrollPane(txtNotes);
        scrollTxtNotes.setBounds(50, 140 ,190, 200);
        newAppointmentPanel.add(scrollTxtNotes);

        JLabel lblPrescription = new JLabel("Prescription:");
        lblPrescription.setBounds(260, 110 ,130, 20);
        newAppointmentPanel.add(lblPrescription);

        comboMedicine = new JComboBox<>();
        comboMedicine.setBounds(260, 140 ,130, 30);
        appointmentController.fillComboMedicine(comboMedicine);
        newAppointmentPanel.add(comboMedicine);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(400, 140 ,80, 30);
        btnAdd.addActionListener(e -> {
            appointmentController.btnAddClick(comboMedicine,listModel);
        });
        newAppointmentPanel.add(btnAdd);

        listModel = new DefaultListModel<>();
        medicineList = new JList<>(listModel);
        JScrollPane scrollListMedicine = new JScrollPane(medicineList);
        scrollListMedicine.setBounds(260, 190 ,130, 150);
        newAppointmentPanel.add(scrollListMedicine);

        JButton btnRemove = new JButton("Remove");
        btnRemove.setBounds(400, 190 ,80, 30);
        btnRemove.addActionListener(e -> {
            appointmentController.btnRemoveClick(medicineList,listModel);
        });
        newAppointmentPanel.add(btnRemove);

        JLabel lblAddNewMessage = new JLabel();
        lblAddNewMessage.setBounds(260, 350 ,250, 30);
        lblAddNewMessage.setVisible(false);
        newAppointmentPanel.add(lblAddNewMessage);

        btnAddNew = new JButton("Add New");
        btnAddNew.setBounds(50,360,100,30);
        btnAddNew.addActionListener(e -> {
            String message = appointmentController.insertIntoAppointment(lblDate,lblTime,patientId,txtNotes,medicineList);
            lblAddNewMessage.setText(message);
            lblAddNewMessage.setVisible(true);
        });
        newAppointmentPanel.add(btnAddNew);

        btnBack = new JButton("Back");
        btnBack.setBounds(50,400,100,30);
        btnBack.addActionListener(e -> {
            appointmentController.clearFrame(frame);
            patientAppointment(frame,patientId);
        });
        newAppointmentPanel.add(btnBack);
    }

}
