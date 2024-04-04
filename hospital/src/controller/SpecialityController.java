package controller;

import entity.Speciality;
import model.SpecialityModel;
import utils.Utils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SpecialityController {
    SpecialityModel objSpecialityModel = new SpecialityModel();



    public SpecialityController(){
        this.objSpecialityModel = new SpecialityModel();
    }

    public static  SpecialityModel instanceModel(){
        return new SpecialityModel();
    }

    public void getAll(){
        String list = this.getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null,list);
    }


    public String getAll(List listObject){
        String list = " ============ List Specialities ============\n";
        for(Object obj: listObject){
            Speciality objSpeciality = (Speciality) obj;
            list += objSpeciality.toString() +  "\n________________________________________________________________________\n";
        }

        return list;
    }


    public void addSpeciality(){


        try {
            String name = JOptionPane.showInputDialog("Enter the speciality name");
            String description = JOptionPane.showInputDialog("Enter the speciality description");

            instanceModel().insert(new Speciality(name, description));


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error in creating the speciality");
        }


    }

    public void delete(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
       Speciality objSelected = (Speciality) JOptionPane.showInputDialog(
                null,
                "Select a speciality",
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
        Speciality objSelected = (Speciality) JOptionPane.showInputDialog(
                null,
                "Select a speciality to update",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        objSelected.setName(JOptionPane.showInputDialog(null,"Enter new name", objSelected.getName()));
        objSelected.setDescription(JOptionPane.showInputDialog(null,"Enter new description", objSelected.getDescription()));

        instanceModel().update(objSelected);


    }


    public void menuSpeciality(){
        boolean flag = true;


        while (flag){
            String option = JOptionPane.showInputDialog("""
                    ============ Administrator Specialities ============
                    1. List Specialities
                    2. Add Speciality
                    3. Update speciality
                    4. Delete Speciality
                    5. Exit
                    """);


            switch (option){
                case "1":
                    this.getAll();
                    break;

                case "2":
                    this.addSpeciality();
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
