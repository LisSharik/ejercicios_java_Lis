package model;

import database.CRUD;
import database.Configdb;
import entity.Plane;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlaneModel implements CRUD {
    @Override
    public Object insert(Object object) {
        Connection objConnection = Configdb.openConnection();
        Plane objPlane = (Plane) object;

        try {
            String sql = "INSERT INTO plane(model,capacity) VALUES (?, ?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, objPlane.getModel());
            objPrepare.setInt(2, objPlane.getCapacity());
            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objPlane.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"Plane insertion was successful");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        Configdb.closeConnection();
        return objPlane;
    }

    @Override
    public boolean update(Object object) {
        Plane objPlane = (Plane) object;
        boolean isUpdated = false;
        Connection objConnection = Configdb.openConnection();

        try {
            String sql = "UPDATE plane SET model = ?, capacity = ? WHERE id_plane = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objPlane.getModel());
            objPrepare.setInt(2, objPlane.getCapacity());

            objPrepare.setInt(3, objPlane.getId());

            if(objPrepare.executeUpdate() > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        Configdb.closeConnection();
        return isUpdated;


    }

    @Override
    public boolean delete(Object object) {
        Plane objPlane = (Plane) object;
        boolean isDeleted = false;
        Connection objConnection = Configdb.openConnection();

        try {
            String sql = "DELETE FROM plane WHERE plane.id_plane = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,objPlane.getId());

            if(objPrepare.executeUpdate() > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The delete was successfully");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        Configdb.closeConnection();
        return isDeleted;
    }

    @Override
    public List<Object> findAll() {
        ArrayList<Object> listPlanes = new ArrayList<>();
        Connection objConnection = Configdb.openConnection();

        try {
            String sql = "SELECT * FROM plane ORDER BY plane.id_plane;";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Plane objPlane = new Plane();
                objPlane.setId(objResult.getInt("plane.id_plane"));
                objPlane.setModel(objResult.getString("plane.model"));
                objPlane.setCapacity(objResult.getInt("plane.capacity"));

                listPlanes.add(objPlane);
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
            System.out.println(e.getMessage());

        }
        Configdb.closeConnection();
        return listPlanes;
    }
}
