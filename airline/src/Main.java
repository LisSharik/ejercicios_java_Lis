import controller.FlightController;
import controller.PassengerController;
import controller.PlaneController;
import controller.ReservationController;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        PlaneController objPlaneController = new PlaneController();
        FlightController objFlightController = new FlightController();
        PassengerController objPassengerController = new PassengerController();
        ReservationController objReservationController = new ReservationController();

        boolean flag = true;


        while (flag){
            String option = JOptionPane.showInputDialog("""
                    ============ Menu ============
                    1. Administrator Planes
                    2. Administrator Flights
                    3. Administrator Passengers
                    4. Administrator Reservations
                    5. Exit
                    """);


            switch (option){
                case "1":
                    objPlaneController.menu();
                    break;

                case "2":
                    objFlightController.menu();
                    break;

                case "3":
                    objPassengerController.menu();
                    break;

                case "4":
                    objReservationController.menu();
                    break;

                case "5":
                    flag = false;
                    break;

                case null:
                    JOptionPane.showMessageDialog(null,"Canceled");
                    flag = false;
                    break;


                default:
                    JOptionPane.showMessageDialog(null,"Invalid Option");
                    break;

            }


        }


    }
}
