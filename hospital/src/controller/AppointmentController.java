package controller;

import entity.Appointment;
import entity.Doctor;
import entity.Patient;
import entity.Speciality;
import model.AppointmentModel;
import model.DoctorModel;
import model.PatientModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class AppointmentController {
    AppointmentModel objAppointmentModel = new AppointmentModel();

    DoctorController objDoctorController = new DoctorController();
    DoctorModel objDoctorModel = new DoctorModel();

    PatientController objPatientController = new PatientController();
    PatientModel objPatientModel = new PatientModel();

    public AppointmentController() {
        this.objAppointmentModel = new AppointmentModel();
    }

    public static AppointmentModel instanceModel() {
        return new AppointmentModel();
    }


    public void getAll() {
        String list = this.getAll(this.objAppointmentModel.findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public String getAll(List<Object> listObject) {
        String list = " ============ List Appointments ============\n";
        for (Object obj : listObject) {
            Appointment objAppointment = (Appointment) obj;
            System.out.println(objAppointment);
            list += objAppointment.toString() + "\n___________________________________________\n";
        }

        return list;
    }

    public static void addAppointment() {
        try {
            int year = Integer.parseInt(JOptionPane.showInputDialog("Enter the appointment year"));
            int month = Integer.parseInt(JOptionPane.showInputDialog("Enter the appointment month "));
            int day = Integer.parseInt(JOptionPane.showInputDialog("Enter the appointment day"));

            String date = Utils.manageDate(year) + "-" + Utils.manageDate(month) + "-" + Utils.manageDate(day);
            String time = JOptionPane.showInputDialog("Format time: HH:mm:ss " + "\nEnter the appointment time");
            String reason = JOptionPane.showInputDialog("Enter the appointment reason");

            Object[] optionsPatients = Utils.listToArray(PatientController.instanceModel().findAll());
            Patient objPatient = (Patient) JOptionPane.showInputDialog(
                    null,
                    "Select a patient",
                    "",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    optionsPatients,
                    optionsPatients[0]
            );

            Object[] optionsDoctors = Utils.listToArray(DoctorController.instanceModel().findAll());
            Doctor objDoctors = (Doctor) JOptionPane.showInputDialog(
                    null,
                    "Select a doctor",
                    "",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    optionsDoctors,
                    optionsDoctors[0]
            );

            instanceModel().insert(new Appointment(date, time, reason, objPatient.getId_patient(), objPatient,
                    objDoctors.getId_doctor(), objDoctors));


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in creating the patient");
        }
    }

    public void delete() {
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Appointment objAppointment = (Appointment) JOptionPane.showInputDialog(
                null,
                "Select a doctor to delete",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        instanceModel().delete(objAppointment);
    }

    public void update() {
        Object[] optionsAppointment = Utils.listToArray(instanceModel().findAll());
        Appointment objAppointment = (Appointment) JOptionPane.showInputDialog(
                null,
                "Select a appointment to update",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsAppointment,
                optionsAppointment[0]
        );

        objAppointment.setId_appointment(objAppointment.getId_appointment());

        int year =
                Integer.parseInt(JOptionPane.showInputDialog("Current date: " + objAppointment.getAppointment_date() + "\nEnter the new appointment year"));
        int month =
                Integer.parseInt(JOptionPane.showInputDialog("Current date: " + objAppointment.getAppointment_date() + "\nEnter the new appointment month "));
        int day =
                Integer.parseInt(JOptionPane.showInputDialog("Current date: " + objAppointment.getAppointment_date() + "\nEnter the new appointment day"));

        String date = Utils.manageDate(year) + "-" + Utils.manageDate(month) + "-" + Utils.manageDate(day);
        objAppointment.setAppointment_date(date);

        objAppointment.setAppointment_time(JOptionPane.showInputDialog(null, "Format time: HH:mm:ss " + "\nEnter the appointment time", objAppointment.getAppointment_time()));
        objAppointment.setReason(JOptionPane.showInputDialog(null, "Enter the new appointment reason", objAppointment.getReason()));


        //Patient
        Object[] optionsPatients = Utils.listToArray(objPatientController.instanceModel().findAll());
        Patient objPatient = (Patient) JOptionPane.showInputDialog(
                null,
                "Select a patient to update",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsPatients,
                optionsPatients[0]
        );
        objAppointment.setId_patient(objPatient.getId_patient());
        objAppointment.setObjPatient(objPatient);


        //Doctor
        Object[] optionsDoctors = Utils.listToArray(objDoctorController.instanceModel().findAll());
        Doctor objDoctor = (Doctor) JOptionPane.showInputDialog(
                null,
                "Select a doctor to update",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsDoctors,
                optionsDoctors[0]
        );

        objAppointment.setId_doctor(objDoctor.getId_doctor());
        objAppointment.setObjdoctor(objDoctor);


        instanceModel().update(objAppointment);


    }


    public void findBydate(){
        String list = "============= List Appointments =============\n";
        String dateSearch = JOptionPane.showInputDialog("Enter the date to search");

        for (Object obj: objAppointmentModel.findByDate(dateSearch)){
            list += obj.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null,list);
    }

    public void menuAppointments() {
        boolean flag = true;

        while (flag) {
            String option = JOptionPane.showInputDialog("""
                    ============ Administrator Appointments ============
                    1. List Appointments
                    2. Add Appointments
                    3. Update Appointments
                    4. Delete Appointments
                    5. Find by date
                    6. Exit
                    """);


            switch (option) {
                case "1":
                    this.getAll();
                    break;

                case "2":
                    this.addAppointment();
                    break;

                case "3":
                    this.update();
                    break;

                case "4":
                    this.delete();
                    break;

                case "5":
                    this.findBydate();
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
