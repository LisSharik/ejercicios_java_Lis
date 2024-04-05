package entity;

public class Passenger {
    private int id;
    private String name;
    private String lastName;
    private String identificationDocument;

    public Passenger() {}

    public Passenger(String name, String lastName, String identificationDocument) {
        this.name = name;
        this.lastName = lastName;
        this.identificationDocument = identificationDocument;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentificationDocument() {
        return identificationDocument;
    }

    public void setIdentificationDocument(String identificationDocument) {
        this.identificationDocument = identificationDocument;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - Name: " + name + " " + lastName + " - Identification Document: " + identificationDocument;
    }
}
