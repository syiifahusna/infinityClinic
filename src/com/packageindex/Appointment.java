package com.packageindex;

import java.sql.*;
import java.util.ArrayList;

public class Appointment {

    DbConnect dbConnect = new DbConnect();

    public ArrayList<ArrayList<String>> getAppointmentsData(String id){
        ArrayList<ArrayList<String>> appointment = new ArrayList<>();
        try{
            dbConnect.setCon();
            Connection con = dbConnect.getCon();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from appointment where patientId ='" + id +"'");

            while(rs.next()){
                ArrayList<String> row = new ArrayList<>();
                for (int j = 0; j <= appointment.size(); j++) {
                    row = new ArrayList<>();
                    row.add(rs.getString(1));//add col 1
                    row.add(rs.getString(2));//add col 2
                    row.add(rs.getString(3));//add col 3
                }
                appointment.add(row);
            }

            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return appointment;
    }

    public Boolean insertAppointment(String date, String time, String patientId, String notes, String prescription){
        boolean success= false;
        try {

            dbConnect.setCon();
            Connection con = dbConnect.getCon();

            int newPatientId = Integer.parseInt(patientId);

            PreparedStatement preparedStmt = con.prepareStatement("Insert Into appointment (date, time, patientId, notes, prescription) Values (?,?,?,?,?)");
            preparedStmt.setString (1, date);
            preparedStmt.setString (2, time);
            preparedStmt.setInt (3, newPatientId);
            preparedStmt.setString(4,notes);
            preparedStmt.setString(5,prescription);

            //prepare statement return false if execute success means no error recieve
            if(preparedStmt.execute()){
                success = false;
            }else{
                success = true;
            }

        }catch(Exception e){
            System.out.println(e);
        }

        return success;
    }


}
