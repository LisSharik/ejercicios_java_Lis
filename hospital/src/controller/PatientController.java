package controller;

import entity.Patient;
import entity.Speciality;
import model.PatientModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class PatientController {
    PatientModel objPatientModel = new PatientModel();

    public PatientController() {
        this.objPatientModel = new PatientModel();
    }

    public void getAll(){
        String list = this.getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null,list);
    }

    public String getAll(List listObject){
        String list = " ============ List Patients ============\n";
        for (Object obj: listObject){
            Patient objPatient = (Patient) obj;
            list += objPatient.toString() + "\n";
        }

        return list;
    }

    public static PatientModel instanceModel(){
        return new PatientModel();
    }

    public void addPatient(){
        try {
            String name = JOptionPane.showInputDialog("Enter the patient name");
            String lastName = JOptionPane.showInputDialog("Enter the patient last name");
            int year =Integer.parseInt(JOptionPane.showInputDialog("Enter the patient year birth")) ;
            int month = Integer.parseInt(JOptionPane.showInputDialog("Enter the patient month birth")) ;
            int day =Integer.parseInt(JOptionPane.showInputDialog("Enter the patient day birth")) ;
            String documentIdentification = JOptionPane.showInputDialog("Enter the patient document identification");


            String date = Utils.manageDate(year) + "-" + Utils.manageDate(month) + "-" + Utils.manageDate(day);

            System.out.println(date);

            instanceModel().insert(new Patient(name,lastName,date,documentIdentification));

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error in creating the patient");
        }
    }

    public void delete(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Patient objPatient = (Patient) JOptionPane.showInputDialog(
          null,
          "Select a patient",
          "",
          JOptionPane.QUESTION_MESSAGE,
          null,
          options,
          options[0]
        );
        instanceModel().delete(objPatient);


    }

    public void update(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Patient objPatient = (Patient) JOptionPane.showInputDialog(
                null,
                "Select a speciality to update",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        objPatient.setName_patient(JOptionPane.showInputDialog(null,"Enter new name", objPatient.getName_patient()));
        objPatient.setLast_name_patient(JOptionPane.showInputDialog(null,"Enter new last name", objPatient.getLast_name_patient()));
        int year = Integer.parseInt(JOptionPane.showInputDialog("Current date of birth: " + objPatient.getBirth_date() + "\nEnter the new year birth")) ;
        int month = Integer.parseInt(JOptionPane.showInputDialog("Current date of birth: " + objPatient.getBirth_date() +"\nEnter the new month birth")) ;
        int day = Integer.parseInt(JOptionPane.showInputDialog("Current date of birth: " + objPatient.getBirth_date() + "\nEnter the new day birth")) ;
        objPatient.setBirth_date(Utils.manageDate(year) + "-" + Utils.manageDate(month) + "-" + Utils.manageDate(day));

        objPatient.setIdentification_document(JOptionPane.showInputDialog(null,"Enter new identification document",objPatient.getIdentification_document()));

        instanceModel().update(objPatient);

    }

    public void findByDocument(){
        String list = "============= List Patients =============\n";
        String search = JOptionPane.showInputDialog("Enter the document to search");

        for (Object obj: objPatientModel.foundByDocument(search)){
            list += obj.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null,list);
    }

    public void menuSpeciality(){
        boolean flag = true;


        while (flag){
            String option = JOptionPane.showInputDialog("""
                    ============ Administrator Patients ============
                    1. List Patients
                    2. Add Patients
                    3. Update Patients
                    5. Delete Patients
                    6. Search by document
                    7. Exit
                    """);


            switch (option){
                case "1":
                    this.getAll();
                    break;

                case "2":
                    this.addPatient();
                    break;

                case "3":
                    this.update();
                    break;

                case "5":
                    this.delete();
                    break;

                case "6":
                    this.findByDocument();
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
