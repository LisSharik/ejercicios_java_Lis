import javax.swing.*;

public class Main {
    public static void main(String[] args){
        MenuCourses menuCourses = new MenuCourses();
        MenuStudents menuStudents = new MenuStudents();

        boolean flagMain = true;

        try {
            do {
                String option = JOptionPane.showInputDialog("""
                        ============== Menu Administrator ==============
                        1. Administrator courses
                        2. Administrator students
                        3. Exit
                        """);

                if (option == null){
                    JOptionPane.showMessageDialog(null,"Finalized");
                    flagMain = false;

                }else {
                    switch (option){
                        case "1":
                            menuCourses.menuCourses();
                            break;

                        case "2":

                            menuStudents.menuStudents(Integer.parseInt(JOptionPane.showInputDialog("Enter the code of course"))  );
                            break;

                        case "3":
                            flagMain = false;
                            break;

                        default:
                            JOptionPane.showMessageDialog(null,"Invalid option");
                    }
                }


            }while (flagMain == true);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error");
        }

    }
}
