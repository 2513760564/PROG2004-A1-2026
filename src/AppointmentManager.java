package src;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**Manages the collection of appointments for the health service.
 * Provides functionality to add, view, filter, sort and cancel appointments while preventing invaid bookings and double-booking.
 */
public class AppointmentManager {

    //Fixed daliy appointment schedule.
    private static final LocalTime START_TIME = 
            LocalTime.of(8, 0);

    private static final LocalTime END_TIME = 
            LocalTime.of(16, 0);

    private  static final int SLOT_INTERVAL_MINUTES = 30;

    //Stores all appointments managed by the system.
    private final ArrayList<Appointment> appointments;

    //Constructs an oppointment manager with an empty collecion.
    public AppointmentManager() {
        appointments = new ArrayList<>();
    }

    //Creates the available appointment time slots.
    public static List<LocalTime> getAvailableTimeSlots(){

        ArrayList<LocalTime> timeSlots = new ArrayList<>();

        LocalTime currentTime = START_TIME;

        while (!currentTime.isAfter(END_TIME)) {

            timeSlots.add(currentTime);

            currentTime = currentTime.plusMinutes(SLOT_INTERVAL_MINUTES);
        }

        return Collections.unmodifiableList(timeSlots);
    }

    //Checks whether a time is part of the clinic's schedule.
    private boolean isValidAppointmentTime(LocalTime time) {

        if (time == null) {
            return false;
        }

        return getAvailableTimeSlots().contains(time);
    }

    /**Adds a new oppointment to the collection.
     * 
     * Checks required information,appointment time, professional working day and double-booking.
     */
    public String addAppointment(Appointment appointment){

        //Check that an appointment was provided.
        if (appointment == null){
            return "WARNING: Appointment cannot be null.";
        }

        //Check whether the appointment time is valid.
        if (!isValidAppointmentTime(
                appointment.getAppointmentTime())){

            return "WARNING: Invaid appointment time. "
                   + "Please select a time between 08:00 and 16:00 "
                   +"in 30-minute intervals.";
            }

            //Appointments are only allowed on the current day.
            if (!appointment.getAppointmentDate().equals(
                    LocalDate.now())) {

                return "WARNING: Appointments can only be booked "
                      + "for the current day.";
            }

            //Check whether the professional works today.
            DayOfWeek today = LocalDate.now().getDayOfWeek();

            if (!appointment.getProfessional()
                    .getWorkingDays().contains(today)){
                
                return "WARNING: "    
                        + appointment.getProfessional().getName()
                        + " in not working today.";

            }

            //Check for an existing appointment for the same professional at the same time.
            for (Appointment existingAppointment : appointments){

                boolean sameProfessional = 
                        existingAppointment.getProfessional().equals(
                            appointment.getProfessional());
                
                boolean sameTime = 
                        existingAppointment.getAppointmentTime().equals(
                            appointment.getAppointmentTime());
                
                if (sameProfessional && sameTime) {

                    return "WARNING: "
                           + appointment.getProfessional().getName()
                           + " already has an appointment at "
                           + appointment.getAppointmentTime() + ".";
                }
            }

            //Add the appointment if all checks pass.
            appointments.add(appointment);

            return  "SUCCESS: Appointment booked successfully for "
                    + appointment.getPatient().getName()
                    + " at "
                    + appointment.getAppointmentTime() + "."; 
    }

    //Returns all appointments.
    //A defensive copy is returned to protect the internal collection.

    public List<Appointment> getAllAppointments() {

        return Collections.unmodifiableList(
                new ArrayList<>(appointments));
    }

    //Finds appointments associated with a health professional ID.

    public List<Appointment> getAppointmentsByProfessionalId(
            String professionalId) {

        ArrayList<Appointment> results = new ArrayList<>();

        if (professionalId == null
                || !professionalId.trim().matches("[0-9]+")) {

            return Collections.unmodifiableList(results);
        }

        String searchId = professionalId.trim();

        for (Appointment appointment : appointments) {

            if (appointment.getProfessional()
                    .getId().equals(searchId)){
                
                results.add(appointment);   
            }
        }

        return Collections.unmodifiableList(results);
    }

    //Finds appointments associated with a patient's mobile number.
    //Spaces, brackets and hyphens are ignored when comparing numbers.

