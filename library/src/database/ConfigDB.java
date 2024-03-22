package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    static Connection objConnection = null;

    public static Connection openConnection(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://uis4zeu7mukajnnm:UFx5ofACT0F5Kc4KPfL0@bcsm5uivin0lqs3gledj-mysql.services.clever-cloud.com:3306/bcsm5uivin0lqs3gledj";
            String user = "uqnfatpcaftvsxap";
            String password = "CMZxDMcIDrJgdkypdqsD";

            objConnection = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Perfect Connection");

        }catch (ClassNotFoundException e){
            System.out.println("Error >> Driver not installed");
        }catch (SQLException e){
            System.out.println("Error >> Could not establish a connection whit the DB");
        }

        return objConnection;
    }


    public static void closeConnection(){
        try {
            if (objConnection != null) objConnection.close();
        }catch (SQLException e){
            System.out.println("Error " + e.getMessage());
        }
    }


}
