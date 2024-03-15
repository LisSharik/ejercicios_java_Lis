import javax.swing.*;

public class MenuStudents {
    CourseManagnament objManagnament = new CourseManagnament();
    public void menuStudents(int code){
        Course course = objManagnament.searchForCode(code);

        System.out.println(course);
        if(course == null){
            JOptionPane.showMessageDialog(null,"Course didn't found");
        }else {

            boolean flag = true;
            try {
                do {
                    String option = JOptionPane.showInputDialog("""
                            ========== Administrator Courses ==========
                            1. Add student
                            2. List students
                            3. Exit
                        """);

                    if (option == null){
                        flag = false;
                    }else {
                        switch (option){
                            case "1":
                                String nameStudent =JOptionPane.showInputDialog("Enter the student name");
                                String emailStudent = JOptionPane.showInputDialog("Enter the student email");
                                course.addStudent(nameStudent,emailStudent);
                                break;

                            case "2":
                                course.listStudents();
                                break;

                            case "3":
                                flag = false;
                                break;

                            default:
                                JOptionPane.showMessageDialog(null,"Invalid option");
                                break;
                        }
                    }


                }while (flag == true);

            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Error");
            }
        }


    }

}
