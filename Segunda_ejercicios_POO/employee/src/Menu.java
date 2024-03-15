import javax.swing.*;

public class Menu {
    public void menuEmployee(){
        boolean flag = true;
        EmployeeMagnament objEmployee = new EmployeeMagnament();

        try {

            do {
                String option = JOptionPane.showInputDialog("""
                        ======= Enter an option =======
                        1. Add employee
                        2. List employee
                        3. Remove employee
                        4. Exit
                        """);

                if (option == null){
                    JOptionPane.showMessageDialog(null,"Finalized");
                    flag = false;
                }else {

                    switch (option){
                        case "1":
                            String nameEmployee = JOptionPane.showInputDialog("Enter the employee's name");
                            int age = Integer.parseInt(JOptionPane.showInputDialog("Enter the employee's age"));
                            double salary = Double.parseDouble(JOptionPane.showInputDialog("Enter the employee's salary"));
                            int seleccion = JOptionPane.showOptionDialog(
                                    null,
                                    "Select employee type",
                                    "Select",
                                    JOptionPane.YES_NO_CANCEL_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,    // null para icono por defecto.
                                    new Object[] { "Temporary employee", "Permanent employee" },   // null para YES, NO y CANCEL
                                    "Temporary employee");

                            switch (seleccion){
                                case 0:
                                    objEmployee.addTemporaryEmployee(nameEmployee,age,salary);
                                    break;

                                case 1:
                                    objEmployee.addPermanentEmployee(nameEmployee,age,salary);
                                    break;

                            }


                            break;


                        case "2":
                            JOptionPane.showMessageDialog(null,objEmployee.listEmployees());
                            break;

                        case "3":
                            objEmployee.removeEmployee();
                            break;

                        case "4":
                            flag = false;
                            break;

                        default:
                            JOptionPane.showMessageDialog(null,"Invalid option");
                            break;
                    }
                }



            }while (flag);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error");
        }
    }
}
