package com.example.myfitness;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionJAVA {
    Connection con;
    String uname,pass,ip,port,database;
    public Connection connectionclass()
    {
        ip="172.1.1.0";
        database="MyFitness";
        uname="DESKTOP-O9DCCT0\\FIREFLY";
        pass="";
        port="1433";

        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection=null;

        String ConnectionURL = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL= "jdbc:jtds:sqlserver://"+ ip + ":"+ port+";"+ "databasename="+ database+";user="+uname+";password="+pass+";";
            connection= DriverManager.getConnection(ConnectionURL);
        }
        catch (Exception ex){
            Log.e( "Error",ex.getMessage());

        }
        return connection;


    }


}
