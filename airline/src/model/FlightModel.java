package model;

import database.CRUD;
import database.Configdb;
import entity.Flight;
import entity.Plane;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightModel implements CRUD {
    @Override
    public Object insert(Object object) {

        Connection objConnection = Configdb.openConnection();
        Flight objFlight = (Flight) object;

        try {
            String sql = "INSERT INTO flight(destiny,departure_date,departure_time,id_plane) VALUES (?, ?, ?, ?);";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objFlight.getDestiny());
            objPrepare.setDate(2, Date.valueOf(objFlight.getDepartureDate()));
            objPrepare.setTime(3, Time.valueOf(objFlight.getDepartureTime()));
            objPrepare.setInt(4, objFlight.getIdPlane());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objFlight.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Flight insertion was successful");


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        Configdb.closeConnection();
        return objFlight;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = Configdb.openConnection();
        Flight objFlight = (Flight) object;
        boolean isUpdated = false;

        try {
            String sql = "UPDATE flight SET destiny = ?, departure_date = ?, departure_time = ?, id_plane = ? WHERE id_flight = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objFlight.getDestiny());
            objPrepare.setDate(2, Date.valueOf(objFlight.getDepartureDate()));
            objPrepare.setTime(3, Time.valueOf(objFlight.getDepartureTime()));
            objPrepare.setInt(4, objFlight.getIdPlane());

            objPrepare.setInt(5,objFlight.getId());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            if (objPrepare.executeUpdate() > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        Configdb.closeConnection();
        return isUpdated;

    }

    @Override
    public boolean delete(Object object) {
        Flight objFlight = (Flight) object;
        boolean isDeleted = false;
        Connection objConnection = Configdb.openConnection();

        try {
            String sql = "DELETE FROM flight WHERE id_flight = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1, objFlight.getId());

            if (objPrepare.executeUpdate() > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The delete was successfully");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        Configdb.closeConnection();
        return isDeleted;


    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = Configdb.openConnection();
        List<Object> listFlights = new ArrayList<>();
        try {
            String sql = "SELECT * FROM flight\n" +
                    "INNER JOIN plane on plane.id_plane = flight.id_plane\n" +
                    "ORDER BY flight.id_flight ASC;";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Flight objFlight = new Flight();
                Plane objPlane = new Plane();

                objFlight.setId(objResult.getInt("flight.id_flight"));
                objFlight.setDestiny(objResult.getString("flight.destiny"));
                objFlight.setDepartureDate(objResult.getString("flight.departure_date"));
                objFlight.setDepartureTime(objResult.getString("flight.departure_time"));

                objPlane.setId(objResult.getInt("plane.id_plane"));
                objPlane.setModel(objResult.getString("plane.model"));
                objPlane.setCapacity(objResult.getInt("plane.capacity"));

                objFlight.setObjPlane(objPlane);


                listFlights.add(objFlight);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());
        }

        Configdb.closeConnection();
        return listFlights;
    }

    public List<Object> findByDestiny(String search){
        Connection objConnection = Configdb.openConnection();
        List<Object> listFlights = new ArrayList<>();
        try {
            String sql = "SELECT * FROM flight\n" +
                    "INNER JOIN plane on plane.id_plane = flight.id_plane\n" +
                    "WHERE destiny LIKE '%"+ search +"%'\n" +
                    "ORDER BY flight.id_flight ASC;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Flight objFlight = new Flight();
                Plane objPlane = new Plane();

                objFlight.setId(objResult.getInt("flight.id_flight"));
                objFlight.setDestiny(objResult.getString("flight.destiny"));
                objFlight.setDepartureDate(objResult.getString("flight.departure_date"));
                objFlight.setDepartureTime(objResult.getString("flight.departure_time"));

                objPlane.setId(objResult.getInt("plane.id_plane"));
                objPlane.setModel(objResult.getString("plane.model"));
                objPlane.setCapacity(objResult.getInt("plane.capacity"));

                objFlight.setObjPlane(objPlane);


                listFlights.add(objFlight);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());
        }

        Configdb.closeConnection();

        return listFlights;
    }




}
