package entity;

public class Speciality {
    private int id_speciality;
    private String name;
    private String description;


    public Speciality(){}

    public Speciality(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public int getId_speciality() {
        return id_speciality;
    }

    public void setId_speciality(int id_speciality) {
        this.id_speciality = id_speciality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ID: " + id_speciality +  " - Speciality: " + name + "\n Description: \n" + description;
    }
}
