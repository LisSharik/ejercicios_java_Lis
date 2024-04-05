package entity;

public class Flight {
    private  int id;
    private String destiny;
    private String departureDate;
    private String departureTime;
    private int idPlane;
    private Plane objPlane;

    public Flight() {}

    public Flight(String destiny, String departureDate, String departureTime, int idPlane, Plane objPlane) {
        this.destiny = destiny;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.idPlane = idPlane;
        this.objPlane = objPlane;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public int getIdPlane() {
        return idPlane;
    }

    public void setIdPlane(int idPlane) {
        this.idPlane = idPlane;
    }

    public Plane getObjPlane() {
        return objPlane;
    }

    public void setObjPlane(Plane objPlane) {
        this.objPlane = objPlane;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - Destiny: " + destiny + " - Departure date: " + departureDate
                + " - Departure time: " + departureTime + " - Model Plane: " + objPlane.getModel();
    }
}
