package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;
import entity.Book;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class BookModel implements CRUD {
    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Book objBook = (Book) object;

        try {
            String sql = "INSERT INTO books (title,publication_year,price,id_author) VALUES(?, ?, ?, ?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objBook.getTitle());
            objPrepare.setString(2, objBook.getPublicationYear());
            objPrepare.setDouble(3, objBook.getPrice());
            objPrepare.setInt(4, objBook.getId_autor());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objBook.setId_autor(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Book insertion was successful");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error adding coder " + e.getMessage());
        }

        ConfigDB.closeConnection();


        return objBook;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Book objBook = (Book) object;
        boolean isUpdated = false;

        try {
            String sql = "UPDATE books SET title = ?, publication_year = ?, price = ? WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objBook.getTitle());
            objPrepare.setString(2, objBook.getPublicationYear());
            objPrepare.setDouble(3, objBook.getPrice());

            objPrepare.setInt(4, objBook.getId());

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
        Book objBook = (Book) object;
        boolean isDeleted = false;
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "DELETE FROM books WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            System.out.println(objBook.getId());
            objPrepare.setInt(1,objBook.getId());

            if (objPrepare.executeUpdate() > 0){
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
        Connection objConnection = ConfigDB.openConnection();


        List<Object> listBooks = new ArrayList<>();

        try {
            String sql = "SELECT  books.id ,books.title, books.publication_year, books.price,books.id_author ,authors.name AS author "
                    + "FROM authors INNER JOIN books ON authors.id = books.id_author;";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Book objBook = new Book();
                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setPublicationYear(objResult.getString("publication_year"));
                objBook.setPrice(objResult.getDouble("price"));
                objBook.setId_autor(objResult.getInt("id_author"));
                objBook.setName(objResult.getString("author"));

                listBooks.add(objBook);
            }



        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Data adquisition Error");
        }


        ConfigDB.closeConnection();


        return listBooks;
    }

    @Override
    public Object findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Book objBook = null;

        try {
            String sql = "SELECT * FROM books WHERE id = ?;";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objBook = new Book();
                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setPublicationYear(objResult.getString("publication_year"));
                objBook.setPrice(objResult.getDouble("price"));
                objBook.setId_autor(objResult.getInt("id_author"));

            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Data adquisition Error");
            System.out.println(e.getMessage());
        }



        return objBook;
    }

    public List<Book> findByName(String name){
        ArrayList<Book> books = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM books WHERE title LIKE '%" + name + "%';";
            PreparedStatement preparedStatement = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = preparedStatement.executeQuery();

            while (objResult.next()){
                Book book = new Book();
                book.setId(objResult.getInt("id"));
                book.setTitle(objResult.getString("title"));
                book.setPublicationYear(objResult.getString("publication_year"));
                book.setPrice(objResult.getDouble("price"));

                books.add(book);

            }
            ConfigDB.closeConnection();
            return books;

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Data adquisition Error");
            System.out.println(e.getMessage());
        }

        return null;
    }
}
