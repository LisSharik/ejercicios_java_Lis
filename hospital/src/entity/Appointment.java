package entity;

public class Appointment {
    private int id_appointment;
    private String appointment_date;
    private String appointment_time;
    private String reason;

    private int id_patient;
    private Patient objPatient;

    private int id_doctor;
    private Doctor objdoctor;

    public Appointment() {
    }

    public Appointment(String appointment_date, String appointment_time, String reason, int id_patient,
                       Patient objPatient, int id_doctor, Doctor objdoctor) {
        this.appointment_date = appointment_date;
        this.appointment_time = appointment_time;
        this.reason = reason;
        this.id_patient = id_patient;
        this.objPatient = objPatient;
        this.id_doctor = id_doctor;
        this.objdoctor = objdoctor;
    }

    public int getId_appointment() {
        return id_appointment;
    }

    public void setId_appointment(int id_appointment) {
        this.id_appointment = id_appointment;
    }

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(String appointment_time) {
        this.appointment_time = appointment_time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public Patient getObjPatient() {
        return objPatient;
    }

    public void setObjPatient(Patient objPatient) {
        this.objPatient = objPatient;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public Doctor getObjdoctor() {
        return objdoctor;
    }

    public void setObjdoctor(Doctor objdoctor) {
        this.objdoctor = objdoctor;
    }

    @Override
    public String toString() {
        return "ID: " + id_appointment
                + " - Appointment date: " + appointment_date + " " + appointment_time
                + " - Patient name: " + objPatient.getName_patient() + " "+ objPatient.getLast_name_patient()
                + " - Doctor: " + objdoctor.getName_doctor() + " " +objdoctor.getLast_name_doctor()
                + "\n - Reason: \n" + reason;
    }
}
