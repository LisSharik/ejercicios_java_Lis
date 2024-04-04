package model;

import database.CRUD;
import database.ConfigDb;
import entity.Speciality;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SpecialityModel implements CRUD {

    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDb.openConnection();
        Speciality objSpeciality = (Speciality) object;

        try {
            String sql = "INSERT INTO specialties(name, description) VALUES (?, ?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, objSpeciality.getName());
            objPrepare.setString(2, objSpeciality.getDescription());
            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objSpeciality.setId_speciality(objResult.getInt(1));
            }


            JOptionPane.showMessageDialog(null,"Speciality insertion was succesful");


            ConfigDb.closeConnection();
            return objSpeciality;

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        return objSpeciality;


    }

    @Override
    public boolean update(Object object) {
        Speciality objSpeciality = (Speciality) object;

        Connection objConnection = ConfigDb.openConnection();

        boolean isUpdated = false;

        try {
            String sql = "UPDATE specialties SET name = ?, description = ? WHERE id_speciality = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objSpeciality.getName());
            objPrepare.setString(2, objSpeciality.getDescription());

            objPrepare.setInt(3, objSpeciality.getId_speciality());

            if(objPrepare.executeUpdate() > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }
            ConfigDb.closeConnection();
            return isUpdated;


        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Data acquisition Error");
            System.out.println(e.getMessage());

        }
            return isUpdated;
    }
    @Override
    public boolean delete(Object object) {
        Speciality objSpeciality = (Speciality) object;
        boolean isDeleted = false;
        Connection objConnection = ConfigDb.openConnection();

        try {
            String sql = "DELETE FROM specialties WHERE specialties.id_speciality = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,objSpeciality.getId_speciality());

            if(objPrepare.executeUpdate() > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The delete was successfully");
            }
            ConfigDb.closeConnection();
            return isDeleted;

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Data acquisition Error");
            System.out.println(e.getMessage());
        }
        return isDeleted;

    }

    @Override
    public List<Object> findAll() {
        ArrayList<Object> listSpecialities = new ArrayList<>();
        Connection objConnection = ConfigDb.openConnection();

        try {
            String sql = "SELECT * FROM specialties  ORDER BY specialties.id_speciality ASC;";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Speciality objSpeciality = new Speciality();
                objSpeciality.setId_speciality(objResult.getInt("id_speciality"));
                objSpeciality.setName(objResult.getString("name"));
                objSpeciality.setDescription(objResult.getString("description"));

                listSpecialities.add(objSpeciality);
            }
            ConfigDb.closeConnection();
            return listSpecialities;

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());
        }

        return listSpecialities;
    }








}
