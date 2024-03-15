import java.text.NumberFormat;

public class Employee extends Person {

   /* Clase Empleado: Hereda de Persona y añade propiedades como idEmpleado y salario. Usa
    encapsulamiento adecuadamente.*/

    private int idEmployee;
    private double salary;

    private NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

    public Employee(String name, int age, int idEmployee, double salary) {
        super(name, age);
        this.idEmployee = idEmployee;
        this.salary = salary;
    }


    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String salaryFormat(){
        return numberFormat.format(this.salary);
    }

    @Override
    public String toString() {
        String message = super.toString();
        return "ID: " + idEmployee + message + " - Salary: " + salaryFormat() ;
    }
}
