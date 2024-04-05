package controller;

import Utils.Utils;
import entity.Flight;
import entity.Passenger;
import entity.Plane;
import entity.Reservation;
import model.FlightModel;
import model.PassengerModel;
import model.ReservationModel;

import javax.swing.*;
import java.util.List;

public class ReservationController {

    ReservationModel objReservationModel = new ReservationModel();

    public ReservationController() {
        this.objReservationModel = new ReservationModel();
    }

    public static ReservationModel instanceModel() {
        return new ReservationModel();
    }

    public void getAll() {
        String list = this.getAll(this.objReservationModel.findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public String getAll(List<Object> listObject) {
        String list = " ============ List Reservations ============\n";
        for (Object obj : listObject) {
            Reservation objReservation = (Reservation) obj;
            list += objReservation.toString() + "\n_____________________________________________________________\n";
        }

        return list;
    }




    public void insert(){

        int year = Integer.parseInt(JOptionPane.showInputDialog("Enter the reservation year"));
        int month = Integer.parseInt(JOptionPane.showInputDialog("Enter the reservation month"));
        int day = Integer.parseInt(JOptionPane.showInputDialog("Enter the reservation day"));

        String date = year + "-" + month + "-" + day;
        String seat = JOptionPane.showInputDialog("Enter the reservation seat");

        Object[] optionsPassenger = Utils.listToArray(PassengerController.instanceModel().findAll());
        Passenger objPassenger = (Passenger) JOptionPane.showInputDialog(
                null,
                "Select a Passenger",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsPassenger,
                optionsPassenger[0]
        );
        Object[] optionsFlight = Utils.listToArray(FlightController.instanceModel().findAll());
        Flight objFlight = (Flight) JOptionPane.showInputDialog(
                null,
                "Select a Flight",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsFlight,
                optionsFlight[0]
        );



        if (instanceModel().validatePlane(new Reservation(date,seat,
                objPassenger.getId(),objPassenger,
                objFlight.getId(),objFlight))){

            instanceModel().insert(new Reservation(date,seat,
                    objPassenger.getId(),objPassenger,
                    objFlight.getId(),objFlight));
        }else {
            JOptionPane.showMessageDialog(null,"Flight not available");
        }

    }

    public void delete() {
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Reservation objReservation = (Reservation) JOptionPane.showInputDialog(
                null,
                "Select a flight to delete",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        instanceModel().delete(objReservation);

    }

    public void update(){
        Object[] optionsReservation = Utils.listToArray(instanceModel().findAll());
        Reservation objReservation = (Reservation) JOptionPane.showInputDialog(
                null,
                "Select a Reservation",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsReservation,
                optionsReservation[0]
        );

       objReservation.setId(objReservation.getId());


        int year = Integer.parseInt(JOptionPane.showInputDialog("Current date: " + objReservation.getReservationDate() + "\nEnter the new reservation year"));
        int month = Integer.parseInt(JOptionPane.showInputDialog("Current date: " + objReservation.getReservationDate() + "\nEnter the new reservation month"));
        int day = Integer.parseInt(JOptionPane.showInputDialog("Current date: " + objReservation.getReservationDate() + "\nEnter the new reservation day"));

        Object[] optionsPassenger = Utils.listToArray(PassengerController.instanceModel().findAll());
        Passenger objPassenger = (Passenger) JOptionPane.showInputDialog(
                null,
                "Select a speciality to update",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsPassenger,
                optionsPassenger[0]
        );


        Object[] optionsFlight = Utils.listToArray(FlightController.instanceModel().findAll());
        Flight objFlight = (Flight) JOptionPane.showInputDialog(
                null,
                "Select a speciality to update",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsFlight,
                optionsFlight[0]
        );
        objReservation.setReservationDate(year + "-" + month + "-" + day);
        objReservation.setSeat(JOptionPane.showInputDialog(null,"Enter the new seat",objReservation.getSeat()));
        objReservation.setIdPassenger(objPassenger.getId());
        objReservation.setObjPassenger(objPassenger);
        objReservation.setObjFlight(objFlight);
        objReservation.setIdFlight(objFlight.getId());

        instanceModel().update(objReservation);

    }

    public void findByFlight(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Flight objFlight = (Flight) JOptionPane.showInputDialog(
                null,
                "Select a flight",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        JOptionPane.showMessageDialog(null,this.getAll(instanceModel().findByReservation(objFlight.getId())));
    }
    public void menu(){
        boolean flag = true;


        while (flag){
            String option = JOptionPane.showInputDialog("""
                    ============ Administrator Reservations ============
                    1. List Reservations
                    2. Add Reservations
                    3. Update Reservations
                    4. Delete Reservations
                    5. Find by flight
                    6. Exit
                    """);


            switch (option){
                case "1":
                    this.getAll();
                    break;

                case "2":
                    this.insert();
                    break;

                case "3":
                    this.update();
                    break;

                case "4":
                    this.delete();
                    break;

                case "5":
                    this.findByFlight();
                    break;

                case "6":
                    flag = false;
                    break;

                case null:
                    JOptionPane.showMessageDialog(null,"Canceled");
                    flag = false;
                    break;


                default:
                    JOptionPane.showMessageDialog(null,"Invalid Option");
                    break;

            }


        }



    }

}
