package src;

import java.time.LocalDate;
import java.time.LocalTime;

//Represents an oppointment between a patient and a health professional.
public class Appointment implements Comparable<Appointment> {

    //Information associated with the appointment.
    private final Patient patient;
    private final HealthProfessional professional;
    private final LocalDate appointmentDate;
    private final LocalTime appointmentTime;

    //Constructs an appointment for the current day.
    public Appointment(Patient patient, HealthProfessional professional,
                          LocalTime appointmentTime){
            
            if (patient == null){
                throw new IllegalArgumentException(
                    "Patient cannot be null.");
            }

            if (professional == null){
                throw new IllegalArgumentException(
                    "Health professional cannot be null.");
            }

            if (appointmentTime == null){
                throw new IllegalArgumentException(
                    "Appointment time cannot be null.");
            }

            this.patient = patient;
            this.professional = professional;
            this.appointmentDate = LocalDate.now();
            this.appointmentTime = appointmentTime;
        }

        //Returns the patient associated with this appointment.
        public Patient getPatient(){
            return patient;
        }

        //Returns the selected health professional.
        public HealthProfessional getProfessional(){
            return professional;
        }

        //Returns the appointment date.
        public LocalDate getAppointmentDate(){
            return appointmentDate;
        }

        //Returns the appointment time.
        public LocalTime getAppointmentTime(){
            return appointmentTime;
        }

        //Compares two appointments according to their time.
        @Override
        public int compareTo(Appointment other){

            if (other == null){
                throw new IllegalArgumentException(
                    "Cannot compare to a null appointment.");
            }

            return this.appointmentTime.compareTo(other.appointmentTime);
        }

        //Returns a readdable description of the appointment, including patient and professional information.
        @Override
        public String toString(){
            
            return "Appointment Details\n" 
                    + "------------------------------------------------\n" 
                    + " Date: " + appointmentDate + "\n"
                    + " Time: " + appointmentTime + "\n"
                    + " Patient: " + patient.getName() + "\n"
                    + " Professional: " + professional.getName() + "\n"
                    + "------------------------------------------------\n";
        }
}
