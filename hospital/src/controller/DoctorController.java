package controller;

import entity.Doctor;
import entity.Speciality;
import model.DoctorModel;
import controller.SpecialityController;
import model.SpecialityModel;
import utils.Utils;

import javax.swing.*;
import javax.swing.text.IconView;
import java.util.ArrayList;
import java.util.List;

public class DoctorController {
    DoctorModel objDoctorModel = new DoctorModel();
    SpecialityController objSpecialityController = new SpecialityController();
    SpecialityModel objSpecialityModel = new SpecialityModel();


    public DoctorController() {
        this.objDoctorModel = new DoctorModel();
    }

    public static DoctorModel instanceModel(){
        return new DoctorModel();
    }
    public void getAll() {
        String list = this.getAll(this.objDoctorModel.findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public String getAll(List<Object> listObject) {
        String list = " ============ List Doctors ============\n";
        for (Object obj : listObject) {
            Doctor objDoctor = (Doctor) obj;
            list += objDoctor.toString() + "\n";
        }

        return list;
    }


    public static void addDoctor() {
        String name = JOptionPane.showInputDialog("Enter the doctor name");
        String lastName = JOptionPane.showInputDialog("Enter the doctor last name");

        Object[] optionsSpecialities = Utils.listToArray(SpecialityController.instanceModel().findAll());
        Speciality objSpeciality = (Speciality) JOptionPane.showInputDialog(
                null,
                "Select a speciality",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsSpecialities,
                optionsSpecialities[0]
        );
        instanceModel().insert(new Doctor(name,lastName,objSpeciality.getId_speciality(),objSpeciality) );

    }

    public void delete() {
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Doctor objSelected = (Doctor) JOptionPane.showInputDialog(
                null,
                "Select a doctor to delete",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        instanceModel().delete(objSelected);

    }


    public void update() {
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Doctor objDoctor = (Doctor) JOptionPane.showInputDialog(
                null,
                "Select a doctor to update",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        objDoctor.setId_doctor(objDoctor.getId_doctor());
        objDoctor.setName_doctor(JOptionPane.showInputDialog(null, "Enter the new doctor name",objDoctor.getName_doctor()));
        objDoctor.setLast_name_doctor(JOptionPane.showInputDialog("Enter the new doctor last name", objDoctor.getLast_name_doctor()));


        Object[] optionsSpeciality = Utils.listToArray(SpecialityController.instanceModel().findAll());
        Speciality objSpeciality = (Speciality) JOptionPane.showInputDialog(
                null,
                "Select a speciality to update",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsSpeciality,
                optionsSpeciality[0]
        );


        objDoctor.setId_speciality_doctor(objSpeciality.getId_speciality());
        objDoctor.setObjSpeciality(objSpeciality);

        instanceModel().update(objDoctor);

    }

    public void find(){
        String list = "============= List Doctors =============\n";
        String search = JOptionPane.showInputDialog("Enter the date to search");

        for (Object obj: objDoctorModel.findBySpeciality(search)){
            list += obj.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null,list);
    }
    public void menuDoctors() {
        boolean flag = true;

        while (flag) {
            String option = JOptionPane.showInputDialog("""
                    ============ Administrator Doctors ============
                    1. List Doctors
                    2. Add Doctors
                    3. Update Doctors
                    4. Delete Doctors
                    5. Find by speciality
                    6. Exit
                    """);


            switch (option) {
                case "1":
                    this.getAll();
                    break;

                case "2":
                    this.addDoctor();
                    break;

                case "3":
                    this.update();
                    break;

                case "4":
                    this.delete();
                    break;

                case "5":
                    this.find();
                    break;

                case "6":
                    flag = false;
                    break;

                case null:
                    JOptionPane.showMessageDialog(null, "Canceled");
                    flag = false;
                    break;


                default:
                    JOptionPane.showMessageDialog(null, "Invalid Option");
                    break;

            }


        }

    }
}