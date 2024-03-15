import javax.swing.*;

public class MenuCourses {
    CourseManagnament objManagnament = new CourseManagnament();

    public void menuCourses (){
        boolean flag = true;
        try {
            do {
                String option = JOptionPane.showInputDialog("""
                            ========== Administrator Courses ==========
                            1. Add course
                            2. List Courses
                            3. Exit
                        """);

                if (option == null){
                    flag = false;

                }else {
                    switch (option){
                        case "1":
                            int code = Integer.parseInt(JOptionPane.showInputDialog("Enter the course code")) ;
                            String nameCourse = JOptionPane.showInputDialog("Enter the course name");
                           objManagnament.addCourse(code, nameCourse);
                            break;

                        case "2":
                            objManagnament.listCourse();
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
