package model;

import database.CRUD;
import database.ConfigDb;
import entity.Appointment;
import entity.Patient;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PatientModel implements CRUD {

    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDb.openConnection();
        Patient objPatient = (Patient) object;
        try {
            String sql = "INSERT INTO patients(name_patient, last_name_patient,birth_date,identification_document) VALUES (?, ?, ?, ?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1,objPatient.getName_patient());
            objPrepare.setString(2,objPatient.getLast_name_patient());
            objPrepare.setDate(3, Date.valueOf(objPatient.getBirth_date()) );
            objPrepare.setString(4,objPatient.getIdentification_document());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objPatient.setId_patient(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Patient insertion was succesful");
            ConfigDb.closeConnection();
            return objPatient;

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());
        }
        return objPatient;

    }

    @Override
    public boolean update(Object object) {
        Patient objPatient = (Patient) object;
        Connection objConnection = ConfigDb.openConnection();
        boolean isUpdated = false;

        try {
            String sql = "UPDATE patients SET name_patient = ?, last_name_patient = ?, birth_date = ?, identification_document = ? WHERE id_patient = ?;";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objPatient.getName_patient());
            objPrepare.setString(2,objPatient.getLast_name_patient());
            objPrepare.setDate(3,Date.valueOf(objPatient.getBirth_date()));
            objPrepare.setString(4,objPatient.getIdentification_document());
            objPrepare.setInt(5,objPatient.getId_patient());

            if (objPrepare.executeUpdate() > 0){
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
        Patient objPatient = (Patient) object;
        Connection objConnection = ConfigDb.openConnection();
        boolean isDeleted = false;


        try {
            String sql = "DELETE FROM patients WHERE patients.id_patient = ?;";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1,objPatient.getId_patient());

            if(objPrepare.executeUpdate()>0){
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
        ArrayList<Object> listPatients = new ArrayList<>();
        Connection objConnection = ConfigDb.openConnection();

        try {
            String sql = "SELECT * FROM patients ORDER BY patients.id_patient ASC;";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Patient objPatient = new Patient();
                objPatient.setId_patient(objResult.getInt("id_patient"));
                objPatient.setName_patient(objResult.getString("name_patient"));
                objPatient.setLast_name_patient(objResult.getString("last_name_patient"));
                objPatient.setBirth_date(objResult.getString("birth_date"));
                objPatient.setIdentification_document(objResult.getString("identification_document"));

                listPatients.add(objPatient);
            }


            ConfigDb.closeConnection();
            return listPatients;

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());
        }
        return listPatients;

    }

    public List<Patient> foundByDocument(String documentSearch){
        ArrayList<Patient> listDocuments = new ArrayList<>();
        Connection objConnection = ConfigDb.openConnection();
        try {
            String sql = "SELECT * FROM patients WHERE identification_document LIKE '%4%';";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Patient objPatient = new Patient();
                objPatient.setId_patient(objResult.getInt("id_patient"));
                objPatient.setName_patient(objResult.getString("name_patient"));
                objPatient.setLast_name_patient(objResult.getString("last_name_patient"));
                objPatient.setBirth_date(objResult.getString("birth_date"));
                objPatient.setIdentification_document(objResult.getString("identification_document"));

                listDocuments.add(objPatient);
            }


            ConfigDb.closeConnection();
            return listDocuments;

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());
        }

        return listDocuments;

    }

}
