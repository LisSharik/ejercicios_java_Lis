import javax.swing.*;

public class MenuStudents {
   private CourseManagnament objManagnament ;

    public MenuStudents(CourseManagnament objManagnament) {
        this.objManagnament = objManagnament;
    }

    public void menuStudents(int code){
        Course course = this.objManagnament.searchForCode(code);

        System.out.println(course);
        if(course == null){
            JOptionPane.showMessageDialog(null,"Course didn't found");
        }else {

            boolean flag = true;
            try {
                do {
                    String option = JOptionPane.showInputDialog( "========== Administrator " +course.getName() + "==========\n" + """
                            1. Add student
                            2. List students
                            3. Remove student
                            4. Exit
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
                                JOptionPane.showMessageDialog(null,course.listStudents());
                                break;

                            case "3":
                                course.removeStudent(Integer.parseInt(JOptionPane.showInputDialog(course.listStudents()+ "Enter student id")));
                                break;
                            case "4":
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
