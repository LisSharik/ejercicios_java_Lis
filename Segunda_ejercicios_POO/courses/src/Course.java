import javax.swing.*;
import java.util.ArrayList;

public class Course {
   /* Clase Curso: Con propiedades como codigo, nombre, y listaEstudiantes, donde
    listaEstudiantes es un ArrayList de objetos Estudiante.*/


    private int code;

    private int idStudent = 0;
    private String name;

    private ArrayList<Student> students = new ArrayList<>();


    public Course(int code, String name) {
        this.code = code;
        this.name = name;
        this.students = new ArrayList<>();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void addStudent(String nameStudent, String emailStudent){
        this.idStudent++;
        Student newStudent = new Student(this.idStudent,nameStudent,emailStudent);
        this.students.add(newStudent);
        JOptionPane.showMessageDialog(null, "The student " + nameStudent + " has been admitted");
    }


    public String listStudents(){
        String listAllStudents = "";
        if (this.students.isEmpty()){
            listAllStudents = "The list is empty";

        }else {
            for (Student student: this.students){
                listAllStudents += student.toString() + "\n";
            }

        }

        return "========== Courses list ==========\n" + listAllStudents;

    }

    public void removeStudent(int idStudent){
        String message = "";
        if (this.students.removeIf(student -> student.getId() == idStudent)){
            message = "The student has been removed";

        }else {
            message = "The student hasn't been found";
        }

        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public String toString() {
        return "Code: " + code + " - Name: " + name;
    }
}
