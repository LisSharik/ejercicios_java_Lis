import javax.swing.*;
import java.util.ArrayList;

public class CourseManagnament {
    /*Clase GestionCursos: Encargada de administrar los cursos, incluyendo métodos para
    agregar cursos, inscribir estudiantes en cursos y listar estudiantes inscritos en un curso
    específico.*/

    private ArrayList<Course> courses = new ArrayList<>();



    public CourseManagnament() {
    }



    public void addCourse (int code, String name){
        if (this.searchForCode(code) == null){
            Course newCourse = new Course(code, name);
            System.out.println(newCourse);
            courses.add(newCourse);
            JOptionPane.showMessageDialog(null, "The course " + name + " has been created");

        }else {
            JOptionPane.showMessageDialog(null,"The code is alredy exists");
        }

    }


    public void listCourse(){
        String listCourses ="";
        if(this.courses.isEmpty()){
            JOptionPane.showMessageDialog(null,"The courses list is empty");
        }else {
            for (Course course: this.courses){
                listCourses += course.toString() +"\n";
            }
            JOptionPane.showMessageDialog(null,"========== Courses list ==========\n" + listCourses);
        }
    }

    public Course searchForCode(int code){
        for (Course courseTemp: this.courses){
            System.out.println(courseTemp.getCode());
            System.out.println(code);
            if (courseTemp.getCode() == code){
                return courseTemp;
            }
        }
        return null;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}
