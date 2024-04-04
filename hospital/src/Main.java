import controller.AppointmentController;
import controller.DoctorController;
import controller.PatientController;
import controller.SpecialityController;
import database.ConfigDb;
import entity.Appointment;
import entity.Patient;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SpecialityController objSpeciality = new SpecialityController();
        DoctorController objDoctor = new DoctorController();
        PatientController objPatient = new PatientController();
        AppointmentController objAppointment = new AppointmentController();

        boolean flag = true;

        while (flag){
            String option = JOptionPane.showInputDialog("""
                    ========== MENU ==========
                    1. Administrator Specialities
                    2. Administrator Doctors
                    3. Administrator Patients
                    4. Administrator Appointment
                    5. Exit
                    """);

            switch (option){
                case "1":
                    objSpeciality.menuSpeciality();
                    break;

                case "2":
                    objDoctor.menuDoctors();
                    break;

                case "3":
                    objPatient.menuSpeciality();
                    break;

                case "4":
                    objAppointment.menuAppointments();
                    break;

                case "5":
                    flag = false;
                    break;

                case null:
                    JOptionPane.showMessageDialog(null,"Canceled");
                    flag = false;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid Option");
                    break;
            }


        }


    }
}