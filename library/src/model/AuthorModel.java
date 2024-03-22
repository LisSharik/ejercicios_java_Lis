package model;

import controller.AuthorController;
import database.CRUD;
import database.ConfigDB;
import entity.Author;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class AuthorModel implements CRUD {
    @Override
    public Object insert(Object object) {
        Connection objConnetion = ConfigDB.openConnection();
        Author objAuthor = (Author) object;

        try {
            String sql = "INSERT INTO authors(name,nationality) VALUES(?, ?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnetion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objAuthor.getName());
            objPrepare.setString(2, objAuthor.getNationality());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objAuthor.setId(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Author insertion was successful");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
        }
        ConfigDB.closeConnection();

        return objAuthor;
    }

    @Override
    public boolean update(Object object) {
        Author objAuthor = (Author) object;
        Connection objConnection = ConfigDB.openConnection();

        boolean isUpdated = false;
        try {
            String sql = "UPDATE authors SET name = ?, nationality = ? WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objAuthor.getName());
            objPrepare.setString(2, objAuthor.getNationality());

            objPrepare.setInt(3,objAuthor.getId());

            if (objPrepare.executeUpdate() > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"The update was succesful");

            }



        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Data adquisition Error " +e.getMessage());
        }finally {
            ConfigDB.closeConnection();
            return isUpdated;
        }

    }

    @Override
    public boolean delete(Object object) {
        Author objAuthor = (Author) object;

        boolean isDeleted = false;
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "DELETE FROM authors WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1,objAuthor.getId());

            if (objPrepare.executeUpdate()>0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"The delete was succesfuly");
            }

            ConfigDB.closeConnection();
            return isDeleted;

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Data adquisition Error");
        }



        return isDeleted;
    }

    @Override
    public List<Object> findAll() {
        ArrayList<Object> listAuthors = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {

            String sql = "SELECT * FROM authors ORDER BY authors.id ASC;";
            PreparedStatement objPrepareStatement = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepareStatement.executeQuery();

            while (objResult.next()){
                Author objAuthor = new Author();
                objAuthor.setId(objResult.getInt("id"));
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setNationality(objResult.getString("nationality"));

                listAuthors.add(objAuthor);

            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error " + e.getMessage());
        }

        ConfigDB.closeConnection();

        return listAuthors;
    }

    @Override
    public Object findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Author authorFound = null;

        try {
            String sql = "SELECT * FROM authors WHERE authors.id = ?;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                authorFound = new Author();
                authorFound.setId(objResult.getInt("id"));
                authorFound.setName(objResult.getString("name"));
                authorFound.setNationality(objResult.getString("nationality"));
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Data adquisition Error");
        }

        ConfigDB.closeConnection();
        return authorFound;
    }

    public List<Author> findByName(String name){
        ArrayList<Author> authors = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM authors WHERE name LIKE '%" + name + "%';";
            PreparedStatement preparedStatement = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = preparedStatement.executeQuery();

            while (objResult.next()){
                Author author = new Author();
                author.setId(objResult.getInt("id"));
                author.setName(objResult.getString("name"));
                author.setNationality(objResult.getString("nationality"));

                authors.add(author);

            }

            ConfigDB.closeConnection();

            return authors;

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Data adquisition Error");
        }

        return null;
    }
}
