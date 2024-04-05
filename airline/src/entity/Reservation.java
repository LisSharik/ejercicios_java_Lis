package entity;

public class Reservation {

    private int id;
    private String reservationDate;
    private String seat;
    private int idPassenger;
    private Passenger objPassenger;
    private int idFlight;
    private Flight objFlight;

    public Reservation(){}

    public Reservation(String reservationDate, String seat, int idPassenger, Passenger objPassenger, int idFlight, Flight objFlight) {
        this.reservationDate = reservationDate;
        this.seat = seat;
        this.idPassenger = idPassenger;
        this.objPassenger = objPassenger;
        this.idFlight = idFlight;
        this.objFlight = objFlight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getIdPassenger() {
        return idPassenger;
    }

    public void setIdPassenger(int idPassenger) {
        this.idPassenger = idPassenger;
    }

    public Passenger getObjPassenger() {
        return objPassenger;
    }

    public void setObjPassenger(Passenger objPassenger) {
        this.objPassenger = objPassenger;
    }

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public Flight getObjFlight() {
        return objFlight;
    }

    public void setObjFlight(Flight objFlight) {
        this.objFlight = objFlight;
    }
    //id_reservation, reservation_date, seat, id_passenger, id_flight
    @Override
    public String toString() {
        return "ID: " + id + " - Reservation date: " + reservationDate
                + " - Seat: " + seat
                + " - Destiny: " + this.objFlight.getDestiny()
                + "\n - Name passenger: " + this.objPassenger.getName() + " " + this.objPassenger.getLastName()
                + " - Identification document: " + this.objPassenger.getIdentificationDocument()
                + " - Departure date: " + this.objFlight.getDepartureDate()
                + " - Departure time: " + this.objFlight.getDepartureTime()
                + " - Model Plane: " + this.objFlight.getObjPlane().getModel();
    }
}

