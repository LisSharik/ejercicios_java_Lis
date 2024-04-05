package controller;

import Utils.Utils;
import entity.Passenger;
import entity.Plane;
import model.PassengerModel;
import model.PlaneModel;

import javax.swing.*;
import java.util.List;

public class PassengerController {

    PassengerModel objPassengerModel = new PassengerModel();

    public PassengerController(){
        this.objPassengerModel = new PassengerModel();
    }

    public static PassengerModel instanceModel(){
        return new PassengerModel();
    }


    public void getAll(){
        String list = this.getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null,list);
    }


    public String getAll(List listObject){
        String list = " ============ List Passenger ============\n";
        for(Object obj: listObject){
            Passenger objPassenger = (Passenger) obj;
            list += objPassenger.toString() +  "\n";
        }

        return list;
    }

    public void insert(){

        try {
            String name = JOptionPane.showInputDialog("Enter the passenger name");
            String lastName = JOptionPane.showInputDialog("Enter the passenger last name");
            String identificationDocument = JOptionPane.showInputDialog("Enter the passenger identification document");

            instanceModel().insert(new Passenger(name, lastName,identificationDocument));


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error in creating the plane");
        }


    }

    public void delete(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Passenger objPassenger = (Passenger) JOptionPane.showInputDialog(
                null,
                "Select a Passenger",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(objPassenger);

    }


    public void update(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Passenger objPassenger = (Passenger) JOptionPane.showInputDialog(
                null,
                "Select a Passenger",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        objPassenger.setName(JOptionPane.showInputDialog(null,"Enter new name", objPassenger.getName()));
        objPassenger.setLastName(JOptionPane.showInputDialog(null,"Enter new last name", objPassenger.getLastName()));
        objPassenger.setIdentificationDocument(JOptionPane.showInputDialog(null,"Enter new identification document", objPassenger.getIdentificationDocument()));

        instanceModel().update(objPassenger);

    }

    public void findByName(){
        String list = "============= List Passengers =============\n";
        String search = JOptionPane.showInputDialog("Enter the name to search");

        for (Object obj: objPassengerModel.findByName(search)){
            list += obj.toString() + "\n_______________________________________________________\n";
        }
        JOptionPane.showMessageDialog(null,list);
    }
    public void menu(){
        boolean flag = true;


        while (flag){
            String option = JOptionPane.showInputDialog("""
                    ============ Administrator Passengers ============
                    1. List Passengers
                    2. Add Passengers
                    3. Update Passengers
                    4. Delete Passengers
                    5. Find by name
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
                    this.findByName();
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
