package com.packageindex;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {

    private Connection con;

    private void setDbConnection(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/infinity_clinic", "root", "password");

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void setCon() {
        setDbConnection();
    }

    public Connection getCon() {
        return con;
    }
}
