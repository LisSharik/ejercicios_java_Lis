package controller;


import entity.Author;
import model.AuthorModel;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class AuthorController {
    AuthorModel objAuthorModel = new AuthorModel();

    public AuthorController() {
        this.objAuthorModel = new AuthorModel();
    }

    public void getAll(){
        String list = this.getAll(this.objAuthorModel.findAll());
        JOptionPane.showMessageDialog(null,list);

    }


    public String getAll(List listObject){
        String list = "============= List Authors =============\n";
        for (Object obj: listObject){
            Author objAuthor = (Author) obj;
            list += objAuthor.toString() + "\n";
        }

        return list;
    }

    public void foundByName(){
        String list = "============= List Authors =============\n";
        String name = JOptionPane.showInputDialog("Enter the name");

        for (Object obj: objAuthorModel.findByName(name)){
            list += obj.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null,list);
    }



    public void addAuthor(){
        Author newAuthor = new Author();

        try {
            newAuthor.setName(JOptionPane.showInputDialog("Enter the author name"));
            newAuthor.setNationality(JOptionPane.showInputDialog("Enter the nationality author"));

            newAuthor = (Author) this.objAuthorModel.insert(newAuthor);
            JOptionPane.showMessageDialog(null,newAuthor.getName() + " has been added");
        }catch (Exception e){
            this.addAuthor();
        }
    }

    public void delete(){
        String list = this.getAll(this.objAuthorModel.findAll());
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(list + "\nEnter the author id"));
        int confirm = 1;

        Author objAuthor =(Author) this.objAuthorModel.findById(idDelete);
        if (objAuthor == null){
            JOptionPane.showMessageDialog(null,"Author not found");
        }else {
            confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure want to delete the author?: \n" + objAuthor.toString());

            if (confirm == 0){
                this.objAuthorModel.delete(objAuthor);
            }

        }

    }

    public void update(){
        String listCoders = this.getAll(this.objAuthorModel.findAll());
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listCoders + "\nEnter the id of the coder to edit"));

        Author objAuthor = (Author) this.objAuthorModel.findById(idUpdate);

        if (objAuthor == null){
            JOptionPane.showMessageDialog(null,"Author not found");
        }else {
            objAuthor.setName(JOptionPane.showInputDialog(null,"Enter new name", objAuthor.getName()));
            objAuthor.setNationality(JOptionPane.showInputDialog(null,"Enter new nationality", objAuthor.getNationality()));

            this.objAuthorModel.update(objAuthor);
        }



    }


    public void menuAuthor(){
        boolean flag = true;

        do {
            String option = JOptionPane.showInputDialog("""
                    AUTHORS MENU
                    1. List Authors
                    2. Insert Author
                    3. Update Author
                    4. Delete Author
                    5. Get by name
                    6. Exit
                    """);

            switch (option){
                case "1":
                    this.getAll();
                    break;

                case "2":
                    this.addAuthor();
                    break;

                case "4":
                    this.delete();
                    break;

                case "3":
                    this.update();
                    break;

                case "5":
                    this.foundByName();
                    break;

                case "6":
                    flag = false;
                    break;

                case null:
                    JOptionPane.showMessageDialog(null,"Cancel");
                    flag = false;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option");
                    break;
            }

        }while (flag == true);
    }

}
