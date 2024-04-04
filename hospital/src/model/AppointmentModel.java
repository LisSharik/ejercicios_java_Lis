package model;

import database.CRUD;
import database.ConfigDb;
import entity.Appointment;
import entity.Doctor;
import entity.Patient;
import entity.Speciality;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentModel implements CRUD {
    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDb.openConnection();
        Appointment objAppointment = (Appointment) object;

        try {

            String sql = "INSERT INTO appointments(appointment_date, appointment_time, reason, id_patient, id_doctor)" +
                    " " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setDate(1, Date.valueOf(objAppointment.getAppointment_date()));
            objPrepare.setTime(2, Time.valueOf(objAppointment.getAppointment_time()) );
            objPrepare.setString(3, objAppointment.getReason());
            objPrepare.setInt(4, objAppointment.getId_patient());
            objPrepare.setInt(5, objAppointment.getId_doctor());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objAppointment.setId_appointment(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Doctor insertion was successful");

            ConfigDb.closeConnection();
            return objAppointment;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding doctor " + e.getMessage());
            System.out.println(e.getMessage());
        }

        return objAppointment;


    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDb.openConnection();
        Appointment objAppointment = (Appointment) object;
        boolean isUpdated = false;

        try {
            String sql = "UPDATE appointments SET appointment_date = ?, appointment_time = ?, reason = ?,id_patient = ?,id_doctor = ?  WHERE id_appointment = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setDate(1,Date.valueOf(objAppointment.getAppointment_date()));
            objPrepare.setTime(2,Time.valueOf(objAppointment.getAppointment_time()));
            objPrepare.setString(3, objAppointment.getReason());

            objPrepare.setInt(4,objAppointment.getId_patient());
            objPrepare.setInt(5,objAppointment.getId_doctor());

            objPrepare.setInt(6, objAppointment.getId_appointment());

            int numColumn = objPrepare.executeUpdate();

            if (numColumn > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }

            ConfigDb.closeConnection();
            return isUpdated;

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data acquisition Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        return isUpdated;


    }

    @Override
    public boolean delete(Object object) {
        Appointment objAppointment = (Appointment) object;
        boolean isDeleted = false;
        Connection objConnection = ConfigDb.openConnection();

        try {
            String sql = "DELETE FROM appointments WHERE id_appointment = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,objAppointment.getId_appointment());

            if (objPrepare.executeUpdate() > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The delete was successfully");
            }

            ConfigDb.closeConnection();
            return isDeleted;

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data acquisition Error");
        }

        return isDeleted;


    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDb.openConnection();
        List<Object> listAppointments = new ArrayList<>();

        try {
            String sql = "SELECT * FROM appointments "
                    + "INNER JOIN patients ON patients.id_patient = appointments.id_patient "
                    + "INNER JOIN doctors on doctors.id_doctor = appointments.id_doctor "
                    + "INNER JOIN specialties on specialties.id_speciality = doctors.id_speciality_doctor "
                    + "ORDER BY appointments.id_appointment ASC;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Appointment objAppointment = new Appointment();
                Patient objPatient = new Patient();
                Doctor objDoctor = new Doctor();
                Speciality objSpeciality = new Speciality();

                objAppointment.setId_appointment(objResult.getInt("appointments.id_appointment"));
                objAppointment.setAppointment_date(objResult.getString("appointments.appointment_date"));
                objAppointment.setAppointment_time(objResult.getString("appointments.appointment_time"));
                objAppointment.setReason(objResult.getString("appointments.reason"));
                objAppointment.setId_patient(objResult.getInt("appointments.id_patient"));
                objAppointment.setId_doctor(objResult.getInt("appointments.id_doctor"));

                objPatient.setId_patient(objResult.getInt("patients.id_patient"));
                objPatient.setName_patient(objResult.getString("patients.name_patient"));
                objPatient.setLast_name_patient(objResult.getString("patients.last_name_patient"));
                objPatient.setBirth_date(objResult.getString("patients.birth_date"));
                objPatient.setIdentification_document(objResult.getString("patients.identification_document"));

                objSpeciality.setId_speciality(objResult.getInt("specialties.id_speciality"));
                objSpeciality.setName(objResult.getString("specialties.name"));
                objSpeciality.setDescription(objResult.getString("specialties.description"));

                objDoctor.setId_doctor(objResult.getInt("doctors.id_doctor"));
                objDoctor.setName_doctor(objResult.getString("doctors.name_doctor"));
                objDoctor.setLast_name_doctor(objResult.getString("doctors.last_name_doctor"));
                objDoctor.setId_speciality_doctor(objResult.getInt("doctors.id_speciality_doctor"));

                objDoctor.setObjSpeciality(objSpeciality);
                objAppointment.setObjPatient(objPatient);
                objAppointment.setObjdoctor(objDoctor);

                System.out.println(objAppointment);
                System.out.println(objDoctor);

                listAppointments.add(objAppointment);

            }
            ConfigDb.closeConnection();
            return listAppointments;


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data acquisition Error");
            System.out.println(e.getMessage());
        }

        return listAppointments;

    }

    public List<Appointment> findByDate(String search){
        ArrayList<Appointment> listAppointments = new ArrayList<>();
        Connection objConnection = ConfigDb.openConnection();

        try {
            String sql = "SELECT * FROM appointments \n" +
                    "INNER JOIN patients ON patients.id_patient = appointments.id_patient\n" +
                    "INNER JOIN doctors on doctors.id_doctor = appointments.id_doctor\n" +
                    "INNER JOIN specialties on specialties.id_speciality = doctors.id_speciality_doctor\n" +
                    "WHERE appointment_date  LIKE '%" +search +"%';";
            PreparedStatement preparedStatement = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = preparedStatement.executeQuery();

            while (objResult.next()) {
                Appointment objAppointment = new Appointment();
                Patient objPatient = new Patient();
                Doctor objDoctor = new Doctor();
                Speciality objSpeciality = new Speciality();

                objAppointment.setId_appointment(objResult.getInt("appointments.id_appointment"));
                objAppointment.setAppointment_date(objResult.getString("appointments.appointment_date"));
                objAppointment.setAppointment_time(objResult.getString("appointments.appointment_time"));
                objAppointment.setReason(objResult.getString("appointments.reason"));
                objAppointment.setId_patient(objResult.getInt("appointments.id_patient"));
                objAppointment.setId_doctor(objResult.getInt("appointments.id_doctor"));

                objPatient.setId_patient(objResult.getInt("patients.id_patient"));
                objPatient.setName_patient(objResult.getString("patients.name_patient"));
                objPatient.setLast_name_patient(objResult.getString("patients.last_name_patient"));
                objPatient.setBirth_date(objResult.getString("patients.birth_date"));
                objPatient.setIdentification_document(objResult.getString("patients.identification_document"));

                objSpeciality.setId_speciality(objResult.getInt("specialties.id_speciality"));
                objSpeciality.setName(objResult.getString("specialties.name"));
                objSpeciality.setDescription(objResult.getString("specialties.description"));

                objDoctor.setId_doctor(objResult.getInt("doctors.id_doctor"));
                objDoctor.setName_doctor(objResult.getString("doctors.name_doctor"));
                objDoctor.setLast_name_doctor(objResult.getString("doctors.last_name_doctor"));
                objDoctor.setId_speciality_doctor(objResult.getInt("doctors.id_speciality_doctor"));

                objDoctor.setObjSpeciality(objSpeciality);
                objAppointment.setObjPatient(objPatient);
                objAppointment.setObjdoctor(objDoctor);

                System.out.println(objAppointment);
                System.out.println(objDoctor);

                listAppointments.add(objAppointment);

            }
            ConfigDb.closeConnection();
            return listAppointments;


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Data adquisition Error");
            System.out.println(e.getMessage());
        }

        return listAppointments;
    }

}
