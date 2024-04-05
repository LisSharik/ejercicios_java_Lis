package model;

import database.CRUD;
import database.Configdb;
import entity.Flight;
import entity.Passenger;
import entity.Plane;
import entity.Reservation;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PassengerModel implements CRUD {
    @Override
    public Object insert(Object object) {
        Connection objConnection = Configdb.openConnection();
        Passenger objPassenger = (Passenger) object;

        try {
            String sql = "INSERT INTO passenger(name,last_name,identification_document) VALUES (?, ?, ?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objPassenger.getName());
            objPrepare.setString(2, objPassenger.getLastName());
            objPrepare.setString(3, objPassenger.getIdentificationDocument());
            objPrepare.execute();
            
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objPassenger.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Passenger insertion was successful");


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        Configdb.closeConnection();
        return objPassenger;
    }

    @Override
    public boolean update(Object object) {
        Passenger objPassenger = (Passenger) object;
        boolean isUpdated = false;
        Connection objConnection = Configdb.openConnection();

        try {
            String sql = "UPDATE passenger SET name = ?, last_name = ?,identification_document = ?  WHERE id_passenger = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objPassenger.getName());
            objPrepare.setString(2, objPassenger.getLastName());
            objPrepare.setString(3, objPassenger.getIdentificationDocument());

            objPrepare.setInt(4, objPassenger.getId());

            if(objPrepare.executeUpdate() > 0){
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
        Passenger objPassenger = (Passenger) object;
        boolean isDeleted = false;
        Connection objConnection = Configdb.openConnection();

        try {
            String sql = "DELETE FROM passenger WHERE id_passenger = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,objPassenger.getId());

            if(objPrepare.executeUpdate() > 0){
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
        ArrayList<Object> listPassenger = new ArrayList<>();
        Connection objConnection = Configdb.openConnection();
        try{
            String sql = "SELECT * FROM passenger ORDER BY passenger.id_passenger;";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Passenger objPassenger = new Passenger();
                objPassenger.setId(objResult.getInt("passenger.id_passenger"));
                objPassenger.setName(objResult.getString("passenger.name"));
                objPassenger.setLastName(objResult.getString("passenger.last_name"));
                objPassenger.setIdentificationDocument(objResult.getString("passenger.identification_document"));


                listPassenger.add(objPassenger);
            }



        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        Configdb.closeConnection();
        return listPassenger;

    }

    public List<Object> findByName(String search){
        ArrayList<Object> listPassenger = new ArrayList<>();
        Connection objConnection = Configdb.openConnection();
        try{
            String sql = "SELECT * FROM reservation\n" +
                    "INNER JOIN passenger on passenger.id_passenger = reservation.id_passenger\n" +
                    "INNER JOIN flight on flight.id_flight = reservation.id_flight\n" +
                    "INNER JOIN plane on plane.id_plane = flight.id_plane\n" +
                    "WHERE passenger.name LIKE '%" + search +"%';";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Reservation objReservation = new Reservation();
                Passenger objPassenger = new Passenger();
                Flight objFlight = new Flight();
                Plane objPlane = new Plane();


                objReservation.setId(objResult.getInt("reservation.id_reservation"));
                objReservation.setReservationDate(objResult.getString("reservation.reservation_date"));
                objReservation.setSeat(objResult.getString("reservation.seat"));
                objReservation.setIdPassenger(objResult.getInt("reservation.id_passenger"));
                objReservation.setIdFlight(objResult.getInt("reservation.id_flight"));


                objPassenger.setId(objResult.getInt("passenger.id_passenger"));
                objPassenger.setName(objResult.getString("passenger.name"));
                objPassenger.setLastName(objResult.getString("passenger.last_name"));
                objPassenger.setIdentificationDocument(objResult.getString("passenger.identification_document"));
                objReservation.setObjPassenger(objPassenger);


                objPlane.setId(objResult.getInt("plane.id_plane"));
                objPlane.setModel(objResult.getString("plane.model"));
                objPlane.setCapacity(objResult.getInt("plane.capacity"));


                objFlight.setId(objResult.getInt("flight.id_flight"));
                objFlight.setDestiny(objResult.getString("flight.destiny"));
                objFlight.setDepartureDate(objResult.getString("flight.departure_date"));
                objFlight.setDepartureTime(objResult.getString("flight.departure_time"));
                objFlight.setIdPlane(objResult.getInt("flight.id_plane"));
                objFlight.setObjPlane(objPlane);

                objReservation.setObjFlight(objFlight);
                listPassenger.add(objReservation);
            }



        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        Configdb.closeConnection();
        return listPassenger;
    }
}
