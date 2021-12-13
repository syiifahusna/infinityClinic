package com.packageindex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Medicine {

    DbConnect dbConnect = new DbConnect();

    private String changeTrueFalse(String value){
        String col3 = "";
        if(value.equals("true")){
            col3 = "Yes";
        }else{
            col3 = "No";
        }

        return col3;
    }

    public ArrayList<ArrayList<String>> getMedicinesData(){
        ArrayList<ArrayList<String>> medicine = new ArrayList<>();
        try{
            dbConnect.setCon();
            Connection con = dbConnect.getCon();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from medicine");

            while(rs.next()){
                ArrayList<String> row = new ArrayList<>();
                for (int j = 0; j <= medicine.size(); j++) {
                    row = new ArrayList<>();
                    row.add(rs.getString(1));//add col 1
                    row.add(rs.getString(2));//add col 2
                    String col3 = changeTrueFalse(rs.getString(3));
                    row.add(col3);//add col 3
                }
                medicine.add(row);
            }

            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return medicine;
    }

    public ArrayList<ArrayList<String>> getMedicineData(String medicineName) {

        ArrayList<ArrayList<String>> medicine = new ArrayList<>();

        try {
            dbConnect.setCon();
            Connection con = dbConnect.getCon();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * From medicine Where medicine Like '%" + medicineName +"%'");

            while(rs.next()){
                ArrayList<String> row = new ArrayList<>();
                row.add(rs.getString(1));//add col 1
                row.add(rs.getString(2));//add col 2
                row.add(rs.getString(3));//add col 3

                medicine.add(row);
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return medicine;
    }

    public Boolean insertMedicine(String medicineName, String availability){
        boolean success= false;
        try {

            dbConnect.setCon();
            Connection con = dbConnect.getCon();

            PreparedStatement preparedStmt = con.prepareStatement("Insert Into medicine (medicine, availability) Values (?,?)");
            preparedStmt.setString (1, medicineName);
            preparedStmt.setString (2, availability);

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

    public ArrayList<String> getMedicineDetail(String id) {

        ArrayList<String> medicine = new ArrayList<>();

        try {
            dbConnect.setCon();
            Connection con = dbConnect.getCon();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * From medicine Where id = '" + id +"'");

            if (rs.next()) {

                medicine.add(rs.getString(1));//add col 1 into row
                medicine.add(rs.getString(2));//add col 2
                medicine.add(rs.getString(3));//add col 3

            }else{
                System.out.println("No Record Found");
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return medicine;
    }

    public Boolean updateMedicine(String id, String medicineName, String availability){
        boolean success= false;
        int intId = Integer.parseInt(id);

        try {
            dbConnect.setCon();
            Connection con = dbConnect.getCon();

            String query = "Update medicine Set medicine=?, availability=? Where id = ?";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, medicineName);//(position of ? in query,new value)
            preparedStmt.setString (2, availability);
            preparedStmt.setInt(3, intId);

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
