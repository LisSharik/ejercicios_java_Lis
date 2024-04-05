package model;

import database.CRUD;
import database.Configdb;
import entity.Flight;
import entity.Passenger;
import entity.Plane;
import entity.Reservation;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReservationModel implements CRUD {



    @Override
    public Object insert(Object object) {
        Connection objConnection = Configdb.openConnection();
        Reservation objReservation = (Reservation) object;

        try {
            String sql = "INSERT INTO reservation(reservation_date,seat,id_passenger,id_flight) VALUES (?, ?, ?, ?);";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setDate(1, Date.valueOf(objReservation.getReservationDate()));
            objPrepare.setString(2,objReservation.getSeat());
            objPrepare.setInt(3, objReservation.getIdPassenger());
            objPrepare.setInt(4, objReservation.getIdFlight());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objReservation.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Reservation insertion was successful");



        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        Configdb.closeConnection();
        return objReservation;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = Configdb.openConnection();
        Reservation objReservation = (Reservation) object;
        boolean isUpdated = false;

        try {
            String sql = "UPDATE reservation SET reservation_date = ?, seat = ?, id_passenger = ?, id_flight = ? WHERE id_reservation = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setDate(1, Date.valueOf(objReservation.getReservationDate()));
            objPrepare.setString(2, objReservation.getSeat());
            objPrepare.setInt(3, objReservation.getIdPassenger());
            objPrepare.setInt(4, objReservation.getIdFlight());

            objPrepare.setInt(5,objReservation.getId());

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
        Reservation objReservation = (Reservation) object;
        boolean isDeleted = false;
        Connection objConnection = Configdb.openConnection();

        try {
            String sql = "DELETE FROM reservation WHERE id_reservation = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,objReservation.getId());

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
        List<Object> listReservations = new ArrayList<>();

        try {
            String sql = "SELECT * FROM reservation\n" +
                    "INNER JOIN passenger on passenger.id_passenger = reservation.id_passenger\n" +
                    "INNER JOIN flight on flight.id_flight = reservation.id_flight\n" +
                    "INNER JOIN plane on plane.id_plane = flight.id_plane;";

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

                listReservations.add(objReservation);


            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());
        }

        Configdb.closeConnection();
        return listReservations;
    }
//    SELECT * FROM reservation\n" +
//            "INNER JOIN passenger on passenger.id_passenger = reservation.id_passenger\n" +
//            "INNER JOIN flight on flight.id_flight = reservation.id_flight\n" +
//            "INNER JOIN plane on plane.id_plane = flight.id_plane\n" +
//            "WHERE flight.id_flight = ?;"
    public List<Object> findByReservation(Object object){
        Connection objConnection = Configdb.openConnection();
        List<Object> listReservations = new ArrayList<>();
        Reservation objReservation = (Reservation) object;

        try {
            String sql = "SELECT * FROM reservation\n" +
                    "INNER JOIN passenger on passenger.id_passenger = reservation.id_passenger\n" +
                    "INNER JOIN flight on flight.id_flight = reservation.id_flight\n" +
                    "INNER JOIN plane on plane.id_plane = flight.id_plane\n" +
                    "WHERE flight.id_flight = ?;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objReservation.getIdFlight());


            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Reservation objReservationNew = new Reservation();
                Passenger objPassenger = new Passenger();
                Flight objFlight = new Flight();
                Plane objPlane = new Plane();


                objReservationNew.setId(objResult.getInt("reservation.id_reservation"));
                objReservationNew.setReservationDate(objResult.getString("reservation.reservation_date"));
                objReservationNew.setSeat(objResult.getString("reservation.seat"));
                objReservationNew.setIdPassenger(objResult.getInt("reservation.id_passenger"));
                objReservationNew.setIdFlight(objResult.getInt("reservation.id_flight"));


                objPassenger.setId(objResult.getInt("passenger.id_passenger"));
                objPassenger.setName(objResult.getString("passenger.name"));
                objPassenger.setLastName(objResult.getString("passenger.last_name"));
                objPassenger.setIdentificationDocument(objResult.getString("passenger.identification_document"));
                objReservationNew.setObjPassenger(objPassenger);


                objPlane.setId(objResult.getInt("plane.id_plane"));
                objPlane.setModel(objResult.getString("plane.model"));
                objPlane.setCapacity(objResult.getInt("plane.capacity"));


                objFlight.setId(objResult.getInt("flight.id_flight"));
                objFlight.setDestiny(objResult.getString("flight.destiny"));
                objFlight.setDepartureDate(objResult.getString("flight.departure_date"));
                objFlight.setDepartureTime(objResult.getString("flight.departure_time"));
                objFlight.setIdPlane(objResult.getInt("flight.id_plane"));
                objFlight.setObjPlane(objPlane);

                objReservationNew.setObjFlight(objFlight);

                listReservations.add(objReservationNew);


            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());
        }

        Configdb.closeConnection();
        return listReservations;
    }

    public boolean validatePlane(Object object){
        boolean valid = true;
        Reservation objReservation = (Reservation) object;

        System.out.println(this.findByReservation(objReservation).size());
        if (this.findByReservation(objReservation).size() > objReservation.getObjFlight().getObjPlane().getCapacity()){
            JOptionPane.showMessageDialog(null,"There is no more space for this flight");
           return valid = false;
        }

        for (Object obj : this.findByReservation(objReservation)){
            System.out.println(obj);
            Reservation objReserv = (Reservation) obj;

            System.out.println("-> " + objReserv.getSeat());
            System.out.println("-> " + objReservation.getSeat());

            if (Objects.equals(objReserv.getSeat(), objReservation.getSeat())){
                System.out.println("son Iguales");
                JOptionPane.showMessageDialog(null,"The seat is reserved");
                valid = false;
                break;
            }
        }

        return valid;
    }
}
