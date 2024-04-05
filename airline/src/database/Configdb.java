package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Configdb {
    static Connection objConnection = null;
    public static Connection openConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://uhxzte96grehlvf3:yYOkTRZKXY2ldvGq9ca0@b6pujxybqlfamxkgd8ph-mysql.services.clever-cloud.com:3306/b6pujxybqlfamxkgd8ph";
            String user = "uhxzte96grehlvf3";
            String password = "yYOkTRZKXY2ldvGq9ca0";

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
