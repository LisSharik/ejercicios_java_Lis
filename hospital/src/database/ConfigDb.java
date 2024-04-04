package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDb {
    static Connection objConnection = null;

    public static Connection openConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://uopuweq86vqqnjtp:RkUIylUqK7DCquHvZdRW@b6unrsuabzwrikx7htaz-mysql.services.clever-cloud.com:3306/b6unrsuabzwrikx7htaz";
            String user = "uopuweq86vqqnjtp";
            String password = "RkUIylUqK7DCquHvZdRW";

            objConnection = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Perfect Connection");

        }catch (ClassNotFoundException e){
            System.out.println("Error >> Driver not installed");

        }catch (SQLException e){
            System.out.println("Error >> Could not stablish a connection white the db");
            System.out.println(e.getMessage());
        }

        return objConnection;
    }

    public  static void closeConnection(){
        try{
            if(objConnection != null) objConnection.close();
        }catch (SQLException e){
            System.out.println("Error " + e.getMessage());
        }
    }

}
