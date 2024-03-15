public class TemporaryEmployee extends Employee{
    /*Clase EmpleadoTemporal y Clase EmpleadoPermanente: Heredan de Empleado y
    representan diferentes tipos de empleados.*/

    public TemporaryEmployee(String name, int age, int idEmployee, double salary) {
        super(name, age, idEmployee, salary);
    }

    @Override
    public String toString() {
        String message = super.toString();

        return message + " - Type of employee: Temporary";
    }
}
