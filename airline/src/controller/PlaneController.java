package controller;

import Utils.Utils;
import entity.Plane;
import model.PlaneModel;

import javax.swing.*;
import java.util.List;

public class PlaneController {
    PlaneModel objPlaneModel = new PlaneModel();

    public PlaneController() {
        this.objPlaneModel = new PlaneModel();
    }

    public static PlaneModel instanceModel(){
        return new PlaneModel();
    }

    public void getAll(){
        String list = this.getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null,list);
    }


    public String getAll(List listObject){
        String list = " ============ List Planes ============\n";
        for(Object obj: listObject){
            Plane objPlane = (Plane) obj;
            list += objPlane.toString() +  "\n";
        }

        return list;
    }


    public void insert(){

        try {
            String model = JOptionPane.showInputDialog("Enter the plane model");
            int capacity = Integer.parseInt(JOptionPane.showInputDialog("Enter the plane capacity"));
            instanceModel().insert(new Plane(model, capacity));


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error in creating the plane");
        }


    }

    public void delete(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Plane objSelected = (Plane) JOptionPane.showInputDialog(
                null,
                "Select a Plane",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(objSelected);

    }
    public void update(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Plane objPlane = (Plane) JOptionPane.showInputDialog(
                null,
                "Select a Plane to update",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        objPlane.setModel(JOptionPane.showInputDialog(null,"Enter new model", objPlane.getModel()));
        objPlane.setCapacity(Integer.parseInt(JOptionPane.showInputDialog(null,"Enter new description", objPlane.getCapacity())));

        instanceModel().update(objPlane);


    }


    public void menu(){
        boolean flag = true;


        while (flag){
            String option = JOptionPane.showInputDialog("""
                    ============ Administrator Planes ============
                    1. List Planes
                    2. Add Planes
                    3. Update Planes
                    4. Delete Planes
                    5. Exit
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
