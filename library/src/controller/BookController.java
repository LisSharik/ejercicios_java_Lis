package controller;

import entity.Author;
import entity.Book;
import model.AuthorModel;
import model.BookModel;

import javax.swing.*;
import java.util.List;

public class BookController {
    BookModel bookModel = new BookModel();
    AuthorController objAuthorController = new AuthorController();
    AuthorModel objAuthorModel = new AuthorModel();

    public BookController(){
        this.bookModel = new BookModel();
    }

    public void getAll(){
        String list = this.getAll(this.bookModel.findAll());
        JOptionPane.showMessageDialog(null,list);
    }

    public String getAll(List listobject){
        String list = "================ List Books ================ \n";
        for (Object obj: listobject){
            Book objBook = (Book) obj;

            list += objBook.toString() + "\n";
        }

        return list;
    }
    public void foundByName(){
        String list = "============= List Books =============\n";
        String title = JOptionPane.showInputDialog("Enter the title");

        for (Object obj: bookModel.findByName(title)){
            list += obj.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null,list);
    }
    public void create(){
        String listAuthorString = objAuthorController.getAll(objAuthorModel.findAll());
        Book objBook = new Book();
        int idAuthor = Integer.parseInt(JOptionPane.showInputDialog(null,listAuthorString + "\nEnter the Author's id"));

        Author objAuthor = (Author) this.objAuthorModel.findById(idAuthor);
        if (objAuthor == null){
            JOptionPane.showMessageDialog(null,"Author not found.");
        }else {
            /*title,publication_year,price,id_author*/
            objBook.setTitle(JOptionPane.showInputDialog("Enter the book's title"));
            objBook.setPublicationYear(JOptionPane.showInputDialog("Enter the book' Publication Date"));
            objBook.setPrice(Double.parseDouble(JOptionPane.showInputDialog(null,"Enter the book' price")));
            objBook.setId_autor(idAuthor);

            objBook = (Book) this.bookModel.insert(objBook);
        }


    }

    public void delete(){
        String listBook = this.getAll(this.bookModel.findAll());
        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listBook + "Enter the ID of the book to delete"));
        System.out.println(idDelete);
        Book objBook = (Book) this.bookModel.findById(idDelete);
        System.out.println(objBook);

        if (objBook == null){
            JOptionPane.showMessageDialog(null,"Book not found.");

        }else {
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete the book: \n");

            if (confirm == 0){
                this.bookModel.delete(objBook);
            }

        }

    }
    public void update(){
        String listBooks = this.getAll(this.bookModel.findAll());
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listBooks + "\nEnter the id of the book to edit" ));
        Book objBook = (Book) this.bookModel.findById(idUpdate);

        if (objBook == null){
            JOptionPane.showMessageDialog(null,"Book not found");
        }else {
            objBook.setTitle(JOptionPane.showInputDialog(null,"Enter new title ", objBook.getTitle()));
            objBook.setPublicationYear(JOptionPane.showInputDialog(null,"Enter new publication year " + objBook.getPublicationYear()));
            objBook.setPrice(Double.parseDouble(JOptionPane.showInputDialog(null,"Enter the new price " + objBook.getPrice())));
            this.bookModel.update(objBook);
        }


    }

    public void menuBook(){
        boolean flag = true;

        do {
            String option = JOptionPane.showInputDialog("""
                    BOOKS MENU
                    1. List Books
                    2. Insert Book
                    3. Update Book
                    4. Delete book
                    5. Get by name
                    6. Exit
                    
                    """);

            switch (option){
                case "1":
                    this.getAll();
                    break;

                case "2":
                    this.create();
                    break;

                case "3":
                    this.update();
                    break;

                case "4":
                    this.delete();
                    break;

                case "5":
                    this.foundByName();
                    break;

                case "6":
                    flag = false;
                    break;

                case null:
                    JOptionPane.showMessageDialog(null,"CANCELED");
                    flag = false;
                    break;

                default:
                    JOptionPane.showMessageDialog(null,"Invalid option");
                    break;
            }
        }while (flag == true);

    }

}
