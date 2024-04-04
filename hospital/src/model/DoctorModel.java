package model;

import database.CRUD;
import database.ConfigDb;
import entity.Doctor;
import entity.Speciality;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DoctorModel implements CRUD {


    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDb.openConnection();
        Doctor objDoctor = (Doctor) object;

        try {
            String sql = "INSERT INTO doctors(name_doctor, last_name_doctor, id_speciality_doctor) VALUES(?, ?, ?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objDoctor.getName_doctor());
            objPrepare.setString(2, objDoctor.getLast_name_doctor());
            objPrepare.setInt(3, objDoctor.getId_speciality_doctor());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objDoctor.setId_doctor(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Doctor insertion was successful");

            ConfigDb.closeConnection();
            return objDoctor;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding doctor " + e.getMessage());
            System.out.println(e.getMessage());
        }
        return objDoctor;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDb.openConnection();
        Doctor objDoctor = (Doctor) object;
        boolean isUpdated = false;

        try {
            String sql = "UPDATE doctors SET name_doctor = ?, last_name_doctor = ?, id_speciality_doctor = ? WHERE " +
                    "id_doctor = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objDoctor.getName_doctor());
            objPrepare.setString(2, objDoctor.getLast_name_doctor());
            objPrepare.setInt(3, objDoctor.getId_speciality_doctor());

            objPrepare.setInt(4, objDoctor.getId_doctor());

            if (objPrepare.executeUpdate() > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }

            ConfigDb.closeConnection();
            return isUpdated;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data acquisition Error " + e.getMessage());
        }

        return isUpdated;
    }

    @Override
    public boolean delete(Object object) {
        Doctor objDoctor = (Doctor) object;
        boolean isDeleted = false;
        Connection objConnection = ConfigDb.openConnection();

        try {
            String sql = "DELETE FROM doctors WHERE id_doctor = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1, objDoctor.getId_doctor());

            if (objPrepare.executeUpdate() > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The delete was successfully");
            }

            ConfigDb.closeConnection();
            return isDeleted;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data acquisition Error");
        }

        return isDeleted;



    }

    @Override
    public List<Object> findAll() {

        Connection objConnection = ConfigDb.openConnection();


        List<Object> listDoctors = new ArrayList<>();

        try {
            String sql = "SELECT* FROM doctors INNER JOIN specialties ON specialties.id_speciality = doctors" +
                    ".id_speciality_doctor;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Doctor objDoctor = new Doctor();
                Speciality objSpeciality = new Speciality();

                objDoctor.setId_doctor(objResult.getInt("doctors.id_doctor"));
                objDoctor.setName_doctor(objResult.getString("doctors.name_doctor"));
                objDoctor.setLast_name_doctor(objResult.getString("doctors.last_name_doctor"));
                objDoctor.setId_speciality_doctor(objResult.getInt("doctors.id_speciality_doctor"));

                objSpeciality.setId_speciality(objResult.getInt("specialties.id_speciality"));
                objSpeciality.setName(objResult.getString("specialties.name"));
                objSpeciality.setDescription(objResult.getString("specialties.description"));

                objDoctor.setObjSpeciality(objSpeciality);


                listDoctors.add(objDoctor);
            }

            ConfigDb.closeConnection();
            return listDoctors;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data acquisition Error");
            System.out.println(e.getMessage());
        }


            return listDoctors;



    }

    public List<Object> findBySpeciality(String search){
        Connection objConnection = ConfigDb.openConnection();

        List<Object> listDoctors = new ArrayList<>();

        try {
            String sql = "SELECT* FROM doctors \n" +
                    "INNER JOIN specialties \n" +
                    "ON specialties.id_speciality = doctors.id_speciality_doctor\n" +
                    "WHERE specialties.name LIKE '%"+ search +"%';";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Doctor objDoctor = new Doctor();
                Speciality objSpeciality = new Speciality();

                objDoctor.setId_doctor(objResult.getInt("doctors.id_doctor"));
                objDoctor.setName_doctor(objResult.getString("doctors.name_doctor"));
                objDoctor.setLast_name_doctor(objResult.getString("doctors.last_name_doctor"));
                objDoctor.setId_speciality_doctor(objResult.getInt("doctors.id_speciality_doctor"));

                objSpeciality.setId_speciality(objResult.getInt("specialties.id_speciality"));
                objSpeciality.setName(objResult.getString("specialties.name"));
                objSpeciality.setDescription(objResult.getString("specialties.description"));

                objDoctor.setObjSpeciality(objSpeciality);


                listDoctors.add(objDoctor);
            }

            ConfigDb.closeConnection();
            return listDoctors;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data acquisition Error");
            System.out.println(e.getMessage());
        }


        return listDoctors;
    }

}
