package entity;

public class Patient {
    //id_patient, name_patient, last_name_patient, birth_date, identification_document
    private int id_patient;
    private String name_patient;
    private String last_name_patient;
    private String birth_date;

    private String identification_document;

    public Patient(){};

    public Patient(String name_patient, String last_name_patient, String birth_date, String identification_document) {
        this.name_patient = name_patient;
        this.last_name_patient = last_name_patient;
        this.birth_date = birth_date;
        this.identification_document = identification_document;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public String getName_patient() {
        return name_patient;
    }

    public void setName_patient(String name_patient) {
        this.name_patient = name_patient;
    }

    public String getLast_name_patient() {
        return last_name_patient;
    }

    public void setLast_name_patient(String last_name_patient) {
        this.last_name_patient = last_name_patient;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getIdentification_document() {
        return identification_document;
    }

    public void setIdentification_document(String identification_document) {
        this.identification_document = identification_document;
    }

    @Override
    public String toString() {
        return "ID: " + id_patient + " - Nombre: " + name_patient + " " + last_name_patient + " - Birth date: " + birth_date +
                " - Identification document: " + identification_document;
    }
}
