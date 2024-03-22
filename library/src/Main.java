import controller.AuthorController;
import controller.BookController;

import javax.swing.*;


public class Main {
    public static void main(String[] args){
        AuthorController objAuthorController = new AuthorController();
        BookController objBookController = new BookController();


        boolean flag = true;
        do {
            String option = JOptionPane.showInputDialog("""
                    MENU
                    1. Administrator Authors
                    2. Administrator Books
                    3. Exit
                    """);

            switch (option){
                case "1":
                    objAuthorController.menuAuthor();
                    break;

                case "2":
                    objBookController.menuBook();
                    break;

                case "3":
                    flag = false;
                    break;

                case null:
                    JOptionPane.showMessageDialog(null,"Cancel");
                    flag = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Invalid Option");
                    break;

            }

        }while (flag == true);
    }


}
