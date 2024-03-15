public class PermanetEmployee extends Employee {
    public PermanetEmployee(String name, int age, int idEmployee, double salary) {
        super(name, age, idEmployee, salary);
    }

    @Override
    public String toString() {
        String message = super.toString();

        return message + " - Type of employee: Permanent";
    }
}
