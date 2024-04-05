package controller;

import Utils.Utils;
import entity.Flight;
import entity.Plane;
import model.FlightModel;
import model.PlaneModel;

import javax.swing.*;
import java.util.List;

public class FlightController {
    FlightModel objFlightModel = new FlightModel();
    PlaneModel objPlaneModel = new PlaneModel();
    PlaneController objPlaneController = new PlaneController();


    public FlightController() {
        this.objFlightModel = new FlightModel();
    }

    public static FlightModel instanceModel(){
        return new FlightModel();
    }
    public void getAll() {
        String list = this.getAll(this.objFlightModel.findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public String getAll(List<Object> listObject) {
        String list = " ============ List Flights ============\n";
        for (Object obj : listObject) {
            Flight objFlight = (Flight) obj;
            list += objFlight.toString() + "\n";
        }

        return list;
    }

    public void insert(){
        String destiny = JOptionPane.showInputDialog("Enter the flight destiny");

        int year = Integer.parseInt(JOptionPane.showInputDialog("Enter the flight year"));
        int month = Integer.parseInt(JOptionPane.showInputDialog("Enter the flight month"));
        int day = Integer.parseInt(JOptionPane.showInputDialog("Enter the flight day"));

        String date = year + "-" + month + "-" + day;

        String departureTime = JOptionPane.showInputDialog("Format time: HH:MM:SS \nEnter the flight departure time");

        Object[] options = Utils.listToArray(PlaneController.instanceModel().findAll());
        Plane objPlane = (Plane) JOptionPane.showInputDialog(
                null,
                "Select a Plane",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );


        instanceModel().insert(new Flight(destiny,date,departureTime,objPlane.getId(),objPlane));

    }

    public void delete() {
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Flight objFlight = (Flight) JOptionPane.showInputDialog(
                null,
                "Select a flight to delete",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        instanceModel().delete(objFlight);

    }



    public void update(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Flight objFlight = (Flight) JOptionPane.showInputDialog(
                null,
                "Select a flight to update",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        objFlight.setId(objFlight.getId());
        objFlight.setDestiny(JOptionPane.showInputDialog("Current date: " + objFlight.getDepartureDate() + "\nEnter the new flight destiny",objFlight.getDestiny()));
        int year = Integer.parseInt(JOptionPane.showInputDialog("Current date: " + objFlight.getDepartureDate() + "\nEnter the new flight year"));
        int month = Integer.parseInt(JOptionPane.showInputDialog("Current date: " + objFlight.getDepartureDate() + "\nEnter the new flight month"));
        int day = Integer.parseInt(JOptionPane.showInputDialog("Current date: " + objFlight.getDepartureDate() + "\nEnter the new flight day"));

        objFlight.setDepartureDate(year + "-" + month + "-" + day);
        objFlight.setDepartureTime(JOptionPane.showInputDialog(null,"Format time: HH:MM:SS \nEnter the flight departure time",objFlight.getDepartureTime()));

        Object[] optionsSpeciality = Utils.listToArray(PlaneController.instanceModel().findAll());
        Plane objPlane = (Plane) JOptionPane.showInputDialog(
                null,
                "Select a plane",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsSpeciality,
                optionsSpeciality[0]
        );

        objFlight.setIdPlane(objPlane.getId());
        objFlight.setObjPlane(objPlane);

        instanceModel().update(objFlight);

    }

    public void findByDestiny(){
        String list = "============= List Flights =============\n";
        String dateSearch = JOptionPane.showInputDialog("Enter the destiny to search");

        for (Object obj: objFlightModel.findByDestiny(dateSearch)){
            list += obj.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null,list);

    }

    public void menu(){
        boolean flag = true;


        while (flag){
            String option = JOptionPane.showInputDialog("""
                    ============ Administrator Flights ============
                    1. List Flights
                    2. Add Flights
                    3. Update Flights
                    4. Delete Flights
                    5. Find by destiny
                    6. Find by id
                    7. Exit
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
                    this.findByDestiny();
                    break;

                case "7":
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
