package com.packageindex;


import java.sql.*;
import java.util.ArrayList;

public class Patient {

    DbConnect dbConnect = new DbConnect();

    public ArrayList<ArrayList<String>> getPatientsData(){
        ArrayList<ArrayList<String>> patient = new ArrayList<>();
        try{
            dbConnect.setCon();
            Connection con = dbConnect.getCon();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from patient");

                while(rs.next()){
                    ArrayList<String> row = new ArrayList<>();
                    for (int j = 0; j <= patient.size(); j++) {
                        row = new ArrayList<>();
                        row.add(rs.getString(1));//add col 1
                        row.add(rs.getString(2));//add col 2
                        row.add(rs.getString(3));//add col 3
                    }
                    patient.add(row);
                }

            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return patient;
    }

    public ArrayList<ArrayList<String>> getPatientData(String name) {

        ArrayList<ArrayList<String>> patient = new ArrayList<>();

        try {
            dbConnect.setCon();
            Connection con = dbConnect.getCon();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * From patient Where name Like '%" + name +"%'");

                while(rs.next()){
                    ArrayList<String> row = new ArrayList<>();
                    row.add(rs.getString(1));//add col 1
                    row.add(rs.getString(2));//add col 2
                    row.add(rs.getString(3));//add col 3

                    patient.add(row);
                }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return patient;
    }

    public Boolean insertPatient(String name, String tel,String address){
        boolean success= false;
        try {

            dbConnect.setCon();
            Connection con = dbConnect.getCon();

            PreparedStatement preparedStmt = con.prepareStatement("Insert Into patient (name, tel, address) Values (?,?,?)");
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, tel);
            preparedStmt.setString (3, address);

            //prepare statement return false if execute success
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

    public ArrayList<String> getPatientDetail(String id) {

        ArrayList<String> patient = new ArrayList<>();

        try {
            dbConnect.setCon();
            Connection con = dbConnect.getCon();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * From patient Where id = '" + id +"'");

            if (rs.next()) {

                patient.add(rs.getString(1));//add col 1 into row
                patient.add(rs.getString(2));//add col 2
                patient.add(rs.getString(3));//add col 3
                patient.add(rs.getString(4));//add col 3

            }else{
                System.out.println("No Record Found");
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return patient;
    }

    public Boolean updatePatient(String id, String name, String tel,String address){
        boolean success= false;
        int intId = Integer.parseInt(id);

        try {
            dbConnect.setCon();
            Connection con = dbConnect.getCon();

            String query = "Update patient Set name=?, tel=?, address=?  Where id = ?";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, name);//(position of ? in query,new value)
            preparedStmt.setString (2, tel);
            preparedStmt.setString (3, address);
            preparedStmt.setInt(4, intId);

            //prepare statement return false if execute success
            if(preparedStmt.execute()){
                success = false;
            }else{
                success = true;
            }

        }catch (Exception e){
            System.out.println(e);
        }

        return  success;
    }

}



