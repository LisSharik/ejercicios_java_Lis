package entity;

public class Doctor {
    private int id_doctor;
    private String name_doctor;
    private String last_name_doctor;
    private int id_speciality_doctor;

    private Speciality objSpeciality;
    public Doctor(){};

    public Doctor(String name_doctor, String last_name_doctor, int id_speciality_doctor1, Speciality objSpeciality) {
        this.name_doctor = name_doctor;
        this.last_name_doctor = last_name_doctor;
        this.id_speciality_doctor = id_speciality_doctor1;
        this.objSpeciality = objSpeciality;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public String getName_doctor() {
        return name_doctor;
    }

    public void setName_doctor(String name_doctor) {
        this.name_doctor = name_doctor;
    }

    public String getLast_name_doctor() {
        return last_name_doctor;
    }

    public void setLast_name_doctor(String last_name_doctor) {
        this.last_name_doctor = last_name_doctor;
    }


    public int getId_speciality_doctor() {
        return id_speciality_doctor;
    }

    public void setId_speciality_doctor(int id_speciality_doctor) {
        this.id_speciality_doctor = id_speciality_doctor;
    }

    public Speciality getObjSpeciality() {
        return objSpeciality;
    }

    public void setObjSpeciality(Speciality objSpeciality) {
        this.objSpeciality = objSpeciality;
    }

    @Override
    public String toString() {
        return "ID: " + id_doctor + " - Name: " + name_doctor + " " + last_name_doctor + " - Speciality: " + this.objSpeciality.getName() ;
    }
}
