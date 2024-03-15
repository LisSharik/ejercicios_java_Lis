import javax.swing.*;
import java.util.ArrayList;

public class EmployeeMagnament {
    /*Clase GestionEmpleados: Utiliza un ArrayList para gestionar objetos del tipo Empleado.
    Implementa métodos para añadir, eliminar y mostrar empleados. Utiliza polimorfismo para
    manejar diferentes tipos de empleados.*/

    private ArrayList<Employee> employees = new ArrayList<>();
    private int generateId = 0;

    public EmployeeMagnament(){}

    public void addTemporaryEmployee(String nameEmployee,int age, double salary){
        this.generateId++;
        Employee newEmployee = new TemporaryEmployee(nameEmployee,age,this.generateId,salary);
        employees.add(newEmployee);
        JOptionPane.showMessageDialog(null,"Employee successfully added");
    }

    public void addPermanentEmployee(String nameEmployee,int age, double salary){
        this.generateId++;
        Employee newEmployee = new PermanetEmployee(nameEmployee,age,this.generateId,salary);
        employees.add(newEmployee);
        JOptionPane.showMessageDialog(null,"Employee successfully added");
    }

    public String listEmployees(){
        String listEmployees = "";

        if (this.employees.isEmpty()){
            String message = "The employees list is empty";
            return message;
        }else {

            for (Employee employee: this.employees){
                listEmployees += employee.toString() + "\n";
            }
        }
        return "======= Employee List =======\n" + listEmployees;



    }


    public void removeEmployee(){

        if(this.employees.isEmpty()){
            JOptionPane.showMessageDialog(null,"The employees list is empty");
        }else {

            int idEmployee = Integer.parseInt(JOptionPane.showInputDialog(this.listEmployees() + "\nEnter the Id of the employee to remove"));
            if (this.employees.removeIf(employee -> employee.getIdEmployee() == idEmployee)){
                JOptionPane.showMessageDialog(null,"The employee has been remove");
            }else {
                JOptionPane.showMessageDialog(null,"The employee id hasn't been found");
            }
        }


    }





}