    public List<Appointment> getAppointmentsByPatientMobileNumber(
            String mobileNumber) {
        
        ArrayList<Appointment> results = new ArrayList<>();

        String searchNumber = normalizeMobileNumber(mobileNumber);

        if (!isValidMobileNumber(searchNumber)) {
            return Collections.unmodifiableList(results);
        }

        for (Appointment appointment : appointments){

            String appointmentNumber = normalizeMobileNumber(
                   appointment.getPatient().getMobileNumber());
            
            if (appointmentNumber.equals(searchNumber)){
                results.add(appointment);
            }    
        }

        return Collections.unmodifiableList(results);
    }

    //Returns a sorted copy of all appointments.
    //Appointments are sorted from earliest to Latest.
    public List<Appointment> getSortedAppointments(){

        ArrayList<Appointment> sortedAppointments = 
                 new ArrayList<>(appointments);

        Collections.sort(sortedAppointments);

        return Collections.unmodifiableList(sortedAppointments);
    }

    //Returns appoointments for a professional sorted by time.
    public List<Appointment> getSortedAppointmentsByProfessionalId(
            String professionalId) {
        ArrayList<Appointment> sortedAppointments = 
                 new ArrayList<>(
                    getAppointmentsByProfessionalId(professionalId));
        
        Collections.sort(sortedAppointments);

        return Collections.unmodifiableList(sortedAppointments);             
    }

    //Returns appointments for a patient sorted by time.
    public List<Appointment> getSortedAppointmentsByPatientMobileNumber(
                String mobileNumber) {

        ArrayList<Appointment> sortedAppointments = 
                new ArrayList<>(
                    getAppointmentsByPatientMobileNumber(mobileNumber));
       
        Collections.sort(sortedAppointments);

        return Collections.unmodifiableList(sortedAppointments);
    }

    //Cancels an appointment using a professional ID and appointment time.
    public String cancelAppointmentByProfessionalIdAndTime(
            String professionalId,
            LocalTime appointmentTime) {
        

        if (professionalId == null
                || !professionalId.trim().matches("[0-9]+")){

            return "WARNING: Inbalid professional ID.";
        }

        if (!isValidAppointmentTime(appointmentTime)) {

            return "WARNING: Invalid appointment time.";
        }

        String searchId = professionalId.trim();

        Iterator<Appointment> iterator = appointments.iterator();

        while (iterator.hasNext()){

            Appointment appointment = iterator.next();

            boolean sameProfessional = appointment.getProfessional().getId().equals(searchId);
            boolean sameTime = appointment.getAppointmentTime().equals(appointmentTime);

            if (sameProfessional && sameTime){

                iterator.remove();

                return  "SUCCESS: Appointment at "
                        + appointmentTime
                        +" for professional ID "
                        + searchId
                        + " has been cancelled.";
            }
        }

        return "WARNING: No matching appointment was found.";
    }

    /**Cancels all appointments associated with a patient's mobile number.
     * 
     * All matching appointments are removed because a mobile number alone cannot distinguish between multiple bookings.
     */
    public String cancelAppointmentsByPatientMobileNumber(
            String mobileNumber){

        String searchNumber = normalizeMobileNumber(mobileNumber);

        if (!isValidMobileNumber(searchNumber)){

            return "WARNING: Invalid patient mobile number.";
        }

        int cancelledCount = 0;

        Iterator<Appointment> iterator = appointments.iterator();

        while (iterator.hasNext()){

            Appointment appointment = iterator.next();

            String appointmentNumber = normalizeMobileNumber(
                    appointment.getPatient().getMobileNumber());
            
            if (appointmentNumber.equals(searchNumber)){

                iterator.remove();
                cancelledCount++;
            }
        }

        if (cancelledCount == 0){

            return "WARNING: No appointments found for mobile number "
                    + mobileNumber + ".";
        }

        return "SUCCESS: "
              + cancelledCount
              + "appointment(s) cancelled for mobile number "
              + mobileNumber + ".";          
    }

    //Removes formatting characters from a mobile number.
    private String normalizeMobileNumber(String mobileNumber){

        if (mobileNumber == null) {
            return "";
        }

        return mobileNumber.replaceAll("[\\s()-]", "");
    }

    //Checks whether a normalized mobile number is valid.
    private boolean isValidMobileNumber(String mobileNumber){

        return mobileNumber != null
                && mobileNumber.matches("\\+?[0-9]{7,15}");
    }   
}
